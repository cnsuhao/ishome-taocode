<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld"  prefix="pagtag" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">

<script type="text/javascript">
	// 交易记录
	//     function tiaozhuan1() {
	// 		document.form2.action = "";
	// 		form2.submit();
	// 	}
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
	//充值
	function chongzhi() {
	
		document.form02.action = "/WMBM06/H.go";
		form02.submit();
	}
	//提现
	function txin() {
		document.form03.action = "/WMBM07/H.go";
		form03.submit();
	}
	//佣金结算
	function jiesuan() {
		document.form01.action = "/WMZHD1/M.go";
		form01.submit();
	}

	
</script>

<style type="text/css">
.ff{ float:right; margin-left:4px;}


</style>

</head>
<body>
	<table width="100%" border="0">
		<tr>
		<tr>
    <td><table  width="100%" border="0"> 
            <tr>
            <td height="40" align="left" valign="middle">
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title"><div style="font-size:14px; color:#000;">我的账户</div></h3>
      </div></td>
      
        </table>
        </td>
  </tr>
  
  
<tr>
        <td  align=""><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="line4">
  <tr>
<td width="50%" height="82"><div >
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td ><div style="padding:0 20px; border-bottom:1px solid #ccc;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="42%" align="left" valign="middle"><table width="312" border="0" cellspacing="0" cellpadding="0">
                  <tr>

                    <td align="left" valign="middle"><div>
                      <label><span style="font-size:14px; font-weight:bold;">会员等级</span>&nbsp;&nbsp;${wmui01dbo.fb1}&nbsp;&nbsp;&nbsp; </label>
                    </div></td>

                  </tr>
                </table></td>
                <td width="58%" align="left" valign="middle"><table width="410" border="0" cellspacing="0" cellpadding="0" class="line6">

                  <tr>
                 
                    <td align="right" valign="middle"><table width="400" border="0" align="right" cellpadding="0" cellspacing="0">
                      <tr>
                        <td height="40" align="left" valign="middle"><label><strong>安全等级:</strong>&nbsp;&nbsp; </label></td>
                      </tr>
                      <tr>
                        <td height="40" align="left" valign="middle"><label><strong>最后登录地址、时间:</strong>&nbsp;&nbsp;${allowbean.fb2}${allowbean.fb1}&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                      </tr>
                    </table></td>
                  </tr>

                </table></td>
              </tr>
            </table>
          </div></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td align="right">&nbsp;</td>
    </tr>
  </table>
</div>  
  <p> </p>
</td>
  </tr>

		<tr>
			<td align="left" width="767" >

				<div class="control-group">
					<div>
<div>
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
<!-- 			          <td><div style="padding:0 20px;"> -->
<!-- 			            <table width="100%" border="0" cellpadding="0" cellspacing="0"> -->
<!-- 			              <tr> -->
<!-- 			                <td width="42%" height="40" align="left" valign="middle"><table width="312" border="0" align="left" cellpadding="0" cellspacing="0"> -->
<!-- 			                  <tr> -->
<!-- 			                    <td><label> <strong>账户余额</strong> :&nbsp;&nbsp;<a href="javascript:parent.toTargetForm('/WMZHM1/F.go','')"> ${wmui01dbo.f01}</a>&nbsp; 元</label></td> -->
<!-- 			                    </tr> -->
<!-- 		                    </table></td> -->
<!-- 			                <td width="58%" align="left" valign="middle"><table width="410" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 			                  <tr> -->
<!-- 			                    <td align="right" valign="middle"><table width="400" border="0" align="right" cellpadding="0" cellspacing="0"> -->
<!-- 			                      <tr> -->
<!-- 			                        <td align="left" valign="middle"><table width="400" border="0" align="left" cellpadding="0" cellspacing="0"> -->
<!-- 			                          <tr> -->
<!-- 			                            <td> -->
<!-- 			                            <c:if test="${wmui01dbo.k02=='DFB_QT_VIP_1'}"> -->
<!-- 			                            <label> <strong>未结算佣金总额:</strong>&nbsp;&nbsp;<a href="javascript:parent.toTargetForm('/WMZHM1/F.go','')">${wmui01dbo.f02}</a>&nbsp;元</label> -->
<!-- 			                            </c:if> -->
<!-- 			                            </td> -->
<!-- 			                            </tr> -->
			                          </table></td>
