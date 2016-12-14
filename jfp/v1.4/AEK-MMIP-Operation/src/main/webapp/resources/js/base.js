$(function(){
	/*时间处理*/
	/*var $endDate = $(".endDate"),
	$startDate = $(".startDate");
	$startDate.each(function(i, item){
		$(item).datetimepicker({
			language:  'zh-CN',
			autoclose: true,
			minView: 0,
		    format: 'yyyy-mm-dd hh:ii'
		}).on('changeDate',function(ev){
		    $endDate.eq(i).datetimepicker('setStartDate', $(this).find('input').val());
		});
	});
	$endDate.each(function(i, item){
		$(item).datetimepicker({
			language:  'zh-CN',
			autoclose: true,
			minView: 0,
		    format: 'yyyy-mm-dd hh:ii'
		}).on('changeDate',function(ev){
		    $startDate.eq(i).datetimepicker('setEndDate', $(this).find('input').val());
		});
	});*/
	//全选处理
	$("#allCheck-btn").on('click', function(){
		$("#tableList .check-btn").prop('checked', $(this).prop('checked'));
	})

	$("#tableList").on('click', '.check-btn', function(){
		if($(this).prop('checked') == false){
			$("#allCheck-btn").prop('checked', false);
		}else{
			var flag = true;
			$("#tableList .check-btn").each(function(){
				if(this.checked == false) flag = false;
				return false;
			});
			$("#allCheck-btn").prop('checked', flag);
		}
	});
	/*查看大图*/
	$imgMod = $(".img-mod");
	if($imgMod.length){
		$(".img-mod").on('click', 'a.thumbnail', function(){
			var $this = $(this),
				url = $.trim($this.attr('href')),
				type = $this.data('type');

			if(url && type == 'pdf'){
				return true;
			}else if(url){
				top.showImg(url);
				return false;
			}
		})
	}
});