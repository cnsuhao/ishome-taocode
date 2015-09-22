<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>荣宝充值接口-提交信息处理</title>
</head>


<body onload="pay.submit()">   
   <form id="rongpaysubmit" id="pay"  name="pay" target="_self" action="http://epay.reapal.com/portal" method="get">
   <input type="hidden" name="body" value=" "/>
   <input type="hidden" name="merchant_ID" value="${postMap['merchant_ID']}"/>
   <input type="hidden" name="notify_url" value="${postMap['notify_url']}"/>
   <input type="hidden" name="charset" value="${postMap['charset']}"/>
   <input type="hidden" name="order_no" value="${postMap['order_no']}"/>
   <input type="hidden" name="return_url" value="${postMap['return_url']}"/>
   <input type="hidden" name="title" value=" "/>
   <input type="hidden" name="total_fee" value="${postMap['total_fee']}"/>
   <input type="hidden" name="service" value="${postMap['service']}"/>
   <input type="hidden" name="paymethod" value="${postMap['paymethod']}"/>
   <input type="hidden" name="seller_email" value="${postMap['seller_email']}"/>
   <input type="hidden" name="payment_type" value="${postMap['payment_type']}"/>
   <input type="hidden" name="sign" value="${postMap['sign']}"/>
   <input type="hidden" name="sign_type" value="${postMap['sign_type']}"/>
   
   <input type="hidden" name="defaultbank" value="${postMap['defaultbank']}"/>
   
  </form>
</body>
</html>
