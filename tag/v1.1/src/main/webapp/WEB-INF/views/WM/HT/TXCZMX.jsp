<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>
<!doctype html>
<html lang="en">
 <head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
	
 </head>
 <body>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
 <tr>
    <td><div class="tab-main dark-gray">
       <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">充值明细</span></h3>
      </div>
        </td>
  </tr>
<tr>
	<td align="left">	
	<table  width="100%" border="0" cellpadding="0" cellspacing="0"> 
       <tr>
            <td style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="X.go" /></td>
          
        </tr>
       
        </table>
    </td>
</tr> 

<tr>
    <td><table class="table table-hover">
                            <tr>
                               <th>订单号</th>
                               
                               <th>用户名</th>
                              <th>会员名称</th>
                              <th>交易金额</th>
							 
                              <th>实得金额</th>
                              <th>支付通道</th>
                               <th>时间</th>
                                <th>支付状态</th>
 
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                             <td>${zftd.puk}</td>
                              <td>${zftd.eb1}</td>
                              <td>${zftd.f02}</td>
                              <td>${zftd.f07}</td>
							  <td>${zftd.f18}</td>
							  
							   <td>${zftd.fb1}</td>
							  <td>${zftd.eb4}</td>
							  <td>
							  <c:if test="${zftd.f06=='0'}">
							         申请中
							  </c:if>
							   <c:if test="${zftd.f06=='1'}">
							   失败
							    </c:if>
							   <c:if test="${zftd.f06=='2'}">
							   成功
							    </c:if>
							  </td>
							  
													   		    
						    
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</form>
 </body>
</html>