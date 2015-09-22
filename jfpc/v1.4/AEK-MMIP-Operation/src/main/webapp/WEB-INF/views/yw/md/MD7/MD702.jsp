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
                <c:if test="${UserData.puk == null || UserData.puk == '' }">
                新增规格
                </c:if>
                <c:if test="${UserData.puk != null && UserData.puk != ''}">
                编辑规格
                </c:if>
            </div>
            <!-- 主体 -->
            <div class="panel-body">
                <form class="form-horizontal form-edit submit-form" role="form" action="/39108002" method="post" id="myform">
                	<input type="hidden" name="puk" value="${UserData.puk }">
                	<input type="hidden" name="k01_cpid" value="${k01_cpid }">
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>规格全称</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f01_ggqc" value="${UserData.f01_ggqc }"/>
                        </div>
                        <label class="col-sm-3 control-label"><em>*</em>规格条码</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="k03_ggtm" value="暂无内容！"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>产品主编号</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f03_cpzbh" value="${UserData.f03_cpzbh }"/>
                        </div>
                        <label class="col-sm-3 control-label"><em>*</em>产品次编号</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f04_cpcbh" value="${UserData.f04_cpcbh }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-3 control-label"><em>*</em>产品型号</label>
                        <div class="col-sm-3">
                            <input type="text" vd-key="nonempty" class="form-control" name="f05_cpxh" value="${UserData.f05_cpxh }"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">中文规格描述</label>
                        <div class="col-sm-9">
                            <textarea type="text" rows="3" class="form-control textarea" name="f06_zwggms">${UserData.f06_zwggms }</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">英文规格描述</label>
                        <div class="col-sm-9">
                            <textarea type="text" rows="3" class="form-control textarea" name="f07_ywggms">${UserData.f07_ywggms }</textarea>
                        </div>
                    </div>
                    <!-- 外观 -->
                    <div class="form-group">
                        <label class="col-sm-3 control-label">外观</label>
                        <div class="col-sm-9 well well-sm">
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">材质</label>
                                <div class="col-sm-4">
                                    <select  class="form-control" name="f08_cl" id="" value="${UserData.f08_cl }"></select>
                                </div>
                                <label class="col-sm-2 control-label">颜色</label>
                                <div class="col-sm-4">
                                    <select  class="form-control" name="f09_ys" id="" value="${UserData.f09_ys }"></select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">误差</label>
                                <div class="col-sm-4">
                                    <select  class="form-control" name="f10_wc" id="" value="${UserData.f10_wc }"></select>
                                </div>
                                <label class="col-sm-2 control-label">计量单位</label>
                                <div class="col-sm-4">
                                    <select  class="form-control" name="f11_jldw" id=""></select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">长度</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f12_cd" value="${UserData.f12_cd }"/>
                                </div>
                                <label class="col-sm-2 control-label">宽度</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f13_kd" value="${UserData.f13_kd }"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">高度</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f14_gd" value="${UserData.f14_gd }"/>
                                </div>
                                <label class="col-sm-2 control-label">厚度</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f15_hd" value="${UserData.f15_hd }"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">内直径</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f16_nzj" value="${UserData.f16_nzj }"/>
                                </div>
                                <label class="col-sm-2 control-label">外直径</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f17_wzj" value="${UserData.f17_wzj }"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">孔数</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f18_ks" value="${UserData.f18_ks }"/>
                                </div>
                                <label class="col-sm-2 control-label">重量</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f19_zl" value="${UserData.f19_zl }"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">形状</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f20_xz" value="${UserData.f20_xz }"/>
                                </div>
                                <label class="col-sm-2 control-label">患处</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="f21_syhc" value="${UserData.f21_syhc }"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end外观 -->
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使用范围</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="3" name="f22_syfw">${UserData.f22_syfw }</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注说明</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" rows="3" name="bbb">${UserData.bbb }</textarea>
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