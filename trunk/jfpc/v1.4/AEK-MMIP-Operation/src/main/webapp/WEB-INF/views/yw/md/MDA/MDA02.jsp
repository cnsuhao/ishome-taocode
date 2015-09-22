<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/pagetag.tld"  prefix="p" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<title>规格维护</title>
	<%@ include file="/resources/jsp/yw/inc.jsp" %>
	<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/bootstrap-3.2.0-dist/css/bootstrap-datetimepicker.min.css">
<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap-datetimepicker.min.js"></script>
<script src="/resources/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="panel panel-default mt20">
            <!-- 导航按钮 -->
            <div class="panel-heading tc">
                <a type="button" class="btn btn-default fl-l btn-xs" href="javascript:;">
                    <span class="glyphicon glyphicon-arrow-left"></span>
                </a>
                新增产品线
            </div>
            <!-- 主体 -->
            <div class="panel-body">
                <form class="form-horizontal form-edit" role="form" action="/39111002" method="post" id="myform">
                    <input type="hidden" name="puk" value="${UserData.puk }">
                    <div class="form-group form-group-sm">
                        <label class="col-sm-2 control-label">厂商全称</label>
                        <div class="col-sm-10">
                            <div class="input-group combox">
                                <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="f11" value="${UserData.f11 }">
                                <input type="hidden" name="" />
                                <div class="input-group-btn">
                                    <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-2 control-label">中文描述</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="f01_zwmc" value="${UserData.f01_zwmc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-2 control-label">英文描述</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="f02_ywmc" value="${UserData.f02_ywmc }"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label class="col-sm-2 control-label">分类说明</label>
                        <div class="col-sm-10">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>大分类</th>
                                            <th>中分类</th>
                                            <th>小分类</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tableList">
                                        <c:if test="${UserData.puk == null || UserData.puk == '' }">
                                        <tr>
                                            <td>1</td>
                                            <td>
                                                <div class="input-group combox">
                                                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[0].f01_flmc">
                                                    <input type="hidden" name="" />
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="input-group combox">
                                                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[0].f04">
                                                    <input type="hidden" name="" />
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="input-group combox">
                                                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[0].f05">
                                                    <input type="hidden" name="" />
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="javascript:;" type="button" class="btn del-type-btn btn-danger btn-xs">删除</a>
                                            </td>
                                        </tr>
                                        </c:if>
                                        <c:if test="${UserData.puk != null || UserData.puk != '' }">
                                        <c:forEach items="${UserData.cpxlbList}" var="dbo" varStatus="status">
                                        <tr>
                                            <td>${status.index + 1}</td>
                                            <td>
                                                <div class="input-group combox">
                                                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[${status.index }].f01_flmc" value="${dbo.f01_flmc }">
                                                    <input type="hidden" name="" />
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="input-group combox">
                                                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[${status.index }].f04" value="${dbo.f04 }">
                                                    <input type="hidden" name="" />
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="input-group combox">
                                                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[${status.index }].f05" value="${dbo.f05 }">
                                                    <input type="hidden" name="" />
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <a href="javascript:;" type="button" class="btn del-type-btn btn-danger btn-xs">删除</a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </c:if>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td><a href="javascript:;" type="button" class="btn add-type-btn btn-default btn-xs glyphicon glyphicon-plus"></a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>  
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-warning">重置</button>
                        </div>
                    </div>
                </form>
            </div><!-- end-body -->
        </div>
    </div>
    <script>
    $(function(){
        $('#tableList').on('click', '.del-type-btn', function(){
            $(this).closest('tr').remove();
        }).on('click', '.add-type-btn', function(){
            var $this = $(this),
                $tr = $this.closest('tr'),
                index = $tr.index() + 1;

            var tmpl = document.getElementById('typeRow').innerHTML;

            tmpl = tmpl.format(index, index - 1);

            $tr.before(tmpl);
        });
    })
    </script>
    <script type="text/html" id="typeRow">
        <tr>
            <td>{0}</td>
            <td>
                <div class="input-group combox">
                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[{1}].f01_flmc">
                    <input type="hidden" name="" />
                    <div class="input-group-btn">
                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                    </div>
                </div>
            </td>
            <td>
                <div class="input-group combox">
                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[{1}].f04">
                    <input type="hidden" name="" />
                    <div class="input-group-btn">
                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                    </div>
                </div>
            </td>
            <td>
                <div class="input-group combox">
                    <input autocomplete="off" type="text"class="form-control" vd-key="nonempty" name="cpxlbList[{1}].f05">
                    <input type="hidden" name="" />
                    <div class="input-group-btn">
                        <button type="button" class="btn show-btn btn-default"><span class="caret"></span></button>
                    </div>
                </div>
            </td>
            <td>
                <a href="javascript:;" type="button" class="btn del-type-btn btn-danger btn-xs">删除</a>
            </td>
        </tr>
    </script>
</body>
<%@ include file="/resources/jsp/formJS.jsp" %>
</html>