<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>course</title>
<link href="css/course.css" rel="stylesheet" type="text/css">
<script src="js/course.js"></script>
</head>

<body>
<!--给登陆用户赋予通行证-->
<c:if test="${ empty sessionScope.status}">
	<script>window.location.href="login.jsp";</script>
</c:if>

<!-- 导航栏部分 -->
<jsp:include page="navbar.jsp"/>

<main>

<define:course courseID="${param.courseID}" />

<!--定义相关课程部分-->
<section>
<dfn></dfn><span>相关课程</span>
<figure>
<img src="image/cover.jpg">
<figcaption>Windows游戏编程</figcaption>
<p>游戏系</p>
</figure>
<figure>
<img src="image/cover.jpg">
<figcaption>Windows游戏编程</figcaption>
<p>游戏系</p>
</figure>
<figure>
<img src="image/cover.jpg">
<figcaption>Windows游戏编程</figcaption>
<p>游戏系</p>
</figure>
</section>

</aside>


</main>

</body>
</html>
