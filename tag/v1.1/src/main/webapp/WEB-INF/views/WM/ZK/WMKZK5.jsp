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
    <title>客户系统授权</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
<!-- 	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script> -->
	<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"/>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
	  function validate(){
	     if(document.getElementById('f12').value!=''){
	       return true;
	     }else{
	       return false;
	     }
	  }
	</script>
  </head>
  <body>
  <table width="100%" border="0">
     <form action="/WMKZ01/RTO.go" method="post" id="form1" name="form1" onsubmit=" return validate()">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">客户系统创建编辑</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">${message}</td>
		    <td align="right">
					  <button  class="btn btn-default btn-sm" onclick="back()">返回</button>
					  <button  class="btn btn-default btn-sm" onclick="clearForm()">重置</button>
					         <input  type="submit" value="更改" class="btn btn-primary btn-sm"/>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td>
      <table align="center" width="100%" style="margin:0; padding:0;" border="0">  


       
<tr height="40">
          <td width="20%" align="right">系统终了日期：</td>
          <td width="5"></td>
          <td>
             <input type="hidden" name="puk" value="${param.puk}"   /> 
             <input type="text" class="form-control input-sm" id="f12" name="f12" value="${parambean1.f12}" 
              onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()">         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>   
      </table>
   
      </td>
    </tr>
       </form>
  </table>
</body>
</html>
