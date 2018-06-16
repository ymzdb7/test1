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
							    <form  action="<%=basePath%>org/index?ac=<%=ac%>&searchId=${searchId}" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">搜索：</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="searchName" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入机构名称" data-original-title="机构名称">
			                        </div>
			                        <div class="col-sm-4" style="text-align: right;"> 
			                                <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
					                        <button type="button" class="btn btn-primary" id="addOrg"> 新增<i class="fa fa-plus"></i></button> 
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
												<th>机构ID</th>
												<th>机构名称</th>
												<th>父编号</th>
												<th>序号</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="org" items="${pageList}" >
										   		<tr class="gradeX" id="org_${org.orgId}">
										   			<td>
										   			  ${org.orgId }
										   			  <input type="hidden" name="id" value="${org.orgId}"/> 
										   			</td>
										   			<td>${org.orgName}</td>
										   			<td>${org.parentOrgId }</td>
										   			<td>${org.orderId }</td>
										   			<td style="text-align: center;">
										   			    <a href="javascript:void(0);" onclick="edit('${org.orgId}')"><i class="fa fa-btn fa-pencil"></i>修改</a>
										   			    <a href="javascript:void(0);" onclick="removeOrg('${org.orgId}')"><i class="fa fa-btn fa-exchange"></i>
										   			      <span id="Operdesc_${org.orgId}">删除</span>
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
                    
                    
                    
                    $(document).ready(function() { 
						var t = $("#tree");
						t = $.fn.zTree.init(t, setting, zNodes); 
						var bodyH = $("#main-content").height(); 
						$('#tree').height(bodyH-80);  
						var zTree = $.fn.zTree.getZTreeObj("tree"); 
						zTree.expandAll(true);  
						var dictmap = {}; 
						<c:forEach var="dict"  items="${dictlist}"  > 
                             dictmap['${dict.dictId}'] = '${dict.dictName}';    
						 </c:forEach> 
						
						$('.orgType').each(function(){ 
						     $(this).html(dictmap[$(this).attr('id')]);
						      
						}); 
						$('#addOrg').click(function(){ 
						    location.href = "<%=basePath%>org/add?ac=<%=ac%>&orgId=${searchId}";
						});
					});
                    
                    function deleteOrg(v, h, f){
                       if (v == 'ok') {
	                      $.ajax({ type: 'POST',
								   async: true,
								   cache: false,
								   url:  '<%=basePath%>org/remove',
								   data: {'id':orgId},
								   dataType: 'text',
								   error:function (data, textStatus) { 
									     top.$.jBox.closeTip();
									     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
									     return;
								   },success:function (data,textStatus){ 
								      var json =   $.parseJSON(data); 
									  if(json.status==1){ 
									        $('#org_'+orgId).remove(); 
									        var zTree = $.fn.zTree.getZTreeObj("tree"); 
									        var node = zTree.getNodeByParam("id", orgId, null); 
									        zTree.removeNode(node);
										    return false;
									  }else{ 
										 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
									  }
								   }      
							})
						}else if (v == 'cancel') {
					        // 取消
					    } 
					    return true; //close	
                    }
                    var orgId;
                    function removeOrg(id){   
                        orgId = id;
                        $.jBox.confirm("确定要删除数据吗？", "提示", deleteOrg); 
                    }
                    function edit(id){  
                       $('#searchForm').attr('action','<%=basePath%>org/edit?ac=<%=ac%>&searchId=${searchId}&id='+id);
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
 
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("tree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					}  
					 
		 </script>
</body>
</html>