<!-- ログイン成功画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="object.Student"  %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面一覧</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div class="center">
		<h1>ホーム一覧</h1>
		
		<!-- セットされていた生徒情報を取得する -->
		<% Student student = (Student)session.getAttribute("student"); %>
		<div  class="container">
			<div> <%= student.getDepartment() %> &emsp;</div>
			<div> <%= student.getId() %> 番 &emsp;</div>
			<div> <%= student.getName() %> </div>
		</div>
		
		<button onclick="location.href='/StudentAttendance/DiaryServlet'">日報</button><br>
		<button onclick="location.href='/StudentAttendance/'">カレンダー</button><br>
		<button onclick="location.href='/StudentAttendance/TimeConfirmationServlet'">出席状況確認</button>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>