package hwms.servlet;

import java.io.File;
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

import hwms.entity.Assistant;
import hwms.entity.Course;
import hwms.entity.Invitation;
import hwms.entity.Manage;
import hwms.entity.Student;
import hwms.entity.StudentCourse;
import hwms.entity.Teacher;
import hwms.service.AdminManage;
import hwms.service.AssistantManage;
import hwms.service.CourseManage;
import hwms.service.InvitationManage;
import hwms.service.StudentCourseManage;
import hwms.service.StudentManage;
import hwms.service.TeacherManage;
import jxl.Cell;
import jxl.Workbook;


/**
 * Servlet implementation class manageServlet
 */
@WebServlet("/manageServlet")
public class manageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageServlet() {
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
		String methodName = request.getParameter("funct");
		System.out.println(methodName);

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
	
	//获取全部教师
	protected void allT(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("allT");
		
		HttpSession session = request.getSession(); // 获取session
		TeacherManage teacherService = new TeacherManage();
		List<Teacher> teacher = new ArrayList<Teacher>();
		teacher = teacherService.getAllTeacher();
		session.setAttribute("teacher", teacher);
		
	}
	
	//获取全部助教
	protected void allA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		AssistantManage assistantService = new AssistantManage();
		List<Assistant> assistant = new ArrayList<Assistant>();
		assistant = assistantService.getAllAssistant();
		session.setAttribute("assistant", assistant);
		
	}
	
	//获取全部学生
	protected void allS(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		StudentManage studentService = new StudentManage();
		List<Student> student = new ArrayList<Student>();
		student = studentService.getAllStudent();
		session.setAttribute("student", student);
		
	}
	
	//获取全部课程
	protected void allC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		CourseManage courseService = new CourseManage();
		List<Course> course = new ArrayList<Course>();
		course = courseService.getAllCourse();
		session.setAttribute("course", course);
		
	}
	
	//获取全部学生选课列表
	protected void allSC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		StudentCourseManage studentcourseService = new StudentCourseManage();
		List<StudentCourse> studentcourse = new ArrayList<StudentCourse>();
		studentcourse = studentcourseService.getAllStudentCourse();
		session.setAttribute("studentcourse", studentcourse);
		
	}
	
	// 获取全部邀请列表
	protected void allI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		HttpSession session = request.getSession(); // 获取session
		InvitationManage studentcourseService = new InvitationManage();
		List<Invitation> invitation = new ArrayList<Invitation>();
		invitation = studentcourseService.getInvitation();
		session.setAttribute("invitation", invitation);
	}
	
	//添加教师
	protected void addT(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		String account = request.getParameter("t_account");
		String name = request.getParameter("t_name");
		String password = request.getParameter("t_password");
		Teacher teacher = new Teacher();
		teacher.setT_Account(account);
		teacher.setT_Name(name);
		teacher.setT_Password(password);
		
		PrintWriter out = response.getWriter();
		TeacherManage teacherService = new TeacherManage();
		if(teacherService.addTeacher(teacher)) {
			allT(request,response);
			out.print(
					"<script language='javascript'>alert('添加成功！');window.location = '/HomeWorkManageSystem/manage/m_teachermanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('添加失败！');window.location = '/HomeWorkManageSystem/manage/m_teachermanage.jsp';</script>");
		}
		
	}
	
	//添加助教
	protected void addA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		String account = request.getParameter("a_account");
		String name = request.getParameter("a_name");
		String password = request.getParameter("a_password");
				
		Assistant assistant = new Assistant();
		assistant.setA_Account(account);
		assistant.setA_Name(name);
		assistant.setA_Password(password);
		
		PrintWriter out = response.getWriter();
		AssistantManage assistantService = new AssistantManage();
		if(assistantService.addAssistant(assistant)) {
			allA(request,response);
			out.print(
					"<script language='javascript'>alert('添加成功！');window.location = '/HomeWorkManageSystem/manage/m_assistantmanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('添加失败！');window.location = '/HomeWorkManageSystem/manage/m_assistantmanage.jsp';</script>");
		}
	}
	
	//添加学生
	protected void addS(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		String account = request.getParameter("s_account");
		String clazz = request.getParameter("s_clazz");
		String name = request.getParameter("s_name");
		String password = request.getParameter("s_password");
				
		Student student = new Student();

		student.setS_Account(account);
		student.setS_Class(clazz);
		student.setS_Name(name);
		student.setS_Password(password);
		
		PrintWriter out = response.getWriter();
		StudentManage studentService = new StudentManage();
		if(studentService.addStudent(student)) {
			allS(request,response);
			out.print(
					"<script language='javascript'>alert('添加成功！');window.location = '/HomeWorkManageSystem/manage/m_studentmanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('添加失败！');window.location = '/HomeWorkManageSystem/manage/m_studentmanage.jsp';</script>");
		}
	}
	
	//添加课程
	protected void addC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		String name = request.getParameter("cour_name");
		int credit = Integer.parseInt(request.getParameter("cour_credit"));
		String id = request.getParameter("cour_id");
		int count = Integer.parseInt(request.getParameter("cour_count"));
		String t_id = request.getParameter("cour_t_id");
				
		Course course = new Course();
		course.setCour_Name(name);
		course.setCour_Credit(credit);
		course.setCour_ID(id);
		course.setCour_Count(count);
		course.setCour_t_ID(t_id);
	
		PrintWriter out = response.getWriter();
		CourseManage courseService = new CourseManage();
		if(courseService.addCourse(course)) {
			allC(request,response);
			out.print(
					"<script language='javascript'>alert('添加成功！');window.location = '/HomeWorkManageSystem/manage/m_coursemanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('添加失败！');window.location = '/HomeWorkManageSystem/manage/m_coursemanage.jsp';</script>");
		}
	}
	
	// 添加学生选课
	protected void addSC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		String s_account = request.getParameter("sc_s_account");
		String cour_id = request.getParameter("sc_cour_id");


		StudentCourse studentcourse = new StudentCourse();
		studentcourse.setSc_s_Account(s_account);
		studentcourse.setSc_cour_ID(cour_id);

		PrintWriter out = response.getWriter();
		StudentCourseManage studentcourseService = new StudentCourseManage();
		if (studentcourseService.addStudentCourse(studentcourse)) {
			allSC(request, response);
			out.print(
					"<script language='javascript'>alert('添加成功！');window.location = '/HomeWorkManageSystem/manage/m_studentcoursemanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('添加失败！');window.location = '/HomeWorkManageSystem/manage/m_studentcoursemanage.jsp';</script>");
		}
	}
	
	// 添加邀请
	protected void addI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		String a_account = request.getParameter("in_a_account");
		String cour_id = request.getParameter("in_cour_id");
		String accept = request.getParameter("in_accept");

		Invitation invitation = new Invitation();
		invitation.setIn_a_Account(a_account);
		invitation.setIn_cour_ID(cour_id);
		invitation.setIn_Accept(accept);

		PrintWriter out = response.getWriter();
		InvitationManage invitationService = new InvitationManage();
		if (invitationService.addInvitation(invitation)) {
			allI(request, response);
			out.print(
					"<script language='javascript'>alert('添加成功！');window.location = '/HomeWorkManageSystem/manage/m_invitationmanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('添加失败！');window.location = '/HomeWorkManageSystem/manage/m_invitationmanage.jsp';</script>");
		}
	}
	
	
	// 导入教师列表
	protected void addTeacherExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("addTeacherExcel");
			
		//System.out.println(this.getServletContext().getRealPath ("/"));  
		try {

			Workbook wb = Workbook
					.getWorkbook(new File(this.getServletContext().getRealPath("/") + "WEB-INF/teacher.xls"));

			for (int i = 1; i < wb.getSheet(0).getRows(); i++) {

				Cell[] cell = wb.getSheet(0).getRow(i);
				Teacher teacher = new Teacher();
				teacher.setT_Account(cell[0].getContents());
				teacher.setT_Name(cell[1].getContents());
				teacher.setT_Password(cell[2].getContents());

				TeacherManage teacherService = new TeacherManage();
				teacherService.addTeacher(teacher);
			}
			wb.close();
			allT(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 导入助教列表
	protected void addAssistantExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("addAssistantExcel");
		
		//System.out.println(this.getServletContext().getRealPath ("/")); 
		 
		try {

			Workbook wb = Workbook
					.getWorkbook(new File(this.getServletContext().getRealPath("/") + "WEB-INF/assistant.xls"));

			for (int i = 1; i < wb.getSheet(0).getRows(); i++) {

				Cell[] cell = wb.getSheet(0).getRow(i);
				Assistant assistant = new Assistant();
				assistant.setA_Account(cell[0].getContents());
				assistant.setA_Name(cell[1].getContents());
				assistant.setA_Password(cell[2].getContents());
						
				AssistantManage assistantService = new AssistantManage();
				assistantService.addAssistant(assistant);
			}
			wb.close();
			allA(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 导入学生列表
	protected void addStudentExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("addStduentExcel");
		
		try {

			Workbook wb = Workbook
					.getWorkbook(new File(this.getServletContext().getRealPath("/") + "WEB-INF/student.xls"));

			for (int i = 1; i < wb.getSheet(0).getRows(); i++) {

				Cell[] cell = wb.getSheet(0).getRow(i);
				Student student = new Student();
				student.setS_Account(cell[0].getContents());
				student.setS_Name(cell[1].getContents());
				student.setS_Class(cell[2].getContents());
				student.setS_Password(cell[3].getContents());

				StudentManage studentService = new StudentManage();
				studentService.addStudent(student);
			}
			wb.close();
			allS(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 导入课程列表
	protected void addCourseExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("addCourseExcel");
		
		try {

			Workbook wb = Workbook
					.getWorkbook(new File(this.getServletContext().getRealPath("/") + "WEB-INF/course.xls"));

			for (int i = 1; i < wb.getSheet(0).getRows(); i++) {

				Cell[] cell = wb.getSheet(0).getRow(i);
				Course course = new Course();
				course.setCour_Name(cell[0].getContents());
				course.setCour_Credit(Integer.parseInt(cell[1].getContents()));
				course.setCour_ID(cell[2].getContents());
				course.setCour_Count(Integer.parseInt(cell[3].getContents()));
				course.setCour_t_ID(cell[4].getContents());

				CourseManage courseService = new CourseManage();
				courseService.addCourse(course);
			}
			wb.close();
			allC(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 导入课程列表
	protected void addStudentCourseExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("addStudentCourseExcel");

		try {

			Workbook wb = Workbook
					.getWorkbook(new File(this.getServletContext().getRealPath("/") + "WEB-INF/studentcourse.xls"));

			for (int i = 1; i < wb.getSheet(0).getRows(); i++) {

				Cell[] cell = wb.getSheet(0).getRow(i);
				StudentCourse studentcourse = new StudentCourse();
				studentcourse.setSc_s_Account(cell[0].getContents());
				studentcourse.setSc_cour_ID(cell[1].getContents());

				StudentCourseManage studentcourseService = new StudentCourseManage();
				studentcourseService.addStudentCourse(studentcourse);
			}
			wb.close();
			allSC(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//删除教师
	protected void delT(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String t_Account = request.getParameter("account");
		TeacherManage teacherService = new TeacherManage();
		if(teacherService.deleteTeacher(t_Account)) {
			allT(request,response);
			out.print(
					"<script language='javascript'>alert('刪除成功！');window.location.reload();</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('刪除失败！');window.location.reload();;</script>");
		}

	}
		
	// 删除助教
	protected void delA(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String a_Account = request.getParameter("account");
		AssistantManage assistantService = new AssistantManage();
		if(assistantService.deleteAssistant(a_Account)) {
			allA(request,response);
			out.print(
					"<script language='javascript'>alert('刪除成功！');window.location.reload();</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('刪除失败！');window.location.reload();</script>");
		}

	}
		
	// 删除学生
	protected void delS(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String s_Account = request.getParameter("account");
		StudentManage studentService = new StudentManage();
		if(studentService.deleteStudent(s_Account)) {
			allS(request,response);
			out.print(
					"<script language='javascript'>alert('刪除成功！');window.location.reload();</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('刪除失败！');window.location.reload();</script>");
		}
	}
		
	// 删除课程
	protected void delC(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String cour_num = request.getParameter("cour_num");
		CourseManage courseService = new CourseManage();
		if(courseService.deleteCourse(cour_num)) {
			allC(request,response);
			out.print(
					"<script language='javascript'>alert('刪除成功！');window.location.reload();</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('刪除失败！');window.location.reload();</script>");
		}
		
	}
	
	// 删除学生选课
	protected void delSC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		PrintWriter out = response.getWriter();

		String sc_num = request.getParameter("sc_num");
		StudentCourseManage studentcourseService = new StudentCourseManage();
		if (studentcourseService.deleteStudentCourse(sc_num)) {
			allSC(request, response);
			out.print("<script language='javascript'>alert('刪除成功！');window.location.reload();</script>");
		} else {
			out.print("<script language='javascript'>alert('刪除失败！');window.location.reload();</script>");
		}

	}
	
	// 删除邀请
	protected void delI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

		PrintWriter out = response.getWriter();

		String in_num = request.getParameter("in_num");
		InvitationManage invitationService = new InvitationManage();
		if (invitationService.delInvitation(in_num)) {
			allI(request, response);
			out.print("<script language='javascript'>alert('刪除成功！');window.location.reload();</script>");
		} else {
			out.print("<script language='javascript'>alert('刪除失败！');window.location.reload();</script>");
		}

	}
	
	// 获取修改教师信息
	protected void getUpdateT(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		HttpSession session = request.getSession(); // 获取session
		
		String account = request.getParameter("account");
		Teacher teacher = new Teacher();
		TeacherManage teacherService = new TeacherManage();
		teacher = teacherService.getTeacherByAccount(account);
		session.setAttribute("t", teacher);
	}
	
	// 获取修改助教信息
	protected void getUpdateA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		HttpSession session = request.getSession(); // 获取session

		String account = request.getParameter("account");
		Assistant assistant = new Assistant();
		AssistantManage assistantService = new AssistantManage();
		assistant = assistantService.getAssistantByAccount(account);
		session.setAttribute("a", assistant);
	}
		
	// 获取修改学生信息
	protected void getUpdateS(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		HttpSession session = request.getSession(); // 获取session

		String account = request.getParameter("account");
		Student student = new Student();
		StudentManage studentService = new StudentManage();
		student = studentService.getStudentByAccount(account);
		session.setAttribute("s", student);
	}
		
	// 获取修改课程信息
	protected void getUpdateC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		HttpSession session = request.getSession(); // 获取session

		String cour_num = request.getParameter("cour_num");
		Course course = new Course();
		CourseManage courseService = new CourseManage();
		course = courseService.getCourseByNum(cour_num);
		session.setAttribute("c", course);
	}
	
	
	// 修改教师信息
	protected void updateT(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("updateT");
		
		int num = Integer.parseInt(request.getParameter("t_num"));
		String account = request.getParameter("t_account");
		String name = request.getParameter("t_name");
		String password = request.getParameter("t_password");
		Teacher teacher = new Teacher();
		teacher.setT_Num(num);
		teacher.setT_Account(account);
		teacher.setT_Name(name);
		teacher.setT_Password(password);
		
		PrintWriter out = response.getWriter();
		TeacherManage teacherService = new TeacherManage();
		if(teacherService.updateTeacher(teacher)) {
			allT(request,response);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_teachermanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_teachermanage.jsp';</script>");
		}
		
	}

	// 修改助教信息
	protected void updateA(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("updateA");
		
		int num = Integer.parseInt(request.getParameter("a_num"));
		String account = request.getParameter("a_account");
		String name = request.getParameter("a_name");
		String password = request.getParameter("a_password");
				
		Assistant assistant = new Assistant();
		assistant.setA_Num(num);
		assistant.setA_Account(account);
		assistant.setA_Name(name);
		assistant.setA_Password(password);
		
		PrintWriter out = response.getWriter();
		AssistantManage assistantService = new AssistantManage();
		if(assistantService.updateAssistant(assistant)) {
			allA(request,response);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_assistantmanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_assistantmanage.jsp';</script>");
		}

	}

	// 修改学生信息
	protected void updateS(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		System.out.println("updateS");
		
		int num = Integer.parseInt(request.getParameter("s_num"));
		String account = request.getParameter("s_account");
		String clazz = request.getParameter("s_clazz");
		String name = request.getParameter("s_name");
		String password = request.getParameter("s_password");
				
		Student student = new Student();
		student.setS_Num(num);
		student.setS_Account(account);
		student.setS_Class(clazz);
		student.setS_Name(name);
		student.setS_Password(password);
		
		PrintWriter out = response.getWriter();
		StudentManage studentService = new StudentManage();
		if(studentService.updateStudent(student)) {
			allS(request,response);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_studentmanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_studentmanage.jsp';</script>");
		}
		
	}

	// 修改课程信息
	protected void updateC(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		System.out.println("updateC");
		
		int num = Integer.parseInt(request.getParameter("cour_num"));
		String name = request.getParameter("cour_name");
		int credit = Integer.parseInt(request.getParameter("cour_credit"));
		String id = request.getParameter("cour_id");
		int count = Integer.parseInt(request.getParameter("cour_count"));
		String t_id = request.getParameter("cour_t_id");
				
		Course course = new Course();
		course.setCour_Num(num);
		course.setCour_Name(name);
		course.setCour_Credit(credit);
		course.setCour_ID(id);
		course.setCour_Count(count);
		course.setCour_t_ID(t_id);
	
		PrintWriter out = response.getWriter();
		CourseManage courseService = new CourseManage();
		if(courseService.updateCourse(course)) {
			allC(request,response);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_coursemanage.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_coursemanage.jsp';</script>");
		}
		
	}
	
	
	//导入教师表
	protected void addTeacherList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

	}
	
	//导入助教表
	protected void addAssistantList(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

	}
	
	//导入学生表
	protected void addStudentList(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

	}
		
	//导入课程表
	protected void addCourseList(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");

	}
	
	
	
	//更改用户名
	protected void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Manage manage = new Manage();
		manage = (Manage) session.getAttribute("manage");
		
		String oldAccount = manage.getM_Account();
		String newAccount = request.getParameter("account");
		
		manage.setM_Account(newAccount);
		
		PrintWriter out = response.getWriter();
		AdminManage manageService = new AdminManage();
		
		
		if(manageService.updateManageAccount(newAccount, oldAccount)) {
			session.setAttribute("manage", manage);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
		}
		
		
	}
	
	//更改姓名
	protected void updateName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Manage manage = new Manage();
		manage = (Manage) session.getAttribute("manage");
		
		String newName = request.getParameter("name");
		manage.setM_Name(newName);
		
		PrintWriter out = response.getWriter();
		AdminManage manageService = new AdminManage();
		if(manageService.updateManageName(manage)) {
			session.setAttribute("manage", manage);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
		}
		
	}
	
	//更改密码
	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		HttpSession session = request.getSession(); // 获取session
		Manage manage = new Manage();
		manage = (Manage) session.getAttribute("manage");
		
		String m_password = request.getParameter("psw");
		String check_newpsw = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		//System.out.println(m_password);
		//System.out.println(check_newpsw);
		
		if (!m_password.equals(check_newpsw)) {
			// System.out.println("不一致");
			out.print(
					"<script language='javascript'>alert('密码不一致！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
			return;
		}
		manage.setM_Password(m_password);
		AdminManage manageService = new AdminManage();
		if(manageService.updateManagePassword(manage)) {
			session.setAttribute("manage", manage);
			out.print(
					"<script language='javascript'>alert('修改成功！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
		} else {
			out.print(
					"<script language='javascript'>alert('修改失败！');window.location = '/HomeWorkManageSystem/manage/m_personalseting.jsp';</script>");
		}
		
	}
}
