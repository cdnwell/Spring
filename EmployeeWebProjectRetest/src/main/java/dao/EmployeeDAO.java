package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBManager;
import dto.EmployeeDTO;

public class EmployeeDAO {
	private static EmployeeDAO instance = new EmployeeDAO();
	private Connection conn;
	
	private EmployeeDAO() {
		conn = DBManager.getInstance().getConnection();
	}

	public static EmployeeDAO getInstance() {
		if(instance == null)
			instance = new EmployeeDAO();
		return instance;
	}


	public ArrayList<EmployeeDTO> selectEmployee(String kind, String search) {
		String sql = "select * from employee where ";
		if(kind.equals("eno") || kind.equals("name"))
			sql += kind + " like '%'||?||'%'";
		else
			sql += kind + " = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		try {
			pstmt = conn.prepareStatement(sql);
			if(kind.equals("eno") || kind.equals("name"))
				pstmt.setString(1, search);
			else
				pstmt.setInt(1, Integer.parseInt(search));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				   list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4))); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertEmployee(EmployeeDTO dto) throws SQLException {
		String sql = "insert into employee values(?,?,?,?)";
		PreparedStatement pstmt = null;
		int count = 0;
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getEno());
		pstmt.setString(2, dto.getName());
		pstmt.setString(3, dto.getDepartment());
		pstmt.setInt(4, dto.getPosition());
			
		count = pstmt.executeUpdate();
		return count;
	}

	public int deleteEmployeeDao(String eno) {
		String sql = "delete from employee where eno = ?";
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eno);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return count;
	}
}
























