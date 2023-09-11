<!-- 日報登録失敗画面（どこかに空欄があるとき） -->

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
		<p>日報に空欄の箇所があるため、保存に失敗しました。<br>全ての箇所を記入して保存してください。</p>
		<div>
			<button onclick="location.href='/StudentAttendance/DiaryServlet'">日報記録画面へ戻る</button>
		</div>
	</div>
</body>
</html>