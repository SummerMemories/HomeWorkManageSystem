package hwms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hwms.entity.Assistant;
import hwms.entity.CheckWork;
import hwms.entity.Course;
import hwms.entity.Invitation;
import hwms.entity.Student;
import hwms.entity.StudentCourse;
import hwms.entity.Work;
import hwms.service.AssistantManage;
import hwms.service.CheckWorkManage;
import hwms.service.CourseManage;
import hwms.service.InvitationManage;
import hwms.service.StudentCourseManage;
import hwms.service.StudentManage;
import hwms.service.WorkManage;

/**
 * Servlet implementation class assistantServlet
 */
@WebServlet("/assistantServlet")
public class assistantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public assistantServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		// 截取其中的方法名
		String methodName = "";
		methodName = request.getParameter("funct");

		//System.out.println(methodName);
		Method method = null;
		try {
			// 使用反射机制获取在本类中声明了的方法
			method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 执行方法
			method.invoke(this, request, response);
			// response.sendRedirect("/HomeWorkManageSystem/student/student.jsp");
		} catch (Exception e) {
			throw new RuntimeException("调用方法出错！");
		}
	}
	
	protected void a_course(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		System.out.println("a_course");
		
		HttpSession session = request.getSession();
		Assistant assistant = new Assistant();
		assistant = (Assistant)session.getAttribute("assistant");
	
		List<Invitation> invitationList = new ArrayList<Invitation>();
		InvitationManage invitationService = new InvitationManage();
		invitationList = invitationService.getInvitationByAccountN(assistant.getA_Account());
		System.out.println(invitationList.size());
		List<Course> course = new ArrayList<Course>();
		CourseManage courseService = new CourseManage();
		for(int i = 0; i < invitationList.size(); i++) {
			Course course1 = new Course();
			course1 = courseService.getCourseByCourseID(invitationList.get(i).getIn_cour_ID());
			course.add(course1);
		}
		session.setAttribute("course", course);
	}
	
	protected void a_invitation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		System.out.println("a_invitation");
		
		HttpSession session = request.getSession();
		Assistant assistant = new Assistant();
		assistant = (Assistant)session.getAttribute("assistant");
	
		List<Invitation> invitation = new ArrayList<Invitation>();
		InvitationManage invitationService = new InvitationManage();
		invitation = invitationService.getInvitationByAccount(assistant.getA_Account());

		List<Course> course = new ArrayList<Course>();
		CourseManage courseService = new CourseManage();
		for(int i = 0; i < invitation.size(); i++) {
			Course course1 = new Course();
			course1 = courseService.getCourseByCourseID(invitation.get(i).getIn_cour_ID());
			course.add(course1);
		}
		session.setAttribute("course", course);
		session.setAttribute("invitation", invitation);
	}
	
	protected void a_statistics(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("a_statistics");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Assistant assistant = new Assistant();
		assistant = (Assistant) session.getAttribute("assistant");

		// 获取助教的课程列表
		InvitationManage invitationService = new InvitationManage();
		List<Invitation> invitationList = new ArrayList<Invitation>();
		invitationList = invitationService.getInvitationByAccount(assistant.getA_Account());
		
		List<Course> courseList = new ArrayList<Course>();
		CourseManage courseService = new CourseManage();
		for(int i = 0; i < invitationList.size(); i++) {
			courseList.add(courseService.getCourseByCourseID(invitationList.get(i).getIn_cour_ID()));
		}
		
		List<String> clazz = new ArrayList<String>();
		for (int i = 0; i < courseList.size(); i++) {
			System.out.println(courseList.get(i).getCour_ID());
			clazz.add(courseList.get(i).getCour_ID());
		}
		session.setAttribute("c", clazz);

		// 获取作业列表
		WorkManage workService = new WorkManage();
		List<Work> workList = new ArrayList<Work>();
		for(int i = 0; i < invitationList.size(); i++) {
			List<Work> workList1 = new ArrayList<Work>();
			workList1 = workService.getWorkByCourseID(invitationList.get(i).getIn_cour_ID());
			workList.addAll(workList1);
		}
		session.setAttribute("w", workList);
	}
	
	protected void a_checkwork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("a_checkwork");
		
		HttpSession session = request.getSession();

		String w_num = request.getParameter("w_num");
		String w_cour_id = request.getParameter("w_cour_id");
		String w_title = request.getParameter("w_title");
		
		session.setAttribute("w_num", w_num);
		session.setAttribute("w_cour_id", w_cour_id);
		session.setAttribute("w_title", w_title);
		
		//获取该课程下的学生列表		
		StudentCourseManage studentcourseService = new StudentCourseManage();
		List<StudentCourse> studentcourseList = new ArrayList<StudentCourse>();
		studentcourseList = studentcourseService.getStudentByCourse(w_cour_id);
		List<Student> studentList = new ArrayList<Student>();
		StudentManage studentService = new StudentManage();
		for (int i = 0; i < studentcourseList.size(); i++) {
			studentList.add(studentService.getStudentByAccount(studentcourseList.get(i).getSc_s_Account()));
		}
		session.setAttribute("student", studentList);

		
		//获取该课程下该作业的审核列表
		List<CheckWork> checkworkList = new ArrayList<CheckWork>();
		CheckWorkManage checkworkService = new CheckWorkManage();
		checkworkList = checkworkService.getCheckWorkByCourIDWNum(w_cour_id, w_num);
		session.setAttribute("checkwork", checkworkList);
		
	}
	
	protected void correctWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("correctWork");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		String ch_num = request.getParameter("ch_num");
		String ch_mark = request.getParameter("ch_mark");
		String ch_score = request.getParameter("ch_score");

		/*
		 * System.out.println(ch_num);
		 * System.out.println(ch_mark);
		 * System.out.println(ch_score);
		 */

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String ch_checkTime = dateFormat.format(date);
		CheckWorkManage checkworkService = new CheckWorkManage();
		PrintWriter out = response.getWriter();
		if (checkworkService.updateCheckWorkByTeacher(ch_num, ch_score, ch_mark, ch_checkTime)) {
			out.print(
					"<script language='javascript'>alert('批阅成功！');window.location = '/HomeWorkManageSystem/assistant/a_checkwork.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('批阅失败！');window.location = '/HomeWorkManageSystem/assistant/a_checkwork.jsp';</script>");
		}
	}
	
	protected void accept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		System.out.println("accept");
		
		String in_num = request.getParameter("num");
		String in_accept = "是";
		
		PrintWriter out = response.getWriter();
		InvitationManage invitationService = new InvitationManage();
		if(invitationService.updateInvitation(in_accept, in_num)) {
			a_invitation(request,response);
			out.print("<script language='javascript'>alert('接受成功！');window.location.reload();</script>");
		} else {
			out.print("<script language='javascript'>alert('接受失败！');window.location.reload();</script>");
		}
		
	}
	
	protected void refuse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		System.out.println("refuse");
		
		String in_num = request.getParameter("num");
		String in_accept = "否";
		
		PrintWriter out = response.getWriter();
		InvitationManage invitationService = new InvitationManage();
		if(invitationService.updateInvitation(in_accept, in_num)) {
			a_invitation(request,response);
			out.print("<script language='javascript'>alert('拒绝成功！');window.location.reload();</script>");
		} else {
			out.print("<script language='javascript'>alert('拒绝失败！');window.location.reload();</script>");
		}
	}
	
	
	protected void updateName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession(); // 获取session
		PrintWriter out = response.getWriter();

		if (session.getAttribute("assistant") != null) {
			Assistant assistant = new Assistant();
			assistant = (Assistant) session.getAttribute("assistant");
			String newName = request.getParameter("name");
			assistant.setA_Name(newName);
			AssistantManage assistantService = new AssistantManage();
			if (assistantService.updateAssistantName(assistant)) {
				session.setAttribute("assistant", assistant);
				out.print("<script language='javascript'>alert('修改成功！');parent.location.reload();</script>");
			} else {
				out.print("fail");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/assistant/a_personalseting.jsp';</script>");
		}
	}
	
	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		String a_password = request.getParameter("psw");
		String check_newpsw = request.getParameter("password");
		PrintWriter out = response.getWriter();

		if (!a_password.equals(check_newpsw)) {
			// System.out.println("不一致");
			out.print(
					"<script language='javascript'>alert('密码不一致！');window.location = '/HomeWorkManageSystem/assistant/a_personalseting.jsp';</script>");
			return;
		}

		HttpSession session = request.getSession(); // 获取session
		if (session.getAttribute("assistant") != null) {
			Assistant assistant = new Assistant();
			assistant = (Assistant) session.getAttribute("assistant");
			assistant.setA_Password(a_password);
			AssistantManage assistantService = new AssistantManage();
			if (assistantService.updateAssistantPassword(assistant)) {
				session.setAttribute("assistant", assistant);
				out.print(
					"<script language='javascript'>alert('修改成功！');parent.location.reload();</script>");
			} else {
				out.print("fail");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/assistant/a_personalseting.jsp';</script>");
		}
	}
	
}
