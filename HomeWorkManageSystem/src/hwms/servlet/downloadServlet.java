package hwms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hwms.service.CheckWorkManage;

/**
 * Servlet implementation class downloadServlet
 */
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");
		
		System.out.println("download");
		String ch_num = request.getParameter("ch_num");
		
		// 得到文件目录
		String path = request.getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println(path);
		// 得到文件名
		CheckWorkManage checkworkService = new CheckWorkManage();
		String fileName = checkworkService.getCheckWorkByNum(ch_num).getCh_Path();
		System.out.println(path + "\\" + fileName);
		
		// 得到要下载的文件
		File file = new File(path + "\\" + fileName);
		// 如果文件不存在
		if (!file.exists()) {
			request.setAttribute("message", "您下载的资源已被删除");
			return;
		}
		
		// 处理文件名
		String realname = fileName.substring(fileName.lastIndexOf("_") + 1);

		// 设置响应头，控制浏览器下载该文件
		response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		try{
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while((len = in.read(b)) > 0){
                os.write(b,0,len);
            }
            os.flush();
            os.close();
            in.close();
        }catch (IOException ioe){
           ioe.printStackTrace();
        }
		
		/*
		// 读取要下载的文件，保存到文件输入流
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		// 创建输出流
		OutputStream out = response.getOutputStream();
		// 创建缓冲区
		byte buffer[] = new byte[1024];
		int len = 0;
		// 循环将输入流中的内容读取到缓冲区当中
		while ((len = in.read(buffer)) > 0) {
			// 输出缓冲区的内容到浏览器，实现文件下载
			out.write(buffer, 0, len);
		}
		// 关闭文件输入流
		in.close();
		// 关闭输出流
		out.close();*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
