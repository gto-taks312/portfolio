<!-- 選択した日付の登下校時間を表示する画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登下校時間確認画面</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<% ArrayList<ArrayList<String>> time = (ArrayList<ArrayList<String>>)session.getAttribute("time"); %>
		<table>
		<tr>
			<th>登校時間</th>
			<th>遅刻の有無</th>
			<th>下校時間</th>
			<th>早退の有無</th>
		</tr>
		
		<% for(int i = 0; i < time.size(); i++) {  %>
		<tr>
			<td><%= time.get(i).get(0) %></td>
			<td><%= time.get(i).get(1) %></td>
			<td><%= time.get(i).get(2) %></td>
			<td><%= time.get(i).get(3) %></td>
		</tr><br>
		<% } %>
	</table>
	<jsp:include page="footer_2.jsp"/>
	<jsp:include page="footer.jsp"/>
</body>
</html>