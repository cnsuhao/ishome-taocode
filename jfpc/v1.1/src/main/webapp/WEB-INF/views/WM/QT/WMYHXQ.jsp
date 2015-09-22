<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>客户详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
  </head>
  <body>
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">客户详情</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
		    <input type="button" class="btn btn-info" value="返回" onclick="parent.toTargetForm('/WMBM09/F.go','/WMBM09/F.go')" />
		    <input type="button" class="btn btn-info" value="充值一览" onclick="parent.toTargetForm('/WMBM02/${user.puk}/X.go','/WMBM02/${user.puk}/X.go')" />
		    <input type="button" class="btn btn-info" value="提现一览" onclick="parent.toTargetForm('/WMBM03/${user.puk}/X.go','/WMBM03/${user.puk}/X.go')" />
<!-- 		    <input type="button" class="btn btn-info" value="佣金一览" onclick="parent.toTargetForm('/WMBM04/${user.puk}/X.go','/WMBM04/${user.puk}/X.go')" /> -->
		    <input type="button" class="btn btn-info" value="密码修改" onclick="parent.toTargetForm('/CSSR01/${user.puk}/tu.go','/CSSR01/${user.puk}/tu.go')" />
		    <input type="button" class="btn btn-info" value="通道分配" onclick="parent.toTargetForm('/WMKHTD/${user.puk}/F.go','/WMKHTD/${user.puk}/F.go')" /> 
		    
		    
		     <input type="button" class="btn btn-info" value="修改费率" onclick="parent.toTargetForm('/WMKHTD/${user.puk}/M.go','/WMKHTD/${user.puk}/M.go')" /> 
		    
		    
		     <input type="button" class="btn btn-info" value="银行卡管理" onclick="parent.toTargetForm('/WMQTHYYHK/${user.puk}/Y.go','/WMQTHYYHK/${user.puk}/Y.go')" /> 
<!-- 		      <input type="button" class="btn btn-info" value="通道一览" onclick="parent.toTargetForm('/WMKLH1/${user.puk}/Y.go','/WMKLH1/${user.puk}/Y.go')" />  -->
<!-- 		    <a href="/WMKLH1/${user.puk}/Y.go">银行卡管理</a> -->
<!--             <a href="/WMKLH1/${user.puk}/Y.go">通道一览</a> -->
		    
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
      
      <tr height="40">
          <td width="20%" align="right">用户名：</td>
          <td width="5"></td>
          <td>${user.f03 }</td>
          <td width="5"></td>
        </tr>
       
        <tr height="40">
          <td width="20%" align="right">用户状态：</td>
          <td width="5"></td>          
          <td>
          <c:if test="${user.ddd=='0'}">正常
          </c:if>
          <c:if test="${user.ddd=='1'}">无效
          </c:if>
          
          </td>
          <td width="5"></td>
         
        </tr>
        <tr height="40">
          <td width="20%" align="right">客户名称：</td>
          <td width="5"></td>          
          <td>${user.f04 }</td>
          <td width="5"></td>      
        </tr>
         <tr height="40">
          <td width="20%" align="right">用户类型：</td>
          <td width="5"></td>          
          <td>
          <c:if test="${user.k02=='DFB_QT_VIP_0'}">终端会员
          </c:if>
          <c:if test="${user.k02=='DFB_QT_VIP_1'}">代理商
          </c:if>
          </td>
          <td width="5"></td>      
        </tr>
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">开户行所在省：</td> -->
<!--           <td width="5"></td>           -->
<!--           <td>${user.bbb }</td> -->
<!--           <td width="5"></td> -->
         
<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">开户行所在市：</td> -->
<!--           <td width="5"></td>           -->
<!--           <td>${user.f05 }</td> -->
<!--           <td width="5"></td> -->

<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">银行名称：</td> -->
<!--           <td width="5"></td>           -->
<!--           <td>${user.f02 }</td> -->
<!--           <td width="5"></td> -->
<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">开户行名称：</td> -->
<!--           <td width="5"></td>           -->
<!--           <td>${user.f05 }</td> -->
<!--           <td width="5"></td> -->
        
<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">银行户名：</td> -->
<!--           <td width="5"></td>           -->
<!--           <td>${user.f01 }</td> -->
<!--           <td width="5"></td> -->
         
<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">银行账号：    </td> -->
<!--           <td width="5"></td>           -->
<!--           <td>${user.k02 }</td> -->
<!--           <td width="5"></td>  -->
         
<!--         </tr> -->
         
        
                
      </table></td>
    </tr>
  </table>
  
  
  
</body>
</html>
