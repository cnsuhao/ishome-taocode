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

		document.form01.action = "/WMBM10/F.go";

		form01.submit();
	}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">浏览</h3></td>
	</tr>
	<tr style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
			 <form id="form01" name="form01" action="" method="post">
					  <button type="submit" class="btn btn-default btn-sm" onclick="fanhui()">返回</button>
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
					<td width="20%" align="right">银行账户</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f01" name="f01" value="${parambean1.f01}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">授权编号</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f02" name="f02" value="${parambean1.f02}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">通道名称</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="fb1" name="fb1" value="${parambean1.fb1}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">充值金额</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f07" name="f07" value="${parambean1.f07}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">实得金额</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f18" name="f18" value="${parambean1.f18}"></td>
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
