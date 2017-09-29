<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path %>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>我的桌面</title>
</head>
<body>
<div class="page-container">
	<p class="f-20 text-success">欢迎来到301医院门诊部后台管理系统 <span class="f-14"></span></p>
	<p>上次登录时间：<fmt:formatDate value="${adm.times }" type="both"/></p>
	<table class="table table-border table-bordered table-bg table-hover ">
		<thead>
			<tr>
				<th colspan="7" scope="col">信息统计</th>
			</tr>
			<tr class="text-c">
				<th>门诊挂号人数统计</th>
			<c:forEach items="${times}" var="time">
				<th>${time}</th>
			</c:forEach>	
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${map}" var="maps">
				<tr class="text-c">
					<td width="50px">${maps.key}</td>
			<c:forEach items="${maps.value }" var="count">
			<td width="30px">${count }</td>
			</c:forEach>
				</tr> 
				</c:forEach>
		</tbody>
	</table>
	
	<div id="container" style="min-width:700px;height:400px"></div>
</div>
<footer class="footer mt-20">
	<div class="container">
		<p>医院管理系统<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架"></a></p>
	</div>
</footer>
<script type="text/javascript" src="<%=path %>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=path %>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=path %>/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=path %>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="https://img.hcharts.cn/highcharts/highcharts.js"></script> 
<script type="text/javascript" src="https://img.hcharts.cn/highcharts/highcharts.js"></script> 
<script type="text/javascript" src="https://img.hcharts.cn/highcharts/modules/drilldown.js"></script> 
<script type="text/javascript" src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script> 

<script type="text/javascript">


$(function () {
	
	
	
    // Create the chart
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: '本季度各科室病人挂号比例'
        },
        subtitle: {
            text: '详细信息'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '总挂号人数'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.f}人'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.f}人</b> of total<br/>'
        },
        series: [{
            name: '科室名称',
            colorByPoint: true,
            data: [
            
            {
            
                name: '外科',
                y: 56,
                drilldown: '外科'
            }, {
                name: '内科',
                y: 24,
                drilldown: '内科'
            }, {
                name: '骨科',
                y: 10,
                drilldown: '骨科'
            }, {
                name: '妇科',
                y: 47,
                drilldown: '妇科'
            }, {
                name: '脑科',
                y: 91,
                drilldown: '脑科'
            }, {
                name: '口腔科',
                y: 7,
                drilldown: '口腔科'
            }]
        }],
        drilldown: {
            series: [{
                name: '外科',
                id: '外科',
                data: [
                    [
                        '本季度',
                        56
                    ],
                    [
                        '本月',
                        20
                    ],
                    [
                        '本周',
                        10
                    ],
                    [
                        '昨天',
                        5
                    ],
                    [
                        '今天',
                        1
                    ]
                ]
            }, {
                name: '内科',
                id: '内科',
                data: [
                    [
                        '本季度',
                        56
                    ],
                    [
                        '本月',
                        20
                    ],
                    [
                        '本周',
                        10
                    ],
                    [
                        '昨天',
                        5
                    ],
                    [
                        '今天',
                        1
                    ]
                ]
            }, {
                name: '骨科',
                id: '骨科',
                data: [
                    [
                        'v35',
                        2.76
                    ],
                    [
                        'v36',
                        2.32
                    ],
                    [
                        'v37',
                        2.31
                    ],
                    [
                        'v34',
                        1.27
                    ],
                    [
                        'v38',
                        1.02
                    ],
                    [
                        'v31',
                        0.33
                    ],
                    [
                        'v33',
                        0.22
                    ],
                    [
                        'v32',
                        0.15
                    ]
                ]
            }, {
                name: 'Safari',
                id: 'Safari',
                data: [
                    [
                        'v8.0',
                        2.56
                    ],
                    [
                        'v7.1',
                        0.77
                    ],
                    [
                        'v5.1',
                        0.42
                    ],
                    [
                        'v5.0',
                        0.3
                    ],
                    [
                        'v6.1',
                        0.29
                    ],
                    [
                        'v7.0',
                        0.26
                    ],
                    [
                        'v6.2',
                        0.17
                    ]
                ]
            }, {
                name: 'Opera',
                id: 'Opera',
                data: [
                    [
                        'v12.x',
                        0.34
                    ],
                    [
                        'v28',
                        0.24
                    ],
                    [
                        'v27',
                        0.17
                    ],
                    [
                        'v29',
                        0.16
                    ]
                ]
            }]
        }
    });
    
    $('.table-hover').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"pading":false,
		"bLengthChange": false,//是否显示每页大小的下拉框
		"aLengthMenu": [[5], [5]],
		"aoColumnDefs": [
		  {"orderable":false,"aTargets":[0]}// 不参与排序的列
		]
	});
});
</script>


<!--/此乃百度统计代码，请自行删除-->
</body>
</html>