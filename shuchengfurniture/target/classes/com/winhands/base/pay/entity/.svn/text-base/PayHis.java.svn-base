package com.winhands.base.pay.entity;

import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_pay_his", schema = "public")
public class PayHis implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2044972589007876445L;
	@Id
	@Column(name = "id")
	private String id;//主键
	@Column(name = "trade_no")
	private String trade_no;//订单号
	@Column(name = "pay_p")
	private String pay_p;//交易平台 
	@Column(name = "transaction_id")
	private String transaction_id;//交易号
	@Column(name = "contenx")
	private String contenx;//交易报文
	@Column(name = "uptime")
	private Date uptime;//收到时间
	@Column(name = "paytime")
	private Date paytime;//交易时间
	@Column(name = "result")
	private String result;//结果 1.成功 2.失败
	@Column(name = "trdresult")
	private String trdresult;//同步第三方支付状态
	@Column(name = "total_amount")
	private String total_amount;//订单金额
	@Column(name = "receipt_amount")
	private String receipt_amount;//实收金额
	@Column(name = "fund_bill_list")
	private String fund_bill_list;//金额来源信息
	@Column(name = "msg")
	private String msg;
	@Column(name = "order_no")
	private String order_no;//套餐id
	@Column(name = "expire_time")
	private Date expire_time;//充值到期时间 
	@Column(name = "member_id")
	private String member_id;//会员id

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getPay_p() {
		return pay_p;
	}

	public void setPay_p(String pay_p) {
		this.pay_p = pay_p;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getContenx() {
		return contenx;
	}

	public void setContenx(String contenx) {
		this.contenx = contenx;
	}

	

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
 

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public String getTrdresult() {
		return trdresult;
	}

	public void setTrdresult(String trdresult) {
		this.trdresult = trdresult;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getReceipt_amount() {
		return receipt_amount;
	}

	public void setReceipt_amount(String receipt_amount) {
		this.receipt_amount = receipt_amount;
	}

	public String getFund_bill_list() {
		return fund_bill_list;
	}

	public void setFund_bill_list(String fund_bill_list) {
		this.fund_bill_list = fund_bill_list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Date getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(Date expire_time) {
		this.expire_time = expire_time;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	

}
