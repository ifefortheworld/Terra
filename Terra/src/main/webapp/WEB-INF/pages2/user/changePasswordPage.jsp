<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>密码修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="resources/css/base/style.css" />

<script src="resources/js/base/CSCW_TIME.js" language="javascript"></script>
<script src="resources/js/base/pageheader.js" language="javascript"></script>
<script src="resources/js/base/menu.js"></script>
<script src="resources/js/modol_t_h2.js"></script>

<script type="text/javascript">
function $id(id){return document.getElementById(id);}

var xhr;

function submit()
{
	if($id("old").value == "" || $id("new").value == "" || $id("new_rep").value =="")
	{
		alert("请输入完整的信息!");
		return;
	}
	
	if($id("new").value != $id("new_rep").value)
	{
		alert("2次输入的新密码不一致,请重新输入!");
		return;
	}
	
	xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = ajaxCallBack;
	
	xhr.open("POST", "user/changePassword", true);
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	xhr.send("oldPassword="+$id("old").value+"&newPassword="+$id("new").value);
	
}

function ajaxCallBack()
{

	if(xhr.readyState == 4)
	{
		if(xhr.status == 200) //成功
		{
			alert(xhr.responseText);
			//window.location = "/";
		}
		else
		if(xhr.status == 401) //用户未登录,重新登录
		{	
			alert(xhr.responseText);
			//window.location = "/login";
		}
		else
		if(xhr.status == 409) //CONFLICT 密码错误
		{
			alert(xhr.responseText);
		}
		else
		{
		alert(xhr.responseText);
		}
		
	}
	
}
</script>

</head>

<body>
	<div id="mytab">
		<div class="pztion">
			<p>当前位置&nbsp;&gt;&gt;&nbsp;个人办公&nbsp;&gt;&gt;&nbsp;修改密码</p>
		</div>
		<div class="modol2">
			<div class="modol_t">
				<h2>修改密码</h2>
			</div>
			<div class="modol_d2">
				<div class="modol_d2_opera">&nbsp;</div>

				<div class="modol_d2_tab">
					<table>
						<tbody>
							<tr>
								<td class="r" width="150px">当前密码</td>
								<td class="l" width=""><input type="password" id="old"
									name="old" />
								</td>
							</tr>
							<tr>
								<td class="r" width="150px">新密码:<span class="ne">*</span></td>
								<td class="l" width="80%"><input type="password" id="new"
									name="new" /></td>
							</tr>
							<tr>
								<td class="r" width="150px">重新输入新密码:<span class="ne">*</span>
								</td>
								<td class="l" width=""><input type="password"
									id="new_rep" name="new_rep" /></td>
							</tr>
							<tr>
								<td class="r" width="150px"></td>
								<td class="l" width=""><input type="button" value="保存"
									class="but" onclick="return submit()" /></td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
				<!-- test -->
			</div>
		</div>

	</div>
</body>
</html>
