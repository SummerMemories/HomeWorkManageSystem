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
	<title>教师提交统计详细</title>
    <script type="text/javascript">
    //panel-check操作
        $(document).ready(function(){
        	//点击批改按钮
            $("a.check").click(function(){
                //alert("没错");
                $("div.panel-check").animate({
                  width:'toggle'
                });
            });
        });
    </script>
	<script type="text/javascript">
		function check(Sno,Sname,W_title,ch_num){
			//alert(Sno+Sname+W_title);
			//动态设置批改对象信息
			$("span.sno").html(Sno+":"+Sname+"------"+W_title+"----"+ch_num);
			//设置隐藏input值
			$("input.ch_num").val(ch_num);
		}
	</script>
</head>
<body>

<%
	int a=0;
	int flag = 0;
	List<CheckWork> chW = new ArrayList<CheckWork>();
	chW = (List<CheckWork>) session.getAttribute("checkwork");
	for(int i=0; i<chW.size(); i++){
		System.out.println("jsp123:"+chW.get(i).getCh_s_Account());
	}
	List<Student> student = new ArrayList<Student>();
	student = (List<Student>)session.getAttribute("student");
	for(int i=0; i<student.size(); i++){
		System.out.println("jsp456:"+student.get(i).getS_Name());
	}
	String cour_id = (String)session.getAttribute("w_cour_id");
	String w_title = (String)session.getAttribute("w_title");
	String w_num = (String)session.getAttribute("w_num");
	
	System.out.println("提交统计班级作业点击jsp--课程号:"+cour_id);
	System.out.println("提交统计班级作业点击jsp--:作业号"+w_num);
	System.out.println("提交统计班级作业点击jsp--:作业主题"+w_title);
