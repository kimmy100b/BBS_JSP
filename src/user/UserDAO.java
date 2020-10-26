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
			String dbURL="jdbc:mysql://localhost:3306/bbs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "mysql";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM user WHERE userID = ?";
		
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if(rs.next()) {
				if(rs.getNString(1).equals(userPassword))
					return 1; // �α��� ����
				else
					return 0; // ��й�ȣ ����ġ
			}
			return -1; // ���̵� ����
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // �����ͺ��̽� ����
	}

}



