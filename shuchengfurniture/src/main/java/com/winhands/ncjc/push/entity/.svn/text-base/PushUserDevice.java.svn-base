package com.winhands.ncjc.push.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 推送用户和渠道关系
 * @author wang
 *
 */
@Entity
@Table(name = "t_push_user_device", schema = "public")
public class PushUserDevice implements java.io.Serializable{
	private static final long serialVersionUID = 8938297393215749102L;
	@Id
	private PushUserDeviceBean pushUserDeviceBean;
	@Column(name = "appid")
	private String appid;//应用ID
	@Column(name = "channel_id")
	private String channel_id;// 渠道ID
	@Column(name = "create_time")
	private Date create_time;//注册时间
	@Column(name = "update_time")
	private Date update_time;//更新时间
	@Column(name = "device_type")
	private String device_type;//1: web 2: pc 3:android 4:ios 5:wp
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public PushUserDeviceBean getPushUserDeviceBean() {
		return pushUserDeviceBean;
	}
	public void setPushUserDeviceBean(PushUserDeviceBean pushUserDeviceBean) {
		this.pushUserDeviceBean = pushUserDeviceBean;
	}
	
	
}
