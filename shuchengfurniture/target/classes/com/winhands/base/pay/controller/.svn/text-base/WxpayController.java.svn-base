package com.winhands.base.pay.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winhands.base.pay.entity.PayHis;
import com.winhands.base.pay.service.PayService;
import com.winhands.base.pay.util.DESUtilPlus;
import com.winhands.base.pay.util.Dom4jUtil;
import com.winhands.base.pay.util.WxNotify;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.StringUtil;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.fee.entity.MemberShipFee;
import com.winhands.cshj.fee.service.FeeService;
import com.winhands.cshj.member.entity.Member;
import com.winhands.cshj.member.service.MemberService;
/**
 * 微信支付对外接口
 * @author lei
 *
 */
@Component
@RequestMapping("/wxPay")
public class WxpayController extends BaseController {

	/**
	 * 
	 */
	private static final  Logger logger = LoggerFactory.getLogger(WxpayController.class);  
	@Autowired
	private PayService payService;
	@Autowired
    private FeeService feeService;
	@Autowired
	private MemberService memberService;
	@ResponseBody
	@RequestMapping("/syncStatus")  
	public String syncStatus(HttpServletRequest request,HttpServletResponse response){   
		try {   
            StringBuffer xml = new StringBuffer();
			String line = null;
			String xmlStr = "";
			String order_No="";
			String member_id="";
			try {
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null) {
					xml.append(line);
				}
				//正式环境
				xmlStr = xml.toString();
				Map xmlMap=doXMLParse(xmlStr); 
				String attach=(String) xmlMap.get("attach");//获取会员号和套餐id
				System.out.println(attach);
				String []temStr=attach.split(",");
				logger.info("xmlStr:"+xmlStr);
				logger.info("attach:"+attach);
				order_No=temStr[1];//套餐id
				member_id=temStr[0];;//会员id
				//测试环境
				//xmlStr="<xml><appid><![CDATA[wxbfb1f1e926c50609]]></appid><attach><![CDATA[5567129478559455365,6417617003199798837]]></attach><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[1396419202]]></mch_id><nonce_str><![CDATA[37373333]]></nonce_str><openid><![CDATA[ogUQ_xGWZe9oucVEcYGjZda9elHA]]></openid><out_trade_no><![CDATA[20161029112400]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B28DC7FB88BCBC0CD48CA115946964AB]]></sign><time_end><![CDATA[20161017140506]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[APP]]></trade_type><transaction_id><![CDATA[4008882001201610176930588477]]></transaction_id></xml>";
				
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error("系统异常", e.getMessage());
				logger.info("读取xml数据异常", e.toString());
			} 
		    Map<String,String> requestParams =  Dom4jUtil.parseXMLMap(xmlStr);
		    //检查回调信息是否正常
		    boolean payResult = WxNotify.verify(requestParams);
		    logger.info("payResult:", payResult);
			if(payResult){
				logger.info("订单号:", order_No);
				logger.info("会员id:", member_id);
				//正确 开始业务逻辑处理
				//保存支付历史
				String paymentDate = null;
				if(requestParams.get("time_end")!=null){
					 paymentDate = DateUtil.format(DateUtil.parse((String)requestParams.get("time_end"), "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
				} 
				PayHis payHis = new PayHis();
				payHis.setId(StringUtil.getUUIDString());
				payHis.setPay_p(BaseConstant.wechat);
				payHis.setContenx(xmlStr);
				payHis.setTrade_no((String)requestParams.get("out_trade_no"));
				payHis.setReceipt_amount((String)requestParams.get("cash_fee"));//实收金额  单位分
				payHis.setMember_id(member_id);
				//套餐编号
				payHis.setOrder_no((String)requestParams.get("order_no"));
				//查询该用户的原始会员到期时间
				String expireTimeStr=(String)requestParams.get("expire_time");
				
				//根据会员id获取会员对象
				List <Member>menberList=new ArrayList<Member>();
				Member tepMem=new Member();
				tepMem.setId(member_id);
				menberList=memberService.queryMemberList(tepMem);
				tepMem=menberList.get(0);
				Date oriDate=new Date();
				//获取改会员原始到期时间
				if(tepMem.getExpireTime()==null){
				}else{
					oriDate=tepMem.getExpireTime();
				}
				
				//根据套餐编号查询套餐天数
				MemberShipFee msf=new MemberShipFee ();
				msf.setId(order_No);
				msf=feeService.queryFeeById(msf);
				//获取type值  1是半年，2是年
				tepMem.setType(msf.getType());
				if(msf.getType().equals("1")){
					oriDate=DateUtil.forwordMonth(oriDate,6);
				}else if(msf.getType().equals("2")){
					oriDate=DateUtil.forwordMonth(oriDate,12);
				}
				//支付成功后根据选择的套餐，将天数添加到到期时;间上
				tepMem.setExpireTime(oriDate);
				payHis.setExpire_time(oriDate);
				payHis.setTransaction_id((String)requestParams.get("transaction_id"));
				payHis.setUptime(new Date());  
				String paymentStatus = "SUCCESS".equals((String)requestParams.get("result_code"))?"PAID":"UNPAID";
				StringBuffer paramBuffer = new StringBuffer();
				paramBuffer.append("orderCode=").append(URLEncoder.encode((String)requestParams.get("out_trade_no"),"utf-8"))
				           .append("&paymentStatus=").append(paymentStatus)
				           .append("&paymentNumber=").append(URLEncoder.encode((String)requestParams.get("transaction_id"),"utf-8"))
				           .append("&paymentDate=").append(URLEncoder.encode(paymentDate,"utf-8"))
				           .append("&paymentModeId=132&paymentAmount=").append(Double.valueOf((String)requestParams.get("total_fee"))/100)//分转元
				           .append("&orderKey=").append(URLEncoder.encode(DESUtilPlus.encrypt((String)requestParams.get("out_trade_no")),"utf-8"));
				payService.save(payHis);
				//更新会员表信息
				tepMem.setLastRechargeTime(new Date());
				memberService.updateMember(tepMem);
				logger.info("his表到期时间:", payHis.getExpire_time());
				logger.info("会员id和到期时间:",tepMem.getId()+";"+ tepMem.getExpireTime());
				return  "<xml> <return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";
				   
			}else{
				return "<xml> <return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[校验失败]]></return_msg> </xml>";
			}
		    
		} catch (Exception e) {
			logger.error("微信支付状态异常!");
			e.printStackTrace();
			return "<xml> <return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[系统异常]]></return_msg> </xml>";
		}
		 
	}
	//xml解析  
    public static Map doXMLParse(String strxml) throws Exception {  
          strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");  

          if(null == strxml || "".equals(strxml)) {  
              return null;  
          }  
            
          Map result = new HashMap();
          InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));  
          SAXBuilder builder = new SAXBuilder();  
          Document doc = builder.build(in);  
          Element root = doc.getRootElement();  
          List list = root.getChildren();  
          Iterator it = list.iterator();  
          while(it.hasNext()) {  
              Element e = (Element) it.next();  
              String k = e.getName();  
              String v = "";  
              List children = e.getChildren();  
              if(children.isEmpty()) {  
                  v = e.getTextNormalize();  
              } else {  
                  v = getChildrenText(children);  
              }  
                
              result.put(k, v);  
          }  
            
          //关闭流  
          in.close();  
            
          return result;  
      } 
    public static String getChildrenText(List children) {  
        StringBuffer sb = new StringBuffer();  
        if(!children.isEmpty()) {  
            Iterator it = children.iterator();  
            while(it.hasNext()) {  
                Element e = (Element) it.next();  
                String name = e.getName();  
                String value = e.getTextNormalize();  
                List list = e.getChildren();  
                sb.append("<" + name + ">");  
                if(!list.isEmpty()) {  
                    sb.append(getChildrenText(list));  
                }  
                sb.append(value);  
                sb.append("</" + name + ">");  
            }  
        }  
          
        return sb.toString();  
    }
}
