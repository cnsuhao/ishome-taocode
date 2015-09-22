<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function jiansuo() {
		

			document.form01.action = "/WMBN01/R.go";
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
	<table width="100%" border="0">
		<tr>
			<td>
				<table width="100%" border="0" style="border:1px solid #dbdbdb;">
					<tr>
						<td>
							<div class="tab-main dark-gray" style="border:0 none;">
								<h3 class="tab-main-title">个人通道检索</h3>
							</div>
						</td>
					</tr>
					<tr style="border-top:1px solid #dbdbdb;">
						<td>
							 <form id="form01" name="form01" action="">
							<table align="center" border="0" width="100%">

								

								<tr height="30">
									<td colspan="3"></td>
								</tr>
								<tr height="50">
									<td align="right" width="35%"><label> 客户编号： </label>
									</td>
									<td width="60%">
										<div class="controls">

											<input type="text" style="margin:0" id="" placeholder=""
												value="${parambean1.k01}">
										</div>
									</td>
									<td width="20%"></td>
								</tr>
								<tr height="50">
									<td align="right" width="20%"><label> 客户名称： </label>
									</td>
									<td width="60%">
										<div class="controls">
											<input type="text" style="margin:0" id="" placeholder=""
												value="${parambean1.f01}">
										</div>
									</td>
								<tr height="50">
									<td align="right" width="35%"><label> 通道名称： </label>
									</td>
									<td width="60%">
										<div class="controls">
											<input type="text" style="margin:0" id="" placeholder=""
												value="${parambean1.f03}">
										</div>
									</td>
									<td width="20%"></td>
								</tr>

								
								
								
								<tr height="50">
									<td align="right" width="35%"><label>  </label>
									</td>
									<td width="60%">
										<div class="controls">
											<input type="hidden" style="margin:0" id="" placeholder=""
												value="${parambean1.uu1}">
										</div>
									</td>
									<td width="20%"></td>
								</tr>

							</table>
							</form>
						</td>
					</tr>
					<tr>
						<td align="right">
							<table align="center" width="100%" border="0">
								<tr height="20">
									<td colspan="4"></td>
								</tr>
								<tr height="50">
									<td align="right" width="20%"><label> </label>
									</td>

									
									<td width="30%">
										<div class="controls">
                                  
											<button type="submit" id="edit" class="btn btn-info btn-lg"
												onclick="jiansuo()">检索</button>
											<button type="submit" id="edit" class="btn btn-info btn-lg"
												onclick="back()">返回</button>
                                 
										</div>
									</td>
									<td width="10%"></td>
								</tr>
								<tr height="20">
									<td colspan="4"></td>
								</tr>
								
							</table>
							
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
		
	</table>
</body>
</html>

