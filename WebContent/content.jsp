<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>content</title>
<link href="css/content.css" rel="stylesheet" type="text/css">
<script>
window.onload=function(){
    let imgs = document.querySelectorAll(".content>div>img");
    for(let i=0; i<imgs.length; i++){
        imgs[i].style.marginTop = -(imgs[i].height/2)+80+'px'
    }
};
</script>
</head>
<body>
<!-- 内容显示部分 -->
<define:content depart="${param.depart}" major="${param.major}" key="${param.key}" />
</body>
</html>