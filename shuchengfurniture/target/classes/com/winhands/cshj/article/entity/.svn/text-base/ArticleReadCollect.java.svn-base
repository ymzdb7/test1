package com.winhands.cshj.article.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_article_read_collect", schema = "public")
public class ArticleReadCollect implements java.io.Serializable{ 
	/**
	 * 记录用户对文章收藏，喜爱状态
	 */
	private static final long serialVersionUID = 3344472339477372865L;
	@Id
	@Column(name = "id")
	private String id;//记录id
	@Column(name = "file_id")
	private String file_id;//文章id
	@Column(name = "read_user_id")
	private String read_user_id;//登录人id
	@Column(name = "type")
	private String type;//0:阅读   1：喜爱    2:收藏 3：答题
	@Column(name = "state_value")
	private String state_value;//0: 取消    1：确认
	@Column(name = "create_date")
	private Date create_date;//操作时间
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState_value() {
		return state_value;
	}
	public void setState_value(String state_value) {
		this.state_value = state_value;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
