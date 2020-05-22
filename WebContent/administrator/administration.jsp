<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>administration</title>
<link href="../css/administration.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script src="../js/administration.js"></script>
</head>

<body>
<!--给登陆用户赋予通行证-->
<c:if test="${sessionScope.status ne 'administrator'}">
	<script>window.location.href="../login.jsp";</script>
</c:if>

<span>用户管理</span>
<button type="button">添加账号</button>
<form id="search">
<input type="search" placeholder="查询学号/工号" /><input type="image" src="../image/search.png" form="" />
</form>  

<!--用户表单部分-->
<form action="../AdministrationServlet" method="POST">
<table>
<tr>
<th>学号/工号</th><th>身份</th><th>密码</th><th>姓名</th><th>系别</th><th>操作</th>
</tr>
<%
	List<Map<String,String>> list = DatabaseAccess.getAllUser();
	Iterator<Map<String,String>> iterator = list.iterator();
	while(iterator.hasNext()){
		Map<String,String> map = iterator.next();
		String identity = map.get("identity");
		String status = map.get("status");
		String password = map.get("password");
		String username = map.get("username");
		String depart = map.get("depart");
%>
<tr>
<td><input type="text" value="<%= identity%>" name="identity" disabled required maxlength="10"></td>
<td>
	<select name="status" disabled required data-status="<%= status%>">
    	<option value="student">student</option>
        <option value="teacher">teacher</option>
    </select>
</td>
<td><input type="text" value="<%= password%>" name="password" disabled required maxlength="16"></td>
<td><input type="text" value="<%= username%>" name="username" disabled required></td>
<td><select name="depart" data-depart="<%= depart%>" disabled required>
	<option value="软件工程系">软件工程系</option>
	<option value="网络技术系">网络技术系</option>
	<option value="电子系">电子系</option>
	<option value="游戏系">游戏系</option>
	<option value="数码媒体系">数码媒体系</option>
	<option value="管理系">管理系</option>
	<option value="国际经贸系">国际经贸系</option>
	<option value="财会系">财会系</option>
	<option value="外语系">外语系</option>
	<option value="计算机系">计算机系</option>
</select></td>
<td>
	<input type="image" src="../image/edit.png" name="edit">
	<input type="image" src="../image/trash.png" name="remove">
</td>
</tr>
<% 	
	}
%>
<input type="submit" hidden>
</table>
</form>
        
<!-- 分页部分 -->
<ul id="page">
<li>«</li><li class="active">1</li><li>»</li>
</ul>

</body>
</html>