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
					<div class="col-md-12"> 
						<section class="panel">
						      <div class="panel-body"> 
							    <div class="form-horizontal">
							     <form  action="<%=basePath%>train/trainIndex?ac=<%=ac%>" method="post" name="searchForm" id="searchForm">
								      <div class="form-group">
				                        <label class="col-sm-1 col-sm-1 control-label">搜索：</label>
				                        <div class="col-sm-8">
				                            <input type="text" name="searchName" class="form-control tooltips"  value="${searchName}" placeholder="培训名称" />
				                        </div>
				                        <div class="col-sm-2">
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                           <button type="button" class="btn btn-primary" id="addTrain">新增<i class="fa fa-plus"></i></button> 
				                        </div>
	 			                       </div>
 			                           <input id="pageNo" name="pageNo" type="hidden" value="${pageBt.pageNo}" />
					                   <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" />  
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th>培训id</th>
												<th>培训名称</th>
												<th>培训内容</th>
												<th>培训时间</th>
												<th>授课人</th>
												<th>培训地点</th>
												<th>培训单位</th>
												<th>操作</th> 
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="train" items="${pageList}" >
										   		<tr class="gradeX" id="train_${train.train_id }">
										   			<td>
										   			  ${train.train_id }
										   			  
										   			</td>
										   			<td>${train.train_name }</td>
										   			<td>${train.train_content }</td>
										   			<td>${train.train_start_time }至${train.train_end_time }</td>
										   			<td>${train.train_tec }</td>
										   			<td>${train.train_address }</td>
										   			<td>${train.train_dept }</td>
										   			<td>
										   			<a href="javascript:void(0);" onclick="edit('${train.train_id }')"><i class="fa fa-btn fa-pencil"></i>修改</a>
										   			<a href="javascript:void(0);" onclick="delTrain('${train.train_id }')"><i class="fa fa-btn fa-exchange">删除</i></a>
										   			<a href="javascript:void(0);" onclick="chooseChefs('${train.train_id }')"><i class="fa fa-btn fa-exchange">学员管理</i></a>
										   			<a href="javascript:void(0);" onclick="scoreChefs('${train.train_id }')"><i class="fa fa-btn fa-exchange">评分</i></a>
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
	<script src="<%=jsPath %>/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<!--tree-->  
    <script>        
          $(function(){
			$('#addTrain').click(function() { 
				location.href="<%=basePath %>train/trainAdd?ac=300011";
			}); 
		}); 
		function edit(id){  
		      $('#searchForm').attr('action','<%=basePath%>train/trainEdit?ac=300011&id='+id);
		      $('#searchForm').submit();
		   }
		function chooseChefs(id){  
		      $('#searchForm').attr('action','<%=basePath%>train/trainChefIndex?ac=300011&train_id='+id);
		      $('#searchForm').submit();
		   }
		function scoreChefs(id){  
		      $('#searchForm').attr('action','<%=basePath%>train/queryTrainScoreList?ac=300011&train_id='+id);
		      $('#searchForm').submit();
		   }
		   
		function delTrain(id){   
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>train/remove',
							   data: {'id':id},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#train_'+id).remove();
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
                    }           
		 </script>
</body>
</html>