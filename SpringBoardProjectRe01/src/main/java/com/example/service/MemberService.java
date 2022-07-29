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
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		return mapper.login(map);
	}
	

}
