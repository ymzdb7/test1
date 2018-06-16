package com.winhands.cshj.member.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winhands.base.org.entity.Org;
import com.winhands.base.org.service.OrgService;
import com.winhands.base.shiro.BaseRealm;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.Digests;
import com.winhands.base.util.Encodes;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.base.web.entity.Tree;
import com.winhands.cshj.comment.service.CommentService;
import com.winhands.cshj.integration.service.IntegrationService;
import com.winhands.cshj.log.entity.Log;
import com.winhands.cshj.log.service.LogService;
import com.winhands.cshj.member.entity.Captcha;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;
import com.winhands.ncjc.mail.SendMail;

/** 
 * @author guojun
 */
@RequestMapping("/member")
@Controller
public class MemberController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberService impl;
    @Autowired
    private CommentService commentService;
    @Autowired
	private OrgService orgService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private LogService logService;
    private static String AC;

    /**
     * 查询会员列表
     * @param pageNum
     * @param pageSize
     * @param m
     * @param ac
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(String pageNo,String pageSize,Member m,String searchName){
    	List<Org> list = new ArrayList <Org>();
        if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(!StringUtil.isNull(searchName)){
            m.setSpare1(searchName);//学校年级班级   模糊查询
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        Page<Member> page = new Page<Member>();
        try{
            page = (Page<Member>)impl.queryMemberList(m);
            list = orgService.findAll(); 
        }catch (Exception e){
            logger.error("查询会员列表出错：",e);
        }
        //转换成json
		String json = list2TreeJson(list,searchName,"",true,true);  
        ModelAndView mv = new ModelAndView("forward:/view/member/memberIndex.jsp?ac=600001");
        mv.addObject("pagesBt",page2PageBt(page));
        mv.addObject("list",page.getResult());
        mv.addObject("member",m);
        mv.addObject("treejson",json);
        return mv;
    }
    //付费详单
    @RequestMapping("/queryMemberFeeList")
    public ModelAndView queryMemberFeeList(String pageNo,String pageSize,Member m){
        if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        Page<Member> page = new Page<Member>();
        try{
            page = (Page<Member>)impl.queryMemberFeeList(m);
        }catch (Exception e){
            logger.error("查询会员列表出错：",e);
        }
        ModelAndView mv = new ModelAndView("forward:/view/member/memberFeeList.jsp?ac=600002");
        mv.addObject("pagesBt",page2PageBt(page));
        mv.addObject("list",page.getResult());
        mv.addObject("member",m);
        return mv;
    }
  //付费统计
    @RequestMapping("/queryFeeCountList")
    public ModelAndView queryFeeCountList(String pageNo,String pageSize,Member m){
        if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        Page<Member> page = new Page<Member>();
        try{
            page = (Page<Member>)impl.queryFeeCountList(m);
        }catch (Exception e){
            logger.error("付费统计列表查询出错：",e);
        }
        ModelAndView mv = new ModelAndView("forward:/view/member/memberCountList.jsp?ac=600003");
        mv.addObject("pagesBt",page2PageBt(page));
        mv.addObject("list",page.getResult());
        mv.addObject("member",m);
        return mv;
    }
  //积分统计
    @RequestMapping("/queryIntegrationCountList")
    public ModelAndView queryIntegrationCountList(String pageNo,String pageSize,Member m,String ac){
        if(StringUtil.isNull(pageNo)){
            pageNo = "1";
        }
        if(StringUtil.isNull(pageSize)){
            pageSize = "10";
        }
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        Page<Member> page = new Page<Member>();
        ModelAndView mv = null;
       try{
        if((StringUtil.isNull(m.getSpare1())&&StringUtil.isNull(m.getSpare2()))||!StringUtil.isNull(m.getSpare2())){
        	page= (Page<Member>) impl.queryIntegrationCountListByClass(m);
        	mv = new ModelAndView("forward:/view/member/integCountListByClass.jsp");//按班级排
        }else if(!StringUtil.isNull(m.getSpare1())&&"".equals(m.getSpare2())){
        	page= (Page<Member>) impl.queryIntegrationCountListBySchool(m);
        	mv = new ModelAndView("forward:/view/member/integCountListBySchool.jsp");//按班级排
        }
        }catch (Exception e){
            logger.error("积分统计列表查询出错：",e);
        }
        mv.addObject("pagesBt",page2PageBt(page));
        mv.addObject("list",page.getResult());
        mv.addObject("member",m);
        mv.addObject("ac",ac);
        return mv;
    }
    /**
     * 导出按积分统计
     *
     * @param orderInfo
     * @param response
     */
    @RequestMapping("/expExcelBySchool")
    public void expExcelBySchool(Member m, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder("(");
        List jfList =new ArrayList();
        try {
            jfList=  impl.queryIntegrationCountListBySchool(m);
            response.setContentType("application/octet-stream");
            String name = "家具生产安全";
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1") + ".xls");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("按企业积分统计列表");
            HSSFRow row = sheet.createRow((int) 0);
            String[] header = {"序号", "企业", "积分"};
            HSSFCellStyle style = wb.createCellStyle();
            HSSFCellStyle style2 = wb.createCellStyle();
            // 设置居中样式
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            // 添加表格头
            for (int i = 0; i < header.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(style);
            }
            row = sheet.createRow((int) 1); // 第二行
            String curVal = "";
            for (int i = 0; i < jfList.size(); i++) {
            	Member temp = (Member) jfList.get(i);
                row = sheet.createRow(i + 1);
                row.setHeight((short) 500);
                HSSFCell cell = row.createCell(0);
                cell.setCellStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(i+1);
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(temp.getSchoolId());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue(temp.getIntegration());
            }
            sheet.autoSizeColumn((short) 0); // 调整第一列宽度
            sheet.autoSizeColumn((short) 1); // 调整第二列宽度
            sheet.autoSizeColumn((short) 2); // 调整第三列宽度
            
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("获取积分统计出错：", e);
        }
    }
    /**
     * 导出按积分统计
     *
     * @param orderInfo
     * @param response
     */
    @RequestMapping("/expExcelByClass")
    public void expExcelByClass(Member m, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder("(");
        List jfList =new ArrayList();
        try {
            jfList=  impl.queryIntegrationCountListByClass(m);
            response.setContentType("application/octet-stream");
            String name = "家具生产安全";
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1") + ".xls");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("按学校积分统计列表");
            HSSFRow row = sheet.createRow((int) 0);
            String[] header = {"序号","企业","积分"};
            HSSFCellStyle style = wb.createCellStyle();
            HSSFCellStyle style2 = wb.createCellStyle();
            // 设置居中样式
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            // 添加表格头
            for (int i = 0; i < header.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(style);
            }
            row = sheet.createRow((int) 1); // 第二行
            String curVal = "";
            for (int i = 0; i < jfList.size(); i++) {
            	Member temp = (Member) jfList.get(i);
                row = sheet.createRow(i + 1);
                row.setHeight((short) 500);
                HSSFCell cell = row.createCell(0);
                cell.setCellStyle(style);
                cell = row.createCell(0);
                cell.setCellValue(i+1);
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(temp.getClassId());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue(temp.getIntegration());
            }
            sheet.autoSizeColumn((short) 0); // 调整第一列宽度
            sheet.autoSizeColumn((short) 1); // 调整第二列宽度
            sheet.autoSizeColumn((short) 2); // 调整第三列宽度
            
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("获取积分统计出错：", e);
        }
    }
    /**
     * 调整会员状态
     * @param m
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeType")
    public BaseJson changeType(Member m){
        BaseJson json = new BaseJson();
        String type = m.getType();
        Member member = new Member();
        member.setId(m.getId());
        try {
            member = impl.queryMemberList(member).get(0);
            //原会员为付费会员，现在调整为普通会员
            if(type.equals("0")){
            	member.setType(type);
            	member.setLastRechargeTime(null);
            	//设置到期时间
            	member.setExpireTime(new Date());
            }
          //原会员为普通会员，现在调整为付费会员
            if(type.equals("2")){
            	member.setType(type);
            	member.setLastRechargeTime(new Date());
            	member.setExpireTime(DateUtil.forwordMonth(new Date(),12));
            }
            impl.updateMember(member);
            json.setStatus(200);
        }catch (Exception e){
            json.setStatus(500);
        }
        return json;
    }
  //删除信息
  	@ResponseBody
  	@RequestMapping("/remove")  
  	public BaseJson remove(String memberId){
  		
  		try {
  			if(StringUtil.isNull(memberId)){ 
  				return new BaseJson(600,"参数无效","");
  			}else{
  				//删除评论
  				commentService.deleteCommentByCreateUserId(memberId);
  				//删除积分日志
  				integrationService.deleteIntegrationMember(memberId);
  				//删除会员信息
  				impl.delteteMember(memberId);
  				//记录操作日志
  				logService.saveLog(new Log(StringUtil.getUUIDString(),"删除会员","会员管理",getUserSession().userId,getUserSession().userName));
  				return new BaseJson(1,"删除成功!","");
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  			return new BaseJson(600,"参数无效","");
  		}
  		
  	}
    /**
     * 修改会员账号是否有效
     * @param m
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeIsVaild")
    public BaseJson changeIsVaild(Member m){
        String isVaild = m.getIsVaild();
        Member member = new Member();
        try {
            m.setIsVaild("");
            member = impl.queryMemberList(m).get(0);
            member.setIsVaild(isVaild);
            impl.updateMember(member);
        }catch (Exception e){
            logger.error("查询会员或修改出错：",e);
            return new BaseJson(500,"查询会员或修改出错!","");
        }
        Log log;
        if(("1".equals(isVaild))){
            log = new Log(StringUtil.getUUIDString(),"对会员【"+member.getUserName()+"】账号进行解冻","会员管理",getUserSession().userId,getUserSession().userName);
        }else {
            log = new Log(StringUtil.getUUIDString(),"对会员【"+member.getUserName()+"】账号进行冻结","会员管理",getUserSession().userId,getUserSession().userName);
        }
        try {
            logService.saveLog(log);
        }catch (Exception e){
            logger.error("保存日志出错：",e);
        }
         return new BaseJson(200,"变更状态成功!","");
    }


    private void entryptPassword(Member m) {
        byte[] salt = Digests.generateSalt(BaseConstant.SALT_SIZE);
        m.setSalt(Encodes.encodeHex(salt));
        byte[] hashPassword = Digests.sha1(m.getPassword().getBytes(), salt, BaseRealm.HASH_INTERATIONS);
        m.setPassword(Encodes.encodeHex(hashPassword));
    }
    //网页重置密码  重置后为123456
    @ResponseBody
    @RequestMapping("/resetPassWeb")
    public BaseJson resetPassWeb(String phoneNum){
        BaseJson json = new BaseJson();
        try{
            Member member =  impl.queryMemberByName(phoneNum);
            if(member == null){   //不存在
                json.setStatus(501);
                json.setMessage("用户不存在！");
                return json;
            }
                member.setPassword("123456");
                entryptPassword(member);
                impl.updateMember(member);
                json.setStatus(200);
                json.setMessage("重置后的密码为：123456！");
        }catch (Exception e){
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统错误！");
        }
        return json;
    }

    /******************************************************************客户端接口**************************************************************/
  //忘记密码且换手机号
    @ResponseBody
    @RequestMapping("/msForgivePass")
    public BaseJson msForgivePass(String email){
        BaseJson json = new BaseJson();
        Member member =  new Member();
        try{
        	List memberList=impl.queryMemberByEmail(email);
        	if(memberList==null||memberList.size()==0){
        		 json.setStatus(501);
                 json.setMessage("该邮箱尚未注册！");
                 return json;
        	}else{
        		if(memberList.size()==1){
        			member=(Member) memberList.get(0);
            		//发邮件
            		//生成随机数，加密修改完密码，将生成后的密码发给用户邮箱
            		 Random r = new Random();
                     String newPass = (r.nextInt(899999) + 100000) + "";
                     logger.info("newPass"+newPass);
                     member.setPassword(newPass);
                     entryptPassword(member);
                     impl.updateMember(member);
                     //SendMail.sendTextMail("fuyun1403589085@163.com","fuyun102125",member.getEmail(),newPass);
                     SendMail.sendTextMail("ntsccm@163.com","asdf8899",member.getEmail(),newPass);
                     json.setStatus(200);
                     json.setMessage("密码修改成功，请查看邮件！");
        		}else{
        			 json.setStatus(501);
                     json.setMessage("账号异常，请联系管理员！");
                     return json;
        		}
        		
        	}
          
        }catch (Exception e){
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统错误！");
            return json;
        }

        return json;
    }
    /**
     * 找回密码发送短信验证码
     * @param phoneNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/msSendSMS")
    public BaseJson msSendSMS(String phoneNum){
        BaseJson json = new BaseJson();
        try{
            Member member =  impl.queryMemberByName(phoneNum);
            if(member == null){   //不存在
                json.setStatus(501);
                json.setMessage("用户不存在！");
                return json;
            }
        }catch (Exception e){
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统错误！");
            return json;
        }
        try {
            Captcha c = impl.queryCaptcha("captcha2:"+phoneNum);  //根据手机号码获取验证码
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
                            json.setMessage("超过限制次数！");
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
                impl.updateCaptcha(c);  //修改记录
            }else{  //没有该号码记录
                send(phoneNum,captcha);
                c = new Captcha();
                c.setTodayCount(1);
                c.setCaptcha(captcha);
                c.setPhoneNum("captcha2:"+phoneNum);
                impl.saveCaptcha(c);  //新增记录
            }
            json.setStatus(200);

        }catch (Exception e){
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统错误！");
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
        String msg = "【"+BaseConstant.PRE_SMS+"】您的短信验证码是"+captcha+"，5分钟内有效。您正在使用该手机号码找回密码，如非本人操作，请忽略该短信。";
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
    /**
     * 重置密码
     * @param phoneNum
     * @param pass
     * @return
     */
    @ResponseBody
    @RequestMapping("/msResetPass")
    public BaseJson msResetPass(String phoneNum,String pass,String captcha){
        BaseJson json = new BaseJson();
        try{
            Member member =  impl.queryMemberByName(phoneNum);
            if(member == null){   //不存在
                json.setStatus(501);
                json.setMessage("用户不存在！");
                return json;
            }
            Captcha c = impl.queryCaptcha("captcha2:"+member.getPhoneNum());
            if(c==null){
                json.setStatus(500);
                json.setMessage("请先请求验证码！");
                return json;
            }
            String captcha2 = c.getCaptcha();
            if(System.currentTimeMillis() - c.getUpdateTime().getTime() > 1000 * 60 * 5){  //验证码超时
                json.setStatus(500);
                json.setMessage("请先请求验证码！");
                return json;
            }
            if(!captcha.equals(captcha2)){   //验证码错误
                json.setStatus(502);
                json.setMessage("验证码错误！");
                return json;
            }
                member.setPassword(pass);
                entryptPassword(member);
                impl.updateMember(member);
                json.setStatus(200);

        }catch (Exception e){
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统错误！");
        }
        return json;
    }

    /**
     * 修改密码
     * @param phoneNum
     * @param pass
     * @param newPass
     * @return
     */
    @ResponseBody
    @RequestMapping("/msChangePass")
    public BaseJson msChangePass(String phoneNum,String pass,String newPass){
        BaseJson json = new BaseJson();
        try{
            Member member =  impl.queryMemberByName(phoneNum);
            if(member == null){   //不存在
                json.setStatus(501);
                return json;
            }
            byte[] salt = Encodes.decodeHex(member.getSalt());
            if(member.getPassword().equals(Encodes.encodeHex(Digests.sha1(pass.getBytes(), salt, BaseRealm.HASH_INTERATIONS)))){
                member.setPassword(newPass);
                entryptPassword(member);
                impl.updateMember(member);
                json.setStatus(200);
            }else{
                json.setStatus(502);  //密码错误
            }
        }catch (Exception e){
                json.setStatus(500);
        }
        return json;
    }
    
    
