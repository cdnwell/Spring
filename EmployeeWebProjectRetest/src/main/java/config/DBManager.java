package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	private Connection connection;
	private static DBManager instance = new DBManager();

	public static DBManager getInstance() {

		return instance;
	}

	private DBManager() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.1.1:1521:xe", "scott2", "tiger");
			System.out.println("DB 연결 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
