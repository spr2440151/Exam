package scoremanager.main;

import java.util.Collections;
import java.util.Comparator;
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
			List<Student> list = dao.filter(school);

			// 並び替え処理をここで実施！
			Collections.sort(list, new Comparator<Student>() {
				public int compare(Student s1, Student s2) {
					// ① 入学年度 昇順
					int cmp = Integer.compare(s1.getEntYear(), s2.getEntYear());
					if (cmp != 0) return cmp;

					// ② 学籍番号 昇順（no は文字列なので注意）
					cmp = s1.getNo().compareToIgnoreCase(s2.getNo());
					if (cmp != 0) return cmp;

					// ③ クラス番号 昇順
					return s1.getClassNum().compareToIgnoreCase(s2.getClassNum());
				}
			});


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