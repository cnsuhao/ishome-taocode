<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>客户详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
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
  <sf:form id="myForm" name="myForm" modelAttribute="user" action="/WMSZT1/${user.puk}/K.go">
  <sf:hidden path="uu1"/>
  <table width="100%" border="0">
    <tr height="40" style="border:1px solid #DBDBDB; background:#f8f8f8;">
      <td><h4 style="margin-left:10px;">客户详情</h4></td>
    </tr>
    <tr>
      <td height="60"><table width="100%" border="0">
		  <tr>
          	<td width="2%"></td>
		    <td style="color:red; font-size:12px;">客户详情</td>
		    <td align="right">
		   
		    
		       <c:if test="${user.ddd=='0'}">
               <a href="X.go">停用用户</a>
                </c:if>
               <c:if test="${user.ddd=='1'}">
               <a href="I.go">启用用户</a>
               </c:if>
		    
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/CSSR01/${user.puk}/tu2.go">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="Y.go">银行卡管理</a>&nbsp;&nbsp;&nbsp;
			   <a href="/WMKLH1/${user.puk}/P.go">分配通道</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="A.go">查看费率</a> &nbsp;&nbsp;&nbsp;
			 <a href="/WMBM02/${user.puk}/X.go ">充值一览</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/WMBM03/${user.puk}/X.go">提现一览</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/WMBM04/${user.puk}/X.go ">佣金一览</a>
		    
		    
		 
					 
            </td>
            <td width="2%"></td>
	      </tr>
		  </table></td>
    </tr>
    <tr style="border-top:1px solid #DBDBDB;">
      <td><table align="center" width="100%" style="margin:0; padding:0;" border="0">
      
        <tr height="40">
          <td width="193" align="right" valign="middle">
           <div id="sjdlidl" style="display:none;">         
			        上级会员登录ID：
                </div> 
          
         </td>
          <td width="12"></td>
          <td align="left" valign="middle">
          <div id="sjdlidv" style="display:none;">  
          <sf:input  path="k01" id="k01"/>
           </div> 
          </td>
          <td></td>
          <td><input type="submit" value="修改保存" class="btn btn-primary btn-sm"></td>
        </tr>
      
      <%--  <tr height="40">
          <td width="20%" align="right">上级代理商：</td>
          <td width="5"></td>          
          <td>${user.k01 }</td>
          <td width="5"></td>      
        </tr> --%>
      
      <tr height="40">
          <td width="20%" align="right">用户名：</td>
          <td width="5"></td>
          <td>${user.f03 }</td>
          <td width="5"></td>
          <td></td>
        </tr>
       
        <tr height="40">
          <td width="20%" align="right">用户状态：</td>
          <td width="5"></td>          
          <td><c:if test="${user.ddd=='0'}">正常
          </c:if>
          <c:if test="${user.ddd=='1'}">无效
          </c:if>
          </td>
          <td width="5"></td>
          <td></td>
         
        </tr>
        
         <tr height="40">
          <td width="20%" align="right">审核结果：</td>
          <td width="5"></td>          
          <td><c:if test="${user.fb4=='0'}">未审核
          </c:if>
          <c:if test="${user.fb4=='1'}">审核通过
          </c:if>
            <c:if test="${user.fb4=='2'}">审核失败
          </c:if>
          </td>
          <td width="5"></td>
          <td></td>
         
        </tr>
        
        <tr height="40">
          <td width="20%" align="right">会员名称：</td>
          <td width="5"></td>          
          <td>${user.f04 }</td>
          <td width="5"></td>  
          <td></td>    
        </tr>
        
          <tr height="40">
          <td width="193" align="right" valign="middle" style="color:#F00;">用户类型：</td>
          <td width="12"></td>
          <td align="left" valign="middle">
           <sf:select path="k02" id="yhlx" onchange="showtcxx()">

					 <sf:option value="DFB_QT_VIP_0">终端会员</sf:option>	
					 <sf:option value="DFB_QT_VIP_1">代理商</sf:option>					 
           </sf:select>
          </td>
          <td width="11"></td>
          <td></td>
        </tr>
        
        
        <%-- 
          <tr height="40">
          <td width="20%" align="right">用户类型：</td>
          <td width="5"></td>          
          <td><c:if test="${user.k02=='DFB_QT_VIP_0'}">终端会员
          </c:if>
          <c:if test="${user.k02=='DFB_QT_VIP_1'}">代理商
          </c:if></td>
          <td width="5"></td>      
        </tr> --%>
        
        
         <tr height="40">
          <td width="193" align="right" valign="middle">
			   <div id="tcxxlabel" style="color:#F00; display:none;">         
			          提成选项：
                </div>   
          </td>
          <td width="12"></td>
          <td align="left" valign="middle">
          
      <div id="tcxx" style="display:none;">  
          <sf:radiobutton path="k03" value="0"/>无提成<sf:radiobutton path="k03" value="1"/>充值提成<sf:radiobutton path="k03" value="2"/>提现提成<sf:radiobutton path="k03" value="3"/>全部提成
     </div>   
          
          </td>
          <td width="11"></td>
          <td width="336" align="left" valign="middle"></td>
           
        </tr>
        
      <%--    <tr height="40">
          <td width="20%" align="right">提成选项：</td>
          <td width="5"></td>          
          <td><c:if test="${user.k03=='0'}">无提成
          </c:if>
          <c:if test="${user.k03=='1'}">充值提成
          </c:if>
          <c:if test="${user.k03=='2'}">提现提成
          </c:if>
          <c:if test="${user.k03=='3'}">全部提成
          </c:if>
          </td>
          <td width="5"></td>      
        </tr>
         --%>
       
        
        
       <%--  <tr height="40">
          <td width="20%" align="right">开户行所在省：</td>
          <td width="5"></td>          
          <td>${user.bbb }</td>
          <td width="5"></td>
         
        </tr>
        <tr height="40">
          <td width="20%" align="right">开户行所在市：</td>
          <td width="5"></td>          
          <td>${user.f05 }</td>
          <td width="5"></td>

        </tr>
        <tr height="40">
          <td width="20%" align="right">银行名称：</td>
          <td width="5"></td>          
          <td>${user.f02 }</td>
          <td width="5"></td>
        </tr>
        <tr height="40">
          <td width="20%" align="right">开户行名称：</td>
          <td width="5"></td>          
          <td>${user.f05 }</td>
          <td width="5"></td>
        
        </tr>
        <tr height="40">
          <td width="20%" align="right">银行户名：</td>
          <td width="5"></td>          
          <td>${user.f01 }</td>
          <td width="5"></td>
         
        </tr>
        <tr height="40">
          <td width="20%" align="right">银行账号：</td>
          <td width="5"></td>          
          <td>${user.k02 }</td>
          <td width="5"></td>
         
        </tr> --%>
    
                
      </table></td>
    </tr>
  </table>
    	
  </sf:form>
</body>
</html>
