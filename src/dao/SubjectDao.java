package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

	public List<Subject> filter(School school) throws Exception {
		List<Subject> list = new ArrayList<>();

		// データベース接続取得
		Connection con = getConnection();

		// SQL文の準備
		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM subject WHERE school_cd LIKE ?");

		// schoolがnullまたはcdがnullの場合は""（空文字）を代入
		String cd = (school == null || school.getCd() == null) ? "" : school.getCd();
		st.setString(1, "%" + cd + "%");

		// SQL実行
		ResultSet rs = st.executeQuery();

		// 検索結果をリストに追加
		while (rs.next()) {
			Subject s = new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			list.add(s);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	public boolean save(Subject subject) throws Exception {
	    boolean result = false;

	    Connection con = getConnection();

	    // cdチェック
	    PreparedStatement checkSt = con.prepareStatement(
	    		"SELECT COUNT(*) FROM subject WHERE cd = ?");
	    checkSt.setString(1, subject.getCd());
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
	        st = con.prepareStatement("UPDATE subject SET name = ? WHERE cd = ?");
	        System.out.println("更新");
	        st.setString(1, subject.getName());
	        st.setString(2, subject.getCd());
	    } else {
	        // 挿入処理
	        st = con.prepareStatement("INSERT INTO subject (school_cd, cd, name) VALUES (?, ?, ?)");
	        System.out.println("挿入");
	        School school = subject.getSchool();
	        String school_cd = school.getCd();
	        st.setString(1, school_cd);
	        st.setString(2, subject.getCd());
	        st.setString(3, subject.getName());
	    }

	    int rows = st.executeUpdate();
	    result = rows > 0;

	    st.close();
	    con.close();

	    return result;
	}

	public Subject get(String cd ,School school) throws Exception {

		// データベース接続取得
		Connection con = getConnection();

		// SQL文の準備
		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM subject WHERE school_cd = ? AND cd = ?");

		// schoolがnullまたはcdがnullの場合は""（空文字）を代入
		String s_cd = school.getCd();
		st.setString(1, s_cd);
		st.setString(2, cd);

		// SQL実行
		ResultSet rs = st.executeQuery();

		// 検索結果を変数に追加
		Subject sub = new Subject();
		while (rs.next()) {
			sub.setCd(rs.getString("cd"));
			sub.setName(rs.getString("name"));

		}

		rs.close();
		st.close();
		con.close();

		return sub;
	}

}
