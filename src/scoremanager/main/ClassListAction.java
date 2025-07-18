package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassListAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		try {
			//ローカル変数の宣言 1
			String url = "";
			HttpSession session=req.getSession();
			ClassNumDao dao = new ClassNumDao();
			Teacher teacher = (Teacher) session.getAttribute("user");
			School school = teacher.getSchool();
			List<String> list = dao.filter(school);

			session.setAttribute("list", list);

			url = "class_list.jsp";

			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

