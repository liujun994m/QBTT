<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/init.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
<script type="text/javascript">
	function col() {
		var collectionname = document.getElementsByName("name")[0].value;
		var address = document.getElementsByName("address")[0].value;

		if (collectionname.length < 1) {
			alert("采集器名称不能为空！");
			return false;
		}

		if (address.length < 1) {
			alert("采集器地址不能为空！");
			return false;
		}

		/* 提交表单前校验采集器名称 */
		var cnames = document.getElementsByName("coname");
		var text = document.getElementById("cn");
		for (var i = 0; i < cnames.length; i++) {
			if (cnames[i].value == collectionname) {
				alert("该采集器名称已存在！");
				document.getElementsByName("name")[0].style.color = "red";
				text.style.color = "red";
				return false;
			}
		}
		document.form.action = "collection_add.action?centerId=" + ${centerId};
		document.form.target = "right";
		document.form.submit();
		return true;
		/* //采集器的地址编号可以重复哦！

		var id = document.getElementsByName("id")[0].value;
		alert(id);
		
		
		if (id.length < 1) {
			
		

		} else {
			
			
			 document.form.action = "collection_edit.action?centerId="+${centerId}+"&collectionId="+${collectionId};
			document.form.target = "right";
			document.form.submit(); 
			return true;
		} */
	}
</script>


</head>
<body>
	<s:debug></s:debug>
	<div id="container">
		<s:hidden name="id" />
		<table class="table-edit">
			<s:form action="" name="form" method="post">
				<tbody>

					<tr>
					<tr>
						<td>采集器名称</td>
						<td><s:textfield name="name" class="InputStyle" /> *</td>
					</tr>

					<tr>
						<td>采集器地址</td>
						<td><s:textfield name="address" class="InputStyle" /> *</td>
					</tr>

					<tr>
						<td>使用状态</td>
						<td><s:select name="isUse" size="1" cssClass="SelectStyle"
								list="#{1:'使用',0:'未用'}" listKey="key" listValue="value" />*</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<%-- <img src="${pageContext.request.contextPath}/images/save.png" style="cursor:hand" onclick="col();" />  --%>
							<input type="button" value="保存" style="cursor:hand"
							onclick="col();">
						</td>
					</tr>
				</tfoot>
			</s:form>

			<s:iterator value="#collectionNameList" status="st">
				<s:hidden name="coname"
					value="%{#collectionNameList.get(#st.index)}" cssClass="InputStyle" />
			</s:iterator>

			<s:iterator value="#collectAddrList" status="std">
				<s:hidden name="coAddr" value="%{#collectAddrList.get(#std.index)}"
					cssClass="InputStyle" />
			</s:iterator>
		</table>

	</div>
</body>
</html>
