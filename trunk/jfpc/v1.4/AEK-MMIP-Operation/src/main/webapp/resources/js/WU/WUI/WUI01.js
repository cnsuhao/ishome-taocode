$(function() {
	// form表达提交
	$('#jcxx_subbtn').click(function(){
		var jcxxForm = $("#gys_jcxx").serializeJSON();
		if (jcxxForm['f02_qyqc'].isEmpty()) {
			$("#f02_qyqc").errMsg('供应商全称不能为空！');
			return false;
		}
		if (jcxxForm['f08_frxm'].isEmpty()) {
			$("#f08_frxm").errMsg('法人代表不能为空！');
			return false;
		}
		if (jcxxForm['f13_szs'].isEmpty() || jcxxForm['f13_szs'] == '选择省') {
			$("#f16_szxxdz").errMsg('请选择所在省份！');
			return false;
		}
		if (jcxxForm['f14_szs'].isEmpty() || jcxxForm['f14_szs'] == '选择市') {
			$("#f16_szxxdz").errMsg('请选择所在市！');
			return false;
		}
		if (jcxxForm['f15_szq'].isEmpty() || jcxxForm['f15_szq'] == '选择县') {
			$("#f16_szxxdz").errMsg('请选择所在县或区！');
			return false;
		}
		if (jcxxForm['f16_szxxdz'].isEmpty()) {
			$("#f16_szxxdz").errMsg('注册地址不能为空！');
			return false;
		}
		if (jcxxForm['f18_lxdh'].isEmpty()) {
			$("#f18_lxdh").errMsg('联系电话不能为空！');
			return false;
		}
		if (jcxxForm['scopeMemo'].isEmpty() || jcxxForm['jyfwHidden'].isEmpty()) {
			$("#scopeMemoErr").errMsgTip('经营范围不能为空！');
			return false;
		}
		if(!jcxxForm['f18_lxdh'].isTel()){
			$("#f18_lxdh").errMsg('请输入正确的固定电话号码！');
			return false;
		}
		
		$("#gys_jcxx").submit();
	});
	
	$('#yyzz_subbtn').click(function(){
			var yyzzForm = $("#yyzz_form").serializeJSON();
//			if (yyzzForm['bbb'].isEmpty()) {
//				$("#bbb").errMsg('请上传营业执照扫描件！');
//				return false;
//			}
			if (yyzzForm['k02_zjbh'].isEmpty()) {
				$("#yyzz_k02_zjbh").errMsg('营业执照编号不能为空！');
				return false;
			}
			if (yyzzForm['f04_yxksrq'].isEmpty()) {
				$("#yyzz_f04_yxksrq").errMsg('营业执照有效期不能为空！');
				return false;
			}
			if (yyzzForm['f05_yxzzrq'].isEmpty()) {
				$("#yyzz_f05_yxzzrq").errMsg('营业执照有效期不能为空！');
				return false;
			}
			if (yyzzForm['f01_fzdwmc'].isEmpty()) {
				$("#yyzz_f01_fzdwmc").errMsg('发证单位不能为空！');
				return false;
			}
			if (yyzzForm['f02_fzrq'].isEmpty()) {
				$("#yyzz_f02_fzrq").errMsg('发证日期不能为空！');
				return false;
			}
		$("#yyzz_form").submit();
	});
	
	$('#jyxkz_subbtn').click(function(){
			var jyxkzForm = $("#jyxkz_form").serializeJSON();
			if (jyxkzForm['k02_zjbh'].isEmpty()) {
				$("#jyxkz_k02_zjbh").errMsg('经营许可证编号不能为空！');
				return false;
			}
			if (jyxkzForm['f04_yxksrq'].isEmpty()) {
				$("#jyxkzksrq").errMsg('经营许可证有效期不能为空！');
				return false;
			}
			if (jyxkzForm['f05_yxzzrq'].isEmpty()) {
				$("#jyxkzjerq").errMsg('经营许可证有效期不能为空！');
				return false;
			}
			if (jyxkzForm['f01_fzdwmc'].isEmpty()) {
				$("#jyxkz_f01_fzdwmc").errMsg('发证单位不能为空！');
				return false;
			}
			if (jyxkzForm['f02_fzrq'].isEmpty()) {
				$("#jyxkz_f02_fzrq").errMsg('发证日期不能为空！');
				return false;
			}
		$("#jyxkz_form").submit();
	});
	
	$('#swdjz_subbtn').click(function(){
			var swdjzForm = $("#swdjz_form").serializeJSON();
			if (swdjzForm['k02_zjbh'].isEmpty()) {
				$("#swdjz_k02_zjbh").errMsg('税务登记证号不能为空！');
				return false;
			}
			if (swdjzForm['f01_fzdwmc'].isEmpty()) {
				$("#swdjz_f01_fzdwmc").errMsg('发证单位不能为空！');
				return false;
			}
			if (swdjzForm['f02_fzrq'].isEmpty()) {
				$("#swdjz_f02_fzrq").errMsg('发证日期不能为空！');
				return false;
			}
//			if (swdjzForm['f04_yxksrq'].isEmpty()) {
//				$("#swdjzksrq").errMsg('税务登记证有效期不能为空！');
//				return false;
//			}
//			if (swdjzForm['f05_yxzzrq'].isEmpty()) {
//				$("#swdjzjerq").errMsg('税务登记证有效期不能为空！');
//				return false;
//			}
		$("#swdjz_form").submit();
	});

    // 供应商基础信息文件上传
    $("#jcxx_uploadify").uploadify({
    	//指定swf文件
        'swf': '/resources/uploadify/uploadify.swf?t='+Math.random(),
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
    
    $("#yyzz_uploadify").uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf?t='+Math.random(),
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '上传图片',
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 20,
        'width': 200,
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
            $('#gys_yyzz_img').attr('src', imgPath);
            $('#gys_yyzz_img_a').attr('href', imgPath);
            $("#gys_yyzz_img").prev().remove();
            $('#yyzz_img_path_hidden').attr('value', imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
    
    $("#jyxkz_uploadify").uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf?t='+Math.random(),
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '上传图片',
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 20,
        'width': 200,
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
            $('#gys_jyxkz_img').attr('src', imgPath);
            $('#gys_jyxkz_img_a').attr('href', imgPath);
            $('#jyxkz_img_path_hidden').attr('value', imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
    
    $("#swdjz_uploadify").uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf?t='+Math.random(),
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '上传图片',
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 20,
        'width': 200,
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
        'onUploadSuccess':function(file, data, response){
            var imgCode = JSON.parse(data).result;
            var imgPath = ftpImgPath + downloaderPath + "/" + imgCode + sysToken;
            $('#gys_swdjz_img').attr('src', imgPath);
            $('#gys_swdjz_img_a').attr('href', imgPath);
            $('#swdjz_img_path_hidden').attr('value', imgCode);
            //$('#uploadify').uploadify('upload')"
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
});
