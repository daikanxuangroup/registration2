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
    
    <title>药品列表</title>
    
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
		<form action="findDrug">
		选择价格区间：
		<input type="number" class="input-text" min="0" step="0.01" style="width:130px" placeholder="输入价格下限" id="price1" value="${price1 }" name="price1"> --
		<input type="number" class="input-text" min="0.01" step="0.01" style="width:130px" placeholder="输入价格上限" id="price2" value="${price2 }" name="price2">
		<button type="submit" class="btn btn-success radius" onclick="this.form.submit()"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		<a href="javascript:;" onclick="price_clear()" class="btn btn-secondary radius"><i class="Hui-iconfont">&#xe68f;</i> 重置</a>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" onclick="member_add('添加药品','editDrug?drid=0','380','580')" class="btn btn-primary radius">
		<i class="Hui-iconfont">&#xe600;</i> 添加药品</a>
	&nbsp;
	<a href="javascript:;" onclick="drs_start()" class="btn btn-success radius"><i class="Hui-iconfont">&#xe676;</i> 批量启用</a>  
 	<a href="javascript:;" onclick="drs_stop()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量停用</a>  </span>
 	<c:set var="num" value="0"></c:set>
 	<c:forEach items="${drlist }" var="dr">
 		<c:if test="${dr.drsum lt 100 }"><c:set var="num" value="${num+1 }"/></c:if>
 	</c:forEach>
 	<c:if test="${num gt 0}"><span class="r" style="color:red;font-size:16px">共有<strong>${num}</strong>种药品库存数不足100！</span></c:if>
 	 </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="30" id="allcheck">全选&nbsp;<input type="checkbox" name="" value="" ></th>
				<th width="50">编号</th>
				<th width="80">药品名</th>
				<th width="70">药品类别</th>
				<th width="60">价格</th>
				<th width="200">可用科室</th>
				<th width="80">库存数</th>
				<th width="70">状态</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody id="tbr">
			
			<c:forEach items="${drlist }" var="dr">
				<tr class="text-c" id="dr${dr.drid }">
				<td><input type="checkbox" value="${dr.drid }" name="drid"></td>
				<td>${dr.drid }</td>
				<td>${dr.drname}</td>
				<td>${dr.drugtype.dyname }</td>
				<td>${dr.drprice}</td>
				<td class="text-l">${dr.dename }</td>
				<td ${dr.drsum lt 100?'style="color:red;font-weight:bold;"':''}>${dr.drsum}<%-- ${dr.drsum lt 100?'(库存不足100)':''} --%></td>
				<td class="td-status">
				<c:choose>
					<c:when test="${dr.drstate eq 1}"><span class="label label-success radius">已启用</span></c:when>
					<c:otherwise><span class="label label-defaunt radius">已停用</span></c:otherwise>
				</c:choose>
				
				</td>
				<td class="td-manage">
					<c:choose>
						<c:when test="${dr.drstate eq 1}">
						<a style="text-decoration:none" onClick="drug_stop(this,${dr.drid })" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
						</c:when>
						<c:otherwise>
						<a style="text-decoration:none" onClick="drug_start(this,${dr.drid })" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe66b;</i></a>
						</c:otherwise>
					</c:choose>
					<a title="编辑" href="javascript:;" onclick="member_edit('编辑','editDrug?drid='+${dr.drid },'380','580')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>  
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
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.15/js/jquery.dataTables.min.js"></script>  
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.15/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.15/js/jszip.min.js"></script>   
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.15/js/buttons.html5.min.js "></script> 
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		dom: 'lfrtBip',
         "buttons": [  
                      {  
                        'extend': 'excelHtml5',  
                        'className': 'btn btn-secondary radius', //按钮的class样式
                        'text': '<i class="Hui-iconfont">&#xe640;</i>导出到excel',//定义导出excel按钮的文字  
                        'exportOptions':{ //从DataTable中选择要收集的数据。这包括列、行、排序和搜索的选项。请参阅button.exportdata()方法以获得完整的详细信息——该参数所提供的对象将直接传递到该操作中，以收集所需的数据，更多options选项参见：https://datatables.net/reference/api/buttons.exportData()
				           	'columns': [1,2,3,4,5,6,7]  
				         }
	                   }  
	               ],      
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,8]}// 制定列不参与排序
		]
	});
	
});
function price_clear(){
	$("#price1").val("");  
	$("#price2").val(""); 
}
/*药品-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
//批量停用
function drs_stop(){
	var obj = document.getElementsByName("drid");
    var check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    layer.confirm('确认要批量停用吗？',function(index){
    	if(check_val.length<1){
	    	layer.tips('  请至少选中一行进行批量操作！  ', '#allcheck', {
			  tips: [2, '#E65']
			});
	    }else{	
	    	$.each(check_val, function (index, id){
	    		$.ajax({
	    			type: 'POST',
					url: 'drugState',
					data:{drid:id,drstate:0},
					dataType: 'json',
					async: false, //将ajax改为同步
					success: function(data){
						var dy = $("#dr"+id);
						if(data.result=="ok"){
							$(dy).find(".td-manage").find("a:first").remove();
							$(dy).find(".td-manage").prepend('<a style="text-decoration:none" onClick="drug_start(this,'+id+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe66b;</i></a> ');
							$(dy).find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
						}
					},
	    		});
	    	});
	    	layer.msg('批量停用完成!',{icon: 4,time:1200});
	    }
    });
}
/*药品-停用*/
function drug_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'drugState',
			data:{drid:id,drstate:0},
			dataType: 'json',
			success: function(data){
				if(data.result=="ok"){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="drug_start(this,'+id+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe66b;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
					$(obj).remove();
					layer.msg('已停用!',{icon: 4,time:1000});
				}else{
					layer.msg('出错了!',{icon: 2,time:1000});
				}
				
			},
			error:function(data) {
				alert(data.msg);
				console.log(data.msg);
			},
		});		
		layer.close(index);
	});
}
//批量启用
function drs_start(){
	var obj = document.getElementsByName("drid");
	var check = [];
    for(k in obj){
        if(obj[k].checked)
            check.push(obj[k].value);
    }
    layer.confirm('确认要批量启用吗？',function(index){
		if(check.length<1){
	    	layer.tips('  请至少选中一行进行批量操作！  ', '#allcheck', {
			  tips: [2, '#E65']
			});
	    }else{
	    	var i = 0;	
	    	$.each(check, function (index, id){
	    		$.ajax({
	    			type: 'POST',
					url: 'drugState',
					data:{drid:id,drstate:1},
					dataType: 'json',
					async: false, //将ajax改为同步
					success: function(data){
						var dy = $("#dr"+id);
						if(data.result=="ok"){
							$(dy).find(".td-manage").find("a:first").remove();
							$(dy).find(".td-manage").prepend('<a style="text-decoration:none" onClick="drug_stop(this,'+id+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
							$(dy).find(".td-status").html('<span class="label label-success radius">已启用</span>');
						}else if(data.result=="stop"){
							i++;
						}
					},
	    		});
	    	});
	    	if(i==0)
	    		layer.msg('批量启用完成!',{icon: 6,time:1200});
	    	else
	    		layer.msg('类型被停用的药品暂未启用！',{icon: 7,time:1700});
	    }
	});
}

/*药品-启用*/
function drug_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'drugState',
			data:{drid:id,drstate:1},
			dataType: 'json',
			success: function(data){
				if(data.result=="ok"){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="drug_stop(this,'+id+')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
					$(obj).remove();
					layer.msg('已启用!',{icon: 6,time:1000});
				}else if(data.result=="stop"){
					layer.msg('该药品类的型处于停用状态，无法启用!',{icon: 2,time:1500});
				}
				
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}
/*药品-编辑*/
function member_edit(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 
  </body>
</html>
