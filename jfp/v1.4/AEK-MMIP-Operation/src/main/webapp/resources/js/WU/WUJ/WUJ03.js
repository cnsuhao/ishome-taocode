$(function() {
	$('#cjzl_upload_subbtn').click(function(){
		var cjxxForm = $("#cjzl_upload_form").serializeJSON();
		if (cjxxForm['cjscxkzLogoUrl'].isEmpty()) {
			$("#cjyyzz_img_path_hidden").errMsg('请上传营业执照图片！');
			return false;
		}
		if (cjxxForm['csyyzzCode'].isEmpty()) {
			$("#csyyzzCode").errMsg('请填写营业执照编号！');
			return false;
		}
		if (cjxxForm['csyyzzStarDate'].isEmpty()) {
			$("#csyyzzStarDate").errMsg('请填写营业执照有效开始日期！');
			return false;
		}
		if (cjxxForm['csyyzzEndDate'].isEmpty()) {
			$("#csyyzzEndDate").errMsg('请填写营业执照有效结束日期！');
			return false;
		}
		if (cjxxForm['cjyyzzFzdwmc'].isEmpty()) {
			$("#yyzz_f01_fzdwmc").errMsg('请填写发证单位名称！');
			return false;
		}
		if (cjxxForm['cjyyzzFzrq'].isEmpty()) {
			$("#yyzz_f02_fzrq").errMsg('请填发证日期！');
			return false;
		}
		
		if (cjxxForm['csyyzzLogoUrl'].isEmpty()) {
			$("#cjscxkz_img_path_hidden").errMsg('请上传生产许可证图片！');
			return false;
		}
		if (cjxxForm['cjscxkzCode'].isEmpty()) {
			$("#cjscxkzCode").errMsg('请填写生产许可证编号！');
			return false;
		}
		if (cjxxForm['cjscxkzStarDate'].isEmpty()) {
			$("#cjscxkzStarDate").errMsg('请填写生产许可证有效开始日期！');
			return false;
		}
		if (cjxxForm['cjscxkzEndDate'].isEmpty()) {
			$("#cjscxkzEndDate").errMsg('请填写生产许可证有效结束日期！');
			return false;
		}
		if (cjxxForm['cjscxkzFzdwmc'].isEmpty()) {
			$("#jyxkz_f01_fzdwmc").errMsg('请填写发证单位名称！');
			return false;
		}
		if (cjxxForm['cjscxkzFzrq'].isEmpty()) {
			$("#jyxkz_f02_fzrq").errMsg('请填发证日期！');
			return false;
		}
		$('#cjzl_upload_form').submit();
		
	});
	// 厂家营业执照文件上传
    $("#cj_yyzz_uploadify").uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf',
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '上传图片',
       // 'hideButton':true,
       // 'rollover':true,
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 10,
        'width': 100,
        //上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc': 'Image Files',
        //允许上传的文件后缀
        'fileTypeExts': fileType,
        'fileSizeLimit':fileSizeLimit,
        'simUploadLimit':1,//允许同时上传的个数
        'queueSizeLimit':1,//当允许多文件生成时，设置选择文件的个数，默认值：999 。
        'fileObjName': 'file',
        //发送给后台的其他参数通过formData指定
        //'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
        //上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false  自动生成,  不带#
        //'queueID': 'fileQueue',
        //选择文件后自动上传
        'auto': true,
        //设置为true将允许多文件上传
        'multi': false,
        'onError':function(event,queueId,fileObj,errorObj)
        {
            //错误时触发
            //错误的类型，有三种‘HTTP’, ‘IO’, or ‘Security’//错误的描述
            alert(event);
            alert(queueId);
            alert(fileObj);
            alert(errorObj);
        },
        'onUploadSuccess':function(file, data, response){
            var imgCode = JSON.parse(data).result;
            var imgPath = ftpImgPath + downloaderPath + "/" + imgCode + sysToken;
            $('#cjyyzz_img').attr('src', imgPath);
            $('#cjyyzz_img_path_hidden').attr('value', imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
    
    // 厂家生产许可证文件上传
    $("#cj_jyxkz_uploadify").uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf',
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '上传图片',
       // 'hideButton':true,
       // 'rollover':true,
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 10,
        'width': 100,
        //上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc': 'Image Files',
        //允许上传的文件后缀
        'fileTypeExts': fileType,
        'fileSizeLimit':fileSizeLimit,
        'simUploadLimit':1,//允许同时上传的个数
        'queueSizeLimit':1,//当允许多文件生成时，设置选择文件的个数，默认值：999 。
        'fileObjName': 'file',
        //发送给后台的其他参数通过formData指定
        //'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
        //上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false  自动生成,  不带#
        //'queueID': 'fileQueue',
        //选择文件后自动上传
        'auto': true,
        //设置为true将允许多文件上传
        'multi': false,
        'onError':function(event,queueId,fileObj,errorObj)
        {
            //错误时触发
            //错误的类型，有三种‘HTTP’, ‘IO’, or ‘Security’//错误的描述
            alert(event);
            alert(queueId);
            alert(fileObj);
            alert(errorObj);
        },
        'onUploadSuccess':function(file, data, response){
            var imgCode = JSON.parse(data).result;
            var imgPath = ftpImgPath + downloaderPath + "/" + imgCode + sysToken;
            $('#cjscxkz_img').attr('src', imgPath);
            $('#cjscxkz_img_path_hidden').attr('value', imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
});