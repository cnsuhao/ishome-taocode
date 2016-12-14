<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>添加用户组</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet"
	media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
</head>
<body>

	<script type="text/javascript">
		var arr = new Array("f01","k01");

		function validateForm() {
			var i;
			var k = 0;
			for (i = 0; i < arr.length; i++) {
				var index = arr[i];
				if ($("#" + index).val() == '') {
					$("#" + index + "_tips").css("visibility", "visible");
				} else {
					k++;
					$("#" + index + "_tips").css("visibility", "hidden");
				}

			}
			if (k == i) {
				return true;
			} else {
				return false;
			}
		}
	</script>

	<table width="100%" border="0">
		<form  method="post" onsubmit="return validateForm()"
		
		 <c:if test="${empty parambean1}">
action="C.go"
</c:if>
			<c:if test="${! empty parambean1}">
action="U.go"
</c:if>
			>
			<tr>
				<td><table width="100%" border="0">
						<tr>
							<td>
								<div class="tab-main dark-gray">
									<h3 class="tab-main-title">添加用户组</h3>
								</div>
							</td>
						<tr>
							<td align="right">提示信息</td>
						<tr>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td>
					<table align="center" border="0">
						<tr>
							<td align="right"><label> <span style="color: red">*为必填
								</span></label></td>
						</tr>

						<tr>
							<!-- 
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*</BASEFONT>系统识别ID</label></td>
               <td width="10">&nbsp;</td>
               -->
							<td><input type="hidden" placeholder="系统识别ID" id="puk"
								name="puk" class="requried" value="${parambean1.puk}" /></td>

						</tr>



						<tr>

							<td align="right"><label><span style="color: red">*用户组编号
								</span></label></td>
							<td width="10">&nbsp;</td>
							<td><input type="text" placeholder="用户组编号" id="k01"
								name="k01" size='40' class="requried" value="${parambean1.k01}" />
								<span id="k01_tips" style="color: red; visibility: hidden;">用户组编号不能为空</span>
							</td>
						</tr>



						<tr>

							<td align="right"><label><span style="color: red">*用户组名称
								</span></label></td>
							<td width="10">&nbsp;</td>
							<td><input type="text" placeholder="用户组名称" id="f01"
								name="f01" size='40' class="requried" value="${parambean1.f01}" />
								<span id="f01_tips" style="color: red; visibility: hidden;">用户组名称不能为空</span>

							</td>
						</tr>



					<%-- 	<tr>
							<td align="right"><label><span>上级用户组 </span></label></td>
							<td width="10">&nbsp;</td>
							<td><select name="k03">
									<option value=""></option>
									<c:forEach var="parentGroup" items="${parentGroups}">

										<option value="${parentGroup.k01}"
											<c:if test="${parambean1.k03==parentGroup.k01}">selected=true</c:if>>${parentGroup.f01}</option>
									</c:forEach>
							</select></td>
						</tr>
 --%>


				 		<tr>
							<td align="right"><label><span>分类  								 
							</span></label></td>
							<td width="10">&nbsp;</td>
							<td><select name="f02">
									<option value="WM.HT"
										<c:if test="${parambean1.f02==WM.HT}">selected=true</c:if>>后台</option>
								
							</select></td>
						</tr>



						<tr>
							<td align="right"><label>备注</label></td>
							<td width="10">&nbsp;</td>
							<td><textarea cols="30" rows="10" id="bbb" name="bbb">${parambean1.bbb}</textarea>
							</td>
						</tr>






					</table>
				</td>
			</tr>

			<tr>
				<td align="right">
					<table align="center" width="100%" border="0">
						<tr height="20">
							<td colspan="4"></td>
						</tr>
						<tr height="50">
							<td align="right" width="20%"><label> </label></td>
							<td width="55%"></td>
							<td width="15%">
								<div class="controls">
									<button type="button" id="edit" class="btn btn-default btn-lg"
										onclick="location.href='F.go'">返回</button>
									<button type="submit" id="edit" class="btn btn-info">保存</button>
								</div>
							</td>
							<td width="10%"></td>
						</tr>
						<tr height="20">
							<td colspan="4"></td>
						</tr>
					</table>
				</td>
			</tr>
	</table>
	</form>
</body>
</html>



