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
<!-- 导航栏部分 -->
<nav>
<img src="image/logo.png">
<span>广州大学华软软件学院</span>
<!--
<a href="login.html" target="_blank"><img src="image/user-empty.png"> 登陆</a>
-->
<div class="dropdown">
<a href="#" class="dropbtn" target="_blank"><img src="image/login.png"> <span>Twitter</span></a>
<div class="dropdown-content">
	<a href="#">添加课程</a>
    <a href="#">课程管理</a>
	<a href="#">用户设置</a>
	<a href="#">学习记录</a>
	<a href="#">消息</a>
	<a href="#">退出</a>
</div>
</div>

</nav>


<div class="container">

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
