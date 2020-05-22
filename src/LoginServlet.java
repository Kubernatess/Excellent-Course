import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DatabaseAccess;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        // 获取表单数据
        String status = request.getParameter("status");
        String identity = request.getParameter("identity");
        String password = request.getParameter("password");
        // 如果不存在 session 会话,则创建一个 session 对象
        HttpSession session = request.getSession(true);
        
        // 判断是否为超级管理员
        if(identity.equals("admin")&&password.equals("admin")){
        	session.setAttribute("status","administrator");
        	response.sendRedirect("./administrator/administration.jsp");
        	return;
        }
        
        
        // 根据用户名获得数据
        Map<String,String> map = DatabaseAccess.getUser(identity);
        // 如果map为空,说明该用户名不存在
        if(map.size()<=0){
        	response.sendRedirect("login.jsp");
        	return;
        }
        // 如果密码不相等
        if(!map.get("password").equals(password)){
        	response.sendRedirect("login.jsp");
        	return;
        }
        // 如果身份不符合
        if(!map.get("status").equals(status)){
        	response.sendRedirect("login.jsp");
        	return;
        }
        // 用户名存在,密码也正确,登陆成功,
        session.setAttribute("identity",identity);
        session.setAttribute("status",status);
        session.setAttribute("depart",map.get("depart"));
        session.setAttribute("username",map.get("username"));
        session.setAttribute("password",map.get("password"));
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
