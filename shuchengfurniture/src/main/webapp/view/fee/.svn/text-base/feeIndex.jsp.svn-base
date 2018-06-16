<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.winhands.cshj.fee.enums.StatEnum" %>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<div class="col-md-12">
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
									<form  action="<%=basePath%>fee/index?ac=<%=ac%>" method="post" name="searchForm" id="searchForm">
										<div class="form-group">
                                                <div class="col-sm-4">
                                                    <select class="col-sm-2 form-control " name="type" id="type-sel">
                                                        <option value="0">请选择会费类别</option>
                                                        <option value="1">半年</option>
                                                        <option value="2">年</option>
                                                    </select>
                                                </div>
											<div class="col-sm-4" >

											</div>
											<div class="col-sm-4" style="text-align: right;">
												<button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
												<button type="button" class="btn btn-primary" id="add" onclick="addClick()">新增<i class="fa fa-plus"></i></button>
											</div>
										</div>

									</form>
								</div>
								<div class="adv-table">
									<a href="#myModal" data-toggle="modal" style="display:none" id="dialog">点击弹出dialog</a>
									<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button aria-hidden="true" data-dismiss="modal"
													class="close" type="button">×</button>
												<h4 class="modal-title">会费配置</h4>
											</div>
											<div class="modal-body">
												<form role="form" id="addForm" class="form-horizontal"  action="<%=basePath%>fee/addFee" method="post">
								                    <div class="form-group">
								                        <label class="col-sm-4 col-lg-4 control-label">会费类别:</label>
								                        <div class="col-sm-6">
								                            <select class="col-sm-2 form-control m-bot15" name="type">
                                                                <option value="1">半年</option>
                                                                <option value="2">年</option>
                                                            </select>
								                        </div>
								                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-4 col-lg-4 control-label">会费:</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" name="fee" class="form-control" id="fee"  title="" />
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-4 col-lg-4 control-label">生效时间:</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" name="effectiveTime" class="form-control"  title=""  onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:00',minDate:'%y-%M-{%d}'})"/>
                                                        </div>
                                                    </div>

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
												<th>会费类别</th>
												<th>费用</th>
												<th>生效时间</th>
                                                <th>状态</th>
												<th>操作</th>

											</tr>
										</thead>
										<tbody id="tbody"> 
										   	<c:forEach var="msf" items="${list }" varStatus="status">
										   		<tr>
													<td style="text-align:center">${status.index+1}</td>
										   			<td style="text-align:center"><c:if test="${msf.type eq '1'}">半年</c:if><c:if test="${msf.type eq '2'}">年</c:if></td>
													<td style="text-align:center">${msf.fee}元</td>
										   			<td style="text-align:center">${fn:substring(msf.effectiveTime,0,16 )}</td>
                                                    <td style="text-align:center">${msf.state}</td>
													<td style="text-align:center"><c:if test="${msf.state != '使用中'}"><a href="javascript:void(0);" onclick="remove('${msf.id}')"><i class="fa fa-btn fa-exchange"></i>删除</a></c:if>
														</td>
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
    <script language="javascript" type="text/javascript" src="<%=jsPath %>/My97DatePicker/WdatePicker.js"></script>
    <script>
        $(function(){
           $('#type-sel').val(${type});
        });
	    function roleCheckClick(obj){
	    	if($(obj).attr("checked")){
	    		$('.roleCheck').prop("checked",false);
	    		$(obj).prop("checked",true);
	    	}
	    };      
	    function addClick(){

			document.getElementById('dialog').click();
	    };
	    
	    function beforeClick(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.checkNode(treeNode, true, null, true);
			return false;
		};
		
		function addSubmit(){
            $.ajax({
                type:'post',
                async:false,
                url:'<%=basePath%>fee/addFee',
                data:$('#addForm').serialize(),
                success:function(data){
                    if(data.status==200){
                        $.jBox.alert("配置成功！");
                        location.href="<%=basePath%>fee/index";
                    }else {
                        $.jBox.alert("系统错误！");
                    }
                }
            })

		};
		

		
		function remove(id){
			$.ajax({
			    type:'post',
                async:false,
                url:'<%=basePath%>fee/deleteFee',
                data:{'id':id},
                success:function(data){
                    if(data.status==200){
                        $.jBox.alert("删除成功！");
                        location.reload();
                    }else{
                        $.jBox.alert("系统错误，请重新操作！");
                    }
                }
            })
		};
              

		
		

                    
                    
                   
					 
		 </script>
</body>
</html>