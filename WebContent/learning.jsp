<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>learning</title>
<link href="css/learning.css" rel="stylesheet" type="text/css">
<script src="js/learning.js"></script>
</head>

<body>
<!-- 导航栏部分 -->
<nav id="navbar">
<span>${param.courseName}</span>
<a href="index.jsp" target="_blank">首页</a>
<a href="#">介绍</a>
<a href="#">热门课程</a>
<a href="#">推荐课程</a>
<a href="#">最新课程</a>
<!-- 搜索框部分 -->
<form>
  <input type="search" placeholder="搜索感兴趣的课程" /><input type="image" src="image/search.png" />
</form>
</nav>

<!-- 子级导航条 -->
<nav id="subnavbar">
<define:learning courseID="${param.courseID}" />
</nav>

<iframe name="mainIframe" scrolling="yes">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>

</body>
</html>
