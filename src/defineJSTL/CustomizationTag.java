package defineJSTL;

import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import db.DatabaseAccess;

public class CustomizationTag extends SimpleTagSupport {
	private String courseID;

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}


	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Map<String,String> map = DatabaseAccess.getTabByCourseId(courseID);
		for(String tabIndex : map.keySet()){
			String tabName = map.get(tabIndex);
			out.println("<a href=\"custom.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"\" target=\"mainIframe\">");
			out.println("<input type=\"text\" value=\""+tabName+"\" data-tabindex=\""+tabIndex+"\" readonly />");
			out.println("</a> ");
		}
	}
}
