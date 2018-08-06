<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<head>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>增加水表</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/init.css" />
<!-- 	<link rel="stylesheet" type="text/css" href="../css/bootstrap.css"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />

<script type="text/javascript">
	function a() {
		/*  alert(${centerId}); */
		var deviceNo = document.getElementsByName("deviceNo")[0].value;
		var deviceInfor = document.getElementsByName("deviceInfor")[0].value; 
		var deviceTypeID = document.getElementsByName("deviceTypeID")[0].value;
		var MaxNum = Number(document.getElementsByName("MaxNum")[0].value);
		var iMultiple = Number(document.getElementsByName("imultiple")[0].value);
		var alarmMax = Number(document.getElementsByName("alarmMax")[0].value);
		var alarmMin = Number(document.getElementsByName("alarmMin")[0].value);
		

			function a() {
				/* var deviceNo = document.getElementsByName("deviceNo")[0].value;
		 		var deviceInfor = document.getElementsByName("deviceInfor")[0].value; 
				/* var deviceTypeID = document.getElementsByName("deviceTypeID")[0].value;
				var MaxNum = Number(document.getElementsByName("MaxNum")[0].value);
				var iMultiple = Number(document.getElementsByName("imultiple")[0].value);
				var alarmMax = Number(document.getElementsByName("alarmMax")[0].value);
				var alarmMin = Number(document.getElementsByName("alarmMin")[0].value);
				
				if (deviceNo.length < 1) {
					alert("请输入表编号！");
					return false;
				}
		 		if (deviceInfor.length < 1) {
					alert("请选择上级设备！");
					return false;
				} 
				if (deviceTypeID < 1) {
					alert("请选择水表型号！");
					return false;
				} 
				if (isNaN(MaxNum)) {
					alert("最大值栏请输入数字！");
					return false;
				}
				if (isNaN(iMultiple)) {
					alert("倍率栏请输入数字！");
					return false;
				}
				if (isNaN(alarmMax)) {
					alert("告警上限栏请输入数字！");
					return false;
				}
				if (isNaN(alarmMin)) {
					alert("告警下限栏请输入数字！");
					return false;} */ 
				var id=document.getElementsByName("id")[0].value;
				 if(id.length>1){
					document.form.action = "device_edit.action";
					document.form.target = "right";
					document.form.submit();
					return true;
				}else{
					var temp =  ${collectionId};
					if(temp != null){
						document.form.action = "device_add.action?collectionId="+${collectionId};
						document.form.target = "right";
						document.form.submit();
						return true;
					}
					var temp2 = ${centerId};
					if(temp2 != null){
						document.form.action = "device_add.action?centerId="+${centerId};
						document.form.target = "right";
						document.form.submit();
						return true;
					}
				}
			}
			
		
		/* 
		用途：检查输入字符串是否符合正整数格式 
		输入： s：字符串 
		返回： 如果通过验证返回true,否则返回false 
		 */
		function isNumber(s) {
			var regu = "^[0-9]+$";
			var re = new RegExp(regu);
			if (s.search(re) != -1) {
				return true;
			} else {
				return false;
			}

		}
		if (deviceInfor.length < 1) {
		alert("请选择上级设备！");
		return false;
		} 
		if (deviceTypeID < 1) {
		alert("请选择水表型号！");
		return false;
		} 
		if (isNaN(MaxNum)) {
		alert("最大值栏请输入数字！");
		return false;
		}
		if (isNaN(iMultiple)) {
		alert("倍率栏请输入数字！");
		return false;
		}
		if (isNaN(alarmMax)) {
		alert("告警上限栏请输入数字！");
		return false;
		}
		if (isNaN(alarmMin)) {
		alert("告警下限栏请输入数字！");
		return false;
		}  
		/* var id=document.getElementsByName("id")[0].value;
		 if(id.length>1){
			document.form.action = "device_edit.action";
			document.form.target = "right";
			document.form.submit();
			return true;
		}else{  */
			
		
	   var collectionId=document.getElementsByName("collectionId")[0].value; 
		var ff=collectionId.length;
		    if (ff==0) {
			
		    document.form.action = "device_edit.action";
			document.form.target = "right";
			document.form.submit();
			return true; 
		
		} 
		    else{
		    	
			 document.form.action = "device_add.action?centerId=%{centerId}";
			document.form.target = "right";
			document.form.submit();

			return true;
		    }
		}      

		/* }  */

	

	/* 
	用途：检查输入字符串是否符合正整数格式 
	输入： s：字符串 
	返回： 如果通过验证返回true,否则返回false 
	 */
	/* function isNumber(s) {
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1) {
			return true;
		} else {
			return false;
		}
	} */
