package com.winhands.cshj.article.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_file_read_log", schema = "public")
public class FileReadLog implements java.io.Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2741188430902178057L;
	/**
	 * 目录表
	 */
	@Id
	@Column(name = "id")
	private String id;//记录id
	@Column(name = "file_id")
	private String file_id;//文章id
	@Column(name = "read_user_id")
	private String read_user_id;//阅读人id
	@Column(name = "read_user_name")
	private String read_user_name;//阅读人名
	@Column(name = "read_date")
	private Date read_date;//阅读时间
	@Column(name = "type")
	private String type;//区分    0：阅读，1：点赞，和2：取消点赞
	@Column(name = "spare_1")
	private String spare_1;
	@Column(name = "spare_2")
	private String spare_2;
	@Column(name = "spare_3")
	private String spare_3;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getRead_user_id() {
		return read_user_id;
	}
	public void setRead_user_id(String read_user_id) {
		this.read_user_id = read_user_id;
	}
	public String getRead_user_name() {
		return read_user_name;
	}
	public void setRead_user_name(String read_user_name) {
		this.read_user_name = read_user_name;
	}
	public Date getRead_date() {
		return read_date;
	}
	public void setRead_date(Date read_date) {
		this.read_date = read_date;
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
	
	
	
}
