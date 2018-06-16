<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./view/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="./view/common/head.jsp"></jsp:include>
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
				<a href="<%=basePath%>index"><img src="<%=imgPath %>/logo.png" alt="">
				</a>
			</div>

			<div class="logo-icon text-center">
				<a href="<%=basePath%>index"><img src="<%=imgPath %>/logo_icon.png" alt="">
				</a>
			</div>
			<!--logo and iconic logo end--> 
			<!--sidebar nav start-->
			<div class="left-side-inner"> 
			    <jsp:include page="./view/common/leftmenu.jsp">
			        <jsp:param name="acId" value="1000" /> 
			    </jsp:include> 
			</div>
			<!--sidebar nav end--> 
		</div>
		<!-- left side end-->

		<!-- main content start-->
		<div class="main-content"> 
			<!-- header section start-->
			<div class="header-section">  
			   <jsp:include page="./view/common/notify.jsp"></jsp:include> 
			</div>
			<!-- header section end-->
            <!-- page heading start -->
            <!-- 此处点击菜单切换可考虑放置菜单名也可仅在首页的时候展示 -->
			<!-- page heading end--> 
			<!--body wrapper start-->
			<!-- 此处是点击菜单后，菜单内容填充地 -->
			<!--body wrapper end-->
            <!--footer section start-->
			<jsp:include page="./view/common/company.jsp"></jsp:include>
			<!--footer section end--> 
		</div>
		<!-- main content end-->
	</section> 
	<jsp:include page="./view/common/foot.jsp"></jsp:include>
	<!--easy pie chart-->
	<script src="<%=jsPath %>/easypiechart/jquery.easypiechart.js"></script>
	<script src="<%=jsPath %>/easypiechart/easypiechart-init.js"></script>

	<!--Sparkline Chart-->
	<script src="<%=jsPath %>/sparkline/jquery.sparkline.js"></script>
	<script src="<%=jsPath %>/sparkline/sparkline-init.js"></script>

	<!--icheck -->
	<script src="js/iCheck/jquery.icheck.js"></script>
	<script src="<%=jsPath %>/icheck-init.js"></script>

	<!-- jQuery Flot Chart -->
	<script src="<%=jsPath %>/flot-chart/jquery.flot.js"></script>
	<script src="<%=jsPath %>/flot-chart/jquery.flot.tooltip.js"></script>
	<script src="<%=jsPath %>/flot-chart/jquery.flot.resize.js"></script>
   

	<!--Morris Chart-->
	<script src="<%=jsPath %>/morris-chart/morris.js"></script>
	<script src="<%=jsPath %>/morris-chart/raphael-min.js"></script>
    
	<!--Calendar -->
	<script src="<%=jsPath %>/calendar/clndr.js"></script>
	<script src="<%=jsPath %>/calendar/evnt.calendar.init.js"></script>
	<script src="<%=jsPath %>/calendar/moment-2.2.1.js"></script> 

	<!--Dashboard Charts-->
	<script src="<%=jsPath %>/dashboard-chart-init.js"></script>


</body>
</html>