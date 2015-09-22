<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	$(function (){
		jQuery("#form01").validationEngine('attach',
			{promptPosition : "centerRight", autoPositionUpdate : true});	
	});
	function save(){
	        document.form01.action = "/AQSMS1/C.go";
	        
			form01.submit();
}

  function fanhui() {
		    document.form01.action = "/WMBM09/F.go";
			form01.submit();			
}

function clearForm() {
		var formObj = document.getElementById("form01");

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
	</script>
  </head>
  <body>
     <form id="form01" name="form01"  method="post" action="/AQSMS1/C.go" enctype="multipart/form-data">
  <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;"><h4 style="margin-left:10px;">实名认证</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
          	<td width="33"></td>
		    <td width="594" style="color:red; font-size:12px;">${message}</td>
		    <td width="923" align="right">
					<input type="button" id="edit" class="btn btn-default btn-lg"onclick="clearForm()" value="清空"> 
            <input type="submit" id="edit" class="btn btn-info" value="提交" >					 

            </td>
            <td width="100"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td align="center" valign="top"><table>
		  <tr>
	      </tr>
		  <tr>
		 
		    <td align="left" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
		      
		        <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;真实姓名：</th>
		          <td width="1"></td>
		          <td align="left">
		          <input style="margin-bottom:0; width:90%;" type="text"  placeholder="" 
		          class="validate[required]" id="f04" name="f04" value="${paramBean.f04}">
		          </td>
	            </tr>
	           
		        <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;手机号：</th>
		          <td width="1"></td>
		          <td align="left"><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f03" name="f03" 
		          class="validate[required,custom[phone]]" value="${paramBean.f03}"></td>
	            </tr>
		        <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;证件类型：</th>
		          <td width="1"></td>
		          <td align="left">
				      <select name="f05">
				                       <option value="0">身份证</option>
			         </select>
			   </td>
	            </tr>
		        <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;证件号码：</th>
		          <td width="1"></td>
		          <td align="left">
				  <input style="margin-bottom:0; width:90%;" type="text"   id="f06" name="f06" value="${paramBean.f06}"
				  class="validType[required]">
				  
				  </td>
	            </tr>	
				 <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;QQ号码：</th>
		          <td width="1"></td>
		          <td align="left">
				  <input style="margin-bottom:0; width:90%;" type="text"   id="f16" name="f16" value="${paramBean.f16}"
				   class="validate[required]">
				  
				  </td>
	            </tr>	
				 <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;个人邮箱：</th>
		          <td width="1"></td>
		          <td align="left">
				  <input style="margin-bottom:0; width:90%;" type="text"   id="f17" name="f17" value="${paramBean.f17}"
				  class="validate[required]">
				  
				  </td>
	            </tr>	
				 <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;身份证正面：</th>
		          <td width="1"></td>
		          <td align="left">
				                         <input type="file" id="file" name="file" class="validate[required,]" />
										
				  </td>
	            </tr>	
	             <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;身份证反面：</th>
		          <td width="1"></td>
		          <td align="left">
				                         
										  <input type="file" id="file1"   name="file1" class="validate[required,]"  />
										   

				  </td>
	            </tr>	
	             <tr height="40">
		          <th height="50" align="left" valign="middle"><span style="color:#F00000;">*</span>&nbsp;最新照片：</th>
		          <td width="1"></td>
		          <td align="left">
				                           <input type="file" id="file2" name="file2" class="validate[required,]"  />

				  </td>
	            </tr>	
				
				
		        <tr height="40">
		          <td height="30" align="left" valign="middle">&nbsp;</td>
		          <td height="30"></td>
		          <td height="30" align="left">&nbsp;</td>
	            </tr>
	          
	        </table></td>
	       
	      </tr>
	      
	    </table></td>
	</tr>
	
  </table>
  </form>
  
</body>
</html>
