<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf"%>
    <link rel="stylesheet" type="text/css" href="css/top.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

  </head>
 
  <body>
	<div>
		<div id="top">	
			<div id="top-logo">
				<h1>智能远程抄表管理系统</h1>
			</div>
			<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="images/top/user.gif" /> 您好，<b><s:property value="#session.operator.username"/></b>&nbsp;&nbsp;
			</div>
			
			<div id="Head1Right_UserSetup" >
            	
            	 <a target="right" href="${pageContext.request.contextPath}/operator_changePasswordUI.action">  
            	 <img border="0" width="13" height="14" src="images/top/user_setup.gif"  style="cursor:hand" /> 修改密码
				</a>
			</div>
			</div>
			
			  <div id="Head1Right_SystemButton">
             <a target="_parent" href="${pageContext.request.contextPath }/operator_logout.action"> 
				<img width="78" height="20" alt="退出系统" src="images/top/logout.gif" />
			</a>
        </div> 
		<%-- 	<div id="top-info">
				<span><a target="_parent" href="${pageContext.request.contextPath }/operator_logout.action"><i class="fa fa-power-off"></i>&nbsp;退出</a></span>
				<span><a target="right" href="${pageContext.request.contextPath}/operator_changePasswordUI.action"><i class="fa fa-edit"></i> &nbsp;<img border="0" width="13" height="14" src="images/top/user_setup.gif" />修改密码</a></span>
				<span> <img border="0" width="13" height="14" src="images/top/user.gif" /> 您好，<b><s:property value="#session.operator.username"/>&nbsp;</b></span>
			</div> --%>
		</div> 
			
	
		
	
		
      
		
	
		<div id="main"></div>
<%-- 		<div class="show11" title="用户信息">
		<iframe src="${pageContext.request.contextPath}/operator_changePasswordUI.action" width="100%" height="500"  frameborder="no" scrolling="yes"></iframe>
	</div> --%>
	</div>
</body>
</html>
