package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		SubjectDao dao = new SubjectDao();

		String cd = req.getParameter("cd");
    	String name = req.getParameter("name");
    	School school = (School)req.getAttribute("school");
    	Subject sub = new Subject();
    	String url ="";

		sub.setCd(cd);
		sub.setName(name);
		sub.setSchool(school);
		dao.delete(sub);

		//リダイレクト
		url = "subjectDeleteDone.jsp";
		res.sendRedirect(url);


    }
}


