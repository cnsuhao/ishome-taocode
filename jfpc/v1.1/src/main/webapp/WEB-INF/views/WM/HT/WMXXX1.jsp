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
<title>Document</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function save(aa,bb,cc,dd,ee,ff){
	var image = document.getElementById("image").value;
        if (aa == "") {
           alert("请输入用户名");
		} else if(bb == "") {
			alert("请输入登录密码");
			}else if(cc == ""){
			alert("请输入客户名称");
			}else if(dd == ""){
	         alert("请输入用户类型");
	         }else if(ee == ""){
	         alert("请输入账号类型");
	         }else if(ff == ""){
	         alert("请输入上级代理编码");
	          }else if(image == ""){
	         alert("请上传图片");
		}else {
		   
	        document.form01.action = "/WMBM09/C.go";
			form01.submit();
	}
}

function fanhui() {
		
		  
		    document.form01.action = "/WMBM09/F.go";
	
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
// 	function uploadImage(){
// 	alert("ok");
//        var image = document.getElementById("image").value;
//        if(image == "") {
//           alert("请选择上传的图片!");
//           return;
//        }else{ 
//        alert("ok");
      
//           document.form01.action = "/WMBM09/uploadImage.go";
	
// 			form01.submit();
         
                 
         
        
//     }
// 	}
	
</script>
 </head>
 <body>
  <table width="100%" border="0">
  <tr>
    <td><table  width="100%" border="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title">添加客户</h3>
      </div></td>
      <tr>
        <td align="right">提示信息</td>
        <tr>
        </tr>
        </table>
        </td>
  </tr>
  <tr>
    <td>
        <table  align="center" border="0"> 
		<tr>
		<td align="right"><label > <BASEFONT COLOR=#FF0000> *为必填</BASEFONT></label></td>
		</tr>
		
            <tr>
			
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*</BASEFONT>用户名</label></td>
               <td width="10">&nbsp;</td>
              
               <td><input type="text" id="aa" name="aa" placeholder="" value="${parambean1.f01}"></td>
          
             </tr>
           
			 

             <tr>
            
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*
	</BASEFONT>登录密码</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text" id="bb" name="bb" placeholder="" value="${parambean1.f02}"></td>
             </tr>
            
			 

             <tr>
            
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*
	</BASEFONT>客户名称</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text" id="cc" name="cc" placeholder="" value="${parambean1.k02}"></td>
             </tr>
           
			 

             <tr>
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*
	</BASEFONT>用户类型</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text" id="dd" name="dd" placeholder="" value="${parambean1.k01}"></td>
             </tr>
           
			 

             <tr>
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*
	</BASEFONT>账号类型</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text" id="ee" name="ee" placeholder="" value="${parambean1.k03}"></td>
             </tr>
          
			 

             <tr>
               <td align="right"> <label >推荐人编号</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text" id="ff" name="ff" placeholder="" value="${parambean1.f05}">
			   <BASEFONT COLOR=#FF0000>请确认该编号的正确性(非用户名)
	</BASEFONT>
	</td>
             </tr>
             
             <tr>
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*
	</BASEFONT>上传图片</label></td>
               <td width="10">&nbsp;</td>  
               <td> <form action="upload.go" method="post" enctype="multipart/form-data" name="form1">   
  <input type="file" name="file" />   
  <input type="submit" name="submit" value="上传" />   
</form></td>
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
								<td align="right" width="20%">
                                    <label>
                                    </label>
                                </td>
								<td width="55%">
									
								</td>
								<td width="15%">
									<div class="controls">
										 <button type="button" id="edit" class="btn btn-default btn-lg"onclick="fanhui()">返回</button>
            <button type="button" id="edit" class="btn btn-info"onclick="save(document.getElementById('aa').value,document.getElementById('bb').value,document.getElementById('cc').value,document.getElementById('dd').value,document.getElementById('ee').value,document.getElementById('ff').value)">提交</button>
									</div>
								</td>
								<td width="10%">
									
								</td>
							</tr>	
							<tr height="20">
								<td colspan="4"></td>
							</tr>
						</table>	
                    </td>
                </tr>
            </table>
		
 </body>
</html>
