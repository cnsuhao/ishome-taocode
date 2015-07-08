$.debug = typeof debug == 'undefined' ? false : debug;
$(function(){
	resetText();
});
(function($) {
	$.fn.format = function(o) {
		var newObj = {
			success: 0
		},
		str = o;
		if(typeof str == "undefined"){
			return newObj;
		}
		if (str.indexOf("{") != -1) {
			str = str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
			newObj = $.extend({},newObj,str.toJSON());
			newObj.success = 1;
		}
		return newObj;
	};
	$.fn.Options = function(options) {
		return options == null ? $.fn.format($(this).attr("class")) : (typeof(options) == "object" ? options : $.fn.format($(this).attr(options)));
	};
	$.fn.extend({
		serializeJSON:function(options) {//输入框提示
			var opts = $.fn.Options.call(this, options);
			var $this = $(this),
				data = opts.data || $this.serializeArray(),
				list = [];
			$.each(data, function(i, filed){
				list.push('"'+filed.name+'":"'+filed.value+'"');
			});
			var uiSerialize = "{" + list.join(',') + "}";
			return uiSerialize.toJSON();
		},
		uiSwitch: function(options) {
            //tag
            return this.each(function(e) {
                var name = $.fn.Options.call(this, options),
                    //获取数组
                    boxClass = "uiSwitchBox" + e;
                name = $.extend({},
                    $.fn.uiSwitch.defaults, name);
                //重置数组,把自定义函数和默认函数合并到一起
                $(this).parent().addClass(boxClass);
                //给该tag父层增加指定的class
                name.box = "." + boxClass;
                //更改name的box属性,并赋予该tag父层指定的class名字
                Tag(name, this, e);
            });
        },
		errMsg : function(msg, a, x, y, time){
			a = a || 0;
			var $this = $(this),
				top = y || $this.height(),
				left = x || $this.scrollLeft(),
				width = $this.innerWidth(),
				time = time>0 ? 3000:time,
				msg = msg || $this.attr("tips"),
				skin = a.toString().decode(1, "sucees", 2, "warning", 3, "info", -1, "error", "error");
			if(msg){
				var html = "<div class=\"message-box message-"+skin+"\" style=\"width:"+width+"px; top:"+top+"px; left:"+left+"px;\"><span class=\""+skin+"\"></span><p>"+msg+"</p>";
				a != -1 ? html += "<div class=\"triangle-css3 transform\"></div>":"";
				html+="</div>";
				$this.before(html);
				if(time > 0){
					setTimeout(function(){
						$(".message-box").remove();
					}, time);
				}
				$(".message-box").outClick({
					click : function(){
						$(".message-box").remove();
					}
				});
			}
		},
		errMsgTip : function(msg, a, time){
			a = a || 0;
			var $this = $(this),
				top = $this.height(),
				width = $this.innerWidth()+13,
				time = time>0 ? 3000:time,
				msg = msg || $this.attr("tips"),
				skin = a.toString().decode(1, "sucees", 2, "warning", 3, "info", -1, "error", "error");
			var html = "<div class=\"message-box message-"+skin+"\" style=\"width:200px; top:0px; left:"+width+"px;";
			a == -1 ? html+="position:relative;":"";
			html+="\"><span class=\""+skin+"\"></span><p>"+msg+"</p>";
			a != -1 ? html += "<div class=\"triangle-css2 transform\"></div>":"";
			html+="</div>";
			$this.before(html);
			if(time > 0){
				setTimeout(function(){
					$(".message-box").remove();
				}, time);
			}
			$(".message-box").outClick({
				click : function(){
					$(".message-box").remove();
				}
			});
		},
		formTips : function(){
			var $this = $(this),
				input = $($this.find("input"));
			input.each(function(){
				$(this).bind({
					focus : function(){
						if($(this).attr("tips") != undefined)
							$(this).errMsg($(this).attr("tips"), 3, 0, 0, 0);
					},
					blur : function(){
						$(".message-box").remove();
					}
				});
			})
		},
		tips : function(){
			var $this = $(this),
				input = $($this.find("input"));
			input.each(function(){
				$(this).bind({
					focus : function(){
						if($(this).attr("tips") != undefined)
							$(this).errMsgTip($(this).attr("tips"), 3);
					},
					blur : function(){
						$(".message-box").remove();
					}
				});
			})
		},
		outClick: function(options) {
            //添加区域以外点击事件
            var name = $.extend({}, $.fn.outClick.defaults, options),
                $this = $(this);
            name.obj = this;
            if (typeof $this.data("idcache") == "undefined") {
                setTimeout(function() {
                        name.clickNum = 0;
                        $this.data("idcache", name.idcache);
                        $this.find("*").data("idcache", name.idcache);
                        $(document).delegate("*", "mousedown",
                            function(e) {
                                e = e || window.event;
                                var target = e.target || e.srcElement;
                                //if ($(target).data("idcache") != name.idcache && $(name.obj).find(target).length <= 0 && name.clickNum == 0) {
                                if ($(target).data("idcache") != name.idcache && $(name.obj).find(target).length <= 0 && name.clickNum == 0) {
                                    name.click.call(this);
                                    $this.removeClick();
                                    name.clickNum++;
                                }
                            });
                    },
                    50);
            }
        },
        removeClick: function() {
            //移除区域以外点击事件
            $(this).removeData("idcache");
            $(this).find("*").removeData("idcache");
            $(document).undelegate("*", "mousedown");
        },
		alert: function(options) {
			//alert提示
			return this.each(function(e) {
				//重新组json数据
				var name = new uiAlert.Tostring(options);
				uiAlert.first(name, e);
			});
		},
		input: function(options) { //输入框提示
            return this.each(function(e) {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, $.fn.input.defaults, opts);
                new fInput(this, opts);
            });
        },
		checkbox: function(options) { //复选框
            return this.each(function(e) {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, $.fn.checkbox.defaults, opts);
                new checkBox(this, opts);
            });
        },
        radio: function(options) { //单选框
            return this.each(function(e) {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, $.fn.radio.defaults, opts);
                new radio(this, opts);
            });
        },
        /**
         * 绑定输入框只能数组数字(含一位小数点)
         * @param  {object || function} options 当鼠标离开焦点时执行function
         */
        number: function(options) {
            if (typeof options === 'function') {
                var opts = {
                    fun: options
                };
            } else {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, {
                    fun: null
                }, opts);
            };
            $(this).live('keyup afterpaste focusout', function(e) {
                var val = this.value.replace(/[^\d.]/g, '').replace(/^\./g, "").replace(/\.{2,}/g, ".").replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
                val = val.length <= 0 ? 0 : val;
                if (e.type == 'focusout') {
                    if (typeof opts.fun === 'function') {
                        var val = this.value * 1;
                        opts.fun.call(this, val);
                    };
                };
                if (val === 0 || val) {
                    this.value = val;
                };
            });
        },
		nav : function(){
			var $this = $(this).find("li");
			$this.mousemove(function(event) {
				$(this).addClass('menu-hover');
			}).mouseleave(function(event) {
				$(this).removeClass('menu-hover');
			});
		}
	});
	$.fn.outClick.defaults = {
	    //默认区域外点击事件
	    idcache: "UiOutSide",
	    remove: true,
	    click: function() {}
	};
	$.fn.input.defaults = {
        text: "请输入",
        color: "#c5c5c5"
    };
    $.fn.checkbox.defaults = {
        skin: "checkboxs",
        check: "check",
        disabled: "disabled",
        hover: "over",
        text: false,
        click: false,
        readonly: false
    };
    $.fn.radio.defaults = {
        skin: "radiobox",
        check: "check",
        disabled: "disabled",
        hover: "over",
        text: false,
        alert: "已禁用",
        click: false,
        readonly: false,
        remove: true
    };
    $.fn.uiSwitch.defaults = {
        //tag默认设置
        movetag: "li",
        //点击切换
        showtag: ".stag",
        //显示/隐藏
        addtclass: "tselected",
        //选项卡增加class
        addsclass: "stselected",
        //显示/隐藏增加class
        jump: true,
        //针对链接可点/不可点
        time: 300,
        //超时时间
        custom: false,
        //点击事件
        box: null
        //为筛选切换时准备的,不需要初始设置
    };
})(jQuery);

