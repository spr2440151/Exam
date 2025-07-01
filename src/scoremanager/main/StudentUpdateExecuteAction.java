package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		StudentDao dao = new StudentDao();

		String cl = req.getParameter("class");
    	String name = req.getParameter("name");
    	School school = (School)req.getAttribute("school");
    	Student stu = new Student();
    	String url ="";

    	stu.setClassNum(cl);
    	stu.setName(name);

		if (!(cl.equals(stu.getClassNum()))) {// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<>();
			errors.add("科目が存在していません");
			req.setAttribute("errors", errors);

			req.setAttribute("class",cl );
			req.setAttribute("name", name);

			//フォワード
			url = "StudentUpdate.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		} else {
			stu.setClassNum(cl);
			stu.setName(name);
			stu.setSchool(school);
			dao.save(stu);
			//リダイレクト
			url = "StudentUpdateDone.jsp";
			res.sendRedirect(url);
		}
    }
}

