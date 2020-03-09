package defineJSTL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class BasicTag extends SimpleTagSupport {
	
	private String courseName;
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void doTag() throws JspException, IOException {
		PageContext pc=(PageContext) getJspContext();
		HttpSession session=pc.getSession();
		String teacherIdentity=(String) session.getAttribute("identity");
		String depart=(String) session.getAttribute("depart");
		// 通行证
		if(teacherIdentity==null){
			return;
		}
		
		String description="";
		String cover="";
		String major="";
		String team="";
		Map<String,String> courseMap=new HashMap<>();	
		
		// 只有传递了课程名参数时,才去执行的一些操作
		if(!courseName.equals("")){
			// 获得指定课程的信息
			courseMap=DatabaseAccess.getCourseByIdAndName(teacherIdentity,courseName);		
			description=courseMap.get("description");
			cover=courseMap.get("cover");
			major=courseMap.get("major");
			team=courseMap.get("team");
			
			// 把多余的课程封面删除
			String uploadPath = (String) pc.getServletContext().getAttribute("uploadPath");
			String dirPath=uploadPath+File.separator+teacherIdentity+File.separator+courseName;
			File dirFile=new File(dirPath);
			File[] files=dirFile.listFiles();
	        for(File f:files){
	        	if(!cover.equals(f.getName())){
	        		f.delete();
	        	}
	        }			
		}		

		// 准备一个Map和List,存放所有系别和专业		
		Map<String,List<String>> map=new HashMap<>();
		List<String> list=new LinkedList<>();
		list.add("电子与计算机工程");
		list.add("数据科学与大数据技术");
		list.add("软件工程");
		map.put("软件工程系",list);
		list=new LinkedList<>();
		list.add("网络工程");
		list.add("信息管理与信息系统");
		map.put("网络技术系",list);
		list=new LinkedList<>();
		list.add("电子信息工程");
		list.add("通信工程");
		list.add("智能科学与技术");
		list.add("自动化");
		map.put("电子系",list);
		list=new LinkedList<>();
		list.add("数字媒体技术");
		list.add("动画");
		list.add("网络与新媒体");
		map.put("游戏系",list);
		list=new LinkedList<>();
		list.add("环境设计");
		list.add("风景园林");
		list.add("视觉传达设计");
		list.add("数字媒体艺术");
		list.add("产品设计");
		map.put("数码媒体系",list);
		list=new LinkedList<>();
		list.add("人力资源管理");
		list.add("市场营销");
		list.add("工商管理");
		list.add("物流管理");
		list.add("行政管理");
		map.put("管理系",list);
		list=new LinkedList<>();
		list.add("国际经济与贸易");
		list.add("电子商务");
		map.put("国际经贸系",list);
		list=new LinkedList<>();
		list.add("会计学");
		list.add("财务管理");
		map.put("财会系",list);
		list=new LinkedList<>();
		list.add("英语");
		list.add("日语");
		map.put("外语系",list);
		list=new LinkedList<>();
		list.add("计算机科学与技术");
		list.add("物联网工程");
		map.put("计算机系",list);
		
		JspWriter out = getJspContext().getOut();
		out.println("<div><label for=\"courseName\">课程名称</label><input type=\"text\" name=\"courseName\" id=\"courseName\" value=\""+courseName+"\" required /></div>");
		out.println("<div><label for=\"description\">课程描述</label><textarea id=\"description\" name=\"description\" required>"+description+"</textarea></div>");
		out.println("<div><label for=\"major\">所属专业</label>");
		out.println("<select id=\"major\" name=\"major\" required>");
		// 循环输出所有专业
		List<String> majors=new LinkedList<>();
		if(depart!=null){
			majors=map.get(depart);
		}
		for(int i=0;i<majors.size();i++){
			String selected="";
			if(majors.get(i).equals(major)){
				selected="selected";
			}
			out.println("<option "+selected+" value=\""+majors.get(i)+"\">"+majors.get(i)+"</option>");
		}	
		out.println("</optgroup></select></div>");
		out.println("<div><label for=\"cover\">课程封面</label><input type=\"file\" accept=\"image/*\" name=\"cover\" id=\"cover\" required /></div>");
		out.println("<div><label for=\"teach\">添加教师</label>");		
		// 教师团队显示方式
		String teams[]=team.split(",");
		for(String member:teams){
			out.println("<input type=\"text\" name=\"teacherName\" value=\""+member+"\" required>");
		}				
		out.println("<button type=\"button\" id=\"add\"><img src=\"../image/add.png\"></button>");
		out.println("<button type=\"button\" id=\"reduce\"><img src=\"../image/reduce.png\"></button></div>");
		
	}
}
