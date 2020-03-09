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
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		JspWriter out = getJspContext().getOut();
		// 获得指定课程的信息
		Map<String,String> courseMap=DatabaseAccess.getCourseByIdAndName(teacherIdentity,courseName);	
		String description = courseMap.get("description");
		String cover = courseMap.get("cover");
		String team = courseMap.get("team");
		// 封面部分
		out.println("<div class=\"cover\">");
		out.println("<img src=\""+contextPath+"/upload/"+teacherIdentity+"/"+courseName+"/"+cover+"\">");
		out.println("<p>"+courseName+"</p>");
		out.println("<p>"+description+"</p>");
		out.println("<p>已有90607人参加</p>");
		out.println("<a href=\"learning.jsp?teacherIdentity="+teacherIdentity+"&courseName="+courseName+"\" target=\"_blank\">立即参加</a>");
		out.println("</div>");
		// 课程详细部分
		out.println("<div class=\"content\">");
		out.println("<span>课程详情</span><span>课程评价(0)</span>");
		out.println("<hr>");
		out.println("<div class=\"detail\">");
		out.println("<p>"+description+"</p>");

		// 获得所有栏目信息
		List<Map<String,Object>> list=DatabaseAccess.selectTabAndColumnByIdAndName(teacherIdentity,courseName);
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
		
		// 课程评论部分
		out.println("<div class=\"comment\">");
		out.println("<h2>所有评论</h2>");
		out.println("<form method=\"POST\" action=\""+contextPath+"/CommentServlet\" id=\"review\">");
		out.println("<textarea cols=\"108\" rows=\"8\" name=\"comment\"></textarea>");
		out.println("<input type=\"submit\" value=\"发表评论\" />");
		out.println("</form><ul>");
		List<Map<String,Object>> linkedList = DatabaseAccess.getAllCommentByIdAndName(teacherIdentity,courseName);
		Iterator iter = linkedList.iterator();
		while(iter.hasNext()){
			Map<String,Object> map = (Map<String, Object>) iter.next();
			String username = (String) map.get("username");
			String dateStr = (String) map.get("date").toString();
			String comment = (String) map.get("comment");
			out.println("<li><span>作者: "+username+"</span><span>"+dateStr+"</span><p>"+comment+"</p><hr/></li>");
		}
		out.println("</ul></div>");
		
		out.println("</div>");
		
		// 根据教师工号获得所属系别
		Map<String,String> userMap=DatabaseAccess.getUserById(teacherIdentity);
		String depart=userMap.get("depart");
		out.println("<aside>");
		out.println("<div class=\"teach\">");
		out.println("<p>"+depart+"</p>");
		out.println("<hr>");
		// 获得教学团队信息
		String[] teams=team.split(",");
		out.println("<span></span><span></span>");
		out.println("<ul>");
		for(String menber : teams){
			out.println("<li><img src=\"image/user-circle.png\"><span>"+menber+"</span></li>");
		}
		out.println("</ul>");
		out.println("</div>");
	}
}
