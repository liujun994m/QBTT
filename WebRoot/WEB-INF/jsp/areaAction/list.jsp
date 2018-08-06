<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<title>My JSP 'list.jsp' starting page</title>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jquery.treeview.css" />
<script
	src="${pageContext.request.contextPath}/script/tree/jquery.treeview.js"></script>
<script
	src="${pageContext.request.contextPath}/script/tree/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery-ui.min.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function() {
		$("#tree").treeview();
	/* 	var x=$(window).width()*0.8;
		var y=$(window).height()*0.9; */
		$('.show11').dialog({
			autoOpen: false, 
			bgiframe: true, 
			width:650,
			height:400,
			modal:true,
			buttons: {
				"返回": function() {
				/* 	window.parent.frames.main.location.reload(); */
					$(this).dialog("close"); 
				} 
			}
		});		
		$('.dialog').click(function(){
			 $("#url11").attr("src","area_addUI.action"); 		
			 $('.show11').dialog({
				 title:"增加区域"
			 });
			$('.show11').dialog('open');		
			return false;
		});
		$('.edit').click(function(){
			var id=$(this).attr("id");
			 $("#url11").attr("src","area_editUI.action?id="+id); 		
			 $('.show11').dialog({
				 title:"修改区域"
			 });
			$('.show11').dialog('open');		
			return false;
		});
	});

</script>
<style type="text/css">
body {
	
}
</style>
<body>
	<div id="container">
		<div class="Title_bar">
			<span>区域管理>></span>
		</div>
		<div class="tree border1">
			<ul id="tree" class="tree3">

				<s:iterator value="#areaList">
					<s:if test="#session.operator.hasAreaByName(name)">

						<li><label for="ckb_${id }" class="o">${name } </label>
						 <s:a action="area_delete?id=%{id}&parentId=%{id}" onClick="return window.confirm('这将删除所有的下级区域，您确定要删除吗？')" class="o1">删除</s:a> 
					   	<a href="#" class="edit" id="${id}">修改</a>


						<ul>
							<s:iterator value="children">
								<s:if test="#session.operator.hasAreaByName(name)">

									<li><label for="ckb_${id }" class="o3">${name } </label> <s:a action="area_delete?id=%{id}" onClick="return window.confirm('您确定要删除吗？')" class="o1">删除</s:a>
										<a href="#" class="edit"  id="${id}">修改</a>


									<ul>
										<s:iterator value="children">
											<s:if test="#session.operator.hasAreaByName(name)">
												<li><label for="ckb_${id }" class="o4">${name } </label> 
												<s:a action="area_delete?id=%{id}" onClick="return window.confirm('您确定要删除吗？')" class="o1">删除</s:a>
													<a href="#" class="edit"  id="${id}">修改</a>
												
												<ul>
													<s:iterator value="children">
														<s:if test="#session.operator.hasAreaByName(name)">
															<li><label for="ckb_${id }" class="o5">${name } </label> 
															<s:a action="area_delete?id=%{id}" onClick="return window.confirm('您确定要删除吗？')" class="o1">删除</s:a>
																<a href="#" class="edit"  id="${id}">修改</a>
															</li>

														</s:if>
													</s:iterator>
												</ul>
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
	</div>


	<div title="区域编辑" style="text-align: center;">
		<img src="${pageContext.request.contextPath}/images/createNew.png" style="cursor:hand" class="dialog" />
	</div>
		 <div class="show11" title="增加区域" style="display:none">
		<iframe id="url11"  width="100%" height="400" frameborder="no" scrolling="no"  ></iframe>
	</div> 

</body>
</html>
