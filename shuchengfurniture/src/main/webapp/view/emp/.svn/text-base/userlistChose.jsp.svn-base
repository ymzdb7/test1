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
							    <form  action="<%=basePath%>train/trainChefIndex?ac=<%=ac %>&searchId=${searchId}" method="post" name="searchForm" id="searchForm">
							      <div class="form-group"> 
				                        <div class="col-sm-4">
				                             <input type="text" name="searchName" class="form-control tooltips"  value="${searchName}" placeholder="请输入用户名" />
				                        </div>
				                        <div class="col-sm-4">
				                            <input type="text" name="searchPhone" class="form-control tooltips"  value="${searchPhone}" placeholder="请输入手机号" />
                                        </div> 
				                        <div class="col-sm-4" style="text-align: right;">
				                           <button type="button" class="btn btn-primary" onclick="doback()">返回<i class="fa fa-level-up"></i></button>
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                           <button type="button" class="btn btn-primary" id="addUser">确认添加<i class="fa fa-plus"></i></button> 
				                        </div> 
 			                       </div>
 			                        <input id="pageNo" name="pageNo" type="hidden" value="${pageBt.pageNo}" />
					                <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" />   
					                <input id="train_id" name="train_id" type="hidden" value="${train_id }" />
 			                      </form>  
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th><input name="checkall" type="checkbox" value="" /></th>
												<th>登录名</th>
												<th>用户名</th>
												<th>单位</th>
												<th>岗位</th>
												<th>联系方式</th> 
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="user" items="${pageList}" >
										   		<tr class="gradeX" id="user_${user.userId}">
										   			<td align="center">
										   			    <input name="usercheck" type="checkbox" value="${user.userId}"
										   			    <c:if test="${user.userExtend6 eq 1}">checked</c:if>/>
										   			    <c:if test="${user.userExtend6 eq 1}">
										   			       <input name="chosed" type="hidden" value="${user.userId}"/>
										   			    </c:if>
										   			</td>
										   			<td>
										   			  ${user.userName }
										   			  <input type="hidden" name="userId" value="${user.userId}"/> 
										   			</td>
										   			<td>${user.userNameCn}</td>
										   			<td>${user.orgName }</td>
										   			<td>
										   			    <c:if test="${user.userType eq 1}">
										   			                         市局管理员
										   			    </c:if>
										   			    <c:if test="${user.userType eq 2}">
										   			                          信息安全员
										   			    </c:if>
										   			    <c:if test="${user.userType eq 3}">
										   			                          厨师
										   			    </c:if>
										   			</td>
										   			<td>${user.userPhone }</td> 
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
                  $(function(){
					$("input[name='checkall']").click(function(){
						if($(this).attr('checked')=='checked'){
							$("[name='usercheck']").attr("checked",'true');//全选
						}else{
							$("[name='usercheck']").removeAttr("checked");//全不选
						}
					});
					$('#addUser').click(function(){ 
					 		var checkIDs = "";
					 		var choseIDs = "";
					 		var indexTemp = 0;
					 		var tempUser="";
							$("input[name='usercheck']").each(function(){
								if($(this).attr('checked') == "checked"){
									indexTemp ++;
									checkIDs += $(this).val()+",";
								}
							});
							
							$("input[name='chosed']").each(function(){
								choseIDs += $(this).val()+",";
							}); 
							//将选中的厨师新增到培训用户表中去
							$.ajax({ type: 'POST',
								   async: true,
								   cache: false,
								   url:  '<%=basePath%>train/addTrainChef',
								   data: {'chef_ids':checkIDs,train_id:"${train_id}","chose_ids":choseIDs},
								   dataType: 'text',
								   error:function (data, textStatus) { 
									     top.$.jBox.closeTip();
									     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
									     return;
								   },success:function (data,textStatus){ 
								  
								      var json =   $.parseJSON(data); 
									  if(json.status==1){ 
									 	 $('#searchForm').submit();
									  }else{ 
										 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
									  }
								   }      
							})
					}); 
                   
                   
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
					
					function doback(){
					    history.go(-1);
					}
					 
		 </script>
</body>
</html>