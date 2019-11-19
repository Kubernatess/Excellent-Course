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
import bean.Team;
import db.DatabaseAccess;

@WebServlet("/BasicServlet")
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    public BasicServlet() {
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
        String uploadPath = getServletContext().getRealPath("/")  + UPLOAD_DIRECTORY;
		
        String teacherIdentity=null;
        String courseName=null;
        String description=null;
        String depart=null;
        String template=null;
        String fileName=null;
        String major=null;
        List<String> list=new ArrayList<>();
        
        HttpSession session = request.getSession();
        teacherIdentity=(String) session.getAttribute("identity");
        
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
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
	                    else if(field.equals("depart")){
	                    	depart=value;
	                    }
	                    else if(field.equals("major")){
	                    	major=value;
	                    }
	                    else if(field.equals("teacherName")){
	                    	list.add(value);
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
	            		
	                	fileName = new File(item.getName()).getName();
	                    String filePath=dirPath+File.separator+fileName;
	                    File storeFile = new File(filePath);
	                    // 保存文件到硬盘
	                    item.write(storeFile);
	                }
	            }
			}
			
			// 添加一门课程 
            Course course=new Course();      
            course.setTeacherIdentity(teacherIdentity);
            course.setName(courseName);
            course.setDescription(description);
            course.setDepart(depart);
            course.setMajor(major);
            course.setCover(fileName);
            course.setTemplate(template);
            DatabaseAccess.addCourse(course);
            // 添加教学团队成员
            Team team=new Team();
            team.setTeacherIdentity(teacherIdentity);
            team.setCourseName(courseName);
            team.setMember(list);
            DatabaseAccess.addTeam(team);
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
