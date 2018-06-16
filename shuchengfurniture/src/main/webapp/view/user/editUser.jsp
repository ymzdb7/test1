<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"></jsp:include>
    <link href="<%=jsPath %>/ztree/css/metroStyle/metroStyle.css" rel="stylesheet">
    <!--common scripts for all pages-->
</head>
<script type="text/javascript">


</script>
<body class="sticky-header">

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
            <h3>修改用户 </h3>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
                <div class="col-sm-12">
                    <form id="form1" class="form-horizontal adminex-form" method="post"
                          action="../user/update?ac=<%=ac%>">

                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">用户名:</label>
                            <div class="col-sm-6">
                                <input type="text" name="userName" readonly="readonly" class="form-control"
                                       value="${user.userName}"/>
                                <input type="hidden" name="userId" class="form-control" value="${user.userId}"/>
                                <input id="searchName" name="searchName" type="hidden" value="${searchName}"/>
                                <input id="searchPhone" name="searchPhone" type="hidden" value="${searchPhone}"/>
                                <input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
                                <input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">姓名:</label>
                            <div class="col-sm-6">
                                <input type="text" name="userNameCn"
                                       class="form-control" value="${user.userNameCn}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">密码:</label>
                            <div class="col-sm-6">
                                <input type="password" name="password" class="form-control" value=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">手机号:</label>
                            <div class="col-sm-6">
                                <input type="text" name="userPhone" class="form-control" value="${user.userPhone}"/>
                            </div>
                        </div>

                        <div class="form-group" style="text-align: center;">
                            <div class="col-lg-8" id="msgDiv">
                                <p id="msg" class="msg"> ${msg }</p>
                            </div>
                            <div class="col-lg-8">
                                <button class="btn btn-primary" type="submit">提交</button>
                                &nbsp;&nbsp;
                                <button class="btn btn-primary" id="doback" type="button">返回</button>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
            <div id="orgContent" style="display:none; position: absolute;background-color: #FFFFFF;">
                <ul id="orgTree" class="ztree" style="margin-top:0; width:450px;overflow:auto;z-index: 9999"></ul>
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
<script src="<%=jsPath%>/ztree/js/jquery.ztree.all.min.js"></script>
<script>



    $(document).ready(function () {

        $('#doback').click(function () {
            $('#form1').attr('action', '<%=basePath%>user/index?ac=<%=ac%>&searchId=${searchId}');
            $('#form1').submit();
        });
    });

    function showOrg() {
        var dictObj = $("#orgSel");
        var dictOffset = $("#orgSel").offset();
        $("#orgContent").css({
            left: dictOffset.left + "px",
            top: dictOffset.top + dictObj.outerHeight() + "px"
        }).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if ($(event.target).parents("#orgContent").length == 0) {
            hideMenu();
        }
    }
    function hideMenu() {
        $("#orgContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree"),
                nodes = zTree.getCheckedNodes(true);
        $("#orgSel").attr("value", nodes[0].name);
        $("#orgId").attr("value", nodes[0].id);
    }

    function beforeClick(treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree");
        zTree.checkNode(treeNode, true, null, true);
        return false;
    }
</script>
</body>
</html>