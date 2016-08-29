<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<script type="text/javascript">
	function doSubmit() {
		if (document.all("url").value == "") {
			alert("请输入业务测试内容");
			return;
		}
		document.getElementById("TestForm").action = "/PULL/"
			+ document.all("size").value + "/"
			+ document.all("time").value + "?url="
			+ document.all("url").value;
		TestForm.submit();
	}
</script>
</head>
<body>

	<form id="TestForm" action="/" method="GET">
		<input type="text" id="url" name="url" size="100" value="http://www.baidu.com" />
		<input type="text" id="size" name="size" size="100" value="1" />
		<input type="text" id="time" name="time" size="100" value="1" />
		<button type="button" onclick="doSubmit()">Test</button>
	</form>
</body>
</html>
