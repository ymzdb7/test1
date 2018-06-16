<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<!--common scripts for all pages-->
<script src="js/scripts.js"></script>
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
		            <h3>新增积分规则 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="../integration/saveRule?ac=<%=ac%>">
		                    <!-- div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">目录id:</label>
		                        <div class="col-sm-6">
		                            <input type="text"  name="dir_id" class="form-control" value="${directory.dir_id }"/>
		                        </div>
		                    </div -->
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">规则名称:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="ruleName" class="form-control" value="${rule.ruleName}"/>
		                        </div>
		                    </div>
		                     <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">分数:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="score" class="form-control" value="${rule.score}"/>
		                        </div>
		                    </div>
		                    
		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-8" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-8">
                                    <button class="btn btn-primary" type="submit">提交</button> 
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button">返回</button>
                                </div>
                                 
                            </div>
		                </form>
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
	<script>
					$('#doback').click(function(){ 
						    location.href = "<%=basePath %>integration/dirIndex?ac=500001&type=0&super_dir_id=1001";
					 });
		 </script>
</body>
</html>