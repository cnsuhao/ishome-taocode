var uhtml = "<div class=\"boxContent\">\n\
	<div class=\"clearfix\">\n\
		<span class=\"mlogo mlogo-min lFloat\" id=\"yui-u-logo\"></span>\n\
		<div class=\"userInfoBoxText\">\n\
			<span id=\"yui-u-name\"></span>，<span id=\"yui-u-tips\"></span><br />\n\
			<span id=\"yui-u-hospitalName\" class=\"gray\"></span>\n\
		</div>\n\
	</div>\n\
	<ul class=\"userRemind clearfix2\">\n\
		<li><a href=\"javascript:void(0);\" onclick=\"post('/3140205?index=0');\" class=\"btn\">帐户设置</a></li>\n\
		<li><a href=\"javascript:void(0);\" onclick=\"post('/3140205?index=1');\" class=\"btn\">成员管理</a></li>\n\
		<li class=\"noMar\"><a href=\"javascript:showLayer('/32011002', 500, true);\" class=\"btn\">添加供应商</a></li>\n\
	</ul>\n\
	<div class=\"gray\" style=\"text-align:right;\" id=\"yui-u-date\">今天是 星期四  2014年11月6日</div>\n\
</div>";
if($(".userBox").length <= 0) $(".boxContentRight").append(uhtml);
ajax("/3000003?t="+Math.random(),{},function(json){
	if(json.code == 0){
		var timeText = "";
		var now = new Date(),hour = now.getHours();
		var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
		if(hour < 12) timeText="上午好！";
		if(hour < 18 && hour > 12) timeText="下午好！";
		if(hour < 24 && hour > 18) timeText="晚上好！";
		$("#yui-u-tips").html(timeText);
		$("#yui-u-name").html(json.result.userNameCN);
		$("#yui-u-logo").html(splitLogo(json.result.userNameCN,json.result.companyLogo));
		$("#yui-u-hospitalName").html(json.result.companyNameCN);
		$("#yui-u-date").html("今天是："+dayNames[now.getDay()] +" "+ now.getFullYear() + "年"+(now.getMonth() + 1) +"月"+now.getDate()+ "日");
	}
});