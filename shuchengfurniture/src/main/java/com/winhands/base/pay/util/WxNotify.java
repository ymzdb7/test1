package com.winhands.base.pay.util;

import java.util.Map;

import com.winhands.base.util.BaseConstant;
import com.winhands.base.util.MD5;
 
 
/**
 * 微信支付通知检查
 * @author lei
 *
 */
public class WxNotify {

    private static final String success = "SUCCESS";

    /**
     * 验证消息是否是支付宝发出的合法消息
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params) {
    	//微信支付校验核心代码
        String responseTxt = "false";
        if(params.get("appid") == null||"".equals(params.get("appid"))) {
        	return false;
        }
		if(success.equals(params.get("return_code"))) { 
			responseTxt = "true";
		}else{
			//交易表示失败，无需校验。
			return false;
		}
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);
	    if (responseTxt.equals("true")&&isSign) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
		String appid  = Params.get("appid");
		String api_key = null;
    	//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
        //获取待签名字符串
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        String signStr = preSignStr +"&key="+BaseConstant.API_KEY; 
        //获得签名验证结果 
        String signValue =  MD5.GetMD5Code(signStr);
        return sign.equals(signValue.toUpperCase());
    }
 
}
