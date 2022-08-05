package com.example.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	int deleteBoard(int bno);

	int insertComment(BoardCommentDTO dto);

	int addBoardComment(BoardCommentDTO dto);

	int insertBoardLike(HashMap<String, Object> map);

	int deleteBoardLike(HashMap<String, Object> map);

	int insertBoardHate(HashMap<String, Object> map);

	int deleteBoardHate(HashMap<String, Object> map);

	int selectBoardNo();

	int insertBoard(BoardDTO dto);

	int insertFileList(FileDTO fileDTO);

	FileDTO selectFile(HashMap<String, String> map);

	int insertCommentLike(HashMap<String, String> map);

	int deleteCommentLike(HashMap<String, String> map);

	int insertCommentHate(HashMap<String, String> map);
	
	int deleteCommentHate(HashMap<String, String> map);

	int deleteComment(HashMap<String, Object> map);

	int deleteBoardComment(int cno);

	int selectBoardImageNo();

	int insertBoardImage(Map<String, Object> map);

	String selectImageFile(int fno);

}
