$(function(){
	getMsg();
});

function getMsg(){
//	ajax("ajax/msg.php?t="+Math.random(),{"act":"getMsg"},function(json){
//		if(json.code == 0){
//			$("#msgNum").text(json.msg);
//		}
//	});
}

function selectHos(){
	showWindow("选择接受订单短信的医院","?a=account_sel_hospital", 650, 500, true);
}

function scope(){
	showWindow("选择经营范围","选择经营范围.html", 650, 500, true);
}