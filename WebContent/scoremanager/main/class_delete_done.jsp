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
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス削除</h2>
		<div>
			<p class="text-center" style="background-color:#66CC99">削除が完了しました</p>
		</div>
		<div></div>
		<br>
		<a href="ClassList.action">クラス一覧</a>
		</body>
		</html>
	</c:param>
</c:import>