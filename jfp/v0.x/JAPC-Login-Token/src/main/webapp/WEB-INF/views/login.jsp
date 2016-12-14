<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<%@ include file="/resources/jsp/theme.jsp" %>
</head>
<body>
<div>企业用户登录
<form id="form1" name="form1" method="post" action="/00000000">         
  <!-- 登录地址 -->
  <input type="hidden" name="loginUrl"  id="loginUrl" value="/" />         
  <!-- 回调地址 -->
  <input type="hidden" name="callBackUrl"  id="callBackUrl" value="00011000" />

  <!-- 产品ID -->
  <input type="text" name="productId" id="productId" value="JFPC" />
  <input type="text" name="companyId" id="companyId"  value="JFPC"/>
  <input type="text" name="userName" id="userName"  value="admin"/>
  <input type="text" name="passWord" id="password"  value="admin"/>
  <input type="text" name="token" id="token" />
  
  <!-- 安全验证码 -->
  <input type="hidden" name="securityCode"  id="securityCode" value="${securityCode}" /> 
  
  <input type="submit" name="button" id="button" value="login" />
  
</form>
</div>

<div>产品订购</div>
</body>
</html>
