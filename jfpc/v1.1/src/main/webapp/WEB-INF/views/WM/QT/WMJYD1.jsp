<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link href="../../../../resources/css/wm/qt/global.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	//交易记录
    function tiaozhuan1() {
		document.form1.action = "";
		form1.submit();
	}
	//充值记录
	 function tiaozhuan2() {
		document.form2.action = "/WMBM02/F.go";
		form2.submit();
	}
	//体现记录
	 function tiaozhuan3() {
		document.form3.action = "/WMBM03/F.go";
		form3.submit();
	}
	//佣金记录
	 function tiaozhuan4() {
		document.form4.action = "/WMBM04/F.go";
		form4.submit();
	}
	</script>
 </head>
 <body>
 <table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
<tr>
    <td><table  width="100%" border="0"> 
            <tr>
            <td height="40">
            <div>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><span style=" font-size:16px; font-weight:bold; padding-left:20px; color:#000;">交易管理</span></td>
  </tr>
</table>

      </div></td>
      
        </table>
        </td>
  </tr>

<tr>
<td align="left">

    </td>
</tr> 

<!-- <tr>   -->
<!--     <td><div style="padding:0 20px;"><table class="table table-hover"> -->
<!-- 	<form id="form1" name="form1" method="POST" action=""> -->
<!-- 	<input type="button" name="button" class="btn btn-info" value="近期交易记录" onclick="location.href=('wm/WMJYC1.jsp')"/> -->
<!-- 	 <c:forEach var="wmbm02dbo" items="${list1}" end="3"> -->
                            
<!--                             <tr> -->
<!--                               <td>${wmbm02dbo.k01}</td> -->
<!--                               <td>${wmbm02dbo.k02}</td> -->
<!--                               <td>${wmbm02dbo.k03}</td> -->
<!-- 						    </tr> -->
<!--      </c:forEach> -->

<!--  </form> -->
<!--   </table></div> -->
<!--  </td> -->

<!--  </tr> -->
 
 <tr>  
    <td><form id="form2" name="form2" method="POST" action=""><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
      
        <div style=" text-align:right; height:40px; padding:10px 100px 0 0;"><input type="button" name="button" class="btn btn-info" id="button" value="近期充值记录" onclick="tiaozhuan2()"/></div>
        <tr>
          <td align="center"><strong>通道名称</strong></td>
          <td align="center"><strong>充值金额</strong></td>
          <td align="center"><strong>手续费</strong></td>
          </tr>
        <c:forEach var="wmbm02dbo" items="${list1}" end="2">
          
          <tr>
            <td>${wmbm02dbo.fb1}</td>
            <td>${wmbm02dbo.f07}</td>
            <td>${wmbm02dbo.f15}</td>
            </tr>
          </c:forEach>
    </table> </form>
</td>

 </tr>



<tr>  
    <td>  <form id="form3" name="form3" method="POST" action=""><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">

    <div style=" text-align:right; height:40px; padding:10px 100px 0 0;"><input type="button" name="button" class="btn btn-info" id="button" value="近期提现记录" onclick="tiaozhuan3()"/></div>
    <tr>
      <td align="center"><strong>客户名称</strong></td>
      <td align="center"><strong>提现金额</strong></td>
      <td align="center"><strong>提现手续费</strong></td>
      </tr>
    <c:forEach var="wmbm03dbo" items="${list2}" end="2">
      
      <tr>
        <td>${wmbm03dbo.f03}</td>
        <td>${wmbm03dbo.f07}</td>
        <td>${wmbm03dbo.f15}</td>
        </tr>
      </c:forEach>
    </table></form>
</td>

 </tr>



<tr>  
    <td><form id="form4" name="form4" method="POST" action=""><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
  
    <div style=" text-align:right; height:40px; padding:10px 100px 0 0;"><input type="button" class="btn btn-info" name="button" id="button" value="近期佣金记录" onclick="tiaozhuan4()"/></div>
    <tr>
      <td align="center"><strong>订单号</strong></td>
      <td align="center"><strong>结算费</strong></td>
      <td align="center"><strong>产生佣金金额</strong></td>
      </tr>
    <c:forEach var="wmbm04dbo" items="${list3}" end="2">
      
      <tr>
        <td>${wmbm04dbo.f04}</td>
        <td>${wmbm04dbo.f06}</td>
        <td>${wmbm04dbo.f08}</td>
        </tr>
      </c:forEach>
  
</table>  
  </form>
					     </td>
                        </tr> 
</table>
</body>
</html>
