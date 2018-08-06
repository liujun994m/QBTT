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
$(document).ready(function() {
	$("#month").change(function() {
		
		var text1 = $(this).val();
		$("#day").empty();
		  $.post("DayServlet",{
              num:text1
          }, function (returnedData, status) {
          	for(var i = 0; i < returnedData.length; i++){
          		var day=returnedData[i];
          		$("#day").append("<option value="+day+">"+day+"</option>");       		
          	}
          });
	});
	$(".search").click(function() {
		if($("input[name='deviceIds']:checked").length <1){
			alert("请勾选水表!");
			return false;
		}else{
			document.form.action="waterExport_deviceList.action";
			document.form.target="right";
			document.form.submit();
			return true;
		}
	
	});
/*  	$(".writeExport").click(function() {
 		if(confirm("请确认此时水表的读取时间与文本框月份日期的一致！")){
		if($("input[name='deviceIds']:checked").length <1){
			alert("请勾选水表!");
			return false;
		}else{
			var array=new Array();
			var ids="";
			$("input[name='deviceIds']").each(function(){
				if(this.checked==true){
					array.push($(this).attr("id"));
				}
				 ids=array.join(",");
		
			});
			var md=ids+":"+$("#month").val()+","+$("#day").val();
			  $.post("ExcelServlet",{
	              num:md
	          }, function (returnedData, status) {
	         alert("Excel已经生成并存放在d盘dataExcel目录下,名字为deviceTmp.xls！");
	          });
		}
 		}
	});  */ 
	
});
function saveCode(obj) { 
	 var winname = window.open('', '_blank', 'top=10000'); 
     var strHTML = document.all.allcontent.innerHTML; 
     winname.document.open('text/html', 'replace'); 
     winname.document.writeln(strHTML); 
     winname.document.execCommand('saveas','','excel.xls'); 
     winname.close();   
     
 }
	</script>
  </head>
    <style type="text/css">
body{
text-align:center;
}
</style>
  <body >
    <div id="container3">
    <table class="center table-roleList">
			
			<thead class="toggle" style="width:100%;" >
				<tr>
					<th colspan="44" style="background: #A9CCF8;">
						<div class="toggle_bar"><i class="fa fa-minus-square" style="font-size:20px;">显示/隐藏操作栏</i></div><br>
					</th>
				</tr>
			</thead>
			
				<thead class="top_head" style="width:100%;">
					
						<tr>	
							<th style="margin:0;" colspan="22" >
							<div class="toggle_content">
									<s:form action="waterExport_findByUserNo" target="right">
	                        <s:textfield name="userNo" ></s:textfield>
	                        <s:fielderror ><s:param>userNo</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/userNose.gif"/></s:form>
	                        </div>
							</th>	
						<th>
						<div class="toggle_content">
							 <s:form action="waterExport_findBydeviceNo" target="right">
	                         <s:textfield name="deviceNom"></s:textfield>
	                           <s:fielderror><s:param>deviceNom</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/deviceNose.gif"/></s:form>
	                        	</div>
						</th>
				
						</tr>
					<tr>	
							<th style="margin:0;" colspan="22" >
							<div class="toggle_content">
									<s:form action="waterExport_findByUserName" target="right">
	                        <s:textfield name="userName" ></s:textfield>
	                        <s:fielderror ><s:param>userName</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/userNase.gif"/></s:form>
	                        </div>
							</th>	
						<th>
						<div class="toggle_content">
							 <s:form action="waterExport_findByUserAddr" target="right">
	                         <s:textfield name="userAddr"></s:textfield>
	                           <s:fielderror><s:param>userAddr</s:param></s:fielderror>
	                        <input type="image" src="${pageContext.request.contextPath}/images/collect/addressSe.gif"/></s:form>
	                        	</div>
						</th>
						</tr>
						</thead>
						</table>
    
		<s:form action=""  name="form" >
		<div class="toggle_content" style="border-top: 1px dashed #DB94FC;border-bottom: 1px dashed #DB94FC;margin-top:40px;padding-bottom:50px;">
		<div style="margin:0;float:left;">  
    <input type="image" src="${pageContext.request.contextPath}/images/exportExcel.gif"  class="writeExport" onclick="saveCode(allcontent)"/>
 	
		</div>
		<table  style="margin:0 auto;">
		<thead class="top_head">
						<tr>
							<table style="margin:0 auto;">
									<tr >
										<td colspan="5" style="text-align:center;">
											选择时间
										</td>
									</tr>
									<tr >
	                               <td colspan="5" style="text-align:center;">
											<br>
										</td>
									</tr>
									<tr>
										<td>选择月份</td>
										<td></td>
										<td>选择日期</td>
								        <td></td>
								        <td></td>
									</tr>
									<tr>
										<td><s:select name="month" cssClass="SelectStyle"	list="#monthList" id="month"  
                        	listKey="key" listValue="value" cssStyle="width:135px;height:24px"/> </td>
									    <td>&nbsp;</td>
										<td> <s:select name="day" cssClass="SelectStyle"	list="#dayList" id="day"   
                        	listKey="key" listValue="value" cssStyle="width:135px;height:24px"/></td>
										<td>&nbsp;&nbsp;</td>
										<td><img src="${pageContext.request.contextPath}/images/collect/showValue.gif"  class="search" /></td>
									</tr>
								</table>
						</tr>
						
					
				</thead>
		</table>
		<!-- <input type="button" value=" 另存为Excel" onclick="saveCode(allcontent)"> <br>  -->
		<div id="allcontent">
		<table class="center table-roleList" style="width:1200px;margin:0 auto;" border="1" cellspacing="0" cellpadding="0">
				
			
			<thead>
				<tr>
					<th style="width:100px">
						<input type="CHECKBOX" id="cbSelectAll" onClick="$('[name=deviceIds]').attr('checked',this.checked)"/>
								<label for="cbSelectAll">全选</label>
					</th>
					
					<th style="width:100px">用户号</th>
					<th style="width:100px">用户名</th>
					<th style="width:200px">用户地址</th>
					<th style="width:100px">表编号</th>		                
					<th style="width:100px">本次读数</th>
					<th style="width:200px">本次抄表时间</th>		                				
					<th style="width:100px">水表地址</th>
	                <th style="width:200px">设备类型</th>
	                
				</tr>
			</thead>
			<tbody>
         <s:iterator value="#deviceList" status="">
				<tr>
					<td>
						<input type="checkbox" name="deviceIds" value="${id }" id="${id}" <s:property value="%{id in deviceIds ? 'checked' : ''}" />>
							 				
							 		 <label for="ckb_${id }"><span >${id }</span> </label>
					</td>
					        <td><s:property value="userNo"/>&nbsp;</td>	 	
					       	<td><s:property value="userName"/>&nbsp;</td>	 
					       	<td><s:property value="userAddr"/>&nbsp;</td>
			                <td><s:property value="deviceNo"/>&nbsp;</td>
			                <td><s:property value="ShowValue"/>&nbsp;</td>
			                <td><s:property value="readTime"/>&nbsp;</td>   			                
			                <td><s:property value="addr"/>&nbsp;</td>
			                <td><s:property value="deviceType.name"/>&nbsp;</td> 
				</tr>
	      </s:iterator> 
			</tbody>
		</table>
		</div>
		</div>	
		</s:form>
	</div>
<%-- <s:textfield name="cddd" value="%{#deviceTmps.get(0).showValue}" /> --%>

  </body>
</html>
