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
							     <form  action="" method="post" name="searchForm" id="searchForm">
								      <div class="form-group">
				                        <label class="col-sm-1 col-sm-1 control-label">搜索：</label>
				                        <div class="col-sm-3">
				                            <input type="text" name="usersname" class="form-control tooltips"  value="${usersname}" placeholder="请输入用户名" />
				                        </div>
				                        <div class="col-sm-3">
				                            <input type="text" name="startdate"  class="form-control tooltips"  value="${startdate}"
				                              onFocus="WdatePicker({isShowClear:false,readOnly:true})"  placeholder="请输入开始时间" />
				                        </div>
				                        <div class="col-sm-3">
				                            <input type="text" name="enddate"    class="form-control tooltips"  value="${enddate}"
				                              onFocus="WdatePicker({isShowClear:false,readOnly:true})"   placeholder="请输入结束时间" />
				                        </div>
				                        <div class="col-sm-2">
				                           <button type="submit" class="btn btn-primary">搜索</button>
				                        </div>
	 			                       </div>
 			                           <input id="pageNo" name="pageNo" type="hidden" value="${pageBt.pageNo}" />
					                   <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" />  
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th>功能ID</th>
												<th>用户ID</th>
												<th>来源</th>
												<th>描述</th>
												<th>操作时间</th> 
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="log" items="${pageList}" >
										   		<tr class="gradeX">
										   			<td>
										   			  ${log.funcId }
										   			  
										   			</td>
										   			<td>${log.userId }</td>
										   			<td>${log.os }</td>
										   			 
										   			<td>${log.spare1 }</td>
										   			<td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
                     
					 
		 </script>
</body>
</html>