<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>customization</title>
<link href="../css/customization.css" rel="stylesheet" type="text/css">
<script src="../js/customization.js"></script>
</head>

<body>
<!--右键菜单-->
<ul hidden>
<li>重命名</li>
<li>左移</li>
<li>右移</li>
<li>删除</li>
</ul>

<!--定义一个大容器-->
<div>
<!--标题栏目部分-->
<form method="POST" action="../CustomizationServlet">
<a href="basic.jsp?courseID=${param.courseID}" target="mainIframe"><input type="text" value="基本信息" readonly class="tap"></a>
<!-- 遍历所有标题栏目 -->
<c:if test="${not empty param.courseID}">
	<define:customization courseID="${param.courseID}" />
</c:if>
<!-- 如果还没有填写基本信息,则不允许新增标题栏目 -->
<c:choose>
	<c:when test="${empty param.courseID}">
        <button type="button" disabled><img src="../image/add.png" title="添加栏目"></button>
    </c:when>
    <c:otherwise>
    	<button type="button"><img src="../image/add.png" title="添加栏目"></button>
    </c:otherwise>
</c:choose>
<input type="hidden" value="${param.courseID}" name="courseID">
<input type="hidden" name="tabIndex">
<input type="hidden" name="operation">
<input type="submit" disabled hidden>
</form>

<iframe src="basic.jsp?courseID=${param.courseID}" scrolling="yes" height="650" name="mainIframe">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>

</div>

</body>
</html>

