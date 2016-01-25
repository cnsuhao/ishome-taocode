<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
<!--	     <script src="/resources/js/bootstrap.js"></script>   -->
  <script type="text/javascript">
	function save(){
	        alert("11");		
			var  rs=document.formsb01.puk.value;
				alert("ok");
			 
 			 	document.formsb01.action="/CSSR03/C.go";
				formsb01.submit();	
         

		}

		function back(){
			document.formsb01.action="/CSSR02/F.go";
			formsb01.submit();
			}
		function clearForm(){	
			var formObj = document.getElementById("formsb01"); 
			
			if(formObj==undefined){
				
				return;
			}
			for(var i=0; i<formObj.elements.length; i++){
				if(formObj.elements[i].type == "text"){
					formObj.elements[i].value = ""; 
				}else if(formObj.elements[i].type == "password"){
					formObj.elements[i].value = "";  
				}else if(formObj.elements[i].type == "radio"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "checkbox"){
					formObj.elements[i].checked = false;
				}else if(formObj.elements[i].type == "file"){
					var file = formObj.elements[i]; 
					if (file.outerHTML){
						file.outerHTML = file.outerHTML;
					}else{
						 file.value = ""; 
					}
				}else if(formObj.elements[i].type == "textarea"){
					formObj.elements[i].value = "";
				}else if(formObj.elements[i].type == "select"){
					formObj.elements[i].options[0].selected = true;
				}else if(formObj.elements[i].type == "hidden"){
					formObj.elements[i].value = "";
				}
			}
		}	
		
           function showhide_obj(obj,icon)
             {
           obj=document.getElementById(obj);
           icon=document.getElementById(icon);
           if (obj.style.display=="none") 
  { 
        //指定文档中的对象为div,仅适用于IE;   
        div_list = document.getElementsByTagName("div");
        for (i=0; i< div_list.length;i ++)
        {
          thisDiv = div_list[i]; 
           if(thisDiv.id.indexOf("title")!=-1)//当文档div中的id含有list时,与charAt类似;
           {
            //循环把所有菜单链接都隐藏起来
           thisDiv.style.display="none";
           icon.innerHTML = "+";
           }
        } 
 
   myfont=document.getElementsByTagName("font");
   for(i=0;i<myfont.length;i++)
    {
    thisfont = myfont[i];
   }
    icon.innerHTML="-";
    obj.style.display=""; //只显示当前链接
}
 else
  {
  //当前对象是打开的，就关闭它;
   icon.innerHTML="+";
   obj.style.display="none";
   }
 }

function show_this(obj)
{
    obj=document.getElementById(obj);
    if (obj.id==obj.id) 
     {
       blinkicon=document.getElementsByTagName("font");
       for(x=0;x<blinkicon.length;x++)
        {
           if( blinkicon[x].id.indexOf("select")!=-1 && obj.id!=obj )
            {
               blinkicon[x].innerHTML=" ";
            }
        }
          obj.innerHTML=">"; 
     }
     else
     {
           obj.innerHTML=" ";
     }
}

</script>
<style type="text/css">
span{ width:100px;Height:25px;margin-left:15px;}


body,td{font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px;color:#000000;}
div{cursor:default;border-top:1px solid gray ; border-left:1px solid gray ;border-right:1px solid gray;background-color:#F6F6F6 ;width:200px;}
pre{ border:1px solid gray;font-family:verdana;Arial;padding:20px;}
</style>

 </head>
<body>
<form action="/CSSR02/F.go" method="get" name="formsb01" id="formsb01" >

<table>
<tr>
<td>
    <input type="text" placeholder="k02" id="k02" name="k02" size='20'  class="requried" value="${sr02dbo.k01}">
</td>
<td>
    <input type="text" placeholder="k03" id="k03" name="k03" size='20'  class="requried"
	value="${sr02dbo.k02}">

</td>
</tr>
</table>
<br><br><br>
<div  style="line-height:25px;" onClick="showhide_obj('title1','icon1')">
<font id='icon1'>+</font>菜单A</div>
<div id="title1"  style="line-height:22px;display:none;">
<span id="menu1_1" onMouseOver="this.style.backgroundColor='blue'" onMouseOut="this.style.backgroundColor=''" onclick="show_this('select1_1')">
<font id='select1_1'></font>
<input type="text" placeholder="k01" id="k01" name="k01" size='20'  class="requried" value="${parambean1.k01}" /></span>
</font>
<br />
<span id="menu1_2" onclick="show_this('select1_2')">
<font id='select1_2'></font>
<input type="hidden" placeholder="puk" id="puk" name="puk" size='20'  class="requried" value="${parambean1.puk}"/>
<input type="text" placeholder="k02" id="k02" name="k02" size='20'  class="requried" value="${parambean1.k02}"/>
</span>
</font>
<br />
<span id="menu1_3" onclick="show_this('select1_3')">
<font id='select1_3'></font>
<input type="text" placeholder="k03" id="k03" name="k03" size='20'  class="requried" value="${parambean1.k03}"/>
</span>
</font>
<br />
</div>
<br>
<br>
<div style="line-height:25px;" onClick="showhide_obj('title2','icon2')"><font id='icon2'>+</font>菜单B</div>
<div id="title2" style="background-color:#fffff3;line-height:22px;display:none;">
<span id="menu2_1" onclick="show_this('select2_1')"><font id='select2_1'></font><input type="text" placeholder="f01" id="f01" name="f01" size='20'  class="requried" value="${sr02dbo.f01}"/></span></font><br />
<span id="menu2_2" onclick="show_this('select2_2')"><font id='select2_2'></font><input type="text" placeholder="f02" id="f02" name="f02" size='20'  class="requried" value="${parambean1.f02}"/></span></font><br />
<span id="menu2_3" onclick="show_this('select2_3')"><font id='select2_3'></font><input type="text" placeholder="f03" id="f03" name="f03" size='20'  class="requried" value="${parambean1.f03}"/></span></font><br />
</div>


<br><br><br><br>







<blockquote>
    <p>
      <button type="button" class="button" onclick="back()">返回</button>
      <button type="button"   id="fbtn" onclick="clearForm()">清空</button>
       <button type="button" id="fn" id="save" onclick="save()">保存</button>
      
    </p>
  </blockquote>

</form>
</body>
</html>
