package defineJSTL;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class SearchTag extends SimpleTagSupport {
	
	private String key;

	public void setKey(String key) {
		this.key = key;
	}
	
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		JspWriter out = getJspContext().getOut();
		// 获得指定课程的信息
		List<Map<String,String>> list=DatabaseAccess.getCourseByKey(key);	
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Map<String,String> map = (Map<String, String>) iterator.next();
			String teacherIdentity = map.get("teacherIdentity");
			String courseName = map.get("courseName");
			String depart = map.get("depart");
			String description = map.get("description");
			String team = map.get("team");
			team = team.replaceAll(",","、");
			team = team.substring(0,team.length()-1);
			String cover = map.get("cover");
			cover = contextPath+"/"+"upload"+"/"+teacherIdentity+"/"+courseName+"/"+cover;
			List<Map<String,Object>> comments = DatabaseAccess.getAllCommentByIdAndName(teacherIdentity,courseName);
			out.println("<div class=\"content\">");
			out.println("<div><img src=\""+cover+"\" /></div>");
			out.println("<a href=\"course.jsp?teacherIdentity="+teacherIdentity+"&courseName="+courseName+"\" target=\"_blank\">"+courseName+"</a>");
			out.println("<span>"+depart+"</span><span>"+team+"</span>");
			out.println("<p>"+description+"</p>");
			out.println("<img src=\"image/user-empty.png\" /><span>225</span><img src=\"image/chat.png\" /><span>"+comments.size()+"</span>");
			out.println("</div>");
		}
	}
}
