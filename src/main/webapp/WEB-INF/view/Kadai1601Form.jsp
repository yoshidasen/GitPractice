<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			Member me = (Member)session.getAttribute("input_data");
			String gender_m = "";
			String gender_w = "";
			String gender_n = "";
			if(me.getGender() == 0){
				gender_m = "checked";
			} else if (me.getGender() == 1){
				gender_w = "checked";
			} else {
				gender_n = "checked";
			}
	%>
		<p style="color:red">登録に失敗しました</p>
		<h3>新規会員登録</h3>
		<form action="Kadai1601RegisterConfirmServlet" method="post">
			名前:<input type="text" name="name" value="<%=me.getName() %>"><br>
			年齢:<input type="text" name="age" value="<%=me.getAge() %>"><br>
			性別:<input type="radio" name="gender" value="0" <%=gender_m %>>男
			<input type="radio" name="gender" value="1" <%=gender_w %>>女
			<input type="radio" name="gender" value="2" <%=gender_n %>>その他<br>
			電話番号:<input type="tel" name="phone_number" value="<%=me.getPhone_number() %>"><br>
			メールアドレス:<input type="email" name="mail" value="<%=me.getMail() %>"><br>
			パスワード:<input type="password" name="password"><br>
			<input type="submit" value="登録">
		</form>
	<%
		} else {
	%>
		<h3>新規会員登録</h3>
		<form action="Kadai1601RegisterConfirmServlet" method="post">
			名前:<input type="text" name="name"><br>
			年齢:<input type="text" name="age"><br>
			性別:<input type="radio" name="gender" value="0">男
			<input type="radio" name="gender" value="1">女
			<input type="radio" name="gender" value="3">その他<br>
			電話番号:<input type="tel" name="phone_number"><br>
			メールアドレス:<input type="email" name="mail"><br>
			パスワード:<input type="password" name="password"><br>
			<input type="submit" value="登録">
		</form>
	<%
		}
	%>
		
</body>
</html>