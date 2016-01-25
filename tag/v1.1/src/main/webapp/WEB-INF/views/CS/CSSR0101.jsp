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
<title>添加登录用户</title>
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
	<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
 </head>
 <body>
 <script type="text/javascript">

function checkname(){
	var val=document.getElementById('f03').value;

	if(val==''){
		return false;
	}
	
	
	$.ajax({
	    url: "/CSSR01/CheckUserName.go",
	    type: 'POST',
	    data:{'username':val},
	    timeout: 1000,
	    error: function(){
	    	document.getElementById('f03_tips').innerHTML='服务器错误';
	    },
	    success: function(str){
	    	if(str=="success"){
		    	document.getElementById('f03_tips').innerHTML='账号可以被使用!';
		    	$("#f03_tips").css("visibility","visible");
	       }else{
	  	    	document.getElementById('f03_tips').innerHTML='账号已经重复!请修改';
	  	    	$("#f03_tips").css("visibility","visible");
	       }
	    }
	});
	
}






</script>
 <script type="text/javascript">
       
        //修改时验证
        function validate2Form(){   
        	 var arr = new Array(3);
        	var i=0;
        	var k=0;
        	for(i=0;i<arr.length;i++){
        		 var index =  arr[i];
        		  if($("#f0"+index).val()==''){
                  	$("#f0"+index+"_tips").css("visibility","visible");           	
                  }else{
                  	 k++;
                	  $("#f0"+index+"_tips").css("visibility","hidden");            	
                  }   
        		
        	}
        	
        	if(k==i){
        		return true;
        	}else{
        		 return false;	
        	}
        }
        
        
        
        //添加时验证
        function validateForm(){   
        	 var arr = new Array(3,4);
        	var i=0;
        	var k=0;
        	for(i=0;i<arr.length;i++){
        		 var index =  arr[i];
        		  if($("#f0"+index).val()==''){
                  	$("#f0"+index+"_tips").css("visibility","visible");           	
                  }else{
                  	 k++;
                	  $("#f0"+index+"_tips").css("visibility","hidden");            	
                  }   
        		
        	}
        	
         	var val=document.getElementById('f03').value;
        	if(val==''){
        		return false;
        	}

        	
        	$.ajax({
        	    url: "/CSSR01/CheckUserName.go",
        	    type: 'POST',
        	    async:false,
        	    data:{'username':val},
        	    timeout: 1000,
        	    error: function(){
        	    	document.getElementById('f03_tips').innerHTML='服务器错误';
        	    },
        	    success: function(str){
        	    	if(str=="success"){
        		    	document.getElementById('f03_tips').innerHTML='账号可以被使用!';
        		    	$("#f03_tips").css("visibility","visible");
        	       }else{
        	    		document.getElementById('f03_tips').innerHTML='账号已经重复!请修改';
        	  	    	$("#f03_tips").css("visibility","visible");
        	             i = i+1;
        	       }
        	    }
        	});
    		
        	if(k==i){
        		return true;
        	}else{
        		 return false;	
        	}
        }
        
 
 
 </script>
 
 
  <table width="100%" border="0">
  <form method="post" 
  
  <c:if test="${empty parambean1}">
		onsubmit="return validateForm()"
		</c:if>
  
  <c:if test="${! empty parambean1}">
   onsubmit="return validate2Form()"

</c:if> 

<c:if test="${empty parambean1}" >
   action="C.go"
</c:if>
<c:if test="${! empty parambean1}">
action="U.go"
</c:if> 

>
 
 
  <tr>
    <td>
    <table  width="100%" border="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title">添加登录用户</h3>
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
		<td align="right"><label > <span style="color:red">*为必填
	</span></label></td>
		</tr>
		    
		       <c:if test="${! empty parambean1}">
                <input type="hidden"  placeholder="用户ID" id="puk" name="puk"    value="${parambean1.puk}"/>
               </c:if>

						<!--   <tr>
			
               <td align="right"><label ><BASEFONT COLOR=#FF0000>*</BASEFONT>用户ID</label></td>
               <td width="10">&nbsp;</td>
              
               <td>
            
              </td>
          
             </tr> -->



						<tr>
            
               <td align="right"><label ><span style="color:red">*
	</span>允许用户登录 ${parambean1.f02}</label></td>
               <td width="10">&nbsp;</td>  
               <td><select name="f02">
  <option value="Y"
  <c:if test="${parambean1.f02=='Y'}">  selected=true</c:if>
  
  
  >允许用户登录</option>
  <option value="N"  <c:if test="${parambean1.f02=='N'}">  selected=true</c:if> >禁止用户登录</option>
</select></td>
             </tr>
            
			 

   <%--           <tr>
            
               <td align="right"><label ><span style="color:red">*
	</span>人员编号</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text"  placeholder="人员编号" id="f01" name="f01" size='25'   value="${parambean1.f01}" />
               <span id="f01_tips" style="color:red;visibility:hidden;">人员编号不能为空</span>
               </td>
             </tr> --%>
           
			 

             <tr>
               <td align="right"><label ><span style="color:red">*
	</span>登录名</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="text"
               
               <c:if test="${empty parambean1}">
                onblur="checkname()"
               </c:if>
                 placeholder="登录名" id="f03" name="f03" size='25'   value="${parambean1.f03}"
                 <c:if test="${! empty parambean1}">
               readonly
               </c:if> />
                
                <span id="f03_tips" style="color:red;visibility:hidden;">登录名不能为空</span>
               </td>
             </tr>
           
			 

             <tr>
               <td align="right"><label ><span style="color:red">*
	</span>密码</label></td>
               <td width="10">&nbsp;</td>  
               <td><input type="password"  placeholder="密码" id="f04" name="f04" size='25'  class="requried" value="" 
		       />
                <span id="f04_tips" style="color:red;visibility:hidden;">密码不能为空</span>
               </td>
             </tr>
          
			 

             <tr>
               <td align="right"> <label >用户组</label></td>
               <td width="10">&nbsp;</td>  
               <td><select name="groups" multiple="multiple">
 <c:forEach var="parentGroup"  items="${parentGroups}">
    
 
              <option value="${parentGroup.k01}"
              <c:forEach var="mygroup" items="${myGroups}">
              
              
              <c:if test="${ mygroup['groups'] == parentGroup.k01 }">
               selected = true
              </c:if>           
             </c:forEach>
              
              >${parentGroup.f01}</option>   
    	      </c:forEach>
</select>
			  <span style="color:red">
	</span>
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
								<td align="right" width="20%">
                                    <label>
                                    </label>
                                </td>
								<td width="55%">
									
								</td>
								<td width="15%">
									<div class="controls">
										 <button type="button" id="edit" class="btn btn-default btn-lg"onclick="location.href='F.go'">返回</button>
            <button type="submit" id="edit" class="btn btn-info">提交</button>
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
</form>		
 </body>
</html>
