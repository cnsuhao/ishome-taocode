<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>实名详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>

  </head>
  <body >
  <sf:form id="myForm" name="myForm" modelAttribute="user" method="post">
  <sf:hidden path="puk"/>
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">实名详情</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">实名详情</td>
		   
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
      <tr height="40">
          <td width="20%" align="right">真实姓名</td>
          <td width="5"></td>
          <td>${user.f01 }</td>
          <td width="5"></td>
        </tr>
        
        <tr height="40">
          <td width="20%" align="right">实名认证结果</td>
          <td width="5"></td>          
          <td>
          <c:if test="${user.f18=='0'}">申请中
          </c:if>
          <c:if test="${user.f18=='1'}">认证失败
          </c:if>
          <c:if test="${user.f18=='2'}">认证成功
          </c:if>
         
          </td>
          <td width="5"></td>
         
        </tr>
       
        <tr height="40">
          <td width="20%" align="right">手机号码</td>
          <td width="5"></td>          
          <td>${user.f12 }
          </td>
          <td width="5"></td>
         
        </tr>
        
         <tr height="40">
          <td width="20%" align="right">证件类型</td>
          <td width="5"></td>          
          <td><c:if test="${user.f05=='0'}">身份证
          </c:if>
          
          </td>
          <td width="5"></td>
         
        </tr>
        
        <tr height="40">
          <td width="20%" align="right">证件号码</td>
          <td width="5"></td>          
          <td>${user.f06 }</td>
          <td width="5"></td>      
        </tr>
        
          <tr height="40">
          <td width="193" align="right" valign="middle">QQ号码</td>
          <td width="12"></td>
          <td align="left" valign="middle">
          ${user.f16 }
          </td>
          <td width="11"></td>
          <td width="336" align="left" valign="middle"></td>
        </tr>
        
        
       
        
       
        
       <tr height="40">
          <td width="20%" align="right">个人邮箱</td>
          <td width="5"></td>          
          <td>
          ${user.f17 }
          </td>
          <td width="5"></td>      
        </tr>
        
       
        
        
       <tr height="40">
          <td width="20%" align="right">身份证正面</td>
          <td width="5"></td>          
          <td><img src="${user.fb1 }"></td>
          <td width="5"></td>
         
        </tr>
        <tr height="40">
          <td width="20%" align="right">身份证反面</td>
          <td width="5"></td>          
          <td><img src="${user.fb2 }"></td>
          <td width="5"></td>

        </tr>
        <tr height="40">
          <td width="20%" align="right">最新照片</td>
          <td width="5"></td>          
          <td><img src="${user.fb3 }"></td>
          <td width="5"></td>
        </tr>
       
       <tr height="40">
          <td width="20%" align="right">审核失败理由</td>
          <td width="5"></td>          
          <td><sf:input path="bbb"/></td>
          <td width="5"></td>
        </tr>
    
                
      </table></td>
    </tr>
  </table>
    <input type="submit" value="审核未通过" class="btn btn-primary btn-sm">	
    <a href="P.go">审核通过</a>
  </sf:form>
</body>
</html>
