<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator,bean.*,java.sql.Timestamp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>course</title>
<link href="css/course.css" rel="stylesheet" type="text/css">
<script src="js/course.js"></script>
</head>
<body>
<!--给登陆用户赋予通行证-->
<%
	if(session.getAttribute("status")==null){
		out.println("<script>window.location.href=\"login.jsp\";</script>");
		return;
	}
%>
<!-- 导航栏部分 -->
<jsp:include page="navbar.jsp"/>

<main>
<%
	String contextPath = (String) application.getAttribute("contextPath");	
	// 获得指定课程的信息
	String courseID = request.getParameter("courseID");
	Map<String,String> courseMap = DatabaseAccess.getCourse(courseID);
	String courseName = courseMap.get("courseName");
	String description = courseMap.get("description");
	String team = courseMap.get("team");
	String teacherIdentity = (String) courseMap.get("teacherIdentity");
	Map<String,Integer> recordMap = DatabaseAccess.getAllVisitor();
	int visitors = recordMap.containsKey(courseID)?recordMap.get(courseID):0;
%>
<!-- 封面部分 -->
<table>
<tr>
<th rowspan="4"><img src="<%= contextPath%>/upload/<%= courseID%>/cover.png"></th>
<th><%= courseName%></th>
</tr>
<tr>
<td><p><%= description%></p></td>
</tr>
<tr>
<td>已有<%= visitors%>人参加</td>
</tr>
<tr>
<td>
<%
	String studentIdentity = (String) session.getAttribute("identity");
	List<Map<String,Object>> list = DatabaseAccess.getAllRecordByStudentId(studentIdentity,courseID);
	String status = (String) session.getAttribute("status");
	if(status.equals("student")&&list.size()>0){
		out.println("<a href=\"learning.jsp?courseID="+courseID+"\" target=\"_blank\" style=\"background-color:#FF6633\">已经加入,继续学习</a>");
	}
	else{
		out.println("<a href=\"learning.jsp?courseID="+courseID+"\" target=\"_blank\" onclick=\"isjoin()\">立即参加</a>");
	}
%>
</td>
</tr>
</table>

<!-- 课程详细部分 -->
<article>
<a class="hovering">课程详情</a><a>课程评价(11)</a>
<hr>
<section id="detail">
<p><%= description%></p>
<%
	//获得所有栏目信息
	Map<String,String> tabMap = DatabaseAccess.getAllTabByCourseId(courseID);
	for(String tabIndex : tabMap.keySet()){
		String tabName = tabMap.get(tabIndex);
		out.println("<h3>"+tabName+"</h3>");
		Map<Integer,String> columnMap = DatabaseAccess.getColumnsByCourseIdAndTabId(courseID,tabIndex);
		for(int columnIndex : columnMap.keySet()){
			String columnName = columnMap.get(columnIndex);
			out.println("<p>"+columnName+"</p>");
		}
	}
%>
</section>

<!-- 课程评论部分 -->
<section id="review" hidden>
<h2>所有评论</h2>
<form method="POST" action="<%= contextPath%>/CommentServlet">
<textarea cols="108" rows="8" name="comment" required></textarea>
<input type="hidden" name="courseID" value="<%= courseID%>">
<input type="submit" value="发表评论" />
</form>
<ul>
<%
	List<Map<String,String>> linkedList = DatabaseAccess.getAllReviewAndDetail(courseID);
	Iterator<Map<String,String>> iterator = linkedList.iterator();
	while(iterator.hasNext()){
		Map<String,String> map = iterator.next();
		String identity = map.get("identity");
		String username = map.get("username");
		String dateTime = map.get("dateTime");
		int index = dateTime.indexOf(".");
		String showTime = dateTime.substring(0,index);
		String comments = (String) map.get("comment");
		out.println("<li>");
		out.println("<address data-identity=\""+identity+"\">作者: "+username+"</address>");
		if(studentIdentity.equals(identity)){
			out.println(" <button type=\"submit\" form=\"removeForm\">删除</button>");
		}
		out.println("<time datetime=\""+dateTime+"\">"+showTime+"</time>");
		out.println("<p>"+comments+"</p>");
		out.println("<hr/></li>");
	}
%>
</ul>
<form id="removeForm" method="POST" action="<%= contextPath%>/RemoveComment">
<input type="hidden" name="courseID" value="<%= courseID%>">
<input type="hidden" name="dateTime">
</form>
</section>

</article>


<aside>
<section id="teaching">
<%
	Map<String,String> userMap = DatabaseAccess.getUser(teacherIdentity);
	String depart = userMap.get("depart");
	out.println("<p>"+depart+"</p>");
%>
<hr>
<dfn></dfn><span></span>
<%
	//获得教学团队信息
	String[] teams = team.split("、");
	for(String menber : teams){
		out.println("<figure><img src=\"image/user-circle.png\"><span>"+menber+"</span></figure>");
	}
%>
</section>

<!--定义相关课程部分-->
<section id="related">
<dfn></dfn><span>相关课程</span>
<%
	List<Map<String,String>> courseList = DatabaseAccess.getAllCourseAndDetail("",depart,"");
	Iterator<Map<String,String>> courseIterator = courseList.iterator();
	while(courseIterator.hasNext()){
		Map<String,String> map = courseIterator.next();
		String cover = contextPath+"/"+"upload"+"/"+map.get("courseID")+"/cover.png";
		out.println("<a href=\"course.jsp?courseID="+map.get("courseID")+"\" target=\"_blank\">");
		out.println("<img src=\""+cover+"\">");
		out.println("<figcaption>"+map.get("courseName")+"</figcaption>");
		out.println("<p>"+depart+"</p>");
		out.println("</a>");
	}
%>
</section>

</aside>


</main>

</body>
</html>