package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
	public ClassNum get(String class_num ,School school) throws Exception {

		// データベース接続取得
		Connection con = getConnection();

		// SQL文の準備
		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM class_num WHERE school_cd = ? AND class_num = ?");

		String s_cd = school.getCd();
		st.setString(1, s_cd);
		st.setString(2, class_num);

		// SQL実行
		ResultSet rs = st.executeQuery();

		// 検索結果を変数に追加
		ClassNum cln = new ClassNum();
		school.setCd(rs.getString("school_cd"));
		while (rs.next()) {
			cln.setSchool(school);
			cln.setClass_num(class_num);
		}

		rs.close();
		st.close();
		con.close();

		return cln;
	}

	public List<String> filter(School school) throws Exception {
		List<String> list = new ArrayList<>();

		// データベース接続取得
		Connection con = getConnection();

		// SQL文の準備
		PreparedStatement st = con.prepareStatement(
			"SELECT class_num FROM class_num WHERE school_cd LIKE ?");

		// schoolがnullまたはcdがnullの場合は""（空文字）を代入
		String cd = (school == null || school.getCd() == null) ? "" : school.getCd();
		st.setString(1, "%" + cd + "%");

		// SQL実行
		ResultSet rs = st.executeQuery();

		// 検索結果をリストに追加
		while (rs.next()) {
			list.add(rs.getString("class_num"));
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}
}
