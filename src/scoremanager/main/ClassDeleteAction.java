package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import tool.Action;

public class ClassDeleteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		String url = "";
		String cln = req.getParameter("classNum");
		System.out.println(cln);
		System.out.println(school);
		try {
			url = "class_delete.jsp";
			req.setAttribute("num", cln);
			req.setAttribute("school", school);
			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
