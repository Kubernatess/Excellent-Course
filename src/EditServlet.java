

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

import bean.Subcolumn;
import db.DatabaseAccess;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    public EditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=(String) session.getAttribute("courseName");
        String tabIndex=(String) session.getAttribute("tabIndex");
        String columnIndex=(String) session.getAttribute("columnIndex");
        String subcolumnIndex=(String) session.getAttribute("subcolumnIndex");
        String content=new String(request.getParameter("content").getBytes("ISO8859-1"),"UTF-8");
        
        Subcolumn subcolumn=new Subcolumn();
        subcolumn.setTeacherIdentity(teacherIdentity);
        subcolumn.setCourseName(courseName);
        subcolumn.setTabIndex(Integer.parseInt(tabIndex));
        subcolumn.setColumnIndex(Integer.parseInt(columnIndex));
        subcolumn.setSubcolumnIndex(Integer.parseInt(subcolumnIndex));
        subcolumn.setContent(content);
        DatabaseAccess.updateContent(subcolumn);
        
        // 定期清理磁盘目录下多余的文件
        String uploadPath=getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
        String currentPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex+File.separator+subcolumnIndex;
        File dir=new File(currentPath);
        File[] files=dir.listFiles();
        for(File f:files){
        	if(content.indexOf(f.getName())==-1){
        		f.delete();
        	}
        }
        response.sendRedirect("./teacher/edit.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"&subcolumnIndex="+subcolumnIndex+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
