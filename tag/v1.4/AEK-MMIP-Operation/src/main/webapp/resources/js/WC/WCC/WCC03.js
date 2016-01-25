$(function() {
	$('#submitBtn').click(function(){
		var wtsForm = $("#wts_form").serializeJSON();
		if ($('#fb2').val() == '') {
			$("#fb2").errMsg('请填写证书名称！');
			return false;
		}
		if (wtsForm['f10'].isEmpty()) {
			$("#f10").errMsg('员工姓名不能为空！');
			return false;
		}
		if ($('#yyid').val() == '') {
			$("#yymc").errMsg('请选择医院！');
			return false;
		}
//		if ($('#k02_zjbh').val() == '') {
//			$("#k02_zjbh").errMsg('请输入证书编号！');
//			return false;
//		}
		if (wtsForm['bbb'].isEmpty()) {
			$("#wts_img_error").errMsg('请上传委托书照片！');
			return false;
		}
		if (wtsForm['f04_yxksrq'].isEmpty()) {
			$("#f04_yxksrq").errMsg('委托书有效开始日期不能为空！');
			return false;
		}
		if (wtsForm['f05_yxzzrq'].isEmpty()) {
			$("#f05_yxzzrq").errMsg('委托书有效结束日期不能为空！');
			return false;
		}
		if (wtsForm['f13'].isEmpty()) {
			$("#sfz_img_error").errMsg('请上传身份证扫描件！');
			return false;
		}
		$("#wts_form").submit();
	});
	// 经销商授权书扫描件文件上传
    $("#wts_uploadify").uploadify({
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
            $('#wts_img').attr('src', imgPath);
            $('#wts_img_a').attr('href', imgPath);
            $('#wts_img_path_hidden').val(imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
    
 // 经销商授权书扫描件文件上传
    $("#sfz_uploadify").uploadify({
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
            $('#sfz_img').attr('src', imgPath);
            $('#sfz_img_a').attr('href', imgPath);
            $('#wts_sfz_img_path_hidden').val(imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
});