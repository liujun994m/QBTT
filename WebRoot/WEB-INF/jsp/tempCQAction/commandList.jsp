<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
    <title>部门列表</title>
     <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.4.4.js"></script>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
   
 	<script type="text/javascript">	
 		 $(document).ready(function () {
			    var a1="执行成功！";
			 	var a2="执行失败！";
			 	var a3="集中器未连接,请检查连接";
			 	 var array=new Array();
					$(".comid").each(function() {
						array.push($(this).text());
					}); 
					var ids=array.join(",");
			       	setInterval(su,4000);
			       	
			 function su(){	
				 var state=document.getElementsByTagName("span");	
				 var centerlength=document.getElementsByName("centerIds").length;	 	
				 	var Num=0;
					for(var i=0;i<state.length;i++){
							var obj=state[i].innerHTML.replace(/[ ]/g,"").replace(/^\n+|\n+$/g,"");		
							if(obj==a1||obj==a2||obj==a3){					
								Num=Num+1;
							}		
						}			
				if(Num==state.length){		
					alert("命令执行成功，马上刷新页面！");
						 if(centerlength<1){
							 document.form1.action="tempCQ_deviceList.action";
								document.form1.target="right";
								document.form1.submit();
								return true;
						 }else{
							 document.form1.action="tempCQ_centerList.action";
								document.form1.target="right";
								document.form1.submit();
								return true;
						 }
				}else{
					  $.post("GsonServlet",{
	                        num:ids
	                    }, function (returnedData, status) {
	                    	for(var i = 0; i < returnedData.length; i++){
	                    		var idt=returnedData[i].id;
	                    		var stat=returnedData[i].State;
	                    		switch (stat)
	                    		{
	                    		case 0:$("."+idt).html("命令还未执行"); break;
	                    		case 1:$("."+idt).html("集中器未连接,请检查连接"); break;
	                    		case 2:$("."+idt).html("等待执行"); break;
	                    		case 3:$("."+idt).html("正在执行中！"); break;
	                    		case 4:$("."+idt).html("执行错误，重试中！"); break;
	                    		case 5:$("."+idt).html("执行成功！"); break;
	                    		case 6:$("."+idt).html("执行失败！"); break;
	                    		}
	                    	}
	                    });
				}
					return true;
				}	
		 }); 

	</script>   

</head>
<body>

 <div id="container3" > 
 	<div class="Title_bar">
			<span>临时命令列表 >></span>
		</div>
     <div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/goBack.png"/></a> 
     </div> 
 <s:hidden name="command" value="%{#tCQList.get(0).command}" id="comm"/>   
      <table class="center table-roleList" >
        	             <thead>
						 	<tr>
					    <th style="display:none;">id</th>					
		                <th>命令类型</th>
		                <th>集中器编号</th>
		                <th>集中器名称</th>
		                <th>采集器地址</th>
		                <th>水表地址</th>
		                <th>命令生成时间</th>
		                <th>命令状态</th> 
		                <th>操作员</th>	                
						</tr>
					      </thead>
             <tbody>
             <s:iterator value="#tCQList" id="tCQ">
		<tr align="CENTER" >
		 <td style="display:none;"><div class="comid"><s:property value="id"/></div></td>
							<td>
							<s:if test='command=="000101"'>数据采集</s:if>
       <s:elseif test='command=="000102"'>数据读取</s:elseif>
       <s:elseif test='command=="000302"'>水表开阀</s:elseif> 
       <s:elseif test='command=="000304"'>水表关阀 </s:elseif> 
       <s:elseif test='command=="000301"'>集中器开阀 </s:elseif> 
       <s:elseif test='command=="000303"'>集中器关阀 </s:elseif> 
       <s:elseif test='command=="000204"'>设备校时 </s:elseif> 
       <s:elseif test='command=="000208"'>下载用户档案 </s:elseif> 
							</td>                      
			                <td><s:property value="ContentValue1"/>&nbsp;</td> 
			                <td><s:property value="(#centerList.{?#tCQ.ContentValue1==#this.gprsNum})[0].name"/>&nbsp;</td>
			                <td><s:property value="ContentValue2"/>&nbsp;</td>
			                <td><s:property value="ContentValue3"/>&nbsp;</td>
			                <td><s:property value="GenerateTime"/>&nbsp;</td>			               
			              <td>
			              <span id="state" class="${id}" style="color:#FF0000;">
			               <s:if test='State==0'>命令还未执行</s:if> 
			               <s:elseif test='State==1'>集中器未连接,请检查连接</s:elseif> 
			               <s:elseif test='State==2'>等待执行</s:elseif>
			               <s:elseif test='State==3'>正在执行中！</s:elseif>
			               <s:elseif test='State==4'>执行错误，重试中！</s:elseif>
			               <s:elseif test='State==5'>执行成功！</s:elseif>
			               <s:elseif test='State==6'>执行失败！</s:elseif>		    
			               </span>                    
			              </td> 		              
			                <td><s:property value="Operator"/>&nbsp;</td>
                       
		</tr>
		
		  </s:iterator> 
		             
			</tbody>
                </table>

 
        <s:form action=""   name="form1">
         <s:iterator value="centerIds" status="cs"> 
  <s:hidden  name="centerIds" value="%{centerIds[#cs.index]}"/>
   </s:iterator>

   <s:iterator value="deviceIds" status="ds"> 
  <s:hidden  name="deviceIds" value="%{deviceIds[#ds.index]}"/>
   </s:iterator>    
        </s:form>

</div> 


</body>
</html>

