<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">

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
					<div class="col-md-12"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <form  action="<%=basePath %>directory/dirIndex?ac=500001&type=0&super_dir_id=1001" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">搜索：</label>
			                        <div class="col-sm-8">
			                            <input type="text" name="searchName" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入作者名称" data-original-title="作者名称">
			                        </div>
 			                        <div class="col-sm-2" style="text-align: right;">
												<button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
												<button type="button" class="btn btn-primary" id="addDir" >新增<i class="fa fa-plus"></i></button>
											</div>
 			                       </div>
 			                       </div>
 			                       	<input id="super_dir_id" name="super_dir_id" type="hidden" value="${super_dir_id}" />
 			                        <input id="pageNo" name="pageNo" type="hidden" value="${pageBt.pageNo}" />
					                <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" />  
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th style="width:80px">序号</th>
												<th>作者名称</th>
												<th>是否有效</th>
												<th>是否上线</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="directory" items="${pageList}" varStatus="status">
										   		<tr class="gradeX" id="directory_${directory.dir_id}">
										   			<td>
										   			  ${directory.order_id}
										   			  <input type="hidden" name="id" value=" ${directory.dir_id }"/>
										   			  <input type="hidden" id="status_${directory.dir_id }" value="${directory.is_valid}"/>
										   			</td>
										   			<td>${directory.dir_name }</td>
										   			<td id="isInvalid_${directory.dir_id}">
										   			   <c:if test="${directory.is_valid eq 1}">有效</c:if> 
										   			   <c:if test="${directory.is_valid eq 0}">无效</c:if>  
										   			</td>
										   			<td id="isOnline_${directory.dir_id}">
										   			   <c:if test="${directory.is_online eq 1}">上线</c:if> 
										   			   <c:if test="${directory.is_online eq 0}">未上线</c:if> 
										   			   <c:if test="${directory.is_online eq 2}">下线</c:if> 
										   			</td>
										   			<td style="text-align: center;">
										   			    <a href="javascript:void(0);" onclick="edit('${directory.dir_id }')"><i class="fa fa-btn fa-pencil"></i>修改</a>
										   			    <a href="javascript:void(0);" onclick="changeStatus('${directory.dir_id }')"><i class="fa fa-btn fa-exchange"></i>
										   			      <span id="Operdesc_${directory.dir_id }"><c:if test="${directory.is_valid eq 1}">无效</c:if> 
										   			      <c:if test="${directory.is_valid eq 0}">有效</c:if></span> 
										   			    </a>
										   			    <a href="javascript:void(0);" onclick="del('${directory.dir_id }')"><i class="fa fa-btn fa-pencil"></i>删除</a>
										   			</td>
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
	<!--tree--> 
    <script>        
                    function del(id){
					if(confirm('是否确认删除？')){   
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>directory/remove',
							   data: {'dir_id':id},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#directory_'+id).remove();
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
						}
                    }
                    function changeStatus(id){  
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>directory/changeStatus',
							   data: {'dir_id':id,'status':$('#status_'+id).val()},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#isInvalid_'+id).html($('#status_'+id).val()==1?"无效":"有效");
								        $('#Operdesc_'+id).html($('#status_'+id).val()==1?"有效":"无效"); 
								        $('#status_'+id).val($('#status_'+id).val()==1?0:1);
								        
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
                    }
                    function edit(id){  
                       location.href ='<%=basePath%>directory/toEditDir?ac=<%=ac%>&type=0&dir_id='+id;
                    } 
                    $(document).ready(function() { 
						$('#addDir').click(function(){ 
						    location.href = "<%=basePath%>directory/addDir?ac=<%=ac%>&type=${type}&super_dir_id=${super_dir_id}";
						});
					});
					 
		 </script>
</body>
</html>