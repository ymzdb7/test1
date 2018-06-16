<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">

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
							     <form  action="<%=basePath%>log/index" method="post"  role="form" id="searchForm" name="searchForm">
								      <div class="form-group">
				                        <label class="col-sm-1 col-sm-1 control-label">搜索：</label>
				                        <div class="col-sm-3">
				                            <input type="text" id="userName" name="userName" class="form-control tooltips"  value="${userName}" placeholder="请输入用户名" />
				                        </div>
				                        <div class="col-sm-3">
				                            <input type="text" name="startTime"  class="form-control tooltips"  value="${startTime}"
				                              onFocus="WdatePicker({isShowClear:false,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:00'})"  placeholder="请输入开始时间" />
				                        </div>
				                        <div class="col-sm-3">
				                            <input type="text" name="endTime" class="form-control tooltips"  value="${endTime}"
				                              onFocus="WdatePicker({isShowClear:false,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:00'})"   placeholder="请输入结束时间" />
				                        </div>
				                        <div class="col-sm-2">
										  <button type="button" class="btn btn-primary" onclick="resetForm()">重置</button>
										  <button type="submit" class="btn btn-primary">搜索</button>
									    </div>
	 			                       </div>
 			                           <input id="pageNo" name="pageNum" type="hidden" value="" />
					                   <input id="pageSize" name="pageSize" type="hidden" value="" />
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th style="width:40px"></th>
												<th>操作人</th>
												<th>操作内容</th>
												<th>操作时间</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="log" items="${list}" varStatus="status">
										   		<tr class="gradeX">
													<td style="text-align: center">${status.index+1}</td>
										   			<td style="text-align: center">
										   			  ${log.createUserName}
										   			  
										   			</td>
										   			<td>${log.content }</td>

										   			<td style="text-align: center"> <fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	<script src="<%=jsPath %>/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<!--tree-->  
    <script>        
                     function resetForm(){
                     	$('form input').val("");
					 }
					 
		 </script>
</body>
</html>