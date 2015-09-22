<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统授权信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet">
    <link href="resources/css/axure_rp_page.css" type="text/css" rel="stylesheet">
    <link href="客户系统管理_系统实施__files/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	<script src="data/sitemap.js"></script>
<!--     <script src="resources/scripts/jquery-1.7.1.min.js"></script> -->
    <script src="resources/scripts/axutils.js"></script>
<!--     <script src="resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
 -->    <script src="resources/scripts/axurerp_beforepagescript.js"></script>
    <script src="resources/scripts/messagecenter.js"></script>
	<script src='客户系统管理_系统实施__files/data.js'></script>
	<script src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function(){
// 			alert("");
	/*		function isEmail(obj){   
		      reg=/^\w{3,}@\w+(\.\w+)+$/;   
		       if(!reg.test(obj)){        
		            $("#test").html("<b>请输入正确的邮箱地址</b>");   
		       }else{   
		            $("#test").html("");   
		       }   
	  		 }   
		});*/
// 		function add(){
// 			document.form1.action = "/CSSS01/H.go";
// 			form1.submit();
// 		}

		function del(puk){
			document.form2.action = "/CSSS01/D.go?puk="+puk;
			form2.submit();
		}
		function edit(puk,uu1){
			if(uu1 == ""){
				document.form2.action="/CSSS01/R.go?puk="+puk;	
				form2.submit();	
			}else{	
				document.form2.action="/CSSS01/R.go?puk="+puk+"&uu1="+uu1;		
				form2.submit();	
			}	
		}
		function pagination(pagecurrent){
			/*var pagecurrent=document.getElementById("pagecurrent").value;
			var pagelimit=5;
			var totalcount= document.getElementById("totalcount").value;
			var pagecount=document.getElementById("pagecount").value;
			alert(totalcount);
			if(pagecurrent==0){
				pagecurrent=1;		
			}else if(pagecurrent>pagecount){
				pagecurrent=pagecount;
			}else {
				pagecurrent=pagecurrent;
			}		
			
			if(""==tot){
				document.form2.action="/CSSS01/L.go";
				form2.submit();	
			}else{
				document.form2.action="/CSSS01/L.go?totalcount="+totalcount;
				form2.submit();	
			}*/
// 			document.form2.action="/CSSS01/L.go";
// 			form2.submit();	
			if(""==pagecurrent){
				alert("");
				pagecurrent=1;
				document.form2.action="/CSSS01/L.go";
				form2.submit();	
			}else{
				alert(pagecurrent);
				document.form2.action="/CSSS01/L.go?pagecurrent="+pagecurrent;
				form2.submit();	
			}			
		}
	</script>
  </head>  
  <body>
  <input type="button" id="test1" name="test1" value="test" onclick="">
    <div id="main_container">
<div id="u0_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">系统授权信息</span></p></div>
<!-- </div> -->
<!-- <div id="u1" class="u1_container"   > -->
<!-- <div id="u1_img" class="u1_normal detectCanvas"></div> -->
<div id="u2" class="u2" style="visibility:hidden;"  >
<!-- <div id="u2_rtf"></div> -->
</div>
<!-- <DIV id="u4" class="u4;" > -->
<!-- </DIV> -->

<!-- <DIV id="u4container" style="position:absolute; left:20px; top:200px; width:788px; height:140px; overflow:visible"> -->
<!-- 	<div class="preload"><img src="客户系统管理_系统实施__files/u1_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u5_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u7_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u9_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u11_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u13_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u15_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u17_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u19_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u21_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u23_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u25_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u27_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u29_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u31_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u33_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u35_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u37_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u39_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u41_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u43_normal.png" width="1" height="1"/><img src="客户系统管理_系统实施__files/u45_normal.png" width="1" height="1"/> -->
<!-- 	</div> -->

<!-- <form action="" method="get" id="form1" name="form1"> -->
<!-- 	<INPUT id="u47" type=text value="账户种类" class="u47" style="margin-left: 50px"> -->

<!-- <INPUT id="u48" type=text value="账户余额" class="u48"     > -->

<!-- <INPUT id="u49" type="submit" class="u49" value="管理"   > -->

<!-- <INPUT id="u50" type=text value="更新时间" class="u50" > -->

<!-- <INPUT id="u51" type="submit" class="u51" value="管理"   > -->
<!-- <p align="center"> -->
<form action="/CSSS01/H.go" method="get" id="form3" name="form3">		
<INPUT id="u52" type="submit" class="u52" value="添加" >
<INPUT id="u3" type="button" class="u3" value="检索"   >
</form>
<button type="button" id="test" onclick="pagination('${totalcount}')">test</button>
<!-- </p> -->
<!-- </form> -->
	<table class="table" border="1" cellpadding="2" cellspacing="0" style="margin-left: 50px;">
		<thead>
		<tr>
			<td><input type="hidden" id="puk" value="授权Id"></td>
			<td>分系统授权期限</td>
			<td>授权者</td>
			<td>授权信息</td>
			<td>创建者</td>
			<td>更新时间</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		</thead>
		<tbody>
		<form action="" method="post" id="form2" name="form2">		
			<c:forEach var="csss01dbo" items="${list}">
				<tr>
					<td><input type="hidden" id="puk" value="${csss01dbo.puk}"></td>
					<td>${csss01dbo.k02}</td>
					<td>${csss01dbo.f01}</td>
					<td>${csss01dbo.f09}</td>
					<td>${csss01dbo.f10}</td>
					<td>${csss01dbo.cc2}</td>
					<td>${csss01dbo.uu1}</td>
					<td><button type="button" id="btn3" onclick="edit('${csss01dbo.puk}','${csss01dbo.uu1}')">编辑</button></td>
<!-- 					<td><button type="button" id="btn3" onclick="edit('${wmui01dbo.puk}','${wmui01dbo.uu1}')">编辑</button></td> -->
					<td><button type="button" id="btn4" onclick="del('${csss01dbo.puk}')">删除</button></td>
				</tr>
			</c:forEach>
		</form>		
		<p>		
				<input type="button" class="btn" id="first" value="首页" onclick="pagination(1)">
				<input type="button" class="btn"  id="prev" value="上一页" onclick="pagination(${pagecurrent}-1)">
				当前第
				<input type="text"   id="pagecurrent" name="pagecurrent" 
				 value="${pagecurrent}" style="width:50px" onchange="">页
				 
				<input type="button" class="btn"  id="next" value="下一页" onclick="pagination(${pagecurrent}+1)"> 
				<input type="button" class="btn"  id="last" value="末页" onclick="pagination(${pagecount})"> 
				&nbsp;&nbsp;&nbsp;
				每页：<input type="text"  id="pagelimit" name="pagelimit" 
				 value="${pagelimit}" style="width:30px">条 &nbsp;              		
				记录总数：<input type="text"   id="totalcount" 
				name="totalcount" value="${totalcount}" style="width:50px"> 
				&nbsp;  共：<input type="text"  id="pagecount" name="pagecount" 
				 value="${pagecount}" style="width:50px">	页			
		</p>
		
		</tbody>
		
		
	</table>
<!-- <div id="u6" class="u6"  > -->
<!-- 	<div id="u6_rtf"> -->
<!-- 		<p style="text-align:center;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#000000;">开始运营日期 -->
<!-- 		</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u7" class="u7_container"   > -->
<!-- 	<div id="u7_img" class="u7_normal detectCanvas"></div> -->
<!-- <div id="u8" class="u8"  > -->
<!-- 		<div id="u8_rtf"><p style="text-align:center;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;"> -->
<!-- 		公司负责人</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u9" class="u9_container"   > -->
<!-- 	<div id="u9_img" class="u9_normal detectCanvas"></div> -->
<!-- <div id="u10" class="u10"  > -->
<!-- 	<div id="u10_rtf"><p style="text-align:center;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;"> -->
<!-- 	系统版本</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u11" class="u11_container"   > -->
<!-- 	<div id="u11_img" class="u11_normal detectCanvas"></div> -->
<!-- <div id="u12" class="u12"  > -->
<!-- 	<div id="u12_rtf"><p style="text-align:center;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;"> -->
<!-- 	绑定域名</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u13" class="u13_container"   > -->
<!-- 	<div id="u13_img" class="u13_normal detectCanvas"></div> -->
<!-- <div id="u14" class="u14"  > -->
<!-- 	<div id="u14_rtf"> -->
<!-- 	<p style="text-align:center;"> -->
<!-- 	<span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;"> -->
<!-- 	联系电话</span></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u15" class="u15_container"   > -->
<!-- 	<div id="u15_img" class="u15_normal detectCanvas"></div> -->
<!-- <div id="u16" class="u16"  > -->
<!-- 	<div id="u16_rtf"><p style="text-align:center;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">授权终止日期 -->
<!-- 	</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u17" class="u17_container"   > -->
<!-- 	<div id="u17_img" class="u17_normal detectCanvas"></div> -->
<!-- <div id="u18" class="u18" style="visibility:hidden;"  > -->
<!-- <div id="u18_rtf"></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u19" class="u19_container"   > -->
<!-- 	<div id="u19_img" class="u19_normal detectCanvas"></div> -->
<!-- <div id="u20" class="u20"  > -->
<!-- <div id="u20_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">2</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">013/1/1</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u21" class="u21_container"   > -->
<!-- 	<div id="u21_img" class="u21_normal detectCanvas"></div> -->
<!-- <div id="u22" class="u22"  > -->
<!-- <div id="u22_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">张</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">三</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u23" class="u23_container"   > -->
<!-- 	<div id="u23_img" class="u23_normal detectCanvas"></div> -->
<!-- <div id="u24" class="u24"  > -->
<!-- <div id="u24_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">普通版</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u25" class="u25_container"   > -->
<!-- 	<div id="u25_img" class="u25_normal detectCanvas"></div> -->
<!-- <div id="u26" class="u26"  > -->
<!-- <div id="u26_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">1</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">63</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u27" class="u27_container"   > -->
<!-- 	<div id="u27_img" class="u27_normal detectCanvas"></div> -->
<!-- <div id="u28" class="u28"  > -->
<!-- <div id="u28_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">4006-575-686</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u29" class="u29_container"   > -->
<!-- 	<div id="u29_img" class="u29_normal detectCanvas"></div> -->
<!-- <div id="u30" class="u30"  > -->
<!-- <div id="u30_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">2</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">015-01-01</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u31" class="u31_container"   > -->
<!-- 	<div id="u31_img" class="u31_normal detectCanvas"></div> -->
<!-- <div id="u32" class="u32" style="visibility:hidden;"  > -->
<!-- <div id="u32_rtf"></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u33" class="u33_container"   > -->
<!-- 	<div id="u33_img" class="u33_normal detectCanvas"></div> -->
<!-- <div id="u34" class="u34"  > -->
<!-- <div id="u34_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">2</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">013/1/1</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u35" class="u35_container"   > -->
<!-- 	<div id="u35_img" class="u35_normal detectCanvas"></div> -->
<!-- <div id="u36" class="u36"  > -->
<!-- <div id="u36_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">李</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#0000FF;">四</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u37" class="u37_container"   > -->
<!-- 	<div id="u37_img" class="u37_normal detectCanvas"></div> -->
<!-- <div id="u38" class="u38"  > -->
<!-- <div id="u38_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">豪</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">华版</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u39" class="u39_container"   > -->
<!-- 	<div id="u39_img" class="u39_normal detectCanvas"></div> -->
<!-- <div id="u40" class="u40"  > -->
<!-- <div id="u40_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">1</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">89</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u41" class="u41_container"   > -->
<!-- 	<div id="u41_img" class="u41_normal detectCanvas"></div> -->
<!-- <div id="u42" class="u42"  > -->
<!-- <div id="u42_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">4006-575-686</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u43" class="u43_container"   > -->
<!-- 	<div id="u43_img" class="u43_normal detectCanvas"></div> -->
<!-- <div id="u44" class="u44"  > -->
<!-- <div id="u44_rtf"><p style="text-align:left;"><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">2</span><span style="font-family:Arial;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">015-01-01</span></p></div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="u45" class="u45_container"   > -->
<!-- 	<div id="u45_img" class="u45_normal detectCanvas"></div> -->
<!-- <div id="u46" class="u46" style="visibility:hidden;"  > -->
<!-- <div id="u46_rtf"></div> -->
<!-- </div> -->
<!-- </div> -->
</DIV>
</div>
  </body>
  <script src="resources/scripts/axurerp_pagescript.js"></script>
  <script src="客户系统管理_系统实施__files/axurerp_pagespecificscript.js" charset="utf-8"></script>
</html>
