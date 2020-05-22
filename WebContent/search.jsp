<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>search</title>
<link href="css/search.css" rel="stylesheet" type="text/css">
<script src="js/search.js"></script>
</head>

<body>
<!-- 导航栏部分 -->
<jsp:include page="navbar.jsp"/>

<!-- 侧边栏部分 -->
<aside>
<span></span><span>精彩推荐</span>
<%
	String contextPath = (String) application.getAttribute("contextPath");
	List<Map<String,String>> list = DatabaseAccess.getAllCourseAndDetail("","","");
	Iterator<Map<String,String>> iterator = list.iterator();
	while(iterator.hasNext()){
		Map<String,String> map = iterator.next();
		String courseID = map.get("courseID");
		String courseName = map.get("courseName");
		String depart = map.get("depart");
		String cover = contextPath+"/"+"upload"+"/"+courseID+"/cover.png";
		out.println("<a href=\"course.jsp?courseID="+courseID+"\" target=\"_blank\">");
		out.println("<img src=\""+cover+"\">");
		out.println("<figcaption>"+courseName+"</figcaption>");
		out.println("<p>"+depart+"</p>");
		out.println("</a>");
	}
%>
</aside>

<main>
<!-- 搜索结果文字部分-->
<span>共0条相关的结果</span>
<!-- 搜索框部分 -->
<form id="search" method="GET" action="search.jsp">
<input type="search" placeholder="搜索感兴趣的课程" name="key" /><input type="image" src="image/search.png" />
</form>
<!-- 内容显示部分 -->
<define:display key="${param.key}" depart="${param.depart}" major="${param.major}" />
</main>

<!-- 分页部分 -->
<ul id="page">
<li>«</li><li>»</li>
</ul>

<!-- 页脚部分 -->
<footer>
<p>版权所有：广州大学华软软件学院</p>
<p>Copyright 🌏 2019 Company name All rights reserved.</p>
</footer>

</body>
</html>