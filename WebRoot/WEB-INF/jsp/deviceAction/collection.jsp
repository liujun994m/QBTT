<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<script type="text/javascript">
	function deleteAll() {
		document.form.action = "device_delete.action";
		if (confirm("删除之后不可恢复，确认删除么？")) {
			document.form.target = "right";
			document.form.submit();
			return true;
		}
		return false;
	}

	function addBig() {
		document.form.action = "device_addBigDevice.action";
		if (confirm("您确定将选中的水表设置为总表？")) {
			document.form.target = "right";
			document.form.submit();
			return true;
		}
	}

	$(function() {
		if ($("input[name='ids']:checked").length < 1) {
			$(".fun3 input,.fun4 input").attr("disabled", true);
		} else if ($("input[name='ids']:checked").length == 1) {
			$(".fun3 input,.fun4 input").removeAttr("disabled");
			$(".fun3 input").removeAttr("disabled");
		} else if ($("input[name='ids']:checked").length >= 2) {
			$(".fun3 input").attr("disabled", true);
			$(".fun4 input").removeAttr("disabled");
		}
		$("#checkAll").click(function() {
			$("input[name='ids']").prop("checked", this.checked);
			if ($("input[name='ids']:checked").length < 1) {
				$(".fun3 input,.fun4 input").attr("disabled", true);
			} else if ($("input[name='ids']:checked").length >= 2) {
				$(".fun3 input").attr("disabled", true);
				$(".fun4 input").removeAttr("disabled");
			}
		});
		var arr = new Array();
		var $ids = $("input[name='ids']");
		$ids.click(function() {
			$("#checkAll").prop(
					"checked",
					$ids.length == $("input[name='ids']:checked").length ? true
							: false);

			if ($("input[name='ids']:checked").length < 1) {
				$(".fun3 input,.fun4 input").attr("disabled", true);
			} else if ($("input[name='ids']:checked").length == 1) {
				$(".fun3 input,.fun4 input").removeAttr("disabled");
				$(".fun3 input").removeAttr("disabled");
			} else if ($("input[name='ids']:checked").length >= 2) {
				$(".fun3 input").attr("disabled", true);
				$(".fun4 input").removeAttr("disabled");
			}
		});

		//根据屏幕自定义宽度和高度
		var x = winSize().width * 0.8;
		var y = winSize().height * 0.9;
		$('.show11').dialog({
			autoOpen : false,
			bgiframe : true,
			width : x,
			height : y,

			modal : true,
			buttons : {
				"返回" : function() {
					window.parent.frames.right.location.reload();
					$(this).dialog("close");
				}
			},
			close : function() {
				window.parent.frames.right.location.reload();
			}
		});

		var collectionId = document.getElementsByName("collectionId")[0].value;
		$('.dialog1')
				.click(
						function() {
							$("#Detail").attr(
									"src",
									"device_addUi.action?collectionId="
											+ collectionId);
							$('.show11').dialog({
								title : "水表信息设置"
							});
							$('.show11').dialog('open');
							return false;
						});
		$('.dialog11').click(
				function() {
					var id_array = new Array();
					if ($('input[name="ids"]:checked').is(':checked')) {
						$('input[name="ids"]:checked').each(
								function() {
									id_array.push($(this).attr('id'));
									var l = id_array.length - 1;
									$("#Detail").attr(
											"src",
											"device_editUI.action?id="
													+ id_array[l]);
									$('.show11').dialog({
										title : "修改水表信息设置"
									});
									$('.show11').dialog('open');
								});
					}
					return false;
				});

		$('.dialog2').click(
				function() {
					var id_array = new Array();
					if ($('input[name="ids"]:checked').is(':checked')) {
						$('input[name="ids"]:checked').each(
								function() {
									id_array.push($(this).attr('id'));
									var l = id_array.length - 1;


									$("#Detail").attr(
											"src",
											"deviceChange_changeUi.action?ID="
													+ id_array[l]);
									$('.show11').dialog({
										title : "更换水表"
									});
									$('.show11').dialog('open');
								});

					}
					return false;
				});
		$('.dialog4').click(
				function() {

					$("#Detail").attr(
							"src",
							"collection_look.action?collectionId="
									+ collectionId);

					$('.show11').dialog({
						title : "采集器信息"
					});
					$('.show11').dialog('open');
					return false;
				});
		$('.dialog5').click(
				function() {
					var array = new Array();
					var b = new Array();
					$("input[name='ids']:checked").each(function() {
						array.push($(this).attr("id"));
						b = array.join(",");
					});
					var c = b.toString();
					$("#Detail").attr("src",
							"device_addBigForSelected.action?IDS=" + c);
					$('.show11').dialog({
						title : "为选中水表添加总表"
					});
					$('.show11').dialog('open');
					return false;
				});
	});
