<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";>
<html>
<head>
<title>用户信息</title>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.validate-1.13.1.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/md5.js"></script>
<style type="text/css">
        body{
            background: #F3FCFD;
        }
         #username,.edit{
            font-size: 30px;
            font-weight: bold;
        }
        td{
            width: 900px; 
            height:90px;
        }
        form input{
            height: 40px;
            width: 900px;
            margin: 5px;
            padding-left: 5px;
        }
        form label{
            margin: 5px;
            font-size: 20px;
        }
        .edit_password{
            left: 5%;
            top:5%;
            position: absolute;
        }
        label.error {
            color: red;
            font-size:10px;
        }
        .btn-lg{
            width: 200px;
        } 
    </style>
</head>
 <script type="text/javascript">
 
 
	function a() {
		var password=document.getElementsByName("password")[0].value;
		var password11=hex_md5(password);	
		var opassword=document.getElementsByName("opassword")[0].value;
		var repassword=document.getElementsByName("newpassword")[0];
		var repassword1=document.getElementsByName("confirmpassword")[0];
		if(password11!=opassword){
			alert("密码不正确！");
			return false;
		}
		if(repassword.value!=repassword1.value){
			alert("两次输入密码不一致！");
			return false;
		}
		if(repassword.value.length < 1)
		{
			alert("密码不能为空");
			return false;
		}
		else
		{
			document.form.submit();
		return true; 
		}
		}
	
	
</script> 
<body>
  <div id="container">
		<div class="Title_bar">
			<span>修改密码>></span>
		</div>
  <div class="edit_password" style="margin-left:30%;">
 
        
        <s:form action="operator_changePassword" name="form" target="right">
            <table>
            	<tr>
							<td><s:textfield type="hidden"  name="opassword" value="%{#session.operator.password}"
									cssClass="InputStyle" cssStyle="width:250px;"/></td>
			   </tr>	
                <tr>
                    <td>
                        <label for="password">当前密码</label><br>
                        <s:textfield type="password"  name="password" placeholder="请输入当前密码" cssStyle="width:250px;"/><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="new_password">新密码</label><br>
                        <s:textfield type="password"  name="newpassword" placeholder="请输入新密码" cssStyle="width:250px;"/><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="confirm_password">确认密码</label><br> 
                        <s:textfield type="password"  name="confirmpassword" placeholder="确认密码和新密码一致" cssStyle="width:250px;"/>
                    </td>
                </tr>
                 <tr>
							<td><s:textfield type="hidden" name="id" value="%{#session.operator.id}" 
									cssClass="InputStyle" /> </td>

				</tr> 
                <tr>
                    <td colspan="1">
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/images/save.png" style="cursor:hand" onclick="a();" /> 
                         &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/goBack.png"/></a>
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
     </div>
</body>
</html>
