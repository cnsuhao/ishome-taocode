<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>规格维护</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
</head>
<body>
    <div class="container-fluid">
        <div class="panel panel-default mt20">
            <!-- 导航按钮 -->
            <div class="panel-heading tc">
                <a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;">
                    <span class="glyphicon glyphicon-arrow-left"></span>
                </a>
                产品信息-比较
            </div>
            <!-- 主体 -->
            <div class="panel-body">
                <form class="form-horizontal form-edit form-read submit-form" role="form" action="/39207002" method="post" id="myform">
                    <div class="form-group table-title">
                        <label class="col-sm-3 control-label label-title">&nbsp;</label>
                        <label class="col-sm-4 control-label label-content">修改前内容</label>
                        <label class="col-sm-4 control-label label-content">修改后内容</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">证件编号：</label>
                        <label class="col-sm-4 control-label label-content">${left.f01_zczzwmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f01_zczzwmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">REG,NO：</label>
                        <label class="col-sm-4 control-label label-content">${left.f02_zczywmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f02_zczywmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">产品中文名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f03_cpzwmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f03_cpzwmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">产品英文名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f04_cpywmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f04_cpywmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">发证国家名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f05_fzgjmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f05_fzgjmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">发证单位名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f06_fzdwmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f06_fzdwmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">发证日期：</label>
                        <label class="col-sm-4 control-label label-content">${left.f07_fzrq }</label>
                        <label class="col-sm-4 control-label label-content">${right.f07_fzrq }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">有效年限：</label>
                        <label class="col-sm-4 control-label label-content">${left.f08_yxnx }</label>
                        <label class="col-sm-4 control-label label-content">${right.f08_yxnx }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">有效开始时间：</label>
                        <label class="col-sm-4 control-label label-content">${left.f09_yxksrq }</label>
                        <label class="col-sm-4 control-label label-content">${right.f09_yxksrq }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">有效结束时间：</label>
                        <label class="col-sm-4 control-label label-content">${left.f10_yxzzrq }</label>
                        <label class="col-sm-4 control-label label-content">${right.f10_yxzzrq }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">是否进口：</label>
                        <label class="col-sm-4 control-label label-content"></label>
                        <label class="col-sm-4 control-label label-content"></label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">是否停用：</label>
                        <label class="col-sm-4 control-label label-content"></label>
                        <label class="col-sm-4 control-label label-content"></label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">产品执行标准编号：</label>
                        <label class="col-sm-4 control-label label-content">${left.f17_cpzxbzbh }</label>
                        <label class="col-sm-4 control-label label-content">${right.f17_cpzxbzbh }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">产品性能结构及组成：</label>
                        <label class="col-sm-4 control-label label-content">${left.f18_cpxnjgjzc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f18_cpxnjgjzc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">注册地址所在国家：</label>
                        <label class="col-sm-4 control-label label-content">${left.f21_zcdzszgj }</label>
                        <label class="col-sm-4 control-label label-content">${right.f21_zcdzszgj }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">注册代理机构名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f23_zcdljgmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f23_zcdljgmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">注产品适用范围：</label>
                        <label class="col-sm-4 control-label label-content">${left.f19_cpsyfw }</label>
                        <label class="col-sm-4 control-label label-content">${right.f19_cpsyfw }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">售后服务机构名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f25_shfwjgmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f25_shfwjgmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">售后服务热线：</label>
                        <label class="col-sm-4 control-label label-content">${left.f26_shfwrx }</label>
                        <label class="col-sm-4 control-label label-content">${right.f26_shfwrx }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">售后服务地址：</label>
                        <label class="col-sm-4 control-label label-content">${left.f27_shfwdz }</label>
                        <label class="col-sm-4 control-label label-content">${right.f27_shfwdz }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">注意事项：</label>
                        <label class="col-sm-4 control-label label-content">${left.f29_zysx }</label>
                        <label class="col-sm-4 control-label label-content">${right.f29_zysx }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产厂家名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.k04_scqyid }</label>
                        <label class="col-sm-4 control-label label-content">${right.k04_scqyid }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业联系人名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f38_scqylxrxm }</label>
                        <label class="col-sm-4 control-label label-content">${right.f38_scqylxrxm }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业联系人名称：</label>
                        <label class="col-sm-4 control-label label-content"></label>
                        <label class="col-sm-4 control-label label-content"></label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业英文名称：</label>
                        <label class="col-sm-4 control-label label-content">${left.f36_scqyywmc }</label>
                        <label class="col-sm-4 control-label label-content">${right.f36_scqyywmc }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业地质：</label>
                        <label class="col-sm-4 control-label label-content">${left.f33_scqydz }</label>
                        <label class="col-sm-4 control-label label-content">${right.f33_scqydz }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业邮编：</label>
                        <label class="col-sm-4 control-label label-content">${left.f34_scqyyb }</label>
                        <label class="col-sm-4 control-label label-content">${right.f34_scqyyb }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业电话号码：</label>
                        <label class="col-sm-4 control-label label-content">${left.f35_scqydhhm }</label>
                        <label class="col-sm-4 control-label label-content">${right.f35_scqydhhm }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业联系人邮箱：</label>
                        <label class="col-sm-4 control-label label-content">${left.f39_scqylxraqyx }</label>
                        <label class="col-sm-4 control-label label-content">${right.f39_scqylxraqyx }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">生产企业联系人电话：</label>
                        <label class="col-sm-4 control-label label-content">${left.f40_scqylxrdh }</label>
                        <label class="col-sm-4 control-label label-content">${right.f40_scqylxrdh }</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label label-title">备注：</label>
                        <label class="col-sm-4 control-label label-content">${left.f30_bz }</label>
                        <label class="col-sm-4 control-label label-content">${right.f30_bz }</label>
                    </div>
                    <div class="form-result">
                        <label class="col-sm-3 control-label label-title">理由</label>
                        <div>
                            <textarea cols="50" rows="3" name="n04_shly"></textarea>
                        </div>
                    </div>
                    <div class="form-group btn-form-group">
                        <label class="col-sm-3 control-label"></label>
                        <div class="col-sm-4">
                            <input type="hidden" name="puk" value="${right.puk }">
                            <button type="button" onclick="formSub('0')" class="btn btn-primary" >通过</button>
                            <button type="button" onclick="formSub('1')" class="btn btn-warning" >拒绝</button>
                        </div>
                    </div>
                </form>
            </div><!-- end-body -->
        </div>
    </div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
<script type="text/javascript">
function formSub(type) {
	$('#myform').attr('action', '/39207002/' + type);
	var formObj = document.getElementById("myform");
	formObj.submit();
}
</script>
</html>