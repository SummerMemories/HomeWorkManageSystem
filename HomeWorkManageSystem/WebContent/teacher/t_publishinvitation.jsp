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
	<title>教师管理-发出邀请</title>
	<script>
		
	</script>
	
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form class="form-horizontal" action="/HomeWorkManageSystem/teacherServlet" method="post" role="form">
					<input type="hidden" name="funct" value="publishinvitation"  />

					<div class="input-group input-group-sm" style="width: 250px;">
						<span class="input-group-addon">课程号</span>
						<select class="form-control" name="in_cour_id">
							<c:if test="${! empty course }">
								<c:forEach items="${course }" var="c">
									<option>${c.cour_ID }</option>
								</c:forEach>
							</c:if>
						</select>
					</div><br><br>

					<div class="input-group input-group-sm" style="width: 250px;">
						<span class="input-group-addon">助教姓名</span>
						<select
							class="form-control" name="in_a_Account">
							<c:if test="${! empty assistant }">
								<c:forEach items="${assistant }" var="a">
									<option>${a.a_Account }</option>
								</c:forEach>
							</c:if>
						</select>
					</div><br><br>

					<div class="form-group">
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-default">发出邀请</button>&nbsp;
						<button type="reset" class="btn btn-default">重置</button>
					</div>
				</form>
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