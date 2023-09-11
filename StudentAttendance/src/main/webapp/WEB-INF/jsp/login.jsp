<!-- ログイン画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div class="center">
		<h1>ログイン画面</h1>
		<form action="/StudentAttendance/LoginServlet" method="post">
			<div>
				<label>管理者ID</label><br>
				<input type="text" name="student_id">
			</div>
			<div>
				<label>パスワード</label><br>
				<input type="password" name="password">
			</div>
			<div>
				<button type="submit">ログイン</button>
			</div>
		</form>
	</div>
</body>
</html>