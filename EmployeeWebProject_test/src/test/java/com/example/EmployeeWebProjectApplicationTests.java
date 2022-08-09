package com.example;

import static org.assertj.core.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.EmployeeDTO;
import com.example.mapper.EmployeeMapper;

@SpringBootTest
class EmployeeWebProjectApplicationTests {
	//그동안 만든 테스트 다 스되 autowired만 추가 되었다.
	@Autowired
	EmployeeMapper mapper;
	
	@DisplayName("전체 조회 테스트")
	@Test
	void testSelectAllEmployee() {
		List<EmployeeDTO> list = mapper.selectAllEmployee();
		System.out.println(list);
		if(list.size()==0)
			fail("전체 조회 오류");
	}

}
