

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Column;
import db.DatabaseAccess;

/**
 * Servlet implementation class TestingServlet
 */
@WebServlet("/TestingServlet")
public class TestingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public TestingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseID = request.getParameter("courseID");
        String tabIndex = request.getParameter("tabIndex");
        int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
        // 获取测试题的答案
        Column column = new Column();
        column.setCourseID(courseID);
        column.setTabIndex(tabIndex);
        column.setIndex(columnIndex);
        Map<String,String> map = DatabaseAccess.getColumnById(column);
        String answer = map.get("answer");
        String answers[] = answer.split(",");
        float step = 100/(answers.length-1);
        float score = 0;
        // 同样也要字符串拆解操作
        String combination = request.getParameter("combination");
        String strs[] = combination.split(",");
        for(int i=1; i<strs.length; i++){
        	String[] options = request.getParameterValues(strs[i]);
        	// 判断单选题
        	if(options.length==1){
        		if(answers[i].equals(options[0])){
        			score += step;
        		}
        	}
        	// 判断多选题
        	else{
        		int j = 0;
        		// 前提条件:用户选择的答案和正确的答案数目要一致
        		if(options.length==answers[i].length()){
        			for(; j<options.length; j++){
                		// 如果是多选题,哪怕有一个没选对,都不得分
            			String ans = answers[i].charAt(j)+"";
            			if(!ans.equals(options[j])){
                			break;
                		}
                	}
            		if(j==options.length){
            			score += step;
            		}
        		}
        	}
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        out.println("本次测试您获得了"+score+"分");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
