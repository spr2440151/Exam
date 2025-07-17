package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		String url = "";
		String cd = "";
		String name = "";
//		TeacherDao teacherDao = new TeacherDao();
//		Teacher teacher = null;

		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");// 科目コード
		name = req.getParameter("name");//科目名

		//DBからデータ取得 3
		HttpSession session=req.getSession();
		SubjectDao dao = new SubjectDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		Subject sub = dao.get(cd, school);
//		String school_cd = user.getCd();

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//フォワード 7
		//条件で手順4~7の内容が分岐
		if (cd.length() != 3) {// 認証失敗の場合
			List<String> errors = new ArrayList<>();
			errors.add("科目コードは3文字で入力してください");
			req.setAttribute("errors", errors);

			req.setAttribute("cd", cd);
			req.setAttribute("name", name);

			//フォワード
			url = "subject_create.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		} else if (cd.equals(sub.getCd())) {// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("科目コードが重複しています");
			req.setAttribute("errors", errors);

			req.setAttribute("cd", cd);
			req.setAttribute("name", name);

			//フォワード
			url = "subject_create.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		} else {
			sub.setCd(cd);
			sub.setName(name);
			sub.setSchool(school);
			dao.save(sub);
			//リダイレクト
			url = "subject_create_done.jsp";
			res.sendRedirect(url);
		}

	}

}
