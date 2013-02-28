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
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>File Detail</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap -->
    <!--<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">-->
    <!-- Le styles -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <!--<link href="../css/bootstrap-responsive-min-980px.css" rel="stylesheet">-->
    <link href="../css/docs.css" rel="stylesheet">
    <style type="text/css" rel="stylesheet">
        body{
            padding-top: 50px;
        }
        @media (min-width: 768px) and (max-width: 979px) {
            body{
                padding-top: 0px;
            }
        }
        .checkbox{
            padding-left: 0px;
        }
        h2{
            font-size: 22px;
            margin: 0px;
        }
        h3{
            font-size: 20px;
            margin: 0px;
        }
        h4{
            font-size: 18px;
            margin: 0px;
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
                <a class="brand" href="#">Terra</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li class="active"><a href="/myspace/"> Home</a></li>
                        <!--<li><a href="#"> Upload </a></li>-->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Platform <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="icon-file"></i> Files</a></li>
                                <li><a href="#"><i class="icon-bookmark"></i> Columns</a></li>
                                <li><a href="#"><i class="icon-tag"></i> Pages</a></li>
                                <li><a href="#"><i class="icon-pencil"></i> Notes</a></li>
                            </ul>
                        </li>
                        <li><a href="#"> Search </a></li>
                        <li><a href="#"> Columns </a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"  data-toggle="dropdown">About <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="icon-envelope"></i> Contact</a></li>
                                <li><a href="#"><i class="icon-globe"></i> Introduction</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav pull-right">
                        <li><a href="/myspace/file-list"> @<sec:authentication property="principal.username"/></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> My Box <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                            	<sec:authorize access="!hasAuthority('index')">
                                	<li><a href="/"><i class="icon-user"></i> Sign in</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasAuthority('index')">
                                	<li><a href="/j_spring_security_logout"><i class="icon-user"></i> Sign out</a></li>
                                </sec:authorize>
                                <li class="divider"></li>
                                <li><a href="#"><i class="icon-file"></i> Files</a></li>
                                <li><a href="#"><i class="icon-bookmark"></i> Columns</a></li>
                                <li><a href="#"><i class="icon-tag"></i> Pages</a></li>
                                <li><a href="#"><i class="icon-pencil"></i> Notes</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="icon-arrow-up"></i> Upload </a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>
    </div>
</header>
<!--header(end)-->
<!--container-->

<div class="container">
    <ul class="breadcrumb">
        <li><a href="#">Box</a> <span class="divider">/</span></li>
        <li class="active"><a href="#">File</a> </li>
    </ul>
    <!--<div class="well" style="margin-top: 10px;background: none">-->

        <div class="alert alert-info alert-block" style="margin-top: 10px">
            <strong>Tips!</strong> Please give your comment!
        </div>
        <div class="row-fluid">

            <div class="span10">

                <div class="well" style="background: none">
                    <img width="42px" src="img/duck.png" style="float:right;margin-top: 11px;margin-right: 20px">
                    <h1>${file.name}</h1>
                    <div class="clearfix"></div>
                    <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                    <!--<label>Shared</label>-->
                    <!--<select style="width: 100%">-->
                        <!--<option>Shared</option>-->
                        <!--<option>UnShared</option>-->
                    <!--</select>-->
                    <!--<label><strong>File Name</strong></label>-->
                    <!--<h2 style="font-size: 20px">File Name</h2>-->
                    <!--<p>Design Pattern</p>-->
                    <!--<input class="input-block-level" type="text" placeholder="input something....">-->
                    <h2>File Code</h2>
                    <p>${file.id}</p>
                    <h2>File Owner</h2>
                    <p>@${file.owner}</p>
                    <h2>File Type</h2>
                    <p>     
                    	<c:if test="${file.type == 'Text'}"> <i class="icon-book"></i></c:if>
                       	<c:if test="${file.type == 'Video'}"><i class="icon-film"></i></c:if>
                       	<c:if test="${file.type == 'Audio'}"><i class="icon-music"></i></c:if>
                       	<c:if test="${file.type == 'Image'}"><i class="icon-picture"></i></c:if>
                       	<c:if test="${file.type == 'Bag'}">  <i class="icon-lock"></i></c:if>
                       	<c:if test="${file.type == 'Other'}"><i class="icon-file"></i></c:if> 
                       	<strong>${file.type}</strong>
                       </p>
                    <h2>Upload Date</h2>
                    <p>${file.uploadDate}</p>
                    <h2>Detail</h2>
                    <p>${file.detail}</p>
                    <!--<textarea class="input-block-level" rows="6" placeholder="input something...."></textarea>-->
                    <div class="divider" style="border-top: 1px solid #e5e5e5;margin-top: 10px;margin-bottom: 10px"></div>
                    <h2>Tag</h2>
                    <c:forEach var="tag" items="${file.tags}" varStatus="status">
                    <a href="#">
                    	<c:if test="${status.index %4 == 0}"><span class="badge badge-success"   style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
                    	<c:if test="${status.index %4 == 1}"><span class="badge badge-warning"   style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
                    	<c:if test="${status.index %4 == 2}"><span class="badge badge-important" style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
                    	<c:if test="${status.index %4 == 3}"><span class="badge badge-info"      style="float: left;margin-left: 5px;">${tag.name}</span></c:if>
                    </a>
                    </c:forEach>
                    <div class="clearfix"></div>
                    <!--<textarea class="input-block-level" rows="2" placeholder="Tag..."></textarea>-->
                    <!--<button class="btn" type="button" style="float: right;margin-bottom: 10px">Auto</button>-->
                    <!--<div class="clearfix"></div>-->
                    <!--<label>Tag</label>-->
                    <!--<div class="well">-->

                    <!--</div>-->
                    <!--<button class="btn" type="button" style="margin-bottom: 10px">Auto</button>-->
                </div>
                <div class="well" style="background: none">
                    <h2 style="margin-right:10px;font-size: 20px">Comment</h2>
                    <textarea class="input-block-level" rows="5" placeholder="input something..." id="content"></textarea>
                    <a href="#" style="float: left">@<sec:authentication property="principal.username"/></a>
                    <button class="btn btn-primary" type="button" style="float: right;margin-bottom: 10px" id="publishBtn">Publish</button>
                    <div class="clearfix"></div>
                    <div class="alert alert-info">
                        <strong>Comments!</strong> The file have <span class="label label-warning">${page.totalElements}</span>. Comments!
                    </div>
                    <div class="content">
                    	<c:if test="${valComment != null }">
                        <div class="well">
                            <h4 style="margin: 0">The Most Valuable Comment!</h4>
                            <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                            <div class="media">
                                <a class="pull-left" href="#">
                                    <img src="img/Hen.png" class="media-object" data-src="holder.js/64x64">
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading" style="float: left;margin-right: 20px">@${valComment.owner}</h4>
                                    <label  style="float: left;margin-right: 20px">${valComment.date}</label>
                                    <a href="#" style="float: left;margin-right: 20px" id="${valComment.id}" class="voter"><span class="label label-warning"><i class="icon-thumbs-up icon-white"></i> <span id="num_${valComment.id}">${valComment.votes}</span></span></a>
                                    <div class="clearfix"></div>
                                    <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                                    <div class="media">
                                        <p>${valComment.content}</p>
                                    </div>
                                    <a href="#" style="float: right"><i class="icon-hand-right"></i> Quote</a>
                                </div>
                            </div>
                        </div>
                        <div class="divider" style="border-top: 1px solid #fff;margin-top: 10px;margin-bottom: 10px"></div>
                        </c:if>
                        
                        <c:forEach var="comment" items="${comments}">
                        <c:if test="${comment.id != valComment.id}">
                        <div class="media">
                            <a class="pull-left" href="#">
                                <img src="img/Hen.png" class="media-object" data-src="holder.js/64x64">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading" style="float: left;margin-right: 20px">@${comment.owner}</h4>
                                <label  style="float: left;margin-right: 20px">${comment.date}</label>
                                <a href="#" style="float: left;margin-right: 20px" id="${comment.id}" class="voter"><span class="label label-warning"><i class="icon-thumbs-up icon-white"></i> <span id="num_${comment.id}">${comment.votes}</span></span></a>
                                <div class="clearfix"></div>
                                <div class="divider" style="border-top: 1px dashed #f5f5f5;margin-top: 10px;margin-bottom: 10px"></div>
                                <div class="media">
                                    <p>${comment.content}</p>
                                </div>
                                <a href="#" style="float: right"><i class="icon-hand-right"></i> Quote</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="divider" style="border-top: 1px solid #fff;margin-top: 10px;margin-bottom: 10px"></div>
                        </c:if>
                        </c:forEach>
                        
                        
                        <a href="" style="float: right">More...</a>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <div class="span2">
            	<c:if test="${file.type == 'Text'}"><img src="/img/ico-260px-text.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px"></c:if>
            	<c:if test="${file.type == 'Video'}"><img src="/img/ico-260px-video.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px"></c:if>
            	<c:if test="${file.type == 'Audio'}"><img src="/img/ico-260px-audio.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px"></c:if>
            	<c:if test="${file.type == 'Image'}"><img src="/img/ico-260px-image.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px"></c:if>
            	<c:if test="${file.type == 'Bag'}"><img src="/img/ico-260px-bag.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px"></c:if>
            	<c:if test="${file.type == 'Other'}"><img src="/img/ico-260px-other.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px"></c:if>
                
                <img src="img/tag-mostvaluable.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 20px">
                <c:if test="${user.username == file.owner}">
                <button class="btn btn-info" type="button" style="width: 100%;margin-bottom: 10px">Change</button>
                </c:if>
                <c:if test="${user.username != file.owner}">
                <button class="btn btn-success" type="button" style="width: 100%;margin-bottom: 10px">Favorite</button>
                </c:if>
                <a href="${file.fileUrl}" id="downBtn">
                <button class="btn btn-danger" type="button" style="width: 100%;margin-bottom: 10px">Download</button>
                </a>
                <div class="well" style="background: none">
                    <!--<h2>Statistical</h2>-->
                    <!--<div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>-->
                    <ul style="list-style: none;margin: 0">
                        <li>
                            <label><i class=" icon-eye-open"></i> Views <span class="label label-info">${file.viewsCnt}</span></label>
                            <div class="progress progress-info progress-striped">
                                <div class="bar" style="width: ${file.viewsCnt}%"></div>
                            </div>
                        </li>
                        <li>
                            <label><i class=" icon-heart"></i> Favs  <span class="label label-success">${file.favsCnt}</span></label>
                            <div class="progress progress-success progress-striped">
                                <div class="bar" style="width: ${file.favsCnt}%"></div>
                            </div>
                        </li>
                        <li>
                            <label><i class=" icon-arrow-down"></i> Downs <span class="label label-important" id="downsCnt">${file.downsCnt}</span></label>
                            <div class="progress progress-danger progress-striped">
                                <div class="bar" style="width: ${file.downsCnt}%"></div>
                            </div>
                        </li>
                    </ul>
                    <!--<div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>-->
                    <!--<a href="#" style="float: right">More....</a>-->
                    <div class="clearfix"></div>
                </div>
                <!--<button class="btn btn-info" type="button" style="width: 100%;margin-bottom: 10px" disabled>Save</button>-->
                <!--<div class="well" style="background: none">-->
                    <!--<img src="img/duck.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 10px">-->
                    <!--<h2>Owner</h2>-->
                    <!--<input class="input-block-level" type="text" value="@Terra" disabled>-->
                    <!--<h2>Date</h2>-->
                    <!--<input class="input-block-level" type="text" value="2013/1/14" disabled>-->
                <!--</div>-->
                <!--<div class="well" style="background-color: #dddddd;border: none">-->
                    <!--<h4>Share</h4>-->

                <!--</div>-->
                <!--<img src="img/user-avater.png" style="margin: 0 auto">-->
            </div>
        </div>
    <!--</div>-->
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
				if(result.status == "SUCCESS")
					alert("发表成功");
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