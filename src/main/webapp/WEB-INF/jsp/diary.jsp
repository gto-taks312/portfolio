<!-- 日誌記録画面 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日報記入画面</title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>
<body>
	<div class="center">
		<h1>日報記入画面</h1>
		<form action="/StudentAttendance/DiaryServlet" method="post" class="diary">
			<div>
				<label>科目</label><br>
				<input type="text" name="kamoku">
			</div>
			<div>
				<label>講義内容</label><br>
				<input type="text" name="naiyou">
			</div>
			<div>
				<label>学んだこと</label><br>
				<input type="text" name="manabi">
			</div>
			<div>
				<label>難しかった点</label><br>
				<textarea name="muzukashii" cors="20" rows="5"></textarea>
			</div>
			<div>
				<label>習熟度5段階評価</label><br>
				<input type="text" name="hyouka">
			</div>
			<div>
				<input type="submit" value="記録を保存する">
			</div>
		</form>
		<button onclick="location.href='/StudentAttendance/LookBackServlet'">過去の記録を見る</button>
	</div>
	<jsp:include page="footer_2.jsp"/>
	<jsp:include page="footer.jsp"/>
</body>
</html>