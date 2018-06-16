<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请求错误</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="<%=basePath%>/css/css_last.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
	function returnPage(){
	    var parentWindow = parent.window;
		if(parentWindow!=null){
			parent.parent.window.location = "<%=basePath%>";
		}
		else{
			window.location="<%=basePath%>";
		}
	}
	</script>
  </head>
  
  <body>
    您的请求可能存在错误哦....
    <br/>
    <div class="div_button">  
		 <div class="tips"><span id="tipsText" class="tipsText"></span></div>
		 <input type="button" class="buttons blue medium" value="返回上页" onclick = "history.go(-1);"/>  
		 <input type="button" class="buttons blue medium" value="返回首页" onclick = "returnPage();"/> 
    </div> 
  </body>
</html>
