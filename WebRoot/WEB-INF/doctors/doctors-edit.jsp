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
		<input type="hidden" name="doid" value="${dr.doid}">
		<input type="hidden" name="doexist" value="${dr.doexist}">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>医生名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${dr.doname}" id="doname" name="doname" ${dr.doname!=null?'readonly style="border-style:none"':''} style="width: 225px;">
			</div>
		</div>
		<c:if test="${dr.doid eq null }">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>登录用户名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" id="aname" name="aname" style="width: 225px;">
			</div>
		</div>
		</c:if>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>挂号费：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" min="0" step="1" class="input-text" value="${dr.bcost}" placeholder="" id="bcost" name="bcost" style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>每小时可挂号人数（网上）：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" min="0" step="1" class="input-text" value="${dr.pcreg}" placeholder="" id="pcreg" name="pcreg" style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>每小时可挂号人数（现场）：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="number" min="0" step="1" class="input-text" value="${dr.xcreg}" placeholder="" id="xcreg" name="xcreg" style="width: 225px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>医生职位：</label>
			<div class="formControls col-xs-8 col-sm-9"><span class="select-box" style="width: 225px;">
				 <%-- <input type="text" class="input-text" value="${dr.title}" id="title" name="title" style="width: 225px;"> --%>
				<select class="select" size="1" name="title" style="width: 215px;">
					<option value="1" ${dr.title==1?'selected':""}>住院医师</option>
					<option value="2" ${dr.title==2?'selected':""}>主治医师</option>
					<option value="3" ${dr.title==3?'selected':""}>副主任医师</option>
					<option value="4" ${dr.title==4?'selected':""}>主任医师</option>
				</select>
				</span>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>科室：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width: 225px;">
				<select class="select" size="1" name="deid" style="width: 215px;">
					<c:forEach items="${dylist}" var="dy">
			    		<option value='${dy.deid}' 
			    		${dy.deid==dr.deid?'selected':""}> 
			    		${dy.dename}</option>
			    	</c:forEach>
				</select>
				</span> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>医生简介：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<%-- <input type="text" class="input-text" value="${dr.title}" id="info" name="info" style="width: 225px;"> --%>
				<textarea cols="30" rows="2" id="info" name="info" >${dr.info}</textarea> 
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">值班信息：<br> &nbsp;</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:260px;text-align: center;">
				<dl class="permission-list">
					<dt>
			 		<label>	全选:&nbsp;
							<input type="checkbox" value="" name="user-Character-0" id="user-Character-0">
							</label> &nbsp;&nbsp;&nbsp;
							<!-- <label>
							上午:&nbsp;<input type="checkbox" value="" name="user-Character-0" id="user-Character-0">
							</label>&nbsp;&nbsp;
							<label>
							下午:&nbsp;<input type="checkbox" value="" name="user-Character-0" id="user-Character-0">
							</label> -->
					</dt>
					<dd>
						<dl class="cl permission-list2" >
						<dt>
							
							  周一:
								&nbsp;
								&nbsp;
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.monam eq 1? 'checked':''} id="monam" name="monam" ></label>
								&nbsp;
								&nbsp;
							    <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.monpm eq 1? 'checked':''} id="monpm" name="monpm"></label>
							<br>
							   
							   
							   周二:
								&nbsp;
								&nbsp; 
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.tueam eq 1? 'checked':''} name="tueam" id="tueam"></label>
								&nbsp;
								&nbsp;
							    <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.tuepm eq 1? 'checked':''} name="tuepm" id="tuepm"></label> 
							   <br>
							  
							    周三:
								&nbsp;
								&nbsp;
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.wedam eq 1? 'checked':''} name="wedam" id="wedam"></label>
								&nbsp;
								&nbsp;
							    <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.wedpm eq 1? 'checked':''} name="wedpm" id="wedpm"></label>
							    <br>
							    周四:
								&nbsp;
								&nbsp;
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.thuam eq 1? 'checked':''} name="thuam" id="thuam"></label>
								&nbsp;
								&nbsp;
							    <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.thupm eq 1? 'checked':''} name="thupm" id="thupm"></label>
							    <br>
							    周五:
								&nbsp;
								&nbsp;
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.friam eq 1? 'checked':''} name="friam" id="friam"></label>
								&nbsp;
								&nbsp;
							        <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.fripm eq 1? 'checked':''} name="fripm" id="fripm"></label>
							    <br>	
							    周六:
								&nbsp;
								&nbsp;
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.satam eq 1? 'checked':''} name="satam" id="satam"></label>
								&nbsp;
								&nbsp;
							        <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.satpm eq 1? 'checked':''} name="satpm" id="satpm"></label>
							    <br>
							    周日:
								&nbsp;
								&nbsp;
								<label>上午:&nbsp;<input type="checkbox" value="1" ${dr.sunap eq 1? 'checked':''} name="sunap" id="sunap"></label>
								&nbsp;
								&nbsp;
							        <label>下午:&nbsp;<input type="checkbox" value="1" ${dr.sumpm eq 1? 'checked':''} name="sumpm" id="sumpm"></label>
							    <br>
										 
							</dt>
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
			doname:{
				required:true,
				minlength:2,
				maxlength:16
			},
			bcost:{
				required:true,
			},
			aname:{
				required:true,
			},
			xcreg:{
				required:true,
			},
			pcreg:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
					url:"doctors-modifyAndadd",
                    type: "post",
                    success: function (data) {
                    		//alert(data)
                        if (data.modify == 'modify'){
                       		layer.msg('修改成功!',{icon:6,time:3000});
                       		setTimeout(function() {
	                       		var index = parent.layer.getFrameIndex(window.name);
	                        	window.parent.location.reload();
	                        	parent.layer.close(index);
								
								/* parent.layer.close(index); */
                       		}, 1000)
                        	
						
                        }if (data.add == 'add'){
                       		layer.msg('添加成功!同时为医生创建了一个账户。<br>    初始密码为111111！',{icon: 6,time:3000});
                       		setTimeout(function() {
	                       		var index = parent.layer.getFrameIndex(window.name);
	                        	window.parent.location.reload();
	                        	parent.layer.close(index);
								
								/* parent.layer.close(index); */
                       		}, 2500)
                        	
						
                        }if(data.add == 'had'){
                        	layer.msg('登录名已存在，无法添加！',{icon: 2,time:1000});
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
	/* $(".permission-list2 dd input:checkbox").click(function(){
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
	}); */
	
	
});

</script> 
<!--/请在上方写此页面业务相关的脚本-->
  </body>
</html>
