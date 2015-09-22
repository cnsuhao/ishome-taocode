<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>品牌比较</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;" onclick="post('/39211001')">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				品牌-比较
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit form-read submit-form" role="form" action="/39211003" method="post" id="myform">
					<input type="hidden" name="puk" value="${newobj.puk }">
					
					<div class="form-group table-title">
						<label class="col-sm-2 control-label label-title">&nbsp;</label>
						<label class="col-sm-4 control-label label-content">修改前内容</label>
						<label class="col-sm-4 control-label label-content">修改后内容</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌全称：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f01_ppqc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f01_ppqc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">生产厂商：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f01_ppqc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f01_ppqc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">所在国家：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.s12_ssgj }</label>
						<label class="col-sm-4 control-label label-content">${newobj.s12_ssgj }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">厂家地址：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f16 }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f16 }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">厂家负责人：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f17 }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f17 }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">售后服务热线：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f11_shfwrx }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f11_shfwrx }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌种类：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f03_ppzl }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f03_ppzl }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌知名度：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f04_ppzmd }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f04_ppzmd }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌所属环节</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f05_ppsshj }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f05_ppsshj }</label>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌来源：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f06_pply }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f06_pply }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌生命周期：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f07_ppsmzq }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f07_ppsmzq }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌内外销</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f08_ppnwx }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f08_ppnwx }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌所属行业：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f09_ppssxy }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f09_ppssxy }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌负责人：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f10_ppfzr }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f10_ppfzr }</label>
					</div>
					<div class="form-result">
						<label class="col-sm-2 control-label label-title">理由</label>
						<div>
							<textarea cols="50" rows="3" name="n04_shly"></textarea>
						</div>
					</div>
					<div class="form-group btn-form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary" onclick="msub({n03_shzt:0})">通过</button>
							<button type="reset" class="btn btn-warning" onclick="msub({n03_shzt:1})">拒绝</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
</body>
<script type="text/javascript">
	function setParentFrameHeight(){
		if(window.parent && window.parent.setIframeHeihgt){
			window.parent.setIframeHeihgt($("html").height());
		}
	}
	$(function(){
		setParentFrameHeight();
	});
	
	function msub(PARAMS){
		
		var temp = document.getElementById("myform");
		for ( var x in PARAMS) {
			var opt = document.createElement("input");
			opt.type="hidden";
			opt.name = x;
			opt.value = PARAMS[x];
			// alert(opt.name)        
			temp.appendChild(opt);
		}
		document.body.appendChild(temp);
		temp.submit();
		return false;
	}
</script>
</html>