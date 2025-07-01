package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

	public Student get(String no ) throws Exception {

		// データベース接続取得

		Connection con = getConnection();

		// SQL文の準備

		PreparedStatement st = con.prepareStatement(

			"SELECT * FROM student WHERE no = ? ");

		// schoolがnullまたはcdがnullの場合は""（空文字）を代入l

		st.setString(1, no);

		// SQL実行

		ResultSet rs = st.executeQuery();

		// 検索結果を変数に追加

		Student stu = new Student();

		while (rs.next()) {

			stu.setNo(rs.getString("no"));

			stu.setName(rs.getString("name"));

			stu.setEntYear(rs.getInt("ent_year"));

			stu.setClassNum(rs.getString("Class_num"));


		}

		rs.close();

		st.close();

		con.close();

		return stu;

	}

	public List<Student> filter(School school) throws Exception {
		List<Student> list = new ArrayList<>();

		// データベース接続取得
		Connection con = getConnection();

		// SQL文の準備
		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM student WHERE school_cd LIKE ?");

		// schoolがnullまたはcdがnullの場合は""（空文字）を代入
		String cd = (school == null || school.getCd() == null) ? "" : school.getCd();
		st.setString(1, "%" + cd + "%");

		// SQL実行
		ResultSet rs = st.executeQuery();

		// 検索結果をリストに追加
		while (rs.next()) {
			Student stu = new Student();
			stu.setEntYear(rs.getInt("ent_year"));
			stu.setNo(rs.getString("no"));
			stu.setName(rs.getString("name"));
			stu.setClassNum(rs.getString("class_num"));
			stu.setAttend(rs.getBoolean("is_attend"));
			list.add(stu);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}
}

