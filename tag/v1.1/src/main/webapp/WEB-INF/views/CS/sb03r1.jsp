<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">   
    <title>系统画面菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/resources/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
// 		$(function(){
// 			alert("hh");
// 			$("#test").click(function(){
// 				alert("ok");
// 			});
// 		});
		/*(function($){
			$.fn.simplePage=function(o){
				var options={
					pager: '.pager',//表格控件的容器 
					container: '.tableData',//放置表格数据的容器
					form: '#form',//放置查询条件的表单
					pageForm: '#pageForm',//放置隐藏域的Div	
					url: '',//发送请求的地址 
					currentPage: 1,
					pageSize: 2,//每页显示记录数
					type: null,//可选：action 当前进行的是何操作；
					pageShow:7 					
				};
				$.page={
					setPage:function(o){
					},
					getCurrentPage:function(o){//获取当前页
					},
					getPageSize:function(o){//获取每页显示数量 
					},
					genData:function(o){//生成发送所需要的json数据 
					},
					loadData:function(o){//发送数据 
						var that = this;
						var data = that.genData(o);
						$.ajax({
							url: o.url,
							data: data,
							type: 'post',
							dataType: 'html',
							cache: false,
							success:function(result){
									var res = $(result).find('tbody').html();
									var totalPage = $(result).find('#totalPage').val();
									var currentPage = $(result).find('#currentPage').val();
									o.currentPage=currentPage;
									o.pager.empty(); 
									$.line.setLine(totalPage,o);
							},
							error:function(){
								alert("error");
							}	
						});
					}
				};	
				$.line={
					setLine:function(totalPage,o){
						for(var i=0;i<totalPage;i++){
							var a=$('<a/>').html('<span>'+'i+1'+'</span>')
							.addClass('pageA').bind('click',function(){
														
							});
						}
					},	
				};
							
// 				return "str";
			}
		})(jQuery);*/
// 一	为了我们的控件可以随意使用，我们将其写成插件的形式，首先搭个框架，我们将插件命名为simplePage 
// 二	由于需要发送当前页、每页显示数量，所以需要 currentPage，pageSize 两个基本参数； 
// 由于需要查询表格内容，所以需要一个放置查询条件的表单 form； 
// 由于需要修改删除后记住当前页，所以需要一个标志指示当前进行的是何操作type； 
// 为了使我们的程序更具有灵活性，加上获取数据后需要加载到的container，还有就是分页控件加载的pager， 

// 三 为了便于维护，我们声明一个独立的对象来进行获取数据，绑定事件的操作，我们将这个函数命名为$.page	
			
// 	四	实现以上所声明的函数，当分页第一次加载的时候，我们需要从服务器获取总页数来生成分页控件，所以首先实现loadData函数 

