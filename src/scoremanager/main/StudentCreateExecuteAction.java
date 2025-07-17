package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        String url = "";
        int entYear = 0;
        String no = "";
        String name = "";
        String classNum = "";

        // リクエストパラメータ―の取得
        try {
            entYear = Integer.parseInt(req.getParameter("entYear")); // 入学年度
        } catch (NumberFormatException e) {
            entYear = 0;
        }
        no = req.getParameter("no"); // 学生番号
        name = req.getParameter("name"); // 学生名
        classNum = req.getParameter("classNum"); // クラス

        // DBからデータ取得
        HttpSession session = req.getSession();
        StudentDao dao = new StudentDao();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        Student stu = dao.get(no);

        List<String> errors = new ArrayList<>();

     // 入学年度未選択チェック
        if (entYear == 0) {
            errors.add("入学年度を選択してください");
        }

        // 学生番号重複チェック
        if (stu != null && stu.getNo() != null) {
            // 個別エラーのみセット
            req.setAttribute("duplicateError", "学生番号が重複しています");
        }

        // エラーがある場合
        if (!errors.isEmpty() || req.getAttribute("duplicateError") != null) {
            req.setAttribute("errors", errors);

            // 入力値をリクエストスコープに保存
            List yearList = (List) session.getAttribute("yearList");
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("classNum", classNum);
            req.setAttribute("entYear", entYear); // 入学年度も保存
            req.setAttribute("yearList", yearList);

            // フォワード
            url = "student_create.jsp";
            req.getRequestDispatcher(url).forward(req, res);
            return;
        }

        // 新規作成処理
        if (stu == null) {
            stu = new Student();
        }

        stu.setNo(no);
        stu.setName(name);
        stu.setEntYear(entYear);
        stu.setClassNum(classNum);
        stu.setAttend(true);
        stu.setSchool(school);
        dao.save(stu);

        // リダイレクト
        url = "student_createDone.jsp";
        res.sendRedirect(url);
    }

}
