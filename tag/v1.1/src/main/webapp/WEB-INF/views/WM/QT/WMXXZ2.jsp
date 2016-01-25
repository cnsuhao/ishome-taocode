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
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	  
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
$(function (){
		$('#f15').focus();		
		jQuery("#form01").validationEngine('attach',
			{promptPosition : "centerRight", autoPositionUpdate : true});		
	});
	function checkf15(val){
		var val1=val;
		//alert(val1);
		var val2=document.getElementById("f15").value;
		if(val1>val2){
			document.getElementById("erroinfo").style.display="block";
			document.getElementById("f15").focus();
			document.getElementById("btn").disabled = true;
			return ;
		}else{
			document.getElementById("erroinfo").style.display="none";
			document.getElementById("btn").disabled = false;
		}	
	}
		function checkf16(val){
// 			alert(val);
		var val1=val;
		var val2=document.getElementById("f16").value;
		if(val1>val2){
			document.getElementById("erroinfo1").style.display="block";
			document.getElementById("f16").focus();			
			return false;
		}else{
			document.getElementById("erroinfo1").style.display="none";
			return true;
		}	
	}
		function save(){
            document.form01.action = "/WMBN01/U.go";
			form01.submit();
	}
	function back() {
		document.form01.action = "/WMBN01/F.go";
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
		<td><h4 style="margin-left:10px;">会员费率编辑</h4></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
<!-- 			  <input type="button" class="btn btn-info" value="保存" onclick="parent.toTargetForm('/WMBN01/${user.puk},${user.uu1}/U.go','')" /> -->
				 <button type="button" id="edit" class="btn btn-info"onclick="save()">确定</button>
<!-- 					<button type="submit" id="edit" class="btn btn-default" -->
<!-- 												onclick="clearForm()">清空</button> -->
					<button type="submit" id="edit" class="btn btn-default"
												onclick="back()">返回</button>
									
					 
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
					<td width="20%" align="right">客户编号</td>
					<td width="5"></td>
					<td>${user.k01}</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">客户名称</td>
					<td width="5"></td>
					<td>${user.f04}</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">通道名称</td>
					<td width="5"></td>
					<td>${user.fb1}
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">充值费率</td>
					<td width="5"></td>
					<td><input  type="text"  placeholder="" id="f15" name="f15" value="${user.f15}"
					class="validate[required,custom[integer]]"  onchange="checkf15(${user.f15})"></td>
					<td width="5"></td>
					<td width="30%">
						<div style="display: none;" id="erroinfo">手续费率必须大于当前费率</div>
					</td>
				</tr>
				<tr height="40">
		          <td width="20%" align="right" ">提现手续费(标准)：</td>
		          <td width="5"></td>
		          <td><input  type="text"  placeholder="" id="f16" name="f16" value="${user.f16}"
					onchange="checkf16(${user.f16})" class="validate[required,custom[number]]"></td>
		          <td width="5"></td>
		          <td width="40%">
		          <div style="float:left; width:240px;">（标准工作时间：周一至周五 9点至17点）</div>
		          <div id="erroinfo1" style="display: none; float:left; width:190px;">手续费率必须大于当前费率</div>
		        </tr>
		        <tr height="40">
		          <td width="20%" align="right" ">提现手续费(休息日)：</td>
		          <td width="5"></td>          
		          <td><input  type="text" id="eb3" name="eb3" value="${user.eb3}"
					class="validate[required,custom[integer]]">
		          </td>
		          <td width="5"></td>
		          <td>（休息时间）</td>
		        </tr>
		        <tr height="40">
		          <td width="20%" align="right">提现手续费(节假日)：</td>
		          <td width="5"></td>          
		          <td><input  type="text" id="eb4" name="eb4" value="${user.eb4}"
					class="validate[required,custom[integer]]"></td>
		          <td width="5"></td>
		          <td>（节假日）</td>
		        </tr>
				<tr height="40">
					<td width="20%" align="right">提现上限</td>
					<td width="5"></td>
					<td>${user.f17}</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">提现说明</td>
					<td width="5"></td>
					<td><input type="text"  placeholder="" id="f19" name="f19" value="${user.f19}" class="validate[required,custom[integer]]"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				
				<tr height="40">
					<td width="20%" align="right"></td>
					<td width="5"></td>
					<td>
					<input type="hidden" id="puk" name="puk"  value="${user.puk}"> 
					<input type="hidden" id="uu1" name="uu1"  value="${user.uu1}">
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
