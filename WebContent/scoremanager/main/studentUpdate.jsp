<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Student" %>
<c:import url="/common/base.jsp">
<c:param name="title">

		学生管理システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
	<%Student student = (Student)request.getAttribute("Student");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生更新</title>
</head>
<head>
<meta charset="UTF-8">
<main class="col  pt-4 px-4">
<!-- ① タイトル帯 -->
<div class="bg-light p-3 mb-4 border rounded">
	<h3 class="m-0">学生情報変更</h3>
</div>

        <!-- フォーム -->
<form action="StudentUpdateExecute.action" method="get">
	<div class="mb-3">
		<label for="id" class="form-label">入学年度</label>
			<input type="text" name="id" id="id" class="form-control" value="${student.entYear}" readonly>
	</div>

	<div class="mb-3">
		<label for="id" class="form-label">学生番号</label>
			<input type="text" name="id" id="id" class="form-control" value="${student.no}" readonly>
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
		<label for="name" class="form-label">学生氏名</label>
			<input type="text" name="name" id="name" class="form-control" value="${student.name}" required>
	</div>
	<div class="mb-3">
		<label for="id" class="form-label">クラス</label>
			<input type="text" name="id" id="id" class="form-control" value="${student.classNum}" >
	</div>
	<button type="submit" class="btn btn-primary">変更</button>
	<br>
	<a href="studenttList.jsp" class="btn btn-link">戻る</a>
</form>
</main>
</body>
</html>
</c:param>
</c:import>
