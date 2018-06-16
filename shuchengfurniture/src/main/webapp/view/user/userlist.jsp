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
							    <form  action="<%=basePath%>user/index?ac=<%=ac%>" method="post" name="searchForm" id="searchForm">
							      <div class="form-group"> 
				                        <div class="col-sm-4">
				                             <input type="text" name="searchName" class="form-control tooltips"  value="${searchName}" placeholder="请输入用户名" />
				                        </div>
				                        <div class="col-sm-4">
				                            <input type="text" name="searchPhone" class="form-control tooltips"  value="${searchPhone}" placeholder="请输入手机号" />
                                        </div> 
				                        <div class="col-sm-4" style="text-align: right;">
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                           <button type="button" class="btn btn-primary" id="addUser">新增<i class="fa fa-plus"></i></button> 
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
												<th>用户名</th>
												<th>姓名</th>
												<th>手机号</th>
												<th>注册时间</th>
												<th>最近登录时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="user" items="${pageList}" >
										   		<tr class="gradeX" id="user_${user.userId}">
										   			<td style="text-align: center">
										   			  ${user.userName }
										   			  <input type="hidden" name="userId" value="${user.userId}"/> 
										   			</td>
										   			<td  style="text-align: center">${user.userNameCn}</td>
										   			<td style="text-align: center">${user.userPhone }</td>
										   			<td style="text-align: center">${user.userOpTime }</td>
													<td style="text-align: center">${fn:substring(user.lastLoginTime,0,16 )  }</td>
										   			<td style="text-align: center;">
										   			        <a href="javascript:void(0);" onclick="edit('${user.userId}')"><i class="fa fa-btn fa-pencil"></i>修改</a>
															 <a href="javascript:void(0);" onclick="resetPass('${user.userId}')"><i class="fa fa-btn fa-pencil"></i>密码重置</a>
										   			    	<a href="javascript:void(0);" onclick="removeUser('${user.userId}')"><i class="fa fa-btn fa-exchange"></i>
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
    	<script type="text/javascript" src="<%=jsPath %>/jBox/jquery.jBox-2.3.min.js"></script>
	 <script type="text/javascript" src="<%=jsPath %>/jBox/i18n/jquery.jBox-zh-CN.js"></script>
    <script>        
                    
                    
                    
                    $(document).ready(function() { 
						$('#addUser').click(function(){
						    location.href = "<%=basePath%>user/add?ac=<%=ac%>&orgId=${searchId}";
						}); 
					});
                    
                    function deleteUser(v, h, f){
                       if (v == 'ok') {
	                      $.ajax({ type: 'POST',
								   async: true,
								   cache: false,
								   url:  '<%=basePath%>user/remove',
								   data: {'id':userId},
								   dataType: 'text',
								   error:function (data, textStatus) { 
									     top.$.jBox.closeTip();
									     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
									     return;
								   },success:function (data,textStatus){ 
								      var json =   $.parseJSON(data); 
									  if(json.status==1){ 
									        $('#user_'+userId).remove();  
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
                    var userId;
                    function removeUser(id){   
                        userId = id;
                        $.jBox.confirm("确定要删除数据吗？", "提示", deleteUser); 
                    }
                    function edit(id){  
                       $('#searchForm').attr('action','<%=basePath%>user/edit?ac=<%=ac%>&searchId=${searchId}&userId='+id);
                       $('#searchForm').submit();
                    } 

					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("tree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					}  
                    function resetPass(user_id){
                       if(confirm('确定要重置密码吗？')){
	                      $.ajax({ type: 'POST',
								   async: true,
								   cache: false,
								   url:  '<%=basePath%>user/resetPassword',
								   data: {'user_id':user_id},
								   dataType: 'text',
								   error:function (data, textStatus) { 
									     top.$.jBox.closeTip();
									     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
									     return;
								   },success:function (data,textStatus){ 
								      var json =   $.parseJSON(data); 
									  if(json.status==1){ 
										    return false;
									  }else{ 
										 top.$.jBox.tip(json.message,"success",{persistent:true,opacity:0});
									  }
								   }      
							})
						}else if (v == 'cancel') {
					        // 取消
					    } 
					    return true; //close	
                    }
					 
		 </script>
</body>
</html>