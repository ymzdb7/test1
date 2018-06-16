<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<script type="text/javascript" src="<%=jsPath%>/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=jsPath%>/jquery-multi-select/css/multi-select.css" />

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
			<div class="left-side-inner">
				<jsp:include page="../common/leftmenu.jsp"></jsp:include>
			</div>
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
		            <h3>选择培训厨师 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="../train/trainSave?ac=<%=ac%>">
		                     <div class="form-group last">
                                <label class="control-label col-md-3">选择厨师</label>

                                <div class="col-md-9">
                                <select name="country" class="multi-select" multiple="" id="my_multi_select3">
                                <c:forEach var="user" items="${chefList}" >
                                	<option value="${user.userId }">${user.userName }</option>
                                </c:forEach>
                            	</select>
                            	</div>
                            </div>
                             <div class="form-group" style="text-align: center;">
                                <div class="col-lg-10">
                                    <button class="btn btn-primary" id="next" type="button">下一步</button>
                                </div>
                                 
                            </div>
		                </form>
					</div>
				</div>
				<div id="dictContent"   style="display:none; position: absolute;background-color: #FFFFFF;">
					<ul id="dictTree" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
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
	<!--multi-select-->

<script type="text/javascript" src="<%=jsPath%>/jquery-multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery-multi-select/js/jquery.quicksearch.js"></script>
<script src="<%=jsPath%>/multi-select-init.js"></script>
<!--common scripts for all pages-->
	<script>
					$('#doback').click(function(){ 
						    location.href = "<%=basePath%>dict/index?ac=<%=ac%>&searchId=${searchId}";
					 });
					 $('#next').click(function(){ 
					 		var userStr=""
						    var chefID=document.getElementById("my_multi_select3");
						   for(var i=0;i<chefID.options.length;i++){
							    if(chefID.options[i].selected){
							       userStr+=chefID.options[i].value+","+chefID.options[i].text+";";// 收集选中项
							    }
							}
						 location.href = "<%=basePath%>train/showTrainChef?ac=<%=ac%>&userStr="+userStr+"&train_id="+"${train_id}";
					 });
					
					
		 </script>
</body>
</html>