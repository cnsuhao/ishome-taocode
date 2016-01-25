<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/lib/pagtag.tld" prefix="pagtag" %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <title>允许用户登录</title>
 
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
  

  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0; padding:0;  border:1px solid #DBDBDB;">
    <tr height="40" >
		<td style="background:#f8f8f8; border-bottom:1px solid #dbdbdb; color:#666666;"><h4 style="margin-left:10px;"><span style="font-size:14px; font-weight:bold; color:#666666;">允许登录用户</span></h4></td>
	</tr>
	<tr >
		<td><table border="0" cellpadding="0" cellspacing="0" style="width:100%; color:red; font-size:12px; margin:0; padding-top:10px;">
		  <tr>
		    <td align="right">${message}</td>
		    <td width="2%">&nbsp;</td>
	      </tr>
		  </table>
	    </td>
	</tr>
	
	  

<%--       <form action="/CSSR01/F.go" method="get" id="form1">
    	<fieldset>
    	    登录名：
    		<input type="text" placeholder="登录名" id="f03" name="f03" >&nbsp;&nbsp;
    	    用户分类:
    	     <select name="">
    	        <c:forEach var="parentGroup"  items="${parentGroups}">
              <option value="${parentGroup.k01}">${parentGroup.f01}</option>   
    	      </c:forEach>
    	    
    	    
         </select>&nbsp;&nbsp;
         用户分类:
			 <select name="">
             <option value="WM.QT">前台</option>
             <option value="WM.HT">后台</option>
             <option value="WM.ZK">总控</option>
                          <option value="YD">云端</option>
             
         </select>
    		<br>
         <p style="margin-left: 180px">
         	<button class="btn" type="button" id="btn1" onclick="location.href=('/CSSR01/H.go')">添加</button>&nbsp;&nbsp;&nbsp;
         	<button class="btn" type="submit" id="btn2" >检索</button>
         </p>
         </fieldset> 
    </form> --%>
  
	
	<tr height="50" style="border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table align="center" width="100%" style="margin:0; padding:0;" border="0">
				<tr>
					<td width="20px"></td>
					<pagtag:pagtag pageVo="${pageVO}"  url="F.go" />

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
					
					   <a href="H.go">添加</a>
					   <!-- <a href="">检索</a> -->
					  <!-- 
					   <button type="submit" class="btn"  onclick="tianjia()">添加</button>
						<button type="submit" class="btn btn-primary">检索</button>
						 -->
					
					</td>
					<td width="2%"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="border-bottom:1px solid #DBDBDB; border-left:1px solid #DBDBDB; border-right:1px solid #DBDBDB;">
		<td>
			<table class="table table-hover">
                <tr>
                    <th>登录名</th>
  <!--   <th>人员编号</th> -->
 <!--    <th>用户组</th> -->
 <!--    <th>分类</th> -->
   
                              <th class="table-th-do">操作</th>
                </tr>
				  
				    <c:forEach var="sr01dbo" items="${pageVO.pageData}">
  <tr>
    <td>${sr01dbo.f03}</td>
 <%--    <td>${sr01dbo.f01}</td> --%>
  <%--   <td>
    <c:if test=" ${sr01dbo.f05=='WM.HT'}">
    后台
    </c:if>
      <c:if test=" ${sr01dbo.f05=='WM.HT'}">
    总控
    </c:if>
   </td>  --%>
<%-- 	<td>${sr01dbo.f06}</td> --%>
	<td align="center">
	<!-- <a href="">临时赋权</a>	 -->
    <a href="R.go?puk=${sr01dbo.puk}" >编辑</a> 
    <a href="D.go?puk=${sr01dbo.puk}&uu1=${sr01dbo.uu1}">删除</a> 
    </td>
   
  </tr>
   </c:forEach>
				  
				
            </table>
		</td>
	</tr>
  </table>
</body>
</html>