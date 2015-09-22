 // /*验证填入项目*/
 var validate = new (function(){
 	var pattern = {
 	 	'useraccount': {
 	 		name: '手机或邮箱',
 	 		rex: '^(1[\\d]{10}|([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3})$',
 	 		tips: '请输入手机或邮箱',
 	 		error: '手机或邮箱出错！'
 	 	},
 	 	'phone': {
 	 		name: '手机',
 	 		rex: '^1[\\d]{10}$',
 	 		tips: '请输入正确的手机号码，用于账户登录、找回密码、账户保护！虚假注册，一经查实，将会被注销号码!',
 	 		error: '手机格式不正确！'
 	 	},
 	 	'oldphone': {
 	 		name: '已绑定手机号',
 	 		rex: '^1[\\d]{10}$',
 	 		tips: '请输入手机号码',
 	 		error: '手机格式不正确！'
 	 	},
 	 	'newphone': {
 	 		name: '新绑定手机号',
 	 		rex: '^1[\\d]{10}$',
 	 		tips: '请输入手机号码',
 	 		error: '手机格式不正确！'
 	 	},
 	 	'password': {
 	 		name: '密码',
 	 		rex: '^[\\w]{6,20}$',
 	 		tips: '请输入6位以上的密码',
 	 		error: '密码由6-20位的字母、数字和下划线组成。'
 	 	},
 	 	'oldpasswd': {
 	 		name: '旧密码',
 	 		rex: '^[\\w]{6,20}$',
 	 		tips: '请输入6-20位以上的密码',
 	 		error: '密码由6-20位的字母、数字和下划线组成。'
 	 	},
 	 	'repassword': {
 	 		name: '重复密码',
 	 		rex: '^[\\w]{6,20}$',
 	 		tips: '请重复输入密码',
 	 		error: '密码由6-20位的字母、数字和下划线组成。'
 	 	},
 	 	'email': {
 	 		name: '邮箱',
 	 		rex: '^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$',
 	 		tips: '请输入邮箱',
 	 		error: '您输入的邮箱不正确，请输入正确邮箱地址！'
 	 	},
 	 	'code': {
 	 		name: '激活码',
 	 		rex: '^\\d{6}$',
 	 		tips: '请输入激活码',
 	 		error: '激活码格式出错！'
 	 	},
 	 	'nick': {
 	 		name: '昵称',
 	 		rex: '^[\\u4e00-\\u9fa5a-zA-Z0-9_]+$',
 	 		tips: '请输入昵称',
 	 		error: '4-12个字符，支持中文、英文和数字,下划线'
 	 	},
 	 	'username': {
 	 		name: '真实姓名',
 	 		rex: '^[\\u4e00-\\u9fa5]{2,5}$',
 	 		tips: '请输入中文名',
 	 		error: '请输入2-5个中文字'
 	 	},
 	 	'receiptname': {
 	 		name: '收货信息姓名',
 	 		rex: '^[\\u4e00-\\u9fa5]{2,5}$',
 	 		tips: '请输入中文名',
 	 		error: '请输入2-5个中文字'
 	 	},
 	 	'location': {
 	 		name: '街道地址',
 	 		rex: '^[^\\s]+$',
 	 		tips: '请输入街道地址',
 	 		error: '街道地址填写错误!'
 	 	},
 	 	'zipcode': {
 	 		name: '邮编',
 	 		rex: '^[1-9][\\d]{5}$',
 	 		tips: '请输入邮编',
 	 		error: '邮编格式错误！'
 	 	},
        'nonempty': {
            name: '非空',
            rex: function(val){
                return $.trim(val).length != 0;
            },
            tips: '不能为空',
            error: '不能为空'
        },
        'idCard':{
            name:'身份证',
            rex:'^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$',
            tips:"请输入身份证号",
            error:'身份证号码错误！'
        },
        'num':{
            name:'数字',
            rex:'^[0-9]*$ ',
            tips:'请输入数字',
            error:'格式错误'
        }
 	 }
 	 //验证
 	this.test = function(obj){
        var par = $(obj).find('[vd-key]');

 	 	for (var i = 0; i < par.length; i++) {
            var key = par.eq(i).attr('vd-key');
            if(!key) continue;

            var $input = par.eq(i),
                val = $input.val(),
                key = $input.attr('vd-key'),
                obj = pattern[key],
                rex = obj.rex;

 	 		if(($.isFunction(rex) && !rex(val)) ||(!$.isFunction(rex) && !new RegExp(rex).test(val))){
                /*弹出*/
                /*优先使用元素本身配置的属性*/
                var content = $.trim($input.data('content')),
                    placement = $.trim($input.data('placement'));
                if(content.length == 0){
                    content = obj.error;
                }
                if(placement.length == 0){
                    placement = "top";
                }
                $input.popover({
                    'content': content,
                    'trigger': 'none',
                    'placement': placement
                    }).popover('show');
                try {
                   $input.focus();
                } catch (error) {　　 
                   $input.attr('tabindex', '-1').focus().removeAttr('tabindex');
                }
                $input.parent().addClass('has-error');
                $input.on('change', function(){
                    $(this).popover('destroy');
                    $(this).parent().removeClass('has-error');
                })
                return false;
            }
 	 	};
        return true;
 	}
 })();
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
/*-----------------------------
        placeHolder处理
-------------------------------*/
 function placeHolder() {
    var supportPlaceholder = 'placeholder' in document.createElement('input');
    /**
     * @name supportPlaceholder
     * @class 获取浏览器是否支持Placeholde
     */
    this.supportPlaceholder = function(){
        return supportPlaceholder;
    }
    /**
     * @name initInput
     * @class 初始化input
     * @param {inputObj || nodeList || array} obj 要获取样式的对象
     * @param {Boolean} span 是否使用span绝对定位 flase 则给input内添加文字
     */
    this.initInput = function(obj, span){
        if(!supportPlaceholder){
            if(!obj || obj === 'All'){
                var input = document.getElementsByTagName('INPUT');
                initInputList(input, span);
            }else if(typeof obj === 'object' ){
                if(obj.length){
                    initInputList(obj, span);
                }else{
                    placeHolderInput(obj, span);
                }
            }
        }
    }
    /*处理input数组*/
    function initInputList(list, span){
         for (var i = list.length - 1; i >= 0; i--) {
            placeHolderInput(list[i], span);
        };
    }
    //处理input
    function placeHolderInput(obj, span){
        if (!obj.getAttribute('placeholder')) return;
        var imitateMode = span===true?true:false;
        var defaultValue = obj.getAttribute('placeholder');
        if (!imitateMode) {
            obj.onfocus = function () {
                (obj.value == defaultValue) && (obj.value = '');
                obj.style.color = '';
            }
            obj.onblur = function () {
                if (obj.value == defaultValue) {
                    obj.style.color = '';
                } else if (obj.value == '') {
                    obj.value = defaultValue;
                    obj.style.color = '#ACA899';
                }
            }
            obj.onblur();
        } else {
            var placeHolderCont = document.createTextNode(defaultValue);
            var oWrapper = document.createElement('span');
            oWrapper.style.cssText = 'position:absolute; color:#ACA899; display:inline-block; overflow:hidden;';
            oWrapper.className = 'wrap-placeholder';
            oWrapper.style.fontFamily = getStyle(obj, 'fontFamily');
            oWrapper.style.fontSize = getStyle(obj, 'fontSize');
            oWrapper.style.marginLeft = parseInt(getStyle(obj, 'marginLeft')) ? parseInt(getStyle(obj, 'marginLeft')) + 3 + 'px' : 3 + 'px';
            oWrapper.style.marginTop = parseInt(getStyle(obj, 'marginTop')) ? getStyle(obj, 'marginTop'): 1 + 'px';
            oWrapper.style.paddingLeft = getStyle(obj, 'paddingLeft');
            oWrapper.style.width = obj.offsetWidth - parseInt(getStyle(obj, 'marginLeft')) + 'px';
            oWrapper.style.height = obj.offsetHeight + 'px';
            oWrapper.style.lineHeight = obj.nodeName.toLowerCase()=='textarea'? '':obj.offsetHeight + 'px';
            oWrapper.appendChild(placeHolderCont);
            obj.parentNode.insertBefore(oWrapper, obj);
            oWrapper.onclick = function () {
                obj.focus();
            }
            //绑定input或onpropertychange事件
            if (typeof(obj.oninput)=='object') {
                obj.addEventListener("input", changeHandler, false);
            } else {
                obj.onpropertychange = changeHandler;
            }
            function changeHandler() {
                oWrapper.style.display = obj.value != '' ? 'none' : 'inline-block';
            }
            /**
             * @name getStyle
             * @class 获取样式
             * @param {Object} obj 要获取样式的对象
             * @param {String} styleName 要获取的样式名
             */
            function getStyle(obj, styleName) {
                var oStyle = null;
                if (obj.currentStyle)
                    oStyle = obj.currentStyle[styleName];
                else if (window.getComputedStyle)
                    oStyle = window.getComputedStyle(obj, null)[styleName];
                return oStyle;
            }
        }
    }
}
!function($){
 /* 基于Bootstrap Typeahead改造而来的自动完成插件
  * Author：F.L.F
  * Site: http://digdata.me
  * ================================= */

  var Autocomplete = function (element, options) {
    this.$element = $(element)
    this.options = $.extend({}, $.fn.autocomplete.defaults, options)
    this.sorter = this.options.sorter || this.sorter
    this.highlighter = this.options.highlighter || this.highlighter
    this.updater = this.options.updater || this.updater
    this.source = this.options.source
    this.$menu = $(this.options.menu)
    this.shown = false
    this.formatItem = this.options.formatItem || this.formatItem
    this.setValue = this.options.setValue || this.setValue
    this.listen()
  }

  Autocomplete.prototype = {

    constructor: Autocomplete
  , processObj:0
  , formatItem:function(item){
        return item.toString();
    }
  , setValue:function(item){
        return {"data-value":item.toString(),"real-value":item.toString()};
    }
  
  , select: function () {
      var val = this.$menu.find('.active').attr('data-value')
      var realVal = this.$menu.find('.active').attr('real-value')
      this.$element
        .val(this.updater(val)).attr("real-value",realVal)
        .change()
      this.options.select && this.options.select(realVal);
      return this.hide()
    }

  , updater: function (item) {
      return item
    }

  , show: function () {
      var pos = $.extend({}, this.$element.position(), {
        height: this.$element[0].offsetHeight
      })

      this.$menu
        .insertAfter(this.$element)
        .css({
          top: pos.top + pos.height
        , left: pos.left
        })
        .show()

      this.shown = true
      return this
    }

  , hide: function () {
      this.$menu.hide()
      this.shown = false
      return this
    }

  , lookup: function (event) {
      var items

      this.query = event === false ? '' : this.$element.val()

      if (this.query.length < this.options.minLength) {
        return this.shown ? this.hide() : this
      }

      items = $.isFunction(this.source) ? this.source(this.query, $.proxy(this.process, this)) : this.source

      return items ? this.process(items) : this
    }

  , process: function (items) {
      var that = this
      if (!items.length) {
        return this.shown ? this.hide() : this
      }

      return this.render(this.options.items != '-1' ? items.slice(0, this.options.items) : items).show()
    }
 
  , highlighter: function (item) {
      var that = this
      item = that.formatItem(item)
      var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
      return item.replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
        return '<strong style="color:#FF6600;">' + match + '</strong>'
      })
    }

  , render: function (items) {
      var that = this

      items = $(items).map(function (i, item) {
        i = $(that.options.item).attr(that.setValue(item))
        i.find('a').html(that.highlighter(item))
        return i[0]
      })

      items.first().addClass('active')
      this.$menu.html(items)
      return this
    }

  , next: function (event) {
      var active = this.$menu.find('.active').removeClass('active')
        , next = active.next()

      if (!next.length) {
        next = $(this.$menu.find('li')[0])
      }

      next.addClass('active')
    }

  , prev: function (event) {
      var active = this.$menu.find('.active').removeClass('active')
        , prev = active.prev()

      if (!prev.length) {
        prev = this.$menu.find('li').last()
      }

      prev.addClass('active')
    }

  , listen: function () {
      this.$element
        .on('focus',    $.proxy(this.focus, this))
        .on('blur',     $.proxy(this.blur, this))
        .on('keypress', $.proxy(this.keypress, this))
        .on('keyup',    $.proxy(this.keyup, this))

      if (this.eventSupported('keydown')) {
        this.$element.on('keydown', $.proxy(this.keydown, this))
      }

      this.$menu
        .on('click', $.proxy(this.click, this))
        .on('mouseenter', 'li', $.proxy(this.mouseenter, this))
        .on('mouseleave', 'li', $.proxy(this.mouseleave, this))
    }

  , eventSupported: function(eventName) {
      var isSupported = eventName in this.$element
      if (!isSupported) {
        this.$element.setAttribute(eventName, 'return;')
        isSupported = typeof this.$element[eventName] === 'function'
      }
      return isSupported
    }

  , move: function (e) {
      if (!this.shown) return

      switch(e.keyCode) {
        case 9: // tab
        case 13: // enter
        case 27: // escape
          e.preventDefault()
          break

        case 38: // up arrow
          e.preventDefault()
          this.prev()
          break

        case 40: // down arrow
          e.preventDefault()
          this.next()
          break
      }

      e.stopPropagation()
    }

  , keydown: function (e) {
      this.suppressKeyPressRepeat = ~$.inArray(e.keyCode, [40,38,9,13,27])
      this.move(e)
    }

  , keypress: function (e) {
      if (this.suppressKeyPressRepeat) return
      this.move(e)
    }

  , keyup: function (e) {
      switch(e.keyCode) {
        case 40: // down arrow
        case 38: // up arrow
        case 16: // shift
        case 17: // ctrl
        case 18: // alt
          break

        case 9: // tab
        case 13: // enter
          if (!this.shown) return
          this.select()
          break

        case 27: // escape
          if (!this.shown) return
          this.hide()
          break

        default:
          var that = this
          if(that.processObj){
            clearTimeout(that.processObj)
            that.processObj = 0
          }
          that.processObj = setTimeout(function(){
            that.lookup()
          },that.options.delay)
      }

      e.stopPropagation()
      e.preventDefault()
  }

  , focus: function (e) {
      this.focused = true
    }

  , blur: function (e) {
      this.focused = false
      if (!this.mousedover && this.shown) this.hide()
    }

  , click: function (e) {
      e.stopPropagation()
      e.preventDefault()
      this.select()
      this.$element.focus()
    }

  , mouseenter: function (e) {
      this.mousedover = true
      this.$menu.find('.active').removeClass('active')
      $(e.currentTarget).addClass('active')
    }

  , mouseleave: function (e) {
      this.mousedover = false
      if (!this.focused && this.shown) this.hide()
    }

  }


  /* TYPEAHEAD PLUGIN DEFINITION
   * =========================== */

  var old = $.fn.autocomplete

  $.fn.autocomplete = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('autocomplete')
        , options = typeof option == 'object' && option
      if (!data) $this.data('autocomplete', (data = new Autocomplete(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.autocomplete.defaults = {
    source: []
  , items: 8
  , menu: '<ul class="typeahead dropdown-menu autocomplete-list"></ul>'
  , item: '<li><a href="#"></a></li>'
  , minLength: 1
  , delay: 500
  }

  $.fn.autocomplete.Constructor = Autocomplete


 /* TYPEAHEAD NO CONFLICT
  * =================== */

  $.fn.autocomplete.noConflict = function () {
    $.fn.autocomplete = old
    return this
  }


 /* TYPEAHEAD DATA-API
  * ================== */

  $(document).on('focus.autocomplete.data-api', '[data-provide="autocomplete"]', function (e) {
    var $this = $(this)
    if ($this.data('autocomplete')) return
    $this.autocomplete($this.data())
  })

}(window.jQuery);
/*combox*/
function combox(obj, option){
    var $obj = $(obj),
        $input = $obj.find('input[type=text]'),
        $downBtn = $obj.find('.show-btn');

    if($obj.data('autocomplete') == true) return false;
    $obj.data('autocomplete', true);

    $input.autocomplete(option);
    
    $downBtn.on('click', function(){
        var $input = $(this).closest('.combox').find('input[type=text]');
        var acObj = $input.data('autocomplete');
        $input.focus();
        acObj.lookup(false);
    });
}
// 初始化一个标准的combox
function initCombox(obj){
    $(obj).each(function(i, item){
        var t = $(this).data('t'),
        	url = $(this).data('url');
        
        combox(item, {
            source:function(query,process){
                var data = {
                    't' : t,
                    'q' : query
                };
                $.post(url , data, function(res){
                    if($.isArray(res)){
                       process(res);
                    }
                }, 'json');
            },
            formatItem:function(item){
                return item.text;
            },
            setValue:function(item){
                return {'data-value':item["text"],'real-value':item["id"]};
            },
            select:function(val){
                $(this).find('hidden').val(val);
            },
            delay: 0,
            minLength: 0,
            items: -1
        });
    });
}

/*初始化*/
 $(function() {
    /*placeHolder处理----*/
    var phText = new placeHolder();
    phText.initInput('All', true);

 	/*时间处理----*/
    //开始结束时间选择 ---
 	var $endDate = $(".endDate"),
 		$startDate = $(".startDate");

    if($endDate.length > 0 && $startDate.length >　0){
     	$startDate.each(function(i, item) {
     		$(item).datetimepicker({
     			language: 'zh-CN',
     			autoclose: true,
     			minView: 2,
     			format: 'yyyy-mm-dd'
     		}).on('changeDate', function(ev) {
     			$endDate.eq(i).datetimepicker('setStartDate', $(this).val());
     		});
     	});
     	$endDate.each(function(i, item) {
     		$(item).datetimepicker({
     			language: 'zh-CN',
     			autoclose: true,
     			minView: 2,
     			format: 'yyyy-mm-dd'
     		}).on('changeDate', function(ev) {
     			$startDate.eq(i).datetimepicker('setEndDate', $(this).val());
     		});
     	});
    }
    // 单个时间选择 ---
    var $formDateTime = $(".form_datetime");
    if($formDateTime.length > 0){
        $formDateTime.datetimepicker({minView: 2, autoclose:true ,format: 'yyyy-mm-dd'}) 
    }

 	//全选处理----
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
    // $imgMod = $(".img-mod");
    // if($imgMod.length){
        $(document).on('click', '.img-mod>a.thumbnail', function(e){
            if(e.target.className.indexOf('del') != -1) return false;
            var $this = $(this),
                url = $.trim($this.attr('href')),
                type = $this.data('type');

            if(url && type != 'pdf'){
                top.showImg(url);
                return false;
            }if(type == 'pdf'){
                return true;
            }
            return false;
        })
    // }]
    /*整页提交 表单验证*/
    $(".submit-form").on('submit', function(){
        if(!validate.test($(this))){
            return false;
        }
    });

    // 自动补全
    var $combox = $('.combox');
    if($combox.length > 0){
        initCombox($combox);
    };
 });