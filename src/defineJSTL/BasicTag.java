package defineJSTL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
		String description="";
		String depart="";
		String template="默认";
		String cover="";
		String major="";		
		Map<String,String> map=new HashMap<>();
		
		List<String> teamList=new ArrayList<>();		
		// 准备一个Map,存放所有系别		
		Map<String,String> departMap=new LinkedHashMap<>();
		departMap.put("软件工程系","<option value=\"软件工程系\">软件工程系</option>");
		departMap.put("网络技术系","<option value=\"网络技术系\">网络技术系</option>");
		departMap.put("电子系","<option value=\"电子系\">电子系</option>");
		departMap.put("游戏系","<option value=\"游戏系\">游戏系</option>");
		departMap.put("数码媒体系","<option value=\"数码媒体系\">数码媒体系</option>");
		departMap.put("管理系","<option value=\"管理系\">管理系</option>");
		departMap.put("国际经贸系","<option value=\"国际经贸系\">国际经贸系</option>");    
		departMap.put("财会系","<option value=\"财会系\">财会系</option>");    
		departMap.put("外语系","<option value=\"外语系\">外语系</option>");    
		departMap.put("计算机系","<option value=\"计算机系\">计算机系</option>"); 
		
		// 同理,准备一个map存放所有专业
		Map<String,String> majorMap=new LinkedHashMap<>();
		majorMap.put("软件工程系","<optgroup label=\"软件工程系\">");
		majorMap.put("数据科学与大数据技术","<option value=\"数据科学与大数据技术\">数据科学与大数据技术</option>");
		majorMap.put("软件工程","<option value=\"软件工程\">软件工程</option>");
		majorMap.put("软件工程(国际版)","<option value=\"软件工程(国际版)\">软件工程(国际版)</option>");
		majorMap.put("网络技术系","</optgroup><optgroup label=\"网络技术系\">");
		majorMap.put("网络工程","<option value=\"网络工程\">网络工程</option>");
		majorMap.put("网络工程(华为创新实验班)","<option value=\"网络工程(华为创新实验班)\">网络工程(华为创新实验班)</option>");
		majorMap.put("信息管理与信息系统","<option value=\"信息管理与信息系统\">信息管理与信息系统</option>");
		majorMap.put("电子系","</optgroup><optgroup label=\"电子系\">");
		majorMap.put("智能科学与技术(人工智能创新实验班)","<option value=\"智能科学与技术(人工智能创新实验班)\">智能科学与技术(人工智能创新实验班)</option>");
		majorMap.put("电子信息工程","<option value=\"电子信息工程\">电子信息工程</option>");
		majorMap.put("通信工程","<option value=\"通信工程\">通信工程</option>");
		majorMap.put("游戏系","</optgroup><optgroup label=\"游戏系\">");
		majorMap.put("数字媒体技术","<option value=\"数字媒体技术\">数字媒体技术</option>");
		majorMap.put("动画","<option value=\"动画\">动画</option>");
		majorMap.put("网络与新媒体","<option value=\"网络与新媒体\">网络与新媒体</option>");
		majorMap.put("数码媒体系","</optgroup><optgroup label=\"数码媒体系\">");
		majorMap.put("环境设计","<option value=\"环境设计\">环境设计</option>");
		majorMap.put("风景园林","<option value=\"风景园林\">风景园林</option>");
		majorMap.put("视觉传达设计","<option value=\"视觉传达设计\">视觉传达设计</option>");
		majorMap.put("数字媒体艺术","<option value=\"数字媒体艺术\">数字媒体艺术</option>");
		majorMap.put("产品设计","<option value=\"产品设计\">产品设计</option>");
		majorMap.put("管理系","</optgroup><optgroup label=\"管理系\">");
		majorMap.put("行政管理","<option value=\"行政管理\">行政管理</option>");
		majorMap.put("物流管理","<option value=\"物流管理\">物流管理</option>");
		majorMap.put("工商管理","<option value=\"工商管理\">工商管理</option>");
		majorMap.put("市场营销","<option value=\"市场营销\">市场营销</option>");
		majorMap.put("人力资源管理","<option value=\"人力资源管理\">人力资源管理</option>");
		majorMap.put("国际经贸系","</optgroup><optgroup label=\"国际经贸系\">");
		majorMap.put("电子商务","<option value=\"电子商务\">电子商务</option>");
		majorMap.put("国际经济与贸易","<option value=\"国际经济与贸易\">国际经济与贸易</option>");
		majorMap.put("财会系","</optgroup><optgroup label=\"财会系\">");
		majorMap.put("财务管理","<option value=\"财务管理\">财务管理</option>");
		majorMap.put("会计学","<option value=\"会计学\">会计学</option>");
		majorMap.put("外语系","</optgroup><optgroup label=\"外语系\">");
		majorMap.put("英语","<option value=\"英语\">英语</option>");
		majorMap.put("日语","<option value=\"日语\">日语</option>");
		majorMap.put("计算机系","</optgroup><optgroup label=\"计算机系\">");
		majorMap.put("计算机科学与技术","<option value=\"计算机科学与技术\">计算机科学与技术</option>");
		majorMap.put("物联网工程","<option value=\"物联网工程\">物联网工程</option>");
		// 只有传递了课程名参数时,才去执行的一些操作
		if(!courseName.equals("")){
			// 获得指定课程的信息
			map=DatabaseAccess.getCourseByIdAndName(teacherIdentity,courseName);		
			description=map.get("description");
			depart=map.get("depart");
			template=map.get("template");
			cover=map.get("cover");
			major=map.get("major");
			
			// 添加selected属性
			String str=departMap.get(depart);
			StringBuilder strBuilder=new StringBuilder(str);
			int index=strBuilder.indexOf(">");
			strBuilder.insert(index," selected ");
			departMap.put(depart,strBuilder.toString());
			// 同上,添加selected属性
			str=majorMap.get(major);
			strBuilder=new StringBuilder(str);
			index=strBuilder.indexOf(">");
			strBuilder.insert(index," selected ");
			majorMap.put(major,strBuilder.toString());
			// 获取教学团队信息
			teamList=DatabaseAccess.selectTeamByIdAndName(teacherIdentity,courseName);			
		}		
		JspWriter out = getJspContext().getOut();
		out.println("<div><label for=\"courseName\">课程名称</label><input type=\"text\" name=\"courseName\" id=\"courseName\" value=\""+courseName+"\" required /></div>");
		out.println("<div><label for=\"description\">课程描述</label><textarea id=\"description\" name=\"description\" required>"+description+"</textarea></div>");
		out.println("<div><label for=\"depart\">所属系别</label>");
		out.println("<select id=\"depart\" name=\"depart\" required>");	
		// 循环输出所有系别
		for(String key:departMap.keySet()){
			out.println(departMap.get(key));
		}		
		out.println("</select></div><div>");
		out.println("<label for=\"major\">所属专业</label>");
		out.println("<select id=\"major\" name=\"major\" required>");
		// 循环输出所有专业
		for(String key:majorMap.keySet()){
			out.println(majorMap.get(key));
		}	
		out.println("</optgroup></select></div>");    
		out.println("<div><label for=\"cover\">课程封面</label><input type=\"file\" accept=\"image/*\" name=\"cover\" id=\"cover\" required /></div>");
		out.println("<div><label for=\"teach\">添加教师</label>");		
		// 教师团队显示方式
		for(String member:teamList){
			out.println("<input type=\"text\" name=\"teacherName\" value=\""+member+"\" required>");
		}				
		out.println("<button type=\"button\" id=\"teach\"><img src=\"../image/add.png\"></button></div>");				
		out.println("<div><label for=\"template\">选择模板</label><img src=\"../image/image.png\" id=\"template\"> <input type=\"text\" name=\"template\" value=\""+template+"\" readonly></div>");
		
	}
}
