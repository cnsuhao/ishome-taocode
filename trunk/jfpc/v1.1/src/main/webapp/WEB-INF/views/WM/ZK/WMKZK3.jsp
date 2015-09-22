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
    <script src="/resources/js/wm/zk/bootstrap.min.js"></script>
    <script src="/resources/js/wm/zk/topmenu.js"></script>	 	
	<script src="/resources/js/wm/zk/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			jQuery("#form1").validationEngine('attach',
			{promptPosition : "centerRight", autoPositionUpdate : true});		
		});
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
		function back() {
			document.form2.action = "/WMJG01/F.go";
			form2.submit();
		}
	/*	function save(){
	  		var rs = document.form1.puk.value;
			if (rs != "") {
				document.form1.action = "/WMJG01/U.go";
				form1.submit();
			} else {
				document.form1.action = "/WMJG01/C.go";
				form1.submit();
			}	
		}
	/*	function isEmpty(){
			var val=document.getElementById("k03").value;
// 			alert(value);
			if(""==val){
				alert("请输入内容");
				this.onfocus();
			}else{
// 				alert("不能输入");
				this.blur();
			}		
		}*/
	</script>
  </head>
  <body>
      <form 
      <c:if test="${not empty parambean1.puk}"> action="/WMJG01/U.go"
	   </c:if>
	   <c:if test="${empty parambean1.puk}">action="/WMJG01/C.go"</c:if> 
      
       method="post" id="form1" name="form1">
  <table width="100%" border="0"> 
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">通道分配 </h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">      
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:14px;">${message}</td>
		    <td align="right">
<!-- 		    <form action="" method="post" id="form2" name="form2"> -->
					  <button  class="btn btn-default btn-sm" onclick="back()">返回</button>
					  <button  class="btn btn-default btn-sm" onclick=" clearForm()">重置</button>
					<input type="submit" id="btnsave" class="btn btn-primary btn-sm" value="保存（显示VIP组）"/>
<!--             </form> -->
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td> 
      <table align="center" width="100%" style="margin:0; padding:0;" border="0"> 
        <tr height="40">
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
          	<input type="hidden"  id="puk" name="puk" value="${parambean1.puk}" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right" style="font-size:14px;">
          <label for="f03">通道名称:</label></td>
          <td width="5"></td>
          <td>
          <select id="f03" name="f03" size="" style="width: 260px; height: 28px;" 
          class="validate[groupRequired] ">
       <!--    validata-options="validType:'Require',msg:'未选择所用操作系统'"> -->  
	        			<option value="">支付通道名称</option>
	        			<c:forEach var="wmbma1" items="${listtd}">
	        			<option value="${wmbma1.puk}">${wmbma1.f03}</option>	        			        			 -->
	        			</c:forEach>	        			
	      </select>
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>    
          
        <tr height="40">
          <td width="20%" align="right">所属系统ID：</td>
          <td width="5"></td>
          <td>
          <input type="text" class="form-control input-sm" id="eb5" name="eb5" value="${eb5}"           
           validata-options="validType:'ZInteger'" >
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
     <%--    <tr height="40">
          <td width="20%" align="right">商户号：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f01" name="f01" value="${parambean1.f01}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr> --%>
<!--          <tr height="40"> -->
<!--           <td width="20%" align="right">授权号：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text" class="form-control input-sm" id="f09" name="f09" value="${parambean1.f09}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> -->
      <%--    <tr height="40">
          <td width="20%" align="right">支付密钥：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f02" name="f02" value="${parambean1.f02}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">通道简介：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f04" name="f04" value="${parambean1.f04}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">通道说明：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f05" name="f05" value="${parambean1.f05}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">通道状态：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f06" name="f06" value="${parambean1.f06}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
          <td width="20%" align="right">授权号：</td>
          <td width="5"></td>
          <td> --%>
<!--              <input type="text" class="form-control input-sm" id="f09" name="f09" value="${parambean1.f09}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%"></td> -->
<!--         </tr> -->
<!--          <tr height="40"> -->
<!--           <td width="20%" align="right">商户号MerchantID：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text" class="form-control input-sm" id="f10" name="f10" value="${parambean1.f10}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%">合作商户的商户号(例：MerchantID=100001)</td> -->
<!--         </tr> -->
<!--          <tr height="40"> -->
<!--           <td width="20%" align="right">支付渠道PayID：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text" class="form-control input-sm" id="f11" name="f11" value="${parambean1.f11}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%">(例：网上银行支付：PayID=1000)</td> -->
<!--         </tr> -->
<!--         <tr height="40"> -->
<!--           <td width="20%" align="right">通知商户地址：</td> -->
<!--           <td width="5"></td> -->
<!--           <td> -->
<!--              <input type="text" class="form-control input-sm" id="f12" name="f12" value="${parambean1.f12}"  >          -->
<!--           </td> -->
<!--           <td width="5"></td> -->
<!--           <td width="30%">（url编码：例http://www.baofoo.com/demo/BusinessNotIFy.aspx）</td> -->
<!--         </tr> -->
        <tr height="40">
          <td width="20%" align="right">充值手续费数量：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f15" name="f15" value="${parambean1.f15}" 
              validata-options="validType:'ZDouble',msg:'输入数字，保留小数'"  >         
          </td>
          <td width="5"></td>
          <td width="30%">元</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">提现手续费数量：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f16" name="f16" value="${parambean1.f16}"  
             validata-options="validType:'ZDouble',msg:'输入数字，保留小数'" >         
          </td>
          <td width="5"></td>
          <td width="30%">元</td>
        </tr>
        <tr height="40">
<!--         0百分比1固定 -->
          <td width="20%" align="right">充值手续费率：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm validate[required,custom[number]] text-input" id="f13" name="f13" value="${parambean1.f13}" 
              >         
         
          </td>
          <td width="5"></td>
          <td width="30%">百分比/固定</td>
        </tr>
        <tr height="40">
<!--         0百分比1固定 -->
          <td width="20%" align="right">提现手续费率：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm validate[required,custom[number]] text-input" id="f14" name="f14" value="${parambean1.f14}"  >         
          </td>
          <td width="5"></td>
          <td width="30%">百分比/固定</td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">提现最大金额：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm validate[required,custom[number]] text-input" id="f17" name="f17" value="${parambean1.f17}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
        
        <tr height="40">
          <td width="20%" align="right">结算说明：</td>
          <td width="5"></td>
          <td>
             <input type="text" class="form-control input-sm" id="f19" name="f19" value="${parambean1.f19}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
         <tr height="40">
<!--          更新时间 -->
          <td width="20%" align="right"></td>
          <td width="5"></td>
          <td>
             <input type="hidden" class="form-control input-sm" id="uu1" name="uu1" value="${parambean1.uu1}"  >         
          </td>
          <td width="5"></td>
          <td width="30%"></td>
        </tr>
      </table>
   </form>
      </td>
    </tr>
  </table>
</body>
</html>
