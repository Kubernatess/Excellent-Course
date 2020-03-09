<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>management</title>
<link href="../css/management.css" rel="stylesheet" type="text/css">
</head>

<body>
<div>
<p>课程管理</p>
<ul>
<define:management />
<li><a href="customization.jsp" target="_blank"><img src="../image/add-square.png">添加课程</a href="#"></li>
</ul>
</div>
</body>
</html>