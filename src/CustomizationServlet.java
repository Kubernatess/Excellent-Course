

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Column;
import bean.Tab;
import db.DatabaseAccess;

@WebServlet("/CustomizationServlet")
public class CustomizationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private String uploadPath;

	@Override
	public void init() throws ServletException {
		super.init();
		uploadPath = (String) getServletContext().getAttribute("uploadPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseID = request.getParameter("courseID");
        String tabIndex = request.getParameter("tabIndex");
        String operation = request.getParameter("operation");
        
        Tab tab=new Tab();
        tab.setCourseID(courseID);
        tab.setIndex(tabIndex);
        
        if(operation.equals("add")){
        	String tabName = new String(request.getParameter("tabName").getBytes("ISO8859-1"),"UTF-8");
        	tab.setName(tabName);
        	DatabaseAccess.addTab(tab);
            // 并创建文件夹
	        String dirPath = uploadPath+File.separator+courseID+File.separator+tabIndex;
	        File dirFile = new File(dirPath);
	    	if(!dirFile.exists()){
	    		dirFile.mkdirs();
	    	}    
        }
        else if(operation.equals("rename")){
        	String tabName = new String(request.getParameter("tabName").getBytes("ISO8859-1"),"UTF-8");
        	tab.setName(tabName);
        	DatabaseAccess.addTab(tab);
        }
        else if(operation.equals("remove")){
        	DatabaseAccess.removeTab(tab);
        	// 删除对应的目录文件
        	String dirPath = uploadPath+File.separator+courseID+File.separator+tabIndex;
            File dirFile = new File(dirPath);
            dirFile.delete();
        }
		response.sendRedirect("./teacher/customization.jsp?courseID="+courseID);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
