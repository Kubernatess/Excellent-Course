

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import db.DatabaseAccess;

@WebServlet("/AdministrationServlet")
public class AdministrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identity = request.getParameter("identity");
        // 如果没有传递其他的参数,则执行删除操作
        if(request.getParameter("status")==null){
        	DatabaseAccess.deleteUser(identity);
        }
        // 如果传递了别的参数,则执行插入更新操作
        else{
        	String status = request.getParameter("status");
            String password = request.getParameter("password");
            String username = new String(request.getParameter("username").getBytes("ISO8859-1"),"UTF-8");
            String depart = new String(request.getParameter("depart").getBytes("ISO8859-1"),"UTF-8");
            User user = new User();
            user.setIdentity(identity);
            user.setStatus(status);
            user.setPassword(password);
            user.setUsername(username);
            user.setDepart(depart);
            DatabaseAccess.addUser(user);
        }
        response.sendRedirect("./administrator/administration.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
