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
			String originID = "";
			String courseID = "";
			String courseName = "";
	        String description = "";
	        String fileName = "";
	        String team = "";
	        FileItem fileItem = null;
	        HttpSession session = request.getSession();
	        String teacherIdentity = (String) session.getAttribute("identity");
	        
			formItems = upload.parseRequest(request);
			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
	            for (FileItem item : formItems) {
	                // 处理表单字段
	                if (item.isFormField()) {
	                    String field=item.getFieldName();
	                    String value=item.getString("utf-8");
	                    if(field.equals("courseID")){
	                    	courseID = value;
	                    }
	                    else if(field.equals("originID")){
	                    	originID = value;
	                    }
	                    else if(field.equals("courseName")){
	                    	courseName = value;
	                    }
	                    else if(field.equals("description")){
	                    	description = value;
	                    }
	                    else if(field.equals("teacherName")){
	                    	team += value+",";
	                    }
	                }
	                // 文件处理
	                else {
	                	fileItem = item;
	                }
	            }
			}
			// 如果没有originID参数,则执行插入更新语句
			// 或者传递了originID参数,并且和courseID也相同,则执行修改课程信息操作
    		if(originID.equals("")||originID.equals(courseID)){
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
                // 添加一门课程 
                Course course=new Course();      
                course.setId(courseID);
                course.setName(courseName);
                course.setDescription(description);
                course.setTeam(team);
                course.setTeacherIdentity(teacherIdentity);
                DatabaseAccess.addCourse(course);
    		}
    		// 如果有originID参数,并且与courseID不相同,则执行修改课程代码操作
    		else if((!originID.equals("")) && (!originID.equals(courseID))){
    			// 修改文件夹名字
    			String originPath = uploadPath+File.separator+originID;
        		File originFile = new File(originPath);
    			String destPath = uploadPath+File.separator+courseID;
        		File destFile = new File(destPath);
        		originFile.renameTo(destFile);
    			// 修改数据库课程主键
    			DatabaseAccess.modifyCourseID(courseID,originID);
    		}
    		
            
            PrintWriter out = response.getWriter();
            // 跨页面跳转
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
