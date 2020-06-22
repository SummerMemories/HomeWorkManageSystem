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
	<title>教师管理</title>
	
	<script type="text/javascript">
	
		//删除教师
		function del(account){
			$.ajax({  
        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"delT",
        			  "account": account },
        		async:false
        	});
			window.location.reload();
		}
		
		//面包屑导航
		function setbreadcrumb(account){
			$.ajax({  
        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"getUpdateT",
        			  "account": account },
        		async:false
        	});
			$("ol.breadcrumb",parent.document).html("<li><a href='javascript:void(0)''>首页</a></li>"+"<li class='active'>修改教师</li>");
		}
		
		function addTeacherExcel(){
			$.ajax({  
        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"addTeacherExcel"},
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
	
	<div class = "text-center">
		<strong style="font-size:25px">教师总览</strong>
	</div>
	<table class="table table-hover table-bordered table-striped">
		<thead>
			<tr>
				<th>序号</th>
				<th>教师工号</th>
				<th>姓名</th>
				<th>密码</th>
				<th>修改</th>
				<th style="width: 45px;">删除</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty teacher}" > 
				<c:forEach items="${teacher}" var="t" varStatus="status">
					<c:if test="${status.count%2==0 } ">
						<tr class="success" style="color:#98FB98; font-size:20px;">
					</c:if>
					<c:if test="${status.count%2!=0 }" >
						<tr>
					</c:if>
						<td><%=++a %></td>
						<td>${ t.t_Account }</td>
						<td>${ t.t_Name }</td>
						<td>${ t.t_Password }</td>
						<td><a href="/HomeWorkManageSystem/manage/m_updateteacher.jsp" onclick="setbreadcrumb('${t.t_Account}')">修改</a></td>
						<td>
							<a type="button" class="close" href = "javascript:del('${t.t_Account }')">
								<span class="glyphicon glyphicon-trash"></span>
								<span class="sr-only">关闭</span>
							</a>
						</td>
					</tr>
					
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<button class = "btn btn-info" onclick = "addTeacherExcel()" style = "height:40px;width:78px;margin-left: 90%">导入 </button>
<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
    <!-- 可选: 包含 jQuery 库 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>
</body>
</html>