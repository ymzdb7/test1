package com.winhands.base.order.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.BaseJson;
import com.winhands.base.util.DateUtil;
import com.winhands.base.util.MD5;
import com.winhands.base.web.BaseController;
import com.winhands.cshj.fee.entity.MemberShipFee;
import com.winhands.cshj.fee.service.FeeService;

@Component
@RequestMapping("/order")
public class OrderController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
    private FeeService impl;
	
	//购买会员  支付宝
	@ResponseBody
	@RequestMapping("/buy")  
	public BaseJson buyMember(String memberType){
		//根据会员类型，购买会员。
		//查询当期会员价格
		//生成订单号
		List<MemberShipFee> list = new ArrayList<MemberShipFee>();
		MemberShipFee msf = new  MemberShipFee(); 
        try{
        	msf.setType(memberType);
            list = impl.queryInUseFeeList(msf); 
            msf =  list.get(0); 
            String orderNum = DateUtil.format(new Date(), "YYYYMMDD")+impl.queryOrderNum();
            Object  json =  JSON.parse("{\"fee\":\""+msf.getFee()+"\",\"orderNum\":\""+orderNum+"\"}");// ();
            return new BaseJson(200, "订单号、费率查询成功", "ok", json); 
        }catch (Exception e){
            logger.error("获取使用中会费标准、订单号出错：",e); 
            return new BaseJson(500, "订单号、费率查询失败", "ok", "");
        } 
	}
	//微信获取订单号
	@ResponseBody
	@RequestMapping("/buyWx")  
	public BaseJson buyMemberWx(HttpServletRequest request,String member_id,String fee_id,String device_type){
		//根据会员类型，购买会员。
		//查询当期会员价格
		//生成订单号
		List<MemberShipFee> list = new ArrayList<MemberShipFee>();
		MemberShipFee msf = new  MemberShipFee(); 
        try{
        	msf.setId(fee_id);
        	msf = impl.queryFeeById(msf); 
            String orderNum = DateUtil.format(new Date(), "YYYYMMDD")+impl.queryOrderNum();
            //统一下单
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid",BaseConstant.APP_ID));
            packageParams.add(new BasicNameValuePair("attach",member_id+","+fee_id));//存放会员id和套餐id
            packageParams.add(new BasicNameValuePair("body","舒晨文化传播-"+orderNum));
            packageParams.add(new BasicNameValuePair("mch_id",BaseConstant.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str",genNonceStr()));
            packageParams.add(new BasicNameValuePair("notify_url","http://118.178.95.211:8080/shucheng/wxPay/syncStatus"));
            packageParams.add(new BasicNameValuePair("out_trade_no",orderNum));
            packageParams.add(new BasicNameValuePair("spbill_create_ip","118.178.95.211"));
            packageParams.add(new BasicNameValuePair("total_fee", (new   Double(msf.getFee()*100)).intValue()+""));
            packageParams.add(new BasicNameValuePair("trade_type","APP"));
            String sign="";
            sign=	genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign",sign));
            String xmlstring = toXml(packageParams);
//            xmlstring=  new String(xmlstring.toString().getBytes(), "ISO8859-1");
         // 构造HTTP请求
            String conetent =httpsRequest(  
                    "https://api.mch.weixin.qq.com/pay/unifiedorder", "POST",  
                    xmlstring );  
            JSONObject preObj = doXMLParse(conetent); 
            String prepay_id = preObj.optString("prepay_id");
          //获取app签名
            String nonceStr=genNonceStr();
            String timeStamp=String.valueOf(genTimeStamp());
             List<NameValuePair> signParams = new LinkedList<NameValuePair>();
     		signParams.add(new BasicNameValuePair("appid", BaseConstant.APP_ID));
     		signParams.add(new BasicNameValuePair("noncestr", nonceStr));
     		signParams.add(new BasicNameValuePair("package", "Sign=WXPay"));
     		signParams.add(new BasicNameValuePair("partnerid", BaseConstant.MCH_ID));
     		signParams.add(new BasicNameValuePair("prepayid", prepay_id));
     		signParams.add(new BasicNameValuePair("timestamp",timeStamp));
     		String appSign = genAppSign(signParams);
     		
     		JSONObject result = new JSONObject();
     		result.put("preInfo",preObj);
     		result.put("packageSign", sign);
     		result.put("appSign", appSign);
     		result.put("orderNum", orderNum);
     		result.put("nonceStr", nonceStr);
     		result.put("timestamp",timeStamp);
     		result.put("fee", msf.getFee());
            return new BaseJson(200, "订单号、费率查询成功", "ok", result); 
        }catch (Exception e){
            logger.error("获取使用中会费标准、订单号出错：",e); 
            return new BaseJson(500, "订单号、费率查询失败", "ok", "");
        } 
	}
	//获取app签名
	private String genAppSign(List<NameValuePair> params) {
	StringBuilder sb = new StringBuilder();

	for (int i = 0; i < params.size(); i++) {
		sb.append(params.get(i).getName());
		sb.append('=');
		sb.append(params.get(i).getValue());
		sb.append('&');
	}
	sb.append("key=");
	sb.append(BaseConstant.API_KEY);
	//sb.append("sign str\n" + sb.toString() + "\n\n"); 
	String appSign = MD5.getMessageDigest(sb.toString().getBytes())
			.toUpperCase(Locale.getDefault());
	return appSign;
}

	/**
	 * 生成签名
	 */
	private String genPackageSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(BaseConstant.API_KEY);

		String packageSign;
		try {
			packageSign = MD5.getMessageDigest(sb.toString().getBytes("UTF-8"))
			.toUpperCase(Locale.getDefault());
			return packageSign;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	private String genNonceStr() {
		Random random = new Random();
		return MD5.byteToString(String.valueOf(random.nextInt(10000))
				.getBytes());
	}
	private String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");
			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");

		return sb.toString();
//		try {
//			return new String(sb.toString().getBytes(), "ISO8859-1");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return "";
//		}
	}
    //请求方法  
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {  
          try {  
               
              URL url = new URL(requestUrl);  
              HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
              
              conn.setDoOutput(true);  
              conn.setDoInput(true);  
              conn.setUseCaches(false);  
              // 设置请求方式（GET/POST）  
              conn.setRequestMethod(requestMethod);  
              conn.setRequestProperty("content-type", "application/json");  
              // 当outputStr不为null时向输出流写数据  
              if (null != outputStr) {  
                  OutputStream outputStream = conn.getOutputStream();  
                  // 注意编码格式  
                  outputStream.write(outputStr.getBytes("UTF-8"));  
                  outputStream.close();  
              }  
              // 从输入流读取返回内容  
              InputStream inputStream = conn.getInputStream();  
              InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
              BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
              String str = null;  
              StringBuffer buffer = new StringBuffer();  
              while ((str = bufferedReader.readLine()) != null) {  
                  buffer.append(str);  
              }  
              // 释放资源  
              bufferedReader.close();  
              inputStreamReader.close();  
              inputStream.close();  
              inputStream = null;  
              conn.disconnect();  
              return buffer.toString();  
          } catch (ConnectException ce) {  
              System.out.println("连接超时：{}"+ ce);  
          } catch (Exception e) {  
              System.out.println("https请求异常：{}"+ e);  
          }  
          return null;  
      }
    //xml解析  
    public static JSONObject doXMLParse(String strxml) throws Exception {  
          strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");  

          if(null == strxml || "".equals(strxml)) {  
              return null;  
          }  
            
          JSONObject result = new JSONObject();
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
    private long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

}
