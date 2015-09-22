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
    <td><table  width="100%" border="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title">待结算记录</h3>
      </div></td>
      <tr>
        <td align="right"><a href="S.go">结算并生成委托文件</a></td>
        </tr>
        </table>
        </td>
  </tr>
<tr>
<td align="left">
	
	<pagtag:pagtag pageVo="${pageVO}" url="J.go" />
    </td>
</tr> 

<tr>
    <td><table class="table table-hover">
                            <tr>
                              <th>订单号</th>
                              <th>银行户名</th>                               
                              
                              <th>银行账号</th>
                              
                              <th>银行名称</th>
                              <th>分行名称</th>
                              
                              
                              <th>开户行名称</th>
                              <th>交易金额</th>
							 
                              <th>实得金额</th>
                              <th>支付通道</th>
                               <th>时间</th>
                                <th>状态</th>
                                 <th>操作</th>
  
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                             <td>${zftd.puk}</td>
                              <td>${zftd.f03}</td>
                               <td>${zftd.f01}</td>
                               
                              
                              <td>${zftd.f02}</td>
                              
                               <td>${zftd.fb3}</td>
                              
                              
							  <td>${zftd.f04}</td>
                              
                              <td>${zftd.f07}</td>
							  <td>${zftd.f18}</td>
							  
							   <td>${zftd.k02}</td>
							  <td>${zftd.cc1}</td>
							  <c:if test="${zftd.f06=='0'}">
							  <td>申请中</td>
							  </c:if>
							  <c:if test="${zftd.f06=='2'}">
							  <td>已结算</td>
							  </c:if>
							   <c:if test="${zftd.f06=='1'}">
							  <td>结算失败</td>
							  </c:if>
					           <td><a href="/WMSZT2/${zftd.puk}/M.go">查看明细</a></td>
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</form>
 </body>
</html>