<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/init.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	
</head>
<body>
	<div id="container">
		<table class="table-edit">
			<tbody>
				<tr>
					<td>采集器名称</td>
					<td>
						<s:property value="name"/>&nbsp; 
					</td>
				</tr>
				<tr>
					<td>采集器地址</td>
					<td>
						<s:property value="address"/>&nbsp; 
					</td>
				</tr>
				<tr>
					<td>采集器状态</td>
					<td>
						<s:property value="runStatue"/>&nbsp; 
					</td>
				</tr>
				<tr>
					<td>备注信息</td>
					<td>
						<s:property value="remark"/>&nbsp; 
					</td>
				</tr>
			</tbody>
		</table>

	</div>
</body>
</html>