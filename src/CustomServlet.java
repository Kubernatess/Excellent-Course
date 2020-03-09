

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
import db.DatabaseAccess;

@WebServlet("/CustomServlet")
public class CustomServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private String uploadPath;

	@Override
	public void init() throws ServletException {
		super.init();
		uploadPath = (String) getServletContext().getAttribute("uploadPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=new String(request.getParameter("courseName").getBytes("ISO8859-1"),"UTF-8");
        int tabIndex=Integer.parseInt(request.getParameter("tabIndex"));
        int columnIndex=Integer.parseInt(request.getParameter("columnIndex"));
        String operation=request.getParameter("operation");
        
        if(operation.equals("add")){
    		// 新增栏目
        	String columnName=new String(request.getParameter("columnName").getBytes("ISO8859-1"),"UTF-8");
        	Column column=new Column();
            column.setTeacherIdentity(teacherIdentity);
            column.setCourseName(courseName);
            column.setTabIndex(tabIndex);
            column.setColumnIndex(columnIndex);
            column.setColumnName(columnName);
            DatabaseAccess.addColumn(column);
            // 并创建文件夹
            String dirPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex;
            File dirFile=new File(dirPath);
    		if(!dirFile.exists()){
    			dirFile.mkdirs();
    		}
    	}
    	else if(operation.equals("removeColumn")){
    		
    	}
    	else if(operation.equals("renameColumn")){
    		String columnName=new String(request.getParameter("columnName").getBytes("ISO8859-1"),"UTF-8");
        	Column column=new Column();
            column.setTeacherIdentity(teacherIdentity);
            column.setCourseName(courseName);
            column.setTabIndex(tabIndex);
            column.setColumnIndex(columnIndex);
            column.setColumnName(columnName);
            DatabaseAccess.addColumn(column);
    	}
    	else if(operation.equals("moveUpColumn")){
    		int previousIndex=columnIndex-1;
			Column column=new Column();
            column.setTeacherIdentity(teacherIdentity);
            column.setCourseName(courseName);
            column.setTabIndex(tabIndex);
            
            column.setColumnIndex(columnIndex);
			DatabaseAccess.modifyColumnIndex(column,1000);
			String currentPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex;
            File currentFile=new File(currentPath);
            String tempPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+1000;
            File tempFile=new File(tempPath);
            currentFile.renameTo(tempFile);
			// 修改上一个元素的索引值
			column.setColumnIndex(previousIndex);
			DatabaseAccess.modifyColumnIndex(column,columnIndex);
			String previousPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+previousIndex;
            File previousFile=new File(previousPath);
            previousFile.renameTo(currentFile);
			// 修改当前元素的索引值
			column.setColumnIndex(1000);
			DatabaseAccess.modifyColumnIndex(column,previousIndex);
			tempFile.renameTo(previousFile);
    	}
    	else if(operation.equals("moveDownColumn")){
    		int nextIndex=columnIndex+1;
			Column column=new Column();
            column.setTeacherIdentity(teacherIdentity);
            column.setCourseName(courseName);
            column.setTabIndex(tabIndex);
            
            column.setColumnIndex(columnIndex);
			DatabaseAccess.modifyColumnIndex(column,1000);
			String currentPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+columnIndex;
            File currentFile=new File(currentPath);
            String tempPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+1000;
            File tempFile=new File(tempPath);
            currentFile.renameTo(tempFile);
			// 修改下一个元素的索引值
			column.setColumnIndex(nextIndex);
			DatabaseAccess.modifyColumnIndex(column,columnIndex);
			String nextPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+nextIndex;
            File nextFile=new File(nextPath);
            nextFile.renameTo(currentFile);
			// 修改当前元素的索引值
			column.setColumnIndex(1000);
			DatabaseAccess.modifyColumnIndex(column,nextIndex);
			tempFile.renameTo(nextFile);
    	}
    	response.sendRedirect("./teacher/custom.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
