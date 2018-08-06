<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-ui.min.js"></script>
		<script>
		function a() {
			var diag = new Dialog();
			diag.Title="新建设备类型信息";
			diag.autoOpen=false;
			diag.dialogClass="alert";
			diag.Width = 800;
			diag.Height = 500;
			diag.modal=true;
			diag.CancelEvent = function() {
				if (confirm("还未保存，确定要退出?")) {
					diag.close();
				}
			};
			diag.URL = "deviceType_addUI.action";
			diag.show();
		}  
		
		function edit(menu) {
			 var diag = new Dialog();
			diag.autoOpen=false;
			diag.dialogClass="alert";
			diag.Width = 800;
			diag.Height = 500;
			diag.modal=true;
			diag.CancelEvent = function() {
				if (confirm("确定要退出?")) {
					diag.close();
				}
			};
			diag.URL = "deviceType_editUI.action?id="+menu.id;
			diag.show();  
		} 
	</script>
</head>
<body>
	<div id="container">
		<div class="monitor">
			<div class="Title_bar">
				<span>设备类型管理>></span>
			</div>
		</div>
		<table class="center table-roleList">
			<thead>
				<tr>
					<th>相关操作</th>
					<th>编号</th>
					<th>设备名称</th>
					<th>最大数</th>
					<th>序列号</th>
					<th>生产厂家</th>
					<th>规格</th>
					<th>厂家地址</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
			  <s:iterator value="#deviceTypeList">
				<tr>
					<td>
						<s:a action="deviceType_delete?id=%{id}" onClick="return window.confirm('您确定要删除吗?')">删除</s:a>
						<!-- <a href="" onClick="return window.confirm('您确定要删除吗?')">删除</a> -->
						<span   onclick="edit(this);" class="sp" id="${id}">修改</span>
					</td>
					  <td><s:property value="id"/>&nbsp;</td>
		                <td><s:property value="name"/>&nbsp;</td>
		                <td><s:property value="showNum"/>&nbsp;</td>                
		                <td><s:property value="image"/>&nbsp;</td>
		                <td><s:property value="manufacture"/>&nbsp;&nbsp;</td>                               
		                <td><s:property value="specification"/>&nbsp;</td>
		                <td><s:property value="address"/>&nbsp;</td>
		                <td><s:property value="remark"/>&nbsp;</td>
				</tr>
				 </s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
					<!-- 	<input type="submit" value="新建" class="dialog1" /> -->
						<img  src="${pageContext.request.contextPath}/images/createNew.png" style="cursor:hand" onclick="a();"/> 
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
<%-- 	<div class="show11" title="新增设备类型信息">
		<iframe id="Detail" width="100%" height="1000" frameborder="no" scrolling="yes"></iframe>
		<iframe src="${pageContext.request.contextPath}/deviceType_addUI.action" width="100%" height="380"  frameborder="no" scrolling="yes"></iframe>
	</div> --%>

</body>
</html>
    