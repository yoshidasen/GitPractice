<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除成功</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	
	String mail = request.getParameter("mail");
	
	int result = MemberDAO.DeleteMember(mail);
	%>
	<h3>削除完了</h3>
	<p>メールアドレス <%=mail %> のアカウントの削除が完了しました。</p>
	<a href="./">戻る</a>
</body>
</html>