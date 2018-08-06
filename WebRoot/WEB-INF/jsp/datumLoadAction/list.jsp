<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";>
<html>
  <head>
    
    
    <title>资料导入导航页面</title>
 <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

  </head>
     <script type="text/javascript">
 
 
	function a() {
var g=document.getElementsByName("utype");
var c=3;
if(g[0].checked==true){
	c=g[0].value;
}else{
	c=g[1].value;
}
		if(c==0){
			document.form.action="datumLoad_newuser.action";
			document.form.submit();
			return true;
		}else{
			document.form.action="datumLoad_last.action";
			document.form.submit();
			return true;
		}
		}
	

</script> 
  <body style="width:100%;">
   <div id="container">
		<div class="Title_bar">
			<span>资料导入>></span>
		</div>
		<div class="des1">
			<div class="operate_describe">
				<h1>说明</h1>
				<div class="des2">资料导入分为两种：<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一种是千宝通原来的老用户，即数据可以从原先千宝通数据库导出 来的,我们专门为这类
				型用户写了一个小程序， 名字叫做excelExport，老用户直接用这个程序导出excel即可，然后点击下一步；<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;另一种是新用户即数据不存在于原先千宝通数据库中，这类用户需要先下载我们的excel模板，然后按格式填入，具体说明后面有。
		
				</div>
			</div>
			<s:form  action="" target="right" name="form">
			<table class="center table-roleList">
				<tbody  class="des_tbody">
					<tr>
						<td style="font-weight: bold;">下面，请选择您的用户类型</td>
					</tr>
					<tr>
						<td>
							<s:radio name="utype" list="#{'0':'新用户','1':'老用户'}"  listKey="key"
									listValue="value" cssStyle="width:10%"></s:radio>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><img src="${pageContext.request.contextPath}/images/nexts.gif" style="cursor:hand" onclick="a();"/> </td>
					</tr>
				</tfoot>
			</table>
			</s:form>
			
		</div>
	</div>
  </body>
</html>
