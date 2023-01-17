<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MemberDAO" %>
<%@ page import="dto.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧表示</title>
</head>
<body>
	<h3>会員一覧</h3>
	<table border="1">
		<tr>
			<th>名前</th>
			<th>年齢</th>
			<th>性別</th>
			<th>電話番号</th>
			<th>メールアドレス</th>	
		</tr>
	<%
		@SuppressWarnings("unchecked")
		List<Member> list = (ArrayList<Member>)request.getAttribute("list");
		for(Member m : list) {
	%>
			<tr>
				<td><%=m.getName() %></td>
				<td><%=m.getAge() %></td>
				<%
				String gender = "";
				if(m.getGender() == 0){
					gender = "男";
				} else if(m.getGender() == 1){
					gender = "女";
				} else {
					gender = "その他";
				}
				%>
				<td><%=gender %></td>
				<td><%=m.getPhone_number() %></td>
				<td><%=m.getMail() %></td>
			</tr>
	<%
		}
	%>
	</table>
	
	<a href="./">戻る</a>
</body>
</html>