package defineJSTL;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Column;
import bean.Record;
import db.DatabaseAccess;

public class RecordTag extends SimpleTagSupport {
	private boolean existVideoElement;
	private boolean isAllFinished;
	private String courseID;
	private String tabIndex;
	private int columnIndex;
	public void setExistVideoElement(boolean existVideoElement) {
		this.existVideoElement = existVideoElement;
	}
	public void setAllFinished(boolean isAllFinished) {
		this.isAllFinished = isAllFinished;
	}
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
		PageContext pc=(PageContext) getJspContext();
		HttpSession session = pc.getSession();
		String studentIdentity = (String) session.getAttribute("identity");
		Record record = new Record();
		record.setCourseID(courseID);
		record.setTabIndex(tabIndex);
		record.setColumnIndex(columnIndex);
		record.setStudentIdentity(studentIdentity);
		boolean isRecord = DatabaseAccess.isRecord(record);
		if(isRecord==false){
			DatabaseAccess.addRecord(record);
		}
	}
}
