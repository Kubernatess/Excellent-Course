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

public class DisplayTag extends SimpleTagSupport {
	private String key;
	private String depart;
	private String major;
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
		PageContext pc = (PageContext) getJspContext();
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		JspWriter out = getJspContext().getOut();
		List<Map<String,String>> list = new ArrayList<>();
		// 如果只传递了'系别'参数,则根据'系别'获得所有的课程
		if(!depart.equals("")) {
			list = DatabaseAccess.getAllCourseAndDetail("",depart,"");
		}
		// 如果只传递了'专业'参数,则根据'专业'获得所有的课程
		else if(!major.equals("")) {
			list = DatabaseAccess.getAllCourseAndDetail("","",major);
		}
		// 如果只传递了'关键字'参数,则根据'关键字'获得所有的课程
		else if(!key.equals("")) {
			list = DatabaseAccess.getAllCourseAndDetail(key,"","");
		}
		// 默认显示所有的课程		
		else {
			list = DatabaseAccess.getAllCourseAndDetail("","","");
		}
		Iterator<Map<String,String>> iterator = list.iterator();
		while(iterator.hasNext()){
			Map<String,String> map = iterator.next();
			String courseID = (String) map.get("courseID");
			String courseName = (String) map.get("courseName");
			String depart = (String) map.get("depart");
			String description = (String) map.get("description");
			String major = (String) map.get("major");
			Map<String,Integer> recordMap = DatabaseAccess.getAllVisitor();
			int visitors = recordMap.containsKey(courseID)?recordMap.get(courseID):0;
			String team = (String) map.get("team");
			team = team.substring(0,team.length()-1);
			String cover = contextPath+"/"+"upload"+"/"+courseID+"/cover.png";
			List<Map<String,String>> commentList = DatabaseAccess.getAllReviewAndDetail(courseID);
			out.println("<table>");
			out.println("<tr>");
			out.println("<th rowspan=\"4\"><div><img src=\""+cover+"\"></div></th>");
			out.println("<th><a href=\"course.jsp?courseID="+courseID+"\" target=\"_blank\">"+courseName+"</a></th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><span>"+depart+"</span><span>"+major+"</span><span>"+team+"</span></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><p>"+description+"</p></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><img src=\"image/user-empty.png\" /><samp>"+visitors+"</samp><img src=\"image/chat.png\" /><samp>"+commentList.size()+"</samp></td>");
			out.println("</tr>");
			out.println("</table>");
		}
	}
}
