<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.DatabaseAccess,java.util.Map" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>management</title>
<link href="../css/management.css" rel="stylesheet" type="text/css">
</head>

<body>
<p>课程管理</p>
<ul>
<%
	String contextPath = (String) application.getAttribute("contextPath");
	String teacherIdentity = (String) session.getAttribute("identity");
	Map<String,String> map = DatabaseAccess.getAllCourseByTeacherId(teacherIdentity);
	for(String courseID : map.keySet()){
		String courseName = map.get(courseID);
		out.println("<a href=\"customization.jsp?courseID="+courseID+"\" target=\"_blank\" ><img src=\""+contextPath+"/upload/"+courseID+"/cover.png\"><p>"+courseName+"</p></a>");
	}
%>
<a href="customization.jsp" target="_blank"><img src="../image/add-square.png"> 添加课程</a>
</ul>

</body>
</html>