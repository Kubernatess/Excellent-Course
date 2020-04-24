<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="define" uri="http://www.unity3d.cn/unity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>edit</title>
<link href="../css/edit.css" rel="stylesheet" type="text/css">
<script src='../js/edit.js'></script>
</head>

<body>
<form action="../EditServlet" method="POST">

<!-- 工具箱部分 -->
<ul>
<li><button type="button" id="clear"><img src="../image/tools/clear.png" title="清除字体样式"></button></li>
<li class="dropdown">
<button type="button"><img src="../image/tools/font-size.png" title="字体大小"></button>
<div class="dropdown-content">
	<button type="button">16px</button>
    <button type="button">20px</button>
	<button type="button">24px</button>
	<button type="button">28px</button>
	<button type="button">32px</button>
	<button type="button">36px</button>
</div>
</li>
<li><button type="button" id="normal"><img src="../image/tools/normal.png" title="正常字体"></button></li>
<li><button type="button" id="bold"><img src="../image/tools/bold.png" title="字体加粗"></button></li>
<li><button type="button" id="italic"><img src="../image/tools/italic.png" title="斜体字体"></button></li>
<li><button type="button" id="fontColor"><img src="../image/tools/font-color.png" title="字体颜色"><input type="color" name="favcolor"></button></li>
<li><button type="button" id="leftAlign"><img src="../image/tools/left-align.png" title="左对齐"></button></li>
<li><button type="button" id="centerAlign"><img src="../image/tools/center-align.png" title="居中对齐"></button></li>
<li><button type="button" id="rightAlign"><img src="../image/tools/right-align.png" title="右对齐"></button></li>
<li><button type="button" id="hyperlink"><img src="../image/tools/hyperlink.png" title="创建超链接"></button></li>
<li><button type="button"><img src="../image/tools/attachment.png"><input type="file" name="attachment" title="附件形式下载" /></button></li>
<li><button type="button"><img src="../image/tools/video.png"><input type="file" accept="video/*" name="video" title="上传视频"></button></li>
<li><button type="button"><img src="../image/tools/photo.png"><input type="file" accept="image/*" name="image" title="上传图片"></button></li>
<li><button type="button"><img src="../image/tools/pdf.png"><input type="file" accept="application/pdf" name="pdf" title="pdf文件"></button></li>
<li><button type="button"><img src="../image/tools/word.png"><input type="file" accept="application/msword" name="word" title="word文件"></button></li>
<li><button type="button"><img src="../image/tools/ppt.png"><input type="file" accept="application/vnd.ms-powerpoint" name="ppt" title="ppt文件"></button></li>
<li><button type="button"><img src="../image/tools/excel.png"><input type="file" accept="application/vnd.ms-excel" name="excel" title="excel文件"></button></li>
<li><button type="button"><img src="../image/tools/txt.png"><input type="file" accept="text/plain" name="txt" title="文本文件"></button></li>

<li><button type="button" id="TextField"><img src="../image/tools/text.png" title="文本框"></button></li>
<li><button type="button" id="Textarea"><img src="../image/tools/textarea.png" title="多行文本框"></button></li>
<li><button type="button" id="radio"><img src="../image/tools/radio.png" title="单选按钮"></button></li>
<li><button type="button" id="checkbox"><img src="../image/tools/checkbox.png" title="复选框"></button></li>
<li><button type="button" id="UploadFile"><img src="../image/tools/upload.png" title="上传按钮"></button></li>
<li><button type="button" id="Submit"><img src="../image/tools/submit.png" title="提交按钮"></button></li>
<li><button type="button" id="Submithomework"><img src="../image/tools/submit-homework.png" title="提交作业"></button></li>
</ul>


<!--可编辑容器-->
<div contentEditable="true" id="editor">
<define:edit courseID="${param.courseID}" tabIndex="${param.tabIndex}" columnIndex="${param.columnIndex}" />
</div>
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex" value="${param.columnIndex}">
<input type="hidden" name="content">
<input type="submit" value="保存" id="save" />
<input type="submit" value="保存并预览" id="preview" />

</form>


</body>
</html>

