package com.winhands.cshj.article.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SubjectMag {
	private String subjectId;
	private String articleId;
	private String indexNum;//题目序号
	private String subjectType;//0: 判断题  1单选题    2.多选题
	private String subjectContent;//题目内容
	private String subjectItems;//选择题选项
	private String answer;//答案   选择题    ABCD   对应答案 1234   ； 选择题    对错    对应    1，2
	private String createUserId;//出题人
	private Date createDate;//出题时间
	private String msg;//备注
	private String spare1;
	private Map <String ,String> items = new HashMap<String ,String>();
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getSubjectContent() {
		return subjectContent;
	}
	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}
	public String getSubjectItems() {
		return subjectItems;
	}
	public void setSubjectItems(String subjectItems) {
		this.subjectItems = subjectItems;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSpare1() {
		return spare1;
	}
	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}
	public Map<String, String> getItems() {
		return items;
	}
	public void setItems(Map<String, String> items) {
		this.items = items;
	}
	
	
	
	
	
}
