package defineJSTL;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class LearningTag extends SimpleTagSupport {
	private String courseName;
	private String teacherIdentity;

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Map<Integer,String> map=DatabaseAccess.selectTabByIdAndName(teacherIdentity,courseName);
		for(int k:map.keySet()){
			String tagName=map.get(k);
			out.println("<a href=\"learn.jsp?teacherIdentity="+teacherIdentity+"&courseName="+URLEncoder.encode(courseName)+"&tabIndex="+k+"\" target=\"iframeA\">"+tagName+"</a>");
		}
	}
}
