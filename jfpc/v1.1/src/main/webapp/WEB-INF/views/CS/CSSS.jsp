<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统授权信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/resources/js/validatorMessages.js"></script>
	<script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
// 		$(function() {
// 			$("#k01").blur(function() {
// 				if ("" == $(this).val()) {
// 					alert("您还没有输入内容哦");
// 					$(this).focus();
// 				} else {
// 					$("#k02").blur(function() {
// 						if ("" == $(this).val()) {
// 							alert("请输入内容");
// 							$(this).focus();
// 						} else {
// 							$("#k03").blur(function() {
// 								if ("" == $(this).val()) {
// 									alert("您还没有输入内容哦");
// 									$(this).focus();
// 								} else {
// 									$("#f01").blur(function() {
// 										if ("" == $(this).val()) {
// 											alert("您还没有输入内容哦");
// 											$(this).focus();
// 										}else{
// 											$("#f03").blur(function(){
// 												if("" == $(this).val()){
// 													alert("您还没有输入内容哦");
// 													$(this).focus();
// 												}else{
// 													$("#f04").blur(function(){
// 														if("" == $(this).val()){
// 															alert("您还没有输入内容哦");
// 															$(this).focus();
// 														}else{
// 															$("#f05").blur(function(){
// 																if("" == $(this).val()){
// 																	alert("您还没有输入内容哦");
// 																	$(this).focus();
// 																}else{
// 																	$("#f06").blur(function(){
// 																		if("" == $(this).val()){
// 																			alert("您还没有输入内容哦");
// 																			$(this).focus();
// 																		}else{
// 																			$("#f07").blur(function(){
// 																				if("" == $(this).val()){
// 																					alert("您还没有输入内容哦");
// 																					$(this).focus();
// 																				}else{
// 																					$("#f08").blur(function(){
// 																						if("" == $(this).val()){
// 																							alert("您还没有输入内容哦");
// 																							$(this).focus();
// 																						}else{
// 																							$("#f09").blur(function(){
// 																								if("" == $(this).val()){
// 																									alert("您还没有输入内容哦");
// 																									$(this).focus();
// 																								}else{
// 																									$("#f10").blur(function(){
// 																										if("" == $(this).val()){
// 																											alert("您还没有输入内容哦");
// 																											$(this).focus();
// 																										}else{
// 																											$("#bbb").blur(function(){
// 																												if("" == $(this).val()){
// 																													alert("您还没有输入内容哦");
// 																													$(this).focus();
// 																												}else{
// 																													$("#fb1").blur(function(){
// 																														if("" == $(this).val()){
// 																															alert("您还没有输入内容哦");
// 																															$(this).focus();
// 																														}else{
// 																															$("#fb2").blur(function(){
// 																																if("" == $(this).val()){
// 																																	alert("您还没有输入内容哦");
// 																																	$(this).focus();
// 																																}else{
// 																																	$("#fb3").blur(function(){
// 																																		if("" == $(this).val()){
// 																																			alert("您还没有输入内容哦");
// 																																			$(this).focus();
// 																																		}else{
// 																																			$("#fb4").blur(function(){
// 																																				if("" == $(this).val()){
// 																																					alert("您还没有输入内容哦");
// 																																					$(this).focus();
// 																																				}else{
// 																																					$("#fb5").blur(function(){
// 																																						if("" == $(this).val()){
// 																																							alert("您还没有输入内容哦");
// 																																							$(this).focus();
// 																																						}else{
// 																																							$("#eb1").blur(function(){
// 																																								if("" == $(this).val()){
// 																																									alert("您还没有输入内容哦");
// 																																									$(this).focus();
// 																																								}else{
// 																																									$("#eb2").blur(function(){
// 																																										if("" == $(this).val()){
// 																																											alert("您还没有输入内容哦");
// 																																											$(this).focus();
// 																																										}else{
// 																																											$("#eb3").blur(function(){
// 																																												if("" == $(this).val()){
// 																																													alert("您还没有输入内容哦");
// 																																													$(this).focus();
// 																																												}else{
// 																																													$("#eb4").blur(function(){
// 																																														if("" == $(this).val()){
// 																																															alert("您还没有输入内容哦");
// 																																															$(this).focus();
// 																																														}else{
// 																																															$("#eb5").blur(function(){
// 																																																if("" == $(this).val()){
// 																																																	alert("您还没有输入内容哦");
// 																																																	$(this).focus();
// 																																																}else{
// 																																																	$("#ddd").blur(function(){
// 																																																		if("" == $(this).val()){
// 																																																			alert("您还没有输入内容哦");
// 																																																			$(this).focus();
// 																																																		}
// 																																																		else{
// 																																																			$("#cc1").blur(function(){
// 																																																				if("" == $(this).val()){
// 																																																					alert("您还没有输入内容哦");
// 																																																					$(this).focus();
// 																																																				}else{
// 																																																					$("#cc2").blur(function(){
// 																																																						if("" == $(this).val()){
// 																																																							alert("您还没有输入内容哦");
// 																																																							$(this).focus();
// 																																																						}else{
// 																																																							$("#uu1").blur(function(){
// 																																																								if("" == $(this).val()){
// 																																																									alert("您还没有输入内容哦");
// 																																																									$(this).focus();
// 																																																								}else{
// 																																																									$("#uu2").blur(function(){
// 																																																										if("" == $(this).val()){
// 																																																											alert("您还没有输入内容哦");
// 																																																											$(this).focus();
// 																																																										}
// 																																																									});
// 																																																								}
// 																																																							});
// 																																																						}
// 																																																					});
// 																																																				}
// 																																																			});
// 																																																		}
// 																																																	});
// 																																																}
// 																																															});
// 																																														}
// 																																													});
// 																																												}
// 																																											});
// 																																										}
// 																																									});
// 																																								}
// 																																							});
// 																																						}
// 																																					});
// 																																				}
// 																																			});
// 																																		}
// 																																	});
// 																																}
// 																															});
// 																														}
// 																													});
// 																												}
// 																											});
// 																										}
// 																									});
// 																								}
// 																							});
// 																						}
// 																					});
// 																				}																	
// 																			});
// 																		}														
// 																	});
// 																}
// 															});
// 														}
// 													});													
// 												}
// 											});
// 										}
// 									});
// 								}
// 							});
// 						}
// 					});
// 				}
// 			});
// 		});		
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
// 			alert(document.formsb01);
			document.form1.action = "/CSSS01/L.go";
			form1.submit();
		}
		function save(){
			var rs = document.form1.puk.value;
			if (rs != "") {
				alert("u");
				document.form1.action = "/CSSS01/U.go";
				form1.submit();
			} else {
				alert("c");
				document.form1.action = "/CSSS01/C.go";
				form1.submit();
			}
			
// 			document.form1.action = "/WMUI01/C.go";
// 			form1.submit();
		}
	</script>
  </head> 
  <body>
    	 客户系统管理创建与编辑 <br>
    	 
   	 <form action="" method="post" id="form1" name="form1">
   	 	<table class="table" border="0" cellpadding="0" cellspacing="2">
   	 		<tr >
   	 			<td></td><!--     <label>用户ID</label> -->
   	 			<td><input type="hidden"  id="puk" name="puk" value="${parambean1.puk}" ></td>
   	 		</tr>
   	 		<tr>
   	 			<td  style="text-align:right;"><label>分站授权期限</label></td>
   	 			<td  align="left"><input type="text" id="k01" name="k01" value="${parambean1.k01}" style="width: 200px;height: 30px"></td>
   	 		</tr>
   	 		<tr >
   	 			<td style="text-align:right;"><label>授权者</label></td>
   	 			<td  align="left"><input type="text" id="k02" name="k02" value="${parambean1.k02}" style="width: 200px;height: 30px"></td>
   	 		</tr>
   	 		<tr >
   	 			<td style="text-align:right;"><label>授权信息</label></td>
   	 			<td align="left"><input type="text" id="k03" name="k03" value="${parambean1.k03}" style="width: 200px;height: 30px"></td>
   	 		</tr>
   	 		<tr>
   	 			<td style="text-align:right;"><label>创建者</label></td>
   	 			<td align="left"><input type="text" id="f01" name="f01" value="${parambean1.f01}" style="width: 200px;height: 30px"></td>
   	 		</tr>
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>默认银行账户ID</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f03" name="f03" value="${parambean1.f03}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>实名认证姓名</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f04" name="f04" value="${parambean1.f04}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>实名认证手机号</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f05" name="f05" value="${parambean1.f05}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>安全验证提醒文字</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f06" name="f06" value="${parambean1.f06}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>下线人数</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f07" name="f07" value="${parambean1.f07}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>充值次数</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f08" name="f08" value="${parambean1.f08}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>银行卡数目</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f09" name="f09" value="${parambean1.f09}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>提现次数</label></td> -->
<!--    	 			<td align="left"><input type="text" id="f10" name="f10" value="${parambean1.f10}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>备注说明</label></td> -->
<!--    	 			<td align="left"><input type="text" id="bbb" name="bbb" value="${parambean1.bbb}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>备用1</label></td> -->
<!--    	 			<td align="left"><input type="text" id="fb1" name="fb1" value="${parambean1.fb1}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>备用2</label></td> -->
<!--    	 			<td align="left"><input type="text" id="fb2" name="fb2" value="${parambean1.fb2}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>备用3</label> </td> -->
<!--    	 			<td align="left"><input type="text" id="fb3" name="fb3" value="${parambean1.fb3}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>备用4</label></td> -->
<!--    	 			<td align="left"><input type="text" id="fb4" name="fb4" value="${parambean1.fb4}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>备用5</label></td> -->
<!--    	 			<td align="left"><input type="text" id="fb5" name="fb5" value="${parambean1.fb5}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>扩展1</label></td> -->
<!--    	 			<td align="left"><input type="text" id="eb1" name="eb1" value="${parambean1.eb1}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>扩展2</label></td> -->
<!--    	 			<td align="left"><input type="text" id="eb2" name="eb2" value="${parambean1.eb2}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>扩展3</label></td> -->
<!--    	 			<td align="left"><input type="text" id="eb3" name="eb3" value="${parambean1.eb3}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr> -->
<!--    	 			<td  align="right"><label>扩展4</label></td> -->
<!--    	 			<td><input type="text" id="eb4" name="eb4" value="${parambean1.eb4}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--    	 		<tr > -->
<!--    	 			<td align="right"><label>业务系统ID（合作客户ID）</label></td> -->
<!--    	 			<td><input type="text" id="eb5" name="eb5" value="${parambean1.eb5}" style="width: 200px;height: 30px"></td> -->
<!--    	 		</tr> -->
<!--   	 		<tr > -->
<!--   	 			<td align="right"><label>有效标识</label></td> -->
<!--   	 			<td><input type="text" id="ddd" name="ddd" value="${parambean1.ddd}" style="width: 200px;height: 30px"></td> -->
<!--   	 		</tr> -->
  	 		<tr >
  	 			<td style="text-align:right;"><label>创建时间</label></td>
  	 			<td align="left"><input type="text" id="cc1" name="cc1" value="${parambean1.cc1}" style="width: 200px;height: 30px"></td>
  	 		</tr>
  	 		<tr>
  	 			<td  style="text-align:right;"><label>创建者</label></td>
  	 			<td align="left"><input type="text" id="cc2" name="cc2" value="${parambean1.cc2}" style="width: 200px;height: 30px"></td>
  	 		</tr>
  	 		<tr >
  	 			<td style="text-align:right;"><label>更新时间</label></td>
  	 			<td align="left"><input type="text" id="uu1" name="uu1" value="${parambean1.uu1}"  style="width: 200px;height: 30px"></td>
  	 		</tr>
  	 		<tr >
  	 			<td style="text-align:right;"><label>更新者</label></td>
  	 			<td align="left"><input type="text" id="uu2" name="uu2" value="${parambean1.uu2}" style="width: 200px;height: 30px"><br></td>
  	 		</tr>
  	 		<tr >
  	 			<td></td>
  	 			<td><br>	
					<button type="button" id="btn1" onclick="back()">返回</button>
					<button type="button" id="btn2" onclick="clearForm()">清空</button>
					<button type="button" id="btn3" onclick="save()">保存</button>
  	 			</td>
  	 		</tr>
   	 	</table>
   	 </form>
  </body>
</html>
