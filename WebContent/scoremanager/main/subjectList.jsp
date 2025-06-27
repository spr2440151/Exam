<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Subject, java.util.List" %>
<%List<Subject>list=(List<Subject>)session.getAttribute("list");%>
<c:import url="/common/base.jsp">
<c:param name="title">

		得点管理システム
</c:param>

	<c:param name="scripts"></c:param>
<c:param name="content">

<!DOCTYPE html>
<html>
<head>
<body>
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
<div style="margin-top: 10px; margin-left: auto; width: fit-content; margin-right: 55px;">
<a href="SubjectCreate.action">新規登録</a>
</div>
<table style="table-layout: fixed; width: 100%;">
	<tr><th>科目コード</th><th>科目名</th>
	<% for (Subject sub : list) { %>
		<tr>
			<td style="border-bottom: 1px solid #ccc;"><%=sub.getCd() %></td>
			<td style="white-space: nowrap; border-bottom: 1px solid #ccc;"><%=sub.getName() %></td>
			<td style="padding-left: 220px; border-bottom: 1px solid #ccc;"colspan="2">
				<a href="SubjectUpdate.action?cd=<%=sub.getCd()%>">変更</a>
			</td>
			<td style="padding-left: 15px; border-bottom: 1px solid #ccc;">
				<a href="SubjectDelete.action?cd=<%=sub.getCd()%>">削除</a>
			</td>
		</tr>
	<% } %>
</table>
</body>
</html>
</c:param>
</c:import>
