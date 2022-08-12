package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.test.dto.BoardCommentDTO;
import com.test.dto.BoardDTO;
import com.test.dto.FileDTO;

@Mapper
public interface BoardMapper {

	BoardDTO selectBoardDTO(int bno);

	List<FileDTO> selectFileList(int bno);

	List<BoardCommentDTO> selectCommentDTO(int bno);

	Map<String, Object> selectNextBefore(int bno);

	int addBoardCount(int bno);

	List<BoardDTO> selectBoardList(int pageNo);

	int selectBoardCount();

	int selectBoardNo();

	int insertBoard(BoardDTO dto);

	int insertFileList(FileDTO dto);

	FileDTO selectFile(HashMap<String, Object> map);

}
