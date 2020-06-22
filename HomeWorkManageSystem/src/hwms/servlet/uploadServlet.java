package hwms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import hwms.entity.CheckWork;
import hwms.entity.Student;
import hwms.entity.Work;
import hwms.service.CheckWorkManage;
import hwms.service.WorkManage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadServlet() {
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
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		request.setCharacterEncoding("utf-8");
		// 文件名中文乱码处理也可以如此写
		// upload.setHeaderEncoding("utf-8");

		// 设置缓冲区大小与临时文件目录
		factory.setSizeThreshold(1024 * 1024 * 10);
		File uploadTemp = new File("E:\\uploadTemp");
		uploadTemp.mkdirs();
		factory.setRepository(uploadTemp);

		// 设置单个文件大小限制
		upload.setFileSizeMax(1024 * 1024 * 10);
		// 设置所有文件总和大小限制
		upload.setSizeMax(1024 * 1024 * 30);

		try {
			List<FileItem> list = upload.parseRequest(request);
			System.out.println(list);
			String name = null;
			String w_title = null;
			
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					name = fileItem.getFieldName();
					w_title = fileItem.getString("utf-8");
					System.out.println(name + ": " + w_title);
				} else {
					String filName = fileItem.getName();
					filName = filName.substring(filName.lastIndexOf(File.separator) + 1);
					System.out.println(filName);

					// 利用时间戳作为文件名避免重复
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
					Date date = new Date();
					String time = simpleDateFormat.format(date);
					String Uptime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//（插入数据库）

					// 获取文件上传目录路径，在项目部署路径下的upload目录里。若想让浏览器不能直接访问到图片，可以放在WEB-INF下
					String uploadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");

					HttpSession session = request.getSession(); // 获取session
					Student student = (Student)session.getAttribute("student");
					WorkManage workService = new WorkManage();
					Work work = workService.getWorkByWtitle(w_title);
					
					CheckWorkManage checkworkService = new CheckWorkManage(); 
					if(checkworkService.getCheckWorkBySnoWno(student.getS_Account(), work.getW_Num()) != null)
					{
						CheckWork checkwork = new CheckWork();
						checkwork = checkworkService.getCheckWorkBySnoWno(student.getS_Account(), work.getW_Num());
						if((checkwork.getCh_Score() != 0) && (checkwork.getCh_Mark() != null) && (checkwork.getCh_CheckTime() != null))
						{
							session.setAttribute("flag", 2);
						}else {
							String fileName = student.getS_Account() + "_" + time + "_" + filName;
							if(checkworkService.updateCheckWork(Uptime, fileName, student.getS_Account(), work.getW_cour_ID(), work.getW_Num())) {
								File file = new File(uploadPath);
								file.mkdirs();
								fileItem.write(new File(uploadPath, fileName));
							}
							session.setAttribute("flag", 3);
						}
					} else {
						String fileName = student.getS_Account() + "_" + time + "_" + filName;
						if(checkworkService.updateCheckWork(Uptime, fileName, student.getS_Account(), work.getW_cour_ID(), work.getW_Num()))
						{
							File file = new File(uploadPath);
							file.mkdirs();
							// 写入文件到磁盘，该行执行完毕后，若有该临时文件，将会自动删除
							fileItem.write(new File(uploadPath, fileName));
						}
						session.setAttribute("flag", 1);
					}
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/HomeWorkManageSystem/student/s_uploadwork.jsp");
	}
}
