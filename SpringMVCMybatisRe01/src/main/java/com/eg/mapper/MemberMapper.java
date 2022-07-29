package com.eg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eg.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	List<MemberDTO> selectAllMember();

	int insertMember(MemberDTO dto);

	int deleteMember(String id);

	MemberDTO selectMember(String id);

	int updateMember(MemberDTO dto);

}
