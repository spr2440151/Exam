package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {


	private final String baseSql =
		"SELECT " +
		" test.subject_cd, test.no AS test_no, test.point, " +
		" subject.name AS subject_name " +
		"FROM test " +
		"INNER JOIN student ON test.student_no = student.no " +
		"INNER JOIN subject ON test.subject_cd = subject.cd AND test.school_cd = subject.school_cd " +
		"WHERE student.no = ? " +
		"ORDER BY test.subject_cd, test.no";


	private List<TestListStudent> postfilter(ResultSet rSet) throws Exception {
		List<TestListStudent> list = new ArrayList<>();

		while (rSet.next()) {
			TestListStudent tls = new TestListStudent();

			tls.setSubjectCd(rSet.getString("subject_cd"));
			tls.setSubjectName(rSet.getString("subject_name"));
			tls.setNum(rSet.getInt("test_no"));
			tls.setPoint(rSet.getInt("point"));

			list.add(tls);
		}
		return list;
	}


	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(baseSql);
		st.setString(1, student.getNo());

		ResultSet rs = st.executeQuery();
		list = postfilter(rs);

		rs.close();
		st.close();
		con.close();

		return list;
	}
}
