<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'drug.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />

  </head>
  
  <body>
<article class="page-container">
	
	<form action="" method="post" class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>科室名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden"  name="deid" value="${departs.deid }">
				<input type="text" class="input-text" value="${departs.dename}" id="dename" name="dename" ${departs.dename!=null?'readonly':''} style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>简介：</label>
			<div class="formControls col-xs-8 col-sm-9">
				
				<%-- <input type="text"  class="input-text" value="${departs.intro }" id="intro" name="intro" style="width: 225px;height: 100px"> --%>
				<textarea cols="30" rows="5" id="intro" name="intro" >${departs.intro }</textarea> 
			</div>
		</div>
		<div class="row cl">
			<c:if test="${empty departs }">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>可用：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<%-- <input type="text" min="0" step="1" class="input-text" value="${dr.drsum}" placeholder="" id="deexist" name="deprtts.deexist" style="width: 225px;"> --%>
				<input type="radio" id="deexist" name="deexist" value="1" ${departs.deexist } checked="checked"  >是
      			<input type="radio" id="deexist" name="deexist" value="0" ${departs.deexist }>否
			</div>
			</c:if>
			<c:if test="${!empty departs }">
			<div class="formControls col-xs-8 col-sm-9">
				<%-- <input type="text" min="0" step="1" class="input-text" value="${dr.drsum}" placeholder="" id="deexist" name="deprtts.deexist" style="width: 225px;"> --%>
				<input type="hidden" id="deexist" name="deexist" value="1" ${departs.deexist }>
      			<input type="hidden" id="deexist" name="deexist" value="0" ${departs.deexist }>
			</div>
			</c:if>
		</div>
		

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
	
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">


$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			dename:{
				required:true,
				minlength:2,
				maxlength:16
			},
			intro:{
				required:true,
			},
			deexist:{
				required:true,
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
					url:"save",
                    type: "post",
                    success: function (data) {
                    	 if (data.modify == 'modify'){
                       		layer.msg('修改成功!',{icon: 6,time:3000});
                       		setTimeout(function() {
	                       		var index = parent.layer.getFrameIndex(window.name);
	                        	window.parent.location.reload();
	                        	parent.layer.close(index);
								
								/* parent.layer.close(index); */
                       		}, 1000)
                        	
						
                        }if (data.add == 'add'){
                       		layer.msg('添加成功!',{icon: 6,time:3000});
                       		setTimeout(function() {
	                       		var index = parent.layer.getFrameIndex(window.name);
	                        	window.parent.location.reload();
	                        	parent.layer.close(index);
								
								/* parent.layer.close(index); */
                       		}, 1000)
                        	
						
                        }
                    },
                    error: function () {
                      alert("系统出现错误，请联系管理员");
                    }
                });
			/* var index = parent.layer.getFrameIndex(window.name);
			window.parent.location.reload();
			parent.layer.close(index); */
		}
	});
	
	$(".permission-list dt input:checkbox").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
	$(".permission-list2 dd input:checkbox").click(function(){
		var l =$(this).parent().parent().find("input:checked").length;
		var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
		if($(this).prop("checked")){
			$(this).closest("dl").find("dt input:checkbox").prop("checked",true);
			$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
		}
		else{
			if(l==0){
				$(this).closest("dl").find("dt input:checkbox").prop("checked",false);
			}
			if(l2==0){
				$(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
			}
		}
	});
	
	
});

</script> 
 
<!--/请在上方写此页面业务相关的脚本-->
  </body>
</html>
