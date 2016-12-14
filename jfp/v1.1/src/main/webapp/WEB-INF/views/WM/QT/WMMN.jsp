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

	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">	
	<script src="/resources/js/wm/qt/jquery.js"></script>
    <script src="/resources/js/wm/qt/bootstrap.min.js"></script>
<script type="text/javascript">
	// 交易记录
	//     function tiaozhuan1() {
	// 		document.form2.action = "";
	// 		form2.submit();
	// 	}
	//充值记录
	function tiaozhuan2() {
		document.form02.action = "/WMBM02/F.go";
		form02.submit();
	}
	//体现记录
	function tiaozhuan3() {
		document.form02.action = "/WMBM03/F.go";
		form02.submit();
	}
	//佣金记录
	function tiaozhuan4() {
		document.form02.action = "/WMBM04/F.go";
		form02.submit();
	}
	//充值
	function chongzhi() {

		document.form01.action = "/WMBM06/H.go";

		form01.submit();
	}
	//提现
	function txin() {
		document.form01.action = "/WMBM07/H.go";
		form01.submit();
	}
	//佣金结算
	function jiesuan() {

		document.form01.action = "/WMZHD1/M.go";
		form01.submit();
	}
</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="line3" >
		<tr>
		<tr>
			<td><table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td >
                        <div style="padding:0 20px; border-bottom:1px solid #ccc;">
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
                                      <td height="40" align="left" valign="middle"><label><strong>安全等级:</strong>&nbsp;&nbsp;普通</label></td>
                                    </tr>
                                    <tr>
                                      <td height="40" align="left" valign="middle"><label><strong>最后登录地址、时间:</strong>&nbsp;&nbsp; ${allowbean.fb2}${allowbean.fb1} &nbsp;&nbsp;&nbsp;&nbsp;</label></td>
                                    </tr>
                                  </table></td>
                                </tr>
                              </table></td>
                            </tr>
                          </table>
                        </div>
                        </td>
					</tr>

				</table></td>
		</tr>

		<tr>
			<td align="left">
            <div >
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="line5">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="42%" height="40" align="left" valign="middle">
        <label> <span style="padding:0 20px; font-weight:bold;">账户余额 :&nbsp;</span> &nbsp; <a href="javascript:parent.toTargetForm('/WMZHM1/F.go','')">${wmui01dbo.f01}</a>&nbsp; 元</label></td>
        <td width="58%" align="left" valign="middle"><table width="420" border="0" align="left" cellpadding="0" cellspacing="0">
          <tr>
            <td width="12">&nbsp;</td>
            <td>
            <c:if test="${wmui01dbo.k02=='DFB_QT_VIP_1'}">
            <label> <strong>未结算佣金总额:</strong>&nbsp;&nbsp;<a href="javascript:parent.toTargetForm('/WMZHM1/F.go','')">${wmui01dbo.f02}</a>&nbsp;元</label>
            </c:if>
            </td>
          </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="right"><table width="800" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="40" align="right" valign="middle">
        <input type="button" class="btn btn-info" value="充值" onclick="parent.toTargetForm('/WMBM06/F.go','/WMBM06/F.go')" />
        <input type="button" class="btn btn-info" value="提现" onclick="parent.toTargetForm('/WMBM07/F.go','/WMBM07/F.go')" />
					<c:if test="${wmui01dbo.k02=='DFB_QT_VIP_1'}">
					 <input type="button" class="btn btn-info" value="佣金统计" onclick="parent.toTargetForm('/WMZHM1/F.go','')" />
					</c:if>						  
		</td>
        <td width="80" align="left" valign="middle"></td>
        </tr>
    </table></td>
  </tr>
</table>

					    </div></td>
		</tr>

		<tr>
			<td align="left">
            	<div style="padding:0 0 0 20px; border-bottom:1px solid #ccc;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>

							<div class="control-group">
								<div class="controls">

									<form id="form02" name="form02" action="">
										
										<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="13%" height="60" align="left" valign="middle"><div style="font-size:14px; font-weight:bold; padding:10px 0 0;">账户变动</div></td>
    <td width="87%" align="left"><table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="right" valign="middle">
         
            <div style="padding-top:6px;">             
         <!--     <input type="button" class="btn btn-info" value="近期交易记录" onclick="parent.toTargetForm('/WMBM06/H.go','/WMBM06/H.go')" />  -->
              <input type="button" class="btn btn-info" value="近期充值记录" onclick="parent.toTargetForm('/WMBM02/F.go','/WMBM06/H.go')" />
              <input type="button" class="btn btn-info" value="近期提现记录" onclick="parent.toTargetForm('/WMBM03/F.go','/WMBM06/H.go')" />
              <c:if test="${wmui01dbo.k02=='DFB_QT_VIP_1'}">
              <input type="button" class="btn btn-info" value="近期佣金记录" onclick="parent.toTargetForm('/WMBM04/F.go','/WMBM06/H.go')" />
              </c:if>
      </td>
        <td width="80" align="right" valign="middle">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>

										
                                        	
									</form>
								</div>
							</div></td>
					</tr>
				</table>
                	</div>
                </td>
		</tr>

		<tr>
			<td align="left">
            	<div style="padding:0 20px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div class="control-group">
								<div class="controls">

									<form id="form03" name="form03" action="">
										<div style="font-size:14px; font-weight:bold; padding:10px 0 ;">系统公告</div>
										

										</p>
								      <table width="100%" border="0" cellspacing="0" cellpadding="0">
									      <tr>
									        <td><table width="100%" border="0"
											align="left" cellpadding="0" cellspacing="0" class="line1">
											<tr>
												<td height="30" align="center" valign="middle" class="line2"><strong>标题</strong></td>
												<td align="center" valign="middle" class="line2"><strong>开始日期</strong></td>
												<td align="center" valign="middle" class="line2"><strong>结束日期</strong></td>
											</tr>
											<c:forEach var="cssm01dbo" items="${list4}" end="1">
											<tr>
												<td height="30" align="center" valign="middle" class="line2">${cssm01dbo.f01}</td>
												<td align="center" valign="middle" class="line2">${cssm01dbo.f02}</td>
												<td align="center" valign="middle" class="line2">${cssm01dbo.f03}</td>
											</tr>
											</c:forEach>
										</table></td>
								        </tr>
									      <tr>
									        <td>&nbsp;</td>
								        </tr>
								      </table>
									</form>
								</div>
							</div></td>
					</tr>
		  </table></div></td>
		</tr>




	</table>
	<p>&nbsp;</p>
</body>
</html>
