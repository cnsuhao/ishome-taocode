<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>index</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="/resources/js/jquery.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>

    <!-- 主要样式表 -->
    <link rel="stylesheet" type="text/css" href="/resources/css/pt/global.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/pt/form.css">
	
	
	<!--一级菜单-->
    <script src="/resources/js/pt/topmenu.js" type="text/javascript"></script>

	<script type="text/javascript">
		function showForm(ulid,urlpath){
			document.blankForm.action=urlpath;
			blankForm.submit();
			onout(ulid);
			}

		function onover(obj){

       document.getElementById(obj).style.display='block';
    }

    function onout(obj){
        document.getElementById(obj).style.display='none';
    }
    
    function body_onload(){
        showForm('blankForm','/PTCP/F.go');
    }
  
    //画面给予系统框架迁移
	//actionpath 当前操作跳转地址
	//returnpath 跳转后页面回调地址
	function toTargetForm(actionpath,returnpath){
		document.blankForm.pageid.value=returnpath;
		document.blankForm.action=actionpath;
		blankForm.submit();
	}	
    
    
	</script>

  </head>
  <body class="body" onload="body_onload()">
 <form action="" id="blankForm" name="blankForm" target="BizFrame" method="post">  												
	<input type="hidden" name="LoginUrl" id="LoginUrl" value="${LoginUrl}">
	<input type="hidden" name="eb5" id="eb5" value="${eb5}">
	<input type="hidden" name="pageid" id="pageid" value="WM.PT">	
	<input type="hidden" name="listParams" id="listParams" value="">	
  </form>
	<table align="center" width="100%" style="margin:0; padding:0;" border="0">
		<tr>
			<td width="2%"></td>
			<td>
				<table align="center" width="100%" border="0">
					<tr height="60">
						<td colspan="2" >
						<table align="center" width="90%" border="0">
							<tr height="40">
	 							<td align="left" style="font-size:26px;">云端</td>
								<td></td>
								<td align="right" valign="bottom"><a>欢迎张三</a>&nbsp;<a>消息（3）</a>&nbsp;<a>退出</a></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="nav fix">
								<div class="logo">
									<ul>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','#')">首页</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTCP/F.go')">产品一览</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTTDGLYL/F.go')">通道管理</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTCP2/F.go')">功能菜单管理</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTYDM/F.go')">客户系统管理</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTCP3/F.go')">功能授权管理</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTYDG/F.go')">系统公告</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTFZ1/F.go')">客户系统管理</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTFZ2/F.go')">分站授权期限</a></li>
										<li><a class="a1" href="#" onclick="showForm('blankDiv','/PTFZ3/F.go')">超级管理员管理</a></li>

										<!-- li><a class="a1" href="#">子系统监管</a></li>
										<li><a class="a1" href="#">黑名单</a></li>
										<li><a class="a1" href="#">优质资源</a></li-->
									</ul>
								</div>
							</div>
						</td>
					</tr>
					<tr height="10">
						<td colspan="2"></td>
					</tr>
					<tr style="border:1px solid #d4d4d4; border-bottom:0 none;">
						<td colspan="2">
							<div class="topmain">
								<p>位置：<a href="">首页</a></p>
							</div>
						</td>
					</tr>
					<tr style="border:1px solid #d4d4d4; background-color:#fff;">
						<td></td>
						<td width="100%">
							<table align="center" width="100%" border="0">
								<tr>
									<td valign="top">
										<div class="mainright">
											<h2>快捷操作</h2>
											<p><a href="#">添加</a></p>
											<p><a href="#">列表</a></p>
										</div>
									</td>
									<td width="85%" style="border-left:1px solid #d4d4d4;" valign="top" height="490">
<iframe src="javascript:'';"  width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" id="BizFrame" name="BizFrame"></iframe>									
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr height="40">
						<td colspan="2" align="center">
							<a class="footer-a" href="" target="_blank">关于云端</a>  |  <a class="footer-a" href="" target="_blank">导航</a>  |  <a class="footer-a" href="" target="_blank">诚征英才</a>  |  <a class="footer-a" href="" target="_blank">联系我们</a>  |  <a class="footer-a" href=" " target="_blank">International Business</a>  |  <a class="footer-a" href="" target="_blank">Copyright©2005-</a><span class="footer-a">2013</span><a class="footer-a" href="" target="_blank">***公司 版权所有</a>
						</td>
					</tr>
				</table>
			</td>
			<td width="2%"></td>
		</tr>
	</table>

  </body>
</html>
					