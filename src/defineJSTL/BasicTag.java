package defineJSTL;

import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import db.DatabaseAccess;

public class BasicTag extends SimpleTagSupport {
	
	private String courseID;

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void doTag() throws JspException, IOException {
		String courseName = "";
		String description = "";
		String team = "";
		// 只有传递了课程名参数时,才去获取指定课程的信息
		if(!courseID.equals("")){
			Map<String,Object> courseMap = DatabaseAccess.getCourseById(courseID);		
			courseName = (String) courseMap.get("name");
			description = (String) courseMap.get("description");
			team = (String) courseMap.get("team");
		}
		JspWriter out = getJspContext().getOut();
		out.println("<div><label>课程名称</label><input type=\"text\" name=\"courseName\" value=\""+courseName+"\" required /></div>");
		out.println("<div><label>课程描述</label><textarea name=\"description\" required>"+description+"</textarea></div>");
		out.println("<div><label>课程封面</label><input type=\"file\" accept=\"image/*\" name=\"cover\" required /></div>");
		out.println("<div><label>添加教师</label>");		
		// 教师团队显示方式
		String teams[] = team.split(",");
		for(String member:teams){
			out.println("<input type=\"text\" name=\"teacherName\" value=\""+member+"\" required>");
		}				
		out.println("<input type=\"image\" src=\"../image/add.png\" id=\"add\"> ");
		out.println("<input type=\"image\" src=\"../image/reduce.png\" id=\"reduce\"></div>");	
	}
}
