<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"  media="screen"/>
<meta charset="utf-8">
<title>Welcome</title>
<script type="text/javascript">
function doPOST(actURL){
	var url = "/" + document.all("userID").value + actURL + "/1";
	document.all("actURL").value = "action：" + url;
	document.forms['myform'].action = url; //修改地址
	document.forms['myform'].submit(); //表单提交 
}
</script>
</head>
<body>
 <form action="getInfo.jsp" name="myform" method="post">
  <input type="hidden" name="paramValue" id="paramValue" value="{}" /> 
 <table>
    <tr>
      <td>	
        <table>
		  <tr>
			<td><H1>OMD休闲娱乐模块接口演示</H1></td>
		  </tr>		
		  <tr>
			<td><h3>${DDD}</h3></td>
		  </tr>		
		  <tr>
			<td><input type ="text" size="100" name="actURL" id="actURL" value="action="/></td>
		  </tr>		
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
	    <table>
		  <tr>
			<td>称呼</td>
			<td><input type ="text" name="userName" id="userName" value="游客"/></td>
			<td><input type ="text" name="userID" id="userID" value="12345678901234567"/></td>
			<td><input type="button" value="创建用户" onclick="doPOST('/10001234/10001000')"/></td>
		  </tr>		
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
 	    <table>
			<tr>
			  <td>温泉泡点：</td>
			  <td><input type="button" value="进入温泉" onclick="doPOST('/10101234/10101000')"/></td>
			  <td><input type="button" value="离开温泉" onclick="doPOST('/10101234/10102000')"/></td>
			  <td><input type="button" value="加点" onclick="doPOST('/10101234/10109000')"/></td>
			</tr>
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
 	    <table>
			<tr>
			  <td>漂流瓶：</td>
			  <td><input type ="text" name="plmessage" id="plmessage" value="我的漂流"/></td>
			  <td><input type="button" value="扔一个" onclick="doPOST('/10201234/10201000')" /></td>
			  <td><input type="button" value="捡一个" onclick="doPOST('/10201234/10202000')" /></td>
			  <td><input type="button" value="我的瓶子" onclick="doPOST('/10201234/10203000')" /></td>
			  <td><input type="button" value="对话（未完成）" onclick="doPOST('/10201234/10203010')" /></td>
			  <td><input type="button" value="删除（未完成）" onclick="doPOST('/10201234/10203020')" /></td>
			  <td><input type="button" value="添加好友（未完成）" onclick="doPOST('/10201234/10203030')" /></td>
			</tr>
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
 	    <table>
			<tr>
			  <td>许愿墙：</td>
			  <td><input type ="text" name="xymessage" id="xymessage" value="我的许愿"/></td>
			  <td><input type="button" value="许愿" onclick="doPOST('/10301234/10301000')"/></td>
			  <td><input type="button" value="查看" onclick="doPOST('/10301234/10302000')"/></td>
			  <td><input type="button" value="点赞（未完成）" onclick="doPOST('/10301234/10302010')"/></td>
			</tr>
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
 	    <table>
			<tr>
			  <td>幸运星：</td>
			  <td><input type="button" value="抽奖" onclick="doPOST('/10401234/10401000')"/></td>
			</tr>
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
 	    <table>
			<tr>
			  <td>植树：</td>
			  <td><input type="button" value="浇水" onclick="doPOST('/aa/ccc')"/></td>
			  <td><input type="button" value="查看" onclick="doPOST('/aa/ccc')"/></td>
			</tr>
	    </table>
	  </td>
	</tr>
    <tr>
      <td>	
 	    <table>
			<tr>
			  <td>钓鱼：</td>
			  <td><input type="button" value="钓鱼" onclick="doPOST('/aa/ccc')"/></td>
			</tr>
	    </table>
	  </td>
	</tr>
 </table>
 </form>
</body>
</html>
