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

public class ScheduleTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		HttpSession session = pc.getSession();
		String studentIdentity = (String) session.getAttribute("identity");
		List<Map<String,Object>> list = DatabaseAccess.getPageviewsByStudentId(studentIdentity);
		JspWriter out = getJspContext().getOut();
		Iterator<Map<String,Object>> iterator = list.iterator();
		while(iterator.hasNext()){
			Map<String,Object> map = iterator.next();
			String courseID = (String) map.get("courseID");
			String courseName = (String) map.get("courseName");
			int hitcount = (int) map.get("hitcount");
			out.println("<a href=\"../learnin.jsp?courseID="+courseID+"&courseName="+courseName+"\" target=\"_blank\">");
			out.println("<img src=\""+contextPath+"/upload/"+courseID+"/cover.png\">");
			out.println("<p>"+courseName+"</p>");
			out.println("<progress value=\""+hitcount+"\" max=\"100\"></progress>");
			out.println("<samp></samp>");
		}
	}
}
