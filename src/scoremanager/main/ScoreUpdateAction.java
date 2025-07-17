package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class ScoreUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");

        // セッションからユーザー情報など取得できる場合は取る（今回は省略可）
        HttpSession session = req.getSession();

        // パラメータを取得
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subject = req.getParameter("subject");
        String times = req.getParameter("times");

        // ここでDAOを使って検索処理（例）
        // List<Result> results = dao.search(entYear, classNum, subject, times);

        // 検索結果をリクエストにセット
        // req.setAttribute("results", results);

        // フォワード先のJSPパス
        String url = "/scoremanager/main/result.jsp";  // 絶対パスで指定

        // フォワード
        req.getRequestDispatcher(url).forward(req, res);
    }
}
