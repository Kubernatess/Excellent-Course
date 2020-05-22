<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="db.DatabaseAccess,java.util.Map,bean.Column" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>edit</title>
<link href="../css/edit.css" rel="stylesheet" type="text/css">
<script src='../js/edit.js'></script>
</head>

<body>
<!--右键菜单部分-->
<ul>
<li>上移</li>
<li>下移</li>
<li>重命名</li>
<li>删除该栏目</li>
</ul>

<form action="../CustomServlet" method="POST" id="asideForm">
<%
	String courseID = request.getParameter("courseID");
	String tabIndex = request.getParameter("tabIndex");	
	Map<Integer,String> map = DatabaseAccess.getColumnsByCourseIdAndTabId(courseID,tabIndex);
	for(int columnIndex : map.keySet()){
		String columnName = map.get(columnIndex);
		out.println("<a href=\"edit.jsp?courseID="+courseID+"&tabIndex="+tabIndex+"&columnIndex="+columnIndex+"\">");
		out.println("<input type=\"text\" value=\""+columnName+"\" data-columnindex=\""+columnIndex+"\" readonly>");
		out.println("</a> ");
	}
%>
<button type="button"><img src="../image/add.png" title="添加子栏目"></button>
<input type="hidden" name="courseID" value="${param.courseID}">
<input type="hidden" name="tabIndex" value="${param.tabIndex}">
<input type="hidden" name="columnIndex">
<input type="hidden" name="anotherIndex">
<input type="submit" disabled hidden>
</form>


<main>
<p>欢迎来到精品课程网站,请点击左侧添加按钮,新建您的第一个课程页面</p>

<form action="../EditServlet" method="POST" id="editForm">
<!-- 工具箱部分 -->
<button type="button" id="clear"><img src="../image/tools/clear.png" title="清除字体样式"></button>
<span class="dropdown">
<button type="button" class="dropbtn"><img src="../image/tools/font-size.png" title="字体大小"></button>
<ul class="dropdown-content">
	<li>16px</li>
    <li>20px</li>
	<li>24px</li>
	<li>28px</li>
	<li>32px</li>
	<li>36px</li>
</ul>
</span>
<button type="button" id="normal"><img src="../image/tools/normal.png" title="正常字体"></button>
<button type="button" id="bold"><img src="../image/tools/bold.png" title="字体加粗"></button>
<button type="button" id="italic"><img src="../image/tools/italic.png" title="斜体字体"></button>
<button type="button"><img src="../image/tools/font-color.png" title="字体颜色"><input type="color" id="fontColor"></button>
<button type="button" id="leftAlign"><img src="../image/tools/left-align.png" title="左对齐"></button>
<button type="button" id="centerAlign"><img src="../image/tools/center-align.png" title="居中对齐"></button>
<button type="button" id="rightAlign"><img src="../image/tools/right-align.png" title="右对齐"></button>
<button type="button" id="hyperlink"><img src="../image/tools/hyperlink.png" title="创建超链接"></button>
<button type="button"><img src="../image/tools/attachment.png"><input type="file" id="attachment" title="附件形式下载" /></button>
<button type="button"><img src="../image/tools/video.png"><input type="file" accept="video/*" id="video" title="上传视频"></button>
<button type="button"><img src="../image/tools/photo.png"><input type="file" accept="image/*" id="image" title="上传图片"></button>
<button type="button"><img src="../image/tools/pdf.png"><input type="file" accept="application/pdf" id="pdf" title="pdf文件"></button>
<button type="button"><img src="../image/tools/word.png"><input type="file" accept="application/msword" id="word" title="word文件"></button>
<button type="button"><img src="../image/tools/ppt.png"><input type="file" accept="application/vnd.ms-powerpoint" id="ppt" title="ppt文件"></button>
<button type="button"><img src="../image/tools/excel.png"><input type="file" accept="application/vnd.ms-excel" id="excel" title="excel文件"></button>
<button type="button"><img src="../image/tools/txt.png"><input type="file" accept="text/plain" id="txt" title="文本文件"></button>
<fieldset id="objective">
<button type="button" id="radio"><img src="../image/tools/radio.png" title="单选按钮"></button>
<button type="button" id="checkbox"><img src="../image/tools/checkbox.png" title="复选框"></button>
</fieldset>
<fieldset id="subjective">
<button type="button" id="Textarea"><img src="../image/tools/textarea.png" title="多行文本框"></button>
<button type="button" id="UploadFile"><img src="../image/tools/upload.png" title="上传按钮"></button>
</fieldset>
<button type="button" id="btnSubmit"><img src="../image/tools/submit.png" title="提交按钮"></button>


<!--可编辑容器-->
<div contentEditable="true" id="editor">
<%
	if(request.getParameter("columnIndex")==null){
		return;
	}
	int columnIndex = Integer.parseInt(request.getParameter("columnIndex"));
	Column column = new Column();
	column.setCourseID(courseID);
	column.setTabIndex(tabIndex);
	column.setIndex(columnIndex);
	Map<String,String> columnMap = DatabaseAccess.getColumn(column);
	String content = columnMap.get("content");
	if(content==null){
		out.println("<span>在此处编辑</span>");
	}
	else{
		out.println(content);
	}
%>
</div>
<input type="hidden" name="courseID" value="<%= courseID%>">
<input type="hidden" name="tabIndex" value="<%= tabIndex%>">
<input type="hidden" name="columnIndex" value="<%= columnIndex%>">
<input type="hidden" name="content">
<input type="hidden" name="sequence">
<input type="submit" value="保存" id="save" class="btn" />
<input type="submit" value="保存并预览" id="preview" class="btn" />

</form>

</main>


</body>
</html>

