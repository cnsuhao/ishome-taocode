$(function() {
	// 基础信息原始属性值
	// form表达提交
	$('#submitBtn').click(function(){
		var jcxxForm = $("#yyxxForm").serializeJSON();
		if (jcxxForm['f02_qyqc'].isEmpty()) {
			$("#f02_qyqc").errMsg('医院全称不能为空！');
			return false;
		}
		if (jcxxForm['f08_frxm'].isEmpty()) {
			$("#f08_frxm").errMsg('院长不能为空！');
			return false;
		}
		if (jcxxForm['f16_szxxdz'].isEmpty()) {
			$("#f16_szxxdz").errMsg('地域信息不能为空！');
			return false;
		}
		if (jcxxForm['bbb'].isEmpty()) {
			$("#bbbErr").errMsgTip('医院简介不能为空！');
			return false;
		}
		$("#yyxxForm").submit();
	});

    // 供应商基础信息文件上传
    $("#jcxx_uploadify").uploadify({
    	//指定swf文件
        'swf': '/resources/uploadify/uploadify.swf',
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '上传图片',
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 10,
        'width': 60,
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
            $('#gys_logo_img').attr('src', imgPath);
            $('#gys_logo_path_hidden').attr('value', imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
});

/**
 * 判断form表单的值是否改变
 * @param currentForm
 * @param initForm
 * @returns {Boolean}
 */
function checkChange(formId, initForm) {
    var dataformNow = $("#" + formId).serializeArray();
    var jsonTextNow = JSON.stringify({ dataform: dataformNow });
    if (jsonTextNow == initForm) {
        return false;
    } else {
        return true;
    }
}