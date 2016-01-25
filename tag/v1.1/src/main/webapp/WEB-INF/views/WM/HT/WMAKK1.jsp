<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>添加银行卡</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>


<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css">
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script>
        
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#myForm").validationEngine('attach', {promptPosition : "centerRight", autoPositionUpdate : true});
			
		});
</script>
  </head>
  <body>
  <sf:form class="form-horizontal" role="form" id="myForm" modelAttribute="cardinfo" method="post">
  <table width="100%" border="0">
  
   <sf:hidden path="k01"/>
 	
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">添加银行卡</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">添加银行卡</td>
		    <td align="right">
					  <!-- <button type="submit" class="btn btn-default btn-sm">返回</button> -->
					  <input type="reset" class="btn btn-default btn-sm">
					  <input type="submit" class="btn btn-primary btn-sm" value="提交">	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
       
        <tr height="40">
          <td width="20%" align="right">开户行所在省：</td>
          <td width="5"></td>          
          <td><sf:input path="f04" class="validate[required] text-input" /></td>
          <td width="5"></td>         
        </tr>
        <tr height="40">
          <td width="20%" align="right">开户行所在市：</td>
          <td width="5"></td>
          <td><sf:input  path="f05" class="validate[required] text-input"  /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">银行名称：</td>
          <td width="5"></td>
          <td><sf:input  path="f02" class="validate[required] text-input"  /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        
         <tr height="40">
          <td width="20%" align="right">分行名称：</td>
          <td width="5"></td>
          <td><sf:input  path="fb3" class="validate[required] text-input" /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        
         <tr height="40">
          <td width="20%" align="right">开户行名称：</td>
          <td width="5"></td>
          <td><sf:input path="f03"  class="validate[required] text-input"   /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">银行户名：</td>
          <td width="5"></td>
          <td><sf:input path="f01" class="validate[required] text-input"  /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      <%--   <tr height="40">
          <td width="193" align="right" valign="middle">账号类型：</td>
          <td width="12"></td>
          <td align="left" valign="middle"><sf:radiobutton path="f07" value="0"/>个人账号<sf:radiobutton path="f07" value="1"/>企业账号</td>
          <td width="11"></td>
          <td width="336" align="left" valign="middle">备注</td>
        </tr> --%>
         <tr height="40">
          <td width="20%" align="right">银行账号：</td>
          <td width="5"></td>
          <td><sf:input  path="k02" class="validate[required,custom[integer]] text-input" /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">重复银行账号：</td>
          <td width="5"></td>
          <td><sf:input  path="fb4" class="validate[required,custom[integer],equals[k02]] text-input" /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         
        
       
      </table></td>
    </tr>
  </table>
   </sf:form>
</body>
</html>
