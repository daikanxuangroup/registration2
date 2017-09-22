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
    
    <title>My JSP 'chartsDrSal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />

  </head>
  
  <body>
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 药品管理 <span class="c-gray en">&gt;</span> 销售统计 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<div class="text-c"> 
		<form action="">
			<label>选择药品类型：</label>
			<span class="select-box" style="width: 225px;">
				<select class="select" size="1" name="dyid" style="width: 215px;" id="dyid">
					<option value="0" >全部类型</option>
					<c:forEach items="${dylist}" var="dy">
			    		<option value='${dy.dyid}' 
			    		${dy.dyid==dr.dyid?'selected':""}> 
			    		${dy.dyname}</option>
			    	</c:forEach>
				</select>
			</span> 
				<a  class="btn btn-success radius" onclick="statTypes()"><i class="Hui-iconfont">&#xe665;</i> 查询</a>
		</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> &nbsp;</span> </div>
		<!-- <div class="f-14 c-error">特别声明：Highcharts 是一个用纯 JavaScript编写的一个图表库，仅免费提供给个人学习、个人网站，如果在商业项目中使用，请去Highcharts官网网站购买商业授权。或者您也可以选择其他免费的第三方图表插件，例如百度echarts。H-ui.admin不承担任何版权问题。</div> -->
		<div id="container" style="min-width:700px;height:500px"></div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
	
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
	<script type="text/javascript" src="lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
	<script type="text/javascript">
	$(function () {
		   alltypes()
	});
	
	function statTypes(){
		var dyid = document.getElementById("dyid").value;
		if(dyid==0){
			alltypes();
		}else{
			onetypes(dyid);
		}
	}
	function alltypes(){
		$('#container').empty();
		$.ajax({
			url:'tsalStatJson',
			dataType: 'json',
			success: function(data){
				console.log(data);
				var mo = data.month
				var dt = data.dt;
				console.log(mo);
				console.log(dt);
				//画图!!!!!
			  	Highcharts.chart('container', {
			        title: {
			            text: '各类型药品销售量统计',
			            x: -20 //center
			        },
			        subtitle: {
			            text: '数据时段: 半年内',
			            x: -20
			        },
			        xAxis: {
			            categories: mo
			        },
			        yAxis: {
			            title: {
			                text:'销售量 (/份)'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '份'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: dt
			    });
			}
		});
	}
	
	function onetypes(dyid){
		$.ajax({
			url:'dsalStatJson',
			dataType: 'json',
			data:{"dyid":dyid},
			success: function(data){
				console.log(data);
				var mo = data.month
				var dt = data.dt;
				console.log(mo);
				console.log(dt);
				$('#container').empty();
				//画图!!!!!
			  	Highcharts.chart('container', {
			        title: {
			            text: '该类型下各药品销售统计',
			            x: -20 //center
			        },
			        subtitle: {
			            text: '数据时段: 半年内',
			            x: -20
			        },
			        xAxis: {
			            categories: mo
			        },
			        yAxis: {
			            title: {
			                text:'销售量 (/份)'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: '份'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: dt
			    });
			}
		});
	}
	</script>
  </body>
</html>
