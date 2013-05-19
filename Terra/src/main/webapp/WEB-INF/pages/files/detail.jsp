<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Detail</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap -->
    <!--<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">-->
    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <!--<link href="../css/bootstrap-responsive-min-980px.css" rel="stylesheet">-->
    <link href="/css/docs.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        body{
            padding-top: 50px;
        }
        @media (min-width: 768px) and (max-width: 979px) {
            body{
                padding-top: 40px;
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

<div class="container">
    <div class="alert alert-info alert-block" style="margin-top: 10px">
        <strong>Good News!</strong>
        <!--<a href="#"><span class="label label-warning"></span></a>-->
    </div>
    <ul class="breadcrumb" style="border-bottom: none">
        <li><a href="#">Terra</a> <span class="divider">/</span></li>
        <li class="active"><a href="#">File Detail</a> </li>
    </ul>
    <div class="row-fluid" style="margin-top: 10px">
        <div class="span12">
            <div class="well" style="background: none" >

                <!--<img src="/img/Hen.png" class="img-rounded" style=";margin-left:20px;margin-right:10px;float:left;width: 64px;height: 64px;">-->
                <div class="container-header">
                    <h1 style="float: left;margin-top:25px;margin-left:10px;font-size: 50px;">${file.name}</h1>
                    <!--<div class="well" style="float: right;margin-bottom: 0px;padding:10px;border-width:2px;border-style:dotted ">-->
                        <!--<button class="btn btn-danger" type="button" style="margin-bottom: 10px">DownLoad</button>  <br>-->
                        <!--<button class="btn btn-danger" type="button" style="margin-bottom: 10px">Favorite</button>-->
                        <!--&lt;!&ndash;<img src="/img/Hen.png" class="img-rounded" style="float: left;margin-right:20px;float:left;width: 64px;height: 64px;">&ndash;&gt;-->
                        <!--&lt;!&ndash;<div style="float: left;padding-top: 5px">&ndash;&gt;-->
                            <!--&lt;!&ndash;<label>By <a>@Hen</a></label>&ndash;&gt;-->
                            <!--&lt;!&ndash;<label>2013-3-13</label>&ndash;&gt;-->
                        <!--&lt;!&ndash;</div>&ndash;&gt;-->
                        <!--&lt;!&ndash;<div class="clearfix"></div>&ndash;&gt;-->
                    <!--</div>-->
                    <div class="well" style="float: right;;margin-bottom: 0px;padding:10px;border-width:2px;border-style:dotted ">
                        <img src="/img/Hen.png" class="img-rounded" style="float: left;margin-right:20px;float:left;width: 64px;height: 64px;">
                        <div style="float: left;padding-top: 5px">
                            <label>By <a>@Hen</a></label>
                            <label>2013-3-13</label>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>

                <div class="container-shop" style="padding: 15px;margin-bottom: 0px">
                    <div class="shop-left" style="float: left;width: 650px">

                        <button class="btn btn-primary" type="button" ><i class="icon-comment icon-white"></i> 50</button>
                        <!--<div class="progress">-->
                            <!--<div class="bar bar-info" style="width: 35%;"><i class="icon icon-file icon-white"></i> 35% File</div>-->
                            <!--&lt;!&ndash;<div class="bar bar-warning" style="width: 20%;"> 20% Page</div>&ndash;&gt;-->
                            <!--&lt;!&ndash;<div class="bar bar-danger" style="width: 10%;"> 10% Note</div>&ndash;&gt;-->
                            <!--<div class="bar" style="background:#afafaf;width: 65%;color: #ffffff"><i class="icon icon-hdd icon-white"></i> 65% Available</div>-->
                        <!--</div>-->
                    </div>
                    <div class="shop-right" style="float: right">
                        <!--<div style="margin-right: 10px;float: left">-->
                        <!--</div>-->
                        <div style="margin-right: 0px;float: left">
                            <button class="btn btn-success" type="button" ><i class="icon-thumbs-up icon-white"></i> ${file.favsCnt}</button>
                            <button class="btn btn-warning" type="button" ><i class="icon-star-empty icon-white"></i> ${file.viewsCnt}</button>
                            
                            <a href="${file.fileUrl}" id="downBtn">
                            <button class="btn btn-danger" type="button"  ><i class="icon-download-alt icon-white"></i> <span id="downsCnt">${file.downsCnt}</span></button>
                            </a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>

                <div class="container-detail">
                    <!--<div style="padding: 10px;padding-left: 30px">-->
                    <!--<img src="/img/Hen.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 10px;margin-right:10px;float:left">-->
                    <!--<div style="float: left">-->
                    <!--<label style="font-style: normal;font-size: 18px;margin-bottom: 10px">By <em></em><a href="#">@Hen</a></em><br></label>-->
                    <!--<label style="font-style: normal;font-size: 18px;">2013-3-17</a></label>-->
                    <!--</div>-->
                    <!--&lt;!&ndash;<label style="display: none">By <a>@Hen</a></label>&ndash;&gt;-->
                    <!--&lt;!&ndash;<label>2013-3-17</label>&ndash;&gt;-->
                    <!--<div class="well" style="float: right;width: 300px">-->
                    <!--<label></label>-->
                    <!--</div>-->
                    <!--<div class="clearfix"></div>-->
                    <!--</div>-->

                    <!--<div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>-->

                    <div style="padding: 20px;padding-top:10px ">
                        <div style="float: left">
                            <div style="margin-bottom: 20px">
                                <label style="font-weight: bold;font-size: 20px">File Code</label>
                                <p>${file.id}</p>
                            </div>
                            <!--<div class="input-append ">-->
                                <!--<input class="span11" id="appendedInputButton" type="text">-->
                                <!--<button class="btn" type="button">Select</button>-->
                            <!--</div>-->
                            <!--<div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>-->
                            <!--<img src="/img/ico-260px-file.png" class="img-rounded" style="margin-bottom: 10px;margin-right:20px;float:left;width: 128px;height: 128px;opacity: 0.7">-->
                            <!--<div style="float: left;width: 530px">-->
                            <div style="margin-bottom: 20px">
                                <label style="font-weight: bold;font-size: 20px">File Name</label>
                                <p>${file.name}</p>
                            </div>
                            <div style="margin-bottom: 20px">
                                <label style="font-weight: bold;font-size: 20px">File Type</label>
                                <p>     
			                    	<c:if test="${file.type == 'Text'}"> <i class="icon-book"></i></c:if>
			                       	<c:if test="${file.type == 'Video'}"><i class="icon-film"></i></c:if>
			                       	<c:if test="${file.type == 'Audio'}"><i class="icon-music"></i></c:if>
			                       	<c:if test="${file.type == 'Image'}"><i class="icon-picture"></i></c:if>
			                       	<c:if test="${file.type == 'Bag'}">  <i class="icon-lock"></i></c:if>
			                       	<c:if test="${file.type == 'Other'}"><i class="icon-file"></i></c:if> 
			                       	<strong>${file.type}</strong>
		                       </p>
                            </div>
                            <div style="margin-bottom: 20px">
                                <label style="font-weight: bold;font-size: 20px">Date</label>
                                <p>${file.uploadDate}</p>
                            </div>
                        </div>
                        <div style="float: right;margin-top:40px;margin-right: 20px">
                            <img src="/img/ico-260px-book.png" class="img-rounded" style="float: left;margin-right:10px;float:left;width: 160px;height: 160px;">
                        </div>
                        <div class="clearfix"></div>
                        <!--</div>-->
                        <!--<div class="clearfix"></div>-->
                        <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                        <div style="margin-bottom: 20px">
                            <label style="font-weight: bold;font-size: 20px">Detail </label>
                            <p>${file.detail}</p>
                        </div>
                        <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                        <div style="margin-bottom: 40px">
                        <label style="font-weight: bold;font-size: 20px">Tag </label>
		                    <c:forEach var="tag" items="${file.tags}" varStatus="status">
			                    <a href="#">
			                    	<c:if test="${status.index %4 == 0}"><span class="badge badge-success"   style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
			                    	<c:if test="${status.index %4 == 1}"><span class="badge badge-warning"   style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
			                    	<c:if test="${status.index %4 == 2}"><span class="badge badge-important" style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
			                    	<c:if test="${status.index %4 == 3}"><span class="badge badge-info"      style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
			                    </a>
		                    </c:forEach>                      </div>
                        <!--<button class="btn" type="button" style="float: right;margin-bottom: 10px">Auto</button>-->
                        <div class="clearfix"></div>
                        <!--<div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>-->
                        <!--&lt;!&ndash;<button class="btn btn-success" style="float: left">Add More...</button>&ndash;&gt;-->
                        <!--<button class="btn btn-danger" type="button" style="float:right;margin-bottom: 10px">DownLoad</button>-->
                        <div class="clearfix"></div>
                    </div>
                    <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                 </div>
                <div class="container-comment" style="padding:20px">
                    <div class="well">
                        <label style="font-weight: bold;font-size: 20px;margin-bottom: 10px">Comment </label>
                        <textarea class="input-block-level" rows="5" placeholder="input something..." id="content"></textarea>
                        <a href="#" style="float: left">@${file.owner}</a>
                        <button class="btn btn-primary" type="button" style="float: right;margin-bottom: 10px" id="publishBtn">Publish</button>
                        <div class="clearfix"></div>
                    </div>
                    <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                    <div class="alert alert-info">
                        <strong>Comments!</strong> The file have <span class="label label-warning">${page.totalElements} </span> Comments!.
                    </div>
                    <div class="content">
                    <c:if test="${valComment != null }">
                        <!--<label style="font-weight: bold;font-size: 20px;margin-bottom: 10px">Comment List</label>-->
                        <div class="well" style="background-color: #dddddd;border: none">
                            <label style="margin: 0;font-weight: bold;font-size: 15px;">The Most Valuable Comment!</label>
                            <div class="divider" style="border-top: 1px solid #fff;margin-top: 10px;margin-bottom: 10px"></div>
                            <div class="media">
                                <a class="pull-left" href="#">
                                    <img src="/img/Hen.png" class="media-object" data-src="holder.js/64x64">
                                </a>
                                <div class="media-body">
                                    <label class="media-heading" style="float: left;font-weight: bold;font-size: 18px;margin-right: 20px">@${valComment.owner}</label>
                                    <label  style="float: left;margin-right: 20px"> ${valComment.date}</label>
                                    
                                    <div class="clearfix"></div>
                                    <div class="divider" style="border-top: 1px dashed #fff;margin-top: 10px;margin-bottom: 10px"></div>
                                    <div class="media">
                                        <p>${valComment.content}</p>
                                    </div>
                                    <a class="btn voter" id="${valComment.id}" href="#" style="float: right"><i class="icon-thumbs-up"></i> <span id="num_${valComment.id}">${valComment.votes}</span></a>
                                    <a class="btn" href="#" style="float: right;margin-right: 10px"><i class="icon-hand-right"></i> </a>
                                </div>
                            </div>
                        </div>
                        
                        <c:forEach var="comment" items="${comments}">
                        <c:if test="${comment.id != valComment.id}">
                        
                        <div class="media">
                            <a class="pull-left" href="#">
                                <img src="/img/Hen.png" class="media-object" data-src="holder.js/64x64">
                            </a>
                            <div class="media-body">
                                <label class="media-heading" style="float: left;font-weight: bold;font-size: 18px;margin-right: 20px">@${comment.owner}</label>
                                <label  style="float: left;margin-right: 20px"> ${comment.date}</label>
                                
                                <div class="clearfix"></div>
                                <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                                <div class="media">
                                    <p>${comment.content}</p>
                                </div>
                                <a class="btn voter" id="${comment.id}" href="#" style="float: right"><i class="icon-thumbs-up"></i> <span id="num_${comment.id}">${comment.votes}</span></a>
                                <a class="btn" href="#" style="float: right;margin-right: 10px"><i class="icon-hand-right"></i> </a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                        
                        </c:if>
                        </c:forEach>
                        
                        <a href="" style="float: right">More...</a>
                        <div class="clearfix"></div>
                    </c:if>
                    </div>
                </div>
                </div>
            </div>

        </div>
        <!--<div class="span2" >-->
        <!--</div>-->
    </div>
    <!--<div class="progress">-->
        <!--<div class="bar bar-info" style="width: 35%;"><i class="icon icon-file icon-white"></i> 35% File</div>-->
        <!--&lt;!&ndash;<div class="bar bar-warning" style="width: 20%;"> 20% Page</div>&ndash;&gt;-->
        <!--&lt;!&ndash;<div class="bar bar-danger" style="width: 10%;"> 10% Note</div>&ndash;&gt;-->
        <!--<div class="bar" style="background:#afafaf;width: 65%;color: #ffffff"><i class="icon icon-hdd icon-white"></i> 65% Available</div>-->
    <!--</div>-->
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
<script src="/js/jquery-latest.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script type="text/javascript">
$("#publishBtn").click(function(){
	if($("#content").val() == "")
	{
		alert("评论不能为空!");
		return;
	}
	
	$.post(
			"/files/${file.id}/coments",
			{
				"content" : $("#content").val(),
			},

			function(result) {
				if(result.status != "SUCCESS")
				{
					alert("发表失败");
					return;
				}
				$("#content").val("");
				window.location.reload();
			},
			"json"
	);
	
	return false;
});

$("#downBtn").click(function(){
	var file_id = "${file.id}";
	
	$.post(
			"/files/downsCnt/inc",
			{
				"file_id" : file_id
			},

			function(result) {
				if(result.status == "SUCCESS"){
				}
			},
			"json"
	);
	
	<%//不等结果返回,直接显示+1了%>
	var num = $("#downsCnt").html();
	num++;
	$("#downsCnt").html(num);
	
	return true;
});

$(".voter").click(function(){
	var comment_id = $(this).attr("id");
	
	$.post(
			"/comments/inc",
			{
				"comment_id" : comment_id
			},

			function(result) {
				//if(result.status != "SUCCESS")
					//alert("vote fail!");
			},
			"json"
	);
	
	<%//不等结果返回,直接显示+1了%>
	var num = $("#num_"+comment_id).html();
	num++;
	$("#num_"+comment_id).html(num);
	
	return false;
});
</script>
</body>
</html>