%>


    <div class="container" style=" ">
        <div class="row-fluid">
            <div class="span12">
                <div style="float: left; width: 100%;">
                <table class="table table-hover table-bordered table-striped">
                    <h3><%=cour_id %>班:&nbsp;&nbsp;&nbsp;<%=w_title %>----------------提交统计情况</h3>
                    <thead>
                        <tr style="text-algin:center">
                            <th style="width: 60px;text-align:center;vertical-align:middle">序号</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">学号</th>
                            <th style="width: 90px;text-align:center;vertical-align:middle">姓名</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">得分</th>
                            <th style="width: 60px;text-align:center;vertical-align:middle">评语</th>
                            <th style="width: 90px;text-align:center;vertical-align:middle">是否提交</th>
                            <th style="width: 100px;text-align:center;vertical-align:middle">提交时间</th>
                            <th style="width: 100px;text-align:center;vertical-align:middle">批阅时间</th>
                            <th style="width: 90px;text-align:center;vertical-align:middle">批阅结果</th>
                            <th style="width: 90px;text-align:center;vertical-align:middle">下载作业</th>
                        </tr>
                    </thead>
                    <tbody>
                    	
                    	<%
								for(int i=0; i<student.size(); i++){
						%>
									<tr >
										<td style="text-align:center;vertical-align:middle"><%=++a %></td>
					                    <td style="text-align:center;vertical-align:middle"><%=student.get(i).getS_Account() %></td>
					                    <td style="text-align:center;vertical-align:middle"><%=student.get(i).getS_Name() %></td>
					                            <%-- <td><%=student.get(i).getS_sex()%></td> --%>
						<%
									for(int j = 0; j < chW.size(); j++){ 
										if( chW.get(j).getCh_UpTime().length() > 0){
						%>				
					                            <td style="text-align:center;vertical-align:middle"><%=chW.get(j).getCh_Score()  %></td>
					                            <td style="text-align:center;vertical-align:middle"><%=chW.get(j).getCh_Mark() %></td>
					                            <td style="text-align:center;vertical-align:middle">已提交</td>
					                            <td style="text-align:center;vertical-align:middle"><%=chW.get(j).getCh_UpTime().toString() %></td>
					                            <%
					                            	System.out.println("提交时间"+j+"："+chW.get(j).getCh_UpTime());
					                           		System.out.println("批改号"+j+"："+chW.get(j).getCh_Num());
					                            	System.out.println("批改时间"+j+"："+chW.get(j).getCh_CheckTime());
					                            		if( chW.get(j).getCh_Score() ==0 ){
					                            %>			
					                            			<td style="text-align:center;vertical-align:middle"><%=chW.get(j).getCh_CheckTime() %></td><!-- 此时为null -->
					                            			<td style="text-align:center;vertical-align:middle"><a type="button" class="check" onclick="check('<%=student.get(i).getS_Account() %>','<%=student.get(i).getS_Name() %>','<%=w_title %>','<%=chW.get(j).getCh_Num() %>')"  href = "javascript:void(0)">批阅</a></td>
					                            			<td style="text-align:center;vertical-align:middle"><a href="/HomeWorkManageSystem/teacher/Download?Wno=<%=w_num%>&c_id=<%=cour_id%>&Uptime=<%=chW.get(j).getCh_UpTime().toString() %>">下载</a></td>
					                            <%	}
					                            		else{
					                            %>			
					                            			<td style="text-align:center;vertical-align:middle"><%=chW.get(j).getCh_CheckTime().toString()  %></td><!-- 此时已有时间 -->
					                            			<td style="text-align:center;vertical-align:middle"><a style="color:#B23AEE;" type="button" class="check" onclick="check('<%=student.get(i).getS_Account() %>','<%=student.get(i).getS_Name() %>','<%=w_title %>','<%=chW.get(j).getCh_Num() %>')"  href = "javascript:void(0)">已批阅</a></td>
					                            			<td style="text-align:center;vertical-align:middle"><a href="/HomeWorkManageSystem/teacher/Download">下载</a></td>
					                            <%
					                            		}
					                            %>
						<%
										} else {
						%>
				                            <td style="text-align:center;vertical-align:middle"></td>
				                            <td style="text-align:center;vertical-align:middle"></td>
				                            <td style="color:red;text-align:center;vertical-align:middle">未提交</td>
				                            <td ></td>
				                            <td></td>
				                            <td style="text-align:center;vertical-align:middle"> 无法批阅</td>
				                            <td style="text-align:center;vertical-align:middle">无</td>
			                        	</tr>
			            <%
										}
									}
								}
						%>
                    </tbody>
                </table>
                </div>
			<!-- 批改面板 -->
                <div class="panel-check" style="background-color: #F0F0F0;width: 100%;height: 100%; position: absolute;display: none;">
                	<form action="/HomeWorkManageSystem/assistantServlet" method = "post">
                		<!-- 隐藏表单传递批改号 -->
                		<input name = "funct" class="form-control" type="hidden"  value = "correctWork">
                		<input name = "ch_num" class = "ch_num" type = "hidden" value = "666">
                		<input name = "cour_id" class = "cour_id" type = "hidden" value = "<%=cour_id%>"><!-- 课程号 -->
                		<input name = "w_num" class = "w_num" type = "hidden" value = "<%=w_num%>"><!-- 作业号 -->
                		
	                    <br><span class = "sno" style="margin-left:45%;"> 	&nbsp;&nbsp;&nbsp;&nbsp;</span>
	                    
	                    <!-- <span style="margin-left:280px;"><a type="button" onclick="Download()" href="javascript:void(0)">下载学生作业</a></span> -->
	                    <br><br>
	                    <textarea name = "ch_mark" rows="10" cols="135" style="resize: none;" style = "background-color: #F0F0F0;"></textarea>
	                    <div class="input-group input-group-sm" style="width: 300px;">
	                        
	                        <span class="input-group-addon">得分</span>
	                        <input name = "ch_score" type="text" class="form-control" style = "background-color: #F0F0F0;">
	                    </div> <br><br>
	                    
	                    <div style = "margin-left:9%">
	                        <input type="submit" class="btn btn-primary" value="发布">
	                        <a class="check"><input type="button" class="btn btn-primary" value ="返回" style="margin-left: 40px;"></a>
	                    </div>                   
                    </form>
                </div>
            </div>
        </div>

    </div>
</body>
</html>