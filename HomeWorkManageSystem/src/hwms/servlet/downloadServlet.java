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
		
		// �õ��ļ�Ŀ¼
		String path = request.getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println(path);
		// �õ��ļ���
		CheckWorkManage checkworkService = new CheckWorkManage();
		String fileName = checkworkService.getCheckWorkByNum(ch_num).getCh_Path();
		System.out.println(path + "\\" + fileName);
		
		// �õ�Ҫ���ص��ļ�
		File file = new File(path + "\\" + fileName);
		// ����ļ�������
		if (!file.exists()) {
			request.setAttribute("message", "�����ص���Դ�ѱ�ɾ��");
			return;
		}
		
		// �����ļ���
		String realname = fileName.substring(fileName.lastIndexOf("_") + 1);

		// ������Ӧͷ��������������ظ��ļ�
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
		// ��ȡҪ���ص��ļ������浽�ļ�������
		FileInputStream in = new FileInputStream(path + "\\" + fileName);
		// ���������
		OutputStream out = response.getOutputStream();
		// ����������
		byte buffer[] = new byte[1024];
		int len = 0;
		// ѭ�����������е����ݶ�ȡ������������
		while ((len = in.read(buffer)) > 0) {
			// ��������������ݵ��������ʵ���ļ�����
			out.write(buffer, 0, len);
		}
		// �ر��ļ�������
		in.close();
		// �ر������
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
