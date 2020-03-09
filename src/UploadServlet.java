

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
import db.DatabaseAccess;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
	private String uploadPath;
	private String contextPath;

	@Override
	public void init() throws ServletException {
		super.init();
		// 配置上传参数
        factory = new DiskFileItemFactory();
        upload = new ServletFileUpload(factory);        
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 这个路径相对当前应用的目录
        uploadPath = (String) getServletContext().getAttribute("uploadPath");
        contextPath = (String) getServletContext().getAttribute("contextPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=(String) session.getAttribute("courseName");
        String tabIndex=(String) session.getAttribute("tabIndex");
        String columnIndex=(String) session.getAttribute("columnIndex");
        String fileName = "";
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
            	for (FileItem item : formItems) {
	            	// 文件处理
	                if (!item.isFormField()&&!item.getName().equals("")) {
	                	fileName = new File(item.getName()).getName();
	                	String filePath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex+File.separator+fileName;
	                    File storeFile = new File(filePath);
	                    // 保存文件到硬盘
	                    item.write(storeFile);
	                }
	            }
            }
            // 返回虚拟路径
            String vitualPath = contextPath+"/"+"upload"+"/"+teacherIdentity+"/"+courseName+"/"+tabIndex+"/"+columnIndex+"/"+fileName;
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
