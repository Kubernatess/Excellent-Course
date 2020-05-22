
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.Column;
import bean.Record;
import db.DatabaseAccess;


@WebServlet("/ObjectiveServlet")
public class ObjectiveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public ObjectiveServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		if(!status.equals("student")){
			return;
		}
        String studentIdentity = (String) session.getAttribute("identity");
		String courseID = request.getParameter("courseID");
        String tabIndex = request.getParameter("tabIndex");
        int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));       
        // 获取测试题的答案
        Column column = new Column();
        column.setCourseID(courseID);
        column.setTabIndex(tabIndex);
        column.setIndex(columnIndex);
        Map<String,String> map = DatabaseAccess.getColumn(column);
        // 为了防止报错
        if(!map.containsKey("answer")){
        	return;
        }
        String[] answers = map.get("answer").split(",");
        float step = 100/(answers.length-1);
        float score = 0;
        for(int i=1; i<answers.length; i++){
        	String[] replies = request.getParameterValues(String.valueOf(i));
        	// 判断单选题
        	if(replies.length==1){
        		if(answers[i].equals(replies[0])){
        			score += step;
        		}
        	}
        	// 判断多选题
        	else{
        		String reply = "";
        		for(int j=0; j<replies.length; j++){
        			reply += replies[j];
        		}
        		if(answers[i].equals(reply)){
        			score += step;
        		}
        	}
        }
        // 把分数记录到数据库
        Record record = new Record();
        record.setCourseID(courseID);
        record.setTabIndex(tabIndex);
        record.setColumnIndex(columnIndex);
        record.setStudentIdentity(studentIdentity);
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        // 先判断该学生是否已经有成绩了,就是不允许学生添加第二次的分数
        Map<String,Object> recordMap = DatabaseAccess.getRecord(record);
        if((float)recordMap.get("score")==-1){
        	record.setScore(score);
            DatabaseAccess.addScore(record);
            out.println("本次测试您获得了"+score+"分");
        }
        else{
        	out.println("您已经参加过本次测试");
        }
               
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
