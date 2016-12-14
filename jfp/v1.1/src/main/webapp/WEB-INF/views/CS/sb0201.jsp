<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配置</title>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
<!-- 	<script src="/resources/js/bootstrap.js"></script> -->
<script type="text/javascript">
	function save(){		
			var  rs=document.formsb01.puk.value;
			
			 if(rs!=""){
 				document.formsb01.action="/CSSB02/U.go";
				formsb01.submit();	
             }else{
				document.formsb01.action="/CSSB02/C.go";
 				formsb01.submit(); 				
			}		
	}
        function back(){
			document.formsb01.action="/CSSB02/F.go";
			formsb01.submit();
		}
		
		
		function clearForm(){	
		
			var formObj = document.getElementById("formsb01"); 
		
			if(formObj==undefined){
				return;
			}
			for(var i=0; i<formObj.elements.length; i++){
				if(formObj.elements[i].type == "text"){
					formObj.elements[i].value = ""; 
				}else if(formObj.elements[i].type == "password"){
					formObj.elements[i].value = "";  
				}else if(formObj.elements[i].type == "radio"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "checkbox"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "file"){
					var file = formObj.elements[i]; 
					if (file.outerHTML){
						file.outerHTML = file.outerHTML;
					}else{
						 file.value = ""; 
					}
				}else if(formObj.elements[i].type == "textarea"){
					formObj.elements[i].value = "";
				}else if(formObj.elements[i].type == "select"){
					formObj.elements[i].options[0].selected = true;
				}else if(formObj.elements[i].type == "hidden"){
					formObj.elements[i].value = "";
				}
			}
		}	
		
		</script>
</head>
<body>
<form action="" method="get" name="formsb01" id="formsb01">
<input type="hidden"  placeholder="puk" id="puk" name="puk"   class="requried" value="${parambean1.puk}"/>
关键字：<input type="text"  placeholder="fb1" id="fb1" name="fb1" size='25'  class="requried" value="${parambean1.fb1}"  /><br><br><br>
内容：<input type="text"  placeholder="fb2" id="fb2" name="fb2" size='25'  class="requried" value="${parambean1.fb2}" /><br><br><br>
<textarea name="textarea" cols="30" rows="10"  id="fb3" name="fb3" value="${parambean1.fb3}">备注说明：</textarea>
<table id="itable" border="1" ondblclick="get(event);" >
</table><br><br><br>
<div class="a" alin='right'>
  <blockquote>
    <p>
      <button type="button" class="button" onclick="back()">返回</button>
      <button type="button" class="button"  id="fbtn" onclick="clearForm()">清空</button>
     <button type="button"  onclick="save()">保存</button>
      
    </p>
  </blockquote>
</div>
</form>
</body>
</html>
