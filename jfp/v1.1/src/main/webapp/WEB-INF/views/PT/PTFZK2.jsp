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
    
    <title>客户系统创建编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="/resources/css/pt/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- 	<link href="/resources/css/pt/global.css" rel="stylesheet" media="screen"> -->
	<script type="text/javascript" src="/resources/js/pt/jquery.js"></script>
	<script src="/resources/js/pt/bootstrap.min.js"></script>
	<script type="text/javascript">

		function save(){
			var  rs=document.formsb01.puk.value;
			 	if(rs!=""){
					document.formsb01.action="/PTFZ1/U.go";
					formsb01.submit();
				}else{
					document.formsb01.action="/PTFZ1/I.go";
 					formsb01.submit(); 				
				}
 		}
 		function back(){
			document.formsb01.action="/PTFZ1/F.go";
			formsb01.submit();
			}
		function clearForm(){	
		
			var formObj=document.getElementById('formsb01');
			if(formObj==undefined){
				return;
			}	
			
			for(var i=0; i<formObj.elements.length; i++){
				if(formObj.elements[i].type == "text"){
					formObj.elements[i].value = ""; 
				}else if(formObj.elements[i].type == "password"){
					formObj.elements[i].value = "";  
				}else if(formObj.elements[i].type == "radio"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "checkbox"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "file"){
					var file = formObj.elements[i]; 
					if (file.outerHTML){
						file.outerHTML = file.outerHTML;
					}else{
						 file.value = ""; 
					}
				}else if(formObj.elements[i].type == "textarea"){
					formObj.elements[i].value = "";
				}else if(formObj.elements[i].type == "select"){
					formObj.elements[i].options[0].selected = true;
				}else if(formObj.elements[i].type == "hidden"){
					formObj.elements[i].value = "";
				}
			}
		}	
		
	</script>
  </head> 
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
  	<form  action="" method="post" id="formsb01" name="formsb01">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">客户系统创建编辑</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">提示信息</td>
		    <td align="right">
					  <button type="submit" class="btn btn-default btn-sm" onclick="back()">返回</button>
					  <button type="reset" class="btn btn-default btn-sm">重置</button>
					  <button type="submit" class="btn btn-primary btn-sm" onclick="save()">保存</button>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr>
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
        <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td><input type="hidden" class="form-control input-sm" id="puk" name="puk" value="${parambean1.puk}"></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">授权编号：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="k01" name="k01" maxlength="20" value="${parambean1.k01}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">绑定域名：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="k02" name="k02" maxlength="40" value="${parambean1.k02}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">超级管理员ID：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="k03" name="k03" maxlength="20" value="${parambean1.k03}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">客户简称：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f01" name="f01" maxlength="40" value="${parambean1.f01}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">客户全称：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f02" name="f02" maxlength="80" value="${parambean1.f02}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">客户地址：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f03" name="f03" maxlength="200" value="${parambean1.f03}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">联系人电话：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f08" name="f08" maxlength="20" value="${parambean1.f08}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">系统分类：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f09" name="f09" maxlength="40" value="${parambean1.f09}"></td>
<!--           <button class="btn btn-info" id="btninfo" onclick="">功能定义</button> &nbsp;&nbsp;      -->
<!--           		<input type="radio" id="f09" name="f09" value="0" ${parambean1.f09}==0?'checked':''>普通版 -->
<!-- 	   	 		<input type="radio" id="f09" name="f09" value="1" ${parambean1.f09}==1?'checked':''>豪华版 -->
<!-- 	   	 		<input type="radio" id="f09" name="f09" value="2" ${parambean1.f09}==2?'checked':''>微缩版 -->
          </td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">系统版本：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f10" name="f10" maxlength="40" value="${parambean1.f10}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">系统密钥：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f11" name="f11" maxlength="128" value="${parambean1.f11}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">系统终了日期：</td>
          <td width="5"></td>
          <td><input type="text" class="form-control input-sm" id="f12" name="f12" maxlength="20" value="${parambean1.f12}"></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td><input type="hidden" class="form-control input-sm" id="uu1" name="uu1" value="${parambean1.uu1}"></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table></td>
    </tr>
    </form>
  </table>
  </body>
</html>