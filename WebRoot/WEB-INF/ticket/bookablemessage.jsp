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
<title>${bookable.doname}医生挂号信息</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> <span class="c-gray en">&gt;</span>
		挂号管理 <span class="c-gray en">&gt;</span> ${bookable.doname}医生挂号信息<a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<table class="table table-border table-bordered table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="9">${bookable.doname}医生挂号具体信息</th>
				</tr>
				<tr class="text-c">
					<th width="80">医生姓名</th>
					<th width="80">所属科室</th>
					<th width="90">可预约人数</th>
					<th width="80">已预约人数</th>
					<th width="80">挂号金额</th>
					
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td>${bookable.doname }</td>
					<td>${bookable.dename }</td>
					<td>${bookable.xcum }</td>
					<td>${bookable.xcyum }</td>
					<td>${bookable.bcost }</td>
					
				</tr>
			</tbody>
		</table>
	<div class="page-container">
		<form class="form form-horizontal" id="form-ticket-add" method="post" action="addticket">
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text"  placeholder="" id="bid" name="bid" value="${bookable.bid }">
		</div>
	</div>
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text"  placeholder="" id="doname" name="doname" value="${bookable.doname }">
		</div>
	</div>
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text"  placeholder="" id="dename" name="dename" value="${bookable.dename }">
		</div>
	</div>
	<div class="row cl">
		<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" class="input-text" value="${bookable.bcost }" placeholder="" id="bcost" name="bcost">
		</div>
	</div>
	
	
	<div class="row cl">
	<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>诊疗卡号：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="" placeholder="" id="medcard" name="medcard">
		</div>
	</div>
	
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;付费挂号&nbsp;&nbsp;" >
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




</script>
</body>
</html>