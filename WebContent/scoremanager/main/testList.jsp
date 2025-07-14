<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Test, bean.Subject, bean.TestListSubject, java.util.List"%>
<%@page import="java.util.Set, java.util.LinkedHashSet"%>

<%
    List<Test> tlist = (List<Test>) session.getAttribute("tList");
    List<Subject> slist = (List<Subject>) session.getAttribute("sList");
    List<String> clist = (List<String>) session.getAttribute("cList");
    List<Integer> yearList = (List<Integer>) session.getAttribute("yearList");
    List<TestListSubject> sublist = (List<TestListSubject>) session.getAttribute("subList");

    // 検索条件の値を保持
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

  <!-- 🔍 科目情報 + 学生情報 統合フォーム -->
  <form method="get" class="border p-3 mb-3 bg-light rounded">

    <!-- 科目情報 -->
    <div class="row g-3 align-items-center mb-3">
      <div class="col-auto">
        <label class="col-form-label fw-bold">科目情報</label>
      </div>

      <!-- 入学年度 -->
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

      <!-- クラス -->
      <div class="col-auto">
        <label for="classNum" class="col-form-label">クラス</label>
        <select id="classNum" name="f2" class="form-select">
          <option value="">--------</option>
          <% for (String classNum : clist) { %>
            <option value="<%= classNum %>" <%= classNum.equals(f2) ? "selected" : "" %>><%= classNum %></option>
          <% } %>
        </select>
      </div>

      <!-- 科目 -->
      <div class="col-auto">
        <label for="subCd" class="col-form-label">科目</label>
        <select id="subCd" name="f3" class="form-select">
          <option value="">--------</option>
          <% for (Subject sub : slist) { %>
            <option value="<%= sub.getCd() %>" <%= sub.getCd().equals(f3) ? "selected" : "" %>><%= sub.getName() %></option>
          <% } %>
        </select>
      </div>

      <!-- 科目検索ボタン -->
      <div class="col-auto">
        <button type="submit" formaction="TestListSubjectExecute.action" class="btn btn-secondary">検索</button>
      </div>
    </div>

    <!-- 水平線 -->
    <hr class="my-4">

    <!-- 学生情報 -->
    <div class="row g-3 align-items-center">
      <div class="col-auto">
        <label class="col-form-label fw-bold">学生情報</label>
      </div>

      <!-- 学生番号 -->
      <div class="col-auto">
        <label for="studentNo" class="col-form-label">学生番号</label>
        <input type="text" id="studentNo" name="f4" class="form-control" placeholder="学生番号を入力してください" value="${param.f4}">
      </div>

      <!-- 学生検索ボタン -->
      <div class="col-auto">
        <button type="submit" formaction="TestListStudentExecute.action" class="btn btn-secondary">検索</button>
      </div>
    </div>

  </form>

  <!-- 注意メッセージ -->
  <label class="text-info">
    科目情報または学生情報を入力して検索ボタンをクリックしてください
  </label>

  <!-- 検索結果表示 -->
  <% if (sublist != null && !sublist.isEmpty()) { %>
    <div>
      <p>科目：科目名></p>
    </div>

    <!-- 成績一覧テーブル -->
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
  <% } %>

  </c:param>
</c:import>
