<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <title>用户组定义</title>
 
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

  </head>
  <body>
  

   <%--    <form  action="/CSSR03/F.go" method="get" id="form1">
    	<fieldset>
    		<p>
    		名称:
    		<input type="text"  id="messageId"  style=" width:180px; height: 26px" value="名称">
    		编号:
    		<input type="text"  id="messageId"  style=" width:180px; height: 26px" value="编号">&nbsp;<br><br><br>
    			上级用户组:
    			<select name="k03" style="width:180px; height:26px">
    			   <c:forEach  var="parentGroup" items="${parentGroups}">
    			    <option value="${parentGroup.k01}">${parentGroup.f01}</option>   
    			   </c:forEach>
    			
        	 </select>
        	 分类:
			 <select name="f02" style="width:180px; height:26px">
			     <option value="QT">前台</option>
    	    <option value="HT">后台</option>
    	    <option value="ZK">总控</option>
    	    <option value="YD">云端</option>
        	 </select>
    		</p>
    		<p style="margin-left: 500px" >    
    		<button class="btn" type="button" id="btn1" onclick="location.href=('/CSSR03/H.go')">添加</button>	
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn">检索</button></p>
    	</fieldset>
    </form>
     --%>
  <table width="100%" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb;"><h4 style="margin-left:10px;"><span style="font-size:14px; font-weight:bold; color:#666666;">用户组定义</span></h4></td>
	</tr>
	<tr >
		<td align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td  align="left" >${message}</td>
		    <td width="100" align="right" ><form action="" method="post" id="form1" name="form1" ><table width="100" border="0" align="right" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <a href="H.go">添加</a>
      <!--  <a href="">检索</a> -->
      <!-- 
					   <button type="submit" class="btn"  onclick="tianjia()">添加</button>
						<button type="submit" class="btn btn-primary">检索</button>
						 -->
    </td>
  </tr>
</table></form>
</td>
		    <td width="50" align="right" >&nbsp;</td>
		   
	      </tr>
		  </table>
	    </td>
	</tr>
	
	<tr  >
		<td>
			<table align="center" width="100%" >
				<tr>
					<td align="left" style="padding:20px;">
					<pagtag:pagtag pageVo="${pageVO}"  url="F.go" /></td>

					<!-- 	<div class="pagination" style="margin:0;">
							<ul>
								<li><a href="#">上一页</a></li>
								<li class="active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一页</a></li>
                            </ul>
						</div> -->
					</td>
					<td width="30%" align="right">

					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table border="0" cellpadding="0" cellspacing="0" class="table table-hover">
                <tr>
               <th>编号</th>
	<th>名称</th>
<!-- 	<th>上级用户组</th> -->
	<td>分类</td>
	<th>&nbsp;&nbsp;</th>
	<th>&nbsp;&nbsp;</th>
                              <th class="table-th-do">操作</th>
                </tr>
				  
				 <c:forEach var="sr03dbo" items="${pageVO.pageData}">
   <tr>
	<td>${sr03dbo.k01}</td>
	<td>${sr03dbo.f01}</td>
	<%-- <td>${sr03dbo.k02}</td> --%>
	<td>
	<c:if test="${sr03dbo.f02=='WM.HT'}">
	    后台
	</c:if>
	<c:if test="${sr03dbo.f02=='WM.ZK'}">
	    总控
	</c:if>
	</td>
	<td>
	    <a href="R.go?puk=${sr03dbo.puk}" >编辑</a> 
	
	</td>
	<td>
	<a href="D.go?puk=${sr03dbo.puk}">删除</a>
	</td>
	
	  <td>
    <a href="TG.go?puk=${sr03dbo.puk}">赋权</a> 
  
    </td>
   </tr>
   </c:forEach>
            </table>
		</td>
	</tr>
  </table>
</body>
</html>