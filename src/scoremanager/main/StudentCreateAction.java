package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentCreateAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		try {
			//ローカル変数の宣言 1
			String url = "";

			url = "student_create.jsp";

			List<Integer> yearList = new ArrayList<>();
			LocalDate localDate = LocalDate.now();
			int currentYear = localDate.getYear();
			for (int i = currentYear - 10; i <= currentYear + 10; i++) {
			    yearList.add(i);
			}
			req.setAttribute("yearList", yearList);


			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}

