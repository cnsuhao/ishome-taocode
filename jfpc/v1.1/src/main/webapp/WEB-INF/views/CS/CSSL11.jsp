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
       <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">登录日志一览</span></h3>
      </div>
     </td>
</tr>

<tr>
   <td align="right" valign="middle">
<table  width="100%" border="0" cellpadding="0" cellspacing="0"> 
       <tr>
            <td align="left" style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="F.go" /></td>
        </tr>
 </table>
  </tr>
<tr>
    <td><table border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                            <tr>
                              <th align="center" valign="middle">系统授权编号</th>
                              <th align="center" valign="middle">登录域名</th>							 
                              <th align="center" valign="middle">登录用户名</th>
                              <th align="center" valign="middle">IP地址</th>
                               <th align="center" valign="middle">用户类别</th>
                                <th align="center" valign="middle">登录日时</th>
                            </tr>
		
	                    <c:forEach var="item" items="${pageVO.pageData}">
                            <tr >                         
                              <td align="center" valign="middle">${item.k03}</td>
                              <td align="center" valign="middle">${item.k02}</td>
                              <td align="center" valign="middle">${item.k01}</td>
							  <td align="center" valign="middle">${item.fb2}</td>							  
							   <td align="center" valign="middle"> 
								  <c:if test="${item.fb3=='1'}">
								  超级管理员
								  </c:if>
								  <c:if test="${item.eb4=='1'}">
								  会员
								  </c:if>
								  <c:if test="${item.eb4=='0'&&item.fb3=='0'}">
							      系统用户  
								  </c:if>
						       </td>
							  <td align="center" valign="middle">${item.cc1}</td>							 
							  </tr>
                           </c:forEach>
    </table></td>
</tr> 




</table>
</form>
 </body>
</html>