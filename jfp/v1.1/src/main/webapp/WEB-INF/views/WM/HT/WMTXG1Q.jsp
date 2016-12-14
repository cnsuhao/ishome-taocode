<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>提现查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>

  </head>
  <body>
  <table width="100%" border="0">
  <form class="form-horizontal" role="form" method="get" action="/WMCZG1/F.go" >
  	
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">查询</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;"></td>
		    <td align="right">
					 <!--  <button type="submit" class="btn btn-default btn-sm">返回</button> -->
					  <input type="reset" class="btn btn-default btn-sm">
					  <input type="submit" class="btn btn-primary btn-sm">	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
      
      <tr height="40">
          <td width="20%" align="right">登录会员id：</td>
          <td width="5"></td>
          <td><input  name="f01" class="form-control input-sm" /></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
       
        <tr height="40">
          <td width="20%" align="right">会员名字：</td>
          <td width="5"></td>          
          <td><input  name="f02" class="form-control input-sm" /></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">通道id：</td>
          <td width="5"></td>          
          <td><input name="f03" class="form-control input-sm" /></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">查询日期：</td>
          <td width="5"></td>          
          <td><input name="f04" class="form-control input-sm" /></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
                
        </form>
      </table></td>
    </tr>
  </table>
</body>
</html>
