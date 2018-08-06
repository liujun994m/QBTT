<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<html>
	<head>
		<title>QBT</title>
	</head>
	<frameset rows="60,*,40" framespacing="0" border="0" frameborder="0" name="index">
		<frame src='${pageContext.request.contextPath}/home_top.action' name="top" scrolling="no" noresize="noresize" />
		<frameset cols="230,*" name="bot" >
			<frame src="${pageContext.request.contextPath}/home_left.action" name="left" scrolling="auto"/>
			<frame src="${pageContext.request.contextPath}/home_right.action" name="right" scrolling="auto"/>
		</frameset>			
		<frame  name="bottom"  src="${pageContext.request.contextPath}/home_bottom.action" scrolling="no" noresize="noresize" />
	</frameset>

</html>
