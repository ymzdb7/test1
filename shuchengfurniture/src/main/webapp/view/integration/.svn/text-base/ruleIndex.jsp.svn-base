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
							    <form  action="<%=basePath %>integration/index" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
 			                        <div class="col-sm-1" style="text-align: right;">
												<button type="button" class="btn btn-primary" id="addRule" >新增<i class="fa fa-plus"></i></button>
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
												<th style="width:40px"></th>
												<th>积分规则</th>
												<th>分数</th>
												<th>制定人</th>
												<!--th>操作</th-->
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="rule" items="${pageList}" varStatus="status">
										   		<tr class="gradeX" id="rule_${rule.id}">
										   			<td align="center">
										   			  ${status.index+1}
										   			  <input type="hidden" name="id" value=" ${rule.id}"/>
										   			</td>
										   			<td align="center">${rule.ruleName}</td>
										   			<td align="center">${rule.score}</td>
										   			<td align="center">${rule.createUserId}</td>
										   			<!--  td style="text-align: center;">
										   			    <a href="javascript:void(0);" onclick="edit('${rule.id}')"><i class="fa fa-btn fa-pencil"></i>修改</a>
										   			    <a href="javascript:void(0);" onclick="del('${rule.id}')"><i class="fa fa-btn fa-pencil"></i>删除</a>
										   			</td-->
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
    <script>       
    	$(document).ready(function() { 
			$('#addRule').click(function(){ 
				 location.href = "<%=basePath%>integration/addRule?ac=<%=ac%>";
			});
		});		 
	</script>
</body>
</html>