package defineJSTL;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Subcolumn;
import db.DatabaseAccess;

public class DisplayTag extends SimpleTagSupport {
	private String teacherIdentity;
	private String courseName;
	private int tabIndex;
	private int columnIndex;
	private int subcolumnIndex;
	
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
	public void setSubcolumnIndex(int subcolumnIndex) {
		this.subcolumnIndex = subcolumnIndex;
	}
	
	public void doTag() throws JspException, IOException {
		Subcolumn subcolumn=new Subcolumn();
		subcolumn.setTeacherIdentity(teacherIdentity);
		subcolumn.setCourseName(courseName);
		subcolumn.setTabIndex(tabIndex);
		subcolumn.setColumnIndex(columnIndex);
		subcolumn.setSubcolumnIndex(subcolumnIndex);
		String content=DatabaseAccess.getContent(subcolumn);
		JspWriter out = getJspContext().getOut();
		out.println(content);

	}
}
