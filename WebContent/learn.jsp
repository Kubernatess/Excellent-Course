<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>learn</title>
<link href="css/learn.css" rel="stylesheet" type="text/css">
<script src="js/learn.js"></script>
</head>

<body>
<!--子标题部分-->
<aside>
<define:learn courseID="${param.courseID}" tabIndex="${param.tabIndex}" />
</aside>

<div>
<iframe name="subIframe" scrolling="yes">
  <p>您的浏览器不支持  iframe 标签。</p>
</iframe>
</div>

</body>
</html>
