<form action="" id="blankForm" name="blankForm" target="_self" method="post">  												
	<input type="hidden" name="token" id="token" value="${loginer.token}">	
	<input type="hidden" name="returnPath" id="returnPath" value="">	
	<input type="hidden" name="LoginUrl" id="LoginUrl" value="">
	<input type="hidden" name="ppp" id="ppp" value="">
	<input type="hidden" name="listParams" id="listParams" value="${listParams}">	
</form>
<script type="text/javascript">
function showPage(actionpath){
	document.blankForm.action=actionpath;
	blankForm.submit();
}

function showTargetPage(actionpath, target) {
	$('#blankForm').attr('target', target);
	showPage(actionpath);
	$('#blankForm').attr('target', '_self');
}

//菜单请求
//actionpath 目标路径
//returnpath 返回路径
function toTargetPage(actionpath,returnpath){
	document.blankForm.returnPath.value=returnpath;
	document.blankForm.action=actionpath;
	blankForm.submit();
}

function post(URL, PARAMS) {
	var temp = document.createElement("form");
	temp.action = URL;
	temp.target = "iframe";
	temp.method = "post";
	temp.style.display = "none";
	for ( var x in PARAMS) {
		var opt = document.createElement("textarea");
		opt.name = x;
		opt.value = PARAMS[x];
		// alert(opt.name)        
		temp.appendChild(opt);
	}
	document.body.appendChild(temp);
	temp.submit();
	return temp;
}
</script>