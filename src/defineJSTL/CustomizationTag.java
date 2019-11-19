package defineJSTL;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class CustomizationTag extends SimpleTagSupport {
	private String courseName;
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		HttpSession session=pc.getSession();
		String teacherIdentity=(String) session.getAttribute("identity");
		JspWriter out = getJspContext().getOut();
		Map<Integer,String> map=DatabaseAccess.selectTagNameByIdAndName(teacherIdentity,courseName);
		for(int k:map.keySet()){
			String tagName=map.get(k);
			out.println("<a href=\"custom.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+k+"\" target=\"iframeA\"><input type=\"text\" value=\""+tagName+"\" name=\"tag\"></a>");
		}
	}
}
