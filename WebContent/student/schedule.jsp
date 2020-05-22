<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator,bean.*" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>schedule</title>
<link href="../css/schedule.css" rel="stylesheet" type="text/css">
<script src="../js/schedule.js"></script>
</head>
<body>
<p>已加入的课程</p>
<%
	String contextPath = (String) application.getAttribute("contextPath");
	String studentIdentity = (String) session.getAttribute("identity");
	Map<String,String> map = DatabaseAccess.getRecordsByStudentId(studentIdentity);
	for(String courseID : map.keySet()){
		String courseName = (String) map.get(courseID);
		int pageview = 0;		
		List<Map<String,Object>> recordList = DatabaseAccess.getAllRecordByStudentId(studentIdentity,courseID);
		Iterator<Map<String,Object>> iterator = recordList.iterator();
		while(iterator.hasNext()){
			Map<String,Object> mmap = iterator.next();
			int hitcount = (int) mmap.get("hitcount");
			pageview += hitcount;
		}
		out.println("<a href=\"./statistics.jsp?courseID="+courseID+"&courseName="+courseName+"\" target=\"_blank\">");
		out.println("<img src=\""+contextPath+"/upload/"+courseID+"/cover.png\">");
		out.println("<p>"+courseName+"</p>");
		out.println("<progress value=\""+pageview+"\" max=\"100\"></progress>");
		out.println("<samp></samp>");
	}
%>
</body>
</html>