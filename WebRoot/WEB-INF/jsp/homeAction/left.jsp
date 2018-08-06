<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="css/toolbar.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
	<script type="text/javascript">
		$(document).ready(function() {
	
		

			/*
			$(".21").click(function() {
				document.device_list.action="device_list.action";
				document.device_list.target="left";
				document.device_list.submit();
				document.device_right.action="device_right.action";
				document.device_right.target="right";
				document.device_right.submit();
			});
 	 		$(".23").click(function() {
	 			document.templeft.action="tempCQ_list.action";
				document.templeft.target="left";
				document.templeft.submit();
				document.tempright.action="tempCQ_right.action";
				document.tempright.target="right";
				document.tempright.submit();
			}); 
	 		$(".26").click(function() {
	 			document.waterleft.action="waterExport_list.action";
				document.waterleft.target="left";
				document.waterleft.submit();
				document.waterright.action="waterExport_right.action";
				document.waterright.target="right";
				document.waterright.submit();
			});  */
	 		
	 		/*导航栏折叠后，项目所对应的内容显示，主导航栏折叠*/

			/*点击导航栏中项目时，对应项目被激活，背景颜色改变*/
			$("li a").click(function(){
				$("li a").removeClass("active");
				$(this).addClass("active");
			});
			/*隐藏显示二级菜单，且相应一级菜单图标变化,
			显示时，每次只显示一个二级菜单，即当前的菜单*/
			$(".show").click(function(){
				$(this).next().slideToggle('slow')//展开隐藏该一级菜单下的二级菜单
				.parent().siblings().children('.show')
				.children('i').removeClass('fa-chevron-circle-down')//将其他一级菜单的图标文件变成向左的
				.parent('.show').next().slideUp("slow");//隐藏其他耳机菜单

				$(this).children('i').toggleClass('fa-chevron-circle-down');//一级菜单图标切换
			}); 

			/*点击 导航菜单 ，导航栏折叠或显示，且导航栏显示时，导航栏侧除导航栏以外的东西均隐藏*/ 
			$(".menu").click(function() {
				$(".sdmenu").slideToggle(600);
				$(".tempCQ_listshow").hide();
				$(".deviceManage_listshow").hide();

			});
		});
</script>
<style>
</style>
</head>
<body>
	<div>
		<div class="menu"><i class="fa fa-navicon"></i>&nbsp;&nbsp;&nbsp;&nbsp;导航菜单</div>
		<div id="my-menu" class="sdmenu">
		 <s:iterator value="#application.topFunctionList" status="st">
		
			     <s:if test='#session.operator.hasFunctionByName(name)'>     
			      <div class="collapsed">          
             <span class="show"><i class="fa fa-chevron-circle-right"></i>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="name" /></span>
                    	
                    	<ul  class="show_detail">
                    		<s:bean name="hust.ioic.oa.utils.MyComparator" id="myComparator"></s:bean>	
                    	  <s:sort comparator="myComparator" source="children"> 
                    	<s:iterator status="stc">
				        <s:if test='#session.operator.hasFunctionByName(name)'>
                    		
                           
                        <s:if test='id=="21"||id=="23"||id=="26"'>    
                    <li class="${st.index }-${stc.index}">  
        <a  target="left" href="${pageContext.request.contextPath}${url }.action" class="${id}"><i class="fa  fa-circle-o"></i>&nbsp;<s:property value="name"/></a>
                    </li>
                    </s:if>
                     <s:elseif test='id!="21"&&id!="23"&&id!="26"'>       <li class="${st.index }-${stc.index}">  
        <a target="right" href="${pageContext.request.contextPath}${url }.action" ><i class="fa  fa-circle-o"></i>&nbsp;<s:property value="name"/></a>
                    </li></s:elseif> 
                        </s:if>
			            </s:iterator>  
			             </s:sort>        
                	    </ul>
        
                    </div>
             	</s:if>
             	
			</s:iterator>
	
		</div>
	</div>

	<%-- <s:form name="device_list"></s:form>
	<s:form name="device_right"></s:form> --%>
	<%-- <s:form name="templeft"></s:form>
	<s:form name="tempright"></s:form> 
	<s:form name="waterleft"></s:form>
	<s:form name="waterright"></s:form>--%>
	
</body>
</html>
