package defineJSTL;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Column;
import db.DatabaseAccess;

public class FetchTag extends SimpleTagSupport {
	
	private String courseID;
	private String tabIndex;
	private int columnIndex;
	private String courseName;
	private String tabName;
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	public void doTag() throws JspException, IOException {
		PageContext pc = (PageContext) getJspContext();
		HttpSession session = pc.getSession();
		String teacherIdentity = (String) session.getAttribute("identity");
		String uploadPath = (String) pc.getServletContext().getAttribute("uploadPath");
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		// 显示不同的课程内容
		JspWriter out = getJspContext().getOut();
		if(courseID.equals("")){
			Map<String,String> map = DatabaseAccess.getCourseByTeacherID(teacherIdentity);
			for(String courseID : map.keySet()){
				String courseName = map.get(courseID);
				out.println("<a href=\"backstage.jsp?courseID="+courseID+"&courseName="+courseName+"\">");
				out.println("<img src=\"../image/files/folder.png\">");
				out.println("<p>"+courseName+"</p>");
				out.println("</a>");
			}
		}
		else if(tabIndex.equals("")){
			Map<String,String> map = DatabaseAccess.getTabByCourseId(courseID);
			for(String tabIndex : map.keySet()){
				String tabName = map.get(tabIndex);
				out.println("<a href=\"backstage.jsp?courseID="+courseID+"&courseName="+courseName+"&tabIndex="+tabIndex+"&tabName="+tabName+"\">");
				out.println("<img src=\"../image/files/folder.png\">");
				out.println("<p>"+tabName+"</p>");
				out.println("</a>");
			}
		}
		else if(columnIndex==0){
			Column column = new Column();
			column.setCourseID(courseID);
			column.setTabIndex(tabIndex);
			Map<Integer,String> map = DatabaseAccess.getColumnByCourseIdAndId(column);
			for(int columnIndex : map.keySet()){
				String columnName = map.get(columnIndex);
				out.println("<a href=\"backstage.jsp?courseID="+courseID+"&courseName="+courseName+"&tabIndex="+tabIndex+"&tabName="+tabName+"&columnIndex="+columnIndex+"&columnName="+columnName+"\">");
				out.println("<img src=\"../image/files/folder.png\">");
				out.println("<p>"+columnName+"</p>");
				out.println("</a>");
			}
		}
		else{
			String homeworkPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+"homework";
			File dir = new File(homeworkPath);
	        if(dir.listFiles()!=null){
	        	File[] files = dir.listFiles();
	            for(File f:files){
	            	String vitualPath = contextPath+"/"+"upload"+"/"+courseID+"/"+tabIndex+"/"+columnIndex+"/"+"homework"+"/"+f.getName();
	            	int endIndex = f.getName().lastIndexOf(".");
	                String type = f.getName().substring(endIndex+1);
	            	String fileImg = "txt.png";
	                if(type.equals("xls")||type.equals("xlsx")){
	            		fileImg = "excel.png";
	            	}
	                else if(type.equals("png")||type.equals("jpg")||type.equals("jpeg")||type.equals("bmp")){
	                	fileImg = "image.png";
	                }
	                else if(type.equals("pdf")){
	                	fileImg = "pdf.png";
	                }
	                else if(type.equals("ppt")||type.equals("pptx")){
	                	fileImg = "ppt.png";
	                }
	                else if(type.equals("avi")||type.equals("mp4")||type.equals("mov")){
	                	fileImg = "video.png";
	                }
	                else if(type.equals("doc")||type.equals("docx")){
	                	fileImg = "word.png";
	                }
	                else if(type.equals("zip")||type.equals("rar")||type.equals("jar")){
	                	fileImg = "zip.png";
	                }
	            	out.println("<a href=\""+vitualPath+"\">");
					out.println("<img src=\"../image/files/"+fileImg+"\" title=\""+f.getName()+"\">");
					out.println("<p>"+f.getName()+"</p>");
					out.println("</a>");
	            }
	        }
		}
	}
}
