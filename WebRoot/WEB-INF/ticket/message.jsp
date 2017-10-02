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
<title>现场挂号</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		挂号业务 <span class="c-gray en">&gt;</span> 现场挂号 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">

		<table class="table table-border table-bordered table-bg table-hover">
			<thead>
				<tr>
					<th scope="col" colspan="4">所有科室及其医生</th>
				</tr>
				<tr class="text-c">
					<th width="30">科室编号</th>
					<th width="30">科室名称</th>
					<th width="80" >科室介绍</th>
					<th width="400" >可挂号医生</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${map}" var="maps">
				<tr class="text-c">
					<td>${maps.key.deid}</td>
					<td>${maps.key.dename}</td>
					<td>${maps.key.intro}</td>
			<td class="text-l">
			<c:forEach items="${maps.value }" var="doc">
			&nbsp;<span ${doc.pcreg>doc.xcreg?'class="label label-success radius"':'class="label label-danger radius"' } >
			<a title="挂号(${doc.xcreg }/${doc.pcreg })" href="javascript:;" onclick="regDoc(this,${doc.doid},${doc.xcreg },${doc.pcreg })"
			 class="ml-5" style="text-decoration:none;color:white;">
			 <i class="Hui-iconfont">&#xe62c;</i>${doc.title}：${doc.doname } </a>
			</span>&nbsp;
			</c:forEach></td>
				</tr> 
				</c:forEach>
			</tbody>
		</table>
		
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
	
	$('.table-hover').dataTable({
		"aaSorting": [[ 0, "asc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"pading":false,
		"aoColumnDefs": [
		  {"orderable":false,"aTargets":[1,2]}// 不参与排序的列
		]
	});
	
});

function regDoc(obj,id,rnum,rsum){
	if(rnum<rsum){
		/* alert(id+"医生"+rnum+"/"+rsum); */
		layer_show('门诊挂号','findByDoname?doid='+id,'580','400');
	}else{
		//人满了
		layer.tips('这个医生预约人数满了！', obj, {
		  tips: [1, '#D52B2B'],
		  time: 4000
		});
	}
}

</script>
</body>
</html>