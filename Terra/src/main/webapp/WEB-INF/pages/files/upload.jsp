<%@ page language="java" import="java.util.*,com.ireland.utils.*" pageEncoding="utf-8"%>
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
    <title>Upload</title>
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
                        <li><a href="/<sec:authentication property="principal.username"/>/file-list" > @<sec:authentication property="principal.username"/></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> My Box <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="icon-user"></i> Sign in</a></li>
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

<div class="content container">
    <ul class="breadcrumb">
        <li><a href="#">Box</a> <span class="divider">/</span></li>
        <li><a href="#">File</a> <span class="divider">/</span></li>
        <li class="active"><a href="#">Upload</a> </li>
    </ul>
    <div class="well" style="margin-top: 10px; background: none">
        <div class="row-fluid">

            <div class="span10">
                <div class="well" style="background: none">
                    <h1>FILE DETAIL</h1>
                    <div class="divider" style="border-top: 1px solid #fff;margin-top: 10px;margin-bottom: 10px"></div>
                    <h2>Shared  <span class="label label-important">Important!</span></h2>
                    <select style="width: 100%" id="isShared">
                        <option value="true">Shared</option>
                        <option value="false">UnShared</option>
                    </select>
                    <h2>File Name  <span class="label label-warning">Required!</span></h2>
                    <input class="input-block-level" type="text" placeholder="input something...." id="name">
                    <h2>File Type  <span class="label label-warning">Required!</span></h2>
                    <select style="width: 100%" id="type">
                        <option>Text</option>
                        <option>Video</option>
                        <option>Audio</option>
                        <option>Image</option>
                        <option>Bag</option>
                        <option>Other</option>
                    </select>
                    <h2>Detail</h2>
                    <textarea class="input-block-level" rows="6" placeholder="input something...." id="detail"></textarea>
                    <div class="divider" style="border-top: 1px solid #e5e5e5;margin-top: 10px;margin-bottom: 10px"></div>
                    <h2>Tag</h2>
                    <textarea class="input-block-level" rows="2" placeholder="Tag..." id="tags"></textarea>
                    <button class="btn" type="button" style="float: right;margin-bottom: 10px">Auto</button>
                    <div class="clearfix"></div>
                    <h2>File</h2>
                    <input class="input-block-level" type="file" name="file" id="file">
                </div>
            </div>
            <div class="span2">
                <div class="well" style="background: none">
                    <img src="img/user-avater.png" class="img-rounded" data-src="holder.js/64x64" style="margin-bottom: 10px">
                    <h2>Owner</h2>
                    <input class="input-block-level" type="text" value="@<sec:authentication property="principal.username"/>" disabled>
                    <h2>Date</h2>
                    <input class="input-block-level" type="text" value="<%=new MyDate()%>" disabled>
                </div>
                <div class="well" style="background: none">
                    <h2>Sync To</h2>
                    <!--<div class="divider" style="border-top: 1px solid #fff;margin-top: 10px;margin-bottom: 10px"></div>-->
                    <ul style="list-style: none;margin: 0px;">
                        <li>
                            <label class="checkbox inline">
                                <input type="checkbox" value="option1"> Weibo
                            </label>
                        </li>
                        <li>
                            <label class="checkbox inline">
                                <input type="checkbox" value="option1"> Twitter
                            </label>
                        </li>
                        <li>
                            <label class="checkbox inline">
                                <input type="checkbox" value="option1"> FaceBook
                            </label>
                        </li>
                    </ul>
                    <div class="divider" style="border-top: 1px solid #ddd;margin-top: 10px;margin-bottom: 10px"></div>
                    <!--<br>-->
                    <a href="#" style="float: right">Setting</a>
                    <div class="clearfix"></div>
                </div>
                <!--<button class="btn" type="button" style="width: 100%;margin-bottom: 10px">Save</button>-->
                <button class="btn btn-danger" type="button" style="width: 100%;margin-bottom: 10px" id="uploadBtn">Upload</button>
            </div>

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
<script src="js/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
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
   	  formData.append("isShared",$("#isShared").val());
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