package com.example.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(Map<String, Object> map);
	
}
