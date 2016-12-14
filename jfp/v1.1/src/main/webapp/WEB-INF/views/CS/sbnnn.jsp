<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
 <title>提示信息</title>
</head>
<body>
&nbsp;&nbsp;&nbsp;&nbsp;<input value="关键字" id="" size='59' ></input><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<select name="" align="right">
	<option value="">信息种类&nbsp;&nbsp;&nbsp;&nbsp;</option>
	<option value="">
</select><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<input value="内容" size='59'></input><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;<textarea name="textarea" cols="45" rows="10">备注说明：</textarea>
<table id="itable" border="1" ondblclick="get(event);" >
</table><br>
<div class="a" >
  <blockquote>
    <p>
      <input type="button" value="返回" onclick=""/>
      <input type="button" value="清空" onclick="" />
      <input type="button" value="保存" onclick="" />
    </p>
  </blockquote>
</div>
</body>
</html>