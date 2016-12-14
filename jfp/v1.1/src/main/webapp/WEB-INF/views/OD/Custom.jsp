<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>货付宝</title>
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
	function tijiao(){
	//	count();
		form1.submit();
	}
/*	function totalCount(bbb,list){	
		alert(document.getElementById("td1").innerHTML);
	//	alert(document.getElementById("table").cols[3]);
// 		for(var i=0;i<list.length;i++){			
// 		}	
		//循环取得最后一列的值
//		var spans=document.getElementById("table").cols[3].getElementsByTagName("span");
// 		for(var i=0;i<list.length;i++){
// 			var v1=document.getElementById(bbb+'s').innerHTML;
// 						alert(v1);	
// 			if(spans[i].id==list[0]){
// 			  break;
// 		}	
// 		var sum=0;
// 		for(var i = 0; i<document.getElementById("table").childNodes[i].childNodes.length; i++){
// 	 		 sum+=parseInt(document.getElementById("table").
// 	 		 childNodes[3].childNodes[i].value;
// 		}

	}
/*	function count(){
		var checkboxes = document.getElementsByName('checkbox');	
		var checkedArr = new Array();		
			for(var i=0;checkboxes.length;i++){	
				var flag=checkboxes[i].checked;
				//alert(flag);	
				if(flag){	
				checkedArr[i]=checkboxes[i].value;
				//checkedArr.push(checkboxes[i].value);		
			}	
 		}		
 		alert(checkedArr);		
	}	*/
	function test(bbb){
		var ih=document.getElementById(bbb+'s').innerHTML;//小计
		var val=document.getElementById(bbb).value;//所选价格
		if(document.getElementById(bbb).checked){	
			var r=parseInt(val)+parseInt(ih);//计算
			var t=document.getElementById('total').innerHTML;
			var total=parseInt(val)+parseInt(t);
			document.getElementById(bbb+'s').innerHTML=r;//小计设置显示		
			document.getElementById('total').innerHTML=total;
		}else{
			var val=document.getElementById(bbb).value;//所选价格
			var r=parseInt(ih)-parseInt(val);//计算
			var t=document.getElementById('total').innerHTML;
			var total=parseInt(t)-parseInt(val);
			document.getElementById(bbb+'s').innerHTML=r;//小计设置显示		
			document.getElementById('total').innerHTML=total;	
		}
	}
	function test1(ccc){
		var ih=document.getElementById(ccc+'s').innerHTML;//小计的值
		if(document.getElementById(ccc+'a').checked){
			var val=document.getElementById(ccc+'a').value;//所选价格
			var r=parseInt(val)+parseInt(ih);//计算
			var t=document.getElementById('total').innerHTML;
			var total=parseInt(val)+parseInt(t);
			document.getElementById(ccc+'s').innerHTML=r;//小计设置显示
			document.getElementById('total').innerHTML=total;	
		}else{
			var val=document.getElementById(ccc+'a').value;//所选价格
			var r=parseInt(ih)-parseInt(val);//计算
			var t=document.getElementById('total').innerHTML;
			var total=parseInt(t)-parseInt(val);
			document.getElementById(ccc+'s').innerHTML=r;//小计设置显示		
			document.getElementById('total').innerHTML=total;			
		}	
	}
</script>
</head>
<body>
  <form action="/HOMEORDER/A.go" method="post" id="form1" name="form1" >
<div class="warp">
  <div style="text-align:center;">
  <input type="button" value="test" onclick="totalCount('${ptcp02.puk}','${newList}')" />
 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" valign="middle">       
        	<table width="100%"  border="1" cellpadding="0" cellspacing="0" class="pack">
          <tr style="height: 50px">
            <td width="12%" height="40" align="center" valign="middle" class="pack1">&nbsp;</td>
            <td colspan="2" align="center" valign="middle" class="pack1">采购选择单价</td>
            <td colspan="2" align="center" valign="middle" class="pack1">二次开发单价</td>
            <td width="35%" align="center" valign="middle" class="pack1">小计</td>
            </tr>      
                     
            <c:forEach var="ptcp02" items="${data}">          
            <tr style="height: 50px">
           		 <td height="40" align="center" valign="middle" class="pack1">${ptcp02.f01}</td>
           		 <c:choose>
           		 	<c:when test="${not empty ptcp02.f04}">
           		 		<td width="14%" align="center" valign="middle" class="pack1" colspan="" >
           		 		<label for="">${ptcp02.f04}&nbsp;</label></td>
           		 		 <td width="12%" align="center" valign="middle" class="pack1">           		 		 
           		 		 <input type="checkbox" name="checkbox" id="${ptcp02.puk}" 
           		 		 value="${ptcp02.f04}" onclick="test(${ptcp02.puk})"/>
           		 		 </td> 
           		 	</c:when>
           		 	<c:otherwise>
           		 		<td width="14%" align="center" valign="middle" class="pack1">&nbsp;</td>
           		 		<td width="14%" align="center" valign="middle" class="pack1">&nbsp;</td>
           		 	</c:otherwise>          		 
           		 </c:choose>
           		 <c:choose>
           		 	<c:when test="${not empty ptcp02.fb2}">
           		 		<td width="14%" align="center" valign="middle" class="pack1" colspan="">
           		 		<label for="checkbox1">${ptcp02.fb2}</label></td>
           		 		 <td width="12%" align="center" valign="middle" class="pack1">       	 		 
           		 		<input type="checkbox" name="checkbox" id="${ptcp02.puk}a" 
           		 		value="${ptcp02.fb2}" onclick="test1(${ptcp02.puk})"></td> 
           		 	</c:when>
           		 	<c:otherwise>
           		 		<td width="14%" align="center" valign="middle" class="pack1">&nbsp;</td>
           		 		<td width="14%" align="center" valign="middle" class="pack1">&nbsp;</td>
           		 	</c:otherwise>          		 
           		 </c:choose>
            	 <td align="center" valign="middle" class="pack1" >
            	 <span id="${ptcp02.puk}s" > 0</span>
<!--             	 ${ptcp02.eb1} -->
            	 </td>
           	</tr>              
            </c:forEach>                  
          <tr>
            <td colspan="5" rowspan="2" class="pack1">&nbsp;</td>
            <td height="40" align="center" valign="middle" class="pack1">总价：￥
            <span class="blue_jg" id="total">0</span>&nbsp;/元</td>
            </tr>
          <tr>
            <td height="40" align="center" valign="middle" class="pack1"><span class="red" id="discount">折后价：￥ &nbsp;${ct}&nbsp;</span></td>
          </tr>
        </table>
          	
        </td>
      </tr>
      <tr>
        <td align="center" valign="middle">&nbsp;</td>
      </tr>
      <tr>
        <td align="center" valign="middle">
	        <table border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td >
					<input type="submit" value="提交订单" />
	            </td>
	            </tr>
	        </table>
        </td>
      </tr>
    </table>   
    <label for="textfield"></label>
 </div>
</div>
<div style=" clear:both;"></div>
   </form>  
</body>
</html>
