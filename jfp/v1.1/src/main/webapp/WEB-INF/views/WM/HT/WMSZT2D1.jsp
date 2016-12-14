<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>关闭通道</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
  </head>
  <body>
  <table width="100%" border="0">
  <sf:form class="form-horizontal" role="form" modelAttribute="roadinfo" method="post">
  <sf:hidden path="puk"/>
  <sf:hidden path="uu1"/>	
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">关闭通道</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">关闭通道</td>
		    <td align="right">
					<!--   <button type="submit" class="btn btn-default btn-sm">返回</button> -->
					  <button type="submit" class="btn btn-default btn-sm">重置</button>
					  <input type="submit" class="btn btn-primary btn-sm" value="提交">	
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
       
        <tr height="40">
          <td width="20%" align="right">通道名称：</td>
          <td width="5"></td>          
          <td>${roadinfo.f03}</td>
          <td width="5"></td>         
        </tr>
        <tr height="40">
          <td width="20%" align="right">关闭说明：</td>
          <td width="5"></td>
          <td><sf:input  path="f07" class="form-control input-sm" /></td>
          <td width="5"></td>
          <td width="30%">备注</td>
        </tr>
        
        </sf:form>
      </table></td>
    </tr>
  </table>
</body>
</html>
