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
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script   type="text/javascript">
	function xiayibu(){
		document.form01.action="/WMBM06/C.go";
		form01.submit();
	}
    function bao(){	
		var rs = document.getElementById("f07").value;
		var rs1 = document.getElementById("f02").value;
		if(rs1.indexOf("%")>0){
			rs1 = rs1.replace("%","") * 0.01 ;
		}
		
		var total = 0;		
		try{
			if(rs!==""&&rs1!="")
			total = rs * rs1*1;
		}catch(e){}
		document.getElementById("f07m").innerHTML="充值手续费用: "+total+" 元";
	}
	 

    function tdChanged(value){
    	var strs=value.split("|");
    	document.getElementById("f01").value = strs[0];
    	document.getElementById("f02").value = strs[1]; 
		document.getElementById("f02m").innerHTML="手续费率: 【"+strs[1]+"】　"+strs[2];
    	if(document.getElementById("f07").value!="")
    		bao();
    }
	
    function yhkChanged(value){
    	var strs=value.split("|");
    	document.getElementById("f03").value = strs[0];
    	document.getElementById("f04").value = strs[1]; 
    	document.getElementById("f05").value = strs[2];
    	document.getElementById("f05m").innerHTML = strs[2];
    }
    
	</script>
  </head>
  <body>
  <table width="100%" border="0" align="center" style="margin:0; padding:0;" >
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">充值</h4></td>
	</tr>
	<tr style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">			
				<button type="button" id="edit" class="btn btn-info" onclick="xiayibu()">确认</button>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td valign="top">
			<table width="100%" border="0" align="center" style="margin:0; padding:0;">
			<form id="form01" name="form01" method="post">
				<tr height="36">
					<td width="30%" align="right"><b>支付通道</b></td>					
                    <td width="10"></td>
					<td width="50%"> 
					<select onchange="tdChanged(this.value)">
					  <option value="0 0"></option>
	                  <c:forEach var="item" items="${listGrtd}" > 
                      	<option value="${item.puk}|${item.f15}${item.f05}|${item.f19}">${item.fb1}</option>
                      </c:forEach>
                  	</select>
                    </td>
                    <td width="5"></td>
                    <td></td>
				</tr>
				<tr height="36">
					<td align="right"></td>
					<td></td>
					<td><label name="f02m" id="f02m"></label></td>
					<td></td>
					<td></td>
				</tr>
				<input type="hidden" name="f01" id="f01" value="通道ID">	
				<input type="hidden" name="f02" id="f02" value="通道手续费率">	
				<tr height="36">
					<td align="right"><b>银行名称</b></td>					
                    <td></td>
					<td> 
					<select name="fb2">
					
						<option value="1001">招商银行</option>
						<option value="1002">工商银行</option>
						<option value="1003">建设银行</option>
						<option value="1004">浦发银行</option>
						<option value="1005">农业银行</option>
						<option value="1006">民生银行</option>
						<option value="1009">兴业银行</option>
						<option value="1020">交通银行</option>
						<option value="1022">光大银行</option>
						<option value="1026">中国银行</option>
						<option value="1032">北京银行</option>
						<option value="1035">平安银行</option>
						<option value="1036">广发银行|CGB</option>
						<option value="1038">中国邮政储蓄银行</option>
						<option value="1039">中信银行</option>
						<option value="1059">上海银行</option>
						<option value="1080">银联在线</option>							
                  	</select>			
					<!-- 
					<select onchange="yhkChanged(this.value)">
					  <option value="0 0"></option>
	                  <c:forEach var="item" items="${listYh}" > 
                      	<option value="${item.puk}">${item.f01}</option>
                      </c:forEach>
                  	</select>
                  	 -->
                    </td>
                    <td width="5"></td>
                    <td></td>
				</tr>
				<input type="hidden" name="f03" id="f03" value="银行卡识别ID">
				<input type="hidden" name="f04" id="f04" value="银行卡分类ID">		
				<input type="hidden" name="f05" id="f05" value="银行卡片ID">		
				<tr height="36">
					<td align="right"></td>
					<td></td>
					<td><label name="f05m" id="f05m"></label></td>
					<td></td>
					<td></td>
				</tr>					
				<tr height="36">
					<td align="right"><b>充值金额</b></td>
					<td></td>
					<td>
					<input onBlur="bao()" style="margin-bottom:0; " type="text" maxlength="20"  placeholder="" id="f07" name="f07" value="">
					元</td>
					<td></td>
					<td></td>
			    </tr>
				<tr height="36">
					<td align="right"></td>
					<td></td>
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
				<tr height="36">
					<td align="right"><b>备注说明</b></td>
					<td></td>
					<td>
					  <input style="margin-bottom:0;" type="text"  placeholder="" id="f17" name="f17" value="">
				    </td>
					<td></td>
					<td></td>
				</tr>		
				<tr height="36">
					<td align="right"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				</form>
			</table>
	  </td>

	</tr>
  </table>
</body>
</html>
