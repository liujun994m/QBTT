<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>水表列表</title>
    
	<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<script type="text/javascript">

$(function(){
	$(".toggle_bar").click(function() {
		$('.top_head').toggle();
		$(this).children('i').toggleClass('fa-plus-square');
		$(this).children('i').toggleClass('fa-minus-square');
	});
	$("#cbSelectAll").click(function(){
		if(this.checked==true){
			$(".TableStyle  tr").css('background', '#99FF99');
		}else if(this.checked==false){
			 $(".TableStyle  tr:even").css('background', '#FFFFFF');
			    $(".TableStyle  tr:odd").css('background', '#CCEEFF');
		}
	$(this).find("input").attr("checked",this.checked);
	}); 
	
		$("[name=deviceIds]").click(function(){			
			if(this.checked==true){					
				 $(this).parents("tr").css('background', '#99FF99');
			}else{
				$(this).parents("tr").css('background', '#EEFFBB');
			}	
	});
});

function collect(){
	var devices=document.getElementsByName("deviceIds");
	var count=0;
	for(var i=0;i<devices.length;i++){
		if(devices[i].checked){
			count++;
		}
	}
	if(count==0){
	alert("请勾选水表！");
	return false;
	}
	var day=document.getElementsByName("day")[0].value;
	if(day.length<1){
		alert("日期项不能为空！");
		return false;
	}if(day==0){	
		document.form.target="right";
		document.form.action="tempCQ_tempCollect.action";		
		document.form.submit();
		return true;
	}else{
		var para="planCQ_planCollect.action";
         checkform(para,day);
	}
}

function dataLoad(){
	var devices=document.getElementsByName("deviceIds");
	var count=0;
	for(var i=0;i<devices.length;i++){
		if(devices[i].checked){
			count++;
		}
	}
	if(count==0){
	alert("请勾选水表！");
	return false;
	}
	var day=document.getElementsByName("day")[0].value;
	if(day.length<1){
		alert("日期项不能为空！");
		return false;
	}if(day==0){
		document.form.target="right";
		document.form.action="tempCQ_dataLoad.action";
		document.form.submit();
		return true;
	}else{
		var para="planCQ_dataLoad.action";
		 checkform(para,day);
	}
}

function deviceOpen(){
	var devices=document.getElementsByName("deviceIds");
	var count=0;
	for(var i=0;i<devices.length;i++){
		if(devices[i].checked){
			count++;
		}
	}
	if(count==0){
	alert("请勾选水表！");
	return false;
	}
	var day=document.getElementsByName("day")[0].value;
	if(day.length<1){
		alert("日期项不能为空！");
		return false;
	}
	if(day==0){	
	document.form.target="right";
	document.form.action="tempCQ_deviceOpen.action";
	document.form.submit();
	return true;
	}else{
		var para="planCQ_deviceOpen.action";
		 checkform(para,day);
	}
}

function deviceClose(){
	var devices=document.getElementsByName("deviceIds");
	var count=0;
	for(var i=0;i<devices.length;i++){
		if(devices[i].checked){
			count++;
		}
	}
	if(count==0){
	alert("请勾选水表！");
	return false;
	}
	var day=document.getElementsByName("day")[0].value;
	if(day.length<1){
		alert("日期项不能为空！");
		return false;
	}
	if(day==0){
	document.form.target="right";
	document.form.action="tempCQ_deviceClose.action";
	document.form.submit();	
	return true;
	}else{
		var para="planCQ_deviceClose.action";
		 checkform(para,day);
	}
}
function checkform(para,day){
	var hour=document.getElementsByName("hour")[0].value;
	var minute=document.getElementsByName("minute")[0].value;
	var d=new Date();
	var h=d.getHours();	
	var min=d.getMinutes();  
	if(day==1){
		hour=hour+0;
		minute=minute+0;
		if(h>hour){
			alert("小时项必须在今日时间之后！");
			return false;
		}
		if(h==hour&&min>minute){				
			alert("分钟项必须在当前小时分钟之后！");
			return false;
		}else{
			document.form.target="right";
			document.form.action=para;
			document.form.submit();
			return true;
		}
	}
	if(isNaN(hour)==true||isNaN(minute)==true){
		alert("请输入只包含数字的小时分钟值！");
		return false;
	}
	if(hour.length<1||minute.length<1){
		alert("小时分钟项不能为空！");
		return false;
	}
	if(hour.length!=2||minute.length!=2){
		alert("小时分钟项格式错误，请输入类似00，01这种格式的值！");
		return false;
	}if(hour>23||hour<0||minute>59||minute<0){
		alert("小时分钟项不在范围内！");
		return false;
	}
	else{
		document.form.target="right";
		document.form.action=para;
		document.form.submit();
		return true;
	}
	
}
	</script>
  </head>
    <style type="text/css">
