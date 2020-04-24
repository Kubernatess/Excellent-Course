

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import db.DatabaseAccess;

/**
 * Servlet implementation class AAdministrationServlet
 */
@WebServlet("/AdministrationServlet")
public class AdministrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String identity = request.getParameter("identity");
        String operation = request.getParameter("operation");
        if(operation.equals("add")||operation.equals("modify")){
        	String status = request.getParameter("status");
            String password = request.getParameter("password");
            String name = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
            String depart = new String(request.getParameter("depart").getBytes("ISO8859-1"),"UTF-8");
            String major = new String(request.getParameter("major").getBytes("ISO8859-1"),"UTF-8");
            User user = new User();
            user.setIdentity(identity);
            user.setIdentity(identity);
            user.setStatus(status);
            user.setPassword(password);
            user.setName(name);
            user.setDepart(depart);
            user.setMajor(major);
            DatabaseAccess.addUser(user);
        }
        else if(operation.equals("remove")){
        	DatabaseAccess.deleteUser(identity);
        }
        response.sendRedirect("./administrator/administration.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
