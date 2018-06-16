<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<!--common scripts for all pages--> 
</head>
<LINK rel="stylesheet" href="<%=basePath%>js/ueditor/themes/default/css/ueditor.css">
	<script type="text/javascript" charset="gbk">
      window.UEDITOR_HOME_URL = "<%=basePath%>js/ueditor/";//编辑器项目路径
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
		            <h4>新增 </h4> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" enctype="multipart/form-data" method="post" action="../news/saveCarousel?ac=<%=ac%>"> 
		                    <div class="form-group">
		                        <label class="col-sm-1  control-label">标题:</label>
		                        <div class="col-sm-11">
		                          <input type="text" style="width: 81%" name="title" class="form-control" value="" placeholder="请输入标题"/>
		                        </div> 
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-1  control-label">作者:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text"  name="author"  class="form-control" value="" placeholder="请输入作者"/>
		                        </div> 
		                         <label class="col-sm-1  control-label">序号:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text"  name="orderId"  class="form-control" value="" placeholder="请输入序号"/>
		                        </div> 
		                    </div>
		                    
		                    <div class="form-group"> 
		                        <label class="col-sm-1  control-label">缩略图:</label>
		                        <div class="col-sm-4">
		                            <input type="file"    name="pic" id="pic"  value=""/>
		                        </div>
		                        <label class="col-sm-1 control-label">是否上线: </label>
		                        <label class="col-sm-1">
		                                                                                         是:<input type="radio"  class="radio-inline" name="isOnline" id="isOnline1"  value="1" checked="checked"/>
		                        </label>         
		                        <label class="col-sm-2">          
		                                                                                       否:<input type="radio"  class="radio-inline" name="isOnline" id="isOnline2"  value="0"/>
		                        </label>
		                       
		                    </div>
		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-10" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-10">
                                    <button class="btn btn-primary" type="submit">提交</button> 
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button">返回</button>
                                </div>
                                 
                            </div>
                            <div id="myEditor" style="mamargin-left:30px;margin-top:5px; width: 950px;height: 450px;" name="newsContent">
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
	<SCRIPT type="text/javascript" src="<%=basePath%>js/ueditor/ueditor.config.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="<%=basePath%>js/ueditor/ueditor.all.js"></SCRIPT>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	 <SCRIPT type="text/javascript"  > 
		    var editor = new baidu.editor.ui.Editor(); 
		    editor.render("myEditor"); 
		    editor.ready(function(){
			     editor.setContent('${newsContent}');
			})
	 </SCRIPT>
	<script>
		$('#doback').click(function(){ 
		location.href = "<%=basePath%>news/carouselIndex?ac=<%=ac%>&type=${type}";
		});
	</script>
</body>
</html>