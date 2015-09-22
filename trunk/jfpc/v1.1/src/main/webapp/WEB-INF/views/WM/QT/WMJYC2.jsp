<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
	function fanhui() {
    
		document.form01.action = "/WMBM02/F.go";

		form01.submit();
	}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">浏览 </h4></td>
	</tr>
	<tr style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
			 <form id="form01" name="form01" action="">
					  <button type="button" class="btn btn-default btn-sm" onclick="fanhui()">返回</button>
					 </form>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr height="40">
					<td width="20%" align="right">通道名称</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f03" name="f03" value="${parambean1.f03}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">发生日期</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="cc1" name="cc1" value="${parambean1.cc1}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">发生类型</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f06" name="f06" value="${parambean1.f06}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">发生金额</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f07" name="f07" value="${parambean1.f07}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">交易费</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f14" name="f14" value="${parambean1.f14}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">结算费</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f15" name="f15" value="${parambean1.f15}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">帐记余额</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f01" name="f01" value="${parambean1.f01}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				
				
				<tr height="40">
					<td width="20%" align="right"></td>
					<td width="5"></td>
					<td></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
			</table>
		</td>
	</tr>
  </table>
</body>
</html>
