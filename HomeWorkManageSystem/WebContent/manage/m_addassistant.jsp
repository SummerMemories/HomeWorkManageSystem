<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1
    	user-scalable=no">
    <!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="/HomeWorkManageSystem/BootStrap/css/bootstrap.min.css">
	<title>助教管理-添加</title>
	<script>
		function addAssistantExcel(){
			System.out.println("addAssistantExcle");
			$.ajax({  
        		url:"/HomeWorkManageSystem/manageServlet",//servlet文件的名称
        		type:"POST",
        		data:{"funct":"addAssistantExcel"},
        		async:false
        	});
		}
	</script>
	
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form class="form-horizontal" action="/HomeWorkManageSystem/manageServlet" method="post" role="form">
					<input type="hidden" name="funct" value="addA"  />
					<div class="form-group">
						 <label for="inputEmail3" class="col-sm-2 control-label">账号:</label>
						<div class="col-sm-2">
							<input type="text" name="a_account" class="form-control" id="inputEmail3"/>
						</div>
					</div>
					
					<div class="form-group">
						 <label for="inputEmail3" class="col-sm-2 control-label">姓名:</label>
						<div class="col-sm-2">
							<input type="text" name="a_name" class="form-control" id="inputEmail3"/>
						</div>
					</div>
					
					<div class="form-group">
						 <label for="inputEmail3" class="col-sm-2 control-label">密码:</label>
						<div class="col-sm-2">
							<input type="text" name="a_password" class="form-control" id="inputEmail3"/>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							 <button type="submit" class="btn btn-default">添加</button>&nbsp;
							 <button type="reset" class="btn btn-default">重置</button>
						</div>
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