

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

@WebServlet("/RemoveComment")
public class RemoveComment extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String identity = (String) session.getAttribute("identity");
        String dateTimeStr = request.getParameter("dateTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
			date = sdf.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Timestamp dateTime = new Timestamp(date.getTime());
        DatabaseAccess.removeComment(identity,dateTime);
        
        // courseID、tabIndex、columnIndex仅仅用于返回页面时传递参数
        String courseID = request.getParameter("courseID");        
        if(request.getParameter("tabIndex")==null&&request.getParameter("columnIndex")==null){
        	response.sendRedirect("course.jsp?courseID="+courseID);
        }
        else{
        	String tabIndex = request.getParameter("tabIndex");
            int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
        	response.sendRedirect("learning.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
