<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />

<title>订单管理</title>
<link rel="stylesheet" href="resources/css/jquery-ui.css" />
<script type="text/javascript" src="resources/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
<link rel="stylesheet" href="resources/css/storage.css" />

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
		});
	});
	
	$(function(){
		$("#searchbtn").click(function(){
			$("#mainTable_tbody").load("/orders/table",
								{
									"startDate":$("#startDate").val(),
								  	"endDate":$("#endDate").val(),
								  	"client_id":$("#client_id").val(),
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
							
							//alert($(this).attr("id"));
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
			<h5>主页 >> 业务管理 >> 订单管理</h5>
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
								<li><label style="margin-right:10px;">接单时间</label> <input
									id="startDate" name="startDate" type="text" class="date">&nbsp;至&nbsp;
									<input type="text" id="endDate" name="endDate" class="date">
								</li>
								<li><label>收货方</label> <select id="client_id"
									name="client_id">

										<option value="all">全部</option>
										<c:forEach var="client" items="${clients}">
											<option value="${client.id}">${client.shortName}</option>
										</c:forEach>
								</select></li>

								<li><label>订单状态</label> 
									<select id="status" name="status">
										<option value="-1">所有</option>
										<option value="0">未完成</option>
										<option value="1">已完成</option>
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
							<td width="8%">接单时间</td>
							<td width="8%">要求送达时间</td>
							<td width="5%">收货方</td>
							<td width="5%">装货仓库</td>
							<td width="5%">卸货地址</td>
							<td width="10%">货物</td>
							<td width="4%">单位</td>
							<td width="5%">订单量</td>
							<td width="5%">已送达</td>
							<td width="5%">运费</td>
							<td width="5%">结算方式</td>
							<td width="8%">下单人</td>
							<td width="5%">状态</td>
						</tr>
						</thead>
						
						<tbody id="mainTable_tbody">
							<c:forEach var="order" items="${orders}">
								<tr id="${order.id}">
									<td>${order.no}</td>
									<td>${order.orderTime}</td>
									<td>${order.requireDeliveryTime}</td>
									<td>${order.client.shortName}</td>
									<td>${order.loadingAddress}</td>
									<td>${order.unloadingAddress}</td>
									<td>${order.goods}</td>
									<td>${order.unit}</td>
									<td>${order.quantity}</td>
									<td>${order.sendedQuantity}</td>
									<td>${order.cost}</td>
									<td>${order.settlementWay}</td>
									<td>${order.orderMaker.trueName}</td>
									<c:if test="${order.finished}">
										<td>已完成</td>
									</c:if>
									<c:if test="${!order.finished}">
										<td>未完成</td>
									</c:if>
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
	#dialog-form-fieldset{width:100%}
    </style>
<script>
	$(function() {
		var _orderTime = $("#_orderTime"), 
		_requireDeliveryTime = $("#_requireDeliveryTime"), 
		_client_id = $("#_client_id"),

		_cContactPerson = $("#_cContactPerson"), 
		
		_contractNumber = $("#_contractNumber"),

		_loadingAddress = $("#_loadingAddress"), 
		
		_unloadingAddress = $("#_unloadingAddress"),

		_goods = $("#_goods"),

		_unit = $("#_unit"),

		_quantity = $("#_quantity"),

		_cost = $("#_cost"),

		_settlementWay = $("#_settlementWay"), 
		_note = $("#_note"),

		allFields = $([]).add(_orderTime).add(_requireDeliveryTime ).add(_client_id ).add(
				_cContactPerson ).add(_contractNumber ).add(_loadingAddress ).add(_unloadingAddress ).add(
				_goods ).add(_unit ).add(_quantity ).add(_cost ).add(
				_settlementWay ).add(_note ), 
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
													"/order/add",
													{
														"orderTime" : _orderTime
																.val(),
														"requireDeliveryTime" : _requireDeliveryTime .val(),
														"client_id" : _client_id 
																.val(),
														"cContactPerson" : _cContactPerson
																.val(),
														"contractNumber" : _contractNumber 
																.val(),
														"loadingAddress" : _loadingAddress .val(),
														"unloadingAddress" : _unloadingAddress
																.val(),
														"goods" : _goods 
																.val(),
														"unit" : _unit.val(),
														"quantity" : _quantity .val(),
														
														"cost" : _cost 
																.val(),
														"settlementWay" : _settlementWay 
																.val(),
										
														"note" : _note.val()
													},

													function(result) {
														alert("订单添加成功");
														window.location="orders";
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

<div id="dialog-form" title="订单录入">
	<form>
				接单时间:<input type="text" name="_orderTime" id="_orderTime"
							class="date d_text ui-widget-content ui-corner-all" /> 
				要求送达时间:
				<input type="text" name="_requireDeliveryTime" id="_requireDeliveryTime"
							class="date d_text ui-widget-content ui-corner-all" /> 
				
						收货方:
				<select id="_client_id" name="_client_id" class="d_text ui-widget-content ui-corner-all dialog-form-select">
					<c:forEach var="client" items="${clients}">
					<option value="${client.id}">${client.shortName}</option>
					</c:forEach>
				</select> <br/><br/>
		
				联系人:<input type="text" name="_cContactPerson" id="_cContactPerson"
									class="text d_text ui-widget-content ui-corner-all" /> 
									
				联系电话:<input type="text" name="_contractNumber" id="_contractNumber"
									class="text d_text ui-widget-content ui-corner-all" /> 
			
				装货仓库:<input type="text" name="_loadingAddress" id="_loadingAddress"
						class="text d_text ui-widget-content ui-corner-all" /> 

				卸货地址:<input type="text" name="_unloadingAddress" id="_unloadingAddress"
						class="text d_text ui-widget-content ui-corner-all" /> 

				承运货物:<input type="text" name="_goods" id="_goods"
						class="text d_text ui-widget-content ui-corner-all" /> 
						
				计量单位:<input type="text" name="_unit" id="_unit"
						class="text d_text ui-widget-content ui-corner-all" /> 	
				
				数量:<input type="number" name="_quantity" id="_quantity"
						class="text d_text ui-widget-content ui-corner-all" /> 						

				运费:<input type="number" name="_cost" id="_cost"
						class="text d_text ui-widget-content ui-corner-all" /> 	

				结算方式:<select id="_settlementWay" name="_settlementWay"
							class="d_text ui-widget-content ui-corner-all  dialog-form-select" >
				
						<option value="现结">现结</option>
						<option value="预付">预付</option>
						<option value="月结">月结</option>
				
						</select> <br/><br/>						
																	
				<label for="weight">备注</label>
				<input type="text" name="_note" id="_note" value=""
							class="d_text ui-widget-content ui-corner-all" />

	</form>
</div>
</html>