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

		document.form01.action = "/WMBM03/F.go";

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
					<td width="20%" align="right">结算编号</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f13" name="f13" value="${parambean1.f13}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">开户行名称</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f04" name="f04" value="${parambean1.f03}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">银行名称</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f03" name="f03" value="${parambean1.f03}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">所属通道</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="k02" name="k02" value="${parambean1.k02}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">实付金额</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="f15" name="f15" value="${parambean1.f15}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">申请时间</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="cc1" name="cc1" value="${parambean1.cc1}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">结算时间</td>
					<td width="5"></td>
					<td><input style="margin-bottom:0; width:90%;" type="text"  placeholder="æ é¢" id="uu1" name="uu1" value="${parambean1.uu1}"></td>
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
