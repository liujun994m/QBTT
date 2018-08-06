<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  <title>区域设置</title>
     <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
  </head>
   <script type="text/javascript">
 
	function a() {
		var id=document.getElementsByName("id")[0].value;
		var ae=document.getElementsByName("ae");
		var name=document.getElementsByName("name")[0].value;
		var text=document.getElementById("ro");
		if(name.indexOf(" ") >=0){
			alert("区域名不能有空格");
			return false;
		}
		for(var i=0;i<ae.length;i++){
			if(ae[i].value==name){
				alert("区域名已存在！");
				document.getElementsByName("name")[0].style.color="red";
				text.style.color="red";
				return false;
			}
		}
		if(id.length<1){
			document.form.action="area_add.action";
			document.form.submit();
			return true; 
		}
		else{
			document.form.action="area_edit.action";
			document.form.submit();
			return true; 
		}
		}
	
</script> 
  <body>
    <div id="container">
	<!-- 	<div class="Title_bar">
			<span>区域编辑</span>
		</div> -->
		<table class="table-edit">
			<s:form id="area_edit" name="form" action="" target="right">		
			<s:hidden name="id"/>
				<tbody>
					<tr><td width="100">上级区域</td>
                        <td><s:select name="parentId" cssClass="SelectStyle" cssStyle="width:250px;"
                        	list="#areaList" listKey="id" listValue="name" 
                        	headerKey="" headerValue="===请选择部门===" />
                        </td>
                    </tr>
                    <tr><td><div id="ro"> 区域名称</div></td>
                        <td><s:textfield name="name" class="InputStyle" cssStyle="width:250px;"/> *</td>
                    </tr>
                    <tr><td>职能说明</td>
                        <td><s:textarea name="remark" class="TextareaStyle" cssStyle="width:250px;"></s:textarea></td>
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
	</div>
<s:iterator value="#areaName" status="st"> 
   <s:hidden name="ae"  value="%{#areaName.get(#st.index)}" cssClass="InputStyle"/> 
   </s:iterator>
  </body>
</html>
