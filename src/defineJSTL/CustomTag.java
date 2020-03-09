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

import db.DatabaseAccess;

public class CustomTag extends SimpleTagSupport {
	private String courseName;
	private int tabIndex;
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		HttpSession session=pc.getSession();
		String teacherIdentity=(String) session.getAttribute("identity");
		
		JspWriter out = getJspContext().getOut();
		Map<Integer,String> map=DatabaseAccess.selectColumnByIdAndNameAndIndex(teacherIdentity,courseName,tabIndex);
		for(int columnIndex:map.keySet()){
			String columnName = map.get(columnIndex);
			out.println("<li><a href=\"edit.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\" target=\"iframeB\">");
			out.println("<input type=\"text\" value=\""+columnName+"\" name=\"columnName\" tabindex=\""+columnIndex+"\" >");
			out.println("</a></li>");
		}
	}
}
