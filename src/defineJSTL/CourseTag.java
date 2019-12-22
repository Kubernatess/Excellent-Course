package defineJSTL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class CourseTag extends SimpleTagSupport {
	private String courseName;
	
	private String teacherIdentity;
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void setTeacherIdentity(String teacherIdentity) {
		this.teacherIdentity = teacherIdentity;
	}
	
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		String path=pc.getServletContext().getContextPath();
		JspWriter out = getJspContext().getOut();
		// 获得指定课程的信息
		Map<String,String> map=DatabaseAccess.getCourseByIdAndName(teacherIdentity,courseName);	
		String description=map.get("description");
		String depart=map.get("depart");
		String cover=map.get("cover");
		// 封面部分
		out.println("<div class=\"cover\">");
		out.println("<img src=\""+path+"/upload/"+teacherIdentity+"/"+courseName+"/"+cover+"\">");
		out.println("<p>"+courseName+"</p>");
		out.println("<p>"+description+"</p>");
		out.println("<p>已有90607人参加</p>");
		out.println("<a href=\"#\">立即参加</a>");
		out.println("</div>");
		// 课程详细部分
		out.println("<div class=\"detail\">");
		out.println("<span>课程详情</span><span>课程评价(11)</span>");
		out.println("<hr>");
		out.println("<p>"+description+"</p>");

		
		// 获得所有栏目信息
		List<Map<String,Object>> list=DatabaseAccess.selectTabAndColumnAndSubcolumnByIdAndName(teacherIdentity,courseName);
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Map<String,Object> tabMap=(Map<String, Object>) iterator.next();
			String tabName=(String) tabMap.get("tabName");
			List<String> columnList=(List<String>) tabMap.get("columnList");
			out.println("<h3>"+tabName+"</h3>");
			for(int i=0;i<columnList.size();i++){
				out.println("<p>"+columnList.get(i)+"</p>");
			}
		}
		out.println("</div>");
		// 获得教学团队信息
		List<String> teamList=DatabaseAccess.selectTeamByIdAndName(teacherIdentity,courseName);
		out.println("<aside>");
		out.println("<div class=\"teach\">");
		out.println("<p>"+depart+"</p>");
		out.println("<hr>");
		out.println("<span></span><span>7位授课老师</span>");
		out.println("<ul>");
		for(int i=0;i<teamList.size();i++){
			out.println("<li><img src=\"image/user-circle.png\"><span>"+teamList.get(i)+"</span></li>");
		}
		out.println("</ul>");
		out.println("</div>");
	}
}
