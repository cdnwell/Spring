package com.example.service;

import java.util.HashMap;
import java.util.List;

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

	public List<MemberDTO> selectAll() {
		return mapper.selectAll();
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}

	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}

	public int insertErrorLog(String log, String result) {
		HashMap<String, String> map = new HashMap<>();
		map.put("log", log);
		map.put("result", result);
		return mapper.insertErrorLog(map);
	}
	
	
	
	
}
