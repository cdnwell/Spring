package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.BoardCommentDTO;
import com.example.dto.BoardDTO;
import com.example.dto.FileDTO;

@Mapper
public interface BoardMapper {

	int selectAllCount();

	List<BoardDTO> selectBoardList(int pageNo);

	int addBoardCount(int bno);

	BoardDTO selectBoard(int bno);

	List<BoardCommentDTO> selectCommentList(int bno);

	List<FileDTO> selectFileList(int bno);

	int deleteBoard(int bno);

	int selectBoardNo();

	int insertBoard(BoardDTO dto);

	int insertFile(FileDTO file);

}
