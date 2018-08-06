<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd";>
<html>
	<head>
		<title>OA</title>
		<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<script type="text/javascript">
	function a() {
/* try{ 
	  var  browserCfg = {};  
	  var maxsize = 5*1024*1024;
      var ua = window.navigator.userAgent;  
    
      if (ua.indexOf("MSIE")>=1){  
          browserCfg.ie = true;  
      }else if(ua.indexOf("Firefox")>=1){  
          browserCfg.firefox = true;  
      }else if(ua.indexOf("Chrome")>=1){  
          browserCfg.chrome = true;  
      }  
	
	 var filesize = 0;  
     if(browserCfg.firefox || browserCfg.chrome ){  
         filesize = obj.files[0].size;  
     }else if(browserCfg.ie){  
         var obj_img = document.getElementById("tempimg");  
         obj_img.dynsrc=obj.value;  
         filesize = obj_img.fileSize;  
     }else{  
         alert("您的浏览器暂不支持计算上传文件的大小！");  
         return false;  
         }  
     if(filesize==-1){  
         alert("您的浏览器暂不支持计算上传文件的大小！");  
         return false;  
     }else if(filesize>maxsize){  
         alert("请上传小于5M的文件!");  
         return false;  
     }else{  
    		document.form.submit(); 
         return true;  
     } 
    }catch(e){  
    alert(e);  
    }  */

    var obj = document.getElementsByName("upload")[0];
	 if(obj.value.length<1){  
        alert("请先选择上传文件");  
        return false;  
    } 
    
    	var c1=confirm("导入数据时请按顺序存放sheet！");
    	 if(c1==true){
    			 document.form.action="datumLoad_ofileLoad.action";
    			 document.form.target="right";
        		 document.form.submit();
    		
    	    	return true;
    	    }else{
    	    	return false;
    	    }
        
   
	}
	function b(){
		document.form.reset();
		return true;
	}
	function c(){
		document.form.action="datumLoad_checkExcel.action";
		 document.form.target="right";
		 document.form.submit();
		 return true;
	}

</script>
	</head>
	<body>
	<div id="container">
		<div class="Title_bar">
			<span>资料导入>></span>
		</div>
			<div class="des1">
			<div class="operate_describe">
				<h1 style="font-size: 20px;font-weight: bold;">说明</h1>
				<div class="des2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;资料导入前请点击文件检测以检验要导入的excel是否存在问题,新用户导入时要仔细对照数据库文档，excel多余的字段可以不填。<br>
				
		
				</div>
			</div>
			</div>
		<s:form action=""  enctype="multipart/form-data" method="post" name="form">
		<table class="table-edit" style="width:500px;margin:0 auto;">
			
				<tbody>
					<tr>
			
					 <td style="width:250px;;font-weight: bold"><span> 请选择Excel文件</span> </td> 
						<td style="width:400px;">	
								<input type="file"  name="upload" style="width:300px;" value="请选择Excel文件"/>
									<s:fielderror ><s:param>load</s:param></s:fielderror>
							</td>	  		       
						
					</tr>
					
				</tbody>
				<tfoot>
			
					<tr>
						<td colspan="2"> 
						<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/images/lasts.gif" style="cursor:hand" /></a>
						    <img src="${pageContext.request.contextPath}/images/checkf.gif" style="cursor:hand" onclick="c();" />
							<img src="${pageContext.request.contextPath}/images/upload.gif" style="cursor:hand" onclick="a();" /> 
							<img src="${pageContext.request.contextPath}/images/reset.gif" style="cursor:hand" onclick="b();" /> </td>
						</tr>
					</tfoot>
				</table>
			
		</s:form>
	</div>
</body>
</html>
