package com.example.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, String> map);

	int insertMember(MemberDTO dto);

	MemberDTO selectMember(String id);

	ArrayList<MemberDTO> selectAllMember();

	int deleteMember(String id);

	int updateMember(MemberDTO dto);

	List<MemberDTO> selectMember(Map<String, Object> map);

}
