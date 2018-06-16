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
					<div class="col-md-12">
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							    <form  action="<%=basePath%>member/index?ac=600001" method="post" name="searchForm" id="searchForm">
							      <div class="form-group"> 
				                        <div class="col-sm-2">
				                             <input type="text" name="userName" class="form-control tooltips"  value="${member.userName}" placeholder="请输入会员名" />
				                        </div>
				                        <div class="col-sm-2">
				                            <input type="text" name="phoneNum" class="form-control tooltips"  value="${member.phoneNum}" placeholder="请输入手机号" />
                                        </div>
                                        <div class="col-sm-2">
										  <select class="col-sm-2 form-control " name="type" id="type-sel">
											  <option value="">请选择会员类别</option>
											  <option value="0">普通</option>
											  <option value="2">年</option>
										  </select>
									  </div>
                                        <div class="col-sm-4">
                                        	<input class="form-control" id="orgSel" type="text"   readonly  onclick="showOrg();" placeholder="请选择企业" />
		                            		<input id="searchName" name="searchName"  type="hidden" />
				                        </div>
									  
									  <div class="col-sm-2"><input hidden></div>
				                        <div class="col-sm-2" style="text-align: right;">
											<button type="button" class="btn btn-primary" onclick="resetForm()">重置</button>
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
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
												<th>手机号</th>
												<th>会员名</th>
												<th>行业</th>
												<th>企业</th>
												<th>注册时间</th>
												<th>最近登录时间</th>
												<th>会员类别</th>
												<th>到期时间</th>
												<th>账号状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="m" items="${list}" >
										   		<tr class="gradeX" id="user_${m.id}">
													<td style="text-align: center">${m.phoneNum}</td>
										   			<td style="text-align: center">
										   			  ${m.userName }
										   			  <input type="hidden" name="userId" value="${m.id}"/>
										   			</td>
										   			<td style="text-align: center">${m.schoolId}</td>
										   			<td style="text-align: center">${m.classId}</td>
										   			<td style="text-align: center">${fn:substring(m.createTime,0,16 ) }</td>
													<td style="text-align: center">${fn:substring(m.lastLoginTime,0,16 )  }</td>
													<td  style="text-align: center" id="type_${m.id}"><c:if test="${m.type == '0'}">注册用户</c:if><c:if test="${m.type == '1'}">会员用户</c:if><c:if test="${m.type == '2'}">会员用户</c:if></td>
										   			<td  style="text-align: center"><fmt:formatDate value="${m.expireTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td  style="text-align: center"><c:if test="${m.isVaild == '1'}">有效</c:if><c:if test="${m.isVaild == '2'}">冻结</c:if></td>
													<td style="text-align: center;">
														<c:if test="${m.type == '0'}">
															<a href="javascript:void(0);" onclick="editType(2,'${m.id}')"><i class="fa fa-btn fa-pencil"></i>升级年费会员</a>
														</c:if>
														<c:if test="${m.type == '2'}">
															<a href="javascript:void(0);" onclick="editType(0,'${m.id}')"><i class="fa fa-btn fa-pencil"></i>调整普通会员</a>
														</c:if>
														<c:if test="${m.isVaild == '1'}">
															<a href="javascript:void(0);" onclick="setIsVaild(2,'${m.id}')"><i class="fa fa-btn fa-pencil"></i>冻结</a>
														</c:if>
														
														<c:if test="${m.isVaild == '2'}">
															<a href="javascript:void(0);" onclick="setIsVaild(1,'${m.id}')"><i class="fa fa-btn fa-pencil"></i>解冻</a>
														</c:if>
														<a href="javascript:void(0);" onclick="changePass(${m.phoneNum})"><i class="fa fa-btn fa-pencil"></i>重置密码</a>
														<a href="javascript:void(0);" onclick="del('${m.id}')"><i class="fa fa-btn fa-pencil"></i>删除</a>
										   			</td>
										   		</tr>
										   	</c:forEach> 
										</tbody>
										
									</table>
									<c:if test="${pagesBt.count>10}"><div style="float: right;margin: 0px 20px 0px 0px;">${pagesBt}</div></c:if>
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
		<div id="orgContent"   style="display:none; position: absolute;background-color: #FFFFFF;">
			<ul id="orgTree" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
		</div>
		<!-- main content end-->
	</section>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<!--tree--> 
    <script src="<%=jsPath%>/ztree/js/jquery.ztree.all.min.js"></script> 
    	<script type="text/javascript" src="<%=jsPath %>/jBox/jquery.jBox-2.3.min.js"></script>
	 <script type="text/javascript" src="<%=jsPath %>/jBox/i18n/jquery.jBox-zh-CN.js"></script>
    <script>        
             $(function(){
             	$('#type-sel').val(${member.type});
			 });
			 function resetForm(){
				 $('form input').val("");
				 $('#type-sel').val("");
			 };
			 function setIsVaild(status,id){
			 	var fuc = function(v){
			 	    if(!v){
			 	        return true;
                    }
			 	    $.ajax({
			 		type:'post',
					async:false,
					url:'<%=basePath%>member/changeIsVaild',
					data:{'isVaild':status,'id':id},
					success:function (data) {
						if(data.status==200){
							$.jBox.success("修改成功！");
							location.reload();
							return true;
						}else {
							$.jBox.error("系统错误，请重新操作！");
							return true;
						}
					}
				});}
				$.jBox.confirm("确定执行冻结/解冻操作？","提示：",fuc,{ buttons: { '确定': true, '取消': false } });

			 }
			  function del(id){
					if(confirm('是否确认删除？')){   
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>member/remove',
							   data: {'memberId':id},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#user_'+id).remove();
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
						}
                    }
			 //resetPassWeb
			 function changePass(phoneNum){
			 if(confirm('是否确认调整？')){ 
			 	    $.ajax({
			 		 type: 'POST',
					async: true,
					cache: false,
					url:'<%=basePath%>member/resetPassWeb',
					data:{'phoneNum':phoneNum},
					dataType: 'text',
					error:function (data, textStatus) {
							 top.$.jBox.closeTip();
							 top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
							 return;
					},success:function (data,textStatus){ 
					var json =   $.parseJSON(data); 
						if(json.status==200){ 
						$.jBox.success(json.message);
							return true;
						}else{ 
							$.jBox.error("系统错误，请重新操作！");
							return true;
						}
						}    
				});}
			 }
             function editType(type,id){
            	if(confirm('是否确认调整？')){ 
			 	    $.ajax({
			 		 type: 'POST',
					async: true,
					cache: false,
					url:'<%=basePath%>member/changeType',
					data:{'type':type,'id':id},
					dataType: 'text',
					error:function (data, textStatus) {
							 top.$.jBox.closeTip();
							 top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
							 return;
					},success:function (data,textStatus){ 
					var json =   $.parseJSON(data); 
						if(json.status==200){ 
						$.jBox.success("修改成功！");
							location.reload();
							return true;
						}else{ 
							$.jBox.error("系统错误，请重新操作！");
							return true;
						}
						}    
				});}

			 }       

					 
		 </script>
		 <script type="text/javascript">
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
						callback: { 
							onCheck: onCheck,
							beforeClick: beforeClick
						}

					}; 
					var zNodes = ${treejson};

					$(document).ready(function() { 
						var t = $("#orgTree");
						t = $.fn.zTree.init(t, setting, zNodes); 
						var bodyH = $("#main-content").height(); 
						var zTree = $.fn.zTree.getZTreeObj("orgTree"); 
						zTree.expandAll(true);  
						$('#orgTree').height(200); 
						$('#orgTree').css('width',$('#orgSel').css('width')); 
						nodes = zTree.getCheckedNodes(true); 
						
						
						//初始化树
						var zTree = $.fn.zTree.getZTreeObj("orgTree"),
						nodes = zTree.getCheckedNodes(true); 
						$("#orgSel").attr("value", nodes[0].name);
					});
					 
					function showOrg() {
						var dictObj = $("#orgSel");
						var dictOffset = $("#orgSel").offset();
						$("#orgContent").css({left:dictOffset.left + "px", top:dictOffset.top + dictObj.outerHeight() + "px"}).slideDown("fast");
			            $("body").bind("mousedown", onBodyDown);
					} 
					
					function onBodyDown(event) {
					   if ($(event.target).parents("#orgContent").length==0) {
							hideMenu();
					   }  
					}
					function hideMenu() {
						$("#orgContent").fadeOut("fast");
						$("body").unbind("mousedown", onBodyDown);
					} 
					
					function onCheck(e, treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("orgTree"),
						nodes = zTree.getCheckedNodes(true); 
						$("#orgSel").attr("value", nodes[0].name);
						$("#searchName").attr("value", nodes[0].id);
					}
					
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("orgTree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					} 
		 </script>
</body>
</html>