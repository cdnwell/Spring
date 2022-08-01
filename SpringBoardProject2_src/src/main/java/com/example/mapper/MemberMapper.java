package com.example.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, String> map);

}