(function(){
	//防止调试输出时错误
    if ((typeof window.console == "undefined" && window.console == null)) {
      window.console = {};
      window.console.log = function(){
          return;
      }
    }
    if($.debug){
       window.onerror = function(msg,url,line){
            console.log("URL:" + url + "\r\n错误:" + msg + "\r\n行号:" + line);
            return true;
    	};
    }
	//js扩展
	//去除空格
	String.prototype.trim = function() {
		return this.replace(/^\s+/,"").replace(/\s+$/,"");
	};
	//获取字节长度
	String.prototype.getLength = function() {
		return this.replace(/[^\x00-\xff]/g, "aa").length;
	};
	//检测url有效性
	String.prototype.isUrl = function() {
		var url = this.trim().toLowerCase(),
		re = "^((https|http|ftp|rtsp|mms)?://)"
		+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@ 
        + "(([0-9]{1,3}.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184 
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+.)*" // 域名- www. 
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]." // 二级域名 
        + "[a-z]{2,6})" // first level domain- .com or .museum 
        + "(:[0-9]{1,4})?" // 端口- :80 
        + "((/?)|" // a slash isn't required if there is no file name 
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$",
		re = new RegExp(re);
		return re.test(url);
	};
	//格式化html
	String.prototype.toText = function() {
		var str = this;
		str = str.replace(/</g, "&lt;");
		str = str.replace(/>/g, "&gt;");
		str = str.replace(/\n\r/g, "<br />");
		str = str.replace(/\n/g, "<br />");
		str = str.replace(/\r/g, "<br />");
		return str;
	};
	//格式化text To html
	String.prototype.toHtml = function() {
		var str = this;
		str = str.replace(/&lt;/g, "<");
		str = str.replace(/&gt;/g, ">");
		str = str.replace(/\n\r/g, "<br />");
		str = str.replace(/\n/g, "<br />");
		str = str.replace(/\r/g, "<br />");
		return str;
	};
	//验证email
	String.prototype.isEmail = function() {
		var s = this.trim().toLowerCase(),
		re = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		return re.test(s);
	};
	//字符串转数字
	String.prototype.toNumber = function(o) {
		var o = o || 0,
			val = this.replace(/[^\d]/g, "");
		if (val == '' || val == 0) {
			val = o;
		}
		return val;
	};
	//验证邮编
    String.prototype.isZipCode = function(){
        var reg = /^[0-9]{6}$/;
        return reg.test(this);
    };
    //判断该字符串与传递的字符串是否相等
	String.prototype.isEqual = function(item) {
		return this == (item || "");
	};
	//验证字符串是否为空
	String.prototype.isEmpty = function() {
		var s = this.trim();
		return s.length == 0;
	};
	//验证字符串是否为纯数字
	String.prototype.isNumber = function() {
		return !isNaN(this);
	};
	//验证是否在数组内
	Array.prototype.contains = function(item){ 
		return RegExp(item).test(this);
	};
	//验证是否在数组内,并返回第N项包含(否则返回null)
	Array.prototype.containsLen = function(item){
		var str = null;
		for(var i = 0; i < this.length; i++){
			if(RegExp(item).test(this[i])){
				str = i;
				return str;
			}
		}
		return str;
	};
	//验证字符串是否是数组内的其中一项
	Array.prototype.equal = function(item){
		var str = false;
		for (var i = 0; i < this.length; i++) {
			if (this[i] == item) {
				return str = true;
			}
		}
		return str;
	};
	//获取查询字符串在数组中的位置
	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
			if (this[i] == val){
                return i;
            }
		}
		return -1;
	};
	//移除数组中的字符窜
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index,1);
		}
                return this;
	};
	//把字符串转换成json格式
	String.prototype.toJSON = function(){
        return new Function("return " + this.toText())();
	};
	//截取字符/包含中英文截取	
	String.prototype.subString = function(len,add){
		var str = this,add = add || "";
		if(!str || !len) { return ''; }
		var a = 0,//预期计数：中文2字节，英文1字节
			i = 0,//循环计数
			temp = '';//临时字串
		for (i=0;i<str.length;i++){
			if (str.charCodeAt(i)>255){
				a += 2;//按照预期计数增加2
			}else{
				a++;
			}
			//如果增加计数后长度大于限定长度，就直接返回临时字符串
			if(a > len) {
				return temp + add;
			}
			//将当前内容加到临时字符串
			temp += str.charAt(i);
		}
		//如果全部是单字节字符，就直接返回源字符串
		return str;
	};
	//以1开头的纯数字
	String.prototype.exNum = function(){
		var s = this,temp = [];
		if(s == ""){
			return 1;
		}
		s = s.replace(/[^\d]/g,"");
		for(var i = 0; i < s.length; i++){
			temp[0] = s.substring(0,1);
			temp[1] = s.substring(1,s.length);
			if(temp[0] != 0){
				return s;
			}else{
				s = temp[1];
			}
		}
		return s || 1;
	};
	//金钱格式1,四舍五入保留2位小数点
	String.prototype.formatMoney = function(s){//s = 分隔符,默认为空
		var num = this.toString().replace(/[^\d.]/g,"").split("."),
                    s = s || "";
		num.length = 2;
		num = num.join(".");
		if (isNaN(num)){
			num = "0";
		}
		var sign = (num == (num = Math.abs(num)));
		num = Math.floor(num * 100 + 0.50000000001);
		var cents = num % 100;
		num = Math.floor(num / 100).toString();
		if (cents < 10){
			cents = "0" + cents;
		}
		for(var i = 0,j = Math.floor((num.length - (1 + i)) / 3); i < j; i++){
			num = num.substring(0, num.length - (4 * i + 3)) + s + num.substring(num.length - (4 * i + 3));
		}
		return (((sign) ? '' : '-') + num + '.' + cents);
	};
	//金钱格式2,小数点后保持原来不变
	String.prototype.toMoney = function(s){//s = 分隔符,默认为空
		var str = this.toString().replace(/[^\d.]/g,"").split(".");
                    str.length = 2,
                    s = s || "",
                    num = ((str[0] || 0)*1000/1000).toString();
		str[1] = str[1] || 0;
                str[1] = Number("0." + str[1]).toString().substring(2,str[1].length+2);
                var nums = this.toString().substr(0,1) == '-' ? true : false;
		if(str[1].length < 2){
                    for(var i = str[1].length;i < 2; i++){
			str[1] += "0";
                    }
		}
		for(var i = 0,j = Math.floor((num.length - (1 + i)) / 3); i < j; i++){
			num = num.substring(0, num.length - (4 * i + 3)) + s + num.substring(num.length - (4 * i + 3));
		}
		str[0] = num;
		return (nums ? "-" : "") + str.join(".");
	};
	Number.prototype.toMoney = String.prototype.toMoney;
    Number.prototype.formatMoney = String.prototype.formatMoney;

    //时间格式化
	Date.prototype.Format = function(format) {
		var format = format || "yyyy-MM-dd hh:mm:ss",
			o = {
				"M+": this.getMonth() + 1,
				"d+": this.getDate(),
				"h+": this.getHours(),
				"m+": this.getMinutes(),
				"s+": this.getSeconds(),
				"q+": Math.floor((this.getMonth() + 3) / 3),
				"S": this.getMilliseconds()
			};
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
		}
		for (var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length))
			}
		}
		return format;
	};
	Date.prototype.add = function(milliseconds){
		var m = this.getTime() + milliseconds;
		return new Date(m);
	};
	Date.prototype.addSeconds = function(second){
		return this.add(second * 1000);
	};
	Date.prototype.addHours = function(hour){
		return this.addMinutes(hour*60);
	};
	Date.prototype.addMinutes = function(minute){
		return this.addSeconds(minute*60);
	};
    //根据传入数字返回前几天时间
    Date.prototype.addDate = function(format){
        var format = parseInt(format,10) || 0,
            time = 0;
        time = this.getTime() + (format * 60 * 60 * 24 * 1000);
        return new Date(time);
    };
	//时间差
	String.prototype.dateDiff = function(date,interval){//时间格式字符串差
		var objInterval = {'D':1000 * 60 * 60 * 24,'H':1000 * 60 * 60,'M':1000 * 60,'S':1000,'T':1},
			Time = (interval|| "D").toUpperCase(),
			start = new Date(this.replace(/-/g,"/")),
			end = new Date(date.replace(/-/g,"/"));
		try{
		  return Math.round((end.getTime() - start.getTime()) / parseInt(eval("objInterval."+Time),10));
		}catch (e){
		  return e.message;
		}
	};
	//手机格式验校
    String.prototype.isTelephone = function() {
        return /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/.test(this.trim());
	};
	//电话格式验校
	String.prototype.isTel = function(){
         return (/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,6})?$/.test(this.trim()));
    };
    String.prototype.decode = function(args) {
	    var result = this;
	    if (arguments.length > 0) {
	        if (arguments.length == 1) {
	            result = args;
	        } else {
	            for (var i = 0; i < arguments.length; i = i + 2) {
	                if (arguments[i] != undefined) {
	                    if (arguments[i + 1] != undefined) {
	                        if (result == arguments[i]) {
	                            result = arguments[i + 1];
	                            break;
	                        }
	                    } else {
	                        result = arguments[i];
	                    }
	                }
	            }
	        }
	    }
	    return result;
	};
	var inputFocus = true,
        text_t = null;
	if(typeof ie6png === "undefined"){
        ie6png = true;
        $.getScript("/js/png.js",function(){
            DD_belatedPNG.fix('.icon, .png');
        });
    }
    $("input[type=text]").each(function(index, element) {
        if ($(this).attr("readonly") == "readonly" || $(this).attr("readonly") == true) {
            $(this).addClass("readonly");
        } else if (inputFocus) {
            $(this).focus();
            inputFocus = false;
        }
    }).live({
        focusin: function() {
            var $this = $(this);
            clearTimeout(text_t);
            text_t = setTimeout(function() {
                $this.select();
            }, 150);
        }
    });
})();

