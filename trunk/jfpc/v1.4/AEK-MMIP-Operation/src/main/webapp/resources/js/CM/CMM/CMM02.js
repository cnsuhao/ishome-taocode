var imgIndex = 0;
$(function() {
	var ippath = $("#ippath").val();
    var imgIndex = 0;   
    $('#uploadfy').uploadify({
        //指定swf文件
        'swf': '/resources/uploadify/uploadify.swf',
        //后台处理的页面
        'uploader': uploaderPath + sysToken,
        //按钮显示的文字
        'buttonText': '<span class="glyphicon glyphicon-plus"></span>',
       // 'hideButton':true,
       // 'rollover':true,
        //'buttonImage': '/resources/img/noImg.png',
        //显示的高度和宽度，默认 height 30；width 120
        'height': 90,
        'width': 90,
        //上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
        //在浏览窗口底部的文件类型下拉菜单中显示的文本
        'fileTypeDesc': 'Image Files',
        //允许上传的文件后缀
        'fileTypeExts': '*.gif; *.jpg; *.png',
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
        'onInit': function () {//载入时触发，将flash设置到最小
            $(".uploadify-queue").remove();
        },
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
            //var imgpath = 'http://192.168.1.57:8080';
            var imgCode = JSON.parse(data).result;
            var imgPath = ftpImgPath + downloaderPath + "/" + imgCode + sysToken;
            var htmlTmp = '<a href="javasacript:;" id="imgDiv_' + imgIndex + '"  class="thumbnail reg-img-item">\
                <img data-src="holder.js/90x90" src="'+ imgPath +'">\
                <span  onclick="deleteImg(\'' + imgCode + '\', \'' + imgIndex + '\')" class="glyphicon glyphicon-remove close" ></span>\
            </a>';
            $("#uploadImgList").prepend(htmlTmp);
            var imgPath = $('#imagePaths').attr('value') + imgCode + ';';
            $('#imagePaths').attr('value', imgPath);
            imgIndex++;
            setParentFrameHeight && setParentFrameHeight();
        },
        'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
        'onSelect' : uploadify_onSelect,  
        'onSelectError' : uploadify_onSelectError,  
        'onUploadError' : uploadify_onUploadError
    });
});

/**
 * 展示图片
 * @param imgPaths
 */
function createImg(ipp,imgPaths) {
    alert(imgPaths);
	var imgPathArray = imgPaths.split(';');
	var id = '/00003030/';
    var token = '/2515244';
	for (var i = 0; i < imgPathArray.length; i++) {
		if (imgPathArray[i] != '') {
			var imgPath =ipp + id + imgPathArray[i] + token;
        	var htmlTmp = '<a href="javasacript:;" id="imgDiv_' + imgIndex + '"  class="thumbnail reg-img-item">\
	      	<img data-src="holder.js/90x90" src="'+ imgPath +'">\
	      	<span  onclick="deleteImg(\'' + imgPathArray[i] + '\', \'' + imgIndex + '\')" class="glyphicon glyphicon-remove close" ></span>\
	    </a>';
			$("#uploadImgList").prepend(htmlTmp);
			imgIndex++;
		}
	}
}

/**
 * 删除图片
 * @param imgCode
 */
function deleteImg(imgCode, imgIndex) {
    $('#imagePaths').val(function(i, value){
        return value.replace(imgCode + ';', '');
    });
    $('#imgDiv_' + imgIndex).remove();
    setParentFrameHeight && setParentFrameHeight();
}