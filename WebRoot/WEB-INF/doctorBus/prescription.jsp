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
    
    <title>My JSP 'prescription.jsp' starting page</title>
    
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
  <form action="" method="post">		  
<div class="page-container">

	  
    <div class="cl pd-5 bg-1 bk-gray mt-20"> 
		 <span class="l">&nbsp; &nbsp;病人姓名：<strong style="color: blue;" > ${pname } </strong></span> 
 		 <span class="r">此药方合计：<strong style="color: red;" ><span id="zongja"> ${sum}</span></strong> 元&nbsp; &nbsp;</span> </div>

	<div class="mt-20"> 
	
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
<!-- 				<th width="25"><input type="checkbox" name="" value=""></th> -->
				<th width="40">序号</th>
				<th width="80">药品名字</th>							
				<th width="100">药品价格</th>
				<th width="90">药品数量</th>
				<th width="100">小计价格</th>
				<th width="80">操作</th>

			</tr>
		</thead>
		<tbody>
		<c:forEach items="${map}" var="maps" varStatus="rows">
			<tr class="text-c">
	
				<td>${rows.index+1 }</td>
				<td> ${maps.value.drug.drname} </td>
				<td > ${maps.value.drug.drprice }			
				 <input name="price" type="hidden" value="${maps.value.drug.drprice }">
				 </td>
				<td>
<%-- 				<a onclick="addsum(${rows.index+1 })"><i class="Hui-iconfont Hui-iconfont-add"></i></a>&nbsp; --%>
			<input onblur="pxp(${maps.value.drug.drid},${maps.value.drug.drprice },${maps.value.drnum })" type="number" class="input-text" step="1" 
			 name="sun" min="1" id="${maps.value.drug.drid}" value="${maps.value.drnum }" style="width: 60px" >	
			<%-- 	&nbsp;<a onclick="subsum(${rows.index+1 })"><i class="Hui-iconfont">&#xe6a1;</i></a> --%>	
				</td>
				<td><span id="xiao${rows.index+1 }"> ${maps.value.sum }</span></td>
				<td>
				<a href="" class="btn btn-primary radius"> 移除此药品</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>	
	</table>
		
	
	</div>

</div>
<div><p> &nbsp;</p></div>
<div align="center"><button type="submit"  class="btn btn-primary size-L radius "> 保存</button></div>
</form>	

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
		"aaSorting": [[ 0, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[1]}// 制定列不参与排序
		]
	});
	
});
/*查看药方*/
function findprid(title,url,id,w,h){

	layer_show(title,url,w,h);
}

function pxp(xid,price,sum){
	 var sums = document.getElementById(xid).value; 
	if(sums==0){
	layer.alert("最小值为 1");
	}
	var chajia=(sums-sum)*price;
	
	document.getElementById("xiao"+xid).value;
	document.getElementById("xiao"+xid).innerHTML=(sums*price);
	var zhongjia = document.getElementById("zongja").innerHTML;
	var sum1= parseFloat(zhongjia)+chajia;
	document.getElementById("zongja").innerHTML=sum1;

}


/* 
function addsum(id){
	alert("1")
	var sum = document.getElementById("id").value+1;
	 document.getElementById("id").value=sum;
	 
}
function subsum(id){
	if(id==1){	
		layer.confirm('是否移除药品',function(index){
			
		});
	}else{
		var sum = document.getElementById("id").value-1;
		 document.getElementById("id").value=sum;
	}
}
 */
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
				$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
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
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

</script>   	
  </body>
</html>
