<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認</title>
</head>
<body>
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		Member member = (Member)session.getAttribute("input_data");
	%>
	名前:<%=member.getName() %><br>
	年齢:<%=member.getAge() %><br>
	<%
	String gender = "";
	if(member.getGender() == 0){
		gender = "男";
	} else if(member.getGender() == 1){
		gender = "女";
	} else {
		gender = "その他";
	}
	%>
	性別:<%=gender%><br>
	電話番号:<%=member.getPhone_number() %><br>
	メールアドレス:<%=member.getMail() %><br>
	パスワード:*********<br>
	<a href="Kadai1601ExecuteServlet">OK</a>
	<a href="Kadai1601RegisterFormServlet">戻る</a>
</body>
</html>