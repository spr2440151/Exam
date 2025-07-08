package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	// 学校コード検索用のベースSQL（必要なカラムにエイリアスを付けておく）
	private final String baseSql =
		"SELECT " +
		" test.no AS test_no, test.point, test.class_num AS test_class_num, test.subject_cd, " +
		" student.no AS student_no, student.name, student.ent_year, student.class_num AS student_class_num, student.is_attend " +
		"FROM test " +
		"INNER JOIN student ON test.student_no = student.no " +
		"WHERE student.school_cd = ?";


	// 条件に一致するテスト情報一覧を取得する
	public List<Test> filter(int entYear, String classNum, Subject subject, int no, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection con = getConnection();

		String sql = baseSql
			+ " AND student.ent_year = ?"
			+ " AND test.class_num = ?"
			+ " AND test.subject_cd = ?"
			+ " AND test.no = ?"
			+ " ORDER BY test.student_no";

		PreparedStatement st = con.prepareStatement(sql);

		st.setString(1, school.getCd());          // student.school_cd
		st.setInt(2, entYear);                    // student.ent_year
		st.setString(3, classNum);                // test.class_num
		st.setString(4, subject.getCd());         // test.subject_cd
		st.setInt(5, no);                     // test.no

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Test test = new Test();

			// Studentの設定
			Student student = new Student();
			student.setNo(rs.getString("student_no"));
			student.setName(rs.getString("name"));
			student.setEntYear(rs.getInt("ent_year"));
			student.setClassNum(rs.getString("student_class_num"));
			student.setAttend(rs.getBoolean("is_attend"));
			student.setSchool(school);
			test.setStudent(student);

			// Testの設定
			test.setNo(rs.getInt("test_no"));
			test.setPoint(rs.getInt("point"));
			test.setClassNum(rs.getString("test_class_num"));
			test.setSubject(subject);
			test.setSchool(school);

			list.add(test);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}
}
