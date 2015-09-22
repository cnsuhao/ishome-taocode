<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宝付充值接口-提交信息处理</title>
</head>

<script   type="text/javascript">
function onloadpay(payflag){
	if(payflag=='0'){
		pay.submit();
	}else
	{   
		alert('请勿重复提交');
		location.href="/WMMN/F.go";
	}
	
}
</script>

<body  onload="onloadpay('${payflag}')" >


  



<form  method="post" name="pay" id="pay" target="_self" action="http://paygate.baofoo.com/PayReceive/bankpay.aspx" >

	<input name='MerchantID' type='hidden' value= "${postMap['merchantId']}"/>
	<input name='PayID' type='hidden' value= "${postMap['payId']}"/>		
	<input name='TradeDate' type='hidden' value= "${postMap['tradeDate']}" />
	<input name='TransID' type='hidden' value= "${postMap['transId']}" />
	<input name='OrderMoney' type='hidden' value= "${postMap['orderMoney']}"/>
	<input name='Merchant_url' type='hidden' value= "${postMap['merchantUrl']}"/>
	<input name='Return_url' type='hidden' value= "${postMap['returnUrl']}"/>	
	<input name= 'Md5Sign' type='hidden' value="${postMap['md5Sign']}"/>
	<input name='NoticeType' type='hidden' value= "${postMap['noticeType']}"/>
		
    <input name='ProductName' type='hidden' value= ""/>
	<input name='Amount' type='hidden' value= ""/>
	<input name='ProductLogo' type='hidden' value= ""/>
	<input name='Username' type='hidden' value= ""/>
	<input name='Email' type='hidden' value= ""/>
		<input name='Mobile' type='hidden' value= ""/>
		<input name='AdditionalInfo' type='hidden' value= ""/>
</form>	

</body>
</html>
