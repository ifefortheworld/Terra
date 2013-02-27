<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />

<title>配送管理</title>
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
			$("#mainTable_tbody").load("/distributions/table",
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

.title {
	height: 28px;
	line-height: 28px;
	border-bottom: 1px solid #CCC;
	padding-left: 20px;
	background: rgba(228, 242, 221, 1);
	color: #35A9D6;
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
			<h5>主页 >> 配送管理 >> 配送调度</h5>
		</div>
		<div id="content">
			<div id="storage">
				<div id="toolbar">
					<div class="action">
						<ul>
							<div style="overflow:auto;">
								<li id="create"
									style="background:url('resources/image/folder_go.png') no-repeat left center;">新建</li>
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
						<form action="orders2">
							<ul class="searchul">
								<li><label style="margin-right:10px;">计划出车时间</label> <input
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
										<option value="0">未出车</option>
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
								<td width="8%">单据编号</td>
								<td width="8%">订单编号</td>
								<td width="6%">计划出车时间</td>
								<td width="6%">出车时间</td>
								<td width="6%">回车时间</td>
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
							<tr id="${distribution.id}">
								<td>${distribution.no}</td>
								<td>${distribution.orderForm.no}</td>
								<td>${distribution.planedSendOutTime}</td>
								<td>${distribution.sendOutTime}</td>
								<td>${distribution.goBackTime}</td>
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
		var _orderForm_id = $("#_orderForm_id"), 
		_sendQuantity = $("#_sendQuantity"), 
		_planedSendOutTime = $("#_planedSendOutTime"),

		_track_id = $("#_track_id"), 
		
		_driver_id = $("#_driver_id"),

		_note = $("#_note"),

		allFields = $([]).add(_orderForm_id).add(_sendQuantity ).add(_planedSendOutTime ).add(
				_track_id ).add(_driver_id ).add(_note ), 
				tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}

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

									bValid = bValid
											&& checkLength(name, "username", 3,
													16);
									bValid = bValid
											&& checkLength(email, "email", 6,
													80);
									bValid = bValid
											&& checkLength(password,
													"password", 5, 16);

									bValid = bValid
											&& checkRegexp(name,
													/^[a-z]([0-9a-z_])+$/i,
													"Username may consist of a-z, 0-9, underscores, begin with a letter.");
									// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
									bValid = bValid
											&& checkRegexp(
													email,
													/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
													"eg. ui@jquery.com");
									bValid = bValid
											&& checkRegexp(password,
													/^([0-9a-zA-Z])+$/,
													"Password field only allow : a-z 0-9");

											$.post(
													"/distribution/add",
													{
														"orderForm_id" : _orderForm_id 
																.val(),
														"sendQuantity" : _sendQuantity.val(),
														
														"planedSendOutTime" : _planedSendOutTime  
																.val(),
														"track_id" : _track_id 
																.val(),
														"driver_id" : _driver_id  
																.val(),
														"note" : _note.val()
													},

													function(result) {
														alert("配送单添加成功");
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
	
	var map = {
			<c:forEach var="order" items="${orders}">
					"${order.id}" : ${order.quantity - order.sendedQuantity - order.distributingQuantity},
			</c:forEach>
			" ":""
	};
	
	function change(){
		
		var orderId = $("#_orderForm_id").val();
		
		if(orderId != "")
		{
			$("#_sendQuantity").val(map[orderId]);
			$("#_sendQuantity").attr("max",map[orderId]);
		}
		
	};
</script>


<div id="dialog-form" title="配送单录入">
	<form>
				订单:<select id="_orderForm_id" name="_orderForm_id" class="d_text ui-widget-content ui-corner-all dialog-form-select"
					 onchange="change();">
				
				
					<option value=" "></option>
					
					<c:forEach var="order" items="${orders}">
						<option value="${order.id}">${order.no}</option>
					</c:forEach>
				
				</select>
				
				
				配送量:<input type="number" name="_sendQuantity" id="_sendQuantity"
							class="d_text ui-widget-content ui-corner-all" max=""/> 
				
				计划出车时间:<input type="text" name="_planedSendOutTime" id="_planedSendOutTime"
							class="date d_text ui-widget-content ui-corner-all" /> 
				
				车号:<select id="_track_id" name="_track_id"
				class="d_text ui-widget-content ui-corner-all dialog-form-select">
				
				<c:forEach var="track" items="${tracks}">
					<option value="${track.id}">${track.licenseNumber}</option>
				</c:forEach>
				
				</select> 
				
				司机:<select id="_driver_id" name="_driver_id"
					class="d_text ui-widget-content ui-corner-all dialog-form-select">
					
					<c:forEach var="driver" items="${drivers}">
						<option value="${driver.id}">${driver.name}</option>
					</c:forEach>
					
				</select> <br>
			
					
				<label for="weight">备注</label>
				<input type="text" name="_note" id="_note" value=""
							class="d_text ui-widget-content ui-corner-all" />

	</form>
</div>
</html>