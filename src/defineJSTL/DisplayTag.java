package defineJSTL;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Column;
import db.DatabaseAccess;

public class DisplayTag extends SimpleTagSupport {
	private String teacherIdentity;
	private String courseName;
	private int tabIndex;
	private int columnIndex;
	
	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	
	public void doTag() throws JspException, IOException {
		Column column=new Column();
		column.setTeacherIdentity(teacherIdentity);
		column.setCourseName(courseName);
		column.setTabIndex(tabIndex);
		column.setColumnIndex(columnIndex);
		String content=DatabaseAccess.getContent(column);
		JspWriter out = getJspContext().getOut();
		out.println(content);
	}
}
