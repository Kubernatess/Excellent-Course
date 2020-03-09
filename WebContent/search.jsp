<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>search</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/content.css" rel="stylesheet" type="text/css">
<link href="css/search.css" rel="stylesheet" type="text/css">
<script src="js/search.js"></script>
</head>

<body>
<!-- 导航栏部分 -->
<jsp:include page="nav.jsp"/>

<!-- 侧边栏部分 -->
<aside>
<span></span><span>精彩推荐</span>
<div>
<img src="image/cover.jpg">
<p>Windows游戏编程</p>
<p>游戏系</p>
</div>
<div>
<img src="image/cover.jpg">
<p>面向过程程序设计</p>
<p>软件工程系</p>
</div>
<div>
<img src="image/cover.jpg">
<p>Photoshop软件应用</p>
<p>数码媒体系</p>
</div>
</aside>

<div class="container">
<!-- 搜索结果文字部分-->
<span>共0条<span></span>相关的结果</span>
<input type="hidden" value="${param.key}">
<!-- 搜索框部分 -->
<form>
<input type="search" placeholder="搜索感兴趣的课程" /><input type="image" src="image/search.png" />
</form>
<!-- 内容显示部分 -->
<define:search key="${param.key}" />
</div>

<!-- 分页部分 -->
<div class="page">
<ul>
<li><a href="#">«</a></li><li><a href="#">1</a></li><li><a class="active" href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">...</a></li><li><a href="#">30</a></li><li><a href="#">»</a></li>
</ul>
</div>

<!-- 页脚部分 -->
<footer>
<p>版权所有：广州大学华软软件学院</p>
<p>Copyright 🌏 2019 Company name All rights reserved.</p>
</footer>

</body>
</html>