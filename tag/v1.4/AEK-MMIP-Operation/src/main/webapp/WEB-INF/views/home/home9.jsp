<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>首页</title>

    <%@ include file="/resources/jsp/yw/inc.jsp" %>
    <link rel="stylesheet" type="text/css" href="/resources/baguetteBox/baguetteBox.css">
    <script src="/resources/baguetteBox/baguetteBox.js"></script>
	<style>
        html,body{
            min-width: 1024px;
            overflow: hidden;
            width: 100%;
            height: 100%;
        }
        .middle{
            position: absolute;
            top:50px;
            width: 100%;
            bottom: 0;
            overflow: hidden;
        }
        /* 滚动条 */
        ::-webkit-scrollbar{
            width: 6px;
            height: 6px;
        }
        ::-webkit-scrollbar-track-piece{
            background-color: #CCCCCC;
            -webkit-border-radius: 6px;
        }
        ::-webkit-scrollbar-thumb:vertical{
            height: 5px;
            background-color: #999999;
            -webkit-border-radius: 6px;
        }
        ::-webkit-scrollbar-thumb:horizontal{
            width: 5px;
            background-color: #CCCCCC;
            -webkit-border-radius: 6px;
        }
		.color_navbar{background:#047DAD;}
		.navbar_fontcolor{color:#fff;}
		.navbar_fontcolor:hover{color:#fff;}
		.navbar_bg{color:#fff;}
		.icon_1{float:left; width:20%; line-height:30px; text-align:center; cursor:pointer; background:#ddd;}
		.icon_1:hover{background:#fff;}
		/*.icon_totle{width:150px; height:30px; margin-bottom:10px;margin-top:-10px;margin-left:-12px;}*/
		.panel_none{border-radius:0px;}
		.panel_p{background:rgba(218, 255, 233, 1); padding:10px; }
        /* 面包屑 */
        .breadcrumb-bg{
            position: absolute;
            top:0;
            right: 0;
            left:200px;
            height: 35px;
            background-color: #f5f5f5;
            float: left;
            text-align: center;
        }
        .breadcrumb{
            background-color: transparent;
            text-align: left;
            display: inline-block;
            float: left;
        }
        .breadcrumb-bg .glyphicon{
            padding-right: 3px;  
        }
        /* 主体iframe */
        #iframe-bd{
            position: absolute;
            top:35px;
            left:200px;
            right: 0;
            bottom: 0;
        }
        /* 侧导航栏 */
        .sidebar{
            width: 200px;
            float: left;
            height: 100%;
            overflow: auto;
        }
        /*左侧菜单*/
        .nav-header {
            display: block;
            padding: 3px 15px;
            font-size: 11px;
            font-weight: bold;
            line-height: 20px;
            color: #666;
            text-shadow: 0 1px 0 rgba(255,255,255,0.5);
            text-transform: uppercase;
        }
        .sidebar-menu .nav-text{
            width: 100px;
            display: inline-block;
            text-align: left;
        }
        .sidebar-menu{
            height: 100%;
            background-color: #f5f5f5;
        }
        .sidebar-menu .glyphicon{
            padding-right: 10px;
        }
        /*一级菜单*/
        .menu-first{
            height:45px;
            line-height:45px;
            background-color: #e9e9e9;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #e1e1e1;
            padding: 0;
            font-size: 14px;
            font-weight: normal;
            text-align: left;
            padding: 0 30px;
            position: relative;

        }
        /*一级菜单鼠标划过状态*/
        .menu-first:hover,.menu-first.active{
            text-decoration: none;
            background-color: #d6d4d5;
            border-top: 1px solid #b7b7b7;
            border-bottom: 1px solid #acacac;
            color: #2a6496;
        }
        .menu-first>.caret,.menu-second li a>.caret{
            position: absolute;
            right: 10px;
            top: 50%;
            margin-top: -2px;
        }
        .menu-second li a>.caret{
            right: 30px;
        }
        /*覆盖bootstrap的样式*/
        .nav-list,.nav-list li a{
            padding: 0px;
            margin: 0px;
        }
        /*二级菜单*/
        .menu-second li a{
            background-color: #f6f6f6;
            height:31px;
            line-height:31px;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #efefef;
            font-size: 12px;
            text-align: left;
            padding-left: 50px;
            position: relative;
        }
        /*二级菜单鼠标划过样式*/
        .menu-second li a:hover,.menu-second li.active a {
            text-decoration: none;
            background-color: #428bca;
            color:#fff;
        }
        /*二级菜单选中状态*/
        .menu-second-selected {
            background-color: #66c3ec;
            height:31px;
            line-height:31px;
            border-top: 1px solid #83ceed;
            border-bottom: 1px solid #83ceed;
            border-right: 3px solid #f8881c;
            border-left: 3px solid #66c3ec;
            text-align:center;
        }
        /* fadeIn */
        .fadeIn{
        -webkit-animation:fadeInDown 0.5s 0 ease both;
        -moz-animation:fadeInDown 0.5s 0 ease both;}
        @-webkit-keyframes fadeInDown{
        0%{opacity:0;
        -webkit-transform:translateY(-5px)}
        100%{opacity:1;
        -webkit-transform:translateY(0)}
        }
        @-moz-keyframes fadeInDown{
        0%{opacity:0;
        -moz-transform:translateY(-5px)}
        100%{opacity:1;
        -moz-transform:translateY(0)}
        }
        .spinner{
            z-index: -1;
        }
        /*三级菜单*/
        .third-nav{
            float: right;
            height: 100%;
        }
        .third-nav .third-nav-list{
            margin-right:30px;
            height: 100%;
        }
        .third-nav .third-nav-item{
            float: left;
            height: 100%;
            line-height: 35px;
            padding: 0 10px;
            border-bottom: 2px solid transparent;
            margin: 0 5px;
            color:#428bca;
            cursor: pointer;
        }
        .third-nav .third-nav-item.active{
            border-color: #428bca;
        }
        /*.third-nav .third-nav-item a:hover{
            text-decoration: none;
        }*/
        .third-nav-list{
            display: none;
        }
        #third-nav .third-nav-list{
            display: block;
        }
	</style>
  </head>
  <body>
    <!-- top -->
	<div class="navbar color_navbar" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand navbar_fontcolor" href="#">中国医疗材料信息平台</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="/00099000" class="navbar_bg"><span title="退出"  class="glyphicon glyphicon-log-out"></span></a></li>
          </ul>
        </div>
      </div>
    </div>
    <!-- Middle -->
    <div class="middle">
        <div class="sidebar" id="sidebar">
    		 <div class="sidebar-menu">
        		<c:forEach var="item" items="${menu}" varStatus="status">
        			<c:choose>
	        			<c:when test="${status.index==0}">			
	                    <a href="#um${item.puk}" class="nav-header menu-first collapsed" data-toggle="collapse"><div class="nav-text"><span class="glyphicon ${item.f07_xtburl}"></span>${item.f02_cdmc}</div></a>
	                        <ul id="um${item.puk}" class="nav nav-list collapse menu-second">
	        			</c:when>        			
	        			<c:when test="${item.f06_cddj=='1'}">
                            </ul>
	                    <a href="#um${item.puk}" class="nav-header menu-first collapsed" data-toggle="collapse"><div class="nav-text"><span class="glyphicon ${item.f07_xtburl}"></span>${item.f02_cdmc}</div></a>
	                        <ul id="um${item.puk}" class="nav nav-list collapse menu-second">
	        			</c:when>
	        			<c:when test="${item.f06_cddj=='2'}">
	                    		<li><a href="javascript:;" onclick="post('${item.f04_cdurl}')"><div class="nav-text">${item.f02_cdmc}</div></a>
								<div class="third-nav-list">
	        			</c:when>
	                    <c:when test="${item.f06_cddj=='3'}">			
		                            <div class="third-nav-item" onclick="post('${item.f04_cdurl}')">
                                        ${item.f02_cdmc}
		                            </div>
	        			</c:when>
	                    <c:when test="${item.f06_cddj=='9'}">	
	                    		</div>		
      							</li>
	        			</c:when>
	        			<c:otherwise>
	        			</c:otherwise>
        			</c:choose>        			
        		</c:forEach>
                </ul>
            </div> 
        </div>
        <!-- 面包屑 -->
        <div class="breadcrumb-bg">
	        <ol class="breadcrumb" id="breadcrumb">
                <li>
                    <a href="#" target="BizFrame">
                        <div class="nav-text">
                            <span class="glyphicon glyphicon-home"></span>首页
                        </div>
                    </a>
                </li>
            </ol>
            <!-- div id="frameLoading" class="frameStatus"></div-->
            <div class="third-nav" id="third-nav"></div>
        </div>
        <!-- 图片放大 -->
        <div id="baguetteBox-overlay" class="">
            <div id="baguetteBox-slider" style="-webkit-transform: translate3d(0%, 0px, 0px); transform: translate3d(0%, 0px, 0px);">
                <div class="full-image">
                    <figure>
                        <div class="spinner">
                            <div class="double-bounce1"></div>
                            <div class="double-bounce2"></div>
                        </div>
                        <img id="baguetteBox-img" src=""></figure>
                </div>
            </div>
            <button id="close-button" class="baguetteBox-button">
                <svg width="30" height="30" xmlns="http://www.w3.org/2000/svg" version="1.1">
                    <g stroke="rgb(160, 160, 160)" stroke-width="4">
                        <line x1="5" y1="5" x2="25" y2="25"></line>
                        <line x1="5" y1="25" x2="25" y2="5"></line>
                        X
                    </g>
                </svg>
            </button>
        </div>
        <!-- 主体iframe -->
        <div id="iframe-bd" class="fadeIn">
		  <iframe height="100%" width="100%" class="embed-responsive-item" src="about:blank" frameborder="0" id="BizFrame" name="BizFrame"></iframe>
        </div>
    </div>
    <script type="text/javascript">
        /*查看大图*/
        function showImg(url){
            if(!showImg.initStatus){
                showImg.$bd = $("#baguetteBox-overlay");
                showImg.$closeBtn = showImg.$bd.find("#close-button");
                showImg.$img = showImg.$bd.find("#baguetteBox-img");
                showImg.initStatus = true;
                showImg.$closeBtn.on('click',function(){
                   showImg.$bd.removeClass('visible');
                   setTimeout(function(){
                        showImg.$bd.hide();
                   },500);
                   showImg.$img.attr('href', '');
               });
            }

            var $bd = showImg.$bd,
                $img = showImg.$img;

            $img.attr('src', url);
            $bd.css('display', 'block');
            setTimeout(function(){
                $bd.addClass('visible');
            }, 20);

        }
        /*弹窗*/
        function showWindow(title, htmlOrUrl, modelSize, iframeHeight, btns, callback, cancelBtnFlag){
            cancelBtnFlag = cancelBtnFlag || true;
            /*初始化*/
            var $modal = (showWindow.$modal  || (showWindow.$modal = $($("#modalHtml").html()))).clone();
            // 标题
            $modal.find('.modal-title').html(title);
            //htmlOrUrl
            htmlOrUrl = $.trim(htmlOrUrl);
            var $iframe = $modal.find('iframe');
            if(htmlOrUrl.indexOf('<') == 0){
                $iframe.after(htmlOrUrl);
                $iframe.remove();
            }else{
                $iframe.attr('src', htmlOrUrl);
                if(!isNaN(iframeHeight)){
                    $iframe.height(iframeHeight);
                }
            }
            /*窗体大小*/
            if(modelSize != 'small'){
                $modal.removeClass('bs-example-modal-sm')
                $modal.find('.modal-sm').removeClass('modal-sm');
            }
            /*按钮*/
            if($.isArray(btns)){
                var btnsHtml = '',
                    defBtn = {
                        'class' : '',
                        'text' : '',
                        'id' : ''
                    };
                for (var i = 0; i < btns.length; i++) {
                    btns[i] = $.extend({}, defBtn ,btns[i]);
                    var btnHtml = '<button type="button"  id="{{id}}" class="btn {{class}}">{{text}}</button>';
                    for(var key in btns[i]){
                        if(btns[i].hasOwnProperty(key)){
                            btnHtml = btnHtml.replace("{{" + key + '}}',btns[i][key])
                        }
                    }
                    btnsHtml += btnHtml;
                };
                if(cancelBtnFlag){
                   btnsHtml = '<button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>' + btnHtml;
                }
                $modal.find(".modal-footer").append(btnsHtml);
            }else if(btns === false){
                $modal.find(".modal-footer").remove();
            }

            /*封装一个简单的方法用于关闭*/
            var me = {};
            me.close = function(){
                $modal.modal('hide');
            };
            me.modal = $modal;
            /*如果有iframe则给iframe中的window加上一个全局变量me*/
            var $modalFrame = $modal.find('iframe');
            if($modalFrame.length > 0){
                $modalFrame.each(function(i, item){
                    var frame = item;
                    item.onload = function(){
                        frame.contentWindow.me = me;
                    }
                });
            }
            /*显示modal*/
            $modal.modal();
            /*当modal*/
            $modal.on("hidden.bs.modal", function(){
                $modal.remove();
                if($.isFunction(callback)){
                    callback();
                }
            });
            return me;
        }
        // confirm
        function confirm(text, okFn, cancelFn){
            var modal = showWindow('确认提示', '<div>' + text + '</div>', 'small', null, [{'class':'btn-default','text':'取 消','id':'cancel-btn'},{'class':'btn-primary','text':'确 定','id':'ok-btn'}], false);

            var $modal = modal.modal;

                $modal.find("#ok-btn").on('click',function(){
                    modal.close();
                    okFn && okFn();
                });

                $modal.find("#cancel-btn").on('click',function(){
                    modal.close();
                    cancelFn && cancelFn();
                });
        }
        /*-----------------
            msg
        ------------------*/
        var msg = (function(){
            var tmpl = '<span class="msgTips"></span>',
                $backGround,
                loadingFlag,
                sleepTimer;

            return function(text, icon, sleep){
                if(sleepTimer){
                    clearTimeout(sleepTimer);
                }
                // debugger;
                sleep = sleep || 5000;
                var $msg,
                    text = '<span class="msgTips-text">'+ text +'</span>';

                if(icon == 1 || icon == 'success'){
                    icon = 'success';
                    text = '<span class="glyphicon glyphicon-ok"></span>'+text;
                }else if(icon == 0 || icon == 'error'){
                    icon = 'error';
                    text = '<span class="glyphicon glyphicon-remove"></span>'+text;
                }else if(icon == 2 || icon == 'warning'){
                    icon = 'warning';
                    text = '<span class="glyphicon glyphicon-exclamation-sign"></span>'+text;
                }else if(icon == -1 || icon == 'loading'){
                    icon = 'loading';
                    text = '<span class="msgTips-loading"></span>'+text;
                }else{
                   text = '<span class="glyphicon glyphicon-info-sign"></span>'+text;
                }

                if(loadingFlag){
                    $msg = loadingFlag;
                    // loadingFlag = $();   
                }else{ 
                    $msg = $(tmpl);
                    if(!$backGround){
                        $backGround = $(".breadcrumb-bg");
                    }
                    $backGround.append($msg);
                    if(icon == 'loading'){
                        loadingFlag = $msg;
                    }
                }
                $msg.html(text);
                $msg.attr('class', 'msgTips ' + icon);
                $msg.css('margin-left', function(){
                    return 0 - ($msg.width() / 2);
                });
                $msg.stop().fadeIn(500, function(){
                    if(icon != 'loading'){
                        (function($msg){
                             sleepTimer = setTimeout(function(){
                                sleepTimer = null;
                                $msg.stop().fadeOut(500, function(){
                                    $(this).remove();
                                    loadingFlag = null;
                                })
                            }, sleep);
                        })($msg);
                    }
                });
            }
        })();
        //ready
        $(function(){
            // $('#iframe-bd').height(function(){
            //     return $(window).height() - 90;
            // });
            /*------------------------
                菜单焦点自动绑定
            -----------------------*/
            var $firstMenuActive,
                $subMenuActive,
                $thirdBg = $("#third-nav");
            /*第一级菜单*/
            $(".menu-first").on('click', function(){  
                $firstMenuActive && $firstMenuActive.removeClass('active');
                $firstMenuActive = $(this).addClass('active');
                if($firstMenuActive.attr('target') == 'BizFrame'){
                    $subMenuActive && $subMenuActive.removeClass('active');
                    $subMenuActive = null;
                }
                if($(this).attr('onclick')){
                    initBreadcrumb()
                }
            })
            /*第二级菜单*/
            $(".nav-list>li").on('click', function(){  
                $subMenuActive && $subMenuActive.removeClass('active');
                $subMenuActive = $(this).addClass('active');
                $firstMenuActive && $firstMenuActive.removeClass('active');
                $firstMenuActive = $subMenuActive.parent().prev().addClass('active');
                initBreadcrumb()
                var $thirdNav = $(this).find('.third-nav-list');
                if($thirdNav.length > 0 ){
                    $thirdBg.html($thirdNav.clone());
                    $thirdBg.find('.third-nav-item:eq(0)').trigger('click')
                }else{
                    $thirdBg.empty();
                }
            })
            // 第三级菜单
            $thirdBg.on('click', '.third-nav-item', function(){
                $(this).addClass('active').siblings('.active').removeClass('active');
            });
             /*--------------------
                    刷新面包屑
             ---------------------*/
            function initBreadcrumb(){
                var self = initBreadcrumb;
                if(!self.initStatus){
                   self.$breadcrumb = $("#breadcrumb"); 
                   self.$homeLi = $('<li><a href="index.html" target="BizFrame"><span class="glyphicon glyphicon-home"></span>首页</a></li>');
                   self.initStatus = true;
                }
               /*处理一级菜单*/
                var $firstli = "";
                if($firstMenuActive){
                    $firstli = $("<li></li>");
                    if($firstMenuActive.attr('target') == 'BizFrame' && $subMenuActive){
                        $firstli.append('<a href="'+ $firstMenuActive.attr('href') +'">'+ $firstMenuActive.text() +'<a>');
                    }else{
                        $firstli.append($firstMenuActive.text()).addClass('active');
                    }
                }
                var $subLi = "";
                if($subMenuActive){
                     $subLi = $("<li class='active'>"+ $subMenuActive.children('a').text() +"</li>");   
                }

                self.$breadcrumb.html(self.$homeLi.add($firstli).add($subLi));
            };
        });

        function post(URL, PARAMS) {
    		var contentWindow = document.getElementById('BizFrame').contentWindow,
                temp = contentWindow.document.createElement("form");
    		temp.action = URL;
    		temp.method = "post";
    		temp.style.display = "none";
    		for ( var x in PARAMS) {
    			var opt = document.createElement("textarea");
    			opt.name = x;
    			opt.value = PARAMS[x];
    			// alert(opt.name)        
    			temp.appendChild(opt);
    		}
    		contentWindow.document.body.appendChild(temp);
    		temp.submit();
    		return false;
    	}
    </script>
    <!-- 弹窗模块 -->
    <script type="text/html" id="modalHtml">
        <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel"></h4>
                    </div>
                    <div class="modal-body">
                        <iframe src="about:blank" class="embed-responsive-item" height="500px" width="100%;"  frameborder="0"></iframe>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
    </script>
  </body>
</html>