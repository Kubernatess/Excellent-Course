<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>basic</title>
<link href="../css/basic.css" rel="stylesheet" type="text/css">
<script src="../js/basic.js"></script>
</head>

<body>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<!--侧栏部分-->
<aside>
<button type="button" disabled><img src="../image/add-grey.png" title="添加子栏目"></button>
</aside>


<!--填写基本信息的表单-->
<form method="POST" action="${Path}/BasicServlet" enctype="multipart/form-data">

<define:basic courseName="${param.courseName}" />

<input type="submit" id="save" value="保存" />
<a href="../course.jsp?teacherIdentity=${sessionScope.identity}&courseName=${param.courseName}" target="_blank" id="preview">保存并预览</a>

</form>

</body>
</html>
