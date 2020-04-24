package defineJSTL;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import db.DatabaseAccess;

public class PersonalTag extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		String contextPath = (String) pc.getServletContext().getAttribute("contextPath");
		HttpSession session=pc.getSession();
		String identity=(String) session.getAttribute("identity");
		Map<String,String> map = DatabaseAccess.getUserById(identity);
		String username=map.get("name");
		String depart=map.get("depart");
		String major=map.get("major");
		String status=map.get("status");
		JspWriter out = getJspContext().getOut();
		out.println("<div>工号<span>"+identity+"</span></div>");
		out.println("<div>密码<span>********</span><a>╲╱</a></div>");
		out.println("<form method=\"POST\" action=\""+contextPath+"/ModifyPassword\" hidden>");
		out.println("<input type=\"password\" maxlength=\"16\" name=\"currentPassword\" placeholder=\"当前密码\" />最多16个字符,可以包含字母、数字和特殊字符");
		out.println("<input type=\"password\" maxlength=\"16\" name=\"recentPassword\" placeholder=\"新的密码\" />");
		out.println("<input type=\"password\" maxlength=\"16\" name=\"confirmPassword\" placeholder=\"确认密码\" />");	
		out.println("<input type=\"button\" value=\"Submit\" class=\"btn-primary\">");
		out.println("<input type=\"reset\" value=\"Reset\" class=\"btn-default\">");
		out.println("</form>");
		out.println("<div>姓名<span>"+username+"</span></div>");
		out.println("<div>系别<span>"+depart+"</span></div>");
		out.println("<div>专业<span>"+major+"</span></div>");
		out.println("<div>身份<span>"+status+"</span></div>");
	}
}
