<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>费率查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
    
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
	<script type="text/javascript" src="/resources/js/wm/jquery-1.8.3.js"></script>
	<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"/>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
	function search(){
		form1.submit();
	}
	function cx(){
		document.form1.action="/WMJG02/F.go";
		form1.submit();
	}
	</script>
  </head>
  <body>
  <form action="" method="post" id="form1" name="form1">	
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">查询费率</h4></td>
	</tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;"></td>
		    <td align="right">
<!-- 		    <form action="" method="post" id="form2" name="form2"> -->
<!-- 					<input type="button" value="清空"  class="btn btn-default btn-sm" onclick="parent.toTargetForm('/WMJGY1L/H.go','${bbb}')"> -->
					<input type="button" value="查询" class="btn btn-default btn-sm"  onclick="cx()">
<!--            </form> -->
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
		
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr height="40">
					<td width="20%" align="right">授权编号</td>
					<td width="5"></td>
					<td>
					<input type="text"  class="form-control input-sm" id="k01" name="k01" value="" style="width: 200px;" />
					</td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>
				<tr height="40">
					<td width="20%"  align="right" >授权域名</td>
					<td width="5"></td>
					<td>
					<input type="text" class="form-control input-sm" id="k02" name="k02" value="" style="width: 200px;" />
					</td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>
				<tr height="40">
					<td width="20%" align="right">会员名称</td>
					<td width="5"></td>
					<td>
					<input type="text" class="form-control input-sm" id="f04" name="f04" value="" style="width: 200px;"/>
					</td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>				
				<tr height="40">
					<td width="20%" align="right">支付通道名称</td>
					<td width="5"></td>
					<td>
					<select id="fb1" name="fb1" style="width: 200px; " >
<!-- 					multiple="multiple"  size=""> -->
	        			<option value="">选择通道名称</option>
	        			<c:forEach var="item" items="${listWmbma1}">
	        			<option value="${item.puk}">${item.f03}</option>	        			
	        			</c:forEach>	        			
	      			</select>
					</td>
					<td width="5">&nbsp;</td>
					<td></td>
				</tr>			
<!-- 				<tr height="40"> -->
<!-- 					<td width="20%" align="right">登录用户名</td> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td><input type="text"  class="form-control input-sm" id="f02" name="f02" value="" style="width: 200px;" ></td> -->
<!-- 					<td width="5">&nbsp;</td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
<!-- 				<tr height="40"> -->
<!-- 					<td width="20%" align="right">会员名字：</td> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td><input type="text"  class="form-control input-sm" id="f03" name="f03" value=""  style="width: 200px;" ></td> -->
<!-- 					<td width="5">&nbsp;</td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->

<!-- 				<tr height="40"> -->
<!-- 					<td width="20%" align="right">查询日期：</td> -->
<!-- 					<td width="5"></td> -->
<!-- 					<td> -->
<!-- 					 <input type="text" class="form-control input-sm "  id="f05" name="f05" value="" style="width: 200px;"             -->
<!--              onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()">     				 -->
<!-- 				<input type="text"  class="form-control input-sm" id="f05" name="f05" value="" size="" style="width: 200px;" > -->
<!-- 				 ～ <input type="text"  class="form-control input-sm" id="f06" name="f06" value="" size="" style="width: 200px;"> -->
<!-- 					 </td> -->
<!-- 					<td width="5">&nbsp;</td> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
			</table>			
		</td>
	</tr>
  </table>
  </form>
</body>
</html>
