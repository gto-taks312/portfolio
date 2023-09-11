<!-- 日時を選択して、過去の日報を見るページ -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>過去の日報</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div class="center">
		<form action="/StudentAttendance/LookBackServlet" method="post">
			<p>希望の日時を選択してください</p>
			<input type="date" name="hiduke"></input>
			<input type="submit" value="検索する">
		</form>
	</div>
	<jsp:include page="footer_2.jsp"/>
	<jsp:include page="footer.jsp"/>
</body>
</html>