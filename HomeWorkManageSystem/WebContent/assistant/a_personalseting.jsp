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
	<title>教师个人设置</title>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th>编 号</th>
							<th>类 别</th>
							<th>信 息</th>
							<th>操 作</th>
							<th>    </th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty assistant}">
							<tr class="success">
								<td>1</td>
								<td>账 号</td>
								<td>${assistant.a_Account }</td>
								<td><a id="modal-1" href="javascript:return false;" class="1" data-toggle="modal" style="clear:both;opacity: 0.5">修 改</a></td>
								<td></td>
							</tr>
							<tr class="error">
								<td>2</td>
								<td>姓 名</td>
								<td>${assistant.a_Name }</td>
								<td><a id="modal-2" href="#modal-container-2" class="2" data-toggle="modal" style="clear:both;">修 改</a></td>
								<td></td>
							</tr>
							<tr class="warning">
								<td>3</td>
								<td>密 码</td>
								<td>*********</td>
								<td><a id="modal-3" href="#modal-container-3" class="3" data-toggle="modal" style="clear:both;">修 改</a></td>
								<td></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			<div class="container-fluid">
					<!-- 修改账号 -->
					<div class="modal fade" id="modal-container-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">修改账号</h4>
								</div>
								<form action="/HomeWorkManageSystem/assistantServlet" method = "post">
								<div class="modal-body">
									<input type="hidden" name="id" value="${assistant.a_Num }"/>		<!--修改内容提示  -->
									<input type = "hidden" name = "funct"  value = "updateAccount"/>
									原账号:<input type="text" name="oldaccount" value="${assistant.a_Account }" readonly="readonly" disabled > <br><br>
									新账号:<input type="text" name="account" />
								</div>
								<div class="modal-footer">
									 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
									 <button type="submit" class="btn btn-primary">修改</button>
									 <button type="reset" class="btn btn-primary">重置</button>
								</div>
								</form>
							</div>
						</div>
					</div>
					<!-- 修改姓名 -->
					<div class="modal fade" id="modal-container-2" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">修改姓名</h4>
								</div>
								<form action="/HomeWorkManageSystem/assistantServlet" method = "post">
								<div class="modal-body">
									<input type="hidden" name="id" value="${assistant.a_Num }"/>		<!--修改内容提示  -->
									<input type = "hidden" name = "funct"  value = "updateName"/>
									原名:<input type="text" name="oldname" value="${assistant.a_Name }" readonly="readonly" disabled > <br><br>
									新名:<input type="text" name="name"/>
								</div>
								<div class="modal-footer">
									 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
									 <button type="submit" class="btn btn-primary">修改</button>
									 <button type="reset" class="btn btn-primary">重置</button>
								</div>
								</form>
							</div>
						</div>
					</div>
					<!--修改密码-->
					<div class="modal fade" id="modal-container-3" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">
										修改密码
									</h4>
								</div>
								<form class="cmxform" id="form3" action="/HomeWorkManageSystem/assistantServlet" method = "post">
									<div class="modal-body">
										<p><input type="hidden" name="id" value="${assistant.a_Num }" /></p>		<!--修改内容提示  -->
											<input type = "hidden" name = "funct"  value = "updatePassword"/>
											<!-- <label >原&nbsp;&nbsp;密&nbsp;码:</label> -->
											原&nbsp;&nbsp;密&nbsp;码:<input type="password" name="oldpaw" value="${assistant.a_Password }" readonly="readonly" disabled > <br><br>
										
											<!-- <label for="psw">新&nbsp;&nbsp;密&nbsp;码:</label> -->
											新&nbsp;&nbsp;密&nbsp;码:<input type="password" id="psw" name="psw" ><br><br>
										
											<!-- <label>确定密码:</label> -->
											确定密码:<input type="password" id="password" name="password" ><br><br>
									</div>
									<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
											<button type="submit" id="submit" class="btn btn-primary">修改</button>
											<button type="reset" id="reset" class="btn btn-primary">重置</button>
									</div>
								</form>
							</div>
						</div>
					</div>
			</div>
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