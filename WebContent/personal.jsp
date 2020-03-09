<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>personal</title>
<link href="css/personal.css" rel="stylesheet" type="text/css">
<script src="js/personal.js"></script>
</head>

<body>
<!--给登陆用户赋予通行证-->
<c:if test="${ empty sessionScope.status}">
	<script>window.location.href="login.jsp";</script>
</c:if>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<div>
<p>用户设置</p>
<define:personal />
</div>
</body>
</html>
