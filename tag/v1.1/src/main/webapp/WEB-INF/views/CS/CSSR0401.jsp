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
<link rel="stylesheet" href="/resources/css/wm/zk/css/demo.css" type="text/css">
<link rel="stylesheet" href="/resources/css/wm/zk/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/resources/js/wm/zk/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="/resources/js/wm/zk/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/resources/js/wm/zk/jquery.ztree.excheck-3.5.js"></script>



 </head>
 <body>
 
  <SCRIPT type="text/javascript">
         
         		
		function saveData(){
			try{
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes  = treeObj.getCheckedNodes(true);
				var str='';
				for(x in nodes){
					 var node =   nodes[x];	 
					  if(node.id==0){
						  continue;
					  }
					 
					  str += "<input type='hidden' name='k01' value='"+node.id+"'/>";
				}
				
				$("#hiddenbox").html(str);
                document.forms[0].submit();
			}catch(e){
			  return false;
			}
			
			
			
			
			
		}
		
		
		var setting = {
				
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes = ${menus};
		
		
	
		
		$(document).ready(function(){
			
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
		});
		//-->
	</SCRIPT>
 

  <form <c:if test="${empty parambean1}">
action="G.go"
</c:if>
<c:if test="${! empty parambean1}">
action="G.go"
</c:if> method="post" >
  <table width="100%" border="0"> 

 <!-- <div class="zTreeDemoBackground left"> -->
     
  
		<ul id="treeDemo" class="ztree"></ul>
 
 <!--  </div> -->
  <input type="hidden"  name="puk" value="${param.puk}" />
  <div id="hiddenbox" style="display:none">
   
  </div>
  
  <input type="button" value="提交" onclick="saveData()" />
  
  
  
  
  </table>
</form>

 </body>
</html>