</script>
</head>
<body>

	<div id="container">
		<table class="table-edit">
			<s:hidden name="id" />
			<s:hidden name="collectionId"></s:hidden>
			<tbody>
				<%-- <s:if test="%{id == null}"> --%>
				<%-- <s:form  action="device_add?collectionId=%{collectionId}"> --%>
				<s:form action="" name="form" method="post">
					<tr>
						<td>表号&nbsp;</td>
						<td><s:textfield name="deviceNo" class="InputStyle" /> *</td>
						<td><s:fielderror>
								<s:param>deviceNo</s:param>
							</s:fielderror></td>
					</tr>
					<tr>
						<td>表名称&nbsp;</td>
						<td><s:textfield name="name" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td>设备地址&nbsp;</td>
						<td><s:textfield name="addr" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td>型号&nbsp;</td>
						<td><s:select name="deviceTypeID" cssClass="SelectStyle"
								list="#deviceTypes" listKey="id"
								listValue="name+'('+manufacture+')'" headerKey=""
								headerValue="===请选择水表型号===" /> *</td>
						<%-- <s:textfield name="deviceTypeID" class="InputStyle"/> * --%>
					</tr>
					<tr>
						<td>最大值&nbsp;</td>
						<td><s:textfield name="MaxNum" class="InputStyle" /> *请输入数字</td>
					</tr>
					<tr>
						<td>倍率&nbsp;</td>
						<td><s:textfield name="imultiple" class="InputStyle" />
							*请输入数字</td>
					</tr>
					<tr>
						<td>告警&nbsp;</td>
						<td><s:select name="needAlarm" cssClass="SelectStyle"
								list="#{0:'不需要',1:'需要'}" listKey="key" listValue="value"
								headerKey="" headerValue="===请选择是否需要告警===" /> *</td>
					</tr>
					<tr>
						<td>告警类型&nbsp;</td>
						<td><s:select name="alarmGetType" cssClass="SelectStyle"
								list="#{0:'取当前值',1:'取用量'}" listKey="key" listValue="value"
								headerKey="" headerValue="===请选择告警类型===" /> *</td>
					</tr>
					<tr>
						<td>告警上限&nbsp;</td>
						<td><s:textfield name="alarmMax" class="InputStyle" /> *请输入数字</td>
					</tr>
					<tr>
						<td>告警下限&nbsp;</td>
						<td><s:textfield name="alarmMin" class="InputStyle" /> *请输入数字</td>
					</tr>
					<tr>
						<td>是否使用&nbsp;</td>
						<td><s:select name="StopUse" cssClass="SelectStyle"
								list="#isUse" listKey="key" listValue="value" />*</td>
					</tr>
					<tr>
						<td>计费类型&nbsp;</td>
						<td><s:select name="GetMoneyType" cssClass="SelectStyle"
								list="#{0:'不计费',1:'实时收费',2:'按月收费'}" listKey="key"
								listValue="value" headerKey="" headerValue="===请选择计费方式===" /> *
						</td>
					</tr>
					<tr>
						<td>用量计算方法&nbsp;</td>
						<td><s:select name="UserCountType" cssClass="SelectStyle"
								list="#{0:'不计用量',1:'上次抄读',2:'每天',3:'每月'}" listKey="key"
								listValue="value" headerKey="" headerValue="===请选择用量计算方式===" />
							*</td>
					</tr>
					<tr>
						<td>条码&nbsp;</td>
						<td><s:textfield name="CardID" class="InputStyle" /> *</td>
					</tr>
		<%-- 			<tr>
						<td>生产日期&nbsp;</td>
						<td><s:textfield name="createDate" class="InputStyle" /> *</td>
					</tr>
					<tr>
						<td>安装日期&nbsp;</td>
						<td><s:textfield name="setupDate" class="InputStyle" /> *</td>
					</tr> --%>
				</s:form>
			</tbody>

			<tfoot>
				<tr>
					<!-- 	<td colspan="2">
							<input type="submit" value="保存">
						</td> -->
					<td colspan="5"><img
						src="${pageContext.request.contextPath}/images/save.png"
						style="cursor:hand" onclick="a();" /></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>

