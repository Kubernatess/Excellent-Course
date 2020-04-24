package defineJSTL;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class ManagementTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		HttpSession session = pc.getSession();
		String teacherIdentity = (String) session.getAttribute("identity");
		Map<String,String> map = DatabaseAccess.getCourseByTeacherID(teacherIdentity);
		JspWriter out = getJspContext().getOut();
		for(String courseID : map.keySet()){
			String courseName = map.get(courseID);
			out.println("<li><a href=\"customization.jsp?courseID="+courseID+"\" target=\"_blank\" ><img src=\""+contextPath+"/upload/"+courseID+"/cover.png\">"+courseName+"</a></li>");
		}		
	}
}
