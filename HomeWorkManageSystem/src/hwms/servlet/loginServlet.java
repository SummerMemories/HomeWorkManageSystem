package hwms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hwms.entity.Assistant;
import hwms.entity.Course;
import hwms.entity.Invitation;
import hwms.entity.Manage;
import hwms.entity.Notice;
import hwms.entity.Student;
import hwms.entity.StudentCourse;
import hwms.entity.Teacher;
import hwms.entity.Work;
import hwms.service.AdminManage;
import hwms.service.AssistantManage;
import hwms.service.CourseManage;
import hwms.service.InvitationManage;
import hwms.service.NoticeManage;
import hwms.service.StudentCourseManage;
import hwms.service.StudentManage;
import hwms.service.TeacherManage;
import hwms.service.WorkManage;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 处理中文问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		// 获取发送的值
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String type = request.getParameter("type");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); // 获取session

		if (type.equals("Student")) {
			Student student = new Student();	
			StudentManage studentService = new StudentManage();
			student = studentService.getStudentByAccount(account);		
			if(studentService.getStudentLogin(student)) {
				StudentCourseManage studentCourService = new StudentCourseManage();
				List<StudentCourse> studentcourseList = studentCourService.getCourseByStudent(student.getS_Account());
				
				NoticeManage noticeService = new NoticeManage();
				List<Notice> notice = new ArrayList<Notice>();
				for(int i = 0; i < studentcourseList.size(); i++) {
					List<Notice> notice1 = noticeService.getStudentNotice(studentcourseList.get(i).getSc_cour_ID());
					notice.addAll(notice1);
				}
				
				WorkManage workService = new WorkManage();
				List<Work> work = new ArrayList<Work>();
				for(int i = 0; i < studentcourseList.size(); i++) {
					List<Work> work1 = workService.getStudentWork(studentcourseList.get(i).getSc_cour_ID());
					work.addAll(work1);
				}
				
				boolean[] status = new boolean[work.size()];
				for (int n = 0; n < status.length; n++) {
					status[n] = studentService.getCheckWorkStatus(work.get(n).getW_Num());
					//System.out.println(status[n]);
				}
				session.setAttribute("status", status);
				session.setAttribute("student", student);
				session.setAttribute("notice",notice);
				session.setAttribute("work",work);
				response.sendRedirect("/HomeWorkManageSystem/student/student.jsp");
			} else{
				out.print("<script language='javascript'>alert('用户名或密码错误！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
				
		}
		else if (type.equals("Assistant")) {
			Assistant assistant = new Assistant();
			AssistantManage assistantService = new AssistantManage();
			assistant.setA_Account(account);
			assistant.setA_Password(password);
			if(assistantService.getAssistantLogin(assistant)){
				assistant = assistantService.getAssistantByAccount(account);
				InvitationManage invitationService = new InvitationManage();
				List<Invitation> invitationList = new ArrayList<Invitation>();
				invitationList = invitationService.getInvitationByAccountN(assistant.getA_Account());
				
				List<Course> course = new ArrayList<Course>();
				CourseManage courseService = new CourseManage();
				for(int i = 0; i < invitationList.size(); i++) {
					Course course1 = new Course();
					course1 = courseService.getCourseByCourseID(invitationList.get(i).getIn_cour_ID());
					course.add(course1);
				}
				session.setAttribute("course", course);
				session.setAttribute("assistant", assistant);
				
				response.sendRedirect("/HomeWorkManageSystem/assistant/assistant.jsp");
			} else{
				out.print("<script language='javascript'>alert('用户名或密码错误！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
		}
		else if (type.equals("Teacher")) {
			Teacher teacher = new Teacher();
			TeacherManage teacherService = new TeacherManage();
			teacher.setT_Account(account);
			teacher.setT_Password(password);
			if(teacherService.getTeacherLogin(teacher)){
				WorkManage workService = new WorkManage();
				List<Work> work = new ArrayList<Work>();
				work = workService.getTeacherWork(teacher.getT_Account());
				teacher = teacherService.getTeacherByAccount(account);
				session.setAttribute("work", work);
				session.setAttribute("teacher", teacher);
				response.sendRedirect("/HomeWorkManageSystem/teacher/teacher.jsp");
			}
			else{
				out.print("<script language='javascript'>alert('用户名或密码错误！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
		}
		else if(type.equals("Manage")){
			Manage manage = new Manage();
			AdminManage adminService = new AdminManage();
			manage.setM_Account(account);
			manage.setM_Password(password);
			if(adminService.getManageLogin(manage)){
				TeacherManage teacherService = new TeacherManage();
				List<Teacher> teacher = new ArrayList<Teacher>();
				teacher = teacherService.getAllTeacher();
				manage = adminService.getManageByAccount(account);
				session.setAttribute("teacher", teacher);
				session.setAttribute("manage", manage);
				response.sendRedirect("/HomeWorkManageSystem/manage/manager.jsp");
			}
			else{
				out.print("<script language='javascript'>alert('用户名或密码错误！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
		}
		else {
			out.print("<script language='javascript'>alert('请输入用户名或密码！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
		}
	}
}