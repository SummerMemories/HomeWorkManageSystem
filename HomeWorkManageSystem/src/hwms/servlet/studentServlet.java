package hwms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hwms.entity.Notice;
import hwms.entity.Student;
import hwms.entity.StudentCourse;
import hwms.entity.Work;
import hwms.service.NoticeManage;
import hwms.service.StudentCourseManage;
import hwms.service.StudentManage;
import hwms.service.WorkManage;
import hwms.entity.CheckWork;

/**
 * Servlet implementation class studentServlet
 */
@WebServlet("/studentServlet")
public class studentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public studentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String methodName = request.getParameter("funct");
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

	
	private void s_home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession(); // 获取session
		Student student = new Student();
		if (session.getAttribute("student") != null) {

			// System.out.println("NotNull");
			student = (Student) session.getAttribute("student");
			
			//得到学生与课程的对应关系
			StudentCourseManage studentCourService = new StudentCourseManage();
			List<StudentCourse> studentcourseList = studentCourService.getCourseByStudent(student.getS_Account());
			
			//得到公告列表
			NoticeManage noticeService = new NoticeManage();
			List<Notice> notice = new ArrayList<Notice>();
			for(int i = 0; i < studentcourseList.size(); i++) {
				List<Notice> notice1 = noticeService.getStudentNotice(studentcourseList.get(i).getSc_cour_ID());
				notice.addAll(notice1);
			}
			//得到作业列表
			WorkManage workService = new WorkManage();
			List<Work> work = new ArrayList<Work>();
			for(int i = 0; i < studentcourseList.size(); i++) {
				List<Work> work1 = workService.getStudentWork(studentcourseList.get(i).getSc_cour_ID());
				work.addAll(work1);
			}
			
			StudentManage studentService = new StudentManage();		
			boolean[] status = new boolean[work.size()];
			for (int n = 0; n < status.length; n++) {
				status[n] = studentService.getCheckWorkStatus(work.get(n).getW_Num());
				//System.out.println(status[n]);
			}
			session.setAttribute("status", status);
			session.setAttribute("notice", notice);
			session.setAttribute("work", work);
		} else {
			System.out.println("Null");
		}
	}

	protected void s_personalseting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("s_personalseting");
	}

	protected void s_showcheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession(); // 获取session
		Student student = new Student();
		student = (Student) session.getAttribute("student");

		StudentManage studentService = new StudentManage();
		List<CheckWork> checkwork = studentService.getStudentCheckWork(student.getS_Account());
		for(int i = 0; i < checkwork.size(); i++) {
			if(checkwork.get(i).getCh_UpTime() == null) {
				checkwork.remove(i);
			}
		}

		List<Work> work = new ArrayList<Work>(checkwork.size());
		for (int n = 0; n < checkwork.size(); n++) {
			work.add(n, studentService.getWorkBycheckwork(checkwork.get(n).getCh_w_Num()));
		}
		session.setAttribute("work", work);
		session.setAttribute("checkwork", checkwork);

		System.out.println("s_showcheck");
	}

	protected void s_uploadwork_jsp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession();

		Student student = new Student();
		StudentManage studentService = new StudentManage();
		student = (Student) session.getAttribute("student");
		System.out.println("上传页面的学号:" + student.getS_Account());

		//得到学生与课程的对应关系
		StudentCourseManage studentCourService = new StudentCourseManage();
		List<StudentCourse> studentcourseList = studentCourService.getCourseByStudent(student.getS_Account());
		
		//得到作业列表
		List<Work> work = new ArrayList<Work>();
		for(int i = 0; i < studentcourseList.size(); i++) {
			List<Work> work1 = studentService.getStudentWork(studentcourseList.get(i).getSc_cour_ID());
			work.addAll(work1);
		}
			
		String[] w_title = new String[work.size()];
		for (int m = 0; m < w_title.length; m++) {
			w_title[m] = work.get(m).getW_Title();
			System.out.println("批改表中学生--对应的作业主题：" + w_title[m]);
		}
		session.setAttribute("w_title", w_title);
		session.setAttribute("flag", 0);

		System.out.println("s_uploadwork_jsp");
	}

	protected void updateName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession(); // 获取session
		PrintWriter out = response.getWriter();

		if (session.getAttribute("student") != null) {
			Student student = new Student();
			student = (Student) session.getAttribute("student");
			String name = request.getParameter("s_name");
			student.setS_Name(name);
			StudentManage studentService = new StudentManage();
			if (studentService.updateStudentName(student)) {
				session.setAttribute("student", student);
				response.sendRedirect("/HomeWorkManageSystem/student/s_personalseting.jsp");
			} else {
				out.print("fail");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/student/s_personalseting.jsp';</script>");
		}
	}

	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		String s_password = request.getParameter("s_password");
		String check_newpsw = request.getParameter("check_newpsw");
		PrintWriter out = response.getWriter();

		if (!s_password.equals(check_newpsw)) {
			// System.out.println("不一致");
			out.print(
					"<script language='javascript'>alert('密码不一致！');window.location = '/HomeWorkManageSystem/student/s_personalseting.jsp';</script>");
			return;
		}

		HttpSession session = request.getSession(); // 获取session
		if (session.getAttribute("student") != null) {
			Student student = new Student();
			student = (Student) session.getAttribute("student");
			student.setS_Password(s_password);
			StudentManage studentService = new StudentManage();
			if (studentService.updateStudentPassword(student)) {
				session.setAttribute("student", student);
				response.sendRedirect("/HomeWorkManageSystem/student/s_personalseting.jsp");
			} else {
				out.print("fail");
			}
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/student/s_personalseting.jsp';</script>");
		}
	}
	
}
