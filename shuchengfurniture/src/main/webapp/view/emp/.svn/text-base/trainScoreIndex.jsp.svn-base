<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>

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
							     <form  action="" method="post" name="searchForm" id="searchForm">
								      <div class="form-group">
			                        <label class="col-sm-2 col-sm-2 control-label">搜索：</label>
			                        <div class="col-sm-6">
			                            <input type="text" name="searchName" value="${searchName}"  class="form-control tooltips" data-trigger="hover" data-toggle="tooltip" title="" placeholder="请输入厨师姓名" data-original-title="厨师姓名">
			                        </div>
			                        <div class="col-sm-4" style="text-align: right;">
			                               <button type="button" class="btn btn-primary" onclick="doback()">返回<i class="fa fa-level-up"></i></button>
			                               <button type="submit" class="btn btn-primary">搜索<i class="fa fa-search"></i></button>
<!-- 				                           <button type="button" class="btn btn-primary" id="score">批量评分<i class="fa fa-plus"></i></button>  -->
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
												<th><input name="checkall" type="checkbox" value="" /></th>
												<th>编号</th>
												<th>课程名称</th>
												<th>授课人</th>
												<th>厨师</th>
												<th>分数</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="trainScore" items="${pageList}" >
										   		<tr class="gradeX" id="score_${trainScore.id}">
										   			<td align="center"><input name="usercheck" type="checkbox" value="${trainScore.id}" /></td>
										   			<td>
										   			  ${trainScore.id}
										   			  <input type="hidden" name="id" value="${trainScore.id}"/>
										   			</td>
										   			<td>${trainScore.train_name }</td>
										   			<td>${trainScore.train_tec }</td>
										   			<td>${trainScore.chef_name }</td>
										   			<td>${trainScore.score }</td>
										   			<td style="text-align: center;">
										   			    <a href="javascript:void(0);" onclick="scoreEdit('${trainScore.id}')"><i class="fa fa-btn fa-pencil"></i>评分</a>
										   			    <div id="s_${trainScore.id}" style="display: none;" >
										   			     <input  type="text" id="scoreText_${trainScore.id}"   style="width: 100px;height: 30px;"  name="trainScore.score" value='${trainScore.score}' />
										   			     <button type="button" class="btn" onclick="saveScore('${trainScore.id}');"><i class="fa fa-check-square"></i></button>
										   			    </div>
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
    <script type="text/javascript">
		$(function(){
			$("input[name='checkall']").click(function(){
						if($(this).attr('checked')=='checked'){
							$("[name='usercheck']").attr("checked",'true');//全选
						}else{
							$("[name='usercheck']").removeAttr("checked");//全不选
						}
		    });
			$('#score').click(function() { 
				  //转向网页的地址;
			      var url ="<%=basePath%>train/scoreGive?ac=300011?ids=333"; 
				  var name = "评分"; //网页名称，可为空;
				  var iWidth = 420; //弹出窗口的宽度;
				  var iHeight = 200; //弹出窗口的高度;
				  var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
		          var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
			      window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,status=no');
			      return false;
			}); 
		});
		function scoreEdit(id){   
		     $('#s_'+id).css('display','');
		     return false;
		} 
		
		
		function saveScore(id){ 
		     $.ajax({ type: 'POST',
					  async: true,
					  cache: false,
					  url:  '<%=basePath%>train/saveScore',
					  data: {"score":$('#scoreText_'+id).val(),'id':id},
						      dataType: 'text',
							  error:function (data, textStatus) { 
									     top.$.jBox.closeTip();
									     top.$.jBox.tip("操作失败，请重试！","error",{persistent:true,opacity:0}); 
									     return;
							  },success:function (data,textStatus){ 
								      var json =   $.parseJSON(data); 
									  if(json.status==1){ 
									 	 $('#searchForm').submit();
									  }else{ 
										 top.$.jBox.tip(json.message,"error",{persistent:true,opacity:0});     
									  }
							 }      
			 })
		}
		
		
		function doback(){
			history.go(-1);
		}
		 
</script>

</body>
</html>