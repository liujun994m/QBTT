<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bottom.jsp' starting page</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/top.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
	<style type="text/css">
    #bottom-info{
       float:right;
       height:40px;
       line-height:40px;
          }
</style>
  </head>
  
 <body>
	<div>
		<div id="top">	
			<div id="bottom-info">	
		<span>2015版权所有 千宝通通&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>			
			</div>
		</div>
	</div>
</body>
</html>
