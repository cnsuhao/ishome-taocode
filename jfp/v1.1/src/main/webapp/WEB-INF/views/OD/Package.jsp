<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>货付宝</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	 
<script src="/resources/js/wm/zk/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"/>
 <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
 <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
<script src="/resources/js/wm/zk/bootstrap.js"></script>
<script type="text/javascript">
	function ch(){
		document.form1.action="/HOMEORDER1/F.go";
		form1.submit();
	} 
</script>
</head>
<body>
<div class="warp">
  <div style="text-align:center;">
  ${list}
	 <form action="/HOMEORDER/A.go" method="post" id="form1" name="form1">
	 <input type="hidden" name="k01" value="${k01}" />
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>	
	         <td align="center" valign="middle">&nbsp;</td>
	      </tr>
	      <tr>
	      	<td>
	      		<table  width="100%" border="1" cellpadding="0" cellspacing="0" class="pack">
	      			<tr  style="height: 50px">
	      				<td  align="center" valign="middle">&nbsp;</td>	   
	      				<td  align="center" valign="middle">
		      				<table  width="100%">
		      					<tr>
		      						<td width="36%" align="right" valign="middle">
		      						<input type="radio" name="radio" id="radio4" value="radio" />
	                  				</td>
	              		  			<td width="64%" >${list2[0].f01}</td>
		      					</tr>
		      				</table>
	      				</td>   
	      				<td  align="center" valign="middle">
	      					<table  width="100%">
		      					<tr>
		      						<td width="36%" align="right" valign="middle">
		      						<input type="radio" name="radio" id="radio4" value="radio" />
	                  				</td>
	              		  			<td width="64%" >${list2[1].f01}</td>
		      					</tr>
		      				</table>
	      				</td>  
	      				<td  align="center" valign="middle">
	      					<table  width="100%">
		      					<tr>
		      						<td width="36%" align="right" valign="middle">
		      						<input type="radio" name="radio" id="radio4" value="radio" />
	                  				</td>
	              		  			<td width="64%" >${list2[2].f01}</td>
		      					</tr>
		      				</table>	      				
	      				</td>  
	      				<td  align="center" valign="middle">
	      					<table  width="100%" border="0">
		      					<tr>
		      						<td width="36%" align="right" valign="middle">
		      						<input type="radio" name="radio" id="radio4" value="radio" />
	                  				</td>
	              		  			<td width="64%" >${list2[3].f01}</td>
		      					</tr>
		      				</table>	      				
	      				</td>  				
	      			</tr>	      		
	      			<c:forEach var="it" items="${list1}">
	      				<tr style="height: 50px">	      					
	      					<td>
	      						${it.f01}  						
	      					</td>
	      					<td>
	      						<c:if test="${not empty it.eb1}">
	      						有
	      						</c:if>	      						
	      					</td>
	      					<td>
	      						<c:if test="${not empty it.eb2}">
	      						有
	      						</c:if>	      					
	      					</td>
	      					<td>
	      						<c:if test="${not empty it.eb3}">
	      						有
	      						</c:if>	      						
	      					</td>
	      					<td>
	      						<c:if test="${not empty it.eb4}">
	      					有
	      						</c:if>	      						
	      					</td>
	      				</tr>	      				
	      			</c:forEach>
	      		</table>
	      	</td>
	      </tr>
	      <tr>
	        <td valign="middle" >
	        	<table  width="30%" border="1" cellpadding="0" cellspacing="0" class="pack"
	        	style="margin-left: 800px;">
	        		<tr style="height: 50px;" >
	        			<td>总价</td><td>&nbsp;</td>
	        		</tr>
	        		<tr style="height: 50px;">
	        			<td>成交价</td><td>&nbsp;</td>
	        		</tr>
	        	</table>
	        </td>
	      </tr>
	      <tr>
	      	<td align="center" valign="middle">&nbsp;</td>
	      </tr>
	      <tr>
	        <td align="center" valign="middle">
	        	<table width="50%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><div class="infor_an">
			            <input type="button" class="btn btn-primary" value="自助选择" onclick="ch()"/>
			<!--             <a href="#" class="anniu">自助选择</a> -->
			            </div></td>
			            <td><div class="infor_an">
			            	<input type="submit" value="提交订单" id="btn" />
			<!--             <a href="#" class="anniu">提交订单</a> -->
			            </div>
			            </td>
			          </tr>
			        </table>        
	        </td>
	      </tr>
	    </table>
  </form>
  </div>
</div>
</body>
</html>
