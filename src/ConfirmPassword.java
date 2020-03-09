

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DatabaseAccess;

@WebServlet("/ConfirmPassword")
public class ConfirmPassword extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String currentPassword=request.getParameter("currentPassword");
		HttpSession session = request.getSession();
		String identity=(String) session.getAttribute("identity");
		Map<String,String> map=DatabaseAccess.getUserById(identity);
		String remotePassword=map.get("password");
		PrintWriter out = response.getWriter();
		if(currentPassword.equals(remotePassword)){
			out.println(1);
		}
		else{
			out.println(0);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
