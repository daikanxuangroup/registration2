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
<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>医疗卡管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> <span class="c-gray en">&gt;</span>
		医疗卡管理中心 <span class="c-gray en">&gt;</span> 用户管理 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a href="javascript:;"
				onclick="member_add('添加诊疗卡','cards','450','405')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					办卡业务</a></span> <span class="r">共有诊疗卡：<strong>${requestScope.rows}</strong>
				张
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="80">ID</th>
						<th width="100">用户名</th>
						<th width="40">性别</th>
						<th width="110">手机</th>
						<th width="200">身份证</th>
						<th width="80">余额</th>
						<th width="130">办卡时间</th>
						<!-- <th width="70">状态</th> -->
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.list }" var="cd">
						<tr class="text-c">
							<td>${cd.cid }</td>
							<td>${cd.pname }</td>
							<td>${cd.sex}</td>
							<td>${cd.phone }</td>
							<td>${cd.idcard }</td>
							<td>${cd.ramaining }</td>
							<td><fmt:formatDate value="${cd.newdate }"
									pattern="yyyy-MM-dd" /></td>
							<%-- <c:if test="${0 eq cd.doexist }">
								<td class="td-status"><span
									class="label label-defaunt radius">已挂失</span></td>
							</c:if>
							<c:if test="${1 eq cd.doexist }">
								<td class="td-status"><span
									class="label label-success radius">正常使用</span></td>
							</c:if> --%>
							
							<td class="td-manage"> <a
								title="充值" href="javascript:;"
								onclick="member_edit(${cd.cid})"
								class="ml-5" style="text-decoration:none"><i
									class="Hui-iconfont">&#xe6b7;充值</i></a> 
								</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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
		$(function() {
	
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "desc" ] ], //默认第几个排序
				"bStateSave" : true, //状态保存
				"aoColumnDefs" : [
					//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
					{
						"orderable" : false,
						"aTargets" : [ 7 ]
					} // 制定列不参与排序
				]
			});
	
		});
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
	
		}
		
	
		/*用户-充值*/
		
		
		function member_edit(cid){
	
	layer.prompt({title: '充值金额'},function(val, index){
		$.ajax({
			type: 'POST',
			url: 'updatecard',
			data:{money:val,"cid":cid},
			dataType: 'json',
			success: function(data){
				if(data.result<=0){
				layer.alert('金额输入错误，充值失败！', {
   				 skin: 'layui-layer-lan'
   				 ,closeBtn: 0
 	   			,anim: 4 //动画类型
 				 });
	  				layer.close(index);
				}else{
					
					/* layer.msg("卡号"+cid+"充值了："+val+"元"); */
					layer.alert("卡号"+cid+"充值了："+val+"元",{
					  skin: 'layui-layer-lan', //样式类名
					  closeBtn: 0, //不显示关闭按钮
					  anim: 2
					});
					layer.close(index);
	  				setTimeout(function () { 
				        location.reload();
				    }, 800);
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
 		
	});


}

/*用户-挂失*/
/* function member_stop(obj,id){
	layer.confirm('确认要挂失吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已挂失</span>');
				$(obj).remove();
				layer.msg('已挂失!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
 */
/*用户-启用*/
/* function member_start(obj,id){ 
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="挂失"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">正常使用</span>');
				$(obj).remove();
				layer.msg('已恢复使用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
} */
	</script>
</body>
</html>