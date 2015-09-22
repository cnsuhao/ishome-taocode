var imgIndex = 0;
/**
 * 展示图片
 * @param imgPaths
 */
function createImg(imgPaths) {
	if (imgPaths.length <= 0) {
		$("#uploadImgList").html('无');
	} else {
		var imgPathArray = imgPaths.split(';');
		var id = ftpImgPath + '/00003030/';
	    var token = '/2515244';
		for (var i = 0; i < imgPathArray.length; i++) {
			if (imgPathArray[i] != '') {
				var imgPath = id + imgPathArray[i] + token;
				var htmlTmp = '<div id="imgDiv_' + imgIndex + '" class="reg-img-box">\n\
	            <div class="act-info-img">\n\
	                <a href="'+imgPath+'" target=\"_blank\"><img src="'+imgPath+'" /></a>\n\
	            </div>\n\
	        </div>';

				$("#uploadImgList").append(htmlTmp);
			}
		}
	}
	
}