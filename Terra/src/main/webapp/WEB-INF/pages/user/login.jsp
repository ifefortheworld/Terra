<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SimpleDateFormat f=new SimpleDateFormat("yyyy/MM/dd");
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap -->
    <!--<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">-->
    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!--<link href="../css/bootstrap-responsive-min-980px.css" rel="stylesheet">-->
    <link href="css/docs.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        body{
            padding-top: 50px;
        }
        @media (min-width: 768px) and (max-width: 979px) {
            body{
                padding-top: 0px;
            }
        }
    </style>
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="#">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="#">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="#">
    <link rel="apple-touch-icon-precomposed" href="#">
    <link rel="shortcut icon" href="#">

</head>
<body>
<!--header-->
<header>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="/">Terra</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li class="active"><a href="/"> Search </a></li>
                        <li><a href="/myspace"> Files</a></li>
                        <li><a href="/myspace/columns"> Columns</a></li>
                        <li><a href="/myspace/pages">  Pages</a></li>
                        <li><a href="/myspace/notes"> Notes</a></li>
                        <!--<li><a href="#"> Upload </a></li>-->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"  data-toggle="dropdown">About <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="icon-envelope"></i> Contact</a></li>
                                <li><a href="#"><i class="icon-globe"></i> Introduction</a></li>
                            </ul>
                        </li>
                    </ul>
                    
                    <!-- 未登录 -->
                    <sec:authorize access="notHasAuthority('index')">
                    <ul class="nav pull-right">
                        <li><a href="/register"> Register</a></li>
                        <li><a href="/login"> Sign in</a></li>
                    </ul>
                    </sec:authorize>
                    
                    <!-- 已登录 -->
                    <sec:authorize access="hasAuthority('index')">
                    <ul class="nav pull-right">
                        <li class="dropdown active">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> @<sec:authentication property="principal.username"/> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li class="nav-header">Storage</li>
                                <li><a href="/myspace"><i class="icon-file"></i> Files</a></li>
                                <li><a href="/myspace/columns"><i class="icon-bookmark"></i> Columns</a></li>
                                <li><a href="/myspace/pages"><i class="icon-tag"></i> Pages</a></li>
                                <li><a href="/myspace/notes"><i class="icon-pencil"></i> Notes</a></li>
                                <li style="border: 1px dashed #ddd"></li>
                                <li><a href="/j_spring_security_logout"><i class="icon-user"></i> Log Out</a></li>
                            </ul>
                        </li>
                    </ul>
                    </sec:authorize>
                    
                </div><!--/.nav-collapse -->
            </div>
        </div>
    </div>
</header>
<!--header(end)-->
<!--container-->

