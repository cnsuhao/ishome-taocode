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
    <title>new</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
	<script type="text/javascript" src="/resources/js/wm/zk/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/resources/slvalidate/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/resources/slvalidate/js/jQuery.validate.message_cn.js"></script>
	<script type="text/javascript" src="/resources/slvalidate/js/jquery.metadata.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#form1").validate({		
			submitHandler:function(form1){				
				form1.submit();
			} 
		});				
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
		function back() {
			document.form1.action = "/WMKZ04/F.go";
			form1.submit();
		}
		function save(){
	  		var rs = document.form1.puk.value;
			if (rs != "") {
				document.form1.action = "/WMKZ04/U.go";
				form1.submit();
			} else {
				document.form1.action = "/WMKZ04/C.go";
				form1.submit();
			}	
		}
		function insert(){
			var rs = document.form1.puk.value;			
			if (rs != "") {
				document.form1.action = "/WMKZ04/U.go";
				form1.submit();
			} else {
				document.form1.action = "/WMKZ04/CX.go";
				form1.submit();			
			}				
		}
		function update(k03,puk){		 
			 document.form2.action = "/WMKZ01/U1.go?k03="+k03+"&puk="+puk;
			 form2.submit();
		}	
	</script>
	<style type="text/css"> 
          th{
          	 text-align: center;
          } 
          input.error { 
			border: 1px solid red;
		  }    
		 label.error{
		 	background:url("./demo/images/unchecked.gif") no-repeat 0px 0px;
		 	padding-left: 16px;
		 	padding-bottom: 2px;
		 	font-weight: bold;
		 	color: #EA5200;
		 } 
		 label.checked {
			background:url("./demo/images/checked.gif") no-repeat 0px 0px;
		 }
    </style> 	
  </head>
  <body>
     <form 
       <c:if test="${not empty parambean.puk}">  	
   action="/WMKZ04/U.go"
   </c:if>
   <c:if test="${empty parambean.puk}">
   action="/WMKZ04/CX.go"
   </c:if> 
      
       method="post" id="form1" name="form1">
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">编辑管理员</h4></td>
    </tr>
    <tr>
      <td height="60">
      <table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">${message}</td>
		    <td align="right">
<!-- 					  <button type="submit" class="btn btn-default btn-sm" onclick="back()">返回</button> -->
					  <button  class="btn btn-default btn-sm" onclick="clearForm()">重置</button>
					  <input type="submit" class="btn btn-primary btn-sm"  id="btnsave" value="确定">
            </td>
            <td width="2%"></td>
	      </tr>
		</table>
	  </td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td>
      <table align="center" width="100%" style="margin:0; padding:0;" border="0">  
          	<input type="hidden" class="form-control input-sm" 
          	id="puk" name="puk" value="${parambean.puk}" >
        <tr height="40">
          <td width="20%" align="right">
          	<label for="f03">登录用户名（超级管理员ID）</label>
          	</td>
          <td width="5"></td>
          <td>${parambean.f03}</td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">登录密码</td>
          <td width="5"></td>
          <td>
             <input type="password"  id="f04" name="f04" value="" 
           class="{required:true,minlength:5} form-control input-sm"  style="width:500px">
<!--               validata-options="validType:'SafeString',msg:'密码不符合安全规则'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>   
          
        <tr height="40">
          <td width="20%" align="right">
          	 <label for="f04">重复密码</label>
          	</td>
          <td width="5"></td>
          <td>
            <input  type="password"   class="required email form-control input-sm"
            id="f04l" name="f04l" value=""  />
<!-- 			 validata-options="validType:'Username',msg:'用户名不合法'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table>
       <input type="hidden" id="eb5" name="eb5" value="${parambean.eb5}"  >     
      </td>
    </tr>
  </table>
     </form>
</body>
</html>
