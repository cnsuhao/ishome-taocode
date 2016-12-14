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
  <form method="get" action="/WMSZT3/F.go">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><div class="tab-main dark-gray">
                  <h3 class="tab-main-title"><span style="font-size:14px; font-weight:bold;">费率管理</span></h3>
        </div></td>
      </tr>
      <tr>
        <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
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
                        <td align="left"><input type="text" name="f05" value="${param.f05 }"></td>
                        <td align="right"><strong>支付通道：</strong></td>
                        <td align="left"><input type="text" name="f03" value="${param.f03 }"></td>
                      </tr>
                      <tr>
                        <td width="192" height="40" align="right"><strong>会员名称：</strong>
                          <table  width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td></td>
                            </table></td>
                        <td align="left"><input type="text" name="f04" value="${param.f04 }"></td>
                        <td align="right"><strong>上级代理商：</strong></td>
                        <td align="left"><input type="text" name="k02" value="${param.k02 }"></td>
                      </tr>
                      <tr>
                        <td height="40" colspan="4" align="right" ><div style="padding:0 90px 0 0; ">
                          <input type="submit" value="查询">
                          </div></td>
                      </tr>
                    </table></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td><table  width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td></td>
                      </table></td>
                  </tr>
                  <tr>
                    <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td><table  width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <td></td>
                            </table></td>
                        </tr>
                      <tr>
                        <td align="left" style="padding:20px;"><pagtag:pagtag pageVo="${pageVO}" url="F.go" /></td>
                        </tr>
                      </table></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        
        </table></td>
      </tr>
      <tr>
        <td align="left" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table table-hover">
          <tr>
            <th align="center" valign="middle">用户名</th>
            <th align="center" valign="middle">会员名称</th>
            <th align="center" valign="middle">支付通道</th>
            <th align="center" valign="middle">上级代理商</th>
            <th align="center" valign="middle">充值费率</th>
            <th align="center" valign="middle">提现费用<br>(标准)</th>
            <th align="center" valign="middle">提现费用<br>(休息日)</th>
            <th align="center" valign="middle">提现费用<br>(节假日)</th>
            <th align="center" valign="middle">单笔结算上限</th>
            <th align="center" valign="middle">最后修改时间</th>
            <th align="center" valign="middle">余额</th>
            <th align="center" valign="middle" >操作</th>
          </tr>
         
          <c:forEach var="zftd" items="${pageVO.pageData}">
            <tr >
              <td align="center" valign="middle">${zftd.f05}</td>
              <td align="center" valign="middle">${zftd.f04}</td>
              <td align="center" valign="middle">${zftd.f03}</td>
              <td align="center" valign="middle">${zftd.k02}</td>
              <td align="center" valign="middle" style="color: #00F">${zftd.f15}%</td>
              <td align="center" valign="middle" style="color:#F00;">${zftd.f16}</td>
              <td align="center" valign="middle" style="color:#F00;">${zftd.eb3}</td>
              <td align="center" valign="middle" style="color:#F00;">${zftd.eb4}</td>
              <td align="center" valign="middle">${zftd.f17}</td>
              <td align="center" valign="middle">${zftd.uu1}</td>
              <td align="center" valign="middle">${zftd.f01}</td>
              <td align="center" valign="middle"><a href="/WMSZT3/${zftd.puk}/U.go">修改费率</a></td>
            </tr>
          </c:forEach>
        </table></td>
      </tr>
    </table>
 </form>
 </body>
</html>