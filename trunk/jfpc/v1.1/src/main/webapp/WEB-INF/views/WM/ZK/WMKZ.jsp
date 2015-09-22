<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">	
<title>列表</title>
<script src="/resources/js/wm/zk/jquery.js"></script>
<script src="/resources/js/wm/zk/bootstrap.min.js"></script>
<script src="/resources/js/wm/zk/topmenu.js"></script>	
<script src="/resources/js/wm/zk/bootstrap.js"></script>
<script type="text/javascript">
	function show(puk,f01){
		document.form1.action="/WMKZ/A.go?puk="+puk+"&f01="+f01;
		form1.submit();
	} 
</script>
<style type="text/css">
*{ padding:0 ; margin:0 ; border:0;}
bady{ font-size:12px; font-family:Arial, "宋体"; color:#000;}
.warp{ padding: 0 0 0 10px;}
.line{ border-left:1px solid #ccc; }
.line_bottom{ border-bottom:1px solid #ccc;}
.title{ font-size:14px; font-weight:bold;}
.line1{ border-top:1px solid #ccc; border-left:1px solid #ccc;}
.line2{ border-bottom:1px solid #ccc; border-right:1px solid #ccc;}
</style>
</head>


<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="line_bottom">
      <tr>
        <td width="63%" align="left" valign="middle"><table width="500" border="0" align="left" cellpadding="0" cellspacing="0">
          <tr>
            <td align="left" valign="middle" class="warp">系统版本${paramBean.f18} &nbsp;${xt.f10}</td>
            <td align="left" valign="middle">授权终了日期&nbsp;${paramBean.f19} &nbsp;${xt.f12}</td>
          </tr>
        </table></td>
        <td width="37%" align="left" valign="middle"><table width="400" border="0" cellpadding="0" cellspacing="0" class="line">
          <tr>
            <td height="40" align="left" valign="middle" class="warp">安全等级 </td>
          </tr>
          <tr>
            <td height="40" align="left" valign="middle" class="warp">最后登录时间、地点 </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="line_bottom">
      <tr>
        <td height="40" align="left" valign="middle" class="warp"><div class="title">系统动态</div></td>
      </tr>
      <tr>
        <td align="left" valign="middle" class="warp"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" align="left" valign="middle" ><strong>最后统计时间</strong></td>
  </tr>
  <tr>
    <td align="center" valign="middle"><table width="800" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
      <tr>
        <td height="30" align="center" valign="middle" class="line2">今日交易总笔数（${paramBean.f01}）</td>
        <td align="center" valign="middle" class="line2">今日交易总金额（${paramBean.f02}）</td>
      </tr>
      <tr>
        <td height="30" align="center" valign="middle" class="line2">今日充值总笔数（${paramBean.f03}）</td>
        <td align="center" valign="middle" class="line2">今日充值总金额（${paramBean.f04}）</td>
      </tr>
      <tr>
        <td height="30" align="center" valign="middle" class="line2">今日提现总笔数（${paramBean.f05}）</td>
        <td align="center" valign="middle" class="line2">今日提现总金额（${paramBean.f06}）</td>
      </tr>
      <tr>
        <td height="30" align="center" valign="middle" class="line2">今日产生佣金总笔数（${paramBean.f07}）</td>
        <td align="center" valign="middle" class="line2">今日产生佣金总金额（${paramBean.f08}）</td>
      </tr>
      </table></td>
  </tr>
</table>
</td>
      </tr>
      <tr>
        <td align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="left" valign="middle" class="warp" ><strong>最后统计日期</strong></td>
          </tr>
          <tr>
            <td align="center" valign="middle" class="warp"><table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="left" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">历史充值总金额</td>
                    <td align="center" valign="middle" class="line2"  width="40%"> ${paramBean.f10}</td>
                  </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">历史提现总金额</td>
                    <td align="center" valign="middle" class="line2"  width="40%"> ${paramBean.f11}</td>
                  </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">历史佣金总金额</td>
                    <td align="center" valign="middle" class="line2"  width="40%"> ${paramBean.f12}</td>
                  </tr>
                </table></td>
                <td align="left" valign="top" class="warp"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">通道总数</td>
                    <td align="center" valign="middle" class="line2" width="35%">${paramBean.f13}</td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td align="left" valign="top">&nbsp;</td>
                <td align="left" valign="top" class="warp">&nbsp;</td>
              </tr>
              <tr>
                <td align="left" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">分站总数</td>
                    <td align="center" valign="middle" class="line2" width="40%"> ${paramBean.f14}</td>
                  </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">本月新开</td>
                    <td align="center" valign="middle" class="line2" width="40%">${paramBean.f15}</td>
                  </tr>
                </table></td>
                <td align="left" valign="top" class="warp"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">会员总数</td>
                    <td align="center" valign="middle" class="line2" width="35%">${paramBean.f16}</td>
                  </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">本日新增</td>
                    <td align="center" valign="middle" class="line2" width="35%">${paramBean.f17}</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td align="left" valign="middle" class="warp">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="40" align="left" valign="middle" class="warp" ><div class="title">系统公告</div></td>
      </tr>
      <tr>
        <td align="center" valign="top" class="warp"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="left"> 
            	<table class="table table-hover" style="border: 2px;">  
            		<tr align="center" valign="middle" >
            			<th width="10%">标题</th>
            			<th width="10%">发布时间</th>
            			<th>内容</th>
            			<th width="10%">级别</th><!-- 1云平台2全产品3全系统 -->
            		</tr>        
            		<form  method="post" id="form1" name="form1" action="" >  	
            		<c:forEach var="cssm01" items="${gg}"  >
            			<tr  align="center" valign="middle" >          				
            				<td>
            				<input  type="button"  class="btn btn-link" value="${cssm01.f01}" 
            				onclick="show('${cssm01.puk}','${cssm01.f01}')"/>
            			 </td>
            				<td >${cssm01.f02}</td>
            				<td >${cssm01.bbb}</td>
            				<td >${cssm01.fb1}</td>         				
            			</tr>
            		</c:forEach>
            		</form>
            	</table>
            </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td align="left" valign="middle" class="warp">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