</script>
<style type="text/css">
</style>
</head>
<body>
	<div id="container3">
		<s:form action="" name="form">
			<div class="monitor">
				<div class="Title_bar">

					<span>所选采集器：${collectionName}</span> <a href="#" class="dialog4"
						target="right">查看</a>
					<!-- <a href="#" class="dialog3" target="main">修改</a> -->
					<br>


					<ul class="state">
						<li><a href="#" target="main"><input type="button"
								class="dialog1" value="增加水表" /></a></li>
						<li class="fun4"><a href="#" target=""><input
								type="submit" onClick="deleteAll();" value="删除选中水表" /></a></li>
						<li class="fun3"><a  href="#" target="main"><input
								type="button" class="dialog11" value="修改选中水表" /></a></li>
						<li class="fun3"><a href="#" target="main"><input
								type="button" class="dialog2" value="更换选中水表" /></a></li>
						<li class="fun4"><a href="#" target=" "><input
								type="submit" onClick="addBig();" value="添加选中水表为总表" /></a></li>
						<li class="fun4"><a href="#" target=" "><input
								type="submit" class="dialog5" value="为选中水表添加总表" /></a></li>
					</ul>
				</div>
				<!-- <div class="title2">水表列表</div> -->
				<table class="center table-roleList">

					<thead>
						<tr>
							<th><label for="checkbox">全选</label> <input type="checkbox"
								id="checkAll"
								onClick="$('[name=Ids]').attr('checked',this.checked)" /></th>
							<th>序号</th>
							<th>表号</th>
							<th>表名称</th>
							<th>表地址</th>
							<th>型号</th>
							<th>附加费</th>
							<th>最大值</th>
							<th>倍率</th>
							<th>告警</th>
							<th>告警类型</th>
							<th>告警上限</th>
							<th>告警下限</th>
							<th>条码</th>
							<th>生产日期</th>
							<th>安装日期</th>
							<th>停用</th>
							<th>计费类型</th>
							<th>用量计算方法</th>
						</tr>
					</thead>
					<tbody>
						<s:hidden name="collectionId" />
						<s:iterator value="#deviceList">
							<s:if test="%{status== 0}">
								<tr>
									<td>
										<%-- <s:checkbox name="ids" value="${id}" fieldValue="%{id}" id="${id }"/> --%>
										<input type="checkbox" name="ids" id="${id}" value="${id }">
									</td>
									<td><s:property value="id" />&nbsp;</td>
									<td><s:property value="deviceNo" />&nbsp;</td>
									<td><s:property value="name" />&nbsp;</td>
									<td><s:property value="addr" />&nbsp;</td>
									<td><s:property value="deviceType.name" />&nbsp;</td>
									<td><s:property value="subjoinID" />&nbsp;</td>
									<td><s:property value="MaxNum" />&nbsp;</td>
									<td><s:property value="imultiple" />&nbsp;</td>
									<td><s:if test="%{needAlarm == 0}">不需要</s:if> <s:if
											test="%{needAlarm == 1}">需要</s:if></td>
									<td><s:if test="%{alarmGetType == 0}">取当前值</s:if> <s:if
											test="%{alarmGetType == 1}">取用量</s:if></td>
									<td><s:property value="alarmMax" />&nbsp;</td>
									<td><s:property value="alarmMin" />&nbsp;</td>
									<%--      <td><s:property value="parentId"/>&nbsp;</td> --%>
									<td><s:property value="CardID" />&nbsp;</td>
									<td><s:property value="createDate" />&nbsp;</td>
									<td><s:property value="setupDate" />&nbsp;</td>
									<td><s:if test="%{StopUse == 0}">使用</s:if> <s:elseif
											test="%{StopUse == 1}">未使用</s:elseif> <s:elseif
											test="%{StopUse == 2}">用量异常</s:elseif> <s:else>停止使用</s:else>
									</td>
									<td><s:if test="%{GetMoneyType == 0}">不计费</s:if> <s:if
											test="%{GetMoneyType == 1}">实时收费</s:if> <s:if
											test="%{GetMoneyType == 2}">按月收费</s:if></td>
									<td><s:if test="%{UserCountType==0}">不计用量</s:if> <s:if
											test="%{UserCountType==1}">上次抄读</s:if> <s:if
											test="%{UserCountType==2}">每天</s:if> <s:if
											test="%{UserCountType==3}">每月</s:if></td>
								</tr>
							</s:if>
						</s:iterator>
					</tbody>
				</table>
		</s:form>
	</div>
	<div class="show11">
		<iframe id="Detail" width="100%" height="1000" frameborder="no"
			scrolling="yes"></iframe>
	</div>
	<!-- <div class="show12" title="更换水表">
		<iframe src="device_change.html" width="100%" height="500" frameborder="no" scrolling="yes"></iframe>
	</div>
	<div class="show13" title="修改采集器">
		<iframe src="collection_add.html" width="100%" height="520" frameborder="no" scrolling="yes"></iframe>
	</div>
	<div class="show14" title="采集器信息">
		<iframe src="collection_look.html" width="100%" height="500" frameborder="no" scrolling="yes"></iframe>
	</div>
	<div class="show15" title="为选中水表添加总表">
		<iframe src="add_device.html" width="100%" height="900" frameborder="no" scrolling="yes"></iframe>
	</div> -->
</body>
</html>

