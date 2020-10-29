package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {

	private Connection conn;
	private ResultSet rs;

	public BbsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "mysql";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDate() {
		String SQL = "SELECT NOW()";

		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ""; // 데이터베이스 오류
	}

	public int getNext() {
		String SQL = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";

		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 첫 번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, getNext());
			pstm.setString(2, bbsTitle);
			pstm.setString(3, userID);
			pstm.setString(4, bbsContent);
			pstm.setString(5, getDate());
			pstm.setInt(6, 1);
			return pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

}
