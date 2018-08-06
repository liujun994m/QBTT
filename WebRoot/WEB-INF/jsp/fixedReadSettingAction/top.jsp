<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<title>定时抄表方案管理</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/init.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

	<script type="text/javascript"  src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">

		$(document).ready(function() {
			$("li a").click(function() {
				$(this).addClass('active1').parent().siblings().children('a').removeClass('active1');
			});
		});

	</script>
</head>
<body>
	<div id="container3">
<!-- 		<div class="Title_bar">
			<span>定时抄表方案管理</span>
		</div> -->
		<div class="monitor">
			<ul class="state">
				<li><a href="${pageContext.request.contextPath}/fixedReadSetting_readScheme.action" target="bottom">定时抄表方案管理</a></li>
				<li><a href="${pageContext.request.contextPath}/fixedReadSetting_center.action" target="bottom">集中器定时抄表设置</a></li>
			</ul>
			
		</div>
	</div>

</body>
</html>
