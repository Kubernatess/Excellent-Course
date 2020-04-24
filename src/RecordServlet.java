

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Record;
import db.DatabaseAccess;



@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentIdentity = (String) session.getAttribute("identity");
		String status = (String) session.getAttribute("status");
		String courseID = request.getParameter("courseID");
		String tabIndex = request.getParameter("tabIndex");
		int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
		Record record = new Record();
		record.setCourseID(courseID);
		record.setTabIndex(tabIndex);
		record.setColumnIndex(columnIndex);
		record.setStudentIdentity(studentIdentity);
		boolean isRecord = DatabaseAccess.isRecord(record);
		if(isRecord==false&&status.equals("student")){
			DatabaseAccess.addRecord(record);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
