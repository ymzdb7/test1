<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
<script type="text/javascript" src="<%=jsPath%>/My97DatePicker/WdatePicker.js"></script>
<!--common scripts for all pages--> 
</head>
<script type="text/javascript">
function addTr1(){
		var stufftab=document.getElementById("tab1");
  		var i=stufftab.rows.length;
  		var addrow=stufftab.insertRow(i);//添加行
  		var td1=addrow.insertCell(0);//添加列
  		var str1='<br/><div class="col-sm-5">';
  		str1+='<input type="text" class="form-control tooltips" name="hc_date_start"  autocomplete="off"   onclick="WdatePicker({dateFmt:&quot yyyy-MM-dd &quot})" readonly="readonly"  ></div>';
  		str1+=' <label class="col-sm-1  control-label">~</label><div class="col-sm-5">';
  		str1+='<input type="text" class="form-control tooltips" name="hc_date_end"  autocomplete="off"    onclick="WdatePicker({dateFmt:&quot yyyy-MM-dd &quot})" readonly="readonly"  ></div>';
		str1+='<div class="col-sm-1">';
		str1+='<button style="display: inline;" class="btn btn-default" onclick="addTr1();" type="button">添加</button></div>';			      			
  		td1.innerHTML=str1;
	
		}

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
		            <h3>编辑厨师 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" id="add_frm" action="chefModify.do"
			namespace="/chef" method="post" enctype="multipart/form-data">
		                    
		                     <div class="form-group">
		                        <label class="col-sm-2  control-label">姓名:</label>
		                        <div class="col-sm-4">
		                        	<input id="chef_id" name="chef_id" type="hidden" value="${chef.chef_id}"/>
		                            <input type="text"  class="form-control tooltips" name="name" value="${chef.name}"/>
		                        </div>
		                        <label class="col-sm-1  control-label">性别:</label>
		                        <div class="col-sm-4">
		                             <label class="checkbox-inline">
		                                   <input name="sex"  type="radio" value="1"   <c:if test="${chef.sex eq 1 ||chef.sex==null }">checked="checked"</c:if> >男
		                              </label> 
								      <label class="checkbox-inline">
		                                   <input name="sex"  type="radio" value="0"   <c:if test="${chef.sex eq 0}">checked="checked"</c:if>>女
		                              </label> 
		                        </div>
		                    </div>
		                     <div class="form-group">
		                        <label class="col-sm-2  control-label">身份证号:</label>
		                        <div class="col-sm-4">
		                            <input type="text" name="idcard" class="form-control tooltips" value="${chef.idcard }"/>
		                        </div>
		                        <label class="col-sm-1  control-label">联系电话:</label>
		                        <div class="col-sm-4">
		                            <input type="text" name="phone" class="form-control tooltips" value="${chef.phone }"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">家庭住址:</label>
		                        <div class="col-sm-4">
		                            <input class="form-control" id="orgSel1" type="text" readonly  onclick="showOrg1();" />
		                            <input id="orgId1" name="orgId1" type="hidden" />
		                             <input id="address_village" name="address_village" type="hidden" value="${chef.address_village}"/>
		                            <input id="address_town" name="address_town" type="hidden" value="${chef.address_town}"/>
		                        </div>
		                        <label class="col-sm-1  control-label">详细地址:</label>
		                        <div class="col-sm-4">
		                            <input type="text" name="chef_address_detail" class="form-control tooltips" value="${chef.chef_address_detail }"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-2  control-label">文化程度:</label>
		                    	
		                        <div class="col-sm-4">
		                            <input type="text" name="qualifications" class="form-control tooltips" value="${chef.qualifications }"/>
		                        </div>
		                        <label class="col-sm-1  control-label">照片:</label>
		                        <div class="col-sm-4">
		                           <img src='<%=basePath2%>${chef.idpic}' width="145" height="130" />
		                        </div>
		                    </div>
		                     <div class="form-group">
		                        <label class="col-sm-2  control-label">健康证有效期:</label>
		                        <div class="col-sm-8">
		                            <table id="tab1">
										<tr>
										 <td>
										 <c:forEach var="his" items="${hisList}" varStatus="status">
											<div class="col-sm-5">
					                            <input type="text" class="form-control tooltips" name="hc_date_start" value="${his.spare_1 }" autocomplete="off"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
					                        </div>
					                        <label class="col-sm-1  control-label">~</label>
					                        <div class="col-sm-5">
					                            <input type="text" class="form-control tooltips" name="hc_date_end" value="${his.spare_2 }" autocomplete="off"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
					                        </div>
					                        <div class="col-sm-1">
					                            <button style="display: inline;" class="btn btn-default" onclick="addTr1();" type="button">添加</button>
					                        </div>
										</c:forEach>
											
					                        
										 </td>
											 
										</tr>
									</table>
		                        </div>
		                    </div>
		                     <div class="form-group">
		                        <label class="col-sm-2  control-label">主要活动服务区域:</label>
		                        <div class="col-sm-9">
		                            <input class="form-control" id="orgSel2" type="text" readonly  onclick="showOrg2();" />
		                            <input id="orgId2" name="orgId2" type="hidden" />
		                            <input id="villages" name="villages" type="hidden" />
		                            <input id="towns" name="towns" type="hidden" />
		                        </div>
		                    </div>
		                     <div class="form-group">
		                        <label class="col-sm-2  control-label">传染性疾病史:</label>
		                        <div class="col-sm-9">
		                            <textarea rows="4" name="disease"  class="form-control">${chef.disease}</textarea>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">登记意见:</label>
		                        <div class="col-sm-9">
		                            <textarea rows="4" name="opinion"  class="form-control">${chef.opinion }</textarea>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">管理记录:</label>
		                        <div class="col-sm-9">
		                            <textarea rows="4" name="mange_record"  class="form-control">${chef.mange_record }</textarea>
		                        </div>
		                    </div>
		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-10" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-12">
                                    <button class="btn btn-primary" type="submit">提交</button> 
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button">返回</button>
                                </div>
                                 
                            </div>
							
		                </form>
					</div>
				</div>
				<div id="orgContent1"   style="display:none; position: absolute;background-color: #FFFFFF;">
					<ul id="orgTree1" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
				</div>
				<div id="orgContent2"   style="display:none; position: absolute;background-color: #FFFFFF;">
					<ul id="orgTree2" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
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
	<!-- 地址树 -->
	<script type="text/javascript">
	$('#doback').click(function(){ 
						    location.href = "<%=basePath%>chef/chefIndex?ac=<%=ac%>";
					 });
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
					var zNodes = ${treejson1};
					function onCheck(e, treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("orgTree1"),
						nodes = zTree.getCheckedNodes(true); 
						$("#orgSel1").attr("value", nodes[0].name);
						$("#orgId1").attr("value", nodes[0].id);
						$("#address_village").attr("value", nodes[0].id);
						$("#address_town").attr("value", nodes[0].pId);
					}
					
					function beforeClick(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("orgTree1");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					}
					function showOrg1() {
						var dictObj = $("#orgSel1");
						var dictOffset = $("#orgSel1").offset();
						$("#orgContent1").css({left:dictOffset.left + "px", top:dictOffset.top + dictObj.outerHeight() + "px"}).slideDown("fast");
			            $("body").bind("mousedown", onBodyDown);
					} 
					
					function onBodyDown(event) {
					   if ($(event.target).parents("#orgContent1").length==0) {
							hideMenu();
					   }  
					}
					function hideMenu() {
						$("#orgContent1").fadeOut("fast");
						$("body").unbind("mousedown", onBodyDown);
					} 
		$(document).ready(function(){
			$.fn.zTree.init($("#orgTree1"), setting, zNodes);
			//初始化树
						var zTree = $.fn.zTree.getZTreeObj("orgTree1"),
						nodes = zTree.getCheckedNodes(true); 
						$("#orgSel1").attr("value", '${chef.address_town }');
		}); 				
	</script>
	<!-- 活动区域树 -->
	<script type="text/javascript">
	var setting2 = {
					    check: {
							enable: true,
							chkStyle: "checkbox",
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
							onCheck: onCheck2,
							beforeClick: beforeClick2
						}

					}; 
					var zNodes2 = ${treejson2};
					function onCheck2(e, treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("orgTree2"),
						nodes = zTree.getCheckedNodes(true);
						var noIds="";
						var noNames="";
						for(var i=0;i<nodes.length;i++){
				            noNames+=nodes[i].name + ",";
				            noIds+=nodes[i].id + ",";
				            }
				         if(noNames.length>1){
				         noNames=noNames.substring(0,noNames.length-1);
				         noIds=noIds.substring(0,noIds.length-1);
				         } 
						$("#orgSel2").attr("value", noNames);
						$("#orgId2").attr("value", noIds);
						$("#villages").attr("value", noNames);
						$("#towns").attr("value", noIds);
					}
					
					function beforeClick2(treeId, treeNode) {
			            var zTree = $.fn.zTree.getZTreeObj("orgTree2");
						zTree.checkNode(treeNode, true, null, true);
						return false;
					}
					function showOrg2() {
						var dictObj = $("#orgSel2");
						var dictOffset = $("#orgSel2").offset();
						$("#orgContent2").css({left:dictOffset.left + "px", top:dictOffset.top + dictObj.outerHeight() + "px"}).slideDown("fast");
			            $("body").bind("mousedown", onBodyDown2);
					} 
					
					function onBodyDown2(event) {
					   if ($(event.target).parents("#orgContent2").length==0) {
							hideMenu2();
					   }  
					}
					function hideMenu2() {
						$("#orgContent2").fadeOut("fast");
						$("body").unbind("mousedown", onBodyDown2);
					} 
		$(document).ready(function(){
			$.fn.zTree.init($("#orgTree2"), setting2, zNodes2);
			var zTree = $.fn.zTree.getZTreeObj("orgTree2"),
			nodes = zTree.getCheckedNodes(true); 
			$("#orgSel2").attr("value", '${areaStr}');
		}); 				
	</script>
</body>
</html>