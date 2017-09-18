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
		<input type="hidden" name="drid" value="${dr.drid}">
		<input type="hidden" name="drstate" value="${dr.drstate}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>药品名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${dr.drname}" id="drname" name="drname" ${dr.drname!=null?'readonly style="border-style:none"':''} style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>库存：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" min="0" step="1" class="input-text" value="${dr.drsum}" placeholder="" id="drsum" name="drsum" style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" min="0.01" step="0.01" class="input-text" value="${dr.drprice}" placeholder="" id="drprice" name="drprice" style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">药品类型：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width: 225px;">
				<select class="select" size="1" name="dyid" style="width: 215px;">
					<c:forEach items="${dylist}" var="dy">
			    		<option value='${dy.dyid}' 
			    		${dy.dyid==dr.dyid?'selected':""}> 
			    		${dy.dyname}</option>
			    	</c:forEach>
				</select>
				</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">可用科室：<br> &nbsp;</label>
			<div class="formControls col-xs-8 col-sm-9" style="width: 380px;">
				<dl class="permission-list">
					<dt>
			 		<label>
							<input type="checkbox" value="" name="user-Character-0" id="user-Character-0">
							全选</label> 
					</dt>
					<dd>
						<dl class="cl permission-list2" >
						<!-- <dt>  -->
							<c:forEach items="${delist}" var="delist">
								<c:set var="flag" value="0"></c:set>
								<c:forEach items="${drdelist }" var="dd">
									<c:if test="${dd.deid eq delist.deid }">
										<c:set var="flag" value="1"></c:set>
									</c:if>
								</c:forEach>
								<li style="float:left;padding:2px;display:block;width:80px">
							<label >
									<input type="checkbox" value="${delist.deid }" name="deid" id="user-Character-0-0-0" ${flag eq 1?'checked':''} >
									${delist.dename } </label>
								</li>
							</c:forEach>
							
						<!-- 	 </dt>  -->
						</dl> 
					</dd>
				</dl>
			</div>
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
			drname:{
				required:true,
				minlength:2,
				maxlength:16
			},
			drsum:{
				required:true,
			},
			drprice:{
				required:true,
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
					url:"editOver",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.result == 'success'){
                        	var index = parent.layer.getFrameIndex(window.name);
							window.parent.location.reload();
							parent.layer.close(index);
                        }
                    },
                    error: function () {
                        alert("系统出现错误，请联系管理员");
                    }
                });
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
