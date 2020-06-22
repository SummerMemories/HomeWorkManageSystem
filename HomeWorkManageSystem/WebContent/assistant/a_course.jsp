<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>助教课程总览</title>

	<!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="/HomeWorkManageSystem/BootStrap/css/bootstrap.min.css">
    <!-- 可选: 包含 jQuery 库 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>

</head>

<body>
	<%
		int i = 0;
	%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12" style="">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>课程名称</th>
							<th>课程学分</th>
							<th>课程号</th>
							<th>课程教师号</th>
							<th>课程人数</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${! empty course }">
							<c:forEach items="${course }" var="c">
								<tr>
									<td><%=++i %></td>
									<td>${c.cour_Name }</td>
									<td>${c.cour_Credit }</td>
									<td>${c.cour_ID }</td>
									<td>${c.cour_t_ID }</td>
									<td>${c.cour_Count }</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
    <!-- 可选: 包含 jQuery 库 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>
	
</body>
</html>