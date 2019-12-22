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
/*定义显示容器样式*/
body>div {
	height:500px;
}
</style>
</head>

<body>
<!--显示容器-->
<div>
<define:display teacherIdentity="${param.teacherIdentity}" courseName="${param.courseName}" tabIndex="${param.tabIndex}" columnIndex="${param.columnIndex}" subcolumnIndex="${param.subcolumnIndex}" />
</div>

</body>
</html>