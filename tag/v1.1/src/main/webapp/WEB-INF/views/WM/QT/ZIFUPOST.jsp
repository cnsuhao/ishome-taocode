<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智付充值接口-提交信息处理</title>
</head>


<body  onload="pay.submit()">
<form name="pay" id="pay" method="post" target="_self" action="https://pay.dinpay.com//gateway?input_charset=UTF-8"><!-- 注意 非UTF-8编码的商家网站 此地址必须后接编码格式 -->
	<input type="hidden" name="sign" value="${postMap['sign']}" />
	<input type="hidden" name="merchant_code" value="${postMap['merchant_code']}" />
	<input type="hidden" name="bank_code" value="${postMap['bank_code']}"/>
	<input type="hidden" name="order_no" value="${postMap['order_no']}"/>
	<input type="hidden" name="order_amount" value="${postMap['order_amount']}"/>
	<input type="hidden" name="service_type" value="${postMap['service_type']}"/>
	<input type="hidden" name="input_charset" value="${postMap['input_charset']}"/>
	<input type="hidden" name="notify_url" value="${postMap['notify_url']}">
	<input type="hidden" name="interface_version" value="${postMap['interface_version']}"/>
	<input type="hidden" name="sign_type" value="${postMap['sign_type']}"/>
	<input type="hidden" name="order_time" value="${postMap['order_time']}"/>
	<input type="hidden" name="product_name" value="${postMap['product_name']}"/>
	<input Type="hidden" Name="client_ip" value=""/>
	<input Type="hidden" Name="extend_param" value=""/>
	<input Type="hidden" Name="extra_return_param" value=""/>
	<input Type="hidden" Name="product_code" value=""/>
	<input Type="hidden" Name="product_desc" value=""/>
	<input Type="hidden" Name="product_num" value=""/>
	<input Type="hidden" Name="return_url" value="${postMap['return_url']}"/>
	<input Type="hidden" Name="show_url" value=""/>
	
	</form>

</body>
</html>