ajax = function(o,data,callBack) {
	new ajax.ajax(o,data,callBack);
};
ajax.ajax = function(o,data,callBack) {
	this.url = o.url || o;
	this.type = o.type || "POST";
	this.data = o.data || (data || "");
	this.contentType = o.contentType || "application/x-www-form-urlencoded";
	this.callBack = o.callBack || callBack;
	typeof this.callBack == "undefined" ? this.callBack = ajax.ajaxback: "";
	var $this = this;
	$.ajax({
		url: $this.url,
		type: $this.type,
		data: $this.data,
		contentType: $this.contentType,
		dataType: "json",
		cache: false,
		success: function(data) {
            setTimeout(function(){
                $this.callBack.call(this,data);
            },200);			
		},
		error: function(xml, status) {
			//加载错误
			if (status == "error") {
				try {
					var json = eval("(" + xml.responseText + ")");
					alert(json.Message + "\n" + json.StackTrace);
				} catch(e) {

					}
			} else {
				alert(status + "\n格式错误");
			}
		},
		complete: function() {
        }
	});
};
ajax.ajaxback = function(o) {};
//如 var a = new tableDrag(id);
//在拖拽结束后可直接调用a.id获得物资的id
function tableDrag(id,tree,name,hasCheckbox){
    var _self = this;
    //拖动对象	
    var o = $(id);
    this.relLeft = 0;//记录横坐标
    this.relTop = 0;//记录纵坐标
    this.id = 0;
    this.name = "";
    o.live({
        mousedown:function(e){
            document.onselectstart = function(){return false;};
            e = _self.fixE(e);
            _self.relLeft = e.clientX + 13;
            _self.relTop = e.clientY + 13 + document.documentElement.scrollTop;
            var $this = $(this);
            if(hasCheckbox){
                if(!!$(this).find("input[type=checkbox]").attr("checked")){
                    html = "<div class=\"dragTr\" id=\"dragTr\" style=\"position:absolute; top:"+ _self.relTop +"px; left:"+ _self.relLeft +"px;z-index:999999;\">您可将 <b>" + name + "</b> 拖拽到"+(typeof tree=="undefined"?'左侧分类树':tree)+"</div>";
                }else{
                    html = "<div class=\"dragTr\" id=\"dragTr\" style=\"position:absolute; top:"+ _self.relTop +"px; left:"+ _self.relLeft +"px;z-index:999999;\">您可将 <b>" + $this.attr("data-name") + "</b> 拖拽到"+(typeof tree=="undefined"?'左侧分类树':tree)+"</div>";
                }
            }else{
                html = "<div class=\"dragTr\" id=\"dragTr\" style=\"position:absolute; top:"+ _self.relTop +"px; left:"+ _self.relLeft +"px;z-index:999999;\">您可将 <b>" + $this.attr("data-name") + "</b> 拖拽到"+(typeof tree=="undefined"?'左侧分类树':tree)+"</div>";
            }
            _self.id = $this.attr("data-id");
            _self.name = $this.attr("data-name");
            _self.obj = $this;
            $("body").css({"cursor":"move","-moz-user-select":"none"}).append(html);
            document.onmousemove = function(e){
                _self.drag(e);
            };
            document.onmouseup = function(){
                $(".dragTr").remove();
                _self.end();
            };
        }
    });
    this.drag = function(e){//拖动
            e = this.fixE(e);
            var l = e.clientX + 13;
            var t = e.clientY + 13 + document.documentElement.scrollTop;
            if (t < 0){
                    t = 0;//防止头部消失
            }
            $(".dragTr").css({
                    "left":l +"px",
                    "top":t +"px"
            });
    };
    this.end = function(){//结束拖动
            document.onmousemove = null;
            document.onmouseup = null;
            document.onselectstart = function(){return;};
            $("body").css({"cursor":"auto","-moz-user-select":"auto"});
            _self.id = 0;
            _self.name = "";
    };
    this.fixE = function(e){	//修复事件
            if (typeof e == "undefined"){ e = window.event;}
            return e;
    };
    this.fixU = function(u){	//处理px单位
            return parseInt(u.split("p")[0]);
    };
}
/********************************************************************************************/
///OpenWindow
///弹出层
///OpenWindow
///@int flag 0=默认,1=导航下最大化,2=全屏
/***********************************************************/
var maxWindowHeight = 0,windowIndex = 0,Windows = [null];
function showWindow(title,html,width,height,isUrl,fun,scrolling,flag){//弹层接口
	var title = title || "爱康医院平台",
            html = html || "",
            width = parseInt(width || 300) + "px",
            height = parseInt(height || 100) + "px",
            m_left = ($(document).width() - parseInt(width))/2 + "px",
            m_top = ($(window).scrollTop() + (document.documentElement.clientHeight - parseInt(height))/2) + "px",
            isUrl = isUrl || false,
            flag = flag || 0,
            scrolling = scrolling ? "yes" : "no";
        windowIndex++;
        if(isUrl){
            html = '<iframe width="100%" style="margin:-10px 0;" name="minWindow" id="minWindow" height="' + height + '" src="' + html + '" frameborder="0" scrolling="'+scrolling+'"></iframe>';
        }
        Windows[windowIndex] = new OpenWindow(title ,html,{
            "width":width,
            "height":height,
            "top":m_top,
            "left":m_left
        },
        (fun || null),flag);
        resetText();
}
function hideWindow(){
    Windows[windowIndex].close();
}
var OpenWindow = function(title/*标题*/,content/*显示内容*/,style/*窗口样式*/,fun/*窗口关闭回调函数*/,flag){
	this.title = title || "弹出操作";	//标题
	this.name = parseInt(Math.random()*100000);	//窗口名称
	this.defaults = {
            left:"300px" ,top:"100px" ,width:"400px" ,height:"400px" 
	};
    this.flag = parseInt((flag || 0),10);
	this.style = $.extend(this.defaults,(style || {}));	//窗口样式
	this.content = content;	//显示内容
	this.type = typeof type == "undefined"?"common" : type;	//类型
	this.height = $(document).height() -22; //窗口高度
	this.width = $(document).width();	//窗口宽度
        this.fun = fun || null;
	this.init = function(){	//初始化窗口
            //加载窗口
            var style = $(".owBj").length > 0 ? "none" : "block",
                html = "<div class=\"owBj\" style=\"display:" + style + ";width:" + this.width + "px;height:" + this.height + "px; top:0; left:0;\"></div><div class=\"owBox\" id=\""+ this.name +"\" style=\"display:none;\"><div class=\"owTitle\"><span class=\"owtName\">"+ this.title +"</span><a href=\"#\" class=\"owClose\"></a></div><div class=\"owContent\">" + this.content + "</div></div>",
                box = "<div class=\"OpenWindow\" id=\"box"+ this.name +"\">" + html + "</div>";
            $("body").append(box);
            OpenWindow.ArrayW.push(document.getElementById(this.name));	//存储窗口到数组
            //设置部件命令
            this.setCss();	//设置窗口属性
            this.startDrag();	//设置拖动
            this.setTop();	//设置窗口优先级
            this.setCommond();//设置关闭
	};
	this.init();
    return this;
};
//存储窗口到数组
OpenWindow.ArrayW = new Array();
//拖动类
OpenWindow.Drag = function(o ,oRoot){
	var _self = this;
	//拖动对象
	this.obj = (typeof oRoot != "undefined") ?oRoot : o;
	this.relLeft = 0;	//记录横坐标
	this.relTop = 0;	//记录纵坐标
    _self._move = false;
    _self.moveId = 'moveWindow';
	o.onselectstart = function(){
		return false;
	};
	o.onmousedown = function(e){//鼠标按下
			var _offset = $(oRoot).offset(),
				_move = $('<div class="'+_self.moveId+'" style="border:1px dotted #ddd; cursor: move; position: absolute; background:#fff; z-index: 999998;"></div><div id="'+_self.moveId+'" class="'+_self.moveId+'" style="border:1px dotted #ddd; cursor: move; position: absolute; background:#000; z-index: 999999;"></div>');
			$('body').append(_move);
			$('.'+_self.moveId).css({
				'left':_offset.left,
				'top':_offset.top,
				'width':$(oRoot).outerWidth(),
				'height':$(oRoot).outerHeight(),
				'opacity':0.5
			});
            e = _self.fixE(e);
            _self.relLeft = e.clientX - _self.fixU(_self.obj.style.left);
            _self.relTop = e.clientY - _self.fixU(_self.obj.style.top);
            _self._move = true;
            document.onmousemove = function(e){
                _self.drag(e);
            };
            document.onmouseup = function(){
                _self.end();
            };
	};
	this.drag = function(e){//拖动
            e = this.fixE(e);
            var l = e.clientX - this.relLeft;
            var t = e.clientY - this.relTop;
            if (t < 0)
            {
                t = 0;//防止头部消失
            }
            $('#'+_self.moveId).css({
				'left':l +"px",
				'top':t +"px"
			});
	};
	this.end = function(){//结束拖动
            _self._move = false;
            document.onmousemove = null;
            document.onmouseup = null;
            var _offset = $('#'+_self.moveId).offset();
            $(_self.obj).css({
            	'left':_offset.left +"px",
            	'top':_offset.top +"px"
            })
            $('.'+_self.moveId).remove();
            return false;
	};
	this.fixE = function(e){//修复事件
            if (typeof e == "undefined") e = window.event;
            return e;
	};
	this.fixU = function(u){//处理px单位
            return parseInt(u.split("p")[0]);
	};
};
//设置窗口属性
OpenWindow.prototype.setCss = function(){
    //设定样式
    var obj = document.getElementById(this.name);
//    if(typeof this.style != "undefined")
//    {
        switch(this.flag){
            case 0:
                if(typeof this.style.top != "undefined") obj.style.top = parseInt(this.style.top,10) < 0 ? "0px" : this.style.top; //设置top
//                if(typeof this.style.height != "undefined") obj.style.height = this.style.height; //设置高度
                if(typeof this.style.width != "undefined") obj.style.width = this.style.width;	//设置宽度
                if(typeof this.style.left != "undefined") obj.style.left = this.style.left; //设置left
                break;
            case 1:
                obj.style.top = '91px'; //设置top
                obj.style.height = ($(window).height() - 96) + 'px'; //设置高度
                obj.style.width = '100%';	//设置宽度
                obj.style.left = '0px'; //设置left
                $("#" + this.name).find("#minWindow").height((parseInt(obj.style.height,10) - 31) + "px");
                break;
            case 2:
                obj.style.top = 0; //设置top
                obj.style.height = ($(window).height() - 6) + 'px'; //设置高度
                obj.style.width = '100%';	//设置宽度
                obj.style.left = '0px'; //设置left
                $("#" + this.name).find("#minWindow").height((parseInt(obj.style.height,10) - 31) + "px");
                break;
            default:
                break;
//        }
        
    }
    //存储宽高度
    this.height = obj.style.height;
    this.width = obj.style.width;
    $("#" + this.name).fadeIn(500);
};
//窗口拖动
OpenWindow.prototype.startDrag = function(){
    var obj = document.getElementById(this.name);
    new OpenWindow.Drag(obj.childNodes[0].childNodes[0] ,obj);
};
//设定窗口优先级
OpenWindow.prototype.setTop = function(){
    for(var i=0;i<OpenWindow.ArrayW.length;i++) {
            OpenWindow.ArrayW[i].style.zIndex = 1;
    }
    this.style.zIndex = 100;
    document.getElementById(this.name).onclick = 
    document.getElementById(this.name).onmousedown = 
    function(){
        for(var i=0;i<OpenWindow.ArrayW.length;i++) {
                OpenWindow.ArrayW[i].style.zIndex = 1;
        }
        this.style.zIndex = 100;
    };
};
//设置关闭
OpenWindow.prototype.close = function(windowName){
    var windowName = windowName || this.name;
    $("#box" + windowName).remove();
    top.windowIndex = top.windowIndex - 1 < 0 ? 0 : top.windowIndex - 1;
    if(typeof(this.fun) == "function"){
        this.fun.call(this,{});
    }
};
OpenWindow.prototype.setCommond = function(){
	var _self = this,
            obj = document.getElementById(this.name);
	obj.childNodes[0].getElementsByTagName("a")[0].onclick = function(){
            _self.close();
            return false;
	};
};
//获取值
OpenWindow.prototype.getValue = function(){
	return this.content;
};
//设置值
OpenWindow.prototype.setValue = function(Value){
	this.content = Vlaue;
	this.setContent();
};

