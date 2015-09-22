<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表</title>
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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
</head>


<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="line_bottom">
      <tr>
        <td width="63%" align="left" valign="middle"><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
          <tr>
            <td align="left" valign="middle" class="warp">系统版本${systeminfo.f10 }</td>
            <td align="left" valign="middle">授权终了日期${systeminfo.f12 }</td>
          </tr>
        </table></td>
        <td width="37%" align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="line" style="padding:10px 0 0;">
          <tr>
            <td height="40" align="left" valign="middle" class="warp">安全等级 </td>
          </tr>
          <tr>
            <td height="40" align="left" valign="middle" class="warp">最后登录时间、地点</td>
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
    <td height="40" align="left" valign="middle" ><strong>最后统计时间${syinfo.f09 }</strong></td>
  </tr>
  <tr>
    <td align="center" valign="middle"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
      <tr>
        <td align="center" valign="middle" class="line2">今日交易总笔数</td>
        <td height="30" align="center" valign="middle" class="line2">（${syinfo.f01 }）</td>
        <td align="center" valign="middle" class="line2">今日交易总金额</td>
        <td align="center" valign="middle" class="line2">（${syinfo.f02 }）</td>
      </tr>
      <tr>
        <td align="center" valign="middle" class="line2">今日充值总笔数</td>
        <td height="30" align="center" valign="middle" class="line2">（${syinfo.f03 }）</td>
        <td align="center" valign="middle" class="line2">今日充值总金额</td>
        <td align="center" valign="middle" class="line2">（${syinfo.f04 }）</td>
      </tr>
      <tr>
        <td align="center" valign="middle" class="line2">今日提现总笔数</td>
        <td height="30" align="center" valign="middle" class="line2">（${syinfo.f05 }）</td>
        <td align="center" valign="middle" class="line2">今日提现总金额</td>
        <td align="center" valign="middle" class="line2">（${syinfo.f06 }）</td>
      </tr>
      <tr>
        <td align="center" valign="middle" class="line2">今日产生佣金总笔数</td>
        <td height="30" align="center" valign="middle" class="line2">（${syinfo.f07 }）</td>
        <td align="center" valign="middle" class="line2">今日产生佣金总金额</td>
        <td align="center" valign="middle" class="line2">（${syinfo.f08 }）</td>
      </tr>
      </table></td>
  </tr>
</table>
</td>
      </tr>
      <tr>
        <td align="left" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="left" valign="middle" class="warp" ><strong>最后统计日期${syinfo.f18 }</strong></td>
          </tr>
          <tr>
            <td align="center" valign="middle" class="warp"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="left" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">历史充值总金额</td>
                    <td align="center" valign="middle" class="line2">(${syinfo.f10})</td>
                    </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">历史提现总金额</td>
                    <td align="center" valign="middle" class="line2">(${syinfo.f11 })</td>
                    </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">历史佣金总金额</td>
                    <td align="center" valign="middle" class="line2">${syinfo.f12 }</td>
                    </tr>
                </table></td>
                <td align="left" valign="top" class="warp"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">通道总数</td>
                    <td align="center" valign="middle" class="line2">(${syinfo.f13 })</td>
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
                    <td align="center" valign="middle" class="line2">(${syinfo.f14 })</td>
                  </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">本月新开</td>
                    <td align="center" valign="middle" class="line2">(${syinfo.f15 })</td>
                  </tr>
                </table></td>
                <td align="left" valign="top" class="warp"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="line1">
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">会员总数</td>
                    <td align="center" valign="middle" class="line2">(${syinfo.f16 })</td>
                  </tr>
                  <tr>
                    <td height="30" align="center" valign="middle" class="line2">本日新增</td>
                    <td align="center" valign="middle" class="line2">(${syinfo.f17 })</td>
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
        <td align="center" valign="top" class="warp"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td>网站测试中</td>
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


