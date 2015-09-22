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
			var  rs=document.formsb01.puk.value;
				
			 if(rs!=""){
 			 	document.formsb01.action="/CSSB01/U.go";
				formsb01.submit();	
             }else{
				document.formsb01.action="/CSSB01/C.go";
 				formsb01.submit(); 				
			}		

 		}

		function back(){
			document.formsb01.action="/CSSB01/F.go";
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
 <br><br>&nbsp;&nbsp;<input type="hidden"  placeholder="puk" id="puk" name="puk" size='25'  class="requried" value="${parambean1.puk}"/>
 <input type="text"  placeholder="f02" id="f02" name="f02" size='25'  class="requried" value="${parambean1.puk}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <input type ="text" placeholder="k01" id="k01" name="k01" size='25'  class="requried" value="${parambean1.k01}"/><br><br>
   &nbsp;&nbsp;<input type ="text" placeholder="k02" id="k02" name="k02" size='25'  class="requried" value="${parambean1.k02}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <input " type ="text" placeholder="k03" id="k03" name="k03" size='25'  class="requried"value="${parambean1.k03}"/><br><br><br>
   &nbsp;&nbsp;<input type = "text" placeholder="f01" id="f01" name="f01" size='25'  class="requried"value="${parambean1.f01}"/>
 <table id="itable" border="1" ondblclick="get(event);" >
    </table><br><br><br>
    
<div class="a" alin='right'>
  <blockquote>
    <p>
      <button type="button" class="button" onclick="back()">返回</button>
      <button type="button"   id="fbtn" onclick="clearForm()">清空</button>
      <button type="button" id="fn" id="save" onclick="save()">保存</button>
      
    </p>
  </blockquote>
</div>
</form>
</body>
</html>
