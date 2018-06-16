package com.winhands.base.role.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.winhands.base.org.entity.Org;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role", schema = "public")
public class Role implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 835484815760310754L;
	@Id
	@Column(name = "role_id", unique = true, nullable = false, length = 50)
	private String roleId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Org org;
	@Column(name = "role_name", length = 50)
	private String roleName;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", length = 13)
	private Date createTime;
	@Column(name = "create_user_id", length = 50)
	private String createUserId;
	@Column(name = "role_spare1", length = 100)
	private String roleSpare1;
	@Column(name = "role_spare2", length = 100)
	private String roleSpare2;
	@Column(name = "role_spare3", length = 100)
	private String roleSpare3;
	@Column(name = "role_spare4", length = 100)
	private String roleSpare4;

	
	// Property accessors
	
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}  
	
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	
	public String getRoleSpare1() {
		return this.roleSpare1;
	}

	public void setRoleSpare1(String roleSpare1) {
		this.roleSpare1 = roleSpare1;
	}

	
	public String getRoleSpare2() {
		return this.roleSpare2;
	}

	public void setRoleSpare2(String roleSpare2) {
		this.roleSpare2 = roleSpare2;
	}

	
	public String getRoleSpare3() {
		return this.roleSpare3;
	}

	public void setRoleSpare3(String roleSpare3) {
		this.roleSpare3 = roleSpare3;
	}

	
	public String getRoleSpare4() {
		return this.roleSpare4;
	}

	public void setRoleSpare4(String roleSpare4) {
		this.roleSpare4 = roleSpare4;
	}

}