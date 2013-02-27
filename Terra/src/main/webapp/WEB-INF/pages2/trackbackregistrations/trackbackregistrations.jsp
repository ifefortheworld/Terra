<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />

<title>回车报账</title>
<link rel="stylesheet" href="resources/css/jquery-ui.css" />
<script type="text/javascript" src="resources/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<link rel="stylesheet" href="resources/css/distributions.css" />

<script type="text/javascript">
	$(function() {
		$(".date").datepicker($.datepicker.regional['zh_CN']);
		$(".date").datepicker('option', 'changeMonth', true);
		$(".date").datepicker('option', 'changeYear', true);//年份可调
	});
</script>

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
	$(function() {

		$(".list").click(function() {
			$(this).addClass("current").siblings().removeClass("current");
			var text = $(this).text();
			$(".show").html(text);
			//console.log();
		});
	});
	
	$(function(){
		$("#searchbtn").click(function(){
			$("#mainTable_tbody").load("/trackbackregistrations/table",
								{
									"startDate":$("#startDate").val(),
								  	"endDate":$("#endDate").val(),
								  	"client_id":$("#client_id").val(),
								  	"track_id":$("#track_id").val(),
								  	"driver_id":$("#driver_id").val(),
								  	"status":$("#status").val()
								},
								function(){
									$("#mainTable").alterBgColor(); //应用插件
								});	
			return false;
		});
	});
</script>

<style type="text/css">
.even {
	background: #FFFFEE;
} /* 偶数行样式*/
.odd {
	background: #FFFFEE;
} /* 奇数行样式*/
.selected {
	background: #FF6500;
	color: #fff;
}
</style>

<script type="text/javascript">
	//插件编写
	(function($) {
		$.fn.extend({
			"alterBgColor" : function(options) {
				//设置默认值
				options = $.extend({
					odd : "odd", /* 偶数行样式*/
					even : "even", /* 奇数行样式*/
					selected : "selected" /* 选中行样式*/
				}, options);
				//$("tbody>tr:odd", this).addClass(options.odd);
				//$("tbody>tr:even", this).addClass(options.even);
				$('tbody>tr', this).click(
						function() {
							$('tbody>tr.selected').removeClass("selected");

							//判断当前是否选中
							var hasSelected = $(this).hasClass(options.selected);
							//如果选中，则移出selected类，否则就加上selected类
							$(this)[hasSelected ? "removeClass" : "addClass"](options.selected);
							
							$("#_distributionNo").attr("value",$(this).attr("id"));
							//alert($('tbody>tr.selected').attr("id"));
						});
				// 如果单选框默认情况下是选择的，则高色.
				$('tbody>tr:has(:checked)', this).addClass(options.selected);
				
				return this; //返回this，使方法可链。
			}
		});
	})(jQuery);
	//插件应用
	$(function() {
		$("#mainTable").alterBgColor() //应用插件
		.find("th").css("color", "red");//可以链式操作
	});
</script>

