<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="hwms.entity.Student"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 包含头部信息用于适应不同设备 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1
    	user-scalable=no">
<!-- 包含 bootstrap 样式表 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<title>学生框架</title>
<!--学生的整体框架-->
<script type="text/javascript">
	$(document).ready(function() {
		//主页
		$("a.navbar-brand").click(function() {
			$.ajax({  
        		url:"/HomeWorkManageSystem/studentServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"s_home"},
        		async:false
        	});

			$("iframe").attr("src", "/HomeWorkManageSystem/student/s_home.jsp");
			//面包屑导航				 
			$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"); 
		});

		//个人设置
		$("a.personalseting").click(function() {
			$.ajax({  
        		url:"/HomeWorkManageSystem/studentServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"s_personalseting"},
        		async:false
        	});
			
			$("iframe").attr("src", "/HomeWorkManageSystem/student/s_personalseting.jsp");
			//面包屑导航
			$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>个人设置</li>");
		});
			
		//上传作业
		$("a.upload").click(function() {
			 $.ajax({  
        		url:"/HomeWorkManageSystem/studentServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"s_uploadwork_jsp"},
        		async:false
        	});  
			$("iframe").attr("src", "/HomeWorkManageSystem/student/s_uploadwork.jsp");
			//面包屑导航				 
			$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>上传作业</li>");
		});

		/* //下载作业
		$("a.download").click(function() {
			$("iframe").attr("src", "/WorkManageSystem/student/s_downloadwork");
			//面包屑导航
			$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>下载作业</li>");
		}); */

		//查看批阅
		$("a.showcheck").click(function() {
			$.ajax({  
        		url:"/HomeWorkManageSystem/studentServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"s_showcheck"},
        		async:false
        	});
			$("iframe").attr("src", "/HomeWorkManageSystem/student/s_showcheck.jsp");
			//面包屑导航
			$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>查看批阅</li>");
		});
	});
</script>
</head>
<body>
	<%
	Student stu = new Student();
	if(session.getAttribute("student") == null) {
		out.print("<script language='javascript'>alert('请先登录系统！');window.location = '../login/Login.html';</script>");
	}else{
		stu = (Student)session.getAttribute("student");
	}
	%>
	<!--整个页面栅格-->
	<div class="container-fluid"
		style="background-color: blasck; margin-left: 100px; margin-right: 100px; padding-top: 0px;">
		<div class="row-fluid">
			<div class="span12">

				<!--系统头部-->
				<div class="page-header"
					style="margin-top: 0px; height: 42px; clear: all;">
					<h1 class="text-center">
						<span><strong>作业管理系统-学生</strong></span>
						<!-- <img src="img/bom.gif" style="margin-left: 12%;"> -->
					</h1>
				</div>

				<!--菜单栏-->
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><span
							class="glyphicon glyphicon-pencil" style="font-size: 25px;"></span></a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="#" class="dropdown-toggle upload">上传作业</a></li>
							<!-- <li><a href="#" class="dropdown-toggle download">下载作业</a></li> -->
							<li><a href="#" class="dropdown-toggle showcheck">查看批阅</a></li>
						</ul>
						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" />
							</div>
							<button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li>
								<!-- <a href="#"><span class="glyphicon glyphicon-envelope" style="font-size: 20px;"></a>
								--> <img src="../img/P.jpg" class="img-circle"
								style="width: 50px; height: 50px;">
							</li>
							<li><strong><span style="line-height: 55px;">学生:&nbsp;<%= stu.getS_Name() %></span></strong></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><span
									class="glyphicon glyphicon-user" style="font-size: 20px;"></span><strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="#">学生：&nbsp;<%= stu.getS_Name() %></a></li>
									<li><a href="#" class="personalseting">个人设置</a></li>
									<li class="divider"></li>
									<li><a href="/HomeWorkManageSystem/logoutServlet" class="logout"><span
											class="glyphicon glyphicon-off"></span>注 销</a></li>
								</ul></li>
						</ul>
					</div>

				</nav>


				<!--面包屑导航-->
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)">首页</a></li>
				</ol>

				<!--iframe框架内容-->
				<div class="row-fluid">
					<div class="span12">
						<div style="height: 1000px;">
							<iframe src="/HomeWorkManageSystem/student/s_home.jsp"
								frameborder="0" seamless="seamless" marginheight="0px"
								marginwidth="0px" width="100%" height="100%""></iframe>
						</div>
					</div>
				</div>

				<!--脚部地址-->
				<div class="text-center" style="margin-top: 30%;">
					<hr />
					<address>
						<strong>三峡大学</strong>&nbsp; 计算机与信息学院, 物联网专业&nbsp; 软件工程&nbsp;<abbr
							title="Phone">P:</abbr> 15672506035
					</address>
				</div>

			</div>
		</div>
	</div>
</body>
</html>