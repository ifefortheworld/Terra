var xMLHttpRequest;
var pageRows;
var curPageNum;
var maxPageNum;
var maxRowCount;
var docFilesTable;
var docFilesTableTBody;
var docFilesTablePag;
var docFilesTableCurPageNum;
var docFilesTableMaxPageNum;
var docFilesTablePageRows;

function createXMLHttpRequest(){
	if(window.XMLRequest){
        xMLHttpRequest = new XMLHttpRequest();
	}
    else if(window.ActiveObject){
        try{
            xMLHttpRequest = new ActiveXObject("Msxm12.XMLHTTP");
        }catch(e){
            try{
                xMLHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e){}
        }
    }
}

//function tbRefreshReqURL(tbName,userid,pageNum){
//    var url = "tbRefreshServlet?user="+userid+"&pageNum="+pageNum+"&tbName="+tbName;
//
//}

function init(){
    docFilesTable = document.getElementById("filesTable");
    docFilesTableTBody = document.getElementById("filesTableTBody");
    docFilesTablePag = document.getElementById("fileTablePag");
}

function filesTbRefreshReq(cur){
    //create http request
    createXMLHttpRequest();
    //init
    init();

    userid = "terra"
    var url = "tbRefreshServlet?userID="+userid+"&curPageNum="+cur+"&tbName="+tbName;
    xMLHttpRequest.open("POST",url,true);
    xMLHttpRequest.setRequestHeader("Context-type","application/3-www-form-urlencoded");

    xMLHttpRequest.onreadystatechange=fileTbRefreshRes;
    xMLHttpRequest.send(null);
}

function filesTbRefreshRes(){
    if(xmlHttp.readyState==4)
    {
        if(xmlHttp.status==200)
        {
            fileTbRefresh(xMLHttpRequest.responseXML);
        }
        else
        {
            alert(xMLHttpRequest.status);
        }
    }
}

function fileTbRefresh(xml){
    var results = xml.getElementsByTagName("result");
    pageRows =xml.getElementsByTagName("pageRows")[0].firstChild.nodeValue;
    curPageNum = xml.getElementsByTagName("curPageNum")[0].firstChild.nodeValue;
    maxPageNum = xml.getElementsByTagName("maxPageNum")[0].firstChild.nodeValue;
    maxRowCount = xml.getElementsByTagName("maxRowCount")[0].firstChild.nodeValue;

    if(results.length>0){
        clearFilesTableTBody();
        var resultHtml;
        for(var i=0;i<results.length;i++){
            resultHtml += "<tr>";
            resultHtml += "<td><label class=&quot;checkbox&quot;><input type=&quot;checkbox&quot;></label></td>";
            resultHtml += getHtmlTDFileType(results[i]);
            resultHtml += getHtmlTDFileName(results[i]);
            resultHtml += getHtmlTDFileID(results[i]);
            resultHtml += getHtmlTDFileOwner(results[i]);
            resultHtml += getHtmlTDFileSharedFlag(results[i]);
            resultHtml += "<td><button class=&quot;btn btn-mini btn-info&quot;>Detail</button>/td>";
            resultHtml += "<td><button class=&quot;btn btn-mini btn-danger&quot;> Download</button></td>";
            resultHtml += "</tr>";

//            rows.appendChild();
        }
        alert(resultHtml);
        docFilesTableTBody.innerHTML(resultHtml);
        var pagHtml = getInnerHtmlFileTablePag();
        var l = docFilesTablePag.childNodes.length;
        for(var i =0;i<l;i++){
            s.removeChild(docFilesTablePag.childNodes[i]);
        }
        docFilesTablePag.innerHTML = getInnerHtmlFileTablePag();
//        pagHtml+= "<li><a href=&quot;#&quot;><<</a></li>";
//        pagHtml+= "<li><a href=&quot;#&quot;>"+resultHtml.h+"</a></li>";
//        pagHtml+= "<li><a href=&quot;#&quot;>2</a></li>";
//        pagHtml+= "<li><a href=&quot;#&quot;>3</a></li>";
//        pagHtml+= "<li><a href=&quot;#&quot;>4</a></li>";
//        pagHtml+= "<li><a href=&quot;#&quot;>5</a></li>";
//        pagHtml+= "<li><a href=&quot;#&quot;>>></a></li>";
//        docFileTablePag.
//        docFileTableCurPageNum
    }else{
        alert('none');
    }

}

