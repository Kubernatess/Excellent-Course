package defineJSTL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class ContentTag extends SimpleTagSupport {
	
	private String depart;
	private String major;
	private String key;
	
	public void setKey(String key) {
		this.key = key;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		JspWriter out = getJspContext().getOut();
		
		List<Map<String,Object>> list = new ArrayList<>();
		// 默认显示所有的课程
		if(depart.equals("")&&major.equals("")&&key.equals("")){
			list = DatabaseAccess.getAllCourse();
		}
		// 如果只传递了'系别'参数,则根据'系别'获得所有的课程
		else if(!depart.equals("")&&major.equals("")&&key.equals("")){
			list = DatabaseAccess.getCourseByDepart(depart);
		}
		// 如果只传递了'专业'参数,则根据'专业'获得所有的课程
		else if(depart.equals("")&&!major.equals("")&&key.equals("")){
			list = DatabaseAccess.getCourseByMajor(major);
		}
		// 如果只传递了'关键字'参数,则根据'关键字'获得所有的课程
		else if(depart.equals("")&&major.equals("")&&!key.equals("")){
			list = DatabaseAccess.getCourseByKey(key);
		}
		
		// 显示课程的信息
		Iterator<Map<String,Object>> iterator = list.iterator();
		while(iterator.hasNext()){
			Map<String,Object> map = iterator.next();
			String courseID = (String) map.get("courseID");
			String courseName = (String) map.get("courseName");
			String depart = (String) map.get("depart");
			String description = (String) map.get("description");
			int visitors = DatabaseAccess.getVisitorsByCourseId(courseID);
			String team = (String) map.get("team");
			team = team.replaceAll(",","、");
			team = team.substring(0,team.length()-1);
			String cover = contextPath+"/"+"upload"+"/"+courseID+"/cover.png";
			List<Map<String,Object>> comments = DatabaseAccess.getAllCommentByCourseId(courseID);
			out.println("<table>");
			out.println("<tr>");
			out.println("<th rowspan=\"4\"><img src=\""+cover+"\"></th>");
			out.println("<th><a href=\"course.jsp?courseID="+courseID+"\" target=\"_blank\">"+courseName+"</a></th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><span>"+depart+"</span><span>"+team+"</span></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><p>"+description+"</p></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><img src=\"image/user-empty.png\" /><samp>"+visitors+"</samp><img src=\"image/chat.png\" /><samp>"+comments.size()+"</samp></td>");
			out.println("</tr>");
			out.println("</table>");
		}
	}
}
