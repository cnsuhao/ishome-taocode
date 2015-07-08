<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>添加字典</title>
<%@ include file="/resources/jsp/themeCSS.jsp"%>
<%@ include file="/resources/jsp/themeJS.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" role="form" action="/31608001"
			method="post" id="form1" enctype="multipart/form-data">
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">排列顺序</label>
				<div class="col-sm-4">
					<input type="text" id="f02_pxwz"
						class="form-control col-xs-13 col-sm-12" name="f02_pxwz"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">大分类编号</label>
				<div class="col-sm-4">
					<input type="text" id="k01_dflid"
						class="form-control col-xs-13 col-sm-12" name="k01_dflid"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">大分类名称</label>
				<div class="col-sm-4">
					<input type="text" id="n01_dflmc"
						class="form-control col-xs-13 col-sm-12" name="n01_dflmc"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">中分类编号</label>
				<div class="col-sm-4">
					<input type="text" id="k02_zflid"
						class="form-control col-xs-13 col-sm-12" name="k02_zflid"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">中分类名称</label>
				<div class="col-sm-4">
					<input type="text" id="n02_zflmc"
						class="form-control col-xs-13 col-sm-12" name="n02_zflmc"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">小分类编号</label>
				<div class="col-sm-4">
					<input type="text" id="k03_xflid"
						class="form-control col-xs-13 col-sm-12" name="k03_xflid"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">小分类名称</label>
				<div class="col-sm-4">
					<input type="text" id="n03_xflmc"
						class="form-control col-xs-13 col-sm-12" name="n03_xflmc"
						check-type="required" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right"
					for="form-field-1">字典名称</label>
				<div class="col-sm-4">
					<input type="text" id="f01_bt"
						class="form-control col-xs-13 col-sm-12" name="f01_zdmc"
						check-type="required" />
				</div>
			</div>																						

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="submit1" class="btn btn-primary col-sm-4">
						<i class="icon-ok bigger-110"></i>添加
					</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="/resources/bootstrap-3.2.0-dist/js/bootstrap3-validation.js"></script>
	<script type="text/javascript">
		function demo() {
			var txt = $("#editor1").html();
			var t = $("#editor1").text();
		}
		$(function() {
			$("#form1").validation();
			$("#submit1").on('click', function(event) {
				if ($("#form1").valid(this, "信息填写不完整！") == false) {
					return false;
				}
			});
		})
	</script>
</body>
</html>