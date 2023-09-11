<!-- 日報登録失敗画面（入力した値が数字以外の時に表示する画面） -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報保存失敗</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div style="text-align: center;">
		<h1>日報の保存失敗</h1>
		<p>習熟度の欄に数字以外が入力されました。<br>０〜５の数字を入力してください。</p>
		<div>
			<button onclick="location.href='/StudentAttendance/DiaryServlet'">日報記録画面へ戻る</button>
		</div>
	</div>
</body>
</html>