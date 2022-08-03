package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.StudentDTO;
import com.example.mapper.StudentMapper;

@Service
public class StudentService {
	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<StudentDTO> selectAllStudent() {
		return mapper.selectAllStudent();
	}

	public List<StudentDTO> searchStudent(String kind, String search) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.searchStudent(map);
	}
	
	
	
	
}