</head>
<body onload="startTime()">
	<div id="box">

		<div id="header">
			<a href="/">
			<img class="logo" src="resources/image/logo.png" alt="" />
			</a>
			<h2 class="top">安吉零部件售后配送信息系统</h2>
		</div>
		<div class="title">
			<h5>主页 >> 配送管理 >> 回车报账</h5>
		</div>
		<div id="content">
			<div id="storage">
				<div id="toolbar">
					<div class="action">
						<ul>
							<div style="overflow:auto;">
								<li id="create"
									style="background:url('resources/image/folder_go.png') no-repeat left center;">登记</li>
								<li
									style="background:url('resources/image/folder_edit.png') no-repeat left center;">修改</li>
								<li
									style="background:url('resources/image/folder_delete.png') no-repeat left center;">删除</li>
							</div>
							<div style="overflow:auto;">
								<li
									style="background:url('resources/image/magnifier.png') no-repeat left center;">筛选</li>
								<li
									style="background:url('resources/image/bullet_go.png') no-repeat left center;">导出</li>
								<li
									style="background:url('resources/image/computer_go.png') no-repeat left center;" onclick="window.location='/';">
									返回
									</li>
							</div>
							<div class="clear"></div>
						</ul>
						<div class="clear"></div>
					</div>

					<div class="search">
						<form action="">
							<ul class="searchul">
								<li><label style="margin-right:10px;">出车时间</label> <input
									id="startDate" name="startDate" type="text" class="date">&nbsp;至&nbsp;
									<input type="text" id="endDate" name="endDate" class="date">
								</li>
								
								
								<li>
									<label>车号</label> 
									<select id="track_id" name="track_id">

										<option value="all">全部</option>
										<c:forEach var="track" items="${tracks}">
											<option value="${track.id}">${track.licenseNumber}</option>
										</c:forEach>
									</select>
								</li>
								
								<li>
								<label>司机</label> 
									<select id="driver_id" name="driver_id">

										<option value="all">全部</option>
										<c:forEach var="driver" items="${drivers}">
											<option value="${driver.id}">${driver.name}</option>
										</c:forEach>
									</select>
								</li>
									
								<li>
									<label>收货方</label> 
									<select id="client_id" name="client_id">

										<option value="all">全部</option>
										<c:forEach var="client" items="${clients}">
											<option value="${client.id}">${client.shortName}</option>
										</c:forEach>
									</select>
								</li>

								<li><label>调度状态</label> 
									<select id="status" name="status">
										<option value="-1">所有</option>
										<option value="1">配送中</option>
										<option value="2">已回车</option>
									</select>
								</li>
								
								<li><input type="submit" name="searchbtn" id="searchbtn"
									value="查找"></li>
							</ul>
						</form>
					</div>


				</div>

				<div class="showinfo">
					<table id="mainTable">
						<thead>
							<tr class="thead">
								<td width="8%">配送单编号</td>
								<td width="8%">订单编号</td>
								<td width="6%">出车时间</td>
								<td width="6%">回车时间</td>
								<td width="6%">回车收款</td>
								<td width="5%">车号</td>
								<td width="5%">司机</td>
								<td width="8%">收货方</td>
								<td width="8%">货物</td>
								<td width="5%">单位</td>
								<td width="5%">配送量</td>
								<td width="5%">配送状态</td>
							</tr>
						</thead>
						
						<tbody id="mainTable_tbody">
							<c:forEach var="distribution" items="${distributions}">
							<tr id="${distribution.no}">
								<td>${distribution.no}</td>
								<td>${distribution.orderForm.no}</td>
								<td>${distribution.sendOutTime}</td>
								<td>${distribution.goBackTime}</td>
								<td>${distribution.trackBackRegistration.payment}</td>
								<td>${distribution.track.licenseNumber}</td>
								<td>${distribution.driver.name}</td>
								<td>${distribution.orderForm.client.shortName}</td>
								<td>${distribution.orderForm.goods}</td>
								<td>${distribution.orderForm.unit}</td>
								<td>${distribution.sendQuantity}</td>
								<td>
									<c:if test="${distribution.status == 0}">未出车</c:if>
									<c:if test="${distribution.status == 1}">配送中</c:if>
									<c:if test="${distribution.status == 2}">已回车</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>

			</div>
		</div>

		<div id="footer">
			操作员：<span><sec:authentication property="principal.role.name" />
				| <sec:authentication property="principal.trueName" /> </span>&nbsp;<span
				id="txt"></span>
		</div>

	</div>


</body>
 <style>
	input.d_text { margin-bottom:12px; width:98%; padding: .4em; }
	.dialog-form-select{margin-bottom:12px; width:102%; padding: .4em;}
    </style>
<script>
	$(function() {
		var _distributionNo = $("#_distributionNo"), 
		
		_goBackTime = $("#_goBackTime"),

		_payment = $("#_payment"),

		_note = $("#_note"),

		allFields = $([]).add(_distributionNo).add(_goBackTime ).add(_payment).add(_note );



		$("#dialog-form")
				.dialog(
						{
							autoOpen : false,
							height : 450,
							width : 500,
							modal : true,
							buttons : {
								"确定" : function() {
									var bValid = false;
									allFields.removeClass("ui-state-error");

					
											$.post(
													"/trackbackregistrations/add",
													{
														"distributionNo" : _distributionNo 
																.val(),
														"goBackTime" : _goBackTime.val(),
														
														"payment" : _payment  
																.val(),
													
														"note" : _note.val()
													},

													function(result) {
														alert("回车登记成功");
														window.location.reload();
													});

									$(this).dialog("close");

								},
								"取消" : function() {
									$(this).dialog("close");
								}
							},

							close : function() {
								allFields.val("").removeClass("ui-state-error");
							}
						});

		$("#create").click(function() {
			$("#dialog-form").dialog("open");
		});
	});
	

	

</script>


<div id="dialog-form" title="回车报账">
	<form>
				配送单编号:<input type="text" name="_distributionNo" id="_distributionNo"
							class="d_text ui-widget-content ui-corner-all" readonly="readonly"/> 
			
				
				回车时间:<input type="text" name="_goBackTime" id="_goBackTime"
							class="date d_text ui-widget-content ui-corner-all" /> 
				
				
				回车收款:<input type="text" name="_payment" id="_payment"
							class="d_text ui-widget-content ui-corner-all" /> 
			
				<label >备注</label>
				<input type="text" name="_note" id="_note" value=""
							class="d_text ui-widget-content ui-corner-all" />

	</form>
</div>
</html>