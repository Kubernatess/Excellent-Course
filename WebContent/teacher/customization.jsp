<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.DatabaseAccess,java.util.Map" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>customization</title>
<link href="../css/customization.css" rel="stylesheet" type="text/css">
<script src="../js/customization.js"></script>
</head>

<body>
<!--右键菜单-->
<ul hidden>
<li>重命名</li>
<li>左移</li>
<li>右移</li>
<li>删除</li>
</ul>

<!--标题栏目部分-->
<form method="POST" action="../CustomizationServlet">
<a href="basic.jsp?courseID=${param.courseID}" target="mainIframe"><input type="text" value="基本信息" readonly class="tap"></a>
<%
	String courseID = request.getParameter("courseID");
	Map<String,String> map = DatabaseAccess.getAllTabByCourseId(courseID);
	for(String tabIndex : map.keySet()){
		String tabName = map.get(tabIndex);
		out.println("<a href=\"edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"\" target=\"mainIframe\">");
		out.println("<input type=\"text\" value=\""+tabName+"\" data-tabindex=\""+tabIndex+"\" readonly />");
		out.println("</a> ");
	}
%>
<button type="button"><img src="../image/add.png" title="添加栏目"></button>
<input type="hidden" value="${param.courseID}" name="courseID">
<input type="hidden" name="tabIndex">
<input type="submit" disabled hidden>
</form>

<iframe src="basic.jsp?courseID=${param.courseID}" name="mainIframe">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>


</body>
</html>

