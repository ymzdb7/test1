<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

								  <form  action="<%=basePath %>comment/index" method="post" name="searchForm" id="searchForm" style="margin-bottom: 10px;position: fixed">
									  <input id="pageNo" name="pageNum" type="hidden" value="${pageBt.pageNo}" />
									  <input id="pageSize" name="pageSize" type="hidden" value="${pageBt.pageSize}" />
									  <input id="articleId" name="articleId" type="hidden" value="${articleId}" />
                                      <button class="btn btn-primary" type="button" onclick="back()"><i class="fa fa-angle-double-left"></i>返回</button><br>
								  </form>
								  <ul class="chats normal-chat" style="margin-top:50px ">
									  <c:forEach var="c" items="${list}" >
										  <li class="in">
											  <img src="<%=basePath%>${c.createUserAvatar}" alt="" class="avatar">
											  <div class="message ">
												  <span class="arrow"></span>
												  <a class="name" href="#">${c.createUserName}</a>
												  <span class="datetime">${fn:substring(c.createTime,0,16 )  }</span>
												  <span class="body" >
                                            		 ${c.content}
                                        	  		<div style="width:120px;float:right"><a onclick="showReply(this)" href="javascript:void(0)">查看回复(${fn:length(c.replyList)})</a>&nbsp;<a onclick="delComment(${c.id})" href="javascript:void(0)">删除</a></div>
													<div class="panel" style="max-height: 300px;overflow: auto;display: none;width:80%;margin-left: 20%">
														<c:forEach var="r" items="${c.replyList}">

														<img src="<%=basePath%>${r.createUserAvatar}" alt="" class="avatar" style="">
														<div class="message " style="">
														  <span class="arrow"></span>
														  <a class="name" href="javascript:void(0)">${r.createUserName}</a><c:if test="${!empty r.repliedUserName}">&nbsp;回复:&nbsp;<a class="name" href="javascript:void(0)">${r.repliedUserName}</c:if></a>
														  <span class="datetime">${fn:substring(r.createTime,0,16 )  }</span>
														  <span class="body">
															  ${r.content}
                                                              <div style="width:50px;float:right"><a onclick="delComment(${r.id})" href="javascript:void(0)">删除</a></div>
														  </span>
														</div>
															 <div style="height:10px"></div>
														</c:forEach>
													</div>
											  </span>
											  </div>
										  </li>
									  </c:forEach>
								  </ul>
								  <c:if test="${pageBt.count>10}"><div style="float: right;margin: 0px 20px 0px 0px;">${pageBt}</div></c:if>
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
                    function showReply(obj){
                    	$(obj).parent().next().toggle();
					}
					function delComment(id){
					    var delc = function(v,h,f){
					        if(v){
                                $.ajax({
                                    type:'post',
                                    async:false,
                                    url:'<%=basePath%>comment/deleteComment',
                                    data:{"id":id},
                                    success:function (data) {
                                        if(data.status==200){
                                            $.jBox.success("删除成功！");
                                            location.reload();
                                        }else {
                                            $.jBox.error("系统错误，请重新操作！");
                                        }
                                    }
                                })
                            }
                            return true;

                        }
					    $.jBox.confirm("确定删除该条评论/回复？","提示：",delc,{ buttons: { '确定': true, '取消': false } });
                    }

                    function back(){
                        location.href="<%=basePath%>article/contentIndex?ac=${ac}&order_type=${order_type}&file_type=${file_type}";
                    }
					 
		 </script>
</body>
</html>