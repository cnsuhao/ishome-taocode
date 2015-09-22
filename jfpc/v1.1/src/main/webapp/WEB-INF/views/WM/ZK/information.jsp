<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>货付宝添加页面信息</title>
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
  <form action="/PTCP4/C.go" method="post" id="form1" name="form1">
  <input type="hidden" class="infor_wb" id="puk"  name="puk" value=""/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="428" height="30" align="right">&nbsp;</td>
    <td width="8">&nbsp;</td>
    <td width="588">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">域名</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><label for="textfield2"></label>
      <input  id="k02"  name="k02" type="text" value="" class="infor_wb" />      <label for="textarea"></label></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">超级管理员ID（默认）</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="k03" type="text" class="infor_wb" id="k03" value=""/></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户简称</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f01" type="text" class="infor_wb" id="f01" value=""/></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户地址</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f03" type="text" class="infor_wb" id="f03" value="" /></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户工商税务编号</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f04" type="text" class="infor_wb" id="f04" value=""/></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户公司成立日期</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f05" type="text" class="infor_wb" id="f05" /></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户公司注册资本（万元）</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f06" type="text" class="infor_wb" id="f06" /></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">联系人名称</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f07" type="text" class="infor_wb" id="f07" /></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">联系人电话</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f08" type="text" class="infor_wb" id="f08" /></td>
  </tr>
  <tr>
    <td height="10" colspan="3" align="right" valign="middle"></td>
  </tr>
  <tr>
    <td height="30" colspan="3" align="right" valign="middle"><div class="infor_line"></div></td>
    </tr>
  <tr>
    <td height="50" align="right" valign="middle">&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="top">
    <div class="infor_an">
    <input type="submit" id="btn"  class="btn btn-primary btn-sm" value="下一步"/>
    </div></td>
  </tr>
  </table>
      <label for="textfield"></label>
  </form>
  </div>
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
