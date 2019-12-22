

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

import bean.Subcolumn;
import db.DatabaseAccess;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    public UploadServlet() {
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
        
        HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=(String) session.getAttribute("courseName");
        String tabIndex=(String) session.getAttribute("tabIndex");
        String columnIndex=(String) session.getAttribute("columnIndex");
        String subcolumnIndex=(String) session.getAttribute("subcolumnIndex");
        
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            // 迭代表单数据
            for (FileItem item : formItems) {
            	// 文件处理
                if (!item.isFormField()&&!item.getName().equals("")) {
                	String fileName = new File(item.getName()).getName();
                	String filePath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex+File.separator+subcolumnIndex+File.separator+fileName;
                    File storeFile = new File(filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);
                }
            }
            
        }catch (Exception ex) {
	    	request.setAttribute("message","错误信息: " + ex.getMessage());
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
