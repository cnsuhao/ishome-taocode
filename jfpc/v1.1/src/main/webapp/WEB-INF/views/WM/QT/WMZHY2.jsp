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
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function save(){
            document.form01.action = "/WMQTHYYHK/U.go";
			form01.submit();
	}
	function bao(){
	
	var rs = document.getElementById("puk").value;
	document.form01.action="/WMBM09/N.go?k03="+rs;
	form01.submit();
	}


function fanhui() {
		
		  
		    document.form01.action = "/WMQTHYYHK/Y.go?k01="+k01;
	
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
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">银行卡</h3></td>
	</tr>
	<tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
			
			<input type="button" class="btn btn-info" value="返回" onclick="parent.toTargetForm('/WMQTHYYHK/${parambean1.k01}/Y.go','')" />
            <button type="button" id="edit" class="btn btn-info"onclick="save()">确定</button>
									
					 
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
			<form id="form01" name="form01" method="post">
			

			
				<tr height="40">
					<td width="20%" align="right">用户名</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f01" name="f01" value="${parambean1.f01}">
					<input style="margin-bottom:0; width:90%;" type="hidden"  placeholder="" id="puk" name="puk" value="${parambean1.puk}">
					<input style="margin-bottom:0; width:90%;" type="hidden"  placeholder="" id="uu1" name="uu1" value="${parambean1.uu1}">
					<input style="margin-bottom:0; width:90%;" type="hidden"  placeholder="" id="k01" name="k01" value="${parambean1.k01}">
					</td>
					
					<td width="5"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">银行名称</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f02" name="f02" value="${parambean1.f02}">
					</td>
					<td width="30%"></td>
				</tr>

				<tr height="40">
					<td width="20%" align="right">银行账号</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="k02" name="k02" value="${parambean1.k02}">
					</td>
					<td width="5"></td>
					
				</tr>
				<tr height="40">
					<td width="20%" align="right">所在省</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f04" name="f04" value="${parambean1.f04}">
					</td>
					<td width="5"></td>
					
				</tr>
				<tr height="40">
					<td width="20%" align="right">所在市</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f05" name="f05" value="${parambean1.f05}">
					</td>
					<td width="5"></td>
					
				</tr>
				<tr height="40">
					<td width="20%" align="right">银行详细地址</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f03" name="f03" value="${parambean1.f03}">
					</td>
					<td width="5"></td>
					
				</tr>

			</table>
		</td>
	</tr>
  </table>
</body>
</html>
