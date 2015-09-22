<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>Model</title>
 
  <%@ include file="/resources/jsp/themeCSS.jsp" %> 
	<link rel="stylesheet" href="/resources/css/list.css">
  </head> 
  <body>
  		<div class="container-fluid">
		  <div class="row">
			<div class="col-sm-12">
				<div class="panel_div">
					<form class="form-inline" role="form">
					  <div class="form-group">
						<label for="inputPassword2" class="sr-only">请选择日期</label>
						<input type="password" class="form-control" id="inputPassword2" placeholder="rili">
					  </div>
					  <div class="form-group">
						<label for="inputPassword2" class="sr-only">发件人</label>
						<input type="password" class="form-control" id="inputPassword2" placeholder="text">
					  </div>
					  <button type="submit" class="btn btn-default">检索</button>
					</form>
				</div>
				<div class="panel-body">
				  <div class="table-responsive">
					<table class="table table-bordered">
					  <thead>
						<tr>
						  <th><span class="glyphicon glyphicon-user">发送时间</th>
						  <th>发件人</th>
						  <th>收件人数</th>
						  <th>短信内容</th>
						</tr>
					  </thead>
					  <tbody>
						<tr>
						  <td>1,001</td>
						  <td>Lorem</td>
						  <td>12</td>
						  <td>12</td>
						</tr>
						<tr>
						  <td>1,002</td>
						  <td>amet</td>
						  <td>12</td>
						  <td>12</td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>Integer</td>
						  <td>12</td>
						  <td>12</td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>libero</td>
						  <td>12</td>
						  <td>12</td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>libero</td>
						  <td>12</td>
						  <td>12</td>
						</tr>
						<tr>
						  <td>1,003</td>
						  <td>libero</td>
						  <td>12</td>
						  <td>12</td>
						</tr>
					  </tbody>
					</table>
				  </div>
				</div>
				<ul class="pagination" style="margin-left:20px;">
					  <li class="disabled"><a href="#">&laquo;</a></li>
					  <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">2 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">3 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">4 <span class="sr-only">(current)</span></a></li>
					  <li><a href="#">5 <span class="sr-only">(current)</span></a></li>
					</ul>
			  </div>
			  
			  
			</div>
			
		</div>
		  
	
  <%@ include file="/resources/jsp/themeJS.jsp" %>
  </body>
</html>