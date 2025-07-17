<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>得点管理システム - 成績管理</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid mt-4">

  <!-- 見出し -->
  <div class="bg-primary bg-opacity-10 p-3 mb-4 rounded">
    <h1 class="h3">得点管理システム</h1>
  </div>

  <!-- 成績管理タイトル -->
  <h2 class="h4 mb-3">成績管理</h2>

  <!-- 検索フォーム -->
  <form action="TestRegist.action" method="get" class="bg-light p-3 rounded border">
    <div class="row g-3 align-items-end">

      <!-- 入学年度 -->
      <div class="col-md-2">
        <label for="entYear" class="form-label">入学年度</label>
        <select id="entYear" name="entYear" class="form-select">
          <option value="">--------</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
          <option value="2023">2023</option>
        </select>
      </div>

      <!-- クラス -->
      <div class="col-md-2">
        <label for="classNum" class="form-label">クラス</label>
        <select id="classNum" name="classNum" class="form-select">
          <option value="">--------</option>
          <option value="201">201</option>
          <option value="202">202</option>
        </select>
      </div>

      <!-- 科目 -->
      <div class="col-md-3">
        <label for="subject" class="form-label">科目</label>
        <select id="subject" name="subject" class="form-select">
          <option value="">--------</option>
          <option value="math">数学</option>
          <option value="english">英語</option>
          <option value="programming">プログラミング</option>
        </select>
      </div>

      <!-- 回数 -->
      <div class="col-md-2">
        <label for="times" class="form-label">回数</label>
        <select id="times" name="times" class="form-select">
          <option value="">--------</option>
          <option value="1">第1回</option>
          <option value="2">第2回</option>
          <option value="3">第3回</option>
        </select>
      </div>

      <!-- 検索ボタン -->
      <div class="col-md-1">
        <button type="submit" class="btn btn-secondary w-100">検索</button>
      </div>

    </div>
  </form>

</div>

</body>
</html>
