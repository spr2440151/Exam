package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

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

    	ClassNum checkCls = new ClassNum();
    	checkCls = dao.get(clsnum, school);

    	System.out.println(checkCls.getClass_num());

    	if (clsnum.equals(checkCls.getClass_num())) {
    	    List<String> errors = new ArrayList<>();
    	    errors.add("クラス番号が重複しています");
    	    req.setAttribute("errors", errors);
    	    req.setAttribute("num", clsnum);
    	    req.getRequestDispatcher("class_update.jsp").forward(req, res);
    	} else {
    	    cls.setClass_num(clsnum);
    	    cls.setSchool(school);
    	    dao.save(cls, num);
    	    res.sendRedirect("class_update_done.jsp");
    	}

		}
    }


