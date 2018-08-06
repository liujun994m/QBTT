<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新用户导入资料</title>
     <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
  </head>
  
  <body style="width:100%;">
   <div id="container">
		<div class="Title_bar">
			<span>资料导入--><span style="font-size: 20px">新用户</span></span>
		</div>
		<div class="des1">
			<div class="operate_describe">
				<h1>说明</h1>
				<div class="des2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新用户导入资料时必须先下载我们准备好的模板excel，模板中包含area（区域表），
				center（集中设备表），collection（采集器表），deviceType（设备类型表），device（终端设备表），baseNumber（低数登记表），optDeviceRecord（操作记录表）共7个表。<br>
				                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;导入时必须严格按照每个表的sheet在excel中的顺序，即按照area-->center--> collection-->deviceType-->device-->baseNumber-->optDeviceRecordsheet在excel中sheet顺序为1到
				                  7。同理，每个表格中字段的顺序也必须和模板一样。<br>
		  <span style="color:red;font-weight: bold">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最重要的是，导入时每个表格的数据必须是文本类型，如下图所示：</span></div>
		  
			</div>
			<div class="dyg">
                <img border="0"  src="${pageContext.request.contextPath}/images/dy.png" />
			</div>
			<table class="center table-roleList">
				<tbody class="des_tbody">
					<tr>
						<td><a href="excel/data.xls">请点击此处下载excel</a>
						<s:fielderror ><s:param>do</s:param></s:fielderror> </td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/lasts.gif" style="cursor:hand" /></a>
						<a href="${pageContext.request.contextPath}/datumLoad_last.action" target="right"><img src="${pageContext.request.contextPath}/images/nexts.gif" style="cursor:hand" /></a></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
  </body>
</html>
