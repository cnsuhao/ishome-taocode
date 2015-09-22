<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>新增产品</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<body>
	<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:post('/39107001');">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				新增产品
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit submit-form" role="form" action="/39107003" method="post" id="myform">
					<input type="hidden" name="puk" value="${UserData.puk}">
					<input type="hidden" id="token" value="${loginer.token}">
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"><em>*</em>产品名称</label>
						<div class="col-sm-4">
							<input type="text" vd-key="nonempty" class="form-control" id="f01_cpqc" name="f01_cpqc" value="${UserData.f01_cpqc}"/>
						</div>
						<label class="col-sm-2 control-label"><em>*</em>品牌选择</label>
						<div class="col-sm-4">
							<input type="text" vd-key="nonempty" class="form-control" id="f05_ppqc" name="f05_ppqc" value="${UserData.f05_ppqc}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"><em>*</em>注册证编号</label>
						<div class="col-sm-10">
							<input type="text" vd-key="nonempty" class="form-control" id="f07_zczbh" name="f07_zczbh" value="${UserData.f07_zczbh}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"><em>*</em>注册证名称</label>
						<div class="col-sm-10">
							<input type="text" vd-key="nonempty" class="form-control" id="f08_zczmc" name="f08_zczmc" value="${UserData.f08_zczmc}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"><em>*</em>有效开始时间</label>
						<div class="col-sm-4">
							<input type="text" vd-key="nonempty" class="form-control startDate" id="f09_zczyxks" name="f09_zczyxks" value="${UserData.f09_zczyxks}"/>
						</div>
						<label class="col-sm-2 control-label"><em>*</em>有效结束时间</label>
						<div class="col-sm-4">
							<input type="text" vd-key="nonempty" class="form-control endDate" id="f10_zczyxzj" name="f10_zczyxzj" value="${UserData.f10_zczyxzj}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"><em>*</em>生产厂家</label>
						<div class="col-sm-10">
							<input type="text" vd-key="nonempty" class="form-control" id="f11_sccjmc" name="f11_sccjmc" value="${UserData.f11_sccjmc}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-3">
							<input type="text" vd-key="nonempty" placeholder="产地" class="form-control" id="f13_scdz" name="f13_scdz" value="${UserData.f13_scdz}"/>
						</div>
						<div class="col-sm-3">
							<input type="text" vd-key="nonempty" placeholder="产品线" class="form-control" id="f17_cpxmc" name="f17_cpxmc" value="${UserData.f17_cpxmc}"/>
						</div>
						<div class="col-sm-4">
							<input type="text" vd-key="nonempty" placeholder="分类" class="form-control" id="fb1" name="fb1" value="${UserData.fb1}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label">全国总代理</label>
						<div class="col-sm-4">
							<select class="form-control" id="f06_qgzdlgysmc" name="f06_qgzdlgysmc" value="${UserData.f06_qgzdlgysmc}"></select>
						</div>
						<!-- <div class="col-sm-4">
							<button type="button" id="selSup" class="btn btn-default">供货商选择</button>
							<a href="javascript:post('/39107009')" id="selSup" class="btn btn-default">供货商选择</a>
						</div> -->
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label">服务热线</label>
						<div class="col-sm-4">
							<input type="text" readonly class="form-control" id="f14_fwrx" name="f14_fwrx" value="${UserData.f14_fwrx}"/>
						</div>
						<label class="col-sm-2 control-label">售后电话</label>
						<div class="col-sm-4">
							<input type="text" readonly class="form-control" id="f15_shdh" name="f15_shdh" value="${UserData.f15_shdh}"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label">物资属性</label>
						<div class="col-sm-10">
							<div class="checkbox">
					            <label>
					              	<input type="checkbox" id="1" name="f18_cpsx" value="${UserData.f18_cpsx}"> 一次性耗材
					            </label>
					        </div>
					        <div class="checkbox">
					            <label>
					              	<input type="checkbox" id="2" name="f18_cpsx" value="${UserData.f18_cpsx}"> 植入性耗材
					            </label>
					        </div>
					        <div class="checkbox">
					            <label>
					              	<input type="checkbox" id="3" name="f18_cpsx" value="${UserData.f18_cpsx}"> 检验试剂耗材
					            </label>
					        </div>
					        <div class="checkbox">
					            <label>
					              	<input type="checkbox" id="4" name="f18_cpsx" value="${UserData.f18_cpsx}"> 计量物品
					            </label>
					        </div>
					        <div class="checkbox">
					            <label>
					              	<input type="checkbox" id="5" name="f18_cpsx" value="${UserData.f18_cpsx}"> 其他
					            </label>
					        </div>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label">包装单位</label>
						<div class="col-sm-4">
							<select class="form-control" id="f24_bzdw" name="f24_bzdw" value="${UserData.f24_bzdw}"></select>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label"></label>
						<label class="col-sm-3">当前使用规格数目：<span>5</span></label>
						<label class="col-sm-3">登记规格数目：<span>25</span></label>
						<div class="col-sm-4">
							<!-- <button type="submit" id="selSpec" class="btn btn-default">规格选择</button> -->
						    <!-- <a href="javascript:post('/39107008')" id="selSup" class="btn btn-default">规格选择</a> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-1">
							<button type="reset" class="btn btn-warning">重置</button>
						</div>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary">保存</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
	<script>
	$(function(){
		// 供货商选择
		//$('#selSup').on('click', function(){
			//var mode = top.showWindow('选择供货商', '/39107007', 'big', 400, false, function(){}, false);
		//});
	});
	</script>
</body>
</html>
<%@ include file="/resources/jsp/formJS.jsp" %>