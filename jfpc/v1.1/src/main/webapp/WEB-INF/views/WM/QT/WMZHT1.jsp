<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
	<script src="/resources/js/wm/qt/bootstrap.js"></script>
	<script type="text/javascript">

	function xiayibu(){	
		document.form01.action="/WMBM07/C.go";
		form01.submit();
	}	    

    function tdChanged(value){
    	if(value=='')return;
    	var strs=value.split("|");
    	document.getElementById("f01").value = strs[0];
    	document.getElementById("f02").value = strs[1]; //费用
    	document.getElementById("f12").value = strs[2]; //最大
    	document.getElementById("f07").value = strs[3]; //余额
		document.getElementById("f02m").innerHTML="手续费: 【"+strs[1]+"元】每笔，"+strs[4];    	
    }
	
    function body_onload(value){
    	if(document.getElementById("tdpuk").value!=''){
    		document.getElementById("zftdxx").disabled="disabled";
    		tdChanged(document.getElementById("tdpuk").value);
    	}
    	var zftd = document.getElementById('zftdxx');
    	if(zftd.options.length==1){
    		document.getElementById("f02m").innerHTML = "您的账户通道存在异常，请联系管理员！";
    		zftd.disabled="disabled";
    		return;
    	}
    	var yhk = document.getElementById('yhkxx');  
    	if(yhk.options.length==1){
    		document.getElementById("f05m").innerHTML = "没有审核通过的银行卡可用";
    		yhk.disabled="disabled";
    		return;
    	}
    	for(var i=0;i<yhk.options.length;i++){ 
    	   if(yhk.options[i].value.indexOf('${puk}') >0){   
    		    yhk.options[i].selected = true;   
    		    yhkChanged(yhk.options[i].value);
    	    	break;   
    	    }   
    	} 
    }
    
    
    function yhkChanged(value){
    	if(value=='')return;
    	var strs=value.split("|");
    	document.getElementById("f03").value = strs[0];
    	document.getElementById("f04").value = strs[1]; 
    	document.getElementById("f05").value = strs[2];
    	document.getElementById("f05m").innerHTML = strs[2];
    }
	</script>
  </head>
  <body onload="body_onload()">
  <table align="center" width="100%" style="margin:0; padding:0; border:1px solid #DBDBDB; " border="0">
  	<tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px; ">提现</h4></td>
	</tr>
	<tr style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
          	<td width="33"></td>
		    <td width="941" style="color:red; font-size:12px;">${message}</td>
		    <td width="595" align="right">		
		    	<input type="button" class="btn btn-info" value="申请提现" onclick="xiayibu()" />	
            </td>
            <td width="100"></td>
	      </tr>
	  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td valign="top"><form id="form01" name="form01" method="post">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
			
				<tr height="36">
					<td width="597" align="right"><b>支付通道</b></td>					
                    <td width="10"></td>
					<td width="834">
					<c:if test="${empty puk}"><input type="hidden" name="tdpuk" id="tdpuk" value=""/></c:if>
					<select onchange="tdChanged(this.value)" id = "zftdxx" name="zftdxx">
					  <option value=""></option>
	                  <c:forEach var="item" items="${listGrtd}" >
	                  	  <c:if test="item.ddd != '9'">
	                   		<c:choose>
		                  		<c:when test="${item.puk==puk}">
		                  			<option value="${item.puk}|${item.f16}${item.f06}|${item.f17}|${item.fb4}|${item.f19}" selected>${item.fb1}</option>
                      				<input type="hidden" name="tdpuk" id="tdpuk" value="${item.puk}|${item.f16}${item.f06}|${item.f17}|${item.fb4}|${item.f19}"/>
		                  		</c:when>
		                  		<c:otherwise>
		                  			<option value="${item.puk}|${item.f16}${item.f06}|${item.f17}|${item.fb4}|${item.f19}">${item.fb1}</option>                      	
		                  		</c:otherwise>
		                  	 </c:choose>  
		                  </c:if>                     	
                      </c:forEach>
                  	</select>
                    </td>
                    <td width="103"></td>
                    <td width="125"></td>
				</tr>
				<tr height="30">
					<td align="right"></td>
					<td width="10"></td>
					<td><label name="f02m" id="f02m" style="color:red; font-size:12px;"></label></td>
					<td></td>
					<td></td>
				</tr>
				<input type="hidden" name="f01" id="f01" value="通道ID">	
				<input type="hidden" name="f02" id="f02" value="提现手续费">	
				<input type="hidden" name="f12" id="f12" value="单笔提现最大金额">	
				<tr height="30">
					<td align="right"><b>银行卡</b></td>					
                    <td width="10"></td>
					<td> 
					<select onchange="yhkChanged(this.value)" id = "yhkxx" name="yhkxx">
					  <option value=""></option>
	                  <c:forEach var="wmbs01dbo" items="${listYhk}" > 
	                  <c:if test="${wmbs01dbo.f09==2}">
                      	<option value="${wmbs01dbo.puk}|${wmbs01dbo.fb2}|${wmbs01dbo.k02}|${wmbs01dbo.k03}">${wmbs01dbo.f02}</option>
                      </c:if>
                      </c:forEach>
                  	</select>
                  	<!-- button type="submit" id="button" name="" class="btn btn-primary" onClick="xiayibu()">添加</button-->
                    </td>
                    <td width="103"></td>
                    <td></td>
				</tr>
				<input type="hidden" name="f03" id="f03" value="银行卡识别ID">
				<input type="hidden" name="f04" id="f04" value="银行卡分类ID">		
				<input type="hidden" name="f05" id="f05" value="绑定通道ID">		
				<tr height="36">
					<td align="right"></td>
					<td width="10"></td>
					<td><label name="f05m" id="f05m" style="color:red; font-size:12px;"></label></td>
					<td></td>
					<td></td>
				</tr>					
				<tr height="30">
					<td align="right"><b>提现金额</b></td>
					<td width="10"></td>
					<td>
					<input readonly style="margin-bottom:0; " type="text" maxlength="20"  placeholder="" id="f07" name="f07" value="">
					元</td>
					<td></td>
					<td></td>
			    </tr>
				<tr height="36">
					<td align="right"></td>
					<td width="10"></td>
					<td><label id="f07m"></label></td>
					<td></td>
					<td></td>
				</tr>
<!-- 				<tr height="36"> -->
<!-- 					<td align="right" valign="center"><b>验证码</b></td> -->
<!-- 					<td></td> -->
<!-- 					<td> -->
<!-- 					  <input style="margin-bottom:0;" type="text"  placeholder="" id="f10" name="f10" value=""> -->
<!-- 				    </td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 				</tr>	 -->
<!-- 				<tr height="36"> -->
<!-- 					<td align="right"></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
				<tr height="30">
					<td align="right"><b>备注说明</b></td>
					<td width="10"></td>
					<td>
					  <input style="margin-bottom:0;" type="text"  placeholder="" id="bbb" name="bbb" value="">
				    </td>
					<td></td>
					<td></td>
				</tr>		
				<tr height="30">
					<td align="right"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				
			</table></form>
	  </td>
	</tr>
  </table>
</body>
</html>
