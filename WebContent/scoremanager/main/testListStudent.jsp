<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Test, bean.Subject, bean.Student, bean.TestListSubject, bean.TestListStudent, java.util.List" %>

<%
    List<Test> tlist = (List<Test>) session.getAttribute("tList");
    List<Subject> slist = (List<Subject>) session.getAttribute("sList");
    List<String> clist = (List<String>) session.getAttribute("cList");
    List<Integer> yearList = (List<Integer>) session.getAttribute("yearList");
    Student stu = (Student) session.getAttribute("student");
    List<TestListSubject> sublist = (List<TestListSubject>) session.getAttribute("subList");
    List<TestListStudent> stulist = (List<TestListStudent>) session.getAttribute("stuList");

    String f1 = request.getParameter("f1");
    String f2 = request.getParameter("f2");
    String f3 = request.getParameter("f3");
    String f4 = request.getParameter("f4");
%>

<c:import url="/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">

<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

<!-- 🔍 科目情報フォーム -->
<form method="get" class="border p-3 mb-3 bg-light rounded">

<div class="row g-3 align-items-center mb-3">
    <div class="col-auto">
        <label class="col-form-label fw-bold">科目情報</label>
    </div>

    <div class="col-auto">
        <label for="entYear" class="col-form-label">入学年度</label>
        <select id="entYear" name="f1" class="form-select">
            <option value="">--------</option>
            <c:forEach var="year" items="${yearList}">
                <c:choose>
                    <c:when test="${year == param.f1}">
                        <option value="${year}" selected>${year}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${year}">${year}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>

    <div class="col-auto">
        <label for="classNum" class="col-form-label">クラス</label>
        <select id="classNum" name="f2" class="form-select">
            <option value="">--------</option>
            <% for (String classNum : clist) { %>
                <option value="<%= classNum %>" <%= classNum.equals(f2) ? "selected" : "" %>><%= classNum %></option>
            <% } %>
        </select>
    </div>

    <div class="col-auto">
        <label for="subCd" class="col-form-label">科目</label>
        <select id="subCd" name="f3" class="form-select">
            <option value="">--------</option>
            <% for (Subject sub : slist) { %>
                <option value="<%= sub.getCd() %>" <%= sub.getCd().equals(f3) ? "selected" : "" %>><%= sub.getName() %></option>
            <% } %>
        </select>
    </div>

    <div class="col-auto">
        <button type="submit" class="btn btn-secondary">科目検索</button>
    </div>
</div>


<!-- エラーメッセージ（科目検索） -->
<% if (request.getAttribute("subjectError") != null) { %>
    <div class="mt-3">
        <p class="text-danger"><%= request.getAttribute("subjectError") %></p>
    </div>
<% } %>

<!-- 水平線 -->
    <hr class="my-4">

<!-- 🔍 学生情報フォーム -->
<div class="row g-3 align-items-center">
    <div class="col-auto">
        <label class="col-form-label fw-bold">学生情報</label>
    </div>

    <div class="col-auto">
        <label for="studentNo" class="col-form-label">学生番号</label>
        <input type="text" id="studentNo" name="f4" class="form-control"
               placeholder="学生番号を入力してください" value="${param.f4}" required>
    </div>

    <div class="col-auto">
        <button type="submit" class="btn btn-secondary">学生検索</button>
    </div>
</div>
</form>


<%
boolean isSubjectSearch = (f1 != null && !f1.isEmpty()) &&
                          (f2 != null && !f2.isEmpty()) &&
                          (f3 != null && !f3.isEmpty());

boolean isStudentSearch = (f4 != null && !f4.isEmpty());
%>

<!-- 成績一覧（科目） -->
<% if (isSubjectSearch) { %>

    <% if (sublist != null && !sublist.isEmpty()) { %>

        <div>
        <p>科目：
        <%
            String subjectName = "";
            if (f3 != null && !f3.isEmpty()) {
                for (Subject sub : slist) {
                    if (sub.getCd().equals(f3)) {
                        subjectName = sub.getName();
                        break;
                    }
                }
            }
            out.print(subjectName);
        %>
        </p>
        </div>

        <table class="table table-bordered mt-3">
          <thead>
            <tr>
              <th>入学年度</th>
              <th>クラス</th>
              <th>学生番号</th>
              <th>氏名</th>
              <th>１回</th>
              <th>２回</th>
            </tr>
          </thead>
          <tbody>
            <% for (TestListSubject tlsub : sublist) { %>
              <tr>
                <td><%= tlsub.getEntYear() %></td>
                <td><%= tlsub.getClassNum() %></td>
                <td><%= tlsub.getStudentNo() %></td>
                <td><%= tlsub.getStudentName() %></td>
                <% for (int i = 1; i <= 2; i++) { %>
                  <% String point = tlsub.getPoint(i); %>
                  <td><%= (point != null) ? point : "-" %></td>
                <% } %>
              </tr>
            <% } %>
          </tbody>
        </table>

    <% } else { %>
        <div class="mt-3">
          <p class="text-danger">学生情報が存在しませんでした。</p>
        </div>
    <% } %>

<% } else if (isStudentSearch) { %>

    <% if (stulist != null && !stulist.isEmpty()) { %>

        <div>
          <p>氏名：<%= stu.getName() %> (<%= f4 %>)</p>
        </div>

        <table class="table table-bordered mt-3">
          <thead>
            <tr>
              <th>科目名</th>
              <th>科目コード</th>
              <th>回数</th>
              <th>点数</th>
            </tr>
          </thead>
          <tbody>
            <% for (TestListStudent tlstu : stulist) { %>
              <tr>
                <td><%= tlstu.getSubjectName() %></td>
                <td><%= tlstu.getSubjectCd() %></td>
                <td><%= tlstu.getNum() %></td>
                <td><%= tlstu.getPoint() %></td>
              </tr>
            <% } %>
          </tbody>
        </table>

    <% } else { %>
        <div class="mt-3">
          <p class="text-danger">成績情報が存在しませんでした。</p>
        </div>
    <% } %>

<% } %>

</c:param>
</c:import>
