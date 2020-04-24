package defineJSTL;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Course;
import bean.Pageview;
import bean.Tab;
import db.DatabaseAccess;

public class LearningTag extends SimpleTagSupport {
	private String courseID;

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		HttpSession session = pc.getSession();
		String studentIdentity = (String) session.getAttribute("identity");
		String status = (String) session.getAttribute("status");
		JspWriter out = getJspContext().getOut();
		Map<String,String> map = DatabaseAccess.getTabByCourseId(courseID);
		for(String tabIndex : map.keySet()){
			String tabName = map.get(tabIndex);
			out.println("<a href=\"learn.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"\" target=\"mainIframe\">"+tabName+"</a>");
		}
		
		// 统计访问量加一
		if(status.equals("student")){
			Pageview pageview = new Pageview();
			pageview.setStudentIdentity(studentIdentity);
			pageview.setCourseID(courseID);
			DatabaseAccess.addPageview(pageview);
		}
		
	}
}
