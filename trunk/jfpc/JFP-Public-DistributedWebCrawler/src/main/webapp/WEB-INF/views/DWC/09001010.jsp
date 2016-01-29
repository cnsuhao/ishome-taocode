<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<script type="text/javascript">
function doSubmit() {
	if (document.all("KEY_NAME").value == "") {
		alert("请输入KEY_NAME内容");
		return;
	}
	document.getElementById("KeyForm").action = "/09001010/"
		+ document.all("KEY_NAME").value ;
	KeyForm.submit();
}
</script>
</head>
<body>
<form id="KeyForm" method="POST" action="">
	KEY_NAME<input type="input" size="200" name="KEY_NAME"><br>
	TASK_TYPE<input type="input" size="200" name="TASK_TYPE"><br>
	FTP_PATH<input type="input" size="200" name="FTP_PATH"><br>
	TASK_INVEL<input type="input" size="200" name="TASK_INVEL"><br>
	MAX_NUM<input type="input" size="200" name="MAX_NUM"><br>
	<button type="button" onclick="doSubmit()">Submit</button>
</form>
FTP_PATH=${FTP_PATH},
TASK_INVEL=${TASK_INVEL},
HTTP_URL=${HTTP_URL},
FILE_NAME=${FILE_NAME},
TASK_TYPE=${TASK_TYPE}
</body>
</html>
