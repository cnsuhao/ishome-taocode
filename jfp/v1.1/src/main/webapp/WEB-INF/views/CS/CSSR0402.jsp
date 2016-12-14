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
<title>${msg}</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>

 </head>
 <body>
  <table width="100%" border="0">
  <tr>
    <td><table  width="100%" border="0"> 
            <tr>
           
      <tr>
        <td align="right">提示信息</td>
          ${msg}
        </tr>
        </table>
        </td>
  </tr>
  <tr>
    <td>
        <table  align="center" border="0"> 
      
            <tr>
               <td align="right"><label ><p style="font-size:20px; padding:0 0 20px 0;"><b>操作成功，系统会在3s内返回，如未跳转，请手动点击跳转<a href="${retun}">跳转</a></b></p></label></td>
               <td width="10">&nbsp;</td>  
              
             </tr>
             
             
        </table>
     </td>
</tr>

<tr>
    <td align="right">
    <div class="control-group">
          <div class="controls">

            </div>
         </div>
     </td>
</tr>

</table>
 </body>
</html>