// 	五	下面我们实现上面生成分页控件的函数$.line.setLine 
// 		$.line={ 
// 			setLine:function(totalPage,o){ 
// 			for(var i=0;i<totalPage;i++){ 
// 			var a=$('<a/>').html('<span>'+(i+1)+'</span>').addClass('pageA').bind('click',function(){ 
// 			var s=$(this); 
// 			s.siblings().removeClass('pageActive'); 
// 			s.addClass('pageActive'); 
// 			o.currentPage=s.text(); 
// 			$.page.loadData(o); 
// 			}); 
// 			if(o.currentPage==i+1){ 
// 			a.addClass('pageActive'); 
// 			} 
// 			o.pager.append(a); 
// 			} 
// 			var limit=this.getLimit(o,totalPage); 
// 			var aPage=o.pager.find('a.pageA').not('a.previous,a.nextAll,a.record'); 
// 			aPage.hide(); 
// 			aPage.slice(limit.start,limit.end).show(); 
// 			var prev=$('<a/>').html('<span>上一页</span>').addClass('pageA previous').unbind('click').bind('click',function(){ 
// 			var pageActive=o.pager.find('a.pageActive'); 
// 			var s=$(this); 
// 			if(pageActive.prev().text()=='上一页'){ 
// 			alert('已经是第一页!'); 
// 			return false; 
// 			} 
// 			pageActive.removeClass('pageActive'); 
// 			pageActive.prev().addClass('pageActive'); 
// 			o.currentPage=pageActive.prev().text(); 
// 			$.page.loadData(o); 
// 			}); 
// 			var next=$('<a/>').html('<span>下一页</span>').addClass('pageA nextAll').unbind('click').bind('click',function(){ 
// 			var pageActive=o.pager.find('a.pageActive'); 
// 			var s=$(this); 
// 			if(pageActive.next().text()=='下一页'){ 
// 			alert('已经是最后一页!'); 
// 			return false; 
// 			} 
// 			pageActive.removeClass('pageActive'); 
// 			pageActive.next().addClass('pageActive'); 
// 			o.currentPage=pageActive.next().text(); 
// 			$.page.loadData(o); 
// 			}); 
// 			var pageActiveText=o.pager.find('a.pageActive').text(); 
// 			var record=$('<a/>').html('<span>'+pageActiveText+'/'+totalPage+'</span>').addClass('pageA record'); 
// 			o.pager.prepend(prev).prepend(record).append(next); 
// 			} 
// 			} 
// 		在上面的代码中我们给当前的页面加上了pageActive类，所以现在我们可以实现$.page的getCurrentPage函数，非常简单 	
// 		getcurrentPage: function(o){ 
// 			var p = o.pager.find("a.pageActive").text(); 
// 			return p; 
// 		} 
// 		接着我们实现生成json数据的genData函数，json格式为{key:value,key:value} 
// 		genData: function(o){ 
// 			var sdata = $.extend({}, { "currentPage": o.currentPage, 
// 			"pageSize": o.pageSize}, $.jsonObj(o.pageForm)); 
// 			return sdata; 
// 			}, 
// 		上面的$.jsonObj为自定义的函数，为了生成我们需要的json格式以便发送查询的数据，只支持input，select 
// 		$.jsonObj = function(form){ 
// 判断是否有序列化的东东 
// 			if (!$(form).html() || $(form).html() == null || $.trim($(form).html()) == "") { 
// 			return null; 
// 			} 
// 			var formEl = $(form).find('input[type="text"]'); 
// 			var formselect = $(form).find('select'); 
// 			var json = "{"; 
// 			for (var i = 0; i < formEl.length - 1; i++) { 
// 			var name = formEl.eq(i).attr('name'); 
// 			var val = "'" + formEl.eq(i).val() + "'"; 
// 			json += name; 
// 			json += ":"; 
// 			json += val; 
// 			json += ","; 
// 			} 
// 			var lname = formEl.eq(formEl.length - 1).attr('name'); 
// 			var lval = "'" + formEl.eq(formEl.length - 1).val() + "'"; 
// 			json += lname; 
// 			json += ":"; 
// 			json += lval; 
// 			if (formselect) { 
// 			json += ","; 
// 			for (var i = 0; i < formselect.length - 1; i++) { 
// 			var name = formselect.eq(i).attr('name'); 
// 			var val = "'" + formselect.eq(i).val() + "'"; 
// 			json += name; 
// 			json += ":"; 
// 			json += val; 
// 			json += ","; 
// 			} 
// 			var lname = formselect.eq(formselect.length - 1).attr('name'); 
// 			var lval = "'" + formselect.eq(formselect.length - 1).val() + "'"; 
// 			json += lname; 
// 			json += ":"; 
// 			json += lval; 
// 			} 
// 			json += "}"; 
// 			var jsonObj = eval("(" + json + ")") 
// 			return jsonObj; 
// 			} 
// 		接着我们为查询表单的按钮绑定事件，我们扩展下我们的$.page函数 
// 		handleQueryLine:function(o){ 
// 			$(o.form).find(".query").click(function(){ 
// 			$(o.pageForm).append($(o.form).clone(true)); 
// 			$(o.pageForm).empty(); 
// 			$(o.form).find('input[type="text"]').each(function(){ 
// 			var vals = $(this).val(); 
// 			var s = $(this).clone().val(vals); 
// 			$(o.pageForm).append(s); 
// 			}); 
// 			$(o.form).find('select').each(function(){ 
// 			var vals = $(this).val(); 
// 			var s = $(this).clone().val(vals); 
// 			$(o.pageForm).append(s); 
// 			}); 
// 			$.page.query(o); 
// 			}); 
// 			} 
// 		基本的函数已经完成，下面完成主函数 
// 		$.fn.simplePage = function(os){ 
// 			var options = { 
// 			pager: '.pager',//表格控件的容器 
// 			container: '.tableData',//放置表格数据的容器 
// 			form: '#form',//放置查询条件的表单 
// 			pageForm: '#pageForm',//放置隐藏与的Div 
// 			url: '',//发送请求的地址 
// 			currentPage: 1, 
// 			pageSize: 2, 
// 			type: null,//可选：action, 
// 			pageShow:7//, 
// 			}; 
// 			var o = $.extend(options, os); 
// 			return this.each(function(){ 
// 			o.pager = $(this).find(o.pager); 
// 			o.container = $(this).find(o.container); 
// 			首先清除click事件 
// 			o.pager.unbind('click'); 
// 			if (o.type == 'action') { 
// 			指定的动作，比如删除时的事件，这时需要在当前页刷新数据 
// 			o.currentPage = $.page.getPageSize(o); 
// 			o.pageSize = $.page.getCurrentPage(o); 
// 			$.page.loadData(o); 
// 			return; 
// 			} 
// 			$.page.loadData(o); 
// 			$.line.handleQueryLine(o); 
// 			}) 
// 			} 
// 		分页还不是很好看，我们用firebug查看下生成的分页结构，写了如下样式
// 		.pager a { 
// 			display: block; 
// 			float: left; 
// 			width: 16px; 
// 			height: 16px; 
// 			margin: 5px; 
// 			}} 
// 			.pager a.pageA{ 
// 			background:url("../images/grid/page.png") no-repeat left 0px transparent; 
// 			display:inline-block; 
// 			font-size:14px; 
// 			margin:0 3px; 
// 			padding-left:6px; 
// 			text-align:center; 
// 			vertical-align:bottom; 
// 			height:auto; 
// 			width:auto; 
// 			cursor:pointer; 
// 			} 
// 			.pager a.pageA span{ 
// 			background:url("../images/grid/page.png") no-repeat right 0px transparent; 
// 			display:inline-block; 
// 			height:24px; 
// 			line-height:22px; 
// 			padding-right:6px; 
// 			} 
// 			.pager a.pageActive{ 
// 			background:url("../images/grid/page.png") no-repeat left -24px transparent; 
// 			} 
			
	
	
	
/* 		$.ajax(function(){
		type:"post",
		url:"default.aspx",
		data:"{=1}",
		}); */
		function sub(){		
			document.form1.action="/CSSB03/F.go";
			form1.submit();		
		}
		function del(puk){
			document.form2.action="/CSSB03/D.go?puk="+puk;
			form2.submit();
		}
		function edit(puk){
			document.form2.action="/CSSB03/R.go?puk="+puk;
			form2.submit();
		}
				
	</script>
  </head>
  <!--存储数据的容器--> 
