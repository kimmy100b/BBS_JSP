package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

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

	public String setSecPW(String userPassword) throws NoSuchAlgorithmException {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(userPassword.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM user WHERE userID = ?";

		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(setSecPW(userPassword)))
					return 1; // 로그인 성공
				else {
					return 0; // 비밀번호 불일치
				}
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
			pstm.setString(1, user.getUserID());
			pstm.setString(2, setSecPW(user.getUserPassword()));
			pstm.setString(3, user.getUserName());
			pstm.setString(4, user.getUserGender());
			pstm.setString(5, user.getUserEmail());

			return pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1; // 데이터베이스 오류
	}

}
