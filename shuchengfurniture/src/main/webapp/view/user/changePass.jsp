<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link href="<%=stylePath%>/style-responsive.css" rel="stylesheet">
<!--multi-select-->
<link rel="stylesheet" type="text/css"
	href="<%=jsPath%>/jquery-multi-select/css/multi-select.css" />

<!--file upload-->
<link rel="stylesheet" type="text/css"
	href="<%=stylePath%>/bootstrap-fileupload.min.css" />

<!--tags input-->
<link rel="stylesheet" type="text/css"
	href="<%=jsPath%>/jquery-tags-input/jquery.tagsinput.css" />
<style type="text/css">
.panel-heading {
	font-weight: normal
}

#pageSel {
	line-height: 30px;
	height: 30px;
	width: 40px;
	border: 1px solid #ddd
}

.pagination li {
	height: 30px;
}
</style>
</head>

<body class="sticky-header" scroll="no">

	<section>
		<!-- left side start 左侧菜单导航-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="index.html"><img src="<%=imgPath%>/logo.png" alt="">
				</a>
			</div>

			<div class="logo-icon text-center">
				<a href="index.html"><img src="<%=imgPath%>/logo_icon.png"
					alt=""> </a>
			</div>
			<!--logo and iconic logo end-->
			<!--sidebar nav start-->
			<div class="left-side-inner">
				<jsp:include page="../common/leftmenu.jsp">
					<jsp:param value="" name="" />
				</jsp:include>
			</div>
			<!--sidebar nav end-->
		</div>
		<!-- left side end-->

		<!-- main content start-->
		<div class="main-content">
			<!-- header section start-->
			<div class="header-section">
				<jsp:include page="../common/notify.jsp"></jsp:include>
			</div>
			<!-- header section end-->
			<!-- page heading start-->
			<div class="page-heading" style="padding: 5px 15px">
				<h4>修改密码</h4>
			</div>
			<!-- page heading end-->
			<!--body wrapper start-->
			<div class="wrapper" style="min-height: 1000px">
				<div class="row">
					<div class="col-md-12">
						<section class="panel">
							<div class="panel-body" style="padding: 35px">
								<form role="form" action="" method="post" enctype="multipart/form-data" class="form-horizontal">
									<div class="form-group">
										<label for="input1" class="col-lg-2 col-sm-2 control-label">输入原密码：</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" id="input1"
												 name="facilityName">
										</div>
									</div>
									<div class="form-group">
										<label for="input2" class="col-lg-2 col-sm-2 control-label">新密码：</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" id="input2"
												 name="keywords">
										</div>
									</div>
									<div class="form-group">
										<label for="input2" class="col-lg-2 col-sm-2 control-label">确认新密码：</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" id="input3"
												 name="keywords">
										</div>
									</div> 
							        <br/> 
							        <br/>
							   <div style="text-align: center;"   class="col-lg-8">
									 <p id="msg" style="color: #FF0000;font-size: 13px;">${message}</p>
								</div>    
							   <div style="text-align: center;" class="col-lg-8">     
							       <button type="button" class="btn btn-primary"
								     style="margin-left: 15px; margin-top: 15px" onclick="formSubmit()">修改</button>
							   </div> 	
							   </form>
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
	<!--script for editable table-->
	<!--multi-select-->
	<!--spinner-->
	<script src="<%=jsPath%>/spinner-init.js"></script>
	<!--file upload-->
	<!--tags input-->
	<!--bootstrap input mask-->
	<script type="text/javascript" src="<%=jsPath%>/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	<script>
		$(function() {

		});
		function formSubmit(){
			if($('#input3').val()!=$('#input2').val()){
				alert("两次密码输入不一致，请重新输入！");
				return;
			}
			$.ajax({
				type:'post',
				async:false,
				url:'<%=basePath%>user/changePassword',
				data:{password:$('#input1').val(),newPassword:$('#input2').val()},
				success:function(data){
				    $('#msg').html(data.message); 
				}
			})
		}
	</script>

</body>
</html>
