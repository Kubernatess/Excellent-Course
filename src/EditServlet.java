

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subcolumn;
import db.DatabaseAccess;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String teacherIdentity=(String) session.getAttribute("identity");
        String courseName=new String(request.getParameter("courseName").getBytes("ISO8859-1"),"UTF-8");
        int tabIndex=Integer.parseInt(request.getParameter("tabIndex"));
        int columnIndex=Integer.parseInt(request.getParameter("columnIndex"));
        int subcolumnIndex=Integer.parseInt(request.getParameter("subcolumnIndex"));
        String content=new String(request.getParameter("content").getBytes("ISO8859-1"),"UTF-8");
        Subcolumn subcolumn=new Subcolumn();
        subcolumn.setTeacherIdentity(teacherIdentity);
        subcolumn.setCourseName(courseName);
        subcolumn.setTabIndex(tabIndex);
        subcolumn.setColumnIndex(columnIndex);
        subcolumn.setSubcolumnIndex(subcolumnIndex);
        subcolumn.setContent(content);
        DatabaseAccess.updateContent(subcolumn);
        response.sendRedirect("./teacher/edit.jsp?courseName="+URLEncoder.encode(courseName)+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"&subcolumnIndex="+subcolumnIndex+"");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
