package com.winhands.base.login.web;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.winhands.base.auth.entity.Menu;
import com.winhands.base.auth.service.MenuService;
import com.winhands.base.exception.AuthenticationUtilException;
import com.winhands.base.shiro.BaseRealm;
import com.winhands.base.shiro.BaseRealm.ShiroUser;
import com.winhands.base.user.service.UserService;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.Digests;
import com.winhands.base.util.Encodes;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.member.entity.Captcha;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService; 
    @Autowired
    private MenuService menuService;
    @Autowired
    protected MemberService memberService;

    private RuntimeSchema<Menu> schema =  RuntimeSchema.createFrom(Menu.class);

    @ResponseBody
    @RequestMapping("/login")
    public BaseJson login(String name, String pass, HttpSession session) {
        logger.debug("进入登录操作功能:{},{}", name, pass); 
    	if(!vaildFQ()){
    		BaseJson base = new BaseJson();
    		base.setStatus(600);
            base.setMessage("登录异常，请联系开发人员!");
            logger.debug("登录异常，请联系开发人员!"); 
            return base;
    	}
        BaseJson base = new BaseJson();
        //正常登录逻辑
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setRememberMe(false);
        token.setUsername(name);
        token.setPassword(pass.toCharArray());
        token.setHost("1");
        List<Menu> list = new ArrayList<Menu>();
        try {
            SecurityUtils.getSubject().login(token);
            //没有异常即为登录校验通过
            base.setStatus(1);

        } catch (AuthenticationUtilException e) {
            base.setStatus(600);
            base.setMessage(e.getExceptionInfo());
            //base.setMessage("用户名或密码错误!");
            logger.error("AuthenticationUtilException", e);
            logger.info("用戶：{}，密码：{}，登录失败", name, pass);
        } catch (Exception e) {
            logger.info("用戶：{}，密码：{}，登录失败", name, pass);
            base.setStatus(600);
            base.setMessage("用户名密码不匹配!");
            logger.error("Exception", e);
        }
        if (base.getStatus() == 1) {
            try {
                userService.updateLastLoginByUserId(getUserSession().userId);
                String userId = BaseController.getUserSession().userId;
                list = menuService.queryMenuByUserId(userId);
                Menu root = new Menu();
                root.setMenuList(list);
                //RedisDao redisDao = new RedisDao();
                //redisDao.putMenu(root, userId);
                //base.setJson(root);
                session.setAttribute(userId,root);
            } catch (Exception e) {
                logger.error("修改登录时间出错：", e);
            }

        }

        return base;
    }

    @RequestMapping("/index")
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView("forward:/index.jsp?ac=0000");//redirect模式  
        return mv;
    }

    @ResponseBody
    @RequestMapping("/msLogin")
    public BaseJson mslogin(String name, String pass) {
        //SecurityUtils.getSubject().logout();
        logger.debug("进入登录操作功能:{},{}", name, pass);
        BaseJson base = new BaseJson();
        //正常登录逻辑
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setRememberMe(false);
        token.setUsername(name);
        token.setPassword(pass.toCharArray());
        token.setHost("2");
        //token.setRememberMe(true);
        try {
            SecurityUtils.getSubject().login(token);
            Member m = memberService.queryMemberByName(name);
            if("2".equals(m.getIsVaild())){  //账号被冻结
                SecurityUtils.getSubject().logout();
                base.setStatus(603);
                base.setMessage("账户被冻结，请联系客服！");
                return base;
            }
            if("2".equals(m.getType())){  //如果是年费会员
                    if(m.getExpireTime().compareTo(new java.sql.Date(System.currentTimeMillis()))==-1){  //充值到期
                        m.setType("0");   //设为普通会员
                        memberService.updateMember(m);
                    }
            }
            m.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            memberService.updateMember(m);
            //没有异常即为登录校验通过
            base.setStatus(200);
            ShiroUser s = getUserSession(); 
            return new BaseJson(200, "登录成功", "", m);
        } catch (AuthenticationUtilException e) {
            base.setStatus(600);
            base.setMessage(e.getExceptionInfo());
            logger.error("AuthenticationUtilException", e);
            logger.info("用戶：{}，密码：{}，登录失败", name, pass);
        } catch (Exception e) {
            logger.info("用戶：{}，密码：{}，登录失败", name, pass);
            base.setStatus(600);
            base.setMessage("用户名密码不匹配!");
            logger.error("Exception", e);
        }
       
        return base;
    }

    /**
     * 注册
     * @param m
     */
    @ResponseBody
    @RequestMapping("/register")
    public BaseJson register(Member m,String captcha){
        BaseJson json = new BaseJson();
        try{
            Captcha c = memberService.queryCaptcha(m.getPhoneNum());
            if(c==null){
                json.setStatus(500);
                json.setMessage("验证码无效，请重新发送验证码！");
                return json;
            }
            String captcha2 = c.getCaptcha();
            if(System.currentTimeMillis() - c.getUpdateTime().getTime() > 1000 * 60 * 5){  //验证码超时
                json.setStatus(500);
                json.setMessage("验证码超时，请重新发送验证码！");
                return json;
            }
            if(!captcha.equals(captcha2)){   //验证码错误
                json.setStatus(502);
                json.setMessage("验证码错误！");
                return json;
            }
                Member member =  memberService.queryMemberByName(m.getPhoneNum());
                if(member != null){   //已存在
                    json.setStatus(501);
                    json.setMessage("该手机号已注册！");
                    return json;
            }
            m.setId(StringUtil.getUUIDString());
            m.setType("0");  //普通会员
            m.setIsVaild("1");  //有效
            m.setIsCompleteStudent("0");
            m.setHasCompleted("0");
            entryptPassword(m);
            memberService.saveMember(m);
            json.setStatus(200);
            json.setMessage("注册成功！");
    }catch (Exception e){
            logger.error("会员注册出错：",e);
            json.setStatus(500);
            json.setMessage("系统错误！");
        }
        return json;
    }
    @ResponseBody
    @RequestMapping("/msQueryMemberById")
    public BaseJson msQueryMemberById(String id) {
    	 BaseJson json = new BaseJson();
        try {
        	if(!StringUtil.isNull(id)){
        		 Member m = memberService.queryMemberById(id);
                 json.setStatus(200);
                 json.setJson(m);
        	}else{
        		json.setStatus(600);
                json.setMessage("参数有误!");
        	}
           
        } catch (Exception e) {
            json.setStatus(600);
            json.setMessage("参数有误!");
            logger.error("Exception", e);
        }
       
        return json;
    }
    /**
     * 发送短信
     * @param phoneNum
     */
    @ResponseBody
    @RequestMapping("/msSendSMSUpdate")
    public BaseJson msSendSMSUpdate(String phoneNum){
        BaseJson json = new BaseJson();
        String phone=getUserSession().userPhone;
        try{
        	if(phone.equals(phoneNum)){
        		
        	}else{
        		 Member member =  memberService.queryMemberByName(phoneNum);
                 if(member != null){   //已存在
                     json.setStatus(501);
                     json.setMessage("该手机号已注册！");
                     return json;
                     }
        	}
           
            }catch (Exception e){
                e.printStackTrace();
                json.setStatus(500);
                json.setMessage("系统错误！");
                return json;
            }
        try {
            Captcha c = memberService.queryCaptcha(phoneNum);  //根据手机号码获取验证码
            Random r = new Random();
            String captcha = (r.nextInt(899999) + 100000) + "";
            int c1 = 1;
            if(c!=null){
                if(System.currentTimeMillis() - c.getUpdateTime().getTime() < 1000 * 60){  //两次间隔小于一分钟
                    json.setStatus(503);
                    json.setMessage("两次间隔不得小于一分钟");
                    return json;
                }else{
                    int count = c.getTodayCount();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 =  f.parse(c.getUpdateTime().toString());
                    String str1 = f.format(d1);
                    Date d2=new Date();
                    String str2 = f.format(d2);
                    if(str1.equals(str2)){  //还是当天
                        if(count >= BaseConstant.SMS_COUNT){  //超过限制次数
                            json.setStatus(502);
                            json.setMessage("发送验证码超过限制次数！");
                            return json;
                        }
                        c1 = c.getTodayCount() + 1;
                    }else{
                        c1 = 1;
                    }
                }
                send(phoneNum,captcha);  //发送短信
                c.setTodayCount(c1);
                c.setCaptcha(captcha);
                memberService.updateCaptcha(c);  //修改记录
            }else{  //没有该号码记录
                send(phoneNum,captcha);
                c = new Captcha();
                c.setTodayCount(1);
                c.setCaptcha(captcha);
                c.setPhoneNum(phoneNum);
                memberService.saveCaptcha(c);  //新增记录
            }
            json.setStatus(200);
        }catch (Exception e) {
            e.printStackTrace();
            json.setStatus(500);
        }
        return json;
    }
    /**
     * 发送短信
     * @param phoneNum
     */
    @ResponseBody
    @RequestMapping("/sendSMS")
    public BaseJson sendSMS(String phoneNum){
        BaseJson json = new BaseJson();
        try{
            Member member =  memberService.queryMemberByName(phoneNum);
            if(member != null){   //已存在
                json.setStatus(501);
                json.setMessage("该手机号已注册！");
                return json;
                }
            }catch (Exception e){
                e.printStackTrace();
                json.setStatus(500);
                json.setMessage("系统错误！");
                return json;
            }
        try {
            Captcha c = memberService.queryCaptcha(phoneNum);  //根据手机号码获取验证码
            Random r = new Random();
            String captcha = (r.nextInt(899999) + 100000) + "";
            int c1 = 1;
            if(c!=null){
                if(System.currentTimeMillis() - c.getUpdateTime().getTime() < 1000 * 60){  //两次间隔小于一分钟
                    json.setStatus(503);
                    json.setMessage("两次间隔不得小于一分钟");
                    return json;
                }else{
                    int count = c.getTodayCount();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 =  f.parse(c.getUpdateTime().toString());
                    String str1 = f.format(d1);
                    Date d2=new Date();
                    String str2 = f.format(d2);
                    if(str1.equals(str2)){  //还是当天
                        if(count >= BaseConstant.SMS_COUNT){  //超过限制次数
                            json.setStatus(502);
                            json.setMessage("发送验证码超过限制次数！");
                            return json;
                        }
                        c1 = c.getTodayCount() + 1;
                    }else{
                        c1 = 1;
                    }
                }
                send(phoneNum,captcha);  //发送短信
                c.setTodayCount(c1);
                c.setCaptcha(captcha);
                memberService.updateCaptcha(c);  //修改记录
            }else{  //没有该号码记录
                send(phoneNum,captcha);
                c = new Captcha();
                c.setTodayCount(1);
                c.setCaptcha(captcha);
                c.setPhoneNum(phoneNum);
                memberService.saveCaptcha(c);  //新增记录
            }
            json.setStatus(200);
        }catch (Exception e) {
            e.printStackTrace();
            json.setStatus(500);
        }
        return json;
    }
    public void send(String phoneNum,String captcha) throws Exception{
        HttpClient client = new HttpClient();
        String url = BaseConstant.SMSURL;
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("account",BaseConstant.ACCOUNT);
        postMethod.addParameter("pswd",BaseConstant.PSWD);
        postMethod.addParameter("mobile",phoneNum);
        String msg = "【"+BaseConstant.PRE_SMS+"】您的短信验证码是"+captcha+"，5分钟内有效。您正在使用该手机号码注册账号，如非本人操作，请忽略该短信。";
        postMethod.addParameter("msg",msg);
        postMethod.addParameter("needstatus","false");
        HttpClientParams params = new HttpClientParams();
        params.setContentCharset("UTF-8");
        postMethod.setParams(params);
        System.out.println(".....................");
        try {
        	 client.executeMethod(postMethod);
		} catch (Exception e) {
			 e.printStackTrace();
		}
        
        
    }
    
    private boolean vaildFQ(){
         GetMethod request = new GetMethod("http://www.winhands.com/result.html");//这里发送get请求
         
         // 获取当前客户端对象
         HttpClient client = new HttpClient();
        
         // 通过请求对象获取响应对象
         int statusCode;
		 try {
			request.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,1000);
			request.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,1);
			statusCode = client.executeMethod(request);
			// 判断网络连接状态码是否正常(0--200都数正常)
	        if (statusCode == HttpStatus.SC_OK) { 
	            //读取内容 
	            byte[] responseBody = request.getResponseBody();  //得到返回的内容
	            //处理内容
	            String  result  = new String(responseBody);
	            
	            if("false".equals(result)){
	            	return false;
	            }
	        } 
		 } catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }catch (Exception e) {
			 e.printStackTrace();
		}
		 return true; 
    }
    
    
    
    @RequestMapping("/logout")
    public ModelAndView logout(String name, String pass) {
        logger.debug("进入退出登录操作功能:{},{}", name, pass);
        try {
            SecurityUtils.getSubject().logout();
            //没有异常即为登录校验通过
        } catch (AuthenticationUtilException e) {

            logger.error("AuthenticationUtilException", e);
            logger.info("用戶：{}，密码：{}，登录失败", name, pass);
        } catch (Exception e) {
            logger.info("用戶：{}，密码：{}，登录失败", name, pass);

            logger.error("Exception", e);
        }
        ModelAndView mv = new ModelAndView("forward:/login.jsp");//redirect模式
        mv.addObject("message", "退出登录成功！");
        return mv;
    }

    private void entryptPassword(Member m) {
        byte[] salt = Digests.generateSalt(BaseConstant.SALT_SIZE);
        m.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(m.getPassword().getBytes(), salt, BaseRealm.HASH_INTERATIONS);
        m.setPassword(Encodes.encodeHex(hashPassword));
    }

   
}
