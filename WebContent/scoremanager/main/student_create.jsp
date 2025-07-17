<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bean.Student, java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%List<Student>list=(List<Student>)session.getAttribute("list");%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報登録</title>
</head>
<body>

    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

    <form action="StudentCreateExecute.action" method="post">

        <!-- 入学年度 -->
        <label>入学年度</label><br>
        <select name="entYear" required style="width:100%;">
            <option value="0">-----</option>
            <c:forEach var="year" items="${yearList}">
            <option value="${year}" <c:if test="${year == entYear}">selected</c:if>>${year}</option>
            </c:forEach>
        </select><br><br>

        <!-- バリデーションエラー -->
        <c:if test="${not empty errors}">
            <div>
                <ul style="color:gold;">
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <!-- 学生番号 -->
        <label>学生番号</label><br>
        <input
            type="text"
            name="no"
            value="${no}"
            style="width:100%;"
            maxlength="10"
            placeholder="学生番号を入力してください"
            required /><br>
        <c:if test="${not empty duplicateError}">
        <span style="color:gold;">${duplicateError}</span>
        </c:if>
        <br><br>

        <!-- 氏名 -->
        <label>氏名</label><br>
        <input
            type="text"
            name="name"
            value="${name}"
            style="width:100%;"
            maxlength="50"
            placeholder="氏名を入力してください"
            required /><br><br>

        <!-- クラス -->
        <label>クラス</label><br>
        <%
            Set<String> classSet = new HashSet<>();
            for (Student stu : list) {
                if (stu.getClassNum() != null && !stu.getClassNum().isEmpty()) {
                    classSet.add(stu.getClassNum());
                }
            }
        %>

       <select name="classNum" required style="width:100%;">
           <% for (String classNum : classSet) { %>
           <option value="<%= classNum %>"><%= classNum %></option>
           <% } %>
       </select><br><br>

        <!-- 登録ボタン -->
        <input type="submit" class="btn btn-primary" value="登録して終了">
    </form>
    <br>

    <a href="StudentList.action">戻る</a>

</body>
</html>

    </c:param>
</c:import>