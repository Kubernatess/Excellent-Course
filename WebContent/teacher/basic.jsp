<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.DatabaseAccess,java.util.Map" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>basic</title>
<link href="../css/basic.css" rel="stylesheet" type="text/css">
<script src="../js/basic.js"></script>
</head>

<body>

<input type="image" src="../image/add-grey.png" disabled>

<!--填写基本信息的表单-->
<form method="POST" action="../BasicServlet" enctype="multipart/form-data">
<% String courseID = request.getParameter("courseID"); %>
<div><label>课程代码</label><input type="text" name="courseID" value="<%= courseID%>" required maxlength="6" /> <input type="image" src="../image/edit.png" height="20"></div>
<input type="hidden" value="<%= courseID%>" />
<fieldset>
<%
	String courseName = "";
	String major = "";
	String description = "";
	String team = "";
	// 只有传递了课程名参数时,才去获取指定课程的信息
	if(!courseID.equals("")){
		Map<String,String> map = DatabaseAccess.getCourse(courseID);
		courseName = map.get("courseName");
		major = map.get("major");
		description = map.get("description");
		team = map.get("team");		
	}
	String depart = (String) session.getAttribute("depart");
%>
<div><label>课程名称</label><input type="text" name="courseName" value="<%= courseName %>" required /></div>
<div><label>课程描述</label><textarea name="description" required><%= description%></textarea></div>
<div><label>所属系别</label><select name="depart"><option><%= depart%></option></select></div>
<div><label>所属专业</label><select name="major"><option value="<%= major%>"><%= major%></option></select></div>
<div><label>课程封面</label><input type="file" accept="image/*" name="cover" required /></div>
<div><label>添加教师</label>
<%
	//教师团队显示方式
	String teams[] = team.split("、");
	for(String member:teams){
		out.println("<input type=\"text\" name=\"teacherName\" required value=\""+member+"\">");
	}
%>
<input type="image" src="../image/add.png" id="add">
<input type="image" src="../image/reduce.png" id="reduce"></div>
</fieldset>
<input type="submit" id="save" value="保存" />
<input type="submit" id="preview" value="保存并预览" />
</form>

</body>
</html>
