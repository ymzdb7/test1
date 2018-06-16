<%@page import="com.winhands.base.util.StringUtil"%>
<%@page import="com.winhands.base.web.BaseController"%>
<%@page import="com.winhands.base.auth.entity.Menu"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<script src="<%=jsPath %>/jquery-1.10.2.min.js"></script>
<ul class="nav nav-pills nav-stacked custom-nav">
	<%	
	    //RedisDao redisDao = new RedisDao();
		String userId = BaseController.getUserSession().userId;
		//Menu root = redisDao.getMenu(userId);
		Menu root = (Menu)session.getAttribute(userId);
	%>
	<input type="hidden" id="input" value="<%if(root==null){ root = new Menu();%><%=1%><%}%>" />



	<% List<Menu> list = root.getMenuList();
		if(list==null){
			list = new ArrayList<Menu>();
		}
	%>
	<%for(Menu m : list){ %>
	<%if("2".equals(m.getIsVaild())){continue;} %>
	<%if("2".equals(m.getIsParent())) {%>
	<li <%String u = m.getUrl();
		if(StringUtil.isNull(u)){continue;}
		int a = u.indexOf("ac=");
		int b = u.indexOf("&");
		if(b==-1){ if(u.substring(a+3).equals(ac)){%> class="active" <%}}else{if(u.substring(a+3,b).equals(ac)){ %> class="active" <%}}%>>
		<a href="<%=basePath+m.getUrl()%>"><i class="fa <%=m.getJsText()%>"></i>
			<span><%=m.getName() %></span> </a></li>
	<%continue;} %>
	<li class="menu-list  "><a href=""><i class="fa <%=m.getJsText()%>"></i><span><%=m.getName() %></span></a>
		<ul class="sub-menu-list">
			<%for(Menu sm : m.getMenuList()){%>

			<li <%String u = sm.getUrl();
				if(StringUtil.isNull(u)){continue;}
				int a = u.indexOf("ac=");
				int b = u.indexOf("&");
				if(b==-1){ if(u.substring(a+3).equals(ac)){%> class="active" <%}}else{if(u.substring(a+3,b).equals(ac)){ %> class="active" <%}}%>>
				<a href="<%=basePath+sm.getUrl() %>"><%=sm.getName() %></a></li>
			<%} %>
		</ul></li>
	<%} %>
	
	
	
</ul>
<script type="text/javascript" >
	$(function(){
		$('.active').parent().parent('li').addClass("nav-active");
		/*if($('#input').val()){  //redis中没有记录
			$.ajax({
				type:'post',
				async:false,
				url:'<%=basePath%>menu/putMenuIntoRedis',
				data:{},
				success:function(data){
					location.reload();
				}
			})
		}*/
	});
</script>
