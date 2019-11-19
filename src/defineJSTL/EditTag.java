package defineJSTL;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Subcolumn;
import db.DatabaseAccess;

public class EditTag extends SimpleTagSupport {
	private String courseName;
	private int tabIndex;
	private int columnIndex;
	private int subcolumnIndex;
	
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
		PageContext pc=(PageContext) getJspContext();
		HttpSession session=pc.getSession();
		String teacherIdentity=(String) session.getAttribute("identity");
		Subcolumn subcolumn=new Subcolumn();
		subcolumn.setTeacherIdentity(teacherIdentity);
		subcolumn.setCourseName(courseName);
		subcolumn.setTabIndex(tabIndex);
		subcolumn.setColumnIndex(columnIndex);
		subcolumn.setSubcolumnIndex(subcolumnIndex);
		String content=DatabaseAccess.getContent(subcolumn);
		JspWriter out = getJspContext().getOut();
		if(content==null){
			out.println("<span>在此处编辑</span>");
		}
		else{
			out.println(content);
		}
	}
	
}
