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
	var title = act.toString().decode(1,"设置",2,"修改","设置"),
		height = act.toString().decode(1,150,2,205,150);
	showWindow(title+"电子邮箱","修改电子邮箱.html", 400, height, true);
}

function editCert(id, title){
	showWindow("修改"+title,"过期证书修改.html", 530, 260, true);
}

function certShow(title, url){
	showWindow("查看"+title,url, 700, 370, true);
}

function getCertList(){
	var html="", textClass="";
	for(var i=0; i<data.length;i++){
		if(data[i][0] != undefined && data[i][0] == 1) textClass=" class=\"red\"";
		html +="<li><a href=\"javascript:look(0, "+i+");\""+textClass+">"+data[i][1]+"</a></li>";
	}
	$("#certList").html(html);
}

function look(act, i){
	if(i != undefined) index = i;
	var count = data.length - 1;
	if(act != 0){
		if(act == "pre"){
			if(index > 0) index = index - 1; else index = count;
		}
		if(act == "next"){
			if(index<count) index = index + 1; else index = 0;
		}
	}
	$("#photo").attr("src",data[index][2]);
	var tempa= "<a href=\""+data[index][2]+"\" target=\"_blank\"></a>";
	$("#photo").wrap(tempa);
	if($("#name")) $("#name").text(data[index][4]);
	$("#dateTime").text(data[index][3]);
}