<!-- 指定した日付の登校・下校時間を確認する画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登下校確認画面</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div class="center">
		<form action="/StudentAttendance/TimeConfirmationServlet" method="post">
			<p class="center">何月の登下校時刻を見たいですか？</p>
			<select name="month">
				<% for(int i = 1; i <=12; i++){ %>
					<option value="<%= i %>"><%= i %>月</option>
				<% } %>
			</select>
			<input type="submit" value="検索する">
		</form>
	</div>
	<jsp:include page="footer_2.jsp"/>
	<jsp:include page="footer.jsp"/>
</body>
</html>