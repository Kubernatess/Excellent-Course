

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import bean.Column;
import db.DatabaseAccess;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private String uploadPath;

	@Override
	public void init() throws ServletException {
		super.init();
		uploadPath = (String) getServletContext().getAttribute("uploadPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=(String) session.getAttribute("courseName");
        String tabIndex=(String) session.getAttribute("tabIndex");
        String columnIndex=(String) session.getAttribute("columnIndex");
        String content=new String(request.getParameter("content").getBytes("ISO8859-1"),"UTF-8");
        
        Column column=new Column();
        column.setTeacherIdentity(teacherIdentity);
        column.setCourseName(courseName);
        column.setTabIndex(Integer.parseInt(tabIndex));
        column.setColumnIndex(Integer.parseInt(columnIndex));
        column.setContent(content);
        DatabaseAccess.updateContent(column);
        
        // 定期清理磁盘目录下多余的文件
        String currentPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex;
        File dir=new File(currentPath);
        File[] files=dir.listFiles();
        for(File f:files){
        	if(content.indexOf(f.getName())==-1){
        		f.delete();
        	}
        }
        response.sendRedirect("./teacher/edit.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
