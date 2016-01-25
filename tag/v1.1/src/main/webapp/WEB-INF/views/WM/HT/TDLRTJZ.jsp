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
  <table width="100%" border="0">
 <tr>
    <td><div class="tab-main dark-gray">
       <h3 class="tab-main-title">周利润统计</h3>
      </div>
        </td>
  </tr>
<tr>
<td align="left">
	<pagtag:pagtag pageVo="${pageVO}" url="R.go" />
    </td>
</tr> 

<tr>
    <td><table class="table table-hover" border="0">
                            <tr>
                          
                              <th align="center" valign="middle">统计时间</th>
                              <th align="center" valign="middle">交易总额</th>
                                 <th align="center" valign="middle">代理提成</th>
							 
                              <th align="center" valign="middle">交易利润</th>
                              <th align="center" valign="middle">提现金额</th>
                               <th align="center" valign="middle">提现利润</th>
                                <th align="center" valign="middle">利润总额</th>
        					
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                            <td align="center" valign="middle">${zftd.cc1}</td>
                               <td align="center" valign="middle">${zftd.f01}</td>
							  <td align="center" valign="middle">${zftd.f05}</td>
							  
							   <td align="center" valign="middle">${zftd.f02}</td>
							  <td align="center" valign="middle">${zftd.f03}</td>
							   <td align="center" valign="middle">${zftd.f04}</td>
							    <td align="center" valign="middle">${zftd.f06}</td>
							  
							  
						
							 
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</form>
 </body>
</html>