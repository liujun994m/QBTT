<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<!DOCTYPE HTML>

	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>
	<!-- 	<link rel="stylesheet" type="text/css" href="../css/init.css" /> -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/init.css" />
	<!-- 	<link rel="stylesheet" type="text/css" href="../css/bootstrap.css"> -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
	<script type="text/javascript">
	function a(){
		 var cardID = document.getElementsByName("cardID")[0].value;
		 if (cardID.length < 1) {
				alert("请输入表编号！");
				return false;
				}
		 
		document.form.action = "deviceChange_change.action?ID="+${ID};
		document.form.target = "right";
		document.form.submit();
		return true;
	}
</script>
</head>
<body>
	<div id="container">
		<table class="table-edit">
	<%-- 	<s:hidden name="ID"></s:hidden> --%>
			 <%--  <s:form action="deviceChange_change?ID=%{#ID}" > --%>
			 <s:form action="" name="form" method="post">
				<tbody>
					 <tr>
                    	<td>设备条码：&nbsp;</td>
                         <td><s:textfield name="cardID" class="InputStyle"/> *</td>
                         <td><s:fielderror><s:param>cardID</s:param></s:fielderror></td>
                    </tr>
                    <tr>
                    	<td>新表地址：&nbsp;</td>
                         <td><s:textfield name="iaddr" class="InputStyle"/> *</td>
                    </tr>
                    <tr>
                    	<td>新表读数：&nbsp;</td>
                         <td><s:textfield name="newReadData" class="InputStyle"/> *</td>
                    </tr>
                    <tr>
                    	<td>计费用量：&nbsp;</td>
                         <td><s:textfield name="userCount" class="InputStyle"/> *</td>
                    </tr>
                    <tr>
                    	<td>备      注：&nbsp;</td>
                         <td><s:textfield name="ramark" class="InputStyle"/> *</td>
                    </tr>
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
							<%-- <img src="${pageContext.request.contextPath}/images/save.png"  style="cursor:hand" onclick="a();" />  --%>
							<input type="button" value="保存"   style="cursor:hand" onclick="a();">
						</td>
					</tr>
				<!-- 	<tr>
						<td colspan="2">
							<input type="submit" value="保存">
						</td>
					</tr> -->
				</tfoot>
			</s:form>
		</table>
	</div>
</body>
</html>