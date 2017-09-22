<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		 <span class="l">&nbsp; &nbsp;病人姓名：<strong style="color: blue;" > ${cards.pname } </strong></span> 
		 &nbsp;&nbsp;&nbsp;
		 <span class="1">诊疗卡余额：<strong style="color: red;" ><span id="zongja">${cards.ramaining } </span></strong> 元</span>
 		 <span class="r">此药方合计：<strong style="color: red;" ><span id="zongja">
 		 <fmt:formatNumber type="number"  value="${sum}" pattern="0.0" maxFractionDigits="1"/> 
 		 </span></strong> 元&nbsp; &nbsp;</span> </div>

	<div class="mt-20"> 
<!-- 	-->
	<table class="table table-border table-bordered table-hover table-bg  table-sort">
		<thead>
			<tr class="text-c">
<!-- 				<th width="25"><input type="checkbox" name="" value=""></th> -->
				<th width="40">序号</th>
				<th width="80">药品名字</th>							
				<th width="100">药品价格</th>
				<th width="90">药品数量</th>
				<th width="100">小计价格</th>				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${map}" var="maps" varStatus="rows">
			<tr class="text-c" >
				<td>${rows.index+1 }</td>
				<td> ${maps.value.drug.drname} </td>
				<td > ${maps.value.drug.drprice }			
				<td>${maps.value.drnum }</td>			
				<td>
 			<fmt:formatNumber type="number"  value="${maps.value.sum }" pattern="0.0" maxFractionDigits="1"/> 				
				</td>
			</tr>
			</c:forEach>
		</tbody>	
	</table>
		
	
	</div>

</div>
<div><p> &nbsp;</p></div>
<div align="center">
<button onClick="charge_add(${cards.cid });" class="btn btn-primary size-L radius" type="button"> 诊疗卡充值&nbsp;</button>&nbsp&nbsp&nbsp&nbsp
<button onClick="charge(${prid},${cards.cid },${cards.ramaining },${sum });" class="btn btn-primary size-L radius" type="button"> 确认收费&nbsp;</button>&nbsp&nbsp&nbsp&nbsp
<button onClick="layer_close();" class="btn btn-warning size-L radius" type="button"> 返回</button>
<!-- <button type="submit"  class="btn btn-primary size-L radius "> 保存</button> --></div>
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
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
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

function charge(prid,cid,ramaining,sum){
	
 	if(ramaining<sum){
	layer.alert("余额不足请提醒充值");
	}else{
			$.ajax({
			type:'post',
			url:'charge',
			data:{"prid":prid,"cid":cid,"price":sum},
			success: function(data){		
				if(data=="true"){	
						layer.msg('收费成功~请发药！',{icon:1,time:1500});
			 			setTimeout(function () { 
						var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index); 
				        parent.location.reload(); 
				    
				    }, 2000);				
				}

			}
		}); 
	}
}

function charge_add(cid){
	
	layer.prompt({title: '卡号:'+cid},function(val, index){
		
	if(val>0){
		$.ajax({
			type: 'POST',
			url: 'addrecharge',
			data:{"cid":cid,"price":val},
		
			success: function(data){
			/* 	console.log(data); */
				if(data!="ok"){
					layer.msg('充值失败！');
	  				layer.close(index);
				}else{
			
					layer.msg('充值成功');
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
	
	}else{
	
		layer.msg('充值失败！不能为负数...');
		}
	
	
 		
	});


}




</script>   	
  </body>
</html>
