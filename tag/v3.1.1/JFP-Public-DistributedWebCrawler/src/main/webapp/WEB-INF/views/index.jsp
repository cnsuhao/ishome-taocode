<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"  media="screen"/>
<meta charset="utf-8">
<title>Welcome</title>
<script type="text/javascript">
	
</script>
</head>
<body>
 <H1>* 分布式网络爬虫服务端</H1>c
 <H3>* Distributed Web Crawler</H3><BR>

	<form method="POST" action="/GJ3">
		频率设定
		<input type="input" name="key">
		<input type="input" name="taskInvel">
		<input type="submit" value="submit">
	</form>
	<BR><BR><BR>
	<form method="POST" enctype="multipart/form-data" action="/C310/010">
		文件名称<input type="input" name="jobid">文件路径
		<input type="file" name="img"><input type="submit" value="submit">
	</form>
	<BR><BR><BR>
	<form method="POST" action="/GJ1">
		文件名称登记
		<input type="input" name="code">
		<input type="input" name="name">
		<input type="submit" value="submit">
	</form>
</body>
</html>
