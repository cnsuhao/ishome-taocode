<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>系统画面菜单</title>
    
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
	function search(){
			document.form1.action = "/WMBM10/R.go";
			form1.submit();
		}
	function clearForm(){
		
			var formObj=document.getElementById('form1');
			if(formObj==undefined){
				return;
			}
			
			for(var i=0; i<formObj.elements.length; i++){
				if(formObj.elements[i].type == "text"){
					formObj.elements[i].value = ""; 
				}else if(formObj.elements[i].type == "password"){
					formObj.elements[i].value = "";  
				}else if(formObj.elements[i].type == "radio"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "checkbox"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "file"){
					var file = formObj.elements[i]; 
					if (file.outerHTML){
						file.outerHTML = file.outerHTML;
					}else{
						 file.value = ""; 
					}
				}else if(formObj.elements[i].type == "textarea"){
					formObj.elements[i].value = "";
				}else if(formObj.elements[i].type == "select"){
					formObj.elements[i].options[0].selected = true;
				}else if(formObj.elements[i].type == "hidden"){
					formObj.elements[i].value = "";
				}
			}
		}
// 	function add(){
// 			document.form1.action = "/PTCP/C.go";
// 			form1.submit();
// 		}
</script>
<body>
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
	<form action="" method="post" id="form1" name="form1">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">产品查询条件</h4></td>
	</tr>
	<tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">提示信息</td>
		    <td align="right">
					  <button type="submit" class="btn btn-default btn-sm" onclick="clearForm()">清空</button>
					  <button type="submit" class="btn btn-primary btn-sm" onclick="search()">查询</button>	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr>
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr height="40">
					<td width="20%" align="right">业务系统ID：</td>
					<td width="5"></td>
					<td><input type="text" class="form-control input-sm" id="eb5" name="eb5" value="${parambean1.eb5}"></td>
					<td width="5"></td>
					<td width="30%">备注</td>
				</tr>
				<tr height="40">
					<td width="20%" align="right"></td>
					<td width="5"></td>
					<td></td>
					<td width="5"></td>
					<td width="30%"></td>
				</tr>
			</table>
		</td>
	</tr>
	</form>
  </table>
</body>
</html>
<!-- 	<table width="100%" border="0"> -->
<!-- 		<form action="" method="post" id="form1" name="form1"> -->
<!-- 		<tr height="40"><td><table  width="100%" border="0">  -->
<!--             <tr><td> -->
<!--             	<div class="tab-main dark-gray"> -->
<!--        				<h3 class="tab-main-title">产品查询条件</h3> -->
<!--       			</div> -->
<!--         </table></td></tr> -->
        
<!--         <tr style="border-top:1px solid #dbdbdb;"> -->
<!--         	<td> -->
<!--             	<table align="center" border="0" width="100%">						 -->
<!-- 					<tr height="30"><td colspan="3"></td></tr> -->
<!-- 					<tr height="50"> -->
<!--                    		<td width="10%" align="right"> -->
<!-- 							<label class="control-label">下拉选框</label> -->
<!-- 						</td> -->
<!-- 						<td style="padding:0 10px;"> -->
<!-- 							<select class="form-control" id="f03" name="f03" > -->
<!-- 								<option>支付通道名称</option> -->
<!-- 								<c:forEach var="parambean1" items="${list}"> -->
<!-- 	        						<option value="${parambean1.f03}">${parambean1.f03}</option> -->
<!-- 	        					</c:forEach> -->
<!-- 							</select> -->
<!-- 						</td> -->
<!--                   	</tr> -->
<!--                   	<tr height="10"><td colspan="2"></td></tr> -->
<!--           		</table> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr style="border-top:1px solid #dbdbdb;"> -->
<!-- 			<td> -->
<!--             	<table align="center" border="0" width="100%">						 -->
<!-- 					<tr height="30"><td colspan="3"></td></tr> -->
<!-- 					<tr height="50"> -->
<!-- 						<td align="right"><label >系统识别ID</label></td> -->
<!--                			<td style="padding:0 10px;"><input type="text" id="puk" name="puk" value="${parambean1.puk}"></td> -->
<!--                		</tr> -->
<!--                   	<tr height="10"><td colspan="2"></td></tr> -->
<!--           		</table> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr style="border-top:1px solid #dbdbdb;"> -->
<!-- 			<td> -->
<!--             	<table align="center" border="0" width="100%"> -->
<!-- 					<tr height="30"><td colspan="3"></td></tr> -->
<!-- 					<tr height="50"> -->
<!-- 						<td align="right"><label>运营负责人</label></td>  -->
<!-- 						<td style="padding:0 10px;"><input type="text" id="k03" name="k03" value="${parambean1.k03}" ></td> -->
<!--                		</tr> -->
<!--                   	<tr height="10"><td colspan="2"></td></tr> -->
<!--           		</table> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--  			<td align="right"> -->
<!--            		<table align="center" width="100%" border="0"> -->
<!-- 					<tr height="20"><td colspan="4"></td></tr> -->
<!--                   	<tr height="50"> -->
<!-- 						<td align="right" width="20%"></td> -->
<!-- 						<td width="55%"></td> -->
<!-- 						<td width="15%"> -->
<!-- 							<div class="controls"> -->
<!-- 								<form action="" method="post" id="form1" name="form1"> -->
<!--              						<button type="submit" class="btn btn-info" onclick="clearForm()">清空</button>&nbsp; -->
<!--              						<button type="submit" class="btn btn-info" onclick="search()">查询</button> -->
<!--              					</form> -->
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 						<td width="10%"></td> -->
<!-- 					</tr>	 -->
<!-- 					<tr height="20"><td colspan="4"></td></tr> -->
<!-- 				</table>	 -->
<!--         	</td> -->
<!--        	</tr> -->
<!--        	</form> -->
<!--    </table> -->
<!-- </body> -->
<!-- </html> -->
