package com.winhands.base.util;


public class BaseJson {
	private int status;
	private String message;
	private String info;
	private Object json; 
	private PageBt pageBt;
	public BaseJson(){
		 
	}
	public BaseJson(int status,String message,String info){
		this.status = status;
		this.message = message;
		this.info = info;
	}
	public BaseJson(int status,String message,String info,Object json){
		this.status = status;
		this.message = message;
		this.json = json;
	}
	public BaseJson(int status,String message,String info,Object json,PageBt pageBt){
		this.status = status;
		this.message = message;
		this.json = json;
		this.pageBt = pageBt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	public Object getJson() {
		return json;
	}
	public void setJson(Object json) {
		this.json = json;
	}
	public PageBt getPageBt() {
		return pageBt;
	}
	public void setPageBt(PageBt pageBt) {
		this.pageBt = pageBt;
	}
	
   
}
