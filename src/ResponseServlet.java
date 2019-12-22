

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    public ResponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);        
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
        
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            String fileName=null;
            
            HttpSession session = request.getSession();
            String teacherIdentity=(String) session.getAttribute("identity");
            String courseName=(String) session.getAttribute("courseName");
            String tabIndex=(String) session.getAttribute("tabIndex");
            String columnIndex=(String) session.getAttribute("columnIndex");
            String subcolumnIndex=(String) session.getAttribute("subcolumnIndex");
            
            // 迭代表单数据
            for (FileItem item : formItems) {
            	// 文件处理
                if (!item.isFormField()&&!item.getName().equals("")) {
                	fileName=item.getName();
                }
            }
            
            // 返回虚拟路径
            String vitualPath=getServletContext().getContextPath()+"/"+UPLOAD_DIRECTORY+"/"+teacherIdentity+"/"+courseName+"/"+tabIndex+"/"+columnIndex+"/"+subcolumnIndex+"/"+fileName;
            PrintWriter out = response.getWriter();
            out.println(URLEncoder.encode(vitualPath,"utf-8"));

        }catch (Exception ex) {
	    	request.setAttribute("message","错误信息: " + ex.getMessage());
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
