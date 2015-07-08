<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">
 
    <title>Java Framework Project for Clound</title>
 
    <%@ include file="/resources/jsp/theme.jsp" %>
 
    <link rel="stylesheet" href="/resources/css/index.css">
  </head>
 
  <body>
 
    <div class="container">
    
 	  <form class="form-signin" role="form" id="form1" name="form1" method="post" action="/00000000">         

        <h2 class="form-signin-heading">请输入用户名和密码</h2>
        
        <!-- 登录地址 -->
		  <input type="hidden" name="loginUrl"  id="loginUrl" value="/" />         
		  <!-- 回调地址 -->
		  <input type="hidden" name="callBackUrl"  id="callBackUrl" value="00011000" />
		
		  <!-- 产品ID -->
		  <input type="text" name="productId" id="productId" value="JFPC" class="form-control"/>
		  <input type="text" name="companyId" id="companyId"  value="JFPC" class="form-control"/>
		  <input type="text" name="userName" id="userName"  value="admin" class="form-control" placeholder="用户名" required autofocus/>
		  <input type="text" name="passWord" id="password"  value="admin" class="form-control" placeholder="密码" required/>
		  <input type="hidden" name="token" id="token" />
		  
		  <!-- 安全验证码 -->
		  <input type="hidden" name="securityCode"  id="securityCode" value="${securityCode}" /> 
        
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
 
    </div> 
  </body>
</html>

