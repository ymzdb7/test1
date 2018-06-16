<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<!--<link rel="stylesheet" href="<%=jsPath%>/ztree/css/demo.css"
	type="text/css">  -->
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
					<div class="col-md-3">
						&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="edit()"><i class="fa fa-btn fa-pencil"></i>修改</a>
						<a href="javascript:void(0);" onclick="remove()"><i class="fa fa-btn fa-exchange"></i>删除</a>
						<a href="javascript:void(0);" onclick="add()"><i class="fa fa-btn fa-plus"></i>新增</a>
						<a href="#myModal" data-toggle="modal" style="display:none" id="dialog">点击弹出dialog</a>
						<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button aria-hidden="true" data-dismiss="modal"
													class="close" type="button">×</button>
												<h4 class="modal-title" id="title">菜单修改</h4>
											</div>
											<div class="modal-body">
												<form role="form"  class="form-horizontal" action="<%=basePath%>menu/editMenu" method="post" id="menuForm">
													<div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">菜单名:</label>
								                        <input id="menuId" name="id" hidden>
								                        <div class="col-sm-6">
								                            <input type="text" name="name" id="menuName" class="form-control"    title=""  />
								                        </div>
								                    </div>
													<div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">URL:</label>
								                        <div class="col-sm-6">
								                            <input type="text" name="url" id="url" class="form-control"    title=""  />
								                        </div>
								                    </div>
								                    <div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">是否父节点:</label>
								                        <div class="col-sm-6">
								                            <select class="col-sm-2 form-control m-bot15" name="isParent" id="isParent" onchange="onSelectChange(this)">
									                            <option value="1">是</option>
									                            <option value="2">否</option>
								                            </select>
								                        </div>
								                    </div>
								                    <div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">父节点:</label>
								                        <div class="col-sm-6">
								                            <select class="form-control m-bot15" name="parentId" id="parentMenuName">
								                            </select>
								                        </div>
								                    </div>
								                    <div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">排序:</label>
								                        <div class="col-sm-2">
								                            <input type="text" name="orderId" class="form-control" id="orderId"   title=""  />
								                        </div>
								                        <label class="col-sm-4 col-lg-2 control-label">是否有效:</label>
								                        <div class="col-sm-2">
								                            <select class="col-sm-2 form-control m-bot15" name="isVaild" id="isVaild">
									                            <option value="1">是</option>
									                            <option value="2">否</option>
								                            </select>
								                        </div>
								                    </div>
								                    <div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">js_text:</label>
								                        <div class="col-sm-6">
								                            <input type="text" name="jsText" class="form-control"  id="jsText"  title=""  />
								                        </div>
								                    </div>
													<div class="form-group">
						                                <div class=" col-lg-12" style="text-align:center">
						                                    <button type="submit" class="btn btn-primary">提交</button>
						                                </div>
						                            </div>
												</form>
											</div>
										</div>
									</div>
								</div>
						<ul id="tree" class="ztree" style="overflow:auto;"></ul>
					</div>
					<div class="col-md-9"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <h3>子节点列表</h3>
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th>菜单名</th>
												<th>导航节点</th>
												<th>URL</th>
												<th>排序</th>
												<th>是否有效</th>
											</tr>
										</thead>
										<tbody id="tbody"> 
										   	<c:forEach var="menu" items="${list }">
										   		<tr>
										   			<td style="text-align:center">${menu.name }</td>
										   			<td style="text-align:center"><c:choose>
														   <c:when test="${menu.parentId == '0' and menu.isParent =='1' }">  
														         是    
														   </c:when>
														   <c:otherwise> 
														    	否
														   </c:otherwise>
														</c:choose></td>
										   			<td style="text-align:center">${menu.url }</td>
										   			<td style="text-align:center">${menu.orderId }</td>
										   			<td style="text-align:center"><c:choose>
														   <c:when test="${menu.isVaild == '1' }">  
														         是    
														   </c:when>
														   <c:otherwise> 
														    	否
														   </c:otherwise>
														</c:choose></td>
										   		</tr>
										   	</c:forEach>
										</tbody>
										
									</table>
									
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
    	<script type="text/javascript" src="<%=jsPath %>/jBox/jquery.jBox-2.3.min.js"></script>
	 <script type="text/javascript" src="<%=jsPath %>/jBox/i18n/jquery.jBox-zh-CN.js"></script>
    <script>        
                    
                    
                    
                    $(document).ready(function() { 
						var t = $("#tree");
						t = $.fn.zTree.init(t, setting, zNodes); 
						var bodyH = $("#main-content").height(); 
						$('#tree').height(bodyH-80);  
						var zTree = $.fn.zTree.getZTreeObj("tree"); 
						zTree.expandAll(true); 
					});
                    
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
							selectedMulti : false,
							txtSelectedEnable: true
						},
						data : {
							
						},
						callback : {
							 beforeClick:beforeClick,
							 onCheck:onCheck,
							 beforeCheck:beforeCheck
						}
					}; 
					var zNodes = ${treeJson};
 
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("tree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					}  
					function onCheck(event,treeId, node){
						if(node.getCheckStatus().checked){
							$('#tbody').html('');
							$.get('<%=basePath%>menu/getSonMenu',{'id':node.id},function(data){
								for(var i=0;i<data.length;i++){
									var obj = data[i];
									$('#tbody').append("<tr><td>"+obj.name+"</td><td>"+((obj.parentId=='0'&&obj.isParent=='1')?"是":"否")+"</td><td>"+obj.url+"</td><td>"+obj.orderId+"</td><td>"+(obj.isVaild=='1'?"是":"否")+"</td></tr>");
								}
								$('#tbody > tr > td').css("text-align","center");
							})
							
						}
					}
					function beforeCheck(treeId, node){
						if(node.getCheckStatus().checked){
							return false;
						}
						return true;
					}
                    function edit(){
                    	$('#title').text("菜单修改");
                    	var zTree = $.fn.zTree.getZTreeObj("tree");
                    	var node = zTree.getCheckedNodes(true)[0];
                    	if(node.id=='0'){
                    		$.jBox.alert("不能操作根节点！");
                    		return;
                    	}
                    	clearDialog();
                    	var parentNodes = zTree.getNodesByParam("isParent2","1",null);
                    	$.ajax({
                    		type:'post',
                    		async:false,
                    		url:'<%=basePath%>menu/getMenuDetail',
                    		data:{'id':node.id},
                    		success:function(data){
                    			$('#menuId').val(data.id);
                    			$('#menuName').val(data.name);
                    			$('#url').val(data.url);
                    			$('#isParent').val(data.isParent);
                    			$('#orderId').val(data.orderId);
                    			$('#isVaild').val(data.isVaild);
                    			for(var i=0;i<parentNodes.length;i++){
                    				var id = parentNodes[i].id;
                    				var name = parentNodes[i].name;
                    				if(id == "0"){
                    					name = "根节点";
                    				}
                    				if(node.id != id){
                    					$('#parentMenuName').append("<option value='"+id+"'>"+name+"</option>");
                    				}
                    				
                    			}
                    			$('#parentMenuName').val(node.getParentNode().id);
                    			$('#jsText').val(data.jsText);
                    			$('#menuForm').attr("action","<%=basePath%>menu/editMenu");
                    			document.getElementById('dialog').click();
                    		}
                    	})
                    }
                    
                    function onSelectChange(obj){
                    	if($(obj).val()=="1"){
                    		$('#url').val("").attr("readonly",true);
                    	}else{
                    		$('#url').attr("readonly",false);
                    	}
                    }
                    
                    function remove(){
                    	var zTree = $.fn.zTree.getZTreeObj("tree");
                    	var node = zTree.getCheckedNodes(true)[0];
                    	if(node.id=='0'){
                    		$.jBox.alert("不能操作根节点！");
                    		return;
                    	}
                    	$.jBox.confirm("确定删除选中菜单？","提示",deleteMenu);
                    	function deleteMenu(){
                    		$.ajax({
                    			type:'post',
                    			async:false,
                    			url:'<%=basePath%>menu/deleteMenu',
                    			data:{'id':node.id},
                    			success:function(data){
                    				if(data.info>0){
                    					$.jBox.alert("删除成功！");
                    					location.reload();
                    				}
                    			}
                    		})
                    	}
                    };
                    
                    function add(){
                    	$('#title').text("菜单新增");
                    	var zTree = $.fn.zTree.getZTreeObj("tree");
                    	var node = zTree.getCheckedNodes(true)[0];
                    	var parentNodes = zTree.getNodesByParam("isParent2","1",null);  //所有父节点
                    	clearDialog();
                    	for(var i=0;i<parentNodes.length;i++){
            				var id = parentNodes[i].id;
            				var name = parentNodes[i].name;
            				if(id == "0"){
            					name = "根节点";
            				}
            					$('#parentMenuName').append("<option value='"+id+"'>"+name+"</option>");
            				
            			}
                    	if(node.isParent2==2){
                    		$('#parentMenuName').val(node.getParentNode().id);
                    	}else{
                    		$('#parentMenuName').val(node.id);
                    	}
                    	$('#menuForm').attr("action","<%=basePath%>menu/addMenu");
                    	document.getElementById('dialog').click();
                    	
                    };
                    function clearDialog(){
                    	$('#menuId').val("");
            			$('#menuName').val("");
            			$('#url').val("");
            			$('#isParent').val("");
            			$('#orderId').val("");
            			$('#isVaild').val("");
            			$('#parentMenuName').html("");
            			$('#jsText').val("");
                    }
                    
					 
		 </script>
</body>
</html>