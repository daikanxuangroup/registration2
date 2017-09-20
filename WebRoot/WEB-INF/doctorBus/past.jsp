<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'past.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/css/style.css" />
	
  </head>
  
  <body>
   	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 门诊业务 <span class="c-gray en">&gt;</span> 就医诊断 <span class="c-gray en">&gt;</span> 病人病历<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	<h3 id="clock"></h3>
	 日期范围：	<input type="text" id="date" class="input-text"  style="width:240px" name="date" value="${date }">
	
		<a  class="btn btn-success radius" href="javascript:dates(${cid })"><i class="Hui-iconfont">&#xe665;</i> 搜索</a>
	
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
<span class="l">

</span> <span class="r"><strong></strong> </span>
</div> 
	<div class="mt-20"> 
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
<!-- 				<th width="25"><input type="checkbox" name="" value=""></th> -->
				<th width="40">主治医生</th>
				<th width="80">病人姓名</th>							
				<th width="40">病人性别</th>
				<th width="90">医生职位</th>
				<th width="80">病历状况</th>
				<th width="170">处理翻案</th>
				<th width="100">就医时间</th>		
				<th width="100">查看药方</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${hist}" var="hist">
			<tr class="text-c">
		<!-- 		<td><input type="checkbox" value="1" name=""></td> -->
				<td>${hist.doctors.doname}</td>
				<td>
				<%-- <u style="cursor:pointer" class="text-primary" onclick="cha()">${reg.cards.pname}</u> --%>
				${hist.cards.pname}
				</td>
				<td>${hist.cards.sex}</td>
				<td>${hist.doctors.title}</td>
				<td> 
				<c:if test="${hist.deal eq 1}">回家观察</c:if>
				<c:if test="${hist.deal eq 2}">开药观察</c:if>
				<c:if test="${hist.deal eq 3}">住院观察</c:if>
				</td>
				<td>
					${hist.brief } 
				
				</td>
				<td class="td-status">
				<!-- <span class="label label-success radius">未整疗</span> -->
				<fmt:formatDate value="${hist.hidate }" pattern="yyyy-MM-dd"/>
				</td>
					
				<td class="td-manage">
				<c:if test="${hist.prescripton.prid != null}">		
				
			<a href="javascript:findprid('${hist.cards.pname}-药方信息','findprid?prid=${hist.prescripton.prid}',1,800,500)"  class="btn btn-primary radius"> <i class="Hui-iconfont">&#xe665;</i>查看药方</a>
				</c:if>
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
<script type="text/javascript" src="<%=path %>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path %>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="<%=path %>/laydate/laydate.js"></script>
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0]}// 制定列不参与排序
		]
	});
	
	laydate.render({
		elem:'#date',
		range:true
	});	
});
/*查看药方*/
function findprid(title,url,id,w,h){

	layer_show(title,url,w,h);
}
function dates(cid){

	var date = $("#date").val();
	window.location.href="past?cid="+cid+"&date="+date;
}





</script>   	
  </body>
</html>
