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
     药品管理 <span class="c-gray en">&gt;</span> 药品列表 <a id="refresh" class="btn btn-success radius r" 
     style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
     <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 
		<form action="drug">
		选择价格区间：
		<input type="number" class="input-text" step="0.01" style="width:130px" placeholder="输入价格下限" id="price1" value="${price1 }" name="price1"> --
		<input type="number" class="input-text" step="0.01" style="width:130px" placeholder="输入价格上限" id="price2" value="${price2 }" name="price2">
		<button type="submit" class="btn btn-success radius" onclick="this.form.submit()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		<a href="javascript:;" onclick="price_clear()" class="btn btn-secondary radius"><i class="Hui-iconfont">&#xe68f;</i> 重置</a>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	 <a href="javascript:;" onclick="member_add('药方信息','finddrandpr','800','500')" 
	 class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe665;</i> 查看药方</a></span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
			
				<th width="50">编号</th>
				<th width="80">药品名</th>			
				<th width="80">价格</th>			
				<th width="80">库存数</th>
				<th width="70">药品类别</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			
			
			<c:forEach items="${drlist }" var="dr" varStatus="rows">
				<tr class="text-c">	
				<td>${rows.index+1 }</td>
				<td>${dr.drug.drname}</td>
				<td>${dr.drug.drprice }</td>
				<td>${dr.drug.drsum }</td>
				<td>${dr.drug.drugtype.dyname}</td>
			<%-- 	<td class="td-status">
				<c:choose>
					<c:when test="${dr.drstate eq 1}"><span class="label label-success radius">已启用</span></c:when>
					<c:otherwise><span class="label label-defaunt radius">已停用</span></c:otherwise>
				</c:choose>	
				</td> --%>
				<td class="td-manage">
			
				<%-- <a title="" href="javascript:;" onclick="findprid(${hist.prescripton.prid})" class="ml-5" style="text-decoration:none"> --%>
			<a href="javascript:addDrandPre(${dr.drug.drid},${rid})"  class="btn btn-primary radius"> <!-- <i class="Hui-iconfont">&#xe665;</i> -->使用此药品</a>
			</td>
			
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>

	<div align="center">
	<button onClick="layer_close();" class="btn btn-primary size-L radius" type="button"> 确定&nbsp;</button>
	</div>
	<br>
	<br>
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
		"aaSorting": [[ 0, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[1,2,3]}// 制定列不参与排序
		]
	});
	
});
function price_clear(){
	$("#price1").val("");  
	$("#price2").val(""); 
}

function addDrandPre(drid,rid){
	$.ajax({
			type:'post',
			url:'drandpr',
			data:{"drid":drid,"rid":rid},
 	
			success: function(data){
				if(data=="true"){
				
					layer.msg("添加成功",{icon:6,time:1500,size:50});
				}

			}
	});
}


function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
  </body>
</html>
