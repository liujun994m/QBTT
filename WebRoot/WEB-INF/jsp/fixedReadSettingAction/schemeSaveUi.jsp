<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>抄表方案</title>
<!-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/init.css" /> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/init.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />

<script type="text/javascript">

	
	function a()
	{
		
		
		var schemeID = document.getElementsByName("schemeID")[0].value;
		 var schemeName = document.getElementsByName("schemeName")[0].value;

		 var timerType = document.getElementsByName("timerType")[0].value;
		var beginDay = document.getElementsByName("beginDay")[0].value;
		var dayInterval = document.getElementsByName("dayInterval")[0].value;
		var readDayNum = document.getElementsByName("readDayNum")[0].value;
	/* 	var readInMonthEnd = document.getElementsByName("readInMonthEnd")[0].value;  */
		var beginTime = document.getElementsByName("beginTime")[0].value;
		var dayReadNum = document.getElementsByName("dayReadNum")[0].value;
		var hourInterval = document.getElementsByName("hourInterval")[0].value;

		/*  var isReadOnScheme = document.getElementsByName("isReadOnScheme")[0].value; */  
		var retryNum = document.getElementsByName("retryNum")[0].value;
		var reTryInterval = document.getElementsByName("reTryInterval")[0].value;
		
		//抄表方案名称不能重复
		var schna = document.getElementsByName("schNa");
		var textna = document.getElementById("na");
		 for (var i = 0; i < schna.length; i++) {
			if (schna[i].value == schemeName) {
				alert("该方案名称已经存在！");
				document.getElementById("schemeName").style.color = "red";
				textna.style.color = "red";
				 return false; 
			}
			
		}
			//抄表方案编号不能重复
	 		var sch = document.getElementsByName("schId");
			var text = document.getElementById("bh");
			 for (var i = 0; i < sch.length; i++) {
				 if (sch[i].value == schemeID) {
						alert("该方案编号已经存在！");
						document.getElementById("schemeID").style.color = "red";
						text.style.color = "red";
						return false;
				 }

			} 
		

		if (schemeID.length < 1) {
			
			alert("抄表方案的编号不能为空");
			return false;
		}
		if (isNaN(schemeID) == true) {
			alert("抄表方案编号只能为数字");
			return false;
		}
		
		if (schemeName.length < 1) {
			alert("抄表方案的名称不能为空！");
			return false;
		}

		if (isNaN(retryNum)) {

			alert("重复执行次数为整数");
			return false;
		} else if (retryNum < 1 || retryNum > 7) {
			alert("重复执行次数为大于1小于7的整数！");
			return false;
		}
		
		
		
		
		if (isNaN(reTryInterval)) {

			alert("重复执行时间间隔为整数");
			return false;
		} else if (reTryInterval < 1 || reTryInterval > 7) {
			alert("重复执行时间间隔为大于1小于7的整数！");
			return false;
		}
		

		 
			  if (timerType != 1) {
				
				 
			 if (isNaN(beginDay)) {
					alert("一天里首次抄表时间为整数");
					return false;
				} else if (beginDay < 1 || beginDay > 24) {
					alert("一天里首次抄表时间大于1小于24的整数！");
					return false;
				}
				 if (timerType == 3) {

					 if (isNaN(beginTime)) {

						alert("开始日期为整数");
						return false;
					} else if (beginTime < 1 || beginTime > 28) {
						alert("开始日期大于1小于28的整数！");
						return false;
					}
					
		
					
					if (isNaN(dayInterval)) {

						alert("间隔天数为整数");
						return false;
					} else if (dayInterval < 1 || dayInterval > 28) {
						alert("间隔天数大于1小于28的整数！");
						return false;
					}


					if (isNaN(readDayNum)) {

						alert("月最大抄表天数为整数");
						return false;
					} else if (readDayNum < 1 || readDayNum > 28) {
						alert("月最大抄表天数大于1小于28的整数！");
						return false;
					}
					

					if (isNaN(dayReadNum)) {

						alert("每天采集次数为整数");
						return false;
					} else if (dayReadNum < 1 || dayReadNum > 7) {
						alert("每天采集次数为1-7的整数！");
						return false;
					}

				 if (isNaN(hourInterval)) {

						alert("采集时间间隔为整数");
						return false;
					} else if (hourInterval < 1 || hourInterval > 7) {
						alert("采集时间间隔为1-7的整数！");
						return false;
					}

					
				}  
			 } 
			  
			  var id=document.getElementsByName("id")[0].value;
				alert(id.lenght);
				 if(id.length<1){
					
				    document.form.action="fixedReadSetting_add.action";
					document.form.target="right";
					document.form.submit();
					return true; 
				}
				else{
					 document.form.action="fixedReadSetting_edit.action";
					document.form.target="right";
					document.form.submit(); 
					return true; 
				} 
	}
	
