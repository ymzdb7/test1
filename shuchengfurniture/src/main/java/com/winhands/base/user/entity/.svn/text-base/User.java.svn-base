package com.winhands.base.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_users", schema = "public")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6800874535551333832L;
	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 32)
	private String userId;
	@Column(name = "user_code", length = 32)
	private String userCode;
	@Column(name = "user_name", nullable = false, length = 100)
	private String userName;
	@Column(name = "user_name_cn", length = 100)
	private String userNameCn;
	@Column(name = "password", nullable = false, length = 50)
	private String password; 
	@Column(name = "salt", nullable = false, length = 50)
	private String salt;
	@Column(name = "user_op_time", length = 16)
	private String userOpTime;
	@Column(name = "user_build_id", length = 32)
	private String userBuildId;
	@Column(name = "user_destroy", length = 16)
	private String userDestroy;
	@Column(name = "user_destroy_date", length = 16)
	private String userDestroyDate;
	@Column(name = "password_update", length = 16)
	private String passwordUpdate;
	@Column(name = "user_type", length = 16)
	private String userType;
	@Column(name = "user_extend1", length = 100)
	private String userExtend1;
	@Column(name = "user_extend2", length = 100)
	private String userExtend2;
	@Column(name = "user_extend3", length = 100)
	private String userExtend3;
	@Column(name = "user_extend4", length = 100)
	private String userExtend4;
	@Column(name = "user_extend5", length = 100)
	private String userExtend5;
	@Column(name = "user_extend6", length = 100)
	private String userExtend6;
	@Column(name = "org_id", length = 32)
	private String orgId;
	@Column(name = "org_name", length = 100)
	private String orgName;
	@Column(name = "user_order", length = 32)
	private String userOrder;
	@Column(name = "parent_user_id", length = 32)
	private String parentUserId;
	@Column(name = "user_phone", length = 20)
	private String userPhone;
	@Column(name = "user_mail", length = 32)
	private String userMail;
	@Column(name = "org_id1", length = 32)
	private String  orgId1;
	@Column(name = "org_id2", length = 32)
	private String  orgId2;
	@Column(name = "org_id3", length = 32)
	private String  orgId3;

	@Column(name = "last_login_time")
	private Timestamp lastLoginTime;  //最后登录时间

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	// Property accessors
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getUserNameCn() {
		return this.userNameCn;
	}

	public void setUserNameCn(String userNameCn) {
		this.userNameCn = userNameCn;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getUserOpTime() {
		return this.userOpTime;
	}

	public void setUserOpTime(String userOpTime) {
		this.userOpTime = userOpTime;
	}

	
	public String getUserBuildId() {
		return this.userBuildId;
	}

	public void setUserBuildId(String userBuildId) {
		this.userBuildId = userBuildId;
	}

	
	public String getUserDestroy() {
		return this.userDestroy;
	}

	public void setUserDestroy(String userDestroy) {
		this.userDestroy = userDestroy;
	}

	
	public String getUserDestroyDate() {
		return this.userDestroyDate;
	}

	public void setUserDestroyDate(String userDestroyDate) {
		this.userDestroyDate = userDestroyDate;
	}

	
	public String getPasswordUpdate() {
		return this.passwordUpdate;
	}

	public void setPasswordUpdate(String passwordUpdate) {
		this.passwordUpdate = passwordUpdate;
	}

	
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	public String getUserExtend1() {
		return this.userExtend1;
	}

	public void setUserExtend1(String userExtend1) {
		this.userExtend1 = userExtend1;
	}

	 
	public String getUserExtend2() {
		return this.userExtend2;
	}

	public void setUserExtend2(String userExtend2) {
		this.userExtend2 = userExtend2;
	}

	public String getUserExtend3() {
		return this.userExtend3;
	}

	public void setUserExtend3(String userExtend3) {
		this.userExtend3 = userExtend3;
	}

	public String getUserExtend4() {
		return this.userExtend4;
	}

	public void setUserExtend4(String userExtend4) {
		this.userExtend4 = userExtend4;
	}

	public String getUserExtend5() {
		return this.userExtend5;
	}

	public void setUserExtend5(String userExtend5) {
		this.userExtend5 = userExtend5;
	}

	public String getUserExtend6() {
		return this.userExtend6;
	}

	public void setUserExtend6(String userExtend6) {
		this.userExtend6 = userExtend6;
	} 
	
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	public String getUserOrder() {
		return this.userOrder;
	}

	public void setUserOrder(String userOrder) {
		this.userOrder = userOrder;
	}

	
	public String getParentUserId() {
		return this.parentUserId;
	}

	public void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId;
	}

	
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	
	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getOrgId1() {
		return orgId1;
	}

	public void setOrgId1(String orgId1) {
		this.orgId1 = orgId1;
	}

	public String getOrgId2() {
		return orgId2;
	}

	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}

	public String getOrgId3() {
		return orgId3;
	}

	public void setOrgId3(String orgId3) {
		this.orgId3 = orgId3;
	} 
	
	
}