<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Student, java.util.List" %>
<% List<Student> list = (List<Student>) session.getAttribute("list"); %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>

  <!-- 🔍 検索フォーム -->
  <form action="StudentList.action" method="get" class="border p-3 mb-3 bg-light rounded">
    <div class="row g-3 align-items-center">
      <div class="col-auto">
        <label for="entYear" class="col-form-label">入学年度</label>
        <select id="entYear" name="entYear" class="form-select">
          <option value="">--------</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
          <option value="2023">2023</option>
        </select>
      </div>
      <div class="col-auto">
        <label for="classNum" class="col-form-label">クラス</label>
        <select id="classNum" name="classNum" class="form-select">
          <option value="">--------</option>
          <option value="201">201</option>
          <option value="202">202</option>
        </select>
      </div>
      <div class="col-auto form-check">
        <input class="form-check-input" type="checkbox" id="attend" name="attend" value="true">
        <label class="form-check-label" for="attend">在学中</label>
      </div>
      <div class="col-auto">
        <button type="submit" class="btn btn-secondary">絞込み</button>
      </div>
    </div>
  </form>

  <!-- 新規登録ボタン -->
  <div style="margin-top: 10px; margin-left: auto; width: fit-content; margin-right: 55px;">
    <a href="StudentCreate.action" class="btn btn-sm btn-primary">新規登録</a>
  </div>

  <!-- 学生一覧テーブル -->
  <table class="table mt-3 border-top border-bottom">
    <thead>
      <tr>
        <th>入学年度</th>
        <th>学籍番号</th>
        <th>氏名</th>
        <th>クラス</th>
        <th>在学中</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <% for (Student stu : list) { %>
        <tr>
          <td><%= stu.getEntYear() %></td>
          <td><%= stu.getNo() %></td>
          <td><%= stu.getName() %></td>
          <td><%= stu.getClassNum() %></td>
          <td><%= stu.isAttend() ? "○" : "×" %></td>
          <td><a href="StudentUpdate.action?id=<%=stu.getNo()%>" class="btn btn-sm btn-link">変更</a></td>
        </tr>
      <% } %>
    </tbody>
  </table>

  </c:param>
</c:import>
