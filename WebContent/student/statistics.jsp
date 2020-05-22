<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.DatabaseAccess,java.util.Map,java.util.List,java.util.Iterator,bean.*" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>statistics</title>
<link href="../css/statistics.css" rel="stylesheet" type="text/css">
<script src="../js/statistics.js"></script>
</head>
<body>
<%
	String courseID = request.getParameter("courseID");
	String courseName = request.getParameter("courseName");
	out.println("<h1>"+courseName+"</h1>");
%>
<section id="hitcount">
<%
	String studentIdentity = (String) session.getAttribute("identity");
	int pageview = 0;
	List<Map<String,Object>> recordList = DatabaseAccess.getAllRecordByStudentId(studentIdentity,courseID);
	Iterator<Map<String,Object>> iterator = recordList.iterator();
	while(iterator.hasNext()){
		Map<String,Object> map = iterator.next();
		int hitcount = (int) map.get("hitcount");
		pageview += hitcount;
	}
	float ratio = pageview>=500?100:((float)pageview/500)*100;
%>
您当前的网站访问量：<%= pageview%><br>
需要完成的访问量：500<br>
<progress value="<%= ratio%>" max="100"></progress> <samp><%= ratio%></samp>%
</section>

<section id="completion">
当前课程的完成度<br>
<progress max="100"></progress> <samp></samp>%
<%
	Column column = new Column();
	column.setCourseID(courseID);
	Map<String,String> tabMap = DatabaseAccess.getAllTabByCourseId(courseID);
	for(String tabIndex : tabMap.keySet()){
		String tabName = tabMap.get(tabIndex);
		out.println("<h3>"+tabName+"</h3>");		
		Map<Integer,String> columnMap = DatabaseAccess.getColumnsByCourseIdAndTabId(courseID,tabIndex);
		for(int columnIndex : columnMap.keySet()){
			Record record = new Record();
			record.setCourseID(courseID);
			record.setStudentIdentity(studentIdentity);
			record.setTabIndex(tabIndex);
			record.setColumnIndex(columnIndex);
			Map<String,Object> map = DatabaseAccess.getRecord(record);
			boolean finished = false;
			if(map.size()>0){
				finished = (boolean) map.get("finished");
			}
			String columnName = columnMap.get(columnIndex);
			out.println("<figure>"+columnName);
			if(finished){
				out.println("<img src=\"../image/correct.png\">");
			}
			else{
				out.println("<img src=\"../image/correct.png\" style=\"visibility:hidden;\">");
			}
			out.println("</figure>");
		}
	}
%>
</section>

<table border="1">
<caption>以下是所有的测试分数</caption>
<tr>
<th>章节</th><th>小节</th><th>分数</th>
</tr>
<%
	List<Map<String,Object>> list = DatabaseAccess.getTestsByCourseId(courseID);
	int requirements = list.size();
	int unfinished = 0;
	Iterator<Map<String,Object>> iter = list.iterator();
	while(iter.hasNext()){
		Map<String,Object> map = iter.next();
		String testTabIndex = (String) map.get("tabIndex");
		int testColumnIndex = (int) map.get("columnIndex");
		String columnName = (String) map.get("columnName");
		Map<String,String> tabmap = DatabaseAccess.getAllTabByCourseId(courseID);
		String tabName = tabmap.get(testTabIndex);
%>
<tr>
<td><%= tabName%></td>
<td><%= columnName%></td>
<%
		Record record = new Record();
		record.setCourseID(courseID);
		record.setStudentIdentity(studentIdentity);
		record.setTabIndex(testTabIndex);
		record.setColumnIndex(testColumnIndex);
		Map<String,Object> recordMap = DatabaseAccess.getRecord(record);
		float score = (float) recordMap.get("score");
		if(score==-1){
			unfinished += 1;
			out.println("<td>未完成</td>");
		}
		else{
			out.println("<td>"+score+"</td>");
		}
	}
	ratio = ((requirements-unfinished)/(float)requirements)*100;
%>
</tr>
</table>

本课程需要完成<%= requirements%>次测试，你还有<%= unfinished%>次测试未完成
<progress max="100" value="<%= ratio%>"></progress> <samp><%= ratio%></samp>%
<p>总的完成度<progress max="100"></progress> <samp></samp>%</p>

</body>
</html>