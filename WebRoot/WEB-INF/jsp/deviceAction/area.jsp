<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<script type="text/javascript">
	$(function() {
		if ($("input[name='centerIds']:checked").length < 1) {
			$(".fun1 input,.fun2 input").attr("disabled", true);
		} else if ($("input[name='centerIds']:checked").length == 1) {
			$(".fun1 input,.fun2 input").removeAttr("disabled");
			$(".fun1 input").removeAttr("disabled");
		} else if ($("input[name='centerIds']:checked").length >= 2) {
			$(".fun1 input").attr("disabled", true);
			$(".fun2 input").removeAttr("disabled");
		}
		$("#checkAll").click(function() {
			$('input[name="centerIds"]').prop("checked", this.checked);

			if ($("input[name='centerIds']:checked").length < 1) {
				$(".fun1 input,.fun2 input").attr("disabled", true);
			} else if ($("input[name='centerIds']:checked").length >= 2) {
				$(".fun1 input").attr("disabled", true);
				$(".fun2 input").removeAttr("disabled");
			}
		});
		var arr = new Array();
		var $subBox = $("input[name='centerIds']");
		$subBox
				.click(function() {
					$("#checkAll")
							.prop(
									"checked",
									$subBox.length == $("input[name='centerIds']:checked").length ? true
											: false);

					if ($("input[name='centerIds']:checked").length < 1) {
						$(".fun1 input,.fun2 input").attr("disabled", true);
					} else if ($("input[name='centerIds']:checked").length == 1) {
						$(".fun1 input,.fun2 input").removeAttr("disabled");
						$(".fun1 input").removeAttr("disabled");
					} else if ($("input[name='centerIds']:checked").length >= 2) {
						$(".fun1 input").attr("disabled", true);
						$(".fun2 input").removeAttr("disabled");
					}
				});

		//根据屏幕自定义宽度和高度
		var x = winSize().width * 0.8;
		var y = winSize().height * 0.9;

		/* var x=$(window).width()*0.8;
		var y=$(window).height()*0.9; */
		$('.show11').dialog({
			autoOpen : false,
			bgiframe : true,
			width : x,
			height : y,
			modal : true,
			buttons : {
				/*"保存": function() { 
					confirm("确定");
					
					$(this).dialog("close"); 
				}, */
				"返回" : function() {
					window.parent.frames.right.location.reload();
					$(this).dialog("close");
				}
			},
			close : function() {
				window.parent.frames.right.location.reload();
			}
		});

		$('.dialog').click(
				function() {
					var areaName = $(".areaName").text();

					$("#url11").attr("src",
							"center_addUi.action?areaId=" + ${area.id});

					$('.show11').dialog({
						title : "增加集中器->所选区域为:" + areaName

					});
					$('.show11').dialog('open');

					return false;
				});

		$('.dialog1').click(
				function() {
					var id_array = new Array();
					var areaName = $(".areaName").text();
					if ($('input[name="centerIds"]:checked').is(':checked')) {
						$('input[name="centerIds"]:checked').each(
								function() {
									id_array.push($(this).attr('id'));
									var l = id_array.length - 1;

									$("#url11").attr(
											"src",
											"center_editUi.action?areaId="
													+ ${area.id} + "&centerId="
													+ id_array[l]);
									$('.show11').dialog({
										title : "修改集中器->所选区域为:" + areaName

									});

									$('.show11').dialog('open');
								});
					}
					return false;
				});

	});

	function del() {
		document.form.action = "center_delete.action?areaId=" + ${area.id};
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
		<s:form action="" name="form">
			<div class="monitor">
				<div class="Title_bar">

					<span class="areaName">当前区域：${area.name}</span><br>

				</div>
				<ul class="state">
					<li><a href="#" target="right"><input type="button"
							class="dialog" value="增加集中器" /> <!-- &nbsp;&nbsp; --></a></li>
					<li class="fun2"><a target="right"
						onClick="return window.confirm('您确定要删除该集中器下及以下的所有设备吗？')"><input
							type="button" onclick="del();" value="删除选中集中器" /> <!-- &nbsp;&nbsp; --></a></li>
					<li class="fun1"><a href="#" target="right"><input
							type="button" class="dialog1" value="修改选中集中器"> <!-- &nbsp;&nbsp; --></a></li>
				</ul>
			</div>
			<div class="title2">集中器列表</div>
			<table class="center table-roleList">

				<thead>
					<tr>
						<th><label for="checkbox">全选</label> <input type="checkbox"
							id="checkAll"
							onClick="$('[name=centerIds]').attr('checked',this.checked)" /></th>
						<th>集中器名称</th>
						<th>安装地址</th>
						<th>协议类型</th>
						<th>编号</th>
						<th>当前状态</th>
						<th>读取周期</th>
						<th>是否使用</th>
						<th>集中器配置</th>
						<th>是否自动采集</th>

						<th>上下行类型</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#area.centers">
						<tr>
							<%-- <td>
						<input type="checkbox" name="centerIds" id="${id}">
					</td>
					<td>${id}</td> --%>
							<td align="Center">
								<li><input type="checkbox" name="centerIds" value="${id }"
									id="${id }"
									<s:property value="%{id in centerIds ? 'checked' : ''}" />>

									<label for="ckb_${id }"><span>${id }</span> </label></li>

							</td>
							<td>${name}</td>
							<td>${installAddr}</td>
							<td><s:if test='protocolType==1'>
					内部协议
					</s:if> <s:elseif test='protocolType==2'>
						130协议</s:elseif> <s:elseif test='protocolType==3'>
					中原油田协议</s:elseif></td>

							<td>${gprsNum}</td>
							<td><s:if test='runStatue==0'>
					运行正常
					</s:if> <s:elseif test='runStatue==1'>
						运行异常</s:elseif></td>
							<td>${readPeriod}</td>
							<td><s:if test='isUse==0'>
					未使用
					</s:if> <s:elseif test='isUse==1'>
						使用</s:elseif></td>
							<td>${config}</td>
							<td><s:if test='isAutoCollection==0'>
					否
					</s:if> <s:elseif test='isAutoCollection==1'>
						是</s:elseif></td>
							<td>${readSchemeId}</td>
							<td>${uplinkType}</td>
						</tr>
					</s:iterator>

				</tbody>
			</table>
		</s:form>
	</div>
	<div class="show11" title="增加集中器" style="display:none">
		<iframe id="url11" width="100%" height="1000" frameborder="no"
			scrolling="no"></iframe>
	</div>

</body>
</html>