</script>

</head>
<body>
	<div id="container">
		<!-- 	<br>
		<span style="font-size:20px;">抄表方案</span> -->

		<table class="table-edit">
			<s:form action="" method="post" name="form">
				<s:hidden name="id"></s:hidden>
				<tbody>
					<tr>
						<td><div id="bh">方案编号&nbsp;</div></td>
						<td><s:textfield name="schemeID" /> *</td>
					</tr>
					<tr>
						<td><div id="na">方案名称&nbsp;</div></td>
						<td><s:textarea name="schemeName" class="InputStyle" />
							*(请详细描述)</td>
					</tr>
					<tr>
						<td>定时类型&nbsp;</td>
						<td><s:select name="timerType" size="1"
								cssClass="SelectStyle"
								list="#{1:'每天一次且不规定时间',2:'每天一次规定时间',3:'按一下参数自定义'}" listKey="key"
								listValue="value" /></td>
					</tr>
					<tr>
						<td>开始日期&nbsp;</td>
						<td><s:textfield name="beginDay" class="InputStyle" />
							*(请输入1-28之间的数字)</td>
					</tr>
					<tr>
						<td>间隔天数&nbsp;</td>
						<td><s:textfield name="dayInterval" class="InputStyle" />
							*(请输入1-7之间的数字)</td>
					</tr>
					<tr>
						<td>月最大抄表天数&nbsp;</td>
						<td><s:textfield name="readDayNum" class="InputStyle" />
							*(请输入1-28之间的数字)</td>
					</tr>
					<tr>
						<td>月末是否抄表&nbsp;</td>
						<td><s:select name="readInMonthEnd" size="1"
								cssClass="SelectStyle" list="#{0:'否',1:'是'}" listKey="key"
								listValue="value" /></td>
					</tr>

					<tr>
						<td>一天首次抄表时间</td>
						<td><s:textfield name="beginTime" class="InputStyle" />
							*(请输入1-24之间的整数)</td>
					</tr>
					<tr>
						<td>每天采集次数&nbsp;</td>
						<td><s:textfield name="dayReadNum" class="InputStyle" />
							*(请输入1-7之间的整数)</td>
					</tr>
					<tr>
						<td>每天采集间隔&nbsp;</td>
						<td><s:textfield name="hourInterval" class="InputStyle" />
							*(请输入1-10之间的整数)</td>
					</tr>
					<tr>
						<td>抄表是否指定时间</td>
						<td><s:select name="isReadOnScheme" size="1"
								cssClass="SelectStyle" list="#{0:'否',1:'是'}" listKey="key"
								listValue="value" />*</td>
					</tr>
					<tr>
						<td>重复执行次数</td>
						<td><s:textfield name="retryNum" class="InputStyle" />
							*(请输入1-7之间的整数)</td>
					</tr>
					<tr>
						<td>重复执行时的间隔时</td>
						<td><s:textfield name="reTryInterval" class="InputStyle" />
							*(请输入1-7之间的整数)</td>
					</tr>
					<%--<tr>
						<td>备注&nbsp;</td>
						<td><s:textarea name="remark" class="InputStyle" /> *</td>
					</tr> --%>


				</tbody>
				<tfoot>
					<tr>
						<td colspan="3"><input type="button" value="保存"
							onclick="a();"></td>

					</tr>
				</tfoot>
			</s:form>
		</table>
	</div>
</body>
</html>
