<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>edit</title>
<link href="../css/edit.css" rel="stylesheet" type="text/css">
<script src='../js/edit.js'></script>
</head>

<body>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<form action="${Path}/EditServlet" method="POST">

<!-- 工具箱部分 -->
<ul>
<li><button type="button" id="clear"><img src="../image/tools/clear.png"></button></li>
<li class="dropdown">
<button type="button"><img src="../image/tools/font-size.png"></button>
<div class="dropdown-content">
	<button type="button">16px</button>
    <button type="button">20px</button>
	<button type="button">24px</button>
	<button type="button">28px</button>
	<button type="button">32px</button>
	<button type="button">36px</button>
</div>
</li>
<li><button type="button" id="normal"><img src="../image/tools/normal.png"></button></li>
<li><button type="button" id="bold"><img src="../image/tools/bold.png"></button></li>
<li><button type="button" id="italic"><img src="../image/tools/italic.png"></button></li>
<li><button type="button" id="fontColor"><img src="../image/tools/font-color.png"><input type="color" name="favcolor"></button></li>
<li><button type="button" id="leftAlign"><img src="../image/tools/left-align.png"></button></li>
<li><button type="button" id="centerAlign"><img src="../image/tools/center-align.png"></button></li>
<li><button type="button" id="rightAlign"><img src="../image/tools/right-align.png"></button></li>
<li><button type="button" id="hyperlink"><img src="../image/tools/hyperlink.png"></button></li>
<li><button type="button"><img src="../image/tools/attachment.png"><input type="file" name="attachment"></button></li>
<li><button type="button"><img src="../image/tools/video.png"><input type="file" accept="video/*" name="video"></button></li>
<li><button type="button"><img src="../image/tools/photo.png"><input type="file" accept="image/*" name="image"></button></li>
</ul>


<!--可编辑容器-->
<div contentEditable="true" id="editor">
<define:edit courseName="${param.courseName}" tabIndex="${param.tabIndex}" columnIndex="${param.columnIndex}" />
</div>
<c:set var="courseName" scope="session" value="${param.courseName}"/> 
<c:set var="tabIndex" scope="session" value="${param.tabIndex}"/> 
<c:set var="columnIndex" scope="session" value="${param.columnIndex}"/> 
<c:set var="subcolumnIndex" scope="session" value="${param.subcolumnIndex}"/> 
<input type="hidden" id="courseName" value="${param.courseName}">
<input type="hidden" id="tabIndex" value="${param.tabIndex}">
<input type="hidden" id="columnIndex" value="${param.columnIndex}">
<input type="hidden" id="subcolumnIndex" value="${param.subcolumnIndex}">
<input type="hidden" name="content">
<input type="button" value="保存" id="save" />
<a href="../learning.jsp?teacherIdentity=${sessionScope.identity}&courseName=${param.courseName}" target="_blank" id="preview">保存并预览</a>

</form>
</body>
</html>

