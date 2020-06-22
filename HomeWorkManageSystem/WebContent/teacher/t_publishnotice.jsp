<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"> 
		<meta charset="{CHARSET}">
	    <!-- 包含头部信息用于适应不同设备 -->
	    <meta name="viewport" content="width=device-width, initial-scale=1
	    	user-scalable=no">
	    <!-- 包含 bootstrap 样式表 -->
	    <link rel="stylesheet" href="/HomeWorkManageSystem/BootStrap/css/bootstrap.min.css">
	    <!-- 可选: 包含 jQuery 库 -->
	    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
	    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
	    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>
	    
		<title>发布公告</title>
		<!--教师的发布公告-->
		
		<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
		<script src="https://cdn.bootcss.com/moment.js/2.22.1/moment-with-locales.min.js"></script>
		<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	</head>

	<body>
		<div class="container-fluid">
		    <div class="row-fluid">
		        <div class="span12" style=" ">
		            <form action="/HomeWorkManageSystem/teacherServlet" method="post">
		                <div class="text" style="float: left;margin-left: 10%;">
		                	<input type="hidden" class="form-control" name = "funct"  value = "publishNotice">
		                    <div class="input-group input-group-sm" style="width: 250px;">
		                        <span class="input-group-addon">主题</span>
		                         <input type="text" class="form-control" name = "nt_title">
		                    </div><br><br>
		                    <div class="input-group input-group-sm" style="width: 250px;">
		                        <span class="input-group-addon">发布时间</span>
		                        <input type="text" class="form-control" id = "datetime" name="nt_time">
		                    </div><br><br>
		                    <div class="input-group input-group-sm" style="width: 250px;">
		                        <span class="input-group-addon">课程班级</span>
		                        <select class="form-control" name = "nt_cour_id">
		                        	<c:if test="${! empty clazz }">
		                				<c:forEach items="${clazz }" var="c"> 
			                        	   <option>${c.cour_ID }</option>
			                            </c:forEach>
		                            </c:if>
		                        </select>
		                    </div><br><br>
		                    <div style=" margin-left: 12%;">
		                        <input type="submit" class="btn btn-primary" value="发布">
		                        <input type="reset" class="btn btn-primary" name="重置" style="margin-left: 40px;">
		                    </div>
		                </div>
		                <div style="margin-left: 40%;">
		                    <textarea name = "nt_content" class="form-control textarea-sm" rows="12"  cols="60" placeholder="内容" style="resize: none;width: 500px;"></textarea>
		                </div>            
		            </form>
		        </div>
		    </div>
		</div>
	</body>
	<script>
		$("#datetime").datetimepicker({
			format: 'YYYY-MM-DD',
			locale: moment.locale('zh-CN'),
		});
	</script>
</html>