package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession();
		SubjectDao subdao = new SubjectDao();
		StudentDao studao = new StudentDao();
		TestDao tdao = new TestDao();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();
		String url ="";
		List<Test> list = null;

		// パラメータ取得
		String[] subCdArray = req.getParameterValues("subject");
		String[] numStrArray = req.getParameterValues("count");
		String[] stuNoStrArray = req.getParameterValues("regist");
		String[] pointStrArray = req.getParameterValues("point");
		System.out.println("[DEBUG]科目:"+subCdArray);
		System.out.println("[DEBUG]回数:"+numStrArray);
		System.out.println("[DEBUG]学番:"+stuNoStrArray);
		System.out.println("[DEBUG]点数:"+pointStrArray);

		for (int i = 0; 1 < subCdArray.length; i++) {
			String subCd = subCdArray[i];
			Subject sub = subdao.get(subCd, school);

			String stuNo =  stuNoStrArray[i];
			Student stu = studao.get(stuNo);

			String numStr = numStrArray[i];
			int no = Integer.parseInt(numStr);

			String pointStr = pointStrArray[i];
			if (pointStr == "") {
				int point = null;
			} else {
				int point = Integer.parseInt(pointStr);
			}

			String classNum = stu.getClassNum();

			Test test = new Test();
			test.setStudent(stu);
			test.setSubject(sub);
			test.setClassNum(classNum);
			test.setNo(no);
			test.setPoint(point);
			test.setSchool(school);

			list.add(test);
		}

		tdao.save(list);
		//リダイレクト
		url = "testRegistDone.jsp";
		res.sendRedirect(url);

    }
}