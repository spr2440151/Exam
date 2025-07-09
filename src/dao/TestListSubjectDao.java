package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    protected String baseSql =
        "SELECT s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, " +
        "t.no AS no, t.point AS point " +
        "FROM student s " +
        "LEFT JOIN test t ON s.no = t.student_no AND t.subject_cd = ? " +
        "WHERE s.ent_year = ? AND s.class_num = ? AND s.school_cd = ? " +
        "ORDER BY s.no, t.no";

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();

        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(baseSql);
        ) {
            st.setString(1, subject.getCd());
            st.setInt(2, entYear);
            st.setString(3, classNum);
            st.setString(4, school.getCd());

            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs);
            }
        }

        return list;
    }

    private List<TestListSubject> postFilter(ResultSet rs) throws Exception {
        Map<String, TestListSubject> map = new LinkedHashMap<>();

        while (rs.next()) {
            String studentNo = rs.getString("student_no");
            TestListSubject testList = map.get(studentNo);

            if (testList == null) {
                testList = new TestListSubject();
                testList.setEntYear(rs.getInt("ent_year"));
                testList.setClassNum(rs.getString("class_num"));
                testList.setStudentNo(studentNo);
                testList.setStudentName(rs.getString("student_name"));
                map.put(studentNo, testList);
            }

            int no = rs.getInt("no");
            int point = rs.getInt("point");

            if (!rs.wasNull()) {
                testList.putPoint(no, point);
            }
        }

        return new ArrayList<>(map.values());
    }
}
