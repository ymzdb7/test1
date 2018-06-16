<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen" href="<%=jsPath %>/jBox/Skins2/Blue/jbox.css"/>
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
					<%--<div class="col-md-3">
						&nbsp;&nbsp;<ul id="tree" class="ztree" style="overflow:auto;"></ul>
					</div>--%>
					<div class="col-md-12">
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <form  action="<%=basePath%>member/queryFeeCountList?ac=600003" method="post" name="searchForm" id="searchForm">
							       <div class="form-group"> 
				                        <div class="col-sm-2">
				                             <input type="text" class="form-control tooltips" name="avatarPath" value="${member.avatarPath}" autocomplete="off"   onclick="WdatePicker({dateFmt:'yyyyMM'})" readonly="readonly" placeholder="请输入时间" data-original-title="日期" />
				                        </div>
				                        <div class="col-sm-2">
                                        </div>
									  <div class="col-sm-2">
										
									  </div>
									  <div class="col-sm-4"><input hidden></div>
				                        <div class="col-sm-2" style="text-align: right;">
				                        	<button type="button" class="btn btn-primary" onclick="resetForm()">重置</button>
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                        </div>
 			                       </div>
 			                        <input id="pageNo" name="pageNo" type="hidden"  />
					                <input id="pageSize" name="pageSize" type="hidden"  />
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th>日期</th>
												<th>月营业额</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="m" items="${list}" >
										   		<tr class="gradeX">
													<td style="text-align: center">${m.userName}</td>
													<td style="text-align: center">${m.phoneNum}</td>
										   		</tr>
										   	</c:forEach> 
										</tbody>
										
									</table>
									<c:if test="${pagesBt.count>10}"><div style="float: right;margin: 0px 20px 0px 0px;">${pagesBt}</div></c:if>
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
	<!--tree--> 
    <script src="<%=jsPath%>/ztree/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="<%=jsPath%>/My97DatePicker/WdatePicker.js"></script> 
    <script type="text/javascript" src="<%=jsPath %>/jBox/jquery.jBox-2.3.min.js"></script>
	 <script type="text/javascript" src="<%=jsPath %>/jBox/i18n/jquery.jBox-zh-CN.js"></script>
    <script>        
             $(function(){
             	$('#type-sel').val(${member.type});
			 });
			 function resetForm(){
				 $('form input').val("");
				 $('#type-sel').val("");
			 };
			 function setIsVaild(status,id){
			 	var fuc = function(v){
			 	    if(!v){
			 	        return true;
                    }
			 	    $.ajax({
			 		type:'post',
					async:false,
					url:'<%=basePath%>member/changeIsVaild',
					data:{'isVaild':status,'id':id},
					success:function (data) {
						if(data.status==200){
							$.jBox.success("修改成功！");
							location.reload();
							return true;
						}else {
							$.jBox.error("系统错误，请重新操作！");
							return true;
						}
					}
				});}
				$.jBox.confirm("确定执行冻结/解冻操作？","提示：",fuc,{ buttons: { '确定': true, '取消': false } });

			 }
             function editType(type,id){
            if(confirm('是否确认调整？')){ 
			 	    $.ajax({
			 		 type: 'POST',
					async: true,
					cache: false,
					url:'<%=basePath%>member/changeType',
					data:{'type':type,'id':id},
					dataType: 'text',
					error:function (data, textStatus) {
							 top.$.jBox.closeTip();
							 top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
							 return;
					},success:function (data,textStatus){ 
					var json =   $.parseJSON(data); 
						if(json.status==200){ 
						$.jBox.success("修改成功！");
							location.reload();
							return true;
						}else{ 
							$.jBox.error("系统错误，请重新操作！");
							return true;
						}
						}    
				});}

			 }       

					 
		 </script>
</body>
</html>