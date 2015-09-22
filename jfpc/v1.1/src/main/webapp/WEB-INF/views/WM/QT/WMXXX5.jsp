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
            document.form01.action = "/WMKHTD/U.go";
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
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">费率设定</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">${message}</td>
		    <td align="right">
<!-- 		     <input type="button" class="btn btn-info" value="确定" onclick="parent.toTargetForm('/WMKHTD/U.go','/WMKHTD/C.go')" /> -->
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
			<form id="form01" name="form01" method="post">			
				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;支付通道名称</th>
					<td width="5"></td>
					<td>${parambean.fb1}</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
<!-- 				<input  type="hidden"  name="uu2" id="uu2" value="${parambean.uu2}"/> -->
				<input  type="hidden"  name="f05" id="f05" value="${puk}" />


  		<tr height="40">
          <td width="20%" align="right" style="color: #00F">充值手续费率：</td>
          <td width="5"></td>          
          <td><input  type="text"  placeholder="" id="f15" name="f15" value="${parambean.f15}"></td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
                
        
        
        <tr height="40">
          <td width="20%" align="right" style="color:#F00;">提现手续费(标准)：</td>
          <td width="5"></td>
          <td><input  type="text"  placeholder="" id="f16" name="f16" value="${parambean.f16}"></td>
          <td width="5"></td>
          <td width="30%">（标准工作时间：周一至周五 9点至17点）</td>
        </tr>
        <tr height="40">
          <td width="30%" align="right" style="color:#F00;">提现手续费(休息日)：</td>
          <td width="5"></td>          
          <td><input  type="text"  placeholder="" id="eb3" name="eb3" value="${parambean.eb3}"></td>
          <td width="5"></td>
          <td>（休息时间）</td>
        </tr>
        <tr height="40">
          <td width="30%" align="right" style="color:#F00;">提现手续费(节假日)：</td>
          <td width="5"></td>          
          <td><input  type="text"  placeholder="" id="eb4" name="eb4" value="${parambean.eb4}"></td>
          <td width="5"></td>
          <td>（节假日）</td>
        </tr>



				<tr height="40">
					<th width="20%" align="right"><span style="color:#F00000;">*</span>&nbsp;提现最大金额</th>
					<td width="5"></td>
					<td><input  type="text"  placeholder="" id="f17" name="f17" value="${parambean.f17}">
						<input  type="hidden"  placeholder="" id="f07" name="f07" value="${parambean.puk}">				
					
					<input  type="hidden"  placeholder="" id="uu1" name="uu1" value="${parambean.uu1}">
					</td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
            </form>
			</table>
		</td>
	</tr>
  </table>
</body>
</html>
