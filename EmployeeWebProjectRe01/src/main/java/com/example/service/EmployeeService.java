package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.EmployeeDTO;
import com.example.mapper.EmployeeMapper;

@Service
public class EmployeeService {
	private EmployeeMapper mapper;

	public EmployeeService(EmployeeMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<EmployeeDTO> selectAllEmployee() {
		return mapper.selectAllEmployee();
	}

	public List<EmployeeDTO> searchEmplyee(String kind, String search) {
		HashMap<String, String> map = new HashMap<>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.searchEmployee(map);
	}
	
	
	
}
