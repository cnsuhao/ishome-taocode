/**
 * 初始化右侧注册证证编号
 */
function initZjbh() {
	var html = '';
	for ( var i = 0; i < dataList.length; i++) {
		var obj = dataList[i];
		var clazz = '';
		if (obj.eb1 == '1') {
			clazz = 'red';
		}
		html += '<li><a href="javascript:void(0);" onclick="setZczValue(' + i + ', this)" class="'
				+ clazz + '">' + obj.k02_zjbh
				+ '</a></li>';
	}
	$('#exp-step-nav').html(html);
}

/**
 * 根据下标，填充数据
 * 
 * @param index
 */
function setZczValue(index, obj) {
	$('#exp-step-nav a').removeClass("current");
	$(obj).addClass("current");
	var obj = dataList[index];
	$.ajax({
		type : 'POST',
		url : '/314030739/' + obj.puk,
		success : function(msg) {
			$('#exp-step-nav a').eq(index).addClass('current');

			obj = msg.result;
			$('#k02_zjbh').html(obj.k02_zjbh);
			$('#f08_regno').html(obj.f08_regno);
			$('#f09_zccpmc').html(obj.f09_zccpmc);
			$('#f10_cpywmc').html(obj.f10_cpywmc);
			$('#f04_yxksrq').html(obj.f04_yxksrq + '至' + obj.f05_yxzzrq);
			$('#f12_scqy_mc').html(obj.f12_scqy_mc);
			$('#f13_scqy_dz').html(obj.f13_scqy_dz);
			$('#f15_scqy_cd').html(obj.f15_scqy_cd);
			$('#f14_fdzzs').html(obj.f14_fdzzs);
			$('#f12_scqy_yb').html(obj.f12_scqy_yb);
			$('#f54_scqy_dhhm').html(obj.f54_scqy_dhhm);
			$('#f16_cpzbbh').html(obj.f16_cpzbbh);
			$('#f17_syzcdwzgg').html(obj.f17_syzcdwzgg);
			$('#f19_cpxnjgjzc').html(obj.f19_cpxnjgjzc);
			$('#f20_cpsyfw').html(obj.f20_cpsyfw);
			$('#f22_zcdljgmc').html(obj.f22_zcdljgmc);
			$('#f24_shfwjgmc').html(obj.f24_shfwjgmc);
			$('#f25_shfwrx').html(obj.f25_shfwrx);
			$('#f26_shfwdz').html(obj.f26_shfwdz);
			$('#f27_cpjjz').html(obj.f27_cpjjz);
			$('#f29_shdw').html(obj.f29_shdw);
			$('#f32_bz').html(obj.f32_bz);
			if (obj.fb1 == '1') {
				$('#cjyyzz').attr('href', 'javascript:showTargetPage("/314030742/' + obj.k01_sccj_qyid + '/0", "_blank")');
			} else {
				$('#cjyyzz').attr('href', 'javascript:void(0);');
				$('#cjyyzz').addClass('nobg');
			}
			if (obj.fb2 == '1') {
				$('#cjjyxkz').attr('href', 'javascript:showTargetPage("/314030742/' + obj.k01_sccj_qyid + '/1", "_blank")');
			} else {
				$('#cjjyxkz').attr('href', 'javascript:void(0);');
				$('#cjjyxkz').addClass('nobg');
			}
			if (obj.fb3 == '1') {
				$('#jxsqs').attr('href', 'javascript:showTargetPage("/314030743/' + obj.puk + '/' + obj.ppp + '","_blank")');
			} else {
				$('#jxsqs').attr('href', 'javascript:void(0);');
				$('#jxsqs').addClass('nobg');
			}
			var imgInfos = obj.bbb.split(';');
			var imgHtml = '';
			var pdfHtml = '';
			for ( var i = 0; i < imgInfos.length; i++) {
				var imgInfo = imgInfos[i];
				if (imgInfo != '') {
					var imgTmp = imgInfo.split('@');
					if (imgTmp[1] == '.pdf' || imgTmp[1] == '.PDF') {
						pdfHtml += '<a href="'
								+ ftpImgPath
								+ '/00003030/'
								+ imgTmp[0]
								+ sysToken
								+ '" target="_blank"><img src="/resources/skin/yy/images/pdf.png" /></a>';
					} else {
						imgHtml += '<a href="' + ftpImgPath + '/00003030/'
								+ imgTmp[0] + sysToken + '" target="_blank">'
								+ '<img src="' + ftpImgPath + '/00003030/'
								+ imgTmp[0] + sysToken
								+ '" width="590" /></a><br /><br />';
					}
				}
			}
			if (imgHtml != '') {
				$('#imageTitle').show();
				$('#show_images').show();
				$('#show_images').html(imgHtml);
			} else {
				$('#imageTitle').hide();
				$('#show_images').hide();
				$('#show_images').html("");
			}
			if (pdfHtml != '') {
				$('#pdfTitle').show();
				$('#pdfList').show();
				$('#pdfList').html(pdfHtml);
			} else {
				$('#pdfTitle').hide();
				$('#pdfList').hide();
				$('#pdfList').html("");
			}
		}
	});
}