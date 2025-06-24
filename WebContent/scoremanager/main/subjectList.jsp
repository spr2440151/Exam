<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		<div align="right">
			<a href="SubjectCreate.action">新規登録</a>
		</div>
		<table style="table-layout: fixed; width: 100%;">
		<tr><th>科目コード</th><th>科目名</th>
		<% for (Subject stu : list) { %>
		<tr>
		<td><%=stu.getCd() %></td>
		<td><%=stu.getName() %></td>
		<td><a href="SubjectUpdate.action">更新</a></td>
		<td><a href="SubjectDelete.action">削除</a></td>
		</tr>
		<% } %>
		</table>
		</body>
		</html>
	</c:param>
</c:import>