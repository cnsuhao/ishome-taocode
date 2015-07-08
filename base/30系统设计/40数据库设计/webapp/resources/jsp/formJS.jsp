<form action="" id="blankForm" name="blankForm" target="BizFrame" method="post">  												
	<input type="hidden" name="returnPath" id="returnPath" value="">	
	<input type="hidden" name="LoginUrl" id="LoginUrl" value="">
	<input type="hidden" name="ppp" id="ppp" value="">
	<input type="hidden" name="listParams" id="listParams" value="">	
</form>
<script type="text/javascript">
function showPage(actionpath){
	document.blankForm.action=actionpath;
	blankForm.submit();
}

//画面给予系统框架迁移
//actionpath 当前操作跳转地址
//returnpath 跳转后页面回调地址
function toTargetPage(actionpath,returnpath){
	document.blankForm.returnPath.value=returnpath;
	document.blankForm.action=actionpath;
	blankForm.submit();
}	
</script>