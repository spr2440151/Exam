package scoremanager.main;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            HttpSession session = req.getSession();
            SubjectDao sdao = new SubjectDao();
            ClassNumDao cdao = new ClassNumDao();
            StudentDao studao = new StudentDao();

            TestListStudentDao stuldao = new TestListStudentDao();
            Teacher teacher = (Teacher) session.getAttribute("user");
            School school = teacher.getSchool();

            // パラメータ取得
            String entyStr = req.getParameter("f1");
            String classNum = req.getParameter("f2");
            String subCd = req.getParameter("f3");
            String no = req.getParameter("f4");

            System.out.println("[DEBUG]入学:" + entyStr);
            System.out.println("[DEBUG]クラス:" + classNum);
            System.out.println("[DEBUG]科目:" + subCd);
            System.out.println("[DEBUG]学番:" + no);

            // 入学年度リスト生成
            List<Integer> yearList = new ArrayList<>();
            int currentYear = LocalDate.now().getYear();
            for (int i = currentYear - 10; i <= currentYear + 10; i++) {
                yearList.add(i);
            }

            // 学校に紐づく科目一覧取得
            List<Subject> sList = sdao.filter(school);

            // 学校に紐づくクラス一覧取得
            List<String> cList = cdao.filter(school);

            // 検索条件がある場合のみテスト一覧を取得
            if (no != null && !no.isEmpty()) {

            	System.out.println("if文チェック");
                Student stu = studao.get(no);

                List<TestListStudent> stulist =  stuldao.filter(stu);
                session.setAttribute("classnum", classNum);
                session.setAttribute("school", school);
                session.setAttribute("stuList", stulist);
            }

            System.out.println("[DEBUG]s:" + sList);
            System.out.println("[DEBUG]c:" + cList);
            session.setAttribute("yearList", yearList);
            session.setAttribute("sList", sList);
            session.setAttribute("cList", cList);

            req.getRequestDispatcher("testListStudent.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
