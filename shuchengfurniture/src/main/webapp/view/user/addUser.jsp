<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<!--common scripts for all pages--> 
</head>
<script type="text/javascript">


</script>
<body class="sticky-header">

	<section>
		<!-- left side start 左侧菜单导航-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="index.html"><img src="<%=imgPath %>/logo.png" alt="">
				</a>
			</div>

			<div class="logo-icon text-center">
				<a href="index.html"><img src="<%=imgPath %>/logo_icon.png"
					alt=""> </a>
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
			<!-- page heading start-->
			  <div class="page-heading">
		            <h3>新增用户 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="<%=basePath%>user/save?ac=<%=ac%>" id="userForm">
		                    
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">用户名:</label>
		                        <div class="col-sm-6">
		                            <input type="text"  name="userName" class="form-control" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">姓名:</label>
		                        <div class="col-sm-6">
		                            <input type="text"  name="userNameCn" class="form-control" autocomplete="off"/>
									<input  type="password"  hidden name="password" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">密码:</label>
		                        <div class="col-sm-6">
		                            <input  autocomplete="off" type="password" name="password1" class="form-control" id="pwd1" autocomplete="off" value="123456"/><i style="color: red">*默认密码为123456</i>
		                        </div>
		                    </div>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">确认密码:</label>
								<div class="col-sm-6">
									<input  autocomplete="off" type="password" name="password2" class="form-control" id="pwd2" onblur="checkPwd()" value="123456"/>
								</div>
							</div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">手机号:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="userPhone" class="form-control" />
		                        </div>
		                    </div>

		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-8" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-8">
                                    <button class="btn btn-primary" type="button" onclick="addUser()" id="submitBtn">提交</button>
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button" onclick="returnClick()">返回</button>
                                </div>
                                 
                            </div>
		                </form>
					</div>
				</div>
				<div id="orgContent"   style="display:none; position: absolute;background-color: #FFFFFF;">
					<ul id="orgTree" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
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
	<script src="<%=jsPath%>/ztree/js/jquery.ztree.all.min.js"></script> 
	<script>


					$(document).ready(function() { 

					});

		function checkPwd() {
			if($('#pwd1').val()!=$('#pwd2').val()){
				$('#msg').text("两次密码不一致！");
				$('#submitBtn').attr("onclick","");
				return;
			}
			$('#msg').text("");
			$('#submitBtn').attr("onclick","addUser()");
		};
		function addUser() {
			$('#userForm').submit();
		};
		function returnClick(){
			history.back();
		}
					 

					

					

					

		 </script>
</body>
</html>