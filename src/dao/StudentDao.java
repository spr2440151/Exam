package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

	// ベースSQL（学校コード検索用）
	private final String baseSql =
			"SELECT * FROM student WHERE school_cd LIKE ?";

	// 学生情報をResultSetから取得してリストに追加する共通メソッド
	private List<Student> postfilter(ResultSet rs, School school) throws Exception {
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			Student stu = new Student();
			stu.setEntYear(rs.getInt("ent_year"));
			stu.setNo(rs.getString("no"));
			stu.setName(rs.getString("name"));
			stu.setClassNum(rs.getString("class_num"));
			stu.setAttend(rs.getBoolean("is_attend"));
			stu.setSchool(school);
			list.add(stu);
		}
		return list;
	}

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

	public List<Student> filter(School school, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();

		String sql = "";
		if (isAttend) {
			sql = baseSql + " AND is_attend = ?";
		} else {
			sql = baseSql;
		}
		sql += " ORDER BY ent_year, class_num, no, is_attend";

		PreparedStatement st = con.prepareStatement(sql);
		String cd = (school == null || school.getCd() == null) ? "" : school.getCd();
		st.setString(1, "%" + cd + "%");

		if (isAttend) {
			st.setBoolean(2, true);
		}

		ResultSet rs = st.executeQuery();
		list = postfilter(rs, school);

		rs.close();
		st.close();
		con.close();

		return list;
	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();

		String sql = baseSql + " AND ent_year = ?";
		if (isAttend) {
			sql += " AND is_attend = true";
		}
		sql += " ORDER BY ent_year, class_num, no, is_attend";

		PreparedStatement st = con.prepareStatement(sql);
		String cd = (school == null || school.getCd() == null) ? "" : school.getCd();
		st.setString(1, "%" + cd + "%");
		st.setInt(2, entYear);

		ResultSet rs = st.executeQuery();
		list = postfilter(rs, school);

		rs.close();
		st.close();
		con.close();

		return list;
	}


	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		Connection con = getConnection();

		String sql = baseSql + " AND ent_year = ? AND class_num = ?";
		if (isAttend) {
			sql += " AND is_attend = true";
		}
		sql += " ORDER BY ent_year, class_num, no, is_attend";

		PreparedStatement st = con.prepareStatement(sql);
		String cd = (school == null || school.getCd() == null) ? "" : school.getCd();
		st.setString(1, "%" + cd + "%");
		st.setInt(2, entYear);
		st.setString(3, classNum);

		ResultSet rs = st.executeQuery();
		list = postfilter(rs, school);

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

