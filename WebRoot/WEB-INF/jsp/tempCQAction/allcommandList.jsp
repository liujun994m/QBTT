<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
    <title>部门列表</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
     <script type="text/javascript">
     function flush(){
    	 document.form.action="tempCQ_getDayList.action";
    	 document.form.target="right";
    	 document.form.submit();
    	 return true;
     }
     </script>

</head>
<body>

 <div id="container3" > 
  	<div class="Title_bar">
			<span>今日临时命令列表 >></span>
		</div>
     <div id="InputDetailBar">

            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/goBack.png"/></a> 
     </div> 
<!-- <div  style="text-align: center;font-size: 15px;color:#000000;font-weight:bold">
                                                临时命令列表                  
   </div>  -->   
      <table class="center table-roleList">
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
       <s:elseif test='command=="000304"'>水表开阀 </s:elseif> 
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

        </s:form>

</div> 


</body>
</html>


