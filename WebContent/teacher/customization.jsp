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
<!--给登陆用户赋予通行证-->
<c:if test="${ sessionScope.status ne '老师' }">
	<script>window.location.href="../login.jsp";</script>
</c:if>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<!--定义一个大容器-->
<div>

<!--右键菜单-->
<ul>
<li>重命名</li>
<li>左移</li>
<li>右移</li>
<li>删除</li>
</ul>


<!--标题栏目部分-->
<form method="POST" action="${Path}/CustomizationServlet">
<a href="basic.jsp?courseName=${param.courseName}" target="iframeA"><input type="text" value="基本信息"></a>
<!-- 遍历所有标题栏目 -->
<define:customization courseName="${param.courseName}" />
<!-- 如果还没有填写基本信息,则不允许新增标题栏目 -->
<c:choose>
	<c:when test="${empty param.courseName}">
        <button type="button" disabled><img src="../image/add.png" title="添加栏目"></button>
    </c:when>
    <c:otherwise>
    	<button type="button"><img src="../image/add.png" title="添加栏目"></button>
    </c:otherwise>
</c:choose>
<input type="hidden" value="${param.courseName}" name="courseName">
<input type="hidden" name="tabIndex">
<input type="submit">
</form>

<iframe src="basic.jsp?courseName=${param.courseName}" scrolling="no" height="650" name="iframeA">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>

</div>

</body>
</html>

