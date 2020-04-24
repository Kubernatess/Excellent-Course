<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>navbar</title>
<link href="css/navbar.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav>
<img src="image/logo.png">
<span>广州大学华软软件学院</span>

<c:choose>
    <c:when test="${ empty sessionScope.username }">
       <a href="login.jsp" target="_blank"><img src="image/user-empty.png"> 登录</a>
    </c:when>
    <c:otherwise>
        <div class="dropdown">
		<img src="image/login.png"> ${ sessionScope.username }
		<div class="dropdown-content">
			<a href="./personal.jsp" target="_blank">用户设置</a>
			<c:if test="${sessionScope.status eq 'teacher'}">
   				<a href="./teacher/customization.jsp" target="_blank">课程定制</a>
    			<a href="./teacher/management.jsp" target="_blank">课程管理</a>
				<a href="./teacher/backstage.jsp" target="_blank">后台管理</a>
			</c:if>			
			<c:if test="${sessionScope.status eq 'student'}">
				<a href="./student/schedule.jsp" target="_blank">我的课程</a>
   				<a href="#">学习记录</a>
				<a href="#">消息</a>
			</c:if>		
			<a href="./LogoutServlet">退出</a>
		</div>
		</div>
    </c:otherwise>
</c:choose>

</nav>
</body>
</html>