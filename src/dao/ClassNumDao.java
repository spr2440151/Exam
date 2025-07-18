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

	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement(
	        "SELECT * FROM class_num WHERE school_cd = ? AND class_num = ?");

	    String s_cd = school.getCd();
	    st.setString(1, s_cd);
	    st.setString(2, class_num);

	    ResultSet rs = st.executeQuery();

	    ClassNum cln = null;

	    if (rs.next()) {
	        cln = new ClassNum();
	        school.setCd(rs.getString("school_cd"));
	        cln.setSchool(school);
	        cln.setClass_num(rs.getString("class_num"));
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

	public boolean save(ClassNum classNum, String num) throws Exception {
	    boolean result = false;

	    // データベース接続取得
	    Connection con = getConnection();

	    // Schoolオブジェクトから学校コード取得
	    School school = classNum.getSchool();
	    String school_cd = school.getCd();

	    // 新しいクラス番号
	    String class_num = classNum.getClass_num();

	    // 主キー（class_num）が変更されていない場合 → MERGEでUPSERT
	    if (num.equals(class_num)) {
	        // MERGE文は、既にデータがあれば更新、なければ挿入を行う
	        String sql = "MERGE INTO class_num (school_cd, class_num) " +
	                     "KEY (school_cd, class_num) " +
	                     "VALUES (?, ?)";

	        try (PreparedStatement st = con.prepareStatement(sql)) {
	            st.setString(1, school_cd);
	            st.setString(2, class_num);
	            System.out.println("MERGE（更新または挿入）実行");

	            result = st.executeUpdate() > 0;
	        }
	    } else {
	        // 主キー（class_num）が変更されている場合 → DELETE + INSERT

	        // まず旧クラス番号でDELETE
	        String deleteSql = "DELETE FROM class_num WHERE school_cd = ? AND class_num = ?";
	        try (PreparedStatement st = con.prepareStatement(deleteSql)) {
	            st.setString(1, school_cd);
	            st.setString(2, num);
	            System.out.println("DELETE実行（主キー変更対応）");
	            st.executeUpdate();
	        }

	        // 新しいクラス番号でINSERT
	        String insertSql = "INSERT INTO class_num (school_cd, class_num) VALUES (?, ?)";
	        try (PreparedStatement st = con.prepareStatement(insertSql)) {
	            st.setString(1, school_cd);
	            st.setString(2, class_num);
	            System.out.println("INSERT実行（主キー変更対応）");

	            result = st.executeUpdate() > 0;
	        }
	    }

	    // データベース接続を閉じる
	    con.close();

	    return result;
	}


	public boolean delete(ClassNum classNum) throws Exception {
	    boolean result = false;

	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement(
	        "DELETE FROM class_num WHERE class_num = ? AND school_cd = ?"
	    );
	    School school = classNum.getSchool();
	    String school_cd = school.getCd();
	    st.setString(1, classNum.getClass_num());
	    st.setString(2, school_cd);

	    int rows = st.executeUpdate();
	    System.out.println(rows);
	    result = rows < 0;

	    st.close();
	    con.close();

	    return result;
	}
}
