$(function(){
    $("body").mCustomScrollbar({theme:"minimal"});
    var inputFocus = true,
        text_t = null;
    $("input[type=text], textarea").each(function(index, element) {
        if ($(this).attr("readonly") == "readonly" || $(this).attr("readonly") == true) {
            $(this).addClass("readonly");
        } else if (inputFocus && $(this).prop('disabled') == false) {
            $(this).focus();
            inputFocus = false;
        }
    }).bind({
        focusin: function() {
            var $this = $(this);
            clearTimeout(text_t);
            text_t = setTimeout(function() {
                $this.select();
            }, 150);
        }
    });
});
(function($) {
    $.fn.format = function(o) {
        var newObj = {
            success: 0
        },
            str = o;
        if (typeof str == "undefined") {
            return newObj;
        }
        if (str.indexOf("{") != -1) {
            str = str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
            newObj = $.extend({}, newObj, str.toJSON());
            newObj.success = 1;
        }
        return newObj;
    };
    $.fn.Options = function(options) {
        return options == null ? $.fn.format($(this).attr("class")) : (typeof(options) == "object" ? options : $.fn.format($(this).attr(options)));
    };
    $.fn.extend({
        outClick: function(options) {
            // 添加区域以外点击事件
            var name = $.extend({}, $.fn.outClick.defaults, options),
                $this = $(this);
            name.obj = this;
            if (typeof $this.data("idcache") == "undefined") {
                setTimeout(function() {
                    name.clickNum = 0;
                    $this.data("idcache", name.idcache);
                    $this.find("*").data("idcache", name.idcache);
                    $(document).delegate("*", "mousedown", function(e) {
                        e = e || window.event;
                        var target = e.target || e.srcElement;
                        if ($(target).data("idcache") != name.idcache && $(name.obj).find(target).length <= 0 && name.clickNum == 0) {
                            name.click.call(this);
                            $this.removeClick();
                            name.clickNum++;
                        }
                    });
                }, 50);
            }
        },
        removeClick: function() {
            // 移除区域以外点击事件
            $(this).removeData("idcache");
            $(this).find("*").removeData("idcache");
            $(document).undelegate("*", "mousedown");
        },
        uiSwitch: function(options) {
            // tag
            return this.each(function(e) {
                var name = $.fn.Options.call(this, options),
                    // 获取数组
                    boxClass = "uiSwitchBox" + e;
                name = $.extend({}, $.fn.uiSwitch.defaults, name);
                // 重置数组,把自定义函数和默认函数合并到一起
                $(this).parent().addClass(boxClass);
                // 给该tag父层增加指定的class
                name.box = "." + boxClass;
                // 更改name的box属性,并赋予该tag父层指定的class名字
                Tag(name, this, e);
            });
        },
        uiSelect: function(options) {
            // select
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
                // 给该tag父层增加指定的class
                name.box = ".uiSelect" + index;
                // 更改name的box属性,并赋予该tag父层指定的class
                if (name.change) {
                    Select(name, this, index);
                }
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
        serializeJSON:function(options) {// 输入框提示
            var opts = $.fn.Options.call(this, options);
            return uiSerialize(this,opts);
        },
        autocomplete: function(urlOrData, options) {
            var isUrl = typeof urlOrData == "string";
            options = jQuery.extend({}, jQuery.Autocompleter.defaults, {
                url: isUrl ? urlOrData : null,
                data: isUrl ? null : urlOrData,
                delay: isUrl ? jQuery.Autocompleter.defaults.delay : 10,
                max: options && !options.scroll ? 10 : 150
            }, options);

            // if highlight is set to false, replace it with a do-nothing
			// function
            options.highlight = options.highlight ||
            function(value) {
                return value;
            };

            // if the formatMatch option is not specified, then use formatItem
			// for backwards compatibility
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
            $(this).bind('keyup afterpaste focusout', function(e) {
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
        alert: function(options) {
            // alert提示
            return this.each(function(e) {
                // 重新组json数据
                var name = new uiAlert.Tostring(options);
                uiAlert.first(name, e);
            });
        },
        uiConfirm: function(options) {
            // 确认提示
            return this.each(function(e) {
                var name = uiConfirm.toJson(options);
                uiConfirm(name, e);
            });
        },
        inputTips: function(url, len, back, width, fun, clickStatus, isValid, tipError) {
            var url = url || "",
                back = back || "",
                len = len || 2,
                width = width || 166;
            isValid = isValid == undefined ? true:isValid;
            tipError = tipError == undefined ? false : tipError;
            if (url == "") {
                return false;
            }
            $(this).unbind();
            $(this).autocomplete(url, {
                "max": 30,
                "minChars": len,
                "clickStatus":clickStatus,
                "tipError":tipError,
                "width": width,
                "dataType": "json",
                parse: function(data) {
                    if(data.code == 0){
                        if (isValid == true && fun != null) fun.call(this, data.result);
                        return $.map(data.result, function(item, row) {
                            return {
                                data: item,
                                value: item.value,
                                result: item.text
                            }
                        });
                    }
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
                if (fun != null && val == "") {
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
        },
        checkbox: function(options) { // 复选框
            return this.each(function(e) {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, $.fn.checkbox.defaults, opts);
                new checkBox(this, opts);
            });
        },
        radio: function(options) { // 单选框
            return this.each(function(e) {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, $.fn.radio.defaults, opts);
                new radio(this, opts);
            });
        },
        info: function(msg, success) {
            var msg = msg || '',
                success = success || false;
            var  $thisP = $(this).closest('.contnet'),
            $thisP = $thisP.length ? $thisP : $(this).closest('.tablelist');
            xPosition = $thisP.width() + $thisP.offset().left * 2,
            marginTop = $(this).closest("div").offset().top,
            yPosition = $thisP.height();
            if ((!-[1, ])) {
                yPosition = yPosition + 33;
            }
            feedBackMessage(msg, xPosition, yPosition, -1, marginTop);
        },
        input: function(options) { // 输入框提示
            return this.each(function(e) {
                var opts = $.fn.Options.call(this, options);
                opts = $.extend({}, $.fn.input.defaults, opts);
                new fInput(this, opts);
            });
        }
    });
    $.fn.outClick.defaults = {
        // 默认区域外点击事件
        idcache: "UiOutSide",
        remove: true,
        click: function() {}
    };
    $.fn.alert.defaults = {
        // 弹出框默认设置
        nav: "",
        // 弹出内容
        flag: false,
        // 处理成功标志
        skin: "orange",
        // 皮肤
        title: "温馨提示",
        // 提示标题
        close: false,
        // 自动关闭
        next: function() {},
        // 点击确定后执行事件
        opacity: 0.1,
        // 遮挡层透明度
        time: 3000
        // 超时时间,当自动关闭为true时生效使用
    };
    $.fn.uiConfirm.defaults = {
        title: "确认提示", // 标题
        skin: "orange", // 皮肤
        content: "", // 确认提示内容
        width: 350,
        data: null, // 开放自定义接口,为方法之间数据传输
        opacity: 0.1,
        button: 0, // 确认回调方法 0/1,0表示取消,1表示确认
        flag:1,
        fontcolor:"#434343",
        alertsubmit:'确认',
        alertcancle:'取消',
        fun: false // 用户选择后回调方法
    };
    $.fn.uiSwitch.defaults = {
        // tag默认设置
        movetag: "li",
        // 点击切换
        showtag: ".stag",
        // 显示/隐藏
        addtclass: "tselected",
        // 选项卡增加class
        addsclass: "stselected",
        // 显示/隐藏增加class
        jump: true,
        // 针对链接可点/不可点
        time: 300,
        // 超时时间
        custom: false,
        // 点击事件
        box: null
        // 为筛选切换时准备的,不需要初始设置
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
    $.fn.input.defaults = {
        text: "请输入",
        color: "#c5c5c5"
    };
    $.fn.uiSelect.defaults = {
        // select默认设置
        width: "100",
        // 选择项的宽度
        height: "200",
        // 选择项的高度
        type: "click",
        // mouseenter鼠标移入显示,click鼠标点击显示项
        change: true,
        // 是否重置样式
        onchange: false,
        ajax: false,
        box: null,
        // 为筛选切换时准备的,不需要初始
        input: false,
        text: "" // 默认增加文字
    };
})(jQuery);

// 判断文件，获取传参。
function getJsUrl(url){
  var js = $("script"), list =[], lists = [];
  for (var i = 0; i < js.length; i++) {  
      if (js[i].src.indexOf(url) >= 0) {  
        var arraytemp = new Array(), arraytemps = new Array();
        arraytemp = js[i].src.split('?');
        arraytemp = arraytemp[1].split('&');
        for (var j = 0; j < arraytemp.length; j++) {
            arraytemps = arraytemp[j].split('=');
            list.push('"'+arraytemps[0]+'":"'+decodeURI(arraytemps[1])+'"');
        };
      }
  }
  lists = "{" + list.join(',') + "}";
  return lists.toJSON();
}

function getUrlParam(name){
    var url = window.location.href,
        urlArr = url.split("?");
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    if(urlArr.length > 1){
    	var r = urlArr[1].match(reg);  // 匹配目标参数
        if (r!=null) return unescape(r[2]); return null; // 返回参数值
    }else
    	 return null;
}

// ajax读取html页面
function getPage(obj, url, id){
    id = id || "#yui-contentBox";
    $(obj).parent().parent().find("a").removeClass("active");
    $(obj).addClass("active");
    if(url){
        ajax({url:url+"?t"+Math.random(),type:"GET",dataType:"html",callBack:function(data){
            $(id).html(data);
        }});
    }
}

function ajaxPage(url){
    if(url){
        ajax({url:url+"?t"+Math.random(),type:"GET",dataType:"html",callBack:function(data){
            $("#yui-contentBox").html(data);
        }});
    }
}

// 顶部短信提示
function msgText(text, fun){
    this.fun = fun || "";
    var html = "<div class=\"topMsgTips\" id=\"yui-topMsgTips\">\n\
            <span class=\"lFloat\"><img src=\"/resources/images/icon/t_1.png\" border=\"0\"></span>\n\
            <p class=\"msgTipsText\">"+text+"</p>\n\
        </div>";
    if(top.$(".topMsgTips").length <= 0)
        top.$("body").append(html);
    else{
        top.$("#yui-topMsgTips .msgTipsText").html(text);
        top.$("#yui-topMsgTips").show();
    }
    if(fun == true){
    	setTimeout(function() {
    		top.$("#yui-topMsgTips").hide("slow");
		}, 2000);
    }
    if (typeof fun == "function") {
       this.fun.call(this, top.$("#yui-topMsgTips"));
    }
}

// 表单元素获取
function uiSerialize(o,opts){
    var $this = $(o),
        data = opts.data || $this.serializeArray(),
        list = [];
    $.each(data, function(i, filed){
        list.push('"'+filed.name+'":"'+filed.value+'"');
    });
    var newdata = "{" + list.join(',') + "}";
    return newdata.toJSON();
}

// 重置表格
function hoverTable(obj, tr, thead) {
    if($(obj).parents(".tablelist .sData").length <= 0){
        $(obj).jFixedtable({
            width: 0,
            headerRows: 1,
            headDispay: thead,
            height: 0,
            edit: true
        }, 100);
    }
    if(thead) $(".sData").mCustomScrollbar({theme:"minimal"}); else $(obj).parent().mCustomScrollbar({theme:"minimal"});
    var $Class = $(obj),
        tr = tr || false;
    if (typeof hoverTable._init == "undefined") {
        // 2012.10.9新增 //在设置表格内容超出隐藏后增加提示功能
        /*
		 * $Class.find("tbody").find("td").bind({ mouseenter: function() { var
		 * $this = $(this), title = $this.attr("title") || false, text =
		 * $this.text().trim(), Tips = $this.attr("tips") || false; if (title &&
		 * !Tips) { $this.attr("tips", true); return; } else if (Tips) { return; }
		 * if (text != "" && text != "-+") { $this.attr("title", text); } } });
		 */
        hoverTable._init = true;
    } else {
        $Class.find("tbody").find("tr").removeClass("trSplit").off("click mouseenter mouseleave");
    }
    if (tr) {
        $Class.each(function(index, element) {
            $(this).find("thead").find("th").last().addClass("lastTh");
            $Class.eq(index).find("tbody").find("tr").each(function(i, element) {
                if (i % 2 == 1) {
                    $(this).addClass("trSplit");
                }
            });
        });
    }
    $Class.find("tbody").find("tr").bind({
        click: function() {
            $(this).addClass("trClick").siblings().removeClass("trClick");
        },
        mouseenter: function() {
            $(this).addClass("trHover");
        },
        mouseleave: function() {
            $(this).removeClass("trHover");
        }
    });
}

function resetText(o) {
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
            if(clone == "dropDown") {
                var $obj = $("input[icon=" + clone + "]"),
                    $search = $("<input type=\"button\" class=\"dropDown\" value=\"\" id=\"" + name + "\" />");
                $obj.hide();
                $this.wrap($("<label type=\"" + (attr || "label") + "\" class=\"borderRadius" + Class + "\"></label>")).after($search).attr("autocomplete", "off");
                $search.click(function() {
                    zyhDrop(13,true);
                });
                new inputEnter({
                    input: this,
                    func: function() {
                        $obj.click();
                    }
                });
            } else if(clone == "dropCss") {
                var $obj = $("input[icon=" + clone + "]"),
                    $search = $("<span class=\"dropDown\" value=\"\" id=\"" + name + "\" />");
                $obj.hide();
                $this.wrap($("<label type=\"" + (attr || "label") + "\" class=\"borderRadius" + Class + "\"></label>")).after($search).attr("autocomplete", "off");
                $search.bind("click",function() {
                    $this.click();
                });
                new inputEnter({
                    input: this,
                    func: function() {
                        $obj.click();
                    }
                });
            } else{
                if(clone) {
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
                }  else {
                    $this.wrap(function() {
                        return "<label type=\"" + (attr || "label") + "\" class=\"borderRadius" + Class + "\" />";
                    }).attr("autocomplete", "off");
                    if($this.attr("arrow") == "true") $this.before("<span class=\"inputimg\"></span>");
                    if($this.attr("wdate") == "true"){
                        $this.attr("readonly",true);
                        $this.before("<span class=\"Wdate\"></span>");
                    }
                }
            }
        }
    });
    inputHover();
}

function inputHover() {
    $("input,.borderRadius").bind("mouseenter mouseleave", function(e) {
        var Class = ($(this).attr("icon") || $(this).attr("type")) + "Hover";
        if (e.type == "mouseenter") {
            $(this).addClass(Class);
        } else {
            $(this).removeClass(Class);
        }
    });
}

function inputEnter(obj) { // 输入框回车事件
    if (typeof obj != "object") {
        return false;
    }
    var obj = $.extend({}, inputEnter.defaults, obj);
    if (!obj.input) {
        return false;
    }
    $(obj.input).bind({
        keyup: function(event) {
            var event = event || window.event;
            if (event.keyCode == 13) {
                obj.func.call(this);
            }
        }
    });
}
inputEnter.defaults = {
    input: false,
    func: false
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
    // 重置label
    next = e.next("label");
    // 初始化
    if (disabled == false) {
        if (e.is(":checked")) {
            next.addClass(opt.skin + "-" + opt.check);
        }
    } else {
        next.addClass(opt.skin + "-" + opt.disabled);
    }
    // 移除只读复选框
    // if(opt.readonly){
    // e.remove();
    // }
    // 绑定事件
    next.bind({
        click: function(event) {
            if (opt.readonly) {
                return false;
            }
            if (e.is(":disabled")) {
                // alert(opt.alert);
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

function feedBackMessage(message, x, y, delay, top) {
    if (!message) return;
    var x = /\d{1,2}%|100%|left|right/.test(x) ? x : (parseInt(x) || 0);
    var y = /\d{1,2}%|100%|top|bottom/.test(y) ? y : (parseInt(y) || 0);
    var width = 0;
    var height = 0;
    delay = parseInt(delay) || -1;
    var fdDiv = document.getElementById('show_feedBack_message');
    if (!fdDiv) {
        var showMessage = document.createElement("div");
        showMessage.id = 'show_feedBack_message';
        document.body.appendChild(showMessage);
        fdDiv = document.getElementById('show_feedBack_message');
    }
    if (feedBackMessage.timer) {
        clearInterval(feedBackMessage.timer)
    }
    fdDiv.innerHTML = message;
    fdDiv.style.display = "";
    width = $(fdDiv).outerWidth();
    height = $(fdDiv).outerHeight();
    var docHeight = document.documentElement.scrollHeight > document.documentElement.clientHeight ? document.documentElement.scrollHeight : document.documentElement.clientHeight;
    if (/left|right/.test(x)) {
        x = (x == "left") ? "0px" : (docWidth - fdDiv.offsetWidth) + "px";
    } else {
        x = (parseInt(x, 10) - width) / 2 + "px";
    }
    if (/top|bottom/.test(y)) {
        y = (y == "top") ? "0px" : (docHeight - fdDiv.offsetHeight) + "px";
    } else {
        y = ((parseInt(y, 10) - height) / 2 + top) + "px";
    }
    fdDiv.style.left = x;
    fdDiv.style.top = y;
    var step = parseInt(delay / 100);
    // var IE = navigator.userAgent.indexOf("MSIE")>0 ? 1: 0;
    // var opacity = IE ? fdDiv.filters.Alpha.Opacity : fdDiv.style.opacity;
    fdDiv.style.opacity = 100;
    if (delay != -1) {
        feedBackMessage.timer = setInterval(function() {
            if (fdDiv.style.opacity > 0) {
                fdDiv.style.opacity--;
            } else {
                clearInterval(feedBackMessage.timer);
                fdDiv.style.display = "none";
            }
        }, step);
    }
}

function inputTips(id, url, len, back, width, fun, clickStatus, isValid, tipError) {
    var id = id || "",
        url = url || "",
        back = back || "",
        len = len || 2,
        width = width || 166,
        clickStatus = clickStatus == null ? false : clickStatus;
    if (id == "" || url == "") {
        return false;
    }
    
    $(id).inputTips(url, len, back, width, fun, clickStatus, isValid, tipError);
}

function Tag(data, o) {
    // tag绑定
    var T = null;
    // $(data.box + " " + data.movetag).bind({
    $(o).find(data.movetag).bind({
        click: function(e) {
            // 点击tag
            Tag.TagEnforce(data, this, o, $(this).index());
            data.jump ? "" : Tag.stopDefault(e);
        },
        mouseover: function() {
            // 鼠标移上去tag
            var index = $(this).index(),
                $this = this;
            T = setTimeout(function() {
                Tag.TagEnforce(data, $this, o, index);
            }, data.time);
        },
        mouseout: function() {
            // 鼠标移开tag
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
Tag.TagEnforce = function(data, obj, o, n) {
    // tag处理
    $(obj).addClass(data.addtclass).siblings().removeClass(data.addtclass);
    $(data.showtag).eq(n).addClass(data.addsclass).siblings().removeClass(data.addsclass);
    data.custom ? data.custom.call(obj) : "";
    // 判断是否有自定义事件并执行
};

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
        // 创建select盒子
        // 获取select项
        $(box + "select option").each(function() {
            var $this = $(this),
                Class = $this.attr("selected") ? " class=\"optGroupLi selected\"" : " class=\"optGroupLi\"",
                SelectVal = $this.text() || "",
                icon = $this.attr("icon") ? "<span class=\"selectIcon " + $this.attr("icon") + "\"></span>" : "";
            if (SelectVal != "") {
                // Option.push("<li" + Class + (data.onchange ? "
				// onclick=\"javascript:" + data.onchange + ";\"" : "") + "
				// value=" + $(this).val() + ">" + SelectVal + "</li>");
                val.push(SelectVal);
                Option.push("<li" + Class + " value=" + $this.val() + ">" + icon + SelectVal + "</li>");
            }
        });
        $(box + ".selectul").append(Option.join(""));
        // 在指定class内添加select项
        // 验证option高度
        // boxHeight[1] = $(box + ".selectubox").height();
        // boxHeight[2] = data.height = boxHeight[1] < boxHeight[0] &&
		// boxHeight[1] != 0 ? boxHeight[1] : boxHeight[0];
        // $(box + ".selectubox").height(boxHeight[2]);
        // 执行绑定
        Select.SelectOption(data, obj);
        // 获取select分组
        $(box + "select optgroup").each(function(i) {
            var $this = $(this),
                SelectVal = $this.attr("label") || "",
                option = $this.find("option").eq(0).index(box + "select option");
            $(box + ".selectul").find("li").eq(i + option).before($("<li class=\"optgroup\">" + SelectVal + "</li>"));
        });
    }
}
// 获取分组添加位置
Select.optgroup = function(a, c) {
    for (var a = 0; a < c.length; a++) {
        if (b == c[a]) {
            return a;
            break
        }
    }
};
// 绑定下拉
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
        // 选择项动作,以及点击项后触发动作
        mouseenter: function() {
            // 鼠标移入
            $(this).addClass("Optionselect");
        },
        mouseleave: function() {
            // 鼠标移出
            $(this).removeClass("Optionselect");
        },
        click: function() {
            // 鼠标点击
            var $this = $(this),
                Index = $this.index(data.box + " .optGroupLi");
            $(data.box + " option").eq(Index).attr("selected", "selected"); // .siblings().removeAttr("selected");
            // 控制select
            if (data.input) {
                $(data.box + " .selecttext").val($this.text());
            } else {
                $(data.box + " .selecttext").text(data.text + $this.text());
            }
            // 显示当前选择内容
            $this.addClass("selected").siblings().removeClass("selected");
            // 为当前选择添加指定class,并移除同辈元素的指定class
            Selectremove();
            if (typeof Change == "function") {
                Change.call($(data.box + " select"));
            }
        }
    });

    function Selectremove() {
        // 移出选择项盒子
        $box.removeClass(hoverClass);
        $option.css({
            "top": "-9999px",
            "left": "-9999px"
        });
        _init = false;
        // 恢复初始默认状态
    };
};
// select绑定
/**
 * 2013-09-05 修正下拉项高度问题
 */
Select.SelectOption = function(data, obj) {
    var SelectTime = null,
        $box = $(data.box + " .selecttbox"),
        $option = $(data.box + " .selectubox"),
        $ouclick = $(data.box + " .selectubox"),
        _init = false,
        hoverClass = "selecthover";
    if (data.type == "mouseover") {
        // 根据用户自定义绑定动作
        SelectBindM();
    } else {
        SelectBindC();
    }
    Select.SelectLi(data);

    function SelectBindM() {
        // 绑定鼠标移入显示选择项
        $box.bind({
            mouseenter: function() {
                // 鼠标移入
                var $this = this;
                $(this).addClass(hoverClass);
                SelectTime = setTimeout(function() {
                    SelectOver($this);
                }, 300);
            },
            mouseleave: function() {
                // 鼠标移出
                _init ? "" : $box.removeClass(hoverClass);
                clearTimeout(SelectTime);
            }
        });
    };

    function SelectBindC() {
        // 绑定鼠标点击显示选择项
        $box.bind({
            mouseleave: function() {
                // 鼠标移出
                _init ? "" : $box.removeClass(hoverClass);
            },
            click: function() {
                // 鼠标点击
                _init ? "" : SelectOver(this);
            },
            mouseenter: function() {
                // 鼠标移入
                $(this).addClass(hoverClass);
            }
        });
    };

    function SelectOver(o) {
        // 移入选择项盒子
        _init = false;
        SelectTop(o);
        $ouclick.outClick({
            click: function() {
                Selectremove();
                _init = false;
            }
        });
    };

    function Selectremove() {
        // 移出选择项盒子
        $box.removeClass(hoverClass);
        $option.css({
            "top": "-9999px",
            "left": "-9999px"
        });
        _init = false;
        // 恢复初始默认状态
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

// 输入框提示
function fInput(o, opts) {
    var $this = $(o),
        padding = parseInt(getCss(o, "paddingLeft")),
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
// 获取css属性

function getCss(o, key) {
    return o.currentStyle ? o.currentStyle[key] : document.defaultView.getComputedStyle(o, false)[key];
};
var supports = (function() {
    var div = document.createElement('div'),
        vendors = 'Khtml Ms O Moz Webkit'.split(' '),
        len = vendors.length;
    return function(prop) {
        if (prop in div.style) {
            return true
        };
        prop = prop.replace(/^[a-z]/, function(val) {
            return val.toUpperCase();
        });
        while (len--) {
            if (vendors[len] + prop in div.style) {
                return true;
            }
        }
        return false;
    };
})(jQuery);

function CharMode(iN){
    if (iN>=48 && iN <=57) // 数字
        return 1;  
    if (iN>=65 && iN <=90) // 大写字母
        return 2;  
    if (iN>=97 && iN <=122) // 小写
        return 4;  
    else  
        return 8; // 特殊字符
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

pub_getws = ajax = function(o, data, callBack) {
    new ajax.ajax(o, data, callBack);
};
ajax.ajax = function(o, data, callBack) {
    this.url = o.url || o;
    this.type = o.type || "POST";
    this.data = o.data || (data || "");
    this.dataType = o.dataType || "json";
    this.loading = o.load == null ? true : o.load;
    this.contentType = o.contentType || "application/x-www-form-urlencoded";
    this.callBack = o.callBack || callBack;
    this.data['token'] = top.token;
    typeof this.callBack == "undefined" ? this.callBack = ajax.ajaxback : "";
    var $this = this;
    $.ajax({
        url: $this.url,
        type: $this.type,
        data: $this.data,
        contentType: $this.contentType,
        dataType: this.dataType,
        cache: false,
        success: function(data) {
            if(data.code == 9){
                top.msgText(data.msg,true);
                return false;
            }
            setTimeout(function() {
                $this.callBack.call(this, data);
            }, 200);
        },
        error: function(xml, status) {
            if (status == "error") {
                try {
                    var json = eval("(" + xml.responseText + ")");
                    alert(json.Message + "\n" + json.StackTrace);
                } catch (e) {

                }
            } else {
                alert(status + "\n格式错误");
            }
        },
        complete: function() {
            if ($this.loading) {
                top.loading.Close();
            }
        }
    });
    if (this.loading) {
        top.loading();
    }
};
ajax.ajaxback = function(o) {};

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
            imgurl: "/resources/images/loading.gif",
            type: true,
            content: "",
            background: "#000"
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
    // 创建盒子
    $Box = $(".Uialert_Bbox");
    $("." + obj.modebj + ",.Uialert_bj").fadeTo(100, 0.3);
    // 更改遮挡层透明度

    $Box.show();
    loading.object = obj;
    uiAlert.ChangeCss();
    // 更改css
}
loading.Close = function(){
    uiAlert.Close();
}

jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') {
        // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
            // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else {
        // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));

                    break;
                }
            }
        }
        return cookieValue;
    }
};
/** ******************************************************************* */
function CheckLogin(sCookie) {
    var sSearch;
    sSearch = sCookie + "=";
    offset = document.cookie.indexOf(sSearch);
    if (offset != -1) {
        offset += sSearch.length;
        end = document.cookie.indexOf(";", offset);
        if (end == -1)
            end = document.cookie.length;
        end = document.cookie.indexOf("&", offset);
        if (end == -1)
            end = document.cookie.length;
        return unescape(document.cookie.substring(offset, end));
    } else {
        return "";
    }
}

function loadUpload(c) {
	var c = c || {};
    if (typeof c != "object") {
        c = {}
    }
    c = $.extend({},loadUpload.defaults, c);
	$(c.id).uploadify({
        // 指定swf文件
        'swf': c.swf,
        // 后台处理的页面
        'uploader': c.uploader,
        'scriptData': c.scriptData,
        // 按钮显示的文字
        'buttonText': c.buttonText,
        'buttonClass': c.buttonClass,
        'height': c.height,
        'width': c.width,
        'fileTypeDesc': c.type_description,
        // 允许上传的文件后缀
        'fileTypeExts': c.type,
        'fileSizeLimit':c.fileSizeLimit,
        'simUploadLimit':c.simUploadLimit,// 允许同时上传的个数
        'queueSizeLimit':c.queueSizeLimit,// 当允许多文件生成时，设置选择文件的个数，默认值：999 。
        'fileObjName': 'file',
        'auto': c.auto,
        // 设置为true将允许多文件上传
        'multi': c.multi,
        'onUploadSuccess':c.success,
        'onInit': loadingStartFun,
        'onSelect':showLoad,
        'queueID':'queuelist',
        'onQueueComplete':loadSuccess
    });
}

loadUpload.defaults = {
	swf: '/resources/images/uploadify.swf',
    // 后台处理的页面
    uploader: '/00003010',
    scriptData : {'token':top.token},
    // 按钮显示的文字
    buttonText: '上传',
    buttonClass: 'uploadifyBtn',
    height: 30,
    width: 45,
    id:'#tag_0',
    type_description: '所有文件',
    type: '*.gif; *.jpg; *.png',
    fileSizeLimit: 2048,
    simUploadLimit:1,// 允许同时上传的个数
    queueSizeLimit:1,// 当允许多文件生成时，设置选择文件的个数，默认值：999 。
    auto: true,
    // 设置为true将允许多文件上传
    multi: false,
    success:function(file, data, response){}
}

function DrawImage(f, e, d) {
    var c = $(f).width();
    if (c > e) {
        $(f).width(e)
    }
    var a = $(f).height();
    if (a > d) {
        $(f).height(d)
    }
}

function cateImage(img, ext){
	if(ext == ".pdf"){
		return "/resources/images/pdf.png";
	}else
		return img != ""?ftpImgPath+downloaderPath+"/"+img+"/"+top.token:"";
}

function centerImage(a, d, c) {
    if ($(a).data("cache-id") == undefined) {
        var h = (this.id || "") + parseInt(Math.random() * 100000);
        $(a).wrap(function() {
            return '<div id="' + h + '" />'
        }).data("cache-id", h)
    } else {
        var h = $(a).data("cache-id");
        $(a).css({
            width: "",
            height: ""
        });
        $("#" + h).css({
            padding: 0
        })
    }
    DrawImage(a, d, c);
    var g = $("#" + h);
    if (a.height < c) {
        var f = (c - a.height) / 2;
        g.css({
            "padding-top": f + "px"
        })
    }
    if (a.width < d) {
        var e = (d - a.width) / 2;
        g.css({
            "padding-left": e + "px"
        })
    }
}

function splitLogo(text, imgurl){
	return !imgurl.isEmpty() ? "<img src=\""+cateImage(imgurl)+"\" />":(!text.isEmpty()?text.substring(0, 1):"");
}

function removeInfo(){
	$("#show_feedBack_message").remove();
}

function cLayer(obj, title, content, width, pos){
    var $this = $(obj), width = width || 300, pos = pos || "left", gid = "";
    gid = $this.attr("gid");
    title = title || "";
    content = content || "";
    var top = parseInt($(window).height()) - parseInt($this.offset().top);

    $this.parent().addClass("relative");
    var box = $("<div>").attr("class","absolute minBox").width(width).css(pos,0),
        html=(title != "" ? "<div class=\"minBoxTitle\">"+title+"</div>":"")+"<div class=\"minBoxContent\">"+content+"</div>";
        gid != "undefined" ? box.attr("gid",gid):"";
    if(top < 340) box.css("bottom",0);
    $this.parent().append(box.append(html));
    $(".minBox").outClick({
        click:function(){
            $this.parent().removeClass("relative");
            $(".minBox").remove();
        }
    });
}

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
            e.after("<label class=\"" + opt.skin + "\" "+(text == ""?"style='padding-left:15px;'":"")+" data-d=\"true\">" + text + "</label>");
        } else {
            next.addClass(opt.skin).attr({
                tabindex: 0,
                unselectable: "on",
                "data-d": "true"
            });
        }
    }
    // 重置label
    next = e.next("label");
    // 初始化
    if (disabled == false) {
        if (e.is(":checked")) {
            next.addClass(opt.skin + "-" + opt.check);
        }
    } else {
        next.addClass(opt.skin + "-" + opt.disabled);
    }
    // 移除只读复选框
    if (opt.readonly && opt.remove) {
        e.remove();
    }
    // 绑定事件
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

function uiConfirm(n) {
    var data = n || $.fn.uiConfirm.defaults;
    uiConfirm.data = data;
    this._init = function() {
        uiAlert.Close();
        var $Uialert_Bbox = $("<div class=\"Uialert_Bbox Uialert_" + data.skin + "\" style=\"height:" + ($(window).height() - 22) + "px;display:none;\"><div class=\"Uialert_bj\" style=\"height:" + ($(window).height() - 22) + "px;\"></div><div class=\"Uialert_box\" style=\"width:" + parseInt(data.width, 10) + "px; display:none;\"></div></div>");
        // 创建盒子
        var $Box = $Uialert_Bbox.find(".Uialert_box");
        $Box.append("<div class=\"Uialert_title\"><span class=\"Uialert_Tspan\">" + data.title + "</span><a href=\"#\" class=\"uiAlert_close\" onclick=\"uiConfirm.close(0);return false;\">关闭</a></div>");
        // 创建title
        $Box.append("<div class=\"Uialert_nav\"><div class=\"Uialert_Nleft\"><div class='Uialert_NLicon" + (data.flag ? ' Uialert_question' : ' Uialert_warning') + "'></div></div><div class=\"Uialert_Nright\" style=\"width:" + (parseInt(data.width, 10) - 100) + "px;color:" + data.fontcolor+ ";\">" + data.content + "</div></div>");
        // 创建正文
        $Box.append("<div class=\"Uialert_button\"><input type=\"button\" value="+data.alertsubmit+" style=\"right:90px;\" onclick=\"uiConfirm.close(1);return false;\" class=\"Uialert_Binput uiAlert_close radius\" /><input type=\"button\" value="+data.alertcancle+" onclick=\"uiConfirm.close(0);return false;\" style=\"right:0;\" class=\"Uialert_Binput uiAlert_close radius\" /></div>");
        // 创建底部按钮
        $("body").append($Uialert_Bbox);
        $(".Uialert_bj").fadeTo(0, data.opacity);
        // 更改遮挡层透明度
        $(".Uialert_Bbox").show();
        $Box.fadeIn(300);
        this.ChangeCss();
        // 光标焦点处于取消按钮上
        $(".Uialert_Binput").eq(1).focus();
    };
    this.ChangeCss = function() {
        // 更改css
        $(window).resize(this.ChangeCss);
        // 当window窗口变化时
        var $Box = $(".Uialert_box");
        $(".Uialert_Bbox,.Uialert_bj").css({
            "height": $(window).height()
        });
        $Box.css({
            // 重置提示框位置
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
    // uiConfirm.data.fun ? uiConfirm.data.fun.call(this.data,this.data.button)
	// : "";
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
/** ********************************************************************************* */
// alert
function uiAlert(data, flag) {
    this.data = data;
    this.flag = flag || data.flag;
    if (typeof uiAlert._initialized == "undefined") {
        uiAlert.prototype.Append = function() {
            // 创建弹出
            // $(".Uialert_Bbox").length > 0 ? $(".Uialert_Bbox").remove() : "";
            uiAlert.Close();
            $("body").append("<div class=\"Uialert_Bbox Uialert_" + this.data.skin + "\" style=\"height:" + ($(window).height() - 22) + "px;display:none;\"><div class=\"Uialert_bj\" style=\"height:" + ($(window).height() - 22) + "px;\"></div><div class=\"Uialert_box\" style=\"display:none;\"></div></div>");
            // 创建盒子
            var $Box = $(".Uialert_box");
            $Box.append("<div class=\"Uialert_title\"><span class=\"Uialert_Tspan\">" + this.data.title + "</span><a href=\"#\" class=\"uiAlert_close\" onclick=\"uiAlert.GoNext();return false;\">关闭</a></div>");
            // 创建title
            $Box.append("<div class=\"Uialert_nav\"><div class=\"Uialert_Nleft\"><span class='Uialert_NLicon" + (this.flag ? ' Uialert_info' : ' Uialert_warning') + "'></span></div><div class=\"Uialert_Nright\">" + this.data.nav + "</div></div>");
            // 创建正文
            $Box.append("<div class=\"Uialert_button\"><input type=\"button\" value=\"确定\" onclick=\"uiAlert.GoNext();return false;\" class=\"Uialert_Binput uiAlert_close radius\" /></div>");
            // 创建底部按钮
            $(".Uialert_bj").fadeTo(0, this.data.opacity);
            // 更改遮挡层透明度
            $(".Uialert_Bbox").show();
            $Box.fadeIn(300);
            uiAlert.ChangeCss();
            // 更改css
            this.B_Click();
            // 绑定点击事件
            this.Auto();
            // 绑定自动关闭事件
            uiAlert.next = this.data.next;
            // 光标焦点处于按钮上
            $(".Uialert_Binput").focus();
        };
        uiAlert.prototype.Auto = function() {
            // 自动
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
            // 点击遮挡层
            var z = $(".Uialert_title").css("background-color");
            $(".Uialert_Bbox,#TB_HideSelect body").bind({
                click: function() {
                    // uiAlert.B_Click_(0,5,z);
                    $(".Uialert_Binput").focus();
                }
            });
        };
        uiAlert._initialized = true;
    }
}
uiAlert.Tostring = function(o) {
    // 重组数据
    this.nav = typeof o == "object" ? o.nav.replace(/\n\r/g, "<br />").replace(/\n/g, "<br />").replace(/\r/g, "<br />") : o.replace(/\n\r/g, "<br />").replace(/\n/g, "<br />").replace(/\r/g, "<br />");
    // 替换\n为<br />
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
    // 添加和执行处理
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
    // 更改css
    $(window).resize(uiAlert.ChangeCss);
    // 当window窗口变化时
    if (typeof loading.object == "undefined" || loading.object.type) {
        var $Box = $(".Uialert_box");
        $(".Uialert_Bbox,.Uialert_bj").css({
            "height": $(window).height()
        });
        $Box.css({
            // 重置提示框位置
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
    var userAgent = navigator.userAgent.toLowerCase();
    $.browser = {
        version: (userAgent.match( /.+(?:rv|it|ra|ie)[/: ]([d.]+)/ ) || [])[1],
        safari: /webkit/.test( userAgent ),
        opera: /opera/.test( userAgent ),
        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
        mozilla: /mozilla/.test(userAgent)&&!/(compatible|webkit)/.test(userAgent)
    };
    !$.browser.msie ? "" : ($.browser.version == 6.0 ? uiAlert.scroll() : "");
    // 当ie6时绑定
};
uiAlert.B_Click_ = function(x, y, z) {
    // 更改背景色触发
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
    // 更改背景颜色
    $(".Uialert_title").css({
        "background-color": "rgb(" + uiAlert.B_Click_B() + "," + uiAlert.B_Click_B() + "," + uiAlert.B_Click_B() + ")"
    });
};
uiAlert.B_Click_B = function() {
    // 随机数字_背景色
    return Math.round(Math.random() * 255);
};
uiAlert.Close = function() {
    // 关闭
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
    // 绑定滚动条滚动事件
    if (typeof uiAlert.scroll._init == "undefined") {
        // iframe to hide select elements in ie6
        // $("html").css("overflow","hidden");
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
// 分页
/*
 * count = 总数 page = 当前页码 pagesize = 每页条数 splitsize = 分页前后显示 使用示例: var newpage =
 * new createPage(100,1,15,3); 输出: newpage.minHtml;//第n页/共n页
 * newpage.pageHtml;//共技n条 当前第n/n页 每页n条 页码 重置分页 newpage.resetPage(页码); 输出: 同上
 */
var createPage = function(count, page, pagesize, splitsize) {
        this.count = parseInt((count || 0), 10);
        this.page = page || 1;
        this.pagesize = pagesize || 15;
        this.splitsize = splitsize || 3;
        this.minTemplet = "第{0}页/共{1}页";
        this.pageTemplet = "共计{0}条 当前第{1}/{2}页 每页{3}条 {4}";
        this.minHtml = this.minTemplet;
        this.pageHtml = this.pageTemplet.format(this.count, "{0}", "{1}", this.pagesize, "{2}");
        this.init();
    };
createPage.prototype = {
    Size: function() { // 分页大小
        this.pagelen = parseInt(this.count % this.pagesize > 0 ? this.count / this.pagesize + 1 : this.count / this.pagesize);
        this.validatePage();
        this.initPage();
    },
    initPage: function() { // 重置分页模板
        this.minHtml = this.minTemplet.format(this.page, this.pagelen);
        this.pageHtml = this.pageHtml.format(this.page, this.pagelen, "{0}");
    },
    concat: function() { // 装载分页链接
        var s = this.page - this.splitsize < 1 ? 1 : this.page - this.splitsize,
            e = this.page + this.splitsize > this.pagelen ? this.pagelen : this.page + this.splitsize,
            html = "<ul class=\"pagination\">";
        for (var i = s; i < e + 1; i++) {
            //var et = (i == this.page ? " class=\"active\"" : "");
            if(i == this.page)
            	html += '<li class=\"active\"><a href="javascript:void(0);">' + i + '</a></li>';
            else
            	html += '<li><a href="javascript:void(0);" onclick="pageGo(' + i + ', this);">' + i + '</a></li>';
        }
        html+='</ul>';
        this.pageHtml = this.pageHtml.format(html);
    },
    validatePage: function() { // 验证有效分页
        if (this.page > this.pagelen) {
            this.page = this.pagelen;
        }
    },
    resetPage: function(page) { // 切换分页
        this.pageHtml = this.pageTemplet.format(this.count, "{0}", "{1}", this.pagesize, "{2}");
        this.page = page || this.page;
        this.initPage();
        return this.concat();
    },
    init: function() { // 装载分页
        this.Size();
        return this.concat();
    }
};

var windowIndex = 0,Windows = [null];
function showWindow(title,html,width,height,isUrl,fun,scrolling,flag){// 弹层接口
    var title = title || "",
        html = html || "",
        width = parseInt(width || 300) + "px",
        height = parseInt(height || 100) + "px",
        m_left = (top.$(document).width() - parseInt(width))/2 + "px",
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
}

function hideWindow(){
    Windows[windowIndex].close();
}

var OpenWindow = function(f, e, d, c, a) {
    this.title = f || "";
    this.name = parseInt(Math.random() * 100000);
    this.defaults = {
        left: "300px",
        top: "100px",
        width: "400px",
        height: "400px"
    };
    this.flag = parseInt((a || 0), 10);
    this.style = $.extend(this.defaults, (d || {}));
    this.content = e;
    this.type = typeof type == "undefined" ? "common": type;
    this.height = $(document).height();
    this.width = $(document).width();
    this.fun = c || null;
    this.init = function() {
        var h = $(".owBj").length > 0 ? "none": "block";
        var g = '<div class="owBj" style="display:' + h + ";width:" + this.width + "px;height:" + this.height + 'px; top:0; left:0;"></div>\n\
        <div class="owBox" id="' + this.name + '" style="display:none;"><div class="owTitle">';
            if(this.title != "") g+='<span class="owtName">' + this.title + '</span>';
            g+='<span></span><a href="javascript:void(0);" class="owClose"></a></div>\n\
            <div class="owContent">' + this.content + "</div>\n\
        </div>";
        var i = '<div class="OpenWindow" id="box' + this.name + '">' + g + '</div>';
        $("body").append(i);
        OpenWindow.ArrayW.push(document.getElementById(this.name));
        this.setCss();
        this.startDrag();
        this.setTop();
        this.setCommond()
    };
    this.init();
    return this
};
OpenWindow.ArrayW = new Array();
OpenWindow.Drag = function(d, c) {
    var a = this;
    this.obj = (typeof c != "undefined") ? c: d;
    this.relLeft = 0;
    this.relTop = 0;
    a._move = false;
    a.moveId = "moveWindow";
    d.onselectstart = function() {
        return false
    };
    d.onmousedown = function(g) {
        var f = $(c).offset(),
        h = $('<div class="' + a.moveId + '" style="border:1px dotted #ddd; cursor: move; position: absolute; background:#fff; z-index: 999998;"></div><div id="' + a.moveId + '" class="' + a.moveId + '" style="border:1px dotted #ddd; cursor: move; position: absolute; background:#000; z-index: 999999;"></div>');
        $("body").append(h);
        $("." + a.moveId).css({
            left: f.left,
            top: f.top,
            width: $(c).outerWidth(),
            height: $(c).outerHeight(),
            opacity: 0.5
        });
        g = a.fixE(g);
        a.relLeft = g.clientX - a.fixU(a.obj.style.left);
        a.relTop = g.clientY - a.fixU(a.obj.style.top);
        a._move = true;
        document.onmousemove = function(i) {
            a.drag(i)
        };
        document.onmouseup = function() {
            a.end()
        }
    };
    this.drag = function(h) {
        h = this.fixE(h);
        var f = h.clientX - this.relLeft;
        var g = h.clientY - this.relTop;
        if (g < 0) {
            g = 0
        }
        $("#" + a.moveId).css({
            left: f + "px",
            top: g + "px"
        })
    };
    this.end = function() {
        a._move = false;
        document.onmousemove = null;
        document.onmouseup = null;
        var e = $("#" + a.moveId).offset();
        $(a.obj).css({
            left: e.left + "px",
            top: e.top + "px"
        });
        $("." + a.moveId).remove();
        return false
    };
    this.fixE = function(f) {
        if (typeof f == "undefined") {
            f = window.event
        }
        return f
    };
    this.fixU = function(e) {
        return parseInt(e.split("p")[0])
    }
};
OpenWindow.prototype.setCss = function() {
    var a = document.getElementById(this.name);
    switch (this.flag) {
    case 0:
        if (typeof this.style.top != "undefined") {
            a.style.top = parseInt(this.style.top, 10) < 0 ? "0px": this.style.top
        }
        if (typeof this.style.width != "undefined") {
            a.style.width = this.style.width
        }
        if (typeof this.style.left != "undefined") {
            a.style.left = this.style.left
        }
        break;
    case 1:
        a.style.top = "91px";
        a.style.height = ($(window).height() - 96) + "px";
        a.style.width = "100%";
        a.style.left = "0px";
        $("#" + this.name).find("#minWindow").height((parseInt(a.style.height, 10) - 31) + "px");
        break;
    case 2:
        a.style.top = 0;
        a.style.height = ($(window).height() - 6) + "px";
        a.style.width = "100%";
        a.style.left = "0px";
        $("#" + this.name).find("#minWindow").height((parseInt(a.style.height, 10) - 31) + "px");
        break;
    default:
        break
    }
    this.height = a.style.height;
    this.width = a.style.width;
    $("#" + this.name).fadeIn(500)
};
OpenWindow.prototype.startDrag = function() {
    var a = document.getElementById(this.name);
    new OpenWindow.Drag(a.childNodes[0].childNodes[0], a)
};
OpenWindow.prototype.setTop = function() {
    for (var a = 0; a < OpenWindow.ArrayW.length; a++) {
        OpenWindow.ArrayW[a].style.zIndex = 10004;
    }
    this.style.zIndex = 100;
    document.getElementById(this.name).onclick = document.getElementById(this.name).onmousedown = function() {
        for (var c = 0; c < OpenWindow.ArrayW.length; c++) {
            OpenWindow.ArrayW[c].style.zIndex = 10004;
        }
        this.style.zIndex = 10005;
    }
};
OpenWindow.prototype.close = function(a) {
    var a = a || this.name;
    $("#box" + a).remove();
    top.windowIndex = top.windowIndex - 1 < 0 ? 0 : top.windowIndex - 1;
    if (typeof(this.fun) == "function") {
        this.fun.call(this, {})
    }
};
OpenWindow.prototype.setCommond = function() {
    var a = this,
    c = document.getElementById(this.name);
    c.childNodes[0].getElementsByTagName("a")[0].onclick = function() {
        a.close();
        return false
    }
};
OpenWindow.prototype.getValue = function() {
    return this.content
};
OpenWindow.prototype.setValue = function(a) {
    this.content = Vlaue;
    this.setContent()
};

function showLayer(dataUrl, width, iframe, scrolling){
    $.nsWindow.open({width: width, dataUrl: dataUrl, iframe:iframe, scrolling: scrolling});
}

function closeLayer(){
    $.nsWindow.close();
}

(function ($, window, undefined) {
    $.nsWindow = {
        name: 'nsWindow',
        window: null,
        options: null,
        bind: function (type, callback) {
            $(document).bind(type, callback);
            return this;
        },
        unbind: function (type) {
            $(document).unbind(type);
            return this;
        },
        open: function (options) {
            this.options = $.extend(true, {
                right: 0,
                zIndex: 1000,
                animate: true,
                dataUrl: null,
                iframe: false,
                scrolling: false,
                load: true
            }, options);
            var opt = this.options, self = this;            
            this.bind($.nsWindowEvent.onOpen, _openAndCloseHandler).bind($.nsWindowEvent.onClose, _openAndCloseHandler);
            var body = $(document.body);
            if (body.find('> .nswindowContainer').length <= 0){
                var pg = _createOverlay(opt, body);
                this.window = $('<div>').attr('class', 'nswindowContent').get(0);
                pg.append(this.window);
                pg.prepend("<a class=\"close\" title=\"关闭\" href=\"javascript:closeLayer();\">关闭</a>");
            }
            /*
			 * $(".nswindowContainer").outClick({ click:function(){
			 * closeLayer(); } });
			 */

            _trigger($.nsWindowEvent.onOpen, this);

            return this;
        },
        close: function (option) {
            var me = this;
            var o = $.extend({
                delay: 0
            }, option);

            if (o.delay > 0) {
                setTimeout(_t, o.delay);
            } else {
                _t();
            }

            function _t() {
                _trigger($.nsWindowEvent.onClose, me);
            }

        }
    }
    $.nsWindowEvent = {
        onOpen: $.nsWindow.name + 'OnOpen',
        onClose: $.nsWindow.name + 'OnClose'
    }

    function _createOverlay(opt, body) {
        var pg = $('<div>');
        pg.addClass('nswindowContainer').css({
            zIndex: opt.zIndex + 10
        });
        body.append(pg);
        return pg;
    }

    function _trigger(type, context) {
        $(document).trigger(jQuery.Event(type, {
            context: context,
            window: context.window,
            dataContainer: $(context.window).get(0),
            options: context.options
        }));
    }

    function _openAndCloseHandler(event) {
        if (typeof event != 'undefined' && event.isDefaultPrevented()) {
            return;
        }
        var opt = event.options, win = $(event.window).parent();
        win.css({"width":opt.width,"height":$(window).outerHeight()});
        
        opt.right = opt.right == 0 ? win.width() : opt.right;
        var css1 = {
            right: -opt.right + 'px',
            opacity: 0.0
        }, css2 = {
            right: 0,
            opacity: 1.0
        }
        if (event.type == $.nsWindowEvent.onOpen) {
            if (opt.animate == true) {
                win.css(css1).stop().animate(css2, {
                    duration: 400,
                    easing: 'easeInOutBack',
                    complete: function (e) {
                        _configWindow(event);
                    }
                });
            }
        } else {
            if (opt.animate == true) {
                win.stop().animate(css1, {
                    duration: 400,
                    easing: 'easeInOutBack',
                    complete: function () {
                        opt.right = 0;
                        _closeWindow(event);
                    }
                });
            }
            $.nsWindow.unbind($.nsWindowEvent.onOpen).unbind($.nsWindowEvent.onClose);
        }
    }

    function _configWindow(event) {
        var opt = event.options, win = $(event.window), height = $(window).outerHeight() - 14;
        var body = $(document.body);
        if (_isEmpty(opt.dataUrl)) {
            return;
        }

        if(opt.iframe){
            var scrolling = opt.scrolling?"yes":"no";
            event.dataContainer.innerHTML = html = '<iframe width="100%" name="minWindow" id="minWindow" height="' + height + '" src="' + opt.dataUrl + '" frameborder="0" scrolling="'+scrolling+'"></iframe>';
        }else{
            // if (opt.dataUrl.indexOf("?") >= 0) opt.dataUrl =
			// opt.dataUrl+"&t="+Math.random(); else opt.dataUrl =
			// opt.dataUrl+"?t="+Math.random();
            $.ajax({
                url: opt.dataUrl,
                dataType: 'html',
                cache: false,
                success: function (data) {
                    $(event.dataContainer).html(data);
                }
            });
        }
    }

    function _closeWindow(event) {
        var body = $(document.body);
        $(event.window).removeAttr('style').removeAttr('class').html('');
        body.find('.nswindowContainer').hide();
        body.find('.nswindowContainer').remove();
    }

    function _isEmpty(value) {
        return typeof value == 'undefined' || value == null || value == 'null' || $.trim(value) == ''
    }

    $.jCarouselLite = {
        version: '1.1'
    };

    $.fn.jCarouselLite = function(options) {
        options = $.extend({}, $.fn.jCarouselLite.options, options || {});
        return this.each(function() {   // Returns the element collection.
										// Chainable.

            var running,
                animCss, sizeCss,
                div = $(this), ul, initialLi, li,
                liSize, ulSize, divSize,
                numVisible, initialItemLength, itemLength, calculatedTo, autoTimeout;

            initVariables();                    // Set the above variables after
												// initial calculations
            initStyles();                       // Set the appropriate styles
												// for the carousel div, ul and
												// li
            initSizes();                        // Set appropriate sizes for the
												// carousel div, ul and li
            attachEventHandlers();              // Attach event handlers for
												// carousel to respond

            function go(to) {
                if(!running) {
                    clearTimeout(autoTimeout);  // Prevents multiple clicks
												// while auto-scrolling - edge
												// case
                    calculatedTo = to;

                    if(options.beforeStart) {   // Call the beforeStart()
												// callback
                        options.beforeStart.call(this, visibleItems());
                    }

                    if(options.circular) {      // If circular, and "to" is
												// going OOB, adjust it
                        adjustOobForCircular(to);
                    } else {                    // If non-circular and "to" is
												// going OOB, adjust it.
                        adjustOobForNonCircular(to);
                    }                           // If neither overrides
												// "calculatedTo", we are not in
												// edge cases.

                    animateToPosition({         // Animate carousel item to
												// position based on calculated
												// values.
                        start: function() {
                            running = true;
                        },
                        done: function() {
                            if(options.afterEnd) {
                                options.afterEnd.call(this, visibleItems());
                            }
                            if(options.auto) {
                                setupAutoScroll();
                            }
                            running = false;
                        }
                    });

                    if(!options.circular) {     // Enabling / Disabling buttons
												// is applicable in non-circular
												// mode only.
                        disableOrEnableButtons();
                    }

                }
                return false;
            }

            function initVariables() {
                running = false;
                animCss = options.vertical ? "top" : "left";
                sizeCss = options.vertical ? "height" : "width";
                ul = div.find(">ul");
                initialLi = ul.find(">li");
                initialItemLength = initialLi.size();

                // To avoid a scenario where number of items is just 1 and
				// visible is 3 for example.
                numVisible = initialItemLength < options.visible ? initialItemLength : options.visible;

                if(options.circular) {
                    var $lastItemSet = initialLi.slice(initialItemLength-numVisible).clone();
                    var $firstItemSet = initialLi.slice(0,numVisible).clone();

                    ul.prepend($lastItemSet)        // Prepend the lis with
													// final items so that the
													// user can click the back
													// button to start with
                        .append($firstItemSet);     // Append the lis with first
													// items so that the user
													// can click the next button
													// even after reaching the
													// end

                    options.start += numVisible;    // Since we have a few
													// artificial lis in the
													// front, we will have to
													// move the pointer to point
													// to the real first item
                }

                li = $("li", ul);
                itemLength = li.size();
                calculatedTo = options.start;
            }

            function initStyles() {
                div.css("visibility", "visible");   // If the div was set to
													// hidden in CSS, make it
													// visible now

                li.css({
                    overflow: "hidden",
                    "float": options.vertical ? "none" : "left" // Some
																// minification
																// tools fail if
																// "" is not
																// used
                });

                ul.css({
                    margin: "0",
                    padding: "0",
                    position: "relative",
                    "list-style": "none",
                    "z-index": "1"
                });

                div.css({
                    overflow: "hidden",
                    position: "relative",
                    "z-index": "2",
                    left: "0px"
                });

                // For a non-circular carousel, if the start is 0 and btnPrev is
				// supplied, disable the prev button
                if(!options.circular && options.btnPrev && options.start == 0) {
                    $(options.btnPrev).addClass("disabled");
                }
                if(!options.circular && options.btnNext && itemLength <= options.visible) {
                	$(options.btnNext).addClass("disabled");
                }
            }

            function initSizes() {

                liSize = options.vertical ?         // Full li size(incl
													// margin)-Used for
													// animation and to set
													// ulSize
                    li.outerHeight(true) :
                    li.outerWidth(true);
                ulSize = liSize * itemLength;       // size of full ul(total
													// length, not just for the
													// visible items)
                divSize = liSize * numVisible;      // size of entire div(total
													// length for just the
													// visible items)

                // Generally, LI's dimensions should be specified explicitly in
				// a style-sheet
                // But in the case of img (with width and height attr), we can
				// derive LI's dimensions and set here
                // May be applicable for other types of LI children if their
				// dimensions are explicitly specified
                // Individual LI dimensions
                li.css({
                    width: li.width(),
                    height: li.height()
                });

                // Size of the entire UL. Including hidden and visible elements
                // Will include LI's (width + padding + border + margin) *
				// itemLength - Using outerwidth(true)
                ul.css(sizeCss, ulSize+"px")
                    .css(animCss, -(calculatedTo * liSize));

                // Width of the DIV. Only the width of the visible elements
                // Will include LI's (width + padding + border + margin) *
				// numVisible - Using outerwidth(true)
                div.css(sizeCss, divSize+"px");

            }

            function attachEventHandlers() {
                if(options.btnPrev) {
                    $(options.btnPrev).click(function() {
                        return go(calculatedTo - options.scroll);
                    });
                }

                if(options.btnNext) {
                    $(options.btnNext).click(function() {
                        return go(calculatedTo + options.scroll);
                    });
                }

                if(options.btnGo) {
                    $.each(options.btnGo, function(i, val) {
                        $(val).click(function() {
                            return go(options.circular ? numVisible + i : i);
                        });
                    });
                }

                if(options.mouseWheel && div.mousewheel) {
                    div.mousewheel(function(e, d) {
                        return d > 0 ?
                            go(calculatedTo - options.scroll) :
                            go(calculatedTo + options.scroll);
                    });
                }

                if(options.auto) {
                    setupAutoScroll();
                }
            }

            function setupAutoScroll() {
                autoTimeout = setTimeout(function() {
                    go(calculatedTo + options.scroll);
                }, options.auto);
            }

            function visibleItems() {
                return li.slice(calculatedTo).slice(0,numVisible);
            }

            function adjustOobForCircular(to) {
                var newPosition;

                // If first, then goto last
                if(to <= options.start - numVisible - 1) {
                    newPosition = to + initialItemLength + options.scroll;
                    ul.css(animCss, -(newPosition * liSize) + "px");
                    calculatedTo = newPosition - options.scroll;

                    //console.log("Before - Positioned at: " + newPosition + " and Moving to: " + calculatedTo);
                }

                // If last, then goto first
                else if(to >= itemLength - numVisible + 1) {
                    newPosition = to - initialItemLength - options.scroll;
                    ul.css(animCss, -(newPosition * liSize) + "px");
                    calculatedTo = newPosition + options.scroll;

                    //console.log("After - Positioned at: " + newPosition + " and Moving to: " + calculatedTo);
                }
            }

            function adjustOobForNonCircular(to) {
                // If user clicks "prev" and tries to go before the first
				// element, reset it to first element.
                if(to < 0) {
                    calculatedTo = 0;
                }
                // If "to" is greater than the max index that we can use to show
				// another set of elements
                // it means that we will have to reset "to" to a smallest
				// possible index that can show it
                else if(to > itemLength - numVisible) {
                    calculatedTo = itemLength - numVisible;
                }

                /*console.log("Item Length: " + itemLength + "; " +
                    "To: " + to + "; " +
                    "CalculatedTo: " + calculatedTo + "; " +
                    "Num Visible: " + numVisible);*/
            }

            function disableOrEnableButtons() {
                $(options.btnPrev + "," + options.btnNext).removeClass("disabled");
                $( (calculatedTo-options.scroll<0 && options.btnPrev)
                    ||
                    (calculatedTo+options.scroll > itemLength-numVisible && options.btnNext)
                    ||
                    []
                ).addClass("disabled");
            }

            function animateToPosition(animationOptions) {
                running = true;

                ul.animate(
                    animCss == "left" ?
                    { left: -(calculatedTo*liSize) } :
                    { top: -(calculatedTo*liSize) },

                    $.extend({
                        duration: options.speed,
                        easing: options.easing
                    }, animationOptions)
                );
            }
        });
    };
    $.fn.jCarouselLite.options = {
        btnPrev: null,              // CSS Selector for the previous button
        btnNext: null,              // CSS Selector for the next button
        btnGo: null,                // CSS Selector for the go button
        mouseWheel: false,          // Set "true" if you want the carousel
									// scrolled using mouse wheel
        auto: null,                 // Set to a numeric value (800) in millis.
									// Time period between auto scrolls

        speed: 200,                 // Set to a numeric value in millis. Speed
									// of scroll
        easing: null,               // Set to easing (bounceout) to specify the
									// animation easing

        vertical: false,            // Set to "true" to make the carousel scroll
									// vertically
        circular: true,             // Set to "true" to make it an infinite
									// carousel
        visible: 3,                 // Set to a numeric value to specify the
									// number of visible elements at a time
        start: 0,                   // Set to a numeric value to specify which
									// item to start from
        scroll: 1,                  // Set to a numeric value to specify how
									// many items to scroll for one scroll event

        beforeStart: null,          // Set to a function to receive a callback
									// before every scroll start
        afterEnd: null              // Set to a function to receive a callback
									// after every scroll end
    };
})(jQuery, window);

jQuery.easing.jswing = jQuery.easing.swing;
jQuery.extend( jQuery.easing,{
    def: 'easeOutQuad',
    swing: function (x, t, b, c, d) {
        // alert(jQuery.easing.default);
        return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
    },
    easeInQuad: function (x, t, b, c, d) {
        return c*(t/=d)*t + b;
    },
    easeOutQuad: function (x, t, b, c, d) {
        return -c *(t/=d)*(t-2) + b;
    },
    easeInOutQuad: function (x, t, b, c, d) {
        if ((t/=d/2) < 1) return c/2*t*t + b;
        return -c/2 * ((--t)*(t-2) - 1) + b;
    },
    easeInCubic: function (x, t, b, c, d) {
        return c*(t/=d)*t*t + b;
    },
    easeOutCubic: function (x, t, b, c, d) {
        return c*((t=t/d-1)*t*t + 1) + b;
    },
    easeInOutCubic: function (x, t, b, c, d) {
        if ((t/=d/2) < 1) return c/2*t*t*t + b;
        return c/2*((t-=2)*t*t + 2) + b;
    },
    easeInQuart: function (x, t, b, c, d) {
        return c*(t/=d)*t*t*t + b;
    },
    easeOutQuart: function (x, t, b, c, d) {
        return -c * ((t=t/d-1)*t*t*t - 1) + b;
    },
    easeInOutQuart: function (x, t, b, c, d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
        return -c/2 * ((t-=2)*t*t*t - 2) + b;
    },
    easeInQuint: function (x, t, b, c, d) {
        return c*(t/=d)*t*t*t*t + b;
    },
    easeOutQuint: function (x, t, b, c, d) {
        return c*((t=t/d-1)*t*t*t*t + 1) + b;
    },
    easeInOutQuint: function (x, t, b, c, d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
        return c/2*((t-=2)*t*t*t*t + 2) + b;
    },
    easeInSine: function (x, t, b, c, d) {
        return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
    },
    easeOutSine: function (x, t, b, c, d) {
        return c * Math.sin(t/d * (Math.PI/2)) + b;
    },
    easeInOutSine: function (x, t, b, c, d) {
        return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
    },
    easeInExpo: function (x, t, b, c, d) {
        return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
    },
    easeOutExpo: function (x, t, b, c, d) {
        return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
    },
    easeInOutExpo: function (x, t, b, c, d) {
        if (t==0) return b;
        if (t==d) return b+c;
        if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
        return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
    },
    easeInCirc: function (x, t, b, c, d) {
        return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
    },
    easeOutCirc: function (x, t, b, c, d) {
        return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
    },
    easeInOutCirc: function (x, t, b, c, d) {
        if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
        return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
    },
    easeInElastic: function (x, t, b, c, d) {
        var s=1.70158;var p=0;var a=c;
        if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
        if (a < Math.abs(c)) { a=c; var s=p/4; }
        else var s = p/(2*Math.PI) * Math.asin (c/a);
        return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
    },
    easeOutElastic: function (x, t, b, c, d) {
        var s=1.70158;var p=0;var a=c;
        if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
        if (a < Math.abs(c)) { a=c; var s=p/4; }
        else var s = p/(2*Math.PI) * Math.asin (c/a);
        return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
    },
    easeInOutElastic: function (x, t, b, c, d) {
        var s=1.70158;var p=0;var a=c;
        if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
        if (a < Math.abs(c)) { a=c; var s=p/4; }
        else var s = p/(2*Math.PI) * Math.asin (c/a);
        if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
        return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
    },
    easeInBack: function (x, t, b, c, d, s) {
        if (s == undefined) s = 1.70158;
        return c*(t/=d)*t*((s+1)*t - s) + b;
    },
    easeOutBack: function (x, t, b, c, d, s) {
        if (s == undefined) s = 1.70158;
        return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
    },
    easeInOutBack: function (x, t, b, c, d, s) {
        if (s == undefined) s = 1.70158;
        if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
        return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
    },
    easeInBounce: function (x, t, b, c, d) {
        return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;
    },
    easeOutBounce: function (x, t, b, c, d) {
        if ((t/=d) < (1/2.75)) {
            return c*(7.5625*t*t) + b;
        } else if (t < (2/2.75)) {
            return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
        } else if (t < (2.5/2.75)) {
            return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
        } else {
            return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
        }
    },
    easeInOutBounce: function (x, t, b, c, d) {
        if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
        return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
    }
});

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
    var userAgent = navigator.userAgent.toLowerCase();
    // Figure out what browser is being used
    jQuery.browser = {
        version: (userAgent.match( /.+(?:rv|it|ra|ie)[/: ]([d.]+)/ ) || [])[1],
        safari: /webkit/.test( userAgent ),
        opera: /opera/.test( userAgent ),
        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
        mozilla: /mozilla/.test(userAgent)&&!/(compatible|webkit)/.test(userAgent)
    };

    // prevent form submit in opera when selecting with return key
    jQuery.browser.opera && jQuery(input.form).bind("submit.autocomplete", function() {
        if (blockSubmit) {
            blockSubmit = false;
            return false;
        }
    });

    // only opera doesn't trigger keydown multiple times while pressed, others
	// don't work with keypress at all
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
                    // stop default to prevent a form submit, Opera needs
					// special handling
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
        if($(".ac_results").length > 0){
            var parents = $(jQueryinput).parent().parents();
            $(".ac_results",parents).parent().remove();
        }
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
            clearTimeout(timeout);
            timeout = setTimeout(function(){
                onChange(0, true, false);
            }, options.delay);
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

    function onChange(crap, skipPrevCheck, status) {
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
        } else if(status == false ||  options.clickStatus){
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
        // autofill in the complete box w/the first match as long as the user
		// hasn't entered in more data
        // if the last user key pressed was backspace, don't autofill
        if (options.autoFill && (lastWord(jQueryinput.val()).toLowerCase() == q.toLowerCase()) && lastKeyPressCode != KEY.BACKSPACE) {
            // fill in the value (keep the case the user has typed)
            jQueryinput.val(jQueryinput.val() + sValue.substring(lastWord(previousValue).length));
            // select the portion of the value not typed by the user (so the
			// next character will erase)
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
        /*
		 * if (data && data.length) { success(term, data); cache.add(term,
		 * parsed); // if an AJAX url has been supplied, try loading the data
		 * now } else
		 */
        if ((typeof options.url == "string") && (options.url.length > 0)) {
            var extraParams = {
                timestamp: +new Date()
            };
            // options.extraParams[input.name||"q"] =
			// encodeURIComponent($.trim(term));
            options.extraParams["q"] = encodeURIComponent($.trim(term));
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
                type:"POST",
                data: jQuery.extend({
                    limit: options.max
                }, extraParams),
                success: function(data) {
                    var parsed = options.parse && options.parse(data) || parse(data);
                    cache.add(term, parsed);
                    success(term, parsed);
                }
            });
        } else {
            // if we have a failure, we need to empty the list -- this prevents
			// the the [TAB] key from selecting the last successful match
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

        // no url was specified, we need to adjust the cache length to make sure
		// it fits the local data store
        if (!options.url) options.cacheLength = 1;

        // track all options for minChars = 0
        stMatchSets[""] = [];

        // loop through the array and create a lookup structure
        for (var i = 0, ol = options.data.length; i < ol; i++) {
            var rawValue = options.data[i];
            // if rawValue is a string, make an array otherwise just reference
			// the array
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
                    // don't search through the stMatchSets[""] (minChars: 0)
					// cache
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
            } else {
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
        // if (!needsInit) return;
        var parentInput = $(input).parent();
        parentInput.before("<div/>");
        parentInput.prev().addClass("relative inlineBlock");
        element = jQuery("<div/>").hide().addClass(options.resultsClass).css("position", "absolute").appendTo(parentInput.prev());

        list = jQuery("<ul/>").appendTo(element).mouseover(function(event) {
            if (target(event).nodeName && target(event).nodeName.toUpperCase() == 'LI') {
                active = jQuery("li", list).removeClass(CLASSES.ACTIVE).index(target(event));
                jQuery(target(event)).addClass(CLASSES.ACTIVE);
            }
        }).click(function(event) {
            jQuery(target(event)).addClass(CLASSES.ACTIVE);
            select();
            // TODO provide option to avoid setting focus again after selection?
			// useful for cleanup-on-focus
            input.focus();
            $(input).next("label").hide();
            if(options.tipError){
	            var tipsMsg = "<span class=\"dataError\"><a href=\"javascript:resetInput(1);\">我要纠错</a></span>";
	            if($(".dataError").length <= 0)
	            	parentInput.parent().append(tipsMsg);
	            else
	            	$(".dataError").html(tipsMsg);
            }
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
        // more fun with IE, sometimes event.target is empty, just ignore it
		// then
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
            var offset = jQuery(input).height() - 4;
            element.css({
                width: typeof options.width == "string" || options.width > 0 ? options.width : jQuery(input).width(),
                top: offset,
                left: 0
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
                        // IE doesn't recalculate width when scrollbar
						// disappears
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

(function(){
    // alert重置
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

    // 获取查询字符串在数组中的位置
    Array.prototype.indexOf = function(val) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) {
                return i;
            }
        }
        return -1;
    };
    // 移除数组中的字符窜
    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
        return this;
    };
    // 格式化html
    String.prototype.toText = function() {
        var str = this;
        str = str.replace(/</g, "&lt;");
        str = str.replace(/>/g, "&gt;");
        str = str.replace(/\n\r/g, "<br />");
        str = str.replace(/\n/g, "<br />");
        str = str.replace(/\r/g, "<br />");
        return str;
    };
    String.prototype.toHtml = function() {
        var str = this;
        str = str.replace(/&lt;/g, "<");
        str = str.replace(/&gt;/g, ">");
        str = str.replace(/\n\r/g, "<br />");
        str = str.replace(/\n/g, "<br />");
        str = str.replace(/\r/g, "<br />");
        return str;
    };
    // 把字符串转换成json格式
    String.prototype.toJSON = function() {
        return new Function("return " + this.toText())();
    };
    // 金钱格式1,四舍五入保留2位小数点
    String.prototype.formatMoney = function(s) { // s = 分隔符,默认为空
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
    // 金钱格式2,小数点后保持原来不变
    String.prototype.toMoney = function(s) { // s = 分隔符,默认为空
        var str = this.toString().replace(/[^\d.]/g, "").split(".");
        str.length = 2, s = s || "", num = ((str[0] || 0) * 1000 / 1000).toString();
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
    // 时间格式化
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
    // 根据传入数字返回前几天时间
    Date.prototype.addDate = function(format) {
        var format = parseInt(format, 10) || 0,
            time = 0;
        time = this.getTime() + (format * 60 * 60 * 24 * 1000);
        return new Date(time);
    };
    // 时间差
    String.prototype.dateDiff = function(date, interval) { // 时间格式字符串差
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
    String.prototype.isEmpty = function() {
        var s = this.trim();
        return s.length == 0;
    };
    // 验证字符串是否为纯数字
    String.prototype.isNumber = function() {
        return !isNaN(this);
    };
    // 手机格式验校
    String.prototype.IsTelephone = function() {
        var mobile = this;
        return /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/.test(mobile);
    };
    String.prototype.isTel = function() {
        return /^(1[3,5,8,7]{1}[\d]{9})|(((400)-(\d{3})-(\d{4}))|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{3,7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/.test(this.trim());
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
})(jQuery);

(function($) {
	$.fn.jdMarquee = function(option, callback) {
        if (typeof option == "function") {
            callback = option;
            option = {};
        };
        var s = $.extend({
            deriction: "up",
            speed: 10,
            auto: false,
            width: null,
            height: null,
            step: 1,
            showId: "#yui-max-pic",
            control: false,
            _front: null,
            _back: null,
            _stop: null,
            _continue: null,
            wrapstyle: "",
            stay: 5000,
            current:1,
            index:0,
            delay: 20,
            dom: "div>ul>li".split(">"),
            mainTimer: null,
            subTimer: null,
            tag: false,
            convert: false,
            btn: null,
            disabled: "disabled",
            pos: {
                ojbect: null,
                clone: null
            }
        },
        option || {});
        var object = this.find(s.dom[1]);
        var subObject = this.find(s.dom[2]);
        var clone;
        if (s.deriction == "up" || s.deriction == "down") {
            var height = object.eq(0).outerHeight();
            var step = s.step * subObject.eq(0).outerHeight();
            object.css({
                width: s.width + "px",
                overflow: "hidden"
            });
        };
        if (s.deriction == "left" || s.deriction == "right") {
            var width = subObject.length * subObject.eq(0).outerWidth();
            object.css({
                width: width + "px",
                overflow: "hidden"
            });
            var step = s.step * subObject.eq(0).outerWidth();
        };
        
        var init = function() {
            var wrap = "<div style='position:relative;overflow:hidden;z-index:1;width:" + s.width + "px;height:" + s.height + "px;" + s.wrapstyle + "'></div>";
            object.css({
                position: "absolute",
                left: 0,
                top: 0
            }).wrap(wrap);
            s.pos.object = 0;
            clone = object.clone();
            //object.after(clone);
            switch (s.deriction) {
            default:
            case "up":
                object.css({
                    marginTop: 0
                });
                clone.css({
                    marginTop: height + "px"
                });
                s.pos.clone = height;
                break;
            case "down":
                object.css({
                    marginLeft:
                    0,
                    marginTop: 0
                });
                clone.css({
                    marginLeft: 0,
                    marginTop: -height + "px"
                });
                s.pos.clone = -height;
                break;
            case "left":
                object.css({
                    marginTop:
                    0,
                    marginLeft: 0
                });
                clone.css({
                    marginTop: 0,
                    marginLeft: width + "px"
                });
                s.pos.clone = width;
                break;
            case "right":
                object.css({
                    marginTop:
                    0,
                    marginLeft: 0
                });
                clone.css({
                    marginTop: 0,
                    marginLeft: -width + "px"
                });
                s.pos.clone = -width;
                break;
            };
            if (s.auto) {
                initMainTimer();
                object.hover(function() {
                    clear(s.mainTimer);
                },
                function() {
                    initMainTimer();
                });
                clone.hover(function() {
                    clear(s.mainTimer);
                },
                function() {
                    initMainTimer();
                });
            };
            if (callback) {
                callback();
            };
            if (s.control) {
                initControls();
            }
        };
        var clickImg = function(obj){
        	var img = $(obj).find("img");
        	if(img.data("ext") == ".pdf" || img.data("ext") == ".PDF"){
        		window.open(img.attr("src"));
        		return false;
        	}
        	if(s.index == subObject.length - 1)
                $("#next").hide();
            else
                $("#next").show();
        	if(s.index == 0)
                $("#foward").hide();
            else
                $("#foward").show();
            subObject.find("img").removeClass("active");
            $(obj).find("img").addClass("active");
            $(s.showId).html("<img src=\""+img.attr("src")+"\" id=\"yui-max-img\" />");
            $("#yui-cert-time").html(img.data("time"));
        }
        var initMainTimer = function(delay) {
            clear(s.mainTimer);
            s.stay = delay ? delay: s.stay;
            s.mainTimer = setInterval(function() {
                initSubTimer()
            },
            s.stay);
        };
        var initSubTimer = function() {
            clear(s.subTimer);
            s.subTimer = setInterval(function() {
                roll()
            },
            s.delay);
        };
        var clear = function(timer) {
            if (timer != null) {
                clearInterval(timer);
            }
        };
        var disControl = function(A) {
            if (A) {
                $(s._front).unbind("click");
                $(s._back).unbind("click");
                $(s._stop).unbind("click");
                $(s._continue).unbind("click");
            } else {
                initControls();
            }
        };
        var initControls = function() {
            if (s._front != null) {
                $(s._front).click(function() {
                    $(s._front).addClass(s.disabled);
                    disControl(true);
                    clear(s.mainTimer);
                    s.convert = true;
                    s.btn = "front";
                    if (!s.auto) {
                        s.tag = true;
                    };
                    convert();
                });
            };
            if (s._back != null) {
                $(s._back).click(function() {
                    $(s._back).addClass(s.disabled);
                    disControl(true);
                    clear(s.mainTimer);
                    s.convert = true;
                    s.btn = "back";
                    if (!s.auto) {
                        s.tag = true;
                    };
                    convert();
                });
            };
            if (s._stop != null) {
                $(s._stop).click(function() {
                    clear(s.mainTimer);
                });
            };
            if (s._continue != null) {
                $(s._continue).click(function() {
                    initMainTimer();
                });
            }
        };
        var convert = function() {
            if (s.tag && s.convert) {
                s.convert = false;
                var count = Math.ceil(subObject.length/s.speed);
                if (s.btn == "front") {
                    if (s.deriction == "down") {
                        s.deriction = "up";
                    };
                    if (s.deriction == "right") {
                        s.deriction = "left";
                    }
                    s.current++;
                    if(s.current < count){
                        $(s._front).show();
                        $(s._back).hide();
                    }else{
                        $(s._front).hide();
                        $(s._back).show();
                    }
                };
                if (s.btn == "back") {
                    if (s.deriction == "up") {
                        s.deriction = "down";
                    };
                    if (s.deriction == "left") {
                        s.deriction = "right";
                    }
                    s.current--;
                    if(s.current > 1 && s.current <= count){
                        $(s._back).show();
                        $(s._front).hide();
                    }else{
                        $(s._back).hide();
                        $(s._front).show();
                    }
                };
                if (s.auto) {
                    initMainTimer();
                }else{
                    initMainTimer(4 * s.delay);
                }
            }
        };
        var setPos = function(y1, y2, x) {
            if (x) {
                clear(s.subTimer);
                s.pos.object = y1;
                s.pos.clone = y2;
                s.tag = true;
            } else {
                s.tag = false;
            };
            if (s.tag) {
                if (s.convert) {
                    convert();
                } else {
                    if (!s.auto) {
                        clear(s.mainTimer);
                    }
                }
            };
            if (s.deriction == "up" || s.deriction == "down") {
                object.css({
                    marginTop: y1 + "px"
                });
                clone.css({
                    marginTop: y2 + "px"
                });
            };
            if (s.deriction == "left" || s.deriction == "right") {
                object.css({
                    marginLeft: y1 + "px"
                });
                clone.css({
                    marginLeft: y2 + "px"
                });
            }
        };
        var roll = function() {
            var y_object = (s.deriction == "up" || s.deriction == "down") ? parseInt(object.get(0).style.marginTop) : parseInt(object.get(0).style.marginLeft);
            var y_clone = (s.deriction == "up" || s.deriction == "down") ? parseInt(clone.get(0).style.marginTop) : parseInt(clone.get(0).style.marginLeft);
            var y_add = Math.max(Math.abs(y_object - s.pos.object), Math.abs(y_clone - s.pos.clone));
            var y_ceil = Math.ceil((step - y_add) / s.speed);
            switch (s.deriction) {
            case "up":
                if (y_add == step) {
                    setPos(y_object, y_clone, true);
                    $(s._front).removeClass(s.disabled);
                    disControl(false);
                } else {
                    if (y_object <= -height) {
                        y_object = y_clone + height;
                        s.pos.object = y_object;
                    };
                    if (y_clone <= -height) {
                        y_clone = y_object + height;
                        s.pos.clone = y_clone;
                    };
                    setPos((y_object - y_ceil), (y_clone - y_ceil));
                };
                break;
            case "down":
                if (y_add == step) {
                    setPos(y_object, y_clone, true);
                    $(s._back).removeClass(s.disabled);
                    disControl(false);
                } else {
                    if (y_object >= height) {
                        y_object = y_clone - height;
                        s.pos.object = y_object;
                    };
                    if (y_clone >= height) {
                        y_clone = y_object - height;
                        s.pos.clone = y_clone;
                    };
                    setPos((y_object + y_ceil), (y_clone + y_ceil));
                };
                break;
            case "left":
                if (y_add == step) {
                    setPos(y_object, y_clone, true);
                    $(s._front).removeClass(s.disabled);
                    disControl(false);
                } else {
                    if (y_object <= -width) {
                        y_object = y_clone + width;
                        s.pos.object = y_object;
                    };
                    if (y_clone <= -width) {
                        y_clone = y_object + width;
                        s.pos.clone = y_clone;
                    };
                    setPos((y_object - y_ceil), (y_clone - y_ceil));
                };
                break;
            case "right":
                if (y_add == step) {
                    setPos(y_object, y_clone, true);
                    $(s._back).removeClass(s.disabled);
                    disControl(false);
                } else {
                    if (y_object >= width) {
                        y_object = y_clone - width;
                        s.pos.object = y_object;
                    };
                    if (y_clone >= width) {
                        y_clone = y_object - width;
                        s.pos.clone = y_clone;
                    };
                    setPos((y_object + y_ceil), (y_clone + y_ceil));
                };
                break;
            }
        };
        
        clickImg(subObject.eq(s.index));
        subObject.bind("click", function(){
            s.index = $(this).index();
            clickImg($(this));
            
        });
        if(subObject.length > s.speed) $(s._front).show();
        var bClick = $(s.showId).next();
        bClick.find("#foward").bind("click",function(){
            s.index == 0?0:s.index--;
            var count = Math.ceil(subObject.length/s.speed);
            if(s.current > 1 && s.current <= count && parseInt(parseInt(s.index+1)*parseInt(s.current)) == parseInt(s.current)*parseInt(s.speed)){
                s.deriction = "down";
                initMainTimer(4 * s.delay);
                s.current--;
                $(s._front).show();
                $(s._back).hide();
            }
            if(s.index < subObject.length - 1) bClick.find("#next").show();
            clickImg(subObject.eq(s.index));
        });
        bClick.find("#next").bind("click",function(){
            s.index<subObject.length-1?s.index++:subObject.length;
            var count = Math.ceil(subObject.length/s.speed);
            if(s.current < count && parseInt(parseInt(s.index)*parseInt(s.current)) == parseInt(s.current)*parseInt(s.speed)){
                s.deriction = "up";
                initMainTimer(4 * s.delay);
                s.current++;
                $(s._back).show();
                $(s._front).hide();
            }
            if(s.index > 0) bClick.find("#foward").show();
            if(s.index == subObject.length - 1){
                $(this).hide();
            }else{
                $(this).show();
            }
            clickImg(subObject.eq(s.index));
        });
        if (s.deriction == "up" || s.deriction == "down") {
            if (height >= s.height && height >= s.step) {
                init();
            }
        };
        if (s.deriction == "left" || s.deriction == "right") {
            if (width >= s.width && width >= s.step) {
                init();
            }
        }
    }

    $.fn.rotate = function(angle,whence) {
        var p = this.get(0);

        // we store the angle inside the image tag for persistence
        if (!whence) {
            p.angle = ((p.angle==undefined?0:p.angle) + angle) % 360;
        } else {
            p.angle = angle;
        }

        if (p.angle >= 0) {
            var rotation = Math.PI * p.angle / 180;
        } else {
            var rotation = Math.PI * (360+p.angle) / 180;
        }
        var costheta = Math.round(Math.cos(rotation) * 1000) / 1000;
        var sintheta = Math.round(Math.sin(rotation) * 1000) / 1000;
        // alert(costheta+","+sintheta);
     
        if (document.all && !window.opera) {
            var canvas = document.createElement('img');

            canvas.src = p.src;
            canvas.height = p.height;
            canvas.width = p.width;

            canvas.style.filter = "progid:DXImageTransform.Microsoft.Matrix(M11="+costheta+",M12="+(-sintheta)+",M21="+sintheta+",M22="+costheta+",SizingMethod='auto expand')";
        } else {
            var canvas = document.createElement('canvas');
            if (!p.oImage) {
                canvas.oImage = new Image();
                canvas.oImage.src = p.src;
            } else {
                canvas.oImage = p.oImage;
            }

            canvas.style.width = canvas.width = Math.abs(costheta*canvas.oImage.width) + Math.abs(sintheta*canvas.oImage.height);
            canvas.style.height = canvas.height = Math.abs(costheta*canvas.oImage.height) + Math.abs(sintheta*canvas.oImage.width);

            var context = canvas.getContext('2d');
            context.save();
            if (rotation <= Math.PI/2) {
                context.translate(sintheta*canvas.oImage.height,0);
            } else if (rotation <= Math.PI) {
                context.translate(canvas.width,-costheta*canvas.oImage.height);
            } else if (rotation <= 1.5*Math.PI) {
                context.translate(-costheta*canvas.oImage.width,canvas.height);
            } else {
                context.translate(0,-sintheta*canvas.oImage.width);
            }
            context.rotate(rotation);
            context.drawImage(canvas.oImage, 0, 0, canvas.oImage.width, canvas.oImage.height);
            context.restore();
        }
        canvas.id = p.id;
        canvas.angle = p.angle;
        p.parentNode.replaceChild(canvas, p);
    }

    $.fn.rotateRight = function(angle) {
        this.rotate(angle==undefined?90:angle);
    }

    $.fn.rotateLeft = function(angle) {
        this.rotate(angle==undefined?-90:-angle);
    }
})(jQuery);