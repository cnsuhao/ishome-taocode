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
       <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">支付通道</span></h3>
      </div>
        </td>
  </tr>
<tr>
<td align="left" style=" padding:20px;">
	<pagtag:pagtag pageVo="${pageVO}" url="F.go" />
    </td>
</tr> 

<tr>
    <td><table class="table table-hover" border="0">
                            <tr>
                          
                              <th align="center" valign="middle">编号</th>
                            <!--   <th align="center" valign="middle">客户名称</th> -->
                              <th align="center" valign="middle">支付通道</th>
                                 <th align="center" valign="middle">原通道名称</th>
							 
                          
					            <th align="center" valign="middle">充值费率</th>
					            <th align="center" valign="middle">提现费用<br>(标准)</th>
					            <th align="center" valign="middle">提现费用<br>(休息日)</th>
					            <th align="center" valign="middle">提现费用<br>(节假日)</th>
                               <th align="center" valign="middle">单笔结算上限</th>
                                <th align="center" valign="middle">提现说明</th>
        					 <th align="center" valign="middle">操作</th> 
        					 <th align="center" valign="middle">报表查看</th> 
							<th align="center" valign="middle">开关</th>
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                             <td align="center" valign="middle">${zftd.puk}</td>
                            <%--   <td align="center" valign="middle">${zftd.f01}</td> --%>
                              <td align="center" valign="middle" title="${zftd.f05}">${zftd.f03}</td>
                               <td align="center" valign="middle">${zftd.fb1}</td>
                               

              <td align="center" valign="middle" style="color: #00F">${zftd.f15}%</td>
              <td align="center" valign="middle" style="color:#F00;">${zftd.f16}</td>
              <td align="center" valign="middle" style="color:#F00;">${zftd.eb1}</td>
              <td align="center" valign="middle" style="color:#F00;">${zftd.eb2}</td>
							   
							  <td align="center" valign="middle">${zftd.f17}</td>
							   <td align="center" valign="middle">${zftd.f19}</td>
							  
							 <td align="center" valign="middle">
							 <a href="${zftd.puk}/U.go">修改</a><br>
		 <%--					 <a href="${zftd.puk}/DF.go">动态费率</a>&nbsp;&nbsp; --%>
							 <a href="${zftd.puk}/E.go">提现待结算款项</a><br>
							 <a href="${zftd.puk}/L.go">提现结算记录</a><br>
							  <a href="${zftd.puk}/J.go">佣金待结算款项</a><br>
							   <a href="${zftd.puk}/N.go">佣金结算记录</a><br>
							 </td>
							 
							  <td align="center" valign="middle">
							 <a href="${zftd.puk}/R.go">日利润统计</a><br>
							 <a href="${zftd.puk}/Z.go">周利润统计</a><br>
							 <a href="${zftd.puk}/Y.go">月利润统计</a>
							 </td>
						     
                           <c:if test="${zftd.f06=='1'}">
                            <td align="center" valign="middle"><a href="${zftd.puk}/C.go">通道已关</a></td>
                           </c:if>
                           
                            <c:if test="${zftd.f06=='0'}">
                            <td align="center" valign="middle"><a href="${zftd.puk}/O.go">通道已开</a></td>
                           </c:if>
                           
                          
                           
                          <%--  <c:if test="${zftd.f06=='0'}">
                            <td><a href="${zftd.puk}/C.go">通道隐藏</a></td>
                           </c:if>
                           
                           
                            <td><a href="${zftd.puk}/Z.go">通道正常</a></td>
							  --%>
							 
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</form>
 </body>
</html>