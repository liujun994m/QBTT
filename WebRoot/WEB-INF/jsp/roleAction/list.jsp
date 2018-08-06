<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>  
    <title>岗位列表</title>
 <%@ include file="/WEB-INF/jsp/public/common.jspf" %>

 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.min.css"/>  
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-ui.min.js"></script>   

   

  </head>
  <script type="text/javascript">
  $(document).ready(function() {
		$('.show11').dialog({
			autoOpen: false, 
			bgiframe: true, 
			width:600,
			height:300,
			modal:true,
			buttons: {
				"返回": function() {
					$(this).dialog("close"); 
				} 
		
			}
		});		
		$('.dialog').click(function(){
			 $("#url11").attr("src","role_addUI.action"); 		
			 $('.show11').dialog({
				 title:"增加角色"
				 
			 });
			$('.show11').dialog('open');		
			return false;
		});
		$('.edit').click(function(){
			var id=$(this).attr("id");
			 $("#url11").attr("src","role_editUI.action?id="+id); 		
			 $('.show11').dialog({
				 title:"修改角色"
			 });
			$('.show11').dialog('open');		
			return false;
		});
		$('.setFunction').click(function(){
			var id=$(this).attr("id");
			 $("#url11").attr("src","role_setFunctionUI.action?id="+id); 	
			 $("#url11").attr("height",1200);
			 $('.show11').dialog({
				 title:"设置角色权限",
				 width:500,
				 height:400
			 });
			$('.show11').dialog('open');		
			return false;
		});
	});

</script>


  <body>
  <div id="container">
		<div class="Title_bar">
			<span>岗位管理>></span>
		</div>
		<table class="center table-roleList" style="width:800px">
			<thead>
				<tr  align="CENTER">		
					<th style="width:200px">岗位名称</th>
					<th style="width:200px">岗位说明</th>
					<th >相关操作</th>
				</tr>
			</thead>
			<tbody>
       <s:iterator value="#roleList" status="st">
       
			<tr align="CENTER" >
				<td><s:property value="name"/>&nbsp;</td>
				<td><s:property value="description"/>&nbsp;</td>
				<td><s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					 
					 <a href="#" class="edit"  id="${id}">修改</a>
					<a href="#" class="setFunction"  id="${id}">设置权限</a> 
					
				</td>
			</tr>
        </s:iterator>
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4" class="cols" id="">
						<!-- <input class="dialog1" type="button" value="新建" /> -->
						<img  src="${pageContext.request.contextPath}/images/createNew.png" style="cursor:hand" class="dialog"/> 
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

	 <div class="show11" title="增加角色" style="display:none">
		<iframe id="url11"  width="100%" height="400" frameborder="no" scrolling="no"  ></iframe>

	</div> 
  </body>
</html>
