package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	int selectAllCount();

	List<BoardDTO> selectBoardList(int pageNo);

}
