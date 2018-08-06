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
	<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
	<script type="text/javascript">

		$(function() {
			$("#checkAll").click(function() {
				$('input[name="ids"]').prop("checked",this.checked); 

				if($("input[name='ids']:checked").length >= 2){
					$(".fun input").attr("disabled",true);
				}
				else{
					$(".fun input").removeAttr("disabled");
				}
			});
			var $ids = $("input[name='ids']");
			$ids.click(function(){
				$("#checkAll").prop("checked",$ids.length == $("input[name='ids']:checked").length ? true : false);

				if($("input[name='ids']:checked").length >= 2){
					$(".fun input").attr("disabled",true);
				}
				else{
					$(".fun input").removeAttr("disabled");
				}
			});
			var x=winSize().height;
			var y=winSize().width; 
/* 			var x=$(window).width()*0.8;
			var y=$(window).height()*0.9; */
			$('.show11').dialog({
				autoOpen: false, 
				bgiframe: true, 
				width:x,
				height:y,
				modal:true,
				buttons: {
					"返回": function() {
						window.parent.frames.right.location.reload();
						$(this).dialog("close"); 
					} 
				},
				close: function() { 
					window.parent.frames.right.location.reload();
				}
			});

			$('.dialog').click(function(){
				$('.show11').dialog('open');
				return false;
			});
		});
		
		function a(){
			document.form.action = "device_setBig.action?IDS="+${IDS};
			document.form.target = "right";
			document.form.submit();
			return true;
		}
	</script>
	<style type="text/css">

	</style>
</head>
<body>
	<div id="container3">
		<div class="title2">¯</div>
		<table class="center table-roleList">
		<s:form action="" name="form" method="post">
			<thead>
				<tr>
					<th>
						<!-- <label for="checkbox">全选</label>
						<input type="checkbox" id="checkAll"/> -->
					</th>
					<th>序号</th>
					<th>表名称</th>
					<th>表编号</th>
					<th>表地址</th>
				</tr>
			</thead>
			<tbody>
				<%--  <s:hidden name="IDS"/> --%>
				  <s:iterator value="#bigDevices">
				<tr>
					<td>
						<s:checkbox name="ids"  fieldValue="%{id}"/>
						<%-- <input type="checkbox" name="ids" id="${id}" value="${id }"> --%>
					</td>
					<td><s:property value="id"/>&nbsp;</td>
					<td><s:property value="name"/>&nbsp;</td>
					<td><s:property value="deviceNo"/>&nbsp;</td>
					<td><s:property value="addr"/>&nbsp;</td>
				</tr>
				</s:iterator>
					<tr>
						<td colspan="5">
							<%-- <img src="${pageContext.request.contextPath}/images/save.png"  style="cursor:hand" onclick="a();" />  --%>
							<input type="button" value="保存"  style="cursor:hand" onclick="a();">
						</td>
					</tr>
			</tbody>
			</s:form>
		</table>
	</div>
</body>
</html>

