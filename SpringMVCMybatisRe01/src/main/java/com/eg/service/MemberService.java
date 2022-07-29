package com.eg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eg.dto.MemberDTO;
import com.eg.mapper.MemberMapper;

@Service
public class MemberService {
	MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}

	public int delete(String id) {
		return mapper.deleteMember(id);
	}

	public MemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}

	public int updateMember(MemberDTO dto) {
		return mapper.updateMember(dto);
	}
	
	
}
