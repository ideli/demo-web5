<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>XXX</title>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/resources/css/font-awesome.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/resources/bootstrap/css/morris-0.4.3.min.css">
<script
	src="${pageContext.request.contextPath }/static/resources/js/jquery-2.1.3.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/bootstrap-paginator.js"></script>
<script
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/resources/css/theme.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/resources/bootstrap/css/bootstrap-switch.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/bootstrap-switch.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/static/resources/bootstrap/css/highlight.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/highlight.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/main.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/raphael-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/morris-0.4.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/bootstrap-treeview.js"></script>


</head>
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>

<script type="text/javascript">
	function itemClick(item){
		$(".content").load("${pageContext.request.contextPath }/"+item);
	}
	
	$(function () {
		getMenu();
	});
	
	function getMenu(){
		$.ajax( {  
			url:'menu.json',// 跳转到 action  
			type:'get',  
			dataType:'json',  
			success:function(data) {
				var menus = new Array();
				for (var i = 0; i < data.nodes.length; i++) {
					var menu = {};
					menu.text = data.nodes[i].name;
					menu.url = data.nodes[i].url;
					if(data.nodes[i].nodes=="" || data.nodes[i].nodes==null){
						menus.push(menu);
						continue;
					}
					menu.nodes = new Array();
					if(data.nodes[i].hasOwnProperty("nodes")){
						for (var j = 0; j < data.nodes[i].nodes.length; j++) {
							var item = {};
							item.text = data.nodes[i].nodes[j].name;
							item.url = data.nodes[i].nodes[j].url;
							menu.nodes.push(item);
						}
					}
					menus.push(menu);
					alert(JSON.stringify(menus))
				}
				$('#tree').treeview({data: menus});
				
				$('#tree').on('nodeSelected', function(event, data) {
					if(data.url!=""){
						itemClick(data.url);
					}
				});
			},  
			error : function() {  
			}
		});
	}
</script>
<body class=" theme-blue">
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="" href="index.html"><span style="font-weight: bold; " class="navbar-brand"><span
					class="fa fa-paper-plane"></span> XXX</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user padding-right-small"
						style="position: relative; top: 3px;"></span> Jack Smith <i
						class="fa fa-caret-down"></i>
				</a>

					<ul class="dropdown-menu">
						<li><a href="./">My Account</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Admin Panel</li>
						<li><a href="./">Users</a></li>
						<li><a href="./">Security</a></li>
						<li><a tabindex="-1" href="./">Payments</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="sign-in.html">Logout</a></li>
					</ul></li>
			</ul>

		</div>
	</div>
	</div>
	<div class="copyrights">
		Collect from <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a>
	</div>

	<div class="sidebar-nav">
		<div style="width:300px" id="tree"></div>
	</div>


	<div class="content">
  		<img alt="" width="1078px" src="${pageContext.request.contextPath }/static/resources/images/maining.jpg">
	</div>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/resources/bootstrap/js/chart-data-morris.js"></script>
</html>