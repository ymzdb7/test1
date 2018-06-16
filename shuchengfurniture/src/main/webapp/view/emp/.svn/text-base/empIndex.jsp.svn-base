<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">

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
							     <form  action="<%=basePath %>chef/chefIndex?ac=<%=ac%>" method="post" name="searchForm" id="searchForm">
								      <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">搜索：</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="searchName" value="${searchName}"  class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入姓名/公司/联系电话查询" data-original-title="厨师姓名">
			                        </div>
			                        <div class="col-sm-4" style="text-align: right;">
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
<!-- 				                           <button type="button" class="btn btn-primary" id="addChef">新增<i class="fa fa-plus"></i></button>  -->
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
												<th>姓名</th>
												<th>身份证号</th>
												<th>联系电话</th>
												<th>公司</th>
												<th>注册时间</th>
<!-- 												<th>操作</th> -->
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="chef" items="${pageList}" >
										   		<tr class="gradeX" id="chef_${chef.chef_id}">
										   			<td>${chef.name }
										   			 <input type="hidden" name="id" value="${chef.chef_id}"/></td>
										   			<td>${chef.idcard }</td>
										   			<td>${chef.phone }</td>
										   			<td>${chef.address_town }${chef.chef_address_detail}</td>
										   			<td>${chef.create_time }</td>
<!-- 										   			<td style="text-align: center;"> -->
<!-- 										   			    <a href="javascript:void(0);" onclick="edit('${chef.chef_id}')"><i class="fa fa-btn fa-pencil"></i>修改</a> -->
<!-- 										   			    <a href="javascript:void(0);" onclick="delChef('${chef.chef_id}')"><i class="fa fa-btn fa-exchange">删除</i> -->
<!-- 										   			    </a> -->
<!-- 										   			</td> -->
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
	<script src="<%=jsPath %>/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<!--tree-->  
    <script type="text/javascript">
		$(function(){
			$('#addChef').click(function() { 
				location.href="<%=basePath %>chef/chefAdd?ac=300010";
			}); 
		});
		function edit(id){  
		      $('#searchForm').attr('action','<%=basePath%>chef/chefEdit?ac=300010&searchName=${searchName}&id='+id);
		      $('#searchForm').submit();
		   } 
		function delChef(id){
				if(confirm('是否确认删除？')){   
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>chef/remove',
							   data: {'id':id},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#chef_'+id).remove();
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
						}
                    }
</script>

</body>
</html>