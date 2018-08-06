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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-ui.min.js"></script>

	<script>
		$(document).ready(function(){
			var x=$(window).width()*0.8;
			var y=$(window).height()*0.9;
			$('.show11').dialog({
				autoOpen: false, 
				bgiframe: true, 
				width:x,
				height:y,
				modal:true, 
				buttons: {
					"返回": function() {
						window.parent.frames.bottom.location.reload();
						$(this).dialog("close"); 
					} 
				}
			});

			 $(".dialog1").click(function(){
				
				  $("#url11").attr("src","fixedReadSetting_editUi.action?id="+$(this).attr("id"));  
				
				$('.show11').dialog({
										title : "修改抄表方案"
									});
				$('.show11').dialog('open'); 
				
				return false; 
			}); 
			$(".dialog2").click(function(){
				  $("#url11").attr("src","fixedReadSetting_addUi.action"); 
				  $('.show11').dialog({
						title : "增加抄表方案"

					});
				$('.show11').dialog('open'); 
				return false;
			});
		});
	</script>
</head>
<body>
	<div id="container2">
		<!-- <div class="Title_bar">
			<span>定时抄表方案</span>
		</div> -->
		<span style="font-size:20px;">集中器抄表方案管理</span>
		<br>
		<br>
		<table class="center table-roleList" style="width:1500px">
			<thead>
				<tr>
					<th>相关操作</th>					
					<th>方案编号</th>
					<th>方案名称</th>
					<th>定时类型</th>
					<th>开始日期</th>
					<th>抄表间隔天数</th>
					<th>月最大抄表天数</th>
					<th>是否指定抄表时间</th>
					<th>一天首次抄表时间(h)</th>
					<th>每天采集次数</th>
					<th>采集间隔</th>
					<th>月末是否指定抄表</th>
					
					<th>重复执行次数</th>
					
					<th>重复执行时间间隔(min)</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#readSchemeList">
				<tr>
					<td>
						<a href="#" 
							class="dialog1" id="${id }" >修改</a>&nbsp; <a href="fixedReadSetting_delete.action?id=${id }"  >删除</a>
					</td>
				
					<td>${schemeID }</td>
					<td>${schemeName }</td>
					<td><s:if test='timerType==1'>
						每天一次且不规定时间
						</s:if> <s:elseif test='timerType==2'>
						每天一次规定时间
						</s:elseif> <s:elseif test="timerType==3">
						按一下参数自定义</s:elseif>
						</td>
					<td>${beginDay }</td>
					<td>${dayInterval }</td>
					<td>${readDayNum }</td>
					<td>
						<s:if test='isReadOnScheme==1'>
						是
						</s:if>
						<s:elseif test='isReadOnScheme==0'>
						否
						</s:elseif></td>
					<td>${beginTime }</td>
					<td>${dayReadNum }</td>
					
					<td>${hourInterval }</td>
					<td><s:if test='readInMonthEnd==1'>
						是
						</s:if>
						<s:elseif test='readInMonthEnd==0'>
						否
						</s:elseif></td>
						
					<td>${retryNum }</td>
					
					<td>${reTryInterval }</td>
				</tr>
				</s:iterator>

			</tbody>
			<tfoot>
				<tr>
					<td colspan="16">
						<input type="button" value="新建" class="dialog2"/>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	<div class="show11" title="抄表方案管理">
		<iframe id="url11" width="100%" height="1000"  frameborder="no" scrolling="no"></iframe>
	</div>
</body>
</html>
