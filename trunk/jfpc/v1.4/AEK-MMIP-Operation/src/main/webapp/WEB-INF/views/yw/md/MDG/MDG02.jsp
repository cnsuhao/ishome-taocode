<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>注册证列表</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
</head>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css">
<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
<body>
    <div class="container-fluid">
        <div class="panel panel-default mt20">
            <!-- 导航按钮 -->
            <div class="panel-heading tc">
                <a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;">
                    <span class="glyphicon glyphicon-arrow-left"></span>
                </a>
        <c:if test="${UserData.puk == null || UserData.puk == '' }">
                新增注册证
        </c:if>
        <c:if test="${UserData.puk != null && UserData.puk != ''}">
                编辑注册证
        </c:if>
            </div>
            <!-- 主体 -->
            <div class="panel-body">
                <form class="form-horizontal form-edit submit-form" role="form" action="/391180003" method="post" id="myform">
                    <input type="hidden" name="puk" value="${UserData.puk }">
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>注册号</label>
                        <div class="col-sm-9">
                            <input type="text" vd-key="nonempty" class="form-control" name="f01_zczzwmc" value="${UserData.f01_zczzwmc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">REG,NO</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="f02_zczywmc" value="${UserData.f02_zczywmc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>产品中文名称</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f03_cpzwmc" value="${UserData.f03_cpzwmc }"/>
                        </div>
                        <label class="col-sm-3 control-label">产品英文名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f04_cpywmc" value="${UserData.f04_cpywmc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>发证国家名称</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f05_fzgjmc" value="${UserData.f05_fzgjmc }"/>
                        </div>
                        <label class="col-sm-3 control-label"><em>*</em>发证单位名称</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f06_fzdwmc" value="${UserData.f06_fzdwmc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>发证日期</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control form_datetime" name="f07_fzrq" readonly value="${UserData.f07_fzrq }"/>
                        </div>
                        <label class="col-sm-3 control-label"><em>*</em>有效年限</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f08_yxnx" value="${UserData.f08_yxnx }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>有效开始时间</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control startDate" name="f09_yxksrq" readonly value="${UserData.f09_yxksrq }"/>
                        </div>
                        <label class="col-sm-3 control-label"><em>*</em>有效结束时间</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control endDate" name="f10_yxzzrq" readonly value="${UserData.f10_yxzzrq }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">是否进口</label>
                        <div class="col-sm-3">
                            <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-primary active">
                                    <input type="radio" name="f16_jkbz" id="option1" autocomplete="off" value="0" <c:if test="${UserData.f16_jkbz == 0 }">checked</c:if>>是</label>
                                <label class="btn btn-primary">
                                    <input type="radio" name="f16_jkbz" id="option2" autocomplete="off" value="1" <c:if test="${UserData.f16_jkbz == 1 }">checked</c:if>>否</label>
                            </div>
                        </div>
                        <label class="col-sm-3 control-label">是否停用</label>
                        <div class="col-sm-3">
                            <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-primary active">
                                    <input type="radio" name="f15_sfty" id="option1" autocomplete="off" value="0" <c:if test="${UserData.f15_sfty == 0 }">checked</c:if>>是</label>
                                <label class="btn btn-primary">
                                    <input type="radio" name="f15_sfty" id="option2" autocomplete="off" value="1" <c:if test="${UserData.f15_sfty == 1 }">checked</c:if>>否</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">产品执行标准编号</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f17_cpzxbzbh" value="${UserData.f17_cpzxbzbh }"/>
                        </div>
                        <label class="col-sm-3 control-label">产品性能结构及组成</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f18_cpxnjgjzc" value="${UserData.f18_cpxnjgjzc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">注册地址所在国家</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f21_zcdzszgj" value="${UserData.f21_zcdzszgj }"/>
                        </div>
                        <label class="col-sm-3 control-label">注册代理机构名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f23_zcdljgmc" value="${UserData.f23_zcdljgmc }"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">产品适用范围</label>
                        <div class="col-sm-9">
                            <textarea type="text" rows="3" class="form-control textarea" name="f19_cpsyfw">${UserData.f19_cpsyfw }</textarea>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">售后服务机构名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f25_shfwjgmc" value="${UserData.f25_shfwjgmc }"/>
                        </div>
                        <label class="col-sm-3 control-label">售后服务热线</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f26_shfwrx" value="${UserData.f26_shfwrx }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">售后服务地址</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f27_shfwdz" value="${UserData.f27_shfwdz }"/>
                        </div>
                        <label class="col-sm-3 control-label">注意事项</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f29_zysx" value="${UserData.f29_zysx }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>生产厂家名称</label>
                        <!-- <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f32_scqyzwmc"/>
                        </div> -->
                        <div class="col-sm-3">
                            <div class="input-group combox" data-t="" data-url="">
                                <input autocomplete="off" vd-key="nonempty"  type="text"class="form-control" name="f32_scqyzwmc" value="${UserData.f32_scqyzwmc }">
                                <input type="hidden" name="k04_scqyid" value="${UserData.k04_scqyid }" />
                                <div class="input-group-btn">
                                    <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                </div>
                            </div>
                        </div>
                        <label class="col-sm-3 control-label"><em>*</em>生产企业联系人名称</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f38_scqylxrxm" value="${UserData.f38_scqylxrxm }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">生产企业英文名称</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f36_scqyywmc" value="${UserData.f36_scqyywmc }"/>
                        </div>
                        <label class="col-sm-3 control-label">生产企业地址</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f33_scqydz" value="${UserData.f33_scqydz }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">生产企业邮编</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f34_scqyyb" value="${UserData.f34_scqyyb }"/>
                        </div>
                        <label class="col-sm-3 control-label">生产企业电话号码</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f35_scqydhhm" value="${UserData.f35_scqydhhm }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label">生产企业联系人邮箱</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f39_scqylxraqyx" value="${UserData.f39_scqylxraqyx }"/>
                        </div>
                        <label class="col-sm-3 control-label">生产企业联系人电话</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="f40_scqylxrdh" value="${UserData.f40_scqylxrdh }"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="3" name="f30_bz">${UserData.f30_bz }</textarea>
                        </div>
                    </div>
                    <div class="fileupload-group">
                        <label class="col-sm-12 control-label">上传文件</label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"></label>
                        <div class="col-sm-3">
                            <button type="submit" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-warning">重置</button>
                        </div>
                    </div>
                </form>
            </div><!-- end-body -->
        </div>
    </div>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>