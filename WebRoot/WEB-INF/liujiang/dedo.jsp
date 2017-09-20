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
    
    <title>My JSP 'drug-list.jsp' starting page</title>
    
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
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
     科室管理 <span class="c-gray en">&gt;</span> 科室列表 <a id="refresh" class="btn btn-success radius r" 
     style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
     <i class="Hui-iconfont">&#xe68f;</i></a></nav>
     
     <div class="page-container">
     	<div class="text-c">
     	</div>
     	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" 
     	onclick="member_add('添加科室','editAdd?deid=0','380','580')" class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe600;</i> 添加科室</a></span> </div>
		<div class="mt-20">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="50">编号</th>
					<th width="80">科室名</th>
					<th width="">科室简介</th>
					<th width="70">状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${departs }" var="d">
					<tr class="text-c">
					<td><input type="checkbox" value="1" name=""></td>
					<td>${d.deid }</td>
					<td>${d.dename }</td>
					<td class="text-l">${d.intro }</td>
					<td class="td-status">
					<c:choose>
						<c:when test="${d.deexist eq 1}"><span class="label label-success radius">已启用</span></c:when>
						<c:otherwise><span class="label label-defaunt radius">已停用</span></c:otherwise>
						</c:choose>
				
					</td>
					<td class="td-manage">
					<c:choose>
						<c:when test="${d.deexist eq 1}">
						<a style="text-decoration:none" onClick="drug_stop(this,${d.deid })" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
						</c:when>
						<c:otherwise>
						<a style="text-decoration:none" onClick="drug_start(this,${d.deid })" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe66b;</i></a>
						</c:otherwise>
					</c:choose>
					<a title="编辑" href="javascript:;" onclick="member_edit('编辑','editAdd?deid='+${d.deid },'380','580')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>  
				</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
     </div>
   </div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path %>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path %>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,4]}// 制定列不参与排序
		]
	});
	
});
/*科室-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}

/*科室-停用*/
function drug_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'departsState',
			data:{deid:id,deexist:0},
			dataType: 'json',
			success: function(data){
				/* alert(data) */
				if(data.result=="ok"){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="drug_start(this,'+id+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe66b;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
					$(obj).remove();
					layer.msg('已停用!',{icon: 5,time:1000});
				}else{
					layer.msg('该科室下有医生不能停用!',{icon: 4,time:3000});
				}
				
			},
			error:function(data) {
				// alert(data.msg);
				console.log(data.msg);
			},
		});		
		layer.close(index);
	});
}

/*科室-启用*/
function drug_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'departsState1',
			data:{deid:id,deexist:1},
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="drug_stop(this,'+id+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*科室-编辑*/
function member_edit(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 
</body>
</html>