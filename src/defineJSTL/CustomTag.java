package defineJSTL;

import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import bean.Column;
import db.DatabaseAccess;

public class CustomTag extends SimpleTagSupport {
	private String courseID;
	private String tabIndex;
	
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		Column column = new Column();
		column.setCourseID(courseID);
		column.setTabIndex(tabIndex);
		Map<Integer,String> map = DatabaseAccess.getColumnByCourseIdAndId(column);
		for(int columnIndex : map.keySet()){
			String columnName = map.get(columnIndex);
			out.println("<a href=\"edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\" target=\"subIframe\">");
			out.println("<input type=\"text\" value=\""+columnName+"\" data-columnindex=\""+columnIndex+"\" readonly>");
			out.println("</a> ");
		}
	}
}
