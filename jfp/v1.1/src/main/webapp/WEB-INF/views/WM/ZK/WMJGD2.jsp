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
<!-- 	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script> -->
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#k01").blur(function() {
				if ("" == $(this).val()) {
					alert("您还没有输入内容哦");
					$(this).focus();
				} else {
					$("#k02").blur(function() {
						if ("" == $(this).val()) {
							alert("请输入内容");
							$(this).focus();
						} else {
							$("#k03").blur(function() {
								if ("" == $(this).val()) {
									alert("您还没有输入内容哦");
									$(this).focus();
								} else {
									$("#f01").blur(function() {
										if ("" == $(this).val()) {
											alert("您还没有输入内容哦");
											$(this).focus();
										}
									});
								}
							});
						}
					});
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
			document.form1.action = "/WMJG01/A.go";
			form1.submit();
		}
		function save(){
// 				document.form1.action = "/WMJG01/C.go";
// 				form1.submit();
// 				alert(f03);
	  		var rs = document.form1.puk.value;
			if (rs != "") {
				document.form1.action = "/WMJG01/U.go";
				form1.submit();
			} else {
// 				document.form1.action = "/WMJG01/C.go";
// 				form1.submit();
// 				alert(document.form1.f03.value);
				document.form1.action = "/WMJG01/E.go";
				form1.submit();
			}	
		}	
	</script>
  </head>
  <body>
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">支付通道编辑</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">message</td>
		    <td align="right">
					  <button  class="btn btn-default btn-sm">返回</button>
					  <button  class="btn btn-default btn-sm">重置</button>
					  <button  class="btn btn-primary btn-sm">保存</button>	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td>
      <form action="" method="post" id="form1" name="form1">
      <table align="center" width="100%" style="margin:0; padding:0;" border="0">  
         <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
          	<input type="hidden" class="form-control input-sm" id="puk" name="puk" value="${parambean1.puk}" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">通道名称：</td>
          <td width="5"></td>
          <td>
          <input type="text"  class="form-control input-sm" id="k01" name="k01" value="${parambean1.k01}" onfous=""  >
<!--           <input type="text" class="form-control input-sm" id="exampleInputEmail1"> -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">支付密钥：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="k02" name="k02" value="${parambean1.k02}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">通道状态：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="k03" name="k03" value="${parambean1.k03}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">授权号：</td>
          <td width="5"></td>
          <td>
             <input type="text"  class="form-control input-sm" id="f01" name="f01" value="${parambean1.f01}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">充值手续费数量：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f02" name="f02" value="${parambean1.f02}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">提现手续费数量：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f03" name="f03" value="${parambean1.f03}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">提现最大金额：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f08" name="f08" value="${parambean1.f08}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">结算说明：</td> -->
<!--           <td width="5"></td> -->
<!--           <td>   -->
<!--           <button class="btn btn-info" id="btninfo" onclick="">功能定义</button> &nbsp;&nbsp;      -->
<!--          <input type="text" class="form-control input-sm" id="f06" name="f06" value="${parambean1.f06}"  >          -->
<!--           		<input type="radio" id="f09" name="f09" value="0" ${parambean1.f09}==0?'checked':''>普通版 -->
<!-- 	   	 		<input type="radio" id="f09" name="f09" value="1" ${parambean1.f09}==1?'checked':''>豪华版 -->
<!-- 	   	 		<input type="radio" id="f09" name="f09" value="2" ${parambean1.f09}==2?'checked':''>微缩版 -->          		
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> -->
         <tr height="40">
          <td width="20%" align="right">结算说明：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f10" name="f10" value="${parambean1.f10}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>              
         <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
             <input type="hidden"  class="form-control input-sm" id="ddd" name="ddd" value="${parambean1.ddd}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
<!--          更新时间 -->
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
             <input type="hidden" class="form-control input-sm" id="uu1" name="uu1" value="${parambean1.uu1}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table>
      </form>
      </td>
    </tr>
  </table>
</body>
</html>
