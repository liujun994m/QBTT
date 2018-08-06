<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
    <title>计划命令列表</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
 	<script type="text/javascript">


     
	</script>   
</head>
<body >


 <div id="container3" > 
 
 <s:form action="planCQ_flush"  name="form" target="right">
   <div id="InputDetailBar" style="text-align: center;" >
    <input type="image" src="${pageContext.request.contextPath}/images/collect/flush.gif"/>   
      <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/goBack.png"/></a> 
     </div> 

</s:form>               


<div  style="text-align: center;font-size: 20px;color:#000000;font-weight:bold">
                                                计划命令列表                  
   </div>    
      <table class="center table-roleList">

        	             <thead>
						 	<tr>
						 					
		                <th>命令类型</th>
		                <th>集中器编号</th>
		                <th>集中器名称</th>
		                <th>采集器地址</th>
		                <th>水表地址</th>		                
		                <th>命令生成时间</th>
		                <th>计划执行时间</th>
		                <th>命令状态</th> 
		                <th>操作员</th>
		               
						</tr>
					      </thead>
             <tbody >
             <s:iterator value="#pCQList" id="pCQ">
		<tr align="CENTER" class="TableDetail1 template">
							<td>
							<s:if test='command=="000101"'>数据采集</s:if>
       <s:elseif test='command=="000102"'>数据读取</s:elseif>
       <s:elseif test='command=="000302"'>水表开阀</s:elseif> 
       <s:elseif test='command=="000304"'>水表开阀 </s:elseif> 
       <s:elseif test='command=="000301"'>集中器开阀 </s:elseif> 
       <s:elseif test='command=="000303"'>集中器关阀 </s:elseif> 
       <s:elseif test='command=="000204"'>设备校时 </s:elseif> 
       <s:elseif test='command=="000208"'>下载用户档案 </s:elseif> 
       <s:elseif test='command=="000103"'>读集中器信息 </s:elseif> 
							</td>                      
			                <td><s:property value="ContentValue1"/>&nbsp;</td> 
			                <td><s:property value="(#centerList.{?#pCQ.ContentValue1==#this.gprsNum})[0].name"/>&nbsp;</td>
			                <td><s:property value="ContentValue2"/>&nbsp;</td>
			                <td><s:property value="ContentValue3"/>&nbsp;</td>
			                <td><s:property value="GenerateTime"/>&nbsp;</td>
			                <td><s:property value="planExecuteTime"/>&nbsp;</td>			               
			              <td>
			              <span id="state">
			               <s:if test='State==0'>命令还未执行</s:if> 
			               <s:elseif test='State==1'>集中器未连接,请检查连接</s:elseif> 
			               <s:elseif test='State==2'>等待执行</s:elseif>
			               <s:elseif test='State==3'>正在执行中!</s:elseif>
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


           
        

</div> 


</body>
</html>

