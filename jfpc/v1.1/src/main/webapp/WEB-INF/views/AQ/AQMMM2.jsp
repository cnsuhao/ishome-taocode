<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>安全中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
	<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	      
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
	<script type="text/javascript" src="/resources/js/wm/zk/jquery-1.8.3.js"></script>
	<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
	<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
	$(function() {	
		jQuery("#form1").validationEngine('attach',
		{promptPosition : "centerRight", autoPositionUpdate : true});			  
	});	
		function clearForm() {
			var formObj = document.getElementById("form1");
				if (formObj == undefined) {
				return;
			}
			for ( var i = 0; i < formObj.elements.length; i++) {
				if (formObj.elements[i].type == "text") {
					formObj.elements[i].value = "";
				} else if (formObj.elements[i].type == "password") {
					formObj.elements[i].value = "";
				} else if (formObj.elements[i].type == "radio") {
					formObj.elements[i].checked = false;
				} else if (formObj.elements[i].type == "checkbox") {
					formObj.elements[i].checked = false;
				} else if (formObj.elements[i].type == "file") {
					var file = formObj.elements[i];
					if (file.outerHTML) {
						file.outerHTML = file.outerHTML;
					} else {
						file.value = "";
					}
				} else if (formObj.elements[i].type == "textarea") {
					formObj.elements[i].value = "";
				} else if (formObj.elements[i].type == "select") {
					formObj.elements[i].options[0].selected = true;
				} else if (formObj.elements[i].type == "hidden") {
					formObj.elements[i].value = "";
				}
			}
		}
		function back(){
			document.form1.action = "/WMKZ01/F.go";
			form1.submit();
		}		
// 		function update(){
// 			document.form1.action = "";
// 			form1.submit();
// 		}
	</script>
  </head>
  <body>
   <form  action="/AQMM/U.go" method="post" id="form1" name="form1" >
 	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
      <td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;"><strong>密码修改</strong></h4></td>
    </tr>
    <tr>
      <td height="60" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;"></td>
		    <td align="right">
					  <button  class="btn btn-default btn-sm" onclick="back()">返回</button>
					  <button  class="btn btn-default btn-sm" onclick="clearForm()">重置</button>
					  <input type="submit" class="btn btn-primary btn-sm" id="btnsave" value="保存" >
            </td>
            <td width="2%"></td>
	      </tr>
	  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td bgcolor="#FFFFFF">  
      ${message}
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;" >  
         <tr height="40">
          <td width="20%" align="right">原密码</td>
          <td width="5"></td>
          <td height="50">
          	<input type="text" class="form-control input-sm " 
          	id="eb1" name="eb1" value="${parambean1.f04}" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr> 
         <tr height="40">
          <td width="20%" align="right">新密码</td>
          <td width="5"></td>
          <td height="50">
          	<input type="text" class="form-control input-sm validate[custom[onlyLetterNumber]]" 
          	id="f04" name="f04" value="" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr> 
         <tr height="40">
          <td width="20%" align="right">重复新密码</td>
          <td width="5"></td>
          <td height="50">
          	<input type="text" class="form-control input-sm validate[custom[onlyLetterNumber]" 
          	id="eb2" name="eb2" value="" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr> 
      </table>     
      </td>
    </tr>
    
    </table> 
</form>  
 <script>
		  /* $('#form1').checkForm();
		   $("#btnsave").click(function(){
		  $('#form1').submit();
		});*/
</script>
</body>
</html>