body{
text-align:center;
}
</style>
  <body>
    <div id="container3">
    <table class="center table-roleList">
			
			<thead class="toggle" style="width:100%;" >
				<tr>
					<th colspan="44" style="background: #A9CCF8;">
						<div class="toggle_bar"><i class="fa fa-minus-square" style="font-size:20px;">显示/隐藏操作栏</i></div><br>
						<div style="font-size:15px;float:left;"><a href="${pageContext.request.contextPath}/tempCQ_getDayList.action"  target="right">点击此处查看今日所有临时命令</a></div><br>
					</th>
				</tr>
			</thead>
			
				<thead class="top_head" style="width:100%;">
					
						<tr>	
							<th style="margin:0;" colspan="22" >
							<div class="toggle_content">
									<s:form action="tempCQ_findByUserNo" target="right">
	                        <s:textfield name="userNo" ></s:textfield>
	                        <s:fielderror ><s:param>userNo</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/userNose.gif"/></s:form>
	                        </div>
							</th>	
						<th>
						<div class="toggle_content">
							 <s:form action="tempCQ_findBydeviceNo" target="right">
	                         <s:textfield name="deviceNom"></s:textfield>
	                           <s:fielderror><s:param>deviceNom</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/deviceNose.gif"/></s:form>
	                        	</div>
						</th>
						</tr>
					<tr>	
							<th style="margin:0;" colspan="22" >
							<div class="toggle_content">
									<s:form action="tempCQ_findByUserName" target="right">
	                        <s:textfield name="userName" ></s:textfield>
	                        <s:fielderror ><s:param>userName</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/userNase.gif"/></s:form>
	                        </div>
							</th>	
						<th>
						<div class="toggle_content">
							 <s:form action="tempCQ_findByUserAddr" target="right">
	                         <s:textfield name="userAddr"></s:textfield>
	                           <s:fielderror><s:param>userAddr</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/addressSe.gif"/></s:form>
	                        	</div>
						</th>
						</tr>
						</thead>
						</table>
    
		<s:form action=""  name="form">
		<div class="toggle_content" style="border-top: 1px dashed #DB94FC;border-bottom: 1px dashed #DB94FC;margin-top:40px;padding-bottom:50px;">
		<table  style="margin:0 auto;">
		<thead class="top_head">
						<tr>
							<th style="margin:0;padding:0;" colspan="22" >
								<div class="state" style="margin-top:20px;">
								 <img src="${pageContext.request.contextPath}/images/collect/collect.gif" style="cursor:hand" onclick="collect();" />  
                                 <img src="${pageContext.request.contextPath}/images/collect/dataload.gif" style="cursor:hand" onclick="dataLoad();" /> 
                                 <img src="${pageContext.request.contextPath}/images/collect/deviceClose.gif" style="cursor:hand" onclick="deviceClose();" /> 
                                 <img src="${pageContext.request.contextPath}/images/collect/deviceOpen.gif" style="cursor:hand" onclick="deviceOpen();" />  
								</div>
							</th>
						</tr>
						<tr class="set_time">
							<td style="padding-left:3%;margin:0;" colspan="22" >
								<table  class="set">
									<tr>
										<td colspan="5" >
											执行时间
										</td>
									</tr>
									<tr>
										<td>日期</td>
										<td></td>
										<td>小时</td>
										<td></td>
										<td>分钟</td>
									</tr>
									<tr>
										<td><s:select name="day" id="day" size="1" cssClass="SelectStyle" list="#{0:'马上执行',1:'今天',2:'明天',3:'后天'}"
									listKey="key" listValue="value" cssStyle="width:120px;height:24px"/></td>
									    <td>&nbsp;</td>
										<td> <s:textfield name="hour"  cssClass="SelectStyle" maxlength="5" cssStyle="width:135px;height:24px" /></td>
										<td>&nbsp;</td>
										<td> <s:textfield name="minute"  cssClass="SelectStyle" maxlength="5" cssStyle="width:135px;height:24px"/></td>
									</tr>
								</table>
								<br>
							</td>
						</tr>
					
				</thead>
		</table>
		<table class="center table-roleList" style="width:2200px">
				
			
			<thead>
				<tr>
					<th style="width:100px">
						<input type="CHECKBOX" id="cbSelectAll" onClick="$('[name=deviceIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
					</th>
					
					<th style="width:100px">用户号</th>
					<th style="width:100px">用户名</th>
					<th style="width:100px">表编号</th>		                
					<th style="width:100px">上次读数</th>
					<th style="width:200px">上次抄表时间</th>
					<th style="width:100px">本次读数</th>
					<th style="width:200px">本次抄表时间</th>
					<th style="width:100px">用量</th>	                
					<th style="width:100px">集中器编号</th>
					<th style="width:100px">采集器名称</th>
					<th style="width:100px">水表地址</th>
					<th style="width:100px">低数</th>
					<th style="width:100px">型号</th>
					<th style="width:100px">通讯状态</th>
					<th style="width:100px">表状态</th>
					<th style="width:100px">阀门状态</th>
					<th style="width:200px">阀门执行时间</th>
					<th style="width:100px">手动开阀</th>              
				</tr>
			</thead>
			<tbody>
         <s:iterator value="#deviceList">
				<tr>
					<td>
						<input type="checkbox" name="deviceIds" value="${id }" id="ckb_${id }" <s:property value="%{id in deviceIds ? 'checked' : ''}" />>
							 				
							 		 <label for="ckb_${id }"><span >${id }</span> </label>
					</td>
					        <td><s:property value="userNo"/>&nbsp;</td>	 	
					        <td><s:property value="userName"/>&nbsp;</td>
			                <td><s:property value="deviceNo"/>&nbsp;</td>
			                <td><s:property value="PrShowValue"/>&nbsp;</td>
			                <td><s:property value="PrCeDate"/>&nbsp;</td>
			                <td><s:property value="ShowValue"/>&nbsp;</td>
			                <td><s:property value="readTime"/>&nbsp;</td>
			                <td><s:property value="MonthCount"/>&nbsp;</td>          
			                <td><s:property value="deviceRelation.center.gprs"/>&nbsp;</td> 
			                <td><s:property value="deviceRelation.collection.name"/>&nbsp;</td>
			                <td><s:property value="addr"/>&nbsp;</td>
			                <td><s:property value="OldValue"/>&nbsp;</td>
			                <td><s:property value="deviceTypeID"/>&nbsp;</td>
			                <td><s:property value="commStatue"/>&nbsp;</td>
			                <td><s:property value="deviceStatue"/>&nbsp;</td>
			                <td><s:property value="strobeStatue"/>&nbsp;</td>
			                <td><s:property value="StrobeDate"/>&nbsp;</td>
			                <td><s:property value="allowOper"/>&nbsp;</td>
				</tr>
	      </s:iterator> 
			</tbody>
		</table>
		</div>	
		</s:form>
	</div>

  </body>
</html>
