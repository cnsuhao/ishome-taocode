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
    <title>黑名单</title>
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
			document.form1.action = "/WMSH01/F.go";
			form1.submit();
		}
		function save(){
				document.form1.action = "/WMSH01/C.go";
				form1.submit();
		}
		function insert(){
			var rs = document.form1.puk.value;			
			if (rs != "") {
				document.form1.action = "/WMSH01/U.go";
				form1.submit();
			} else {
				document.form1.action = "/WMSH01/CX.go";
				form1.submit();			
			}				
		}
		function update(k03,puk){		 
			 document.form2.action = "/WMSH01/U1.go?k03="+k03+"&puk="+puk;
			 form2.submit();
		}	
	</script>
  </head>
  <body>
     <form   method="post" id="form1" name="form1" action="/WMSH01/C.go">
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">黑名单创建编辑</h4></td>
    </tr>
    <tr>
      <td height="60">
      <table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">
		   </td>
		    <td align="right">
					  <input type="button" class="btn btn-default btn-sm" onclick="back()" value="返回">
					  <button  class="btn btn-default btn-sm" onclick="clearForm()">重置</button>
					  <input type="submit" class="btn btn-primary btn-sm"  id="btnsave" value="确定">
            </td>
            <td width="2%"></td>
	      </tr>
		</table>
	  </td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
    	<input type="hidden" class="form-control input-sm" 	id="puk" name="puk" value="${parambean1.puk}" >
    	<td>
    	<table align="center" width="100%" style="margin:0; padding:0;" border="0" >     
        <tr height="40">
        <!-- 联合主键 -->
          <td width="20%" align="right">业务系统ID</td>
          <td width="5"></td>
          <td>
             <input type="text"   class="form-control input-sm required email" 
             id="k01" name="k01" value="${parambean1.k01}">
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
        <!-- 0是1否 -->
          <td width="20%" align="right">来自云端</td>
          <td width="5"></td>
          <td>&nbsp;&nbsp;
          ${parambean1.eb4}
          <input type="radio" name="eb4" value="0"        
          <c:if test="${parambean1.eb4 ==0}">
            checked
          </c:if>>是&nbsp;&nbsp;
			&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       
	 		<input type="radio"  name="eb4" value="1" ${parambean1.eb4==1 ? 'checked':''} >否
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table>  
      </td>
    </tr>
  </table>
     </form>
</body>
</html>