<!-- 			                        </tr>	 -->
<!-- 			                      </table></td> -->
<!-- 			                    </tr> -->
<!-- 			                  </table></td> -->
<!-- 		                  </tr> -->
<!-- 		                </table> -->
<!-- 		              </div></td> -->
<!-- 		            </tr> -->
<!-- 			        <tr></tr> -->
<!-- 			        <tr> -->
<!-- 			          <td align="right"><table border="0" cellspacing="0" cellpadding="0"> -->
<!-- 			            <tr> -->
<!-- 			              <td width="198" height="40" align="right" valign="middle"> -->
<!-- 			              <input type="button" class="btn btn-info" value="充值" onclick="parent.toTargetForm('/WMBM06/F.go','/WMBM06/F.go')" /> -->
<!--         					<input type="button" class="btn btn-info" value="提现" onclick="parent.toTargetForm('/WMBM07/F.go','/WMBM07/F.go')" /> -->
<!--         					<c:if test="${wmui01dbo.k02=='DFB_QT_VIP_1'}"> -->
<!--         					<input type="button" class="btn btn-info" value="佣金统计" onclick="parent.toTargetForm('/WMZHM1/F.go','')" /> -->
<!--         					</c:if>						   -->
<!-- 						  </td> -->
<!-- 			              <td width="80" align="left" valign="middle"></td> -->
<!-- 		                </tr> -->
<!-- 		              </table></td> -->
<!-- 	                </tr> -->
<!-- 	              </table> -->
<!-- </div></td> -->
<!-- 		</tr> -->
     
        <tr>
			<td><form name="form1" method="post" action="">
			  <table width="100%" class="table table-hover" >
			    <tr>
			      <td><div style="padding:0 14px; " >
					<input type="button" class="btn btn-info" value="近期充值记录" onclick="parent.toTargetForm('/WMBM02/F.go','/WMBM06/H.go')" />
					<input type="button" class="btn btn-info" value="充值" onclick="parent.toTargetForm('/WMBM06/F.go','/WMBM06/F.go')" />
			        <table width="100%" border="0" class="table table-hover" >
							<tr>
			              <td>订单号</td>
			              <td>充值金额</td>
			              <td>充值状态</td>
			              </tr>
			          <c:forEach var="wmbm02dbo" items="${list1}" end="2">
			            <tr>
			              <td>${wmbm02dbo.puk}</td>
			              <td>${wmbm02dbo.f07}</td>
			              <td>
			                  <c:choose>
		                  	<c:when test="${wmbm02dbo.f06==0}">
		                  			 申请中     			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.f06==1}">
		                  			 失败       			
		                  		</c:when>
		                  		<c:when test="${wmbm02dbo.f06==2}">
		                  			 成功       			
		                  		</c:when>
		                  	 </c:choose>	              
			              </td>
			              </tr>
			            </c:forEach>
			          </table>
			        </div></td>
			      </tr>
			    <tr>
			      <td><div style="padding:0 14px; " >
			      	<input type="button" class="btn btn-info" value="近期提现记录" onclick="parent.toTargetForm('/WMBM03/F.go','/WMBM06/H.go')" />
			      		<input type="button" class="btn btn-info" value="提现" onclick="parent.toTargetForm('/WMBM07/F.go','/WMBM07/F.go')" />
			        <table width="100%" border="0" class="table table-hover" >
			          <tr>
			              <td>订单号</td>
			              <td>提现金额</td>
			              <td>提现状态</td>
			              </tr>
			          <c:forEach var="wmbm03dbo" items="${list2}" end="2">
			            <tr>
			              <td>${wmbm03dbo.puk}</td>
			              <td>${wmbm03dbo.f07}</td>
			              <td>${wmbm03dbo.f14}
			               <c:choose>
		                  		<c:when test="${wmbm03dbo.f06==0}">
		                  			申请中			
		                  		</c:when>
		                  		<c:when test="${wmbm03dbo.f06==1}">
		                  			提现失败			
		                  		</c:when>
		                  		<c:when test="${wmbm03dbo.f06==2}">
		                  			 提现成功      			
		                  		</c:when>
		                  	 </c:choose>
			              </td>
			              </tr>
			            </c:forEach>
			          </table>
			        </div></td>
			      </tr>
			      <c:if test="${wmui01dbo.k02=='DFB_QT_VIP_1'}">
			      <tr>
			      <td><div style="padding:0 14px; " >
				      <input type="button" class="btn btn-info" value="近期佣金记录" onclick="parent.toTargetForm('/WMBM04/F.go','/WMBM06/H.go')" />
        			<input type="button" class="btn btn-info" value="佣金统计" onclick="parent.toTargetForm('/WMZHM1/F.go','')" />
			        <table width="100%" border="0" class="table table-hover" >
					<tr>
			              <td>订单号</td>
			              <td>结算费</td>
			              <td>产生佣金金额</td>
			              </tr>
			          <c:forEach var="wmbm04dbo" items="${list3}" end="2">
			            <tr>
			              <td>${wmbm04dbo.puk}</td>
			              <td>${wmbm04dbo.f06}</td>
			              <td>${wmbm04dbo.f08}</td>
			              </tr>
			            </c:forEach>
			          <!-- 					<form id="formsb01" name="formsb01" action=""> -->
			          <!-- 					<input type="hidden" name="" class="btn btn-info" -->
			          <!-- 								id="button1" value="" /> -->
			          <!-- 					</form>			 -->
			          </table>
			        </div></td>
			      </tr>
			      </c:if>
			    </table>
		    </form></td>
  </tr>
</table>

</body>
</html>
