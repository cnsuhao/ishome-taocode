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
		document.form2.action="/WMBN01/D.go?puk="+puk;	
		form2.submit();	
		}else{
		document.form2.action="/WMBN01/D.go?puk="+puk+"&uu1="+uu1;
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
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;">会员费率管理</h3></td>
	</tr>
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
				<tr>
					<td width="9">&nbsp;</td>
					<td width="1079" align="left">
					  
					  <pagtag:pagtag  pageVo="${pageVO}"  url="F.go"  />
					</td>
					<td width="479" align="right">
						<input type="button" class="btn btn-info" value="检索" onclick="parent.toTargetForm('/WMQTJYCX/H.go','/WMBN01/F.go')" />
					</td>
					<td width="100"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table width="100%" class="table table-hover">
                <tr>
                              <th>会员编号</th>
                              <th>会员名称</th>
                              <th>通道名称</th>
            <th >充值费率</th>
            <th >提现费用<br>(标准)</th>
            <th >提现费用<br>(休息日)</th>
            <th >提现费用<br>(节假日)</th>
							  <th>提现上限</th>
							  <th>提现说明</th>
 							  <th >操作</th> 
                </tr>
                <form action="" method="post" id="form2" name="form2">
                        <c:forEach var="wmbm01dbo" items="${list}">
                            <tr>                          
                              <td>${wmbm01dbo.k01}</td>
                             <td><a href="javascript:parent.toTargetForm('/WMBM09/${wmbm01dbo.puk}/R.go','')">${wmbm01dbo.f04}</a></td>
                             <td title="${wmbm01dbo.bbb}">${wmbm01dbo.fb1}</td>

              <td align="center" valign="middle" style="color: #00F">${wmbm01dbo.f15}%</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.f16}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb3}</td>
              <td align="center" valign="middle" style="color:#F00;">${wmbm01dbo.eb4}</td>
              	
							 <td>${wmbm01dbo.f17}</td>
							 <td>${wmbm01dbo.f19}</td>
 							  <td>
<!--  							  <input type="button" class="btn btn-info" value="费率修改" onclick="parent.toTargetForm('/WMKHTD/Y.go?puk=${userInfo.puk}&k03=${wmbm01dbo.k03}','/WMKHTD/C.go')" /> -->
 							  <input type="button" class="btn btn-info" value="费率修改" onclick="parent.toTargetForm('/WMBN01/${wmbm01dbo.f07}/R.go','')" />
<!--  							  <input type="button" class="btn btn-info" value="删除" onclick="del('${wmbm01dbo.f07}','${wmbm01dbo.uu1}')" /> -->
<!-- 							  <input type="button" class="btn btn-info" value="明细" onclick="parent.toTargetForm('/WMQTFLMX/${wmbm01dbo.f07}/Z.go','')" />-->
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
