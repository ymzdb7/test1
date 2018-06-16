<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<body>

		<!-- main content start-->
		<div id="main-content" class="main-content">
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" method="post" action="../dict/save?ac=<%=ac%>">
		                    <div class="form-group">
		                        <label class="col-sm-2control-label">分数:</label>
		                        <div class="col-sm-3">
		                            <input type="text"  name="dictId" class="form-control" value="${dict.dictId}"/>
		                        </div>
		                    </div>
		                    <div class="form-group" style="text-align: center;">
                                <div class="col-lg-8">
                                    <button class="btn btn-primary" type="submit">提交</button> 
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button">返回</button>
                                </div>
                                 
                            </div>
		                </form>
					</div>
				</div>
			</div>
			
		</div>
		<!-- main content end-->
	<script>
					$('#doback').click(function(){ 
						    location.href = "<%=basePath%>dict/index?ac=<%=ac%>&searchId=${searchId}";
					 });
					
		 </script>
</body>
</html>