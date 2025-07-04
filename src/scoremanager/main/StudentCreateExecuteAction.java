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
        String studentNo = "";
        String studentName = "";
        String classNum = "";

        // リクエストパラメータ―の取得
        try {
            entYear = Integer.parseInt(req.getParameter("entYear")); // 入学年度
        } catch (NumberFormatException e) {
            entYear = 0;
        }
        studentNo = req.getParameter("studentNo"); // 学生番号
        studentName = req.getParameter("studentName"); // 学生名
        classNum = req.getParameter("classNum"); // クラス

        // DBからデータ取得
        HttpSession session = req.getSession();
        StudentDao dao = new StudentDao();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        Student stu = dao.get(studentNo);

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
            req.setAttribute("studentNo", studentNo);
            req.setAttribute("studentName", studentName);
            req.setAttribute("classNum", classNum);
            req.setAttribute("entYear", entYear); // 入学年度も保存
            req.setAttribute("yearList", yearList);

            // フォワード
            url = "studentCreate.jsp";
            req.getRequestDispatcher(url).forward(req, res);
            return;
        }

        // 新規作成処理
        if (stu == null) {
            stu = new Student();
        }

        stu.setNo(studentNo);
        stu.setName(studentName);
        stu.setEntYear(entYear);
        stu.setClassNum(classNum);
        stu.setAttend(true);
        stu.setSchool(school);
        dao.save(stu);

        // リダイレクト
        url = "studentCreateDone.jsp";
        res.sendRedirect(url);
    }

}
