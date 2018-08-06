<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
	<style type="text/css">
		#mainFrame img{
			width: 100%;
			height: 90%;
		}
		.mi{
		position: absolute;  
      top: 50%;  
      left: 50%; 
 margin-left: -341px;
  margin-top: -180px;
  	font-size: 20px;
		}
	</style>

  </head>
  
  <body>
  <div class="mi">

  修改密码成功！
  </div>
  </body>
</html>
