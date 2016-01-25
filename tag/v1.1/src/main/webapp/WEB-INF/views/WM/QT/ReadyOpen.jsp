<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>充值接口</title>
</head>

<script   type="text/javascript">
function onloadpay(flag){
	if(flag=='1')	{
		pay.submit();
	}	
}
</script>

<body  onload="onloadpay('${flag}')"  >

<form name="pay" action="/WMBM06/${orderno}/t.go" id="pay" >
<table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td align="center" >
		<h4 style="margin-left:10px;">请在新页面内完成充值</h4>
		<h4 style="margin-left:10px;">友情提示：如果您的浏览器不能正常看到支付页面，请将网站设定为信任网站，并且允许弹出页面。</h4>
		</td>
	</tr>
</table>
</form>
</body>
</html>
