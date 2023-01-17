<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員削除</title>
</head>
<body>
	<h3>会員削除</h3>
	<form action="Kadai1603DeleteMemberServlet" method="post">
		メールアドレス:<input type="email" name="mail"><br>
		<input type="submit" value="削除">
	</form>
</body>
</html>