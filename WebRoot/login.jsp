<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path %>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
	
	
  </head>
  
  <body>
  <input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="login" id="ff" method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="aname" name="aname" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="pwd" name="pwd" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
         	<input id="code" name="verifyCode" type="text" placeholder="验证码" style="width: 100px;" class="input-text size-L"/>
			&nbsp;&nbsp;									
		<img align="top" onclick="change()" src="${pageContext.request.contextPath}/imagecode.html" 
		id="Verify"  style="cursor:pointer; height:40px; width: 160px;" alt="看不清，换一张" title="看不清，换一张"/></div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
             <span style="font-size: 20px;color: red">${param.message==0?'验证码错误!':param.message==1?'用户名或密码错误!':'' }</span></label>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
       <!--    <input name="" type="button" onclick="butt()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> -->
    	 <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> 
        <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">  
        </div>   
      </div>
    </form>
  </div>
</div>
<div class="footer">NEWER医院后台管理系统</div>
<script type="text/javascript" src="<%=path %>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/static/h-ui/js/H-ui.min.js"></script>

	<script type="text/javascript">		
		function change(){
	
			var time = new Date();
			var timestamp = time.getSeconds();
			$("#Verify").attr("src","${pageContext.request.contextPath}/imagecode.html?timestamp="+timestamp);			
		}
		
			
	</script>


  </body>
</html>
