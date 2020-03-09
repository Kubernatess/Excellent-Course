

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	private String downloadPath;

	@Override
	public void init() throws ServletException {
		super.init();
		downloadPath = (String) getServletContext().getAttribute("uploadPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=request.getParameter("courseName");
        int tabIndex=Integer.parseInt(request.getParameter("tabIndex"));
        int columnIndex=Integer.parseInt(request.getParameter("columnIndex"));
        String filename=request.getParameter("fileName");
        // 这个路径相对当前应用的目录
        String filePath=downloadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex+File.separator+filename;
		InputStream in=new FileInputStream(filePath);
		//设置MINE类型
		String type=getServletContext().getMimeType(filename);
		response.setContentType(type);
		//以附件的形式打开文件
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GBK"),"ISO8859_1"));
		//IO的拷贝
		OutputStream os=response.getOutputStream();
		int len=0;
		byte b[]=new byte[1024];
		while((len=in.read(b))!=-1){
			os.write(b, 0, len);
		}
		in.close();
		os.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
