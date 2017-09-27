<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>预约订单管理</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>首页<span class="c-gray en">&gt;</span>
		挂号业务 <span class="c-gray en">&gt;</span>取预约号 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="text-c"> 
		<form action="findAll">
			<label>根据身份证查询：</label>
				<input type="text" class="input-text" style="width:230px" placeholder="身份证号" id="idcard" value="${idcard }" name="idcard">
				<button type="submit" class="btn btn-success radius" onclick="this.form.submit()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
				<a href="javascript:;" onclick="c_clear()" class="btn btn-secondary radius"><i class="Hui-iconfont">&#xe68f;</i> 重置</a>
		</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> &nbsp;</span> </div>
		
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="80">预约编号</th>
						<th width="100">预约人姓名</th>
						<th width="80">医疗卡号</th>
						<th width="140">手机</th>
						<th width="150">预约科室</th>
						<th width="120">预约医生</th>
						<th width="130">预约时间</th>
						<th width="70">挂号金额</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.list }" var="bk">
						<tr class="text-c">
							<td>${bk.red }</td>
							<td>${bk.pname }</td>
							<td>${bk.medcard==null?'未绑定':bk.medcard}</td>
							<td>${bk.phone }</td>
							<td>${bk.dename }</td>
							<td>${bk.doname }</td>
							<td><fmt:formatDate value="${bk.bdate }" pattern="yyyy-MM-dd"/></td>
							<td>${bk.bcost }</td>
<!-- member_edit('票号单','viewticket?red=${bk.red}','','510') -->
							<td><a title="获取票号" href="javascript:;"
								onclick="addticket(${bk.red},'${bk.medcard}')"
								class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">扣费取票</i></a> </td>
								
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
						"aTargets" : [  8 ]
					} // 制定列不参与排序
				]
			});
	
		});
		
		function c_clear(){
			$("#idcard").val("");
		}
		
		/*用户-票号*/
		function member_edit(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		
		function addticket(red,card){
			if(card.length==0 ){
				//没卡
				layer.prompt({title: '输入您的诊疗卡号'},function(val, index){
					bcard(red,val);
				});
			}else{
				//有卡
				member_edit('票号单','viewticket2?red='+red+'&card='+card,'580','450');
			}
		}
		//绑定卡
		function bcard(red,card){
			$.ajax({
				type: 'POST',
				url: 'viewticket',
				data:{red:red,card:card},
				dataType: 'json',
				success: function(data){
					if(data.result=='nocard'){
						layer.alert('卡号错误，无法绑定！', {
		   				 skin: 'layui-layer-lan'
		   				 ,closeBtn: 0
		 	   			,anim: 4 //动画类型
		 				 });
					}else{
						member_edit('票号单','viewticket2?red='+red+'&card='+card,'580','450');
					}
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		}
		
		
		
	</script>
</body>
</html>