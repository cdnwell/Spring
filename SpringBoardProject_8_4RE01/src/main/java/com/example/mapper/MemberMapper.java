package com.example.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(Map<String, Object> map);
	MemberDTO selectMember(String id);
	int updateMember(MemberDTO dto);
	String selectId(String id);
	int insertMember(MemberDTO dto);
	
}
