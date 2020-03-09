

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import db.DatabaseAccess;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        // 获取表单数据
        String teacherIdentity=request.getParameter("teacherIdentity");
        String courseName=new String(request.getParameter("courseName").getBytes("ISO8859-1"),"UTF-8");
        String identity=request.getParameter("identity");
        String content=new String(request.getParameter("comment").getBytes("ISO8859-1"),"UTF-8");
        // 获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        try {
			Date date = sdf.parse(sdf.format(new Date()));
			Comment comment=new Comment();
			comment.setTeacherIdentity(teacherIdentity);
			comment.setCourseName(courseName);
			comment.setIdentity(identity);
			comment.setDate(date);
			comment.setComment(content);
			DatabaseAccess.addComment(comment);
			//重定向到课程页面
			response.sendRedirect("course.jsp?teacherIdentity="+teacherIdentity+"&courseName="+URLEncoder.encode(courseName)+"");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
