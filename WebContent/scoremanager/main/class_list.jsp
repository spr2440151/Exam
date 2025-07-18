<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Subject, java.util.List" %>
<%List<String>list=(List<String>)session.getAttribute("list");%>
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
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
<div style="margin-top: 10px; margin-left: auto; width: fit-content; margin-right: 55px;">
<a href="ClassCreate.action">新規登録</a>
</div>
<table style="table-layout: fixed; width: 100%;">
	<tr><th>クラス番号</th>
	<% for (String cln : list) { %>
		<tr>
			<td style="border-bottom: 1px solid #ccc;"><%=cln %></td>
			<td style="padding-left: 220px; border-bottom: 1px solid #ccc;"colspan="2">
				<a href="ClassUpdate.action?classNum=<%=cln%>">変更</a>
			</td>
			<td style="padding-left: 15px; border-bottom: 1px solid #ccc;">
				<a href="ClassDelete.action?classNum=<%=cln%>">削除</a>
			</td>
		</tr>
	<% } %>
</table>
</body>
</html>
</c:param>
</c:import>