function getInnerHtmlFileTablePag(){

    var pagHtml;
    pagHtml+= "<li><a href=&quot;#&quot;><<</a></li>";

    if((maxPageNum-1)<=4){
        for(var i=1;i<=maxPageNum;i++){
            if(i==curPageNum)
                pagHtml+= "<li class=&quot;active&quot;><a href=&quot;#&quot;>"+i+"</a></li>";
            pagHtml+= "<li><a href=&quot;#&quot;>"+i+"</a></li>";
        }
    }else if(curPageNum-1<=2){
        for(var i=1;i<=5;i++){
            if(i==curPageNum)
                pagHtml+= "<li class=&quot;active&quot;><a href=&quot;#&quot;>"+i+"</a></li>";
            pagHtml+= "<li><a href=&quot;#&quot;>"+i+"</a></li>";
        }
    }else if(maxPageNum-curPageNum<=2){
        for(var i=maxPageNum-4;i<=maxPageNum;i++){
            if(i==curPageNum)
                pagHtml+= "<li class=&quot;active&quot;><a href=&quot;#&quot;>"+i+"</a></li>";
            pagHtml+= "<li><a href=&quot;#&quot;>"+i+"</a></li>";
        }
    }else{
        for(var i=curPageNum-2;i<=curPageNum+2;i++){
            if(i==curPageNum)
                pagHtml+= "<li class=&quot;active&quot;><a href=&quot;#&quot;>"+i+"</a></li>";
            pagHtml+= "<li><a href=&quot;#&quot;>"+i+"</a></li>";
        }
    }
    pagHtml+= "<li><a href=&quot;#&quot;>>></a></li>";
    alert(pagHtml);
    return pagHtml;
}

function getHtmlTDFileSharedFlag(result){
//    var flag;
    var htmlCode;
    switch (result.getElementsByTagName("fileSharedFlag")){
        case 0:
            htmlCode  ="<td><span class=&quot;badge badge-warning&quot;>Shared</span></td>";
            break;
        case 1:
            htmlCode  ="<td><span class=&quot;badge badge-success&quot;>UnShared</span></td>";
            break;
        default:
            htmlCode  ="<td><span class=&quot;badge badge-success&quot;>UnShared</span></td>";
    }
    return htmlCode;
}

function getHtmlTDFileUploadDate(result){
    var htmlCode="<td>"+result.getElementsByTagName("fileUploadDate")+"</td>";
    return htmlCode;
}

function getHtmlTDFileOwner(result){
//    if(+result.getElementsByTagName("fileOwner") == "")
    //Mine||Other
    var htmlCode="<td>"+result.getElementsByTagName("fileOwner")+"</td>";
    return htmlCode;
}

function getHtmlTDFileID(result){
    var htmlCode="<td>"+result.getElementsByTagName("fileId")+"</td>";
    return htmlCode;
}

function getHtmlTDFileName(result){
    var htmlCode="<td>"+result.getElementsByTagName("fileName")+"</td>";
    return htmlCode;
}

function getHtmlTDFileType(result){
    var type = result.getElementsByTagName("fileType");
    var htmlCode="";
    var type;
    switch (type){
        case 0:
            /*Text*/
            type = "book";
            break;
        case 1:
            /*video*/
            type = "film";
            break;
        case 2:
            /*Audio*/
            type = "music";
            break;
        case 3:
            /*Image*/
            type = "picture";
            break;
        case 4:
            /*bag*/
            type = "lock"
            break;
        case 5:
            /*other*/
            type = "file";
            break;
        default :
            type = "file";
            break;
            //return htmlCode;

    }
    htmlCode = "<td><i class=&quot;icon-"+type+"&quot;></i></td>";
    return htmlCode;
}

function clearFilesTableTBody(){
    if(docFilesTable.hasChildNodes()){
        for(var i=docFilesTableTBody.childNodes.length-1;i>=0;i--){
            docFilesTableTBody.remove(docFilesTableTBody.childNodes[i]);
        }
    }

}



//function sendRequest(url){
//    createXMLHttpRequest();
//    xMLHttpRequest.open("GET",url,true);
//
//}