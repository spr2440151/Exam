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
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス登録</h2>
<form action="ClassCreateExecute.action">
<label>クラス</label><br>
<input

			value="${cd}"

			type="text"

			name="cd"

			size=100%

			maxlength="3"

			placeholder="クラス番号を入力してください"

			required />
<c:if test="${errors.size()>0}">
<div>
<c:forEach var="error" items="${errors}">
<font color="gold"><a>${error}</a></font>
</c:forEach>
</div>
</c:if>
<br><br>



<input type="submit" class="btn btn-primary" value="登録">
</form><br>
<a href="ClassList.action">戻る</a>
</body>
</html>
</c:param>
</c:import>