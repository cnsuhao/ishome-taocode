<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
	<script src="/resources/js/bootstrap.js"></script>  
	 <script type="text/javascript">
	function save(){		
	alert("ko");
			var  rs=document.formsb01.puk.value;
				alert("ok");
			 
				document.formsb01.action="/CSSP01/C.go";
 				formsb01.submit(); 				
					

 		}

		function back(){
			document.formsb01.action="/CSSP01/F.go";
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
  <input type="hidden"   placeholder="puk" id="puk" name="puk" value="${parambean1.puk}">
  <input type="text"   placeholder="f01" id="f01" name="f01" value="${parambean1.f01}">
  <input type="text"  placeholder="f02" id="f02" name="f02" value="${parambean1.f02}"><br><br>
  <input type="text"   placeholder="f03" id="f03" name="f03" value="${parambean1.f03}">
  <input type="text"   placeholder="f04" id="f04" name="f04" value="${parambean1.f04}">
  <input type="text"   placeholder="f05" id="f05" name="f05" value="${parambean1.f05}"><br><br>
  <input type="text"   placeholder="f06" id="f06" name="f06" value="${parambean1.f06}"><br><br>
  <input type="text"   placeholder="f07" id="f07" name="f07" value="${parambean1.f07}"><br><br>
  <input type="text"  placeholder="f08" id="f08" name="f08"  value="${parambean1.f08}">
  <input type="text"   placeholder="f09" id="f09" name="f09" value="${parambean1.f09}"><br><br><br>

   <button type="button" class="button" onclick="back()">返回</button>
      <button type="button"   id="fbtn" onclick="clearForm()">清空</button>
      <button type="button" id="fn" id="save" onclick="save()">保存</button>
      </form>
 </body>
</html>
