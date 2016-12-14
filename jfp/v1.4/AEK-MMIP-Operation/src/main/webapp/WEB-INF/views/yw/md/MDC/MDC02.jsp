<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>新增品牌</title>
   <%@ include file="/resources/jsp/yw/inc.jsp" %>
   <link href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
	<script src="/resources/js/address.js"></script>
	<script src="/resources/js/country.js"></script>
	<script src="/resources/uploadify/jquery.uploadify.min.js"></script>
	<script src="/resources/uploadify/override.uploadify.js"></script>
	<link href="/resources/uploadify/uploadify.css" rel="stylesheet">
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
				新增品牌
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal submit-form form-edit" role="form" action="/39113003" method="post" id="myform" enctype="multipart/form-data">
					<input type="hidden" name="puk" value="${UserData.puk}"/>
					<input type="hidden" id="ippath" value="<%=ImagePath%>">
					<div class="form-group">
						<label class="col-sm-2 control-label"><em>*</em>品牌全称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="f01_ppqc" name="f01_ppqc" value="${UserData.f01_ppqc}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">logo</label>
						<div class="col-sm-10 img-upload-group">
							<img src="<%=ImagePath%>/00003010/${UserData.f13_logo_url }/123" alt="" width="130" height="99">
							<input type="file" class="file-botton">
							<input type="hidden" name="f13_logo_url"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em>*</em>生产厂商</label>
						<!-- 自动补全内容Start -->
						<div class="col-sm-4" >
							<div class="input-group combox" data-url="XXXX" data-t="3">
								<input autocomplete="off" type="text" class="form-control" id="f14_csqm" name="f14_csqm" value="${UserData.f14_csqm}">
								<input type="hidden" name="" />
								<div class="input-group-btn">
									<button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
								</div>
							</div>
						</div>
						<!-- 自动补全内容End -->
						<label class="col-sm-2 control-label"><em>*</em>所在国家</label>
						<div class="col-sm-4" >
						    <!-- 使用js库Start -->
							<select class="form-control" id="f12_ssgj" name="f12_ssgj" value="${UserData.f12_ssgj}">
							</select>
							<!-- 使用js库End -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>厂家地址</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" readonly="readonly" id="f16" name="f16" value="${UserData.f16}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>厂家负责人</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" readonly="readonly" id="f17" name="f17" value="${UserData.f17}">
						</div>
						<label class="col-sm-2 control-label"><em></em>售后服务热线</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" readonly="readonly" id="f11_shfwrx" name="f11_shfwrx" value="${UserData.f11_shfwrx}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>品牌种类</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f03_ppzl" name="f03_ppzl" value="${UserData.f03_ppzl}">
							  <option>一次性耗材用品</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
						<label class="col-sm-2 control-label"><em></em>品牌知名度</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f04_ppzmd" name="f04_ppzmd" value="${UserData.f04_ppzmd}">
							  <option>国际品牌</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>品牌所属环节</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f05_ppsshj" name="f05_ppsshj" value="${UserData.f05_ppsshj}">
							  <option>制造商品牌</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
						<label class="col-sm-2 control-label"><em></em>品牌来源</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f06_pply" name="f06_pply" value="${UserData.f06_pply}">
							  <option>自有品牌</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>品牌生命周期</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f07_ppsmzq" name="f07_ppsmzq" value="${UserData.f06_pply}">
							  <option>短期品牌</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
						<label class="col-sm-2 control-label"><em></em>品牌内外销</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f08_ppnwx" name="f08_ppnwx" value="${UserData.f08_ppnwx}">
							  <option>内销品牌</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>品牌所属行业</label>
						<div class="col-sm-4" >
							<select class="form-control" id="f09_ppsshy" name="f09_ppssxy" value="${UserData.f09_ppssxy}">
							  <option>医疗行业</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
						<label class="col-sm-2 control-label"><em></em>品牌负责人</label>
						<div class="col-sm-4" >
							<input type="text" class="form-control" id="f10_ppfzr" name="f10_ppfzr" value="${UserData.f10_ppfzr}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-10">
							<button type="submit" class="btn btn-primary" onclick="msub({f03_shzt:0})">提交</button>
							<button type="reset" class="btn btn-warning" onclick="msub({f03_shzt:1})">清空</button>
						</div>
					</div>
				</form>
			</div><!-- end-body -->
		</div>
	</div>
</body>
</html>
<%@ include file="/resources/jsp/formJS.jsp" %>
<script type="text/javascript">
    countryInit("f12_ssgj");
	$(function(){
		$('.file-botton').each(function(i){
			$(this).attr('id', 'file-btn' + i );
			$(this).uploadify({
	            //指定swf文件
	            'swf': '/resources/uploadify/uploadify.swf',
	            //后台处理的页面
	            'uploader': '/00003010/123',
	            //按钮显示的文字
	            'buttonText': '上传',
	            'height': 25,
	            'width': 40,
	            'fileTypeDesc': 'Image Files',
	            //允许上传的文件后缀
	            'fileTypeExts': '*.gif; *.jpg; *.png',
	            'fileSizeLimit':fileSizeLimit,
	            'simUploadLimit':1,//允许同时上传的个数
	            'queueSizeLimit':1,//当允许多文件生成时，设置选择文件的个数，默认值：999 。
	            'fileObjName': 'file',
	            'auto': true,
	            //设置为true将允许多文件上传
	            'multi': false,
	            'onUploadSuccess':function(file, data, response){
	            	var res = JSON.parse(data);
	                //var imgpath = 'http://192.168.1.57:8080';
	                var id = '/00003010/';
	                var imgCode = res.result;
	              //  var token = "/"+$("#token").val();
	              	var token = "/123";
	                var imgPath = ippath + id + imgCode + token;
	                var $group = $(this.button).closest('.img-upload-group');
	                $group.find('img').attr('src', imgPath);
	                $group.find('a').val(imgPath);
	                $group.find('input[type=hidden]').val(imgCode);
	            },
	            'onInit': loadingStartFun,
	            'onSelect':showLoad,
	            'queueID':'queuelist',
	            'onQueueComplete':loadSuccess
	        });
		})	

	})

</script>