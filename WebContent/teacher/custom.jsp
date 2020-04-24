<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>custom</title>
<link href="../css/custom.css" rel="stylesheet" type="text/css">
<script src="../js/custom.js"></script>
</head>

<body>
<!--右键菜单部分-->
<ul hidden>
<li>上移</li>
<li>下移</li>
<li>重命名</li>
<li>删除该栏目</li>
</ul>


<form action="../CustomServlet" method="POST">
<c:if test="${not empty param.courseID and not empty param.tabIndex}">
	<define:custom courseID="${param.courseID}" tabIndex="${param.tabIndex}" />
</c:if>
<button type="button"><img src="../image/add.png" title="添加子栏目"></button>
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex">
<input type="hidden" name="anotherIndex">
<input type="hidden" name="operation">
<input type="submit" disabled hidden>
</form>

<div>
<iframe scrolling="no" name="subIframe" >
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>
<p>欢迎来到精品课程网站,请点击左侧添加按钮,新建您的第一个课程页面</p>
</div>

</body>
</html>