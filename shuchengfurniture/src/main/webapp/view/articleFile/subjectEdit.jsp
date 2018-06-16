<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"></jsp:include>  
<!--common scripts for all pages-->
<script src="js/scripts.js"></script>
</head>
<script type="text/javascript">


</script>
<body class="sticky-header" onload="initSubject();">

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
		            <h3>编辑答题 </h3> 
		       </div>
			<!-- page heading end-->
             
			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<form class="form-horizontal adminex-form" id="form1" method="post" action="../article/saveEditSubject?ac=<%=ac%>&article_id=${article_id}">
		                    <div class="form-group">
		                        <label class="col-sm-1  control-label">序号:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text" id="indexNum" name="indexNum"  class="form-control" value="${subject.indexNum}" placeholder="请输入题目序号"/>
		                         <input id="chooseItems" name="chooseItems"  type="hidden" value="" />
		                         <input id="sub_answer" name="sub_answer"  type="hidden" value="" />
		                         <input id="subjectId" name="subjectId"  type="hidden" value="${subject.subjectId}" />
		                        </div> 
		                         <label class="col-sm-1  control-label">题型:</label>
		                        <div class="col-sm-6"> 
		                         <label class="checkbox-inline">
		                                <input name="subjectType"  type="radio" value="0" onclick="checkType();" <c:if test="${'0' eq subject.subjectType }" >checked="checked"</c:if> >判断题
		                            </label>
		                            <label class="checkbox-inline">
		                                <input name="subjectType"  type="radio" value="1" onclick="checkType();" <c:if test="${'1' eq subject.subjectType }" >checked="checked"</c:if> >单选题
		                            </label>
		                            <label class="checkbox-inline">
		                                <input name="subjectType"  type="radio" value="2" onclick="checkType();" <c:if test="${'2' eq subject.subjectType }" >checked="checked"</c:if> >多选题
		                            </label>
		                        </div> 
		                   </div>
		                    <div class="form-group">
		                        <label class="col-sm-1  control-label">题目内容:</label>
		                        <div class="col-sm-9">
		                           <textarea class="form-control" name="subjectContent" rows="5" cols="120">${subject.subjectContent}</textarea>
		                        </div>
		                    </div>
		                    <div id="xzt1" class="form-group" style="display: none;">
		                        <label class="col-sm-1  control-label">A:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text"  id="subjectItemsA"  class="form-control" value="" placeholder=""/>
		                        </div> 
		                         <label class="col-sm-1  control-label">B:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text"  id="subjectItemsB"  class="form-control" value="" placeholder=""/>
		                        </div> 
		                   </div>
		                   <div id="xzt2" class="form-group" style="display: none;">
		                        <label class="col-sm-1  control-label">C:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text"  id="subjectItemsC"  class="form-control" value="" placeholder=""/>
		                        </div> 
		                         <label class="col-sm-1  control-label">D:</label>
		                        <div class="col-sm-4"> 
		                         <input type="text"  id="subjectItemsD"  class="form-control" value="" placeholder=""/>
		                        </div> 
		                   </div>  
		                   <div id="pdAnswer" class="form-group" >
		                        <label class="col-sm-1  control-label">答案:</label>
		                        <div class="col-sm-9"> 
		                         <input type="text"  id="answer1"  class="form-control" value="${subject.answer}" placeholder=""/>
		                       		 <p  class="msg"> (正确请填“对”，错误请填“错”)</p>
		                        </div> 
		                   </div>
		                   <div id="xzAnswer" class="form-group" style="display: none;">
		                        <label class="col-sm-1  control-label">答案:</label>
		                        <div class="col-sm-9"> 
		                         <input type="text"  id="answer2"  class="form-control" value="${subject.answer}" placeholder=""/>
		                       		 <p  class="msg"> (单选题直接填“A”或“B”或“C”或“D”,多选题答案之间无需用符号相隔,正确格式例如“ABCD”)</p>
		                        </div> 
		                   </div>      
		                    <div class="form-group" style="text-align: center;">
		                        <div class="col-lg-8" id="msgDiv">
		                           <p id="msg" class="msg"> ${msg }</p>
		                        </div>
                                <div class="col-lg-10">
                                    <button class="btn btn-primary" type="button" onclick="saveSubject();">提交</button> 
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
			<!--footer section end-->
		</div>
		<!-- main content end-->
	</section>
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<script src="<%=jsPath %>/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script>
					function checkType (){
				 		var type = $("input[name='subjectType']:checked").val();
				 		if(type == '0'){
				 			document.getElementById("xzt1").style.display = 'none';
				 			document.getElementById("xzt2").style.display = 'none';
				 			document.getElementById("pdAnswer").style.display = 'block';
				 			document.getElementById("xzAnswer").style.display = 'none';
				 		}else if(type == '1'||type == '2'){
				 			document.getElementById("xzt1").style.display = 'block';
				 			document.getElementById("xzt2").style.display = 'block';
				 			document.getElementById("xzAnswer").style.display = 'block';
				 			document.getElementById("pdAnswer").style.display = 'none';
				 		}
		 			}
					function saveSubject(){
					 var itemsValue ;
					 var indexNum=document.getElementById("indexNum").value;
					 var item1=document.getElementById("subjectItemsA").value;
					 var item2=document.getElementById("subjectItemsB").value;
					 var item3=document.getElementById("subjectItemsC").value;
					 var item4=document.getElementById("subjectItemsD").value;
					 itemsValue = "A:"+item1+"%%&&"+"B:"+item2;
					 if(item3==""){
					 }else{
					 itemsValue = itemsValue +"%%&&"+"C:"+item3;
					 }
					 if(item4==""){
					 }else{
					 itemsValue = itemsValue +"%%&&"+"D:"+item4;
					 }
					 document.getElementById("chooseItems").value=itemsValue;
					 var type = $("input[name='subjectType']:checked").val();
					 if(type == '0'){
						 var answer1=document.getElementById("answer1").value;
						 if(answer1=='对'){
						 	answer1='1';
						 }else if(answer1=='错'){
						    answer1='2';
						 }else{
						 	alert("答案格式输入有误，请按规定重新输入！");
						 	return;
						 }
					  	document.getElementById("sub_answer").value= answer1;
				 	 }else if(type == '1'){
				 		var answer2=document.getElementById("answer2").value; 
				 		 if(answer2=='A'){
						 	answer2='1';
						 }else if(answer2=='B'){
						    answer2='2';
						 }else if(answer2=='C'){
						    answer2='3';
						 }else if(answer2=='D'){
						    answer2='4';
						 }else{
						 	alert("答案格式输入有误，请按规定重新输入！");
						 	return;
						 }
				 	     document.getElementById("sub_answer").value= answer2;
				 	 }else if(type == '2'){
				 	 	var answer3=document.getElementById("answer2").value;
				 	 	answer3=answer3.replace("A","1");
				 	 	answer3=answer3.replace("B","2");
				 	 	answer3=answer3.replace("C","3");
				 	 	answer3=answer3.replace("D","4");
				 	     document.getElementById("sub_answer").value= answer3;
				 	 }
					 if(indexNum==null||indexNum==""){
						alert("请填写序号");
						}else{
						　var r = /^\+?[1-9][0-9]*$/;　　//正整数 
      					 if(r.test(indexNum)){ 
      						document.getElementById("form1").submit();    
      					 }else{
      					 	alert("请填写数字！");
      					 }
						 
						}
					 }
					function initSubject(){
					var subjectType = ${subject.subjectType};
					var answer = '${subject.answer}';
					
					if(subjectType=='0'){//判断题
						if(answer == '1'){
						 document.getElementById("answer1").value= "对";
						}else{
						 document.getElementById("answer1").value= "错";
						}
					    document.getElementById("xzt1").style.display = 'none';
				 		document.getElementById("xzt2").style.display = 'none';
				 		document.getElementById("pdAnswer").style.display = 'block';
				 		document.getElementById("xzAnswer").style.display = 'none';
					}else if(subjectType=='1'||subjectType=='2'){//单选题
						var items ='${subject.subjectItems}';
						var strs = items.split("%%&&");
						for(var i=0;i<strs.length;i++){
							var anStrs = strs[i].split(":");
							if(anStrs[0]=='A'){
								document.getElementById("subjectItemsA").value= anStrs[1];
							}else if(anStrs[0]=='B'){
								document.getElementById("subjectItemsB").value= anStrs[1];
							}else if(anStrs[0]=='C'){
							    document.getElementById("subjectItemsC").value= anStrs[1];
							}else if(anStrs[0]=='D'){
							    document.getElementById("subjectItemsD").value= anStrs[1];
							}
						}
				 	 	answer=answer.replace("1","A");
				 	 	answer=answer.replace("2","B");
				 	 	answer=answer.replace("3","C");
				 	 	answer=answer.replace("4","D");
				 	 	document.getElementById("answer2").value= answer;
						document.getElementById("xzt1").style.display = 'block';
				 		document.getElementById("xzt2").style.display = 'block';
				 		document.getElementById("xzAnswer").style.display = 'block';
				 		document.getElementById("pdAnswer").style.display = 'none';
					}
					 
					}
		 </script>
		 <script>       
    	$(document).ready(function() { 
    	    var type = '${subject.subjectType}';
    	    if(type=='0'){ //判断题
    	    	document.getElementById("xzt1").style.display = 'none';
				document.getElementById("xzt2").style.display = 'none';
				document.getElementById("pdAnswer").style.display = 'block';
				document.getElementById("xzAnswer").style.display = 'none';
    	    }else if(type=='1'){//
    	        document.getElementById("xzt1").style.display = 'block';
				document.getElementById("xzt2").style.display = 'block';
				document.getElementById("xzAnswer").style.display = 'block';
				document.getElementById("pdAnswer").style.display = 'none';
    	    }
			$('#doback').click(function(){ 
				history.go(-1)					
			});
		});		 
	</script>
</body>
</html>