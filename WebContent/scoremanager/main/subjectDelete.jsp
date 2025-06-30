<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.Subject" %>
<%Subject subject = (Subject)request.getAttribute("subject");%>

<c:import url="/common/base.jsp">
<c:param name="title">科目削除確認</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<html>
<head>
<style>
        .btn-delete {
            background-color: #e74c3c;
            color: white;
            padding: 8px 16px;
            border: none;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-delete:hover {
            background-color: #c0392b;
        }
</style>
</head>
<body>
<h2>科目削除確認</h2>

    <c:if test="${not empty errorMessage}">
<p style="color:red;">${errorMessage}</p>
</c:if>

    <c:if test="${not empty subject}">
<p>
            「<strong>${subject.name}</strong>（${subject.cd}）」を削除してもよろしいですか？
</p>
<form action="SubjectDeleteExecute.action" method="post">
<input type="hidden" name="cd" value="${subject.cd}">
<input type="submit" value="削除" class="btn-delete">
</form>
</c:if>

    <a href="subjectList.jsp">戻る</a>
</body>
</html>
</c:param>
</c:import>