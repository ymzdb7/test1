<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<!--common scripts for all pages--> 
</head>
<script type="text/javascript">


</script>
<body class="sticky-header">

	<section>
		<!-- left side start 左侧菜单导航-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="index.html"><img src="<%=imgPath %>/logo.png" alt="">
				</a>
			</div>

			<div class="logo-icon text-center">
				<a href="index.html"><img src="<%=imgPath %>/logo_icon.png"
					alt=""> </a>
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
			<!-- page heading start-->
			  <div class="page-heading">
		            <h3>修改机构 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="../org/update?ac=<%=ac%>&searchId=${searchId}">
		                    <div class="form-group">
		                        <label class="col-sm-4 col-sm-2 control-label">机构类型:</label>
		                        <div class="col-sm-6" >
		                         	<label class="checkbox-inline">
		                                   <input name="orgType"  type="radio" value="0"  onclick="checkType();" <c:if test="${'0' eq org.orgType }" >checked="checked"</c:if> >省市区
		                            </label>
		                            <label class="checkbox-inline">
		                                   <input name="orgType"  type="radio" value="2" onclick="checkType();"  <c:if test="${'2' eq org.orgType }" >checked="checked"</c:if> >企业
		                            </label>   
								   
		                        </div>
		                    </div> 
		                    <div id="city">
			                    <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">机构名称:</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="orgName" class="form-control" value="${org.orgName}"/>
			                            <input id="orgId" name="orgId" type="hidden" value="${org.orgId }" />
			                            <input id="orgCode" name="orgCode" type="hidden" value="${org.orgCode }"/>
			                            <input id="searchName" name="searchName" type="hidden" value="${searchName}" />
								        <input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" />
									    <input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
			                        </div>
			                    </div>
		                    </div>
		                    <div id="classSc" style="display: none;">
		                    	<div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">企业名称:</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="orgName" class="form-control" value="${org.orgName}"/>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">企业编号:</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="orgCode" class="form-control" value="${org.orgCode}"/>
			                        </div>
			                    </div>
			                   
		                    </div>
		                    <br>
		                    <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">父机构:</label>
			                        <div class="col-sm-6"> 
			                            <input class="form-control" id="orgSel" type="text" readonly  onclick="showOrg();" />
			                            <input id="parentOrgId" name="parentOrgId" type="hidden" />
			                        </div>
			                </div>
		                    <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">序号:</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="orderId" class="form-control" value="${org.orderId}"   title="" placeholder="序号" />
			                        </div>
			                </div>
		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-8" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-8">
                                    <button class="btn btn-primary" type="submit">提交</button> 
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button">返回</button>
                                </div>
                                 
                            </div>
		                </form>
					</div>
				</div>
				<div id="orgContent"   style="display:none; position: absolute;background-color: #FFFFFF;">
					<ul id="orgTree" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
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
	<script src="<%=jsPath%>/ztree/js/jquery.ztree.all.min.js"></script> 
	<script>
					$('#doback').click(function(){ 
						    location.href = "<%=basePath%>org/index?ac=<%=ac%>&searchId=${searchId}";
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
						$('#dictTree').height(bodyH-80);  
						var zTree = $.fn.zTree.getZTreeObj("orgTree"); 
						zTree.expandAll(true);  
						$('#orgTree').height(200); 
						$('#orgTree').css('width',$('#orgSel').css('width')); 
						
						
						//初始化树
						var zTree = $.fn.zTree.getZTreeObj("orgTree"),
						nodes = zTree.getCheckedNodes(true); 
						$("#orgSel").attr("value", nodes[0].name);
						$("#parentOrgId").attr("value", nodes[0].id);
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
						$("#parentOrgId").attr("value", nodes[0].id);
					}
					
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("orgTree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					} 
		 </script>
		 <script type="text/javascript">
		 	function checkType (){
		 		var type = $("input[name='orgType']:checked").val();
		 		if(type == '0'){
		 			document.getElementById("city").style.display = 'block';
		 			document.getElementById("classSc").style.display = 'none';
		 		}else if(type == '2'){
		 			document.getElementById("city").style.display = 'none';
		 			document.getElementById("classSc").style.display = 'block';
		 		}
		 	}
		 </script>
</body>
</html>