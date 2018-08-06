<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	
<html>

<head>
	<title>增加设备类型信息</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>
 <script type="text/javascript">
 
 
	function a() {
		var id=document.getElementsByName("id")[0].value;
		var showNum=document.getElementsByName("showNum")[0].value;
		var showIndex=document.getElementsByName("showIndex")[0].value;
		if(isNaN(showNum)==true){
			alert("请输入只包含数字的数显位数！");
			return false;
		}
		if(isNaN(showIndex)==true){
			alert("请输入只包含数字的序列号！");
			return false;
		} 
	
		if(id.length<1){
			document.form.action="deviceType_add.action";
			document.form.submit();
			return true; 
		}
		else{
			document.form.action="deviceType_edit.action";
			document.form.submit();
			return true; 
		}
		
		}
	
	
</script> 

<body>
	<div id="container">
	<!-- 	<div class="Title_bar">
			<span>设备类型信息</span>
		</div>  -->
		<table class="table-edit">
			<s:form action="" name="form" target="right">
				 <s:hidden name="id"/>
				<tbody>
					  <tr>
                    	<td width="100">设备名称</td>
                        <td><s:textfield name="name" cssClass="InputStyle"/>*</td>
                    </tr>
                    <tr>
                    	<td>数显位数</td>
                        <td><s:textfield name="showNum" cssClass="InputStyle"/>*</td>
                    </tr>
					    <tr><td>序列号</td>
                        <td><s:textfield name="showIndex" cssClass="InputStyle"/>*</td>
                    </tr>
                    
                    <tr><td>生产厂家</td>
                        <td><s:textfield name="manufacture" cssClass="InputStyle"/>*</td>
                    </tr>
					<tr><td>规格</td>
                        <td><s:textfield name="specification" cssClass="InputStyle"/>*</td>
                    </tr>
                    
                    <tr><td>厂家地址</td>
                        <td><s:textfield name="address" cssClass="InputStyle"/>*</td>
                    </tr>
                    <tr><td>显示配置</td>
                        <td><s:textfield name="showConfig" cssClass="InputStyle"/>*</td>
                    </tr>
                  <%--   <tr><td>显示图片</td>
                        <td><s:textfield name="image" cssClass="InputStyle"/>*</td>
                    </tr> --%>
                   <tr><td>备注</td>
                        <td><s:textfield name="remark" cssClass="InputStyle"/>*</td>
                    </tr>

				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<!-- <input type="submit" value="保存"> -->
							<img src="${pageContext.request.contextPath}/images/save.png" style="cursor:hand" onclick="a();" /> 
						</td>
					</tr>
				</tfoot>
			</s:form>
		</table>
	</div>
</body>
</html>
	
