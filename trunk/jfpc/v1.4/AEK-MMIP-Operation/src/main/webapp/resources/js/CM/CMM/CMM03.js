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
        /*onUploadProgress: function (file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            // console.log(file);
            top.msg('正在上传:'+ file.name +',已完成:' +  (parseInt(bytesUploaded *100 / bytesTotal )) + '%', 'loading')
         // $("#status").html("当前文件已上传：" + bytesUploaded + "当前文件大小：" + bytesTotal + "队列已上传：" + totalBytesUploaded + "队列大小：" + totalBytesTotal);
        },*/
        'onUploadSuccess':function(file, data, response){
        	var res = JSON.parse(data);
        	//var imgpath = 'http://192.168.1.57:8080';
            var imgCode = res.result;
            var imgPath = ftpImgPath + downloaderPath + "/" + imgCode + sysToken;
            var fileType = res.message;
            var imgPath2 = '/resources/skin/yy/images/pdf.png';
            
        	var htmlTmp = '<a target="_blank" data-type="'+ (fileType == ".pdf" ? 'pdf' : 'img')  +'" href="'+ imgPath +'" id="imgDiv_' + imgIndex + '"  class="thumbnail reg-img-item">\
		      	<img data-src="holder.js/90x90" src="'+( fileType == ".pdf"? imgPath2 : imgPath )+'">\
		      	<span  onclick="deleteImg(\'' + imgCode + '\', \'' + imgIndex + '\');return false;" class="del" >删除</span>\
		    </a>';
        	$("#uploadImgList").prepend(htmlTmp);
        	var imgPath = $('#imagePaths').attr('value') + imgCode + ';';
            $('#imagePaths').attr('value', imgPath);
            imgIndex++;
            //top.baguetteBox.run($('#uploadImgList'));
        },
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
});
var imgIndex = 0;
/**
 * 展示图片
 * @param imgPaths
 */
function createImg(ipp,imgPaths) {
    var imgPathArray = imgPaths.split(';');
    var id = '/00003030/';
    var token = '/2515244',
        htmlTmp = "";
    for (var i = 0; i < imgPathArray.length; i++) {
        if (imgPathArray[i] != '') {
            var imgPath =ipp + id + imgPathArray[i] + token;
            htmlTmp += '<a  id="imgDiv_' + imgIndex + '"  class="thumbnail reg-img-item" href="'+ imgPath +'">\
                            <img data-src="holder.js/90x90" src="'+ imgPath +'">\
                            <span class="del" onclick="deleteImg(\'' + imgPathArray[i] + '\', \'' + imgIndex + '\')">删除</span>\
                        </a>';
            imgIndex++;
        }
    }
    $("#uploadImgList").prepend(htmlTmp);
}

/**
 * 删除图片
 * @param imgCode
 */
function deleteImg(imgCode, imgIndex) {
    if(confirm('确定删除？')){
    	$('#imagePaths').val(function(i, value){
    		return value.replace(imgCode + ';', '');
    	});
        $('#imgDiv_' + imgIndex).remove();
    }
    return false;
}