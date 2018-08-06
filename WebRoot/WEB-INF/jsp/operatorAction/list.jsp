<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.min.css">
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-ui.min.js"></script>

	<script type="text/javascript">
	  $(document).ready(function() {
		$('.show11').dialog({
			autoOpen: false, 
			bgiframe: true, 
			width:700,
			height:500,
			modal:true,
			buttons: {
				"返回": function() {
				/* 	window.parent.frames.right.location.reload(); */
					$(this).dialog("close"); 
				} 
			}
		});		

		$('.dialog').click(function(){
			 $("#url11").attr("src","operator_addUI.action"); 		
			 $('.show11').dialog({
				 title:"增加操作员"
			 });
			$('.show11').dialog('open');		
			return false;
		});
		$('.edit').click(function(){
			var id=$(this).attr("id");
			 $("#url11").attr("src","operator_editUI.action?id="+id); 		
			 $('.show11').dialog({
				 title:"修改操作员"
			 });
			$('.show11').dialog('open');		
			return false;
		});
		$('.setArea').click(function(){
			var id=$(this).attr("id");
			 $("#url11").attr("src","operator_setAreaUI.action?id="+id); 		
			 $('.show11').dialog({
				 title:"设置操作员区域",
				 width:500,
				 height:500
			 });
			$('.show11').dialog('open');		
			return false;
		});
	});
	</script>
    

  </head>
  
  <body>
   <div id="container">
		<div class="Title_bar">
			<span>操作员管理</span>
		</div>
		<table class="center table-roleList" style="width:1000px">

			<thead>
				<tr>
					
					<th style="width:100px">登录名</th>
					<th style="width:100px">职工号</th>
					<th style="width:100px">性别</th>
					<th style="width:100px">岗位</th>
					<th style="width:200px">备注</th>
					<th>相关操作</th>
				</tr>
			</thead>
			<tbody>
		 <s:iterator value="#operatorList">
            <tr align=center  >
                <td><s:property value="username"/>&nbsp;</td>
                <td><s:property value="operatorNo"/>&nbsp;</td>
                
                <td><s:property value="gender"/>&nbsp;</td>
                <td>
                	<s:iterator value="role">
                		<s:property value="name"/>
                	</s:iterator>&nbsp;
                </td>
                <td><s:property value="gropuID"/>&nbsp;</td>
                <td><s:a action="operator_delete?id=%{id}" onClick="return delConfirm()">删除</s:a>
                     <a href="#" class="edit"  id="${id}">修改</a>
					<s:a action="operator_initPassword?id=%{id}" onClick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
				 <a href="#" class="setArea"  id="${id}">设置区域</a>
                </td>
            </tr>
        </s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="18">
						<img  src="${pageContext.request.contextPath}/images/createNew.png" style="cursor:hand" class="dialog"/>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
 <div class="show11" title="增加操作员" style="display:none">
		<iframe id="url11"  width="100%" height="550" frameborder="no" scrolling="no"  ></iframe>
	</div> 
  </body>
</html>
