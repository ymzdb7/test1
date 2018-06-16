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
					<div class="col-md-3" style="display:none">
						&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="edit()"><i class="fa fa-btn fa-pencil"></i>修改</a>
						<a href="javascript:void(0);" onclick="remove()"><i class="fa fa-btn fa-exchange"></i>删除</a>
						<a href="javascript:void(0);" onclick="add()"><i class="fa fa-btn fa-plus"></i>新增</a>
						
						
						
					</div>
					<div class="col-md-6"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <h3>角色列表</h3>
			                    </div> 
								<div class="adv-table">
									<a href="javascript:void(0);" onclick="edit()"><i class="fa fa-btn fa-pencil"></i>修改</a>
									<a href="javascript:void(0);" onclick="remove()"><i class="fa fa-btn fa-exchange"></i>删除</a>
									<a href="javascript:void(0);" onclick="add()"><i class="fa fa-btn fa-plus"></i>新增</a><br/>
									<a href="#myModal" data-toggle="modal" style="display:none" id="dialog">点击弹出dialog</a>
									<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button aria-hidden="true" data-dismiss="modal"
													class="close" type="button">×</button>
												<h4 class="modal-title">角色新增</h4>
											</div>
											<div class="modal-body">
												<form role="form"  class="form-horizontal"  id="roleForm">
								                    <div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">角色名称:</label>
								                        <div class="col-sm-6">
								                            <input type="text" name="name" class="form-control" id="roleName"  title=""  />
								                        </div>
								                    </div>
								                                        功能菜单：
								                    <ul id="tree" class="ztree" style="overflow:auto;"></ul>
													<div class="form-group">
						                                <div class=" col-lg-12" style="text-align:center">
						                                    <button type="button" class="btn btn-primary" onclick="addSubmit()" id="btn">提交</button>
						                                </div>
						                            </div>
												</form>
											</div>
										</div>
									</div>
								</div>
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th style="width:40px"></th>
												<th style="width:40px"></th>
												<th>角色名称</th>
											</tr>
										</thead>
										<tbody id="tbody"> 
										   	<c:forEach var="role" items="${list }" varStatus="status">
										   		<tr>
										   			<td style="text-align:center">${status.index+1 }</td>
			                                        <td style="text-align:center"> <input type="checkbox" class="roleCheck" onClick="roleCheckClick(this)"><input hidden value="${role.id }"></td>
										   			<td style="text-align:center">${role.name }</td>
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
	    function roleCheckClick(obj){
	    	if($(obj).attr("checked")){
	    		$('.roleCheck').prop("checked",false);
	    		$(obj).prop("checked",true);
	    	}
	    };      
	    function add(){
	    	$('#roleName').val("");
	    	var zNodes;
	    	$.ajax({
	    		type:'post',
	    		async:false,
	    		url:'<%=basePath%>role/getMenuList',
	    		data:{'roleId':''},
	    		success:function(data){
	    			zNodes = data;
	    		}
	    	})
	    	var zTree; 
			var setting = {
			    check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "ps", "N": "ps" }
				}, 
				view : { 
					dblClickExpand : false,
					showLine : true,
					selectedMulti : true,
					txtSelectedEnable: true
				},
				data : {
					
				},
				callback : {
					beforeClick:beforeClick
				}
			}; 
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes); 
			var bodyH = $("#main-content").height(); 
			$('#tree').height(bodyH-80);  
			zTree = $.fn.zTree.getZTreeObj("tree"); 
			zTree.expandAll(true); 
			$('#btn').attr("onClick","addSubmit()");
			document.getElementById('dialog').click();
	    };
	    
	    function beforeClick(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.checkNode(treeNode, true, null, true);
			return false;
		};
		
		function addSubmit(){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			var nodes = zTree.getCheckedNodes();
			var ids = "";
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].id!=0){  //不为0
					ids = ids + nodes[i].id +",";
				}
			}
			$.ajax({
				type:'post',
				async:false,
				url:'<%=basePath%>role/addRole',
				data:{'name':$('#roleName').val(),'ids':ids},
				success:function(data){
					if(data.info=="ok"){
						location.reload();
					}else{
						$.jBox.alert("系统出错，请重新提交！");
					}
				}
			})
		};
		
		function editSubmit(id){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			var nodes = zTree.getCheckedNodes();
			var ids = "";
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].id!=0){  //不为0
					ids = ids + nodes[i].id +",";
				}
			}
			$.ajax({
				type:'post',
				async:false,
				url:'<%=basePath%>role/updateRole',
				data:{'name':$('#roleName').val(),'ids':ids,'id':id},
				success:function(data){
					if(data.info=="ok"){
						location.reload();
					}else{
						$.jBox.alert("系统出错，请重新提交！");
					}
				}
			})
		};
		
		function remove(){
			if(check()){
				var id = $('.roleCheck:checked').next().val();
				if(confirm("确定删除该角色？")){
					$.ajax({
						type:'post',
						async:false,
						url:'<%=basePath%>role/deleteRole',
						data:{'id':id},
						success:function(data){
							if(data.info=="ok"){
								location.reload();
							}else{
								$.jBox.alert("系统出错，请重新操作！");
							}
						}
					});
				}
			}else{
				$.jBox.alert("请先选择角色！");
			}
		};
              
		function edit(){
			if(check()){
				var name = $('.roleCheck:checked').parent().next().text();
				$('#roleName').val(name);
				var zNodes;
				var id = $('.roleCheck:checked').next().val();
		    	$.ajax({
		    		type:'post',
		    		async:false,
		    		url:'<%=basePath%>role/getMenuList',
		    		data:{'roleId':id},
		    		success:function(data){
		    			zNodes = data;
		    		}
		    	})
		    	var zTree; 
				var setting = {
				    check: {
						enable: true,
						chkStyle: "checkbox",
						chkboxType: { "Y": "ps", "N": "ps" }
					}, 
					view : { 
						dblClickExpand : false,
						showLine : true,
						selectedMulti : true,
						txtSelectedEnable: true
					},
					data : {
						
					},
					callback : {
						beforeClick:beforeClick
					}
				}; 
				var t = $("#tree");
				t = $.fn.zTree.init(t, setting, zNodes); 
				var bodyH = $("#main-content").height(); 
				$('#tree').height(bodyH-80);  
				zTree = $.fn.zTree.getZTreeObj("tree"); 
				zTree.expandAll(true); 
				$('#btn').attr("onClick","editSubmit('"+id+"')");
				document.getElementById('dialog').click();
			}else{
				$.jBox.alert("请先选择角色！");
			}
		};
		
		
        function check(){
        	if($('.roleCheck:checked').length){  //有选中
        		return true;
        	}
        	return false;
        };            
                    
                    
                   
					 
		 </script>
</body>
</html>