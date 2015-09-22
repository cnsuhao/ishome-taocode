<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>供应商基本信息</title>
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
				<a type="button" class="btn btn-default fl-l btn-xs" href="javascript:post('/391020001');">
					<span class="glyphicon glyphicon-arrow-left"></span>
				</a>
				供应商基本信息
			</div>
			<!-- 主体 -->
			<div class="panel-body">
				<form class="form-horizontal submit-form form-edit" role="form" action="/391020003" method="post" id="myform">
					<input type="hidden" name="puk" value="${UserData.puk }">
					<input type="hidden" id="ippath" value="<%=ImagePath%>">
					<input type="hidden" id="token" value="${loginer.token}">
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>供应商全称</label>
						<div class="col-sm-10">
							<input type="text" vd-key="nonempty" class="form-control" name="f01_qyqc" value="${UserData.f01_qyqc }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">logo</label>
						<div class="col-sm-10 img-upload-group">
							<img src="<%=ImagePath%>/00003030/${UserData.f19_logo_url }/123" alt="" width="130" height="99">
							<input type="file" class="file-botton">
							<input type="hidden" name="f19_logo_url"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">官网地址</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="f03_gwdz" value="${UserData.f03_gwdz }">
						</div>
						<label class="col-sm-2 control-label">成立日期</label>
						<div class="col-sm-4" >
						    <input class="form-control form_datetime" size="16" type="text" name="f04_clrq" value="${UserData.f04_clrq }">
						</div> 
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>法人姓名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" vd-key="nonempty" name="f06_frxm" value="${UserData.f06_frxm }">
						</div>
						<label class="col-sm-2 control-label">身份证号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control"  name="f07_frsfzid" value="${UserData.f07_frsfzid }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">付款方式</label>
						<div class="col-sm-4" >
							<select class="form-control" name="f08_fkfs">
								<option value="1">现金支付</option>
								<option value="2">财务支票</option>
								<option value="3">银行转账</option>
								<option value="4">银行转账</option>
							</select>
						</div>
						<label class="col-sm-2 control-label">开户银行</label>
						<div class="col-sm-4" >
							<input type="text" class="form-control" name="f09_khyx" value="${UserData.f09_khyx }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">开户银行账号</label>
						<div class="col-sm-4" >
							<input type="text" class="form-control"  name="f10_khyxzh" value="${UserData.f10_khyxzh }">
						</div>
						<label class="col-sm-2 control-label"><em></em>所在国家</label>
						<div class="col-sm-4" >
							<select class="form-control" vd-key="nonempty" name="f27_qyszgj" id="f27_qyszgj">
							</select>
						</div>
					</div>
				<!-- 	<div class="form-group">
						<label class="col-sm-2 control-label">单位全称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" >
						</div>
					</div> -->
					<div class="form-group">
						<label class="col-sm-2 control-label"><em></em>所在地</label>
						<div class="col-sm-4" >
							<select class="form-control" vd-key="nonempty" id="f11_szs" name="f11_szs">
							</select>
						</div>
						<div class="col-sm-3">
							<select class="form-control" vd-key="nonempty" id="f12_szds" name="f12_szds">
							</select>
						</div>
						<div class="col-sm-3">
							<select class="form-control" vd-key="nonempty" id="f13_szqx" name="f13_szqx">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">所在详细地址</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="f14_szxxdz" value="${UserData.f14_szxxdz }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">办公详细地址</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="f15_bgxxdz" value="${UserData.f15_bgxxdz }">
						</div>
					</div>					
					<div class="form-group">
						<label class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="f16_lxdh" value="${UserData.f16_lxdh }">
						</div>
						<label class="col-sm-2 control-label">传真号码</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="f17_czhm" value="${UserData.f17_czhm }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">凭证编号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="f18_pzbm" value="${UserData.f18_pzbm }">
						</div>
						<label class="col-sm-2 control-label">注册资本</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" name="f05_zczbwy" value="${UserData.f05_zczbwy }">
								<span class="input-group-addon">万元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系人姓名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="f30_lxrxm" value="${UserData.f30_lxrxm }">
						</div>
						<label class="col-sm-2 control-label">联系人电话</label>
						<div class="col-sm-4 ">
							<input type="text" class="form-control" name="f32_lxrdh" value="${UserData.f32_lxrdh }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系人邮箱</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="f31_lxraqyx" value="${UserData.f31_lxraqyx }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">营业执照</label>
						<div class="col-sm-10 ">
							<div class="col-sm-5 img-mod img-upload-group">
								<a href="" class="thumbnail">
									<img src="" alt="">
								</a>
								<input type="file" class="file-botton">
								<input type="hidden" name="f39_yyzz_pic">
							</div>
							<div class="col-sm-7 ">
								<div class="form-group">
									<label class="col-sm-5 control-label">营业执照编号</label>
									<div class="col-sm-7">
										<input type="text" class="form-control " name="f29_qyyyzzbh" value="${UserData.f29_qyyyzzbh }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">有效开始日期</label>
									<div class="col-sm-7">
										<input class="form-control startDate"  size="16" type="text" name="f25_qyyyzzksrq" value="${UserData.f25_qyyyzzksrq }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">有效截止日期</label>
									<div class="col-sm-7">
										<input class="form-control endDate" size="16" type="text" name="f26_qyyyzzzlrq" value="${UserData.f26_qyyyzzzlrq }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">发证机关</label>
									<div class="col-sm-7">
										<select class="form-control" name="eb5" id="eb5">
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">经营许可证</label>
						<div class="col-sm-10 ">
							<div class="col-sm-5 img-upload-group img-mod">
								<a href="" class="thumbnail">
									<img src="" alt="">
								</a>
								<input type="file" class="file-botton">
								<input type="hidden" name="f37_jyxkz_pic">
							</div>
							<div class="col-sm-7 ">
								<div class="form-group">
									<label class="col-sm-5 control-label">营业许可证编号</label>
									<div class="col-sm-7">
										<input type="text" class="form-control " name="f20_qyjyxkzbh" value="${UserData.f20_qyjyxkzbh }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">有效开始日期</label>
									<div class="col-sm-7">
										<input type="text" class="form-control startDate " name="f21_qyjyxkzksrq" value="${UserData.f21_qyjyxkzksrq }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">有效截止日期</label>
									<div class="col-sm-7">
										<input type="text" class="form-control endDate" name="f22_qyjyxkzzlrq" value="${UserData.f22_qyjyxkzzlrq }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">发证机关</label>
									<div class="col-sm-7">
										<select class="form-control" name="eb3" id="eb3">
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">税务登记证</label>
						<div class="col-sm-10 ">
							<div class="col-sm-5 img-mod img-upload-group">
								<a href="" class="thumbnail">
									<img src="" alt="">
								</a>
								<input type="file" class="file-botton">
								<input type="hidden" name="f38_gsswdjz_pic">
							</div>
							<div class="col-sm-7 ">
								<div class="form-group">
									<label class="col-sm-5 control-label">税务登记证编号</label>
									<div class="col-sm-7">
										<input type="text" class="form-control " name="f28_qygsswdjzbh" value="${UserData.f28_qygsswdjzbh }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">有效开始日期</label>
									<div class="col-sm-7">
										<input type="text" class="form-control startDate" name="f23_qygsswdjzksrq" value="${UserData.f23_qygsswdjzksrq }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">有效截止日期</label>
									<div class="col-sm-7">
										<input type="text" class="form-control endDate" name="f24_qygsswdjzzlrq" value="${UserData.f24_qygsswdjzzlrq }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">发证机关</label>
									<div class="col-sm-7">
										<select class="form-control" name="eb4" id="eb4">
										
										</select>
									</div>
								</div>
							</div>
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
<%@ include file="/resources/jsp/formJS.jsp" %>
<script type="text/javascript">
	addressInit("f11_szs","f12_szds","f13_szqx");
	countryInit("f27_qyszgj");
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
	                var id = '/00003030/';
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
</html>