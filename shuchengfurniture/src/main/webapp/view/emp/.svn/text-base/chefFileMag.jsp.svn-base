<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
							     <form  action="<%=basePath%>chef/chefRecordMsg?ac=<%=ac%>" method="post" name="searchForm" id="searchForm">
								      <div class="form-group">
				                        <label class="col-sm-1 col-sm-1 control-label">搜索：</label>
				                       <div class="col-sm-4">
				                            <input type="text" name="town" class="form-control tooltips"  value="${town}" placeholder="请输入镇（街道）" />
				                       </div>
				                       <div class="col-sm-4">
				                            <input type="text" name="yearName" class="form-control tooltips"  value="${yearName}" placeholder="请输入年份（例如：2016）" />
				                       </div>
				                        <div class="col-sm-2">
				                           <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
				                           <button type="button" id="export" class="btn btn-primary">导出</button>
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
												<th>编号</th>
												<th>姓名</th>
												<th>性别</th>
												<th>年龄</th>
												<th>地址</th>
												<th>联系电话</th>
												<th>健康证有效期</th>
												<th>培训记录</th>
												<th>全年操办酒席次数</th>
												<th>发生食物中毒次数</th>  
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="chef" items="${pageList}" >
										   		<tr class="gradeX" >
										   			<td>${chef.chef_id }</td>
										   			<td>${chef.name}</td>
										   			<td>
										   			<c:if test="${chef.sex eq 0}">女</c:if>
										   			<c:if test="${chef.sex eq 1}">男</c:if>  
										   			</td>
										   			<td>${chef.idcard}</td>
										   			<td>${chef.address_town}${chef.chef_address_detail}</td>
										   			<td>${chef.phone}</td>
										   			<td>
										   			<c:if test="${empty chef.hc_startdate}">暂无记录</c:if>
										   			<c:if test="${not empty chef.hc_startdate}">${chef.hc_startdate}至${chef.hc_enddate}</c:if>
										   			</td>
										   			<td>
										   			<a href="javascript:void(0);" onclick="trainList('${chef.chef_id }')"><i class="fa fa-btn fa-pencil"></i>培训记录</a>
										   			</td>
										   			<td>${chef.spare_2}</td>
										   			<td>${chef.spare_1}</td>
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
	<script type="text/javascript" src="<%=jsPath %>/jBox/jquery.jBox-2.3.min.js"></script>
	 <script type="text/javascript" src="<%=jsPath %>/jBox/i18n/jquery.jBox-zh-CN.js"></script>
	<!--tree-->  
    <script>        
          $(function(){
			$('#export').click(function() { 
				location.href="<%=basePath %>train/trainAdd?ac=300011";
			}); 
		}); 
		function trainList(chef_id){  
		    jBoxConfig.defaults.top = '35%';
		    jBoxConfig.defaults.showScrolling = "no";
    	   	$.jBox.setDefaults(jBoxConfig);
    	   	$.jBox.open("iframe:<%=basePath%>train/queryChefFileTrainScoreList?ac=300012&chef_id="+chef_id, "培训列表",800,374,{ buttons: {} });
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