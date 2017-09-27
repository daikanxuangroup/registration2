<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" 
	href="<%=path %>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css"
	 href="<%=path %>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css"
	 href="<%=path %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css"
	 href="<%=path %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" 
	href="<%=path %>/static/h-ui.admin/css/style.css" />
	
	<script type="text/javascript"> 
function realSysTime(clock){ 
var now=new Date(); //创建Date对象 
var year=now.getFullYear(); //获取年份 
var month=now.getMonth(); //获取月份 
var date=now.getDate(); //获取日期 
var day=now.getDay(); //获取星期 
var hour=now.getHours(); //获取小时 
var minu=now.getMinutes(); //获取分钟 
var sec=now.getSeconds(); //获取秒钟 
month=month+1; 
var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 
var week=arr_week[day]; //获取中文的星期 
var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间 
clock.innerHTML=time; //显示系统时间 
} 



</script> 
	
	
  </head> 
  <body>
  <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 门诊业务 <span class="c-gray en">&gt;</span> 就医诊断 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
<!-- 	<h3 id="clock"></h3> -->
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" 
	onclick="member_add('诊疗卡充值','recharge','400','300')" class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe600;</i>诊疗卡充值</a></span> 
		<span class="r" >提示：<span style="color:blue;font-size:17px">共有&nbsp;<strong>${fn:length(list)}</strong>&nbsp;位患者等候取药划价</span></span>
	</div>
	<div class="mt-20"> 
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
<!-- 				<th width="25"><input type="checkbox" name="" value=""></th> -->
				<th width="40">序列</th>
				<th width="90">药方编号</th>
				<th width="70">诊疗卡号</th>
				<th width="100">病人姓名</th>
				<th width="40">性别</th>
				<th width="130">联系电话</th>				
				<th width="90">开药医生</th>								
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list }" var="list" varStatus="rows">
			<tr class="text-c" >
		 		<td>${rows.index+1 }</td>	
				<td>${list.prid }</td>
				<td>${list.cards.cid }</td>
				<td>${list.cards.pname }</td>
				<td>${list.cards.sex }</td>
				<td>${list.cards.phone }</td>
				<td>${list.doctors.doname }</td>				
				<td><a href="javascript:findprid('${list.cards.pname }-药方信息','presXin?prid=${list.prid }&cid=${list.cards.cid }',1,800,500)"  class="btn btn-primary radius">
				 <i class="Hui-iconfont">&#xe665;</i>查看药方</a></td>	 	
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
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 0, "asc" ]],//默认第几个排序
		"bStateSave": false,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,1,2,3,4,5,6,7]}// 制定列不参与排序
		]
	});
	window.setInterval("realSysTime(clock)",100);
});

function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


/*查看药方*/
function findprid(title,url,id,w,h){

	layer_show(title,url,w,h);
}


</script>   	
	
  </body>
</html>
