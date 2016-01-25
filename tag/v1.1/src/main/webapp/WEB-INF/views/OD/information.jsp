<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>货付宝添加页面信息</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	 
<script src="/resources/js/wm/zk/jquery.js"></script>
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"/>
 <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
 <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
<script src="/resources/js/wm/zk/bootstrap.js"></script>
<script type="text/javascript">
	$(function (){		
// 			jQuery("#form1").validationEngine('attach',
// 			{promptPosition : "centerRight", autoPositionUpdate : true});	
				 $.validationEngineLanguage.allRules['address'] = {
	        	 "func": function(field, rules, i, options) {
	        	var patrn=/^[\u0391-\uFFE5]{2,6}$/;       	
	        	return  field.val().match(patrn);
	          },
	          "alertText": "* 输入有误，请输入正确的联系地址。"
	        };     
	   	   $.validationEngineLanguage.allRules['pic'] ={
		        "func":function(field, rules, i, options){	
				var patrn=/(\.jpg)|(\.gif)|(\.jpeg)|(\.png)$/;
				 return field.val().substring(field.val().lastIndexOf("."),field.val().length)
				 .match(patrn);		
		        },
		      "alertText": "* 图片格式不正确,请重新选择jpg/gif/png格式的图片。"    
	        }   
	     //   checkForm();	       
       	 $('#form1').validationEngine();
//        	  $('#btn').show();
		});		
/*		function checkForm(){
			 var val=$("#fb1").val();
			 	if(val==''){
		    		document.getElementById('adName').innerHTML='管理员账号不能为空';	  	    	
			 		//return false;
			 	}		
			  var i=0;
				  $.ajax({
					    url: "/HOMEORDER/CheckAdmin.go",
					    type: 'POST',
					    async:false,
					    data:{'name':val},
					    timeout: 1000,	   
					    success: function(result){
					    	if(result=="success"){	
					    		document.getElementById('adName').innerHTML='管理员账号已经重复!请修改';	 		    	
					        }else{
					    		//document.getElementById('adName').innerHTML='管理员账号已经重复!请修改';	  	    	
					         i++;
					       }
					    }
					}); 
// 				  if(i>0){
// 					  return false;
// 				  }	  
			/*	  var pa = document.getElementById('f02').value;
				  var par = document.getElementById('f02r').value;
				  var cname = document.getElementById('f04').value;
				  if(pa==''||par==''){
			  		document.getElementById('f02_tips').innerHTML='密码不能为空';
			  		return false;
				  }
				 if(pa!=par){
				  	document.getElementById('f02_tips').innerHTML='前后密码不一致';	  
			  		return false;
				 }
				 if(cname==''){
					  	document.getElementById('f04_tips').innerHTML='客户名不能为空';
				  		return false;
				 }	  
			}  */
			 

		
		//弹出层
		function ShowDiv(show_div,bg_div){
			document.getElementById(show_div).style.display='block';
			document.getElementById(bg_div).style.display='block' ;
			var bgdiv = document.getElementById(bg_div);
			bgdiv.style.width = document.body.scrollWidth;
			// bgdiv.style.height = $(document).height();
			$("#"+bg_div).height($(document).height());
		};
		//关闭弹出层
		function CloseDiv(show_div,bg_div){
			document.getElementById(show_div).style.display='none';
			document.getElementById(bg_div).style.display='none';
		};
		
</script>
<style>
.black_overlay{
display: none;
position: absolute;
top: 0%;
left: 0%;
width: 100%;
height: 100%;
background-color: black;
z-index:1001;
-moz-opacity: 0.8;
opacity:.80;
filter: alpha(opacity=80);
}
.white_content {
display: none;
position: absolute;
top: 10%;
left: 10%;
width: 80%;
height: 80%;
border: 16px solid lightblue;
background-color: white;
z-index:1002;
overflow: auto;
}
.white_content_small {
display: none;
position: absolute;
top: 20%;
left: 30%;
width: 40%;
height: 50%;
border: 16px solid lightblue;
background-color: white;
z-index:1002;
overflow: auto;
}
h4{
	color:red;
	align:center; 
}
</style>
</head>
<body>
<div class="warp">
  <div style="text-align:center;">
  <form 
  <c:if test="${not empty parambean.puk}">
  	 action="/HOMEORDER/U.go"
  </c:if>
  <c:if test="${empty parambean.puk}">
  	 action="/HOMEORDER/C.go"
  </c:if>   
  method="post" id="form1" name="form1" enctype="multipart/form-data">
  <h4>${message}</h4>
  <input type="hidden"  id="puk"  name="puk" value="${parambean.puk}"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="40" align="right" valign="middle">授权编号</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><label for="textfield2"></label>
      <input  id="k01"  name="k01" type="text"  class="infor_wb validate[required,custom[number]] text-input" value="${parambean.k01}"/>    
      <label for="textarea"><span id="f01_tips" style="background:red"></span></label></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">域名</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><label for="textfield2"></label>
      <input  id="k02"  name="k02" type="text"  class="infor_wb validate[required,custom[onlyLetterNumber]]text-input" value="${parambean.k02}"/>    
      <label for="textarea"></label></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户简称</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f01" type="text" class="infor_wb" id="f01" value="${parambean.f01}"/></td>
  </tr>
   
  <tr>
    <td height="40" align="right" valign="middle">客户全称</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f02" type="text" class="infor_wb" id="f02" value="${parambean.f02}"/></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">客户地址</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f03" type="text" class="infor_wb
     validate[required,custom[address]]" id="f03" value="${parambean.f03}" /></td>
  </tr>
  <tr>
    <td height="40" align="right" valign="middle">工商税务编号</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f04" type="text" class="infor_wb
     validate[required,custom[integer]]" id="f04" value="${parambean.f04}"/></td>
  </tr>
   <tr>
    <td height="40" align="right" valign="middle">联系人电话</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f08" type="text" class="infor_wb
     validate[custom[phone]]" id="f08" value="${parambean.f08}"/></td>
  </tr>
