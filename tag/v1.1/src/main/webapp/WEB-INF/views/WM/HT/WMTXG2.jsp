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
<script  src="/resources/js/My97DatePicker/WdatePicker.js"></script>
	
	
 </head>
 <body>
 
   
 <sf:form method="get"  action="T.go">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr>
         <td align="left" valign="top"><div class="tab-main dark-gray">
                   <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">提现统计</span></h3>
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
                 <td align="left"><input type="text" name="f01" value="${param.f01 }"></td>
                  <td width="192" height="40" align="right"><strong>会员名称： </strong></td>
                 <td align="left"><input type="text" name="f02" value="${param.f02 }"></td>
                
               </tr>               
               <tr>
                 <td width="192" height="40" align="right"><strong>起始时间：</strong>： </td>
                 <td align="left"><input type="text"  class="form-control input-sm" id="f04" name="f04" value="${param.f04 }" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
                 <td align="right"><strong>结束时间： </strong></td>
                 <td align="left"><input type="text"  class="form-control input-sm" id="f06" name="f06" value="${param.f04 }" size="" style="width: 200px;" onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()" ></td>
               </tr>
               <tr>
                 <td height="40" colspan="4" align="right" ><div style="padding:0 90px 0 0; "><input type="submit" value="查询"></div></td>
                </tr>

      
       
             </table></td>
           </tr>
           <tr>
             <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                 <td align="left" style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="T.go" /></td>
                </tr>
             </table></td>
           </tr>
         </table></td>
       </tr>
 
<tr>
    <td><table class="table table-hover">
                            <tr>
                               <th>日期</th>
                                <th>用户名</th>
                              <th>会员姓名</th>
							 
                              <th>提现总金额</th>
                               <th>实得总金额</th>

                            </tr>
		
	                    <c:forEach var="zftd" items="${pageVO.pageData}">
                            <tr >
                         
                              <td>${zftd.f01}</td>
                              <td>${zftd.eb1}</td>
                              <td>${zftd.f02}</td>
                              <td>${zftd.f07}</td>
							  <td>${zftd.f18}</td>						  		    
						    
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</sf:form>
 </body>
</html>