<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>提示信息编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/resources/js/validatorMessages.js"></script>
	 <script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
			$(function(){
				alert("");
				$('#test').click(function(){
					alert($('#puk').val());
				});
			});
	</script>
  </head>
  
  <body  style="margin-left:50px; margin-top:80px;">
 
  	 提示信息编辑<button type="button" id="test" value="">test</button> <br>
  	 <form action="" method="post" id="form1" class="form-horizontal" >
  	 	<fieldset>
  	 		<input type="hidden" id="puk" name="puk" class="requriedId" value="hidden">
  	 		 <label>关键字</label>
  	 		 <input type="text" placeholder="uu2" id="uu2" name="uu2" style="width: 280; height: 30"><br><br>
  	 		<select id="fb1" style="width: 280px">
<!--   	 		 multiple="multiple" -->
		        <option selected="selected" >1</option>
		        <option >2</option>
		        <option>3</option>
		        <option>4</option>
		        <option>5</option>
		    </select><br><br>
		    <label>内容</label>
		    <input type="text" placeholder="bbb" id="bbb" name="bbb" style="width: 280; height: 30"><br><br>
		    <textarea rows="8" cols="35"></textarea><br><br>
		   <p style="margin-left: 80px;"> 
		    <button type="button" class="btn" >返回</button>&nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="button" class="btn"  value="清空">&nbsp;&nbsp;&nbsp;&nbsp;
		    <input type="submit"  class="btn" value="保存">
		   </p>
  	 	</fieldset>
  	 </form>
  </body>
</html>
