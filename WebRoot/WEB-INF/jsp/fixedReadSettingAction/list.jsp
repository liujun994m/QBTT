<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <frameset rows="60,*" frameborder="0">
		<frame src="${pageContext.request.contextPath}/fixedReadSetting_top.action" name="top" scrolling="no" noresize="noresize" />
		<frame src="${pageContext.request.contextPath}/fixedReadSetting_buttom.action" name="bottom" scrolling="auto"/>
	</frameset>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
