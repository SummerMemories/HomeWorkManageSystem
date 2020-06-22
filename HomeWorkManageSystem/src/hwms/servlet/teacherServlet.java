package hwms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLEncoder;
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
import hwms.entity.Notice;
import hwms.entity.Student;
import hwms.entity.StudentCourse;
import hwms.entity.Teacher;
import hwms.entity.Work;
import hwms.service.AssistantManage;
import hwms.service.CheckWorkManage;
import hwms.service.CourseManage;
import hwms.service.InvitationManage;
import hwms.service.NoticeManage;
import hwms.service.StudentCourseManage;
import hwms.service.StudentManage;
import hwms.service.TeacherManage;
import hwms.service.WorkManage;

/**
 * Servlet implementation class teacherServlet
 */
@WebServlet("/teacherServlet")
public class teacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String methodName = null;
	int num = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		// 截取其中的方法名
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
	
	
	protected void delWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		num = Integer.parseInt(request.getParameter("num"));
		
		WorkManage workService = new WorkManage();
		PrintWriter out = response.getWriter();
		if(workService.deleteWork(num)) {
			//删除审核表
			CheckWorkManage checkworkService = new CheckWorkManage();
			checkworkService.deleteCheckWork(String.valueOf(num));
			t_allworks(request,response);
			out.print("<script language='javascript'>alert('删除成功');window.location = '/HomeWorkManageSystem/teacher/t_allworks.jsp';</script>");
		}else {
			out.print("<script language='javascript'>alert('删除失败');window.location = '/HomeWorkManageSystem/teacher/t_allworks.jsp';</script>");
		}
	}
	
	protected void delNotice(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		num = Integer.parseInt(request.getParameter("num"));
		//System.out.println("delNotice");
		
		NoticeManage noticeService = new NoticeManage();
		PrintWriter out = response.getWriter();
		if(noticeService.deleteNotice(num)) {
			t_allnotice(request,response);
			out.print("<script language='javascript'>alert('删除成功');window.location = '/HomeWorkManageSystem/teacher/t_allnotice.jsp';</script>");
		}else {
			out.print("<script language='javascript'>alert('删除失败');window.location = '/HomeWorkManageSystem/teacher/t_allnotice.jsp';</script>");
		}
	}
	
	protected void delInvitation(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		num = Integer.parseInt(request.getParameter("num"));
		
		InvitationManage invitationService = new InvitationManage();
		PrintWriter out = response.getWriter();
		if(invitationService.delInvitation(String.valueOf(num))) {
			t_allinvitation(request,response);
			out.print("<script language='javascript'>alert('删除成功');window.location.reload();</script>");
		}else {
			out.print("<script language='javascript'>alert('删除失败');window.location.reload();</script>");
		}
	}
	
	
	//全部作业页面
	protected void t_allworks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("t_allworks");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		WorkManage workService = new WorkManage();
		Teacher teacher = new Teacher();
		HttpSession session = request.getSession(); // 获取session
		teacher = (Teacher)session.getAttribute("teacher");
		List<Work> work = workService.getTeacherWork(teacher.getT_Account());
		session.setAttribute("work", work);
	}
	//全部公告页面
	protected void t_allnotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("t_allnotice");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		NoticeManage noticeService = new NoticeManage();
		Teacher teacher = new Teacher();
		HttpSession session = request.getSession(); // 获取session
		teacher = (Teacher)session.getAttribute("teacher");
		List<Notice> notice = noticeService.getTeacherNotice(teacher.getT_Account());
		/*
		 * for(int i = 0; i < notice.size(); i++) {
		 * System.out.println(notice.get(i).getNt_Title()); }
		 */
		session.setAttribute("n", notice);
	}
	
	
	//发布公告页面
	protected void t_publishnotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("t_allnotice");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Teacher teacher = new Teacher();
		teacher = (Teacher)session.getAttribute("teacher");
		CourseManage courseService = new CourseManage();
		List<Course> clazz = courseService.getCourseByTeacher(teacher.getT_Account());
		session.setAttribute("clazz", clazz);
	}
	//发布作业页面
	protected void t_publishwork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("t_allnotice");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Teacher teacher = new Teacher();
		teacher = (Teacher)session.getAttribute("teacher");
		CourseManage courseService = new CourseManage();
		List<Course> course = courseService.getCourseByTeacher(teacher.getT_Account());
		session.setAttribute("course", course);
	}
	
	
	//发布公告
	protected void publishNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("t_allnotice");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("publishNotice");
		
		Teacher teacher = new Teacher();
		HttpSession session = request.getSession(); // 获取session
		teacher = (Teacher)session.getAttribute("teacher");
		NoticeManage noticeService = new NoticeManage();
		Notice notice = new Notice();
		notice.setNt_Title(request.getParameter("nt_title"));
		notice.setNt_Content(request.getParameter("nt_content"));
		notice.setNt_Time(request.getParameter("nt_time"));
		notice.setNt_t_ID(teacher.getT_Account());
		notice.setNt_cour_ID(request.getParameter("nt_cour_id"));
		notice.setNt_t_Name(teacher.getT_Name());
		PrintWriter out = response.getWriter();
		if(noticeService.addNotice(notice)) {
			out.print("<script language='javascript'>alert('发布成功');window.location = '/HomeWorkManageSystem/teacher/t_publishnotice.jsp';</script>");
		} else {
			out.print("<script language='javascript'>alert('发布失败');window.location = '/HomeWorkManageSystem/teacher/t_publishnotice.jsp';</script>");
		}
	}
	//发布作业
	protected void publishWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("publishWork");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		

		CourseManage courseService = new CourseManage();
		Course course = new Course();
		course = courseService.getCourseNameByCourseID(request.getParameter("w_cour_id"));
		
		System.out.println("publishWork");
		HttpSession session = request.getSession(); // 获取session
		Teacher teacher = new Teacher();
		teacher = (Teacher)session.getAttribute("teacher");
		WorkManage workService = new WorkManage();
		Work work = new Work();
		work.setW_Title(request.getParameter("w_title"));
		work.setW_Content(request.getParameter("w_content"));
		work.setW_Deadline(request.getParameter("w_deadline"));
		work.setW_Course(course.getCour_Name());
		work.setW_t_ID(teacher.getT_Account());
		work.setW_cour_ID(request.getParameter("w_cour_id"));
		work.setW_t_Name(teacher.getT_Name());
		
		PrintWriter out = response.getWriter();
		if(workService.addWork(work)) {
			//为各个学生创建作业审核
			StudentCourseManage studentcourseService = new StudentCourseManage();
			List<StudentCourse> studentcourseList = new ArrayList<StudentCourse>();
			studentcourseList = studentcourseService.getStudentByCourse(request.getParameter("w_cour_id"));
			
			Work work1 = new Work();
			work1 = workService.getWorkByWtitle(request.getParameter("w_title"));
			
			CheckWorkManage checkworkService = new CheckWorkManage();
			for(int i = 0 ; i < studentcourseList.size(); i++) {
				checkworkService.addCheckWork(studentcourseList.get(i).getSc_s_Account(),
						work1.getW_cour_ID(), work1.getW_Num(), 0);
			}
				
			out.print("<script language='javascript'>alert('发布成功');window.location = '/HomeWorkManageSystem/teacher/t_publishwork.jsp';</script>");
		} else {
			out.print("<script language='javascript'>alert('发布成功');window.location = '/HomeWorkManageSystem/teacher/t_publishwork.jsp';</script>");
		}
	}
	
	protected void t_allinvitation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		HttpSession session = request.getSession();
		
		Teacher teacher = new Teacher();
		teacher = (Teacher) session.getAttribute("teacher");
		
		CourseManage courseService = new CourseManage();
		List<Course> courseList = new ArrayList<Course>();
		courseList = courseService.getCourseByTeacher(teacher.getT_Account());
		
		InvitationManage invitationService = new InvitationManage();
		List<Invitation> invitation = new ArrayList<Invitation>();
		for(int i = 0; i < courseList.size(); i++) {
			List<Invitation> invitation1 = new ArrayList<Invitation>();
			invitation1 = invitationService.getInvitationByCourID(courseList.get(i).getCour_ID());
			invitation.addAll(invitation1);
		}
		session.setAttribute("invitation", invitation);
		
		List<Assistant> assistant = new ArrayList<Assistant>();
		AssistantManage assistantService = new AssistantManage();
		for(int i = 0; i < invitation.size(); i++) {
			assistant.add(assistantService.getAssistantByAccount(invitation.get(i).getIn_a_Account()));
		}
		session.setAttribute("assistant", assistant);
	}
	
	protected void t_publishinvitation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		HttpSession session = request.getSession();
		
		Teacher teacher = new Teacher();
		teacher = (Teacher) session.getAttribute("teacher");
		CourseManage courseService = new CourseManage();
		List<Course> course = new ArrayList<Course>();
		course = courseService.getCourseByTeacher(teacher.getT_Account());
		
		List<Assistant> assistant = new ArrayList<Assistant>();
		AssistantManage assistantService = new AssistantManage();
		assistant = assistantService.getAllAssistant();
		
		session.setAttribute("course", course);
		session.setAttribute("assistant", assistant);
	}
	
	protected void publishinvitation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("publishinvitation");
		
	
		InvitationManage invitationService = new InvitationManage();
		Invitation invitation = new Invitation();
		invitation.setIn_cour_ID(request.getParameter("in_cour_id"));
		invitation.setIn_a_Account(request.getParameter("in_a_Account"));
		invitation.setIn_Accept("");
		PrintWriter out = response.getWriter();
		if(invitationService.addInvitation(invitation)) {
			t_allinvitation(request,response);
			out.print("<script language='javascript'>alert('邀请发送成功');window.location = '/HomeWorkManageSystem/teacher/t_allinvitation.jsp';</script>");
		} else {
			out.print("<script language='javascript'>alert('邀请发送失败');window.location = '/HomeWorkManageSystem/teacher/t_allinvitation.jsp';</script>");
		}
	}
	
	protected void updateName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession(); // 获取session
		PrintWriter out = response.getWriter();

		if (session.getAttribute("teacher") != null) {
			Teacher teacher = new Teacher();
			teacher = (Teacher) session.getAttribute("teacher");
			String newName = request.getParameter("name");
			teacher.setT_Name(newName);
			TeacherManage teacherService = new TeacherManage();
			if (teacherService.updateTeacherName(teacher)) {
				session.setAttribute("teacher", teacher);
				out.print(
						"<script language='javascript'>alert('修改成功！');parent.location.reload();</script>");
			} else {
				out.print("fail");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/teacher/s_personalseting.jsp';</script>");
		}
	}
	
	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		String s_password = request.getParameter("psw");
		String check_newpsw = request.getParameter("password");
		PrintWriter out = response.getWriter();

		if (!s_password.equals(check_newpsw)) {
			// System.out.println("不一致");
			out.print(
					"<script language='javascript'>alert('密码不一致！');window.location = '/HomeWorkManageSystem/teacher/t_personalseting.jsp';</script>");
			return;
		}

		HttpSession session = request.getSession(); // 获取session
		if (session.getAttribute("teacher") != null) {
			Teacher teacher = new Teacher();
			teacher = (Teacher) session.getAttribute("teacher");
			teacher.setT_Password(s_password);
			TeacherManage teacherService = new TeacherManage();
			if (teacherService.updateTeacherPassword(teacher)) {
				session.setAttribute("teacher", teacher);
				out.print(
					"<script language='javascript'>alert('修改成功！');parent.location.reload();</script>");
			} else {
				out.print("fail");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/teacher/t_personalseting.jsp';</script>");
		}
	}
	
	protected void t_statistics(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("t_statistics");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Teacher teacher = new Teacher();
		teacher = (Teacher)session.getAttribute("teacher");
		
		//获取教师的课程列表
		CourseManage courseService = new CourseManage();
		List<Course> courseList = new ArrayList<Course>();
		courseList = courseService.getCourseByTeacher(teacher.getT_Account());
		List<String> clazz = new ArrayList<String>();
		for(int i = 0; i < courseList.size(); i++) {
			clazz.add(courseList.get(i).getCour_ID());
		}
		session.setAttribute("c", clazz);
		
		//获取作业列表
		WorkManage workService = new WorkManage();
		List<Work> workList = new ArrayList<Work>();
		workList = workService.getTeacherWork(teacher.getT_Account());
		session.setAttribute("w", workList);
	}
	
	protected void t_checkwork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("t_checkwork");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
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
	
	protected void correctWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String ch_checkTime = dateFormat.format(date);
		CheckWorkManage checkworkService = new CheckWorkManage();
		PrintWriter out = response.getWriter();
		if(checkworkService.updateCheckWorkByTeacher(ch_num, ch_score, ch_mark, ch_checkTime)) {
			out.print(
					"<script language='javascript'>alert('批阅成功！');window.location = '/HomeWorkManageSystem/teacher/t_checkwork.jsp';</script>");
		}else {
			out.print(
					"<script language='javascript'>alert('批阅失败！');window.location = '/HomeWorkManageSystem/teacher/t_checkwork.jsp';</script>");
		}
	}
}
