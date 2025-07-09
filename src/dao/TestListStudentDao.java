package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    // 成績一覧取得用のSQL文
	protected String baseSql =
		    "SELECT subject.name AS subject_name, subject.cd AS subject_cd, test.no, test.point " +
		    "FROM test " +
		    "JOIN subject ON test.subject_cd = subject.cd " +
		    "WHERE test.student_no = ? " +
		    "ORDER BY subject.cd, test.no";

    /**
     * 特定の学生に対応するテスト成績を取得する
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list;

        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(baseSql);
        ) {
            st.setString(1, student.getNo());
            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs);
            }
        }

        return list;
    }

    /**
     * ResultSet から TestListStudent リストを作成（内部用）
     */
    private List<TestListStudent> postFilter(ResultSet rs) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rs.next()) {
            TestListStudent t = new TestListStudent();
            t.setSubjectName(rs.getString("subject_name"));
            t.setSubjectCd(rs.getString("subject_cd"));
            t.setNo(rs.getInt("no"));  // ← 修正ここ
            t.setPoint(rs.getInt("point"));
            list.add(t);
        }
        return list;
    }

    }
