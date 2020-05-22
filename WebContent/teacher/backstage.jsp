<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>backstage</title>
<link href="../css/backstage.css" rel="stylesheet" type="text/css">
<script src="../js/backstage.js"></script>
</head>

<body>
<p><a href="backstage.jsp" target="_self">我的文件</a></p>
<hr/>

<define:fetch courseID="${param.courseID}" tabIndex="${param.tabIndex}" columnIndex="${param.columnIndex}" courseName="${param.courseName}" tabName="${param.tabName}" />

</body>
</html>