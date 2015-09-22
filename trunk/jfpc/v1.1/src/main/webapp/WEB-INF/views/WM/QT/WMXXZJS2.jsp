<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
 <head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/wm/qt/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/resources/css/wm/qt/global.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/resources/js/wm/qt/jquery.js"></script>
<script src="/resources/js/wm/qt/bootstrap.js"></script>
<script type="text/javascript">
	function fanhui(){
		
			document.form1.action = "/WMBN01/F.go";
			
			form1.submit();
		
	}
	</script>
 </head>
 <body>
  <table width="100%" border="0">
 <tr>
    <td><table  width="100%" border="0"> 
            <tr>
            <td>
            <div class="tab-main dark-gray">
       <h3 class="tab-main-title">个人支付通道</h3>
      </div></td>
      <tr>
        <td align="right">提示信息</td>
        </tr>
        </table>
        </td>
  </tr>
<!-- <tr> -->
<!-- <td align="left"> -->
	
<!-- 	<div class="control-group"> -->
<!--           <div class="controls"> -->
<!-- 	             <button type="submit" class="btn btn-info">首页</button> -->
<!-- 	              <button type="submit" class="btn btn-info">上一页</button> -->
<!-- 	              当前第 <input type="text" id="currentpage" name="currentpage" style="width: 40;height: 25"> 页 -->
<!-- 	               <button type="submit" class="btn btn-info">下一页</button> -->
<!-- 	                <button type="submit" class="btn btn-info">末页</button> -->
<!-- 	            </div>	             -->
<!-- 	         </div> -->
<!--     </td> -->
<!-- </tr>  -->
<tr>
    <td align="left"><div class="control-group">
          <div class="controls">
		  <form action="" method="post" id="form1" name="form1">
             <button type="submit" class="btn btn-info" onlick="fanhui()">返回</button>
            </form>
			</div>
         </div>
     </td>
</tr>

<tr>
    <td><table class="table table-hover">
                            <tr>
                              <th>客户编号</th>
                              <th>客户名称</th>
                              <th>通道名称</th>
							  
							 
                            </tr>
		                  <tr>
                            <c:forEach var="wmbm01dbo" items="${list}">
                              <td>${wmbm01dbo.k01}</td>
                              </c:forEach>
                              <c:forEach var="wmui01dbo" items="${list}">
                              <td>${wmui01dbo.f01}</td>
                              </c:forEach>
                              <c:forEach var="wmbm01dbo" items="${list}">
                              <td>${wmbm01dbo.f03}</td>
							 </c:forEach>    
                         </table></td>
                   </tr> 




</table>
<!-- </form> -->
 </body>
</html>