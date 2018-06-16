<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<script type="text/javascript" src="<%=jsPath%>/My97DatePicker/WdatePicker.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">

	<section>
		<!-- left side start 左侧菜单导航-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="<%=basePath%>index"><img src="<%=imgPath %>/logo.png"
					alt=""> </a>
			</div>

			<div class="logo-icon text-center">
				<a href="<%=basePath%>index"><img
					src="<%=imgPath %>/logo_icon.png" alt=""> </a>
			</div>
			<!--logo and iconic logo end-->
			<!--sidebar nav start-->
			<div class="left-side-inner">
				<jsp:include page="../common/leftmenu.jsp"></jsp:include>
			</div>
			<!--sidebar nav end-->
		</div>
		<!-- left side end-->

		<!-- main content start-->
		<div id="main-content" class="main-content">
			<!-- header section start-->
			<div class="header-section">
				<jsp:include page="../common/notify.jsp"></jsp:include>
			</div>
			<!-- header section end-->
			<!-- page heading start -->
			<!-- 此处点击菜单切换可考虑放置菜单名也可仅在首页的时候展示 -->
			
			<!-- page heading end-->
			<!--body wrapper start-->
			<!-- 此处是点击菜单后，菜单内容填充地 -->
			<div class="wrapper">

				<div class="row">
					<div class="col-md-12"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <form  action="<%=basePath %>comment/magIndex?ac=500010" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                       <div class="col-sm-3">
			                            <input type="text" name="title"  value="${comment.title}" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入文章标题" data-original-title="文章标题">
			                       </div>
			                       <div class="col-sm-3">
			                            <input type="text" name="phoneNum" value="${comment.phoneNum}" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入手机号|昵称" data-original-title="手机号|昵称">
			                       </div>
			                       <div class="col-sm-3">
			                            <input type="text" name="content" value="${comment.content}"  class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入关键词" data-original-title="关键词">
			                       </div>
			                        
			                        
			                        <div class="col-sm-3" style="text-align: right;" > 
			                        <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
 			                        </div>
 			                       </div>
 			                        <input id="pageNo" name="pageNum" type="hidden" value="${pageBt.pageNo}" />
					                <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" />  
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th style="width:40px"></th>
												<th>文章标题</th>
												<th>回复者</th>
												<th>评论内容</th>
												<th>回复时间</th> 
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="comment" items="${list}" varStatus="status">
										   		<tr class="gradeX" id="comment_${comment.id}">
										   			<td>
										   			 ${status.index+1}
										   			  <input type="hidden" name="id" value=" ${comment.id}"/>
										   			</td>
										   			<td>${comment.title}</td>
										   			<td>${comment.phoneNum }-${comment.userName }</td> 
										   			<td>${comment.content }</td>  
										   			<td>  
										   			    ${fn:substring(comment.createTime,0,19 )}
										   			</td>   
										   			 
										   			<td style="text-align: center;"> 
										   			     <a href="javascript:void(0);" onclick="del('${comment.id }')"><i class="fa fa-btn fa-exchange">删除</i>
										   			     </a>  
										   			</td>
										   		</tr>
										   	</c:forEach> 
										</tbody>
										
									</table>
									<c:if test="${pageBt.count>10}"><div style="float: right;margin: 0px 20px 0px 0px;">${pageBt}</div></c:if>
								</div>
							</div>
						</section> 

					</div>
				</div>

			</div>
			<!--body wrapper end-->
			<!--footer section start-->
			<jsp:include page="../common/company.jsp"></jsp:include>
			<!--footer section end-->
		</div>
		<!-- main content end-->
	</section>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<!--tree--> 
	
    <script>         
      function del(id){
		if(confirm('是否确认删除？')){   
            $.ajax({ type: 'POST',
				   async: true,
				   cache: false,
				   url:  '<%=basePath%>comment/deleteComment',
				   data: {'id':id},
				   dataType: 'text',
				   error:function (data, textStatus) { 
					     top.$.jBox.closeTip();
					     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
					     return;
				   },success:function (data,textStatus){ 
				      var json =   $.parseJSON(data); 
					  if(json.status==200){ 
					        $('#comment_'+id).remove();
						    return false;
					  }else{ 
						 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
					  }
				   }      
			});
			}
        } 				
    </script>
</body>
</html>