package defineJSTL;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Column;
import db.DatabaseAccess;

public class EditTag extends SimpleTagSupport {
	private String courseName;
	private int tabIndex;
	private int columnIndex;
	
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
		PageContext pc=(PageContext) getJspContext();
		HttpSession session=pc.getSession();
		String teacherIdentity=(String) session.getAttribute("identity");
		Column column=new Column();
		column.setTeacherIdentity(teacherIdentity);
		column.setCourseName(courseName);
		column.setTabIndex(tabIndex);
		column.setColumnIndex(columnIndex);
		String content=DatabaseAccess.getContent(column);
		JspWriter out = getJspContext().getOut();
		if(content==null||content.equals("")){
			out.println("<span>在此处编辑</span>");
		}
		else{
			out.println(content);
		}
	}
	
}
