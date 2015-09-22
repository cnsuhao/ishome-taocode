$(function() {
	var imgIndex = 0;
	$('#submitBtn').click(function(){
		$("#zczSearchForm").submit();
	});
	
	$('#normal').click(function (){
		$('#fb2').val('');
		$('#normal').attr('class', 'current');
		$('#overdue').attr('class', '');
		$("#zczSearchForm").submit();
	});
	
	$('#overdue').click(function (){
		$('#fb2').val('2');
		$('#overdue').attr('class', 'current');
		$('#normal').attr('class', '');
		$("#zczSearchForm").submit();
	});
	
	$('#voerDue3M').click(function (){
		$('#ddd').val('1');
		//$('#fb2').val('2');
	//	$('#overdue').attr('class', '');
	//	$('#normal').attr('class', 'current');
		$("#zczSearchForm").submit();
	});
	
});
