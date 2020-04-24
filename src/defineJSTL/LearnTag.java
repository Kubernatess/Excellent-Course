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

import bean.Column;
import bean.Record;
import db.DatabaseAccess;

public class LearnTag extends SimpleTagSupport {
	private String courseID;
	private String tabIndex;
	
	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		HttpSession session = pc.getSession();
		String studentIdentity = (String) session.getAttribute("identity");
		JspWriter out = getJspContext().getOut();
		Column column = new Column();
		column.setCourseID(courseID);
		column.setTabIndex(tabIndex);
		Map<Integer,String> map = DatabaseAccess.getColumnByCourseIdAndId(column);
		for(int columnIndex : map.keySet()){
			// 判断某个学生是否打了绿灯
			Record record = new Record();
			record.setCourseID(courseID);
			record.setTabIndex(tabIndex);
			record.setColumnIndex(columnIndex);
			record.setStudentIdentity(studentIdentity);
			boolean isRecord = DatabaseAccess.isRecord(record);
			String columnName = map.get(columnIndex);
			out.println("<a href=\"display.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\" target=\"subIframe\">");
			if(isRecord){
				out.println("<img src=\"image/correct.png\">");
			}
			else{
				out.println("<img src=\"image/correct.png\" style=\"visibility:hidden;\">");
			}
			out.println(columnName+"</a>");
		}
	}
}
