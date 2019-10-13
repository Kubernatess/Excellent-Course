<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
<link href="css/login.css" rel="stylesheet" type="text/css">
<script src="js/login.js"></script>
</head>

<body>
<c:set var="Path" scope="request" value="${pageContext.request.contextPath}"/>
<form method="POST" action="${Path}/LoginServlet">
<fieldset>
<legend><img src="image/login.png"></legend>
<input type="hidden" name="hidden" value="student">
<a>普通用户</a><a>会员登陆</a>
<div><img src="image/user-empty-grey.png"><input type="text" name="identity" placeholder="请输入学号"><hr></div>
<div><img src="image/lock.png"><input type="password" name="password" placeholder="请输入密码"><hr></div>
<a>忘记密码?</a><br/>
<input type="submit" value="登陆" name="login" />
</fieldset>
</form>
</body>
</html>