<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>货付宝</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	 
<script src="/resources/js/wm/zk/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"/>
 <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
 <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
<script src="/resources/js/wm/zk/bootstrap.js"></script>
<script type="text/javascript">
	function ch(){
		document.form1.action="/PTCP4/B.go";
		form1.submit();
	} 

</script>
</head>
<body>
<div class="head">
	<div class="head_warp">
    	<div class="head_logo"><img src="Images/logo.jpg" width="234" height="68" /></div>
        <div class="head_nav">
        	<ul>
            	<li><a href="index.html">首页</a></li>
                <li><a href="Service.html">商家服务</a></li>
                <li><a href="daifubao.html">产品介绍</a></li>
                <li><a href="gy.html">图顺公益</a></li>
                <li><a href="Join.html">加盟合作</a></li>
                <li><a href="news.html">最新动态</a></li>
                <li><a href="Customer.html">客服中心</a></li>
            </ul>
        
        </div>
    </div>
</div>
<div class="warp">
  <div style="text-align:center;">
  <form action="/PTCP4/A.go" method="post" id="form1" name="form1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="pack">
          <tr>
            <td height="40" align="center" valign="middle" class="pack1">&nbsp;</td>
            <td align="center" valign="middle" class="pack1"><table width="80" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="36%" align="right" valign="middle"><input type="radio" name="radio" id="radio" value="radio" />
                  <label for="radio"></label></td>
                <td width="64%" align="center" valign="middle">套餐一</td>
                </tr>
              </table></td>
            <td align="center" valign="middle" class="pack1"><table width="80" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="36%" align="right" valign="middle"><input type="radio" name="radio" id="radio2" value="radio" />
                  <label for="radio2"></label></td>
                <td width="64%" align="center" valign="middle">套餐一</td>
                </tr>
              </table></td>
            <td align="center" valign="middle" class="pack1"><table width="80" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="36%" align="right" valign="middle"><input type="radio" name="radio" id="radio3" value="radio" />
                  <label for="radio3"></label></td>
                <td width="64%" align="center" valign="middle">套餐一</td>
                </tr>
              </table></td>
            <td align="center" valign="middle" class="pack1"><table width="80" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="36%" align="right" valign="middle"><input type="radio" name="radio" id="radio4" value="radio" />
                  <label for="radio4"></label></td>
                <td width="64%" align="center" valign="middle">套餐一</td>
                </tr>
              </table></td>
          </tr>
          <c:forEach var="" items="">
          	<td height="40" align="center" valign="middle" class="pack1">&nbsp;</td>
          	<td align="center" valign="middle" class="pack1">&nbsp;</td>
            <td align="center" valign="middle" class="pack1">&nbsp;</td>
            <td align="center" valign="middle" class="pack1">&nbsp;</td>
            <td align="center" valign="middle" class="pack1">&nbsp;</td>          
          </c:forEach>
        </table></td>
      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" valign="middle"><table width="50%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><div class="infor_an">
            <input type="button" class="btn btn-default btn-sm" value="自助选择" onclick="ch()"/>
<!--             <a href="#" class="anniu">自助选择</a> -->
            </div></td>
            <td><div class="infor_an">
            	<input type="submit" value="提交订单" id="btn" />
<!--             <a href="#" class="anniu">提交订单</a> -->
            </div></td>
          </tr>
        </table></td>
      </tr>
    </table>
    <label for="textfield"></label>
  </form></div>
</div>


<div style=" clear:both;"></div>
<div class="footer">
	<div class="footer_warp">
    <div class="footer_warp_nav"><a href="#">首页</a> &nbsp;| &nbsp;<a href="#">商家服务</a> &nbsp;| &nbsp;<a href="#">贷付宝</a> &nbsp;|&nbsp; <a href="#">图顺公益</a>&nbsp; |&nbsp; <a href="#">加盟合作</a> | <a href="#">最新动态</a> &nbsp;|&nbsp; <a href="#">客服中心</a></div>
    <div>
    	<ul>
        	<li><a href="#"><img src="Images/bottom_logo001.jpg"  /></a></li>
            <li><a href="#"><img src="Images/bottom_logo002.jpg" /></a></li>
            <li><a href="#"><img src="Images/bottom_logo003.jpg"  /></a></li>
            <li><a href="#"><img src="Images/bottom_logo004.jpg"  /></a></li>
            <li><a href="#"><img src="Images/bottom_logo005.jpg"  /></a></li>
        </ul>
    </div>
    </div>
</div>
</body>

</html>
