<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<script type="text/javascript" src="<%=jsPath%>/My97DatePicker/WdatePicker.js"></script>
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
		            <h3>新增培训信息 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="../train/trainSave?ac=<%=ac%>">
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">培训名称:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="train_name" class="form-control" value="${train.train_name}"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">培训内容:</label>
		                       <div class="col-sm-6">
		                            <input type="text" name="train_content" class="form-control" value="${train.train_content}"   title=""  />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">培训时间:</label>
		                        <div class="col-sm-3">
					                            <input type="text" class="form-control tooltips" name="train_start_time" value="${train.train_start_time }" autocomplete="off"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					                 </div>
					                  <div class="col-sm-3">
					                            <input type="text" class="form-control tooltips" name="train_end_time" value="${train.train_end_time }" autocomplete="off"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
					                  </div>
		                        
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">培训地点:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="train_address" class="form-control" value="${train.train_address}" />
		                        </div>
		                    	</div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">授课人:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="train_tec" class="form-control" value="${train.train_tec}"   title=""  />
		                        </div>
		                    </div>
		                     <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">培训单位:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="train_dept" class="form-control" value="${train.train_dept}"   title=""  />
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
	<script>
					$('#doback').click(function(){ 
						    location.href = "<%=basePath%>train/trainIndex?ac=<%=ac%>&searchId=${searchId}";
					 });
					
		 </script>
</body>
</html>