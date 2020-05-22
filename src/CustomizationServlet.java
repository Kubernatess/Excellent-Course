
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        String dirPath = uploadPath+File.separator+courseID+File.separator+tabIndex;
        File dirFile = new File(dirPath);
        
        Tab tab = new Tab();
        tab.setCourseID(courseID);
        tab.setIndex(tabIndex);
        
        // 如果没有传递tabName这个参数,则执行删除操作
        if(request.getParameter("tabName")==null){
        	DatabaseAccess.removeTab(tab);
        	// 删除对应的目录文件        	
            dirFile.delete();
        }
        // 如果传递了tabName这个参数,则执行插入更新操作
        else{
        	String tabName = new String(request.getParameter("tabName").getBytes("ISO8859-1"),"UTF-8");
        	tab.setName(tabName);
        	DatabaseAccess.addTab(tab);
            // 并创建文件夹
	        if(!dirFile.exists()){
	    		dirFile.mkdirs();
	    	}
        }
		response.sendRedirect("./teacher/customization.jsp?courseID="+courseID);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
