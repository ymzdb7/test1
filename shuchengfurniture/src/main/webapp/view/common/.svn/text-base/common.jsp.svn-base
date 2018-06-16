<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.winhands.base.shiro.BaseRealm.ShiroUser"%>
<%@page import="com.winhands.base.util.BaseConstant"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path; 
	response.setHeader("Cache-Control","no-store"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String imgPath = basePath + "images";
	String stylePath = basePath + "css";
	String fontPath = basePath + "font";
	String jsPath = basePath + "js"; 
	String ico = imgPath + "sys/sys.ico";
	String sysName = BaseConstant.app_name;
	String ac = request.getParameter("ac");  
    ac = ac==null?"":ac; 
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();    
%>