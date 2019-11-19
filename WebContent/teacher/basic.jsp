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
<!--给登陆用户赋予通行证-->
<c:if test="${ sessionScope.status ne '老师' }">
	<script>window.location.href="../login.jsp";</script>
</c:if>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<!--侧栏部分-->
<aside>
<button type="button" disabled><img src="../image/add-grey.png" title="添加子栏目"></button>
</aside>


<!--填写基本信息的表单-->
<form method="POST" action="${Path}/BasicServlet" enctype="multipart/form-data">

<define:basic courseName="${param.courseName}" />

<input type="submit" id="save" value="保存" />
<input type="button" id="preview" value="保存并预览" />

</form>


<!--模态框部分-->
<div id="outer">
	<div id="model">
    <span>选择模板</span><span id="close">×</span>
    <ul>
    <li><img src="../image/image(2).png"><span>默认</span></li>
    <li><img src="../image/image(2).png"><span>蓝色天空</span></li>
    <li><img src="../image/image(2).png"><span>简约风格</span></li>
    <li><img src="../image/image(2).png"><span>黑绿之旅</span></li>
    <li><img src="../image/image(2).png"><span>简约至极</span></li>
    <li><img src="../image/image(2).png"><span>蓝色清新</span></li>
    <li><img src="../image/image(2).png"><span>黑色低调</span></li>
    <li><img src="../image/image(2).png"><span>荷塘月色</span></li>
    <li><img src="../image/image(2).png"><span>水乡墨雅</span></li>
    <li><img src="../image/image(2).png"><span>优雅世界</span></li>
    <li><img src="../image/image(2).png"><span>超级模板</span></li>
    <li><img src="../image/image(2).png"><span>Web之道</span></li>
    </ul>
    <button type="button" id="confirm">确认</button>
    <button type="button" id="cancel">取消</button>
    </div>
</div>

</body>
</html>
