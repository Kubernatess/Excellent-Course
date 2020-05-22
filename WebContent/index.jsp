<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>index</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<script src="js/index.js"></script>
</head>

<body>
<!-- 导航栏部分 -->
<jsp:include page="navbar.jsp"/>

<!-- 侧边栏部分 -->
<aside>
<span></span><span>精品课程</span>
<ol>
<%
	String contextPath = (String) application.getAttribute("contextPath");
	Map<String,Integer> map = DatabaseAccess.getAllVisitor();
	for(String courseID : map.keySet()){
		Map<String,String> courseMap = DatabaseAccess.getCourse(courseID);
		String courseName = courseMap.get("courseName");
		out.println("<li><a href=\"course.jsp?courseID="+courseID+"\" target=\"_blank\">"+courseName+"</a></li>");
	}
%>
</ol>
</aside>

<!-- 定义一个大容器 -->
<main>
<!-- 分类部分 -->
<section>
<ul>
<li><a href="index.jsp?depart=软件工程系">软件系</a></li><li><a href="index.jsp?major=电子与计算机工程">电子与计算机工程</a></li><li><a href="index.jsp?major=数据科学与大数据技术">数据科学与大数据技术</a></li><li><a href="index.jsp?major=软件工程">软件工程</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=网络技术系">网络系</a></li><li><a href="index.jsp?major=网络工程">网络工程</a></li><li><a href="index.jsp?major=信息管理与信息系统">信息管理与信息系统</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=电子系">电子系</a></li><li><a href="index.jsp?major=智能科学与技术">智能科学与技术</a></li><li><a href="index.jsp?major=电子信息工程">电子信息工程</a></li><li><a href="index.jsp?major=通信工程">通信工程</a></li><li><a href="index.jsp?major=自动化">自动化</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=游戏系">游戏系</a></li><li><a href="index.jsp?major=数字媒体技术">数字媒体技术</a></li><li><a href="index.jsp?major=动画">动画</a></li><li><a href="index.jsp?major=网络与新媒体">网络与新媒体</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=数码媒体系">数码系</a></li><li><a href="index.jsp?major=环境设计">环境设计</a></li><li><a href="index.jsp?major=风景园林">风景园林</a></li><li><a href="index.jsp?major=视觉传达设计">视觉传达设计</a></li><li><a href="index.jsp?major=数字媒体艺术">数字媒体艺术</a></li><li><a href="index.jsp?major=产品设计">产品设计</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=管理系">管理系</a></li><li><a href="index.jsp?major=行政管理">行政管理</a></li><li><a href="index.jsp?major=物流管理">物流管理</a></li><li><a href="index.jsp?major=工商管理">工商管理</a></li><li><a href="index.jsp?major=市场营销">市场营销</a></li><li><a href="index.jsp?major=人力资源管理">人力资源管理</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=国际经贸系">国贸系</a></li><li><a href="index.jsp?major=电子商务">电子商务</a></li><li><a href="index.jsp?major=国际经济与贸易">国际经济与贸易</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=财会系">财会系</a></li><li><a href="index.jsp?major=财务管理">财务管理</a></li><li><a href="index.jsp?major=会计学">会计学</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=外语系">外语系</a></li><li><a href="index.jsp?major=英语">英语</a></li><li><a href="index.jsp?major=日语">日语</a></li>
</ul>
<ul>
<li><a href="index.jsp?depart=计算机系">计算机系</a></li><li><a href="index.jsp?major=计算机科学与技术">计算机科学与技术</a></li><li><a href="index.jsp?major=物联网工程">物联网工程</a></li>
</ul>
</section>

<!-- 选项卡部分 -->
<a class="hovering">热门课程</a><a>推荐课程</a><a>最新课程</a>

<!-- 搜索框部分 -->
<form id="search" method="GET" action="search.jsp" target="_blank">
<input type="search" placeholder="搜索感兴趣的课程" name="key" /><input type="image" src="image/search.png" />
</form>

<!-- 课程列表部分 -->
<define:display key="${param.key}" depart="${param.depart}" major="${param.major}" />

</main>


<!-- 分页部分 -->
<ul id="page">
<li>«</li><li>»</li>
</ul>

<!-- 页脚部分 -->
<footer>
<p>版权所有：广州大学华软软件学院</p>
<p>Copyright 🌏 2019 Company name All rights reserved.</p>
</footer>

</body>
</html>
