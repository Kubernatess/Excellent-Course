package defineJSTL;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import db.DatabaseAccess;

public class AdministrationTag extends SimpleTagSupport {
	
	public void doTag() throws JspException, IOException {
		List<Map<String,String>> list = DatabaseAccess.getAllUser();
		Iterator<Map<String,String>> iterator = list.iterator();
		JspWriter out = getJspContext().getOut();
		while(iterator.hasNext()){
			Map<String,String> map = iterator.next();
			String identity = map.get("identity");
			String status = map.get("status");
			String password = map.get("password");
			String name = map.get("name");
			String depart = map.get("depart");
			String major = map.get("major");
			out.println("<tr>");
			out.println("<td><input type=\"text\" value=\""+identity+"\" name=\"identity\" disabled required maxlength=\"10\"></td>");
			out.println("<td><input type=\"text\" value=\""+status+"\" name=\"status\" disabled required></td>");
			out.println("<td><input type=\"text\" value=\""+password+"\" name=\"password\" disabled required maxlength=\"16\"></td>");
			out.println("<td><input type=\"text\" value=\""+name+"\" name=\"name\" disabled required></td>");
			out.println("<td><select name=\"depart\" disabled required>");
			out.println("<option value=\"软件工程系\" "+isSelected(depart,"软件工程系")+">软件工程系</option>");
			out.println("<option value=\"网络技术系\" "+isSelected(depart,"网络技术系")+">网络技术系</option>");
			out.println("<option value=\"电子系\" "+isSelected(depart,"电子系")+">电子系</option>");
			out.println("<option value=\"数码媒体系\" "+isSelected(depart,"数码媒体系")+">数码媒体系</option>");
			out.println("<option value=\"管理系\" "+isSelected(depart,"管理系")+">管理系</option>");
			out.println("<option value=\"国际经贸系\" "+isSelected(depart,"国际经贸系")+">国际经贸系</option>");
			out.println("<option value=\"财会系\" "+isSelected(depart,"财会系")+">财会系</option>");
			out.println("<option value=\"外语系\" "+isSelected(depart,"外语系")+">外语系</option>");
			out.println("<option value=\"计算机系\" "+isSelected(depart,"计算机系")+">计算机系</option>");
			out.println("</select></td>");
			out.println("<td><select name=\"major\" disabled required>");
			out.println("<option value=\""+major+"\">"+major+"</option>");
			out.println("</select></td>");
			out.println("<td><input type=\"image\" src=\"../image/edit.png\" name=\"edit\"> <input type=\"image\" src=\"../image/trash.png\" name=\"remove\"></td>");
			out.println("</tr>");
		}
	}
	
	private String isSelected(String depart,String key){
		if(depart.equals(key)){
			return "selected";
		}
		else{
			return "";
		}
	}
}
