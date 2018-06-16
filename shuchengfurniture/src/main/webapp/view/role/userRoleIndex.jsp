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
<!--multi-select-->
    <link rel="stylesheet" type="text/css" href="<%=jsPath %>/jquery-multi-select/css/multi-select.css" />
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
				<a href="<%=basePath%>index"><img src="<%=imgPath %>/logo.png" alt=""> </a>
			</div>

			<div class="logo-icon text-center">
				<a href="<%=basePath%>index"><img src="<%=imgPath %>/logo_icon.png" alt=""> </a>
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
							    <h3>用户列表</h3>
							    <form  action="<%=basePath %>role/userRoleIndex" method="post" name="searchForm" id="searchForm">
								   <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">搜索：</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="userName" value="${userName }" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入用户登录名" data-original-title="用户登录名">
			                        </div>
			                         <div class="col-sm-4">
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                           <button type="button" class="btn btn-primary"  onclick="edit()">设置权限<i class="fa fa-btn fa-pencil"></i></button>
				                    </div>
 			                       </div>
 			                           <input id="pageNo" name="pageNo" type="hidden"  />
					                   <input id="pageSize" name="pageSize" type="hidden"  />   
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<a href="#myModal" data-toggle="modal" style="display:none" id="dialog">点击弹出dialog</a>
									<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" >
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button aria-hidden="true" data-dismiss="modal" class="close" type="button" id="closeBtn">×</button>
												<h4 class="modal-title">设置权限</h4>
											</div>
											<div class="modal-body">
												<form role="form"  class="form-horizontal"  id="roleForm">
								                    <div class="form-group">
								                        <label class="col-sm-3 col-lg-3 control-label">登录名:</label>
								                        <div class="col-sm-9">
								                            <input type="text" name="name" class="form-control" id="userName"  readonly   />
								                            <input type="hidden"  id="userId" />
								                        </div>
								                    </div>
								                    <div class="form-group">
								                        <label class="col-sm-3 col-lg-3 control-label">用户名:</label>
								                        <div class="col-sm-9">
								                            <input type="text" name="name" class="form-control" id="userNameCn"  readonly />
								                        </div>
								                    </div>
								                     <div class="form-group">
								                        <label class="col-sm-3 col-lg-3 control-label">角色<br/>(右为已授权):</label>
								                        <div class="col-sm-9" id="selectWrapper">
							                                
							                            </div>
								                    </div>
								                                        
													<div class="form-group">
						                                <div class=" col-lg-12" style="text-align:center">
						                                    <button type="button" class="btn btn-primary" onclick="editSubmit()" id="btn">提交</button>
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
												<th>登录名</th>
												<th>用户名</th>
												<th>单位</th>
												<th>联系电话</th>
												<th>序号</th>
											</tr>
										</thead>
										<tbody id="tbody"> 
										   	<c:forEach var="user" items="${list }" varStatus="status">
										   		<tr>
										   			<td style="text-align:center">${status.index+1 }</td>
			                                        <td style="text-align:center"> <input type="checkbox" class="userCheck" onClick="userCheckClick(this)"><input hidden value="${user.userId }"></td>
										   			<td>${user.userName }</td>
										   			<td>${user.userNameCn}</td>
										   			<td>${user.orgName }</td>
										   			<td>${user.userPhone }</td>
										   			<td>${user.userOrder }</td>
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
    	<script type="text/javascript" src="<%=jsPath %>/jBox/jquery.jBox-2.3.min.js"></script>
	 <script type="text/javascript" src="<%=jsPath %>/jBox/i18n/jquery.jBox-zh-CN.js"></script>
	 <!--multi-select-->
<script type="text/javascript" src="<%=jsPath%>/jquery-multi-select/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery-multi-select/js/jquery.quicksearch.js"></script>
<script src="<%=jsPath%>/multi-select-init.js"></script>
    <script>        
	    function userCheckClick(obj){
	    	if($(obj).attr("checked")){
	    		$('.userCheck').prop("checked",false);
	    		$(obj).prop("checked",true);
	    	}
	    };      
		function editSubmit(id){
			var s = $('#ms-my_multi_select1').children('.ms-selection').children('ul').children('li');
			var ids = "";
			s.each(function(){
				if($(this).css("display")!="none"){
					var id = $(this).attr("id")
					ids = ids + id.substring(0,id.indexOf("-"))+",";
				} 
			})
			$.ajax({
				type:'post',
				async:false,
				url:'<%=basePath%>role/updateUserRole',
				data:{'ids':ids,'userId':$('#userId').val()},
				success:function(data){
					if(data.info=="ok"){
						$.jBox.alert("用户授权成功！");
						document.getElementById('closeBtn').click();
					}else{
						$.jBox.alert("系统出错，请重新提交！");
					}
				}
			})
		};
		
		function edit(){
			if(check()){
				var userName = $('.userCheck:checked').parent().next().text();
				var userNameCn = $('.userCheck:checked').parent().next().next().text();
				var id = $('.userCheck:checked').next().val();
				$('#selectWrapper').empty();
				$('#selectWrapper').html('<select multiple="multiple" class="multi-select" id="my_multi_select1"name="my_multi_select1[]"></select>');
				$('#userName').val(userName);
				$('#userNameCn').val(userNameCn);
				$('#userId').val(id);
		    	$.ajax({
		    		type:'post',
		    		async:false,
		    		url:'<%=basePath%>role/getUserRole',
		    		data:{'userId':id},
		    		success:function(data){
		    			for(var i=0;i<data.length;i++){
		    				var role = data[i];
		    				if(role.spare1){  //选中
		    					$('#my_multi_select1').append("<option value='"+role.id+"' selected>"+role.name+"</option>");
		    				}else{
		    					$('#my_multi_select1').append("<option value='"+role.id+"'>"+role.name+"</option>");
		    				}
		    			}
		    			$('#my_multi_select1').multiSelect();
		    		}
		    	})
				document.getElementById('dialog').click();
			}else{
				$.jBox.alert("请先选择用户！");
			}
		};
		
		
        function check(){
        	if($('.userCheck:checked').length){  //有选中
        		return true;
        	}
        	return false;
        };            
                    
                    
                   
					 
		 </script>
</body>
</html>