/**
 *表单验证类
 */
(function() {
	//验证规则
	var pattern = {
		'selectHospital': {
			name: '选择医院',
			rex: '^[\\u4e00-\\u9fa5]+$',
			tips: '请输入医院名称',
			error: '请输入医院名称'
		},
		'username': {
			name: '用户名',
			rex: '^[A-Za-z0-9_\-\u4e00-\u9fa5]{4,20}$',
			tips: '用户名为4－20位字符，支持中文、字母、下划线、数字',
			error: '用户名为4－20位字符，支持中文、字母、下划线、数字！'
		},
		'phone': {
			name: '手机',
			rex: '^1[\\d]{10}$',
			tips: '请输入正确的手机号码，用于账户登录、找回密码、账户保护！虚假注册，一经查实，将会被注销号码!',
			error: '手机格式不正确！'
		},
		'oldphone': {
			name: '已绑定手机号',
			rex: '^1[\\d]{10}$',
			tips: '请输入手机号码',
			error: '手机格式不正确！'
		},
		'newphone': {
			name: '新绑定手机号',
			rex: '^1[\\d]{10}$',
			tips: '请输入手机号码',
			error: '手机格式不正确！'
		},
		'tel': {
			name: '电话',
			rex: '[0-9-()（）]{7,18}',
			tips: '请输入科室电话',
			error: '电话格式有误，正确格式为：0571-89710131'
		},
		'oldpassword': {
			name: '原密码',
			rex: '^[\\w]{6,20}$',
			tips: '请输入6位以上的密码',
			error: '密码由6-20位的字母、数字和下划线组成。'
		},
		'password': {
			name: '密码',
			rex: '^[\\w]{6,20}$',
			tips: '请输入6位以上的密码',
			error: '密码由6-20位的字母、数字和下划线组成。'
		},
		'oldpasswd': {
			name: '旧密码',
			rex: '^[\\w]{6,20}$',
			tips: '请输入6-20位以上的密码',
			error: '密码由6-20位的字母、数字和下划线组成。'
		},
		'repassword': {
			name: '重复密码',
			rex: '^[\\w]{6,20}$',
			tips: '请重复输入密码',
			error: '密码由6-20位的字母、数字和下划线组成。'
		},
		'email': {
			name: '邮箱',
			rex: '^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$',
			tips: '请输入邮箱',
			error: '您输入的邮箱不正确，请输入正确邮箱地址！'
		},
		'code': {
			name: '验证码',
			rex: '^\\d{6}$',
			tips: '请输入验证码',
			error: '验证码格式出错！'
		},
		'vcode':{
			name: '验证码',
			rex: function(val){
				return $.trim(val).length == 4;
			},
			tips: '请输入验证码',
			error: '验证码格式出错！请输入4位验证码。'
		},
		'realname': {
			name: '真实姓名',
			rex: '^[\\u4e00-\\u9fa5]{2,5}$',
			tips: '请输入中文名',
			error: '请输入2-5个中文字'
		},
		'location': {
			name: '街道地址',
			rex: '^[^\\s]+$',
			tips: '请输入街道地址',
			error: '街道地址填写错误!'
		},
		'zipcode': {
			name: '邮编',
			rex: '^[1-9][\\d]{5}$',
			tips: '请输入邮编',
			error: '邮编格式错误！'
		},
		'nonempty':{
			name: '非空',
			rex: function(val){
				return $.trim(val).length != 0
			},
			tips:'不能为空',
			error:'不能为空！'
		},
		'date':{
			name: '非空',
			rex: '^\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}$',
			tips:'请输入日期',
			error:'请输入正确格式的日期,例：2014-01-02'
		},
		'pac':{
			name: '省市区',
			rex: function(val){
				return $.trim(val) != '选择省';
			},
			tips:'请选择省!',
			error:'请选择省!'
		}
	}
	// 获取字符长度
	function getLen(s) { 
		var l = 0;
		var a = s.split('');
		for (var i = 0, len = a.length; i < len; i++) {
			if (a[i].charCodeAt(0) < 299) {
				l++;
			} else {
				l += 2;
			}
		}
		return l;
	}
	function removeAjaxStuats(){
		$this.siblings('.ajaxStatus').remove();
	}
	function ajaxVaild(ajaxUrl, target, errorCallBack){
		var $this = $(target);
		if($this.data('ajaxstatus') == 1) return;
		var value = $this.val();
		// 之前是否校验过;
		// debugger
		var sucval = {};
		if($this.data('sucval')){
			sucval = $this.data('sucval');
		}
		if(sucval[value]){
			errorCallBack.call($this[0], {'success':sucval[value]['success'], 'msg':sucval[value]['msg'], 'target': $this[0]});
			$this.data('ajaxstatus', sucval[value]['ajaxstatus']);
			return;
		}
		$this.parent().css('position', function(i, text){
			if(text == 'static'){
				return 'relative';
			}
			return text;
		});
		$this.parent().find('.statusIcon').remove();
		$this.siblings('.ajaxStatus').remove();
		$this.before('<div class="ajaxStatus">\
						<div class="statusIcon loading"></div><span class="statusText">检测中</span>\
					</div>');
		$this.data('ajaxstatus', 1);
		$this.data('ajaxmsg', '正在校验中，请稍等！');
		setTimeout(function(){
			$.post(ajaxUrl, {'q': value}, function(res){
				$this.siblings('.ajaxStatus').remove();
				if(res.code == 0){
					sucval[value] = {
						'success': 1,
						'msg':'',
						'ajaxstatus': 2
					};
					$this.data('sucval', sucval);
					errorCallBack.call($this[0], {'success':1, 'msg':'', 'target': $this[0]});
					$this.data('ajaxstatus', 2);
				}else{
					sucval[value] = {
						'success': 0,
						'msg': res.message || '该项目已被使用！',
						'ajaxstatus': 3
					};
					$this.data('sucval', sucval);
					$this.data('ajaxstatus', 3);
					$this.data('ajaxmsg', res.message || '该项目已被使用！');
					errorCallBack.call($this[0], {'success':0, 'msg': res.message || '该项目已被使用！' , 'target': $this[0]})
				}
			}, 'json').fail(function(){
				$this.data('ajaxstatus', 4);
				$this.data('ajaxmsg','网络错误！');
				var status = $this.siblings('.ajaxStatus');
				status.find('.statusIcon').attr('class', 'statusIcon refresh').one('click', function(){
					ajaxVaild(ajaxUrl, target, errorCallBack);
				});
				status.find('.statusText').html('检测失败，请重试!');
				errorCallBack.call($this[0], {'success':0, 'msg': '网络错误！' , 'target': $this[0]})
			});
		}, 5000);
	}
	// rex 转function
	function rex2func(rex){
		if($.isFunction(rex)){
			return rex;
		}else{
			var fun;
			(function(rex){
				fun = function(val){
					return new RegExp(rex, 'gi').test(val);
				}
			})(rex);
			return fun;
		};
	}
	// 让input 获取焦点
	function focusDom(dom){
		var $dom = $(dom);
		try{
			$dom.focus(); 
		}catch(e){
			$dom.attr('tabindex', '-1').focus().removeAttr('tabindex');
		}
	}
	// 根据事件绑定验证方式
	function eventVaild($this, type, errorCallBack){
		$this.find('input[vd-key], select[vd-key], textarea[vd-key]').on(type, function(){
			var $this = $(this),
				name = $this.attr('vd-key'),
				value = $.trim(this.value),
				rex = $this.data('rex') || pattern[name].rex,
				typePar = {
					'blur':'error',
					'fucus':'tips'
				},
				msgPAr = typePar[type],
				errorMsg = $this.data('error') || pattern[name][msgPAr];

			if (rex2func(rex)(value)){
				var $pw = $this.closest("form").find("input[vd-key=" +( name == "repassword" ? 'password' : 'repassword')+ "]");
				if(name == "repassword" && $pw.length > 0 &&  $pw.val() !=  value){
					errorCallBack && errorCallBack.call(this, {'success':0, 'msg': '两次密码输入不一致！', 'target': this});
				}else if(name == "password" && $pw.length > 0 && $pw.val().length !=0 && $pw.val() !=  value){
					errorCallBack && errorCallBack.call(this, {'success':0, 'msg': '两次密码输入不一致！', 'target': this});	
				}else{
					var ajaxUrl = $this.data('ajax');
					if(ajaxUrl){
						ajaxVaild(ajaxUrl, $this, errorCallBack);
						return ;
					}
					if((name == "repassword" || name == "password") && $pw.val() == value){
						errorCallBack && errorCallBack.call($pw[0], {'success':1, 'msg':''});
					}
					errorCallBack && errorCallBack.call(this, {'success':1, 'msg':''});
				}
			} else {
				errorCallBack && errorCallBack.call(this, {'success':0, 'msg': errorMsg , 'target': this});
			}
  		});
	}
	// 获取通用的config
	var defCfg = {
		id : '',
		onError: function(){},
		allFlag: true
	};
	function getConfig(cfg){
		cfg = cfg || {};
		return $.extend(defCfg , cfg);
	}
	// 加入jquery
	$.fn.extend({
	  	blurValidate: function(errorCallBack) {  //失去焦点验证
	  		/*
			* 失去焦点验证
			* 回调errorCallBack参数：
			*	{'success': 0,				验证通过:1, 未通过:0;
			*	 'target': $input[i] ,		未通过的input
			*	 'msg': pattern[name].error 验证失败信息
			*	}
			*/
	  		var $this = $(this);
	  		$this.data('blurValidateCallBack', errorCallBack)
	  		eventVaild($this, 'blur', function(data){
	  			errorCallBack.call(this, data);
	  		});
		},
		blurVD: function(config){
			var cfg = getConfig(config),
				$msgDiv = $(cfg.id);
			$(this).blurValidate(function(data){
				var $this = $(this),
					forSelector = $this.data('for'),
					$for = forSelector ? $(forSelector) : $this;
				if(data.success == 0){
					// if($this.attr('vd-key') == 'nonempty'){
					// 	data.msg = '请输入' + $this.parent().prev().text() + '!';
					// }
					var icon = 'err';
					if($.trim($this.val()).length == 0){
						$for.parent().find('.statusIcon').remove();
						$for.parent().append('<span class=" statusIcon lab war"></span>');
						icon = 'war';
					}else{
						if($this.data('ajaxstatus') != 1){
							$for.parent().find('.statusIcon').remove();
							$for.parent().append('<span class=" statusIcon lab err"></span>');
						}
					}
					// focusDom($this);
					$msgDiv.show().html('<div class="errorIcon"><span class="statusIcon '+ icon  +'"></span></div>' + data.msg);
				}else{
					$for.parent().find('.statusIcon').remove();
					$for.parent().append('<span class=" statusIcon lab suc"></span>')
					$msgDiv.hide().html("");					
				}
				cfg.onError.call(this, data);
			})
		},
		focusValidate: function(errorCallBack) {  //焦点提示
			/*
			* 焦点提示
			* 回调errorCallBack参数：
			*	{'success': 0,				验证通过:1, 未通过:0;
			*	 'target': $input[i] ,		未通过的input
			*	 'msg': pattern[name].tips  验证失败信息
			*	}
			*   
			*/
	  		var $this = $(this);
	  		eventVaild($this, 'focus', errorCallBack);
		},
		validate: function(){
			/*
			* 单个个input表单验证，在提交时使用
			* 返回值：
			*	{'success': 0,				验证通过:1, 未通过:0;
			*	 'target': $input[i] ,		未通过的input
			*	 'msg': pattern[name].error 错误信息
			*	}
			*   
			* 如果该input使用的form已经使用过失去焦点验证$(form).blurValidate(errorCallBack)，在返回验证失败信息前会调用
			* blurValidate中的回调函数 
			*/
			var $this = $(this),
				name = $this.attr('vd-key');
			if(!name){
				return false;
			}
			var	rex = $this.data('rex') || pattern[name].rex,
				errorMsg = $this.data('error') || pattern[name].error,
				value = $.trim($this.val()),
				blurValidateCallBack = $this.closest('form').data('blurValidateCallBack');

			if (!rex2func(rex)(value)) {
				if(blurValidateCallBack){
					blurValidateCallBack.call(this, {'success':0,'msg': errorMsg, 'target': this});
				}
				return {'success': 0, 'target': this ,'msg': errorMsg};
			}else if($this.data('ajax') && $this.data('ajaxstatus') !== 2){
				errorMsg = $this.data('ajaxmsg');
				// if(blurValidateCallBack){
				// 	blurValidateCallBack.call(this, {'success':0,'msg': errorMsg, 'target': this});
				// }
				return {'success': 0, 'target': this ,'msg': errorMsg};
				
			}
			return {'success': 1, 'msg': '', 'target': this};
		},
		validateForm: function(config){					//全局表单验证;
			/*
			* 验证表单验证，在提交时使用
			* 传参
			*
			* 返回值： 数组
			*	{
			*		allSuccess: true				//如果整个表单通过：true ，其中一个不通过：false
			*		{[{'success': 0,				验证通过:1, 未通过:0;  //返回表单中每个验证项目的验证结果
			*		 'target': $input[i] ,		未通过的input
			*		 'msg': pattern[name].error 错误信息
			*		},...]
			*	}
			*   
			* 如果该form已经使用过失去焦点验证$(form).blurValidate(errorCallBack)，在返回验证失败信息前会调用blurValidate
			* 中的回调函数 
			*/
			var cfg = getConfig(config);
			var $msgDiv = $(cfg.id);
			var $this = $(this),
				$input = $this.find('input[vd-key], select[vd-key], textarea[vd-key]'),
				blurEventFlag = !!$this.data('blurValidateCallBack'),
				blurValidateCallBack = $this.data('blurValidateCallBack') || function(data){

					var $this = $(this),
						forSelector = $this.data('for'),
						$for = forSelector ? $(forSelector) : $this;
					if(data.success == 0){
						var icon = 'err';
						if($.trim($this.val()).length == 0){
							icon = 'war';
						}
						$msgDiv.show().html('<div class="errorIcon"><span class="statusIcon '+ icon +'"></span></div>' + data.msg);
						focusDom($for);
						$for.parent().find('.statusIcon').remove();
						$for.parent().append('<span class=" statusIcon lab '+ icon +'"></span>');	
					}else{
						$for.parent().find('.statusIcon').remove();
						$for.parent().append('<span class=" statusIcon lab suc"></span>');
					}
				},
				errList = [],
				allSucFlag = true,
				allFlag = false;
				
			$this.find('.statusIcon').remove();
			for (var i = 0 , m = $input.length; i < m; i++) {
				var name = $input.eq(i).attr('vd-key'),
					rex = $input.eq(i).data('rex') || pattern[name].rex,
					errorMsg = $input.eq(i).data('error') || pattern[name].error,
					value = $.trim($input[i].value);
				if (!rex2func(rex)(value)) {
					// if($input.eq(i).attr('vd-key') == 'nonempty'){
					// 	errorMsg = '请输入' + $input.eq(i).parent().prev().text().replace('*', '') + '!';
					// }
					if(blurValidateCallBack){
						blurValidateCallBack.call($input[i], {'success': 0, 'msg': errorMsg, 'target': $input[i]});
					}
					if(allFlag === true){
						errList.push({'success': 0, 'target': $input[i] ,'msg': errorMsg});
					}else{
						focusDom($input.eq(i));
						return {
							allSuccess : false,
							list: [{'success': 0, 'target': $input[i] ,'msg': errorMsg}]
						};
					}
					allSucFlag = false;
				}else{
					var $pw = $input.eq(i).closest("form").find("input[vd-key=" +( name == "repassword" ? 'password' : 'repassword')+ "]");
					if(name == "repassword" && $pw.length > 0 &&  $pw.val() !=  value){
						blurValidateCallBack && blurValidateCallBack.call($input[i], {'success':0, 'msg': '两次密码输入不一致！', 'target': $input[i]});
						allFlag = false;
						return {
							allSuccess : false,
							list: [{'success':0, 'msg': '两次密码输入不一致！', 'target': $input[i]}]
						};
					}else if(name == "password" && $pw.length > 0 && $pw.val().length !=0 && $pw.val() !=  value){
						blurValidateCallBack && blurValidateCallBack.call($input[i], {'success':0, 'msg': '两次密码输入不一致！', 'target': $input[i]});
						return {
							allSuccess : false,
							list: [{'success':0, 'msg': '两次密码输入不一致！', 'target': $input[i]}]
						};
					}else if($input.eq(i).data('ajax') && $input.eq(i).data('ajaxstatus') !== 2){
						errorMsg = $input.eq(i).data('ajaxmsg');
						if(blurValidateCallBack){
							blurValidateCallBack.call($input[i], {'success': 0, 'target': $input[i], 'msg':errorMsg});
						}
						if(allFlag === true){
							errList.push({'success': 0, 'target': $input[i] ,'msg': errorMsg});
						}else{
							focusDom($input.eq(i));
							return {
								allSuccess : false,
								list: [{'success': 0, 'target': $input[i] ,'msg': errorMsg}]
							};
						}
					}else{
						// if(blurValidateCallBack && !allSucFlag){
						// 	blurValidateCallBack.call($input[i], {'success': 1, 'target': $input[i]});
						// }
						errList.push({'success': 1, 'target': $input[i]});
					}
				}
			};
			if(allSucFlag == true){
				$msgDiv.hide();
			}
			return {
				allSuccess : allSucFlag,
				list: errList.reverse()
			};
		}
	});
})();