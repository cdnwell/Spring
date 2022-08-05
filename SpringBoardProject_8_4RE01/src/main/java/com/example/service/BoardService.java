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
		super();
		this.mapper = mapper;
	}

	public List<BoardDTO> selectBoardList(int pageNo) {
		return mapper.selectBoardList(pageNo);
	}

	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	public BoardDTO selectBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public List<BoardCommentDTO> selectComment(int bno) {
		return mapper.selectComment(bno);
	}

	public int addBoardCount(int bno) {
		return mapper.addBoardCount(bno);
	}
	
	
	
}
