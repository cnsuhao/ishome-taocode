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
    <td><table  width="100%" border="0" cellpadding="0" cellspacing="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">审核银行账户</span></h3>
      </div></td>
      
        </table>
        </td>
  </tr>
<tr>
<td align="left" style="padding:20px;">
	
	<pagtag:pagtag pageVo="${pageVO}" url="Z.go" />
    </td>
</tr> 

<tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                            <tr>
                              <th align="center" valign="middle">会员姓名</th>
                              <th align="center" valign="middle">银行名称</th>
                              <th align="center" valign="middle">分行名称</th>
                              <th align="center" valign="middle">开户行名称</th>
							  <th align="center" valign="middle">银行户名</th>
							   <th align="center" valign="middle">银行账号</th>                                                              
                               <th colspan="2" align="center" valign="middle" class="table-th-do">操作</th> 
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                             <td align="center" valign="middle">${zftd.k01}</td>
                              <td align="center" valign="middle">${zftd.f02}</td>
                               <td align="center" valign="middle">${zftd.fb3}</td>
                              <td align="center" valign="middle">${zftd.f03}</td>
							  <td align="center" valign="middle">${zftd.f01}</td>
							  <td align="center" valign="middle">${zftd.k02}</td>
							  						
							
                            <td align="center" valign="middle"><a href="${zftd.puk}/K.go">审核未通过</a></td>
          
                            <td align="center" valign="middle"><a href="${zftd.puk}/M.go">审核通过</a></td>
					    
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</form>
 </body>
</html>