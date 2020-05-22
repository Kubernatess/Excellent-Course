import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Record;
import db.DatabaseAccess;



@WebServlet("/CompletionServlet")
public class CompletionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		if(!status.equals("student")){
			return;
		}
		String studentIdentity = (String) session.getAttribute("identity");
		String courseID = request.getParameter("courseID");
		String tabIndex = request.getParameter("tabIndex");
		int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
		Record record = new Record();
		record.setCourseID(courseID);
		record.setTabIndex(tabIndex);
		record.setColumnIndex(columnIndex);
		record.setStudentIdentity(studentIdentity);
		DatabaseAccess.addCompletion(record);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
