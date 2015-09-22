<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>过期证书</title>
	<%@ include file="/resources/jsp/gys/inc.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/base.css">
<link rel="stylesheet" href="/resources/css/jquery.mCustomScrollbar.css">
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/jquery.mousewheel.js"></script>
<script src="/resources/js/jquery.mCustomScrollbar.js"></script>
<script src="/resources/js/function.js"></script>
<script src="/resources/js/jq.ui.js"></script>
<script src="/resources/js/tableFixed.min.js"></script>
<script>
$(function(){
    $(".tag").uiSwitch();
    $(".checkbox").checkbox();
    tagClick();
    $(".tag>span:eq("+(getUrlParam("index") || 0)+")").click();
});

function tagClick(){
    $(".tag>span").click(function(){
        var index = $(this).index();
        if(index == 0){
            getAuditSup();
            $("#audit-cert-list").empty();
            pageGo = getAuditSup;
        }else if(index == 1){
            getOverdueCert();
            pageGo = getOverdueCert;
        }
    })
}

function getAuditSup(page){
	page = page || 1;
	var html = "", pageSize = 10;
	$("#audit-list").empty();
	removeInfo();
    ajax("/32200201?t="+Math.random(),{"pageCurrent":page,"pageLimit":pageSize},function(data){
        if(data.code == 0){
			if(data.result.list.length>0){
				 $.each(data.result.list,function(index, item){
		            	var showName = item.ppp == 'a' ? item.k02_zjbh : item.k03_zjlb;
		            	var puk = item.ppp == '8' || item.ppp == '9' ? item.fb5 : item.puk;
		            	var url = codeToUrl(item.ppp) + "?pukid=" + puk;
		            	
		            	html +="<tr>\n\
		                    <td>" + showName + (item.fb4 > 0 ? '<br />'+item.fb4:'') + "</td>\n\
		                    <td>" + item.f04_yxksrq + "至" + item.f05_yxzzrq + "</td>\n\
		                    <td class=\"gray\">已过期 <span class=\"font16\">" + (item.ddd * -1) + "</span> 天</td>\n\
		                    <td class=\"gray\"><a href=\"javascript:showLayer(\'" + url +"\', 800, true);\" class=\"btn\">维护</a></td>\n\
		                </tr>";
		            });
				 $("#audit-list").html(html);
				 $("#gqzj").text(data.result.list.length);
			}else{
				 $("#audit-list").info("暂无数据.");
			}
            var newpage = new createPage(data.result.pageCount,page,pageSize,3);
            $("#audit-pages").html(newpage.pageHtml);
            hoverTable(".hoverTable",true,true);

        }
    });
}

function getOverdueCert(page){
	page = page || 1;
	var html = "", pageSize = 10;
	$("#over-cert-list").empty();
	removeInfo();
    ajax("/32200202?t="+Math.random(),{"pageCurrent":page,"pageLimit":pageSize},function(data){
        if(data.code == 0){
        	if(data.result.list.length>0){
        		$.each(data.result.list,function(index, item){
                	var showName = item.ppp == 'a' ? item.k02_zjbh : item.k03_zjlb;
                	var puk = item.ppp == '8' || item.ppp == '9' ? item.fb5 : item.puk;
                	var url = codeToUrl(item.ppp) + "?pukid=" + puk;
                    html +="<tr>\n\
                        <td>" + showName + (item.fb4 > 0 ? '<br />'+item.fb4:'') + "</td>\n\
                        <td>" + item.f04_yxksrq + "至" + item.f05_yxzzrq + "</td>\n\
                        <td class=\"gray\">还有 <span class=\"font16\">" + item.ddd + "</span> 天过期</td>\n\
                        <td class=\"gray\"><a href=\"javascript:showLayer(\'" + url +"\', 800, true);\" class=\"btn\">维护</a></td>\n\
                    </tr>";
                });
        		 $("#over-cert-list").html(html);
       		     $("#jjgq").text(data.result.list.length);
        	}else{
        		 
        		$("#over-cert-list").info("暂无数据.");
        	}
            var newpage = new createPage(data.result.pageCount,page,pageSize,3);
            $("#over-pages").html(newpage.pageHtml);
            
            hoverTable(".hoverTable",true,true);
        }
    });
}

function codeToUrl(code) {
	var url = "";
	if (code == '1') {
        url = "/3110100";
    } else if (code == '2'){
        url = "/3110100";
    } else if (code == '3'){
        url = "/3110100";
    } else if (code == '4'){
        url = "/31104001";
    } else if (code == '5'){
        url = "/31105001";
    } else if (code == '6'){
        url = "/31106001";
    } else if (code == '7'){
        url = "";
    } else if (code == '8'){
        url = "/33010001";
    } else if (code == '9'){
        url = "/33010001";
    } else if (code == 'a'){
        url = "/311100001/2";
    } else if (code == 'b'){
        url = "";
    } else if (code == 'c'){
        url = "";
    }
	return url
}

</script>
</head>
<body>
<div class="contnet">
    <div class="boxContent">
        <div class="tagBox">
            <div class="tag {movetag:'span',showtag:'.tagBox .tagNav',addsclass:'tagnSelect',addtclass:'tagSelect',time:9999999}"> 
                <span class="tagSelect">过期证件一览<em id="gqzj"></em></span>
                <span>即将过期证件<em id="jjgq"></em></span>
            </div>
            <div class="tagBox">
                <div class="tagNav tagnSelect">
                    <div class="tablelist mTop10" style="height:450px;">
                        <table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
                            <thead>
                                <tr>
                                    <th>过期内容</th>
                                    <th>有效时间</th>
                                    <th>过期天数</th>
                                    <th width="15%">操作</th>
                                </tr>
                            </thead>
                            <tbody id="audit-list">
                            </tbody>
                        </table>
                    </div>
                    <div id="audit-pages" class="pages"></div>
                </div>
                <!-- <div class="tagNav">
                    <div class="tablelist" style="height:450px;">
                        <table class="table hoverTable auditCertTable" width="100%" cellspacing="0" cellpadding="0" border="0">
                            <thead>
                                <tr>
                                    <th width="6%"></th>
                                    <th></th>
                                    <th></th>
                                    <th width="10%"></th>
                                    <th width="10%"></th>
                                </tr>
                            </thead>
                            <tbody id="audit-cert-list">
                            </tbody>
                        </table>
                    </div>
                    <div id="cert-pages" class="pages"></div>
                </div> -->
                <div class="tagNav">
                    <div class="tablelist mTop10" style="height:450px;">
                        <table class="table hoverTable" width="100%" cellspacing="0" cellpadding="0" border="0">
                            <thead>
                                <tr>
                                    <th>过期内容</th>
                                    <th>有效时间</th>
                                    <th>距离过期天数</th>
                                    <th width="15%">操作</th>
                                </tr>
                            </thead>
                            <tbody id="over-cert-list">
                            </tbody>
                        </table>
                    </div>
                    <div id="over-pages" class="pages"></div>
                </div>
            </div>
        </div>

        <!-- <ul class="subNav subNavLH clearfix2">
            <li><a href="javascript:void(0);" onclick="getPage(this, 'audit_supplier.html');" class="active">待审核供应商</a></li>
            <li><a href="javascript:void(0);" onclick="getPage(this, 'audit_cert.html');">待审核换证信息</a></li>
            <li><a href="javascript:void(0);" onclick="getPage(this, '');">已过期证件</a></li>
        </ul>
        <div class="boxPContent" id="contentBox"></div> -->
    </div>
</div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>