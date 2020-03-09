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
		HttpSession session=pc.getSession();
		String teacherIdentity=(String) session.getAttribute("identity");
		List<Map<String,String>> list=DatabaseAccess.getCourseById(teacherIdentity);
		JspWriter out = getJspContext().getOut();
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Map<String,String> map=(Map<String, String>) iterator.next();
			String courseName=map.get("courseName");
			String cover=map.get("cover");
			out.println("<li><a href=\"customization.jsp?courseName="+courseName+"\" target=\"_blank\" ><img src=\""+contextPath+"/upload/"+teacherIdentity+"/"+courseName+"/"+cover+"\">"+courseName+"</a></li>");
		}		
	}
}
