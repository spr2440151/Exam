package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ClassNumDao dao = new ClassNumDao();
		HttpSession session=req.getSession();

		String clsnum = req.getParameter("classNum");
    	School school = (School)session.getAttribute("school");
    	ClassNum cls = new ClassNum();
    	String num = (String)session.getAttribute("num");
    	String url ="";
			cls.setClass_num(clsnum);
			cls.setSchool(school);
			System.out.println("後" + clsnum);
			System.out.println("前" + num);
			dao.save(cls, num);
			//リダイレクト
			url = "class_update_done.jsp";
			res.sendRedirect(url);
		}
    }


