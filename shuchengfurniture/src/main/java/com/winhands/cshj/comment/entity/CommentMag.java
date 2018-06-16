package com.winhands.cshj.comment.entity;

/**
 * @author guojun
 */
public class CommentMag extends Comment{
    private String title;//文章标题
    private String phoneNum;//手机号
    private String userName;//昵称
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
   

    
}
