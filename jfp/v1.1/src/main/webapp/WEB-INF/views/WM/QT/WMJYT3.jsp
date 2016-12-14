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
	function fanhui() {

		document.formsb01.action="/WMZHD1/F.go";
			formsb01.submit();
	}
</script>
 </head>
 <body>
  <table width="100%" border="0">
   <tr>
    <td><table  width="100%" border="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title"><div style="font-size:14px; font-weight:bold; color:#000;">提示信息</div></h3>
      </div></td>
      
        </table>
        </td>
  </tr>  
  
  <tr>
    <td>
        <table  align="center" border="0"> 
      
              <tr height="40">
               <td><label ><p style="font-size:20px; padding:0 0 20px 0;"><b>${message}</b></p></label></td>
             </tr>
             
              <tr height="40">
               <td></td>
             </tr>
              <tr height="40">
               <td> <c:if test="${flag!=null}">获得佣金：${f18} 元</c:if></td>
             </tr> 
             
             <tr height="40">
               <td></td>
             </tr>
              <tr height="40">
               <td></td>
             </tr>
        </table>
     </td>
</tr>

<tr>
    <td align="right">
    <div class="control-group">
          <div class="controls">
<!--           <form id="formsb01" name="formsb01" action=""> -->
<!--             <button type="button" id="edit" class="btn btn-info" onclick="fanhui()">返回</button> -->
<!--             </form> -->
            </div>
         </div>
     </td>
</tr>

</table>
 </body>
</html>
