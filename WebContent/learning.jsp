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
<!--定义一个大容器-->
<div>

<!--标题栏目部分-->
<nav>
<define:learning teacherIdentity="${param.teacherIdentity}" courseName="${param.courseName}" />
</nav>

<iframe scrolling="no" height="650" name="iframeA">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>

</div>

</body>
</html>
