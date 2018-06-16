/*   
 * Copyright (c) 2014 南京唯恒思 www.winhands.com. All Rights Reserved.   
 */
package com.winhands.ncjc.push.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * [工程名] push 
 * [类名] PushMsg
 * [描述] 
 * [推送消息]
 *  [History] Version Date Author Content
 * -------- --------- --------
 * 最初版本
 */
@Entity
@Table(name = "t_push_msg", schema = "public")
public class PushMsg implements java.io.Serializable{
	private static final long serialVersionUID = 6444255362558604179L;
	@Id
	@Column(name = "sn")
	private String sn;// 流水号
	@Column(name = "app_id")
	private String app_id;//应用ID
	@Column(name = "cmd")
	private String cmd;// 命令代码
	@Column(name = "time_stamp")
	private Date time_stamp;// 时间戳
	@Column(name = "from_id")
	private String from_id;// 来自用户
	@Column(name = "from_name")
	private String from_name;// 用户名
	@Column(name = "push_type")
	private String push_type;// 默认单用户推送 1.user 2.group
	@Column(name = "device_type")
    private String device_type;
	@Column(name = "group_ids")
	private String group_ids;// 组员ID
	@Column(name = "group_names")
	private String group_names;// 组员名称
	@Column(name = "msg_type")
	private String msg_type;// 信息类型
	@Column(name = "msg_text")
	private String msg_text;// 文本消息内容
	@Column(name = "msg_title")
	private String msg_title;// 文本消息标题
	@Column(name = "att_url")
	private String att_url;// 附件地址
	@Column(name = "att_name")
	private String att_name;// 附件名称
	@Column(name = "att_length")
	private String att_length;// 附件大小
	@Column(name = "msg_id")
	private String msg_id;
	@Column(name = "msg_status")
	private String msg_status;
	@Column(name = "is_read")
	private String is_read;
	@Column(name = "create_time")
	private Date create_time;
	@Column(name = "spare_1")
	private String spare_1;
	@Column(name = "spare_2")
	private String spare_2;
	@Column(name = "spare_3")
	private String spare_3;
	@Column(name = "spare_4")
	private String spare_4;
	@Column(name = "spare_5")
	private String spare_5; 


	public PushMsg() {

	}

	/**
	 * 
	 * <b>Summary: </b> 构造一个 PushMsg <b>Remarks: </b> 构造类 PushMsg 的构造函数 PushMsg
	 * 
	 * @param cmd
	 *            推送命令
	 * @param msg_type
	 *            消息类型
	 * @param msg_text
	 *            消息内容 Apr 9, 2014 [author]lei
	 */
	public PushMsg(String cmd, String msg_type, String msg_text) {
		this.cmd = cmd;
		this.msg_type = msg_type;
		this.msg_text = msg_text;
	}

	public PushMsg(String cmd, String msg_type, String msg_text,
			String att_url, String att_name, String att_length) {
		this.cmd = cmd;
		this.msg_type = msg_type;
		this.msg_text = msg_text;
		this.att_url = att_url;
		this.att_name = att_name;
		this.att_length = att_length;
	}
	
	public PushMsg(String sn, String app_id, Date time_stamp, String from_id,
			 String msg_type, String msg_text) {
		super();
		this.sn = sn;
		this.app_id = app_id;
		this.time_stamp = time_stamp;
		this.from_id = from_id;
		this.msg_type = msg_type;
		this.msg_text = msg_text;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	

	public Date getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Date time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public String getGroup_ids() {
		return group_ids;
	}

	public void setGroup_ids(String group_ids) {
		this.group_ids = group_ids;
	}

	public String getGroup_names() {
		return group_names;
	}

	public void setGroup_names(String group_names) {
		this.group_names = group_names;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getMsg_text() {
		return msg_text;
	}

	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}

	public String getAtt_url() {
		return att_url;
	}

	public void setAtt_url(String att_url) {
		this.att_url = att_url;
	}

	public String getAtt_name() {
		return att_name;
	}

	public void setAtt_name(String att_name) {
		this.att_name = att_name;
	}

	public String getAtt_length() {
		return att_length;
	}

	public void setAtt_length(String att_length) {
		this.att_length = att_length;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPush_type() {
		return push_type;
	}

	public void setPush_type(String push_type) {
		this.push_type = push_type;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(String msg_status) {
		this.msg_status = msg_status;
	}

	public String getIs_read() {
		return is_read;
	}

	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}

	 

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getSpare_1() {
		return spare_1;
	}

	public void setSpare_1(String spare_1) {
		this.spare_1 = spare_1;
	}

	public String getSpare_2() {
		return spare_2;
	}

	public void setSpare_2(String spare_2) {
		this.spare_2 = spare_2;
	}

	public String getSpare_3() {
		return spare_3;
	}

	public void setSpare_3(String spare_3) {
		this.spare_3 = spare_3;
	}

	public String getSpare_4() {
		return spare_4;
	}

	public void setSpare_4(String spare_4) {
		this.spare_4 = spare_4;
	}

	public String getSpare_5() {
		return spare_5;
	}

	public void setSpare_5(String spare_5) {
		this.spare_5 = spare_5;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	
	 

}
