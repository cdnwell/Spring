package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dto.MemberDTO;
import com.example.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String pass) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pass", pass);
		
		return mapper.login(map); 
	}
	
	
	
}
