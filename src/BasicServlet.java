import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import bean.Course;
import db.DatabaseAccess;

@WebServlet("/BasicServlet")
public class BasicServlet extends HttpServlet {
	
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

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName="";
        String description="";
        String template="";
        String fileName="";
        String major="";
        String team="";
        
        HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String depart=(String) session.getAttribute("depart");
        
        // 解析请求的内容提取文件数据
        List<FileItem> formItems;
		try {
			formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
	            for (FileItem item : formItems) {
	                // 处理表单字段
	                if (item.isFormField()) {
	                    String field=item.getFieldName();
	                    String value=item.getString("utf-8");
	                    if(field.equals("courseName")){
	                    	courseName=value;
	                    }
	                    else if(field.equals("description")){
	                    	description=value;
	                    }
	                    else if(field.equals("major")){
	                    	major=value;
	                    }
	                    else if(field.equals("teacherName")){
	                    	team += value+",";
	                    }
	                    else if(field.equals("template")){
	                    	template=value;
	                    }
	                }
	                
	                // 文件处理
	                else {
	                	//如果文件夹不存在,则创建文件夹
	            		String dirPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName;
	            		File dirFile=new File(dirPath);
	            		if(!dirFile.exists()){
	            			dirFile.mkdir();
	            		}
	            		
	            		// 保存文件到硬盘
	                	fileName = new File(item.getName()).getName();
	                    String filePath=dirPath+File.separator+fileName;
	                    File storeFile = new File(filePath);
	                    item.write(storeFile);
	                }
	            }
			}
			// 添加一门课程 
            Course course=new Course();      
            course.setTeacherIdentity(teacherIdentity);
            course.setName(courseName);
            course.setDescription(description);
            course.setMajor(major);
            course.setCover(fileName);
            course.setTeam(team);
            DatabaseAccess.addCourse(course);
            // 跳回基本信息页面,并显示所填写的信息
            response.sendRedirect("./teacher/basic.jsp?courseName="+URLEncoder.encode(courseName)+"");
		} 
		catch(Exception ex) {
    		request.setAttribute("message","错误信息: " + ex.getMessage());
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
