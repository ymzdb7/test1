package com.winhands.cshj.article.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_directory", schema = "public")
public class Directory implements java.io.Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2741188430902178057L;
	/**
	 * 目录表
	 */
	@Id
	@Column(name = "dir_id")
	private String dir_id;//目录id
	@Column(name = "dir_name")
	private String dir_name;//目录名
	@Column(name = "super_dir_id")
	private String super_dir_id;//父目录
	@Column(name = "dir_grade")
	private String dir_grade;//等级
	@Column(name = "order_id")
	private String order_id;//
	@Column(name = "create_user_id")
	private String create_user_id;//创建人id
	@Column(name = "create_user_name")
	private String create_user_name;//创建人名
	@Column(name = "create_date")
	private Date create_date;//创建时间
	@Column(name = "is_valid")
	private String is_valid;//是否可用
	@Column(name = "is_online")
	private String is_online;//是否上线
	@Column(name = "type")
	private String type;//用于抽出来进行管理的类别区分   0:目录   1：作者
	@Column(name = "start_date")
	private String start_date;//生效日期
	@Column(name = "end_date")
	private String end_date;//截止日期
	@Column(name = "summary")
	private String summary;//简介
	@Column(name = "spare_1")
	private String spare_1;
	@Column(name = "spare_2")
	private String spare_2;
	@Column(name = "spare_3")
	private String spare_3;
	public String getDir_id() {
		return dir_id;
	}
	public void setDir_id(String dir_id) {
		this.dir_id = dir_id;
	}
	public String getDir_name() {
		return dir_name;
	}
	public void setDir_name(String dir_name) {
		this.dir_name = dir_name;
	}
	public String getSuper_dir_id() {
		return super_dir_id;
	}
	public void setSuper_dir_id(String super_dir_id) {
		this.super_dir_id = super_dir_id;
	}
	public String getDir_grade() {
		return dir_grade;
	}
	public void setDir_grade(String dir_grade) {
		this.dir_grade = dir_grade;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getCreate_user_name() {
		return create_user_name;
	}
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public String getIs_online() {
		return is_online;
	}
	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
