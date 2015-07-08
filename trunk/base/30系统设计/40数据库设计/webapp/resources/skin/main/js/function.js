$(function(){
	getMsg();
	var phText = new placeHolder();
	phText.initInput();
});

function getMsg(){
	ajax("ajax/msg.php?t="+Math.random(),{"act":"getMsg"},function(json){
		if(json.code == 0){
			$("#msgNum").text(json.msg);
		}
	});
}

function selectHos(){
	showWindow("选择接受订单短信的医院","?a=account_sel_hospital", 650, 500, true);
}