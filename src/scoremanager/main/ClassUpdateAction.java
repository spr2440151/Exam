package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
		ClassNumDao dao = new ClassNumDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		String url = "";
		String clnum = req.getParameter("classNum");
		System.out.println(clnum);
		try {
			url = "class_update.jsp";
			session.setAttribute("school", school);
			session.setAttribute("num", clnum);
			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

//req.getRequestDispatcher("/scoremanager/main/subjectUpdate.jsp").forward(req, res);

