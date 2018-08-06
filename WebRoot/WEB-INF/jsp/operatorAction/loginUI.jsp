<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
	 <%@ include file="/WEB-INF/jsp/public/common.jspf" %> 
    <META http-equiv=Content-Type CONTENT="text/html; charset=utf-8" />
	<TITLE>登录</TITLE>
	<LINK HREF="${pageContext.request.contextPath}/css/login.css" type=text/css rel=stylesheet />
	<script type="text/javascript">
	
		$(function(){
			document.forms[0].username.focus();
		});
		
		if(window.parent != window){
			window.parent.location.reload(true);
		}
		
		function a() {
			var username = document.getElementsByName("username")[0].value;
			var password = document.getElementsByName("password")[0].value;
			var company = document.getElementsByName("enprNo")[0].value;
			if (username.length<1) {
				alert("用户名为空！");
				return false;
			}
			if (password.length<1) {
				alert("密码为空！");
				return false;
			}
			if (company.length<1) {
				alert("请选择公司！");
				return false;
			} else {
				document.form.submit();
				return true;
			}
		}
	</script>
<BODY LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 CLASS=PageBody >
<s:form action="operator_login" name="form">
    
        <DIV ID="CenterArea" >
            <DIV ID="LogoImg"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/images/login/logo.png" /></DIV>
            <DIV ID="LoginInfo">
                <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3"><!-- 显示错误 -->
							<font color="red"><s:fielderror/></font>
                		</td>
                	</tr>
                    <TR>
                        <TD width=45 CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/images/login/userId.gif" /></TD>
                        <TD><INPUT SIZE="20" CLASS="TextField" TYPE="text" NAME="username" /></TD>
                        <TD ROWSPAN="2" STYLE="padding-left:10px;">
                      <%--   <INPUT TYPE="image" SRC="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" onclick="a();"/> --%>
                          <img src="${pageContext.request.contextPath}/images/login/userLogin_button.gif" onclick="a();" /> 
                        </TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject"><IMG BORDER="0" SRC="${pageContext.request.contextPath}/images/login/password.gif" /></TD>
                        <TD><INPUT SIZE="20" CLASS="TextField" TYPE="password" NAME="password" /></TD>
                    </TR>
                    <TR>
                        <TD CLASS="Subject">公司</TD>
                        <TD>
         					<s:select name="enprNo" cssClass="SelectStyle"
                        	list="#enterpriseList" listKey="enprNo" listValue="name" 
                        	headerKey="" headerValue="===请选择水司===" />
                        </TD>
                    </TR>
                </TABLE>
            </DIV>
            <DIV ID="CopyRight"><A HREF="javascript:void(0)">&copy; 2014 版权所有qbtt</A></DIV>
        </DIV>
 
</s:form>
</BODY>

</HTML>

	
