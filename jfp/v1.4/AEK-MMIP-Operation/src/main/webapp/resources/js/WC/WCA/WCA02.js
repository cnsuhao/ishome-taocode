var imgIndex = 0;
$(function() {
	var imgIndex = 0;
	$('#submitBtn').click(function(){
		var zczForm = $("#zcz_form").serializeJSON();
		if (zczForm['k02_zjbh'].isEmpty()) {
			$("#k02_zjbh").errMsg('注册证号不能为空！');
			return false;
		}
		if (zczForm['f09_zccpmc'].isEmpty()) {
			$("#f09_zccpmc").errMsg('注册产品名不能为空！');
			return false;
		}
		if (zczForm['f04_yxksrq'].isEmpty()) {
			$("#begindate").errMsg('注册日期不能为空！');
			return false;
		}
		if (zczForm['f05_yxzzrq'].isEmpty()) {
			$("#enddate").errMsg('失效日期不能为空！');
			return false;
		}
		if ($('#k01_sccj_qyid').val() == '') {
			$('#f12_scqy_mc').errMsg('请选择生产厂家！');
			return false;
		}
		if ($('#f20_cpsyfw').val().length > 400) {
			$('#f20_cpsyfw').errMsg('产品适用范围过长！');
			return false;
		}
		if ($('#f32_bz').val().length > 400) {
			$('#f32_bz').errMsg('备注过长！');
			return false;
		}
		$("#zcz_form").submit();
	});
	
	$('#zczuploadify').uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf',
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '',
        'buttonClass' :'act-info-img2',
       // 'hideButton':true,
       // 'rollover':true,
     //   'buttonImage': '/resources/img/btn_bg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 80,
        'width': 70,
        //上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc': 'Image Files',
        //允许上传的文件后缀
        'fileTypeExts': "*.gif; *.jpg; *.png; *.pdf",
        'fileSizeLimit':fileSizeLimit,
        'simUploadLimit':10,//允许同时上传的个数
        'queueSizeLimit':10,//当允许多文件生成时，设置选择文件的个数，默认值：999 。
        'fileObjName': 'file',
        //发送给后台的其他参数通过formData指定
        //'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
        //上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false  自动生成,  不带#
        //'queueID': 'fileQueue',
        //选择文件后自动上传
        'auto': true,
        //设置为true将允许多文件上传
        'multi': true,
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
            var fileType = JSON.parse(data).message;
            var imgPath = '/resources/skin/yy/images/pdf.png';
            var filePath = ftpImgPath + downloaderPath + "/" + imgCode + sysToken;
            if ('.pdf' != fileType && '.PDF' != fileType) {
            	imgPath = filePath;
            }
        	var htmlTmp = '<div id="imgDiv_' + imgIndex + '" class="reg-img-box">\n\
                        <div class="act-info-img">\n\
        					<a href="'+filePath+'" target="_blank"><img src="'+imgPath+'" /></a>\n\
                        </div>\n\
                        <a href="javascript:deleteImg(\'' + imgCode + '\', \'' + imgIndex + '\')">删除</a>\n\
                    </div>';
        	
        	$("#uploadImgList").append(htmlTmp);
        	var imgPath = $('#imagePaths').val() + imgCode + ';';
            $('#imagePaths').val(imgPath);
            imgIndex++;
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
	$("#zczuploadify-button").removeClass("uploadify-button");
});

/**
 * 展示图片
 * @param imgPaths
 */
function createImg(imgPaths) {
	$("#uploadImgList").html('');
	var imgPathArray = imgPaths.split(';');
	for (var i = 0; i < imgPathArray.length; i++) {
		var imgInfo = imgPathArray[i];
		if (imgInfo != '') {
			var imgtype = imgInfo.split('@');
			var imgPath = '/resources/skin/yy/images/pdf.png';
			var filePath = ftpImgPath + downloaderPath + "/" + imgtype[0] + sysToken;
			if (imgtype[1] != '.pdf') {
				imgPath = filePath;
			}
			var htmlTmp = '<div id="imgDiv_' + imgIndex + '" class="reg-img-box">\n\
            <div class="act-info-img">\n\
				<a href="'+filePath+'" target="_blank"><img src="'+imgPath+'" /></a>\n\
            </div>\n\
            <a href="javascript:deleteImg(\'' + imgtype[0] + '\', \'' + imgIndex + '\')">删除</a>\n\
        </div>';

			$("#uploadImgList").append(htmlTmp);
			imgIndex++;
		}
	}
}
/**
 * 删除图片
 * @param imgCode
 */
function deleteImg(imgCode, imgIndex) {
	var imgPath = $('#imagePaths').val();
	var newImgPath = imgPath.replace(imgCode + ';', '');
    $('#imagePaths').val(newImgPath);
    $('#imgDiv_' + imgIndex).remove();
}

/**
 * 查询厂家信息
 */
function searchFactory() {
	var cjid = $('#k01_sccj_qyid').val();
	$.ajax({
		type: 'POST',
		url: '/31403017/' + cjid,
		success: function(msg) {
			var obj = msg.result;
			$('#f13_scqy_dz').val(obj.f16_szxxdz);
			$('#f02_qyqc').val($('#f12_scqy_mc').val());
		}
	});
}
/**
 * 查询注册证信息
 */
function searchZcz() {
	var zczbh = $('#k02').val();
	$.ajax({
		type: 'POST',
		url: '/31403018/' + zczbh,
		data: {'k02':zczbh},
		success: function(msg) {
			var obj = msg.result;
			$('#k01_sccj_qyid').val(obj.f13);
			$('#imagePaths').val(obj.bbb);
			$('#k02_zjbh').val(obj.k02);//注册证号不做填充
			$('#f08_regno').val(obj.f09);
			$('#f09_zccpmc').val(obj.f10);
			$('#f10_cpywmc').val(obj.f11);
			$('#begindate').val(obj.f04);
			$('#enddate').val(obj.f05);
			$('#f12_scqy_mc').val(obj.f14);
			$('#f13_scqy_dz').val(obj.f15);
			$('#f15_scqy_cd').val(obj.f17);
			$('#f14_fdzzs').val(obj.f16);
			$('#f12_scqy_yb').val(obj.f18);
			$('#f54_scqy_dhhm').val(obj.f19);
			$('#f16_cpzbbh').val(obj.f20);
			$('#f17_syzcdwzgg').val(obj.f21);
			$('#f18_zczgg').val(obj.f22);
			$('#f19_cpxnjgjzc').val(obj.f23);
			$('#f20_cpsyfw').val(obj.f24);
			$('#f22_zcdljgmc').val(obj.f26);
			$('#f24_shfwjgmc').val(obj.f28);
			$('#f25_shfwrx').val(obj.f29);
			$('#f26_shfwdz').val(obj.f30);
			$('#f27_cpjjz').val(obj.f31);
			$('#f29_shdw').val(obj.f33);
			$('#f32_bz').val(obj.f36);
			$('#f21_zcdljgid').val(obj.f25);
			$('#f23_shfwjgid').val(obj.f27);
			$('#imagePaths').val(obj.bbb);
			createImg(obj.eb1);
			$(".lInput").hide();
		}
	});
}

function subYzcForm() {
	$('#f02_qyqc').val($('#f12_scqy_mc').val());
	if ($('#f02_qyqc').val() == '') {
		alert('请输入厂家名称！');
		return false;
	}
	$('#yzcForm').submit();
}
