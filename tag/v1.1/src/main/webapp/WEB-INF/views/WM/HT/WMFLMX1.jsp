<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function save(){		
		var  rs=document.form01.puk.value;				
	 if(rs!=""){
	  	document.form01.action="/WMHTFLMX/U.go";
		form01.submit();	
     }else{
		document.form01.action="/WMHTFLMX/C.go";
 	    form01.submit(); 	  			
			}		
 		}
	
	function back() {

		document.form01.action = "/PTTDGLYL/F.go";
		form01.submit();
	}
	function clearForm() {

		var formObj = document.getElementById("form01");

		if (formObj == undefined) {

			return;
		}
		for ( var i = 0; i < formObj.elements.length; i++) {
			if (formObj.elements[i].type == "text") {
				formObj.elements[i].value = "";
			} else if (formObj.elements[i].type == "password") {
				formObj.elements[i].value = "";
			} else if (formObj.elements[i].type == "radio") {
				formObj.elements[i].checked = false;
			} else if (formObj.elements[i].type == "checkbox") {
				formObj.elements[i].checked = false;
			} else if (formObj.elements[i].type == "file") {
				var file = formObj.elements[i];
				if (file.outerHTML) {
					file.outerHTML = file.outerHTML;
				} else {
					file.value = "";
				}
			} else if (formObj.elements[i].type == "textarea") {
				formObj.elements[i].value = "";
			} else if (formObj.elements[i].type == "select") {
				formObj.elements[i].options[0].selected = true;
			} else if (formObj.elements[i].type == "hidden") {
				formObj.elements[i].value = "";
			}
		}
	}
	</script>
  </head>
  <body>
  <table align="center" width="100%" style="margin:0; padding:0;" border="0">
      <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
        <td><h4 style="margin-left:10px;">动态费率添加</h4></td>
      </tr>
      <tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
        <td height="60"><table width="100%" border="0">
          <tr>
            <td width="2%"></td>
            <td style="color:red; font-size:12px;">${message}</td>
            <td align="right">
              <input type="button" class="btn btn-info" value="返回" onclick="parent.toTargetForm('/WMQTFLMX/F.go','/WMQTFLMX/F.go')" />
              <button type="button" id="edit" class="btn btn-info"onclick="save()">保存</button>
            </td>
            <td width="2%"></td>
            </tr>
          </table></td>
      </tr>
      <tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
        <td>
          <table width="100%" align="center" style="margin:0; padding:0;" border="0">
            <form id="form01" name="form01" method="post" action="">
              <tr height="40">
                <th width="30%" align="right">当前通道</th>
                <td width="10"></td>
                <td>
               ${paramBean.fb1}
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="30%" align="right" valign="middle"><input name="k02"  type="radio" value="0">星期</th>
                <td width="10"></td>
                <td  >开始
                    <select name="f01" style='width:100px;'>
                      <option value="" selected>星期一</option>
                      <option value="" selected>星期二</option>
                      <option value="" selected>星期三</option>
                      <option value="3" selected>星期四</option>
                      <option value="4" selected>星期五</option>
                      <option value="5" selected>星期六</option>
                      <option value="6" selected>星期日</option>
                    </select>
                  终了
                  <select name="f02" style='width:100px;'>
                    <option value="0" selected>星期一</option>
                    <option value="1" selected>星期二</option>
                    <option value="2" selected>星期三</option>
                    <option value="3" selected>星期四</option>
                    <option value="4" selected>星期五</option>
                    <option value="5" selected>星期六</option>
                    <option value="6" selected>星期日</option>
                  </select>
                  </td>
                <td width="10"></td>
                <td width="30%">&nbsp;</td>
                </tr>
              <tr height="40">
                <th width="30%"align="right"><input name="k02" type="radio" value="1">日期</th>
                <td width="10"></td>
                <td>开始<input style="margin-bottom:0; width:30%;" type="text"  placeholder="" id="f01" name="f01" value="${paramBean.f01}">终了<input style="margin-bottom:0; width:30%;" type="text"  placeholder="" id="f02" name="f02" value="${paramBean.f02}"></td>
                <td width="10"></td>
                <td width="30%">&nbsp;</td>
                </tr>
              <tr height="40">
                <th width="30%"align="right">开始时间</th>
                <td width="10"></td>
                <td>开始<input name="f03" type="text" id="f03" placeholder="" style="margin-bottom:0; width:30%;" value="${paramBean.f03}">终了<input style="margin-bottom:0; width:30%;" type="text"  placeholder="" id="f04" name="f04" value="${paramBean.f04}"></td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="100" align="right">充值手续费率</th>
                <td width="10"></td>
                <td>
                  <input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f15" name="f15" value="${paramBean.f15}">
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="30%"align="right">提现手续费</th>
                <td width="10"></td>
                <td>
                  <input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f16" name="f16" value="${paramBean.f16}">
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="30%"align="right">提现最大金额</th>
                <td width="10"></td>
                <td>
                  <input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f17" name="f17" value="${paramBean.f17}">
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="30%"align="right">结算说明</th>
                <td width="10"></td>
                <td>
                  <input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="f19" name="f19" value="${paramBean.f19}">
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="30%"align="right">通道说明</th>
                <td width="10"></td>
                <td>
                  <input style="margin-bottom:0; width:90%;" type="text"  placeholder="" id="bbb" name="bbb" value="${paramBean.bbb}">
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
              <tr height="40">
                <th width="30%"align="right"></th>
                <td width="10"></td>
                <td>
                  <input type="hidden" style="margin:0" id="puk" name="puk"  value="${paramBean.puk}"> 
                  <input type="hidden" style="margin:0" id="k01" name="k01"  value="${paramBean.k01}"> 
                  <input type="hidden" style="margin:0" id="uu1" name="uu1"  value="${paramBean.uu1}">
                 
                </td>
                <td width="10"></td>
                <td width="30%"></td>
                </tr>
            </form>			
            </table>
          </td>
      </tr>
      
    </table>
</body>
</html>