String.prototype.format = function(args) {
   var result = this;
   if (arguments.length > 0) {    
       if (arguments.length == 1 && typeof (args) == "object") {
           for (var key in args) {
               if(args[key]!=undefined){
                   var reg = new RegExp("({" + key + "})", "g");
                   result = result.replace(reg, args[key]);
               }
           }
       } else {
           for (var i = 0; i < arguments.length; i++) {
               if (arguments[i] != undefined) {
                   var regStr = "", num = i.toString();
                   for (j = 0; j < num.length; j++) {
                       regStr += "[" + num.substr(j,1) + "]{1}";
                   }
                   var reg = new RegExp("{" + regStr + "}", "g");
                   result = result.replace(reg, arguments[i]);
               }
           }
       }
   }
   return result;
};

/*
分页
*   count   =   总数
*   page    =   当前页码
*   pagesize    =   每页条数
*   splitsize   =   分页前后显示
*   使用示例:
*   var newpage = new createPage(100,1,15,3);
*   输出:
*   newpage.minHtml;//第n页/共n页
*   newpage.pageHtml;//共技n条 当前第n/n页 每页n条 页码
*   重置分页
*   newpage.resetPage(页码);
*   输出:
*   同上
*/
var createPage = function(count,page,pagesize,splitsize){	
    this.count = parseInt((count || 0),10);
    this.page = page || 1;
    this.pagesize = pagesize || 15;
    this.splitsize = splitsize || 3;
    this.minTemplet = "第{0}页/共{1}页";		
    this.pageTemplet = "共计{0}条 当前第{1}/{2}页 每页{3}条 {4}";
    this.minHtml = this.minTemplet;
    this.pageHtml = this.pageTemplet.format(this.count,"{0}","{1}",this.pagesize,"{2}");
    this.init();
};
createPage.prototype = {
    Size:function(){//分页大小
            this.pagelen = parseInt(this.count % this.pagesize >0 ? this.count/this.pagesize+1: this.count/this.pagesize);
            this.validatePage();
            this.initPage();
    },
    initPage:function(){//重置分页模板
            this.minHtml = this.minTemplet.format(this.page,this.pagelen);
            this.pageHtml = this.pageHtml.format(this.page,this.pagelen,"{0}");
    },
    concat:function(){//装载分页链接
            var s = this.page - this.splitsize < 1 ? 1 : this.page - this.splitsize,
                    e = this.page + this.splitsize > this.pagelen ? this.pagelen : this.page + this.splitsize,
                    html = "";
            for(var i = s; i < e + 1; i++){
                    var et = (i == this.page ? " class=\"asele\"" : "");
                    html += '<a href="javascript:pageGo(' + i + ');"' + et + '>' + i + '</a>';
            }
            this.pageHtml = this.pageHtml.format(html);
    },
    validatePage:function(){//验证有效分页
            if(this.page > this.pagelen){
                    this.page = this.pagelen;
            }
    },
    resetPage:function(page){//切换分页
            this.pageHtml = this.pageTemplet.format(this.count,"{0}","{1}",this.pagesize,"{2}");
            this.page = page || this.page;
            this.initPage();
            return this.concat();
    },
    init:function(){//装载分页
            this.Size();
            return this.concat();
    }
};

