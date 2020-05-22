<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator,bean.*,java.sql.Timestamp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>learning</title>
<link href="css/learning.css" rel="stylesheet" type="text/css">
<script src="js/learning.js"></script>
</head>

<body>
<!-- 导航栏部分 -->
<nav id="navbar">
<%
	String contextPath = (String) application.getAttribute("contextPath");
	String courseID = request.getParameter("courseID");
	Map<String,String> courseMap = DatabaseAccess.getCourse(courseID);
	String courseName = courseMap.get("courseName");
	out.println("<span>"+courseName+"</span>");
%>
<a href="index.jsp" target="_blank">首页</a>
<a href="#">介绍</a>
<a href="#">热门课程</a>
<a href="#">推荐课程</a>
<a href="#">最新课程</a>
<!-- 搜索框部分 -->
<form method="GET" action="search.jsp" target="_blank">
<input type="search" placeholder="搜索感兴趣的课程" name="key" /><input type="image" src="image/search.png" />
</form>
</nav>

<nav id="subnavbar">
<%	
	Map<String,String> tabMap = DatabaseAccess.getAllTabByCourseId(courseID);
	for(String tabIndex : tabMap.keySet()){
		String tabName = tabMap.get(tabIndex);
		out.println("<a href=\"learning.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"\" data-tabindex=\""+tabIndex+"\">"+tabName+"</a>");
	}
%>
</nav>

<!-- 侧边导航栏部分 -->
<aside>
<%
	String tabIndex = request.getParameter("tabIndex");
	// 避免报错信息,减少不必要的查询操作
	if(tabIndex==null){
		return;
	}
	Map<Integer,String> columnMap = DatabaseAccess.getColumnsByCourseIdAndTabId(courseID,tabIndex);
	String studentIdentity = (String) session.getAttribute("identity");
	Record record = new Record();
	record.setCourseID(courseID);
	record.setTabIndex(tabIndex);
	record.setStudentIdentity(studentIdentity);
	for(int columnIndex : columnMap.keySet()){
		// 判断某个学生是否打了绿灯
		record.setColumnIndex(columnIndex);
		Map<String,Object> map = DatabaseAccess.getRecord(record);
		boolean finished = false;
		if(map.size()>0){
			finished = (boolean) map.get("finished");
		}
		out.println("<a href=\"learning.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\" data-columnindex=\""+columnIndex+"\">");
		if(finished){
			out.println("<img src=\"image/correct.png\">");
		}
		else{
			out.println("<img src=\"image/correct.png\" style=\"visibility:hidden;\">");
		}
		String columnName = columnMap.get(columnIndex);
		out.println(columnName+"</a>");
	}
%>
</aside>

<!-- 显示HTML标签内容 -->
<main>
<%
	//避免报错信息,减少不必要的查询操作
	if(request.getParameter("columnIndex")==null){
		return;
	}
	int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
	Column column = new Column();
	column.setCourseID(courseID);
	column.setTabIndex(tabIndex);
	column.setIndex(columnIndex);
	Map<String,String> map = DatabaseAccess.getColumn(column);
	String content = map.get("content");
	out.println(content);
%>
</main>

<!-- 显示评论部分 -->
<article>
<h2>所有讨论</h2>
<form method="POST" action="<%= contextPath%>/CommentServlet" id="commentForm">
<textarea rows="8" name="comment" required></textarea>
<input type="hidden" name="courseID" value="<%= courseID%>">
<input type="hidden" name="tabIndex" value="<%= tabIndex%>">
<input type="hidden" name="columnIndex" value="<%= columnIndex%>">
<input type="submit" value="发表评论" />
</form>

<form method="POST" action="<%= contextPath%>/CommentServlet" id="replyForm">
<ul>
<%
	Comment comment = new Comment();
	comment.setCourseID(courseID);
	comment.setTabIndex(tabIndex);
	comment.setColumnIndex(columnIndex);
	List<Map<String,String>> linkedList = DatabaseAccess.getAllCommentAndDetail(comment);
	Iterator<Map<String,String>> iterator = linkedList.iterator();
	while(iterator.hasNext()){
		Map<String,String> commentMap = iterator.next();
		String identity = commentMap.get("identity");
		String username = commentMap.get("username");
		String dateTime = commentMap.get("dateTime");
		int index = dateTime.indexOf(".");
		String showTime = dateTime.substring(0,index);
		String comments = (String) commentMap.get("comment");
		out.println("<li>");
		out.println("<address data-identity=\""+identity+"\">作者: "+username+"</address>");
		out.println("<button type=\"button\">回复</button>");
		if(studentIdentity.equals(identity)){
			out.println(" <button type=\"submit\" form=\"removeForm\">删除</button>");
		}
		out.println("<time datetime=\""+dateTime+"\">"+showTime+"</time>");
		String opposite = (String) commentMap.get("opposite");
		String str = "";
		Map<String,String> userMap = DatabaseAccess.getUser(opposite);
		if(userMap.get("username")!=null){
			str = "回复"+userMap.get("username")+"：";
		}
		out.println("<p>"+str+comments+"</p>");
		out.println("<fieldset>");
		out.println("<textarea rows=\"8\"></textarea>");
		out.println("<input type=\"submit\" value=\"回复评论\" />");
		out.println("</fieldset>");
		out.println("<hr/>");
		out.println("</li>");
	}
	
	// 统计访问量加一
	String status = (String) session.getAttribute("status");
	if(status.equals("student")){
		record.setColumnIndex(columnIndex);
		DatabaseAccess.addHitcount(record);
	}
%>
</ul>
<input type="hidden" name="courseID" value="<%= courseID%>">
<input type="hidden" name="tabIndex" value="<%= tabIndex%>">
<input type="hidden" name="columnIndex" value="<%= columnIndex%>">
<input type="hidden" name="opposite">
<input type="hidden" name="lunchTime">
</form>

<form id="removeForm" method="POST" action="<%= contextPath%>/RemoveComment">
<input type="hidden" name="courseID" value="<%= courseID%>">
<input type="hidden" name="tabIndex" value="<%= tabIndex%>">
<input type="hidden" name="columnIndex" value="<%= columnIndex%>">
<input type="hidden" name="dateTime">
</form>
</article>

<form id="objective" method="POST" action="./ObjectiveServlet">
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex" value="${param.columnIndex}">
</form>

<form id="subjective" method="POST" action="./SubjectiveServlet"  enctype="multipart/form-data">
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex" value="${param.columnIndex}">
</form>

</body>
</html>
