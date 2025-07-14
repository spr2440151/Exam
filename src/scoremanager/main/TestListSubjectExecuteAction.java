package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        try {
            HttpSession session = req.getSession();
            SubjectDao sdao = new SubjectDao();
            ClassNumDao cdao = new ClassNumDao();
            TestListSubjectDao subldao = new TestListSubjectDao();

            Teacher teacher = (Teacher) session.getAttribute("user");
            School school = teacher.getSchool();

            // パラメータ取得
            String entyStr = req.getParameter("f1");
            String classNum = req.getParameter("f2");
            String subCd = req.getParameter("f3");

            // 入学年度リスト生成
            List<Integer> yearList = new ArrayList<>();
            int currentYear = LocalDate.now().getYear();
            for (int i = currentYear - 10; i <= currentYear + 10; i++) {
                yearList.add(i);
            }

            // 学校に紐づく科目・クラスリスト取得
            List<Subject> sList = sdao.filter(school);
            List<String> cList = cdao.filter(school);

            // 条件チェック
            if (entyStr == null || entyStr.isEmpty() ||
                classNum == null || classNum.isEmpty() ||
                subCd == null || subCd.isEmpty()) {

                // 未入力ならエラーメッセージをリクエストスコープに格納
                req.setAttribute("subjectError", "入学年度とクラスと科目を入力してください");

            } else {
                // 全て入力されていれば検索実行
                int enty = Integer.parseInt(entyStr);
                Subject sub = sdao.get(subCd, school);

                List<TestListSubject> sublist =  subldao.filter(enty, classNum, sub, school);

                session.setAttribute("enty", enty);
                session.setAttribute("classnum", classNum);
                session.setAttribute("subt", sub);
                session.setAttribute("school", school);
                session.setAttribute("subList", sublist);
            }

            // 共通でセットするリスト
            session.setAttribute("yearList", yearList);
            session.setAttribute("sList", sList);
            session.setAttribute("cList", cList);

            req.getRequestDispatcher("testListStudent.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
