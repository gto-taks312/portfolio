<!-- 日報登録失敗画面（入力した内容がDBに保存できないとき） -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="object.DiarySaveFailed" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報保存失敗</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<% DiarySaveFailed diarySaveFailed = (DiarySaveFailed)session.getAttribute("diarySaveFailed"); %>
	
	<div style="text-align: center;">
		<h1>日報の保存失敗</h1>
		<%	String[] failed = diarySaveFailed.getDiaryData(); %>
		<%	if(diarySaveFailed.getSubject_length() > 50){ %>
			<P><%= failed[0] %></P><% } %>
		<% if(diarySaveFailed.getContent_length() > 255){ %>
			<P><%= failed[1] %></P><% } %>
		<% if(diarySaveFailed.getLearning_length() > 255){ %>
			<P><%= failed[2] %></P><% } %>
		<% if(diarySaveFailed.getDifficulty_length() > 255){ %>
			<P><%= failed[3] %></P><% } %>
		<% if(diarySaveFailed.getLevel() <= 0 || diarySaveFailed.getLevel() > 5){ %>
			<P><%= failed[4] %></P><% } %>
		
		<div>
			<button onclick="location.href='/StudentAttendance/DiaryServlet'">日報記録画面へ戻る</button>
		</div>
	</div>
</body>
</html>