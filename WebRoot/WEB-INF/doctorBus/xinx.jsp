<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'xinx.jsp' starting page</title>
    
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
  
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	门诊业务
	<span class="c-gray en">&gt;</span>
	就医诊断
	<span class="c-gray en">&gt;</span>
	诊疗方案
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">

	<form class="form form-horizontal" id="form-article-add">
		<div id="tab-system" class="HuiTab">
		<!-- 	<div class="tabBar cl">
				<span>基本设置</span>
			</div> -->
			<div class="tabCon">
			
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						
						病人姓名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						东方不败
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						诊疗卡号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						78729821
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						
						性别：</label>
					<div class="formControls col-xs-8 col-sm-9">
						雌雄同体
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
						
						出生年月：</label>
					<div class="formControls col-xs-8 col-sm-9">
						1980-02
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>诊断结论：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<!-- <input type="text" class="input-text" value="" placeholder="" id="brief" name="brief"> -->
						<textarea class="textarea" id="brief" name="brief" placeholder="不少于4个字符，不多于200个字符。"></textarea>
					</div>
				</div>

				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>诊疗方式：</label>
					<div class="formControls col-xs-8 col-sm-9 skin-minimal">
						<div class="radio-box">
							<input name="deal" type="radio" id="deal1" value="1" checked >
							<label for="deal1">继续观察</label>
						</div>
						<div class="radio-box">
							<input name="deal" type="radio" id="deal2" value="2" >
							<label for="deal2">开药治疗</label>
						</div>
						<div class="radio-box">
							<input name="deal" type="radio" id="deal3" value="3">
							<label for="deal3">办理住院</label>
						</div>
						<span >
						<button id="btn1" onClick="javascript:alert('开药')" ><i class="Hui-iconfont">&#xe647;</i> 开药</button> &nbsp;
						<button id="btn2" onClick="javascript:alert('药方')" ><i class="Hui-iconfont">&#xe695;</i> 查看药方</button>
						</span>
					</div>
				</div>
			</div>
			
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe615;</i> 提交病历</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button onClick="article_save_submit();" class="btn btn-secondary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button"><i class="Hui-iconfont">&#xe6a6;</i> 取消&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#tab-system").Huitab({
		index:0
	});

	//页面加载时，默认开药、药方按钮禁用
	var btn1 = document.getElementById('btn1'); 
	var btn2 = document.getElementById('btn2'); 
	btn1.setAttribute("class", "btn disabled radius"); 
	btn2.setAttribute("class", "btn disabled radius"); 
		
	//radio选择住院、开药时，修改按钮样式	
  	$(':radio').on('ifChecked', function(event){  
  		if($(this).val()==2||$(this).val()==3){
   			var btn1 = document.getElementById('btn1'); 
			var btn2 = document.getElementById('btn2'); 
			btn1.setAttribute("class", "btn btn-success radius"); 
			btn2.setAttribute("class", "btn btn-success radius"); 
   		}else{
   			var btn1 = document.getElementById('btn1'); 
			var btn2 = document.getElementById('btn2'); 
			btn1.setAttribute("class", "btn disabled radius"); 
			btn2.setAttribute("class", "btn disabled radius"); 
   		}  
	});  
  		
  		
	$("#form-article-add").validate({
		rules:{
		//诊断结果字符长度限制在4到100
			brief:{
				required:true,
				minlength:4,
				maxlength:200
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type: 'post',
				url: "xxxxxxx" ,
				success: function(data){
					layer.msg('添加成功!',{icon:1,time:1000});
				},
                error: function(XmlHttpRequest, textStatus, errorThrown){
					layer.msg('error!',{icon:1,time:1000});
				}
			});
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