//发送邮箱测试
    @ResponseBody
    @RequestMapping("/sendEmail")
    public BaseJson sendEmail(){
    	BaseJson json=new BaseJson();
       if("".equals("")){
    	   logger.info("success");
       }else{
    	   logger.info("false");
       }
        json.setStatus(200);
        return json;
    }
    /**
     * 完善会员信息
     * @param m
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/msCompleteMember")
    public BaseJson msCompleteMember(Member m, MultipartFile file,HttpServletRequest request,String captcha){
        String realPath = request.getSession().getServletContext().getRealPath(BaseConstant.AVATAR_PATH);
        String fileName = m.getPhoneNum() + System.currentTimeMillis();
        String memberId=getUserSession().userId;
        String phone=getUserSession().userPhone;
        
        BaseJson json = new BaseJson();
        Member  tempMem = null;
        //根据手机号查询个人信息
        try {
			tempMem = impl.queryMemberByName(phone);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        String email=tempMem.getEmail();
        if(StringUtil.isNull(email)){
        	email="";
        }
        if(StringUtil.isNull(m.getEmail())){
        	m.setEmail("");
        }
        try {
        	if(phone.equals(m.getPhoneNum())&&m.getEmail().equals(email)){
        		
        		
        	}else{
        		//判断手机号唯一性
        		if(!phone.equals(m.getPhoneNum())){
        			Member tempMem1=impl.queryMemberByName(m.getPhoneNum());
        			if(tempMem1!=null){
        				 json.setStatus(500);
         	            json.setMessage("该手机号已注册！");
         	            return json;
        			}
        		}
        		if(!StringUtil.isNull(m.getEmail())&&!StringUtil.isNull(email)&&!email.equals(m.getEmail())){
        			List memberList=impl.queryMemberByEmail(m.getEmail());
        			if(memberList!=null&&memberList.size()>0){
        				 json.setStatus(500);
         	            json.setMessage("该邮箱已被使用！");
         	            return json;
        			}
        		}
        		 Captcha c = impl.queryCaptcha(m.getPhoneNum());
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
        	}
       
       
            Member member = impl.queryMemberById(memberId);
            if (file!=null&&!file.isEmpty()){
                String fn = file.getOriginalFilename();
                fileName = fileName + fn.substring(fn.lastIndexOf("."));
                File f = new File(realPath,fileName);
                FileUtils.copyInputStreamToFile(file.getInputStream(),f);
                member.setAvatarPath(BaseConstant.AVATAR_PATH+"/"+fileName);
            }else{
                fileName = "ic_avatar_default.png";
            }
           
            member.setHasCompleted("2");
            if(!StringUtil.isNull(m.getUserName())&&!("999").equals(m.getUserName())){
                member.setUserName(m.getUserName());
            }
            if(!StringUtil.isNull(m.getPhoneNum())){
                member.setPhoneNum(m.getPhoneNum());
            }
            if(!StringUtil.isNull(m.getEmail())){
                member.setEmail(m.getEmail());
            }
            if(!StringUtil.isNull(m.getIsStudent())){
            	if(m.getIsStudent().equals("1")&&m.getIsStudent().equals("0")){
            		member.setIsCompleteStudent("0");
            		member.setOrgId1("");
            		member.setOrgId2("");
            		member.setOrgId3("");
            		member.setSchoolId("");
            		member.setClassId("");
            	}
                member.setIsStudent(m.getIsStudent());
            }
            	  impl.updateMember(member);
                  json.setStatus(200);
                  json.setJson(member);
          
        }catch (Exception e){
            logger.info("完善用户资料出错！");
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统内部异常");
        }
        return json;
    }
    /**
     * 完善学生信息
     * @param m
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/msCompleteStudent")
    public BaseJson msCompleteStudent(Member m){
    	 BaseJson json = new BaseJson();
        try {
            Member member = impl.queryMemberById(m.getId());
            if(!StringUtil.isNull(m.getOrgId1())){
                member.setOrgId1(m.getOrgId1());
            }
            if(!StringUtil.isNull(m.getOrgId2())){
                member.setOrgId2(m.getOrgId2());
            }
            if(!StringUtil.isNull(m.getOrgId3())){
                member.setOrgId3(m.getOrgId3());
            }
            if(!StringUtil.isNull(m.getSchoolId())){
                member.setSchoolId(m.getSchoolId());
            }
            if(!StringUtil.isNull(m.getClassId())){
                member.setClassId(m.getClassId());
            }
            if(!StringUtil.isNull(m.getIsCompleteStudent())){
                member.setIsCompleteStudent(m.getIsCompleteStudent());
            }
            if(!StringUtil.isNull(m.getUserNameCn())){
                member.setUserNameCn(m.getUserNameCn());
            }
            	  impl.updateMember(member);
                  json.setStatus(200);
                  json.setJson(member);
          
        }catch (Exception e){
            logger.info("完善个人资料出错！");
            e.printStackTrace();
            json.setStatus(500);
            json.setMessage("系统内部异常");
        }
        return json;
    }
    private String  list2TreeJson(List<Org> orgList,String searchId,String orgId,boolean urlUse,boolean selfChecked){
		List<Tree> treeList = new ArrayList<Tree>(); 
		for(Org org:orgList){
			 Tree tree = new Tree();
			 tree.setId(org.getOrgId());
			 tree.setName(org.getOrgName());
			 tree.setpId(org.getParentOrgId());
			 if(urlUse){
			 } 
			 if(!selfChecked){
				 if(org.getOrgId().equals(orgId)){ 
				    tree.setChkDisabled(selfChecked);	 
				 }
			 }
			 tree.setTarget("_self");
			 tree.setClick("");
			 tree.setChecked(org.getOrgId().equals(searchId)?true:false);
			 tree.setOpen(org.getOrgId().equals(searchId)?true:false);
			 treeList.add(tree);
	    } 
	    //转换成json
		return  JSON.toJSONString(treeList).replace("\"checked\"", "checked").replace("\"open\"", "open");
	}

}
