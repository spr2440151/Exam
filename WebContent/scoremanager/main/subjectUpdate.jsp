<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Subject" %>
<c:import url="/common/base.jsp">
<c:param name="title">

		得点管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
	<%Subject subject = (Subject)request.getAttribute("subject");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目更新</title>
</head>
<head>
<meta charset="UTF-8">
<main class="col  pt-4 px-4">
<!-- ① タイトル帯 -->
<div class="bg-light p-3 mb-4 border rounded">
	<h3 class="m-0">科目情報変更</h3>
</div>

        <!-- フォーム -->
<form action="SubjectUpdateExecute.action" method="get">
	<div class="mb-3">
		<label for="id" class="form-label">科目コード</label>
			<input type="text" name="cd" id="id" class="form-control" value="${subject.cd}" readonly>
	</div>
	        <!-- メッセージ表示 -->
	<c:if test="${errors.size()>0}">
		<div>
			<c:forEach var="error" items="${errors}">
				<a>${error}</a>
			</c:forEach>
		</div>
	</c:if>
	<div class="mb-3">
		<label for="name" class="form-label">科目名</label>
			<input type="text" name="name" id="name" class="form-control" value="${subject.name}" required>
	</div>
	<button type="submit" class="btn btn-primary">変更</button>
	<br>
	<a href="subject_list.jsp" class="btn btn-link">戻る</a>
</form>
</main>
</body>
</html>
</c:param>
</c:import>