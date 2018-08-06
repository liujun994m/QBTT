<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>集中器列表</title>
    
	<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<script type="text/javascript">

	 	$(function() {
		/* 	$("#checkAll").click(function() {
				$('input[name="subBox"]').prop("checked",this.checked); 
			});
			var $subBox = $("input[name='subBox']");
			$subBox.click(function(){
				$("#checkAll").prop("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
			}); */
		}); 
		$(function(){
		 	$("#cbSelectAll").click(function(){
				if(this.checked==true){
					$(".TableStyle  tr").css('background', '#99FF99');
				}else if(this.checked==false){
					 $(".TableStyle  tr:even").css('background', '#FFFFFF');
					    $(".TableStyle  tr:odd").css('background', '#CCEEFF');
				}
			$(this).find("input").attr("checked",this.checked);
			}); 
			
			$("[name=centerIds]").click(function(){			
				if(this.checked==true){					
					 $(this).parents("tr").css('background', '#99FF99');
				}else{
					$(this).parents("tr").css('background', '#EEFFBB');
				}

			});
			$(".toggle_bar").click(function() {
				$('.top_head').toggle();
				$(this).children('i').toggleClass('fa-plus-square');
				$(this).children('i').toggleClass('fa-minus-square');
			});

		});
      
 		function collect(){
 			var centers=document.getElementsByName("centerIds");
 			var count=0;
 			for(var i=0;i<centers.length;i++){
 				if(centers[i].checked){
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
			document.form.action="tempCQ_centerCollect.action";
			document.form.target="right";
			document.form.submit();
			return true;
			}else{
				var para="planCQ_centerCollect.action";
		         checkform(para,day);
			}
		}
		function dataLoad(){
			var centers=document.getElementsByName("centerIds");
 			var count=0;
 			for(var i=0;i<centers.length;i++){
 				if(centers[i].checked){
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
			document.form.action="tempCQ_centerDataLoad.action";
			document.form.target="right";
			document.form.submit();
			return true;
			}else{
				var para="planCQ_centerDataLoad.action";
		         checkform(para,day);
			}
		}
		function check(){
			var centers=document.getElementsByName("centerIds");
 			var count=0;
 			for(var i=0;i<centers.length;i++){
 				if(centers[i].checked){
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
			document.form.action="tempCQ_check.action";
			document.form.target="right";
			document.form.submit();
			return true;
			}else{
				var para="planCQ_check.action";
		         checkform(para,day);
			}
		}
		function userDownload(){
			var centers=document.getElementsByName("centerIds");
 			var count=0;
 			for(var i=0;i<centers.length;i++){
 				if(centers[i].checked){
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
			document.form.action="tempCQ_userDownload.action";
			document.form.target="right";
			document.form.submit();
			return true;
			}else{
				var para="planCQ_userDownload.action";
		         checkform(para,day);
			}
		}
		function centerRead(){
			var centers=document.getElementsByName("centerIds");
 			var count=0;
 			for(var i=0;i<centers.length;i++){
 				if(centers[i].checked){
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
			document.form.action="tempCQ_centerRead.action";
			document.form.target="right";
			document.form.submit();
			return true;
			}else{
				var para="planCQ_centerRead.action";
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
.im{
margin-top:10px;
}
body{
text-align:center;
}
</style>
  <body>
    <div id="container3">
		
		<table class="center table-roleList">
		<thead class="toggle" >
				<tr>
					<th colspan="22" style="background: #A9CCF8;">
						<div class="toggle_bar"><i class="fa fa-minus-square" style="font-size:20px;">显示/隐藏操作栏</i></div><br>
						<div style="font-size:15px;float:left;"><a href="${pageContext.request.contextPath}/tempCQ_getDayList.action"  target="right">点击此处查看今日所有临时命令</a></div><br>
					</th>
				</tr>
			</thead>
				<thead class="top_head">
					<tr>	
							<th style="margin:0;" colspan="22">
							<div class="toggle_content" >
							<s:form action="tempCQ_findByCenterNo" target="right">
	                        <s:textfield name="centerNo" ></s:textfield>
	                        <s:fielderror ><s:param>centerNo</s:param></s:fielderror>
	                       <input type="image"  src="${pageContext.request.contextPath}/images/collect/centerNose.gif"/> 
	                        </s:form>
	                        </div>
							</th>
					</tr>
					
					</thead>
		</table>
		<s:form action=""  name="form">
		 <div class="toggle_content" style="border-top: 1px dashed #DB94FC;border-bottom: 1px dashed #DB94FC;margin-top:40px;padding-bottom:50px;"> 
		<table style="margin:0 auto;" >
			
				<thead class="top_head" >
						<tr>
							<th style="margin:0;padding:0;" colspan="22" >
								<div style="margin-top:20px;">
								 <img src="${pageContext.request.contextPath}/images/collect/collect.gif" style="cursor:hand" onclick="collect();" />  
                                 <img src="${pageContext.request.contextPath}/images/collect/dataload.gif" style="cursor:hand" onclick="dataLoad();"  /> 
                                 <img src="${pageContext.request.contextPath}/images/collect/checkTime.gif" style="cursor:hand" onclick="check();" /> 
                                 <img src="${pageContext.request.contextPath}/images/collect/userDo.gif" style="cursor:hand" onclick="userDownload();" />  
                                 <img src="${pageContext.request.contextPath}/images/collect/centerRead.gif" style="cursor:hand" onclick="centerRead();" /> <br>
								</div>
							</th>
						</tr>
						
							
					
						<tr class="set_time">
							<td style="text-align:left;padding-left:3%;margin:0;" colspan="22" >
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
									listKey="key" listValue="value" cssStyle="width:120px;height:24px" /></td>
									     <td>&nbsp;</td>
										<td> <s:textfield name="hour"  cssClass="SelectStyle" maxlength="5" cssStyle="width:135px;height:24px"/></td>
										<td>&nbsp;</td>
										<td><s:textfield name="minute"  cssClass="SelectStyle" maxlength="5" cssStyle="width:135px;height:24px"/></td>
									</tr>
								</table>
								<br>
							</td>
						</tr>
						
				</thead>
		</table>
		<table class="center table-roleList" style="width:1500px;margin:0 auto;" >		
			<thead>
				<tr align="CENTER" >
					<th align="LEFT" style="width:100px">
						<input type="CHECKBOX" id="cbSelectAll" onClick="$('[name=centerIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
					</th>
					<th style="width:100px">集中器名称</th>
					<th style="width:100px">协议类型</th>
					<th style="width:200px">集中器编号</th>
					<th style="width:200px">最后读取时间</th>		                
					<th style="width:100px">读取周期</th>
					<th style="width:100px">读取类型</th>
					<th style="width:100px">当前状态</th>
					<th style="width:100px">是否使用</th>
					<th style="width:200px">其他操作</th>	                
					<th>备注</th>
				
				
				</tr>
			</thead>
			<tbody>
         <s:iterator value="#centerList">
				<tr align="CENTER">
					<th  align="LEFT">
						<input type="checkbox" name="centerIds" value="${id }" id="ckb_${id }" <s:property value="%{id in centerIds ? 'checked' : ''}" />>
							 	<%-- 	<span > ${id}</span> --%>
					</th>
					        <td><s:property value="name"/>&nbsp;</td>	
		    		 	    <td><s:property value="protocolType"/>&nbsp;</td>	 		 		 
			                <td><s:property value="gprsNum"/>&nbsp;</td>
			                <td><s:property value="readTime"/>&nbsp;</td>
			                <td><s:property value="readPeriod"/>&nbsp;</td>
			                <td><s:property value="readType"/>&nbsp;</td>
			                <td><s:property value="runStatue"/>&nbsp;</td>
			                <td><s:property value="isUse"/>&nbsp;</td>          
			                <td><s:property value="config"/>&nbsp;</td> 
			                <td><s:a action="tempCQ_deviceList?centerId=%{id}">查看所属水表&nbsp;</s:a></td>
				</tr>
	      </s:iterator> 
			</tbody>
			
		</table>
		 </div> 
		</s:form>
	</div>

  </body>
</html>
