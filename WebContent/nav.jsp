<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>nav</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:set var="Path" scope="request" value="${applicationScope.contextPath}"/>
<nav>
<img src="image/logo.png">
<span>广州大学华软软件学院</span>

<c:choose>
    <c:when test="${ empty sessionScope.username }">
       <a href="login.jsp" target="_blank"><img src="image/user-empty.png"> 登陆</a>
    </c:when>
    <c:otherwise>
        <div class="dropdown">
		<a href="#" class="dropbtn" target="_blank"><img src="image/login.png"> <span>${ sessionScope.username }</span></a>
		<div class="dropdown-content">
			<a href="${Path}/personal.jsp" target="_blank">用户设置</a>
			<c:if test="${sessionScope.status eq '老师'}">
   				<a href="${Path}/teacher/customization.jsp" target="_blank">添加课程</a>
    			<a href="${Path}/teacher/management.jsp" target="_blank">课程管理</a>
			</c:if>			
			<c:if test="${sessionScope.status eq '学生'}">
   				<a href="#">学习记录</a>
				<a href="#">消息</a>
			</c:if>		
			<a href="${Path}/LogoutServlet">退出</a>
		</div>
		</div>
    </c:otherwise>
</c:choose>

</nav>
</body>
</html>