package com.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.BoardCommentDTO;
import com.example.dto.BoardDTO;
import com.example.dto.FileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardList(int pageNo);
	int selectBoardCount();
	BoardDTO selectBoard(int bno);
	List<FileDTO> selectFileList(int bno);
	List<BoardCommentDTO> selectComment(int bno);
	int addBoardCount(int bno);
	int commentDelete(int cno);
	int insertComment(HashMap<String, Object> map);
	int selectBoardNo();
	void insertBoard(BoardDTO dto);
	int insertFileList(FileDTO fileDTO);
	FileDTO selectFile(HashMap<String, String> map);
	int insertBoardLike(HashMap<String, Object> map);
	int deleteBoardLike(HashMap<String, Object> map);
	int insertBoardHate(HashMap<String, Object> map);
	int deleteBoardHate(HashMap<String, Object> map);
	int insertCommentLike(HashMap<String, Object> map);
	int deleteCommentLike(HashMap<String, Object> map);
	int insertCommentHate(HashMap<String, Object> map);
	int deleteCommentHate(HashMap<String, Object> map);
	
}
