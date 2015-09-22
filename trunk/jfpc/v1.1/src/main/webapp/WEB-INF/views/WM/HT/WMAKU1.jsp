<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>添加会员</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/ht/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script src="/resources/js/wm/ht/bootstrap.js"></script>
<link href="/resources/css/wm/ht/global.css" rel="stylesheet" media="screen">



<script type="text/javascript" src="/resources/js/wm/ht/jquery.js"></script>



<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/validationEngine.jquery.css">
<link rel="stylesheet" type="text/css" href="/resources/js/wm/ht/formvalidator/css/template.css">
<script src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine-zh_CN.js"></script>
<script  src="/resources/js/wm/ht/formvalidator/js/jquery.validationEngine.js"></script>
<script>
        
		jQuery(document).ready(function(){
			// binds form submission and fields to the validation engine
			jQuery("#myForm").validationEngine('attach', {promptPosition : "centerRight", autoPositionUpdate : true});
			
		});
</script>



<!-- <script type="text/javascript" src="/resources/slvalidate/js/vdform.js"></script>
 -->

<script type="text/javascript">



 function showtcxx(){
	 var yhlx =  document.getElementById("yhlx").value;
	 //终端会员
	 if(yhlx=="DFB_QT_VIP_0"){
		 document.getElementById("tcxx").style.display="none";
		 document.getElementById("tcxxlabel").style.display="none";
		 
		 document.getElementById("sjdlidv").style.display="";
		 document.getElementById("sjdlidl").style.display="";
		 //alert($("input[name='k03']:checked").val());
		  $("input[name='k03']:checked").attr('checked',false);
		  //alert($("input[name='k03']:checked").val());
	 }
	 //代理商
	 if(yhlx=="DFB_QT_VIP_1"){
		 document.getElementById("tcxx").style.display="";
		 document.getElementById("tcxxlabel").style.display="";
		 
		 document.getElementById("sjdlidv").style.display="none";
		 document.getElementById("sjdlidl").style.display="none";
		 $("#k01").val("");
		// alert($("#k01").val());
		// alert($("input[name='k03']:checked").val());
	 }
	
 }
</script>
  </head>
  <body onload="showtcxx()">
   <sf:form class="form-horizontal" role="form" id="myForm" name="myForm" modelAttribute="userinfo" method="post">
   <sf:hidden path="uu1"/>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td style=" border-bottom:1px solid #ccc;"><h4 style="margin-left:10px;">添加会员</h4></td>
    </tr>
    <tr>
      <td align="left"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
          	<td width="2%" height="50" valign="middle"></td>
		    <td valign="middle" style="color:red; font-size:12px;">${message }</td>
		    <td align="right" valign="middle">
					  <!-- <button type="submit" class="btn btn-default btn-sm">返回</button> -->
					  <input type="reset" class="btn btn-default btn-sm">
					  <input type="submit" value="提交" class="btn btn-primary btn-sm">	
            </td>
            <td width="50"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td align="center" valign="top"><table width="800" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;">
       
      
        
         <tr height="40">
           <td height="40" align="right" valign="middle">&nbsp;</td>
           <td height="40" colspan="3" align="left" valign="middle">&nbsp;</td>
         </tr>
         <tr height="40">
          <td width="319" height="40" align="right" valign="middle">
           <div id="sjdlidl" style="display:none;">         
			       <strong> 上级会员登录ID：</strong>
                </div> 
          
         </td>
         
          <td height="40" colspan="3" align="left" valign="middle">
            <div id="sjdlidv" style="display:none;">  
              <sf:input  path="k01" id="k01"/>
              </div> 
          </td>
          </tr>
        <tr height="40">
          <td height="40" align="right" valign="middle"><strong>用户名：</strong></td>
          <td colspan="3" align="left" valign="middle"><sf:input path="f03" class="validate[required] text-input" /></td>
          </tr>
        <tr height="40">
          <td height="40" align="right" valign="middle"><strong>登录密码：</strong></td>
          <td colspan="3" align="left" valign="middle"><sf:password  path="f04" class="validate[required] text-input" /></td>
          </tr>
         <tr height="40">
          <td height="40" align="right" valign="middle"><strong>会员名称：</strong></td>
          <td colspan="3" align="left" valign="middle"><sf:input  path="bbb" class="validate[required] text-input" /></td>
          </tr>
         <tr height="40">
          <td height="40" align="right" valign="middle"><strong>用户类型：</strong></td>
          <td colspan="3" align="left" valign="middle">
            <sf:select path="k02" id="yhlx" onchange="showtcxx()">
              <c:forEach items="${vipinfo }" var="vip">
                <sf:option value="${vip.puk }">${vip.f01 }</sf:option>					 
                </c:forEach>
              </sf:select>
          </td>
          </tr>
        <tr height="40">
          <td align="right" valign="middle">
			   <div id="tcxxlabel" style="display:none;">         
			          <strong>提成选项：</strong>
                </div>   
          </td>
          <td></td>
          <td align="left" valign="middle">
          
      <div id="tcxx" style="display:none;">  
          <sf:radiobutton path="k03" value="0"/>无提成<sf:radiobutton path="k03" value="1"/>充值提成<sf:radiobutton path="k03" value="2"/>提现提成<sf:radiobutton path="k03" value="3"/>全部提成
     </div>   
          
          </td>
          <td ></td>
          <td width="50" align="left" valign="middle"></td>
           
        </tr>
       
        <%--  <tr height="40">
          <td width="193" align="right" valign="middle">账号类型：</td>
          <td width="12"></td>
          <td align="left" valign="middle"><sf:radiobutton path="fb5" value="0"/>个人账号<sf:radiobutton path="fb5" value="1"/>企业账号</td>
          <td width="11"></td>
          <td width="336" align="left" valign="middle">备注</td>
        </tr> --%>
         
    
      </table></td>
    </tr>
  </table>
    </sf:form>
     <!--      
       <script>
$('#myForm').checkForm();
		
</script>  -->
</body>
</html>