<!--  <tr> -->
<!--     <td height="40" align="right" valign="middle">系统分类</td> -->
<!--     <td>&nbsp;</td> -->
<!--     <td align="left" valign="middle"> -->
<!--     <input type="radio" id="" name="f09" value="0" ${parambean.f09==0?'checked':''} >标准 -->
<!--     <input type="radio" id="" name="f09" value="1" ${parambean.f09==1?'checked':''}>专业 -->
<!--     <input type="radio" id="" name="f09" value="2" ${parambean.f09==2?'checked':''}>大客户版 -->
<!--     <input type="radio" id="" name="f09" value="3" ${parambean.f09==3?'checked':''}>服务器版 -->
<!--     </td> -->
<!--   </tr> -->
<!--    <tr> -->
<!--     <td height="40" align="right" valign="middle">系统版本</td> -->
<!--     <td>&nbsp;</td> -->
<!--     <td align="left" valign="middle">  -->
<!--    <input name="f10" type="text" class="infor_wb" id="f10" value="${parambean.f08}"/> -->
<!--     </td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--     <td height="40" align="right" valign="middle">系统密钥</td> -->
<!--     <td>&nbsp;</td> -->
<!--     <td align="left" valign="middle"><input name="f11" type="text" class="infor_wb  -->
<!--     validate[required,custom[onlyLetterNumber]]" id="f11" value="${parambean.f11}"/></td> -->
<!--   </tr>  -->
  <tr>
    <td height="40" align="right" valign="middle">客户公司成立日期</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f05" type="text" class="infor_wb" id="f05" value="${parambean.f05}"
     onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()"/></td>
  </tr> 
<!--   <tr> -->
<!--     <td height="40" align="right" valign="middle">客户公司注册资本（万元）</td> -->
<!--     <td>&nbsp;</td> -->
<!--     <td align="left" valign="middle"><input name="f06" type="text" class="infor_wb -->
<!--      validate[required,custom[number]]" id="f06" value="${parambean.f06}"/></td> -->
<!--   </tr> -->
<!--   <tr> -->
<!--     <td height="40" align="right" valign="middle">联系人名称</td> -->
<!--     <td>&nbsp;</td> -->
<!--     <td align="left" valign="middle"><input name="f07" type="text" class="infor_wb -->
<!--      validate[required]" id="f07" value="${parambean.f07}"/></td> -->
<!--   </tr> -->
 	<tr>
    <td height="40" align="right" valign="middle">版权网址</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="f20" type="text" class="infor_wb 
    validate[required]" id="f20" value="${parambean.f20}"/></td>
  </tr>  
   <tr>
    <td height="40" align="right" valign="middle">前台logo</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle">
    <input type="file"  id="f14" name="file1"   class="validate[required,custom[pic]]"></td>
  </tr> 
  <tr>
    <td height="40" align="right" valign="middle">后台logo</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle">
    <input type="file"  id="f15" name="file2"   class="validate[required,custom[pic]]"></td>
  </tr>  

   <c:if test="${parambean.uu1== null || parambean.uu1 == ''}">
   <tr>
    <td height="40" align="right" valign="middle">超级管理员ID（默认）</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle"><input name="fb1" type="text" class="infor_wb" id="fb1" value=""/>
    </td>
  </tr>
    <tr>
    <td height="40" align="right" valign="middle">登录密码</td>
    <td>&nbsp;</td>
    <td align="left" valign="middle">
    <input name="fb2" type="password" class="infor_wb" id="fb2" value=""/></td>
  </tr>  
   </c:if>
  <tr>
    <td height="10" colspan="3" align="right" valign="middle"></td>
  </tr>
  <tr>
    <td height="30" colspan="3" align="right" valign="middle"><div class="infor_line"></div></td>
    </tr>
  <tr>
    <td height="50" align="right" valign="middle">&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="top">
    <div class="infor_an">
       <input type="reset" id="btn"  class="btn btn-default btn-sm " value="重置"/>&nbsp;&nbsp;
    <input type="submit" id="btn"  class="btn btn-default btn-sm " value="保存"/>&nbsp;&nbsp; 
    </div></td>   
  </tr>
  </table>
  <input type="hidden"  id="uu1" name="uu1" value="${parambean.uu1}" >
      <label for="textfield"></label>
  </form>
  </div>
</div>
</body>
</html>
