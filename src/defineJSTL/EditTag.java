package defineJSTL;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Column;
import db.DatabaseAccess;

public class EditTag extends SimpleTagSupport {
	private String courseID;
	private String tabIndex;
	private int columnIndex;

	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public void doTag() throws JspException, IOException {
		Column column = new Column();
		column.setCourseID(courseID);
		column.setTabIndex(tabIndex);
		column.setIndex(columnIndex);
		Map<String,String> map = DatabaseAccess.getColumnById(column);
		String content = map.get("content");
		JspWriter out = getJspContext().getOut();
		if(content==null||content.equals("")){
			out.println("<span>在此处编辑</span>");
		}
		else{
			out.println(content);
		}
	}
	
}