//tag选项卡
function Tag(data, o) {
    //tag绑定
    var T = null;
    data.index = data.index == undefined ? 0 : data.index;
    $(data.movetag, o).eq(data.index).addClass(data.addtclass).siblings().removeClass(data.addtclass);
    Tag.TagEnforce(data, o, data.index);
    $(o).find(data.movetag).bind({
        click: function(e) {
            //点击tag
            Tag.TagEnforce(data, this, $(this).index());
            data.jump ? "" : Tag.stopDefault(e);
        },
        mouseover: function() {
            //鼠标移上去tag
            var index = $(this).index(),
                $this = this;
            T = setTimeout(function() {
                    Tag.TagEnforce(data, $this, index);
                },
                data.time);
        },
        mouseout: function() {
            //鼠标移开tag
            clearTimeout(T);
        }
    }).wrapInner(document.createElement("b"));
}
Tag.stopDefault = function(e) {
    if (e && e.preventDefault) {
        e.preventDefault();
    } else {
        window.event.returnValue = false;
    }
};
Tag.TagEnforce = function(data, obj, n) {
    //tag处理
    $(obj).addClass(data.addtclass).siblings().removeClass(data.addtclass);
    $(data.showtag).eq(n).addClass(data.addsclass).siblings().removeClass(data.addsclass);
    data.custom ? data.custom.call(obj) : "";
    //判断是否有自定义事件并执行
};

