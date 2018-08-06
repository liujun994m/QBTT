<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/init.css" />
<!-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"> -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#checkAll").click(function() {
				$('input[name="centerIds"]').prop("checked",this.checked); 
			});
			var $subBox = $("input[name='centerIds']");
			$subBox.click(function(){
				$("#checkAll").prop("checked",$subBox.length == $("input[name='centerIds']:checked").length ? true : false);
			});
		});
	</script>
	<style type="text/css">

		.input-group-addon{
			border: 1px solid #919191;
		}
	</style>
</head>
<body>

	<div id="container2">
			<span style="font-size:20px;">集中器抄表方案设置</span>
			<br>
			<br>
			<s:form class="bs-example bs-example-form" role="form" action="fixedReadSetting_setting">
				<div class="btn-group">
					<s:select class="input-group-addon"  name="readSchemeId" style="width:180px;float:left;height:40px" list="#readSchemeList" listKey="id"
								listValue="schemeName" headerKey="" headerValue="  选择一下方案之一">
						
					</s:select>
					<input type="submit"  value="保存" style="height:40px;width:100px;float:left"/>
				</div>
			
		<br>
		<br>
		<br>
		<table class="center table-roleList">
			<thead>
				<tr>
					<th>
						<label for="checkAll">全选</label>
						<input type="checkbox" id="checkAll" onClick="$('[name=centerIds]').attr('checked',this.checked)"	/>
					</th>
					<th>集中器名称</th>
					<th>集中器协议类型</th>
					<th>集中器现在的抄表方案</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#centerList" id="center">
				<tr>
					<td>
						<input type="checkbox" name="centerIds" value="${id }" id="ckb_${id }" <s:property value="%{id in centerIds ? 'checked' : ''}" />/>
						<label for="ckb_${id }"><span>${id }</span> </label>
					</td>
					<td><s:property value="name" />&nbsp;</td>
							<td><s:if test='protocolType==1'>
					内部协议
					</s:if>
					 <s:elseif test='protocolType==2'>
						130协议</s:elseif> 
					<s:elseif test='protocolType==3'>
					中原油田协议</s:elseif><s:else>暂时没有设置</s:else></td>
							<td><s:a
									action="fixedReadSetting_readScheme?id=%{readSchemeId}">
									<s:property
										value="(#readSchemeList.{?#center.readSchemeId==#this.id})[0].schemeName" />
								</s:a></td>               
				</tr>
				</s:iterator>
			</tbody>
		</table>
		</s:form>
	</div>
</body>
</html>
