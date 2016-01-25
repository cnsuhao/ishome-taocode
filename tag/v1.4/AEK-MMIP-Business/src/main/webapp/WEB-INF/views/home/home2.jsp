<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="org.jfpc.framework.helper.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>医院工作台</title>
<meta name="description" content="">
<meta name="keywords" content="">
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/hospital_cert.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script src="/resources/js/highcharts.js"></script>
<%@ include file="/resources/jsp/ImagePath.jsp" %>
<style type="text/css">
.lInput{text-align: left; font-size: 14px;}
.borderRadius{width: 60%; overflow: hidden;}
.borderRadius .text{border-radius: 6px 6px 0 0;}
.textTips{height: 40px; line-height: 40px; width: 100%;}
.btnSearch{padding: 13px;}
</style>
<script>
$(function(){
	resetText();
	$(".textTips").input();
	$(".tag").uiSwitch();
	$(".select").uiSelect();
	$('#container1').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: ['医疗器械注册证', '供应商经营许可证', '生产厂家生产许可证', '逐级授权书'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' 本'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '证件数量',
            data:  [${WDKHZSZ1.fb2}, ${WDKHZSZ1.fb3}, ${WDKHZSZ1.fb4}, ${WDKHZSZ1.fb5}]
        }]
    });

	$('#container2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '客户区域分布图'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '省',
            data: [
                ['北京',   45.0],
                ['上海',       26.8],
                {
                    name: '浙江',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['重庆',    8.5],
                ['河南',     6.2],
                ['湖南',   0.7]
            ]
        }]
    }); 
	getYear();
});

function getCertPage(url){
	top.clickSubMenu(this, url);
}

function getYear(){
	var year = $("#year>option:selected").val();
	ajax("/10233?t="+Math.random(),{"year":year},function(data){
		if(data.code == 0){
			$('#container3').highcharts({
		        title: {
		            text: '销售统计表',
		            x: -20 //center
		        },
		        subtitle: {
		            enabled:false,
		            text: '',
		            x: -20
		        },
		        xAxis: {
		            categories: ['1月', '2月', '3月', '4月', '5月', '6月','7月', '8月', '9月', '10月', '11月', '12月']
		        },
		        yAxis: {
		            title: {
		                text: '销售额（万元）'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        tooltip: {
		            valueSuffix: '万元'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		            name: '销售额',
		            data: data.result
		        }]
		    });
		}
	})
}
</script>
</head>
<body>
<div class="contnet" id="yui-contentBox">
	<div class="boxContentRight rFloat">
		<script src="/resources/js/usinfo2.js?t=<%=DateHelper.currentTime1()%>"></script>
		<div class="mTop10 boxContent">
			<div class="boxInfoTitle">证件提醒</div>
			<div class="boxInfoContent">
				<div class="orderCountNum">
					<span>待处理邀请： <a href="javascript:getCertPage('/32200300?index=0');"><em>${ZJTJY1.fb1 }</em></a></span>
					<span>审核推送证件：<a href="javascript:getCertPage('/32200300?index=1')"><em>${ZJTJY1.fb2 }</em></a></span><br />
					<span>审核换证信息：<a href="javascript:getCertPage('/32200300?index=2')"><em>${ZJTJY1.fb3 }</em></a></span>
					<span>已过期证件：<a href="javascript:getCertPage('/32200300?index=3')"><em>${ZJTJY1.fb4 }</em></a></span>
				</div>
			</div>
		</div>
		<div class="mTop10 boxContent">
			<div class="boxInfoTitle">采购提醒</div>
			<div class="boxInfoContent">
				<div class="orderCountNum">
					等待配送：<span>采购订单 <em>0</em></span> <span>寄销订单 <em>0</em></span><br />
					部分配送：<span>采购订单 <em>0</em></span> <span>寄销订单 <em>0</em></span><br />
					所有订单：<span>采购订单 <em>0</em></span> <span>寄销订单 <em>0</em></span>
				</div>
			</div>
		</div>
		<div class="mTop10 boxContent"><center>广告</center></div>
	</div>
	<div class="boxContentLeft">
		<div class="boxContent">
			<div class="tagBox">
				<div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
					<span class="tagSelect">医疗器械注册证</span>
					<span>供应商</span>
				</div>
				<div class="tagBox">
					<div class="tagNav tagnSelect">
						<div class="indexFormSearch">
							<form id="cert-form" name="cert-form" action="/3210100?index=3" method="POST">
								<input type="text" name="f01_zczzwmc" id="f01_zczzwmc" class="text textTips {text:'请输入注册证号'}" />
								<input type="submit" id="certbtn" name="certbtn" value="搜索" class="btn btnSearch" />
							</form>
						</div>
					</div>
					<div class="tagNav">
						<div class="indexFormSearch">
							<form id="supplier-form" name="supplier-form" action="/32011001" method="POST">
								<input type="text" name="f01_qyqc" id="f01_qyqc" class="text textTips {text:'请输入供应商名称'}" />
								<input type="submit" id="supplierbtn" name="supplierbtn" value="搜索" class="btn btnSearch" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="mTop10 boxContent">
			<div class="boxInfoTitle">统计</div>
			<div class="boxInfoContent">
				<dl class="orderTotal">
					<dt>我的证件：<strong>${WDKHZSZ1.fb1}</strong></dt>
					<dd><div id="container1" style="width:400px; height:200px;"></div></dd>
					<dt>我的供应商：<strong>${WDKHZSZ2.fb1}</strong></dt>
					<dd><div id="container2" style="width:400px; height:200px"></div></dd>
					<dt>我的采购单：<strong>0</strong></dt>
					<dd>
						<div class="select rFloat {onchange:getYear}">
							<select name="year" id="year">
								<option value="2014">2014</option>
								<option value="2013">2013</option>
								<option value="2012">2012</option>
							</select>
						</div>
						<div id="container3" style="width:100%; height:200px;"></div>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</div>
<%@ include file="/resources/jsp/formJS.jsp" %>
</body>
</html>