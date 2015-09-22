<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/resources/js/validatorMessages.js"></script>
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
		function update(){
			alert("ok");
			 check();
			var f04=document.getElementById('f04');
			
		}
		function check(){
			var newPwd=document.getElementById('ff').value;
			var newpwd=document.getElementById('f04').value;
			if(""==newPwd){
				alert("请输入新密码");			
			}else{
				if(""==newpwd){
					alert("请再次输入密码");
				}else{
					if(newPwd!=newpwd){
						alert("两次输入密码不一致，请重新输入");
					}else{
						document.form1.action = "/WMXG01/U.go?f04="+newpwd;
						form1.submit();
					}					
				}			
			}
		}
	</script>
  </head>
  
  <body>
	<table width="100%" border="0">
		<tr>
			<td>
				<table width="100%" border="0" style="border:1px solid #dbdbdb;">
					<tr style="border:0 none;background-color: #F8F8F8 ">
	                    <td >
	                        <div class="tab-main dark-gray" style="border:0 none;" >
							   <h4 class="tab-main-title">密码修改</h4>
							  </div>
	                    </td>
					</tr>
					<tr>
						<td align="right" style="border-top:1px solid #dbdbdb;">${message}</td>
					</tr>
					<tr>
						<td>
							<form action="" method="post" id="form1" name="form1">
					        <table  align="center" border="0" height="200"> 		        
					        	<tr>
					               <td></td>
					               <td width="10">&nbsp;</td>  
					               <td><input type="hidden" id="puk" name="puk" value="${parambean1.puk}"  style="width: 200px;height: 30px"></td>
					             </tr>
					             <tr>
					               <td style="text-align:right"><label >原密码</label></td>
					               <td width="10">&nbsp;</td>  
					               <td><input type="password" id="f" name="f"  value="${parambean1.f04}" style="width: 200px;height: 30px"></td>
					             </tr>
					             <tr>
					               <td style="text-align:right"><label >新密码</label></td>
					               <td width="10">&nbsp;</td>  
					               <td><input type="password" id="ff" name="ff"  value="" style="width: 200px;height: 30px"></td>
					             </tr>
					             <tr>
					               <td style="text-align:right"><label >重复新密码</label></td>
					               <td width="10">&nbsp;</td>  
					               <td><input type="password" id="f04"  name="f04" value="" style="width: 200px;height: 30px"></td>
					             </tr>  
					        </table>
					        </form>
						</td>
					</tr>
					<tr>
	  				  <td align="right"><div class="control-group">
	         			<div class="controls">
	             			<button type="submit" class="btn btn-primary"  onclick="clearForm()">重置</button>
	             			<button type="submit" class="btn btn-primary"  onclick="update()">保存</button>	             
	            		</div>
	        			</div>
	    			 </td>
					</tr>
					
				</table>
			</td>
		</tr>
	</table>
  </body>
</html>
