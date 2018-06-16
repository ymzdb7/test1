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
		            <h3>新增作者 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" id="form1" method="post" action="../directory/saveDir?ac=<%=ac%>">
		                    <!-- div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">目录id:</label>
		                        <div class="col-sm-6">
		                            <input type="text"  name="dir_id" class="form-control" value="${directory.dir_id }"/>
		                        </div>
		                    </div -->
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">作者名称:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="dir_name" class="form-control" value="${directory.dir_name}"/>
<!-- 		                            <span class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span> -->
		                        	<input id="type" name="type" type="hidden" value="${type}" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">序号:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="order_id" id="order_id" class="form-control" value="${directory.order_id}"   title="" placeholder="序号" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 control-label">是否有效:</label>
		                        <div class="col-sm-3">
		                            <label class="checkbox-inline">
		                                <input name="is_valid"  type="radio" value="1" <c:if test="${directory.is_valid eq 1 ||dict.is_valid==null }">checked="checked"</c:if> >有效
		                            </label>
		                            <label class="checkbox-inline">
		                                <input name="is_valid"  type="radio" value="0" <c:if test="${directory.is_valid eq 0}">checked="checked"</c:if>>无效
		                            </label> 
		                        </div>
		                        <label class="col-sm-1 control-label">是否上线:</label>
		                        <div class="col-sm-4">
		                            <label class="checkbox-inline">
		                                <input name="is_online"  type="radio" value="1" <c:if test="${directory.is_online eq 1 ||dict.is_online==null }">checked="checked"</c:if> >是
		                            </label>
		                            <label class="checkbox-inline">
		                                <input name="is_online"  type="radio" value="0" <c:if test="${directory.is_online eq 0}">checked="checked"</c:if>>否
		                            </label> 
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">简介:</label>
		                        <div class="col-sm-6">
		                           <textarea class="form-control" name="summary" rows="5" cols="120"></textarea>
		                        </div>
		                    </div> 
		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-8" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-8">
                                    <button class="btn btn-primary" type="button" onclick="saveAuthor();">提交</button> 
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
						    location.href = "<%=basePath %>directory/dirIndex?ac=500001&type=0&super_dir_id=1001";
					 });
					 function saveAuthor(){
					 var order_id=document.getElementById("order_id").value;
					 if(order_id==null||order_id==""){
						alert("请填写序号");
						}else{
						　var r = /^\+?[1-9][0-9]*$/;　　//正整数 
      					 if(r.test(order_id)){
      						document.getElementById("form1").submit();    
      					 }else{
      					 	alert("请填写数字！");
      					 }
						 
						}
					 }
		 </script>
</body>
</html>