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
	function xiayibu() {		
		document.formsb01.action="/WMBM09/K.go";
			formsb01.submit();
	}
	function tianjia() {		
		document.formsb01.action="/WMBM09/M.go";
			formsb01.submit();
	}
</script>
 </head>
 <body>
  <table width="100%" border="0">
  <tr>
    <td><table  width="100%" border="0"> 
            <tr>
<!--             <td> -->
<!--             <div class="tab-main dark-gray"> -->
<!--        <h3 class="tab-main-title">会管理会幸福</h3> -->
<!--       </div></td> -->
      <tr>
        <td align="right">提示信息</td>
        </tr>
        </table>
        </td>
  </tr>
  <tr>
    <td>
        <table  align="center" border="0"> 
        
            <tr>
               <td align="right"><label ><b>操作成功</b></label></td>
               <td width="10">&nbsp;</td>  
              
             </tr>
             
             
        </table>
     </td>
</tr>

<tr>
    <td align="right">
    <div class="control-group">
          <div class="controls">
          <form id="formsb01" name="formsb01" action="" method="post">
           <button type="button" id="edit" class="btn btn-info" onclick="tianjia()">添加银行卡</button>
            <button type="button" id="edit" class="btn btn-info" onclick="xiayibu()">分配支付通道</button>
            
            </form>
            </div>
         </div>
     </td>
</tr>

</table>
 </body>
</html>
