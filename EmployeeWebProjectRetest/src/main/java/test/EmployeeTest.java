package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

class EmployeeTest {
	private EmployeeDAO instance = EmployeeDAO.getInstance();
	
	@BeforeEach
	void beforeTest() {
		EmployeeDTO dto = new EmployeeDTO("SS99", "홍길동", "4", 5);
		
		try {
			int result = instance.insertEmployee(dto);
			if(result == 0) throw new Exception();
		} catch (Exception e) {
			fail("데이터를 입력하지 못하였거나 오류가 발생하였습니다.");
		}
		
	}
	
	@Test
	void test() {
		String sql = "select * from employee where department = 5";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				   list.add(new EmployeeDTO(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4))); 
			}
			if(list.isEmpty()) throw new Exception();
		} catch (Exception e) {
			fail("데이터를 찾지 못하였거나 오류가 발생하였습니다.");
		}
		
	}
	
	@AfterEach
	void afterTest() {
		try {
			int result = instance.deleteEmployeeDao("SS99");
			if(result == 0) throw new Exception();
		} catch (Exception e) {
			fail("데이터를 삭제하지 못하였거나 오류가 발생하였습니다.");
		}
	}

}
