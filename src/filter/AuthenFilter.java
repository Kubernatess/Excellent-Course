package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/teacher/*")
public class AuthenFilter implements Filter {

    public AuthenFilter() {

    }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 如果不存在 session 会话,则创建一个 session 对象
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		String status = (String) session.getAttribute("status");
		if(status==null){
			((HttpServletResponse) response).sendRedirect("../login.jsp");
		}
		else if(!status.equals("老师")){
			((HttpServletResponse) response).sendRedirect("../index.jsp");
		}
		else{
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
