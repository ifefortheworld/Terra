<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf=8" />
<title>安吉零部件售后配送信息系统</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
}

ul {
	list-style: none;
}

body {
	margin: 0;
	overflow: hidden;
}

#header {
	height: 100px;
	background: #3179CE;
}

.top {
	margin: 0;
	padding: 30px 100px;
	color: white;
}

.logo {
	width: 100px;
	height: 100px;
	float: left;
}

#content {
	height: 1000px;
}

.title {
	height: 28px;
	line-height: 28px;
	border-bottom: 1px solid #ccc;
	padding-left: 20px;
}

.nav {
	float: left;
	width: 140px;
	height: 100%;
	background: #1865BD;
}

.nav .title {
	color: white;
}

.list {
	height: 28px;
	line-height: 28px;
	color: white;
	font-weight: bold;
	font-size: 14px;
	text-align: center;
	cursor: pointer;
}

.current {
	background: white;
	color: brown;
}

.sider {
	float: right;
	width: 200px;
	height: 100%;
	border-left: 1px dashed #999;
}

.cont {
	margin-top: 20px;
}

.refer {
	margin-left: 40px;
	font-size: 14px;
	line-height: 25px;
	text-align: left;
}

.main {
	height: 100%;
	margin: 0 200px 0 140px;
}

#footer {
	position: fixed;
	bottom: 0;
	height: 28px;
	line-height: 28px;
	background: #3179CE;
	width: 100%;
	text-align: center;
	color: white;
	font-weight: bold;
	font-size: 13px;
}

.pos{
	float: left;
	padding: 50px;
}


.wh {
	width: 100px;
	float: left;
	margin-left: 28px;
}

.arrow {
	float: left;
	margin-top: 26px;
	margin-left: 30px;
}

ul li label {
float: left;
margin-left: 168px;
}

.arrow2 {
float: left;
margin-top: 85px;
margin-left: 8px;
}
</style>

<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">
	function startTime() {
		var today = new Date();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		// add a zero in front of numbers<10
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
		t = setTimeout('startTime()', 500);
	}

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
	
	var index = 1;
	
	$(function() {

		$(".list").click(function() {
			$(this).addClass("current").siblings().removeClass("current");
			var text = $(this).text();
			$(".show").html(text);
			//console.log();
			
			var last = index;
			index = $(this).attr("id");
			
			if(last != index)
			{
				$("#submenu"+last).hide();
				$("#submenu"+index).show();
			}
		});
		
	});
</script>

</head>
<body onload="startTime()">
	<div id="header">
		<img class="logo" src="resources/image/logo.png"   />
		<h2 class="top">安吉零部件售后配送信息系统</h2>
	</div>
	<div id="content">
		<div class="nav">
			<h5 class="title">系统功能</h5>
			<ul id="menu">
				<li class="list current" id="1">业务流程</li>
				<li class="list" id="2">订单管理</li>
				<li class="list" id="3">配送管理</li>
				<li class="list" id="4">仓储管理</li>
				<li class="list" id="5">车辆管理</li>
				<li class="list" id="6">信息管理</li>
				<li class="list" id="7">计划管理</li>
				<li class="list" id="8">系统维护</li>
				<li class="list" id="9">辅助工具</li>
			</ul>
		</div>
		<div class="sider">
			<h5 class="title">相关查询</h5>
			<div class="cont">
				<p class="refer">未完成的订单: ${unfinishedOrderFormsCnt}张</p>
				<p class="refer">未出车的配送单: ${undistributedDistributionCnt }张</p>
				<p class="refer">配送中的配送单: ${distributingDistributionCnt }张</p>
			</div>
		</div>
		<div class="main">
			<h5 class="title show">业务流程</h5>
			<div class="contain">

				<div class="submenu" id="submenu1">
					<ul style="float: left;  padding: 50px;">
					  	<li>
						  	<img id="img1"   src="resources/image/_order.png" style="float: left;width: 72px; margin-top: -5px;margin-left: 20px;">
							<img class="arrow"   src="resources/image/arrow.png">
							<img class="wh" id="img2"   src="resources/image/warehouse.png">
							<img class="arrow" src="resources/image/arrow.png">
							<img class="wh" id="img3"   src="resources/image/warehouse.png">
							<img class="arrow" src="resources/image/arrow.png">
							<img class="wh" id="img4"   src="resources/image/warehouse.png">
					  	</li>
						<li style="position: relative;left: -19px;top: 13px;">
							<label for="img1" style="margin-left: -31px;">订单</label>
							<label for="img2" style="">CPD</label>
							<label for="img3" style="margin-left: 175px;">发货仓</label>
							<label for="img4" style="margin-left: 162px;">非发货仓</label>
						</li>
					</ul>
				</div>
				
				<div class="submenu" id="submenu2" hidden="true">
					<a href="orders">
						<img class="pos"   src="resources/image/orders.png" ><br>
					</a>
				</div>
				
				<div class="submenu" id="submenu3" hidden="true">
		
					<a href="distributions" class="pos">
						<img   src="resources/image/distributions.png" >
					</a>
					<img class="arrow2"  src="resources/image/arrow.png">
					<a href="trackoutregistrations" class="pos">
						<img   src="resources/image/trackoutregistrations.png" >
					</a>
					<img class="arrow2"  src="resources/image/arrow.png">
					<a href="trackbackregistrations" class="pos">
						<img   src="resources/image/trackbackregistrations.png" >
					</a>
				</div>
				
				<div class="submenu" id="submenu4" hidden="true" >
					<a class="pos" href="inwarehouse" > 
						<img src="resources/image/inwarehouse.png">
					</a>
					<img class="arrow2"  src="resources/image/arrow.png">
				    <a class="pos" href="outwarehouse" style="margin-left: 5px;margin-top: 3px;"> 
						<img  src="resources/image/outwarehouse.png">
					</a>
					
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		操作员：<span><sec:authentication property="principal.role.name"/> | <sec:authentication property="principal.trueName"/> </span>&nbsp;<span id="txt"></span>
	</div>
</body>

</html>