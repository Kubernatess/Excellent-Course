<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator,bean.Comment,java.sql.Timestamp" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>message</title>
<link href="css/message.css" rel="stylesheet" type="text/css">
</head>
<body>
<!--给登陆用户赋予通行证-->
<c:if test="${ empty sessionScope.status}">
	<script>window.location.href="login.jsp";</script>
</c:if>

<p>消息管理</p>
<%
	String identity = (String) session.getAttribute("identity");
	List<Map<String,Object>> list = DatabaseAccess.getAllMessageAndDetail(identity);
	Iterator<Map<String,Object>> iterator = list.iterator();
	while(iterator.hasNext()){
		Map<String,Object> map = iterator.next();
		String courseID = (String) map.get("courseID");
		String tabIndex = (String) map.get("tabIndex");
		int columnIndex = (int) map.get("columnIndex");
		out.println("<a href=\"learning.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\" target=\"_blank\">");
		
		String username = (String) map.get("username");
		out.println("<strong>"+username+"</strong>回复了我的评论");		
		
		Timestamp lunchTime = (Timestamp) map.get("lunchTime");
		Comment comment = new Comment();
		comment.setIdentity(identity);
		comment.setDateTime(lunchTime);
		String contentt = DatabaseAccess.getComment(comment);
		out.println("<span>"+contentt+"</span>");
		
		String content = (String) map.get("comment");
		out.println("<p>"+content+"</p>");
		
		String dateTime = (String) map.get("dateTime");
		int index =  dateTime.indexOf(".");
		String showTime =  dateTime.substring(0,index);
		out.println("<time datetime=\""+dateTime+"\">"+showTime+"</time>");
		out.println("<hr/></a>");
	}
%>
</body>
</html>