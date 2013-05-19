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
                padding-top: 40px;
            }
        }
        /*.well-columns a img{*/
            /*margin: 2px;*/
        /*}*/
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

<div class="container">
    <div class="alert alert-info alert-block" style="margin-top: 10px">
        <strong>Good News!</strong> Terra have 100 files <a href="#"><span class="label label-warning">Learn More!</span></a>
    </div>
    <div  class="hero-unit" style="background:none;margin-bottom: 0px">
        <h1 style="text-align: center;font-size: 90px;">Terra</h1>
        <h2 style="text-align: center;font-size: 20px">We All Love Sharing</h2>
        <br>
        <form class="form-search" action="/search" style="text-align: center;">
            <div class="input-append" style="margin-bottom: 20px">
                <input type="text" name="q" class="span8 search-query" placeholder="input something...">
                <button type="submit" class="btn">Search</button>
            </div>
            <br>
            <!--<div style="margin-top: 10px">-->
                <!--<a href="#" style="margin-right: 10px;font-size: 15px;">Storage</a>-->
                <!--<a href="#" style="margin-right: 10px;font-size: 15px;">Columns</a>-->
                 <!--|-->
                <!--<a href="#" style="margin-right: 10px;margin-left: 10px;font-size: 15px;">More...</a>-->
            <!--</div>-->
            <div style="margin-top: 10px">
                <p>
                    <img src="img/ico-64px-pig-blue.png" />
                    <a href="#"> Learn more about Terra.....</a>
                </p>
            </div>
        </form>
    </div>

    <div class="row-fluid">
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

</div>
<!--container(end)-->
<!--footer-->
<footer>
    <div class="container">
        <p>Designed and built with all the love in the world by <a href="#">@moc.Terra</a></p>
        <ul class="footer-links">
            <li><a href="#">Blog</a></li>
            <li class="muted">·</li>
            <li><a href="#">Weibo</a></li>
        </ul>
    </div>
</footer>
<!--footer(end)-->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>