<!-- 	<div class="tableData">  -->
<!-- 	</div>  -->
<!-- 分页控件显示  -->
<!-- 	<div class="pageBar"> -->
<!-- 	</div>   -->
  <body style="margin-left:50px; margin-top:80px; ">
	<script src="/resources/js/bootstrap.js"></script>	
    <button type="button" id="test" value="">test</button>
    
    <form action="/CSSB03/F.go" method="post" id="form1">
    	<fieldset>
    		<select style="width:180px; height:26px">
         	<option id="id" >画面id</option>
         </select>
    	
    	<label class="checkbox">
    		<input type="checkbox">系统菜单
    	</label>
    	<select style="width:180px; height:26px">
    	         	<option id="id">父菜单Id&nbsp;&nbsp;</option>
         </select>	<br> 
         <p style="margin-left: 180px">       
         	<button class="btn" type="button" id="btn1" onclick="location.href='/CSSB03/H.go'">添加</button>&nbsp;&nbsp;&nbsp;
         <button type="button" id="btn2" class="button" onclick="sub()">检索</button>
         </p>
         </fieldset> 
    </form> <h5>${obj}</h5>
    
    <table border="2" style="padding: 0px; margin: 0px;">
    	<thead></thead> 	
    	<tbody> 	
    		<tr style="border: 1">
    			<td>画面Id</td>
<!--     			<td>k3</td> -->
    			<td>画面名称</td>
    			<td>系统菜单</td>
    			<td>父菜单Id</td>
    			<td>画面种类</td>
    			<td>说明</td>
    			<td>编辑</td>
    			<td>删除</td>
    		</tr>
    		
    		<form  action="" method="post" id="form2" name="form2">	
    		<fieldset>	
			<c:forEach var="sb03dbo" items="${list}">	
							
    		 <tr style="border: 1">
<!--     			<td>${sb03dbo.puk}</td> -->
    			<td>f01 ${sb03dbo.f01}</td>
    			<td>k03 ${sb03dbo.k03}</td>
    			<td>${sb03dbo.f05}</td>
    			<td>k03 ${sb03dbo.k03}</td>
    			<td>${sb03dbo.f05}</td>
    			<td>${sb03dbo.bbb}</td>
    			<td >   				
<!-- 					<a href="/CSSB03/U.go?puk=${sb03dbo.puk}&f01=${sb03dbo.f01} -->
<!-- 					&f05=${sb03dbo.f05}&k03=${sb03dbo.k03}&f05=${sb03dbo.f05} -->
<!-- 					&bbb=${sb03dbo.bbb}">编辑</a>  -->
				 <button type="button" id="edit" class="button" value="" onclick="edit('${sb03dbo.puk}')">编辑</button>
    			</td>
    			<td >
    				<button type="button"  id="del" class="button" value="" onclick="del('${sb03dbo.puk}')">删除</button>		
<!-- 				<a href="/CSSB03/D.go?puk=${sb03dbo.puk}">删除</a>  -->
    			</td>
    		</tr>
    		</fieldset> 
    		</form>	
			</c:forEach>					
    	</tbody>  
    </table>  
     	 <!-- 分页显示	 -->
				<p>
				首页   &nbsp;&nbsp;&nbsp; 上页  &nbsp;&nbsp;&nbsp;
				当前第
				<input type="text"  placeholder="record" id="record" name="record" 
				class="requriedName" value="" style="width:50px">页
				下页 &nbsp;&nbsp;&nbsp;末页 &nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
				每页：<input type="text"  placeholder="record" id="record" name="record" 
				class="requriedName" value="10" style="width:30px">条 &nbsp;              		
				记录总数：<input type="text"  placeholder="recordCount" id="recordCount" 
				name="recordCount" class="requriedName" value="" style="width:50px"> 
			</p>  	  			
  </body>
</html>
