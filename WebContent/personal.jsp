<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.DatabaseAccess,java.util.Map" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>personal</title>
<link href="css/personal.css" rel="stylesheet" type="text/css">
<script src="js/personal.js"></script>
</head>

<body>
<!--给登陆用户赋予通行证-->
<c:if test="${ empty sessionScope.status}">
	<script>window.location.href="login.jsp";</script>
</c:if>

<h1>用户设置</h1>
<%
	String contextPath = (String) application.getAttribute("contextPath");
	String identity = (String) session.getAttribute("identity");
	String status = (String) session.getAttribute("status");
	String password = (String) session.getAttribute("password");
	String username = (String) session.getAttribute("username");
	String depart = (String) session.getAttribute("depart");
%>
<p>工号<span><%= identity%></span></p>
<p>密码<span>********</span><button>╲╱</button></p>
<form method="POST" action="<%= contextPath%>/ModifyPassword" hidden>
<input type="password" maxlength="16" name="currentPassword" required placeholder="当前密码" data-password="<%= password%>">最多16个字符,可以包含字母、数字和特殊字符
<input type="password" maxlength="16" name="recentPassword" required placeholder="新的密码">
<input type="password" maxlength="16" name="confirmPassword" required placeholder="确认密码">
<input type="submit" value="Submit" class="btn-primary">
<input type="reset" value="Reset" class="btn-default">
</form>
<p>姓名<span><%= username%></span></p>
<p>系别<span><%= depart%></span></p>
<p>身份<span><%= status%></span></p>

</body>
</html>
