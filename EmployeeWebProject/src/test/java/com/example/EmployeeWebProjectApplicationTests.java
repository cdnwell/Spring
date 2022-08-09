package com.example;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.EmployeeDTO;
import com.example.mapper.EmployeeMapper;

@SpringBootTest
class EmployeeWebProjectApplicationTests {
	@Autowired
	EmployeeMapper mapper;
	
	@DisplayName("테스트")
	@Test
	void contextLoads() {
		List<EmployeeDTO> list = mapper.selectAllEmployee();
		//list size = 28
		//unexpected 값으로 28을 비교하고 같다면 test는 fail이 되고 메세지가 나타난다.  
		assertNotEquals(28, list.size(),"결과를 불러오지 못했습니다.");
	}

}
