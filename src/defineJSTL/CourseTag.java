package defineJSTL;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import bean.Column;
import bean.Course;
import bean.Record;
import bean.Tab;
import db.DatabaseAccess;

public class CourseTag extends SimpleTagSupport {
	private String courseID;

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void doTag() throws JspException, IOException {
		PageContext pc = (PageContext) getJspContext();
		HttpSession session = pc.getSession();
		String studentIdentity = (String) session.getAttribute("identity");
		String status = (String) session.getAttribute("status");
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		JspWriter out = getJspContext().getOut();
		// 获得指定课程的信息
		Map<String,Object> courseMap = DatabaseAccess.getCourseById(courseID);
		String courseName = (String) courseMap.get("name");
		String description = (String) courseMap.get("description");
		String team = (String) courseMap.get("team");
		int visitors = DatabaseAccess.getVisitorsByCourseId(courseID);
		String teacherIdentity = (String) courseMap.get("teacherIdentity");
		// 封面部分
		out.println("<table><tr>");
		out.println("<th rowspan=\"4\"><img src=\""+contextPath+"/upload/"+courseID+"/cover.png\"></th>");
		out.println("<th>"+courseName+"</th></tr>");
		out.println("<tr><td><p>"+description+"</p></td></tr>");		
		out.println("<tr><td>已有"+visitors+"人参加</td></tr>");
		out.println("<tr><td>");
		if(status.equals("student")){
			Record record = new Record();
			record.setStudentIdentity(studentIdentity);
			record.setCourseID(courseID);
			boolean isjoin = DatabaseAccess.isjoined(record);
			if(isjoin){
				out.println("<a href=\"learning.jsp?courseID="+courseID+"&courseName="+courseName+"\" target=\"_blank\" style=\"background-color:#FF6633\">已经加入,继续学习</a>");
			}
			else{
				out.println("<a href=\"learning.jsp?courseID="+courseID+"&courseName="+courseName+"\" target=\"_blank\">立即参加</a>");
			}
		}
		else{
			out.println("<a href=\"learning.jsp?courseID="+courseID+"&courseName="+courseName+"\" target=\"_blank\">立即参加</a>");
		}
		out.println("</td></td></table>");
		
		// 课程详细部分
		out.println("<article>");
		out.println("<span class=\"hovering\">课程详情</span><span>课程评价(11)</span><hr>");
		out.println("<section>");
		out.println("<p>"+description+"</p>");
		// 获得所有栏目信息
		Map<String,String> tabMap = DatabaseAccess.getTabByCourseId(courseID);
		for(String tabIndex : tabMap.keySet()){
			String tabName = tabMap.get(tabIndex);
			out.println("<h3>"+tabName+"</h3>");
			Column column = new Column();
			column.setCourseID(courseID);
			column.setTabIndex(tabIndex);
			Map<Integer,String> columnMap = DatabaseAccess.getColumnByCourseIdAndId(column);
			for(int columnIndex : columnMap.keySet()){
				String columnName = columnMap.get(columnIndex);
				out.println("<p>"+columnName+"</p>");
			}
		}
		out.println("</section>");
		
		// 课程评论部分
		out.println("<section hidden>");
		out.println("<h2>所有评论</h2>");
		//out.println("<form method=\"POST\" action=\""+contextPath+"/ReviewServlet\" id=\"review\">");
		out.println("<form method=\"POST\" action=\"/ReviewServlet\">");
		out.println("<textarea cols=\"108\" rows=\"8\" name=\"review\"></textarea>");
		out.println("<input type=\"hidden\" name=\"courseID\">");
		out.println("<input type=\"submit\" value=\"发表评论\" />");
		out.println("</form><ul>");
		List<Map<String,Object>> linkedList = DatabaseAccess.getReviewsByCourseId(courseID);
		Iterator<Map<String, Object>> iter = linkedList.iterator();
		while(iter.hasNext()){
			Map<String,Object> map = iter.next();
			String username = (String) map.get("username");
			String dateStr = (String) map.get("date").toString();
			String comment = (String) map.get("comment");
			out.println("<li><span>作者: "+username+"</span><span>"+dateStr+"</span><p>"+comment+"</p><hr/></li>");
		}
		out.println("</ul></section></article>");
		
		// 根据教师工号获得所属系别
		Map<String,String> userMap = DatabaseAccess.getUserById(teacherIdentity);
		String depart = userMap.get("depart");
		out.println("<aside><section>");
		out.println("<p>"+depart+"</p><hr>");
		// 获得教学团队信息
		String[] teams = team.split(",");
		out.println("<dfn></dfn><span></span>");
		for(String menber : teams){
			out.println("<figure><img src=\"image/user-circle.png\"><span>"+menber+"</span></figure>");
		}
		out.println("</section>");
	}
}
