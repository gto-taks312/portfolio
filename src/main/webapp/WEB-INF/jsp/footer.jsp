<!-- 全画面共通のログアウトjsp（フッター部分に設置） -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/StudentAttendance/css/style.css">
</head>

<body>
	<div class="footer">
		<button onclick="return Logout_Dialog()">ログアウト</button>
	</div>
</body>

<script type="text/javascript">
	function Logout_Dialog(){
		var yes = confirm("ログアウトします。よろしいですか？");
		if(yes){
			location.href='/StudentAttendance/LogoutServlet';
		} else {
			return false;
		};
	};
</script>
</html> 