<!-- ログイン失敗画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン失敗</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div style="text-align: center;">
		<h1>ログイン失敗</h1>
		<p>IDまたはパスワードが違います。<br>ログイン画面に戻り、もう1度ID・パスワードの入力をお願いします。</p>
		<div>
			<button onclick="location.href='/StudentAttendance/loginservlet'">ログイン画面へ戻る</button>
		</div>
	</div>
</body>
</html>