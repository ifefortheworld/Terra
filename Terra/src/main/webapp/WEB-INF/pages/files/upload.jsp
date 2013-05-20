<%@ page language="java" import="java.util.*,com.ireland.utils.*" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Upload</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap -->
    <!--<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">-->
    <!--Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <!--<link href="../css/bootstrap-responsive.css" rel="stylesheet">-->
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
        <li><a href="#">Storage</a> <span class="divider">/</span></li>
        <li class="active"><a href="#">Share</a> </li>
    </ul>
    <div class="row-fluid" style="margin-top: 10px">
        <div class="span12">
            <div class="well" >
                <!--<img src="img/Hen.png" class="img-rounded" style=";margin-left:20px;margin-right:10px;float:left;width: 64px;height: 64px;">-->
                <h1 style="float: left;margin-top:25px;margin-left:10px;font-size: 50px;">Upload File</h1>
                <div class="well" style="float: right;margin-bottom: 0px;padding:10px;border-width:2px;border-style:dotted ">
                    <img src="/img/Hen.png" class="img-rounded" style="float: left;margin-right:20px;float:left;width: 64px;height: 64px;">
                    <div style="float: left;padding-top: 5px">
                        <label>By <a>@<sec:authentication property="principal.username"/></a></label>
                        <label><%=new MyDate()%></label>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                <!--<div style="padding: 10px;padding-left: 30px">-->
                    <!--<img src="img/Hen.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 10px;margin-right:10px;float:left">-->
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
                    <label style="font-weight: bold">File <span class="label label-warning">Required!</span></label>
                    <div class="input-append ">
                        <input class="span11" type="file" name="file" id="file">
                    </div>
                    <!--<div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>-->
                    <!--<img src="img/ico-260px-file.png" class="img-rounded" style="margin-bottom: 10px;margin-right:20px;float:left;width: 128px;height: 128px;opacity: 0.7">-->
                    <!--<div style="float: left;width: 530px">-->
                        <label style="font-weight: bold"> Name  <span class="label label-warning">Required!</span></label>
                        <input class="input-block-level" type="text" placeholder="input something...." id="name">
                        
                        <label style="font-weight: bold"> Type  <span class="label label-warning">Required!</span></label>
                        <select style="width: 100%" id="type">
                            <option>Text</option>
                            <option>Video</option>
                            <option>Audio</option>
                            <option>Image</option>
                            <option>Bag</option>
                            <option>Other</option>
                        </select>
                    <!--</div>-->
                    <!--<div class="clearfix"></div>-->
                    <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                    
                    <label style="font-weight: bold">Detail</label>
                    <textarea class="input-block-level" rows="6" placeholder="input something...." id="detail"></textarea>
                    <div class="divider" style="margin-top: 10px;margin-bottom: 10px"></div>
                    <label style="font-weight: bold">Tag</label>
                    <textarea class="input-block-level" rows="2" placeholder="Tag..." id="tags"></textarea>
                    <!--<button class="btn" type="button" style="float: right;margin-bottom: 10px">Auto</button>-->
                    <div class="clearfix"></div>
                    <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                    <button class="btn btn-success" style="float: left">Add More...</button>
                    <button class="btn btn-danger" type="button" style="float:right;margin-bottom: 10px" id="uploadBtn">Upload</button>
                    <div class="clearfix"></div>
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
	$("#uploadBtn").click(function(){XHRUpload();return false;});
	
	function upload()
	{
		$.post(
					"/files/upload_",
					{
						"isShared" : $("#isShared").val(),
						"name" : $("#name").val(),
						"type" : $("#type").val(),
						"detail" : $("#detail").val(),
						"_tags" : $("#tags").val()
					},

					function(result) {
						if(result.status == "SUCCESS")
						{
							alert("上传成功");
							window.location = result.Location;
						}
					},
					"json");
	}
	

   function $id(id) { return document.getElementById(id);}
   
   var xhr = null;
   
   function XHRUpload()
   {   	  
   	  var formData = new FormData();
   	 /*  formData.append("isShared",$("#isShared").val()); */
   	  formData.append("name",$("#name").val());
   	  formData.append("type",$("#type").val());
   	  formData.append("detail",$("#detail").val());
   	  formData.append("_tags",$("#tags").val());
   	  formData.append("file",$id('file').files[0]);
   	  
   	  xhr = new XMLHttpRequest();  
   	  xhr.upload.onprogress = _onprogress;
   	  
   	  xhr.onload = _onload;
   	   	  
   	  xhr.open("POST", "/files/upload_", true);
   	  
   	  xhr.send(formData);
   }
   
   function _onprogress(evt) 
   {
   		//$("progress").innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
   		//$("uploadProgress").max = evt.total;
   		//$("uploadProgress").value = evt.loaded;
   }
   
   function _onload(evt){
   		var result = JSON.parse(evt.target.responseText);
		if(result.status == "SUCCESS")
		{
			alert("上传成功");
			window.location = result.Location;
		}
   }
   
   </script>
</body>
</html>