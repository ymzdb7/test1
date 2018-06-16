package com.winhands.base.pay.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winhands.base.pay.entity.PayHis;
import com.winhands.base.pay.service.PayService;
import com.winhands.base.pay.util.AlipayNotify;
import com.winhands.base.util.BaseConstant;
import com.winhands.base.web.BaseController;
@Component
@RequestMapping("/aliPay")
public class AliPayController extends BaseController<PayHis> {
	private final Logger logger = LoggerFactory.getLogger(AliPayController.class);
	@Autowired
	private PayService payService;
	//异步通知状态
	@ResponseBody
	@RequestMapping("/notify")  
	public String notify(HttpServletRequest request,HttpServletResponse response){   
		try { 
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map<String,String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			logger.info("支付宝请求参数:{}",params.toString()); 
			if(AlipayNotify.verify(params)){//验证成功
				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
				//商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8"); 
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8"); 
				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8"); 
				//订单金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8"); 
				//实收金额
				String receipt_amount = new String(request.getParameter("receipt_amount").getBytes("ISO-8859-1"),"UTF-8"); 
				//交易时间
				String gmt_payment = null;
				if(request.getParameter("gmt_payment")!=null){
					 gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");  
					 //gmt_payment = ;
				}else if(request.getParameter("gmt_create")!=null){
					 gmt_payment = new String(request.getParameter("gmt_create").getBytes("ISO-8859-1"),"UTF-8");
				}else{
					 gmt_payment= "";
				}  
				//金额来源 
				String fund_bill_list = new String(request.getParameter("fund_bill_list").getBytes("ISO-8859-1"),"UTF-8"); 
				String paymentStatus = null;
				if(trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")){
					  paymentStatus = "PAID";
				}else{
					  paymentStatus = "UNPAID";
				}	
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。 
				PayHis payHis = new PayHis();
				payHis.setPay_p(BaseConstant.alipay);
				payHis.setContenx(requestParams.toString());
				payHis.setTrade_no(out_trade_no);
				payHis.setTransaction_id(trade_no);
				payHis.setUptime(new Date());
				//payHis.setPaytime(gmt_payment);//交易时间
				payHis.setReceipt_amount(receipt_amount);//实收金额
				payHis.setTotal_amount(total_amount);//订单金额
				payHis.setFund_bill_list(fund_bill_list);//金额来源
				payHis.setTrdresult(trade_status);//第三方支付状态
				payHis.setResult(paymentStatus);//自有系统认定支付装填  
				//payService.savePayHis(payHis);
				return "success";
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			 }else{//验证失败 
				logger.info("支付宝验证失败 "); 
				return "fail";
			}  
		} catch (Exception e) {
			logger.error("支付宝状态异常!");
			e.printStackTrace();
			return "fail";
		} 
	}
}
