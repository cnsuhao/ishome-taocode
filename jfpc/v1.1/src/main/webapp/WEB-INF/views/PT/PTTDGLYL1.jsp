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
		var  rs=document.form01.puk.value;				
	 if(rs!=""){
	  	document.form01.action="/PTTDGLYL/U.go";
		form01.submit();	
     }else{
		document.form01.action="/PTTDGLYL/C.go";
 	    form01.submit(); 	  			
			}		
 		}
	
	
	
	
	function back() {

		document.form01.action = "/PTTDGLYL/F.go";
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
		<td><h4 style="margin-left:10px;">通道管理</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
<!-- 			  <input type="button" class="btn btn-info" value="保存" onclick="parent.toTargetForm('/WMBN01/${user.puk},${user.uu1}/U.go','')" /> -->
				 <button type="button" id="edit" class="btn btn-info"onclick="save()">确定</button>
					<input type="button" class="btn btn-info" value="返回" onclick="parent.toTargetForm('/PTTDGLYL/F.go','/PTTDGLYL/F.go')" />
			
									
					 
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
			<form id="form01" name="form01" method="post" action="">
				<tr height="40">
					<td width="20%" align="right">通道名称</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f03" name="f03" value="${paramBean.f03}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">通道简介</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f04" name="f04" value="${paramBean.f04}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">通道说明</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f05" name="f05" value="${paramBean.f05}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">充值手续费率</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f15" name="f15" value="${paramBean.f15}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">提现手续费</td>
					<td width="5"></td>
					<td>
					<input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f16" name="f16" value="${paramBean.f16}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">提现最大金额</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f17" name="f17" value="${paramBean.f17}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">参数1</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f10" name="f10" value="${paramBean.f10}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">参数2</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f11" name="f11" value="${paramBean.f11}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">参数3</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f12" name="f12" value="${paramBean.f12}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right"></td>
					<td width="5"></td>
					<td>
					<input type="hidden" style="margin:0" id="puk" name="puk"  value="${paramBean.puk}"> 
					<input type="hidden" style="margin:0" id="uu1" name="uu1"  value="${paramBean.uu1}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
	</form>			
			</table>
		</td>
	</tr>
	
  </table>
</body>
</html>
