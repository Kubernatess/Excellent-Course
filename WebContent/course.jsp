<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>course</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/course.css" rel="stylesheet" type="text/css">
<script src="js/course.js"></script>
</head>

<body>
<!--给登陆用户赋予通行证-->
<c:if test="${ empty sessionScope.status}">
	<script>window.location.href="login.jsp";</script>
</c:if>

<!-- 导航栏部分 -->
<jsp:include page="nav.jsp"/>

<div class="container">
<input type="hidden" value="${param.teacherIdentity}" name="teacherIdentity" form="review">
<input type="hidden" value="${param.courseName}" name="courseName" form="review">
<input type="hidden" value="${sessionScope.identity}" name="identity" form="review">
<define:course teacherIdentity="${param.teacherIdentity}" courseName="${param.courseName}" />

<!--定义相关课程部分-->
<div class="related">
<span></span><span>相关课程</span>
<ul>
    <li>
    <img src="image/cover.jpg">
    <p>Windows游戏编程</p>
    <p>游戏系</p>
    </li>
    <li>
    <img src="image/cover.jpg">
    <p>Windows游戏编程</p>
    <p>游戏系</p>
    </li>
    <li>
    <img src="image/cover.jpg">
    <p>Windows游戏编程</p>
    <p>游戏系</p>
    </li>
</ul>
</div>

</aside>


</div>
</body>
</html>
