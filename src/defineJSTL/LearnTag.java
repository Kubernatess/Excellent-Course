package defineJSTL;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class LearnTag extends SimpleTagSupport {
	private String courseName;
	private String teacherIdentity;
	private int tabIndex;

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}
	
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		List<Map<String,Object>> list=DatabaseAccess.selectColumnAndSubcolumnByIdAndNameAndIndex(teacherIdentity,courseName,tabIndex);
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Map<String,Object> map=(Map<String,Object>) iterator.next();
			int columnIndex=(int) map.get("columnIndex");
			String columnName=(String) map.get("columnName");
			out.println("<li tabindex=\""+columnIndex+"\"><span>▶</span>"+columnName+"");
			// 输出子栏目
			Map<Integer,String> subMap=(Map<Integer,String>)map.get("subMap");
			out.println("<ul>");
			for(int k:subMap.keySet()){
				String subcolumnName=subMap.get(k);
				out.println("<li><a href=\"display.jsp?teacherIdentity="+teacherIdentity+"&courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"&subcolumnIndex="+k+"\" target=\"iframeB\">"+subcolumnName+"</a></li>");
			}
			out.println("</ul>");
			out.println("</li>");
		}
	}
}
