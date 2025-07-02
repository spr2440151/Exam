package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		try {
			//ローカル変数の宣言 1
			String url = "";
			HttpSession session=req.getSession();
			StudentDao dao = new StudentDao();
			Teacher teacher = (Teacher) session.getAttribute("user");
			School school = teacher.getSchool();
			boolean isAttend = false;
			List<Student> list = dao.filter(school, isAttend);

//			Collections.sort(list, new Comparator<Student>() {
//				public int compare(Student s1, Student s2) {
//					return s1.getCd().compareToIgnoreCase(s2.getCd());
//				}
//			});

			session.setAttribute("list", list);

			url = "studentList.jsp";

			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

