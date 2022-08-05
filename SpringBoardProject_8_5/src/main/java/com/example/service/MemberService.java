package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		return mapper.login(map);
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}

	public MemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}

	public ArrayList<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}

	public int updateMember(MemberDTO dto) {
		return mapper.updateMember(dto);
	}

	public List<MemberDTO> selectMember1(String kind, String search) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("kind", kind);
		if(kind.equals("grade"))
			map.put("search", Integer.parseInt(search));
		else
			map.put("search", search);
		return mapper.selectMember1(map);
	}

	
	
}
