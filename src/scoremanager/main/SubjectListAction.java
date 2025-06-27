package scoremanager.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		try {
			//ローカル変数の宣言 1
			String url = "";
			HttpSession session=req.getSession();
			SubjectDao dao = new SubjectDao();
			Teacher teacher = (Teacher) session.getAttribute("user");
			School school = teacher.getSchool();
			List<Subject> list = dao.filter(school);

			Collections.sort(list, new Comparator<Subject>() {
				public int compare(Subject s1, Subject s2) {
					return s1.getCd().compareToIgnoreCase(s2.getCd());
				}
			});

			session.setAttribute("list", list);

			url = "subjectList.jsp";

			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

