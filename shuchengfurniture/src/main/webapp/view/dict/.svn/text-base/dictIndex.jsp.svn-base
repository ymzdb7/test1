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
					<div class="col-md-3">
						&nbsp;&nbsp;<ul id="tree" class="ztree" style="overflow:auto;"></ul>
					</div>
					<div class="col-md-9"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <form  action="" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">搜索：</label>
			                        <div class="col-sm-8">
			                            <input type="text" name="searchName" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入字典名称" data-original-title="字典名称">
			                        </div>
			                        <div class="col-sm-2 btn-group" style="text-align: right;"> 
					                        <button type="button" class="btn btn-primary" id="addDict">
					                                                                                             新增<i class="fa fa-plus"></i>
					                        </button> 
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
												<th>字典值</th>
												<th>字典名称</th>
												<th>父编号</th>
												<th>是否有效</th>
												<th>序号</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="dict" items="${pageList}" >
										   		<tr class="gradeX" id="dict_${dict.id}">
										   			<td>
										   			  ${dict.dictId }
										   			  <input type="hidden" name="id" value="${dict.id}"/>
										   			  <input type="hidden" id="status_${dict.id}" value="${dict.isInvalid}"/>
										   			</td>
										   			<td>${dict.dictName }</td>
										   			<td>${dict.superDictId }</td>
										   			<td id="isInvalid_${dict.id}">
										   			   <c:if test="${dict.isInvalid eq 1}">有效</c:if> 
										   			   <c:if test="${dict.isInvalid eq 0}">无效</c:if>  
										   			</td>
										   			<td>${dict.orderId }</td>
										   			<td style="text-align: center;">
										   			    <a href="javascript:void(0);" onclick="edit('${dict.id}')"><i class="fa fa-btn fa-pencil"></i>修改</a>
										   			    <a href="javascript:void(0);" onclick="changeStatus('${dict.id}')"><i class="fa fa-btn fa-exchange"></i>
										   			      <span id="Operdesc_${dict.id}"><c:if test="${dict.isInvalid eq 1}">无效</c:if> 
										   			      <c:if test="${dict.isInvalid eq 0}">有效</c:if></span> 
										   			    </a>
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
    <script src="<%=jsPath%>/ztree/js/jquery.ztree.all.min.js"></script> 
    <script>        
                    function changeStatus(id){  
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>dict/changeStatus',
							   data: {'id':id,'status':$('#status_'+id).val()},
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
                       $('#searchForm').attr('action','<%=basePath%>dict/edit?ac=<%=ac%>&searchId=${searchId}&id='+id);
                       $('#searchForm').submit();
                    } 
					var zTree; 
					var setting = {
					    check: {
							enable: true,
							chkStyle: "radio",
							radioType: "all",
							chkboxType: { "Y": "", "N": "" }
						}, 
						view : { 
							dblClickExpand : false,
							showLine : true,
							selectedMulti : false
						},
						data : {
							simpleData : {
								enable : true,
								idKey : "id",
								pIdKey : "pId",
								rootPId : "0"
							}
						},
						callback : {
							 beforeClick:beforeClick
						}
					}; 
					var zNodes = ${treejson};

					$(document).ready(function() { 
						var t = $("#tree");
						t = $.fn.zTree.init(t, setting, zNodes); 
						var bodyH = $("#main-content").height(); 
						$('#tree').height(bodyH-80);  
						var zTree = $.fn.zTree.getZTreeObj("tree"); 
						zTree.expandAll(true); 
						
						$('#addDict').click(function(){ 
						    location.href = "<%=basePath%>dict/add?ac=<%=ac%>&dictId=${searchId}";
						});
					});
					
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("tree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					}  
					 
		 </script>
</body>
</html>