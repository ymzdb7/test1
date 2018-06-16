<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link href="../css/style.css" rel="stylesheet">
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=e8ZzPhgyI0uEKG8yxCSC8mPhZNcl0XWs"></script>
	<title>地图展示</title>
</head>
<body class="sticky-header">
		<!-- left side start 左侧菜单导航-->
		<div id="allmap"></div>
		<div id="dialog" title="新增站点" style="display: none">
  		<div>
			<form id="roleForm">
				<div>
					<label>站点名:</label>
					<input type="text" name="portName" class="form-control" id="portName"/>
				</div>
				<div>
					<label>地址:</label>
					<input type="text" name="portAddress" class="form-control" id="portAddress"/>
				</div>
				<div>
					<label>标签:</label>
					<input type="text" name="lable" class="form-control" id="lable"/>
				</div>
				<div>
					<div style="text-align:center">
					<button type="button" class="btn btn-primary" onclick="editSubmit()" id="btn">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(118.802, 32.064), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("南京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	//右击事件
	var menu = new BMap.ContextMenu();
	var txtMenuItem = [
		{
			text:'添加',
			callback:function(){
			$( "#dialog" ).dialog( "open" );
			}
		}
	];
	for(var i=0; i < txtMenuItem.length; i++){
		menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
	}
	map.addContextMenu(menu);
	//单击获取点经纬度
    map.addEventListener("click",function(e){
    alert("lng:"+e.point.lng);
    alert("lat:"+e.point.lat);
});
</script>
</html>
