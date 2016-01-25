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
<title>添加会员</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	  
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
/* 	 $(function() {
	  $('#form01').validationEngine();
	 jQuery("#form01").validationEngine('attach',
			{promptPosition : "centerRight", autoPositionUpdate : true});							
	 $.fn.xx = function(){
			 jQuery("#form01").validationEngine('attach',
			{promptPosition : "centerRight", autoPositionUpdate : true});				 
			 };		 

      });	 */

	 
	function checkUserName(){
	    var val=$("#f01").val();
	 	if(val==''){
	 		return ;
	 	}
	    $.ajax({
		    url: "/CSSR01/CheckUserName.go",
		    type: 'POST',
		    async:false,
		    data:{'username':val},
		    dataType:'text',
		    timeout: 10000,	   
		    success: function(str){
		    	if(str=="success"){
		    		document.getElementById('f01_tips').innerHTML='账号可以使用';	  	    	
	
		       }else{
		    		document.getElementById('f01_tips').innerHTML='账号已经重复!请修改';	  	    	
		       }
		    }
		});    
	}
	
	
	function checkForm(){
		 var val=$("#f01").val();
		 	if(val==''){
	    		document.getElementById('f01_tips').innerHTML='账号不能为空';	  	    	
		 		return false;
		 	}
		
		  var i=0;
		  $.ajax({
			    url: "/CSSR01/CheckUserName.go",
			    type: 'POST',
			    async:false,
			    data:{'username':val},
			    dataType:'text',
			    timeout: 10000,	   
			    success: function(str){
			    	if(str=="success"){			    	
			       }else{
			    		document.getElementById('f01_tips').innerHTML='账号已经重复!请修改';	  	    	
			         i++;
			       }
			    }
			}); 
		  if(i>0){
			  return false;
		  }
		  
		  var pa = document.getElementById('f02').value;
		  var par = document.getElementById('f02r').value;
		  var cname = document.getElementById('f04').value;
		  if(pa==''||par==''){
	  		document.getElementById('f02_tips').innerHTML='密码不能为空';
	  		return false;
		  }
		 if(pa!=par){
		  	document.getElementById('f02_tips').innerHTML='前后密码不一致';	  
	  		return false;
	
		 }
		 if(cname==''){
			  	document.getElementById('f04_tips').innerHTML='客户名不能为空';
		  		return false;
		 }
		  
		  
		  return true;
		  
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
	
	 function hyChanged(value){
    	var strs=value.split("|");
    	document.getElementById("k01").value = strs[0];
    	document.getElementById("k02").value = strs[1]; 
    }
	
	</script>
  </head>
  <body>
   <form id="form01" name="form01" method="post" action="/WMBM09/C.go" onsubmit="return checkForm()">
  
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">添加会员</h4></td>
	</tr>
	<tr height="50" >
      <td height="60" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
          	<td width="10"></td>
		    <td width="593" bgcolor="#FFFFFF" style="color:red; font-size:12px;">${message}</td>
		    <td width="919" align="right">
<!--  <input type="button" class="btn btn-default btn-sm" value="清空" onclick="parent.toTargetForm('','M09/C.go')" /> -->
<!--  <input type="button" class="btn btn-default btn-sm" value="提交" onclick="parent.toTargetForm('/WMBM09/C.go','/WMBM09/C.go')" /> -->
					<input type="button" id="edit" class="btn btn-default btn-lg"onclick="clearForm()" value="清空">
            <input type="submit" id="edit" class="btn btn-info" value="提交" >					 
            </td>
            <td width="100"></td>
	      </tr>
	  </table></td>
    </tr>
	<tr >
		<td align="center" valign="top" bgcolor="#FFFFFF">
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
	      </tr>
		  <tr>
		    <td align="left" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
		        <tr height="40">
		          <th width="120" height="50" align="right" valign="middle"><span style="color:#F00000;">*</span>&nbsp;系统登录帐号</th>
		          <td width="10"></td>
		          <td align="left">
		          <input style="margin-bottom:0; width:60%;" class="validate[required]" maxlength="20"
		          type="text" id="f01" name="f01" onblur="checkUserName()" value="${parambean1.f03}"><span id="f01_tips" style="background:red"></span>
		          </td>
	            </tr>
	            
		        <tr height="40">
		          <th width="120" height="50" align="right" valign="middle"><span style="color:#F00000;">*</span>&nbsp;登录密码</th>
		          <td width="10"></td>
		          <td align="left"><input style="margin-bottom:0; width:60%;" maxlength="16" class="validate[required]"  
		          type="password" id="f02" name="f02" value="${parambean1.f04}"><span id="f02_tips" style="background:red"></span></td>
	            </tr>
		        <tr height="40">
		          <th width="120" height="50" align="right" valign="middle"><span style="color:#F00000;">*</span>&nbsp;重复密码</th>
		          <td width="10"></td>
		          <td align="left"><input style="margin-bottom:0; width:60%;" maxlength="16" type="password" class="validate[required,equals[f02]]"  id="f02r" name="f02r" value="${parambean1.f04}">
		         
		          </td>
	            </tr>
		        <tr height="40">
		          <th width="120" height="50" align="right" valign="middle"><span style="color:#F00000;">*</span>&nbsp;会员名称</th>
		          <td width="10"></td>
		          <td align="left"><input style="margin-bottom:0; width:60%;" type="text" id="f04" class="validate[required]" name="f04" value="${parambean2.f04}">
		           <span id="f04_tips" style="background:red"></span>
		          </td>
	            </tr>
		        <tr height="40">
		          <th width="120" height="50" align="right" valign="middle"><span style="color:#F00000;">*</span>&nbsp;用户类型</th>
		          <td width="10"></td>
		          <td align="left"><select onchange="hyChanged(this.value)">
		            <c:forEach var="wmuip1dbo" items="${list1}">
			             <c:choose>
	                  		<c:when test="${wmuip1dbo.f03==0}">
				              <option value="${wmuip1dbo.puk}|${wmuip1dbo.f02}">${wmuip1dbo.f01}</option>
	                  		</c:when>
	                  	 </c:choose>
		              </c:forEach>
		            </select></td>
	            </tr>		 
				<input  type="hidden"  name="k01" id="k01" value="DFB_QT_VIP_GROUP" />       
				<input  type="hidden"  name="k02" id="k02"  value="DFB_QT_VIP_0" />
		        <tr height="40">
		          <td height="40" align="left" valign="middle">&nbsp;</td>
		          <td></td>
		          <td align="left">&nbsp;</td>
	            </tr>
	  
	        </table></td>
	      </tr>
	    </table></td>
	</tr>
  </table>
     </form>
</body>
</html>
