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
    <title>Result</title>
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
                        <li class="active"><a href="#"> Search </a></li>
                        <li><a href="#"> Files</a></li>
                        <li><a href="#"> Columns</a></li>
                        <li><a href="#">  Pages</a></li>
                        <li><a href="#"> Notes</a></li>
                        <!--<li><a href="#"> Upload </a></li>-->
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle"  data-toggle="dropdown">About <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="icon-envelope"></i> Contact</a></li>
                                <li><a href="#"><i class="icon-globe"></i> Introduction</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav pull-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> @Hen <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="icon-file"></i> Files</a></li>
                                <li><a href="#"><i class="icon-bookmark"></i> Columns</a></li>
                                <li><a href="#"><i class="icon-tag"></i> Pages</a></li>
                                <li><a href="#"><i class="icon-pencil"></i> Notes</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="icon-user"></i> Log Out</a></li>
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
    <div class="alert alert-info alert-block" style="margin-top: 10px">
        <strong>Good News!</strong>
        <!--<a href="#"><span class="label label-warning"></span></a>-->
    </div>
    <div class="well" style="margin-bottom: 0px;background: url(../img/background.png);background-repeat: no-repeat;background-position: center;">
        <div style="float: left;color: #ffffff">
            <h1>Terra</h1>
        </div>
        <div style="float: left;margin-top: 17px;margin-left: 17px">
            <form style="text-align: center;" action="/search">
                <div style="margin-bottom: 20px">
                    <input type="text" name="q" value="${param.q}" style="width:500px;margin: 0px;margin-right: 10px" placeholder="input something...">
                    <button type="submit" class="btn btn-primary" style="width: 70px"><i class="icon-search icon-white" ></i> </button>
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="row-fluid" style="margin-top: 5px">

        <div class="span12">

        <div class="well" style="margin-bottom: 0px;background:none">
        <div class="alert alert-warning " style="margin-bottom: 10px" >
        <strong>Tips!</strong>
        <!--<a href="#"><span class="label label-warning"></span></a>-->
        </div>
        <div >
            <ul class="nav nav-pills" >
                <li class="active">
                    <a href="#"><i class="icon-file"></i> All File</a>
                </li>
                <li>
                    <a href="#"><i class="icon-book" style="opacity: 0.5"></i> Doc</a>
                </li>
                <li>
                    <a href="#"><i class="icon-film" style="opacity: 0.3"></i> Video</a>
                </li>
                <li>
                    <a href="#"><i class="icon-music" style="opacity: 0.3"></i> Audio</a>
                </li>
                <li>
                    <a href="#"><i class="icon-picture" style="opacity: 0.3"></i> Image</a>
                </li>
                <li>
                    <a href="#"><i class="icon-lock" style="opacity: 0.3"></i> Zip</a>
                </li>
                <li>
                    <a href="#"><i class="icon-file" style="opacity: 0.3"></i> Other</a>
                </li>
            </ul>
        </div>



      <div class="divider" style="border-top: 1px dashed #ddd;margin-top: 10px;margin-bottom: 10px"></div>
      <table class="table table-hover table-striped" style="word-break:break-all; table-layout: fixed;">
        <thead>
            <tr>
                <th class="table-th-checkbox" style="width: 5%">
                    <!--<label class="checkbox">-->
                    <!--<input type="checkbox">-->
                    <!--</label>-->
                </th>
                <th class="table-th-type" style="width: 10%">Type</th>
                <th class="table-th-name" style="width: 25%">Name</th>
                <th class="table-th-detail" style="width: 10%">Flag</th>
                <th class="table-th-owner" style="width: 15%">Owner</th>
                <th class="table-th-date"  style="width: 15%">Date</th>
                <th class="table-th-downloads" style="width: 20%">Function</th>
            </tr>
        </thead>
            <tbody id="filesTableTBody">

   
            <tr>


                

            </tr>
 
            <c:forEach var="file" items="${files}">
  
            <tr>
                <td>
                    <label class="checkbox">
                        <input type="checkbox" id="${file.id}" class="_checkbox">
                    </label>
                </td>
                
                <td>
                	<c:if test="${file.type == 'Text'}"> <i class="icon-book" style="opacity: 0.5"></i></c:if>
                	<c:if test="${file.type == 'Video'}"><i class="icon-film" style="opacity: 0.5"></i></c:if>
                	<c:if test="${file.type == 'Audio'}"><i class="icon-music" style="opacity: 0.5"></i></c:if>
                	<c:if test="${file.type == 'Image'}"><i class="icon-picture" style="opacity: 0.5"></i></c:if>
                	<c:if test="${file.type == 'Bag'}">  <i class="icon-lock" style="opacity: 0.5"></i></c:if>
                	<c:if test="${file.type == 'Other'}"><i class="icon-file" style="opacity: 0.5"></i></c:if>
                </td>
                <td><a href="/files/${file.id}">${file.name}</a></td>
                <td>
                    <a href="#"><i class="icon-flag" style="opacity: 0.3"></i></a>
                </td>
                <td><span class="badge badge-mine">${file.owner}</span></td>
                <td>${file.uploadDate}</td>
                
                <!-- Function -->
                <td>
                    <!--<a href="#"><i class="icon-eye-open" style="opacity: 0.5;margin-right: 5px"></i></a>-->
                    <a href="${file.fileUrl}"><i class="icon-download-alt" style="opacity: 0.5;margin-right: 10px"></i></a>
                    <!-- <a href="#"><i class="icon-remove" style="opacity: 0.5;margin-right: 5px"></i></a> -->
                </td>

            </tr>
            
            </c:forEach> 

        </tbody>
         <tfoot>

        </tfoot>
        </table>
        <div class="pagination pagination-small pagination-right" >
            <c:if test="${page.totalPages > 0}">
                <ul id="fileTablePag">
                	<%// 如果当前而是第一页,则disable上一页%>
                	<c:if test="${page.number == 0}"><li class="disabled"><a>«</a></li></c:if>
                	<%// 如果有下一页,则显示上一页  %> 
                	<c:if test="${page.number != 0}"><li><a href="/myspace/file-list?page=${page.number}&sort=${param.sort}&status=${param.status}">«</a></li></c:if>

                	<%// 总页数少于等于5页,直接显示 %>
                	<c:if test="${page.totalPages <= 5}">
                   	<c:set var="begin" value="0"/>
                   	<c:set var="end" value="${page.totalPages - 1}"/>
                	</c:if>
                	
                    <%// 总页数大于5页 %>
                	<c:if test="${page.totalPages > 5}">
                		<%//如果左边省略,则左边界为[page.number - 2,否则为[0,如果右边省略,则右边界为page.number + 2],否则为: page.totalPages-1]%>
                		<c:set var="begin" value="${(page.number+1 < page.totalPages - 2)?(
               																		(page.number+1 > 3) ? (page.number - 2) : 0
               																		)
               																	 : (page.totalPages - 5)}"/>
                  		<c:set var="end" value="${(page.number+1 > 3)? (
               					   								(page.number+1 < page.totalPages - 2) ? (page.number + 2) : page.totalPages -1
               					   							   )
               					   							 : 
               					   							 4}"/>
                	</c:if>
                	
               	    <%// 显示左边省略 %>
               		<c:if test="${(page.totalPages > 5) && (page.number+1 > 3)}"> 
               			<li><a>...</a></li>
               		</c:if>
               		
           			<c:forEach begin="${begin}" end="${end}" varStatus="status">
           				<li <c:if test="${status.index == page.number}"> class="active"</c:if> >
           					<a href="/myspace/file-list?page=${status.index + 1}&sort=${param.sort}&status=${param.status}">${status.index + 1}</a>
           				</li>
           			</c:forEach>
           			
               		<%// 显示右边省略 %>
               		<c:if test="${(page.totalPages > 5) && (page.number+1 < page.totalPages - 2)}"> 
               			<li><a>...</a></li>
               		</c:if>
                	
                    <%// 如果当前而是末页,则disable下一页 %>
                	<c:if test="${page.number == (page.totalPages - 1)}"><li class="disabled"><a>»</a></li></c:if>
                	<%// 如果有下一页,则显示<<  %>
                	<c:if test="${page.number < (page.totalPages - 1)}"><li><a href="/myspace/file-list?page=${page.number + 2}&sort=${param.sort}&status=${param.status}">»</a></li></c:if>
                </ul>
            </c:if>
        </div>
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
<script src="/js/jquery-latest.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>