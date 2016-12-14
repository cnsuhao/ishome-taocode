<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>厂商基本信息</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<style>	
		.uploadify{
			margin-top: 10px ;
			width: 130px !important;
		}
		.uploadify object,.uploadify .uploadify-button{
			width: 100% !important;
			cursor: pointer;
		}
		.img-mod .thumbnail{
			width: 165px;
			height: 130px;
		}
		.img-mod .thumbnail img{
			width: 155px;
			height: 120px;
		}

	</style>
<body>
		<div class="container-fluid">
		<div class="panel panel-default mt20">
			<!-- 导航按钮 -->
			<div class="panel-heading tc">
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				新增国家行政机构
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal form-edit submit-form" role="form" action="/391060003" method="post" id="myform">
					<input type="hidden" name="puk" value="${UserData.puk }"/>
					<div class="form-group">
						<label class="col-sm-2 control-label">国家机关编号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="k01_gjjgbh" value="${UserData.k01_gjjgbh }">
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
							<input type="text" class="form-control"  vd-key="nonempty" name="f06_szxxdz" value="${UserData.f06_szxxdz }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="f07_lxdh" value="${UserData.f07_lxdh }">
						</div>
					</div>					
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>服务热线</label>
						<div class="col-sm-4">
							<textarea class="form-control" rows="3" name="f08_fwrx" vd-key="nonempty">${UserData.f08_fwrx}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary" >提交</button>
							<button type="reset" class="btn btn-warning" >清空</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
</body>
<script src="/resources/js/address.js"></script>
<%@ include file="/resources/jsp/formJS.jsp" %>
<script type="text/javascript">
	addressInit("f03_szs","f04_szds","f05_szqx");
</script>
</html>