//输入框提示
function fInput(o, opts) {
    var $this = $(o),
        padding = parseInt(getCss(o, "paddingLeft")) + 7,
        width = $this.outerWidth() - padding,
        height = $this.outerHeight(),
        top = $this.position().top,
        left = $this.position().left + parseInt(getCss(o, "marginLeft")),
        vid = $this.attr("id") || "";
    if (typeof fInput._init == "undefined") {
        fInput.num = 0;
        fInput._init = true;
    } else {
        fInput.num++;
    }
    if (vid == "") {
        vid = "input" + fInput.num;
        $this.attr("id", vid);
    }
    $this.attr("data-input", "lInput" + fInput.num).after("<label id=\"lInput" + fInput.num + "\" class=\"lInput\" style=\"width:" + width + "px;height:" + height + "px;color:" + opts.color + ";top:" + top + "px; left:" + left + "px; padding-left:" + padding + "px; line-height:" + height + "px;position:absolute;\" for=\"" + vid + "\">" + opts.text + "</label>");
    if ($this.val() != "") {
        $("#lInput" + fInput.num).hide().text("");
    }
    $this.bind({
        keyup: function() {
            var length = o.value.length,
                id = "#" + $this.attr("data-input");
            if (length > 0) {
                $(id).hide().text("");
            } else {
                $(id).show().text(opts.text);
            }
        },
        click: function() {
            var id = "#" + $this.attr("data-input"),
                length = o.value.length;
            if (length <= 0) $(id).hide().text("");
        },
        blur: function() {
            var id = "#" + $this.attr("data-input"),
                length = o.value.length;
            if (length <= 0) $(id).show().text(opts.text);
        }
    });
};

