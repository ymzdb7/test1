package com.winhands.ncjc.push.entity;

import javax.persistence.Embeddable;



/**
 推送用户和渠道关系
 *
 */
@Embeddable
public class PushUserDeviceBean implements java.io.Serializable{
	private static final long serialVersionUID = -3304319243957837925L;
	private String user_id;
	private String bd_user_id;
	public PushUserDeviceBean() {
		super();
	}
	public PushUserDeviceBean(String user_id, String bd_user_id) {
		super();
		this.user_id = user_id;
		this.bd_user_id = bd_user_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBd_user_id() {
		return bd_user_id;
	}
	public void setBd_user_id(String bd_user_id) {
		this.bd_user_id = bd_user_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bd_user_id == null) ? 0 : bd_user_id.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PushUserDeviceBean other = (PushUserDeviceBean) obj;
		if (bd_user_id == null) {
			if (other.bd_user_id != null)
				return false;
		} else if (!bd_user_id.equals(other.bd_user_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
	
}
