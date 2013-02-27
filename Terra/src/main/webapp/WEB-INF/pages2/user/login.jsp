<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="description" content="">
<title>登录</title>

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background-color: #E4E8EC;
}

.wrap {
	margin: 150px auto;
	width: 380px;
	overflow: hidden;
}

.loginForm {
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, 0.2), 0 3px 0 white, 0 4px 0 rgba(0, 0, 0, 0.2), 0 6px 0 white, 0 7px 0 rgba(0, 0, 0, 0.2);
	position: absolute;
	z-index: 0;
	background-color: white;
	border-radius: 4px;
	height: 275px;
	width: 450px;
	background: -webkit-gradient(linear, left top, left 24, from(#EEE), color-stop(4%, white), to(#EEE) );
	background: -moz-linear-gradient(top, #EEE, white 1px, #EEE 24px);
	background: -o-linear-gradient(top, #EEE, white 1px, #EEE 24px);
	}

.loginForm:before {
	content: '';
	position: absolute;
	z-index: -1;
	border: 1px dashed #CCC;
	top: 5px;
	bottom: 5px;
	left: 5px;
	right: 5px;
	box-shadow: 0 0 0 1px #FFF;
}

.loginForm h1 {
	text-transform: uppercase;
	text-align: center;
	color: #666;
	line-height: 3em;
	margin: 37px 0 20px 0;
	letter-spacing: 4px;
	font: normal 26px/1 Microsoft YaHei, sans-serif;
}

fieldset {
	border: none;
	padding: 10px 10px 0;
}

fieldset input[type=text] {
	background: url(style/default/images/user.png) 4px 5px no-repeat;
}

fieldset input[type=password] {
	background: url(style/default/images/password.png) 4px 5px no-repeat;
}

fieldset input[type=text],fieldset input[type=password] {
	width: 100%;
	line-height: 2em;
	font-size: 12px;
	height: 24px;
	border: none;
	padding: 3px 4px 3px 2.2em;
	width: 375px;
}

fieldset input[type="submit"] {
	text-align: center;
	line-height: 2em;
	border: 1px solid #FF1500;
	border-radius: 3px;
	background: -webkit-gradient(linear, left top, left 24, from(#FF6900), color-stop(0%, #FF9800), to(#FF6900) );
	background: -moz-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	background: -o-linear-gradient(top, #FF6900, #FF9800 0, #FF6900 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900' );
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF9800', endColorstr='#FF6900')";
	height: 42px;
	cursor: pointer;
	letter-spacing: 4px;
	color: white;
	font-weight: bold;
	padding-top: 2px;
	padding-right: 20px;
	padding-bottom: 2px;
	padding-left: 20px;
	margin-left: 144px;
	margin-top: 5px;
	width: 135px;
}


fieldset input[type=submit]:hover {
	background: -webkit-gradient(linear, left top, left 24, from(#FF9800),
		color-stop(0%, #FF6900), to(#FF9800) );
	background: -moz-linear-gradient(top, #FF9800, #FF6900 0, #FF9800 24px);
	background: -o-linear-gradient(top, #FF6900, #FF6900 0, #FF9800 24px);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900',
		endColorstr='#FF9800' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF6900', endColorstr='#FF9800')";
}

.inputWrap {
	background: -webkit-gradient(linear, left top, left 24, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF) );
	background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px);
	background: -o-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 24px);
	border-radius: 3px;
	border: 1px solid #CCC;
	margin: 10px 10px 0;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE',
		endColorstr='#FFFFFF' );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#EEEEEE', endColorstr='#FFFFFF')";
}

fieldset a {
	color: blue;
	font-size: 12px;
	margin: 6px 0 0 10px;
	text-decoration: none;
}

fieldset a:hover {
	text-decoration: underline;
}

fieldset span {
	font-size: 12px;
}
</style>
<!--为了让IE支持HTML5元素，做如下操作：-->
<!--[if IE]>
<script type="text/javascript">
document.createElement("section");
document.createElement("header");
document.createElement("footer");
</script>
<![endif]-->

<script type="text/javascript">
	function $id(id) {
		return document.getElementById(id);
	}

	var xhr;

	function login() {
		/* 
		FormData是用来上传用的,其Content-Type为application/form-data
		var formData = new FormData();
		formData.append("j_username",$("log_name").value);
		formData.append("j_password",$("log_password").value);
		 */
		xhr = new XMLHttpRequest();

		xhr.onreadystatechange = ajaxCallBack;

		xhr.open("POST", "j_spring_security_check", true);

		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

		xhr.send("j_username=" + $id("username").value + "&j_password="
				+ $id("password").value);

	}

	function ajaxCallBack() {

		if (xhr.readyState == 4) {
			if (xhr.status == 200) //登录成功
			{
				window.location = xhr.getResponseHeader("Location");
			} else if (xhr.status == 404) //Not Found 用户名不存在
			{
				alert("用户不存在!");
			} else if (xhr.status == 401) //Unauthorized 密码错误
			{
				alert("密码错误!");
			} else {
				var ret = JSON.parse(xhr.responseText);
				alert(ret.reason);
			}

		}

	}
</script>


</head>

<body>
	<div class="wrap">
		<form action="" method="post">
			<section class="loginForm">
				<header>
					<h1>安吉零部件售后配送信息系统</h1>
				</header>
				<div class="loginForm_content">
					<fieldset>
						<div class="inputWrap">
							<input type="text" id="username" name="username" autofocus placeholder="请输入账号" required>
						</div>
						<div class="inputWrap">
							<input type="password" id="password" name="password" placeholder="请输入密码" required>
						</div>
					</fieldset>
					<fieldset></fieldset>
					<fieldset>
						<input type="submit" id="loginButton" value="登录" onclick="login();return false;">
					</fieldset>
				</div>
			</section>
		</form>
	</div>

</body>
</html>