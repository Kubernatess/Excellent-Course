package filter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.jsp.PageContext;

import db.DatabaseAccess;

/**
 * Servlet Filter implementation class InitFilter
 */
@WebFilter("/*")
public class InitFilter implements Filter {

    public InitFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 上传目录全局变量
		String uploadPath = request.getRealPath("/")+"upload";
		request.getServletContext().setAttribute("uploadPath",uploadPath);
		
		// WEB发布目录全局变量
		String contextPath = request.getServletContext().getContextPath();
		request.getServletContext().setAttribute("contextPath",contextPath);
		
		// 创建所有用户名对应的文件夹
		List<String> list = DatabaseAccess.getAllIdentity();
		for(int i=0;i<list.size();i++){
			//如果文件夹不存在,则创建文件夹
			String dirPath=uploadPath+File.separator+list.get(i);
			File dirFile=new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
