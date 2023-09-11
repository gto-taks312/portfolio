<!-- attendance_timeまたはleavint_timeがnullの日付が選択された時に表示する画面 -->

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
		<h1>日時の選択失敗</h1>
		<p>1日も登下校していない月が選択されました。<br>再度、月を選択してください。</p>
		<div>
			<button onclick="location.href='/StudentAttendance/TimeConfirmationServlet'">日報記録画面へ戻る</button>
		</div>
	</div>
</body>
</html>