function checkBox(e, opt) {
    var $this = e,
        e = $(e),
        next = e.next("label"),
        text = opt.text === false ? e.val() : opt.text,
        disabled = e.is(":disabled");
    e.hide();
    if (next.attr("data-d")) {
        next.removeClass(opt.skin + "-" + opt.check);
        next.unbind("click mouseover mouseout");
    } else {
        if (next.length <= 0) {
            e.after("<label class=\"" + opt.skin + "\" data-d=\"true\">" + text + "</label>");
        } else {
            next.addClass(opt.skin).attr({
                tabindex: 0,
                unselectable: "on",
                "data-d": "true"
            });
        }
    }
    //重置label
    next = e.next("label");
    //初始化
    if (disabled == false) {
        if (e.is(":checked")) {
            next.addClass(opt.skin + "-" + opt.check);
        }
    } else {
        next.addClass(opt.skin + "-" + opt.disabled);
    }
    //移除只读复选框
    if (opt.readonly && opt.remove) {
        e.remove();
    }
    //绑定事件
    next.bind({
        click: function(event) {
            if (opt.readonly) {
                return false;
            }
            if (e.is(":disabled")) {
                alert(opt.alert);
                return false;
            }
            if (!next.prev().is(':checked')) {
                next.addClass(opt.skin + "-" + opt.check);
                next.prev()[0].checked = true;
            } else {
                next.removeClass(opt.skin + "-" + opt.check);
                next.prev()[0].checked = false;
            }
            if (opt.click == false) {

            } else {
                opt.click.call(this, $this);
            }
        },
        mouseover: function() {
            if (!e.is(":disabled") && !next.prev().is(':checked')) {
                next.addClass(opt.skin + "-" + opt.hover);
            }
        },
        mouseout: function() {
            if (!e.is(":disabled")) {
                next.removeClass(opt.skin + "-" + opt.hover);
            }
        }
    });
};

