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
	
	<script type="text/javascript">
	function doCheckOrder(val){
	 	var acti = "/WMBM06/CheckOrder.go";
    	$.ajax({
    	    url: acti,
    	    type: 'POST',
    	    data:{'recordid':val},
    	    timeout: 10000,
    	    error: function(){
    	    	alert('服务器错误');
    	    },
    	    success: function(str){
    	       if(str=="success"){
       	    	  alert('对账完成');
    	       }else{
    	    	   alert('对账出场');
    	       }
    	    }
    	});

	}
 
 </script>
 </head>
 <body>
 
 <sf:form method="get" modelAttribute="param" action="F.go">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
 
       <tr>
         <td align="left" valign="top"><div class="tab-main dark-gray">
                   <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">充值明细</span></h3>
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
                <td width="192" height="40" align="right"><strong>订单号： </strong></td>
                 <td align="left"><input type="text" name="puk" value="${param.puk }"></td>
                 <td width="192" height="40" align="right"><strong>用户名： </strong></td>
                 <td align="left"><input type="text" name="f01" value="${param.f01 }"></td>
                
               </tr>
               <tr>
                 <td width="192" height="40" align="right"><strong>会员名称：</strong>
                   <table  width="100%" border="0" cellpadding="0" cellspacing="0">
                     <tr>
                       <td></td>
                    </table></td>
                 <td align="left"><input type="text" name="f02" value="${param.f02 }"></td>
                 <td width="192" height="40" align="right"><strong>通道名称</strong>： </td>
                 <td align="left"><input type="text" name="fb1" value="${param.fb1 }"></td>
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
                 <td align="left" style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="F.go" /></td>
                </tr>
             </table></td>
           </tr>
         </table></td>
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
                                <th>手动对账</th>
                                 <th>第三方状态</th>
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
							  <td>
							   <a href="javascript:doCheckOrder('${zftd.puk}')">手动对账</a>
							  </td>
							<td>${zftd.f17}</td>			   		    
							  </tr>
                           </c:forEach>
     </table></td>
</tr> 




</table>
</sf:form>

 </body>
</html>