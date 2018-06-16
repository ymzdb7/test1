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
							    <form  action="<%=basePath%>integration/integIndex?ac=<%=ac %>" method="post" name="searchForm" id="searchForm">
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
									  <div class="col-sm-6"><input hidden></div>
				                        <div class="col-sm-6" style="text-align: right;">
											<button type="button" class="btn btn-primary" onclick="resetForm()">重置</button>
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                           <button type="button" class="btn btn-primary" onclick="expExcel()">导出</button>
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
												<h4 class="modal-title">积分修改</h4>
											</div>
											<div class="modal-body">
												<form role="form"  class="form-horizontal"  id="roleForm">
								                    <div class="form-group">
								                        <label class="col-sm-3 col-lg-3 control-label">积分:</label>
								                        <div class="col-sm-9">
								                            <input type="text" name="integration" class="form-control" id="integration"/>
								                            <input type="hidden" name="memberId" id="memberId"/>
								                            <input type="hidden" name="userName" id="userName"/>
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
												<th>手机号</th>
												<th>会员名</th>
												<th>用户名</th>
												<th>会员类别</th>
												<th>到期时间</th>
												<th>账号状态</th>
												<th>积分</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="m" items="${list}" >
										   		<tr class="gradeX" id="user_${user.userId}">
													<td style="text-align: center">${m.phoneNum}</td>
										   			<td style="text-align: center">
										   			  ${m.userName }
										   			  <input type="hidden" name="userId" value="${m.id}"/>
										   			</td>
										   			<td style="text-align: center">
										   			  ${m.userNameCn }
										   			</td>
													<td  style="text-align: center" id="type_${m.id}"><c:if test="${m.type == '0'}">注册用户</c:if><c:if test="${m.type == '1'}">会员用户</c:if><c:if test="${m.type == '2'}">会员用户</c:if></td>
										   			<td  style="text-align: center"><fmt:formatDate value="${m.expireTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td  style="text-align: center"><c:if test="${m.isVaild == '1'}">有效</c:if><c:if test="${m.isVaild == '2'}">冻结</c:if></td>
													<td id="jf_${m.id}" style="text-align: center">${m.integration}</td>
													<td style="text-align: center;">
														<a href="javascript:void(0);" onclick="edit('${m.id}','${m.integration}','${m.userName }')"><i class="fa fa-btn fa-pencil"></i>积分修改</a>
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
			function edit(memberId,integration,userName){
					$('#integration').val(integration);
					$('#memberId').val(memberId);
					$('#userName').val(userName);
					document.getElementById('dialog').click();
			};
			function editSubmit(){
			
			var memberId = document.getElementById("memberId").value;
			var integration = document.getElementById("integration").value;
			var userName = document.getElementById("userName").value;
			$.ajax({
				type:'post',
				async:false,
				url:'<%=basePath%>integration/updateIntegration',
				data:{'memberId':memberId,'integration':integration,'userName':userName},
				success:function(data){
					if(data.info=="ok"){
						$.jBox.alert("积分修改成功！");
						$('#jf_'+memberId).html(integration);
						document.getElementById('closeBtn').click();
					}else{
						$.jBox.alert("系统出错，请重新提交！");
					}
				}
			})
		};
		
       		 function expExcel(){
		        location.href='<%=basePath%>/integration/expExcelInteg';
		        $('#searchForm').attr("action","<%=basePath%>/integration/expExcelInteg");
		        $('#searchForm').submit();
		    }

					 
		 </script>
</body>
</html>