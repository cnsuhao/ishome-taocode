<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>用户组管理</title>
	<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/ztree/zTreeStyle.css">
    <script src="/resources/jquery-1.11.1/jquery-1.11.1.min.js"></script>
    <script src="/resources/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
    <script src="/resources/js/base.js"></script>
    <script src="/resources/ztree/jquery.ztree.all.js"></script>
    <style>
		.fl-r{
			float: right;
		}
		.tree-mod{
			height: 300px;
		}
		.input-xs{
			height: 22px;
			padding: 6px;
		}
		.l-r-mod{
		}
		.l-r-mod .l-b{
			width:300px;
			float:left;
		}
		.l-r-mod .r-b{
			width:450px;
			float:left;
			margin-left:20px;
		}
    </style>
<body>
	<div class="container-fluid" style="margin-top:20px;">
		<div class="l-r-mod">
			<div class="l-b" >
				<div class="panel panel-default">
				  	<div class="panel-heading">用户组 <button class="btn btn-primary btn-xs fl-r" id="add-btn">添加用户组</button></div>
				  	<div class="panel-body" style="padding:0">
						<table class="table table-striped table-hover" style="table-layout:fixed;margin:0;">
							<colgroup width="55%"></colgroup>
							<colgroup width="45%"></colgroup>
							<tbody id="userGroupList">
								<c:forEach items="${list}" var="item" varStatus="status">
								<tr data-id="${item.puk}">
									<td class="nameTd">
										${item.f01_jsmc}
									</td>
									<td>
										<button class="btn btn-primary btn-xs edit-btn">编辑</button>
										<button class="btn btn-danger btn-xs del-btn">删除</button>
										<button class="btn btn-primary btn-xs set-btn">权限</button>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
				  	</div>
				</div>
			</div>
			<div class="r-b">
				<div class="panel panel-default">
				<div class="panel-heading">编辑<b id="gName"></b>权限</div>
				  	<div class="panel-body">
				  		<div class="tree-mod">
							<ul class="ztree" id="tree">
							</ul>
				  		</div>
				  		<button class="btn btn-primary fl-r" id="save-auth-btn">保存权限</button>
				  	</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		var zTreeObj,
		setting = {
			async: {
				enable: true,
				url: "/90102021",
				dataFilter: function ajaxDataFilter(treeId, parentNode, responseData) {
				    return responseData.result;
				  }
			},
			view: {
				selectedMulti: false
			},
			check : {
				enable : true
			},
			data: {
				simpleData: {
					enable: true
				}
			}

		},
		setAuthPuk;
		/*添加*/
		var $userGroupList = $("#userGroupList");
		var addTr = '<tr data-init="true" >\
						<td class="nameTd">\
							<input class="form-control input-xs groupName" placeholder="请输入用户组名"/>\
						</td>\
						<td>\
							<button class="btn btn-primary btn-xs save-btn">保存</button>\
						</td>\
					</tr>';
		$("#add-btn").on('click', function(){
			var $initTr = $userGroupList.find('[data-init]');
			if($initTr.length > 0){
				$initTr.find('.groupName').focus();
			}else{
				var newTr = $(addTr);
				$userGroupList.append(newTr);
				newTr.find('.groupName').focus();
			}
		});
		$userGroupList.on('click', '.edit-btn', function(){
			var $nameTd = $(this).closest('tr').find('.nameTd'),
				name = $.trim($nameTd.text());

			$nameTd.html('<input class="form-control input-xs groupName" value="'+ name +'" placeholder="请输入用户组名"/>');
			$(this).removeClass('edit-btn').addClass('save-btn').html('保存');

		}).on('click', '.save-btn' ,function(){
			var $this = $(this),
				$tr = $this.closest('tr'),
				$groupName = $tr.find('.groupName'),
				id = $tr.data('id'),
				name = $.trim($groupName.val()),
				initFlag = $tr.data('init'),
				url;

			if(name.length == 0){
				top.alert('请先填写用户组名！');
			}

			if(initFlag){
				url = "/90101022";
			}else{
				url = "/90101023";
			}
			$groupName.replaceWith(name);
			top.msg('保存中...', 'loading');
			 $.post(url, {'f01_jsmc': name, 'puk' : id}, function(res){
			 	if(res.code == 0){
			 		top.msg('保存成功！', 'success', 2000);
			 		if(initFlag){
			 			$tr.data('id', res.result);
			 			$tr.removeData('init');
			 			$this.after(' <button class="btn btn-danger btn-xs del-btn">删除</button> <button class="btn btn-primary btn-xs set-btn">权限</button>');
			 		}
			 		$this.removeClass('save-btn').addClass('edit-btn').html('编辑');
			 	}else{
			 		alert(res.msg);
			 	}
			 }, 'json').fail(function(){
				alert('网络错误！尝试刷新页面。');
			});
		}).on('click', '.set-btn', function(){
			var puk = $(this).closest('tr').data('id');
			setting.async.otherParam = {'id' : puk};
			zTreeObj = $.fn.zTree.init($("#tree"), setting);
			setAuthPuk = puk;
			$("#gName").html($(this).closest('tr').find('.nameTd').text());
		}).on('click', '.del-btn', function(){
			var $this = $(this);
			var puk = $(this).closest('tr').data('id');
			
			top.confirm('确定删除！', function(){
				$this.prop('disabled', true);
				top.msg('删除中', 'loading');
				$.post('/90101024', {'id':puk}, function(res){
					if(+res.code == 0){
						top.msg('删除成功', 'success', 2000);
						$this.closest('tr').remove();
					}else{
						alert(res.msg);
						$this.prop('disabled', false);
					}
				},'json').fail(function(){
					alert('网络错误！尝试刷新页面。');
				});
			})
		});
		$('.set-btn:eq(0)').click();
		$("#save-auth-btn").on('click', function(){
			var nodes = zTreeObj.getCheckedNodes(true),
				idArr = [],
				$this = $(this);
			
			if(nodes.length == 0){
				alert('请先选择用户组所需权限！');
				return false;
			}
			for(var i = 0; i< nodes.length; i++){
				idArr.push(nodes[i].id);
			}
			$this.prop('disabled', true).text('保存中..');
			$.post('/90102022',{rool: idArr.join(';'), id: setAuthPuk}, function(res){
				if(res.code == 0){
					alert('保存成功！');
				}else{
					alert(res.msg);
				}
				$this.prop('disabled', false).text('保存权限');
			}, 'json').fail(function(){
				alert('网络错误！尝试刷新页面。');
			});
		});	
	})
	</script>
</body>
</html>