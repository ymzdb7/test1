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

<body class="sticky-header" >

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
							    <form  action="<%=basePath %>article/subjectList?ac=<%=ac %>&id=${article_id}" method="post" name="searchForm" id="searchForm">
							      <div class="form-group">
			                       <div class="col-sm-3" style="text-align: right;" > 
			                       		<button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
										<button type="button" class="btn btn-primary" id="addSubject" >新增<i class="fa fa-plus"></i></button>
										<button type="button" class="btn btn-primary" id="doback" >返回</button>
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
												<th>答题序号</th>
												<th>答题类型</th>
												<th>题目</th>
												<th>答案</th>
												<th>出题人</th>
												<th>备注</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="subject" items="${pageList}" varStatus="status">
										   		<tr class="gradeX" id="subject_${subject.subjectId}">
										   			<td>
										   			 ${subject.indexNum}
										   			  <input type="hidden" name="id" value=" ${subject.subjectId}"/>
										   			</td>
										   			<td style="text-align: center;"   id="subjectType_${subject.subjectId}">
										   			   <c:if test="${subject.subjectType eq 0}">判断题</c:if> 
										   			   <c:if test="${subject.subjectType eq 1}">单选题</c:if> 
										   			   <c:if test="${subject.subjectType eq 2}">多选题</c:if> 
										   			</td>
										   			<td>${subject.subjectContent }</td>
										   			<td style="text-align: center;" class="answer"  id="answer_${subject.subjectId}">
										   			${subject.answer}
										   			
										   			</td>
										   			<td>${subject.createUserId }</td>
										   			<td>${subject.msg }</td>
										   			<td style="text-align: center;">
										   			<a href="javascript:void(0);" onclick="edit('${subject.subjectId}')"><i class="fa fa-btn fa-pencil"></i>修改</a>
										   			<a href="javascript:void(0);" onclick="del('${subject.subjectId}')"><i class="fa fa-btn fa-pencil"></i>删除</a>
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
     
    	$(document).ready(function() { 
			$('#addSubject').click(function(){ 
				 location.href = "<%=basePath%>article/toAddSubject?ac=<%=ac%>&article_id=${article_id}";
			});
			$('#doback').click(function(){ 
				history.go(-1)					
		});
		});	
		function edit(id){
		   location.href = "<%=basePath%>article/toEditSubject?ac=<%=ac%>&article_id=${article_id}&subjectId="+id;
        }
		function del(id){
					if(confirm('是否确认删除？')){   
                        $.ajax({ type: 'POST',
							   async: true,
							   cache: false,
							   url:  '<%=basePath%>article/removeSubject',
							   data: {'subject_id':id},
							   dataType: 'text',
							   error:function (data, textStatus) { 
								     top.$.jBox.closeTip();
								     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
								     return;
							   },success:function (data,textStatus){ 
							      var json =   $.parseJSON(data); 
								  if(json.status==1){ 
								        $('#subject_'+id).remove();
									    return false;
								  }else{ 
									 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
								  }
							   }      
						});
						}
                    }			 
	</script>
</body>
</html>