<div class="content container">
    <!--<ul class="breadcrumb">-->
        <!--<li><a href="#">Box</a> <span class="divider">/</span></li>-->
        <!--<li class="active"><a href="#">Files</a> </li>-->
    <!--</ul>-->
    <div class="alert alert-info alert-block" style="margin-top: 10px">
        <strong>Tips!</strong> We design according to <strong>google</strong> standards!
    </div>
    <div class="row-fluid" style="margin-top: 10px;margin-bottom: 10px">
        <div class="span9">
            <div class="bulletin">
                <div class="content">
                    <h1 style="font-size: 60px;margin-bottom: 20px">Terra</h1>
                    <h2 style="font-size: 20px;margin-bottom: 20px;line-height: 1.5">This is a platform for Cloud Storage and Resource Sharing!</h2>
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" data-src="holder.js/64x64" src="img/ico-64px-lock.png">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Safe</h4>
                            <p>The perfect hardware and advanced technology make user feel safe.<a href="#">More...</a></p>
                        </div>
                    </div>
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" data-src="holder.js/64x64" src="img/ico-64px-cloud.png">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Convenient</h4>
                            <p>Every time,evey where,just need a browser!.<a href="#">More...</a></p>
                        </div>
                    </div>
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" data-src="holder.js/64x64" src="img/ico-64px-touch.png">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Easy</h4>
                            <p>Operating simply,everyone know how to use.<a href="#">More...</a></p>
                        </div>
                    </div>
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" data-src="holder.js/64x64" src="img/ico-64px-free.png">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Free</h4>
                            <p>Permanent free. It's the best advantage.Just need your support!<a href="#">More...</a></p>
                        </div>
                    </div>
                    <!--<p><a class="btn btn-info btn-large">Learn more</a></p>-->
                    <!--<ul class="propaganda-links">-->
                        <!--<li><a href="#"><i class="icon-flag icon-white"></i> Follow:@Terra</a></li>-->
                        <!--<li><a href="#"><i class="icon-envelope icon-white"></i> Terra@gmail.com</a></li>-->
                        <!--<li>version:1.0</li>-->
                    <!--</ul>-->
                </div>
                <!--<p><a class="btn btn-primary btn-large">Learn more</a></p>-->
            </div>
        </div>
        <div class="span3">
            <div class="hero-unit" style="padding:20px;background: none;border: 1px solid #ddd" >
                <form >
                    <!--form-signin-->
                    <h2 class="form-signin-heading" style="font-size: 20px;line-height: 1.5">Sign in Terra</h2>
                    <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 20px"></div>
                    <input type="text" class="input-block-level" placeholder="Email address" id="username" name="username" value="admin">
                    <input type="password" class="input-block-level" placeholder="Password" id="password" name="password" value="123456">
                    <input type="hidden" id="redirect_url" name="redirect_url" value="${param.redirect_url}">
                    <label class="checkbox">
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                    <button class="btn btn-block btn-primary" type="submit" id="loginBtn">Sign in</button>
                    <!--<button class="btn btn-block btn-info" type="submit">Register</button>-->
                    <div class="well" style="margin-top: 20px">
                        <label><i class="icon-hand-right"></i> Binding</label>
                        <a href="#"><span class="label label-important"> QQ</span></a>
                        <a href="#"><span class="label label-warning"> Weibo</span></a>
                        <a href="#"><span class="label label-success"> Gmail</span></a>
                        <a href="#"><span class="label label-important"> Baidu</span></a>
                        <a href="#"><span class="label label-warning"> 163</span></a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="well">
        <h2> What do you Find?</h2>
        <div class="well" style="background-color: #ddd">
        	<c:forEach var="tag" items="${tags}" varStatus="status">
        		<c:if test="${status.index %4 == 0}"><span class="label label-success">${tag.name}</span></c:if>	
        		<c:if test="${status.index %4 == 1}"><span class="label label-warning">${tag.name}</span></c:if>	
        		<c:if test="${status.index %4 == 2}"><span class="label label-important">${tag.name}</span></c:if>	
        		<c:if test="${status.index %4 == 3}"><span class="label label-info">${tag.name}</span></c:if>	
        	</c:forEach>
        </div>
    </div>
</div>
<!--container(end)-->
<!--footer-->
<footer>
    <div class="container">
        <p>Designed and built with all the love in the world by <a href="#">@Mocha</a></p>
        <ul class="footer-links">
            <li><a href="#">Blog</a></li>
            <li class="muted">·</li>
            <li><a href="#">Weibo</a></li>
        </ul>
    </div>
</footer>
<!--footer(end)-->
<script src="/js/jquery-latest.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	$("#loginBtn").click(function(){login();return false;});

	var xhr;

	function login() {
		xhr = new XMLHttpRequest();

		xhr.onreadystatechange = ajaxCallBack;

		xhr.open("POST", "j_spring_security_check", true);

		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		
		
		var redirect_url = $("#redirect_url").val();
		
		var param = "j_username=" + $("#username").val() + "&j_password=" + $("#password").val();
		
		if(redirect_url != null && redirect_url.length > 0)
		{
			param += "&redirect_url=" + redirect_url;
		}
		
		xhr.send(param);
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
});
</script>


</body>
</html>