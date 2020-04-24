<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>display</title>
<style>
/*按钮样式*/
.btn {
	color:#FFF;
	background-color:#169BD5;
	border:1px solid #169BD5;
	border-radius:5px;
	padding: 5px 20px;
}
</style>
<script src="js/display.js"></script>
</head>

<body>
<!--显示content内容-->
<define:display courseID="${param.courseID}" tabIndex="${param.tabIndex}" columnIndex="${param.columnIndex}" />

<form id="testing" method="POST" action="./TestingServlet">
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex" value="${param.columnIndex}">
<input type="hidden" name="combination">
</form>

<form id="homework" method="POST" action="./HomeworkServlet"  enctype="multipart/form-data">
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex" value="${param.columnIndex}">
</form>

</body>
</html>