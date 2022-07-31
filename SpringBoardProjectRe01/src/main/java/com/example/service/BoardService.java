package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.BoardCommentDTO;
import com.example.dto.BoardDTO;
import com.example.dto.FileDTO;
import com.example.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}

	public int selectAllCount() {
		return mapper.selectAllCount();
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		return mapper.selectBoardList(pageNo);
	}

	public int addBoardCount(int bno) {
		return mapper.addBoardCount(bno);
	}

	public BoardDTO selectBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	public List<BoardCommentDTO> selectCommentList(int bno) {
		return mapper.selectCommentList(bno);
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public int deleteBoard(int bno) {
		return mapper.deleteBoard(bno);
	}

	public int selectBoardNo() {
		return mapper.selectBoardNo();
	}

	public int insertBoard(BoardDTO dto) {
		return mapper.insertBoard(dto);
	}

	public int insertFile(FileDTO file) {
		return mapper.insertFile(file);
	}

	public int insertBoardComment(BoardCommentDTO dto) {
		return mapper.insertBoardComment(dto);
	}

}
