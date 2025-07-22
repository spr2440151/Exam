package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import tool.Action;

public class ClassDeleteExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ClassNumDao dao = new ClassNumDao();
		HttpSession session=req.getSession();

		String num = req.getParameter("num");
    	School school = (School)session.getAttribute("school");
    	ClassNum cln = new ClassNum();
    	String url ="";

		cln.setClass_num(num);
		cln.setSchool(school);
		dao.delete(cln);

		//リダイレクト
		url = "class_delete_done.jsp";
		res.sendRedirect(url);


    }
}


