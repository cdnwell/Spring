package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.BoardCommentDTO;
import com.example.dto.BoardDTO;
import com.example.dto.FileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList(int pageNo);

	int selectBoardCount();

	int addBoardCount(int bno);

	BoardDTO selectBoardDTO(int bno);

	List<FileDTO> selectFileList(int bno);

	List<BoardCommentDTO> selectCommentDTO(int bno);

}
