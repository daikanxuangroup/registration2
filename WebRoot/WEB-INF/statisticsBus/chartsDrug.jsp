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
    
    <title>My JSP 'chartsDrug.jsp' starting page</title>
    
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
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 统计管理 <span class="c-gray en">&gt;</span> 饼状图 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    
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
		alltypes();
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
		/* $.ajaxSetup({  
		    async : false  
		});  */
		$('#container').empty();
		$.ajax({
			url:'dtStatJson',
			dataType: 'json',
			success: function(data){
				console.log(data);
				var at = data.atlist
				var sum = data.sum;
				console.log(at);
				console.log(sum);
				//画图!!!!!
			  	$('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: '各类型药品占药品总数的比例统计'
			        },
			        subtitle: {
			            text: '药品总数:'+sum
			        },
			        tooltip: {
			    	    formatter: function() {
					      return '<b>'+ this.point.name +'</b> 数量： '+ Highcharts.numberFormat(this.y, 0, ',') ;
					     }
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    color: '#000000',
			                    connectorColor: '#000000',
			                    formatter: function() {
						         return '<b>'+ this.point.name +'</b>: ' + Highcharts.numberFormat(this.percentage, 1) +'% ';
						        }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: 'Browser share',
			            data: at
			           /*  [
			                ['注射用剂',   25],
			                ['抗生素类',  37],
			                ['中成药',  21],
			                ['医疗器具',   12],
			                ['其他',   7]
			            ] */
			        }]
			    });
			}
		});

	}
	
	function onetypes(dyid){
		$.get( 'druStatJson' ,{"dyid":dyid}, function(data){
			console.log(data);
			var ta = data.talist
			var sum = data.sum;
			console.log(ta+"  "+sum);
			$('#container').empty();
			
			$('#container').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: '该类型下各药品占比统计'
	        },
	        subtitle: {
	            text: '药品总数:'+sum
	        },
	        tooltip: {
	    	    formatter: function() {
			      return '<b>'+ this.point.name +'</b> 数量： '+ Highcharts.numberFormat(this.y, 0, ',') ;
			     }
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#000000',
	                    connectorColor: '#000000',
	                    formatter: function() {
				         return '<b>'+ this.point.name +'</b>: ' + Highcharts.numberFormat(this.percentage, 1) +'% ';
				        }
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'Browser share',
	            data: ta
	        }]
	    });
		},"json");	
		
	}
	</script>
  </body>
</html>
