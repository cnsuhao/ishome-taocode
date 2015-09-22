<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>客户费率修改</title>
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
  <sf:form class="form-horizontal" role="form" id="myForm" modelAttribute="rateinfo" method="post">
  <table width="100%" border="0">
  
  <sf:hidden path="puk"/>
 <sf:hidden path="uu1"/>	
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">客户费率修改</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">客户费率修改</td>
		    <td align="right">
					 <!--  <button type="submit" class="btn btn-default btn-sm">返回</button> -->
					  <button type="submit" class="btn btn-default btn-sm">重置</button>
					  <input type="submit" class="btn btn-primary btn-sm" value="提交">	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
        <tr height="40">
          <td width="20%" align="right">结算通道：</td>
          <td width="5"></td>
          <td>${rateinfo.fb1 }</td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        
         <tr height="40">
          <td width="20%" align="right" style="color: #00F">充值手续费率：</td>
          <td width="5"></td>          
          <td><sf:input  path="f15" class="validate[required,custom[number],min[${rateinfo.f15 }]] text-input"/>%</td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
                
        
        
        <tr height="40">
          <td width="20%" align="right" style="color:#F00;">提现手续费(标准)：</td>
          <td width="5"></td>
          <td><sf:input  path="f16" class="validate[required,custom[number],min[${rateinfo.f16 }]] text-input"/></td>
          <td width="5"></td>
          <td width="30%">（标准工作时间：周一至周五 9点至17点）</td>
        </tr>
        <tr height="40">
          <td width="30%" align="right" style="color:#F00;">提现手续费(休息日)：</td>
          <td width="5"></td>          
          <td><sf:input  path="eb3" class="validate[required,custom[number],min[${rateinfo.eb3 }]] text-input"/></td>
          <td width="5"></td>
          <td>（休息时间）</td>
        </tr>
        <tr height="40">
          <td width="30%" align="right" style="color:#F00;">提现手续费(节假日)：</td>
          <td width="5"></td>          
          <td><sf:input  path="eb4" class="validate[required,custom[number],min[${rateinfo.eb4 }]] text-input"/></td>
          <td width="5"></td>
          <td>（节假日）</td>
        </tr>
        
         <tr height="40">
          <td width="20%" align="right">提现结算上限：</td>
          <td width="5"></td>
          <td><sf:input  path="f17" class="validate[required,custom[number],min[${rateinfo.f17 }]] text-input" /></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
       
      </table></td>
    </tr>
  </table>
   </sf:form>
</body>
</html>
