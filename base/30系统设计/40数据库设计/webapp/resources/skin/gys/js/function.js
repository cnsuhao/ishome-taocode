$(function(){
	getMsg();
});

function getMsg(){
	ajax("ajax/msg.php?t="+Math.random(),{"act":"getMsg"},function(json){
		if(json.code == 0){
			$("#msgNum").text(json.msg);
		}
	});
}

function selectHos(){
	showWindow("选择接受订单短信的医院","选择管理医院.html", 650, 500, true);
}

function scope(){
	showWindow("选择经营范围","选择经营范围.html", 650, 500, true);
}

function selectHosReg(){
	showWindow("选择注册证信息","选择注册证信息.html", 650, 500, true);
}

function editMobile(act){
	var title = act.toString().decode(1,"设置",2,"修改","设置");
	showWindow(title+"手机号码","修改手机号码.html", 480, 150, true);
}

function editPassword(act){
	var title = act.toString().decode(1,"设置",2,"修改","设置");
	showWindow(title+"登录密码","修改密码.html", 520, 200, true);
}

function editEmail(act){
	var title = act.toString().decode(1,"设置",2,"修改","设置");
	showWindow(title+"电子邮箱","修改电子邮箱.html", 520, 150, true);
}