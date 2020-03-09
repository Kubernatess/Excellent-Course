package defineJSTL;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class LearnTag extends SimpleTagSupport {
	private String courseName;
	private String teacherIdentity;
	private int tabIndex;

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Map<Integer,String> map=DatabaseAccess.selectColumnByIdAndNameAndIndex(teacherIdentity,courseName,tabIndex);
		for(int columnIndex:map.keySet()){
			String columnName = map.get(columnIndex);
			out.println("<li>");
			out.println("<a href=\"display.jsp?teacherIdentity="+teacherIdentity+"&courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\" target=\"iframeB\">");
			out.println(columnName);
			out.println("</a></li>");
		}
	}
}
