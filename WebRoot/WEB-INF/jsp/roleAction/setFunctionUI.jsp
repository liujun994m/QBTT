<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <title>权限设置</title>
   <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.treeview.css" />
    <script src="${pageContext.request.contextPath}/script/tree/jquery.treeview.js"></script>
	<script src="${pageContext.request.contextPath}/script/tree/jquery.cookie.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    	<script type="text/javascript">
		$(document).ready(function(){
			/* $("#tree").treeview(); */
			$("#tree").treeview({
				persist: "location",
				collapsed: true,
				unique: true,
			});
			$("#cbSelectAll").click(function(){
				$("input[name='functionIds']").prop("checked",this.checked);		
			});
			$("input[name='functionIds']").click(function(){
				$(this).siblings("ul").find("input").prop("checked",this.checked);
				if(this.checked == true){
					$(this).parents("li").children("input").prop("checked",true);
				}
			});
		});
	
		
</script>
	  <style type="text/css">
	.tree{
	
		padding-top: 2%;
		padding-bottom: 5%;
		margin-left: 20%;
		width: 80%;
		margin-top: 15px;
}
span{
	font-size:16px;
	}
</style>
  </head>

  <body>
   <div id="container">
		<div class="tree">
			<s:form action="role_setFunction" target="right">
			<div>
    <s:hidden name="id"></s:hidden>
    <input type="checkbox" id="cbSelectAll" />
				<label for="cbSelectAll" style="font-size:16px;">全选</label>
				

				<ul id="tree" class="filetree">
					<s:iterator value="#application.topFunctionList">
					<s:if test='#session.operator.hasFunctionByName(name)'> 
								<li>		
									<input type="checkbox" name="functionIds" value="${id }" id="ckb_${id }" <s:property value="%{id in functionIds ? 'checked' : ''}" />>
							 &nbsp;<span>	&nbsp;${name }</span> 
								
									<ul>
									<s:iterator value="children">
									<s:if test='#session.operator.hasFunctionByName(name)'> 
										<li>
											<input type="checkbox" name="functionIds" value="${id }" id="ckb_${id }"
							 				<s:property value="%{id in functionIds ? 'checked' : ''}" />>
										&nbsp; 	<span>&nbsp;${name }</span>
										
											<ul>
											<s:iterator value="children">
											<s:if test='#session.operator.hasFunctionByName(name)'> 
												<li>
													<input type="checkbox" name="functionIds" value="${id }" id="ckb_${id }"
												 	<s:property value="%{id in functionIds ? 'checked' : ''}" />>
												 &nbsp;	<span>&nbsp;${name }</span>
			                                     	</li>
												</s:if>
											</s:iterator>
											</ul>
											</li>
											</s:if>
									</s:iterator>
									</ul>
									</li>
									</s:if>
							</s:iterator>
				</ul>
				</div>
				<div style="padding-left:110px;">
				 <%-- <input type="image" src="${pageContext.request.contextPath}/images/save.png"  style="cursor:hand"/> --%>
				 <input type="submit" style="width:100px;height:35px;" value="保存" />		
				</div>
				
			</s:form>
		</div>
	</div> 
  </body>
</html>
