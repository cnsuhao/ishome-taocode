<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	// 交易记录
	//     function tiaozhuan1() {
	// 		document.form2.action = "";
	// 		form2.submit();
	// 	}
	//充值记录
	function tiaozhuan2() {
		document.form02.action = "/WMBM02/F.go";
		form02.submit();
	}
	//体现记录
	function tiaozhuan3() {
		document.form02.action = "/WMBM03/F.go";
		form02.submit();
	}
	//佣金记录
	function tiaozhuan4() {
		document.form02.action = "/WMBM04/F.go";
		form02.submit();
	}
	//充值
	function chongzhi() {
	
		document.form01.action = "/WMBM06/H.go";
	
		form01.submit();
	}
	//提现
	function txin() {
		document.form01.action = "/WMBM07/H.go";
		form01.submit();
	}
	//佣金结算
	function jiesuan() {
	
		document.form01.action = "/WMZHD1/M.go";
		form01.submit();
	}

	
</script>
</head>
<body>
	<table width="100%" border="0">
		<tr>
		<tr>
    <td><table  width="100%" border="0"> 
            <tr>
      <tr>
        <td height="10" align="right"><p>${message}</p>
         </td>
        </tr>
          <tr>
        <td height="10" align="">
        <table width="773" border="01" cellspacing="0" cellpadding="0">
  <tr>
    <td width="373" height="82">会员等级</td>
    <td width="373"><p>安全等级</p>
      <p>最后登录地址、时间</p></td>
  </tr>
</table>

         </td>
        </tr>
        
        </table>
        </td>
  </tr>

		<tr>
			<td align="left">
			  <table width="773" border="1" cellspacing="0" cellpadding="0">
			   <tr>
				<td>
				<form action="" method="post" id="form01" name="form01"  >
				<div class="control-group">
					<div class="controls">

					
							<p>账户余额	0元			佣金	0元    </p>
							<p>&nbsp;					    </p>
							<p>
					  
							  <input type="button" name="button1" class="btn btn-info"
							id="button1" value="充值" onclick="chongzhi()" />
							  
							  <input type="button" name="button2" id="button2" class="btn btn-info " value="提现" onClick="txin()" />
							  <input type="button" name="button3" id="button3" class="btn btn-info" value="佣金结算" onClick="jiesuan()"/>
					   
					    </p>
					
					</div>
				</div>
			</form> 
			</td>
			</tr>
			</table>
			</td>
		</tr>
     
     <tr>
			<td align="left">
			 <table width="773" border="1" cellspacing="0" cellpadding="0">
			   <tr>
				<td>

				<div class="control-group">
					<div class="controls">

					  <form id="form02" name="form02" action="" >
							<p>账户变动</p>
							<p>&nbsp;					    </p>
							<p>
                            <input type="button" name="button" class="btn btn-info"
							id="button" value="近期交易记录"
							onclick="location.href('WMAQS1')" />
                            <input type="button" name="button" class="btn btn-info"
							id="button" value="近期充值记录" onclick="tiaozhuan2()" />
                            <input type="button" name="button" class="btn btn-info"
							id="button" value="近期提现记录" onclick="tiaozhuan3()" />
                            <input type="button" name="button" class="btn btn-info"
							id="button" value="近期佣金记录" onclick="tiaozhuan4()" />
                         
                            &nbsp;</p>
					  </form>
					</div>
				</div>
		</td>
		</tr>
		</table>
			</td>
		</tr>
     
     <tr>
			<td align="left">
              <table width="773" border="1" cellspacing="0" cellpadding="0">
			   <tr>
				<td>
				<div class="control-group">
					<div class="controls">

					  <form id="form03" name="form03" action="">
						<p>系统公告						</p>
						<table width="378" border="01" cellspacing="0" cellpadding="0" align="center">
						  <tr>
    <td width="131">日期</td>
    <td width="109">标题</td>
    <td width="130">来源</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

					    </p>
					  </form>
					</div>
				</div>
		</td>
		</tr>
		</table>
			</td>
		</tr>
     
     
     
       
	</table>
	<p>&nbsp;</p>
</body>
</html>
