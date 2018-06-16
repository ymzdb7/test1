<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<!--common scripts for all pages-->
</head>
<body class="sticky-header" onload="initDivOnline();initReadLimit();initDivAuthor();initDivIndustry();">

	<section>
		<!-- left side start 左侧菜单导航-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="index.html"><img src="<%=imgPath %>/logo.png" alt="">
				</a>
			</div>

			<div class="logo-icon text-center">
				<a href="index.html"><img src="<%=imgPath %>/logo_icon.png"
					alt=""> </a>
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
			<!-- page heading start-->
			  <div class="page-heading">
		            <h3>修改文章 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" id="form1" method="post" action="../article/saveEditArticle?ac=${ac}" enctype="multipart/form-data">
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">文档压缩包:</label>
		                        <div class="col-sm-4">
		                       		<c:if test="${not empty article.spare_3}">  
										${article.spare_3 }
									</c:if> 
									<br/>
		                            <input type="file"  name="zip" id="zip"  value=""/>
		                        </div>
		                        <label class="col-sm-1  control-label">缩略图:</label>
		                        <div class="col-sm-4">
		                        	<c:if test="${not empty article.small_pic_path}">  
										<img src="<%=basePath2%>${article.small_pic_path }" style="width:100px;height:50px;">
									</c:if> 
									<br/>
		                            <input type="file"  name="pic" id="pic"  value=""/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                     	<label class="col-sm-2  control-label">作者:</label>
		                        <div class="col-sm-4">
		                         <select id="author_name" name="author_name" class="form-control tooltips">  
                                    <c:forEach var="author" items="${authorList}" >
                                    	<option value="${author.dir_name }" selected>  ${author.dir_name }  </option>     
                                    </c:forEach>              
                                  </select>  
		                        </div>
		                     	 <label class="col-sm-1  control-label">权限:</label>
		                        <div class="col-sm-4">
		                         	<select id="read_limit" name="read_limit" class="form-control tooltips">  
                                    	<option value="1" >非注册用户</option>
                                    	<option value="5" >注册用户 </option>     
                                    	<option value="0">会员 </option>        
                                  </select>  
		                        </div>
		                    </div>
		                     
		                    <div class="form-group">
		                      <label class="col-sm-2  control-label">是否为精选:</label>
		                        <div class="col-sm-4">
		                        	
		                            <label class="checkbox-inline">
		                                <input name="is_jx"  type="radio" onclick="changeJxType(1);"  value="1" <c:if test="${article.is_jx eq 1  }">checked="checked"</c:if> >是
		                            </label>
		                            <label class="checkbox-inline">
		                                <input name="is_jx"  type="radio" onclick="changeJxType(0);"  value="0" <c:if test="${article.is_jx eq 0||article.is_jx==null}">checked="checked"</c:if>>否
		                            </label>
		                       </div>
		                       <label class="col-sm-1  control-label">行业:</label>
		                        <div class="col-sm-4">
		                         <select id="industry_name" name="industry_name" class="form-control tooltips">  
                                    <c:forEach var="industry" items="${industryList}" >
                                    	<option value="${industry.dir_name }" selected>  ${industry.dir_name }  </option>     
                                    </c:forEach>              
                                  </select>  
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-sm-2  control-label">是否上线:</label>
		                        <div class="col-sm-4">
		                           
		                            <label class="checkbox-inline">
		                                <input name="is_online"  type="radio" value="1" onclick="changeStryle(1);" <c:if test="${article.is_online eq 1 }">checked="checked"</c:if> >是
		                            </label>
		                             <label class="checkbox-inline">
		                                <input name="is_online"  type="radio" value="0" onclick="changeStryle(0);" <c:if test="${article.is_online eq 0||article.is_online==null}">checked="checked"</c:if>>否
		                            </label>
		                        </div>
			                    <div id="publishId" style="display: inline;">
			                        	 <label class="col-sm-1  control-label">发布模式:</label>
				                        <div class="col-sm-4">
				                        
				                            <label class="checkbox-inline">
				                                <input name="online_type" onclick="changeDate(1)" type="radio" onclick="" value="1" <c:if test="${article.online_type eq 1||article.online_type==null  }">checked="checked"</c:if>  >立即发布
				                            </label>
				                            <label class="checkbox-inline">
				                                <input name="online_type"  type="radio" onclick="changeDate(0)" onclick="" value="0" <c:if test="${article.online_type eq 0  }">checked="checked"</c:if>>定时发布
				                            </label>
				                        </div>
			                    </div>
		                    
		                    </div>
		                    <div class="form-group">
			                    <div id="publishDate" class="form-group" style="display: inline;">
			                        <label class="col-sm-2  control-label">发布时间:</label>
			                        <div class="col-sm-4">
			                         <input type="text" name="online_date" id="online_date" class="form-control tooltips" value="${ article.online_date}" 
					                              onFocus="WdatePicker({isShowClear:false,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"  />
			                        </div>
			                    </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">标题:</label>
		                        <div class="col-sm-9">
		                        <input type="text" name="title" class="form-control" value="${article.title}"/> 
		                        <input type="hidden" name="small_pic_path" id="small_pic_path" class="form-control" value="${ article.small_pic_path}" /> 
		                        <input type="hidden" name="small_pic_save_name" id="small_pic_save_name" class="form-control" value="${ article.small_pic_save_name}"/> 
		                        <input type="hidden" name="small_pic_prior_name" id="small_pic_prior_name" class="form-control" value="${ article.small_pic_prior_name}"/> 
		                        <input type="hidden" name="spare_3" id="spare_3" class="form-control" value="${ article.spare_3}"/> 
		                        <input type="hidden" name="id" id="id" value="${article.id}" class="form-control" /> 
		                        </div>
		                    </div>
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">关键词:</label>
		                        <div class="col-sm-9">
		                        <input type="text" name="key_words" class="form-control" value="${article.key_words}"/> 
		                        </div>
		                    </div>
		                     
		                    <div class="form-group">
		                        <label class="col-sm-2  control-label">简介:</label>
		                        <div class="col-sm-9">
		                           <textarea class="form-control" name="summary" rows="5" cols="120">${article.summary}</textarea>
		                        </div>
		                    </div>
     						<div class="form-group" style="text-align: center;">
     						    <div class="col-lg-11"><font  color="red">${msg}</font></div>
                                <div class="col-lg-11">
                                    <button class="btn btn-primary" id="save">提交</button> 
                                    &nbsp;&nbsp;
                                    <button class="btn btn-primary" id="doback" type="button">返回</button>
                                </div>
                                 
                            </div>
		                </form>
					</div>
				</div>
				
			</div>
			
			<!--body wrapper end-->
			<!--footer section start-->
			<jsp:include page="../common/company.jsp"></jsp:include>
			<script src="<%=jsPath %>/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
			<!--footer section end-->
		</div>
		<!-- main content end-->
	</section>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	 
		<script>
					var date1='';
		 			var now = new Date();
					var year = now.getFullYear(); //getFullYear getYear
					var month = now.getMonth()+1;
					if(month<10){
						month="0"+month;
					}
					var date = now.getDate();
					if(date<10){
						date="0"+date;
					}
					var day = now.getDay();
					if(day<10){
						day="0"+day;
					}
					var hour = now.getHours();
					if(hour<10){
						hour="0"+hour;
					}
					var minu = now.getMinutes();
					if(minu<10){
						minu="0"+minu;
					}
					date1=year+'-'+month+'-'+date+' '+hour+':'+minu;	
					$('#doback').click(function(){ 
						    location.href = "<%=basePath %>article/contentIndex?ac=${ac}&order_type=${order_type}&file_type=${file_type}";
					 });
				function changeStryle(type){
					 var publishDiv=document.getElementById("publishId");
					 var publishDate=document.getElementById("publishDate");
					 var online_type=document.getElementsByName("online_type");
						 if(type==1){
						 publishDiv.style.display='inline';
							 for(var i=0;i<online_type.length;i++){
							 if(online_type[i].value=="1"){
							  online_type[i].checked=true;
							 document.getElementById("online_date").value=date1;
							 	}
							 }
						 }
						 if(type==0){
						 publishDiv.style.display='none';
						 publishDate.style.display='none';
						 document.getElementById("online_date").value='';
						 document.getElementsByName("online_type").value='';
						 }
					 }
					 function changeDate(type){
					 var publishDate=document.getElementById("publishDate");
						 if(type==1){
						  publishDate.style.display='none';
						 document.getElementById("online_date").value=date1;
						 }
						 if(type==0){
						 publishDate.style.display='inline';
						 }
					 }
					 function initDivOnline(){
					 var is_online='${article.is_online}';
					 var onlineType='${article.online_type}';
					 if(is_online==0){
						 document.getElementById("publishId").style.display='none';
					 	 document.getElementById("publishDate").style.display='none';
					 }else{
					 	if(onlineType==1){
					 	 document.getElementById("publishDate").style.display='none';
					 	}
					 }
					 }
					  function initDivAuthor(){
						 var name='${article.author_name}';
						 var author_names=document.getElementById("author_name");
						 for(var i=0;i<author_names.options.length;i++){
							if(author_names.options[i].value==name){
								author_names.options[i].selected=true;
								break;
							}
						}
					 }
					 function initDivIndustry(){
						 var name='${article.industry_name}';
						 var industry_name=document.getElementById("industry_name");
						 for(var i=0;i<industry_name.options.length;i++){
							if(industry_name.options[i].value==name){
								industry_name.options[i].selected=true;
								break;
							}
						}
					 }
					 function initReadLimit(){
						var read_limit=document.getElementById("read_limit");
						for(var i=0;i<read_limit.options.length;i++){
							if(read_limit.options[i].value==${article.read_limit}){
								read_limit.options[i].selected=true;
								break;
							}
						}
					}
		 </script>
</body>
</html>