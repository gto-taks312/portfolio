<!-- DBより取得した指定した日付の日報の表示画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="object.DiaryData" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択した日報</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<% DiaryData diary = (DiaryData)session.getAttribute("diary"); %>
	<div class="center">
		<h1>過去の日報</h1>
		<% if(diary.getSubject()!=null){ %>
			<table>
				<tr>
					<th>科目</th>
					<th>講義内容</th>
					<th>学んだこと</th>
					<th>難しかった点</th>
					<th>習熟度5段階評価</th>
				</tr>
				<tr>
					<td><%= diary.getSubject() %></td>
					<td><%= diary.getContent() %></td>
					<td><%= diary.getLearning() %></td>
					<td><%= diary.getDifficulty() %></td>
					<td><%= diary.getLevel() %></td>
				</tr>
			</table>
		<% }else{ %>
			<p>その日の記録はありません。</p>
		<% } %>	
	</div>
	<div style="text-align: right;">
		<button onclick="location.href='/StudentAttendance/DiaryServlet'">日報記録画面に戻る</button>
	</div>
	<jsp:include page="footer_2.jsp"/>
	<jsp:include page="footer.jsp"/>
</body>
</html>