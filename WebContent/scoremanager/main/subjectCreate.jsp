<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
		<form action="SubjectCreateExecute.action">
		<label>科目コード</label><br>
		<input
			value="${cd}"
			type="text"
			name="cd"
			size=100%
			maxlength="3"
			placeholder="科目コードを入力してください"
			required />
		<c:if test="${errors.size()>0}">
			<div>
				<c:forEach var="error" items="${errors}">
					<a>${error}</a>
				</c:forEach>
			</div>
		</c:if>
		<br>
		<label>科目名</label><br>
		<input
			value="${name}"
			type="text"
			name="name"
			size=100%
			maxlength="20"
			placeholder="科目名を入力してください"
			required /><br><br>
		<input type="submit" class="btn btn-primary" value="登録">
		</form><br>
		<a href="SubjectList.action">戻る</a>
		</body>
		</html>
	</c:param>
</c:import>