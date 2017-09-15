<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bookable.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/css/style.css" />

  </head>
  
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		选择科室：
	 <span class="select-box inline">
		<select name="" class="select">
			<option value="0">骨科</option>
			<option value="1">心外科</option>
			<option value="2">肾内科</option>
		</select>
		</span> &nbsp;&nbsp;&nbsp;&nbsp;选择排班周：
		<input type="text" id="week" class="input-text"  style="width:120px">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询排班</button>
		<a class="btn btn-primary radius" data-title="自动排班" data-href="article-add.html" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 自动排班</a>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 删除该周排班</a> </span>  </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					
					<th width="40">ID</th>
					<th width="60">医生姓名</th>
					<th width="60">医生职称</th>
					<th width="40">时段</th>
					<th width="80">2017-09-10 星期日</th>
					<th width="80">2017-09-11 星期一</th>
					<th width="80">2017-09-12 星期二</th>
					<th width="80">2017-09-13 星期三</th>
					<th width="80">2017-09-14 星期四</th>
					<th width="80">2017-09-15 星期五</th>
					<th width="80">2017-09-16 星期六</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td rowspan=2>10001</td>
					<td rowspan=2>李时珍</td>
					<td rowspan=2>主任医师</td>
					<td>上午</td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
				</tr>
				<tr class="text-c">
					<td hidden>10001</td>
					<td hidden>李时珍</td>
					<td hidden>主任医师</td>
					<td>下午</td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
				</tr>
			 	<tr class="text-c">
					<td rowspan=2>10002</td>
					<td rowspan=2>华佗</td>
					<td rowspan=2>副主任医师</td>
					<td>上午</td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
				</tr>
				<tr class="text-c">
					<td hidden>10002</td>
					<td hidden>华佗</td>
					<td hidden>副主任医师</td>
					<td>下午</td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius"></span></td>
					<td class="td-status"><span class="label label-success radius">值班</span></td>
				</tr> 
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=path %>/laydate/laydate.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 0, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  {"orderable":false,"aTargets":[1,2,3,4,5,6,7,8,9]}// 不参与排序的列
	]
});

//执行一个laydate实例
laydate.render({
  elem: '#week' //指定元素
  ,value: new Date()
});

</script> 
  </body>
</html>
