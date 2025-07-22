<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.School" %>
<%School school = (School)request.getAttribute("school");%>
<%String num = (String)request.getAttribute("num"); %>

<c:import url="/common/base.jsp">
<c:param name="title">クラス削除確認</c:param>
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
<h2>クラス削除確認</h2>
    <c:if test="${not empty errorMessage}">
<p style="color:red;">${errorMessage}</p>
</c:if>

    <c:if test="${not empty school}">
<p>
            「<%=school.getName() %> のクラス番号<%=num %>」を削除してもよろしいですか？
</p>
<form action="ClassDeleteExecute.action" method="post">
<input type="hidden" name="num" value="${num}">
<input type="submit" value="削除" class="btn-delete">
</form>
</c:if>

    <a href="class_list.jsp">戻る</a>
</body>
</html>
</c:param>
</c:import>