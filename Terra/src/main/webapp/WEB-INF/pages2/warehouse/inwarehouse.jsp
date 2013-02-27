<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-cn" />

<title>入库管理</title>
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
			//console.log();
		});
	});

	$(function(){
		$("#searchbtn").click(function(){
			$("#mainTable_tbody").load("/inwarehouse/table",
								{
									"startDate":$("#startDate").val(),
								  	"endDate":$("#endDate").val(),
								  	"client_id":$("#client_id").val(),
								  	"warehouse_id":$("#warehouse_id").val()
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
			<h5>主页 >> 仓储管理 >> 入库管理</h5>
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
						<form action="inwarehouse2">
							<ul class="searchul">
								<li><label style="margin-right:10px;">入库日期</label> <input
									id="startDate" name="startDate" type="text" class="date">&nbsp;至&nbsp;
									<input type="text" id="endDate" name="endDate" class="date">
								</li>
								<li><label>客户</label> <select id="client_id"
									name="client_id">

										<option value="all">全部</option>
										<c:forEach var="client" items="${clients}">
											<option value="${client.id}">${client.shortName}</option>
										</c:forEach>
								</select></li>

								<li><label>仓库</label> <select id="warehouse_id"
									name="warehouse_id">
										<option value="all">全部</option>
										<c:forEach var="warehouse" items="${warehouses}">
											<option value="${warehouse.id}">${warehouse.shortName}</option>
										</c:forEach>
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
								<td width="8%">入库日期</td>
								<td width="5%">客户</td>
								<td width="5%">车号</td>
								<td width="5%">仓库</td>
								<td width="14%">入库清单</td>
								<td width="10%">装卸费</td>
								<td width="10%">仓库费</td>
								<td width="10%">运输费</td>
								<td width="10%">其它费用</td>
							 	<td width="5%">合计</td> 
							<!-- <td width="10%">备注</td> -->
							</tr>
						</thead>

						<tbody id="mainTable_tbody">
							<c:forEach var="record" items="${inRecords}">
							<tr id="${record.id}">
								<td>${record.no}</td>
								<td>${record.date}</td>
								<td>${record.client.shortName}</td>
								<td>${record.track.licenseNumber}</td>
								<td>${record.warehouse.shortName}</td>
								<td>${record.good} ${record.quantity} ${record.unit}</td>
								<td>${record.handlingCharge}</td>
								<td>${record.storageCharge}</td>
								<td>${record.transportationCharge}</td>
								<td>${record.otherCharge}</td>
								<td>${record.handlingCharge +record.storageCharge
									+record.transportationCharge +record.otherCharge}</td>
								<%-- <td>${record.note}</td> --%>
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
		var warehouse_id = $("#_warehouse_id"), date = $("#_date"), client_id = $("#_client_id"),

		track_id = $("#_track_id"), driver_id = $("#_driver_id"),

		good = $("#_good"), batchNumber = $("#_batchNumber"),

		quantity = $("#_quantity"),

		unit = $("#_unit"),

		weight = $("#_weight"),

		handlingCharge = $("#_handlingCharge"),

		storageCharge = $("#_storageCharge"), transportationCharge = $("#_transportationCharge"), otherCharge = $("#_otherCharge"), note = $("#_note"),

		allFields = $([]).add(warehouse_id).add(date).add(client_id).add(
				track_id).add(driver_id).add(good).add(batchNumber).add(
				quantity).add(unit).add(weight).add(handlingCharge).add(
				storageCharge).add(transportationCharge).add(otherCharge).add(
				note), tips = $(".validateTips");

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
													"warehouseInRecord/add",
													{
														"warehouse_id" : warehouse_id
																.val(),
														"date" : date.val(),
														"client_id" : client_id
																.val(),
														"track_id" : track_id
																.val(),
														"driver_id" : driver_id
																.val(),
														"good" : good.val(),
														"batchNumber" : batchNumber
																.val(),
														"quantity" : quantity
																.val(),
														"unit" : unit.val(),
														"weight" : weight.val(),
														"handlingCharge" : handlingCharge
																.val(),
														"storageCharge" : storageCharge
																.val(),
														"transportationCharge" : transportationCharge
																.val(),
														"otherCharge" : otherCharge
																.val(),
														"note" : note.val()
													},

													function(result) {
														alert("入库记录添加成功");
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

<div id="dialog-form" title="入库登记">
	<form>
			仓库: <select id="_warehouse_id" name="_warehouse_id"
				class="d_text ui-widget-content ui-corner-all dialog-form-select">
				<c:forEach var="warehouse" items="${warehouses}">
					<option value="${warehouse.id}">${warehouse.shortName}</option>
				</c:forEach>
			</select><br/>
						客户:
				<select id="_client_id" name="_client_id" class="d_text ui-widget-content ui-corner-all dialog-form-select">
					<c:forEach var="client" items="${clients}">
					<option value="${client.id}">${client.shortName}</option>
					</c:forEach>
				</select> 
				<br> <br>
				 入库日期:<input type="text" name="_date" id="_date"
							class="date d_text ui-widget-content ui-corner-all" /> 
				<br/>
			
			车号:<select id="_track_id" name="_track_id"
				class="d_text ui-widget-content ui-corner-all dialog-form-select">
				
				<c:forEach var="track" items="${tracks}">
					<option value="${track.id}">${track.licenseNumber}</option>
				</c:forEach>
				
			</select> 司机:<select id="_driver_id" name="_driver_id"
				class="d_text ui-widget-content ui-corner-all dialog-form-select">
				
				<c:forEach var="driver" items="${drivers}">
					<option value="${driver.id}">${driver.name}</option>
				</c:forEach>
				
			</select> <br>
			 
			 <label for="good">货名</label> 
			 <input type="text"
				name="_good" id="_good" value=""
				class="d_text ui-widget-content ui-corner-all" /><br> 
				
				<label
				for="batchNumber">批号</label> <input type="text" name="_batchNumber"
				id="_batchNumber" value=""
				class="d_text ui-widget-content ui-corner-all" /><br/>
				
				 <label
				for="quantity">数量</label> <input type="number" name="_quantity"
				id="_quantity" value="" class="d_text ui-widget-content ui-corner-all" />

			<label for="unit">单位</label> <input type="text" name="_unit"
				id="_unit" value="" class="d_text ui-widget-content ui-corner-all" /><br/>

			<label for="weight">重量(吨)</label> <input type="number" name="_weight"
				id="_weight" value="" class="d_text ui-widget-content ui-corner-all" />

			<label for="handlingCharge">装卸费</label><input type="number"
				name="_handlingCharge" id="_handlingCharge" value=""
				class="d_text ui-widget-content ui-corner-all" /><br/>
				
				 <label for="weight">仓储费</label>
			<input type="number" name="_storageCharge" id="_storageCharge"
				value="" class="d_text ui-widget-content ui-corner-all" /><br/>
				 <label
				for="weight">运输费</label> <input type="number"
				name="_transportationCharge" id="_transportationCharge" value=""
				class="d_text ui-widget-content ui-corner-all" /> <br/>
				
				<label for="weight">其它费用</label>
			<input type="number" name="_otherCharge" id="_otherCharge" value=""
				class="d_text ui-widget-content ui-corner-all" /> <br/>
				
				<label for="weight">备注</label>
			<input type="text" name="_note" id="_note" value=""
				class="d_text ui-widget-content ui-corner-all" />

	</form>
</div>
</html>