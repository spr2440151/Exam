<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Student, java.util.List" %>
<%List<Student>list=(List<Student>)session.getAttribute("list");%>
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
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
<div style="margin-top: 10px; margin-left: auto; width: fit-content; margin-right: 55px;">
<a href="StudentCreate.action">新規登録</a>
</div>
<table style="table-layout: fixed; width: 100%;">
	<tr><th>科目コード</th><th>科目名</th>
	<% for (Student stu : list) { %>
		<tr>
			<td style="border-bottom: 1px solid #ccc;"><%=stu.getEntYear() %></td>
			<td style="border-bottom: 1px solid #ccc;"><%=stu.getNo() %></td>
			<td style="border-bottom: 1px solid #ccc;"><%=stu.getName() %></td>
			<td style="border-bottom: 1px solid #ccc;"><%=stu.getClassNum() %></td>
			<td style="border-bottom: 1px solid #ccc;">
			<td style="border-bottom: 1px solid #ccc;">
				<%= stu.isAttend() ? "○" : "×" %>
			</td>
			<td style=" border-bottom: 1px solid #ccc;">
				<a href="StudentUpdate.action?id=<%=stu.getNo()%>">変更</a>
			</td>
		</tr>
	<% } %>
</table>
</body>
</html>
</c:param>
</c:import>
