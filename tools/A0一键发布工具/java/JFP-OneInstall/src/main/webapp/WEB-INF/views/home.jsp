<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<script language="javascript" type="text/javascript">
	var sqls= new Array();   //先声明一维
	//声明二维，每一个一维数组里面的一个元素都是一个数组
	<c:forEach items="${sqllist}" var="item" varStatus="i">
	sqls[${i.index}] = new Array(); 			
	sqls[${i.index}][0] = "${item.puk}"; 
	sqls[${i.index}][1] = "${item.sqlversion}"; 
	sqls[${i.index}][2] = "${item.sqlmeno}"; 
	sqls[${i.index}][3] = "${item.sqlname}";
	sqls[${i.index}][4] = "${item.sqlsize}";
	</c:forEach>

	function body_onload(){
		//根据id查找对象，      
        var obj=document.getElementById("bsbbh");
		var oldVer="";
		for(var i=0; i < sqls.length; i++){
			if(oldVer!=sqls[i][1]){
				oldVer = sqls[i][1];
       			obj.options.add(new Option(sqls[i][1],sqls[i][0])); //兼容IE与firefox
			}
		}
	}
	
	function bsbbh_select(value){
		var old = 0;
		for(var i=0; i < sqls.length; i++){
			sqls[i][9]=0;
		}
		 var obj=document.getElementById("sqlDIV");
		 var strHtml = "即将运行下面SQL文件<br><table width=\"100%\" border=\"1\" >";
		 strHtml = strHtml + "<tr>"+             				               
			"<th>版本</th>"+
			"<th>说明</th>"+
			"<th>文件名称</th>"+
			"<th>文件大小(KB)</th>"+
			"</tr>";
		for(var i=0; i < sqls.length; i++){
       		if(sqls[i][0]>=old&&sqls[i][0]<=value){
       			strHtml = strHtml + "<tr><td>" 
       			+ sqls[i][1] + "</td><td>"+ sqls[i][2] +"</td><td>" + sqls[i][3]+"</td><td>" + sqls[i][4]
       			+ "</td></tr>";
       		}
		}
		strHtml = strHtml + "</table>";
		obj.innerHTML = strHtml;
	}
	
</script>
</head>
<body onload="body_onload()">
<table class="table table-hover" border="1" width="100%">
  <tr>             				               
	<th>当前版本</th>
	<td>1.0-20120101</td>
  </tr>
	<tr>             				               
		<th>部署版本号</th>
		<td><select id="bsbbh" onchange="bsbbh_select(this.value)">
			<option value="0">请选择</option>
			</select></td>
	</tr>
	<tr> 
		<th></th>
		<th><button  class="btn btn-link" >开始安装</button></th>
	</tr>
	<tr> 
		<td colspan="2">
<div id="sqlDIV" name="sqlDIV" >
当前SQL文件清单
<table class="table table-hover" border="1" width="100%">
	<tr>             				               
		<th>编号</th>
		<th>版本</th>
		<th>说明</th>
		<th>文件名称</th>
		<th>文件大小(KB)</th>
	</tr>
	<c:forEach var="item" items="${sqllist}">	
	<tr align="center">
		<td>${item.puk}</td>
		<td>${item.sqlversion}</td>
		<td>${item.sqlmeno}</td>
		<td>${item.sqlname}</td>
		<td>${item.sqlsize}</td>		                  
	</tr>
	</c:forEach>
</table>
</div>		
		
		</td>
	</tr>
</table>

</body>
</html>
