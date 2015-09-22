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

 function checkPassword(){
	var val=  document.getElementById('f04').value;
	var val_repeate =  document.getElementById('f04_repeat').value;
    if(val!=val_repeate){
    	document.getElementById('tips').innerHTML='2次密码不一致';
    }else{
    	var acti = document.form01.action;
    	$.ajax({
    	    url: acti,
    	    type: 'POST',
    	    data:{'f04':val},
    	    timeout: 1000,
    	    error: function(){
    	    	document.getElementById('tips').innerHTML='服务器错误';

    	    },
    	    success: function(str){
    	       if(str=="success"){
       	    	document.getElementById('tips').innerHTML='密码修改成功!';

    	       }else{
          	    	document.getElementById('tips').innerHTML='修改密码失败!';
    	       }
    	    }
    	});
    	
    	
    	// document.form01.submit();    	
    }
 }


</script>
  </head>
  <body>
  <table width="100%" border="0" align="center" style="margin:0; padding:0;" >
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">密码修改</h4></td>
	</tr>
	<tr style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;" id="tips"></td>
		    <td align="right">			
				<button type="button" id="edit" class="btn btn-info" onclick="checkPassword()">确认</button>
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td valign="top">
			<table width="100%" border="0" align="center" style="margin:0; padding:0;">
			<form id="form01" name="form01" method="post" action="/CSSR01/${userInfo.puk}/du.go" >
				<tr height="36">
					<td width="30%" align="right"><b>密码</b></td>					
                    <td width="10"></td>
					<td width="50%"> 
				       <input type="password" name="f04" id="f04" value="" />
                    </td>
                    <td width="5"></td>
                    <td></td>
				</tr>
				<tr height="36">
					<td width="30%" align="right"><b>确认密码</b></td>					
                    <td width="10"></td>
					<td width="50%"> 
					 <input type="password" name="f04_repeat" id="f04_repeat" value="" />
                    </td>
                    <td width="5"></td>
                    <td></td>
				</tr>
				</form>
			</table>
	  </td>

	</tr>
  </table>
</body>
</html>
