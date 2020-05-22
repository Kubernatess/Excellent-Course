import java.io.File;
import java.io.IOException;
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
		// 解析请求的内容提取文件数据
        List<FileItem> formItems;
		try {
			String defaultID = null;
			String courseID = "";
			String fileName = "";
	        String team = "";
	        FileItem fileItem = null;
	        
	        HttpSession session = request.getSession();
	        String teacherIdentity = (String) session.getAttribute("identity");	        
	        Course course = new Course(); 
	        course.setTeacherIdentity(teacherIdentity);
	        
			formItems = upload.parseRequest(request);
			for (FileItem item : formItems) {
                // 处理表单字段
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    String value = item.getString("utf-8");
                    if(field.equals("courseID")){
                    	courseID = value;
                    	course.setId(courseID);
                    }
                    else if(field.equals("defaultID")){
                    	defaultID = value;
                    }
                    else if(field.equals("courseName")){
                    	String courseName = value;
                    	course.setName(courseName);
                    }
                    else if(field.equals("major")){
                    	String major = value;
                    	course.setMajor(major);
                    }
                    else if(field.equals("description")){
                    	String description = value;
                    	course.setDescription(description);
                    }
                    else if(field.equals("teacherName")){
                    	team += value+"、";
                    	course.setTeam(team); 
                    }
                }
                // 文件处理
                else {
                	fileItem = item;
                }
            }
			// 如果没有defaultID参数,则执行插入更新语句
			if(defaultID==null){
    			//如果文件夹不存在,则创建文件夹
        		String dirPath = uploadPath+File.separator+courseID;
        		File dirFile = new File(dirPath);
        		if(!dirFile.exists()){
        			dirFile.mkdir();
        		}
    			// 把封面图片保存到硬盘,统一命名为cover.png
    			fileName = "cover.png";
                String filePath = dirPath+File.separator+fileName;
                File storeFile = new File(filePath);
                fileItem.write(storeFile);
                
                DatabaseAccess.addCourse(course);
    		}
    		// 如果有defaultID参数,则执行修改课程代码操作
    		else {
    			// 修改文件夹名字
    			File defaultFile = new File(uploadPath+File.separator+defaultID);
    			File destFile = new File(uploadPath+File.separator+courseID);
        		defaultFile.renameTo(destFile);
    			// 修改课程主键
    			DatabaseAccess.modifyCourseID(courseID,defaultID);
    		}   		                       
            // 跨页面跳转
    		PrintWriter out = response.getWriter();
            out.println("<script>top.location.href = \"./teacher/customization.jsp?courseID="+courseID+"\";</script>");
		}
		catch(Exception ex) {
    		request.setAttribute("message","错误信息: " + ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
