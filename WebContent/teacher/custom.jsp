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
<!--给登陆用户赋予通行证-->
<c:if test="${ sessionScope.status ne '老师' }">
	<script>window.location.href="../login.jsp";</script>
</c:if>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<!--右键菜单部分-->
<ul>
<li>添加子栏目</li>
<li>删除该栏目</li>
<li>重命名</li>
<li>上移</li>
<li>下移</li>
</ul>


<form action="${Path}/CustomServlet" method="POST">
<ul>
<c:choose>
    <c:when test="${ empty param.tabIndex}">
        <li><span>▶</span><input type="text" value="添加栏目" name="columnName" tabindex="1"><ul><li><a href="edit.jsp" target="iframe"><input type="text" value="添加子栏目" name="subcolumnName" tabindex="1"></a></li></ul></li>
	</c:when>
    <c:otherwise>
        <define:custom courseName="${param.courseName}" tabIndex="${param.tabIndex}" />
    </c:otherwise>
</c:choose>

</ul>
<button type="button"><img src="../image/add.png" title="添加子栏目"></button>
<input type="hidden" name="courseName" value="${param.courseName}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex">
<input type="hidden" name="subcolumnIndex">
<input type="hidden" name="operation">
<input type="submit">
</form>

<div>
<iframe scrolling="no" height="650" name="iframeB">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>
<p>欢迎来到精品课程网站,请点击左侧添加按钮,新建您的第一个课程页面</p>
</div>

</body>
</html>
