<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>register</title>

<style type="text/css">
#content_bg {
	text-align: center;
}

#content_box {
	text-align: left;
	margin: 0 auto;
	width: 850px;
	padding: 0px;
}

ul.location {
	height: 20px;
	width: 850px;
	line-height: 20px;
	float: left;
	margin: 20px 0 0 0;
	padding: 0 0 5px 0;
	border-bottom: 1px dashed #999;
}

.location li {
	float: left;
	height: 20px;
	line-height: 20px;
	margin: 0 10px 0 0;
	padding: 0;
	list-style-type: none;
}

div.logo {
	width: 700px;
	margin: 10px 0 0 10px;
	float: left;
}

div.biaodan {
	width: 700px;
	margin: 20px 0 10px 0px;
	float: left;
}

.item {
	margin: 15px 0
}

.item-error {
	margin-left: 75px;
	color: #fe2617
}

.basic-input {
	width: 200px;
	padding: 5px;
	height: 18px;
	font-size: 14px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	border: 1px solid #c9c9c9
}

.item .basic-input:focus {
	border: 1px solid #a9a9a9
}
</style>

<script type="text/javascript">
	function $(id) {
		return document.getElementById(id);
	}
	function $name(elementName) {
		return document.getElementsByName(elementName);
	}

	function register() {
		//FormData是用来上传用的,其Content-Type为application/form-data
		/* var formData = new FormData();
		formData.append("username",$("username").value);
		formData.append("password",$("password").value); */

		//迟点改成 application/x-www-form-urlencoded
		var password = $("password").value;
		var password2 = $("password2").value;
		if (password != (password2)) {

			alert("密码不一致！");
			return false;
		}

		var xhr = new XMLHttpRequest();

		xhr.onload = _onload;

		xhr.open("POST", "doregister", true);

		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

/* 		var role;
		var list = $name("ROLE");
		for ( var i = 0; i < list.length; i++) {
			if (list[i].checked == true) {
				role = list[i].value;
				break;
			}
		} */


		xhr.send("username=" + $("username").value + 
				"&password=" + $("password").value 
				  + "&ROLE=" + $("ROLE").value
			   +"&trueName=" + $("trueName").value);
	}

	function _onload(evt) {
		if (evt.target.status == 201) //Created 注册成功
		{
			//window.location = evt.target.getResponseHeader("Location");
			alert("注册成功!请重新登录...");

			if (evt.target.getResponseHeader("Location") != null)
				window.location = evt.target.getResponseHeader("Location");
		} else if (evt.target.status == 409) //Conflict 用户账号已存在
		{
			alert(evt.target.responseText);
		} else if (evt.target.status == 400) //Bad Request 请求参数有误
		{
			alert(evt.target.responseText);
		}
	}
</script>

</head>
<body>
	<div id="root">

		<div id="main">
			<div id="banner"></div>

			<div id="content">
				<div id="content_box">
					<ul class="location">
						<li><a href="#">首页&nbsp;</a></li>
						<li><a href="#">注册页面</a></li>
					</ul>
					<div class="clearBoth"></div>

					<!--end sideBar-->
					<form action="" method="post">
						<div id="information">
							<div class="logo">

								<span style="color:#003333;font-size:36px;"> 加入我们吧！</span>
							</div>
							<div class="biaodan">

								<div class="item extra-tips">
									<label style="font-size:14px; color:#336699">用户帐号：</label> <input
										type="text" name="username" id="username" class="basic-input"
										maxlength="60" />
								</div>

								<div class="item extra-tips">
									<label style="font-size:14px; color:#336699">用户密码：</label> <input
										type="password" name="password" id="password"
										class="basic-input" maxlength="60" />
								</div>

								<div class="item extra-tips">
									<label style="font-size:14px; color:#336699">再输密码：</label> <input
										type="password" name="password2" id="password2"
										class="basic-input" maxlength="60" />
								</div>







								<div class="item extra-tips">
									<label style="font-size:14px; color:#336699">姓名：</label> <input
										type="text" name="trueName" id="trueName" class="basic-input"
										maxlength="60" />
								</div>

								<div class="item extra-tips">
									<label style="font-size:14px; color:#336699">角色：</label> 
									
									<select
										id="ROLE" name="ROLE">
										<option value="ROLE_ADMIN">管理员</option>
										<option value="ROLE_BUSINESS">业务</option>
										<option value="ROLE_SCHEDULE">调度</option>
									    <option value="ROLE_FINANCE">财务</option>
										<option value="ROLE_ACCOUNTANT">会计</option>
										<option value="ROLE_LEADER">领导</option>

									</select>
								</div>






								<input type="button" style="font-size:14px; margin-left:30px;"
									onclick="register();return false;" value="注册" />


							</div>
						</div>

					</form>

					<div class="clearBoth"></div>

				</div>
				<!--end content_box-->
				<div class="clearBoth"></div>

			</div>
			<!--end content-->


			<div class="clearBoth"></div>



		</div>
	</div>
	<!--end content_bg-->

</body>
</html>
