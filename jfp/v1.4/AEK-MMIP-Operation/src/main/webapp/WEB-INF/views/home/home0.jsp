<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title>首页</title>

    <%@ include file="/resources/jsp/yw/inc.jsp" %>
    <link rel="stylesheet" type="text/css" href="/resources/baguetteBox/baguetteBox.css">
    <!-- <script src="/resources/baguetteBox/baguetteBox.js"></script> -->
	<style>
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
		.color_navbar{background:rgba(4, 125, 173, 1);}
		.navbar_fontcolor{color:#fff;}
		.navbar_fontcolor:hover{color:#fff;}
		.navbar_bg{color:#fff;}
		.icon_1{float:left; width:20%; line-height:30px; text-align:center; cursor:pointer; background:#ddd;}
		.icon_1:hover{background:#fff;}
		/*.icon_totle{width:150px; height:30px; margin-bottom:10px;margin-top:-10px;margin-left:-12px;}*/
		.panel_none{border-radius:0px;}
		.panel_p{background:rgba(218, 255, 233, 1); padding:10px; }
        html{
            overflow-y:scroll;
        }
        /* 面包屑 */
        .breadcrumb-bg{
            position: fixed;
            top: 50px;
            padding-left:0;
            z-index: 999;
        }
        .breadcrumb-bg .breadcrumb{
        	margin-bottom:0;
        }
        /* 侧导航栏 */
        .sidebar{
            position: fixed;
            top: 50px;
            padding:0;
            bottom: 0;
            overflow: auto;
            border-right: 1px solid #c4c8cb;
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
        .sidebar-menu{
            
            height: 100%;
            background-color: #f5f5f5;
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
            text-align: center;
        }
        /*一级菜单鼠标划过状态*/
        .menu-first:hover,.menu-first.active{
            text-decoration: none;
            background-color: #d6d4d5;
            border-top: 1px solid #b7b7b7;
            border-bottom: 1px solid #acacac;
            color: #2a6496;
        }
        /*二级菜单*/
        .menu-second li a{
            background-color: #f6f6f6;
            height:31px;
            line-height:31px;
            border-top: 1px solid #efefef;
            border-bottom: 1px solid #efefef;
            font-size: 12px;
            text-align:center;
        }
        /*二级菜单鼠标划过样式*/
        .menu-second li a:hover,.menu-second li.active a {
            text-decoration: none;
            background-color: #428bca;
            color:#fff;
        /*            border-top: 1px solid #83ceed;
            border-bottom: 1px solid #83ceed;*/
         /*   border-right: 3px solid #f8881c;*/
            /*border-left: 3px solid #66c3ec;*/
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
        /*覆盖bootstrap的样式*/
        .nav-list,.nav-list li a{
            padding: 0px;
            margin: 0px;
        }
	</style>
  </head>
  <body>
	<!-- 头部 -->
	<script type="text/javascript">   
	function ToUrl(x)   
	{   
		location.href=x;   
	}   
	</script> 
	<div class="navbar color_navbar navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand navbar_fontcolor" href="#">中国医疗材料信息平台</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
<!--             <li><a href="#" class="navbar_bg"><span title="布局" class="glyphicon glyphicon-th-list"></span></a></li>
            <li><a href="#" class="navbar_bg"><span title="提醒" class="glyphicon glyphicon-bell"></span></a></li>
            <li><a href="#" class="navbar_bg"><span title="私信" class="glyphicon glyphicon-envelope"></span></a></li>
 -->            <li><a href="#" class="navbar_bg"><span title="退出" onclick="ToUrl('/00099000')" target="_self" class="glyphicon glyphicon-log-out"></span></a></li>
          </ul>
        </div>
      </div>
    </div>
    
	<div class="container-fluid">
       <div class="row">
        <div class="col-md-2 sidebar" id="sidebar">
    		 <div class="sidebar-menu">

		<c:forEach var="item" items="${menu}" varStatus="status">
			<c:choose>
			<c:when test="${status.index==0}">			
			</c:when>
			<c:when test="${status.index==1}">			
                <a href="#userMain" class="nav-header menu-first collapsed" data-toggle="collapse"><span class="glyphicon ${item.f07_xtburl}"></span>${item.f02_cdmc}</a>
			</c:when>
			<c:when test="${status.index==2}">			
                <a href="#um${item.puk}" class="nav-header menu-first collapsed" data-toggle="collapse"><span class="glyphicon ${item.f07_xtburl}"></span>${item.f02_cdmc}</a>
                <ul id="um${item.puk}" class="nav nav-list collapse menu-second">
			</c:when>
			
			<c:when test="${item.f06_cddj==1}">			
                </ul>
                <a href="#um${item.puk}" class="nav-header menu-first collapsed" data-toggle="collapse"><span class="glyphicon ${item.f07_xtburl}"></span>${item.f02_cdmc}</a>
                <ul id="um${item.puk}" class="nav nav-list collapse menu-second">
			</c:when>
			<c:when test="${item.f06_cddj==2}">
                    <li><a href="javascript:;" onclick="post('${item.f04_cdurl}')">${item.f02_cdmc}</a></li>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
		</c:forEach>
                </ul>
            </div> 
        </div>

        <div class="col-md-10 col-md-offset-2 breadcrumb-bg">
	        <ol class="breadcrumb" id="breadcrumb">
                <li><a href="#" target="BizFrame"><span class="glyphicon glyphicon-home"></span>首页</a></li>
                <li class="active">首页</li>
            </ol>  
            <span class="loading-bar">
                <span class="icon">
                    
                </span>
                <Span></Span>
            </span>     
        </div>
        <div class="col-md-10 col-md-offset-2" style="margin-top:86px;" >
		  <iframe height="2000" width="100%" class="embed-responsive-item" src="about:blank" frameborder="0" id="BizFrame" name="BizFrame" scrolling="no"></iframe>
        </div>
        </div> 
    </div>
    <!-- 图片放大 -->
    <div id="baguetteBox-overlay" class="visible">
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
<!--    <button id="previous-button" class="baguetteBox-button" style="display: none;">
            <svg width="40" height="60" xmlns="http://www.w3.org/2000/svg" version="1.1">
                <polyline points="30 10 10 30 30 50" stroke="rgba(255,255,255,0.5)" stroke-width="4" stroke-linecap="butt" fill="none" stroke-linejoin="round">&lt;</polyline>
            </svg>
        </button>
        <button id="next-button" class="baguetteBox-button" style="display: none;">
            <svg width="40" height="60" xmlns="http://www.w3.org/2000/svg" version="1.1">
                <polyline points="10 10 30 30 10 50" stroke="rgba(255,255,255,0.5)" stroke-width="4" stroke-linecap="butt" fill="none" stroke-linejoin="round">&gt;</polyline>
            </svg>
        </button> -->
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
    <script type="text/javascript">
    /*设置iframe高度*/
    function setIframeHeihgt(height){
        var $iframe =  setIframeHeihgt.$iframe || (setIframeHeihgt.$iframe = $("#BizFrame"));
        setIframeHeihgt.$iframe.height(height || 0);
    }
    /*弹窗*/
    function showWindow(title, htmlOrUrl, modelSize, iframeHeight, btns, callback){
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
    //ready
    $(function(){
        /*------------------------
            菜单焦点自动绑定
        -----------------------*/
        var $firstMenuActive,
            $subMenuActive;
        /*第一级菜单*/
        $(".menu-first").on('click', function(){  
            $firstMenuActive && $firstMenuActive.removeClass('active');
            $firstMenuActive = $(this).addClass('active');
            if($firstMenuActive.attr('target') == 'BizFrame'){
                $subMenuActive && $subMenuActive.removeClass('active')
                $subMenuActive = null;
            }
            initBreadcrumb();
        })
        /*第二级菜单*/
        $(".nav-list>li").on('click', function(){  
            $subMenuActive && $subMenuActive.removeClass('active');
            $subMenuActive = $(this).addClass('active');
            $firstMenuActive && $firstMenuActive.removeClass('active');
            $firstMenuActive = $subMenuActive.parent().prev().addClass('active');
            initBreadcrumb();
        })
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
                 $subLi = $("<li class='active'>"+ $subMenuActive.text() +"</li>");   
            }

            self.$breadcrumb.html(self.$homeLi.add($firstli).add($subLi));
        };
    });
    
    function post(URL, PARAMS) {
		var temp = document.getElementById('BizFrame').contentWindow.document.createElement("form");
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
		document.getElementById('BizFrame').contentWindow.document.body.appendChild(temp);
		temp.submit();
		return false;
	}
    </script>
    <script type="text/html" id="modalHtml">
    <div id="" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
    </script>
  </body>
</html>