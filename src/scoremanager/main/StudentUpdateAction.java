package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
		StudentDao dao = new StudentDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		String url = "";
		String no = req.getParameter("no");
		try {
			Student  stu = dao.get(no);
			stu.setSchool(school);
			url = "studentUpdate.jsp";
			req.setAttribute("student", stu);
			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}