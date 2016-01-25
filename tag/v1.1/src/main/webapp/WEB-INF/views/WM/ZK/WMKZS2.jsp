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
	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script>
	<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"/>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
// 		$(function() {
// 			$('#form1').checkForm();	
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
			document.form1.action = "/WMKZ03/F.go";
			form1.submit();
		}
		function save(){
	  		var rs = document.form1.puk.value;
			if (rs != "") {
				document.form1.action = "/WMKZ03/U.go";
				form1.submit();
			} else {
				document.form1.action = "/WMKZ03/C.go";
				form1.submit();
			}	
		}

	</script>
  </head>
  <body>
   <form action="/WMKZ01/U.go" method="post" id="form1" name="form1">
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">修改授权期限</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">${message}</td>
		    <td align="right">
					  <button type="button" class="btn btn-default btn-sm" onclick="back()">返回</button>
					  <button type="button" class="btn btn-default btn-sm" onclick="clearForm()">重置</button>
					  <input type="submit" class="btn btn-primary btn-sm" id="btnsave" value="保存"></button>	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td>
     
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
          <td width="20%" align="right">授权编号：</td>
          <td width="5"></td>
          <td>
          <input type="text" disabled="disabled" class="form-control input-sm" id="k01" name="k01" value="${parambean1.k01}" onfous=""  >
<!--           <input type="text" class="form-control input-sm" id="exampleInputEmail1"> -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">绑定域名：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="k02" name="k02" value="${parambean1.k02}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">超级管理员ID：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="k03" name="k03" value="${parambean1.k03}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">客户简称：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f01" name="f01" value="${parambean1.f01}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">客户全称：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f02" name="f02" value="${parambean1.f02}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">客户地址：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f03" name="f03" value="${parambean1.f03}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">联系人电话：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f08" name="f08" value="${parambean1.f08}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">系统分类：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f09" name="f09" value="${parambean1.f09}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">系统版本：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f10" name="f10" value="${parambean1.f10}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">系统密钥：</td>
          <td width="5"></td>
          <td>
             <input type="text" disabled="disabled" class="form-control input-sm" id="f11" name="f11" value="${parambean1.f11}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">系统终了日期：</td>
          <td width="5"></td>
          <td>
             <input type="text"  class="form-control input-sm" id="f12" name="f12" value="${parambean1.f12}" 
            onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()">           
<!--             validata-options="validType:'Date',format:'ymd',msg:'日期不存在'" >          -->
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
    
      </td>
    </tr>
  </table>
    </form>
<!--    <script> -->
<!-- 		   $('#form1').checkForm(); -->
<!-- 		   $("#btnsave").click(function(){ -->
<!-- 		  $('#form1').submit(); -->
<!-- 		}); -->
<!-- </script> -->
</body>
</html>
