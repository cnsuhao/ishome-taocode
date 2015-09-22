<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>行政机构单位基本信息</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;" onclick="post('/392040001')">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				行政机构单位-审核
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit submit-form" role="form" action="/392040003" method="post" id="myform">
					<input type="hidden" name="puk" value="${UserData.puk }"/>
					<div class="form-group">
						<label class="col-sm-2 control-label">国家机关编号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="k01_gjjgbh">
						</div>
						<label class="col-sm-2 control-label"><em></em>国家机关类别</label>
						<div class="col-sm-3">
							<select class="form-control"  vd-key="nonempty" name="k02_jgxzlb">
							  <option>类别1</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>单位全称</label>
						<div class="col-sm-9">
							<input type="text" class="form-control"  vd-key="nonempty" name="f01_jgqc" value="${UserData.f01_jgqc }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">所在地</label>
						<div class="col-sm-3">
							<select class="form-control" name="f03_szs" id="f03_szs">
							</select>
						</div>
						<div class="col-sm-3">
							<select class="form-control" name="f04_szds" id="f04_szds">
							</select>
						</div>
						<div class="col-sm-3">
							<select class="form-control" name="f05_szqx" id="f05_szqx">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>单位详细地址</label>
						<div class="col-sm-9">
							<input type="text" class="form-control"  vd-key="nonempty" name="f06_szxxdz">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="f07_lxdh">
						</div>
					</div>					
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>服务热线</label>
						<div class="col-sm-4">
							<textarea class="form-control" rows="3" name="f08_fwrx" vd-key="nonempty"></textarea>
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-sm-2 control-label label-title">理由</label>
						<div class="col-sm-6">
							<textarea class="form-control" cols="40" rows="3" name="n04_shly"></textarea>

						</div>
					</div>
					<div class="form-group btn-form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							<button type="button" class="btn btn-primary" onclick="msub({n03_shzt:0})">通过</button>
							<button type="button" class="btn btn-warning" onclick="msub({n03_shzt:1})">拒绝</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
</body>
<script src="/resources/js/address.js"></script>
<script type="text/javascript">
addressInit("f03_szs","f04_szds","f05_szqx");
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