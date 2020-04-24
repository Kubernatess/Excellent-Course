

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
		String courseID = "";
        String tabIndex = "";
        String columnIndex = "";
        String fileName = "";
        FileItem fileItem = null;
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
            	for (FileItem item : formItems) {
	            	// 文件处理
	                if (!item.isFormField()&&!item.getName().equals("")) {
	                	fileName = new File(item.getName()).getName();
	                	fileItem = item;
	                }
	                else{
	                	String field=item.getFieldName();
                        String value=item.getString("utf-8");
                        if(field.equals("courseID")){
                        	courseID = value;
                        }
                        else if(field.equals("tabIndex")){
                        	tabIndex = value;
                        }
                        else if(field.equals("columnIndex")){
                        	columnIndex = value;
                        }
	                }
	            }
            }
            // 对文件名进行hash编码,保留文件后缀名
            int endIndex = fileName.lastIndexOf(".");
            String fileType = fileName.substring(endIndex);
            String hashName = String.valueOf(fileName.substring(0, endIndex).hashCode());
            fileName = hashName+fileType;
            // 上传文件到服务器
            String filePath=uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+fileName;
            File storeFile = new File(filePath);
            // 保存文件到硬盘
            fileItem.write(storeFile);
            // 返回虚拟路径
            String vitualPath = contextPath+"/"+"upload"+"/"+courseID+"/"+tabIndex+"/"+columnIndex+"/"+fileName;
            PrintWriter out = response.getWriter();
            out.println(vitualPath);
        }catch (Exception ex) {
	    	request.setAttribute("message","错误信息: " + ex.getMessage());
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
