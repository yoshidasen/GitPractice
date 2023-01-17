<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Accounts" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
	Accounts account = (Accounts)session.getAttribute("input_data");
	%>
	名前：<%=account.getName() %><br>
	メール：<%=account.getMail() %><br>
	パスワード：********<br>
	<a href="RegisterExecuteServlet">OK</a><br>
	<a href="RegisterFormServlet">戻る</a>
</body>
</html>