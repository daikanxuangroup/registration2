<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=path %>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/static/h-ui.admin/skin/default/skin.css"
	id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path %>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>票号生成</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i><span class="c-gray en">&gt;</span>
		挂号管理 <span class="c-gray en">&gt;</span> 取票扣费 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<table class="table table-border table-bordered table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="9">票单基本信息</th>
				</tr>
				<tr class="text-c">
					<th width="80">票号</th>
					<th width="80">用户名</th>
					<th width="90">医生</th>
					<th width="80">科室</th>
					<th width="80">挂号费</th>
					<th width="200">诊疗卡号</th>
					
				</tr>
			</thead>
			
			<tbody>
				<tr class="text-c">
					<td>${books.snum }</td>
					<td>${books.pname }</td>
					<td>${books.doname }</td>
					<td>${books.dename }</td>
					<td>${books.bcost }</td>
					<td>${books.medcard }</td>
				</tr>
			</tbody>
		</table>
	<div class="page-container">

		
		
		<form class="form form-horizontal" id="form-ticket-add" method="post" action="getticket">
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text"  placeholder="" id="bid" name="bid" value="${books.bid }">
		</div>
	</div>
	<div class="row cl">
	<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>应付金额：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${books.bcost }" placeholder="" id="bcost" name="bcost">
		</div>
	</div>
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>付费方式：</label>
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
			<div class="radio-box">
				<input name="medcard" type="radio" id="sex-1" checked  value="${books.medcard }">
				<label for="sex-1">诊疗卡</label>
			</div>
			<div class="radio-box">
				<input type="radio" id="sex-2" name="medcard" value="${books.medcard }">
				<label for="sex-2">现金</label>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text" value="${books.snum }" placeholder="" id="snum" name="snum">
		</div>
	</div>
	
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text" placeholder="" name="red" id="red" value="${books.red }" >
		</div>
	</div>
	
	
	
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;" >
		</div>
	</div>
	</form>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path %>/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path %>/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path %>/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path %>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path %>/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">

$(function(){
	$("#form-ticket-add").validate({
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
		alert("xingxi");
		$(form).ajaxSubmit({
					url:"getticket",
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
			
});
 
</script>
</body>
</html>