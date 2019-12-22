

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
import bean.Tab;
import db.DatabaseAccess;

@WebServlet("/CustomizationServlet")
public class CustomizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIRECTORY = "upload";

    public CustomizationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=new String(request.getParameter("courseName").getBytes("ISO8859-1"),"UTF-8");
        int tabIndex=Integer.parseInt(request.getParameter("tabIndex"));
        String uploadPath = getServletContext().getRealPath("/")  + UPLOAD_DIRECTORY;
        
        String operation=request.getParameter("operation");
        if(operation.equals("add")){
        	String tabName=new String(request.getParameter("tabName").getBytes("ISO8859-1"),"UTF-8");
        	Tab tab=new Tab();
            tab.setTeacherIdentity(teacherIdentity);
            tab.setCourseName(courseName);
            tab.setTabIndex(tabIndex);
            tab.setName(tabName);
            DatabaseAccess.addTab(tab);
            
            Column column=new Column();
            column.setTeacherIdentity(teacherIdentity);
            column.setCourseName(courseName);
            column.setTabIndex(tabIndex);
            column.setColumnIndex(1);
            column.setColumnName("添加栏目");
            DatabaseAccess.addColumn(column);
            
            Subcolumn subColumn=new Subcolumn();
            subColumn.setTeacherIdentity(teacherIdentity);
            subColumn.setCourseName(courseName);
            subColumn.setTabIndex(tabIndex);
            subColumn.setColumnIndex(1);
            subColumn.setSubcolumnIndex(1);
            subColumn.setSubcolumnName("添加子栏目");
            DatabaseAccess.addSubColumn(subColumn);
            // 并创建文件夹
            String dirPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex+File.separator+"1"+File.separator+"1";
            File dirFile=new File(dirPath);
    		if(!dirFile.exists()){
    			dirFile.mkdirs();
    		}
        }
        else if(operation.equals("rename")){
        	String tabName=new String(request.getParameter("tabName").getBytes("ISO8859-1"),"UTF-8");
        	Tab tab=new Tab();
            tab.setTeacherIdentity(teacherIdentity);
            tab.setCourseName(courseName);
            tab.setTabIndex(tabIndex);
            tab.setName(tabName);
            DatabaseAccess.modifyTab(tab);
        }
        else if(operation.equals("leftshift")){
        	int previousIndex=tabIndex-1;
        	Tab tab=new Tab();
            tab.setTeacherIdentity(teacherIdentity);
            tab.setCourseName(courseName);
            
            tab.setTabIndex(tabIndex);
            DatabaseAccess.modifyTabIndex(tab,1000);
            String currentPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex;
            File currentFile=new File(currentPath);
            String tempPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+1000;
            File tempFile=new File(tempPath);
            currentFile.renameTo(tempFile);
            // 修改上一个元素的索引值
			tab.setTabIndex(previousIndex);
			DatabaseAccess.modifyTabIndex(tab,tabIndex);
			String previousPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+previousIndex;
            File previousFile=new File(previousPath);
            previousFile.renameTo(currentFile);
			// 修改当前元素的索引值
			tab.setTabIndex(1000);
			DatabaseAccess.modifyTabIndex(tab,previousIndex);
			tempFile.renameTo(previousFile);
        }
        else if(operation.equals("rightshift")){
        	int nextIndex=tabIndex+1;
        	Tab tab=new Tab();
            tab.setTeacherIdentity(teacherIdentity);
            tab.setCourseName(courseName);
            
            tab.setTabIndex(tabIndex);
            DatabaseAccess.modifyTabIndex(tab,1000);
            String currentPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+tabIndex;
            File currentFile=new File(currentPath);
            String tempPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+1000;
            File tempFile=new File(tempPath);
            currentFile.renameTo(tempFile);
            // 修改上一个元素的索引值
			tab.setTabIndex(nextIndex);
			DatabaseAccess.modifyTabIndex(tab,tabIndex);
			String nextPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName+File.separator+nextIndex;
            File nextFile=new File(nextPath);
            nextFile.renameTo(currentFile);
			// 修改当前元素的索引值
			tab.setTabIndex(1000);
			DatabaseAccess.modifyTabIndex(tab,nextIndex);
			tempFile.renameTo(nextFile);
        }
        
		response.sendRedirect("./teacher/customization.jsp?courseName="+URLEncoder.encode(courseName)+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
