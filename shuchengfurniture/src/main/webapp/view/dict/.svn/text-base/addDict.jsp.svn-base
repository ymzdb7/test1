<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<!--common scripts for all pages-->
<script src="js/scripts.js"></script>
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
		            <h3>新增字典 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="../dict/save?ac=<%=ac%>">
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">字典值:</label>
		                        <div class="col-sm-6">
		                            <input type="text"  name="dictId" class="form-control" value="${dict.dictId}"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">字典名称:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="dictName" class="form-control" value="${dict.dictName}"/>
<!-- 		                            <span class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span> -->
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">父ID:</label>
		                        <div class="col-sm-6"> 
		                            <input class="form-control" id="dictSel" type="text" readonly  onclick="showMenu();" />
		                            <input id="superDictId" name="superDictId" type="hidden" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2 col-sm-2 control-label">序号:</label>
		                        <div class="col-sm-6">
		                            <input type="text" name="orderId" class="form-control" value="${dict.orderId}"   title="" placeholder="序号" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-4 col-sm-2 control-label">是否有效:</label>
		                        <div class="col-sm-6">
		                            <label class="checkbox-inline">
		                                <input name="isInvalid"  type="radio" value="1" <c:if test="${dict.isInvalid eq 1 ||dict.isInvalid==null }">checked="checked"</c:if> >有效
		                            </label>
		                            <label class="checkbox-inline">
		                                <input name="isInvalid"  type="radio" value="0" <c:if test="${dict.isInvalid eq 0}">checked="checked"</c:if>>无效
		                            </label> 
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
				<div id="dictContent"   style="display:none; position: absolute;background-color: #FFFFFF;">
					<ul id="dictTree" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
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
						    location.href = "<%=basePath%>dict/index?ac=<%=ac%>&searchId=${searchId}";
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
						var t = $("#dictTree");
						t = $.fn.zTree.init(t, setting, zNodes); 
						var bodyH = $("#main-content").height(); 
						$('#dictTree').height(bodyH-80);  
						var zTree = $.fn.zTree.getZTreeObj("dictTree"); 
						zTree.expandAll(true);   
						$('#dictTree').height(200); 
						$('#dictTree').css('width',$('#dictSel').css('width')); 
						
						
						//初始化树
						var zTree = $.fn.zTree.getZTreeObj("dictTree"),
						nodes = zTree.getCheckedNodes(true); 
						$("#dictSel").attr("value", nodes[0].name);
						$("#superDictId").attr("value", nodes[0].id);
					});
					 
					function showMenu() {
						var dictObj = $("#dictSel");
						var dictOffset = $("#dictSel").offset();
						$("#dictContent").css({left:dictOffset.left + "px", top:dictOffset.top + dictObj.outerHeight() + "px"}).slideDown("fast");
			            $("body").bind("mousedown", onBodyDown);
					} 
					
					function onBodyDown(event) {
					   if ($(event.target).parents("#dictContent").length==0) {
							hideMenu();
					   }  
					}
					function hideMenu() {
						$("#dictContent").fadeOut("fast");
						$("body").unbind("mousedown", onBodyDown);
					} 
					
					function onCheck(e, treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("dictTree"),
						nodes = zTree.getCheckedNodes(true); 
						$("#dictSel").attr("value", nodes[0].name);
						$("#superDictId").attr("value", nodes[0].id);
					}
					
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("dictTree");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					} 
		 </script>
</body>
</html>