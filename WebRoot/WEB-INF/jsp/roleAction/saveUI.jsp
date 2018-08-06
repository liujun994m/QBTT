<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>岗位设置</title>
   <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
  </head>
  <script type="text/javascript">
 
	function a() {
		
		var id=document.getElementsByName("id")[0].value;
		
		var roleName=document.getElementsByName("roleName");
		var name=document.getElementsByName("name")[0].value;
		var text=document.getElementById("ro");
		if(name.indexOf(" ") >=0){
			alert("角色名不能有空格");
			return false;
		}
		
		for(var i=0;i<roleName.length;i++){
			if(roleName[i].value==name){
				alert("角色名已存在！");
				document.getElementsByName("name")[0].style.color="red";
				text.style.color="red";
				return false;
			}
		} 
		if(id.length<1){
			document.form.action="role_add.action";
			document.form.submit();
			return true; 
		}
		else{
			document.form.action="role_edit.action";
			document.form.submit();
			return true; 
		}
			
		}
	
</script> 
  <body>
   <div id="container">
   <s:form action="" name="form" target="right">
    <s:hidden name="id"/>
		<table class="table-edit">
			
				<tbody>
					<tr>
						<td><div id="ro"> 岗位名称</div></td>
						<td>
							<s:textfield name="name" cssClass="InputStyle" cssStyle="width:200px;"/>
						</td>
					</tr>
					<tr>
						<td>岗位说明</td>
						<td>
							<s:textarea name="description" cssClass="TextareaStyle" cssStyle="width:200px;"/>
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
		</table>
		</s:form>
		 <s:iterator value="#roleNames" status="st"> 
   <s:hidden name="roleName"  value="%{#roleNames.get(#st.index)}" cssClass="InputStyle"/> 
   </s:iterator>
	</div>
  </body>
</html>
