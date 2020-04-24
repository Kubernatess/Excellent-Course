<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>administration</title>
<link href="../css/administration.css" rel="stylesheet" type="text/css">
<script src="../js/administration.js"></script>
</head>

<body>
<!--给登陆用户赋予通行证-->
<c:if test="${sessionScope.status ne 'administrator'}">
	<script>window.location.href="../login.jsp";</script>
</c:if>

<span>用户管理</span>
<button type="button">添加账号</button>
<form>
<input type="search" placeholder="查询学号/工号" /><img src="../image/search.png">
</form>  

<!--用户表单部分-->
<form action="AdministrationServlet" method="POST">
<table>
<tr>
<th>学号/工号</th><th>身份</th><th>密码</th><th>姓名</th><th>系别</th><th>专业</th><th>操作</th>
</tr>
<define:administration />
<input type="hidden" name="operation">
<input type="submit" hidden>
</table>
</form>
        
<!-- 分页部分 -->
<div class="page">
<ul>
<li><a href="#">«</a></li><li><a href="#">1</a></li><li><a class="active" href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">...</a></li><li><a href="#">30</a></li><li><a href="#">»</a></li>
</ul>
</div>

</body>
</html>