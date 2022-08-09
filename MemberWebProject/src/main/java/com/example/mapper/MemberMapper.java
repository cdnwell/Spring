package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	List<MemberDTO> selectAll();
	int insertMember(MemberDTO dto);
	int deleteMember(String id);
	int insertErrorLog(HashMap<String, String> map);

}
