<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>

<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />

<style type="text/css">
*{
	padding:0px;
	maigin:0px;
}

#div_title{
	background:rgb(160,198,255);
	width:185px;
	height:30px;
	text-align:center;
	font:14px/30px "微软雅黑";
	font-weight:bold;
}
#div_content ul li a{
	display:block;
	height:30px;
	font:14px/30px "微软雅黑";
}
</style>
</head>
<body>
	<div>
		<div id="div_title"><span>资料导入成功！</span></div>
		<div id="div_content">
			<ul>
				<li><s:a action="area_list" >查看区域资料</s:a></li>
				<li><s:a action="user_list">查看用户资料</s:a></li>
				<li><s:a action="server_list">查看服务器端口集中器采集器资料</s:a></li>
			</ul>
		</div>
	</div>
	<div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
</body>
</html>
