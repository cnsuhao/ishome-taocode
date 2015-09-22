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

		document.formsb01.action="/WMBM06/H.go";
			formsb01.submit();
	}
</script>
 </head>
 <body>

 
 <form action="http://paytest.baofoo.com/PayReceive/payindex.aspx">
    <input name='MerchantID' type='hidden' value= ""/> <!-- 商户号 -->
	<input name='PayID' type='hidden' value= ""/>		 <!-- 支付渠道  -->
	<input name='TradeDate' type='hidden' value= "" /> <!-- 下单日期 -->
	<input name='TransID' type='hidden' value= "" />  <!-- 流水号 -->
	<input name='OrderMoney' type='hidden' value= ""/> <!-- 支付金额 -->

	<input name='Merchant_url' type='hidden' value= ""/>
	<input name='Return_url' type='hidden' value= ""/>	
	<input name= 'Md5Sign' type='hidden' value=""/>
	<input name='NoticeType' type='hidden' value= ""/> 
 
 
 </form>
 
 
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
    <tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
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
               <td><p style="font-size:14px; ">本次充值金额：${paramBean.f07}元。</td>
             </tr> 
             <tr>
               <td></td>
             </tr>         
             </c:if>   
            <tr>
               <td><p style="font-size:14px; "><!-- a herf="#${puk}">明细查看</a--></td>
             </tr>             
        </table>
     </td>
</tr>
<tr>
    <td>
 	</td>
</tr>
</table>
 </body>
</html>
