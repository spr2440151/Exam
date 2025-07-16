package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		SubjectDao dao = new SubjectDao();

		String cd = req.getParameter("cd");
    	String name = req.getParameter("name");
    	School school = (School)req.getAttribute("school");
    	Subject sub = new Subject();
    	String url ="";

    	sub.setCd(cd);
    	sub.setName(name);

		if (!(cd.equals(sub.getCd()))) {// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("科目が存在していません");
			req.setAttribute("errors", errors);

			req.setAttribute("cd", cd);
			req.setAttribute("name", name);

			//フォワード
			url = "subjectUpdate.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		} else {
			sub.setCd(cd);
			sub.setName(name);
			dao.save(sub);
			//リダイレクト
			url = "subjectUpdateDone.jsp";
			res.sendRedirect(url);
		}
    }
}

