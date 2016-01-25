<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>产品比较</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;" onclick="post('/39205001')">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				产品信息-比较
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit form-read submit-form" role="form" action="/39205003" method="post" id="myform">
					<input type="hidden" name="puk" value="${newobj.puk }">
					
					<div class="form-group table-title">
						<label class="col-sm-2 control-label label-title">&nbsp;</label>
						<label class="col-sm-4 control-label label-content">修改前内容</label>
						<label class="col-sm-4 control-label label-content">修改后内容</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">产品名称：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f01_cpqc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f01_cpqc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">品牌：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f05_ppqc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f05_ppqc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">注册证编号：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f07_zczbh }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f07_zczbh }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">注册证名称：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f08_zczmc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f08_zczmc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">有效开始时间：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f09_zczyxks }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f09_zczyxks }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">有效结束时间：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f10_zczyxzj }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f10_zczyxzj }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">生产厂家：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f11_sccjmc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f11_sccjmc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">产地：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f13_scdz }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f13_scdz }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">产品线：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f17_cpxmc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f17_cpxmc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">全国总代理：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f06_qgzdlgysmc }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f06_qgzdlgysmc }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">物资属性：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f18_cpsx }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f18_cpsx }</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">包装单位：</label>
						<label class="col-sm-4 control-label label-content">${oldobj.f24_bzdw }</label>
						<label class="col-sm-4 control-label label-content">${newobj.f24_bzdw }</label>
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