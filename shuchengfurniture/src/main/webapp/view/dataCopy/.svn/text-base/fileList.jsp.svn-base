<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.File,java.net.URLEncoder,com.winhands.base.util.HttpDownload"%>
<%@ include file="../common/common.jsp"%>
<%
String realpath = request.getRealPath("/");
String filePath = "D:/winestCopy";
File file = new File(filePath);
int i = 0;
List<File> list = getFiles(filePath, new ArrayList<File>());
 
        if (list != null && list.size() > 0) {
 
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
 
                }
            });
 
        }

%>

<%!
 public  List<File> getFiles(String realpath, List<File> files) {
 
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

 %>
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
							    <form  action="<%=basePath %>fileBf/fileList?ac=200026" method="post" name="searchForm" id="searchForm">
 			                      </form> 
			                    </div> 
								<div class="adv-table">
									<table class="display table table-bordered table-striped" id="d-table">
									<thead>
											<tr>
												<th style="width:40px"></th>
												<th>文件名</th>
												<th>文件大小</th>
												<th>文件地址</th>
												<th>操作</th>
											</tr>
									</thead>
										
											<tr class="gradeX">
												<%
												if(!file.exists()){
													out.println(filePath+" is not exists.");%>
													<p>暂无数据</p>
												<%	return;
												}
												for( i = 0; i < list.size(); i++){
													String flag = " K";
													int dx = Math.round(list.get(i).length()/1024);
													if(dx > 1024){
														dx = Math.round(dx/1024);
														flag = " M";
													}
													if(dx > 1024){
														dx = Math.round(dx/1024);
														flag = " G";
													}
												%>
													<%
													if(i<10){
													
													 %>
													<td><%=i+1 %></td>
													<% String path1=list.get(i).getAbsolutePath(); %>
										   			<td style="text-align: center;"><%=list.get(i).getName() %></td>
										   			<td style="text-align: center;"><%=dx+flag%></td>
										   			<td style="text-align: center;"><%=list.get(i).getAbsolutePath()%></td>
										   			<td style="text-align: center;">
										   			<a href="javascript:void(0);" onclick="downloadFile('<%=path1%>','<%=list.get(i).getName() %>','<%=i%>')"><i class="fa fa-btn fa-pencil"></i>下载</a>
										   			   <input type="hidden" name="filePath<%=i%>"  id="filePath<%=i%>" value="<%=filePath %>" class="form-control" />
										   			</td>
										</tr>
										<%
											}	
										}
										%>
										
									</table>
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
	
</body>
<script type="text/javascript">
	function  downloadFile(filePathOri,fileNameOri,i){
	var path2= document.getElementById("filePath"+i).value;
	var filePathOri=path2+"/"+fileNameOri;
          location.href = "<%=basePath%>article/saveCopyFile?ac=<%=ac%>&filePath="+filePathOri+"&fileName="+fileNameOri;
				}
	
	</script>
</html>