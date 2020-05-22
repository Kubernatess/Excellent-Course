

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Comment;
import db.DatabaseAccess;


@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取表单数据
        HttpSession session = request.getSession();
        String identity = (String) session.getAttribute("identity");
        Timestamp dateTime = new Timestamp(new Date().getTime());
        String courseID = request.getParameter("courseID");
        String tabIndex = request.getParameter("tabIndex")==null?"":request.getParameter("tabIndex");
        int columnIndex = request.getParameter("columnIndex")==null?0:Integer.parseInt(request.getParameter("columnIndex"));
        String opposite = request.getParameter("opposite");
        Timestamp lunchTime = request.getParameter("lunchTime")==null?null:Timestamp.valueOf(request.getParameter("lunchTime"));
        String content = new String(request.getParameter("comment").getBytes("ISO8859-1"),"UTF-8");
        
        Comment comment = new Comment();
        comment.setIdentity(identity);
        comment.setDateTime(dateTime);
		comment.setCourseID(courseID);
		comment.setTabIndex(tabIndex);
		comment.setColumnIndex(columnIndex);
		comment.setOpposite(opposite);
		comment.setLunchTime(lunchTime);		
		comment.setComment(content);
		DatabaseAccess.addComment(comment);
		
		if(tabIndex==""&&columnIndex==0){
			response.sendRedirect("course.jsp?courseID="+courseID);
		}
		else{
			response.sendRedirect("learning.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
