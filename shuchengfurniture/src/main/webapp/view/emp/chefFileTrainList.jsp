<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../common/head.jsp"></jsp:include>
<body >

		<div >
			<!-- header section end-->
			<!-- page heading start -->
			<!-- 此处点击菜单切换可考虑放置菜单名也可仅在首页的时候展示 -->
			<!-- page heading end-->
			<!--body wrapper start-->
			<!-- 此处是点击菜单后，菜单内容填充地 -->

				<div class="row"> 
					<div class="col-md-12"> 
						<section class="panel">
						      <div class="panel-body"> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
										<thead>
											<tr>
												<th>编号</th>
												<th>课程名称</th>
												<th>授课人</th>
												<th>厨师</th>
												<th>分数</th>
											</tr>
										</thead>
										<tbody> 
										   	<c:forEach var="trainScore" items="${pageList}" >
										   		<tr class="gradeX" id="score_${trainScore.id}">
										   			<td>
										   			  ${trainScore.id}
										   			  <input type="hidden" name="id" value="${trainScore.id}"/>
										   			</td>
										   			<td>${trainScore.train_name }</td>
										   			<td>${trainScore.train_tec }</td>
										   			<td>${trainScore.chef_name }</td>
										   			<td>${trainScore.score }</td>
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
</body>
</html>