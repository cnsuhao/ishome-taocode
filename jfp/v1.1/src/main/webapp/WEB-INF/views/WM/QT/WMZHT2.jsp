<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
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
	function back() {
		document.form01.action = "/WMBM07/H.go";
		form01.submit();
	}
	function tianjia() {
		document.form01.action = "/WMBM07/C.go";
		form01.submit();
	}
	
	
</script>
 </head>
 <body>
 <form id="form01" name="form01" action="">
<table width="100%" border="0">
   <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">提示信息</h4></td>
	</tr>
	<tr style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;"></td>
		    <td align="right">			
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr>
    <td>
        <table align="center" border="0">         
            <tr>
               <td><p style="font-size:20px; "><b>${message}</b></td>
             </tr> 
             <tr>
               <td></td>
             </tr> 
             <c:if test="${flag=='1'}">
             <tr>
               <td><p style="font-size:14px; ">本次提现金额：${paramBean.f07}元。</td>
             </tr> 
             <tr>
               <td></td>
             </tr> 
             <!--             
            <tr>
               <td><p style="font-size:14px; "><a herf="#${puk}">明细查看</a></td>
             </tr>
              -->  
             </c:if>           
        </table>
     </td>
</tr>
<tr>
    <td>
 	</td>
</tr>
</table>
</form>
 </body>
</html>
