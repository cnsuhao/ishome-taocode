<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld" prefix="p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>品牌设置</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12" style="margin-top:20px;"> 
				
				<!-- 工具 -->
				<div class="tools-group">
					<a href="javascript:addSupplier()" class="btn btn-primary">选择供应商</a>
				</div>
				<!-- 表格 -->
				<div class="table-responsive" style="margin-top:20px;">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>供应商名称</th>
								<th>销售权限</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableList">
							<c:forEach items="${page.pageListData }" var="item" varStatus="status">
								<tr>
									<td></td>
									<td>
										<select>
											<option>全国总销售</option>
											<option>地区总销售</option>
											<option>分销商</option>
										</select>
									</td>
									<td>
										<a href="javascript:post('/39113006?puk=${item.puk}');" onclick="return confirm('你确定要删除吗?');" type="button" class="btn btn-primary btn-xs">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="table-bottom-opt-btn">
							<button type="button" class="btn btn-primary" onclick="msub({f03_shzt:0})">保存</button>
					</div>
				</div>	
				<!-- 分页 -->
				<p:page pageVo="${page}"/>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	//弹出窗口
	//新增供应商
	function addSupplier(){
		var modal=top.showWindow("选择供应商", 'url', '', null, [{'class':'btn-primary','text':'确定','id':'save-btn'}]);

	}
	</script>
</body>
</html>