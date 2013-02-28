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
    <title>Files</title>
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
        /*--page_file--*/
        /*.breadcrumb{*/
            /*margin-bottom: 20px;*/
        /*}*/
        .table{
            border-top: 1px solid #ddd;
            border-bottom: 1px solid #ddd;
        }
        .tab-logo-ico{
            float: right;
            margin-top: 2px;
            margin-right: -6px;
            opacity: .25;
        }
        /*.btn-toolbar{*/
            /*border-bottom: 1px solid #ddd;*/
            /**/
        /*}*/
        /*.btn-toolbar .left{*/
            /*float: left;*/
        /*}*/
        /*.btn-toolbar .right{*/
            /*float: right;*/
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
                <a class="brand" href="#">Terra</a>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li class="active"><a href="#"> Home</a></li>
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
                            	<sec:authorize access="!hasAuthority('index')">
                                	<li><a href="#"><i class="icon-user"></i> Sign in</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasAuthority('index')">
                                	<li><a href="#"><i class="icon-user"></i> Sign out</a></li>
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
        <li class="active"><a href="#">Files</a> </li>
    </ul>
    <div class="alert alert-block" style="margin-top: 10px">
        <strong>Tips!</strong> Love Sharing! Be a Creator of Resource.
    </div>
    <div class="row-fluid">
        <div class="span10">
            <div class="well">
                <div class="alert alert-info">
                    <a class="close" data-dismiss="alert">×</a>
                    <strong>Box Info!</strong> You have ${page.totalElements} files! ${unsharedCnt} files <span class="label label-warning">Unshared</span>.
                </div>
                <div class="btn-toolbar">
                    <div class="btn-group">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"><i class=" icon-list-alt"></i> Order <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li <c:if test="${param.sort == null || param.sort == 'uploadDate' }">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number + 1}&sort=uploadDate&type=${param.type}"><i class="icon-calendar"></i> by Date</a></li>
                            <li <c:if test="${param.sort == 'type'}">class="active"</c:if> >      <a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number + 1}&sort=type&type=${param.type}"><i class="icon-file"></i> by Type</a></li>
                            <li <c:if test="${param.sort == 'owner'}">class="active"</c:if> >     <a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number + 1}&sort=owner&type=${param.type}"><i class="icon-user"></i> by Owner</a></li>
                            <!-- 下拉菜单链接 -->
                        </ul>
                    </div>
                    <div class="btn-group">
                        <a class="btn" href="#" id="btn-check"><i class="icon-th-large"></i> Check</a>
                        <a class="btn" href="/files/upload"><i class="icon-arrow-up"></i> Upload</a>
                        <a class="btn" href="#" id="btn-remove"><i class="icon-remove"></i> Delete</a>
                        <a class="btn" href="#"><i class="icon-refresh"></i> Refresh</a>
                    </div>
                    <a class="btn" href="#"><i class="icon-cog"></i> Setting</a>
                </div>
                <table id="filesTable" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>
                            <!--<label class="checkbox">-->
                                <!--<input type="checkbox">-->
                            <!--</label>-->
                        </th>
                        <th>Type</th>
                        <th>Name</th>
                        <!-- <th>Code</th> -->
                        <th>Owner</th>
                        <th>Date</th>
                        <th>Shared</th>
                        <th>Detail</th>
                        <th>Download</th>
                    </tr>
                    </thead>
                    <tbody id="filesTableTBody">
                    
                    <c:forEach var="file" items="${files}">
          
                    <tr>
                        <td>
                            <label class="checkbox">
                                <input type="checkbox" id="${file.id}" class="_checkbox">
                            </label>
                        </td>
                        <td>
                        	<c:if test="${file.type == 'Text'}"> <i class="icon-book"></i></c:if>
                        	<c:if test="${file.type == 'Video'}"><i class="icon-film"></i></c:if>
                        	<c:if test="${file.type == 'Audio'}"><i class="icon-music"></i></c:if>
                        	<c:if test="${file.type == 'Image'}"><i class="icon-picture"></i></c:if>
                        	<c:if test="${file.type == 'Bag'}">  <i class="icon-lock"></i></c:if>
                        	<c:if test="${file.type == 'Other'}"><i class="icon-file"></i></c:if>
                        </td>
                        <td><a href="/files/${file.id}/update">${file.name}</a></td>
                        <%-- <td>${file.id}</td> --%>
                        <td>${file.owner}</td>
                        <td>${file.uploadDate}</td>
                        <td>
                        <c:if test="${file.isShared}">
                        	<span class="badge badge-success">Shared</span>
                        </c:if>
                        <c:if test="${!file.isShared}">
                        	<span class="badge badge-warning">Unshared</span>
                        </c:if>
                        </td>
                        <td><a href="/files/${file.id}/update">
                            <button class="btn btn-mini btn-info">Detail</button>
                            </a>
                        </td>
                        <td>
                            <button class="btn btn-mini btn-danger">Download</button>
                        </td>
                    </tr>
                    
                    </c:forEach>
                   
                    </tbody>
                    <tfoot>

                    </tfoot>
                </table>
                <div class="pagination pagination-small pagination-right" >
                    <ul id="fileTablePag">
                    	<%// 如果当前而是第一页,则disable上一页%>
                    	<c:if test="${page.number == 0}"><li class="disabled"><a>«</a></li></c:if>
                    	<%// 如果有下一页,则显示上一页  %> 
                    	<c:if test="${page.number != 0}"><li><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number}&sort=${param.sort}&type=${param.type}">«</a></li></c:if>

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
               					<a href="/<sec:authentication property="principal.username"/>/file-list?page=${status.index + 1}&sort=${param.sort}&type=${param.type}">${status.index + 1}</a>
               				</li>
               			</c:forEach>
               			
                   		<%// 显示右边省略 %>
                   		<c:if test="${(page.totalPages > 5) && (page.number+1 < page.totalPages - 2)}"> 
                   			<li><a>...</a></li>
                   		</c:if>
                    	
                        <%// 如果当前而是末页,则disable下一页 %>
                    	<c:if test="${page.number == (page.totalPages - 1)}"><li class="disabled"><a>»</a></li></c:if>
                    	<%// 如果有下一页,则显示<<  %>
                    	<c:if test="${page.number < (page.totalPages - 1)}"><li><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number + 2}&sort=${param.sort}&type=${param.type}">»</a></li></c:if>
                    </ul>
                </div>
            </div>
            <!--Sidebar content-->
        </div>
        <div class="span2">
            <ul class="nav nav-tabs nav-stacked">
                <li><a href="/files/upload"><i class="tab-logo-ico icon-arrow-up"></i> Upload</a></li>
                <li><a href="#"><i class="tab-logo-ico icon-search"></i> Search</a></li>
                <li><a href="#"><i class="tab-logo-ico icon-list"></i> Statistics</a></li>
            </ul>
            <div class="well sidebar-nav">
                <ul class="nav nav-list" >
                    <li class="nav-header">File Type</li>
                    <li <c:if test="${param.type == null || param.type == ''}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}"><i class="icon-file"></i> All File</a></li>
                    <li <c:if test="${param.type == 'Text'}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}&type=Text"><i class="icon-book"></i> Text</a></li>
                    <li <c:if test="${param.type == 'Video'}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}&type=Video"><i class="icon-film"></i> Video</a></li>
                    <li <c:if test="${param.type == 'Audio'}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}&type=Audio"><i class="icon-music"></i> Audio</a></li>
                    <li <c:if test="${param.type == 'Image'}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}&type=Image"><i class="icon-picture"></i> Image</a></li>
                    <li <c:if test="${param.type == 'Bag'}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}&type=Bag"><i class="icon-lock"></i> Bag</a></li>
                    <li <c:if test="${param.type == 'Other'}">class="active"</c:if> ><a href="/<sec:authentication property="principal.username"/>/file-list?page=${page.number+1}&sort=${param.sort}&type=Other"><i class="icon-file"></i> Other</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">File Owner</li>
                    <li><a href="#"><i class="icon-user"></i> Mine</a></li>
                    <li><a href="#"><i class="icon-star"></i> Fav</a></li>
                    <li class="divider"></li>
                    <li class="nav-header">Folder</li>
                    <li><a href="#"><i class="icon-folder-close"></i> Mine</a></li>
                    <li><a href="#"><span class="label"><i class="icon-plus icon-white"></i> Add</span></a></li>
                    <li class="divider"></li>
                    <li class="nav-header">trash</li>
                    <li><a href="#"><i class="icon-trash"></i> trash</a></li>
                </ul>
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
<script src="/js/jquery-latest.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/filesTbRefresh.js"></script>
<script type="text/javascript">
<%//numberElementsOnPage: 当前页的实际元素数量%>
var numberElementsOnPage = ${page.numberOfElements};

$("#btn-check").click(function(){
	var cntChecked = 0;
	
	$("._checkbox:checked").each(function(){
		cntChecked++;
	});
	
	if(cntChecked == numberElementsOnPage)
		$("._checkbox").prop("checked",false);
	else		
		$("._checkbox").prop("checked",true);
	
	return false;
});

$("#btn-remove").click(function(){
	var files = new Array();
	
	$("._checkbox:checked").each(function(){
		files.push($(this).attr("id"));
	});
	
	if(files.length == 0)
	{
		alert("请选择要删除的文件!");
		return false;
	}
	
	
	$.post(
		"/files/delete",
		{
			"files" : files.join(',')
		},

		function(result) {
			if(result.status == "SUCCESS")
				alert("删除成功!");
			window.location.reload();
		},
		"json"
	);
	
	return false;
});
</script>
</body>
</html>