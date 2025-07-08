package scoremanager.main;

import java.time.LocalDate;
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

public class StudentListAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			HttpSession session = req.getSession();
			StudentDao dao = new StudentDao();
			Teacher teacher = (Teacher) session.getAttribute("user");
			School school = teacher.getSchool();

			// パラメータ取得
			String enty = req.getParameter("entYear");
			String classNum = req.getParameter("classNum");
			boolean isAttend = "true".equals(req.getParameter("attend"));

			List<Student> list;
			if (enty != null && !enty.isEmpty()) {
				int entYear = Integer.parseInt(enty);
				if (classNum != null && !classNum.isEmpty()) {
					// 入学年度 + クラス + 在学中（任意）
					list = dao.filter(school, entYear, classNum, isAttend);
				} else {
					// 入学年度のみ + 在学中（任意）
					list = dao.filter(school, entYear, isAttend);
				}
			} else {
				// 入学年度なし → 在学中（任意）のみ
				list = dao.filter(school, isAttend);
			}

			List<Integer> yearList = new ArrayList<>();
			LocalDate localDate = LocalDate.now();
			int currentYear = localDate.getYear();
			for (int i = currentYear - 10; i <= currentYear; i++) {
			    yearList.add(i);
			}

			session.setAttribute("yearList", yearList);
			session.setAttribute("list", list);
			req.getRequestDispatcher("studentList.jsp").forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

