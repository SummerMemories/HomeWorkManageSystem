<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.util.*" %>
<%@ page import="hwms.entity.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1
    	user-scalable=no">
    <!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="/HomeWorkManageSystem/BootStrap/css/bootstrap.min.css">
    <!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
    <!-- 可选: 包含 jQuery 库 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>
	<title>教师提交统计</title>
	<script type="text/javascript">
	//班级作业统计
	function show(w_num,w_cour_id,w_title){	
		$.ajax({  
    		url:"/HomeWorkManageSystem/teacherServlet",//servlet文件的名称
    		type:"POST",
    		data:{ 'funct':'t_checkwork',
				   'w_num':w_num, 
				   'w_cour_id':w_cour_id,
				   'w_title':w_title},
    		async:false
    	});
		
		$("iframe").attr("src","/HomeWorkManageSystem/teacher/t_checkwork.jsp");
	}
	</script>
	
</head>
<body>
<%
	List<Work> work = (List<Work>)session.getAttribute("w");
	for(int i=0; i < work.size(); i++){
		System.out.println("作业表jsp:"+work.get(i).getW_Title());
	}
	List<String> clazz = (List<String>)session.getAttribute("c");
	for(int i=0; i<clazz.size(); i++){
		System.out.println("课程号jsp:"+clazz.get(i));
	}
%>
<div class="container" style="min-width: 100%;">
	<div class="row-fluid">
<!--菜单项-->
		<div class="col-md-2 column" style="background-color: #E0FFFF;height: 800px;padding: 0;">
			
			<div class="row clearfix">
					<div class="col-md-12 column">
							<div class="panel-group" id="panel-614308">
								<div class="panel panel-default">
								<%
									for(int i = 0; i < clazz.size(); i++){
										%>
											<div class="panel-heading" style="height: 35px;">
												 <a class="panel-title" data-toggle="collapse" data-parent="#panel-614308" href="#<%=clazz.get(i) %>"><%=clazz.get(i) %>
												 <strong class="caret"></strong></a>
											</div>
											<div id="<%=clazz.get(i) %>" class="panel-collapse collapse in"  aria-expanded="false">
										<%
										for(int j = 0; j < work.size(); j++){
											if(work.get(j).getW_cour_ID().equals(clazz.get(i))){
												%>
													<div class="panel-body"> 
														<a href="javascript:void(0)" class="work "  onclick= "show(<%=work.get(j).getW_Num() %>,<%=clazz.get(i)%>,<%=work.get(j).getW_Title() %>)"><%= work.get(j).getW_Title() %></a> 
													</div>
												<%
											}
										}
										%>
											</div>
										<%
										
									}
								%>
								</div>
							</div>
					</div>
			</div>
		
		</div>
<!--显示区-->
		<div class="col-md-10 column" style="background-color: white;height: 1000px;">
			<!--iframe标签-->	
			<iframe src="" frameborder="0"  seamless="seamless" marginheight="0px" marginwidth="0px" width="100%" height="100%""></iframe>
		</div>													<!-- scrolling="no" -->
	</div>
</div>
</body>
</html>