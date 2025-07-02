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

	public boolean save(Student student) throws Exception {
	    boolean result = false;

	    Connection con = getConnection();

	    // cdチェック
	    PreparedStatement checkSt = con.prepareStatement(
	    		"SELECT COUNT(*) FROM student WHERE no = ?");
	    checkSt.setString(1, student.getNo());
	    ResultSet rs = checkSt.executeQuery();

	    boolean exists = false;
	    if (rs.next()) {
	        exists = rs.getInt(1) > 0;

	    }
	    rs.close();
	    checkSt.close();

	    PreparedStatement st;

	    if (exists) {
	        // 更新処理
	        st = con.prepareStatement("UPDATE student SET name = ?, class_num = ?, is_attend = ? WHERE no = ?");
	        st.setString(1, student.getName());
	        st.setString(2, student.getClassNum());
	        st.setBoolean(3, student.isAttend());
	        st.setString(4, student.getNo());
	    } else {
	        // 挿入処理
	        st = con.prepareStatement(
	        		"INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)");
	        System.out.println("挿入");
	        School school = student.getSchool();
	        String school_cd = school.getCd();
	        st.setString(1, student.getNo());
	        st.setString(2, student.getName());
	        st.setInt(3, student.getEntYear());
	        st.setString(4, student.getClassNum());
	        st.setBoolean(5, student.isAttend());
	        st.setString(6, school_cd);
	    }

	    int rows = st.executeUpdate();
	    result = rows > 0;

	    st.close();
	    con.close();

	    return result;
	}
}

