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

<body class="panel">

	<section>
		<!-- left side start 左侧菜单导航-->
		<!-- left side end-->

		<!-- main content start-->
		<div id="main-content">
			<!-- 此处是点击菜单后，菜单内容填充地 -->
			<div class="wrapper">

				<div class="row">
					<div class="col-md-12"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <form  action="<%=basePath%>article/showLogList?ac=<%=ac%>&article_id=${article_id}&type=${type}" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                        <div class="col-sm-8">
			                            <input type="text" name="searchName" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入读者名" data-original-title="读者名">
			                        </div>
 			                        <div class="col-sm-2" style="text-align: right;">
												<button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
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
												<th>序号</th>
												<th>文章标题</th>
												<th>读者</th>
												<th>操作时间</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="fileReadLog" items="${pageList}" varStatus="status" >
										   		<tr class="gradeX" id="log_${fileReadLog.id}">
										   			<td> ${status.index+1}</td>
										   			<td>${fileReadLog.file_id }</td>
										   			<td>${fileReadLog.read_user_name }</td>
										   			<td>${fileReadLog.spare_3 }</td>
										   			<td>
										   			<c:if test="${fileReadLog.spare_1 eq 1 && fileReadLog.type eq 2}">收藏</c:if> 
										   			<c:if test="${fileReadLog.spare_1 eq 0 && fileReadLog.type eq 2}">取消收藏</c:if>
										   			<c:if test="${fileReadLog.spare_1 eq 1 && fileReadLog.type eq 1}">喜爱</c:if> 
										   			<c:if test="${fileReadLog.spare_1 eq 0 && fileReadLog.type eq 1}">取消喜爱</c:if>  
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
                       location.href ='<%=basePath%>directory/toEditDir?ac=<%=ac%>&type=1&dir_id='+id;
                    } 
                    $(document).ready(function() { 
						$('#addDir').click(function(){ 
						    location.href = "<%=basePath%>directory/addDir?ac=<%=ac%>&type=${type}&super_dir_id=${super_dir_id}";
						});
					});
					 
		 </script>
</body>
</html>