function radio(e, opt) {
    var $this = e,
        e = $(e),
        next = e.next("label"),
        text = opt.text === false ? e.val() : opt.text,
        disabled = e.is(":disabled");
    e.hide();
    if (next.attr("data-d")) {
        next.removeClass(opt.skin + "-" + opt.check);
        next.unbind("click mouseover mouseout");
    } else {
        if (next.length <= 0) {
            e.after("<label data-d=\"true\" class=\"" + opt.skin + "\">" + text + "</label>");
        } else {
            next.attr("data-d", "true").addClass(opt.skin);
        }
    }
    //重置label
    next = e.next("label");
    //初始化
    if (disabled == false) {
        if (e.is(":checked")) {
            next.addClass(opt.skin + "-" + opt.check);
        }
    } else {
        next.addClass(opt.skin + "-" + opt.disabled);
    }
    //移除只读复选框
    //	if(opt.readonly){
    //		e.remove();
    //	}
    //绑定事件
    next.bind({
        click: function(event) {
            if (opt.readonly) {
                return false;
            }
            if (e.is(":disabled")) {
                //alert(opt.alert);
                return false;
            }
            next.addClass(opt.skin + "-" + opt.check).siblings().removeClass(opt.skin + "-" + opt.check);
            if (!next.prev().is(':checked')) {
                next.prev()[0].checked = true;
            }
            if (opt.click === false) {

            } else {
                opt.click.call(this, $this);
            }
        },
        mouseover: function() {
            if (!e.is(":disabled") && !next.prev().is(':checked')) {
                next.addClass(opt.skin + "-" + opt.hover);
            }
        },
        mouseout: function() {
            if (!e.is(":disabled")) {
                next.removeClass(opt.skin + "-" + opt.hover);
            }
        }
    });
};

//获取css属性
function getCss(o, key) {
    return o.currentStyle ? o.currentStyle[key] : document.defaultView.getComputedStyle(o, false)[key];
};

function CharMode(iN){
	if (iN>=48 && iN <=57) //数字  
		return 1;  
	if (iN>=65 && iN <=90) //大写字母  
		return 2;  
	if (iN>=97 && iN <=122) //小写
		return 4;  
	else  
		return 8; //特殊字符  
}

function bitTotal(num){  
	modes=0;  
	for (i=0;i<4;i++){
		if (num & 1) modes++;  
		num>>>=1;  
	}  
	return modes;  
}

function checkStrong(sPW){
	if (sPW.length<=4)  
	return 1;
	Modes=0;
	for (i=0;i<sPW.length;i++){
		Modes|=CharMode(sPW.charCodeAt(i));  
	}
	return bitTotal(Modes); 
}

function strength(val){
	var sClass = "s0";
	if (!val.isEmpty()){
		var S_level=checkStrong(val);
		switch(S_level){
			case 1:
				sClass="s1";
			break;
			case 2:
				sClass="s2";
			break;
			case 3:
				sClass="s3";
			break;
			default:
				sClass="s1";
		}
	}
	$(".strength").find("span").removeClass().addClass(sClass);
	return;
}

function resetText(o) { //如果浏览器不支持css3属性则对传入的class或id进行改装
    var o = o || ".text,.button,.submit,.textarea";
    $(o).each(function(i, item) {
        var $this = $(this),
            attr = $this.attr("icon") ? $this.attr("icon") : "",
            Class = attr != "" ? " " + attr + "Lable" : "",
            type = $this.attr("type") || "",
            clone = $this.attr("copy") || false,
            name = parseInt(Math.random() * 100000);
        Class += type == "button" || type == "submit" ? " labelButton" : "";
        if ($this.hasClass("textarea")) {
            Class = " textareaRadius";
        }
        if (typeof $this.data("cache") == "undefined") {
            $this.data("cache", "true");
            if (clone) {
                var $obj = $("input[icon=" + clone + "]"),
                    $search = $("<input type=\"button\" class=\"inSearch\" value=\"\" id=\"" + name + "\" />");
                $obj.hide();
                $this.wrap($("<label type=\"" + (attr || "label") + "\" class=\"borderRadius" + Class + "\"></label>")).after($search).attr("autocomplete", "off");
                $search.click(function() {
                    $obj.click();
                });
                new inputEnter({
                    input: this,
                    func: function() {
                        $obj.click();
                    }
                });
            } else {
                $this.wrap(function() {
                    //return '<label class="borderRadius' + Class + '"' + icon +' />';
                    return "<label type=\"" + (attr || "label") + "\" class=\"borderRadius" + Class + "\" />";
                }).attr("autocomplete", "off");
                if ($this.attr("arrow") == "true") $this.before("<span class=\"inputimg\"></span>");
            }
        }
    });
    inputHover();
}

function inputHover() {
    $("input,.borderRadius").live("mouseenter mouseleave", function(e) {
        var Class = ($(this).attr("icon") || $(this).attr("type")) + "Hover",
            obj = $(this).find('input,textarea');
        //        alert(this.disable ? 1 : 0)
        if (e.type == "mouseenter" && !$(obj).is(':disabled')) {
            $(this).addClass(Class);
        } else {
            $(this).removeClass(Class);
        }
    });
}