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
	function save(){
            document.form01.action = "/WMBM09/A.go";
			form01.submit();
	}

	function fanhui() {
		    document.form01.action = "/WMBM09/F.go";
	
			form01.submit();			
	}
	
    function tdChanged(value){
    	var strs=value.split("|");
    	document.getElementById("k01").value = strs[0];
    	document.getElementById("f15").value = strs[1];
    	document.getElementById("f16").value = strs[2]; 
    	document.getElementById("f17").value = strs[3];
    	document.getElementById("f19").value = strs[4]; 
    }
     function checkForm(){
	
	  
	  var pa = document.getElementById('k02').value;
	  var par = document.getElementById('k02a').value;
// 	  if(pa==''||par==''){
//   		document.getElementById('k02_tips').innerHTML='卡号不能为空';
//   		return false;
// 	  }
	 if(pa!=par){
	  	document.getElementById('k02a_tips').innerHTML='前后卡号不一致';	  
  		return false;

	 }
	
	  return true;
	  
} 
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">选择通道</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
			<input type="button" class="btn btn-info" value="返回" onclick="fanhui()" />
			<input type="button" class="btn btn-info" value="确定" onclick="save()" />
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
			<form id="form01" name="form01" method="post" onsubmit="return checkForm()">			
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;支付通道名称</th>
					<td width="5"></td>
					<td><select onchange="tdChanged(this.value)">
						<option value="||||"></option>
		                <c:forEach var="wmbm01dbo" items="${list}"> 			
						<option value="${wmbm01dbo.puk}|${wmbm01dbo.f15}|${wmbm01dbo.f16}|${wmbm01dbo.f17}|${wmbm01dbo.f19}">${wmbm01dbo.fb1}</option>
		 				</c:forEach> 
		               </select></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<input  type="hidden"  name="k01" id="k01" value="" />
				<input  type="hidden"  name="puk" id="puk" value="${puk}" />
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;充值手续费率</th>
					<td width="5"></td>
					<td><input  type="text"  placeholder="" id="f15" name="f15" value="${parambean.f15}">&nbsp;&nbsp;%</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;提现手续费</th>
					<td width="5"></td>
					<td><input  type="text"  placeholder="" id="f16" name="f16" value="${parambean.f16}"></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;单笔提现上限</th>
					<td width="5"></td>
					<td>${parambean.f17}</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
<!-- 				<tr height="40"> -->
<!-- 					<td width="20%" align="right">种类所属</td> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td><input  type="text"  placeholder="" id="f18" name="f18" value="${parambean.f18}"></td> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td width="30%"></td> -->
<!-- 				</tr> -->
<!-- 				<tr height="40"> -->
<!-- 					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;结算说明</th> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td><input  type="text"  placeholder="" id="f19" name="f19" value="${parambean.f19}"></td> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td width="30%"></td> -->
<!-- 				</tr>			 -->
				<!-- tr height="40">
		          <th width="80" align="right">账号类型</th>
		          <td width="1"></td>
		          <td align="left"><input type="radio" name="eb1" value="0">个人账号<input type="radio" name="eb1" value="1">企业账号</td>
		          <td width="5"></td>
		          <td width="30%"></td>
	            </tr-->
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;银行开户名</th>
					<td width="5"></td>
					<td><input  type="text"  placeholder="" id="f01" name="f01" value="${parambean1.f01}"></td>					
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;银行名称</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f02" name="f02" value="${parambean1.f02}">
<!-- 					<select name="f02"> -->
<!-- 					<c:forEach var="cssb01dbo" items="${list}"> -->
<!-- 	                <option value="${parambean1.f02}" selected>${cssb01dbo.f02}</option> -->
<!-- 	                </c:forEach> -->
<!--                     </select> -->
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;开户行名称</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f03" name="f03" value="${parambean1.f03}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;银行账号</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="k02" name="k02" value="${parambean1.k02}"><span id="k02_tips" style="background:red"></span>
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;重复银行账号</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="k03" name="k03" value="${parambean1.k03}"><span id="k02a_tips" style="background:red"></span>
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;所在省</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f04" name="f04" value="${parambean1.f04}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;所在市</th>
					<td width="5"></td>
					<td>
					<input  type="text"  placeholder="" id="f05" name="f05" value="${parambean1.f05}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>

			</table>
		</td>
	</tr>
  </table>
</body>
</html>
