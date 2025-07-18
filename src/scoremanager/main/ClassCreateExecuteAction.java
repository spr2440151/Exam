package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータの取得
        String cd = req.getParameter("cd"); // クラス番号

        // セッションから teacher を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        List<String> errors = new ArrayList<>();

        // teacher が null の場合はログイン切れとしてエラー
        if (teacher == null) {
            errors.add("ログイン情報が取得できません。再度ログインしてください。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        School school = teacher.getSchool();

        // DB からデータ取得
        ClassNumDao dao = new ClassNumDao();
        ClassNum classNum = dao.get(cd, school);

        // バリデーション
        if (cd == null || cd.length() != 3) {
            errors.add("クラス番号3文字で入力してください");
        } else if (classNum != null && classNum.getClass_num() != null) {
            errors.add("クラス番号が重複しています");
        }

        if (!errors.isEmpty()) {
            // エラーがある場合
            req.setAttribute("errors", errors);
            req.setAttribute("cd", cd);

            // フォワード
            req.getRequestDispatcher("class_create.jsp").forward(req, res);
            return;
        }

        // クラス新規登録処理
        ClassNum newClassNum = new ClassNum();
        newClassNum.setClass_num(cd);
        newClassNum.setSchool(school);

        dao.save(newClassNum, cd);

        // リダイレクト
        res.sendRedirect("class_create_done.jsp");
    }

}
