<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 包含头部信息用于适应不同设备 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<title>管理员界面</title>

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

<!--管理员的整体框架-->
<script type="text/javascript">
		
/************************************************************************************/
		$(document).ready(function(){
			
			//教师总览，删除，修改
			$("a.allT").click(function(){
				$.ajax({  
	        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
	        		type:"POST",
	        		data:{"funct":"allT"},
	        		async:false
	        	});
				
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_teachermanage.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>教师总览</li>");
			});
			
			//教师添加
			$("a.addT").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_addteacher.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>教师添加</li>");
			});
			
			//助教总览，删除，修改
			$("a.allA").click(function(){
				$.ajax({  
	        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
	        		type:"POST",
	        		data:{"funct":"allA"},
	        		async:false
	        	});
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_assistantmanage.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>助教总览</li>");
			});
			
			//助教添加
			$("a.addA").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_addassistant.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>助教添加</li>");
			});
					
			//学生总览，删除，修改
			$("a.allS").click(function(){
				$.ajax({  
	        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
	        		type:"POST",
	        		data:{"funct":"allS"},
	        		async:false
	        	});
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_studentmanage.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>学生总览</li>");
			});
			
			//学生添加
			$("a.addS").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_addstudent.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>学生添加</li>");
			});
			
			//课程总览，删除，修改
			$("a.allC").click(function(){
				$.ajax({  
	        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
	        		type:"POST",
	        		data:{"funct":"allC"},
	        		async:false
	        	});
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_coursemanage.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>课程总览</li>");
			});
			
			//课程添加
			$("a.addC").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_addcourse.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>课程添加</li>");
			});
					
			//学生选课总览，删除，修改
			$("a.allSC").click(function(){
				$.ajax({  
	        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
	        		type:"POST",
	        		data:{"funct":"allSC"},
	        		async:false
	        	});
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_studentcoursemanage.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>学生选课总览</li>");
			});
			
			//学生选课添加
			$("a.addSC").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_addstudentcourse.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>学生选课添加</li>");
			});
			
			//邀请总览，删除，修改
			$("a.allI").click(function(){
				$.ajax({  
	        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
	        		type:"POST",
	        		data:{"funct":"allI"},
	        		async:false
	        	});
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_invitationmanage.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>助教邀请总览</li>");
			});
			
			//邀请添加
			$("a.addI").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_addinvitation.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>助教邀请添加</li>");
			});
			
			//个人设置
			$("a.m_personalseting").click(function(){
				$("iframe").attr("src","/HomeWorkManageSystem/manage/m_personalseting.jsp");
				//面包屑导航
				$("ol.breadcrumb").html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>个人设置</li>");
			});
			
			
		});
	</script>

</head>
<body>
	<div class="container-fluid"
		style="background-color: blasck; margin-left: 100px; margin-right: 100px; padding-top: 0px;">
		<div class="row-fluid">
			<div class="span12">
				<!-- 顶部 -->
				<div class="page-header"
					style="margin-top: 0px; height: 42px; clear: all;">
					<h1 class="text-center">
						<strong>作业管理系统-管理员</strong>
					</h1>
				</div>

				<!-- 菜单栏 -->
				<nav class="navbar navbar-default" role="navigation">
					<!-- style="background:yellow; -->
					<div class="container-fluid">

						<div class="navbar-header">
							<a class="navbar-brand" href="#"><span
								class="glyphicon glyphicon-pencil" style="font-size: 25px;"></span></a>
						</div>

						<div>
							<ul class="nav navbar-nav">
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> 教师管理<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#" class="allT">总 览</a></li>
										<li class="divider"></li>
										<li><a href="#" class="addT">添 加</a></li>
										<li><a href="#" class="allT">删 除</a></li>
										<li><a href="#" class="allT">修 改</a></li>
									</ul></li>

								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> 助教管理<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#" class="allA">总 览</a></li>
										<li class="divider"></li>
										<li><a href="#" class="addA">添 加</a></li>
										<li><a href="#" class="allA">删 除</a></li>
										<li><a href="#" class="allA">修 改</a></li>
									</ul></li>
									
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> 学生管理<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#" class="allS">学生总 览</a></li>
										<li><a href="#" class="addS">添 加</a></li>
										<li><a href="#" class="allS">删 除</a></li>
										<li><a href="#" class="allS">修 改</a></li>
										<li class="divider"></li>
										<li><a href="#" class="allSC">选课总 览</a></li>
										<li><a href="#" class="addSC">添 加</a></li>
										<li><a href="#" class="allSC">删 除</a></li>			
									</ul></li>
									
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> 课程管理<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#" class="allC">总 览</a></li>
										<li class="divider"></li>
										<li><a href="#" class="addC">添 加</a></li>
										<li><a href="#" class="allC">删 除</a></li>
										<li><a href="#" class="allC">修 改</a></li>
									</ul></li>
								
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> 邀请管理<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#" class="allI">总 览</a></li>
										<li class="divider"></li>
										<li><a href="#" class="addI">添 加</a></li>
										<li><a href="#" class="allI">删 除</a></li>
									</ul></li>

							</ul>
						</div>

						<form class="navbar-form navbar-left" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</form>

						<ul class="nav navbar-nav navbar-right">
							<li>
								<!-- <a href="#"><span class="glyphicon glyphicon-envelope" style="font-size: 20px;"></a> -->
								<img src="../img/P.jpg" class="img-circle"
								style="width: 40px; height: 40px;">
							</li>
							<li><strong><span style="line-height: 55px;">管理员:&nbsp;${manage.m_Name  }</span></strong></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <span
									class="glyphicon glyphicon-user" style="font-size: 20px;"></span>
									<strong class="caret"></strong>
							</a>
								<ul class="dropdown-menu">
									<li><a href="#">管理员:&nbsp;${manage.m_Name  } </a></li>
									<li><a href="#" class="m_personalseting">个人设置</a></li>
									<li class="divider"></li>
									<li><a href="/HomeWorkManageSystem/logoutServlet"><span
											class="glyphicon glyphicon-off"></span>注 销</a></li>
								</ul></li>
						</ul>

					</div>
				</nav>

				<!--面包屑导航-->
				<ol class="breadcrumb">
					<li><a href="javascript:void(0)">主页</a></li>
				</ol>
				<hr>

				<!--iframe框架内容-->
				<div class="row-fluid">
					<!--m_teachermanage.html   -->
					<div class="span12">
						<div style="height: 1000px;">
							<!-- seamless="seamless" 无边框或滚动条 -->
							<iframe src="/HomeWorkManageSystem/manage/m_teachermanage.jsp"
								frameborder="0" seamless="seamless" marginheight="0px"
								marginwidth="0px" width="100%" height="100%""></iframe>

						</div>
					</div>
				</div>
				<!-- <%-- <jsp:forward page="m_teachermanage.jsp"></jsp:forward> --%> -->

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