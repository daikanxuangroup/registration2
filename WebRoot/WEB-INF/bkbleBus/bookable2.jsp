<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 排班管理 <span class="c-gray en">&gt;</span> 医生坐诊排班 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form action="findBK">
	<div class="text-c">
		选择科室：
	 <span class="select-box inline">
		<select name="deid" class="select" id="deid">
			<c:forEach items="${delist}" var="de">
			    	<option value='${de.deid}' ${de.deid==deid?'selected':'' }> 
			    		${de.dename}
			    	</option>
			</c:forEach>
		</select>
		</span> &nbsp;&nbsp;&nbsp;&nbsp;选择排班周：
		<input type="text" id="datetime" class="input-text"  style="width:120px" name="datetime" value="${datetime }">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询排班</button>
		<a class="btn btn-primary radius" data-title="自动排班" data-href="javascript:autoBK();"  href="javascript:autoBK();">
		<i class="Hui-iconfont">&#xe600;</i> 自动排班2</a>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:delBK();" class="btn btn-danger radius">
	<i class="Hui-iconfont">&#xe6e2;</i> 删除该周排班</a> </span>  </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="40">ID</th>
					<th width="60">医生姓名</th>
					<th width="40">时段</th>
					<c:forEach items="${wklist}" var="wk">
						<th width="80">${wk }</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bklist}" var="bk">
				<tr class="text-c">
					<td rowspan=2>${bk.doid}</td>
					<td rowspan=2>${bk.doname}</td>
					<td>上午</td>
					<td class="td-status"><span class="${bk.areg1 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg1 }</span></td>
					<td class="td-status"><span class="${bk.areg2 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg2 }</span></td>
					<td class="td-status"><span class="${bk.areg3 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg3 }</span></td>
					<td class="td-status"><span class="${bk.areg4 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg4 }</span></td>
					<td class="td-status"><span class="${bk.areg5 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg5 }</span></td>
					<td class="td-status"><span class="${bk.areg6 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg6 }</span></td>
					<td class="td-status"><span class="${bk.areg7 == '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.areg7 }</span></td>
				</tr>
				<tr class="text-c">
					<td hidden>${bk.doid}</td>
					<td hidden>${bk.doname}</td>
					<td>下午</td>
					<td class="td-status"><span class="${bk.preg1 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg1 }</span></td>
					<td class="td-status"><span class="${bk.preg2 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg2 }</span></td>
					<td class="td-status"><span class="${bk.preg3 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg3 }</span></td>
					<td class="td-status"><span class="${bk.preg4 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg4 }</span></td>
					<td class="td-status"><span class="${bk.preg5 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg5 }</span></td>
					<td class="td-status"><span class="${bk.preg6 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg6 }</span></td>
					<td class="td-status"><span class="${bk.preg7 eq '可预约'?'label label-warning radius':'label label-defaunt radius'}">${bk.preg7 }</span></td>
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
<script type="text/javascript" src="<%=path %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=path %>/laydate/laydate.js"></script>
<script type="text/javascript">

$(function(){
	//执行一个laydate实例
	laydate.render({
	  elem: '#datetime' //指定元素
	});
	
	$('.table-sort').dataTable({
		"aaSorting": [[ 0, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"pading":false,
		"aoColumnDefs": [
		  {"orderable":false,"aTargets":[1,2,3,4,5,6,7,8,9]}// 不参与排序的列
		]
	});

});

//自动排班
function autoBK(){
	var deid = $("#deid").val();
	var datetime = $("#datetime").val();
	$.ajax({
		url:'findBK2',
		data:{"deid":deid,"datetime":datetime},
		dataType: 'json',
		success: function(data){
			if(data.result=="yes"){
				layer.alert('该科室所选周已排班！', {
    				skin: 'layui-layer-lan'
    				,closeBtn: 0
    				,anim: 4 //动画类型
  				},function(){
  					location.href="findBK?deid="+deid+"&datetime="+datetime;
  				});
			}
			else{
				layer.alert('排班完毕！', {
    				skin: 'layui-layer-lan'
    				,closeBtn: 0
    				,anim: 4 //动画类型
  				},function(){
  					location.href="addBK?deid="+deid+"&datetime="+datetime;
  				});
  					
			}
		}
	});
}

//删除
function delBK(){
	var doid = $("table tr:eq(1) > td:eq(0)").text();//第二行第一列 --医生id
	var datetime = $("table tr:eq(0) > th:eq(3)").text().split(' ')[0]; //排班时间
	/* alert(doid); */
	if(doid!=''&&doid!="没有数据"){
		layer.confirm('是否确定要删除？',
		function(){
		layer.closeAll('dialog');
		$.ajax({
		url:'delBK',
		data:{"doid":doid,"datetime":datetime},
		success:function(data){
			if(data.result=='before'){
				layer.alert('已上线排班无法删除！', {
  					skin: 'layui-layer-molv' 
  					,closeBtn: 0
  					,anim: 1 });
			}else if(data.result=="after"){
				var deid = data.deid;
				layer.alert('删除成功！', {
  					skin: 'layui-layer-molv' 
  					,closeBtn: 0
  					,anim: 1 },function(){
  					location.href="findBK?deid="+deid+"&datetime="+datetime;
  				});
			}else{
				var deid = data.deid;
				layer.alert('此周排班表中存在已上线信息，无法删除。未上线部分已更新！', {
  					skin: 'layui-layer-molv' 
  					,closeBtn: 0
  					,anim: 1 },function(){
  					location.href="findBK?deid="+deid+"&datetime="+datetime;
	  				});
				}
			}
			});
	
		});
	}else{
		layer.msg('没有排班信息，无法删除！', {icon: 5,time:1200});
	}
	
	
}
</script> 
  </body>
</html>
