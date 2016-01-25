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
				top = y || $this.height() <= 35 ? 37 : $this.height(),
				left = x || $this.scrollLeft(),
				width = $this.innerWidth() <= 200 ? 200 : $this.innerWidth(),
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
		stCert : function(selectData){
            return this.find("li").each(function(){
                 $(this).hover(
                    function(){
                        $(this).addClass("active");
                        $(this).find(".jd-other").show();
                    },
                    function(){
                        $(this).removeClass("active");
                        $(this).find(".jd-other").hide();
                    }
                );
                $(this).find(".jd-detail").click(function(){
                    var subVal = $(this).parent().parent().attr("id"), val = $.trim($("h5",this).text());

                    if(!selectData[subVal])
                        selectData[subVal] = [];
                    if($(this).parent().hasClass("current")){
                        $(this).parent().removeClass("current");
                        selectData[subVal].splice(selectData[subVal].indexOf(val),1);
                    }else{
                        $(this).parent().addClass("current");
                        selectData[subVal].push(val);
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
		uiConfirm: function(options) {
            //确认提示
            return this.each(function(e) {
                var name = uiConfirm.toJson(options);
                uiConfirm(name, e);
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
        uiSelect: function(options) {
            //select
            return this.each(function(e) {
                typeof Select.Num == "undefined" ? Select.Num = 0 : Select.Num++;
                var name = $.fn.Options.call(this, options),
                    index = Select.Num,
                    $this = $(this);
                name = $.extend({}, $.fn.uiSelect.defaults, name);

                if ($this.data("ListNum") == undefined) {
                    $this.addClass("uiSelect" + index);
                    $this.data("ListNum", index);
                } else {
                    index = $this.data("ListNum");
                    Select.Num--;
                    $this.find(".selectbox").remove();
                }
                //给该tag父层增加指定的class
                name.box = ".uiSelect" + index;
                //更改name的box属性,并赋予该tag父层指定的class
                if (name.change) {
                    Select(name, this, index);
                }
            });
        },
        autocomplete: function(urlOrData, options) {
			var isUrl = typeof urlOrData == "string";
			options = jQuery.extend({}, jQuery.Autocompleter.defaults, {
				url: isUrl ? urlOrData : null,
				data: isUrl ? null : urlOrData,
				delay: isUrl ? jQuery.Autocompleter.defaults.delay : 10,
				max: options && !options.scroll ? 10 : 150
			}, options);

			// if highlight is set to false, replace it with a do-nothing function
			options.highlight = options.highlight ||
			function(value) {
				return value;
			};

			// if the formatMatch option is not specified, then use formatItem for backwards compatibility
			options.formatMatch = options.formatMatch || options.formatItem;

			return this.each(function() {
				new jQuery.Autocompleter(this, options);
			});
		},
		result: function(handler) {
			return this.bind("result", handler);
		},
		search: function(handler) {
			return this.trigger("search", [handler]);
		},
		flushCache: function() {
			return this.trigger("flushCache");
		},
		setOptions: function(options) {
			return this.trigger("setOptions", [options]);
		},
		unautocomplete: function() {
			return this.trigger("unautocomplete");
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
		},
		inputTips: function(url, len, back, width, fun, clickStatus) {
	        var url = url || "",
	            back = back || "",
	            len = len || 2,
	            width = width || 166;
	        if (url == "") {
	            return false;
	        }
	        $(this).unbind();
	        $(this).autocomplete(url, {
	            "max": 30,
	            "minChars": len,
	            "clickStatus": clickStatus,
	            "width": width,
	            "dataType": "json",
	            parse: function(data) {
	                return $.map(data.list, function(item, row) {
	                    return {
	                        data: item,
	                        result: item.text
	                    }
	                });
	            },
	            formatItem: function(item, i, max) {
	                return item.text;
	            }
	        }).result(function(e, data, formatted) {
	            if (back != "") {
	                $(back).val(data.id);
	            }
	            if (fun != null) {
	                fun.call(this, data);
	            }
	        }).focusout(function() {
	            var val = $(this).val().trim();
	            if (back != "" && val == "") {
	                $(back).val("");
	            }
	            if (fun != null && val != "") {
	                fun.call(this, {});
	            }
	        }).keyup(function() {
	            var val = $(this).val().trim();
	            if (back != "" && val == "") {
	                $(back).val("");
	            }
	        }).click(function() {
	            var val = $(this).val();
	            if (val == "") $(this).next("label").hide();
	        }).blur(function() {
	            var val = $(this).val();
	            if (val == "") $(this).next("label").show();
	        });
	    },
        check: function() {
            return this.each(function() {
                this.checked = true;
            });
        },
        uncheck: function() {
            return this.each(function() {
                this.checked = false;
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
    $.fn.uiSelect.defaults = {
        //select默认设置
        width: "169",
        //选择项的宽度
        height: "200",
        //选择项的高度
        type: "click",
        //mouseenter鼠标移入显示,click鼠标点击显示项
        change: true,
        //是否重置样式
        onchange: false,
        ajax: false,
        box: null,
        //为筛选切换时准备的,不需要初始
        input: false,
        text: "" //默认增加文字
    };
    $.fn.alert.defaults = {
        //弹出框默认设置
        nav: "",
        //弹出内容
        flag: false,
        //处理成功标志
        skin: "orange",
        //皮肤
        title: "温馨提示",
        //提示标题
        close: false,
        //自动关闭
        next: function() {},
        //点击确定后执行事件
        opacity: 0.1,
        //遮挡层透明度
        time: 3000
        //超时时间,当自动关闭为true时生效使用
    };
    $.fn.uiConfirm.defaults = {
        title: "确认提示", //标题
        skin: "orange", //皮肤
        content: "", //确认提示内容
        width: 350,
        data: null, //开放自定义接口,为方法之间数据传输
        opacity: 0.1,
        button: 0, //确认回调方法 0/1,0表示取消,1表示确认
        flag:1,
        fontcolor:"#434343",
        fun: false //用户选择后回调方法
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

    //alert重置
    var _alert = window.alert;
    uiAlert.MyAlert = function(json, flag, fun) {
        var fun = fun || null,
            flag = flag || (json != undefined && json.flag || 0);
        top.uiAlert.first(new uiAlert.Tostring(typeof(json) != "object" ? "" + json + "" : json), flag, fun);
    };
    uiAlert.MyAlert.noConflict = function() {
        window.alert = _alert;
    };
    window.alert = window.uiAlert.MyAlert = uiAlert.MyAlert;

    var _confirm = window.confirm;
    uiConfirm.MyConfirm = function(n, fun, o) {
        var o = o || true;
        if (typeof fun == "undefined") {
            top.uiConfirm.type = false;
        } else if (typeof fun == "function") {
            top.uiConfirm.type = true;
            top.uiConfirm.fun = fun;
        }
        o == true ? top.uiConfirm.call(this, uiConfirm.toJson(n, name)) : _confirm(n);
    };
    uiConfirm.MyConfirm.noConflict = function() {
        window.confirm = _confirm;
    };
    window.confirm = window.uiConfirm.MyConfirm = uiConfirm.MyConfirm;

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
        return /^0?(13[0-9]|15[012356789]|18[02356789]|14[57])[0-9]{8}$/.test(this.trim());
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
	/*if(typeof ie6png === "undefined"){
        ie6png = true;
        $.getScript("/js/png.js",function(){
            DD_belatedPNG.fix('.icon, .png');
        });
    }*/
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
            		html = "<div class=\"owBj\" style=\"display:" + style + ";width:" + this.width + "px;height:" + this.height + "px; top:0; left:0;\"></div>\n\
            		<div class=\"owBox\" id=\""+ this.name +"\" style=\"display:none;\">";
        	if(title != "notitle") html +="<div class=\"owTitle\"><span class=\"owtName\">"+ this.title +"</span><a href=\"#\" class=\"owClose\"></a></div>";
        	html +="<div class=\"owContent\">" + this.content + "</div></div>",
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

function Select(data, obj, num) {
    var Selected = null,
        Option = [],
        val = [],
        box = data.box + " ",
        $boxSelect = $(box + "select"),
        boxHeight = new Array(),
        text = "";
    boxHeight[0] = parseInt(data.height, 10);
    if ($boxSelect.length > 0) {
        $boxSelect.hide();
        Selected = $(data.box + " select option:selected").text();
        text = data.input ? "<input type=\"text\" class=\"selecttext\" id=\"selecttext" + num + "\" value=\"" + Selected + "\" />" : "<span class=\"selecttext\">" + data.text + Selected + "</span>";
        $(obj).append("<div class=\"selectbox\"><div class=\"selecttbox\">" + text + "<span class=\"selectimg\"></span></div><div class=\"selectubox\" style=\"width:" + parseInt(data.width, 10) + "px; max-height:" + boxHeight[0] + "px;top:-9999px;left:-9999px;\"><ul class=\"selectul\"></ul></div></div>");
        if (data.input) {
            $("#selecttext" + num).bind({
                keydown: function(e) {
                    var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
                    if (keyCode == 13) {
                        return false;
                    }
                },
                focusout: function() {
                    var value = this.value;
                    if (!val.equal(value)) {
                        val.push(value);
                        $(box + ".selectul").find("li").removeClass("selected");
                        $(box + ".selectul li:eq(0)").after("<li class=\"selected\" value=" + value + ">" + value + "</li>");
                        $(box + "select").append("<option selected=\"selected\" value=\"" + value + "\">" + value + "</option>");
                        Select.SelectLi(data, true);
                    }
                }
            });
        }
        //创建select盒子
        //获取select项
        $(box + "select option").each(function() {
            var $this = $(this),
                Class = $this.attr("selected") ? " class=\"optGroupLi selected\"" : " class=\"optGroupLi\"",
                SelectVal = $this.text() || "",
                icon = $this.attr("icon") ? "<span class=\"selectIcon " + $this.attr("icon") + "\"></span>" : "";
            if (SelectVal != "") {
                //Option.push("<li" + Class + (data.onchange ? " onclick=\"javascript:" + data.onchange + ";\"" : "") + " value=" + $(this).val() + ">" + SelectVal + "</li>");
                val.push(SelectVal);
                Option.push("<li" + Class + " value=" + $this.val() + ">" + icon + SelectVal + "</li>");
            }
        });
        $(box + ".selectul").append(Option.join(""));
        //在指定class内添加select项
        //验证option高度
        // boxHeight[1] = $(box + ".selectubox").height();
        // boxHeight[2] = data.height = boxHeight[1] < boxHeight[0] && boxHeight[1] != 0 ? boxHeight[1] : boxHeight[0];
        // $(box + ".selectubox").height(boxHeight[2]);
        //执行绑定
        Select.SelectOption(data, obj);
        //获取select分组
        $(box + "select optgroup").each(function(i) {
            var $this = $(this),
                SelectVal = $this.attr("label") || "",
                option = $this.find("option").eq(0).index(box + "select option");
            $(box + ".selectul").find("li").eq(i + option).before($("<li class=\"optgroup\">" + SelectVal + "</li>"));
        });
    }
}
//获取分组添加位置
Select.optgroup = function(a, c) {
    for (var a = 0; a < c.length; a++) {
        if (b == c[a]) {
            return a;
            break
        }
    }
};
//绑定下拉
Select.SelectLi = function(data, type) {
    var SelectTime = null,
        $box = $(data.box + " .selecttbox"),
        $option = $(data.box + " .selectubox"),
        $ouclick = $(data.box + " .selectbox"),
        _init = false,
        hoverClass = "selecthover",
        type = type || false,
        Change = data.onchange ? data.onchange : ($(data.box + " select").attr("onChange") || false);
    if (type) {
        $(data.box + " .selectul li").unbind("mouseenter").unbind("mouseleave").unbind("click");
    }
    $(data.box + " .selectul li").bind({
        //选择项动作,以及点击项后触发动作
        mouseenter: function() {
            //鼠标移入
            $(this).addClass("Optionselect");
        },
        mouseleave: function() {
            //鼠标移出
            $(this).removeClass("Optionselect");
        },
        click: function() {
            //鼠标点击
            var $this = $(this),
                Index = $this.index(data.box + " .optGroupLi");
            $(data.box + " option").eq(Index).attr("selected", "selected"); //.siblings().removeAttr("selected");
            //控制select
            if (data.input) {
                $(data.box + " .selecttext").val($this.text());
            } else {
                $(data.box + " .selecttext").text(data.text + $this.text());
            }
            //显示当前选择内容
            $this.addClass("selected").siblings().removeClass("selected");
            //为当前选择添加指定class,并移除同辈元素的指定class
            Selectremove();
            if (typeof Change == "function") {
                Change();
            }
        }
    });

    function Selectremove() {
        //移出选择项盒子
        $box.removeClass(hoverClass);
        $option.css({
            "top": "-9999px",
            "left": "-9999px"
        });
        _init = false;
        //恢复初始默认状态
    };
};
//select绑定
Select.SelectOption = function(data, obj) {
    var SelectTime = null,
        $box = $(data.box + " .selecttbox"),
        $option = $(data.box + " .selectubox"),
        $ouclick = $(data.box + " .selectubox"),
        _init = false,
        hoverClass = "selecthover";
    if (data.type == "mouseover") {
        //根据用户自定义绑定动作
        SelectBindM();
    } else {
        SelectBindC();
    }
    Select.SelectLi(data);

    function SelectBindM() {
        //绑定鼠标移入显示选择项
        $box.bind({
            mouseenter: function() {
                //鼠标移入
                var $this = this;
                $(this).addClass(hoverClass);
                SelectTime = setTimeout(function() {
                    SelectOver($this);
                }, 150);
            },
            mouseleave: function() {
                //鼠标移出
                _init ? "" : $box.removeClass(hoverClass);
                clearTimeout(SelectTime);
            }
        });
    };

    function SelectBindC() {
        //绑定鼠标点击显示选择项
        $box.bind({
            mouseleave: function() {
                //鼠标移出
                _init ? "" : $box.removeClass(hoverClass);
            },
            click: function() {
                //鼠标点击
                _init ? "" : SelectOver(this);
            },
            mouseenter: function() {
                //鼠标移入
                $(this).addClass(hoverClass);
            }
        });
    };

    function SelectOver(o) {
        //移入选择项盒子
        _init = true;
        SelectTop(o);
        $ouclick.outClick({
            click: function() {
                Selectremove();
                _init = false;
            }
        });
    };

    function Selectremove() {
        //移出选择项盒子
        $box.removeClass(hoverClass);
        $option.css({
            "top": "-9999px",
            "left": "-9999px"
        });
        _init = false;
        //恢复初始默认状态
    };
    /**
     * 2013-09-05修正弹出位置问题
     */
    function SelectTop(o) {
        var $obj = $(o),
            $window = $(window),
            DHeight = $window.height() + $window.scrollTop(),
            oTop = $obj.offset().top,
            height = $(o).height(),
            opHeight = $option.height(),
            top = oTop + opHeight + height;
        top = top > DHeight ? -opHeight : height;
        $option.css({
            "top": top,
            "left": 0
        });
        return;
    };
};

function inputTips(id, url, len, back, width, fun, clickStatus) {
    var id = id || "",
        url = url || "",
        back = back || "",
        len = len || 2,
        width = width || 166,
        clickStatus = clickStatus == null ? false : clickStatus;
    if (id == "" || url == "") {
        return false;
    }
    $(id).inputTips(url, len, back, width, fun, clickStatus);
}

//input用户输入提示
jQuery.Autocompleter = function(input, options) {

    var KEY = {
        UP: 38,
        DOWN: 40,
        DEL: 46,
        TAB: 9,
        RETURN: 13,
        ESC: 27,
        COMMA: 188,
        PAGEUP: 33,
        PAGEDOWN: 34,
        BACKSPACE: 8
    };

    // Create $ object for input element
    var jQueryinput = jQuery(input).attr("autocomplete", "off").addClass(options.inputClass);

    var timeout;
    var previousValue = "";
    var cache = jQuery.Autocompleter.Cache(options);
    var hasFocus = 0;
    var lastKeyPressCode;
    var config = {
        mouseDownOnSelect: false
    };
    var select = jQuery.Autocompleter.Select(options, input, selectCurrent, config);

    var blockSubmit;

    // prevent form submit in opera when selecting with return key
    jQuery.browser.opera && jQuery(input.form).bind("submit.autocomplete", function() {
        if (blockSubmit) {
            blockSubmit = false;
            return false;
        }
    });

    // only opera doesn't trigger keydown multiple times while pressed, others don't work with keypress at all
    jQueryinput.bind((jQuery.browser.opera ? "keypress" : "keydown") + ".autocomplete", function(event) {
        // track last key pressed
        lastKeyPressCode = event.keyCode;
        switch (event.keyCode) {

            case KEY.UP:
                event.preventDefault();
                if (select.visible()) {
                    select.prev();
                } else {
                    onChange(0, true);
                }
                break;

            case KEY.DOWN:
                event.preventDefault();
                if (select.visible()) {
                    select.next();
                } else {
                    onChange(0, true);
                }
                break;
            case KEY.PAGEUP:
                event.preventDefault();
                if (select.visible()) {
                    select.pageUp();
                } else {
                    onChange(0, true);
                }
                break;

            case KEY.PAGEDOWN:
                event.preventDefault();
                if (select.visible()) {
                    select.pageDown();
                } else {
                    onChange(0, true);
                }
                break;

                // matches also semicolon
            case options.multiple && jQuery.trim(options.multipleSeparator) == "," && KEY.COMMA:
            case KEY.TAB:
            case KEY.RETURN:
                if (selectCurrent()) {
                    // stop default to prevent a form submit, Opera needs special handling
                    event.preventDefault();
                    blockSubmit = true;
                    return false;
                }
                break;

            case KEY.ESC:
                select.hide();
                break;

            default:
                clearTimeout(timeout);
                timeout = setTimeout(onChange, options.delay);
                break;
        }
    }).focus(function() {
        // track whether the field has focus, we shouldn't process any
        // results if the field no longer has focus
        hasFocus++;
    }).blur(function() {
        hasFocus = 0;
        if (!config.mouseDownOnSelect) {
            hideResults();
        }
    }).bind("search", function() {
        // TODO why not just specifying both arguments?
        var fn = (arguments.length > 1) ? arguments[1] : null;

        function findValueCallback(q, data) {
            var result;
            if (data && data.length) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].result.toLowerCase() == q.toLowerCase()) {
                        result = data[i];
                        break;
                    }
                }
            }
            if (typeof fn == "function") fn(result);
            else jQueryinput.trigger("result", result && [result.data, result.value]);
        }
        /*jQuery.each(trimWords(jQueryinput.val()), function(i, value) {
            request(value, findValueCallback, findValueCallback);
        });*/
    }).bind("flushCache", function() {
        cache.flush();
    }).bind("setOptions", function() {
        jQuery.extend(options, arguments[1]);
        // if we've updated the data, repopulate
        if ("data" in arguments[1]) cache.populate();
    }).bind("unautocomplete", function() {
        select.unbind();
        jQueryinput.unbind();
        jQuery(input.form).unbind(".autocomplete");
    }).bind("input", function() {
        onChange(0, true);
    });

    function selectCurrent() {
        var selected = select.selected();
        if (!selected) return false;

        var v = selected.result;
        previousValue = v;

        if (options.multiple) {
            var words = trimWords(jQueryinput.val());
            if (words.length > 1) {
                v = words.slice(0, words.length - 1).join(options.multipleSeparator) + options.multipleSeparator + v;
            }
            v += options.multipleSeparator;
        }

        jQueryinput.val(v);
        hideResultsNow();
        jQueryinput.trigger("result", [selected.data, selected.value]);
        return true;
    }

    function onChange(crap, skipPrevCheck) {
        if (lastKeyPressCode == KEY.DEL) {
            select.hide();
            return;
        }

        var currentValue = jQueryinput.val();

        if (!skipPrevCheck && currentValue == previousValue) return;

        previousValue = currentValue;

        currentValue = lastWord(currentValue);
        if (currentValue.length >= options.minChars) {
            jQueryinput.addClass(options.loadingClass);
            if (!options.matchCase) currentValue = currentValue.toLowerCase();
            request(currentValue, receiveData, hideResultsNow);
        } else {
            stopLoading();
            select.hide();
        }
    };

    function trimWords(value) {
        if (!value) {
            return [""];
        }
        var words = value.split(options.multipleSeparator);
        var result = [];
        jQuery.each(words, function(i, value) {
            if (jQuery.trim(value)) result[i] = jQuery.trim(value);
        });
        return result;
    }

    function lastWord(value) {
        if (!options.multiple) return value;
        var words = trimWords(value);
        return words[words.length - 1];
    }

    // fills in the input box w/the first match (assumed to be the best match)
    // q: the term entered
    // sValue: the first matching result

    function autoFill(q, sValue) {
        // autofill in the complete box w/the first match as long as the user hasn't entered in more data
        // if the last user key pressed was backspace, don't autofill
        if (options.autoFill && (lastWord(jQueryinput.val()).toLowerCase() == q.toLowerCase()) && lastKeyPressCode != KEY.BACKSPACE) {
            // fill in the value (keep the case the user has typed)
            jQueryinput.val(jQueryinput.val() + sValue.substring(lastWord(previousValue).length));
            // select the portion of the value not typed by the user (so the next character will erase)
            jQuery.Autocompleter.Selection(input, previousValue.length, previousValue.length + sValue.length);
        }
    };

    function hideResults() {
        clearTimeout(timeout);
        timeout = setTimeout(hideResultsNow, 200);
    };

    function hideResultsNow() {
        var wasVisible = select.visible();
        select.hide();
        clearTimeout(timeout);
        stopLoading();
        if (options.mustMatch) {
            // call search and run callback
            jQueryinput.search(

                function(result) {
                    // if no value found, clear the input box
                    if (!result) {
                        if (options.multiple) {
                            var words = trimWords(jQueryinput.val()).slice(0, -1);
                            jQueryinput.val(words.join(options.multipleSeparator) + (words.length ? options.multipleSeparator : ""));
                        } else jQueryinput.val("");
                    }
                });
        }
        if (wasVisible)
        // position cursor at end of input field
            jQuery.Autocompleter.Selection(input, input.value.length, input.value.length);
    };

    function receiveData(q, data) {
        if (data && data.length && hasFocus) {
            stopLoading();
            select.display(data, q);
            autoFill(q, data[0].value);
            select.show();
        } else {
            hideResultsNow();
        }
    };

    function request(term, success, failure) {
        if (!options.matchCase) term = term.toLowerCase();
        var data = cache.load(term);
        // recieve the cached data
        /*if (data && data.length) {
				success(term, data);
				cache.add(term, parsed);
				// if an AJAX url has been supplied, try loading the data now
			} else */
        if ((typeof options.url == "string") && (options.url.length > 0)) {

            var extraParams = {
                timestamp: +new Date()
            };
            jQuery.each(options.extraParams, function(key, param) {
                extraParams[key] = typeof param == "function" ? param() : param;
            });
            jQuery.ajax({
                // try to leverage ajaxQueue plugin to abort previous requests
                mode: "abort",
                // limit abortion to this input
                port: "autocomplete" + input.name,
                dataType: options.dataType,
                type : "POST",
                url: options.url,
                data: jQuery.extend({
                    q: $.trim(term),
                    limit: options.max
                }, extraParams),
                success: function(data) {
                    var parsed = options.parse && options.parse(data) || parse(data);
                    cache.add(term, parsed);
                    success(term, parsed);
                }
            });
        } else {
            // if we have a failure, we need to empty the list -- this prevents the the [TAB] key from selecting the last successful match
            select.emptyList();
            failure(term);
        }
    };

    function parse(data) {
        var parsed = [];
        var rows = eval('(' + data + ')');
        for (var i = 0; i < rows.length; i++) {
            var row = jQuery.trim(rows[i]);
            if (row) {
                row = row.split("|");
                parsed[parsed.length] = {
                    data: row,
                    value: row[0],
                    result: options.formatResult && options.formatResult(row, row[0]) || row[0]
                };
            }
        }
        return parsed;
    };

    function stopLoading() {
        jQueryinput.removeClass(options.loadingClass);
    };

};
jQuery.Autocompleter.defaults = {
    inputClass: "ac_input",
    resultsClass: "ac_results",
    loadingClass: "ac_loading",
    minChars: 1,
    delay: 400,
    matchCase: false,
    matchSubset: true,
    matchContains: false,
    cacheLength: 10,
    max: 100,
    mustMatch: false,
    extraParams: {},
    selectFirst: true,
    formatItem: function(row) {
        return row[0];
    },
    formatMatch: null,
    autoFill: false,
    width: 0,
    multiple: false,
    multipleSeparator: ", ",
    highlight: function(value, term) {
        return value.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + term.replace(/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi, "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<strong>$1</strong>");
    },
    scroll: true,
    scrollHeight: 180
};

jQuery.Autocompleter.Cache = function(options) {

    var data = {};
    var length = 0;

    function matchSubset(s, sub) {
        if (!options.matchCase) s = (s || "").toString().toLowerCase();
        var i = s.indexOf(sub);
        if (i == -1) return false;
        return i == 0 || options.matchContains;
    };

    function add(q, value) {
        if (length > options.cacheLength) {
            flush();
        }
        if (!data[q]) {
            length++;
        }
        data[q] = value;
    }

    function populate() {
        if (!options.data) return false;
        // track the matches
        var stMatchSets = {},
            nullData = 0;

        // no url was specified, we need to adjust the cache length to make sure it fits the local data store
        if (!options.url) options.cacheLength = 1;

        // track all options for minChars = 0
        stMatchSets[""] = [];

        // loop through the array and create a lookup structure
        for (var i = 0, ol = options.data.length; i < ol; i++) {
            var rawValue = options.data[i];
            // if rawValue is a string, make an array otherwise just reference the array
            rawValue = (typeof rawValue == "string") ? [rawValue] : rawValue;

            var value = options.formatMatch(rawValue, i + 1, options.data.length);
            if (value === false) continue;

            var firstChar = value.charAt(0).toLowerCase();
            // if no lookup array for this character exists, look it up now
            if (!stMatchSets[firstChar]) stMatchSets[firstChar] = [];

            // if the match is a string
            var row = {
                value: value,
                data: rawValue,
                result: options.formatResult && options.formatResult(rawValue) || value
            };

            // push the current match into the set list
            stMatchSets[firstChar].push(row);

            // keep track of minChars zero items
            if (nullData++ < options.max) {
                stMatchSets[""].push(row);
            }
        };

        // add the data items to the cache
        jQuery.each(stMatchSets, function(i, value) {
            // increase the cache size
            options.cacheLength++;
            // add to the cache
            add(i, value);
        });
    }

    // populate any existing data
    setTimeout(populate, 25);

    function flush() {
        data = {};
        length = 0;
    }

    return {
        flush: flush,
        add: add,
        populate: populate,
        load: function(q) {
            if (!options.cacheLength || !length) return null;
            /* 
             * if dealing w/local data and matchContains than we must make sure
             * to loop through all the data collections looking for matches
             */
            if (!options.url && options.matchContains) {
                // track all matches
                var csub = [];
                // loop through all the data grids for matches
                for (var k in data) {
                    // don't search through the stMatchSets[""] (minChars: 0) cache
                    // this prevents duplicates
                    if (k.length > 0) {
                        var c = data[k];
                        jQuery.each(c, function(i, x) {
                            // if we've got a match, add it to the array
                            if (matchSubset(x.value, q)) {
                                csub.push(x);
                            }
                        });
                    }
                }
                return csub;
            } else
            // if the exact item exists, use it
            if (data[q]) {
                return data[q];
            } else if (options.matchSubset) {
                for (var i = q.length - 1; i >= options.minChars; i--) {
                    var c = data[q.substr(0, i)];
                    if (c) {
                        var csub = [];
                        jQuery.each(c, function(i, x) {
                            if (matchSubset(x.value, q)) {
                                csub[csub.length] = x;
                            }
                        });
                        return csub;
                    }
                }
            }
            return null;
        }
    };
};

jQuery.Autocompleter.Select = function(options, input, select, config) {
    var CLASSES = {
        ACTIVE: "ac_over"
    };

    var listItems, active = -1,
        data, term = "",
        needsInit = true,
        element, list;

    // Create results

    function init() {
        if (!needsInit) return;
        element = jQuery("<div/>").hide().addClass(options.resultsClass).css("position", "absolute").appendTo(document.body);

        list = jQuery("<ul/>").appendTo(element).mouseover(function(event) {
            if (target(event).nodeName && target(event).nodeName.toUpperCase() == 'LI') {
                active = jQuery("li", list).removeClass(CLASSES.ACTIVE).index(target(event));
                jQuery(target(event)).addClass(CLASSES.ACTIVE);
            }
        }).click(function(event) {
            jQuery(target(event)).addClass(CLASSES.ACTIVE);
            select();
            // TODO provide option to avoid setting focus again after selection? useful for cleanup-on-focus
            input.focus();
            return false;
        }).mousedown(function() {
            config.mouseDownOnSelect = true;
        }).mouseup(function() {
            config.mouseDownOnSelect = false;
        });

        if (options.width > 0) element.css("width", options.width);

        needsInit = false;
    }

    function target(event) {
        var element = event.target;
        while (element && element.tagName != "LI")
            element = element.parentNode;
        // more fun with IE, sometimes event.target is empty, just ignore it then
        if (!element) return [];
        return element;
    }

    function moveSelect(step) {
        listItems.slice(active, active + 1).removeClass(CLASSES.ACTIVE);
        movePosition(step);
        var activeItem = listItems.slice(active, active + 1).addClass(CLASSES.ACTIVE);
        if (options.scroll) {
            var offset = 0;
            listItems.slice(0, active).each(function() {
                offset += this.offsetHeight;
            });
            if ((offset + activeItem[0].offsetHeight - list.scrollTop()) > list[0].clientHeight) {
                list.scrollTop(offset + activeItem[0].offsetHeight - list.innerHeight());
            } else if (offset < list.scrollTop()) {
                list.scrollTop(offset);
            }
        }
    };

    function movePosition(step) {
        active += step;
        if (active < 0) {
            active = listItems.size() - 1;
        } else if (active >= listItems.size()) {
            active = 0;
        }
    }

    function limitNumberOfItems(available) {
        return options.max && options.max < available ? options.max : available;
    }

    function fillList() {
        list.empty();
        var max = limitNumberOfItems(data.length);
        for (var i = 0; i < max; i++) {
            if (!data[i]) continue;
            var formatted = options.formatItem(data[i].data, i + 1, max, data[i].value, term);
            if (formatted === false) continue;
            var li = jQuery("<li/>").html(options.highlight(formatted, term)).addClass(i % 2 == 0 ? "ac_even" : "ac_odd").appendTo(list)[0];
            jQuery.data(li, "ac_data", data[i]);
        }
        listItems = list.find("li");
        if (options.selectFirst) {
            listItems.slice(0, 1).addClass(CLASSES.ACTIVE);
            active = 0;
        }
        // apply bgiframe if available
        if (jQuery.fn.bgiframe) list.bgiframe();
    }

    return {
        display: function(d, q) {
            init();
            data = d;
            term = q;
            fillList();
        },
        next: function() {
            moveSelect(1);
        },
        prev: function() {
            moveSelect(-1);
        },
        pageUp: function() {
            if (active != 0 && active - 8 < 0) {
                moveSelect(-active);
            } else {
                moveSelect(-8);
            }
        },
        pageDown: function() {
            if (active != listItems.size() - 1 && active + 8 > listItems.size()) {
                moveSelect(listItems.size() - 1 - active);
            } else {
                moveSelect(8);
            }
        },
        hide: function() {
            element && element.hide();
            listItems && listItems.removeClass(CLASSES.ACTIVE);
            active = -1;
        },
        visible: function() {
            return element && element.is(":visible");
        },
        current: function() {
            return this.visible() && (listItems.filter("." + CLASSES.ACTIVE)[0] || options.selectFirst && listItems[0]);
        },
        show: function() {
            var offset = jQuery(input).offset();
            element.css({
                width: typeof options.width == "string" || options.width > 0 ? options.width : jQuery(input).width(),
                top: offset.top + input.offsetHeight,
                left: offset.left
            }).show();
            if (options.scroll) {
                list.scrollTop(0);
                list.css({
                    maxHeight: options.scrollHeight,
                    overflow: 'auto'
                });

                if (jQuery.browser.msie && typeof document.body.style.maxHeight === "undefined") {
                    var listHeight = 0;
                    listItems.each(function() {
                        listHeight += this.offsetHeight;
                    });
                    var scrollbarsVisible = listHeight > options.scrollHeight;
                    list.css('height', scrollbarsVisible ? options.scrollHeight : listHeight);
                    if (!scrollbarsVisible) {
                        // IE doesn't recalculate width when scrollbar disappears
                        listItems.width(list.width() - parseInt(listItems.css("padding-left")) - parseInt(listItems.css("padding-right")));
                    }
                }

            }
        },
        selected: function() {
            var selected = listItems && listItems.filter("." + CLASSES.ACTIVE).removeClass(CLASSES.ACTIVE);
            return selected && selected.length && jQuery.data(selected[0], "ac_data");
        },
        emptyList: function() {
            list && list.empty();
        },
        unbind: function() {
            element && element.remove();
        }
    };
};

jQuery.Autocompleter.Selection = function(field, start, end) {
    if (field.createTextRange) {
        var selRange = field.createTextRange();
        selRange.collapse(true);
        selRange.moveStart("character", start);
        selRange.moveEnd("character", end);
        selRange.select();
    } else if (field.setSelectionRange) {
        field.setSelectionRange(start, end);
    } else {
        if (field.selectionStart) {
            field.selectionStart = start;
            field.selectionEnd = end;
        }
    }
    field.focus();
};

function uiConfirm(n) {
    var data = n || $.fn.uiConfirm.defaults;
    uiConfirm.data = data;
    this._init = function() {
        uiAlert.Close();
        var $Uialert_Bbox = $("<div class=\"Uialert_Bbox Uialert_" + data.skin + "\" style=\"height:" + ($(window).height() - 22) + "px;display:none;\"><div class=\"Uialert_bj\" style=\"height:" + ($(window).height() - 22) + "px;\"></div><div class=\"Uialert_box\" style=\"width:" + parseInt(data.width, 10) + "px; display:none;\"></div></div>");
        //创建盒子
        var $Box = $Uialert_Bbox.find(".Uialert_box");
        $Box.append("<div class=\"Uialert_title\"><span class=\"Uialert_Tspan\">" + data.title + "</span><a href=\"#\" class=\"uiAlert_close\" onclick=\"uiConfirm.close(0);return false;\">关闭</a></div>");
        //创建title
        $Box.append("<div class=\"Uialert_nav\"><div class=\"Uialert_Nleft\"><div class='Uialert_NLicon" + (data.flag ? ' Uialert_question' : ' Uialert_warning') + "'></div></div><div class=\"Uialert_Nright\" style=\"width:" + (parseInt(data.width, 10) - 100) + "px;color:" + data.fontcolor+ ";\">" + data.content + "</div></div>");
        //创建正文
        $Box.append("<div class=\"Uialert_button\"><input type=\"button\" value=\"确定\" style=\"right:90px;\" onclick=\"uiConfirm.close(1);return false;\" class=\"Uialert_Binput uiAlert_close radius\" /><input type=\"button\" value=\"取消\" onclick=\"uiConfirm.close(0);return false;\" style=\"right:0;\" class=\"Uialert_Binput uiAlert_close radius\" /></div>");
        //创建底部按钮
        $("body").append($Uialert_Bbox);
        $(".Uialert_bj").fadeTo(0, data.opacity);
        //更改遮挡层透明度
        $(".Uialert_Bbox").show();
        $Box.fadeIn(300);
        this.ChangeCss();
        //光标焦点处于取消按钮上
        $(".Uialert_Binput").eq(1).focus();
    };
    this.ChangeCss = function() {
        //更改css
        $(window).resize(this.ChangeCss);
        //当window窗口变化时
        var $Box = $(".Uialert_box");
        $(".Uialert_Bbox,.Uialert_bj").css({
            "height": $(window).height()
        });
        $Box.css({
            //重置提示框位置
            "top": (($(window).height() - $Box.height()) / 2),
            "left": (($(window).width() - $Box.width()) / 2)
        });
        uiAlert.ie();
    };
    this._init();
}
uiConfirm.data = {};
uiConfirm.close = function(type) {
    this.data.button = type || 0;
    uiAlert.Close();
    if (uiConfirm.type && this.data.button) {
        uiConfirm.fun();
        return false;
    } else if (uiConfirm.data.fun) {
        uiConfirm.data.fun.call(this.data, this.data.button);
    }
    //uiConfirm.data.fun ? uiConfirm.data.fun.call(this.data,this.data.button) : "";
};
uiConfirm.toJson = function(n, name) {
    if (typeof n == "object") {
        $.fn.uiConfirm.defaults = $.extend({}, $.fn.uiConfirm.defaults, n);
    } else {
        $.fn.uiConfirm.defaults.content = n;
    }
    $.fn.uiConfirm.defaults.content = $.fn.uiConfirm.defaults.content.toHtml();
    return $.fn.uiConfirm.defaults;
};
/************************************************************************************/
//alert
function uiAlert(data, flag) {
    this.data = data;
    this.flag = flag || data.flag;
    /*************************/
    if (typeof uiAlert._initialized == "undefined") {
        uiAlert.prototype.Append = function() {
            //创建弹出
            //$(".Uialert_Bbox").length > 0 ? $(".Uialert_Bbox").remove() : "";
            uiAlert.Close();
            $("body").append("<div class=\"Uialert_Bbox Uialert_" + this.data.skin + "\" style=\"height:" + ($(window).height() - 22) + "px;display:none;\"><div class=\"Uialert_bj\" style=\"height:" + ($(window).height() - 22) + "px;\"></div><div class=\"Uialert_box\" style=\"display:none;\"></div></div>");
            //创建盒子
            var $Box = $(".Uialert_box");
            $Box.append("<div class=\"Uialert_title\"><span class=\"Uialert_Tspan\">" + this.data.title + "</span><a href=\"#\" class=\"uiAlert_close\" onclick=\"uiAlert.GoNext();return false;\">关闭</a></div>");
            //创建title
            $Box.append("<div class=\"Uialert_nav\"><div class=\"Uialert_Nleft\"><span class='Uialert_NLicon" + (this.flag ? ' Uialert_info' : ' Uialert_warning') + "'></span></div><div class=\"Uialert_Nright\">" + this.data.nav + "</div></div>");
            //创建正文
            $Box.append("<div class=\"Uialert_button\"><input type=\"button\" value=\"确定\" onclick=\"uiAlert.GoNext();return false;\" class=\"Uialert_Binput uiAlert_close radius\" /></div>");
            //创建底部按钮
            $(".Uialert_bj").fadeTo(0, this.data.opacity);
            //更改遮挡层透明度
            $(".Uialert_Bbox").show();
            $Box.fadeIn(300);
            uiAlert.ChangeCss();
            //更改css
            this.B_Click();
            //绑定点击事件
            this.Auto();
            //绑定自动关闭事件
            uiAlert.next = this.data.next;
            //光标焦点处于按钮上
            $(".Uialert_Binput").focus();
        };
        uiAlert.prototype.Auto = function() {
            //自动
            if (this.data.close) {
                var AutoT = null;
                AutoT = setTimeout(function() {
                        clearTimeout(AutoT);
                        uiAlert.GoNext();
                    },
                    this.data.time);
            }
        };
        uiAlert.prototype.B_Click = function() {
            //点击遮挡层
            var z = $(".Uialert_title").css("background-color");
            $(".Uialert_Bbox,#TB_HideSelect body").bind({
                click: function() {
                    //uiAlert.B_Click_(0,5,z);
                    $(".Uialert_Binput").focus();
                }
            });
        };
        uiAlert._initialized = true;
    }
    /********************/
}
uiAlert.Tostring = function(o) {
    //重组数据
    this.nav = typeof o == "object" ? o.nav.replace(/\n\r/g, "<br />").replace(/\n/g, "<br />").replace(/\r/g, "<br />") : o.replace(/\n\r/g, "<br />").replace(/\n/g, "<br />").replace(/\r/g, "<br />");
    //替换\n为<br />
    this.skin = o.skin || $.fn.alert.defaults.skin;
    this.close = o.close || $.fn.alert.defaults.close;
    this.time = o.time || $.fn.alert.defaults.time;
    this.title = o.title || $.fn.alert.defaults.title;
    this.opacity = o.opacity || $.fn.alert.defaults.opacity;
    this.next = o.next || $.fn.alert.defaults.next;
    this.flag = o.flag || $.fn.alert.defaults.flag;
    return {
        "nav": this.nav,
        "skin": this.skin,
        "close": this.close,
        "time": this.time,
        "title": this.title,
        "opacity": this.opacity,
        "next": this.next,
        "flag": this.flag
    };
};
uiAlert.first = function(data, n, fun) {
    //添加和执行处理
    if (typeof fun == "function") {
        data.next = fun;
    }
    var Alert = new uiAlert(data, n);
    Alert.Append();
};
uiAlert.GoNext = function() {
    uiAlert.Close();
    uiAlert.next();
};
uiAlert.ChangeCss = function() {
    //更改css
    $(window).resize(uiAlert.ChangeCss);
    //当window窗口变化时
    if (typeof loading.object == "undefined" || loading.object.type) {
        var $Box = $(".Uialert_box");
        $(".Uialert_Bbox,.Uialert_bj").css({
            "height": $(window).height()
        });
        $Box.css({
            //重置提示框位置
            "top": (($(window).height() - $Box.height()) / 2),
            "left": (($(window).width() - $Box.width()) / 2)
        });
        uiAlert.ie();
    } else {
        $(".Uialert_Bbox").css({
            position: "absolute",
            top: loading.object.top,
            left: loading.object.left,
            height: loading.object.height,
            width: loading.object.width
        });
    }
};
uiAlert.ie = function() {
    !$.browser.msie ? "" : ($.browser.version == 6.0 ? uiAlert.scroll() : "");
    //当ie6时绑定
};
uiAlert.B_Click_ = function(x, y, z) {
    //更改背景色触发
    var x = x,
        y = y,
        T = null,
        z = z;
    clearInterval(T);
    T = setInterval(function() {
            if (x <= y) {
                uiAlert.Change_b();
                x++;
            } else {
                $(".Uialert_title").css({
                    "background-color": z
                });
                clearInterval(T);
            }
        },

        100);
};
uiAlert.Change_b = function() {
    //更改背景颜色
    $(".Uialert_title").css({
        "background-color": "rgb(" + uiAlert.B_Click_B() + "," + uiAlert.B_Click_B() + "," + uiAlert.B_Click_B() + ")"
    });
};
uiAlert.B_Click_B = function() {
    //随机数字_背景色
    return Math.round(Math.random() * 255);
};
uiAlert.Close = function() {
    //关闭
    $(".Uialert_Bbox").remove();
    if (typeof uiAlert.scroll._init != "undefined") {
        $("html").css("overflow", "");
        delete uiAlert.scroll._init;
    }
    $(window).unbind("resize scroll");
    typeof loading.object != "undefined" ? delete loading.object : "";
    if (typeof CollectGarbage != "undefined") {
        CollectGarbage();
    }
};
uiAlert.scroll = function() {
    //绑定滚动条滚动事件
    if (typeof uiAlert.scroll._init == "undefined") {
        //iframe to hide select elements in ie6
        //$("html").css("overflow","hidden");
        $(".Uialert_Bbox").append("<iframe id=\"TB_HideSelect\" style=\"position: absolute;z-index:99;background-color: #fff;border: none;filter: alpha(opacity=0);height:" + $(".Uialert_Bbox").height() + "px;width: 100%;\"></iframe>");
        uiAlert.scroll._init = true;
    }
    uiAlert.scroll.css();
    $(window).bind({
        scroll: function() {
            uiAlert.scroll.css();
        }
    });
};
uiAlert.scroll.css = function() {
    $(".Uialert_Bbox").css({
        "top": $(window).scrollTop()
    });
};

function loading(data) {
    var type = typeof data,
        $Box = $(".Uialert_Bbox"),
        obj = {
            box: "body",
            body: "",
            height: 0,
            width: 0,
            top: 0,
            left: 0,
            modebox: "UIload_Bbox",
            modebj: "UIload_bj",
            modeload: "UIload_box",
            imgurl: "/images/loading.gif",
            type: true,
            content: "",
            background: "#fff"
        };
    switch (type) {
        case "object":
            obj = $.extend({}, obj, data);
            break;
        case "string":
            obj.box = data;
            break;
        default:
            break;
    }
    if (obj.box == "body") {
        obj.height = $(window).height() - 22;
        obj.width = $(window).width();
        obj.body = "<div class=\"Uialert_Bbox\" style=\"height:" + obj.height + "px;display:none;\"><div class=\"Uialert_bj\" style=\"height:" + obj.height + "px;\"></div><div class=\"Uialert_box\" style=\" border:0 none;width:" + obj.width + "px;height:" + obj.height + "px;position:absolute;top:" + obj.top + "px;left:" + obj.left + "px;z-index:99999;background:url(" + obj.imgurl + ") no-repeat center center;\"></div></div>";
    } else {
        obj.type = false;
        obj.height = $(obj.box).outerHeight();
        obj.width = $(obj.box).outerWidth();
        obj.top = $(obj.box).offset().top;
        obj.left = $(obj.box).offset().left;
        obj.body = "<div class=\"Uialert_Bbox\" style=\"display:none;\"><div class=\"" + obj.modebj + "\" style=\"width:" + obj.width + "px;height:" + obj.height + "px;position:absolute;top:0;left:0;z-index:1;background-color:" + obj.background + ";\"></div><div class=\"" + obj.modeload + "\" style=\"width:" + obj.width + "px;height:" + obj.height + "px;position:absolute;top:0;left:0;z-index:2;background:url(" + obj.imgurl + ") no-repeat center center;\">" + obj.content + "</div></div>";
    }
    $Box.length > 0 || $Box.length > 0 ? $Box.remove() : "";
    $(obj.box).append(obj.body);
    //创建盒子
    $Box = $(".Uialert_Bbox");
    $("." + obj.modebj + ",.Uialert_bj").fadeTo(100, 0.1);
    //更改遮挡层透明度

    $Box.show();
    loading.object = obj;
    uiAlert.ChangeCss();
    //更改css
}
loading.Close = loading.close = uiAlert.Close;

function allClick(){
    var val = $("input[type='checkbox']").first();
    if(val.attr("checked")){
        $("input[type='checkbox']").attr("checked",true);
    }
    else
        $("input[type='checkbox']").uncheck();

    $(".checkbox").checkbox();
}

function tabSlider(obj, time){
	var t;
	tabSlider.auto(obj, time);
	$(obj).mouseover(function(){
		clearInterval(t);
	});
}
tabSlider.auto = function(obj, time){
	t = setInterval(function() {
		tabSlider.TagEnforce(obj);
    },time);
}
tabSlider.TagEnforce = function(obj){
	if($(obj).attr("class") == "login-banner")
		$(obj).attr("class","login-banner2");
	else
		$(obj).attr("class","login-banner");
}