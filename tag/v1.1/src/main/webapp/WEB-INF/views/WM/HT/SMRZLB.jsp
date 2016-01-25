<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>
 <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
  <sf:form method="get" modelAttribute="param" action="/WMSZT3/N.go">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td align="left" valign="top"><div class="tab-main dark-gray">
                   <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">实名认证列表</span></h3>
                 </div></td>
       </tr>
       <tr>
         <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
           <tr>
             <td align="left" valign="top"><table width="800" border="0" cellpadding="0" cellspacing="0">
               <tr>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
               </tr>
               <tr>
                 
                 <td width="192">&nbsp;</td>
                 <td width="202">&nbsp;</td>
                 <td width="113">&nbsp;</td>
                 <td width="328">&nbsp;</td>
               </tr>
               <tr>
                 <td width="192" height="40" align="right"><strong>用户名： </strong></td>
                 <td align="left"><input type="text" name="bbb" value="${param.bbb }"></td>
                 <td align="right"><strong>实名认证结果：</strong></td>
                 <td align="left"><sf:select path="f18">
                   <sf:option value="">请选择</sf:option>
                   <sf:option value="0">申请中</sf:option>
                   <sf:option value="1">认证失败</sf:option>
                   <sf:option value="2">认证成功</sf:option>
                 </sf:select></td>
               </tr>
               <tr>
                 <td width="192" height="40" align="right"><strong>会员名称：</strong>
                   <table  width="100%" border="0" cellpadding="0" cellspacing="0">
                     <tr>
                       <td></td>
                    </table></td>
                 <td align="left"><input type="text" name="f04" value="${param.f04 }"></td>
                  <td width="192" height="40" align="right"><strong>上级代理商</strong>： </td>
                 <td align="left"><input type="text" name="k01" value="${param.k01 }"></td>
               </tr>
              
               <tr>
                 <td height="40" colspan="4" align="right" ><div style="padding:0 90px 0 0; "><input type="submit" value="查询"></div></td>
                </tr>

      
       
             </table></td>
           </tr>
           <tr>
             <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                 <td align="left" style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="N.go" /></td>
                </tr>
             </table></td>
           </tr>
         </table></td>
       </tr>
       <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                            <tr>
                              <th align="center" valign="middle">编号</th>
                                <th align="center" valign="middle">上级代理商</th>
                              <th align="center" valign="middle">用户名</th>
                              <th align="center" valign="middle">会员名称</th>
                              <th align="center" valign="middle">实名认证结果</th>
							 
                              <th align="center" valign="middle">申请时间</th>
                                                              
                               <th colspan="2" align="center" valign="middle" class="table-th-do">操作</th> 
                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                             <td align="center" valign="middle">${zftd.puk}</td>
                             <td align="center" valign="middle">${zftd.k01}</td>
                              <td align="center" valign="middle">${zftd.bbb}</td>
                              <td align="center" valign="middle"> <a href="/WMKLH1/${zftd.puk}/R.go">${zftd.f04}</a></td>
                              
                               <c:choose>
							  <c:when test="${zftd.f18=='0'}">
							    <td align="center" valign="middle">申请中</td>
							  </c:when>
							   <c:when test="${zftd.f18=='1'}">
							     <td align="center" valign="middle">认证失败</td>
							  </c:when>
							  <c:when test="${zftd.f18=='2'}">
							     <td align="center" valign="middle">认证成功</td>
							  </c:when>
							  
							  <c:otherwise>
							  <td align="center" valign="middle">无</td>
							  </c:otherwise>
							  </c:choose>
							   

							  <td align="center" valign="middle">${zftd.cc1}</td>
							  						
							
                            <td align="center" valign="middle"><a href="${zftd.puk}/X.go">实名详情</a></td>          
					    
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</sf:form>
 </body>
</html>