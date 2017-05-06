<%@ page language="java" contentType="text/html; charset=gbk"  pageEncoding="UTF-8"  %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Cookie killMyCookie = new Cookie("mycookie", null); 
killMyCookie.setMaxAge(0); 
killMyCookie.setPath("/"); 
response.addCookie(killMyCookie); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html">
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
    <script src="js/jquery-3.2.1.js" charset="utf-8"></script>
    
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<!--  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 -->
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 -->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 -->
<title>QRcode</title>

<link rel="stylesheet" href="css/sheet.css" type="text/css"/>

</head>

<body>
<script type="text/javascript">
function toValidateForm()
{   
	   var x =document.getElementsByName("recname");
	   var y =document.getElementsByName("recid"); 
	   var m =document.getElementsByName("viname");
	   var n =document.getElementsByName("viid"); 
	/* alert(x[0].value);
	   if (x[0].value==null || x[0].value=="" ){
	     alert("需要邀请人");
	     return false;
	     }
	   else if (y[0].value==null || y[0].value==""){	
		     alert("eduroamID不能为空");
		     return false;
		} */
	   if (m[0].value==null || m[0].value==""){	
		     alert("请填写名字");
		     return false;
		}
	   else if (n[0].value==null || n[0].value==""){	
		     alert("请填写你的eduroamID");
		     return false;
		}
	  

           return true;
     
	   
}
</script>
<%
request.setCharacterEncoding("utf8");
%>

	<div class="container">

	    <form  action="gif" method="post"  onsubmit="return toValidateForm();">
		  <div class="form-group">
		    <label for="name">邀请人：</label>
		    <%
request.setCharacterEncoding("utf8");
%>
		   <input  type="text" class="form-control"  name="recname"  value=<%=request.getParameter("rec") %>  readonly=true> 
		  
		  </div>
		 <div class="form-group">
		    <label for="name">邀请人eduid：</label>
		    <input type="text" class="form-control"  name="recid"  value=<%=request.getParameter("recid") %>  readonly="true" >
		  </div>
		 <div class="form-group">
		    <label for="name">访客姓名：</label>
		    <input type="text" class="form-control"  name="viname"  placeholder="请输入名称">
		  </div>
		   <div class="form-group">
		    <label for="name">访客eduid：</label>
		    <input type="text" class="form-control"  name="viid"  placeholder="请输入eduroamid">
		  </div>
		 
		  <button type="submit" class="btn btn-primary"   >提交申请</button>
		</form> 
	     
	
	<%-- <form class="form-horizontal" name= "myform" method = 'post'  action="gif"  onsubmit="return validateForm();">
	    <div  class="form-group"> 邀请人：	</div> <input  class="col-md-5"  id="recomment"  type="text" name="recom" value=<%=request.getParameter("rec") %>  >
	    <div  class="form-group"> 邀请人id： </div><input id="recommentid"  type="text" name="recomid" value=<%=request.getParameter("recid") %>  >
		<div  class="form-group"> 姓名：		</div> <input id="numid"  type="text" name="myname"> 
		<div  class="form-group">EduroamID: </div><input  id="eduid"  type="text" name="myid">    
	    <button  type="submit"   class ="btn btn-primary" value="生成二维码"  >邀请</button>            
	   </form>  --%>
	</div>




</body>
</html>