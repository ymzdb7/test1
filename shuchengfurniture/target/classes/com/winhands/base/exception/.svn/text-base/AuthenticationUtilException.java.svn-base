package com.winhands.base.exception; 

import org.apache.shiro.authc.AuthenticationException;

public class AuthenticationUtilException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8187937787928313966L;
	private int exceptionCode;//登录错误代码
	private String exceptionInfo;//登录错误信息
	
	public AuthenticationUtilException(int exceptionCode, String exceptionInfo){
		this.exceptionCode = exceptionCode;
		this.exceptionInfo = exceptionInfo;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}
}
