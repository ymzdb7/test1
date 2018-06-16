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
							    <form  action="<%=basePath %>news/carouselIndex" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
 			                        <div class="col-sm-1" style="text-align: right;">
										<button type="button" class="btn btn-primary" id="addCarousel" >新增<i class="fa fa-plus"></i></button>
									</div>
 			                       </div>
 			                        <input id="pageNo" name="pageNo" type="hidden" value="${pageBt.pageNo}" />
					                <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" /> 
					                <input id="type" name="type" type="hidden" value="0" />   
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th style="width:40px"></th>
												<th>标题</th>
												<th>作者</th>
												<th>排序</th>
												<th>发布时间</th>
												<th>是否上线</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="news" items="${list}" varStatus="status">
										   		<tr class="gradeX" id="news_${news.id}">
										   			<td align="center">
										   			  ${status.index+1}
										   			  <input type="hidden" name="id" value=" ${news.id}"/>
										   			</td>
										   			<td align="center">${news.title}</td>
										   			<td align="center">${news.author}</td>
										   			<td align="center">
										   			  ${news.orderId}
										   			</td>
										   			<td align="center"><fmt:formatDate value="${news.publishTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										   			<td style="text-align: center;" id="isOnline_${news.isOnline}">
										   			   <c:if test="${news.isOnline eq 0}">否</c:if> 
										   			   <c:if test="${news.isOnline eq 1}">是</c:if> 
										   			</td>
										   			<td style="text-align: center;">
										   				<a href="javascript:void(0);" onclick="edit('${news.id}')"><i class="fa fa-btn fa-pencil"></i>编辑</a>
										   			    <a href="javascript:void(0);" onclick="del('${news.id}')"><i class="fa fa-btn fa-pencil"></i>删除</a>
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
    <script>
   		 function del(id){
					if(confirm('是否确认删除？')){   
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>news/remove',
							   data: {'news_id':id},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#news_'+id).remove();
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
						}
        }
     function edit(id){  
            location.href = "<%=basePath%>news/toEditNews?ac=<%=ac%>&type=${type}&id="+id;  
     } 
     $(document).ready(function() { 
			$('#addCarousel').click(function(){ 
				 location.href = "<%=basePath%>news/addCarousel?ac=<%=ac%>&type=${type}";
			});
		});		 
	</script>
</body>
</html>