<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
</head>

<body>   
    <p>程序出现错误：${requestScope.errorMsg}</p>   
    <p>错误原因：${requestScope.reason}</p>   
</body>  
</html>
