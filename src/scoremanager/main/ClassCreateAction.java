package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ClassCreateAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		try {
			//ローカル変数の宣言 1
			String url = "";

			url = "class_create.jsp";

			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

