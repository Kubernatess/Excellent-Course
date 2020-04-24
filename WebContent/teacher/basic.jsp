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

<input type="image" src="../image/add-grey.png" disabled>

<!--填写基本信息的表单-->
<form method="POST" action="../BasicServlet" enctype="multipart/form-data">
<div><label>课程代号</label><input type="text" name="courseID" required maxlength="6" /></div>
<input type="hidden" />
<fieldset>
<define:basic courseID="${param.courseID}" />
</fieldset>
<input type="submit" id="save" value="保存" />
<input type="submit" id="preview" value="保存并预览" />
</form>

</body>
</html>
