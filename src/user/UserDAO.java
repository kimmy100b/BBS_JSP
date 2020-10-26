package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;

	public UserDAO() {
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

	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM user WHERE userID = ?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if (rs.next()) {
				if (rs.getNString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}

	public int join(User user) {
		String SQL = "INSERT INTO user VALUES (?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setNString(1, user.getUserID());
			pstm.setNString(2, user.getUserPassword());
			pstm.setNString(3, user.getUserName());
			pstm.setNString(4, user.getUserGender());
			pstm.setNString(5, user.getUserEmail());
			return pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 데이터베이스 오류
	}

}
