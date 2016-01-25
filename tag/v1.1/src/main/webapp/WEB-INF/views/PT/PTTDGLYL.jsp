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
	function jiansuo() {
		document.form1.action = "/WMBN01/R.go";
		form1.submit();
	}

	function xiugai(puk,uu1) {
		if(uu1 == ""){
			document.form2.action="/WMBN01/R.go?puk="+puk;	
			form2.submit();	
		}else{	
			document.form2.action="/WMBN01/R.go?puk="+puk+"&uu1="+uu1;		
			form2.submit();	
		}	
	}	
	
	function del(puk,uu1)
	{

	if(uu1 == ""){
		document.form2.action="/PTTDGLYL/D.go?puk="+puk;	
		form2.submit();	
		}else{
		document.form2.action="/PTTDGLYL/D.go?puk="+puk+"&uu1="+uu1;
	    form2.submit();	
		}
     }
	 function tianjia() {
		document.form1.action="/WMBN01/H.go";	
		form1.submit();	
		
		}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
		<td><h4 style="margin-left:10px;">系统通道管理</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="2%"></td>
					<td align="left">
					  
					  <pagtag:pagtag  pageVo="${pageVO}"  url="F.go"  />
					</td>
					<td width="30%" align="right">
						<input type="button" class="btn btn-info" value="检索" onclick="parent.toTargetForm('/WMQTJYCX/H.go','/WMBN01/F.go')" />
						<input type="button" class="btn btn-info" value="添加" onclick="parent.toTargetForm('/PTTDGLYL/H.go','/PTTDGLYL/F.go')" />
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" class="table table-hover">
                <tr>
                              <th>通道编号</th>
                              <th>通道名称</th>
                              <th>通道简介</th>
							  <th>通道说明</th>
							  <th>充值手续费率</th>
							  <th>提现手续费</th>
							  <th>提现最大金额</th>
 							  <th class="table-th-do">操作</th> 
                </tr>
                <form action="" method="post" id="form2" name="form2">
                        <c:forEach var="wmbma1" items="${list}">
                            <tr>                          
                              <td>${wmbma1.puk}</td>
                             <td>${wmbma1.f03}</td>
                             <td>${wmbma1.f04}</td>
							 <td>${wmbma1.f05}</td>
							 <td>${wmbma1.f15}</td>	
							 <td>${wmbma1.f16}</td>
							 <td>${wmbma1.f17}</td>
 							  <td>
 							  
 							  <input type="button" class="btn btn-info" value="编辑" onclick="parent.toTargetForm('/PTTDGLYL/${wmbma1.puk}/R.go','')" />
 							  <input type="button" class="btn btn-info" value="删除" onclick="del('${wmbma1.puk}','${wmbma1.uu1}')" />
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
