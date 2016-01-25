 // /*验证填入项目*/
 // var validate = new (function(){
 // 	var pattern = {
 // 	 	'useraccount': {
 // 	 		name: '手机或邮箱',
 // 	 		rex: 'phone||email',
 // 	 		tips: '请输入手机或邮箱',
 // 	 		error: '手机或邮箱出错！'
 // 	 	},
 // 	 	'phone': {
 // 	 		name: '手机',
 // 	 		rex: '^1[\\d]{10}$',
 // 	 		tips: '请输入正确的手机号码，用于账户登录、找回密码、账户保护！虚假注册，一经查实，将会被注销号码!',
 // 	 		error: '手机格式不正确！'
 // 	 	},
 // 	 	'oldphone': {
 // 	 		name: '已绑定手机号',
 // 	 		rex: '^1[\\d]{10}$',
 // 	 		tips: '请输入手机号码',
 // 	 		error: '手机格式不正确！'
 // 	 	},
 // 	 	'newphone': {
 // 	 		name: '新绑定手机号',
 // 	 		rex: '^1[\\d]{10}$',
 // 	 		tips: '请输入手机号码',
 // 	 		error: '手机格式不正确！'
 // 	 	},
 // 	 	'password': {
 // 	 		name: '密码',
 // 	 		rex: '^[\\w]{6,20}$',
 // 	 		tips: '请输入6位以上的密码',
 // 	 		error: '密码由6-20位的字母、数字和下划线组成。'
 // 	 	},
 // 	 	'oldpasswd': {
 // 	 		name: '旧密码',
 // 	 		rex: '^[\\w]{6,20}$',
 // 	 		tips: '请输入6-20位以上的密码',
 // 	 		error: '密码由6-20位的字母、数字和下划线组成。'
 // 	 	},
 // 	 	'repassword': {
 // 	 		name: '重复密码',
 // 	 		rex: '^[\\w]{6,20}$',
 // 	 		tips: '请重复输入密码',
 // 	 		error: '密码由6-20位的字母、数字和下划线组成。'
 // 	 	},
 // 	 	'email': {
 // 	 		name: '邮箱',
 // 	 		rex: '^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$',
 // 	 		tips: '请输入邮箱',
 // 	 		error: '您输入的邮箱不正确，请输入正确邮箱地址！'
 // 	 	},
 // 	 	'code': {
 // 	 		name: '激活码',
 // 	 		rex: '^\\d{6}$',
 // 	 		tips: '请输入激活码',
 // 	 		error: '激活码格式出错！'
 // 	 	},
 // 	 	'nick': {
 // 	 		name: '昵称',
 // 	 		rex: '^[\\u4e00-\\u9fa5a-zA-Z0-9_]+$',
 // 	 		tips: '请输入昵称',
 // 	 		error: '4-12个字符，支持中文、英文和数字,下划线'
 // 	 	},
 // 	 	'username': {
 // 	 		name: '真实姓名',
 // 	 		rex: '^[\\u4e00-\\u9fa5]{2,5}$',
 // 	 		tips: '请输入中文名',
 // 	 		error: '请输入2-5个中文字'
 // 	 	},
 // 	 	'receiptname': {
 // 	 		name: '收货信息姓名',
 // 	 		rex: '^[\\u4e00-\\u9fa5]{2,5}$',
 // 	 		tips: '请输入中文名',
 // 	 		error: '请输入2-5个中文字'
 // 	 	},
 // 	 	'location': {
 // 	 		name: '街道地址',
 // 	 		rex: '^[^\\s]+$',
 // 	 		tips: '请输入街道地址',
 // 	 		error: '街道地址填写错误!'
 // 	 	},
 // 	 	'zipcode': {
 // 	 		name: '邮编',
 // 	 		rex: '^[1-9][\\d]{5}$',
 // 	 		tips: '请输入邮编',
 // 	 		error: '邮编格式错误！'
 // 	 	}
 // 	 }
 // 	 //验证
 // 	 this.validateForm = function(form){
 // 	 	for (var i = 0; i < form.length; i++) {
 // 	 		if(form[i].name)
 // 	 	};
 // 	 }
 // })();
 /*基础js扩展*/
 (function(){
 	//js扩展
    //去除空格
    String.prototype.trim = function() {
        return this.replace(/^\s+/, "").replace(/\s+$/, "");
    };
    //获取字节长度
    String.prototype.getLength = function() {
        return this.replace(/[^\x00-\xff]/g, "aa").length;
    };
    //检测url有效性
    String.prototype.isUrl = function() {
        var url = this.trim().toLowerCase(),
            re = "^((https|http|ftp|rtsp|mms)?://)" + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@ 
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
    String.prototype.isZipCode = function() {
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
    Array.prototype.contains = function(item) {
        return RegExp(item).test(this);
    };
    //验证是否在数组内,并返回第N项包含(否则返回null)
    Array.prototype.containsLen = function(item) {
        var str = null;
        for (var i = 0; i < this.length; i++) {
            if (RegExp(item).test(this[i])) {
                str = i;
                return str;
            }
        }
        return str;
    };
    //验证字符串是否是数组内的其中一项
    Array.prototype.equal = function(item) {
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
            if (this[i] == val) {
                return i;
            }
        }
        return -1;
    };
    //移除数组中的字符窜
    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
        return this;
    };
    //把字符串转换成json格式
    String.prototype.toJSON = function() {
        return new Function("return " + this.toText())();
    };
    //截取字符/包含中英文截取	
    String.prototype.subString = function(len, add) {
        var str = this,
            add = add || "";
        if (!str || !len) {
            return '';
        }
        var a = 0, //预期计数：中文2字节，英文1字节
            i = 0, //循环计数
            temp = ''; //临时字串
        for (i = 0; i < str.length; i++) {
            if (str.charCodeAt(i) > 255) {
                a += 2; //按照预期计数增加2
            } else {
                a++;
            }
            //如果增加计数后长度大于限定长度，就直接返回临时字符串
            if (a > len) {
                return temp + add;
            }
            //将当前内容加到临时字符串
            temp += str.charAt(i);
        }
        //如果全部是单字节字符，就直接返回源字符串
        return str;
    };
    //以1开头的纯数字
    String.prototype.exNum = function() {
        var s = this,
            temp = [];
        if (s == "") {
            return 1;
        }
        s = s.replace(/[^\d]/g, "");
        for (var i = 0; i < s.length; i++) {
            temp[0] = s.substring(0, 1);
            temp[1] = s.substring(1, s.length);
            if (temp[0] != 0) {
                return s;
            } else {
                s = temp[1];
            }
        }
        return s || 1;
    };
    //金钱格式1,四舍五入保留2位小数点
    String.prototype.formatMoney = function(s) { //s = 分隔符,默认为空
        var num = this.toString().replace(/[^\d.]/g, "").split("."),
            s = s || "";
        num.length = 2;
        num = num.join(".");
        if (isNaN(num)) {
            num = "0";
        }
        var sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        var cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10) {
            cents = "0" + cents;
        }
        for (var i = 0, j = Math.floor((num.length - (1 + i)) / 3); i < j; i++) {
            num = num.substring(0, num.length - (4 * i + 3)) + s + num.substring(num.length - (4 * i + 3));
        }
        return (((sign) ? '' : '-') + num + '.' + cents);
    };
    //金钱格式2,小数点后保持原来不变
    String.prototype.toMoney = function(s) { //s = 分隔符,默认为空
        var str = this.toString().replace(/[^\d.]/g, "").split(".");
        str.length = 2,
        s = s || "",
        num = ((str[0] || 0) * 1000 / 1000).toString();
        str[1] = str[1] || 0;
        str[1] = Number("0." + str[1]).toString().substring(2, str[1].length + 2);
        var nums = this.toString().substr(0, 1) == '-' ? true : false;
        if (str[1].length < 2) {
            for (var i = str[1].length; i < 2; i++) {
                str[1] += "0";
            }
        }
        for (var i = 0, j = Math.floor((num.length - (1 + i)) / 3); i < j; i++) {
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
    Date.prototype.add = function(milliseconds) {
        var m = this.getTime() + milliseconds;
        return new Date(m);
    };
    Date.prototype.addSeconds = function(second) {
        return this.add(second * 1000);
    };
    Date.prototype.addHours = function(hour) {
        return this.addMinutes(hour * 60);
    };
    Date.prototype.addMinutes = function(minute) {
        return this.addSeconds(minute * 60);
    };
    //根据传入数字返回前几天时间
    Date.prototype.addDate = function(format) {
        var format = parseInt(format, 10) || 0,
            time = 0;
        time = this.getTime() + (format * 60 * 60 * 24 * 1000);
        return new Date(time);
    };
    //时间差
    String.prototype.dateDiff = function(date, interval) { //时间格式字符串差
        var objInterval = {
                'D': 1000 * 60 * 60 * 24,
                'H': 1000 * 60 * 60,
                'M': 1000 * 60,
                'S': 1000,
                'T': 1
            },
            Time = (interval || "D").toUpperCase(),
            start = new Date(this.replace(/-/g, "/")),
            end = new Date(date.replace(/-/g, "/"));
        try {
            return Math.round((end.getTime() - start.getTime()) / parseInt(eval("objInterval." + Time), 10));
        } catch (e) {
            return e.message;
        }
    };
    //手机格式验校
    String.prototype.isTelephone = function() {
        var mobile = this;
        //		if (/^13\d{9}$/g.test(mobile) || (/^15[0-35-9]\d{8}$/g.test(mobile)) || (/^18[0-9]\d{8}$/g.test(mobile))) {
        //			return true
        //		} else {
        //			return false
        //		}
        return /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/.test(mobile);
    };
    String.prototype.isTel = function() {
        return (/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,6})?$/.test(this.trim()));
    };
    /*
     * JS中的string.format，源于C#中的string.Format()
     * //两种调用方式
     * var template1="我是{0}，今年{1}了";
     * var template2="我是{name}，今年{age}了";
     * var result1=template1.format("loogn",22);
     * var result2=template2.format({name:"loogn",age:22});
     * //两个结果都是"我是loogn，今年22了"
     */
    String.prototype.format = function(args) {
        var result = this;
        if (arguments.length > 0) {
            if (arguments.length == 1 && typeof(args) == "object") {
                for (var key in args) {
                    if (args[key] != undefined) {
                        var reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            } else {
                for (var i = 0; i < arguments.length; i++) {
                    if (arguments[i] != undefined) {
                        var regStr = "",
                            num = i.toString();
                        for (j = 0; j < num.length; j++) {
                            regStr += "[" + num.substr(j, 1) + "]{1}";
                        }
                        var reg = new RegExp("{" + regStr + "}", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
        }
        return result;
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
    }
})();
/*-----------------------
		自动完成
-----------------------*/
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
        jQuery.each(trimWords(jQueryinput.val()), function(i, value) {
            request(value, findValueCallback, findValueCallback);
        });
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
    if (options.clickStatus) {
        jQuery(jQueryinput.prev()).bind("click", function() {
            onChange(0, true);
        });
    }

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
        if (currentValue.length >= options.minChars || options.clickStatus) {
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
                url: options.url,
                data: jQuery.extend({
                    q: encodeURIComponent($.trim(term)),
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
/********************************/
 $(function() {
 	/*时间处理*/
 	var $endDate = $(".endDate"),
 		$startDate = $(".startDate");
 	$startDate.each(function(i, item) {
 		$(item).datetimepicker({
 			language: 'zh-CN',
 			autoclose: true,
 			minView: 0,
 			format: 'yyyy-mm-dd hh:ii'
 		}).on('changeDate', function(ev) {
 			$endDate.eq(i).datetimepicker('setStartDate', $(this).find('input').val());
 		});
 	});
 	$endDate.each(function(i, item) {
 		$(item).datetimepicker({
 			language: 'zh-CN',
 			autoclose: true,
 			minView: 0,
 			format: 'yyyy-mm-dd hh:ii'
 		}).on('changeDate', function(ev) {
 			$startDate.eq(i).datetimepicker('setEndDate', $(this).find('input').val());
 		});
 	});
 	//全选处理
 	$("#allCheck-btn").on('click', function() {
 		$("#tableList .check-btn").prop('checked', $(this).prop('checked'));
 	})

 	$("#tableList").on('click', '.check-btn', function() {
 		if ($(this).prop('checked') == false) {
 			$("#allCheck-btn").prop('checked', false);
 		} else {
 			var flag = true;
 			$("#tableList .check-btn").each(function() {
 				if (this.checked == false) flag = false;
 				return false;
 			});
 			$("#allCheck-btn").prop('checked', flag);
 		}
 	});
 	/*查看大图*/
 	$imgMod = $(".img-mod");
 	if ($imgMod.length) {
 		top.baguetteBox.run($imgMod);
 	}
 });