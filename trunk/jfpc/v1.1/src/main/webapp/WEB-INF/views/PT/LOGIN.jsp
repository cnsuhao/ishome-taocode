<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>login</title>
 
    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.css" rel="stylesheet" media="screen">
	<script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>

    <!-- 主要样式表 -->
    <link rel="stylesheet" type="text/css" href="/resources/css/pt/login.css">

  </head>

  <body class="body">
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
		<tr height="140">
			<td width="28%"></td>
			<td valign="bottom" style="font-size:30px; color: #023749;">云端</td>
			<td width="28%"></td>
		</tr>
		<tr height="10">
			<td width="28%"></td>
			<td valign="bottom"></td>
			<td width="28%"></td>
		</tr>
		<tr height="320">
			<td width="28%"></td>
			<td valign="top">
				<table align="center" width="100%" border="0">
					<tr height="50">
						<td style="background-color:#01A4B7; font-size:24px; color:#fff;"><div class="main-content-title">登陆系统</div></td>
					</tr>
					<tr height="20">
						<td style="background-color:#fff; border:1px solid #ddd; border-top:0;">
							<form  action="/PTLOGIN/HOME.go" method="post" id="form2" name="form2">
							<table width="100%" border="0">
								<tr height="20">
									<td width="15%"></td>
									<td width="15%" align="right"></td>
									<td width="5"></td>
									<td></td>
									<td width="25%"></td>
								</tr>
								<tr height="50">
									<td width="15%"></td>
									<td width="15%" align="right"><label for="inputEmail3" class="login-label">用户名</label></td>
									<td width="5"></td>
									<td><input type="text" class="form-control" id="f03" name="f03" placeholder="username"></td>
									<td width="25%"></td>
								</tr>
								<tr height="50">
									<td width="15%"></td>
									<td width="15%" align="right"><label for="inputPassword3" class="login-label">密码</label></td>
									<td width="5"></td>
									<td><input type="password" class="form-control" id="f04" name="f04" placeholder="Password"></td>
									<td width="25%"></td>
								</tr>
								<tr height="50">
									<td width="15%"></td>
									<td width="15%" align="right"><label for="" class="login-label">验证码</label></td>
									<td width="5"></td>
									<td>
										<table width="100%" border="0">
											<tr>
												<td width="40%"><input type="text" class="form-control" id="" placeholder=""></td>
												<td width="5%"></td>
												<td>验证码</td>
											</tr>
										</table>
									</td>
									<td width="25%"></td>
								</tr>
								<tr height="50">
									<td width="15%"></td>
									<td width="15%"></td>
									<td width="5"></td>
									<td>
										<button type="submit" class="btn-l btn-primary-l btn-lg-l">登陆</button><!-- 
										<button type="button" class="btn btn-link">注册</button> -->
									</td>
									<td width="25%"></td>
								</tr>
								<tr height="20">
									<td width="15%"></td>
									<td width="15%" align="right"></td>
									<td width="5"></td>
									<td></td>
									<td width="25%"></td>
								</tr>
							</table>
							<input type="hidden" name="eb5" id="eb5" value="${usertoken}"> 
							<input type="hidden" name="f02" id="f02" value="PT"> 
							</form>
						</td>
					</tr>
				</table>
			</td>
			<td width="28%"></td>
		</tr>
		<tr>
			<td width="28%"></td>
			<td><table align="center" width="95%" border="0" style="height:50px; border-top:1px solid #DBDBDB;">
					<tr><td align="center">当前版本:0.1.1 build 20130101.1</td></tr>
					<tr>
						<td align="center">Copyright © 2012- 2013  <a href="" target="_blank">杭州图顺公司</a>所有版权
						</td>
					</tr>
				</table></td>
			<td width="28%"></td>
		</tr>
	</table>

  </body>
</html>
