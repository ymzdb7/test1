package com.winhands.base.dict.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Dict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_dict", schema = "public")
public class Dict implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3277299761452623373L;
	@Id
	@Column(name="id", unique=true, nullable=false, length=32)
    private String id;
	@Column(name="dict_id", nullable=false, length=32)
    private String dictId;
	@Column(name="super_dict_id", nullable=false, length=32)
    private String superDictId; 
	@Column(name = "dict_name", length = 50)
	private String dictName;
	@Column(name = "dict_grade", length = 2)
	private String dictGrade;
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "create_people")
	private String createPeople;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date", length = 13)
	private Date createDate;
	@Column(name = "is_invalid", length = 1)
	private String isInvalid;

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictGrade() {
		return this.dictGrade;
	}

	public void setDictGrade(String dictGrade) {
		this.dictGrade = dictGrade;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCreatePeople() {
		return this.createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsInvalid() {
		return this.isInvalid;
	}

	public void setIsInvalid(String isInvalid) {
		this.isInvalid = isInvalid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getSuperDictId() {
		return superDictId;
	}

	public void setSuperDictId(String superDictId) {
		this.superDictId = superDictId;
	}

	
}