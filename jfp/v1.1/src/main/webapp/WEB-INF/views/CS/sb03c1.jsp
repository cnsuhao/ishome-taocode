<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  	 <base href="<%=basePath%>">
  	 
    <title>画面菜单信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen"> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	 <script type="text/javascript" src="/resources/js/jquery.validate.js"></script>
	 <script type="text/javascript" src="/resources/js/validatorMessages.js"></script>
	  <script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
		/*$(function(){
		alert("validate");
			 $('#formSb03').validate({
			 	rules:{
			 		f01:{
			 			required:true,
			 			minlength:4
			 		},
			 		k03:{
			 			required:true,
			 			minlength:6
			 		},
			 		f03:{
			 			required:true,
			 			minlength:4
			 		},
			 		f04:{
			 			required:true,
			 			minlength:6
			 		}
			 	},
			 	
			 	messages:{
			 		f01:{
			 			required:"必填",
			 			minlength:"最小长度4",
			 			document.a.b.focus()
			 		},
			 		k03:{
			 			required:"必填",
			 			minlength:"最小长度6"
			 		},
			 		f03:{
			 			required:"必填",
			 			minlength:"最小长度4"
			 		},
			 		f04:{
			 			required:"必填",
			 			minlength:"最小长度6"
			 		}
			 	},
			 	submitHandler:function(formSb03){ 
			 		formSb03.submit();
			 	 }
			 });
		});*/
		$(function(){
// 			alert("test");
			$("#f01").blur(function(){
// 				alert("f01blur");
// 				var name=$(this).val();
// 					alert(name);
				if(""==$(this).val()){
					alert("非空");
					$(this).focus();
				}	
			});
			$('#k03').blur(function(){
				if(""==$(this).val()){
					alert("k03非空");
					$(this).focus();
				}
			});
			
		});		
		function save(){		
			var  rs=document.formSb03.puk.value;
// 			alert(rs);	
			 if(rs!=""){
// 	 			alert("不为空");	
				document.formSb03.action="/CSSB03/U.go";
				formSb03.submit();	
			}else{
// 				alert("error");
				document.formSb03.action="/CSSB03/C.go";
 				formSb03.submit(); 				
			}		
 		}
		function back(){
			document.formSb03.action="/CSSB03/F.go";
			formSb03.submit();
		}	
		function clearForm(){	
			var formObj = document.getElementById("formSb03"); 
			alert(formObj);
			if(formObj==undefined){
//				alert("undefined");
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
// 		function check(){
// 			if(document.formSb03.bbb.value.length>50){
// 				alert("不能超过50个字符!");
// 				document.formSb03.bbb.focus();
// 				return false;
// 			}
// 		}
	</script>
  </head> 
  <body  style="margin-left:50px; margin-top:80px;">

     <form action="" method="post" name="formSb03" id="formSb03"  onsubmit="return check()">
    	<fieldset>	 		
    		<input type="hidden" id="puk" name="puk" class="requriedId" value="${paramBean1.puk}">
    		<p>	<input type="text"   placeholder="f01" id="f01" name="f01" class="requried" value="${paramBean1.f01}"> &nbsp;&nbsp;
&nbsp;    	<input type="text"  placeholder="k03" id="k03" name="k03" class="requriedName" value="${paramBean1.k03}"> 
    		</p>   		
    		<label class="checkbox" >
    		<input type="checkbox" align="right" id="k01">显示为菜单
    	</label>
    	<select>
    	    <option id="k02">父菜单Id&nbsp;&nbsp;</option>
         </select>	<br><br>
         <input type="text"  placeholder="sysId" id="f03" name="f03" value="${paramBean1.f03}" style=" width:350px; height: 26px">
         <br><br>
         <input type="text"  placeholder="sysId"  id="f04"  name="f04"  value="${paramBean1.f04}" style=" width:180px; height: 26px">
         <select id="f05" >
         	<option  >一览</option>
         </select><br><br>
         <textarea rows="8" style="width: 323" id="bbb">        	
        </textarea><br><br>
        <p style="margin-left:180px;">
        <button type="button" class="button" onclick="back()">返回</button>&nbsp;
        <button type="button"   id="fbtn" onclick="clearForm()">清空</button>&nbsp;&nbsp;
<!--         <button type="submit" class="submit" id="save" name="save">保存</button> 	 -->
		 <button type="button" id="fn" id="save" onclick="save()" value="">保存</button>
		</p> 
    	</fieldset>
    </form>
  </body>
</html>
