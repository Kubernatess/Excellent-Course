

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

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
	private String contextPath;

	@Override
	public void init() throws ServletException {
		super.init();
		uploadPath = (String) getServletContext().getAttribute("uploadPath");
		contextPath = (String) getServletContext().getAttribute("contextPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseID = request.getParameter("courseID");
        String tabIndex = request.getParameter("tabIndex");
        int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
        
        Column column = new Column();
        column.setCourseID(courseID);
        column.setTabIndex(tabIndex);
        column.setIndex(columnIndex);
        
        // 如果coulumnName和anotherIndex两个参数都没有传递过来,则执行删除操作
        if(request.getParameter("columnName")==null && request.getParameter("anotherIndex").equals("")){
        	DatabaseAccess.removeColumn(column);
    		// 删除磁盘下的目录,递归删除
    		String dirPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex;
            File dirFile = new File(dirPath);
            recurrencedelete(dirFile);
            response.sendRedirect("./teacher/edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex);
        }
        // 如果传递了columnName,而没有传递anotherIndex,则执行插入更新操作
        else if(request.getParameter("columnName")!=null && request.getParameter("anotherIndex").equals("")){
        	// 新增栏目
        	String columnName = new String(request.getParameter("columnName").getBytes("ISO8859-1"),"UTF-8");
            column.setName(columnName);
        	DatabaseAccess.addColumn(column);
            // 创建homework文件夹
    		String homeworkPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+"homework";
            File homeworkDirectory = new File(homeworkPath);
    		if(!homeworkDirectory.exists()){
    			homeworkDirectory.mkdirs();
    		}
    		// 创建converter文件夹,用于存放转换后的pdf文件
            String converterPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+"converter";
            File converterDirectory = new File(converterPath);
    		if(!converterDirectory.exists()){
    			converterDirectory.mkdirs();
    		}
    		response.sendRedirect("./teacher/edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex);
        }
        // 如果传递了anotherIndex参数,而没有传递columnName参数,则执行栏目的上移或下移操作
        else if(request.getParameter("columnName")==null && !request.getParameter("anotherIndex").equals("")){
        	int previousIndex = Integer.parseInt(request.getParameter("anotherIndex"));
    		// 交换两个子栏目的索引值
    		swapIndex(column, columnIndex, previousIndex);
    		// 更新数据库content的内容
    		updateContent(column, columnIndex, previousIndex);
			// 上一个元素也要做同样操作
    		updateContent(column, previousIndex, columnIndex);
    		response.sendRedirect("./teacher/edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex);
        }   	
	}
	
	
	// 递归删除磁盘文件
	private void recurrencedelete(File f) {
		File [] files = f.listFiles();
		for(int i =0;i<files.length;i++){
		    if(files[i].isFile()){
		        files[i].delete();
		    }else{
		        recurrencedelete(files[i]);//否则重新递归到方法中
		    }
		}
		f.delete();//最后还要删除自己
	}
	
	// 交换两个子栏目的索引值
	private void swapIndex(Column column, int currentIndex, int anotherIndex){
		column.setIndex(currentIndex);
		DatabaseAccess.modifyColumnIndex(column,1000);
		String currentPath = uploadPath+File.separator+column.getCourseID()+File.separator+column.getTabIndex()+File.separator+currentIndex;
        File currentFile = new File(currentPath);
        String tempPath = uploadPath+File.separator+column.getCourseID()+File.separator+column.getTabIndex()+File.separator+1000;
        File tempFile = new File(tempPath);
        currentFile.renameTo(tempFile);
		// 修改上一个元素的索引值
		column.setIndex(anotherIndex);
		DatabaseAccess.modifyColumnIndex(column,currentIndex);
		String anotherPath = uploadPath+File.separator+column.getCourseID()+File.separator+column.getTabIndex()+File.separator+anotherIndex;
        File anotherFile = new File(anotherPath);
        anotherFile.renameTo(currentFile);
		// 修改当前元素的索引值
		column.setIndex(1000);
		DatabaseAccess.modifyColumnIndex(column,anotherIndex);
		tempFile.renameTo(anotherFile);
	}
	
	// 更新数据库content的内容
	private void updateContent(Column column, int currentIndex, int anotherIndex){
		column.setIndex(currentIndex);
		Map<String,String> map = DatabaseAccess.getColumn(column);
		String answer = map.get("answer");
		String content = map.get("content");
		String RegExp = contextPath+"/upload"+"/"+column.getCourseID()+"/"+column.getTabIndex()+"/"+anotherIndex;
		String replace = contextPath+"/upload"+"/"+column.getCourseID()+"/"+column.getTabIndex()+"/"+currentIndex;
		if(content!=null&&content.indexOf(RegExp)!=-1){
			content = content.replaceAll(RegExp, replace);
			column.setContent(content);
			column.setAnswer(answer);
			DatabaseAccess.updateContent(column);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
