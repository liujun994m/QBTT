<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>操作员设置</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
  </head>
   <script type="text/javascript">
 
 
	function a() {
		var id=document.getElementsByName("id")[0].value;
		var ope=document.getElementsByName("ope");
		var operator=document.getElementById("operatorNo").value;
		var text=document.getElementById("ro");
		var role=document.getElementsByName("roleId")[0].value;
		if(role.length<1){
			alert("岗位不能为空！");
			return false;
		}
		for(var i=0;i<ope.length;i++){
			if(ope[i].value==operator){
				alert("用户号已存在！");
				document.getElementById("operatorNo").style.color="red";
				text.style.color="red";
				return false;
			}
		}
		var telNum=document.getElementsByName("telNum")[0].value;
		var phoneNum=document.getElementsByName("phoneNum")[0].value;
		var username=document.getElementsByName("username")[0].value;
		var operatorNo=document.getElementsByName("operatorNo")[0].value;
		if(username.indexOf(" ") >=0||operatorNo.indexOf(" ") >=0){
			alert("用户名和用户号均不能有空格，请重新输入！");
			return false;
		}
		if(operatorNo.length<1||username.length<1){
			alert("用户名和用户号均不能为空！");
			return false;
		}
		if(telNum.length!=11&&telNum.length!=0){
			alert("请输入11位长度的手机号！");
			return false;
		}
		if(isNaN(telNum)==true){
			alert("请输入只包含数字的手机号！");
			return false;
		}
		if(isNaN(phoneNum)==true){
			alert("请输入只包含数字的电话号！");
			return false;
		}
		
		
			if(id.length<1){
				document.form.action="operator_add.action";
				document.form.submit();
				return true; 
			}
			else{
				document.form.action="operator_edit.action";
				document.form.submit();
				return true; 
			}
		
		
		}
	

</script> 
  <body>
   <div id="container">
		<!-- <div class="Title_bar">
			<span>用户信息</span>
		</div> -->
		<table class="table-edit">	
			<s:form id="operator_edit" action="" target="right" name="form">
			<s:hidden name="id" />
				<tbody>
					 <tr><td width="100">登录名</td>
                        <td><s:textfield name="username" cssClass="InputStyle" cssStyle="width:250px;"/> *
							（登录名要唯一）
						</td>
                    </tr>
                    <tr><td><div id="ro">用户号</div></td>
                        <td><s:textfield name="operatorNo" cssClass="InputStyle" id="operatorNo" cssStyle="width:250px;"/> *</td>
                    </tr>
					<tr><td>性别</td>
                        <td>
                        	<s:radio name="gender" list="{'男','女'}" cssStyle="width:50px;"></s:radio>
						</td>
                    </tr>
                    
                    <tr><td>联系电话一</td>
                        <td><s:textfield name="telNum" cssClass="InputStyle" cssStyle="width:250px;"/>*</td>
                    </tr>
					<tr><td>联系电话二</td>
                        <td><s:textfield name="phoneNum" cssClass="InputStyle" cssStyle="width:250px;"/>*</td>
                    </tr>
                    
                    <tr><td>住址</td>
                        <td><s:textfield name="address" cssClass="InputStyle" cssStyle="width:250px;"/>*</td>
                    </tr>
                    <tr><td>群组</td>
                        <td><s:textfield name="gropuID" cssClass="TextareaStyle" cssStyle="width:250px;"></s:textfield></td>
                    </tr>
                    <tr>
                    <td >岗位</td>
                        <td><s:select name="roleId" multiple="true"  cssClass="SelectStyle"
                        	list="#roleList" listKey="id" listValue="name" cssStyle="height:120px;width:250px;"
                        />
                        
                        </td>
                    </tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<%-- <img src="${pageContext.request.contextPath}/images/save.png" style="cursor:hand" onclick="a();" />  --%>
								<input type="button" style="width:100px;height:35px;" value="保存" onclick="a();"/>			
						</td>
					</tr>
				</tfoot>
			</s:form>
			
		</table>
		<s:iterator value="#operatorNos" status="os"> 
   <s:hidden name="ope"  value="%{#operatorNos.get(#os.index)}" cssClass="InputStyle"/> 
   </s:iterator>
	</div>
  </body>
</html>
