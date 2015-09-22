<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <base href="<%=basePath%>">    
    <title>new</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/resources/css/wm/zk/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/global.css" rel="stylesheet" media="screen">
	<link href="/resources/css/wm/zk/sticky-footer-navbar.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
	<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css"> 	  
	<!-- Bootstrap js -->
	<script src="/resources/js/wm/zk/jquery.js"></script>
	<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
	<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
	<script  type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"></script>
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
		$(function (){
// 			jQuery("#form1").validationEngine('attach',
// 			{promptPosition : "centerRight", autoPositionUpdate : true});	
				 $.validationEngineLanguage.allRules['idcard'] = {
	        	 "func": function(field, rules, i, options) {
	        	var patrn=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;       	
	        	return  field.val().match(patrn);
	          },
	          "alertText": "* 身份证号码格式不正确,请重新输入。"
	        };     
	   	   $.validationEngineLanguage.allRules['pic'] ={
		        "func":function(field, rules, i, options){	
				var patrn=/(\.jpg)|(\.gif)|(\.jpeg)|(\.png)$/;
				 return field.val().substring(field.val().lastIndexOf("."),field.val().length)
				 .match(patrn);		
		        },
		      "alertText": "* 图片格式不正确,请重新选择jpg/gif/png格式的图片。"    
	        }   
       	 $('#form1').validationEngine();	
		});
	/*	function version(){
// 			alert("1");
	    	var browser=navigator.appName;
// 	    	alert("2");
		 	var b_version=navigator.appVersion;
// 		 	alert("3");
		 	var version=b_version.split(";");
		 	alert(version);
		 	var trim_Version=version[1].replace(/[ ]/g,"");
		 	alert(trim_Version);
		 	if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE11.0"){
				    alert("IE 11.0");			
				}else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE6.0"){		
				    alert("IE 6.0");		
			}
	     }  */   	
		function clearForm() {
			var formObj = document.getElementById("form1");
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
		function back(){
			document.form1.action = "/WMKZ01/F.go";
			form1.submit();
		}
		function save(){
			form1.submit();
		}		
	</script>
  </head>
  <body>
     <form 
       <c:if test="${not empty parambean1.puk}">  	
   action="/WMKZ01/U.go"
   </c:if>
   <c:if test="${empty parambean1.puk}">
   action="/WMKZ01/CJ.go"
   </c:if> 

   method="post" id="form1" name="form1" enctype="multipart/form-data">
   
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">创建客户系统</h4></td>
    </tr>
    <tr>
      <td height="60">
      <table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">${message}</td>
		    <td align="right">
					<button  class="btn btn-default btn-sm" onclick="back()">返回</button>
					  <input type="reset"  class="btn btn-default btn-sm">
					  <input type="button"  class="btn btn-default btn-sm" value="确定" onclick="save()">
            </td>
            <td width="2%"></td>
	      </tr>
		  </table>
		  </td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td>
   	 <table align="center" width="100%" style="margin:0; padding:0;" border="0" >  
         <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
          	<input type="hidden" class="form-control input-sm validate[required,custom[number]] text-input"
          	 id="puk" name="puk" value="${parambean1.puk}" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right" style="color:#F00;">*&nbsp;授权编号：</td>
          <td width="5"></td>
          <td>
          <input type="text"  class="form-control input-sm validate[required] text-input" 
          id="k01" name="k01"  value="${parambean1.k01}">
<!--            validata-options="validType:'ZInteger'">        -->
<!--             validata-options="validType:'Limit',max:'10',msg:'请输入正确授权编号" > -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">                
          <td width="20%" align="right" style="color:#F00;">*&nbsp;域名：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm  validate[required] text-input" 
             id="k02" name="k02"  value="${parambean1.k02}">               
<!--              validata-options="validType:'Limit',max:'10',msg:'请输入正确域名'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right" style="color:#F00;">*&nbsp;客户简称：</td>
          <td width="5"></td>
          <td>
             <input type="text"  class="form-control input-sm validate[required] " 
             id="f01" name="f01" value="${parambean1.f01}">          
            
<!--              validata-options="validType:'Limit',max:'10',msg:'内容必须在10个字之内'"  >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        
        <c:if test="${parambean1.uu1== null || parambean1.uu1 == ''}">
	     <tr height="40">
	          <td width="20%" align="right" style="color:#F00;">*&nbsp;超级管理员ID</td>
	          <td width="5"></td>
	          <td>
	             <input type="text"   class="form-control input-sm validate[requried]"
	             id="fb3" name="fb3" value=""> 
	           
<!-- 	             validata-options="required:false,validType:'Username',msg:'用户名不合法'">          -->
	          </td>
	          <td width="5"></td>
	          <td width="30%"></td>
	        </tr>
	        <tr height="40">
	          <td width="20%" align="right" style="color:#F00;">*&nbsp;登录密码</td>
	          <td width="5"></td>
	          <td>
	             <input type="password"  class="form-control input-sm  validate[requried]" 
	              id="fb4" name="fb4" value="">
	           
<!-- 	             validata-options="required:false,validType:'SafeString',msg:'密码不符合安全规则'" >          -->
	          </td>
	          <td width="5"></td>
	          <td width="30%"></td>
	        </tr>
	       </c:if>        
        <tr height="40">
          <td width="20%" align="right">客户全称：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm "
              id="f02" name="f02" value="${parambean1.f02}" >          
              
<!--               validata-options="validType:'Chinese',min:'10',msg:'内容必须在大于七个汉字'"  >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">客户地址：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm  
             id="f03" name="f03" value="${parambean1.f03}" >                      
<!--              validata-options="validType:'Chinese',msg:'您必须输入正确的联系地址'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">工商税务编号：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm " 
             id="f04" name="f04" value="${parambean1.f04}">           
           
<!--              validata-options="validType:'Number',required:'true',msg:'工商税务局编号格式不正确'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">联系人电话：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm validate[custom[phone]]" 
             id="f08" name="f08" value="${parambean1.f08}" >          
<!--              validata-options="required:false,validType:'Phone',msg:'电话号码不正确'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">系统分类：</td> -->
<!--           <td width="5"></td> -->
<!--           <td>   -->
<!--           <button class="btn btn-info" id="btninfo"  >功能定义</button> &nbsp;&nbsp;      -->
<!--           		<input type="radio" id="f09" name="f09" value="DFB_HT_ADMIN_GROUP"> -->
          	
<!--           		validata-options="validType:'Group',msg:'必须选定一个系统分类'"> -->
<!--           		普通版 -->
          		
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> -->
<!--          <tr height="40"> -->
<!--           <td width="20%" align="right">系统版本：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text" class="form-control input-sm" id="f10" name="f10" value="${parambean1.f10}">          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> <tr height="40"> -->
<!--           <td width="20%" align="right">系统密钥：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text" class="form-control input-sm" id="f11" name="f11" value="${parambean1.f11}">            -->
           
<!--              validata-options="validType:'SafeString',msg:'密码不符合安全规则'" >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> -->
		<tr height="40"> 
          <td width="20%" align="right">授权终止日期：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm "  id="f12" name="f12" value="${parambean1.f12}"             
             onfocus="WdatePicker({dateFmt:'yyyy/MM/dd'})" onClick="WdatePicker()">         
<!--             validata-options="validType:'Date',format:'ymd',msg:'日期不存在'"  -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        </tr> 
         </tr> <tr height="40">
          <td width="20%" align="right">版权网址：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm validate[custom[url]]" 
             id="f20" name="f20" value="${parambean1.f20}">        
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        </tr> 
           <tr height="40">
          <td width="20%" align="right">前台logo</td>
          <td width="5"></td>
          <td>
             <input type="file"  id="f14" name="ff"   class="validate[custom[pic]]" >     
          
<!--              validata-options="validType:'Custom',regexp:'.+\.(jpg|jpeg|jpg|gif|png)$',msg:'只能上传jpg|jpeg|jpg|gif|png文件在'" >          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>    
           <tr height="40">
          <td width="20%" align="right">后台logo</td>
          <td width="5"></td>
          <td>
             <input type="file"  id="f15" name="tt"   class="validate[custom[pic]]">
             
<!--              validata-options="validType:'Custom',regexp:'.+\.(jpg|jpeg|jpg|gif|png)$',msg:'只能上传jpg|jpeg|jpg|gif|png文件在'">          -->
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>                    
             <input type="hidden" class="form-control input-sm" id="uu1" name="uu1" value="${parambean1.uu1}"  >         
      </table>  

      </td>
    </tr>
  </table>
     </form>
<!--     <script>   	    -->
<!-- 		  $('#form1').checkForm(); -->
<!-- 		   $("#save").click(function(){ -->
<!-- 		  $('#form1').submit(); -->
<!-- 		}); -->
<!-- 	</script> -->
</body>
</html>
