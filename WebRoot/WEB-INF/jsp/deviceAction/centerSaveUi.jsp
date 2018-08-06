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
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/init.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
	
	<script type="text/javascript">
	function a()
	{
		var cename = document.getElementsByName("name")[0].value;
		var readPeriod = document.getElementsByName("readPeriod")[0].value;
		var gprsNum = document.getElementsByName("gprsNum")[0].value;
		
		if (cename.length < 1) {
			alert("集中器名称不能为空！");
			return false;
		}

		if (gprsNum.length < 1) {
			alert("集中器编号不能为空！");
			return false;
		}
		if (isNaN(gprsNum) == true) {
			alert("请输入只包含数字的集中器编号！");
			return false;
		}
		if (readPeriod.length < 1) {
			alert("读取周期不能为空！");
			return false;
		}
		if (isNaN(readPeriod) == true) {
			alert("请输入只包含数字的读取周期！");
			return false;
		}
		
		/* 提交表单前校验集中器编号 */
		var gprs = document.getElementsByName("gprs");
		var text = document.getElementById("gp");
		for (var i = 0; i < gprs.length; i++) {
			if (gprs[i].value == gprsNum) {
				alert("该集中器编号已存在！");
				document.getElementsByName("gprsNum")[0].style.color = "red";
				text.style.color = "red";
				return false;
			}
		}

		//验证集中器的名称重复
		var cname = document.getElementsByName("cn");
		var ctext = document.getElementById("na");
		for (var i = 0; i < cname.length; i++) {
			if (cname[i].value == cename) {
				alert("该集中器名称已存在！");
				document.getElementsByName("name")[0].style.color = "red";
				ctext.style.color = "red";
				return false;
			}

		}
		
		
		
		
		
		
		var id=document.getElementsByName("id")[0].value;
		if(id.length<1){
		   document.form.action="center_add.action?areaId="+${areaId};
			document.form.target="right";
			document.form.submit();
			/*  document.form1.action = "device_area.action?areaId="+${areaId};
			document.form1.target = "right";
			document.form1.submit(); */   
			return true; 
		}
		else{
		
			 document.form.action="center_edit.action?centerId="+id+"&areaId="+${areaId};
			document.form.target="right";
			document.form.submit(); 
			/*   document.form1.action = "device_area.action?areaId="+${areaId};
			document.form1.target = "right";
			document.form1.submit();   */
			return true; 
		}

	}
	
/* 	 $(document).ready(function() {
	$("#isAutoCollection").change(function() {
		var text1 = $("#isAutoCollection").val();
		alert("good");
		
		if (text1 == 0) {
			
			document.getElementById('readSchemeId').disabled = true;
		}
	});

}); */ 

function check() {
	var text1 = "${isAutoCollection}";
	
	if (text1 == 0) {
		/* document.getElementById('isAutoCollection').disabled = true; */
		document.getElementById('readSchemeId').disabled = true;
	}
	return true;

}

	
	
	</script>

</head>
<body onload="check();">
	<div id="container">
	 <s:hidden name="id"/>
		<table class="table-edit">
			<s:form action="" method="post" name="form"  >
				<tbody>
					<tr>
						<td>端口列表</td>
						<td><s:select list="#portList" listKey="id" listValue="remark"
								name="portId">
							</s:select></td>
					</tr>
					<tr>
						<td><div id="na">集中器名称</div></td>
						<td ><s:textfield name="name" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td>安装地址</td>
						<td ><s:textfield name="installAddr" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td><div id="gp">集中器编号</div></td>
						<td><s:textfield name="gprsNum" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td>读取周期</td>
						<td><s:textfield name="readPeriod" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td>使用状态</td>
						<td><s:select name="isUse" size="1" cssClass="SelectStyle"
								list="#{1:'使用',0:'未用'}" listKey="key" listValue="value" />*</td>
					</tr>
					<tr>
						<td>是否自动采集</td>
						<td><s:select name="isAutoCollection" size="1" 
								cssClass="SelectStyle" id="isAutoCollection"
								list="#{1:'是',0:'否'}" listKey="key" listValue="value"
								headerValue="" /><br>*若使用自动采集，则默认每天采集一次</td>
					</tr>
					<tr>
						<td>抄表方案配置</td>
					 <td><s:select name="readSchemeId" size="1"
									cssClass="SelectStyle" list="#readSchemeList" listKey="id"
									listValue="schemeName" headerValue="" />*</td> 
					</tr>
					<tr>
						<td>设备配置</td>
							<td><s:textarea name="modem" class="InputStyle"></s:textarea></td>
						</tr>
				</tbody>
				
				
				<tfoot>
					<tr>
						<td colspan="2">
							<%-- <img src="${pageContext.request.contextPath}/images/save.png" style="cursor:hand" onclick="a();" />  --%>
							<input type="button" value="保存" style="cursor:hand" onclick="a();">
						</td>
					</tr>
				</tfoot>
			</s:form>
			
		</table>
		
		<s:iterator value="#gprsNumList" status="st">
			<s:hidden name="gprs" value="%{#gprsNumList.get(#st.index)}"
				cssClass="InputStyle" />
		</s:iterator>

		<s:iterator value="#centerNameList" status="std">
			<s:hidden name="cn" value="%{#centerNameList.get(#std.index)}"
				cssClass="InputStyle" />
		</s:iterator>
		
	</div>
	<s:form name="form1" class="coll" action=""></s:form>
</body>
</html>
