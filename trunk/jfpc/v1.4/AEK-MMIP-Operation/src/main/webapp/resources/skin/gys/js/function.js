function editMobile(obj, act){
	var title = act.toString().decode(1,"设置",2,"修改","设置");
	showWindow(title+"手机号码","/31402050/11", 370, 150, true,function(){
		if(top.text != undefined){
			$(obj).parent().prev().text(top.text.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2'));
			delete top.text;
		}
	});
}

function editPassword(obj, act){
	var title = act.toString().decode(1,"设置",2,"修改","设置");
	showWindow(title+"登录密码","/31402050/21", 360, 260, true);
}

function editEmail(obj, act){
	var title = act.toString().decode(1,"设置",2,"修改","设置"),
		height = act.toString().decode(1,150,2,205,150);
	showWindow(title+"电子邮箱","/31402050/31?act="+act, 400, height, true,function(){
		if(top.text != undefined){
			$(obj).parent().prev().text(top.text);
			delete top.text;
		}
	});
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