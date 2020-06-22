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
		function del(num) {
			$.ajax({  
	    		url:"/HomeWorkManageSystem/teacherServlet",//servlet文件的名称
	    		type:"POST",
	    		data:{"funct":"delInvitation",
	    			  "num":num},
	    		async:false
	    	});
			window.location.reload();
		}
	</script>
</head>
<body>
<%
	int a = 0;
	List<Assistant> assistant = new ArrayList<Assistant>();
	assistant = (List<Assistant>) session.getAttribute("assistant");
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
                            <th style="text-align:center;vertical-align:middle">序号</th>
                            <th style="text-align:center;vertical-align:middle">助教姓名</th>
                            <th style="text-align:center;vertical-align:middle">助教账号</th>
                            <th style="text-align:center;vertical-align:middle">课程号</th>
                            <th style="text-align:center;vertical-align:middle">是否接受</th>
                            <th style="width:45px;text-align:center;vertical-align:middle">删除 </th>
                        </tr>
                    </thead>
                    <tbody>
                    	<%
								for(int i = 0; i < invitation.size(); i++) {	
						%>
						
									<tr>
										<td style="text-align:center;vertical-align:middle"><%=++a %></td>
					                    <td style="text-align:center;vertical-align:middle"><%= assistant.get(i).getA_Name() %></td>
					                    <td style="text-align:center;vertical-align:middle"><%= assistant.get(i).getA_Account() %></td>
						<%
											if(invitation.get(i).getIn_Accept().isEmpty())
											{
						%>				
					                            <td style="text-align:center;vertical-align:middle"><%= invitation.get(i).getIn_cour_ID() %></td>
					                            <td style="text-align:center;vertical-align:middle"></td>
					                            <td style="text-align:center;vertical-align:middle">
													<a type="button" class="close" href = "javascript:del('<%= invitation.get(i).getIn_Num() %>')">
														<span class="glyphicon glyphicon-trash"></span>
													</a>
				                        		</td>
						<%
											} 
											else if(invitation.get(i).getIn_Accept().equals("是"))
											{
						%>
					                            <td style="text-align:center;vertical-align:middle"><%= invitation.get(i).getIn_cour_ID() %></td>
					                            <td style="color:green;text-align:center;vertical-align:middle">是</td>
					                            <td style="text-align:center;vertical-align:middle">
													<a type="button" class="close" href = "javascript:del('<%= invitation.get(i).getIn_Num() %>')">
														<span class="glyphicon glyphicon-trash"></span>
													</a>
				                        		</td>
			            <%
											}
											else if(invitation.get(i).getIn_Accept().equals("否"))
											{
						%>   
					                            <td style="text-align:center;vertical-align:middle"><%= invitation.get(i).getIn_cour_ID() %></td>
					                            <td style="color:red;text-align:center;vertical-align:middle">否</td>
					                            <td style="text-align:center;vertical-align:middle">
													<a type="button" class="close" href = "javascript:del('<%= invitation.get(i).getIn_Num() %>')">
														<span class="glyphicon glyphicon-trash"></span>
													</a>
				                        		</td>
			                        	</tr>
						<%
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