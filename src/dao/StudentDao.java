package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Student;

public class StudentDao {

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

			stu.setNo(rs.getString("nd"));

			stu.setName(rs.getString("name"));

			stu.setEntYear(rs.getInt("ent_yaer"));

			stu.setClassNum(rs.getString("Class_num"));


		}

		rs.close();

		st.close();

		con.close();

		return stu;

	}

	private Connection getConnection() {

		// TODO 自動生成されたメソッド・スタブ

		return null;

	}

}

