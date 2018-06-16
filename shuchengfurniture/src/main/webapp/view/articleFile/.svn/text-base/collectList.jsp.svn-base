<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<script type="text/javascript" src="<%=jsPath%>/My97DatePicker/WdatePicker.js"></script>

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
							    <form  action="<%=basePath %>article/collectList?ac=500007" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                       <div class="col-sm-2">
			                            <input type="text" name="author_name" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入作者名称" data-original-title="作者名称">
			                       </div>
			                       <div class="col-sm-2">
			                            <input type="text" name="key_words" class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入关键词" data-original-title="关键词">
			                       </div>
			                       <div class="col-sm-2">
			                       		<input type="text" class="form-control tooltips" name="query_date" value="${query_date }" autocomplete="off"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" placeholder="请输入时间" data-original-title="日期" />
			                       </div>
			                        <div class="col-sm-4" style="text-align: right;" > 
			                        <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
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
												<th style="width:40px"></th>
												<th>文章标题</th>
												<th>作者</th>
												<th>是否上线</th>
												<th>收藏数</th>
												<th>图片</th>
												<th>阅读权限</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="article" items="${pageList}" varStatus="status">
										   		<tr class="gradeX" id="article_${article.id}">
										   			<td>
										   			 ${status.index+1}
										   			  <input type="hidden" name="id" value=" ${article.id}"/>
										   			</td>
										   			<td>${article.title }</td>
										   			<td>${article.author_name }</td>
										   			<td style="text-align: center;" id="isOnline_${article.is_online}">
										   			   <c:if test="${article.is_online eq 5}">待发布</c:if> 
										   			   <c:if test="${article.is_online eq 0}">未上线</c:if> 
										   			   <c:if test="${article.is_online eq 1}">已发布</c:if> 
										   			</td>
										   			<td style="text-align: center;">${article.collect_num }</td>
										   			<td align="center">
										   			<c:if test="${not empty article.small_pic_path}">  
													   <img src="<%=basePath2%>${article.small_pic_path }" style="width:100px;height:50px;">
													</c:if>  
										   			
										   			</td>
										   			<td id="isPromotion_${article.read_limit}">
										   			   <c:if test="${article.read_limit eq 0}">会员</c:if>
										   			   <c:if test="${article.read_limit eq 5}">注册用户</c:if>  
										   			   <c:if test="${article.read_limit eq 1}">非注册用户</c:if> 
										   			</td>
										   			<td style="text-align: center;">
										   			<c:if test="${article.order_sc ne 0}">  
													   <a href="javascript:void(0);" onclick="updateOrderScqx('${article.id}')"><i class="fa fa-btn fa-pencil"></i>取消置顶</a>
													</c:if> 
													<c:if test="${article.order_sc eq 0}">  
													   <a href="javascript:void(0);" onclick="updateOrderSc('${article.id}')"><i class="fa fa-btn fa-pencil">置顶</i></a>
													</c:if>     
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
	<!--tree--> 
	
    <script>        
                    function updateOrderSc(id){
                     location.href = "<%=basePath%>article/upadteOrderSc?ac=<%=ac%>&id="+id;  
                    }
					function updateOrderScqx(id){ 
					location.href = "<%=basePath%>article/updateOrderScqx?ac=<%=ac%>&id="+id;   
                    } 
		 </script>
</body>
</html>