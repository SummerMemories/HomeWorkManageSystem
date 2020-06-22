package hwms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwms.entity.Assistant;
import hwms.entity.Student;
import hwms.entity.Teacher;
import hwms.service.AssistantManage;
import hwms.service.StudentManage;
import hwms.service.TeacherManage;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password1");
		String type = request.getParameter("type");
		
		System.out.println(account);
		System.out.println(password);
		System.out.println(type);
		
		//Date curTime = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//String regtime = sdf.format(curTime);
		
		PrintWriter out = response.getWriter();
		
		if(type.equals("学生"))
		{
			Student student = new Student();
			student.setS_Account(account);
			student.setS_Password(password);

			StudentManage studentService = new StudentManage();
			if(studentService.addStudent(student)){
				out.print("<script language='javascript'>alert('注册成功！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
			else{
				out.print("<script language='javascript'>alert('注册失败，用户名已注册！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
		}
		else if(type.equals("助教"))
		{
			Assistant assistant = new Assistant();
			assistant.setA_Account(account);
			assistant.setA_Password(password);

			AssistantManage assistantService = new AssistantManage();	
			if(assistantService.getAssistantLogin(assistant)){
				out.print("<script language='javascript'>alert('注册成功！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
			else{
				out.print("<script language='javascript'>alert('注册失败，用户名已注册！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
		}
		else if(type.equals("教师")) {
			Teacher teacher = new Teacher();
			teacher.setT_Account(account);
			teacher.setT_Password(password);

			TeacherManage teacherService = new TeacherManage();
			if(teacherService.getTeacherLogin(teacher)){
				out.print("<script language='javascript'>alert('注册成功！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
			else{
				out.print("<script language='javascript'>alert('注册失败，用户名已注册！');window.location = '/HomeWorkManageSystem/login/Login.html';</script>");
			}
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
}
