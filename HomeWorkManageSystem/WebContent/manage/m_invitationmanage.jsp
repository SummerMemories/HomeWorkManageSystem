<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1
    	user-scalable=no">
    <!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="/HomeWorkManageSystem/BootStrap/css/bootstrap.min.css">
	<title>邀请管理</title>
	
	<script type="text/javascript">
		//删除邀请
		function del(in_num){
			$.ajax({  
        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"delI",
        			  "in_num": in_num },
        		async:false
        	});
			window.location.reload();
		}
	</script>
	
</head>
<body>

	<%
		int a = 0;
	%>

	<div class="text-center">
		<strong style="font-size:20px;">邀请总览</strong>
		<!-- <strong class="pull-right">总人数：xx</strong> -->
	</div>
	<table class="table table-hover table-bordered table-striped">
		<thead>
			<tr>
				<th>序号</th>
				<th>助教号</th>
				<th>课程号</th>
				<th>是否接受</th>
				<th style="width: 45px;">
					删除
				</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty invitation}" >
				<c:forEach items="${invitation}" var="in">
					<c:if test="${status.count%2==0 } ">
						<tr class="success" style="color:#98FB98; font-size:20px;">
					</c:if>
					<c:if test="${status.count%2!=0 }" >
						<tr>
					</c:if>
						<td><%=++a %></td>
						<td>${ in.in_a_Account }</td>
						<td>${ in.in_cour_ID }</td>
						<td>${ in.in_Accept }</td>
						<td>
							<a type="button" class="close" href = "javascript:del('${in.in_Num }')">
								<span class="glyphicon glyphicon-trash"></span>
								<span class="sr-only">关闭</span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
    <!-- 可选: 包含 jQuery 库 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>
</body>
</html>