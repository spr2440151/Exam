<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.Test, bean.Subject, java.util.List" %>
<%@page import="java.util.Set, java.util.LinkedHashSet" %>

<%
    List<Test> tlist = (List<Test>) session.getAttribute("tList");
    List<Subject> slist = (List<Subject>) session.getAttribute("sList");
    List<String> clist = (List<String>) session.getAttribute("cList");
    List<Integer> yearList = (List<Integer>) session.getAttribute("yearList");

    // 検索条件の値を保持（f1〜f4）
    String f1 = request.getParameter("f1");
    String f2 = request.getParameter("f2");
    String f3 = request.getParameter("f3");
    String f4 = request.getParameter("f4");

%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">

  <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

  <!-- 🔍 検索フォーム -->
  <form action="TestRegist.action" method="get" class="border p-3 mb-3 bg-light rounded">
    <div class="row g-3 align-items-center">
      <!-- 入学年度 -->
      <div class="col-2">
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
      <div class="col-2">
        <label for="classNum" class="col-form-label">クラス</label>
        <select id="classNum" name="f2" class="form-select">
          <option value="">--------</option>
          <% for (String classNum : clist) { %>
            <option value="<%= classNum %>" <%= classNum.equals(f2) ? "selected" : "" %>><%= classNum %></option>
          <% } %>
        </select>
      </div>

      <!-- 科目 -->
      <div class="col-4">
        <label for="subCd" class="col-form-label">科目</label>
        <select id="subCd" name="f3" class="form-select">
          <option value="">--------</option>
          <% for (Subject sub : slist) { %>
            <option value="<%= sub.getCd() %>" <%= sub.getCd().equals(f3) ? "selected" : "" %>><%= sub.getName() %></option>
          <% } %>
        </select>
      </div>

      <!-- 回数 -->
      <div class="col-2">
        <label for="num" class="col-form-label">回数</label>
        <select id="num" name="f4" class="form-select">
          <option value="">--------</option>
		  <option value="1" <%= "1".equals(f4) ? "selected" : "" %>>1</option>
		  <option value="2" <%= "2".equals(f4) ? "selected" : "" %>>2</option>
        </select>
      </div>

      <!-- ボタン -->
      <div class="col-2">
        <button type="submit" class="btn btn-secondary">絞込み</button>
      </div>
    </div>
  </form>

  <!-- 検索結果表示 -->
  <% if (tlist != null && !tlist.isEmpty()) { %>

    <div>
      <p>科目：<%= tlist.get(0).getSubject().getName() %>（<%= tlist.get(0).getNo() %> 回）</p>
    </div>

    <!-- 成績一覧テーブル -->
    <form action="TestRegistExecute.action" method="post" onsubmit="return validatePoints();">
      <table class="table table-bordered mt-3">
        <thead>
          <tr>
            <th>入学年度</th>
            <th>クラス</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>点数</th>
          </tr>
        </thead>
        <tbody>
          <%
            int index = 0;
            for (Test test : tlist) {
          %>
          <tr>
            <td><%= test.getStudent().getEntYear() %></td>
            <td><%= test.getClassNum() %></td>
            <td><%= test.getStudent().getNo() %></td>
            <td><%= test.getStudent().getName() %></td>
            <td>
              <input type="hidden" name="regist" value="<%= test.getStudent().getNo() %>" />
              <input type="hidden" name="count" value="<%= f4 %>" />
              <input type="hidden" name="subject" value="<%= f3 %>" />
              <input type="hidden" name="studentNo" value="<%= test.getStudent().getNo() %>"/>

              <input type="text" name="point" id="point_<%= index %>" value="<%= test.getPoint() %>"
               class="form-control form-control-sm point-input" />

              <div id="error_<%= index %>" class="text-danger mt-1" style="display:none;">
                0～100の範囲で入力してください
              </div>
            </td>
          </tr>
          <%
              index++;
            } // for end
          %>
        </tbody>
      </table>

      <button type="submit" class="btn btn-primary">登録して終了</button>
    </form>

<% } %>



<script>
// バリデーション関数
function validatePoints() {
    const points = document.getElementsByClassName('point-input');
    let isValid = true;

    for (let i = 0; i < points.length; i++) {
        const input = points[i];
        const errorDiv = document.getElementById('error_' + i);
        const value = input.value.trim();

        // 初期化
        errorDiv.style.display = 'none';

        // チェック
        if (value === '' || isNaN(value) || value < 0 || value > 100) {
            errorDiv.style.display = 'block';
            isValid = false;
        }
    }

    return isValid;
}
</script>

  </c:param>
</c:import>
