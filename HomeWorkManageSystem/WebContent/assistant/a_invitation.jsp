<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.util.*" %>
<%@ page import="hwms.entity.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	 <!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="/HomeWorkManageSystem/BootStrap/css/bootstrap.min.css">
    <!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
    <!-- 可选: 包含 jQuery 库 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/jquery-3.1.1.js"></script>
    <!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
    <script src="/HomeWorkManageSystem/BootStrap/js/bootstrap.min.js"></script>
	
	<title>邀请列表</title>
	<script>
	
	function accept1(num){
		$.ajax({  
    		url:"/HomeWorkManageSystem/assistantServlet",//servlet文件的名称
    		type:"POST",
    		data:{"funct": "accept",
    			  "num": num},
    		async:false
    	});
		window.location.reload();
	}
	
	function refuse1(num){
		$.ajax({  
    		url:"/HomeWorkManageSystem/assistantServlet",//servlet文件的名称
    		type:"POST",
    		data:{"funct": "refuse",
    			  "num": num},
    		async:false
    	});
		window.location.reload();
	}
	
	</script>
</head>
<body>
<%
	int a = 0;
	List<Course> course = new ArrayList<Course>();
	course = (List<Course>) session.getAttribute("course");
	List<Invitation> invitation = new ArrayList<Invitation>();
	invitation = (List<Invitation>)session.getAttribute("invitation");
%>
	<div class="container" style=" ">
        <div class="row-fluid">
            <div class="span12">
                <div style="float: left; width: 100%;">
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                        <tr style="text-algin:center">
                            <th style="width: 60px;text-align:center;vertical-align:middle">序号</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">课程名称</th>
                            <th style="width: 90px;text-align:center;vertical-align:middle">课程人数</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">教师工号</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">是否接受</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">接受</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">拒绝</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<%
								for(int i = 0; i < course.size(); i++)
								{
						%>
									<tr>
										<td style="text-align:center;vertical-align:middle"><%=++a %></td>
					                    <td style="text-align:center;vertical-align:middle"><%= course.get(i).getCour_Name() %></td>
					                    <td style="text-align:center;vertical-align:middle"><%= course.get(i).getCour_Count() %></td>
					                    <td style="text-align:center;vertical-align:middle"><%= course.get(i).getCour_t_ID() %></td>
					                    
						<%
									for(int j = 0; j < invitation.size(); j++)
									{ 
										if(course.get(i).getCour_ID().equals(invitation.get(j).getIn_cour_ID()))
										{
											if(invitation.get(j).getIn_Accept().isEmpty())
											{
						%>				
					                            <td style="text-align:center;vertical-align:middle"></td>
					                            <td style="text-align:center;vertical-align:middle">
					                            	<a type="button" href = "javascript:accept1('<%= invitation.get(j).getIn_Num() %>')">接 受</a>
					                            </td>
					                            <td style="text-align:center;vertical-align:middle">
					                            	<a type="button" href = "javascript:refuse1('<%= invitation.get(j).getIn_Num() %>')">拒 绝</a>
					                            </td>
						<%
											} 
											else if(invitation.get(j).getIn_Accept().equals("是"))
											{
						%>
					                            <td style="color:green;text-align:center;vertical-align:middle">是</td>
					                            <td style="text-align:center;vertical-align:middle"><a id="modal-1" href="javascript:return false;" class="1" data-toggle="modal" style="clear:both;opacity: 0.5">接 受</a></td>
					                            <td style="text-align:center;vertical-align:middle"><a id="modal-1" href="javascript:return false;" class="2" data-toggle="modal" style="clear:both;opacity: 0.5">拒 绝</a></td>
			            <%
											}
											else if(invitation.get(j).getIn_Accept().equals("否"))
											{
						%>   
					                            <td style="color:red;text-align:center;vertical-align:middle">否</td>
					                            <td style="text-align:center;vertical-align:middle"><a id="modal-1" href="javascript:return false;" class="1" data-toggle="modal" style="clear:both;opacity: 0.5">接 受</a></td>
					                            <td style="text-align:center;vertical-align:middle"><a id="modal-1" href="javascript:return false;" class="2" data-toggle="modal" style="clear:both;opacity: 0.5">拒 绝</a></td>
			                        	</tr>
						<%
											}
										}
									}
								}
						%>
                    </tbody>
                </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>