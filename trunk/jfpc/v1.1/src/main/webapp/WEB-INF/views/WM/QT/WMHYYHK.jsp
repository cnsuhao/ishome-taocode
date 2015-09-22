<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld"  prefix="pagtag" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>列表</title>
 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function xiugai(puk,uu1) {
		if(uu1 == ""){
			document.form2.action="/WMBM08/R.go?puk="+puk;	
			form2.submit();	
		}else{	
			document.form2.action="/WMBM08/R.go?puk="+puk+"&uu1="+uu1;		
			form2.submit();	
		}	
	}	
	function del(puk,uu1){
		if(uu1 == ""){
			document.form2.action="/WMQTHYYHK/D.go?puk="+puk;	
			form2.submit();	
		}else{
			document.form2.action="/WMQTHYYHK/D.go?puk="+puk+"&uu1="+uu1+"&k01="k01;		
			form2.submit();	
		}
	}
	
	
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><div style="font-size:14px; font-weight:bold;margin-left:10px;">银行账户</div></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
<pagtag:pagtag  pageVo="${pageVO}"  url="F.go" />
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" class="table table-hover">
                <tr>
                    <th align="center" valign="middle">用户名</th>
                              <th align="center" valign="middle">银行名称</th>
                              <th align="center" valign="middle">银行账号</th>
							  <th align="center" valign="middle">所在省市</th> 
							  <th align="center" valign="middle">银行详细地址</th> 
                              <th align="center" valign="middle" class="table-th-do">操作</th>
							  
                </tr>
					<form action="" method="post" id="form2" name="form2">
				
				  <c:forEach var="wmbs01dbo" items="${pageVO.pageData}">
                            <tr>
                              <td>${wmbs01dbo.f01}</td>
                              <td>${wmbs01dbo.f02}</td>
                              <td>${wmbs01dbo.k02}</td>
							  <td>${wmbs01dbo.f04},${wmbs01dbo.f05}</td>
							  <td>${wmbs01dbo.f03}</td>
 							  <td>
 							  <input type="button" class="btn btn-info" value="编辑" onclick="xiugai('${wmbs01dbo.puk}','${wmbs01dbo.uu1}')" />
 							  <input type="button" class="btn btn-info" value="删除" onclick="del('${wmbs01dbo.puk}','${wmbs01dbo.uu1}','${wmbs01dbo.k01}')" />
 							
							  </td>
                            </tr>
                            </c:forEach>
							</form>
							
            </table>
		</td>
	</tr>
  </table>
</body>
</html>
