
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

@WebServlet("/SubjectiveServlet")
public class SubjectiveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
	private String uploadPath;

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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");		
        try {
        	// 获取表单数据
            String courseID = "";
            String tabIndex = "";
            String columnIndex = "";
            FileItem fileItem = null;
            String text = "";            
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            for (FileItem item : formItems) {
            	if (item.isFormField()) {
                	String field = item.getFieldName();
                	String value = item.getString("utf-8");
                    if(field.equals("courseID")){
                    	courseID = value;
                    }
                    else if(field.equals("tabIndex")){
                    	tabIndex = value;
                    }
                    else if(field.equals("columnIndex")){
                    	columnIndex = value;
                    }
                    else if(field.equals("textarea")){
                    	text = text+value+"\n\n";
                    }                             	
                }
                // 把文件表单项筛选出来
                else{
                	fileItem = item;
                }
            }
            
            String homeworkPath = uploadPath+"/"+courseID+"/"+tabIndex+"/"+columnIndex+"/"+"homework";            
            
            // 保存文件到硬盘
            if(fileItem!=null){
            	String fileName = fileItem.getName();
                String filePath = homeworkPath+"/"+fileName;
                File storeFile = new File(filePath);
                fileItem.write(storeFile);
            }
            
            // 将文本数据存放到.answer文件中
            HttpSession session = request.getSession();
            String identity = (String) session.getAttribute("identity");
            String fileName = identity+".answer";
            String filePath = homeworkPath+"/"+fileName;
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-16");
            PrintWriter pw = new PrintWriter(osw, true);        
            pw.println(text);
            pw.close();
            
            response.sendRedirect("./learning.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex);
            
        }catch (Exception ex) {
	    	request.setAttribute("message","错误信息: " + ex.getMessage());
	    }
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
