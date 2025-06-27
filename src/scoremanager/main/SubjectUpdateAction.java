package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
		SubjectDao dao = new SubjectDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		String url = "";
		String cd = req.getParameter("cd");
		try {
			Subject sub = dao.get(cd, school);
			sub.setSchool(school);
			url = "subjectUpdate.jsp";
			req.setAttribute("subject", sub);
			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

//req.getRequestDispatcher("/scoremanager/main/subjectUpdate.jsp").forward(req, res);

