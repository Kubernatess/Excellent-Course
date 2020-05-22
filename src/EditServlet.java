
import java.io.File;
import java.io.IOException;
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
		String courseID = request.getParameter("courseID");
        String tabIndex = request.getParameter("tabIndex");
        int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
        String content = new String(request.getParameter("content").getBytes("ISO8859-1"),"UTF-8");
        int sequence = new Integer(request.getParameter("sequence"));
        // 字符串拼接操作
        String answers = "";
        for(int i=1; i<=sequence; i++){
        	String options[] = request.getParameterValues(String.valueOf(i));
        	if(options==null){
        		break;
        	}
        	// 如果是但选题目
        	if(options.length==1){
        		answers += ","+options[0];
        	}
        	else{
        		String answer = ",";
        		for(int j=0; j<options.length; j++){
        			answer += options[j];
        		}
        		answers += answer;
        	}
        }
     
        Column column = new Column();
        column.setCourseID(courseID);
        column.setTabIndex(tabIndex);
        column.setIndex(columnIndex);
        column.setContent(content);
        column.setAnswer(answers);
        DatabaseAccess.updateContent(column);
        
        response.sendRedirect("./teacher/edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex);
        
        // 定期清理磁盘目录下多余的文件
        String currentPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex;
        File dir = new File(currentPath);
        if(dir.listFiles()==null){
        	return;
        } 
        for(File f : dir.listFiles()){
            if(content.indexOf(f.getName())==-1 && f.isFile()){
            	f.delete();
            }
        }
        // coverter文件夹下的pdf也要做清理
        String converterPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+"converter";
        dir = new File(converterPath);
        if(dir.listFiles()==null){
        	return;
        }
        for(File f:dir.listFiles()){
        	if(content.indexOf(f.getName())==-1){
        		f.delete();
        	}
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
