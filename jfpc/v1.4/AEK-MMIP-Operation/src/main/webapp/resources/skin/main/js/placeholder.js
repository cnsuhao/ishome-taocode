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