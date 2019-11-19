

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Column;
import bean.Subcolumn;
import db.DatabaseAccess;

@WebServlet("/CustomServlet")
public class CustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIRECTORY = "upload";

    public CustomServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=new String(request.getParameter("courseName").getBytes("ISO8859-1"),"UTF-8");
        int tabIndex=Integer.parseInt(request.getParameter("tabIndex"));
        int columnIndex=Integer.parseInt(request.getParameter("columnIndex"));
    	String uploadPath = getServletContext().getRealPath("/")  + UPLOAD_DIRECTORY;
        
    	String operation=request.getParameter("operation");
    	if(operation.equals("add")){
    		if(request.getParameter("subcolumnIndex").equals("")){
    			// 新增栏目
            	String columnName=new String(request.getParameter("columnName").getBytes("ISO8859-1"),"UTF-8");
            	Column column=new Column();
                column.setTeacherIdentity(teacherIdentity);
                column.setCourseName(courseName);
                column.setTabIndex(tabIndex);
                column.setColumnIndex(columnIndex);
                column.setColumnName(columnName);
                DatabaseAccess.addColumn(column);
                // 新增子栏目
                Subcolumn subcolumn=new Subcolumn();
            	subcolumn.setTeacherIdentity(teacherIdentity);
            	subcolumn.setCourseName(courseName);
            	subcolumn.setTabIndex(tabIndex);
            	subcolumn.setColumnIndex(columnIndex);
            	subcolumn.setSubcolumnIndex(1);
            	subcolumn.setSubcolumnName("添加子栏目");
            	DatabaseAccess.addSubColumn(subcolumn);
            	// 并创建文件夹
                String dirPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex+File.separator+"1";
                File dirFile=new File(dirPath);
        		if(!dirFile.exists()){
        			dirFile.mkdirs();
        		}
    		}
    		else{
    			// 新增子栏目
            	int subcolumnIndex=Integer.parseInt(request.getParameter("subcolumnIndex"));
            	String subcolumnName=new String(request.getParameter("subcolumnName").getBytes("ISO8859-1"),"UTF-8");
            	Subcolumn subcolumn=new Subcolumn();
            	subcolumn.setTeacherIdentity(teacherIdentity);
            	subcolumn.setCourseName(courseName);
            	subcolumn.setTabIndex(tabIndex);
            	subcolumn.setColumnIndex(columnIndex);
            	subcolumn.setSubcolumnIndex(subcolumnIndex);
            	subcolumn.setSubcolumnName(subcolumnName);
            	DatabaseAccess.addSubColumn(subcolumn);
            	// 并创建文件夹
                String subPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex+File.separator+subcolumnIndex;
                File subFile=new File(subPath);
        		if(!subFile.exists()){
        			subFile.mkdir();
        		}
        	}
        }
    	else if(operation.equals("rename")){
        	if(request.getParameter("subcolumnIndex").equals("")){
        		String columnName=new String(request.getParameter("columnName").getBytes("ISO8859-1"),"UTF-8");
            	Column column=new Column();
                column.setTeacherIdentity(teacherIdentity);
                column.setCourseName(courseName);
                column.setTabIndex(tabIndex);
                column.setColumnIndex(columnIndex);
                column.setColumnName(columnName);
                DatabaseAccess.modifyColumn(column);
        	}
        	else{
        		int subcolumnIndex=Integer.parseInt(request.getParameter("subcolumnIndex"));
            	String subcolumnName=new String(request.getParameter("subcolumnName").getBytes("ISO8859-1"),"UTF-8");
            	Subcolumn subcolumn=new Subcolumn();
            	subcolumn.setTeacherIdentity(teacherIdentity);
            	subcolumn.setCourseName(courseName);
            	subcolumn.setTabIndex(tabIndex);
            	subcolumn.setColumnIndex(columnIndex);
            	subcolumn.setSubcolumnIndex(subcolumnIndex);
            	subcolumn.setSubcolumnName(subcolumnName);
            	DatabaseAccess.modifySubColumn(subcolumn);
        	}
        }
    	else if(operation.equals("moveup")){
    		if(request.getParameter("subcolumnIndex").equals("")){
    			
    		}
    		else{
    			
    		}
    	}
    	else if(operation.equals("movedown")){
    		if(request.getParameter("subcolumnIndex").equals("")){
    			
    		}
    		else{
    			
    		}
    	}
        
        response.sendRedirect("./teacher/custom.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
